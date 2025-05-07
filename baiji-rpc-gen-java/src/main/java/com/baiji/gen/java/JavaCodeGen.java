package com.baiji.gen.java;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.common.grammar.definition.ClassInfoDefinition;
import com.baiji.common.grammar.definition.FieldInfoDefinition;
import com.baiji.common.grammar.definition.MethodDefinition;
import com.baiji.common.util.CollectionUtils;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.model.DeploymentRepository;
import org.apache.maven.model.DistributionManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaCodeGen implements LangHandler {

    private final static String DTO_TEMPLATE_PATH = "dto.ftl";
    private final static String INTERFACE_TEMPLATE_PATH = "interface.ftl";
    private final static String SERVICE_TEMPLATE_PATH = "service.ftl";

    private final static String BASE_PATH = System.getProperty("user.dir");
    private final static String GENERATE_SOURCE_PATH_NAME = BASE_PATH + File.separator + "generate_source/src/main/java/";

    @Override
    public String language() {
        return "Java";
    }

    @Override
    public void generate(BaijiGrammarDefinition definition) throws Exception {
        Path generatesourcepath = Paths.get(GENERATE_SOURCE_PATH_NAME);
        if (!Files.exists(generatesourcepath)) {
            Files.createDirectories(generatesourcepath);
        }
        String packagePath = definition.getPackageName().replace(".", File.separator) + File.separator;
        File targetRootLocation = new File(GENERATE_SOURCE_PATH_NAME + packagePath);
        if (!targetRootLocation.exists()) {
            targetRootLocation.mkdirs();
        }
        interfaceGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
        dtoGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
        serviceGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
    }

    @Override
    public void deploy( DeployInfo deployInfo) throws Exception {
        File pom = new File(BASE_PATH + File.separator + "generate_source/pom.xml");
        generatePomFile(deployInfo, pom);
        deployToMavenRepository(pom);
    }

    public static File generatePomFile(DeployInfo deployInfo, File pom) throws Exception {
        Model model = new Model();
        model.setModelVersion("4.0.0");
        model.setGroupId(deployInfo.getGroupId());
        model.setArtifactId(deployInfo.getArtifactId());
        model.setVersion(deployInfo.getVersion());

        // TODO: 2025/3/9 这两个写到配置文件中
        DistributionManagement distributionManagement = new DistributionManagement();
        DeploymentRepository repository = new DeploymentRepository();
        repository.setId("maven-releases");
        repository.setUrl("http://127.0.0.1:8081/repository/maven-releases/");
        distributionManagement.setRepository(repository);

        DeploymentRepository snapshotRepository = new DeploymentRepository();
        snapshotRepository.setId("maven-snapshots");
        snapshotRepository.setUrl("http://127.0.0.1:8081/repository/maven-snapshots/");
        distributionManagement.setSnapshotRepository(snapshotRepository);

        model.setDistributionManagement(distributionManagement);

//        File pomFile = new File("pom.xml");
        try (FileWriter writer = new FileWriter(pom)) {
            MavenXpp3Writer pomWriter = new MavenXpp3Writer();
            pomWriter.write(writer, model);
        }
        return pom;
    }

    /**
     * 使用 MavenEmbedder 发布项目
     */
    private static void deployToMavenRepository(File pomFile) {
        MavenCli cli = new MavenCli();
        System.setProperty("maven.multiModuleProjectDirectory", pomFile.getParent());

        // TODO: 2025/3/9 手动在项目同目录下指定本地仓库位置（没有即创建），然后通过doMain的传参方式传入（xml文件中不支持创建文件夹）
        String resource = JavaCodeGen.class.getResource("/maven/settings.xml").getPath();
        int result = cli.doMain(new String[]{"clean", "deploy", "-X", "-s", resource}, pomFile.getParent(), System.out, System.err);

        if (result != 0) {
            throw new RuntimeException("Maven deploy failed!");
        }
    }

    private void serviceGenerator(BaijiGrammarDefinition definition, File targetRootLocation, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(SERVICE_TEMPLATE_PATH);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("packageName", definition.getPackageName());
            dataModel.put("serviceName", definition.getServiceInfo().getServiceName());
            dataModel.put("appid", definition.getAppid());
            dataModel.put("methods", definition.getServiceInfo().getMethodDefinitionList());
            String fileName = String.format("%sClient.java", definition.getServiceInfo().getServiceName());
            File outputFile = new File(targetRootLocation, fileName);
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            Writer fileWriter = new FileWriter(outputFile);
            template.process(dataModel, fileWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dtoGenerator(BaijiGrammarDefinition definition, File targetRootLocation, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(DTO_TEMPLATE_PATH);
            for (ClassInfoDefinition cls : definition.getClsInfo()) {
                Map<String, Object> dataModel = new HashMap<>();
                dataModel.put("packageName", definition.getPackageName());
                dataModel.put("className", cls.getClsName());
                dataModel.put("fields", parseDtype(cls.getFieldInfos()));
                dataModel.put("isReqCls", isReqCls(cls.getClsName(), definition.getServiceInfo().getMethodDefinitionList()));
                //开始生成代码
                String fileName = String.format("%s.java", cls.getClsName());
                File outputFile = new File(targetRootLocation, fileName);
                File parentDir = outputFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                Writer fileWriter = new FileWriter(outputFile);
                template.process(dataModel, fileWriter);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isReqCls(String clsName, List<MethodDefinition> methodDefinitionList) {
        for (MethodDefinition methodDefinition : methodDefinitionList) {
            if (clsName.equalsIgnoreCase(methodDefinition.getReqType())) {
                return true;
            }
        }
        return false;
    }

    private List<FieldInfoDefinition> parseDtype(List<FieldInfoDefinition> fieldInfoDefinitionList) {
        if (CollectionUtils.isEmpty(fieldInfoDefinitionList)) {
            return new ArrayList<>();
        }
        List<FieldInfoDefinition> res = new ArrayList<>();
        for (FieldInfoDefinition field : fieldInfoDefinitionList) {
            FieldInfoDefinition temp = new FieldInfoDefinition();
            temp.setFieldName(field.getFieldName());
            temp.setDataType(fieldWithGenerics(field.getDataType(), field.getGenerics()));
            temp.setComment(field.getComment());
            res.add(temp);
        }
        return res;
    }

    private String fieldWithGenerics(String fieldType, List<String> generics) {
        if (CollectionUtils.isEmpty(generics)) {
            return BaijiJavaDataTypeMap.getMapType(fieldType);
        }
        StringBuilder sb = new StringBuilder(BaijiJavaDataTypeMap.getMapType(fieldType));
        sb.append("<");
        for (int i = 0; i < generics.size(); i++) {
            sb.append(BaijiJavaDataTypeMap.getMapType(generics.get(i)));
            if (i != generics.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(">");
        return sb.toString();
    }

    private void interfaceGenerator(BaijiGrammarDefinition definition, File targetRootLocation, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(INTERFACE_TEMPLATE_PATH);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("packageName", definition.getPackageName());
            dataModel.put("serviceName", definition.getServiceInfo().getServiceName());
            List<MethodDefinition> serviceMethods = definition.getServiceInfo().getMethodDefinitionList();
            dataModel.put("serviceMethods", serviceMethods);
            String fileName = String.format("%s.java", definition.getServiceInfo().getServiceName());
            File outputFile = new File(targetRootLocation, fileName);
            File parentDir = outputFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            Writer fileWriter = new FileWriter(outputFile);
            template.process(dataModel, fileWriter);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}

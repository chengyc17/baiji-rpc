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
        //最终java文件生成的位置
        File targetRootLocation = new File(GENERATE_SOURCE_PATH_NAME + packagePath);
        if (!targetRootLocation.exists()) {
            targetRootLocation.mkdirs();
        }
        interfaceGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
        dtoGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
        serviceGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());

        generatePOM();
//        deploy(null, null);
    }

    @Override
    public void deploy(String generateCodeLocation, DeployInfo deployInfo) throws Exception {

    }

    public static File generatePomFile() throws Exception {
        // 1. 创建 Maven 项目模型
        Model model = new Model();
        model.setModelVersion("4.0.0");
        model.setGroupId("com.example");
        model.setArtifactId("my-artifact");
        model.setVersion("1.0.0");

        // 2. 将模型写入 pom.xml
        File pomFile = new File("pom.xml");
        try (FileWriter writer = new FileWriter(pomFile)) {
            MavenXpp3Writer pomWriter = new MavenXpp3Writer();
            pomWriter.write(writer, model);
        }

        System.out.println("pom.xml generated successfully at: " + pomFile.getAbsolutePath());
        return pomFile;
    }

    /**
     * 使用 MavenEmbedder 发布项目
     */
    private static void deployToMavenRepository(File pomFile) throws Exception {
        // 1. 初始化 MavenCli
        MavenCli cli = new MavenCli();
        System.setProperty("maven.multiModuleProjectDirectory", new File(".").getAbsolutePath());

        // 2. 动态设置仓库地址和认证信息
        String repositoryUrl = "http://localhost:8081/repository/maven-releases/";
        String repositoryId = "nexus-releases";
        String username = "admin";
        String password = "admin123";

        // 3. 执行 Maven deploy 命令，动态传递仓库地址和认证信息
        System.out.println("Deploying project to Maven repository...");
        int result = cli.doMain(
                new String[]{
                        "deploy",
                        "-DaltDeploymentRepository=" + repositoryId + "::default::" + repositoryUrl,
                        "-DrepositoryId=" + repositoryId,
                        "-DrepositoryUsername=" + username,
                        "-DrepositoryPassword=" + password
                },
                ".", System.out, System.err
        );

        // 4. 检查执行结果
        if (result != 0) {
            throw new RuntimeException("Maven deploy failed!");
        }
        System.out.println("Deploy completed successfully!");
    }

    private void generatePOM() {


//        Model model = new Model();
//        model.setModelVersion("4.0.0");
//        model.setGroupId("com.example");
//        model.setArtifactId("my-artifact");
//        model.setVersion("1.0.0");
//        // 写入 pom.xml 文件
//        try (FileWriter writer = new FileWriter(new File(BASE_PATH + File.separator + "generate_source/pom.xml"))) {
//            MavenXpp3Writer pomWriter = new MavenXpp3Writer();
//            pomWriter.write(writer, model);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        MavenExecutionRequest request = new DefaultMavenExecutionRequest();
//        request.setPom(new File(""));
//        request.setGoals(List.of("deploy"));
//
//        Settings settings = new Settings();
//        Server server = new Server();
//        server.setId("my-repo");
//        server.setUsername("admin");
//        server.setPassword("yc19930117");
//        settings.addServer(server);
//
////        List<RemoteRepository> remoteRepositories = new ArrayList<>();
//        List<RemoteRepository> remoteRepositories = new ArrayList<>();
//        RemoteRepository repo = new RemoteRepository.Builder("my-repo", "default", "http://your-repo-url/repository/maven-releases/").build();
//        remoteRepositories.add(repo);
//        request.setRemoteRepositories(remoteRepositories);
////        request.setRemoteRepositories(remoteRepositories);
//
//        // 创建 RepositorySystem
//        RepositorySystem system = newRepositorySystem();
//        RepositorySystemSession session = newSession(system, settings);
//        request.setRepositorySession(session);
//
//
//        // 创建 ModelBuilder
//        ModelBuilder modelBuilder = new DefaultModelBuilderFactory().newInstance();
//        ModelBuildingRequest modelBuildingRequest = new DefaultModelBuildingRequest();
//        modelBuildingRequest.setPomFile(request.getPom());
//        modelBuildingRequest.setRepositorySession(session);
//        modelBuildingRequest.setModelResolver(new ModelResolver() {
//            @Override
//            public void addRemoteRepository(RemoteRepository repository) {
//                // 可根据需要实现
//            }
//
//            @Override
//            public org.apache.maven.model.Model resolveModel(String groupId, String artifactId, String version) throws org.apache.maven.model.resolution.UnresolvableModelException {
//                // 可根据需要实现
//                return null;
//            }
//
//            @Override
//            public ModelResolver newCopy() {
//                // 可根据需要实现
//                return null;
//            }
//        });
//        request.setModelBuildingRequest(modelBuildingRequest);
//
//        // 执行 Maven 命令
//        MavenCli cli = new MavenCli();
//        MavenExecutionResult result = cli.doMain(request);
//        if (result.hasExceptions()) {
//            result.getExceptions().forEach(Throwable::printStackTrace);
//        } else {
//            System.out.println("Deployment successful.");
//        }
    }

//    private static RepositorySystem newRepositorySystem() {
//        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
//        locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
//        locator.addService(TransporterFactory.class, FileTransporterFactory.class);
//        locator.addService(TransporterFactory.class, HttpTransporterFactory.class);
//        return locator.getService(RepositorySystem.class);
//    }
//
//    private static RepositorySystemSession newSession(RepositorySystem system, Settings settings) {
//        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
//        LocalRepository localRepo = new LocalRepository("~/.m2/repository");
//        session.setLocalRepositoryManager(system.newLocalRepositoryManager(session, localRepo));
//        return session;
//    }

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

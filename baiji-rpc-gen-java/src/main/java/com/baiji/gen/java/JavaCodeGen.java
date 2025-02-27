package com.baiji.gen.java;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.common.grammar.definition.ClassInfoDefinition;
import com.baiji.common.grammar.definition.MethodDefinition;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaCodeGen implements LangHandler {

    private final static String DTO_TEMPLATE_PATH = "dto.flt";
    private final static String INTERFACE_TEMPLATE_PATH = "interface.ftl";
    private final static String SERVICE_TEMPLATE_PATH = "service.flt";

    private final static String BASE_PATH = System.getProperty("user.dir");
    private final static String GENERATE_SOURCE_PATH_NAME = BASE_PATH + File.separator + "generate_source/";

    @Override
    public String language() {
        return "Java";
    }

    @Override
    public void generate(BaijiGrammarDefinition definition) throws Exception {
        Path generatesourcepath = Paths.get(GENERATE_SOURCE_PATH_NAME);
        if (!Files.exists(generatesourcepath)) {
            Files.createDirectory(generatesourcepath);
        }
        String packagePath = definition.getPackageName().replace(".", File.separator) + File.separator;
        //最终java文件生成的位置
        File targetRootLocation = new File(GENERATE_SOURCE_PATH_NAME + packagePath);
        if (!targetRootLocation.exists()) {
            targetRootLocation.mkdirs();
        }
        interfaceGenerator(definition, targetRootLocation, FreeMarkerConfig.getConfig());
    }

    @Override
    public void deploy(String generateCodeLocation, DeployInfo deployInfo) throws Exception {

    }

    private void dtoGenerator(BaijiGrammarDefinition definition, File targetRootLocation, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(DTO_TEMPLATE_PATH);
            for (ClassInfoDefinition cls : definition.getClsInfo()) {

                Map<String, Object> dataModel = new HashMap<>();

                dataModel.put("className", cls.getClsName());
            }


            definition.getClsInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void interfaceGenerator(BaijiGrammarDefinition definition, File targetRootLocation, Configuration cfg) {
        try {
            Template template = cfg.getTemplate(INTERFACE_TEMPLATE_PATH);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("packageName", definition.getPackageName());
            dataModel.put("serviceName", definition.getServiceInfo().getServiceName());
            List<MethodDefinition> serviceMethods = definition.getServiceInfo().getMethodDefinitionList();
            dataModel.put("serviceMethods", serviceMethods);
            String fileName = String.format("%sClient.java", definition.getServiceInfo().getServiceName());
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

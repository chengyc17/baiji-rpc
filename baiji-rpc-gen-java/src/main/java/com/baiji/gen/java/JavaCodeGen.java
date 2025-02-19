package com.baiji.gen.java;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;

public class JavaCodeGen implements LangHandler {

    @Override
    public String language() {
        return "Java";
    }

    @Override
    public void generate(BaijiGrammarDefinition definition) throws Exception {


    }

    @Override
    public void deploy(String generateCodeLocation, DeployInfo deployInfo) throws Exception {

    }

    private void interfaceGenerator(BaijiGrammarDefinition definition) {
        Configuration cfg = FreeMarkerConfig.getConfig();

        try {
            Template template = cfg.getTemplate("Interface.ftl");

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}

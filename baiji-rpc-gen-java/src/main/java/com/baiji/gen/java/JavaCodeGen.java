package com.baiji.gen.java;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;
import com.baiji.spi.DeployInfo;
import com.baiji.spi.LangHandler;

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
}

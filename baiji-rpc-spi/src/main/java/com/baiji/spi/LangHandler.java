package com.baiji.spi;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;

public interface LangHandler {
    /**
     * 编程语言类型
     */
    String language();

    /**
     * 编程语言代码生成
     *
     * @throws Exception
     */
    void generate(BaijiGrammarDefinition definition) throws Exception;

    /**
     * 部署到不同的代码仓库
     *
     * @throws Exception
     */
    void deploy(DeployInfo deployInfo) throws Exception;
}

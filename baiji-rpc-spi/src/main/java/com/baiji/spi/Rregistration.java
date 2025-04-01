package com.baiji.spi;

import com.baiji.common.grammar.definition.BaijiGrammarDefinition;

/**
 * 进行接口契约的注册，留下的扩展点
 */
public interface Rregistration {
    void register(BaijiGrammarDefinition definition) throws Exception;
}

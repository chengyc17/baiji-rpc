package com.baiji.spi;

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
    void generate() throws Exception;

    /**
     * 部署到不同的代码仓库
     *
     * @throws Exception
     */
    void deploy() throws Exception;
}

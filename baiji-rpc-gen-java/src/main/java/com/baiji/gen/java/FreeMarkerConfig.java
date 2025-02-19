package com.baiji.gen.java;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerConfig {
    public static Configuration getConfig() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        try {
            // 设置模板加载路径（这里设置为类路径）
            cfg.setClassForTemplateLoading(FreeMarkerConfig.class, "/templates");
            // 设置默认编码
            cfg.setDefaultEncoding("UTF-8");
            // 设置模板异常处理方式
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cfg;
    }
}

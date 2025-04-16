package com.baiji.config;

import com.baiji.annotation.JsonConfig;
import com.baiji.annotation.PropertiesConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ScanConfigAnnotations implements BeanPostProcessor {

    private Map<String, Set<FieldWrapper>> jsonConfigMap = new HashMap<>();
    private Map<String, Set<FieldWrapper>> propertiesConfigMap = new HashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean == null) {
            return bean;
        }
        Class<?> aClass = bean.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        if (declaredFields == null || declaredFields.length == 0) {
            return bean;
        }
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            JsonConfig jsonConfig = declaredField.getAnnotation(JsonConfig.class);
            if (jsonConfig != null) {
                String configFileName = jsonConfig.value();
                jsonConfigMap.getOrDefault(configFileName, new HashSet<>()).add(new FieldWrapper(declaredField, bean));
                continue;
            }
            PropertiesConfig propertiesConfig = declaredField.getAnnotation(PropertiesConfig.class);
            if (propertiesConfig != null) {
                String configFileName = propertiesConfig.value();
                propertiesConfigMap.getOrDefault(configFileName, new HashSet<>()).add(new FieldWrapper(declaredField, bean));
                continue;
            }
        }
        return bean;
    }

    public Map<String, Set<FieldWrapper>> getJsonConfigMap() {
        return jsonConfigMap;
    }

    public Map<String, Set<FieldWrapper>> getPropertiesConfigMap() {
        return propertiesConfigMap;
    }
}

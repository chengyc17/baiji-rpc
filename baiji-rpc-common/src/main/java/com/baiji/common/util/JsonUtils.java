package com.baiji.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("serialize error", e);
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize error", e);
        }
    }

    public static <T> T deserialize(File file, Class<T> cls) {
        try {
            return objectMapper.readValue(file, cls);
        } catch (IOException e) {
            throw new RuntimeException("deserialize error", e);
        }
    }


    public static <T> T deserialize(String json, TypeReference<T> tTypeReference) {
        try {
            return objectMapper.readValue(json, tTypeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize error", e);
        }
    }

    public static <T> T deserialize(File json, TypeReference<T> tTypeReference) {
        try {
            return objectMapper.readValue(json, tTypeReference);
        } catch (IOException e) {
            throw new RuntimeException("deserialize error", e);
        }
    }

    public static String generateDefaultJson(Class<?> clazz) {
        ObjectNode rootNode = objectMapper.createObjectNode();
        generateJsonForClass(clazz, rootNode, new HashSet<>());
        return rootNode.toString();
    }

    private static void generateJsonForClass(Class<?> clazz, ObjectNode parentNode, Set<Class<?>> processedClasses) {
        if (processedClasses.contains(clazz)) {
            return; // 避免循环引用
        }
        processedClasses.add(clazz);

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                continue; // 跳过静态字段
            }

            String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            // 处理基础类型和String
            if (fieldType == int.class || fieldType == Integer.class) {
                parentNode.put(fieldName, 0);
            } else if (fieldType == String.class) {
                parentNode.put(fieldName, "");
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                parentNode.put(fieldName, false);
            } else if (fieldType == double.class || fieldType == Double.class) {
                parentNode.put(fieldName, 0.0);
            }
            // 其他基础类型...

            // 处理枚举类型
            else if (fieldType.isEnum()) {
                Object[] enumValues = fieldType.getEnumConstants();
                if (enumValues.length > 0) {
                    parentNode.put(fieldName, enumValues[0].toString());
                }
            }

            // 处理List/Set
            else if (List.class.isAssignableFrom(fieldType) || Set.class.isAssignableFrom(fieldType)) {
                ArrayNode arrayNode = parentNode.putArray(fieldName); // 生成空数组 []
                // 如果List有泛型参数，可以递归处理泛型类型（示例省略）
            }

            // 处理Map
            else if (Map.class.isAssignableFrom(fieldType)) {
                parentNode.putObject(fieldName); // 生成空对象 {}
            }

            // 处理自定义对象（递归）
            else if (!fieldType.isPrimitive() && !fieldType.isInterface()) {
                ObjectNode childNode = objectMapper.createObjectNode();
                parentNode.set(fieldName, childNode);
                generateJsonForClass(fieldType, childNode, new HashSet<>(processedClasses));
            }
        }
    }
}

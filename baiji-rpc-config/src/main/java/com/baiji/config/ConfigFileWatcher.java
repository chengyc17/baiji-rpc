package com.baiji.config;

import com.baiji.common.util.CollectionUtils;
import com.baiji.common.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
public class ConfigFileWatcher {

    @Autowired
    private ScanConfigAnnotations scanConfigAnnotations;

    private final WatchService watchService;
    private final Map<WatchKey, Path> keys;
    private final Path configDir = Paths.get(System.getProperty("user.dir"), "config");

    public ConfigFileWatcher() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        registerAll(configDir);

        new Thread(() -> startWatching()).start();
    }

    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private void handleJsonConfig(String fileName, String fullDir) {
        try {
            Map<String, Set<FieldWrapper>> jsonConfigMap = scanConfigAnnotations.getJsonConfigMap();
            Set<FieldWrapper> fieldWrappers = jsonConfigMap.get(fileName);
            if (CollectionUtils.isEmpty(fieldWrappers)) {
                return;
            }

            for (FieldWrapper fieldWrapper : fieldWrappers) {
                Field field = fieldWrapper.getField();
                Object bean = fieldWrapper.getBean();
                field.setAccessible(true);
                field.set(bean, JsonUtils.deserialize(new File(fullDir), bean.getClass()));
            }
        } catch (IllegalAccessException e) {
            // TODO: 2025/4/17  logging
        }
    }

    private void handlePropertiesConfig(String fileName, String fullDir) {
        try {
            Map<String, Set<FieldWrapper>> propertiesConfigMap = scanConfigAnnotations.getPropertiesConfigMap();
            Set<FieldWrapper> fieldWrappers = propertiesConfigMap.get(fileName);
            if (CollectionUtils.isEmpty(fieldWrappers)) {
                return;
            }

            for (FieldWrapper fieldWrapper : fieldWrappers) {
                Field field = fieldWrapper.getField();
                Object bean = fieldWrapper.getBean();
                field.setAccessible(true);
                field.set(bean, JsonUtils.deserialize(new File(fullDir), new TypeReference<Map<String, Object>>() {
                }));
            }
        } catch (IllegalAccessException e) {
            // TODO: 2025/4/17 logging
        }
    }

    private void startWatching() {
        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path configFileName = ev.context();
                Path fullDir = dir.resolve(configFileName);
                if (fullDir.endsWith(".json")) {
                    handleJsonConfig(configFileName.toString(), fullDir.toString());
                    continue;
                }
                if (fullDir.endsWith(".properties")) {
                    handlePropertiesConfig(configFileName.toString(), fullDir.toString());
                }
            }
        }
    }
}

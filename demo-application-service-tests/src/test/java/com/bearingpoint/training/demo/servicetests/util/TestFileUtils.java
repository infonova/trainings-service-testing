package com.bearingpoint.training.demo.servicetests.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class TestFileUtils {

    private TestFileUtils() {
    }

    public static String getFileAsString(String path) {
        try {
            ResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource("classpath:" + path);
            return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException var3) {
            throw new UncheckedIOException(String.format("Could not read file from Classpath '%s'.", path), var3);
        }
    }
}

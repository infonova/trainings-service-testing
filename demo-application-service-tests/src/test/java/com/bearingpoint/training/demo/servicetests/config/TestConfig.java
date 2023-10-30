package com.bearingpoint.training.demo.servicetests.config;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestConfig {

    private static final String DEFAULT_TEST_TARGET = "local";
    @Getter
    private static final String wiremockHost;
    @Getter
    private static final Integer wiremockPort;
    @Getter
    private static final String applicationHost;
    @Getter
    private static final Integer applicationPort;
    @Getter
    private static final String applicationUrl;
    @Getter
    private static final Boolean wiremockStartOnDemand;

    static {
        String testTarget = System.getProperty("test.target");
        if (testTarget == null) {
            testTarget = DEFAULT_TEST_TARGET;
        }

        String testConfigPath = "src/test/resources/targets/" + testTarget + ".properties";
        Path path = Paths.get(testConfigPath);
        if (!path.toFile().exists()) {
            throw new IllegalStateException("Failed to find test config file: " + testConfigPath);
        }

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(path.toFile())) {
            prop.load(input);
            wiremockHost = prop.getProperty("wiremock.host");
            wiremockPort = Integer.valueOf(prop.getProperty("wiremock.port"));
            wiremockStartOnDemand = Boolean.valueOf(prop.getProperty("wiremock.startOnDemand"));

            applicationHost = prop.getProperty("application.host");
            applicationPort = Integer.valueOf(prop.getProperty("application.port"));
            applicationUrl = String.format("http://%s:%d", applicationHost, applicationPort);

        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load property file: " + testConfigPath);
        }
    }
}

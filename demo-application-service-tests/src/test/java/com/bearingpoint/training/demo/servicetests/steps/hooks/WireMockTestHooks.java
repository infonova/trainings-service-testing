package com.bearingpoint.training.demo.servicetests.steps.hooks;

import com.bearingpoint.training.demo.servicetests.config.TestConfig;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class WireMockTestHooks {

    private static WireMockServer wireMockServer;
    private static final Boolean START_ONDEMAND = TestConfig.getWiremockStartOnDemand();
    private static final String WIREMOCK_HOST = TestConfig.getWiremockHost();
    private static final int WIREMOCK_PORT = TestConfig.getWiremockPort();
    private static final String LOCAL_WIREMOCK_HOST = "localhost";
    private static final int LOCAL_WIREMOCK_PORT = 8181;

    @BeforeAll()
    public static void setup() {
        if (START_ONDEMAND) {
            System.out.printf("Starting Wiremock on path: %s:%d%n", LOCAL_WIREMOCK_HOST, LOCAL_WIREMOCK_PORT);
            wireMockServer = new WireMockServer(WireMockConfiguration.options().port(LOCAL_WIREMOCK_PORT));
            wireMockServer.start();
            WireMock.configureFor("http", LOCAL_WIREMOCK_HOST, LOCAL_WIREMOCK_PORT, "");
        } else {
            WireMock.configureFor("http", WIREMOCK_HOST, WIREMOCK_PORT, "");
        }
    }

    @AfterAll
    public static void tearDown() {
        if (START_ONDEMAND) {
            System.out.printf("Stopping Wiremock on path: %s:%d%n", LOCAL_WIREMOCK_HOST, LOCAL_WIREMOCK_PORT);
            wireMockServer.stop();
        }
    }
}

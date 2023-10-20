package com.bearingpoint.training.demo.servicetests.steps.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class WireMockTestHooks {

    private static WireMockServer wireMockServer;
    private static final String WIREMOCK_HOST = "localhost";
    private static final int PORT = 8181;


    @BeforeAll()
    public static void setup() {
        System.out.printf("Starting Wiremock on path: %s:%d%n", WIREMOCK_HOST, PORT);
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8181));
        wireMockServer.start();
        WireMock.configureFor("http", "localhost", 8181, "");
    }

    @AfterAll
    public static void tearDown() {
        System.out.printf("Stopping Wiremock on path: %s:%d%n", WIREMOCK_HOST, PORT);
        wireMockServer.stop();
    }
}

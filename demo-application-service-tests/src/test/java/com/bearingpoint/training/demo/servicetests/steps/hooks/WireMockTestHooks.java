package com.bearingpoint.training.demo.servicetests.steps.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WireMockTestHooks {

    private WireMockServer wireMockServer;


    @Before()
    public void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8181));
        wireMockServer.start();
        WireMock.configureFor("http", "localhost", 8181, "");
    }

    @After
    public void tearDown() {
        wireMockServer.stop();
    }
}

package com.bearingpoint.training.demo.servicetests.steps;

import io.cucumber.java.en.Given;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockAction {

    @Given("^Wiremock will return for a GET request on ([^\" ]+) a status (\\d+) response$")
    public void wireMockWilLReturnForRequestOnTheResponseFrom(String path, Integer expectedStatus) {
        addStubMapping(path, expectedStatus);
    }


    private void addStubMapping(String url, Integer expectedStatus) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                                .withStatus(expectedStatus)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Hello, World!\"}")
                ));
    }


}

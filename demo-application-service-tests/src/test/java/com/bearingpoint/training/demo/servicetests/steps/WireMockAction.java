package com.bearingpoint.training.demo.servicetests.steps;

import com.github.tomakehurst.wiremock.verification.LoggedRequest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockAction {


    public void addStubMapping(String url, Integer expectedStatus) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(expectedStatus)
                ));
    }

    public void addStubMappingWithBody(String url, Integer expectedStatus, String responseBody) {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(expectedStatus)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)
                ));
    }

    public List<LoggedRequest> getRequestsSendToWiremockFor(String url) {
        return findAll(getRequestedFor(urlMatching(url)));
    }
}

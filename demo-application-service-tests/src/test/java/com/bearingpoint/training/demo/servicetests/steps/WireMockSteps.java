package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import com.bearingpoint.training.demo.servicetests.util.TemplateUtils;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import io.cucumber.java.en.Given;
import org.assertj.core.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WireMockSteps {

    WireMockAction wireMockAction = new WireMockAction();

    @Given("^Wiremock will return for a GET request on \"([^\"]+)\" a status (\\d+) response$")
    public void wireMockWilLReturnForRequestOnTheResponseCode(String path, Integer expectedStatus) {
        wireMockAction.addStubMapping(path, expectedStatus);
    }

    @Given("^Wiremock will return for a GET request on \"([^\"]+)\" a status (\\d+) response with response from \"([^\"]+)\"$")
    public void wireMockWilLReturnForRequestOnTheResponseCodeAndResponseMessage(String url, Integer expectedStatus, String responsePath) {
        Map<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("${user}", TestContext.getUser());
        String responseBody = TemplateUtils.readFromTemplate(responsePath, substitutionMap);
        url = TemplateUtils.resolveTemplate(url, substitutionMap);
        wireMockAction.addStubMappingWithBody(url, expectedStatus, responseBody);
    }

    @Given("^verify that Wiremock got (\\d+) request for \"([^\"]+)\"")
    public void wireMockGotARequestForUrlWithBody(Integer amount, String url) {
        Map<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("${user}", TestContext.getUser());
        url = TemplateUtils.resolveTemplate(url, substitutionMap);
        List<LoggedRequest> requestsSendToWiremockFor =
                wireMockAction.getRequestsSendToWiremockForUrlWithHeader(".*", "X-Correlation-Id", TestContext.getCorrelationId());

        Assertions.assertThat(requestsSendToWiremockFor.size()).isEqualTo(amount);

        LoggedRequest loggedRequest = requestsSendToWiremockFor.get(0);
        Assertions.assertThat(loggedRequest.getUrl()).isEqualTo(url);
    }
}

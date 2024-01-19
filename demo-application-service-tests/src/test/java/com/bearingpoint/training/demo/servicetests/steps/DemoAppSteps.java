package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.en.Then;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoAppSteps {

    private final DemoAppAction demoAppAction = new DemoAppAction();

    @Then("^fancy service is called$")
    public void callFancyService() {
        demoAppAction.sendRequest("/demo/fancy-service");
    }

    @Then("^new feature is called$")
    public void callNewFeature() {
        demoAppAction.sendRequest("/demo/new-feature");
    }

    @Then("^verify that the http response code is (\\d+)$")
    public void verifyStatusCodeEqualsTo(Integer statusCode) {
        assertThat(TestContext.getResponseStatusCode()).isEqualTo(statusCode);
    }

    @Then("^verify that the response message is \"([^\"]+)\"$")
    public void verifyResponseMessageIs(String responseMessage) {
        JSONObject responseBody = TestContext.getResponseBodyAsJsonObject();
        assertThat(responseBody.get("message")).isEqualTo(responseMessage);
    }
}

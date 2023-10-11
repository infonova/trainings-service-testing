package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoAppSteps {

    private final DemoAppAction demoAppAction = new DemoAppAction();

    @Then("^fancy service is called$")
    public void callFancyService() {
        demoAppAction.sendRequest("/demo/fancy-service");
    }

    @Then("^verify that the http response code is (\\d+)$")
    public void verifyStatusCodeIs2xxSuccessful(Integer statusCode) {
        assertThat(TestContext.getResponseStatusCode()).isEqualTo(statusCode);
    }
}

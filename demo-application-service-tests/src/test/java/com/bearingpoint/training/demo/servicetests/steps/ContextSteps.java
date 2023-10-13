package com.bearingpoint.training.demo.servicetests.steps;

import io.cucumber.java.en.Then;

public class ContextSteps {
    private final ContextAction contextAction = new ContextAction();
    @Then("^\"([^\"]+)\" is set as user in context$")
    public void setUserInContext(String user) {
        contextAction.setUserInContext(user);
    }
}

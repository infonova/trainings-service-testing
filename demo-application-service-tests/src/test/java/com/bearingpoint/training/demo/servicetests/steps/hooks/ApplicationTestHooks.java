package com.bearingpoint.training.demo.servicetests.steps.hooks;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationTestHooks {

    @Before()
    public void reset(Scenario scenario) {
        System.out.printf("Running scenario \"%s\" in thread: %d%n", scenario.getName(), Thread.currentThread().getId());
        String correlationId = java.util.UUID.randomUUID().toString();
        System.out.printf("Storing correlationId in context: %s%n", correlationId);
        TestContext.setCorrelationId(correlationId);
    }
}

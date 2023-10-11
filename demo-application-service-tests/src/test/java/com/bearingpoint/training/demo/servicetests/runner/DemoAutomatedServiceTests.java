package com.bearingpoint.training.demo.servicetests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {
                "com.bearingpoint.training.demo.servicetests",
        },
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags = "(not @Ignore) and (@Automated)"
)
public class DemoAutomatedServiceTests {
    // Run all cucumber Tests annotated with @Automated
}

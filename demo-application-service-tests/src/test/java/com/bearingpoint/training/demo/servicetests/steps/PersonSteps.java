package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;

public class PersonSteps {
    PersonAction personAction = new PersonAction();

    @Then("person from file \"([^\"]+)\" gets successfully stored$")
    public void addPerson(String path) {
        personAction.addPerson(path);
        Assertions.assertThat(TestContext.getResponseStatusCode()).isEqualTo(200);
    }

    @Then("verify that (\\d+) persons were stored via DemoApplication$")
    public void verifyThatNPersonsWhereStored(Integer amount) {
        personAction.getPersons();
        JSONArray response = TestContext.getResponseBodyAsJsonArray();
        Assertions.assertThat(response.length()).isEqualTo(amount);
    }

    @Then("persons get successfully deleted$")
    public void verifyThatNPersonsWhereStored() {
        personAction.deletePersons();
        Assertions.assertThat(TestContext.getResponseStatusCode()).isEqualTo(200);
    }
}

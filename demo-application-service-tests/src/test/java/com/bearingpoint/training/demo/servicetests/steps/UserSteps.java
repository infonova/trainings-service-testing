package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;

public class UserSteps {
    UserAction userAction = new UserAction();

    @Then("user from file \"([^\"]+)\" gets successfully stored$")
    public void adduser(String path) {
        userAction.addUser(path);
        Assertions.assertThat(TestContext.getResponseStatusCode()).isEqualTo(200);
    }

    @Then("verify that (\\d+) users were stored via DemoApplication$")
    public void verifyThatNUsersWhereStored(Integer amount) {
        userAction.getUsers();
        JSONArray response = TestContext.getResponseBodyAsJsonArray();
        Assertions.assertThat(response.length()).isEqualTo(amount);
    }

    @Then("users get successfully deleted$")
    public void verifyThatNUsersWhereStored() {
        userAction.deleteUsers();
        Assertions.assertThat(TestContext.getResponseStatusCode()).isEqualTo(200);
    }
}

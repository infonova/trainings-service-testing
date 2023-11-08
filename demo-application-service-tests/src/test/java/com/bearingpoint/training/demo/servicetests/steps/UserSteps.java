package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.IntStream;

public class UserSteps {
    UserAction userAction = new UserAction();

    @Then("user from file \"([^\"]+)\" is successfully stored$")
    public void addUser(String path) {
        userAction.addUser(path);
        Assertions.assertThat(TestContext.getResponseStatusCode()).isEqualTo(200);
    }

    @Then("user \"([^\"]+)\" is successfully deleted$")
    public void deleteUser(String username) {
        userAction.deleteUserByUsername(username);
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

    @Then("user \"([^\"]+)\" is not stored$")
    public void verifyThatUserIsNotStored(String username) {
        JSONArray response = TestContext.getResponseBodyAsJsonArray();
        List<String> userNames = IntStream.range(0, response.length())
                .mapToObj(response::getJSONObject)
                .map(userObject -> userObject.getString("username"))
                .toList();

        Assertions.assertThat(userNames).doesNotContain(username);
    }
}

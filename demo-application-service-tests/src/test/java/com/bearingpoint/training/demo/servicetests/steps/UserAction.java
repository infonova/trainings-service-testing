package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.util.TemplateUtils;

import java.util.HashMap;

public class UserAction {
    private final RestAssuredAction restAssuredAction = new RestAssuredAction();

    public void addUser(String pathToUserFile) {
        String endpoint = "/addUser";
        String fileAsString = TemplateUtils.readFromTemplate(pathToUserFile, new HashMap<>());

        restAssuredAction.postRequestWithBody(endpoint, fileAsString);
    }

    public void deleteUsers() {
        String endpoint = "/users";
        restAssuredAction.deleteRequest(endpoint);
    }

    public void deleteUserByUsername(String username) {
        String endpoint = "/user/{username}";
        restAssuredAction.deleteRequestByUsername(endpoint, username);
    }

    public void getUsers() {
        String endpoint = "/users";
        restAssuredAction.getRequest(endpoint);
    }
}

package com.bearingpoint.training.demo.servicetests.steps;

public class DemoAppAction {

    private final RestAssuredAction restAssuredAction = new RestAssuredAction();

    public void sendRequest(String path) {
        restAssuredAction.sendRequestToDemoApp(path);
    }
}

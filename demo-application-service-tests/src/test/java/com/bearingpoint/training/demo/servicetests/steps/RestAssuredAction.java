package com.bearingpoint.training.demo.servicetests.steps;

import com.bearingpoint.training.demo.servicetests.context.TestContext;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAction {

    public RestAssuredAction() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    public void sendRequest(String path) {
        String user = TestContext.getUser();
        RequestSpecification request = RestAssured.given();
        request.body(user);

        TestContext.setResponse(request.get(path));
    }

    public void postRequestWithBody(String path, String body) {
        RequestSpecification request = RestAssured.given();
        request.body(body);
        request.contentType("application/json");

        TestContext.setResponse(request.post(path));
    }

    public void getRequest(String path) {
        TestContext.setResponse(RestAssured.given().get(path));
    }

    public void deleteRequest(String path) {
        TestContext.setResponse(RestAssured.given().delete(path));
    }
}

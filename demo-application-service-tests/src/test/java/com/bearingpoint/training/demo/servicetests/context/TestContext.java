package com.bearingpoint.training.demo.servicetests.context;

import io.restassured.response.Response;
import org.json.JSONArray;


public class TestContext {
    private static Response response = null;

    public static void setResponse(Response response) {
        TestContext.response = response;
    }

    public static Integer getResponseStatusCode() {
        return response.getStatusCode();
    }

    public static JSONArray getResponseBodyAsJsonArray() {
        return new JSONArray(response.getBody().prettyPrint());
    }
}

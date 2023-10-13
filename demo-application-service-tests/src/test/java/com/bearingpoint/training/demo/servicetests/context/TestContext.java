package com.bearingpoint.training.demo.servicetests.context;

import io.restassured.response.Response;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;


public class TestContext {
    private static Response response = null;
    @Getter
    private static String user = "DefaultUser";

    public static void setResponse(Response response) {
        TestContext.response = response;
    }

    public static void setUser(String user) {
        TestContext.user = user;
    }

    public static Integer getResponseStatusCode() {
        if (response == null){
            throw new IllegalArgumentException("Response was not stored in context!");
        }
        return response.getStatusCode();
    }

    public static JSONArray getResponseBodyAsJsonArray() {
        if (response == null){
            throw new IllegalArgumentException("Response was not stored in context!");
        }
        return new JSONArray(response.getBody().prettyPrint());
    }

    public static JSONObject getResponseBodyAsJsonObject() {
        if (response == null){
            throw new IllegalArgumentException("Response was not stored in context!");
        }
        return new JSONObject(response.getBody().prettyPrint());
    }
}

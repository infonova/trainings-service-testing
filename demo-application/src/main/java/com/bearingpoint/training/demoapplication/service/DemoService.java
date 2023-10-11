package com.bearingpoint.training.demoapplication.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public Response callExternalService() throws HttpResponseException {
        RestAssured.baseURI = "http://localhost:8181";

        return RestAssured.given()
                .when()
                .get("/information");
    }
}

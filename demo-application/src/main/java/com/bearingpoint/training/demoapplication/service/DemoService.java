package com.bearingpoint.training.demoapplication.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public Response callExternalService(String user) throws HttpResponseException {
        RestAssured.baseURI = "http://localhost:8181";

        RequestSpecification request = RestAssured.given();
        request.param("user", user);

        return request.get("/information");
    }
}

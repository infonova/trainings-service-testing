package com.bearingpoint.training.demoapplication.service;

import com.bearingpoint.training.demoapplication.dto.DemoRequestBody;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public Response callExternalService(DemoRequestBody requestBody) throws HttpResponseException {
        RestAssured.baseURI = "http://localhost:8181";

        RequestSpecification request = RestAssured.given();
        request.param("user", requestBody.getUser());
        request.header(new Header("X-Correlation-Id", requestBody.getCorrelationId()));

        return request.get("/information");
    }

    public void executeNewFeature(DemoRequestBody requestBody) {
        String correlationId = requestBody.getCorrelationId();
        String user = requestBody.getUser();

        if (!user.equals("DefaultUser")) {
            // Some logic was done and exception was thrown
            throw new IllegalArgumentException("Something bad happened!");
        }

        System.out.println("Value of correlation id: " + correlationId);
    }
}

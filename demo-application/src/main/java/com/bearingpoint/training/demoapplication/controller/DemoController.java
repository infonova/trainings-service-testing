package com.bearingpoint.training.demoapplication.controller;

import com.bearingpoint.training.demoapplication.service.DemoService;
import io.restassured.response.Response;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping(path = "/fancy-service")
    public ResponseEntity<String> doFancyStuff() throws HttpResponseException {

        Response response = demoService.callExternalService();

        return ResponseEntity.status(response.getStatusCode()).build();
    }
}

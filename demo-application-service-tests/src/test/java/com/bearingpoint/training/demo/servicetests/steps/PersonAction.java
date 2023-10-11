package com.bearingpoint.training.demo.servicetests.steps;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class PersonAction {
    private final RestAssuredAction restAssuredAction = new RestAssuredAction();

    public void addPerson(String pathToPersonFile) {
        String endpoint = "/addPerson";
        String fileAsString = getFileAsString(pathToPersonFile);

        restAssuredAction.postRequestWithBody(endpoint, fileAsString);
    }

    public void deletePersons() {
        String endpoint = "/persons";
        restAssuredAction.deleteRequest(endpoint);
    }

    public void getPersons() {
        String endpoint = "/persons";
        restAssuredAction.getRequest(endpoint);
    }

    private String getFileAsString(String path) {
        try {
            ResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource("classpath:" + path);
            return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException var3) {
            throw new UncheckedIOException(String.format("Could not read file from Classpath '%s'.", path), var3);
        }
    }
}

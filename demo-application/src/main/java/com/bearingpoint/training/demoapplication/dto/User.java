package com.bearingpoint.training.demoapplication.dto;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
}

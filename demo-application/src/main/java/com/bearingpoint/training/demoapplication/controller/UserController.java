package com.bearingpoint.training.demoapplication.controller;

import com.bearingpoint.training.demoapplication.dto.User;
import com.bearingpoint.training.demoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.storeUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getStoredUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<List<User>> deleteUsers() {
        userService.deleteUsers();
        return ResponseEntity.ok().build();
    }
}

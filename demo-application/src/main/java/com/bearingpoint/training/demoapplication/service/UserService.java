package com.bearingpoint.training.demoapplication.service;

import com.bearingpoint.training.demoapplication.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public void storeUser(User user) {
        users.add(user);
    }

    public List<User> getStoredUsers() {
        return users;
    }

    public void deleteUsers(){
        users = new ArrayList<>();
    }

    public void deleteUserByUsername(String username) {
        users = users.stream()
                .filter(user -> !user.getUsername().equals(username))
                .collect(Collectors.toList());
    }
}

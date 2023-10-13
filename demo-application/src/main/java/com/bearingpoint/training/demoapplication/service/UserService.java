package com.bearingpoint.training.demoapplication.service;

import com.bearingpoint.training.demoapplication.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}

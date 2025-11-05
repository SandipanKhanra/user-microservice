package com.microservice.user.services;

import com.microservice.user.entities.User;

import java.util.List;

public interface UserService {

//    User operations
    User saveUser(User user);

//    Get All users
    List<User> getAllUsers();

//    User with a given id
    User getUser(String userId);

//    Delete User
    void deleteUser(String userId);

//    Update user
    User updateUser(String userId);
}

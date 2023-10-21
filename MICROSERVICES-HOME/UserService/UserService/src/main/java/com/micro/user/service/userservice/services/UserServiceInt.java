package com.micro.user.service.userservice.services;

import com.micro.user.service.userservice.entities.User;

import java.util.List;

public interface UserServiceInt {
    //User operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);

    //delete user
    User deleteUser(String userId);

    //update user
    User updateUser(User user);
}

package com.projectmanager.projectmanagerapplication.service;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.User;

public interface UserService {
    //Method for Save and Update the User
    boolean addOrUpdateUser(User user) throws Exception;
    //Method to retreive all the Users
    List<User> getUserList() throws Exception;
    boolean deleteUser(User user);
}

package com.projectmanager.projectmanagerapplication.dao;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.User;

public interface UserDao {
    User addOrUpdateUser(User user);
    List<User> getAllUser();
    boolean deleteUser(User user);
}

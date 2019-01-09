package com.projectmanager.projectmanagerapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import com.projectmanager.projectmanagerapplication.dao.UserDao;
import com.projectmanager.projectmanagerapplication.exception.UserNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.User;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserDao userDao;

    public boolean addOrUpdateUser(User user) throws Exception{
        LOGGER.debug("UserServiceImpl - addOrUpdateUser() Starts" + user.getFirstName());
        userDao.addOrUpdateUser(user);
        LOGGER.debug("UserServiceImpl - addOrUpdateUser() Ends");
        return true;
    }

    public List<User> getUserList() throws UserNotFoundException, Exception{
        List<User> userList;
        LOGGER.debug("UserServiceImpl - getUserList() Starts");
        userList = userDao.getAllUser();
        if(CollectionUtils.isEmpty(userList)) {
            throw new UserNotFoundException("Users not available in database");
        }
        else {
            return userList;
        }
    }

    public boolean deleteUser(User user) {
        LOGGER.debug("UserServiceImpl - deleteUser() Starts");
        userDao.deleteUser(user);
        LOGGER.debug("UserServiceImpl - deleteUser() Ends");
        return true;
    }
}
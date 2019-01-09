package com.projectmanager.projectmanagerapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import com.projectmanager.projectmanagerapplication.data.UserData;
import com.projectmanager.projectmanagerapplication.exception.UserNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserData userData) {
        ResponseEntity<?> responseEntity;
        try {
            LOGGER.debug("UserController - createUser() Starts" + userData.getFirstName());
            User user = this.updateUserFromUserData(userData);
            user.setStatus("Active");
            userService.addOrUpdateUser(user);
            responseEntity = new ResponseEntity<UserData>(userData, HttpStatus.OK);
            LOGGER.debug("UserController - createUser() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while saving User in Database");
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserData> getUserList() {
        List<UserData> userDataList = new ArrayList<UserData>();
        try {
            LOGGER.info("UserController - getUserList() Starts");
            List<User> listOfUser = userService.getUserList();

            for (User user : listOfUser) {
                UserData userData = new UserData();
                userData.setUserId(user.getUserId());
                userData.setFirstName(user.getFirstName());
                userData.setLastName(user.getLastName());
                userData.setEmployeeId(user.getEmployeeId());
                userData.setStatus(user.getStatus());
                userDataList.add(userData);
            }
            LOGGER.debug("UserController - getUserList() Ends"+userDataList.size());
        } 
        catch (UserNotFoundException tex) {
            LOGGER.debug("Exception while fetching task list from Database as it is empty");
        }
        catch (Exception e) {
            LOGGER.debug("Exception while fetching task list from Database");
        }
        return userDataList;
    }

    @RequestMapping(method = RequestMethod.DELETE, path={"/{id}"})
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        ResponseEntity<?> responseEntity;
        try {
            LOGGER.debug("UserController - deleteUser() Starts" + id);
            User user = new User();
            user.setUserId(id);
            userService.deleteUser(user);
            responseEntity = new ResponseEntity<UserData>(HttpStatus.OK);
            LOGGER.debug("UserController - deleteUser() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while deleting User in Database");
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestBody UserData userData) {
        ResponseEntity<?> responseEntity = null;
        LOGGER.debug("UserController - updateTask() Starts");
        User user = this.updateUserFromUserData(userData);
        try {
            userService.addOrUpdateUser(user);
            responseEntity = new ResponseEntity<UserData>(userData, HttpStatus.OK);
            LOGGER.debug("UserController - updateTask() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while updating User in Database");
        }
        return responseEntity;
    }

    private User updateUserFromUserData(UserData userData) {
        LOGGER.debug("UserController - updateUserFromUserData() Starts");
        User user = new User();
        user.setUserId(userData.getUserId()); 
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmployeeId(userData.getEmployeeId());
        LOGGER.debug("UserController - updateUserFromUserData() Ends");
        return user;
    } 
}
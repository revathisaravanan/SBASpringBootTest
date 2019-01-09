package com.projectmanager.projectmanagerapplication.data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class UserData {

    private String firstName;
    private String lastName;
    private int employeeId;
    private String status;
    private int userId;

    public int getEmployeeId() {
        return employeeId;
    }

     public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    } 

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
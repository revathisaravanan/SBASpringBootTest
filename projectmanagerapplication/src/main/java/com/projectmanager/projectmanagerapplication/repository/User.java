package com.projectmanager.projectmanagerapplication.repository;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name="usertable")
public class User implements Serializable {

    @Id
    @Column(name = "User_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User(){
    }

    public User(int userId, final String firstName, final String lastName, int employeeId, final String status){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.status = status;
    }
}
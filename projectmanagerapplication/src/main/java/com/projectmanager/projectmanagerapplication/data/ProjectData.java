package com.projectmanager.projectmanagerapplication.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ProjectData {

    private String projectName;
    private Date startDate;
    private Date endDate;
    private int managerId;
    private String managerName;
    private int priority;
    private String status;
    private int projectId;
    private String endProject;
    private int noOfTask;
    private int noOfCompletedTask;

    public String getProjectName() {
        return projectName;
    }

    public int getNoOfCompletedTask() {
        return noOfCompletedTask;
    }

    public void setNoOfCompletedTask(int noOfCompletedTask) {
        this.noOfCompletedTask = noOfCompletedTask;
    }

    public int getNoOfTask() {
        return noOfTask;
    }

    public void setNoOfTask(int noOfTask) {
        this.noOfTask = noOfTask;
    }

    public String getEndProject() {
        return endProject;
    }

    public void setEndProject(String endProject) {
        this.endProject = endProject;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
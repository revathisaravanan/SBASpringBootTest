package com.projectmanager.projectmanagerapplication.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude
public class TaskData {

    private long taskId;
    private long parentTaskId;
    private String taskName;
    private String parentTaskName;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;
    private int priority;
    private String status;
    private String endTask;
    private int projectId;
    private int managerId;
    private String projectName;
    private String managerName;
    
    public Date getStartDate() {
        return startDate;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndTask() {
        return endTask;
    }

    public void setEndTask(String endTask) {
        this.endTask = endTask;
    }

   public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
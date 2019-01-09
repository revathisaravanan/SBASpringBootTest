package com.projectmanager.projectmanagerapplication.data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ParentTaskData {

    private long parentTaskId;
    private String parentTaskName;
    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
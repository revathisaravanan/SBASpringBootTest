package com.projectmanager.projectmanagerapplication.service;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Task;

public interface TaskService {
    //Method for Save and Update the task
    boolean addOrUpdateTask(Task task,ParentTask  parenttask) throws Exception;
    //Method to retreive all the tasks
    List<Task> getTaskList() throws Exception;
    List<ParentTask> getParentTaskList() throws Exception;
}

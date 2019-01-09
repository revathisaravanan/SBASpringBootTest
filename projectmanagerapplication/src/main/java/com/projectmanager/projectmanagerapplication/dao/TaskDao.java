package com.projectmanager.projectmanagerapplication.dao;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Task;

public interface TaskDao {

    Task addOrUpdateTask(Task task, ParentTask  parenttask);

    List<Task> getAllTask();

    List<ParentTask> getAllParentTask();

}

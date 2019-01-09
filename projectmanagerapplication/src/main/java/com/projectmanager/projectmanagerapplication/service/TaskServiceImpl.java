package com.projectmanager.projectmanagerapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import com.projectmanager.projectmanagerapplication.dao.TaskDao;
import com.projectmanager.projectmanagerapplication.exception.TaskNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Task;

@Service("TaskService")
public class TaskServiceImpl implements TaskService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskDao taskDao;

    public boolean addOrUpdateTask(Task task,ParentTask  parenttask) throws Exception{
        LOGGER.debug("TaskServiceImpl - addOrUpdateTask() Starts"/*  + task.getParentTask() */);
        taskDao.addOrUpdateTask(task,  parenttask);
        LOGGER.debug("TaskServiceImpl - addOrUpdateTask() Ends");
        return true;
    }

    public List<Task> getTaskList() throws TaskNotFoundException, Exception{
        List<Task> taskList;
        LOGGER.debug("TaskServiceImpl - getTaskList() Starts");
        taskList = taskDao.getAllTask();
        if(CollectionUtils.isEmpty(taskList)) {
            throw new TaskNotFoundException("Tasks not available in database");
        }
        else {
            return taskList;
        }
    }

    public List<ParentTask> getParentTaskList() throws TaskNotFoundException, Exception{
        List<ParentTask> parentTaskList;
        LOGGER.debug("TaskServiceImpl - getParentTaskList() Starts");
        parentTaskList = taskDao.getAllParentTask();
        if(CollectionUtils.isEmpty(parentTaskList)) {
            throw new TaskNotFoundException("Parent Tasks not available in database");
        }
        else {
            return parentTaskList;
        }
    }
}
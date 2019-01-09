package com.projectmanager.projectmanagerapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import com.projectmanager.projectmanagerapplication.data.ParentTaskData;
import com.projectmanager.projectmanagerapplication.data.TaskData;
import com.projectmanager.projectmanagerapplication.exception.TaskNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.TaskService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody TaskData taskData) {
        ResponseEntity<?> responseEntity;
        try {
            LOGGER.debug("TaskController - createTask() Starts" + taskData.getParentTaskName());
            ParentTask  parenttask = new ParentTask();
            parenttask.setParentId(taskData.getParentTaskId()); 
            parenttask.setParentTaskName(taskData.getParentTaskName());
            Task task = this.updateTaskFromTaskData(taskData);
            task.setStatus("Active");
            task.setParenttask(parenttask);
            taskService.addOrUpdateTask(task, parenttask);
            responseEntity = new ResponseEntity<TaskData>(taskData, HttpStatus.OK);
            LOGGER.debug("TaskController - createTask() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while saving task in Database");
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping(value="tasklist", method = RequestMethod.GET)
    public List<TaskData> getTaskList() {
        List<TaskData> taskDataList = new ArrayList<TaskData>();
        try {
            LOGGER.info("TaskController - getTaskList() Starts");
            List<Task> listOfTask = taskService.getTaskList();
            LOGGER.debug("TaskController - getTaskList() Ends 3"+listOfTask.size());

            for (Task task : listOfTask) {
                TaskData taskData = new TaskData();
                taskData.setTaskId(task.getTaskId());
                taskData.setTaskName(task.getTaskName());
                taskData.setPriority(task.getPriority());
                taskData.setStartDate(task.getStartDate());
                taskData.setEndDate(task.getEndDate());
                if ( task.getParenttask() != null) {
                    taskData.setParentTaskId(task.getParenttask().getParentId());
                    taskData.setParentTaskName(task.getParenttask().getParentTaskName());
                }
                if ( task.getProject() != null) {
                    taskData.setProjectId(task.getProject().getProjectId());
                    taskData.setProjectName(task.getProject().getProjectName());
                }
                if(task.getUser() != null) {
                    taskData.setManagerId(task.getUser().getUserId());
                    taskData.setManagerName(task.getUser().getFirstName()+task.getUser().getLastName());
                }
               // taskData.setParentTaskName(task.getParentTask());
                taskData.setStatus(task.getStatus());
                taskDataList.add(taskData);
            }
            LOGGER.debug("TaskController - getTaskList() Ends"+taskDataList.size());
        } 
        catch (TaskNotFoundException tex) {
            LOGGER.debug("Exception while fetching task list from Database as it is empty");
        }
        catch (Exception e) {
            LOGGER.debug("Exception while fetching task list from Database");
        }
        LOGGER.debug("TaskController - getTaskList() Ends 2"+taskDataList.size());
        return taskDataList;
    }

    @RequestMapping(value="parentTask", method = RequestMethod.GET)
    public List<ParentTaskData> getParentTaskList() {
        
        List<ParentTaskData> parentTaskDataList = new ArrayList<ParentTaskData>(); 
        try {
            LOGGER.info("TaskController - getParentTaskList() Starts");
            List<ParentTask> listOfParentTask = taskService.getParentTaskList();

           for (ParentTask parentTask : listOfParentTask) {
                ParentTaskData parentTaskData = new ParentTaskData();
                parentTaskData.setParentTaskId(parentTask.getParentId());
                parentTaskData.setParentTaskName(parentTask.getParentTaskName());
                parentTaskDataList.add(parentTaskData);
            }
            LOGGER.debug("TaskController - getParentTaskList() Ends"+parentTaskDataList.size());
        } 
        catch (TaskNotFoundException tex) {
            LOGGER.debug("Exception while fetching parent task list from Database as it is empty");
        }
        catch (Exception e) {
            LOGGER.debug("Exception while fetching parent task list from Database");
        }
        return parentTaskDataList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@RequestBody TaskData taskData) {
        ResponseEntity<?> responseEntity = null;
        ParentTask  parenttask = new ParentTask();
        LOGGER.debug("TaskController - updateTask() Starts");
        parenttask.setParentId(taskData.getParentTaskId()); 
        parenttask.setParentTaskName(taskData.getParentTaskName());
        Task task = this.updateTaskFromTaskData(taskData);
        if (taskData.getEndTask() != null && taskData.getEndTask().equalsIgnoreCase("yes")) {
            task.setStatus("InActive");
        }
        else {
            task.setStatus("Active");
        }
        try {
            taskService.addOrUpdateTask(task, parenttask);
            responseEntity = new ResponseEntity<TaskData>(taskData, HttpStatus.OK);
            LOGGER.debug("TaskController - updateTask() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while updating task in Database");
        }
        return responseEntity;
    }

    private Task updateTaskFromTaskData(TaskData taskData) {
        LOGGER.debug("TaskController - updateTaskFromTaskData() Starts");
        Task task = new Task();
        User user = new User();
        Project project = new Project();
        task.setTaskId(taskData.getTaskId());
        task.setTaskName(taskData.getTaskName());
        task.setStartDate(new java.sql.Date(taskData.getStartDate().getTime()));
        task.setEndDate(new java.sql.Date(taskData.getEndDate().getTime()));
        task.setPriority(taskData.getPriority());
        project.setProjectId(taskData.getProjectId());
        user.setUserId(taskData.getManagerId()); 
        task.setUser(user);
        task.setProject(project);
        LOGGER.debug("TaskController - updateTaskFromTaskData() Ends..."); 
        return task;
    }
}
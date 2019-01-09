package com.projectmanager.projectmanagerapplication;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projectmanager.projectmanagerapplication.dao.TaskDao;
import com.projectmanager.projectmanagerapplication.exception.TaskNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.TaskServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TaskServiceImplTest {
    @Mock
    private transient TaskDao taskdao;
    private transient Task task;
    @InjectMocks
    private transient TaskServiceImpl taskServiceImpl;
    static Optional<Task> tasks;
    static Optional<ParentTask> parenttasks;
    //static List<ParentTask> parenttasks;
    ParentTask parentTask;
    Project project;
    User user;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
       // parenttasks = new ArrayList<ParentTask>();
        parentTask = new ParentTask(1, "servicepersonal");
        task = new Task(8, "servicecallju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        project = new Project(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        user = new User(3, "mark", "hannigan", 1911 , "active");
        tasks = Optional.of(task);
        parenttasks = Optional.of(parentTask);
        //parenttasks.add(parentTask);
    }

    @Test
    public void testSaveTask() throws Exception{
        Mockito.when(taskdao.addOrUpdateTask(task, parentTask)).thenReturn(task);
        assertTrue(taskServiceImpl.addOrUpdateTask(task, parentTask));
        Mockito.verify(taskdao, Mockito.times(1)).addOrUpdateTask(task, parentTask);
    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetallTasks() throws Exception{
        List<Task> tasks = new ArrayList<Task>();
        Mockito.when(taskdao.getAllTask()).thenReturn(tasks);
        List<Task> allTasks = taskServiceImpl.getTaskList();
        assertTrue(allTasks.size() == 0);
        Mockito.verify(taskdao, Mockito.times(1)).getAllTask();
    }

    @Test
    public void updateTask() throws Exception{
        Mockito.when(taskdao.addOrUpdateTask(task, parentTask)).thenReturn(task);
        assertTrue(taskServiceImpl.addOrUpdateTask(task, parentTask));
        Mockito.verify(taskdao, Mockito.times(1)).addOrUpdateTask(task, parentTask);

    }
    
    @Test(expected = TaskNotFoundException.class)
    public void testGetallParentTasks() throws Exception{
        List<ParentTask> parenttasks = new ArrayList<ParentTask>();
        Mockito.when(taskdao.getAllParentTask()).thenReturn(parenttasks);
        List<ParentTask> allParentTasks = taskServiceImpl.getParentTaskList();
        assertTrue(allParentTasks.size() == 0);
        Mockito.verify(taskdao, Mockito.times(1)).getAllParentTask();
    }
}
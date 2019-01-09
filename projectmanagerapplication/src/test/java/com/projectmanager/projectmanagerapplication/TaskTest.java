package com.projectmanager.projectmanagerapplication;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.projectmanagerapplication.controller.ProjectController;
import com.projectmanager.projectmanagerapplication.controller.TaskController;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.ProjectService;
import com.projectmanager.projectmanagerapplication.service.TaskService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskTest {

    @Autowired
    private transient MockMvc mockMvc;
    @MockBean
    private transient TaskService taskService;
    private transient Task task;
    static List<Task> tasks;
    static List<ParentTask> parenttasks;
    private transient ParentTask parentTask;
    private transient Project project;
    private transient User user;

    @Before
    public void setUpData(){
    	tasks = new ArrayList<Task>();
        parenttasks = new ArrayList<ParentTask>();
        parentTask = new ParentTask(1, "personal");
        task = new Task(8, "callju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        task.setParenttask(parentTask);
        project = new Project(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        user = new User(3, "mark", "hannigan", 1911 , "active");
        task.setUser(user);
        task.setProject(project);
        tasks.add(task);
        parenttasks.add(parentTask);
    }

    private static String convertfromJsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try{
            final String jsonString = new ObjectMapper().writeValueAsString(obj);
            result = jsonString;
        }catch(JsonProcessingException ex){
            result = "Error Invlaid";

        }
        return result;
    }

    @Test
    public void testSaveNewTask() throws Exception{
        Mockito.when(taskService.addOrUpdateTask(task, parentTask)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(task))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(taskService, times(1)).addOrUpdateTask(Mockito.any(Task.class), Mockito.any(ParentTask.class));
        Mockito.verifyNoMoreInteractions(taskService);

    } 

    @Test
    public void testGetallTask() throws Exception{
        Mockito.when(taskService.getTaskList()).thenReturn(tasks);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/tasklist")).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(taskService, times(1)).getTaskList();
        Mockito.verifyNoMoreInteractions(taskService);

    }

    @Test
    public void testUpdateTask() throws Exception{
        Mockito.when(taskService.addOrUpdateTask(task, parentTask)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(task))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(taskService, times(1)).addOrUpdateTask(Mockito.any(Task.class), Mockito.any(ParentTask.class));
        Mockito.verifyNoMoreInteractions(taskService);
    }

    @Test
    public void testgetParentTask() throws Exception{
    	 Mockito.when(taskService.getParentTaskList()).thenReturn(parenttasks);
         mockMvc.perform(MockMvcRequestBuilders.get("/tasks/parentTask")).andExpect(MockMvcResultMatchers.status().isOk());
         Mockito.verify(taskService, times(1)).getParentTaskList();
         Mockito.verifyNoMoreInteractions(taskService);

    }
}
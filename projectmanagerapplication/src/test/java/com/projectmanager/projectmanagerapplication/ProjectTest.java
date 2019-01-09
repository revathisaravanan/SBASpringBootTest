package com.projectmanager.projectmanagerapplication;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.projectmanagerapplication.controller.ProjectController;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.ProjectService;
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
@WebMvcTest(ProjectController.class)
public class ProjectTest {

    @Autowired
    private transient MockMvc mockMvc;
    @MockBean
    private transient ProjectService projectService;
    private transient Task task;
    static Set<Task> tasks;
    static List<ParentTask> parenttasks;
    static List<Project> projects;
    ParentTask parentTask;
    Project project;
    User user;

    @Before
    public void setUpData(){
    	tasks = new HashSet<Task>();
        parenttasks = new ArrayList<ParentTask>();
        projects = new ArrayList<Project>();
        parentTask = new ParentTask(1, "personal");
        task = new Task(8, "callju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        task.setParenttask(parentTask);
        project = new Project(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        user = new User(3, "mark", "hannigan", 1911 , "active");
        project.setUser(user);
        tasks.add(task);
        project.setTask(tasks);
        projects.add(project);
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
    public void testSaveNewProject() throws Exception{
        Mockito.when(projectService.addOrUpdateProject(project)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/projects").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(project))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(projectService, times(1)).addOrUpdateProject(Mockito.any(Project.class));
        Mockito.verifyNoMoreInteractions(projectService);

    } 

    @Test
    public void testGetallProject() throws Exception{
        Mockito.when(projectService.getProjectList()).thenReturn(projects);
        mockMvc.perform(MockMvcRequestBuilders.get("/projects")).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(projectService, times(1)).getProjectList();
        Mockito.verifyNoMoreInteractions(projectService);

    }

    @Test
    public void testUpdateProject() throws Exception{
        Mockito.when(projectService.addOrUpdateProject(project)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/projects").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(project))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(projectService, times(1)).addOrUpdateProject(Mockito.any(Project.class));
        Mockito.verifyNoMoreInteractions(projectService);
    }
}
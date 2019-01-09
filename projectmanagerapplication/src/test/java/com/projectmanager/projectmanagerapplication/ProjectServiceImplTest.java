package com.projectmanager.projectmanagerapplication;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projectmanager.projectmanagerapplication.dao.ProjectDao;
import com.projectmanager.projectmanagerapplication.exception.ProjectNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.ProjectServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProjectServiceImplTest {
    @Mock
    private transient ProjectDao projectdao;
    private transient Task task;
    @InjectMocks
    private transient ProjectServiceImpl projectServiceImpl;
    static Optional<Task> tasks;
    ParentTask parentTask;
    Project project;
    User user;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
        parentTask = new ParentTask(1, "servicepersonal");
        task = new Task(8, "servicecallju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        project = new Project(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        user = new User(3, "mark", "hannigan", 1911 , "active");
        tasks = Optional.of(task);
    }

    @Test
    public void testSaveProject() throws Exception{
        Mockito.when(projectdao.addOrUpdateProject(project)).thenReturn(project);
        assertTrue(projectServiceImpl.addOrUpdateProject(project));
        Mockito.verify(projectdao, Mockito.times(1)).addOrUpdateProject(project);
    }

    @Test(expected = ProjectNotFoundException.class)
    public void testGetallProjects() throws Exception{
        List<Project> projects = new ArrayList<Project>();
        Mockito.when(projectdao.getAllProject()).thenReturn(projects);
        List<Project> allProjects = projectServiceImpl.getProjectList();
        assertTrue(allProjects.size() == 0);
        Mockito.verify(projectdao, Mockito.times(1)).getAllProject();
    }

    @Test
    public void updateProject() throws Exception{
        Mockito.when(projectdao.addOrUpdateProject(project)).thenReturn(project);
        assertTrue(projectServiceImpl.addOrUpdateProject(project));
        Mockito.verify(projectdao, Mockito.times(1)).addOrUpdateProject(project);
    }
}
package com.projectmanager.projectmanagerapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import com.projectmanager.projectmanagerapplication.data.ProjectData;
import com.projectmanager.projectmanagerapplication.exception.ProjectNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.Project;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.ProjectService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createProject(@RequestBody ProjectData projectData) {
        ResponseEntity<?> responseEntity;
        try {
            LOGGER.debug("ProjectController - createProject() Starts" + projectData.getManagerName());
            Project project = this.updateProjectFromProjectData(projectData);
            project.setStatus("Active");
            projectService.addOrUpdateProject(project);
            responseEntity = new ResponseEntity<ProjectData>(projectData, HttpStatus.OK);
            LOGGER.debug("ProjectController - createProject() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while saving Project in Database");
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProjectData> getProjectList() {
        List<ProjectData> projectDataList = new ArrayList<ProjectData>();
        try {
            LOGGER.info("ProjectController - getProjectList() Starts..");
            List<Project> listOfProject = projectService.getProjectList();
            
            for (Project project : listOfProject) {
                ProjectData projectData = new ProjectData();
                projectData.setProjectId(project.getProjectId());
                projectData.setProjectName(project.getProjectName());
                //projectData.setManagerName(project.getManagerName());
                projectData.setStartDate(project.getStartDate());
                projectData.setEndDate(project.getEndDate());
                projectData.setPriority(project.getPriority());
                if(project.getStatus().equalsIgnoreCase("Active")) {
                    projectData.setStatus("Yes");
                }
                else if(project.getStatus().equalsIgnoreCase("InActive")) {
                    projectData.setStatus("No");
                }
                if (project.getUser() != null) {
                    projectData.setManagerId(project.getUser().getUserId());
                    projectData.setManagerName(project.getUser().getFirstName()+project.getUser().getLastName());
                }
                if (!project.getTask().isEmpty() && project.getTask() != null) {
                    List<String> noofStatus = new ArrayList<String>();
                    for(Task task : project.getTask()) {
                        if(StringUtils.isNotBlank(task.getStatus())) {
                            if(task.getStatus().equalsIgnoreCase("InActive")) {
                                noofStatus.add(task.getStatus());
                            }
                        }
                        LOGGER.debug("ProjectController - Task detail.."+task.getTaskId());
                        projectData.setNoOfTask(project.getTask().size());
                        projectData.setNoOfCompletedTask(noofStatus.size());
                    }
                }
                projectDataList.add(projectData);
            }
            LOGGER.debug("ProjectController - getProjectList() Ends.."+projectDataList.size());
        } 
        catch (ProjectNotFoundException tex) {
            LOGGER.debug("Exception while fetching project list from Database as it is empty");
        }
        catch (Exception e) {
            LOGGER.debug("Exception while fetching project list from Database");
        }
        return projectDataList;
    }

   @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateProject(@RequestBody ProjectData projectData) {
        ResponseEntity<?> responseEntity = null;
        LOGGER.debug("ProjectController - updateProject() Starts");
        Project project = this.updateProjectFromProjectData(projectData);
        if (projectData.getEndProject() != null && projectData.getEndProject().equalsIgnoreCase("yes")) {
            project.setStatus("InActive");
        }
        else {
            project.setStatus("Active");
        }
        try {
            projectService.addOrUpdateProject(project);
            responseEntity = new ResponseEntity<ProjectData>(projectData, HttpStatus.OK);
            LOGGER.debug("ProjectController - updateProject() Ends");
        } catch (Exception e) {
            LOGGER.debug("Exception while updating Project in Database");
        }
        return responseEntity;
    }
    
    private Project updateProjectFromProjectData(ProjectData projectData) {
        LOGGER.debug("ProjectController - updateProjectFromProjectData() Starts");
        Project project = new Project();
        User user = new User();
        project.setProjectId(projectData.getProjectId());
        project.setProjectName(projectData.getProjectName());
        user.setUserId(projectData.getManagerId());
        project.setUser(user);
        project.setStartDate(new java.sql.Date(projectData.getStartDate().getTime()));
        project.setEndDate(new java.sql.Date(projectData.getEndDate().getTime()));
        project.setPriority(projectData.getPriority());
        LOGGER.debug("ProjectController - updateProjectFromProjectData() Ends");
        return project;
    }
}
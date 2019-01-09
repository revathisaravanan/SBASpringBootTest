package com.projectmanager.projectmanagerapplication.service;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.Project;

public interface ProjectService {
    //Method for Save and Update the Project
    boolean addOrUpdateProject(Project project) throws Exception;
    List<Project> getProjectList() throws Exception;
 }
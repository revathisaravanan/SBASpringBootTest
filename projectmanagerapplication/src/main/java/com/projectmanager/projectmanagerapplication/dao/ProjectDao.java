package com.projectmanager.projectmanagerapplication.dao;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.Project;

public interface ProjectDao {
    Project addOrUpdateProject(Project project);
    List<Project> getAllProject();
}
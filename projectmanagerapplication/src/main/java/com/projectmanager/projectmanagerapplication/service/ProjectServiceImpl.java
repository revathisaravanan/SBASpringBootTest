package com.projectmanager.projectmanagerapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import com.projectmanager.projectmanagerapplication.dao.ProjectDao;
import com.projectmanager.projectmanagerapplication.exception.ProjectNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.Project;

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProjectDao projectDao;

    public boolean addOrUpdateProject(Project project) throws Exception{
        LOGGER.debug("ProjectServiceImpl - addOrUpdateProject() Starts");
        projectDao.addOrUpdateProject(project);
        LOGGER.debug("ProjectServiceImpl - addOrUpdateProject() Ends");
        return true;
    }

    public List<Project> getProjectList() throws ProjectNotFoundException, Exception{
        List<Project> projectList;
        LOGGER.debug("UserServiceImpl - getUserList() Starts");
        projectList = projectDao.getAllProject();
        if(CollectionUtils.isEmpty(projectList)) {
            throw new ProjectNotFoundException("Projects not available in database");
        }
        else {
            return projectList;
        }
    }
}
package com.projectmanager.projectmanagerapplication.dao;

import java.util.List;

import com.projectmanager.projectmanagerapplication.repository.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository("ProjectDao")
public class ProjectDaoImpl implements ProjectDao {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Project addOrUpdateProject(Project project) {
        LOGGER.debug("ProjectDaoImpl - addOrUpdateProject() in DAO Starts");
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(project);
        }
        catch (Exception ex) {
            session.close();
            throw ex;
        }
        finally{
            tx.commit();
            session.close();
        }
        LOGGER.debug("ProjectDaoImpl - addOrUpdateProject() in DAO Ends");
        return project;
    }

    public List<Project> getAllProject() {
        LOGGER.debug("ProjectDaoImpl - getAllProject() Starts");
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        List<Project> projectList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Project> query = criteriaBuilder.createQuery(Project.class);
            Root<Project> root = query.from(Project.class);
            query.select(root);
            projectList = session.createQuery(query).getResultList();

        }catch (Exception ex) {
            throw ex;
        }
        finally{
            session.close();
        }
        LOGGER.debug("ProjectDaoImpl - getAllProject() Ends");
        return projectList;
    }
}
package com.projectmanager.projectmanagerapplication.dao;

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

import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Task;

import java.util.List;

@Repository("TaskDao")
public class TaskDaoImpl implements TaskDao {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManagerFactory entityManagerFactory; 
   /*  @Autowired
    private SessionFactory sessionFactory; */
	
	/* public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	} */

    public Task addOrUpdateTask(Task task,ParentTask  parenttask) {
        LOGGER.debug("TaskDaoImpl - addOrUpdateTask() in DAO Starts"/*  +task.getParentTask() */);
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
       /*  Session session = this.sessionFactory.openSession(); */
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(parenttask);
            session.saveOrUpdate(task);
        }
        catch (RuntimeException ex) {
            session.close();
            throw new RuntimeException();
        }
        finally{
            tx.commit();
            session.close();
        }
        LOGGER.debug("TaskDaoImpl - addOrUpdateTask() Ends");
        return task;
    }

    public List<Task> getAllTask() {
        LOGGER.debug("TaskDaoImpl - getAllTask() Starts");
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        /* Session session = this.sessionFactory.openSession(); */
        List<Task> taskList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
            Root<Task> root = query.from(Task.class);
            query.select(root);
            taskList = session.createQuery(query).getResultList();

        }catch (RuntimeException ex) {
            session.close();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
        LOGGER.debug("TaskDaoImpl - getAllTask() Ends");
        return taskList;
    }

    public List<ParentTask> getAllParentTask() {
        LOGGER.debug("TaskDaoImpl - getAllParentTask() Starts");
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        List<ParentTask> parentTaskList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ParentTask> query = criteriaBuilder.createQuery(ParentTask.class);
            Root<ParentTask> root = query.from(ParentTask.class);
            query.select(root);
            parentTaskList = session.createQuery(query).getResultList();

        }catch (RuntimeException ex) {
            session.close();
            throw new RuntimeException();
        }
        finally{
            session.close();
        }
        LOGGER.debug("TaskDaoImpl - getAllParentTask() Ends");
        return parentTaskList;
    }
}

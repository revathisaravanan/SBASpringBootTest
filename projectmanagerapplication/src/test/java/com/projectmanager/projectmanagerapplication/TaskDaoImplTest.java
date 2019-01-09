package com.projectmanager.projectmanagerapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.projectmanager.projectmanagerapplication.dao.TaskDaoImpl;
import com.projectmanager.projectmanagerapplication.repository.ParentTask;
import com.projectmanager.projectmanagerapplication.repository.Task;
import com.projectmanager.projectmanagerapplication.repository.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TaskDaoImplTest {
    @Mock
    private EntityManagerFactory entityManagerFactory; 
    @InjectMocks
    private transient TaskDaoImpl taskDaoImpl;
    User user;
    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;
    static List<Task> tasks;
    Task task;
    static List<ParentTask> parenttasks;
    ParentTask parentTask;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
        tasks = new ArrayList<Task>();
        parenttasks = new ArrayList<ParentTask>();
        session = Mockito.mock(Session.class);
        sessionFactory = Mockito.mock(SessionFactory.class);
        transaction = Mockito.mock(Transaction.class);
        Mockito.doReturn(sessionFactory).when(entityManagerFactory).unwrap(SessionFactory.class);
        Mockito.doReturn(session).when(sessionFactory).openSession();
        Mockito.doReturn(transaction).when(session).beginTransaction();
        task = new Task(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        tasks.add(task);
        parentTask = new ParentTask(1, "personal");
        parenttasks.add(parentTask);
    }

    @Test
    @Transactional
    public void testSaveTask() throws Exception{
    	Mockito.doNothing().when(session).saveOrUpdate(task);
    	taskDaoImpl.addOrUpdateTask(task, parentTask);
    	assertEquals(2, task.getTaskId());
    }
    
   @Test(expected = RuntimeException.class)
    @Transactional
    public void testSaveTaskException() {
    	Mockito.doThrow(new RuntimeException()).when(session).saveOrUpdate(task);
    	taskDaoImpl.addOrUpdateTask(task, parentTask);
    }
   
   @Test(expected = RuntimeException.class)
   @Transactional
   public void testgetAllTaskException() {
   	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
	CriteriaQuery<Task> criteriaquery = Mockito.mock(CriteriaQuery.class);
	Root<Task> root = Mockito.mock(Root.class);
	Query query = Mockito.mock(Query.class);
	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(Task.class);
	Mockito.doReturn(root).when(criteriaquery).from(Task.class);
	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
	Mockito.doThrow(new RuntimeException()).when(query).getResultList();
	taskDaoImpl.getAllTask();
   }
   
   @Test(expected = RuntimeException.class)
   @Transactional
   public void testgetAllParenTaskException() {
   	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
	CriteriaQuery<Task> criteriaquery = Mockito.mock(CriteriaQuery.class);
	Root<Task> root = Mockito.mock(Root.class);
	Query query = Mockito.mock(Query.class);
	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(Task.class);
	Mockito.doReturn(root).when(criteriaquery).from(Task.class);
	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
	Mockito.doThrow(new RuntimeException()).when(query).getResultList();
	taskDaoImpl.getAllParentTask();
   }
    
    @Test
    public void testGetallTask() throws Exception{
    	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
    	CriteriaQuery<Task> criteriaquery = Mockito.mock(CriteriaQuery.class);
    	Root<Task> root = Mockito.mock(Root.class);
    	Query query = Mockito.mock(Query.class);
    	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
    	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(Task.class);
    	Mockito.doReturn(root).when(criteriaquery).from(Task.class);
    	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
    	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
    	Mockito.doReturn(tasks).when(query).getResultList();
    	taskDaoImpl.getAllTask();
    	assertNotNull(tasks);
     }
    
    @Test
    public void testGetallParentTask() throws Exception{
    	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
    	CriteriaQuery<ParentTask> criteriaquery = Mockito.mock(CriteriaQuery.class);
    	Root<ParentTask> root = Mockito.mock(Root.class);
    	Query query = Mockito.mock(Query.class);
    	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
    	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(ParentTask.class);
    	Mockito.doReturn(root).when(criteriaquery).from(ParentTask.class);
    	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
    	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
    	Mockito.doReturn(tasks).when(query).getResultList();
    	taskDaoImpl.getAllParentTask();
    	assertNotNull(parenttasks);
     }
    
   
}
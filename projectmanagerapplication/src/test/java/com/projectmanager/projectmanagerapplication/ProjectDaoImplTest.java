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

import com.projectmanager.projectmanagerapplication.dao.ProjectDaoImpl;
import com.projectmanager.projectmanagerapplication.repository.Project;
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

public class ProjectDaoImplTest {
    @Mock
    private EntityManagerFactory entityManagerFactory; 
    @InjectMocks
    private transient ProjectDaoImpl projectDaoImpl;
    User user;
    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;
    static List<Project> projects;
    Project project;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
        projects = new ArrayList<Project>();
        session = Mockito.mock(Session.class);
        sessionFactory = Mockito.mock(SessionFactory.class);
        transaction = Mockito.mock(Transaction.class);
        Mockito.doReturn(sessionFactory).when(entityManagerFactory).unwrap(SessionFactory.class);
        Mockito.doReturn(session).when(sessionFactory).openSession();
        Mockito.doReturn(transaction).when(session).beginTransaction();
        project = new Project(2, "serviceju", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()), 3, "active");
        projects.add(project);
    }

    @Test
    @Transactional
    public void testSaveProject() throws Exception{
    	Mockito.doNothing().when(session).saveOrUpdate(project);
    	projectDaoImpl.addOrUpdateProject(project);
    	assertEquals(2, project.getProjectId());
    }
    
    @Test
    public void testGetallProject() throws Exception{
    	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
    	CriteriaQuery<Project> criteriaquery = Mockito.mock(CriteriaQuery.class);
    	Root<Project> root = Mockito.mock(Root.class);
    	Query query = Mockito.mock(Query.class);
    	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
    	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(Project.class);
    	Mockito.doReturn(root).when(criteriaquery).from(Project.class);
    	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
    	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
    	Mockito.doReturn(projects).when(query).getResultList();
    	projectDaoImpl.getAllProject();
    	assertNotNull(projects);
     }
}
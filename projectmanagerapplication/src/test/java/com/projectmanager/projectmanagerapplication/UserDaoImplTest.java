package com.projectmanager.projectmanagerapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.projectmanager.projectmanagerapplication.dao.UserDaoImpl;
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

public class UserDaoImplTest {
    @Mock
    private EntityManagerFactory entityManagerFactory; 
    @InjectMocks
    private transient UserDaoImpl userDaoImpl;
    User user;
    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;
    User user1;
    static List<User> users;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
        users = new ArrayList<User>();
        session = Mockito.mock(Session.class);
        sessionFactory = Mockito.mock(SessionFactory.class);
        transaction = Mockito.mock(Transaction.class);
        Mockito.doReturn(sessionFactory).when(entityManagerFactory).unwrap(SessionFactory.class);
        Mockito.doReturn(session).when(sessionFactory).openSession();
        Mockito.doReturn(transaction).when(session).beginTransaction();
        user = new User(3, "mark", "hannigan", 1911 , "active");
        users.add(user);
        user1 = user;
    }

    @Test
    @Transactional
    public void testSaveUser() throws Exception{
    	Mockito.doNothing().when(session).saveOrUpdate(user);
    	userDaoImpl.addOrUpdateUser(user);
    	assertEquals(user.getUserId(), user1.getUserId());
    }
    
    @Test
    @Transactional
    public void deleteUser() throws Exception{
    	Mockito.doNothing().when(session).delete(user);
    	userDaoImpl.deleteUser(user);
    	assertTrue(userDaoImpl.deleteUser(user));
    }

    @Test
    public void testGetallUsers() throws Exception{
    	CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
    	CriteriaQuery<User> criteriaquery = Mockito.mock(CriteriaQuery.class);
    	Root<User> root = Mockito.mock(Root.class);
    	Query query = Mockito.mock(Query.class);
    	Mockito.doReturn(criteriaBuilder).when(session).getCriteriaBuilder();
    	Mockito.doReturn(criteriaquery).when(criteriaBuilder).createQuery(User.class);
    	Mockito.doReturn(root).when(criteriaquery).from(User.class);
    	Mockito.doReturn(criteriaquery).when(criteriaquery).select(root);
    	Mockito.doReturn(query).when(session).createQuery(criteriaquery);
    	Mockito.doReturn(users).when(query).getResultList();
    	userDaoImpl.getAllUser();
    	assertNotNull(users);
     }
    
    @Test(expected = RuntimeException.class)
    @Transactional
    public void testSaveTaskException() {
    	Mockito.doThrow(new RuntimeException()).when(session).saveOrUpdate(user);
    	userDaoImpl.addOrUpdateUser(user);
    }
}
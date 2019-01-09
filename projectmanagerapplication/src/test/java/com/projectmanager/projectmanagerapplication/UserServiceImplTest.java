package com.projectmanager.projectmanagerapplication;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.projectmanager.projectmanagerapplication.dao.UserDao;
import com.projectmanager.projectmanagerapplication.exception.UserNotFoundException;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
    @Mock
    private transient UserDao userDao;
    @InjectMocks
    private transient UserServiceImpl userServiceImpl;
    User user;

    @Before
    public void mockSetUp(){
        MockitoAnnotations.initMocks(this);
        user = new User(3, "mark", "hannigan", 1911 , "active");
    }

    @Test
    public void testSaveUser() throws Exception{
        Mockito.when(userDao.addOrUpdateUser(user)).thenReturn(user);
        assertTrue(userServiceImpl.addOrUpdateUser(user));
        Mockito.verify(userDao, Mockito.times(1)).addOrUpdateUser(user);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetallUsers() throws Exception{
        List<User> users = new ArrayList<User>();
        Mockito.when(userDao.getAllUser()).thenReturn(users);
        List<User> allusers = userServiceImpl.getUserList();
        assertTrue(allusers.size() == 0);
        Mockito.verify(userDao, Mockito.times(1)).getAllUser();
    }

    @Test
    public void updateUser() throws Exception{
        Mockito.when(userDao.addOrUpdateUser(user)).thenReturn(user);
        assertTrue(userServiceImpl.addOrUpdateUser(user));
        Mockito.verify(userDao, Mockito.times(1)).addOrUpdateUser(user);
    }
    
    @Test
    public void deleteUser() throws Exception{
        Mockito.when(userDao.deleteUser(user)).thenReturn(true);
        assertTrue(userServiceImpl.deleteUser(user));
        Mockito.verify(userDao, Mockito.times(1)).deleteUser(user);
    }
}
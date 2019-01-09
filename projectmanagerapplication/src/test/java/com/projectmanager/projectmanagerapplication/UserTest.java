package com.projectmanager.projectmanagerapplication;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanager.projectmanagerapplication.controller.UserController;
import com.projectmanager.projectmanagerapplication.repository.User;
import com.projectmanager.projectmanagerapplication.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserTest {

    @Autowired
    private transient MockMvc mockMvc;
    @MockBean
    private transient UserService userService;
    private transient User user;
    static List<User> users;  
    
    @Before
    public void setUpData(){
        users = new ArrayList<User>();
        user = new User(3, "mark", "hannigan", 1911 , "active");
        users.add(user);
    }

    private static String convertfromJsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try{
            final String jsonString = new ObjectMapper().writeValueAsString(obj);
            result = jsonString;
        }catch(JsonProcessingException ex){
            result = "Error Invlaid";

        }
        return result;
    }

    @Test
    public void testSaveNewUser() throws Exception{
        Mockito.when(userService.addOrUpdateUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(user))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, times(1)).addOrUpdateUser(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userService);

    } 

    @Test
    public void testGetallProject() throws Exception{
        Mockito.when(userService.getUserList()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, times(1)).getUserList();
        Mockito.verifyNoMoreInteractions(userService);

    }

    @Test
    public void testUpdateProject() throws Exception{
        Mockito.when(userService.addOrUpdateUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON).content(convertfromJsonToString(user))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, times(1)).addOrUpdateUser(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userService);
    }

    @Test
    public void testDeleteUser() throws Exception{
        Mockito.when(userService.deleteUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/3").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(userService, times(1)).deleteUser(Mockito.any(User.class));
        Mockito.verifyNoMoreInteractions(userService);
    }
}
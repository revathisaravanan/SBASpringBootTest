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

import com.projectmanager.projectmanagerapplication.repository.User;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManagerFactory entityManagerFactory; 
   
    public User addOrUpdateUser(User user) {
        LOGGER.debug("UserDaoImpl - addOrUpdateUser() in DAO Starts" +user.getFirstName());
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(user);
        }
        catch (RuntimeException ex) {
            session.close();
            throw new RuntimeException();
        }
        finally{
            tx.commit();
            session.close();
        }
        LOGGER.debug("UserDaoImpl - addOrUpdateUser() Ends");
        return user;
    }

    public List<User> getAllUser() {
        LOGGER.debug("UserDaoImpl - getAllUser() Starts");
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        List<User> userList = null;
        try{
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root); 
            userList = session.createQuery(query).getResultList();
        }catch (Exception ex) {
            throw ex;
        }
        finally{
            session.close();
        }
        LOGGER.debug("UserDaoImpl - getAllUser() Ends");
        return userList;
    }

    public boolean deleteUser(User user){
        LOGGER.debug("UserDaoImpl - deleteUser() Starts"+user.getUserId());
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession(); 
        Transaction tx = session.beginTransaction();
        try {
            session.load(user, user.getUserId());
            session.delete(user);
        }
        catch (Exception ex) {
            session.close();
            throw ex;
        }
        finally{
            tx.commit();
            session.close();
        }
        LOGGER.debug("UserDaoImpl - deleteUser() Ends");
        return true;
    }
}

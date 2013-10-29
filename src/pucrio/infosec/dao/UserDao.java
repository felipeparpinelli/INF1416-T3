/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class UserDao {

    public static User searchUser(String user) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from User Usuarios where login = '"
                + user + "'";
        Query query = session.createQuery(queryString);
        User userFound = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return userFound;
    }
    
    public static User searchUserById(int userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from User Usuarios where id = " + userId;
        Query query = session.createQuery(queryString);
        User userFound = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return userFound;
    }
    
    public static void storeUser (User user){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Random rand = new Random();
        Integer salt = rand.nextInt(999999999);
        try {
            String pwdHash = Auth.generatePasswordHash(user.getPwd(), salt);
            user.setPwd(pwdHash);
            user.setpasswordTries(0);
            user.setIsBlocked(false);
            user.setSalt(salt);
            user.setAccessNumber(0);
            user.setQueriesNumber(0);
            session.save(user);
            tx.commit();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.close(); 
    }
    
    public static void updateUser (User user){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();       
        session.close(); 
    }
    
    public static Long countUsers(){
 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "select count(*) from User Usuarios";
        Query query = session.createQuery(queryString);
        Long userFound = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return userFound;
    }
}

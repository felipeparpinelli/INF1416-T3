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
        String queryString = "from User Usuarios where login like '%"
                + user + "%' order by login";
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
            user.setSalt(salt);
            session.save(user);
            tx.commit();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.close(); 
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.model.*;

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

    public static void insertUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(user);
        session.persist(user);
        t.commit();

        session.clear();
        session.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.model.User;
import pucrio.infosec.model.Message;

/**
 *
 * @author Arrais
 */
public class MessageDao {
    
        public static Message searchMessage(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from Message Mensagens where id = "+ id;
        Query query = session.createQuery(queryString);
        Message message = (Message) query.uniqueResult();
        transaction.commit();
        session.close();
        return message;
    }
}

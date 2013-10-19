/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.dao;

import java.util.Calendar;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.model.Message;
import pucrio.infosec.model.Registry;
import pucrio.infosec.model.User;
import pucrio.infosec.helpers.Auth;

/**
 *
 * @author Arrais
 */
public class RegistryrDao {
    public static Message searchEntriesAfterDate(Date date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from Message Mensagens where data = "+ date + "order by data";
        Query query = session.createQuery(queryString);
        Message message = (Message) query.uniqueResult();
        transaction.commit();
        session.close();
        return message;
    }
    
    public static void storeRegistry (int messageId){
        RegistryrDao.storeRegistry(messageId, null);       
    }
    
    public static void storeRegistry (int messageId, String arquivo){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Registry registry = new Registry();
        registry.setDate(new Date());
        registry.setMessageId(messageId);
        User user = Auth.getInstance().getCurrentUser();       
        if (!user.equals(null))
        {
            registry.setUserId(user.getId());
        }
        
        if (!arquivo.equals(null))
        {
            registry.setArquivo(arquivo);
        }

        session.save(registry);
        session.close();      
    }
}

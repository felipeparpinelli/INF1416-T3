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
public class RegistryDao {
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
        RegistryDao.storeRegistry(messageId, null, null);       
    }
    
    public static void storeRegistry (int messageId, String data){
        RegistryDao.storeRegistry(messageId, data, null);       
    }
    
    public static void storeRegistryWithFile (int messageId, String arquivo){
        RegistryDao.storeRegistry(messageId, null, arquivo);
    }
    
    public static void storeRegistry (int messageId, String data, String arquivo){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Registry registry = new Registry();
        registry.setDate(new Date());
        registry.setMessageId(messageId);
        registry.setData(data);
        User user = Auth.getInstance().getCurrentUser();       
        if (user != null)
        {
            registry.setUserId(user.getId());
        }
        
        if (arquivo != null)
        {
            registry.setArquivo(arquivo);
        }
        Transaction tx = session.beginTransaction();
        session.save(registry);
        tx.commit();
        
        session.close(); 
    }
    
}

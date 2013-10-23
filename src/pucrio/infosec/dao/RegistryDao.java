/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    public static ArrayList<Registry> searchEntries() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from Registry Registro order by id";
        Query query = session.createQuery(queryString);
        ArrayList<Registry> entries = (ArrayList<Registry>) query.list();
        transaction.commit();
        session.close();
        return entries;
    }
    
    public static void storeRegistry (int messageId){
        RegistryDao.storeRegistry(messageId, null, null);       
    }
    
    public static void storeRegistry (int messageId, String data){
        RegistryDao.storeRegistry(messageId, data, null);       
    }
    
    public static void storeRegistryWithFile (int messageId, String userId, String arquivo){
        RegistryDao.storeRegistry(messageId, userId, arquivo);
    }
    
    public static void storeRegistry (int messageId, String data, String arquivo){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Registry registry = new Registry();
        registry.setDate(Calendar.getInstance().getTime());
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

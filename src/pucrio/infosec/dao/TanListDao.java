/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.TanList;
import pucrio.infosec.model.User;

/**
 *
 * @author Arrais
 */
public class TanListDao {

    public static TanList store(int userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();        
        TanList tanList = new TanList();      
        tanList.setUserId(userId);
        Transaction tx = session.beginTransaction();
        session.save(tanList);
        tx.commit();
        
        session.close(); 
        
        return tanList;
    }
    
}

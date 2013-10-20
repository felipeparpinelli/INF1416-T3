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
import pucrio.infosec.model.OneTimePassword;
import pucrio.infosec.model.TanList;

/**
 *
 * @author Arrais
 */
public class OneTimePasswordDao {
    
    public static void store(OneTimePassword pass){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();        
        Transaction tx = session.beginTransaction();
        session.save(pass);
        tx.commit();
        
        session.close(); 
    }

    public static List<OneTimePassword> getAll(int tanListId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

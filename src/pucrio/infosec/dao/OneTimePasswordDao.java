/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.OneTimePassword;
import pucrio.infosec.model.TanList;
import pucrio.infosec.model.User;

/**
 *
 * @author Arrais
 */
public class OneTimePasswordDao {
    
    public static void store(OneTimePassword pass){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();        
        Transaction tx = session.beginTransaction();
        Random rand = new Random();
        Integer salt = rand.nextInt(999999999);
        pass.setSalt(salt);
        try {
            pass.setPassword(Auth.generatePasswordHash(pass.getPassword(), salt));
            session.save(pass);
            tx.commit();
            session.close(); 
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(OneTimePasswordDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<OneTimePassword> getAllByTanList(int tanListId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static OneTimePassword getRandomUnusedByUser (int userId){ 
        int tanListId = TanListDao.searchUnusedByUser(userId).getId();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from OneTimePassword OneTimePassword where tanlist_id = "
                + tanListId + " and usado = 0 order by chave";
        Query query = session.createQuery(queryString);
        List<OneTimePassword> passwords = (List<OneTimePassword>) query.list();
        transaction.commit();
        session.close();  
        
        OneTimePassword pass = null;
        Integer  key;
        do{
            Random rand = new Random();
            User user = UserDao.searchUserById(userId);
            key = rand.nextInt(user.getTanLength() - 1);
            try{
                pass = passwords.get(key);
            }catch(IndexOutOfBoundsException ex){
                pass = null;
            }
        }while(pass == null);
        
        return pass;
    }
    
    public static OneTimePassword getByUserIdAndKey (int userId, int key){
        int tanListId = TanListDao.searchUnusedByUser(userId).getId();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "from OneTimePassword OneTimePassword where tanlist_id = "
                + tanListId + " and key = " + key;
        Query query = session.createQuery(queryString);
        OneTimePassword pass = (OneTimePassword) query.uniqueResult();
        transaction.commit();
        session.close(); 
        
        return pass;
    }
    
    public static void update (OneTimePassword pass){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(pass);
        tx.commit();       
        session.close(); 
    }
}

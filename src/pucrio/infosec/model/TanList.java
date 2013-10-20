/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.model;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pucrio.infosec.dao.HibernateUtil;
import pucrio.infosec.dao.OneTimePasswordDao;
import pucrio.infosec.helpers.Auth;

/**
 *
 * @author Arrais
 */
@Entity
@Table(name="Tanlist")
public class TanList {
    @Column(name = "usuario_id")
    private int userId;
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    public void addOneTimePassword(String oneTimePassword, int key) {
        OneTimePassword pass = new OneTimePassword();
        
        pass.setPassword(oneTimePassword);
        pass.setTanListId(this.id);
        pass.setKey(key);
        OneTimePasswordDao.store(pass);
    }
    
    public List<OneTimePassword> getOneTimePasswords (){
        return OneTimePasswordDao.getAll(this.id);
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

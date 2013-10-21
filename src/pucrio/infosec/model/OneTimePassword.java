/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pucrio.infosec.helpers.Auth;

/**
 *
 * @author Arrais
 */
@Entity
@Table(name="OneTimePassword")
public class OneTimePassword {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "tanlist_id")
    private int tanListId;
    
    @Column(name = "senha")
    private String password;
    
    @Column(name = "chave")
    private int key;
    
    @Column(name = "usado")
    private int used;

    /**
     * @return the tanListId
     */
    public int getTanListId() {
        return tanListId;
    }

    /**
     * @param tanListId the tanListId to set
     */
    public void setTanListId(int tanListId) {
        this.tanListId = tanListId;
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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author arrais
 */

@Entity
@Table(name="Registros")
public class Registry implements Serializable{   
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "data")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "mensagem_id")
    private int messageId;
    
    @Column(name = "usuario_id")
    private int userId;
    
    @Column(name = "arquivo")
    private String arquivo;
    
    @Column(name = "dado")
    private String data;
    
    @Column(name = "login")
    private String login;
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param data the date to set
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * @return the data
     */
    public String getData() {
        return this.data;
    }
    
    /**
     * @return the arquivo
     */
    public String getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
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
     * @return the messageId
     */
    public int getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setLogin(String data) {
        this.login = data;
    }
    
    public String getLogin(){
        return this.login;
    }
    
}

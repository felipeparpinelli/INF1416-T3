/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import pucrio.infosec.dao.MessageDao;

/**
 *
 * @author Felipe
 */
public class Message {

    @Id 
    @Column(name = "id")
    private int id;
    
    @Column(name = "mensagem")
    private String message;
    
    public Message getMessage(int id) {
        return MessageDao.searchMessage(id);
    }
}

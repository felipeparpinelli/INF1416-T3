/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pucrio.infosec.dao.MessageDao;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name="Mensagens")
public class Message {


    @Id 
    @Column(name = "chave")
    private int key;
    
    @Column(name = "id")
    private int id;
    
    @Column(name = "mensagem")
    private String message;
        
    public static String parseMessage (int id, String loginName, String file){
        Message message = MessageDao.searchMessage(id);
        
        String text = message.message;
        
        if (loginName != null)
        {
            text = text.replace("<login_name>", loginName);
        }
        
        if (file != null)
        {
            text = text.replace("<arq_name>", file);
        }
        
        return text;
    }
    
    public static String parseMessageWithLogin(int id, String login, String file) {
        Message message = MessageDao.searchMessage(id);
        
        String text = message.message;
        
        if (login != null)
        {
            text = text.replace("<login_name>", login);
        }
        
        if (file != null)
        {
            text = text.replace("<arq_name>", file);
        }
        
        return text;
    }
}

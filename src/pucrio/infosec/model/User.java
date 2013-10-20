/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name="Usuarios")
public class User {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "pwd")
    private String pwd;
    
    @Column(name = "salt")
    private int salt;
    
    private String confirmPwd;
    
    private String tanPath;
    
    private String tanLength;
    
    @Column(name = "accessControl")
    private String accessControl;
    
    @Column(name = "numberAccesses")
    private String accessNumber;
    
    @Column(name = "numberQueries")
    private String queriesNumber;
    
    @Column(name = "groupName")
    @Enumerated(EnumType.STRING)
    private GroupName groupName;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getTanPath() {
        return tanPath;
    }

    public void setTanPath(String tanPath) {
        this.tanPath = tanPath;
    }

    public String getTanLength() {
        return tanLength;
    }

    public void setTanLength(String tanLength) {
        this.tanLength = tanLength;
    }


    public GroupName getGroupName() {
        return groupName;
    }


    public void setGroupName(GroupName group) {
        this.groupName = group;
    }  
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import static java.util.concurrent.TimeUnit.*;
import javax.persistence.Temporal;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.dao.UserDao;
import pucrio.infosec.helpers.Auth;

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
    
    @Column(name = "tentativas_senha")
    private Integer passwordTries;
    
    @Column(name = "bloqueado")
    private Boolean isBlocked;
    
    @Column(name = "data_bloqueio")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date blockDate;
    
    private String confirmPwd;
    
    private String tanPath;
    
    private int tanLength;
 
    @Column(name = "accessControl")
    private String accessControl;
    
    @Column(name = "numberAccesses")
    private int accessNumber;
    
    @Column(name = "numberQueries")
    private int queriesNumber;
    
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

    public int getTanLength() {
        return tanLength;
    }

    public void setTanLength(int tanLength) {
        this.tanLength = tanLength;
    }

    public String getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(String accessControl) {
        this.accessControl = accessControl;
    }

    public int getAccessNumber() {
        return accessNumber;
    }
    
    public Integer getpasswordTries(){
        return this.passwordTries;
    }
    
    public void setpasswordTries(Integer n){
        this.passwordTries = n;
    }
    
    protected boolean getIsBlocked(){
        return this.isBlocked;
    }
    
    public void setIsBlocked(boolean n){
        this.isBlocked = n;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }
    
    public void increaseAccessNumber() {
        this.setAccessNumber(this.getAccessNumber() + 1);
        this.update(); 
        
    }
    
    public void increaseQueriesNumber() {
        this.setQueriesNumber(this.getQueriesNumber() + 1);
        this.update(); 
        
    }

    public int getQueriesNumber() {
        return queriesNumber;
    }

    public void setQueriesNumber(int queriesNumber) {
        this.queriesNumber = queriesNumber;
    }
    
    public GroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupName group) {
        this.groupName = group;
    }  

    public void increasePasswordTries(int phase) {
        this.passwordTries++;
        if (this.passwordTries >= 3)
        {
            switch (phase)
            {
                case 2:
                    RegistryDao.storeRegistry(3008, Auth.getInstance().getCurrentUser().getLogin());
                    break;
                case 3:
                    RegistryDao.storeRegistry(4007, Auth.getInstance().getCurrentUser().getLogin());
                    break;
            }
            this.block();
        }
        else{
            this.update(); 
        }
        
    }
    
    public boolean isBlocked() {
        if(this.isBlocked.equals(true) && !this.canUnblock())
        {
            return true;
        }
        else
        {
            if(this.isBlocked.equals(true) && this.canUnblock())
            {
                this.passwordTries = 0;
                this.unblock();
            }
            return false;
        }
    }

    private void block() {
        this.isBlocked = true;
        this.blockDate = Calendar.getInstance().getTime();
        this.update();
    }
        
    private boolean canUnblock() {
        Date now = new Date();
        
        long MAX_DURATION = MILLISECONDS.convert(2, MINUTES);

        if (!this.blockDate.equals(null)){
            long duration = now.getTime() - this.blockDate.getTime();

            if (duration >= MAX_DURATION) {
                return true;
            }
        }
        
        return false;

    }
    
    private void unblock() {
        this.isBlocked = false;
        this.blockDate = null;
        this.passwordTries = 0;
        this.update();
    }

    private void update() {
        UserDao.updateUser(this);
    }


}

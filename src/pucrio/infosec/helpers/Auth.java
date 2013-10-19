package pucrio.infosec.helpers;


import pucrio.infosec.model.User;
import pucrio.infosec.dao.RegistryrDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arrais
 */
public class Auth {
    
    private static Auth  instance;
    private User currentUser;
    
    private Auth(){
        
    }
    
    public static Auth getInstance() {
      if (instance == null)
         instance = new Auth();
      return instance;
    }
       
    public void setCurrentUser (User user){
        this.currentUser = user;
    }
    
    public User getCurrentUser (){
        return this.currentUser;
    }
    
}


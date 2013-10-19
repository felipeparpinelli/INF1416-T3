/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pucrio.infosec.controller;

import pucrio.infosec.model.User;

/**
 *
 * @author Arrais
 */
public class AuthController {
    private User currentUser;
    
    public void setCurrentUser (User user){
        this.currentUser = user;
    }
    
    public User getCurrentUser (){
        return this.currentUser;
    }
    
}

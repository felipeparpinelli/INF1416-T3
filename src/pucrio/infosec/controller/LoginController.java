/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import javax.swing.JPanel;
import pucrio.infosec.dao.*;
import pucrio.infosec.model.*;

/**
 *
 * @author Felipe
 */
public class LoginController {

    private String login;
    User user = new User();

    public LoginController(String login) {
        this.login = login;
    }

    public boolean checkLogin(JPanel panel) {

        if (login == null || login.isEmpty()) {
            //panel.ErrorMessage("Preencha o login");
            panel.validate();
        } else {

            user = UserDao.searchUser(login);
            if (user == null) {
                //panel.ErrorMessage("Usuario nao encontrado");
                panel.validate();
            } else {
                // TODO Verificar se usuário está bloqueado 
                if (user == null) {
                    
                    //panel.ErrorMessage("Usuario bloqueado, tente novamente em alguns instantes");
                    panel.validate();

                } else {
                    
                    return true;
                    
                }
            }
        }
        
        return false;
    }
}

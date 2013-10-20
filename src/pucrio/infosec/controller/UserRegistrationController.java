/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import pucrio.infosec.dao.UserDao;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class UserRegistrationController {
    
    User user = new User();
    
    public UserRegistrationController() {
    }

    public boolean isValidPwd(String pwd) {
        if (isNumeric(pwd)) {
            if (isValidLength(pwd)) {
                if (isConsecutiveDigits(pwd)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isValidLength(String str) {
        if (str.length() == 6) {
            return true;
        }

        return false;
    }

    public static boolean isConsecutiveDigits(String str) {

        int i = 0;
        int count = 0;
        while (str.length() > i && i != 5) {
            if (str.charAt(i) == str.charAt(i+1)) {
                count++;
            }
            i++;
        }
        
        if(count == 0)
            return true;
        else
            return false;

    }
    
    public boolean checkLogin(String login)
    { 
        user = UserDao.searchUser(login);
        
        if(user == null)
        {   
            return true;   
        } 
        
        return false;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import pucrio.infosec.dao.OneTimePasswordDao;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.OneTimePassword;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class TanListController {

    public boolean isInputCorrect(int passKey, String input) {
        User user = Auth.getInstance().getCurrentUser();
        OneTimePassword pass = OneTimePasswordDao.getByUserIdAndKey(user.getId(), passKey);
        
        if(pass.getPassword().equals(input))
        {
            return true;
        }
        else{
            user.increasePasswordTries();
            return false;
        }
    }
    
}

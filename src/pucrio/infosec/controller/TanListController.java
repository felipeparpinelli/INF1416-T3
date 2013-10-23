/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pucrio.infosec.dao.OneTimePasswordDao;
import pucrio.infosec.dao.RegistryDao;
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
        try {
            if(pass.getPassword().equals(Auth.generatePasswordHash(input, pass.getSalt())))
            {
                if(!pass.isUsed())
                {
                    pass.use();
                    RegistryDao.storeRegistry(4003, Auth.getInstance().getCurrentUser().getLogin());
                    RegistryDao.storeRegistry(4002, Auth.getInstance().getCurrentUser().getLogin());
                    return true;
                }
                else
                {
                    user.increasePasswordTries(3);
                    switch (user.getpasswordTries())
                    {
                        case 1:
                            RegistryDao.storeRegistry(4004, Auth.getInstance().getCurrentUser().getLogin());
                            break;
                        case 2:
                            RegistryDao.storeRegistry(4005, Auth.getInstance().getCurrentUser().getLogin());
                            break;
                        case 3:
                            RegistryDao.storeRegistry(4006, Auth.getInstance().getCurrentUser().getLogin());
                            break;
                    }
                    return false;
                }
            }
            else{
                user.increasePasswordTries(3);
                switch (user.getpasswordTries())
                {
                    case 1:
                        RegistryDao.storeRegistry(4004, Auth.getInstance().getCurrentUser().getLogin());
                        break;
                    case 2:
                        RegistryDao.storeRegistry(4005, Auth.getInstance().getCurrentUser().getLogin());
                        break;
                    case 3:
                        RegistryDao.storeRegistry(4006, Auth.getInstance().getCurrentUser().getLogin());
                        break;
                }
                return false;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(TanListController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}

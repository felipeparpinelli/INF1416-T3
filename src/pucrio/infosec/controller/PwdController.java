/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class PwdController {

    String[] pwdListReceived;
    private int count = 0;

    public PwdController(String[] pwdList) {
        this.pwdListReceived = pwdList;
    }

    public boolean checkPwd() {

       String[][] pwdList = convertListReceived();
       
        User user = Auth.getInstance().getCurrentUser();
        int salt = Auth.getInstance().getCurrentUser().getSalt();
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m = 0; m < 2; m++) {
                            for (int n = 0; n < 2; n++){
                                String candidate = pwdList[0][i] + pwdList[1][j] + pwdList[2][k] +
                                                   pwdList[3][l] + pwdList[4][m] + pwdList[5][n] ;
                                try {
                                    String hash = Auth.generatePasswordHash(candidate, salt);
                                    if (hash.equals(user.getPwd())) {
                                        RegistryDao.storeRegistry(3003, Auth.getInstance().getCurrentUser().getLogin());
                                        RegistryDao.storeRegistry(3002, Auth.getInstance().getCurrentUser().getLogin());
                                        user.setpasswordTries(0);
                                        return true;
                                    }
                                } catch (NoSuchAlgorithmException ex) {
                                    Logger.getLogger(PwdController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (UnsupportedEncodingException ex) {
                                    Logger.getLogger(PwdController.class.getName()).log(Level.SEVERE, null, ex);
                                } 
                            }
                        }
                    }
                }
            }
        }
        
        RegistryDao.storeRegistry(3004, Auth.getInstance().getCurrentUser().getLogin());
        switch (user.getpasswordTries())
        {
            case 1:
                RegistryDao.storeRegistry(3005, Auth.getInstance().getCurrentUser().getLogin());
                break;
            case 2:
                RegistryDao.storeRegistry(3006, Auth.getInstance().getCurrentUser().getLogin());
                break;
            case 3:
                RegistryDao.storeRegistry(3007, Auth.getInstance().getCurrentUser().getLogin());
                break;
        }
        user.increasePasswordTries(2);
        return false;
    }

    public String[][] convertListReceived() {
        String[][] tempList = new String[6][4];
        for (int i = 0; i < 6; i++) {
            tempList[i] = pwdListReceived[i].split(" ou ");
        }
        return tempList;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class PwdController {
    
    List<String> pwdListReceived = new ArrayList<>();
    List<String> pwdList = new ArrayList<>();
    
    public PwdController (List<String> pwdList)
    {
        this.pwdListReceived = pwdList;
    }

    public boolean checkPwd() {
        
        convertListReceived();
        return true;
    }
    
    public List<String> convertListReceived ()
    {
        for (String pwd:pwdListReceived)
        {
            String[] splitString = (pwd.split(" ou "));
            pwdList.add(splitString[0]);
            pwdList.add(splitString[1]);
        }
        
        return pwdList;
    }
    
}

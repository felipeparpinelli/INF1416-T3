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
    private int count = 0;

    public PwdController(List<String> pwdList) {
        this.pwdListReceived = pwdList;
    }

    public boolean checkPwd() {

        pwdList = convertListReceived();

        // TODO pegar senha do usuário cadastrada no banco.
        // A senha não deve ser cadastrada em texto plano e deve seguir as 
        // especificacoes do enunciado.
        ArrayList<String> fakePwd = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
            }
        };
        
        int countPwdReceive = 0; 
        for (int i = 0; i < 6; i++) {
            
            if (pwdList.get(countPwdReceive).equals(fakePwd.get(i)) || pwdList.get(countPwdReceive + 1).equals(fakePwd.get(i))) {
                count++;
            }
            countPwdReceive = countPwdReceive + 2;
        }
        
        if (count == 6)
            return true;

        return false;
    }

    public List<String> convertListReceived() {
        List<String> tempList = new ArrayList<>();
        for (String pwd : pwdListReceived) {
            String[] splitString = (pwd.split(" ou "));
            tempList.add(splitString[0]);
            tempList.add(splitString[1]);
        }

        return tempList;
    }
}

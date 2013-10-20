/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import pucrio.infosec.dao.UserDao;
import pucrio.infosec.model.GroupName;
import pucrio.infosec.model.User;
import static pucrio.infosec.view.PwdPanel.loadPwdList;

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
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            }
            i++;
        }

        if (count == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkLogin(String login) {
        user = UserDao.searchUser(login);

        if (user == null) {
            return true;
        }

        return false;
    }

    public boolean isEqualPwd(String pwd, String confirmPwd) {
        if (pwd.equals(confirmPwd)) {
            return true;
        }

        return false;
    }

    public void createTanList(String path, String length, String login) {
        Writer writer = null;
        int tanLength = Integer.parseInt(length);
        List<String> oneTimePasswords = createOneTimePasswords();
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path + "/" + login + ".tan"), "ASCII"));
            
            if (tanLength > 10)
            {
                tanLength = 10;
            }
            
            if (tanLength < 1)
            {
                tanLength = 1;
            }
            
            for(int i = 0; i < tanLength; i++)
            {
                writer.write(oneTimePasswords.get(i) + "\n");
            }
            
        } catch (IOException ex) {

        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static List<String> loadTanDigitsList() {
        List<String> pwdList = new ArrayList<>();
        pwdList.add("0");
        pwdList.add("1");
        pwdList.add("2");
        pwdList.add("3");
        pwdList.add("4");
        pwdList.add("5");
        pwdList.add("6");
        pwdList.add("7");
        pwdList.add("8");
        pwdList.add("9");
        pwdList.add("A");
        pwdList.add("B");

        return pwdList;
    }

    public static String shuffle() {
        char[] chars = new char[5];
        List<String> pwdList = loadTanDigitsList();
        Collections.shuffle(pwdList);

        for(int i = 0; i < 5; i++)
        {
            
            chars[i] = pwdList.get(i).charAt(0);
        }

        return new String(chars);
    }
    
    public static List<String> createOneTimePasswords()
    {
        List<String> oneTimePasswordsList = new ArrayList<>();
        String oneTimePassword;
        
        for (int i = 0; i < 10; i++)
        {
           oneTimePasswordsList.add(shuffle());
        }
        
        return oneTimePasswordsList;
    }
    
    public void saveUser(User user)
    {   
        UserDao.storeUser(user);
    }
}

package pucrio.infosec.helpers;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pucrio.infosec.model.User;
import pucrio.infosec.dao.RegistryDao;

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
    private ArrayList<String> paths = new ArrayList<String>();
    
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
    
    public static String generatePasswordHash (String password, Integer salt) throws NoSuchAlgorithmException, UnsupportedEncodingException{         
        password = password + String.format("%09d", salt);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");           
            return bytesToHex(md5.digest(password.getBytes("UTF-8")));          
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public void setPaths (ArrayList<String> paths)
    {
        this.paths = paths;
    }
    
    public ArrayList<String> getPaths (){
        return this.paths;
    }
    
    public void addPath (String path){
        this.paths.add(path);
    }
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
}
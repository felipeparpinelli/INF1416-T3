/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import pucrio.infosec.helpers.Security;

/**
 *
 * @author Felipe
 */
public class KeyCheckController {

    private String privateKeyPath;
    private String publicKeyPath;
    private String passphrase;
    private byte[] privatekeyFile;
    private byte[] publicKeyFile;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    Security security = new Security();

    public KeyCheckController(String privateKeyPath, String passphrase, String publicKeyPath) {
        this.privateKeyPath = privateKeyPath;
        this.passphrase = passphrase;
        this.publicKeyPath = publicKeyPath;
    }

    public boolean checkPrivateKey() throws FileNotFoundException, IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, UnsupportedEncodingException, SignatureException {
        privatekeyFile = loadPrivateKey();
        publicKeyFile = loadPublicKey();
        privateKey = security.decryptPrivateKey(privatekeyFile, passphrase);
        publicKey = security.retrivePublicKey(publicKeyFile);
        boolean isSign = security.isSign(privateKey, publicKey);
        return isSign;
    }
    
    public byte[] loadPublicKey() throws FileNotFoundException, IOException
    {
        return publicKeyFile = loadFile(publicKeyPath);
    }
    
    public byte[] loadPrivateKey() throws FileNotFoundException, IOException
    {
        return privatekeyFile = loadFile(privateKeyPath);
    }

    public byte[] loadFile(String path) throws FileNotFoundException, IOException 
    {
        File pKFile = new File(path);

        FileInputStream fileInputStream = new FileInputStream(pKFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] file = new byte[(int) pKFile.length()];
        bufferedInputStream.read(file);

        return file;
    }
}

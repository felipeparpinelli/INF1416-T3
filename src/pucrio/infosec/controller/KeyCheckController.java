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
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.helpers.Security;

/**
 *
 * @author Felipe
 */
public class KeyCheckController {

    private String privateKeyPath;
    private String publicKeyPath;
    private String indexPath;
    private byte[] indexContent;
    private byte[] envelopeContent;
    private byte[] signatureContent;
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

    public KeyCheckController(String privateKeyPath, String publicKeyPath, String indexPath, String signatureContent) {
        this.privateKeyPath = privateKeyPath;
        this.publicKeyPath = publicKeyPath;
        this.indexPath = indexPath;
    }

    public boolean checkPrivateKey() throws FileNotFoundException, IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, UnsupportedEncodingException, SignatureException {
        privatekeyFile = loadPrivateKey();
        publicKeyFile = loadPublicKey();
        privateKey = security.decryptPrivateKey(privatekeyFile, passphrase);
        publicKey = security.retrivePublicKey(publicKeyFile);
        boolean isSign = security.isSign(privateKey, publicKey);
        return isSign;
    }

    public byte[] loadPublicKey() throws FileNotFoundException, IOException {
        return publicKeyFile = loadFile(publicKeyPath);
    }

    public byte[] loadPrivateKey() throws FileNotFoundException, IOException {
        return privatekeyFile = loadFile(privateKeyPath);
    }

    public byte[] loadFile(String path) throws FileNotFoundException, IOException {
        File pKFile = new File(path);

        FileInputStream fileInputStream = new FileInputStream(pKFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] file = new byte[(int) pKFile.length()];
        bufferedInputStream.read(file);

        return file;
    }

    public String getIndexContent(String passPhrase) {
        try {
            privatekeyFile = loadPrivateKey();
            publicKeyFile = loadPublicKey();
            privateKey = security.decryptPrivateKey(privatekeyFile, passPhrase);
            publicKey = security.retrivePublicKey(publicKeyFile);
            signatureContent = loadFile(indexPath + "/index.asd");
            envelopeContent = loadFile(indexPath + "/index.env");
            indexContent = loadFile(indexPath + "/index.enc");

            byte[] seed = security.getSeedEnvelope(envelopeContent, privateKey);
            Key key = security.getKeyFromSeed(seed);
            byte[] content = security.decryptPKCS5(indexContent, key);

            boolean result = security.checkSign(publicKey, signatureContent, content);
            return result ? new String(content, "UTF-8") : "NOT OK";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error!";
    }
    
    public byte[] getFileContent(String passPhrase, String arqName) {
        byte[] content = null;
        try {
            privatekeyFile = loadPrivateKey();
            publicKeyFile = loadPublicKey();
            privateKey = security.decryptPrivateKey(privatekeyFile, passPhrase);
            publicKey = security.retrivePublicKey(publicKeyFile);
            signatureContent = loadFile(indexPath + "/" + arqName + ".asd");
            envelopeContent = loadFile(indexPath + "/" + arqName + ".env");
            indexContent = loadFile(indexPath + "/" + arqName + ".enc");

            byte[] seed = security.getSeedEnvelope(envelopeContent, privateKey);
            Key key = security.getKeyFromSeed(seed);
            content = security.decryptPKCS5(indexContent, key);

            boolean result = security.checkSign(publicKey, signatureContent, content);
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return content;
    }
    
    public String getFileString(String passPhrase, String arqName) {
        byte[] content = null;
        try {
            privatekeyFile = loadPrivateKey();
            publicKeyFile = loadPublicKey();
            privateKey = security.decryptPrivateKey(privatekeyFile, passPhrase);
            publicKey = security.retrivePublicKey(publicKeyFile);
            signatureContent = loadFile(indexPath + "/" + arqName + ".asd");
            envelopeContent = loadFile(indexPath + "/" + arqName + ".env");
            indexContent = loadFile(indexPath + "/" + arqName + ".enc");

            byte[] seed = security.getSeedEnvelope(envelopeContent, privateKey);
            Key key = security.getKeyFromSeed(seed);
            content = security.decryptPKCS5(indexContent, key);

            boolean result = security.checkSign(publicKey, signatureContent, content);
                           
            return result ? "OK" : "NOT OK";
            //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Error";
    }
}

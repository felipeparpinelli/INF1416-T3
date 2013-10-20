/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.launch;

import pucrio.infosec.view.*;
import pucrio.infosec.dao.RegistryrDao;

/**
 *
 * @author Felipe
 */
public class Main {
    
    public static void main(String args[])
    {
        MainFrame mainframe = new MainFrame();
        LoggerFrame logger = new LoggerFrame();
        RegistryrDao.storeRegistry(1001);        
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Felipe
 */
public class LoggerFrame extends JFrame{
    
    private LoggerPanel loggerPanel;
    
    public LoggerFrame ()
    {
        this.setTitle("LogView");
        this.setLocation(580, 200);
        this.setSize(400, 500);    
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loggerPanel = new LoggerPanel();
        this.getContentPane().add(loggerPanel);
  
        this.setVisible(true);
    }
    
}

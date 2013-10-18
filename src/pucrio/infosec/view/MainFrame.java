/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import javax.swing.*;

/**
 *
 * @author Felipe
 */
public class MainFrame extends JFrame {
    
    private MainPanel mainPanel;
    
    public MainFrame ()
    {
        this.setTitle("INF1416 - TrabP1 - Grupo 3");
        this.setLocation(50, 200);
        this.setSize(500, 500);    
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel = new MainPanel(this);
        this.getContentPane().add(mainPanel);
  
        this.setVisible(true);
    }
}

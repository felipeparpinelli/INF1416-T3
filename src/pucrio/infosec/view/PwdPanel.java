/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class PwdPanel extends JPanel{
    
    private JLabel loginLabel;
    
    public PwdPanel ()
    {
        loginLabel = new JLabel("PWD!");
        this.add(loginLabel);
    }
    
}

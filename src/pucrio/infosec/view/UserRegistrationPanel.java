/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class UserRegistrationPanel extends JPanel implements ActionListener{
    
    private JFrame mainFrame;
    private JLabel loginLabel;
    private JLabel groupLabel;
    private JLabel nameLabel;
    private JLabel totalUserLabel;
    private JLabel formLabel;
    
    private JLabel loginText;
    private JLabel groupText;
    private JLabel nameText;
    private JLabel totalUserText;
    
    public UserRegistrationPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(500, 500);
        this.mainFrame.validate();
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        nameLabel = new JLabel("Nome: ");
        totalUserLabel = new JLabel("Total de usuarios no sistema: ");
        formLabel = new JLabel("Formulario de cadastro: ");
        
        loginText = new JLabel("{login}");
        groupText = new JLabel("{Grupo}");
        nameText = new JLabel("{Descricao}");
        totalUserText = new JLabel("{Total de acessos do usuario}");
        
        this.add(loginLabel);
        this.add(loginText);
        
        this.add(groupLabel);
        this.add(groupText);
        
        this.add(nameLabel);
        this.add(nameText);
        
        this.add(totalUserLabel);
        this.add(totalUserText);
        
        this.add(formLabel);  
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

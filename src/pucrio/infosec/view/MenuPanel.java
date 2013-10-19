/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Felipe
 */
public class MenuPanel extends JPanel implements ActionListener{
   
    private JFrame mainFrame;
    private JLabel loginLabel;
    private JLabel groupLabel;
    private JLabel descriptionLabel;
    private JLabel accessLabel;
    private JLabel menuLabel;
    
    private JLabel loginText;
    private JLabel groupText;
    private JLabel descriptionText;
    private JLabel accessText;
    
    private JButton userRegButton;
    private JButton getUserFolderButton;
    private JButton ExitButton;
    
    public MenuPanel (JFrame mainframe)
    {
        this.mainFrame = mainframe;
        this.mainFrame.setSize(500, 500);
        this.mainFrame.validate();
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        descriptionLabel = new JLabel("Descricao: ");
        accessLabel = new JLabel("Total de acessos do usuario: ");
        menuLabel = new JLabel("Menu Principal: ");
        
        loginText = new JLabel("{login}");
        groupText = new JLabel("{Grupo}");
        descriptionText = new JLabel("{Descricao}");
        accessText = new JLabel("{Total de acessos do usuario}");
        
        userRegButton = new JButton("Cadastrar um novo usuario");
        getUserFolderButton = new JButton("Consultar pasta de arquivos secretos de um usuario");
        ExitButton = new JButton("Sair do Sistema");
        
        this.add(loginLabel);
        this.add(loginText);
        
        this.add(groupLabel);
        this.add(groupText);
        
        this.add(descriptionLabel);
        this.add(descriptionText);
        
        this.add(accessLabel);
        this.add(accessText);
        
        this.add(menuLabel);  
        
        this.add(userRegButton);
        this.add(getUserFolderButton);
        this.add(ExitButton);
        
        userRegButton.addActionListener(this);
        getUserFolderButton.addActionListener(this);
        ExitButton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            case "Cadastrar um novo usuario":
                UserRegistrationPanel userRegPanel = new UserRegistrationPanel(mainFrame);
                mainFrame.setContentPane(userRegPanel);
                mainFrame.repaint();
                mainFrame.validate();
                break;
            case "Consultar pasta de arquivos secretos de um usuario":
                break;
            default:
                break;
            
        }
    }
    
}

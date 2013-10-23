/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pucrio.infosec.controller.LoginController;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.dao.UserDao;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class MainPanel extends JPanel implements ActionListener{
    
    private JButton loginButton;
    private JLabel loginLabel;
    private JTextField loginText;
    private JFrame mainFrame;

    @SuppressWarnings("LeakingThisInConstructor")
    public MainPanel (JFrame mainFrame)
    {
        RegistryDao.storeRegistry(2001);
        this.mainFrame = mainFrame;
        loginLabel = new JLabel("Login:");
        loginText = new JTextField(15);
        loginButton = new JButton("Logar");
        
        this.setLayout(new FlowLayout());
        this.add(loginLabel);
        this.add(loginText);
        this.add(loginButton);
        loginButton.addActionListener(this);
    }
    
    public JTextField getLoginText() {
        return loginText;
    }

    public void setLoginText(JTextField loginText) {
        this.loginText = loginText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Check Login Button");
        String login = this.getLoginText().getText();
        LoginController loginController = new LoginController(login);
        
        if(loginController.checkLogin(this) == true)
        {
            PwdPanel pwdPanel = new PwdPanel(mainFrame);
            
            mainFrame.setContentPane(pwdPanel);
            mainFrame.repaint();
            mainFrame.validate();
        }
        else{
            User user = UserDao.searchUser(login);
            if(user != null)
            {
                if(user.isBlocked())
                JOptionPane.showMessageDialog(mainFrame, "Usuário bloqueado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(mainFrame, "Login inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }
}

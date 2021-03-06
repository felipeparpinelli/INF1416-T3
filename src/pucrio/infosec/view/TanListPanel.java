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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pucrio.infosec.controller.PwdController;
import pucrio.infosec.controller.TanListController;
import pucrio.infosec.dao.OneTimePasswordDao;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.helpers.Auth;
import pucrio.infosec.model.OneTimePassword;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class TanListPanel extends JPanel implements ActionListener{
    
    private JFrame mainFrame;
    private JLabel messageLabel;
    private JLabel tanKeyLabel;
    private JButton tanButton;
    private JTextField tanText;
    private int passKey;
    
    public TanListPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(450, 500);
        this.mainFrame.validate();
        messageLabel = new JLabel("Tan List - Digite a respectiva senha para chave \n");
        OneTimePassword pass = OneTimePasswordDao.getRandomUnusedByUser(Auth.getInstance().getCurrentUser().getId());
        this.passKey = pass.getKey();
        String key = String.valueOf(this.passKey+1);
        tanKeyLabel = new JLabel(key);
        tanText = new JTextField(10);
        tanButton = new JButton("Enviar");
        tanButton.addActionListener(this);
        
        this.add(messageLabel);
        this.add(tanKeyLabel);
        this.add(tanText);
        this.add(tanButton);
        RegistryDao.storeRegistry(4001, Auth.getInstance().getCurrentUser().getLogin());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    TanListController tanListController = new TanListController();

    if(tanListController.isInputCorrect(this.passKey, tanText.getText()) == true)
    {
        MenuPanel menuPanel = new MenuPanel(mainFrame);

        mainFrame.setContentPane(menuPanel);
        mainFrame.repaint();
        mainFrame.validate();
    }
    else
    {
        
        User user = Auth.getInstance().getCurrentUser();
        if(!user.isBlocked()){
            JOptionPane.showMessageDialog(mainFrame, "Tan incorreto, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
            TanListPanel tanListPanel = new TanListPanel(mainFrame);
            mainFrame.setContentPane(tanListPanel);
            mainFrame.repaint();
            mainFrame.validate();
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Tan incorreto, usuário bloqueado", "Erro", JOptionPane.ERROR_MESSAGE);
            MainPanel mainPanel = new MainPanel(mainFrame);
            mainFrame.setContentPane(mainPanel);
            mainFrame.repaint();
            mainFrame.validate();
        }
    }
    }
    
}

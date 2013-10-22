/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.helpers.Auth;

/**
 *
 * @author Felipe
 */
public class ExitPanel extends JPanel implements ActionListener{
    
    private JFrame mainFrame;
    private JLabel loginLabel;
    private JLabel groupLabel;
    private JLabel nameLabel;
    private JLabel accessLabel;
    
    private JLabel loginText;
    private JLabel groupText;
    private JLabel nameText;
    private JLabel accessText;
    private JLabel exitLabel;
    private JLabel exitMessageLabel;
    
    private JButton exitButton;
    private JButton backButton;
    public List<String> listaDeArq;
    
    public ExitPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        
        listaDeArq = new ArrayList<String>();
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        nameLabel = new JLabel("Nome: ");
        accessLabel = new JLabel("Total de acessos do usuario: ");
        
       
        loginText = new JLabel("{login}");
         loginText.setText(Auth.getInstance().getCurrentUser().getLogin());
        
        groupText = new JLabel("{Grupo}");
        groupText.setText(Auth.getInstance().getCurrentUser().getGroupName().toString());

        nameText = new JLabel("{Nome}");
        nameText.setText(Auth.getInstance().getCurrentUser().getName());
        accessText = new JLabel("{Total de acessos do usuario}");
        
        exitLabel = new JLabel("Saida do sistema:");
        exitMessageLabel = new JLabel("Pressione o Botão Sair para apagar os arquivos decriptados e \n" +
"encerrar o sistema.");
        
        exitButton = new JButton("Sair");
        exitButton.addActionListener(this);
        backButton = new JButton("Voltar para o menu");
        backButton.addActionListener(this);
        
        this.add(loginLabel);
        this.add(loginText);
        
        this.add(groupLabel);
        this.add(groupText);
        
        this.add(nameLabel);
        this.add(nameText);
        
        this.add(accessLabel);
        this.add(accessText);
        
        this.add(exitLabel);
        this.add(exitMessageLabel);
        
        this.add(exitButton);
        this.add(backButton);
        RegistryDao.storeRegistry(9001, Auth.getInstance().getCurrentUser().getLogin());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Sair":
                RegistryDao.storeRegistry(9002, Auth.getInstance().getCurrentUser().getLogin());
                List<String> array = new ArrayList<>();
                RegistryDao.storeRegistry(1002, Auth.getInstance().getCurrentUser().getLogin());
                for (String path : Auth.getInstance().getPaths()){              
                    File file = new File("C:\\certs\\" + path);
                    file.delete();
                    RegistryDao.storeRegistry(9004, Auth.getInstance().getCurrentUser().getLogin());
                }
                System.exit(0);
                break;
            case "Voltar para o menu":
                RegistryDao.storeRegistry(9003, Auth.getInstance().getCurrentUser().getLogin());
                MenuPanel menuPanel = new MenuPanel(mainFrame);
                mainFrame.setContentPane(menuPanel);
                mainFrame.repaint();
                mainFrame.validate();
                break;
            default:
                break;
        }
    }
    
}

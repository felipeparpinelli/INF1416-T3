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
    
    public ExitPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        nameLabel = new JLabel("Nome: ");
        accessLabel = new JLabel("Total de acessos do usuario: ");
        
        loginText = new JLabel("{login}");
        groupText = new JLabel("{Grupo}");
        nameText = new JLabel("{Nome}");
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
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Sair":
                System.exit(0);
                break;
            case "Voltar para o menu":
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

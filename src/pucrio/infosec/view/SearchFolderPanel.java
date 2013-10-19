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
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Felipe
 */
public class SearchFolderPanel extends JPanel implements ActionListener{
    
    private JFrame mainFrame;
    private JLabel loginLabel;
    private JLabel groupLabel;
    private JLabel nameLabel;
    private JLabel totalSearchLabel;
    
    private JLabel loginText;
    private JLabel groupText;
    private JLabel nameText;
    
    private JLabel pathPulicKeyLabel;
    private JLabel pathPrivateKeyLabel;
    private JLabel passphraseLabel;
    private JLabel folderPathLabel;
    
    private JTextField pathPulicKeyText;
    private JTextField pathPrivateKeyText;
    private JTextField passphraseText;
    private JTextField folderPathText;
    
    private JLabel totalSearchText;
    
    private JButton loadKeyButton;
    private JButton listButton;
    private JButton backButton;
    
    private JTable filesList;
   
    public SearchFolderPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        nameLabel = new JLabel("Nome: ");
        totalSearchLabel = new JLabel("Total de consultas do usuario: ");
        
        loginText = new JLabel("{login}");
        groupText = new JLabel("{Grupo}");
        nameText = new JLabel("{Nome}");
        
        totalSearchText = new JLabel("{Total de consultas do usuario}");
        pathPulicKeyLabel = new JLabel("Caminho da chave publica: ");
        pathPrivateKeyLabel = new JLabel("Caminho da chave privada: ");
        passphraseLabel = new JLabel("Frase secreta da chave privada: ");
        folderPathLabel = new JLabel("Caminho da pasta de arquivos: ");
        
        filesList = new JTable();
        
        pathPulicKeyText = new JTextField(30);
        pathPrivateKeyText = new JTextField(30);
        passphraseText = new JTextField(30);
        folderPathText = new JTextField(30);
        
        loadKeyButton = new JButton("Carregar chave");
        loadKeyButton.addActionListener(this);
        listButton = new JButton("Listar");
        listButton.addActionListener(this);
        backButton = new JButton("Voltar para o menu");
        backButton.addActionListener(this);
        
        this.add(loginLabel);
        this.add(loginText);
        this.add(groupLabel);
        this.add(groupText);
        this.add(nameLabel);
        this.add(nameText);
        this.add(totalSearchLabel);
        this.add(totalSearchText);
        
        this.add(pathPulicKeyLabel);
        this.add(pathPulicKeyText);
        this.add(pathPrivateKeyLabel);
        this.add(pathPrivateKeyText);
        this.add(passphraseLabel);
        this.add(passphraseText);
        
        this.add(loadKeyButton);
        
        this.add(folderPathLabel);
        this.add(folderPathText);
        
        this.add(filesList);
        
        this.add(listButton);
        this.add(backButton);
  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Carregar chave":
                System.out.println("carregar chave clicado.");
                break;
            case "Listar":
                System.out.println("Listar clicado.");
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

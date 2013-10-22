/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import pucrio.infosec.controller.KeyCheckController;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import static java.util.Collections.list;
import javax.swing.JOptionPane;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.helpers.Auth;
import sun.misc.IOUtils;

/**
 *
 * @author Felipe
 */
public class SearchFolderPanel extends JPanel implements ActionListener {

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
    private java.awt.List list;
    private JTable jtable;
    private DefaultListModel model;
    public List<String> listaDeArq;

    public SearchFolderPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.setSize(700, 700);

        List<String> listaDeArq = new ArrayList<>();
        
        loginLabel = new JLabel("Login: ");
        groupLabel = new JLabel("Grupo: ");
        nameLabel = new JLabel("Nome: ");
        totalSearchLabel = new JLabel("Total de consultas do usuario: ");

        loginText = new JLabel("{login}");
        loginText.setText(Auth.getInstance().getCurrentUser().getLogin());
       
        groupText = new JLabel("{Grupo}");
        groupText.setText(Auth.getInstance().getCurrentUser().getGroupName().toString());
        
        nameText = new JLabel("{Nome}");
        nameText.setText(Auth.getInstance().getCurrentUser().getName());
        

        totalSearchText = new JLabel("{Total de consultas do usuario}");
        pathPulicKeyLabel = new JLabel("Caminho da chave publica: ");
        pathPrivateKeyLabel = new JLabel("Caminho da chave privada: ");
        passphraseLabel = new JLabel("Frase secreta da chave privada: ");
        folderPathLabel = new JLabel("Caminho da pasta de arquivos: ");


        list = new java.awt.List() ;
        JScrollPane jscroll = new JScrollPane(list);
        jscroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });

        list.setVisible(false);

        String[] colunas = new String[]{"Estado", "Cidade"};
        String[][] dados = new String[][]{
            {"SP", "Sao Paulo"},
            {"RJ", "Rio de Janeiro"},
            {"RN", "Rio Grande do Norte"},
            {"PR", "Parana"}
        };
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        jtable = new JTable(modelo);
        jtable.setSize(400, 200);
        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtable.setVisible(false);

        pathPulicKeyText = new JTextField(30);
        pathPrivateKeyText = new JTextField(30);
        passphraseText = new JTextField(30);
        folderPathText = new JTextField(30);
        folderPathText.setEditable(false);

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

        this.add(listButton);
        this.add(backButton);

        this.add(list);
        RegistryDao.storeRegistry(8001, Auth.getInstance().getCurrentUser().getLogin());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Carregar chave":
                KeyCheckController keyCheck = new KeyCheckController(pathPrivateKeyText.getText(), passphraseText.getText(), pathPulicKeyText.getText());
                try {

                    boolean isValidKey = keyCheck.checkPrivateKey();
                    if (isValidKey == true) {
                        folderPathText.setEditable(true);
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SignatureException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Listar":
                
                
                KeyCheckController listFiles = new KeyCheckController(pathPrivateKeyText.getText(), pathPulicKeyText.getText(), folderPathText.getText(), "");
                String isValid = listFiles.getIndexContent(passphraseText.getText());
                        
                model = new DefaultListModel();

                List<String> array = null;
                try {
                    array = removerNl(isValid);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for (int i = 0; i<array.size(); i++)
                {
                    String n = array.get(i);
                    list.add(n);
                }

                for (int i = 0; i < array.size(); i++) {
                    model.add(i, array.get(i));
                }

               this.add(list);
                list.setVisible(true);
                list.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                KeyCheckController listFiles = new KeyCheckController(pathPrivateKeyText.getText(), pathPulicKeyText.getText(), folderPathText.getText(), "");
                JOptionPane.showConfirmDialog(groupText, list.getSelectedItem());
                String [] a = list.getSelectedItem().split(" ");
                String nomeSecreto = a[0];
                String nomeFake = a[1];
                Auth.getInstance().addPath(nomeSecreto);
                RegistryDao.storeRegistryWithFile(8003, Auth.getInstance().getCurrentUser().getLogin(), nomeSecreto);
                byte[] isValid = listFiles.getFileContent(passphraseText.getText(), nomeFake);
                
                System.out.println(isValid);
                try {
                    try (FileOutputStream output = new FileOutputStream(folderPathText.getText() + nomeSecreto)) {
                        output.write(isValid);
                    } 
                    
                } catch (IOException ex) {
                    Logger.getLogger(SearchFolderPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
                mainFrame.repaint();
                mainFrame.validate();

                System.out.println(isValid);

                break;
            case "Voltar para o menu":
                RegistryDao.storeRegistry(8002, Auth.getInstance().getCurrentUser().getLogin());
                MenuPanel menuPanel = new MenuPanel(mainFrame);
                mainFrame.setContentPane(menuPanel);
                mainFrame.repaint();
                mainFrame.validate();
                break;
            default:
                break;

        }
    }

    public List<String> removerNl(String frase) throws FileNotFoundException, IOException {
        List<String> listArray = new ArrayList<>();
        StringBuffer str = new StringBuffer();
        String[] resultados = frase.split("\n");
        for (int x = 0; x < resultados.length; x++) {
            listArray.add(resultados[x]);
            System.out.println(listArray.get(x));
        }

        return listArray;
    }
    
}

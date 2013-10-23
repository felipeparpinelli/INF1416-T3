/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import pucrio.infosec.controller.UserRegistrationController;
import pucrio.infosec.model.GroupName;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class UserRegistrationPanel extends JPanel implements ActionListener {

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
    private JLabel nameRegLabel;
    private JLabel loginRegLabel;
    private JLabel groupRegLabel;
    private JLabel pwdRegLabel;
    private JLabel confirmPwdRegLabel;
    private JLabel pathTanRegLabel;
    private JLabel lengthTanRegLabel;
    private JTextField nameRegText;
    private JTextField loginRegText;
    private JComboBox groupRegText;
    private JPasswordField pwdRegText;
    private JPasswordField confirmPwdRegText;
    private JTextField pathTanRegText;
    private JTextField lengthTanRegText;
    private JButton regButton;
    private JButton backButton;
    String[] Groups = {"Selecione", GroupName.ADMINISTRATOR.toString(), GroupName.USER.toString()};

    public UserRegistrationPanel(JFrame mainFrame) {
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
        nameText = new JLabel("{Nome}");
        totalUserText = new JLabel("{Total de usuarios no sistema}");

        nameRegLabel = new JLabel("Nome do Usuário: ");
        loginRegLabel = new JLabel("Login Name: ");
        groupRegLabel = new JLabel("Grupo: ");
        pwdRegLabel = new JLabel("Senha pessoal: ");
        confirmPwdRegLabel = new JLabel("Confirmacao da senha pessoal: ");
        pathTanRegLabel = new JLabel("Caminho da TAN List: ");
        lengthTanRegLabel = new JLabel("Tamanho da TAN List: ");

        nameRegText = new JTextField(30);
        loginRegText = new JTextField(20);
        groupRegText = new JComboBox(Groups);
        pwdRegText = new JPasswordField(6);
        confirmPwdRegText = new JPasswordField(6);
        pathTanRegText = new JTextField(30);
        lengthTanRegText = new JTextField(2);

        regButton = new JButton("Cadastrar");
        regButton.addActionListener(this);
        backButton = new JButton("Voltar para o menu");
        backButton.addActionListener(this);

        this.add(loginLabel);
        this.add(loginText);
        this.add(groupLabel);
        this.add(groupText);
        this.add(nameLabel);
        this.add(nameText);
        this.add(totalUserLabel);
        this.add(totalUserText);
        this.add(formLabel);

        this.add(nameRegLabel);
        this.add(nameRegText);
        this.add(loginRegLabel);
        this.add(loginRegText);
        this.add(groupRegLabel);
        this.add(groupRegText);
        this.add(pwdRegLabel);
        this.add(pwdRegText);
        this.add(confirmPwdRegLabel);
        this.add(confirmPwdRegText);
        this.add(pathTanRegLabel);
        this.add(pathTanRegText);
        this.add(lengthTanRegLabel);
        this.add(lengthTanRegText);

        this.add(regButton);
        this.add(backButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cadastrar":
                
                UserRegistrationController userReg = new UserRegistrationController();
                if(userReg.isValidPwd(pwdRegText.getText()) && userReg.checkLogin(loginRegText.getText()) && userReg.isEqualPwd(pwdRegText.getText(), confirmPwdRegText.getText()) && confirmPwdRegText.getText() != null && pwdRegText.getText() != null && loginRegText.getText() != null && nameRegText.getText() != null && !groupRegText.getSelectedItem().toString().equals("Selecione") && pathTanRegText.getText() != null && lengthTanRegText.getText() != null)
                {
                    User user = new User();
                    user.setName(nameRegText.getText());
                    user.setLogin(loginRegText.getText());
                    user.setPwd(pwdRegText.getText());
                    if(groupRegText.getSelectedItem().toString().equals("ADMINISTRATOR"))
                       user.setGroupName(GroupName.ADMINISTRATOR);
                    else
                       user.setGroupName(GroupName.USER);
                    
                    user.setTanPath(pathTanRegText.getText());
                    user.setTanPath(lengthTanRegText.getText());
                    
                    userReg.createTanList(pathTanRegText.getText(), lengthTanRegText.getText(), loginRegText.getText());
                    userReg.saveUser(user);
                    mainFrame.setContentPane(new UserRegistrationPanel(mainFrame));
                    mainFrame.repaint();
                    mainFrame.validate();
                }
                /*
                else
                {
                    UserRegistrationPanel userRegPanel = new UserRegistrationPanel(mainFrame);
                    mainFrame.setContentPane(userRegPanel);
                    mainFrame.repaint();
                    mainFrame.validate();
                }
                */
                
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

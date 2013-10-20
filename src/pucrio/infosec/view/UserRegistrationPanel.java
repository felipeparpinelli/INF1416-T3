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
import javax.swing.JTextField;
import pucrio.infosec.controller.UserRegistrationController;
import pucrio.infosec.model.GroupName;

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
    private JTextField pwdRegText;
    private JTextField confirmPwdRegText;
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
        pwdRegText = new JTextField(6);
        confirmPwdRegText = new JTextField(6);
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
                userReg.isValidPwd(pwdRegText.getText());
                userReg.checkLogin(loginRegText.getText());
                userReg.isEqualPwd(pwdRegText.getText(), confirmPwdRegText.getText());
                userReg.createTanList(pathTanRegText.getText(), lengthTanRegText.getText(), loginRegText.getText());
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pucrio.infosec.controller.PwdController;

/**
 *
 * @author Felipe
 */
public class PwdPanel extends JPanel implements ActionListener {

    private JButton[] buttons;
    private JLabel messageLabel;
    private JFrame mainFrame;
    private JLabel pwdField;
    private BorderLayout layout;
    String[] pwdArray = new String[12];
    int idx = 0;
    List<String> pwdList = shuffle();
    int clickCount;

    public PwdPanel(JFrame mainFrame) {

        layout = new BorderLayout(5, 5);
        
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(450, 500);
        this.mainFrame.validate();
        messageLabel = new JLabel("Atenção: digite a sua senha eletrônica\n"
                + "no teclado abaixo:");
        
        pwdField = new JLabel("");
        
        buttons = new JButton[5];
        
        
        for (int count = 0; count < 5; count++) {
            buttons[count] = new JButton();
            buttons[count].addActionListener(this);
        }
        
        this.clickCount = 0;
           
        int i = 0;
        for (int count = 0; count < 5; count++) {
            
            buttons[count].setText(pwdList.get(i) + " ou " + pwdList.get(i + 1));
            i = i + 2;
        }

        this.add(messageLabel);
        
        this.add(buttons[0], BorderLayout.NORTH);
        this.add(buttons[1], BorderLayout.SOUTH);
        this.add(buttons[2], BorderLayout.EAST);
        this.add(buttons[3], BorderLayout.WEST);
        this.add(buttons[4], BorderLayout.CENTER);
        
        this.add(pwdField, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {       
        
        this.clickCount++;
        this.pwdField.setText(pwdField.getText() + "*");
        pwdArray[idx] = e.getActionCommand();
        idx++;
        
        int i = 0;
        this.pwdList = shuffle();
        for (int count = 0; count < 5; count++) {
            
            buttons[count].setText(this.pwdList.get(i) + " ou " + this.pwdList.get(i + 1));
            i = i + 2;
        }
        
        if (clickCount > 5) {
            for (int count = 0; count < 5; count++)
                buttons[count].setEnabled(false);
            
            PwdController pwdController = new PwdController(pwdArray);
            boolean checkPwd = pwdController.checkPwd();
            
            if(checkPwd == true)
            {
                TanListPanel tanPanel = new TanListPanel(mainFrame);
                
                mainFrame.setContentPane(tanPanel);
                mainFrame.repaint();
                mainFrame.validate();
            }
            else
            {
                PwdPanel pwdPanel = new PwdPanel(mainFrame);
                mainFrame.setContentPane(pwdPanel);
                mainFrame.repaint();
                mainFrame.validate();
            }
        }
    }

    public static List<String> loadPwdList() {
        List<String> pwdList = new ArrayList<>();
        pwdList.add("0");
        pwdList.add("1");
        pwdList.add("2");
        pwdList.add("3");
        pwdList.add("4");
        pwdList.add("5");
        pwdList.add("6");
        pwdList.add("7");
        pwdList.add("8");
        pwdList.add("9");

        return pwdList;
    }

    public static List<String> shuffle() {
        List<String> pwdList = loadPwdList();
        Collections.shuffle(pwdList);

        return pwdList;
    }
}

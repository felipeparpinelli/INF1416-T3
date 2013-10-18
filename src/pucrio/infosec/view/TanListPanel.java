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
import javax.swing.JTextField;

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
    
    public TanListPanel (JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(450, 500);
        this.mainFrame.validate();
        messageLabel = new JLabel("Tan List - Digite a respectiva senha para chave: \n");
        tanKeyLabel = new JLabel("12345");
        tanText = new JTextField(10);
        tanButton = new JButton("Enviar");
        tanButton.addActionListener(this);
        
        this.add(messageLabel);
        this.add(tanKeyLabel);
        this.add(tanText);
        this.add(tanButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (this.tanText.getText().equals("12345"));
        {
            MenuPanel menuPanel = new MenuPanel(mainFrame);
                
            mainFrame.setContentPane(menuPanel);
            mainFrame.repaint();
            mainFrame.validate();
        }
    }
    
}

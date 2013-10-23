/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import pucrio.infosec.dao.RegistryDao;
import pucrio.infosec.dao.UserDao;
import pucrio.infosec.model.Message;
import pucrio.infosec.model.Registry;
import pucrio.infosec.model.User;

/**
 *
 * @author Felipe
 */
public class LoggerFrame extends JFrame{
    
    private LoggerPanel loggerPanel;
    private JTable logList;
    
   // final Future<Boolean> future= LoggerFrame.updateData(new Callable<Boolean>() {         
     //       @Override
      //      public Boolean call() throws Exception {
        //        // get ws result
           //        return getWSResponse();
         //   }
           // });
    
    public LoggerFrame ()
    {
        this.setTitle("LogView");
        this.setLocation(580, 200);
        this.setSize(700, 600);    
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loggerPanel = new LoggerPanel();
        this.getContentPane().add(loggerPanel);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Data");
        model.addColumn("Mensagem");
        this.logList = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(this.logList);
        this.logList.setFillsViewportHeight(true);
        scrollPane.setVisible(true);
        
        this.logList.setVisible(true);      
        this.add(scrollPane);
        
        updateData(model);
        this.setVisible(true);
        
        while(true)
        {
            updateData(model);
            try {
                Thread.sleep(3300);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoggerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateData(DefaultTableModel model) {
        ArrayList<Registry> entries = RegistryDao.searchEntries();
        ArrayList<Object[]> objects = new ArrayList<Object[]>();
        for (Registry entry : entries)
        {
            User user = UserDao.searchUserById(entry.getUserId());
            String message;
            if(entry.getUserId() != 0)
            {
                message = Message.parseMessage(entry.getMessageId(), user.getLogin(), null);
            }
            else{
                message = Message.parseMessageWithLogin(entry.getMessageId(), entry.getLogin(), null);
            }          
            objects.add(new Object[]{entry.getDate(), message});
        }
        
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }

        for (Object[] data : objects)
        {
            model.addRow(data);
        }
        
    }
}
 

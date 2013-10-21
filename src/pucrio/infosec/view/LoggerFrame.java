/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrio.infosec.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
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
import pucrio.infosec.model.Registry;

/**
 *
 * @author Felipe
 */
public class LoggerFrame extends JFrame{
    
    private LoggerPanel loggerPanel;
    private JTable logList;
    
    public LoggerFrame ()
    {
        this.setTitle("LogView");
        this.setLocation(580, 200);
        this.setSize(800, 500);    
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loggerPanel = new LoggerPanel();
        this.getContentPane().add(loggerPanel);
        DefaultTableModel model = new DefaultTableModel(); 
        model.addColumn("Data");
        model.addColumn("Usuario");
        model.addColumn("Mensagem");
        model.addColumn("Arquivo");
        this.logList = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(this.logList);
        this.logList.setFillsViewportHeight(true);
        scrollPane.setVisible(true);
        
        this.logList.setVisible(true);      
        this.add(scrollPane);
        
        DefaultTableModel thisModel = (DefaultTableModel) this.logList.getModel();
        
        for (Object[] data : this.updateData())
        {
            model.addRow(data);
        }
        
        this.setVisible(true);
    }

    private List<Object[]> initialData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ArrayList<Object[]> updateData() {
        List<Registry> entries = RegistryDao.searchEntriesBeforeDate(Calendar.getInstance().getTime());
        ArrayList<Object[]> objects = new ArrayList<Object[]>();
        for (Registry entry : entries)
        {
            objects.add(new Object[]{entry.getDate(), entry.getUserId(), entry.getMessageId(), entry.getArquivo()});
        }
        
        return objects;
    }
    
}

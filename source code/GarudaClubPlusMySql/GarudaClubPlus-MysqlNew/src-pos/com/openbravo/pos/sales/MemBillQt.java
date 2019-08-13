/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import static com.openbravo.pos.sales.MemBillQt1.getWindow;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author USER
 */
public class MemBillQt  extends javax.swing.JDialog {

    private Object bean;
    private MemBillModel1 fxd_table1;
    private AppView m_App;
    private boolean resultok = false;
    private AppView app;
    private boolean flag;
      
  //  private MemBillModel1 fxd_table1;
   
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
      private static DataLogicCustomers dlCustomers;
     //  private List<MemBillQt1.BillQtList> list;
       private DataLogicFacilities dlfac;
        // private DataLogicSales dlSales = null;
        private  DataLogicSystem dlsystem;
       private DataLogicSales dlSales;
       private  DataLogicReceipts dlReceipts; 
        private  MemBillModel biimdl; 
        private int QT_Bill_Flag;
         private List<MemBillModel1.QtData> list;
         MemberBillDetails dp = new MemberBillDetails();
    private Date sdate;
    private Date edate;
    private String id;
       MemBillTableModel.QtData    showdata;
                 /**
     * Creates new customizer MemBillQt
     */
    
    
     public MemBillQt() {
        initComponents();
        dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        
    }
   
    
    public void setObject(Object bean) {
        this.bean = bean;
    }

     
      public MemBillQt(Frame frame, AppView app, boolean flag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public MemBillQt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
    }
 public MemBillQt(java.awt.Dialog parent,DataLogicSales dlSales,AppView app, boolean flag ,String id) {
        super(parent, true);
       
        this.app = app;
        this.dlSales =dlSales;
        this.flag = flag;
       this.id=id;
        
    }
    
     public MemBillQt(java.awt.Frame parent,DataLogicSales dlSales,  AppView app, boolean flag ,String id) {
        super(parent, true);
       this.dlSales =dlSales;
        this.app = app;
        this.flag = flag;
         this.id=id;
     
        
    }
  
          public static MemBillQt getDialog(Component parent, DataLogicSales dlSales,  AppView app, boolean flag ,String id) {

        Window window = getWindow(parent);

        MemBillQt mybilllogic;

        if (window instanceof Frame) {
            mybilllogic = new MemBillQt((Frame) window, dlSales, app, flag , id);
        } else {
            mybilllogic = new MemBillQt((Dialog) window, dlSales, app, flag ,id);
        }

        return mybilllogic;
    }
     public boolean showDialog() throws BeanFactoryException {
        try {
            init(showdata);
            setVisible(true);
            return resultok;
        } catch (BasicException ex) {
            Logger.getLogger(MemBillQt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
      protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }
     
       
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jDialog4 = new javax.swing.JDialog();
        jDialog5 = new javax.swing.JDialog();
        jDialog6 = new javax.swing.JDialog();
        jDialog7 = new javax.swing.JDialog();
        jDialog8 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog6Layout = new javax.swing.GroupLayout(jDialog6.getContentPane());
        jDialog6.getContentPane().setLayout(jDialog6Layout);
        jDialog6Layout.setHorizontalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog6Layout.setVerticalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog7Layout = new javax.swing.GroupLayout(jDialog7.getContentPane());
        jDialog7.getContentPane().setLayout(jDialog7Layout);
        jDialog7Layout.setHorizontalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog7Layout.setVerticalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog8Layout = new javax.swing.GroupLayout(jDialog8.getContentPane());
        jDialog8.getContentPane().setLayout(jDialog8Layout);
        jDialog8Layout.setHorizontalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog8Layout.setVerticalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Qt Detail");

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "QtDate", "Qt No.", "Item", "Qty", "Remark"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setText("Bill No.");

        jTextField1.setEditable(false);

        jLabel2.setText("Date");

        jTextField2.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel7)
                .addGap(0, 644, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(159, 159, 159)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(17, 17, 17))
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JDialog jDialog6;
    private javax.swing.JDialog jDialog7;
    private javax.swing.JDialog jDialog8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

//////////////////////////////////////////////////////////////////////////
    


   public void init(MemBillTableModel.QtData showdata) throws BeanFactoryException, BasicException {
         m_App = app;
          initComponents();
           this.app = app;
       this.showdata = showdata;
        AppView app = LookupUtilityImpl.getInstance(null).getAppView();
        DataLogicSystem dlSystem = (DataLogicSystem) app.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        TicketParser m_TTP = new TicketParser(app.getDeviceTicket(), LookupUtilityImpl.getInstance(null).getDataLogicSystem());

       
            jTable3.setVisible(true);
        fxd_table1 = MemBillModel1.GetQt44(m_App, showdata.getId());
            jTable3.setModel(fxd_table1.getTableModel());
          //  jTable2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            TableColumnModel cmodel = jTable3.getColumnModel();
             cmodel.getColumn(0).setPreferredWidth(100);
             cmodel.getColumn(0).setPreferredWidth(100);
            cmodel.getColumn(1).setPreferredWidth(50);
            cmodel.getColumn(1).setPreferredWidth(50);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(2).setPreferredWidth(100);
            cmodel.getColumn(3).setPreferredWidth(50);
            cmodel.getColumn(3).setPreferredWidth(50);
             cmodel.getColumn(4).setPreferredWidth(100);
            cmodel.getColumn(4).setPreferredWidth(100);
           
           jTextField1.setText(showdata.getId());
           jTextField2.setText(showdata.getCreatedDate());
       
      
      }
     
     private void loadData() throws BasicException {
         
          
        
     }

   
     
       public static class QtData implements SerializableRead{
// private String SrNo;
 //   private String Memno;
   
 
    ///////////////////////////////////////////////////////////////////////
      private Timestamp CrDate;
     private String id;
     private String name;
      private Double Dmultiply;
       private Properties m_attributes = new Properties();
     
 
         
           public String getCrdDate(){
               String crddate=Formats.TIMESTAMP.formatValue(CrDate);
              return crddate;
           }

        public void setCreatedDate(Timestamp CrDate) {
            this.CrDate = CrDate;
        }
          public String getid() {
           //  id = Memname;
            return id;
        }

        public void settid(String id) {
            this.id = id;
        }
         public String getname() {
           //  id = Memname;
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }
           public double getDmultiply() {
            return Dmultiply;
        }
   public void setDmultiply(double Dmultiply) {
            this.Dmultiply = Dmultiply;
        }
   
    public String getProperty(String key) {
        return m_attributes.getProperty(key);
    }

    public String getProperty(String key, String defaultvalue) {
        return m_attributes.getProperty(key, defaultvalue);
    }

    public void setProperty(String key, String value) {
        m_attributes.setProperty(key, value);
    }

    public void setProperties(Properties attributes) {
        m_attributes = attributes;
    }
     public String getRemarks() {
        return m_attributes.getProperty("dp.remarks");
    }

    public void setRemarks(String value) {
        m_attributes.setProperty("dp.remarks", value);
    }

        /////////////////////////////////////////////////////////////
        
        
        @Override
        public void readValues(DataRead dr) throws BasicException {
             //Memno=dr.getString(1);
             CrDate=dr.getTimestamp(1);
             name = dr.getString(2);
             id = dr.getString(3);
          Dmultiply =dr.getDouble(4);
          try {
            byte[] img = dr.getBytes(5);
            if (img != null) {
                m_attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
            //TODO logging
        }
        
        }
    
}   
     
     
     
     
     
}
    




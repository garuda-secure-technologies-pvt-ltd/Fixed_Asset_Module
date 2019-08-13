/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class DivisionalAccountMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp {

    /**
     * Creates new form DivisionalAccountMaster
     */
    
    private AppView m_App;
    private DataLogicFacilities dlfac;
    private Session s;
    private int flag = 1;
    private List<DivisionMasterBean> dmbList;
    
    private final static String[] TABLEHEADERS = {"Name","Active","No. of Accounts Linked", "Cr By", "Cr Date"};
    private final static String[] TABLEHEADERS1 = {"Name","Active","No. of Accounts Linked", "Cr By", "Cr Date", "Deac By", "Deac Date"};
    
    
    public DivisionalAccountMaster() {
        initComponents();
    }
    
     public void init(AppView app) throws BeanFactoryException {
          m_App = app;
            dlfac = (DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
            s=m_App.getSession();
         //To change body of generated methods, choose Tools | Templates.
    }
     
     
    public void activate() throws BasicException {
        reset();
        loadMaster();
        jTable1.setModel(getTableModel());
         //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        edit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jLabel1.setText("Division Name");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Save Changes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        id.setText("jLabel2");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(id)))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(id)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jButton3.setVisible(false);
        id.setVisible(false);
        jLabel2.setVisible(false);

        jTabbedPane1.addTab("Create New", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        jButton2.setText("Deactivate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit)
                    .addComponent(jButton2))
                .addGap(0, 52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List View", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String divName =  dName.getText();
        if(divName!=null && divName.length()>1)
        {
            try {
                Object obj[] = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM DIVISIONALACCOUNTMASTER WHERE NAME=? and active = true", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(divName);
                                if (obj == null) 
                                {
                                 
                                    Object[] value = new Object[]{UUID.randomUUID().toString(), divName, true, m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost()};
                                  int i =   new PreparedSentence(m_App.getSession(), "INSERT INTO DIVISIONALACCOUNTMASTER(ID, NAME, ACTIVE, CRBY, CRDATE, CRHOST) VALUES (?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(value);
                        //sanj
                                  if(i>0)
                                  {
                                       JOptionPane.showMessageDialog(this, "Saved Successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
                                       reset();
                                  }
                                    
                                    
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this, "Entered Division name already exist.", "Div Name Exist", JOptionPane.ERROR_MESSAGE);
                                }
                                     
            } catch (BasicException ex) {
                Logger.getLogger(DivisionalAccountMaster.class.getName()).log(Level.SEVERE, null, ex);
             new MessageInf(ex).show(getParent());
            }
                            
            
            
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Division name cannot be blank", "Enter Division Name", JOptionPane.ERROR_MESSAGE);
        }
        
        loadMaster();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
         
        jTable1.setModel(getTableModel());
        
        
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
       int row = jTable1.getSelectedRow();
       if(row!=-1)
       {
           jButton3.setVisible(true);
           jButton1.setVisible(false);
           DivisionMasterBean dmb = dmbList.get(row);
           dName.setText(dmb.getName());
           id.setText(dmb.getId());
           jLabel2.setText(dmb.getName());
           jTabbedPane1.setSelectedIndex(0);
       }
       else
       {
           JOptionPane.showMessageDialog(this, "Please select a row then press Edit button", "Select Row", JOptionPane.PLAIN_MESSAGE);
       }
    }//GEN-LAST:event_editActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if(!dName.getText().equals(jLabel2.getText()))
        {
            try {
                int i =   new PreparedSentence(m_App.getSession(), "UPDATE DIVISIONALACCOUNTMASTER SET NAME=? WHERE ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[] {dName.getText(), id.getText()});
                if(i>0)
                {
                    JOptionPane.showMessageDialog(this, "Saved Successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
                                       activate();
                }   //sanj
            } catch (BasicException ex) {
                Logger.getLogger(DivisionalAccountMaster.class.getName()).log(Level.SEVERE, null, ex);
                 new MessageInf(ex).show(getParent());
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No changes to save", "Save", JOptionPane.PLAIN_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();
        if(row!=-1)
        {
            DivisionMasterBean dmb = dmbList.get(row);
            
            if(dmb.isActive())
            {
            if(dmb.getLinkedCount() > 0)
            {
                JOptionPane.showConfirmDialog(this, "There are "+dmb.getLinkedCount()+ " accounts linked with this devision. Please Deactivate all the links and then deactivate this division", "Deactivate", JOptionPane.WARNING_MESSAGE);
              /*  if()
                {
                    try {
                        new PreparedSentence(m_App.getSession(), "UPDATE divisionalaccountlink SET ACTIVE = FALSE,DEACBY=?, DEACDATE = ? ,DEACHOST=? WHERE DIVID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[] {m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), dmb.getId()});
                        new PreparedSentence(m_App.getSession(), "UPDATE DIVISIONALACCOUNTMASTER SET ACTIVE = FALSE ,DEACBY=?,DEACDATE = ? , DEACHOST=? WHERE ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[] {m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), dmb.getId()});
                      JOptionPane.showMessageDialog(this, "Deactivate successfully", "Deactivate successfully", JOptionPane.PLAIN_MESSAGE);
                      activate();
                    } catch (BasicException ex) {
                        Logger.getLogger(DivisionalAccountMaster.class.getName()).log(Level.SEVERE, null, ex);
                        new MessageInf(ex).show(getParent());
                    }
                
                }*/
            }
            else
            {
                try {
                    new PreparedSentence(m_App.getSession(), "UPDATE DIVISIONALACCOUNTMASTER SET ACTIVE = FALSE , DEACBY=? , DEACDATE = ? , DEACHOST=? WHERE ID = ?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP, Datas.STRING, Datas.STRING})).exec(new Object[] {m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), dmb.getId()});
                    
            
            JOptionPane.showMessageDialog(this, "Deactivate successfully", "Deactivate successfully", JOptionPane.PLAIN_MESSAGE);
             activate();
                } catch (BasicException ex) {
                    Logger.getLogger(DivisionalAccountMaster.class.getName()).log(Level.SEVERE, null, ex);
                     new MessageInf(ex).show(getParent());
                }
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Already Deactivated", "Already Deactivated", JOptionPane.PLAIN_MESSAGE);
        }
        }
        else
        {
            
            JOptionPane.showMessageDialog(this, "Please select a row then press Deactivate button", "Select Row", JOptionPane.PLAIN_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dName;
    private javax.swing.JButton edit;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void loadMaster()
    {
        dmbList = new ArrayList<DivisionMasterBean>();
         try {
        
           
                dmbList = new StaticSentence(m_App.getSession()
                     ,"SELECT dam.ID, dam.NAME, dam.ACTIVE, dam.CRBY, dam.CRDATE, count(dal.divid) as totlinkcount from divisionalaccountmaster dam left join divisionalaccountlink dal on (dam.id = dal.divid and dal.active is true) group by dam.id"
                   ,SerializerWriteString.INSTANCE
                   ,new SerializerReadClass( DivisionMasterBean.class )).list();
         
        } catch (BasicException ex) {
                Logger.getLogger(DivisionalAccountMaster.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public String getTitle() {
        return "Divisional Account Master"; //To change body of generated methods, choose Tools | Templates.
    }


    public boolean deactivate() {
        return true;//To change body of generated methods, choose Tools | Templates.
    }

    public JComponent getComponent() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }

   

    public Object getBean() {
        return this; //To change body of generated methods, choose Tools | Templates.
    }
    
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                if(flag==1)
                  return AppLocal.getIntString(TABLEHEADERS[column]);
                else
                  return AppLocal.getIntString(TABLEHEADERS1[column]);
            }
            public int getRowCount() {
                return dmbList.size();
            }
            public int getColumnCount() {
                if(flag==1)
                 return TABLEHEADERS.length;
                else
                 return TABLEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                DivisionMasterBean l = dmbList.get(row);

                switch (column) {

                case 0: return l.getName();
               // case 1: return l.getrperiod();
               case  1:
                   if(l.isActive())
               {
                   return "Yes";
               }
                   else return "No";
                   
               case 2: return l.getLinkedCount();
                   
               case 3: return l.getCrBy();
                   
               case 4: 
                   
                   SimpleDateFormat sd  = new SimpleDateFormat("dd-MMM-yyyy");
                   return sd.format(l.getCrDate());
                   
                default: return null;
                }
            }
        };
    }

    private void reset() {
        dName.setText(null);
        jButton3.setVisible(false);
        id.setText(null);
        jLabel2.setText(null);
        jTabbedPane1.setSelectedIndex(0);
        jButton1.setVisible(true);
        
    }
    
    public static class DivisionMasterBean implements SerializableRead, IKeyed {
        
        private String id;
        private String name;
        private boolean active;
        private String crBy;
        private Date crDate;
        private String deacBy;
        private Date deacDate;
        private int linkedCount;
        

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            name = dr.getString(2);
            active = dr.getBoolean(3);
            crBy = dr.getString(4);
            crDate = dr.getTimestamp(5);
            linkedCount = dr.getInt(6);
            
             //To change body of generated methods, choose Tools | Templates.
        }

        public Object getKey() {
            return name; //To change body of generated methods, choose Tools | Templates.
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getCrBy() {
            return crBy;
        }

        public void setCrBy(String crBy) {
            this.crBy = crBy;
        }

        public Date getCrDate() {
            return crDate;
        }

        public void setCrDate(Date crDate) {
            this.crDate = crDate;
        }

        public String getDeacBy() {
            return deacBy;
        }

        public void setDeacBy(String deacBy) {
            this.deacBy = deacBy;
        }

        public Date getDeacDate() {
            return deacDate;
        }

        public void setDeacDate(Date deacDate) {
            this.deacDate = deacDate;
        }

        public int getLinkedCount() {
            return linkedCount;
        }

        public void setLinkedCount(int linkedCount) {
            this.linkedCount = linkedCount;
        }

        @Override
        public String toString() {
            return this.name; //To change body of generated methods, choose Tools | Templates.
        }
        
        
        
        
        
      
    }
}
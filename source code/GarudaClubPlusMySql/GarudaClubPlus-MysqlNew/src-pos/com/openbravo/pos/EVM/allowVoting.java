

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;
import javax.swing.table.JTableHeader;
import java.awt.Font;

public class allowVoting extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

   
    
    private AppView m_App;
    private VotingStatusTableModel VotingStatus_Table_Model;
     private VotingStatusTableModel VotingStatus_Table_Model2;
    public allowVoting() {
        initComponents();
    }


    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clublogo_label = new javax.swing.JLabel();
        poweredby_label = new javax.swing.JLabel();
        barcode_Text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }

                if (rowIndex==(jTable1.getRowCount() - 1)) {
                    jc.setBackground(Color.GREEN);
                }

                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jLabel27 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }

                if (rowIndex==(jTable2.getRowCount() - 1)) {
                    jc.setBackground(Color.GREEN);
                }

                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};

        barcode_Text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcode_TextKeyPressed(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(6, 42, 239));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(192, 14, 14));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(108, 12, 12));
        jLabel3.setText("Polling Booth Menu");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sr. No", "Polling Station", "Voting Slip Issued"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        }

        jLabel27.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(219, 32, 32));
        jLabel27.setText("Voting Slip Issued ");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(219, 23, 40));
        jLabel4.setText("Vote Cast");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(barcode_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel27)
                                        .addGap(166, 166, 166))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 213, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clublogo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(179, 179, 179)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clublogo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(poweredby_label, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(barcode_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))))
        );

        clublogo_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/clublogoevm.jpg"))); // NOI18N
        poweredby_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/poweredby.png"))); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/barcode.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 String Barcode = null;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
      /*  
        Barcode = barcode_Text.getText();
       
       try{
       
           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT BARCODE FROM evm_barcode where VOTECAST=0 AND BARCODE=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
           if(obj!=null){
               
               String BarcodeDB = obj[0].toString();
               if(Barcode.equals(BarcodeDB)){
               
                   
                   
      ///////////////////////////////////////////////////////////////////////////////////////////////////////              
                    
                      Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                              
                            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM evm_barcode where BARCODE=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
                            String BarcodeID = obj2[0].toString();   
                          
                          
                           @Override      
                           protected Object transact() throws BasicException { 
          
                               int x = new StaticSentence(m_App.getSession()
                                                , "UPDATE evm_barcode SET votecast=1 , VC_DATE=? , VC_HOST=? , PB=?  WHERE ID=?"
                                                , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.STRING  }))
                                                .exec(new Object[] { new Date() ,  m_App.getProperties().getHost() , m_App.getAppUserView().getUser().getId() , BarcodeID});
                             
                            
                               int x3 = new StaticSentence(m_App.getSession()
                                                , "DELETE  FROM evm_slip  WHERE BARCODE=?"
                                                , new SerializerWriteBasic(new Datas[] { Datas.STRING}))
                                                .exec(new Object[] { Barcode});
                               
                              
                              reset();   
                               loaddata();   
                             JOptionPane.showMessageDialog(null, " Vote Casted Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
               
  // ////////////////////////////////////////////////////////////////////////////            
               
               
               
               
               
               
               }
               else{
                    JOptionPane.showMessageDialog(jPanel1, "Already Voted. Please try another next.");
                    loaddata();
                    reset();
               }
               
           }
           else{
               
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT BARCODE FROM evm_barcode where VOTECAST=1 AND BARCODE=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
                if(obj3!=null){
                    JOptionPane.showMessageDialog(jPanel1, "Already Voted. Please try next.");
                    loaddata();
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(jPanel1, "Barcode Not Registered. Please generate voting slip first");
                    loaddata();
                    reset();
                }
           }
           
           
           
       
       
       }
       catch(BasicException e) {
           
       }
       */
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            loaddata();
        }
        catch(BasicException e){
            
        }
        
        
        barcode_Text.setText(null);
        barcode_Text.requestFocus();
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void barcode_TextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcode_TextKeyPressed
       if(barcode_Text.getText().trim().length()==9){
           
             Barcode = barcode_Text.getText();
       
       try{
       
           Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT BARCODE FROM evm_barcode where VOTECAST=0 AND BARCODE=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
           if(obj!=null){
               
               String BarcodeDB = obj[0].toString();
               if(Barcode.equals(BarcodeDB)){
               
                   
                   
      ///////////////////////////////////////////////////////////////////////////////////////////////////////              
                    
                      Transaction t = new Transaction(m_App.getSession()) {                                                                                     
                              
                            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT id FROM evm_barcode where BARCODE=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
                            String BarcodeID = obj2[0].toString();   
                          
                          
                           @Override      
                           protected Object transact() throws BasicException { 
          
                               int x = new StaticSentence(m_App.getSession()
                                                , "UPDATE evm_barcode SET votecast=1 , VC_DATE=? , VC_HOST=? , PB=?  WHERE ID=?"
                                                , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP , Datas.STRING ,Datas.STRING , Datas.STRING  }))
                                                .exec(new Object[] { new Date() ,  m_App.getProperties().getHost() , m_App.getAppUserView().getUser().getId() , BarcodeID});
                             
                            
                               int x3 = new StaticSentence(m_App.getSession()
                                                , "DELETE  FROM evm_slip  WHERE BARCODE=?"
                                                , new SerializerWriteBasic(new Datas[] { Datas.STRING}))
                                                .exec(new Object[] { Barcode});
                               
                              
                               
                              jButton2.setText("Vote Cast");
                              jButton2.setBackground(Color.GREEN);
                              reset();   
                              loaddata();   
                               
                               
                             JOptionPane.showMessageDialog(null, " Vote Casted Successfully ", "Success", JOptionPane.INFORMATION_MESSAGE);
                            
           
                            return null;                                      
                            }                            
                        };                 
                          
                     try {                 
                         t.execute();          
                            
                           
                            
                     }
                     catch (BasicException ex) {                    
                            Logger.getLogger(ContestantMaster.class.getName()).log(Level.SEVERE, null, ex);             
                            ex.printStackTrace();
                            new MessageInf(ex).show(new JFrame());
                            
                     }
               
  // ////////////////////////////////////////////////////////////////////////////            
               
               
               
               
               
               
               }
               else{
                    JOptionPane.showMessageDialog(jPanel1, "Already Voted.");
                    loaddata();
                    reset();
               }
               
           }
           else{
               
                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT BARCODE FROM evm_barcode where VOTECAST=1 AND BARCODE=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Barcode);
                if(obj3!=null){
                    JOptionPane.showMessageDialog(jPanel1, "Already Voted. Please try next.");
                    loaddata();
                    reset();
                }
                else{
                    JOptionPane.showMessageDialog(jPanel1, "Barcode Not Registered. Invalid voting slip");
                    loaddata();
                    reset();
                }
           }
           
           
           
       
       
       }
       catch(BasicException e) {
           
       }
           
           
           
       }
       else{
           if(barcode_Text.getText().trim().length()>9){
               barcode_Text.setText(null);
           }
       }
    }//GEN-LAST:event_barcode_TextKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode_Text;
    private javax.swing.JLabel clublogo_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel poweredby_label;
    // End of variables declaration//GEN-END:variables


    public String getTitle() {
       return "";
    }

    public void activate() throws BasicException {
        reset();
        loaddata();
       
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
       this.m_App = app;
     
       
      
    }

    public Object getBean() {
         return this;
    }

List<Object> PollingStationList = new ArrayList<Object>();
    public void loaddata() throws BasicException{
       
        
        
        barcode_Text.requestFocus();
        
        
       
        
        
      //  jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       // jTable1.getColumnModel().getColumn(0).setMaxWidth(15);
      //   jTable1.getColumnModel().getColumn(1).setMaxWidth(40);
       //  jTable1.getColumnModel().getColumn(2).setMaxWidth(40);
         
       //  jTable1.getColumnModel().getColumn(0).getCellRenderer(centerRenderer);
         
       VotingStatus_Table_Model = VotingStatusTableModel.LoadPollingStationInfo(m_App);
       jTable1.setModel(VotingStatus_Table_Model.getTableModel()); 
       
       
       VotingStatus_Table_Model2 = VotingStatusTableModel.LoadVoteCastedDetails(m_App);
       jTable2.setModel(VotingStatus_Table_Model2.getTableModel2()); 
       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );   
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer ); 
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer ); 
        jTable2.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable2.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable2.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(46);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(105);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(125);
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(46);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(136);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(110);
        
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 11));
        JTableHeader header2 = jTable2.getTableHeader();
        header2.setFont(new Font("Dialog", Font.BOLD, 11));
        
       
        
        
    }



  public void reset(){
    barcode_Text.setText(null);
    barcode_Text.requestFocus();
      
  }




}

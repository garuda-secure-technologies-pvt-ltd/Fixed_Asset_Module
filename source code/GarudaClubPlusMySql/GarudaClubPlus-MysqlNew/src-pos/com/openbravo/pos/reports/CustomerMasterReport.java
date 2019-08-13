/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerMasterReport.java
 *
 * Created on 31-Oct-2009, 13:23:54
 */

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.Accounts.ListModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.MemTypeTableModel;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author swathi
 */
public class CustomerMasterReport extends javax.swing.JPanel implements ReportEditorCreator {

    /** Creates new form CustomerMasterReport */
    private ComboBoxValModel cmodel;
    private DataLogicFacilities dlfac;
   // private ListModel lmodel;
    private PanelReportBean   report;
    private List<tableData> memtypelist;
    private List<tableData> memtypelistmain;
    private String sent;
    private String postfix;
    public CustomerMasterReport(PanelReportBean report) {
        initComponents();
        this.report=report;
    }

    public void init(AppView app) {
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        jLabel1.setText("Member Type");
        jLabel2.setText("With Address");
        jCheckBox1.setText("Yes");
        TableColumn col=jTable1.getColumnModel().getColumn(0);
        col.setPreferredWidth(10);
        col.setMaxWidth(10);
        sent="SELECT C.SEARCHKEY,C.NAME AS CNAME ,C.MOBILE,C.PHONE,C.ADDRESS,M.NAME AS MNAME FROM CUSTOMERS C JOIN MEMTYPE M ON C.MEMTYPE=M.ID";
        postfix=" ORDER BY M.NAME,C.SEARCHKEY";
    }

    public void activate() throws BasicException {
        List<MemTypeTableModel.MemTypeline> list=dlfac.getMemType();
        MemTypeTableModel.MemTypeline mem=new MemTypeTableModel.MemTypeline();
        mem.setID("ALL");
        mem.setName("ALL");
        list.add(0, mem);
        memtypelist=new ArrayList<tableData>();
        memtypelistmain=new ArrayList<tableData>();
        tableData t;
        for(MemTypeTableModel.MemTypeline mem1: list){
            t=new tableData(true, mem1.getname(),mem1.getid());
            memtypelist.add(t);
        }
        memtypelistmain.addAll(memtypelist);
        cmodel=new ComboBoxValModel(list);
        jComboBox1.setModel(cmodel);
        jComboBox1.setSelectedIndex(-1);

    }
    private class tableData{
       private boolean status;
       private String name;
       private String id;
       public tableData(boolean status, String name,String id) {
            this.status = status;
            this.name = name;
            this.id=id;
       }

       public String getName(){
         return name;
       }
       public String getID(){
         return id;
       }
       public boolean getStatus(){
          return status;
       }
       public void setSelected(boolean flag){
           status=flag;
       }
    }

    public SerializerWrite getSerializerWrite() {
       return new SerializerWriteBasic(
              new Datas[] {Datas.OBJECT, Datas.STRING});
    }

    public Component getComponent() {
       return this;
    }

    public Object createValue() throws BasicException {
        return new Object[] {
           QBFCompareEnum.COMP_NONE ,null
        };
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return memtypelist.size();
            }
            public int getColumnCount() {
             return 2;
            }
            boolean[] edit=new boolean[]{true,false};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
	               return edit[0];
           }
            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                if(columnIndex==0){
                    boolean value=Boolean.parseBoolean(aValue.toString());
                    memtypelist.get(rowIndex).setSelected(value);
                }
            }
            Class[] types = new Class [] {
                java.lang.Boolean.class,java.lang.String.class
            };
            @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public Object getValueAt(int row, int column) {
                tableData l = memtypelist.get(row);
                switch (column) {
                    case 0  : return l.getStatus();
                    case 1  : return l.getName();
                    default : return null;
                }
            }
        };
    }
  private String generateSentence(){
      String sentence=null;
      for(tableData m:memtypelist ){
          if(m.getStatus()==true){
           if(sentence==null){
              sentence=" WHERE MEMTYPE='"+m.getID()+"'";
           }else{
              sentence+=" OR MEMTYPE='"+m.getID()+"'";
           }
          }
      }
      if(sentence!=null)
          return sent+sentence+postfix;
      else
          return sent+postfix;
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        jCheckBox1.setText("jCheckBox1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, 0, 0, Short.MAX_VALUE)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 237, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(551, 551, 551))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jCheckBox1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 117, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jCheckBox1)))
            .add(jPanel1Layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 616, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
       if(jComboBox1.getSelectedIndex()!=-1){
            MemTypeTableModel.MemTypeline mem=(MemTypeTableModel.MemTypeline) jComboBox1.getSelectedItem();
            if(mem.getname().equals("ALL")){
                memtypelist=new ArrayList<tableData>();
                memtypelist.addAll(memtypelistmain);
            }else{
                memtypelist=new ArrayList<tableData>();
            }
       }
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables



}

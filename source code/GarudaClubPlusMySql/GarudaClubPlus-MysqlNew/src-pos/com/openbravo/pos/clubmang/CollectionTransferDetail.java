/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CollectionTransferDetail.java
 *
 * Created on Mar 27, 2009, 12:22:56 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class CollectionTransferDetail extends javax.swing.JDialog {
    private boolean resultok = false;
    private AppView m_App;
    private MoneyCollectionTableModel mcModel;
    /** Creates new form CollectionTransferDetail */
    public CollectionTransferDetail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    protected CollectionTransferDetail(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CollectionTransferDetail(Frame parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }

    public CollectionTransferDetail(Dialog parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window)parent;
        } else {
            return getWindow(parent.getParent());
        }
    }


    public static CollectionTransferDetail getDialog(Component parent,AppView app) {

        Window window = getWindow(parent);

        CollectionTransferDetail ct;

        if (window instanceof Frame) {
            ct = new CollectionTransferDetail((Frame) window,app);
        } else {
            ct = new CollectionTransferDetail((Dialog) window,app);
        }

        return ct;
    }
    @SuppressWarnings("empty-statement")
    private void cashtablemodel(String name[],String nos[]){
        Double amt[]=new Double[10];
        Double num[]=new Double[10];
        int i=0;
        Object[][] data=new Object[10][3];
        Double no=Double.parseDouble(nos[9]);
        for(i=0;i<name.length-1;i++){
         if(i<4){
             Double val=Double.parseDouble(nos[i]);
             amt[i]=Double.parseDouble(name[i]) * val;
             num[i]=val;
         }
         else if(i<name.length-1 && i!=4){
              Double val=Double.parseDouble(nos[i-1]);
            amt[i]=Double.parseDouble(name[i]) * val;
            num[i]=val;
         }
        }
        // data[1]={name[1],nos[1],amt[1]};
        amt[9]=Double.valueOf(nos[8]);
        amt[4]=Double.parseDouble(name[4]) * no;
        num[4]=no;
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {name[0],num[0],amt[0]},
                {name[1],num[1],amt[1]},
                {name[2],num[2],amt[2]},
                {name[3],num[3],amt[3]},
                {name[4],num[4],amt[4]},
                {name[5],num[5],amt[5]},
                {name[6],num[6],amt[6]},
                {name[7],num[7],amt[7]},
                {name[8],num[8],amt[8]},
                {name[9],num[9],amt[9]}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));

    }

    public  boolean showDialog(String id) throws BasicException {
      Object[] obj=(Object[]) new StaticSentence(m_App.getSession()
                                 ,"SELECT CASHDETAIL,CHEQUEDETAIL,CASHTOTAL,CHEQUETOTAL FROM COLLECTIONTRANSFER WHERE ID=?"
                                 ,SerializerWriteString.INSTANCE
                                 ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE})).find(id);
      if(obj!=null){
          String nos[]=new String[10];
          String[] name={"1000","500","100","50","20","10","5","2","1","Change"};
         if(obj[0]==null){
             nos=new String[]{"0","0","0","0","0","0","0","0","0","0"};
         }
         else{
             nos=obj[0].toString().split("#");
             if(nos.length<10){
               String temp=obj[0].toString()+"#0";
               nos=temp.split("#");
             }

         }
        if(obj[1]!=null){
        String chequeid[]=obj[1].toString().split("#");
        mcModel=MoneyCollectionTableModel.loadDetail(m_App, chequeid);
         jTable2.setModel(mcModel.getchequeTableModel1());
        }//else
           // mcModel.l
        cashtotal.setText(obj[2].toString());
        chequetotal.setText(obj[3].toString());
        cashtablemodel(name,nos);
       
      }
       setVisible(true);
      return resultok;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cashtotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        chequetotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail");
        setAlwaysOnTop(true);

        jLabel1.setText("Cash Detail");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Cheque Detail");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Total");

        jLabel4.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(441, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(294, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chequetotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(44, 44, 44)
                                            .addComponent(cashtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)))))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cashtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(chequetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cashtotal;
    private javax.swing.JTextField chequetotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}

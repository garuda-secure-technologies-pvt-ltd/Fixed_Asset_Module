/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FacilityDetailDialog.java
 *
 * Created on Jul 2, 2009, 6:14:02 PM
 */

package com.openbravo.pos.clubmang;
//package garudadbmanager;

/*import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JFrame;*/
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.TreePath;

/**
 *
 * @author swathi
 */
public class ProfileEditDetail extends javax.swing.JDialog {

    /** Creates new form FacilityDetailDialog */
    private AppView m_App;
    private String custid;
  /*
    private DataLogicFacilities dmang;
    private MembersFacilityTableModel.Facilityline detail;
    private CustomerInfo cinfo;*/
    private final static String[] HEADER = {"Select","Type","Old Value","New Value"};
    private final static String[] HEADER1 = {"Select","Name","Type","Old Value","New Value"};
    private List<String> lablelist;
    private List<String> newdata;
    private List<String> olddata;
    private List<Boolean> allowchange;
    private JTreeTable t;
    private DependentMain dm;
   /* private List<String> lablelist1;
    private List<String> newdata1;
    private List<String> olddata1;
    private List<Boolean> allowchange1;
    private List<String> deplist;*/
    private List<Dependents> list;
    public ProfileEditDetail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       // JPanel panel=new JPanel();
       // jScrollPane1.add(panel);
       // jButton1.setText("Allow");
        
    }
    protected ProfileEditDetail(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //jButton1.setText("Allow");
    }

   /* public FacilityDetailDialog(Frame parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }

    public FacilityDetailDialog(Dialog parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }*/
    public ProfileEditDetail(Frame parent,AppView app) {
        this(parent, true);
        this.m_App=app;
    }

    public ProfileEditDetail(Dialog parent,AppView app) {
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


 
    public static ProfileEditDetail getDialog(Component parent,AppView app) {

        Window window = getWindow(parent);

        ProfileEditDetail ct;

        if (window instanceof Frame) {
            ct = new ProfileEditDetail((Frame) window,app);
        } else {
            ct = new ProfileEditDetail((Dialog) window,app);
        }

        return ct;
    }

    public void showDialog(List labellist,List olddata,List newdata,List allow,String cid,List<Dependents> list){
        this.lablelist=labellist;
        this.olddata=olddata;
        this.newdata=newdata;
        this.allowchange=allow;
       /* this.lablelist1=labellist1;
        this.olddata1=olddata1;
        this.newdata1=newdata1;
        this.allowchange1=allow1;
        this.deplist=deplist;*/
        this.list=list;
        this.custid=cid;
        jTable1.setModel(getTableModel());
//        jTable2.setModel(getDepTableModel());
        /*DependentsData d=new DependentsData("a", "aa", "aaa", "aaa", true);
        List<DependentsData> dlist=new ArrayList<DependentsData>();
        dlist.add(d);
        Dependents d1=new Dependents("bb", "bb", "bb", true, dlist);
        List<Dependents> dl=new ArrayList<Dependents>();
        dl.add(d1);
        d1=new Dependents("cc", "cc", "cc", true, dlist);
        dl.add(d1);*/
        dm=new DependentMain(list);
        t=new JTreeTable(new treetable(dm));

        jScrollPane1.setViewportView(t);
        //this.repaint();
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
        t.revalidate();
        t.repaint();
     /*   JFrame frame = new JFrame("TreeTable");
	JTreeTable treeTable = new JTreeTable(new FileSystemModel());

	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent we) {
		System.exit(0);
	    }
	});

	frame.getContentPane().add(new JScrollPane(treeTable));
	frame.pack();
	frame.show();*/

        
        TableColumn col=jTable1.getColumnModel().getColumn(0);
        col.setPreferredWidth(10);
        col=jTable1.getColumnModel().getColumn(1);
        col.setPreferredWidth(100);
        col=jTable1.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);
        col=jTable1.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        setVisible(true);
       // TableColumn column=jTable1.getColumnModel().getColumn(0);
       // column.setCellEditor(new DefaultCellEditor(new JCheckBox()));
    }
   
   public class DependentMain{
      private List<Dependents> ddata;
      private boolean allow=true;
      public DependentMain(List d){
       ddata=d;
      }
      public List<Dependents> getDataList(){
           return ddata;
        }
      public boolean isallowed(){
        return allow;
      }
      public void setallowed(boolean status){
         allow=status;
      }
        @Override
      public String toString(){
           return null;
        }
    }

  
    private class treetable extends AbstractTreeTableModel implements TreeTableModel{

        public int getColumnCount() {
           return HEADER1.length;
        }

        public treetable(Object data) {
            //for(Object o:data)
            
            super(data);
        }

        public String getColumnName(int column) {
            return HEADER1[column];
        }
         Class[] types = new Class [] {
                java.lang.Boolean.class, TreeTableModel.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false,false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }


        @Override
            public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
            }
        @Override
        public void setValueAt(Object aValue, Object node, int column){
           boolean flag=false;
           try{
              if(aValue!=null && column==0){
                  flag=Boolean.valueOf(String.valueOf(aValue));
                  if(node instanceof Dependents){
                    Dependents d=(Dependents)node;
                    d.setallowed(flag);
                    for(DependentsData d2:d.getDataList()){
                         d2.setallowed(flag);
                    }
                  }else if(node instanceof DependentsData){
                     DependentsData d=(DependentsData)node;
                     d.setallowed(flag);
                  } else if(node instanceof DependentMain){
                      DependentMain d=(DependentMain)node;
                        d.setallowed(flag);
                        for(Dependents d1:d.getDataList()){
                           d1.setallowed(flag);
                         for(DependentsData d2:d1.getDataList()){
                          d2.setallowed(flag);
                         }
                        }
                  }
               //   fireTreeNodesChanged(node, types, childIndices, types);
                  t.getTreeTableModel().tableValueChanged();
                  
                 }

          }catch(Exception e){
            e.printStackTrace();
          }
        }
        public Object getValueAt(Object node, int column) {
            try{
                if(node instanceof Dependents){
                   Dependents d=(Dependents)node;
                     switch(column){
                      case 0:return d.isallowed();
                      case 1:return d.getName();
                      case 2:return d.getType();
                      default:return null;
                    }
                }else if(node instanceof DependentsData){
                    DependentsData d=(DependentsData)node;
                    switch(column){
                        case 0:return d.isAllowed();
                        case 1:return d.getField();
                        case 2:return d.getOldValue();
                        case 3:return d.getNewValue();
                        case 4:return d.getNewValue();
                        default:return null;
                    }
                }else if(node instanceof DependentMain){
                    DependentMain dm=(DependentMain)node;
                        if(column==0)
                          return dm.isallowed();
                        else if(column==1)
                          return "Select All";
                        else
                          return null;
                }else
                    return null;

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        public Object getChild(Object parent, int index) {
            try{
               DependentMain dm=(DependentMain) parent;
               return dm.getDataList().get(index);
            }catch(Exception ex){
            try{
              Dependents d=(Dependents) parent;
              return d.getDataList().get(index);
            }catch(Exception e){
                return null;
            }
            //return null;
            }
        }

        public int getChildCount(Object parent) {
            try{
               DependentMain dm=(DependentMain) parent;
               return dm.getDataList().size();
            }catch(Exception ex){
            try{
              Dependents d=(Dependents) parent;
              return d.getDataList().size();
            }catch(Exception e){
                return 0;
            }
            //return null;
            }
        }

       
    }
     public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADER[column]);
            }

            public int getRowCount() {
                return lablelist.size();
            }
            public int getColumnCount() {

                return HEADER.length;
            }
             Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true,false, false, false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
           @Override
           public void setValueAt(Object aValue, int row, int column) {
               if(aValue!=null && column==0)
                allowchange.set(row, Boolean.parseBoolean(aValue.toString()));
           }
           public Object getValueAt(int row, int column) {
                switch (column) {
                    case 0 : return allowchange.get(row);
                    case 1 : return lablelist.get(row);
                    case 2 : return olddata.get(row);
                    case 3 : return newdata.get(row);
                  default: return null;
                }
            }
        };
    }
    /* public AbstractTableModel getDepTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADER1[column]);
            }

            public int getRowCount() {
                return deplist.size();
            }
            public int getColumnCount() {

                return HEADER1.length;
            }
             Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class,  java.lang.String.class,java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true,false, false, false,false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
           @Override
           public void setValueAt(Object aValue, int row, int column) {
               if(aValue!=null && column==0)
                allowchange1.set(row, Boolean.parseBoolean(aValue.toString()));
           }
           public Object getValueAt(int row, int column) {
                switch (column) {
                    case 0 : return allowchange1.get(row);
                    case 1 : return deplist.get(row);
                    case 2 : return lablelist1.get(row);
                    case 3 : return olddata1.get(row);
                    case 4 : return newdata1.get(row);
                  default: return null;
                }
            }
        };
    }*/

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.cyan);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.lightGray);
            }
            return c;
        }};
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        allow = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        allow1 = new javax.swing.JButton();
        reject = new javax.swing.JButton();
        reject1 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("Member Profile Data");

        jLabel2.setText("Member Dependent Data");

        allow.setText("Allow");
        allow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allowActionPerformed(evt);
            }
        });

        allow1.setText("Allow");
        allow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allow1ActionPerformed(evt);
            }
        });

        reject.setText("Reject");
        reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectActionPerformed(evt);
            }
        });

        reject1.setText("Reject");
        reject1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reject1ActionPerformed(evt);
            }
        });

        delete.setText("Delete");

        delete1.setText("Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                        .addGap(82, 82, 82))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(488, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                        .addContainerGap(82, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allow, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reject, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(463, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                                .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(allow1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reject1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(82, 82, 82))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allow)
                    .addComponent(reject)
                    .addComponent(delete))
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allow1)
                    .addComponent(reject1)
                    .addComponent(delete1))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void allowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allowActionPerformed
        //{"TaxID","SearchKey","Name","Card","FirstName","LastName","Email","Phone(O)","Phone(R)","Fax","Address","Address2","Postal","City","Region","Country","Son of/Wife of","Mobile","Date Of Birth"};
        if(JOptionPane.showConfirmDialog(this, "Do you want to allow the changes ?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            try {
             Transaction t=new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        String temp = null;
                        String temp1=null;
                        String lable;
                        int i = 0;
                        for (boolean status : allowchange) {
                         if (status == true) {
                          lable = lablelist.get(i);
                         if (lable.equals("Phone(O)")) {
                            lable = "PHONE";
                         } else if (lable.equals("Phone(R)")) {
                            lable = "PHONE2";
                         } else if (lable.equals("Son of/Wife of")) {
                            lable = "SOWO";
                         } else if (lable.equals("Date Of Birth")) {
                            lable = "DOB";
                         }
                         if (temp == null) {
                            temp = lable.toUpperCase() + "='" + newdata.get(i) + "'";
                         }else
                            temp += ","+lable.toUpperCase() + "='" + newdata.get(i) + "'";
                          //A indicated changes accepted
                          if(temp1==null)
                                temp1=lablelist.get(i)+" : "+olddata.get(i)+" : "+newdata.get(i)+" : (A)";
                            else
                                temp1+=" # "+lablelist.get(i)+" : "+olddata.get(i)+" : "+newdata.get(i)+" : (A)";
                         }else{
                             //R indicates changes rejected
                            if(temp1==null)
                                temp1=lablelist.get(i)+" : "+olddata.get(i)+" : "+newdata.get(i)+"(R)";
                            else
                                temp1+=" # "+lablelist.get(i)+" : "+olddata.get(i)+" : "+newdata.get(i)+"(R)";
                         }
                        i++;
                      }
                      int count1=new PreparedSentence(m_App.getSession(), "UPDATE MEMPROFILEEDIT SET CONFIRMEDBY=?,CHANGEDDATA=? WHERE CONFIRMEDBY IS NULL"
                       , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getId(),temp1});
                      if(temp!=null && count1>0)
                        new PreparedSentence(m_App.getSession(), "UPDATE CUSTOMERS SET " + temp + " WHERE ID=?", SerializerWriteString.INSTANCE).exec(custid);
                    return null;
                 }

                };
                t.execute();
                
            } catch (BasicException ex) {
                Logger.getLogger(ProfileEditDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_allowActionPerformed

    private void allow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allow1ActionPerformed
        
}//GEN-LAST:event_allow1ActionPerformed

    private void rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rejectActionPerformed

    private void reject1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reject1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reject1ActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allow;
    private javax.swing.JButton allow1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton reject;
    private javax.swing.JButton reject1;
    // End of variables declaration//GEN-END:variables

}

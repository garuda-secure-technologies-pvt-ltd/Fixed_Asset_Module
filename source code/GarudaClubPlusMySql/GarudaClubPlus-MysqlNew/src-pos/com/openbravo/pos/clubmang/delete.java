/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * delete.java
 *
 * Created on Apr 8, 2009, 4:43:19 PM
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountReports;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.CloseFinancialYear;
import com.openbravo.pos.DataMangement.Archiving;
import com.openbravo.pos.clubmang.purchasejournalEditorTableModel.PurchasejournalTable;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DriverWrapper;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.lang.Object;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonModel;
import javax.swing.ComponentInputMap;
import javax.swing.DefaultButtonModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author swathi
 */
public class delete extends javax.swing.JPanel implements JPanelView,BeanFactoryApp,FocusListener{

    /** Creates new form delete */
    private StringBuffer data;
    private AppView m_app;
    private DataLogicFacilities dmang;
    public delete() {
        initComponents();
    }
    public String getTitle() {
       
        return null;
    }

    public void activate() throws BasicException {
        textField.setAction(new UpdateAnchorAction(table));
        SelectionListener listener = new SelectionListener(table, textField);
        table.getSelectionModel().addListSelectionListener(listener);
    //table.setc
        table.addFocusListener(this);
   // table.addKeyListener(listener);
        table.setSurrendersFocusOnKeystroke(true);
        table.getColumnModel().getSelectionModel()
        .addListSelectionListener(listener);
        Action b4action=new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "works");
            }
        };
        table.getModel().addTableModelListener(new MyTableModelListener(table, textField));
        //ActionMap map=jButton4.getActionMap();
        KeyStroke key=KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
        //map.put(key, jButton4.);
        ComponentInputMap map=new ComponentInputMap(jButton4);
        map.put(key, "b4actionperformed");
        map.setParent(jButton4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));

        jButton4.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map);
        jButton4.getActionMap().put("b4actionperformed", b4action);

        //DefaultButtonModel.

    // Create a frame and add both components to the frame
   /* JFrame frame = new JFrame();
    frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    frame.getContentPane().add(textField, BorderLayout.NORTH);
    frame.pack();
    frame.setVisible(true);*/
    }

    public void focusGained(FocusEvent e) {
        Object del=e.getSource();
       if(e.getSource()==table){
            int row=table.getSelectedRow();
            int column=table.getSelectedColumn();
            String de=table.getValueAt(row, column).toString();
            data.append(de);
       }
    }

    public void focusLost(FocusEvent e) {
       // this.setVisible(false);
    }
  /*  public class PercentRenderer implements FocusListener {
        public PercentRenderer(){
        }
        public void focusGained(FocusEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void focusLost(FocusEvent e) {
            
        }




}*/

    public class SelectionListener implements ListSelectionListener,KeyListener {
        JTable table;
        JTextField textField;

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener(JTable table, JTextField textField) {
            this.table = table;
            this.textField = textField;
        }

        // Update the text field whenever the anchor cell changes
        public void valueChanged(ListSelectionEvent e) {
            int rowIndex = table.getSelectionModel().getAnchorSelectionIndex();
            int vColIndex = table.getColumnModel().getSelectionModel()
                .getAnchorSelectionIndex();

            // Get the value and set the text field
            textField.setText((String)table.getValueAt(rowIndex, vColIndex));
        }

        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyPressed(KeyEvent e) {
           String temp="";
        }

        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public class MyTableModelListener implements TableModelListener,KeyListener{
        JTable table;
        JTextField textField;

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        MyTableModelListener(JTable table, JTextField textField) {
            this.table = table;
            this.textField = textField;
        }
        public int toModel(JTable table, int vColIndex) {
        if (vColIndex >= table.getColumnCount()) {
            return -1;
        }
        return table.getColumnModel().getColumn(vColIndex).getModelIndex();
    }

    // Converts a column index in the model to a visible column index.
    // Returns -1 if the index does not exist.
    public int toView(JTable table, int mColIndex) {
        for (int c=0; c<table.getColumnCount(); c++) {
            TableColumn col = table.getColumnModel().getColumn(c);
            if (col.getModelIndex() == mColIndex) {
                return c;
            }
        }
        return -1;
    }


        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyPressed(KeyEvent e) {
           String temp="";
        }

        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // Update the text field whenever the value in the anchor cell changes
        public void tableChanged(TableModelEvent e) {
            // Get anchor cell location
            int rAnchor = table.getSelectionModel().getAnchorSelectionIndex();
            int vcAnchor = table.getColumnModel().getSelectionModel()
                .getAnchorSelectionIndex();

            // This method is defined in
            // e915 Converting a Column Index Between the View and Model in a JTable Component
            int mcAnchor = toModel(table, vcAnchor);

            // Get affected rows and columns
            int firstRow = e.getFirstRow();
            int lastRow = e.getLastRow();
            int mColIndex = e.getColumn();

            if (firstRow != TableModelEvent.HEADER_ROW
                    && rAnchor >= firstRow
                    && rAnchor <= lastRow
                    && (mColIndex == TableModelEvent.ALL_COLUMNS
                        || mColIndex == mcAnchor)) {
                // Set the text field with the new value
                textField.setText((String)table.getValueAt(rAnchor, vcAnchor));
            }
        }
    }

    public class UpdateAnchorAction extends AbstractAction {
        JTable table;
        UpdateAnchorAction(JTable table) {
            super("Set Anchor");
            this.table = table;
        }

        // Update the value in the anchor cell whenever the text field changes
        public void actionPerformed(ActionEvent evt) {
            JTextField textField = (JTextField)evt.getSource();

            // Get anchor cell location
            int rAnchor = table.getSelectionModel().getAnchorSelectionIndex();
            int vcAnchor = table.getColumnModel().getSelectionModel()
                .getAnchorSelectionIndex();

            table.setValueAt(textField.getText(), rAnchor, vcAnchor);
        }
    }



    

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
     m_app=app;
     dmang=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
     jButton2.setText("trans");
    }

    public Object getBean() {
       return this;
    }
   private void update(String skey,String nkey) throws BasicException{
     new PreparedSentence(m_app.getSession()
             , "UPDATE ACCOUNTMASTER SET SKEY=? WHERE SEARCHKEY=?",
             new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{nkey,skey});
   }
    private void update1(String skey,String nkey,String parent) throws BasicException{
     new PreparedSentence(m_app.getSession()
             , "UPDATE ACCOUNTMASTER SET SKEY=?,PARENT=? WHERE SEARCHKEY=?",
             new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).exec(new Object[]{nkey,parent,skey});
   }
     private void updateParent(int max,String parent) throws BasicException{
     new PreparedSentence(m_app.getSession()
             , "UPDATE ACCOUNTMASTER SET MAXIMUM=? WHERE SKEY=?",
             new SerializerWriteBasic(new Datas[]{Datas.INT,Datas.STRING})).exec(new Object[]{max,parent});
   }
   private Object getmax(String skey) throws BasicException{
       Object obj1=null;
      Object[] obj= (Object[])new StaticSentence(m_app.getSession()
             , "select maximum from accountmaster where skey=?",
             SerializerWriteString.INSTANCE,
            new SerializerReadBasic(new Datas[]{Datas.OBJECT})).find(skey);
      if(obj!=null){
        if(obj[0]!=null)
            obj1=obj[0];
      }
      return obj1;
   }
    private void getsubaccounts(AccountReports.TrailBalanceData main,String nkey,String skey)  throws BasicException{
         List<AccountReports.TrailBalanceData> acclistsubacc= new StaticSentence(m_app.getSession()
                           , " SELECT  AM2.ID,AM2.NAME, AM2.SEARCHKEY,AM2.OPENINGBALANCE,AM2.OPENINGBALANCE ,REPLACE(AM2.LEVEL_,5),AM2.LEVEL_,AM2.SUMMARY"+
                                " FROM ACCOUNTMASTER AM2 "+
                                " WHERE  AM2.LEVEL_='S' AND AM2.PARENT=? "+
                                " ORDER BY AM2.SEARCHKEY "
                           , new SerializerWriteBasic(new Datas[]{Datas.STRING})
                           , new SerializerReadClass(AccountReports.TrailBalanceData.class)).list(new Object[]{main.getSearchkey()});
                            //Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP // startdate,enddate,startdate,enddate, //AND A1.DATE>=? AND A1.DATE<=? //AND A1.DATE>=? AND A1.DATE<=?
                            for(AccountReports.TrailBalanceData breakd:acclistsubacc){
                                String search;
                                 Object max=getmax(nkey);
                                if(max==null){
                                   search=nkey+ ".1";
                                   updateParent(1, nkey);
                                 }
                                else{
                                      String del=nkey+"."+max.toString();

                                      String[] arr=del.split("\\.");
                                      int temp=Integer.parseInt(arr[arr.length-1])+1;
                                      arr[arr.length-1]= String.valueOf(temp);
                                      search=null;
                                      search=arr[0];
                                      for(int j=1;j<arr.length;j++){
                                        search+="."+arr[j];
                                      }
                                      updateParent(temp, nkey);
                                     // search.aparr.;
                                   }
                                update1(breakd.getSearchkey(), search,nkey);

                            }
    }
    private void breakdown(AccountReports.TrailBalanceData main,String nkey,String skey) throws BasicException{
          List<AccountReports.TrailBalanceData> acclistbreak= new StaticSentence(m_app.getSession()
                           ,  "  SELECT  AM2.ID,AM2.NAME,AM2.SEARCHKEY, AM2.OPENINGBALANCE,AM2.OPENINGBALANCE,REPLACE(AM2.LEVEL_,3),AM2.LEVEL_,AM2.SUMMARY "+
                                " FROM ACCOUNTMASTER AM2 "+
                                " WHERE  AM2.LEVEL_='D'  AND AM2.PARENT=?"+

                                " ORDER BY AM2.SEARCHKEY "
                           , SerializerWriteString.INSTANCE
                           , new SerializerReadClass(AccountReports.TrailBalanceData.class)).list(main.getSearchkey());
                           // breakdown();
                            getsubaccounts(main,nkey,skey);
                          for(AccountReports.TrailBalanceData breakd:acclistbreak){
                                String search;
                                 Object max=getmax(nkey);
                                if(max==null){
                                   search=nkey+ ".1";
                                   updateParent(1, nkey);
                                 }
                                else{
                                      String del=nkey+"."+max.toString();
                                      String arr[]=del.split("\\.");
                                       int temp=Integer.parseInt(arr[arr.length-1])+1;
                                      arr[arr.length-1]= String.valueOf(temp);
                                      search=null;
                                      search=arr[0];
                                      for(int j=1;j<arr.length;j++){
                                        search+="."+arr[j];
                                      }
                                      updateParent(temp, nkey);
                                   }
                                update1(breakd.getSearchkey(), search,nkey);
                              //   getsubaccounts(breakd);
                                  breakdown(breakd,search,breakd.getSearchkey());
                               /*  List<AccountReports.TrailBalanceData> acclistbreak1= new StaticSentence(m_app.getSession()
                                 , "  SELECT  AM2.ID,AM2.NAME,AM2.SEARCHKEY, AM2.OPENINGBALANCE,AM2.OPENINGBALANCE,REPLACE(AM2.LEVEL_,3),AM2.LEVEL_,AM2.SUMMARY "+
                                " FROM ACCOUNTMASTER AM2 "+
                                " WHERE  AM2.LEVEL_='D' AND AM2.PARENT=?"+
                                " ORDER BY AM2.NAME  "
                              , SerializerWriteString.INSTANCE
                              , new SerializerReadClass(AccountReports.TrailBalanceData.class)).list(breakd.getSearchkey());

                                 for(AccountReports.TrailBalanceData breakd1:acclistbreak1){

                                     breakdown(breakd1);

                                }*/
                            }

    }
    private void elements(AccountReports.TrailBalanceData ele,String nkey,String skey) throws BasicException{
         List<AccountReports.TrailBalanceData> acclistele1= new StaticSentence(m_app.getSession()
                           , "  SELECT  AM2.ID,AM2.NAME,AM2.SEARCHKEY, AM2.OPENINGBALANCE,AM2.OPENINGBALANCE,CASE WHEN AM2.PARENT IS NULL THEN REPLACE(AM2.LEVEL_,1) ELSE REPLACE(AM2.LEVEL_,2) END,AM2.LEVEL_,AM2.SUMMARY "+
                                " FROM ACCOUNTMASTER AM2 "+
                                " WHERE  AM2.LEVEL_='E' AND AM2.PARENT=?"+
                                " ORDER BY AM2.SEARCHKEY  "
                           , SerializerWriteString.INSTANCE
                           , new SerializerReadClass(AccountReports.TrailBalanceData.class)).list(ele.getSearchkey());

                    for(AccountReports.TrailBalanceData ele1:acclistele1){
                        String search;
                        Object max=getmax(nkey);
                       if(max==null){
                        search=nkey+ ".1";
                        updateParent(1, nkey);
                        }
                       else{
                         String del=nkey+"."+max.toString();
                        String arr[]=del.split("\\.");
                         int temp=Integer.parseInt(arr[arr.length-1])+1;
                                      arr[arr.length-1]= String.valueOf(temp);
                                      search=null;
                                      search=arr[0];
                                      for(int j=1;j<arr.length;j++){
                                        search+="."+arr[j];
                                      }
                                      updateParent(temp, nkey);
                       }
                        update1(ele1.getSearchkey(), search,nkey);
                        getsubaccounts(ele1,search,ele1.getSearchkey());
                        elements(ele1,search,ele1.getSearchkey());
                    }
                 List<AccountReports.TrailBalanceData> acclistmain= new StaticSentence(m_app.getSession()
                           , " SELECT  AM2.ID,AM2.NAME, AM2.SEARCHKEY,AM2.OPENINGBALANCE,AM2.OPENINGBALANCE,REPLACE(AM2.LEVEL_,5),AM2.LEVEL_,AM2.SUMMARY"+
                                " FROM ACCOUNTMASTER AM2 "+
                                " WHERE  AM2.LEVEL_='C' AND AM2.PARENT=? "+

                                " ORDER BY AM2.SEARCHKEY  "
                           , SerializerWriteString.INSTANCE
                           , new SerializerReadClass(AccountReports.TrailBalanceData.class)).list(ele.getSearchkey());

                //  acclistmain.addAll(acclistmain1);
               //  int i=0;
                  for(AccountReports.TrailBalanceData main:acclistmain){
                       String search;
                        Object max=getmax(nkey);
                       if(max==null){
                        search=nkey+ "."+"1";
                        updateParent(1, nkey);
                        }
                       else{
                        String del=nkey+"."+max.toString();
                        String arr[]=del.split("\\.");
                         int temp=Integer.parseInt(arr[arr.length-1])+1;
                                      arr[arr.length-1]= String.valueOf(temp);
                                      search=null;
                                      search=arr[0];
                                      for(int j=1;j<arr.length;j++){
                                        search+="."+arr[j];
                                      }
                                      updateParent(temp, nkey);
                       }
                        update1(main.getSearchkey(), search,nkey);
                       breakdown(main,search,main.getSearchkey());
                  }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setLayout(null);
        add(textField);
        textField.setBounds(210, 30, 200, 20);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        add(jScrollPane1);
        jScrollPane1.setBounds(90, 70, 452, 160);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(300, 260, 73, 23);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(430, 260, 140, 23);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(130, 270, 73, 23);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(10, 250, 110, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
             List<PurchasejournalTable> l=new PreparedSentence(m_app.getSession()
                      , "select total,tno,ino,dchallan,docref,vname,crdate,crby from (SELECT SUM(P.TOTAL) as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,V.ID as vname,P.CRDATE as crdate,P.CREATEDBY as crby FROM PURCHASEJOURNALMAIN P JOIN VENDOR V ON P.VENDOR=V.ID "
                      +" GROUP BY P.TNO,P.INVOICENO,P.DELIVERYCHALLAN,DOCUMENTREF,V.ID,P.CRDATE,P.CREATEDBY  "
                      +" UNION ALL SELECT SUM(P.TOTAL) as total,P.TNO as tno,P.INVOICENO as ino,P.DELIVERYCHALLAN as dchallan,DOCUMENTREF as docref,NULL as vname,P.CRDATE as crdate,P.CREATEDBY as crby FROM PURCHASEJOURNALMAIN P  WHERE P.VENDOR IS NULL "
                      +" GROUP BY P.TNO,P.INVOICENO,P.DELIVERYCHALLAN,DOCUMENTREF,P.CRDATE,P.CREATEDBY )AS DEL  order by crdate,tno"
                      , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                      , new SerializerReadClass(purchasejournalEditorTableModel.PurchasejournalTable.class)).list();
             for(PurchasejournalTable p1:l){
                 String id=UUID.randomUUID().toString();
                new PreparedSentence(m_app.getSession()
                        , "INSERT INTO PURCHASEJOURNALMAIN (ID,TNO,INVOICENO,DELIVERYCHALLAN,DOCUMENTREF,CREATEDBY,CRDATE,VENDOR,TOTAL) VALUES(?,?,?,?,?,?,?,?,?) "
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.DOUBLE})
                        ).exec(new Object[]{id,p1.getTNO(),p1.getInvoiceNo(),p1.getDeliveryChallan(),p1.getDocref(),p1.getCreatedby(),p1.getDate(),p1.getVendorName(),p1.getTotal()});
                 new PreparedSentence(m_app.getSession()
                        , "UPDATE PURCHASEJOURNAL SET PARENT=? WHERE TNO=? AND CRDATE=? "
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP})).exec(new Object[]{id,p1.getTNO(),p1.getDate()});
                  new PreparedSentence(m_app.getSession()
                        , "UPDATE ACCOUNTJOURNAL SET TID=? WHERE TRANSNO=? AND DATE=? AND NARRATION LIKE ?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{id,p1.getTNO(),p1.getDate(),"Purchase%"});
             }
           //if(l==null)
          /*  List<Object[]> plist = new PreparedSentence(m_app.getSession(), "SELECT P.ID,P.CATEGORY,C.PARENTID FROM PRODUCTS P JOIN CATEGORIES C ON P.CATEGORY=C.ID ", null, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).list();
            for(Object[] obj:plist){
                Object pdt=obj[0];
                Object cat=obj[1];
                Object Pcat=obj[2];
                while(Pcat!=null){
                  Object[] obj1=(Object[]) new PreparedSentence(m_app.getSession(), "SELECT C.ID,C.PARENTID FROM CATEGORIES C WHERE C.ID=? "
                            , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(Pcat);
                  Pcat=obj1[1];
                  cat=obj1[0];
                }
                 new PreparedSentence(m_app.getSession(), "UPDATE PDT_PRCAT SET CATEGORY=? WHERE ID=? "
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{cat,pdt});
            }*/
            /*   Date d=new Date();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            cal.set(Calendar.DATE, 1);
            cal.set(Calendar.MONTH, 3);
            cal.set(Calendar.YEAR, 2009);
            cal.set(Calendar.HOUR, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            cal.set(Calendar.MINUTE, 00);
            d.setTime(cal.getTimeInMillis());
            for(int i=0;!(cal.get(Calendar.DATE)==12 && cal.get(Calendar.MONTH)==10  );i++){
            try {
            String compdate = cal.get(Calendar.YEAR) + "-" ;
            String temp=(cal.get(Calendar.MONTH)+1)+"";
            if(temp.length()<2)
            compdate+="0"+temp+"-";
            else
            compdate+=temp+"-";
            temp= cal.get(Calendar.DATE)+"";
            if(temp.length()<2)
            compdate+="0"+temp+"%";
            else
            compdate+=temp+"%";
            int journal=1,contra=1,receipt=1,payment=1;
            List<String> slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Journal"});
            String jstring="",cstring="",rstring="",pstring="";
            for(String s:slist){
            if(!s.equals(jstring)){
            new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{journal+"",s});
            jstring=s;
            journal++;
            }
            }
            slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Contra"});
            for(String s:slist){
            if(!s.equals(cstring)){
            new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{contra+"",s});
            cstring=s;
            contra++;
            }
            }
            slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Receipt"});
            for(String s:slist){
            if(!s.equals(rstring)){
            new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{receipt+"",s});
            rstring=s;
            receipt++;
            }
            }
            slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Payments"});
            for(String s:slist){
            if(!s.equals(pstring)){
            new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{payment+"",s});
            pstring=s;
            payment++;
            }
            }
            new PreparedSentence(m_app.getSession(), "INSERT INTO vouchersequence (DATE,JOURNAL,CONTRA,RECEIPT,PAYMENTs) values (?,?,?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.INT,Datas.INT,Datas.INT,Datas.INT})).exec(new Object[]{d,journal-1,contra-1,receipt-1,payment-1});
            cal.add(Calendar.DATE, 1);
            d.setTime(cal.getTimeInMillis());
            } catch (BasicException ex) {
            ex.printStackTrace();
            }
            }//else{
            System.out.println("finish");*/
            //}
            //   try {
            // TODO add your handling code here
            /*   Date todate=new Date();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(todate.getTime());
            cal.set(Calendar.MONTH, 4);
            todate.setTime(cal.getTimeInMillis());
            Archiving.ArchiveQT(todate, m_app);*/
            /*   try{
            String temp="";
            List<Object[]> list=new StaticSentence(m_app.getSession()
            , "select s.id,c.productscn,c.nofst,nosec,s.units1 from stockdiary s,converter c where s.product1!='af65ec78-ec15-4fe2-afc6-1a221f35f973' and s.product1 not like 'f4fe43a5%' and s.product1=c.productscn and s.product!=c.productfst ",null,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE})).list();
            for(Object[] s:list){
            System.out.println(s[0]);
            System.out.println(s[1]);
            Double val1=Double.parseDouble(String.valueOf(s[4]));
            Double val2=Double.parseDouble(String.valueOf(s[3]));
            Double val3=Double.parseDouble(String.valueOf(s[2]));
            Double val= -1 * Double.parseDouble(String.valueOf(s[4]))*Double.parseDouble(String.valueOf(s[2]))/Double.parseDouble(String.valueOf(s[3]));
            new StaticSentence(m_app.getSession(), "update stockdiary set product=(SELECT C.PRODUCTFST from converter c where c.productscn=? and active=true),units=? where id=?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.DOUBLE,Datas.STRING})
            ).exec(new Object[]{s[1],val,s[0]});
            }
            //textField.setText(temp);
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
            e.printStackTrace();
            }*/
            /*  ClassLoader cloader = new URLClassLoader(new URL[]{new File("C:\\Documents and Settings\\swathi\\Desktop\\temp\\.\\lib\\hsqldb.jar").toURI().toURL()});
            DriverManager.registerDriver(new DriverWrapper((Driver) Class.forName("org.hsqldb.jdbcDriver",true,cloader).newInstance()));
            String sDBUser = "sa";
            String sDBPassword = "";
            //&& sDBPassword.startsWith("crypt:")
            // if (sDBUser != null && sDBPassword != null && sDBPassword.startsWith("crypt:")) {
            //     AltEncrypter cypher = new AltEncrypter("cypherkey" + sDBUser);
            //      sDBPassword = cypher.decrypt(sDBPassword.substring(6));
            // }
            //connection = DriverManager.getConnection("jdbc:mysql://" + "www.busc.in/buscin1_buscuserdb", "buscin1", "RC7Y*Q9M");
            Session s= new Session("jdbc:hsqldb:file://E:/nov11/temp/BUSC", sDBUser, sDBPassword);
            String data1="RA18712";
            List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,attributes,rate,total from billitem where parentid=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.OBJECT,Datas.DOUBLE,Datas.DOUBLE})).list(new Object[]{data1});
            for(Object a:objl){
            new PreparedSentence(m_app.getSession(), "INSERT INTO billitem( id,line,parentid,product,dmultiply,attributes,rate,total)  values (?,?,?,?,?,?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.OBJECT,Datas.DOUBLE,Datas.DOUBLE})).exec(a);
            }*/
            /* List<Object[]> list=  new PreparedSentence(m_app.getSession()
            , "SELECT id,message,sentdate,memid from automsg"
            ,null,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).list();
            for(Object[] obj:list){
            new PreparedSentence(s
            , "insert into automsg (id,message,sentdate,memid) values (?,?,?,?)",
            new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj);
            }*/
            //  for(int i=0;i<=33223;i++){
           /* List<String> list=new ArrayList<String>();
            List<Object[]> objlist=new ArrayList<Object[]>();
            String p="BA";
            for(int i=0;i<=46668;i++){
            String a=p+i;
            Object[] obj=(Object[])new PreparedSentence(m_app.getSession(), "SELECT COUNT(*) from qticket_arv where id=?"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{a});
            if(obj!=null && Integer.parseInt(String.valueOf(obj[0]))>0){
            }else{
            list.add(a);
            }
            }
            p="RA";
            for(int i=0;i<=54448;i++){
            String a=p+i;
            Object[] obj=(Object[])new PreparedSentence(m_app.getSession(), "SELECT COUNT(*) from qticket_arv where id=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.OBJECT})).find(new Object[]{a});
            if(obj!=null && Integer.parseInt(String.valueOf(obj[0]))>0){
            }else{
            list.add(a);
            }
            }
            String temp="";
            String temp1="";
            for(String data1:list){
            temp+=data1+" : ";
            Object[] obj=(Object[])new PreparedSentence(s, "SELECT id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason from qticket_arv where id=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).find(new Object[]{data1});
            if(obj==null){
            Object[] obj1=(Object[])new PreparedSentence(s, "SELECT id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason from qticket where id=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).find(new Object[]{data1});
            if(obj1!=null){
            try{
            new PreparedSentence(m_app.getSession(), "INSERT INTO qticket(id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason) values (?,?,?,?,?,?,?,?,?,?,?)"
            ,       new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj1);
            }catch(Exception e){
            }
            List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,rate,attributes from qtitems where parentid=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).list(new Object[]{data1});
            for(Object a:objl){
            new PreparedSentence(m_app.getSession(), "INSERT INTO qtitems( id,line,parentid,product,dmultiply,rate,attributes)  values (?,?,?,?,?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).exec(a);
            }
            }else{
            temp1+=data1+" : ";
            }
            }else{
            new PreparedSentence(m_app.getSession(), "INSERT INTO qticket_ARV(id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason) values (?,?,?,?,?,?,?,?,?,?,?)"
            ,       new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj);
            List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,rate,attributes from qtitems_arv where parentid=?"
            ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).list(new Object[]{data1});
            for(Object a:objl){
            new PreparedSentence(m_app.getSession(), "INSERT INTO qtitems_ARV( id,line,parentid,product,dmultiply,rate,attributes) values (?,?,?,?,?,?,?)"
            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.OBJECT})).exec(a);
            }
            }
            }
            // for(Object[] obj1:objlist){
            // }
            System.out.println(temp);
            System.out.println(temp1);
            //   }
             */
            /*  } catch (BasicException ex) {
            ex.printStackTrace();
            } catch (InstantiationException ex) {
            ex.printStackTrace();
            } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            } catch (SQLException ex) {
            ex.printStackTrace();
            } catch (MalformedURLException ex) {
            ex.printStackTrace();
            }*/
        } catch (BasicException ex) {
            ex.printStackTrace();
        }

        /*   Date d=new Date();
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.YEAR, 2009);
        cal.set(Calendar.HOUR, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        cal.set(Calendar.MINUTE, 00);
        d.setTime(cal.getTimeInMillis());
        for(int i=0;!(cal.get(Calendar.DATE)==12 && cal.get(Calendar.MONTH)==10  );i++){
            try {
                String compdate = cal.get(Calendar.YEAR) + "-" ;
                String temp=(cal.get(Calendar.MONTH)+1)+"";
                if(temp.length()<2)
                    compdate+="0"+temp+"-";
                else
                    compdate+=temp+"-";
                temp= cal.get(Calendar.DATE)+"";
                if(temp.length()<2)
                    compdate+="0"+temp+"%";
                else
                    compdate+=temp+"%";
                int journal=1,contra=1,receipt=1,payment=1;
                List<String> slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Journal"});
                String jstring="",cstring="",rstring="",pstring="";
                for(String s:slist){
                    if(!s.equals(jstring)){
                        new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{journal+"",s});
                        jstring=s;
                        journal++;
                    }
                }
                slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Contra"});
                for(String s:slist){
                    if(!s.equals(cstring)){
                        new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{contra+"",s});
                        cstring=s;
                        contra++;
                    }
                }
                slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Receipt"});
                for(String s:slist){
                    if(!s.equals(rstring)){
                        new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{receipt+"",s});
                        rstring=s;
                        receipt++;
                    }
                }
                 slist = new PreparedSentence(m_app.getSession(), "SELECT TID from accountjournal where date like ? and transref=? order by dateofentry"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), SerializerReadString.INSTANCE).list(new Object[]{compdate,"Payments"});
                for(String s:slist){
                    if(!s.equals(pstring)){
                        new PreparedSentence(m_app.getSession(), "update accountjournal set transno=? where tid=?"
                                      , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{payment+"",s});
                        pstring=s;
                        payment++;
                    }
                }
                new PreparedSentence(m_app.getSession(), "INSERT INTO vouchersequence (DATE,JOURNAL,CONTRA,RECEIPT,PAYMENTs) values (?,?,?,?,?)"
                                      , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.INT,Datas.INT,Datas.INT,Datas.INT})).exec(new Object[]{d,journal-1,contra-1,receipt-1,payment-1});
                cal.add(Calendar.DATE, 1);
                d.setTime(cal.getTimeInMillis());
            } catch (BasicException ex) {
               ex.printStackTrace();
            }
        }//else{
          System.out.println("finish");*/
        //}
        //   try {
            // TODO add your handling code here
            /*   Date todate=new Date();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(todate.getTime());
            cal.set(Calendar.MONTH, 4);
            todate.setTime(cal.getTimeInMillis());
            Archiving.ArchiveQT(todate, m_app);*/
            /*   try{
            String temp="";
            List<Object[]> list=new StaticSentence(m_app.getSession()
            , "select s.id,c.productscn,c.nofst,nosec,s.units1 from stockdiary s,converter c where s.product1!='af65ec78-ec15-4fe2-afc6-1a221f35f973' and s.product1 not like 'f4fe43a5%' and s.product1=c.productscn and s.product!=c.productfst ",null,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE})).list();
            for(Object[] s:list){
            System.out.println(s[0]);
            System.out.println(s[1]);
            Double val1=Double.parseDouble(String.valueOf(s[4]));
            Double val2=Double.parseDouble(String.valueOf(s[3]));
            Double val3=Double.parseDouble(String.valueOf(s[2]));
            Double val= -1 * Double.parseDouble(String.valueOf(s[4]))*Double.parseDouble(String.valueOf(s[2]))/Double.parseDouble(String.valueOf(s[3]));
            new StaticSentence(m_app.getSession(), "update stockdiary set product=(SELECT C.PRODUCTFST from converter c where c.productscn=? and active=true),units=? where id=?",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.DOUBLE,Datas.STRING})
            ).exec(new Object[]{s[1],val,s[0]});
            }
            //textField.setText(temp);
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
            e.printStackTrace();
            }*/
          /*  ClassLoader cloader = new URLClassLoader(new URL[]{new File("C:\\Documents and Settings\\swathi\\Desktop\\temp\\.\\lib\\hsqldb.jar").toURI().toURL()});
            DriverManager.registerDriver(new DriverWrapper((Driver) Class.forName("org.hsqldb.jdbcDriver",true,cloader).newInstance()));
            String sDBUser = "sa";
            String sDBPassword = "";
            //&& sDBPassword.startsWith("crypt:")
           // if (sDBUser != null && sDBPassword != null && sDBPassword.startsWith("crypt:")) {
           //     AltEncrypter cypher = new AltEncrypter("cypherkey" + sDBUser);
          //      sDBPassword = cypher.decrypt(sDBPassword.substring(6));
           // }
            //connection = DriverManager.getConnection("jdbc:mysql://" + "www.busc.in/buscin1_buscuserdb", "buscin1", "RC7Y*Q9M");
          Session s= new Session("jdbc:hsqldb:file://E:/nov11/temp/BUSC", sDBUser, sDBPassword);
          String data1="RA18712";
           List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,attributes,rate,total from billitem where parentid=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.OBJECT,Datas.DOUBLE,Datas.DOUBLE})).list(new Object[]{data1});
                               for(Object a:objl){
                                   new PreparedSentence(m_app.getSession(), "INSERT INTO billitem( id,line,parentid,product,dmultiply,attributes,rate,total)  values (?,?,?,?,?,?,?,?)"
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.OBJECT,Datas.DOUBLE,Datas.DOUBLE})).exec(a);
                               }*/
         /* List<Object[]> list=  new PreparedSentence(m_app.getSession()
                    , "SELECT id,message,sentdate,memid from automsg"
                    ,null,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).list();
          for(Object[] obj:list){
              new PreparedSentence(s
                    , "insert into automsg (id,message,sentdate,memid) values (?,?,?,?)",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj);
          }*/
          //  for(int i=0;i<=33223;i++){
         /*   List<String> list=new ArrayList<String>();
            List<Object[]> objlist=new ArrayList<Object[]>();
                String p="BA";
               for(int i=0;i<=46668;i++){
                    String a=p+i;
                  Object[] obj=(Object[])new PreparedSentence(m_app.getSession(), "SELECT COUNT(*) from qticket_arv where id=?"
                       , new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.INT})).find(new Object[]{a});
                   if(obj!=null && Integer.parseInt(String.valueOf(obj[0]))>0){
                   }else{
                     list.add(a);
                   }
                }
                 p="RA";
                for(int i=0;i<=54448;i++){
                 String a=p+i;
                   Object[] obj=(Object[])new PreparedSentence(m_app.getSession(), "SELECT COUNT(*) from qticket_arv where id=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.OBJECT})).find(new Object[]{a});
                    if(obj!=null && Integer.parseInt(String.valueOf(obj[0]))>0){
                   }else{
                     list.add(a);
                   }
                }
                 
                 String temp="";
                 String temp1="";
                 for(String data1:list){
                     temp+=data1+" : ";
                     Object[] obj=(Object[])new PreparedSentence(s, "SELECT id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason from qticket_arv where id=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).find(new Object[]{data1});
                     if(obj==null){
                         Object[] obj1=(Object[])new PreparedSentence(s, "SELECT id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason from qticket where id=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).find(new Object[]{data1});
                     if(obj1!=null){
                         try{
                               new PreparedSentence(m_app.getSession(), "INSERT INTO qticket(id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason) values (?,?,?,?,?,?,?,?,?,?,?)"
                       ,       new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj1);
                         }catch(Exception e){
                         }
                               List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,rate,attributes from qtitems where parentid=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).list(new Object[]{data1});
                               for(Object a:objl){
                                   new PreparedSentence(m_app.getSession(), "INSERT INTO qtitems( id,line,parentid,product,dmultiply,rate,attributes)  values (?,?,?,?,?,?,?)"
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).exec(a);
                               }
                      }else{
                               temp1+=data1+" : ";
                      }
                     }else{
                           new PreparedSentence(m_app.getSession(), "INSERT INTO qticket_ARV(id,customer,place,waiter,floor,prcategory,billed,billref,createdby,crdate,reason) values (?,?,?,?,?,?,?,?,?,?,?)"
                       ,       new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})).exec(obj);
                               List<Object[]> objl=new PreparedSentence(s, "SELECT id,line,parentid,product,dmultiply,rate,attributes from qtitems_arv where parentid=?"
                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.OBJECT})).list(new Object[]{data1});
                               for(Object a:objl){
                                   new PreparedSentence(m_app.getSession(), "INSERT INTO qtitems_ARV( id,line,parentid,product,dmultiply,rate,attributes) values (?,?,?,?,?,?,?)"
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.DOUBLE,Datas.DOUBLE,Datas.OBJECT})).exec(a);
                               }
                     }
                 }
                // for(Object[] obj1:objlist){

                // }
                 System.out.println(temp);
                 System.out.println(temp1);
             //   }
            */

      /*  } catch (BasicException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }*/
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
          /* List<Object[]> obj=new StaticSentence(m_app.getSession()
                , "SELECT ACCOUNT,SEARCHKEY,NAME FROM CUSTOMERS WHERE ACCOUNT IS NOT NULL AND SEARCHKEY IS NOT NULL" ,
                null
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).list();
           for(Object[] ob: obj){
               new StaticSentence(m_app.getSession()
                , "UPDATE ACCOUNTMASTER SET NAME=? WHERE ID=?",
                new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{ob[1].toString()+" - "+ob[2],ob[0]});

           }*/
            //List<Object[]> list=new StaticSentence(m_app.getSession(), "select sum(bi.dmultiply) from billitem_arv bi,bill_arv b", serwrite)
            Transaction t=new Transaction(m_app.getSession()) {

                @Override
                protected Object transact() throws BasicException {


            Date d=new Date();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            //cal.set(Calendar.DATE, 10);
            //cal.set(Calendar.MONTH, Calendar.JULY);
            cal.set(Calendar.HOUR, 00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 00);
            d.setTime(cal.getTimeInMillis());
            int l=0;
            String accountid=null;
          while(l<7){
              if(l==0)
                  accountid="1.1.2.2";
              else if(l==1)
                  accountid="1.1.2.3";
              else if(l==2)
                  accountid="1.1.2.8";
              else if(l==3)
                  accountid="1.1.2.9";
              else if(l==4)
                  accountid="1.1.2.10";
              else if(l==5)
                  accountid="1.1.2.11";
             
            List<Object[]> list= new StaticSentence(m_app.getSession(),"SELECT A.TID,AM.ID FROM ACCOUNTJOURNAL A,ACCOUNTMASTER AM WHERE A.ACCOUNTID=AM.ID  AND A.TRANSTYPE='C' AND AM.searchkey=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING})).list(accountid);
            if(list.size()>0){
            Object[] acc2=list.get(0);
            String macc=String.valueOf(acc2[1]);
            //AM.searchkey='1.1.2.4' or AM.searchkey='1.1.2.5' or AM.searchkey='1.1.2.6' or AM.searchkey='1.1.2.12'
            for(Object[] acc1:list){
               String acc=String.valueOf(acc1[0]);
                   Object[] obj=(Object[])new StaticSentence(m_app.getSession(), "SELECT count(*) from accountjournal where tid=?", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.INT})).find(acc);
                   int cnt=0;
                   if(obj!=null && obj[0]!=null){
                       cnt=Integer.parseInt(String.valueOf(obj[0]));
                   }
                   if(cnt==2){
                       Object[] obj2=(Object[])new StaticSentence(m_app.getSession(), "SELECT am.searchkey from accountjournal a,accountmaster am where a.tid=?  and a.transtype='D' and a.accountid=am.id ", SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(acc);
                       if(obj2!=null && obj2[0]!=null){
                          String a=String.valueOf(obj2[0]);
                          if(a!=null && (a.equals("1.1.2.4") || a.equals("1.1.2.5") || a.equals("1.1.2.6") || a.equals("1.1.2.12")) ){
                              //new StaticSentence(m_app.getSession(), "delete from accountjournal where tid=?",SerializerWriteString.INSTANCE).exec(acc);
                              //ID,TID,MEMID,DATE,TRANSTYPE,TRANSREF,TRANSNO,AMOUNT,DUEDATE,ADJUSTED,CREATEDBY,COUNTER,NARRATION,ACCOUNTID,BALANCEAMOUNT,ACTIVE
                             /* String tid=UUID.randomUUID().toString();
                              Double amt=Double.parseDouble(String.valueOf(obj2[0]));
                              String pid=String.valueOf(obj2[3]);
                              Object[] obj3=new Object[]{UUID.randomUUID().toString(),tid,new Date(),"C","Cheque Transfer","Cheque Transfer",amt,new Date(),false,m_app.getAppUserView().getUser().getName(),m_app.getProperties().getHost(),"Cheque transfered",obj2[1],amt,true};
                              dmang.insertintoaccjoutnal(obj3);
                              Object[] obj4=new Object[]{UUID.randomUUID().toString(),tid,new Date(),"D","Cheque Transfer","Cheque Transfer",amt,new Date(),false,m_app.getAppUserView().getUser().getName(),m_app.getProperties().getHost(),"Cheque transfered","205",amt,true};
                              dmang.insertintoaccjoutnal(obj4);*/
                              new StaticSentence(m_app.getSession(), "delete from accountjournal where tid=?",SerializerWriteString.INSTANCE).exec(acc);//"50e747ea-1d14-4bcb-8c40-b9a229c8858c"
                              
                          }
                       }

                   }
           }
            Object[] value=(Object[]) new StaticSentence(m_app.getSession(),"SELECT (SELECT SUM(A1.AMOUNT) FROM ACCOUNTJOURNAL A1 WHERE A1.ACCOUNTID=A.ID AND A1.TRANSTYPE='C' ) "
                    +" ,(SELECT SUM(A1.AMOUNT) FROM ACCOUNTJOURNAL A1 WHERE A1.ACCOUNTID=A.ID AND A1.TRANSTYPE='D' ) FROM ACCOUNTMASTER A WHERE A.ID=? ",SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.DOUBLE,Datas.DOUBLE})).find(macc);
            Double debit=0.0,credit=0.0;
            if(value[0]!=null && !value[0].toString().trim().equals(""))
                credit=Double.parseDouble(String.valueOf(value[0]));
            if(value[1]!=null && !value[1].toString().trim().equals(""))
                debit=Double.parseDouble(String.valueOf(value[1]));
            Double val=debit-credit;
            if(val>0){
                String tid=UUID.randomUUID().toString();
                String transno=dmang.getnextTranscationNum(d, "Contra");
                Object[] obj3=new Object[]{UUID.randomUUID().toString(),tid,null,new Date(),"C","Contra",transno,val,new Date(),false,m_app.getAppUserView().getUser().getName(),m_app.getProperties().getHost(),"Cheque transfered-correction entry",macc,val,true};
                dmang.insertintoaccjoutnal(obj3);
                Object[] obj4=new Object[]{UUID.randomUUID().toString(),tid,null,new Date(),"D","Contra",transno,val,new Date(),false,m_app.getAppUserView().getUser().getName(),m_app.getProperties().getHost(),"Cheque transfered-correction entry","207",val,true};
                dmang.insertintoaccjoutnal(obj4);
            }
          }
            l++;
             
        }
            new StaticSentence(m_app.getSession(), "update cheque set holder=? ",new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{"50e747ea-1d14-4bcb-8c40-b9a229c8858c"});
            return null;
           }
        };
        t.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
          e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            Date d = new Date();
            Date d1 = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(d.getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(d1.getTime());
            cal.set(Calendar.DATE, 1);
            cal.set(Calendar.MONTH, 5);
            cal.set(Calendar.HOUR, 00);
            cal.set(Calendar.SECOND,00);
            cal.set(Calendar.MINUTE, 00);
            cal.set(Calendar.MILLISECOND,00);

            d.setTime(cal.getTimeInMillis());
            cal1.set(Calendar.DATE, 30);
            cal1.set(Calendar.MONTH, 6);
            cal1.set(Calendar.HOUR, 00);
            cal1.set(Calendar.SECOND,00);
            cal1.set(Calendar.MINUTE, 00);
            cal1.set(Calendar.MILLISECOND,00);
            d1.setTime(cal1.getTimeInMillis());
            new PreparedSentence(m_app.getSession(), "INSERT INTO GENERALTABLE (ID,NAME,VALUE) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), "Datestart", Formats.TIMESTAMP.formatValue(d)});
            new PreparedSentence(m_app.getSession(), "INSERT INTO GENERALTABLE (ID,NAME,VALUE) VALUES (?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), "Dateend", Formats.TIMESTAMP.formatValue(d1)});
        } catch (BasicException ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      //  try {
            // TODO add your handling code here:
             JOptionPane.showMessageDialog(this, "Works");
          //  CloseFinancialYear.close(m_app);
       // } catch (BasicException ex) {
       //     Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
       //     ex.printStackTrace();
       // }
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables

}

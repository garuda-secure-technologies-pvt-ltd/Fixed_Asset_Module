/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AuditTrail.java
 *
 * Created on 27-Oct-2009, 10:56:23
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountReports.AccountsList;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JRBasicField;
import com.openbravo.pos.clubmang.JTreeTable;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;
import com.openbravo.pos.clubmang.TreeTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComponentInputMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperPrint;
//import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author swathi
 */
public class AuditTrail extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
    private ComboBoxValModel amodel;
   // private SentenceList sen;
    private JTextComponent editor;
    private waitDialog w;
    private AppView m_App;
    private List<AccountsList> ledgerlist=new ArrayList<AccountsList>();
    private final static String[] HEADERS = {"Account","Debit","Credit"};
    private final static String[] HEADERS1 = {"Date","Account","Reference","Debit","Credit","Created by"};
    private DataLogicFacilities dlfac;
    private Map<String ,AccountTree> map;
    private JTable table;
    private JTreeTable treetable;
    private String accountid=null;
    private String accname=null;
    private List<JComponent> tablelist;    
    private AccountAudit accaudit=null;
    private AccountsList acclist=null;
    private Date todate;
    private Date fromdate;
    double dtemp = 0, ctemp = 0;//by pratima
    /** Creates new form AuditTrail */
    public AuditTrail() {
        initComponents();
    }

    public String getTitle() {
        return "Audit Trail";
    }

    public void activate() throws BasicException {
        jButton5.setVisible(false);
        amodel=new ComboBoxValModel();
        jTextField1.setText(null);
         jTextField2.setText(null);
       
    }

     private void TreeTableMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
               AccountAudit a=(AccountAudit) treetable.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign()!=null && a.getSign().equals("S")){
                  accaudit=a;
                  forward();
               }
             }
           // DefaultMutableTreeNode temp=(DefaultMutableTreeNode)treetable.getTree().getSelectionPath().getLastPathComponent();

          //  if(temp.isLeaf()){
          //   if(evt.getClickCount()==2){
              /* accm=(AccountMaster)temp.getUserObject();
                 jTable1.setVisible(true);
                 etModel=EditorTableModel.loadInstance(m_App,accm.getid());
                 jTable1.setModel(etModel.getTableModel());
                 jLabel1.setText(accm.getName());*/

          //  }
          // }
        }catch(Exception e){
            new MessageInf(e).show(new JFrame());
          e.printStackTrace();
        }
    }
    private void TreeTableKeyPressed(java.awt.event.KeyEvent evt){
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
                   AccountAudit a=(AccountAudit) treetable.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign().equals("S")){
               // try {
                    accaudit = a;
                    forward();
               // } catch (BasicException ex) {
               //     ex.printStackTrace();
               // } catch (SQLException ex) {
               //     ex.printStackTrace();
               // }
               }
          }
    }
    private void TableMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
                  forward();
             }
        }catch(Exception e){
            new MessageInf(e).show(new JFrame());
          e.printStackTrace();
        }
    }
    private void TableKeyPressed(java.awt.event.KeyEvent evt){
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
                    forward();
          }
    }

    private void TreeMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
               AccountAudit a=(AccountAudit) treetable.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign().equals("S")){
                  accaudit=a;
                  loadReport(jTextField1.getText(),jTextField2.getText(), a);
               }
             }
           // DefaultMutableTreeNode temp=(DefaultMutableTreeNode)treetable.getTree().getSelectionPath().getLastPathComponent();

          //  if(temp.isLeaf()){
          //   if(evt.getClickCount()==2){
              /* accm=(AccountMaster)temp.getUserObject();
                 jTable1.setVisible(true);
                 etModel=EditorTableModel.loadInstance(m_App,accm.getid());
                 jTable1.setModel(etModel.getTableModel());
                 jLabel1.setText(accm.getName());*/

          //  }
          // }
        }catch(Exception e){
          e.printStackTrace();
        }
    }
    private void TreeKeyPressed(java.awt.event.KeyEvent evt){
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
                   AccountAudit a=(AccountAudit) treetable.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign().equals("S")){
                try {
                    accaudit = a;
                    loadReport(jTextField1.getText(), jTextField2.getText(), a);
                } catch (BasicException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
               }
          }
    }
    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
       return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        m_App=app;
        jButton1.setText("Next");
        jButton2.setText("Back");
        jButton3.setText("Date");
        jButton4.setText("Date");
        jLabel2.setText("From");
        jLabel3.setText("To");
        dlfac=(DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        tablelist=new ArrayList<JComponent>();
        Action Backwardaction=new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               backwardAction();
            }
        };
        //jButton1.setIcon(new ImageIcon)
        KeyStroke key=KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,InputEvent.ALT_DOWN_MASK);
        ComponentInputMap map1=new ComponentInputMap(jScrollPane1);
        map1.put(key, "Escactionperformed");
        map1.setParent(jScrollPane1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jScrollPane1.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map1);
        jScrollPane1.getActionMap().put("Escactionperformed", Backwardaction);
        Action forwardaction=new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               forwardAction();
            }
        };
        key=KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,InputEvent.ALT_DOWN_MASK);
        ComponentInputMap map2=new ComponentInputMap(jScrollPane1);
        map2.put(key, "Enteractionperformed");
        map2.setParent(jScrollPane1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jScrollPane1.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map2);
        jScrollPane1.getActionMap().put("Enteractionperformed", forwardaction);
        /*sen=new PreparedSentence(app.getSession()
                           , "SELECT A.ID,A.NAME,A.SEARCHKEY,A.TYPE,A.SIGN,A.SUMMARY,A.PARENT FROM ACCOUNTMASTER A WHERE  A.SUMMARY=FALSE AND UCASE(A.NAME) LIKE ? ORDER BY A.NAME"
                           , SerializerWriteString.INSTANCE
                           , new SerializerReadClass(AccountMasterExt.class));
        */
        m_App=app;
        jTextField1.setText(LookupUtilityImpl.getInstance(null).getGeneralSettingsMap().get("Datestart").getValue());
        jTextField2.setText(Formats.DATE.formatValue(new Date()));

    }
    public void backwardAction(){
        back();
    }
    private void back(){
        jScrollPane1.setViewportView(treetable);
        try{
           if(treetable.getRowCount()>0){
               treetable.setRowSelectionInterval(0, 0);
           }
           java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                   treetable.requestFocus();
                }
            });
        }catch(Exception e){
            new MessageInf(e).show(new JFrame());
          e.printStackTrace();
        }
    }
    public void forwardAction(){
    /*  if(tablelist.size()<2){
       if( jScrollPane1.getViewport().getView() instanceof JTreeTable){
            tablelist.add(treetable);
       }else{
           tablelist.add(table);
       }
      }*/
          forward();
    }
    public Object getBean() {
        return this;
    }
     private Date getDate(String date) throws BasicException{
       Date d=(Date)Formats.TIMESTAMP.parseValue(date);
       Calendar cal=Calendar.getInstance();
       cal.setTimeInMillis(d.getTime());
       cal.set(Calendar.HOUR_OF_DAY, 00);
       cal.set(Calendar.MINUTE, 00);
       cal.set(Calendar.SECOND, 00);
       cal.set(Calendar.MILLISECOND, 00);
       d.setTime(cal.getTimeInMillis());
       return d;
    }
     private Date getSecondDate(String date) throws BasicException{
       Date d=(Date)Formats.TIMESTAMP.parseValue(date);
       Calendar cal=Calendar.getInstance();
       cal.setTimeInMillis(d.getTime());
       cal.set(Calendar.HOUR_OF_DAY, 23);
       cal.set(Calendar.MINUTE, 59);
       cal.set(Calendar.SECOND, 59);
       cal.set(Calendar.MILLISECOND, 59);
       //cal.set(Calendar.AM_PM, Calendar.PM);
       d.setTime(cal.getTimeInMillis());
       return d;
    }
     ///////////////////////////////////////////////////////////by pratima :to correct the amount in table
      private List<AccountAudit> getStatementPeriod(Date fdate,Date edate) throws BasicException {

       System.out.println("fdate in get statement period"+fdate);
       System.out.println("edate in get statement period"+edate);
 List<AccountAudit> eacclist= new PreparedSentence(m_App.getSession(),
    " SELECT AM.ID,AM.NAME,AM.SEARCHKEY,AM.PARENT,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) ,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) ,AM.ACTIVE,AM.LEVEL_,AM.SUMMARY AS SUMMARY FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID AND AJ.EDATE>=? and AJ.EDATE<=?\n" +
    "  GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.SUMMARY having am.active=1 ORDER BY  AM.PARENT,AM.NAME "
                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}), new SerializerReadClass(AccountAudit.class)).list(new Object[]{fdate,edate});
       
   /* if(jRadioButton2.isSelected()){
        for (int i=0;i<acclistele.size();i++){
         TrailBalanceDataPeriod trailBalObj =  acclistele.get(i);
            if((trailBalObj.getdebit_f()==0.00)&&(trailBalObj.getcredit_f()==0.00)&&(trailBalObj.getdebit_d()==0.00 )&&(trailBalObj.getcredit_d()==0.00 )&&(trailBalObj.getdebit_t()==0.00 )&&(trailBalObj.getcredit_t()==0.00)){
            acclistele.remove(i);
            i--;
            }
        }
    }*/
        return eacclist;
    }
     /////////////////////////////////////////////////////////////////////////////////////////////////////
 
    private List<AccountTree> acctreelist;
  /*  private void test(String pskey){
         if(map.containsKey(pskey)){
            List<AccountAudit> a= map.get(pskey).getAccountList();
            for(AccountAudit a1:a){
                System.out.println(a1.getName());
               test(a1.getSerachkey());
            }
         }
    }*/
    private void treestructure() throws BasicException{
      map=new HashMap<String,AccountTree>();
      List<String> templist=new ArrayList();
      //List<AccountAudit> eacclist=dlfac.getaccountswithTB();//commented by pratima
      /////////////added by pratima
       String todate1=jTextField2.getText();
         String fromdate1=jTextField1.getText();
         Date sdate=new Date();
         sdate= getDate(fromdate1);
         Date edate=getSecondDate(todate1);
      List<AccountAudit> eacclist=getStatementPeriod(sdate,edate);
       /////////////ended by pratima
      int flag=0,i=0;
      for(AccountAudit acc: eacclist){
//          if(acc.getSerachkey().equals("2.1.15") || acc.getSerachkey().equals("2.1.15.1") ){
//              String aa="";
//          }
         if(map.containsKey(acc.getparent())){
             String parent=acc.getparent();
             double debit=acc.getDebit();
             double credit=acc.getCredit();
             String tparent=null;
             AccountTree at;
             flag=3;
          while(map.containsKey(parent)){
             at=map.get(parent);
             if(flag==3){
               at.getAccountList().add(acc);
               flag=0;
             }
            if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                flag=1;
            } else if(flag==1){
                parent="";
              flag=2;
            } else if(flag==2){
                flag=3;
               break;
            }

          }
         }else{
             map.put(acc.getparent(), new AccountTree(acc));
             String parent=acc.getparent();
             double debit=acc.getDebit();
             double credit=acc.getCredit();
             AccountTree at;
             flag=3;
             String tparent=null;
          while(map.containsKey(parent)){
             at=map.get(parent);
             if(flag==3){
               flag=0;
             }
          if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                flag=1;
            } else if(flag==1){
                parent="";
              flag=2;
            } else if(flag==2){
                flag=3;
               break;
            }

          }
            // System.out.println(acc.getparent());
             if(acc.getSign().equals("S"))
             {
                 if(!templist.contains(acc.getparent())){
                     templist.add(acc.getparent());
                 }
             }
         }
      }
      for(String temp:templist){
           AccountTree at=map.get(temp);
           Collections.sort(at.getAccountList());
           map.put(temp, at);
      }
    }
    private class AccountTree{
       private List<AccountAudit> acclist;
       private double debit=0.0;
       private double credit=0.0;

        public AccountTree(AccountAudit a) {
            this.acclist = new ArrayList<AccountAudit>();
            acclist.add(a);
            debit=0.0;
            credit=0.0;
        }
        public void addDC(double debit,double credit){
            this.debit+=debit;
            this.credit+=credit;
        }
        public void setDC(double debit,double credit){
            this.debit=debit;
            this.credit=credit;
        }
        public List<AccountAudit> getAccountList(){
            return acclist;
        }
        public void setAccountList(List<AccountAudit> l){
            acclist=l;
        }
        public double getDebit(){
            return debit;
        }
        public double getCredit(){
            return credit;
        }

    }
    private class treetableModel extends AbstractTreeTableModel implements TreeTableModel{
        public int getColumnCount() {
           return HEADERS.length;
        }

        public treetableModel(Object data) {
            super(data);
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false, false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }


        @Override
            public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
            }
        
        public Object getValueAt(Object node, int column) {
            AccountAudit a=(AccountAudit) node;
            String debit=null,credit=null;
            try{
            if(map.containsKey(a.getSerachkey())){
                AccountTree at=map.get(a.getSerachkey());
                double temp=at.getDebit()-at.getCredit();
               // try{
                if(temp >0){
                   debit=Formats.CURRENCY.formatValue(temp);
                   credit=null;
                }else if(temp<0){
                    debit=null;
                   credit=Formats.CURRENCY.formatValue(temp*-1);
                }
                //debit=at.getDebit();
                //credit=at.getCredit();
            }else{
                double temp=a.getDebit()-a.getCredit();
                if(temp >0){
                   debit=Formats.CURRENCY.formatValue(temp);
                   credit=null;
                }else if(temp<0){
                    debit=null;
                   credit=Formats.CURRENCY.formatValue(temp*-1);
                }
                //debit=a.getDebit();
               // credit=a.getCredit();
            }
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getName();
                case 1: return debit;
                case 2: return credit;
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           AccountAudit a=(AccountAudit) parent;
           if(!a.getName().equals("Element")){
              String del=null;
           }
           if(a!=null && map!=null && a.getSerachkey()!=null && map.get(a.getSerachkey())!=null)
               return map.get(a.getSerachkey()).getAccountList().get(index);
           else
               return null;
        }

        public int getChildCount(Object parent) {
           AccountAudit a=(AccountAudit) parent;
           if(a!=null && map!=null && a.getSerachkey()!=null && map.get(a.getSerachkey())!=null)
               return map.get(a.getSerachkey()).getAccountList().size();
           else
               return 0;
        }

    }
     public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS1[column]);
            }
            public int getRowCount() {
                return ledgerlist.size();
            }
            public int getColumnCount() {

                return HEADERS1.length;
            }
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public Object getValueAt(int row, int column) {
                AccountsList l = ledgerlist.get(row);

                switch (column) {
                case 0: return Formats.TIMESTAMP.formatValue(l.getDate());
                case 1: return l.getAccountName();
                case 2: return l.getTransref();
                case 3: if(l.getTranstype().equals("D"))
                         return Formats.CURRENCY.formatValue(l.getAmt());
                        else
                            return null;
                case 4: if(l.getTranstype().equals("C"))
                         return Formats.CURRENCY.formatValue(l.getAmt());
                        else
                            return null;
                    case 5: return l.getCreatedBy();
                default: return null;
                }
            }
        };
    }
     private JRField[] getJRFields() throws JRException, UnsupportedOperationException {

        JRField[] fields = new JRField[10];
        fields[0] = (JRField) new JRBasicField("account", "account", java.lang.String.class, "java.lang.String");
        fields[1] = (JRField) new JRBasicField("amount", "amount", java.lang.Double.class, "java.lang.Double");
        fields[2] = (JRField) new JRBasicField("transtype", "transtype", java.lang.String.class, "java.lang.String");
        fields[3] = (JRField) new JRBasicField("createdby", "createdby", java.lang.String.class, "java.lang.String");
        fields[4] = (JRField) new JRBasicField("reference", "reference", java.lang.String.class, "java.lang.String");
        fields[5] = (JRField) new JRBasicField("date", "date", java.util.Date.class, "java.util.Date");
        return fields;
    }
     public void printTreeTableModel() {
         try{
         String companyName = m_App.getSession().getCompanyName();
         String companyAddress = m_App.getSession().getCompanyAddress();
         Map reportparams = new HashMap();
         String todate1=jTextField2.getText();
         String fromdate1=jTextField1.getText();
         reportparams.put("todate", todate1);
         reportparams.put("fromdate",fromdate1);
         reportparams.put("companyName", companyName);
         reportparams.put("companyAddress", companyAddress);
         /////////////////////////////////////////////////////by pratima
          reportparams.put("dobalance", dtemp);
                if (ctemp != 0) {
                    reportparams.put("cobalance", ctemp * -1);
                } else {
                    reportparams.put("cobalance", ctemp);
                }
                 reportparams.put("flag", true);
         ///////////////////////////////////////////////////////////////
         DataSourceProvider data1=new DataSourceProvider(ledgerlist);
         data1.setFields(getJRFields());
         AccountListDataSource ds= new AccountListDataSource(ledgerlist);
         data1.setDataSource(ds);
         JasperPrint jp = JasperReportNew.runReport(m_App,"./reports/com/openbravo/reports/AuditTrialReport.jrxml", reportparams,false, data1,true,null);
        dtemp = 0; ctemp = 0;
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }

    private void loadReport(String from,String to,AccountAudit acc1) throws BasicException, SQLException{
          Date sdate=new Date();
          try{
             sdate= getDate(from);
          }catch(Exception e){
            e.printStackTrace();
          }
          Date edate=getSecondDate(to);
          LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
          Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
          GeneralSettingInfo sinfo=gs.get("Datestart");
          GeneralSettingInfo einfo=gs.get("Dateend");
          Date fsdate=(Date)Formats.DATE.parseValue(sinfo.getValue());
          Date fedate=(Date)Formats.DATE.parseValue(einfo.getValue());
          Calendar fscal=Calendar.getInstance();//financial year sdate
          fscal.setTime(fsdate);
          Calendar fecal=Calendar.getInstance();//financial year edate
          fecal.setTime(fedate);
          Calendar rscal=Calendar.getInstance();//report start date
          Calendar rscal1=Calendar.getInstance();
          Calendar recal=Calendar.getInstance();//report end date
          Calendar recal1=Calendar.getInstance();//report end date
          rscal.setTimeInMillis(sdate.getTime());
          recal.setTimeInMillis(edate.getTime());
          rscal1.setTimeInMillis(rscal.getTimeInMillis());
          rscal1.add(Calendar.DATE, -1);
          recal1.setTimeInMillis(recal.getTimeInMillis());
          recal1.add(Calendar.DATE, -1);
          List<String> list=new ArrayList<String>();
          ResultSet rs=m_App.getSession().getConnection().getMetaData().getTables(null,null, "AJ%", null);
          while(rs.next()){
             list.add(rs.getString("TABLE_NAME"));
          }
          ////////////////////////////////////////////////////////////////////by pratima
           
                //calculate OB
                double crtemp = 0, drtemp = 0, temp = 0;
                if (!rscal.after(fscal)) {
                    if (rscal1.get(Calendar.DATE) != rscal1.getActualMaximum(Calendar.DATE)) {
                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT SUM(A.DEBIT-A.CREDIT) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=? AND A.EDATE < ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find(new Object[]{acc1.getid(), sdate});
                        temp = Double.parseDouble(obj2[0].toString());
                    } else {
                        rscal1.set(Calendar.DATE, rscal1.getActualMinimum(Calendar.DATE));
                        Date stemp = new Date(rscal1.getTimeInMillis());

                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT (SELECT SUM(A.DEBIT-A.CREDIT) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=? AND A.EDATE < ? ),(SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL WHERE ACCOUNTID=? AND DATE>=? AND DATE < ?  AND TRANSTYPE='C' AND ACTIVE=TRUE)" + " ,(SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL WHERE ACCOUNTID=? AND DATE>=? AND DATE < ?  AND TRANSTYPE='D' AND ACTIVE=TRUE) FROM APPLICATIONS WHERE ID=?   ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{acc1.getid(), sdate, acc1.getid(), sdate, stemp, acc1.getid(), sdate, stemp, AppLocal.APP_ID});
                        if (obj2[1] != null) {
                            crtemp = Double.parseDouble(obj2[1].toString());
                        }
                        if (obj2[2] != null) {
                            drtemp = Double.parseDouble(obj2[2].toString());
                        }
                        if (obj2[0] != null) {
                            temp = Double.parseDouble(obj2[0].toString());
                        }
                        temp = temp + drtemp - crtemp;
                    }
                } else {
                    Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT (SELECT SUM(A.DEBIT-A.CREDIT) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=? AND A.EDATE < ? ),(SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL WHERE ACCOUNTID=? AND DATE>=? AND DATE < ?  AND TRANSTYPE='C' AND ACTIVE=TRUE)" + " ,(SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL WHERE ACCOUNTID=? AND DATE>=? AND DATE < ?  AND TRANSTYPE='D' AND ACTIVE=TRUE) FROM APPLICATIONS WHERE ID=?   ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).find(new Object[]{acc1.getid(), fsdate, acc1.getid(), fsdate, sdate, acc1.getid(), fsdate, sdate, AppLocal.APP_ID});
                    if (obj2 != null) {

                        if (obj2[1] != null) {
                            crtemp = Double.parseDouble(obj2[1].toString());
                        }
                        if (obj2[2] != null) {
                            drtemp = Double.parseDouble(obj2[2].toString());
                        }
                        if (obj2[0] != null) {
                            temp = Double.parseDouble(obj2[0].toString());
                        }
                        temp = temp + drtemp - crtemp;
                    }
                }
                if (temp >= 0) {
                    dtemp = temp;
                } else {
                    ctemp = temp;
                }
          ///////////////////////////////////////////////////////////////////////////////
          AccountReports rep=new AccountReports(m_App);
          ledgerlist = rep.loadReport(rscal,recal,fscal,fecal,acc1.getid(),list,"");
          jButton5.setVisible(true);
          table=new javax.swing.JTable(){
              @Override
            public Component prepareRenderer(TableCellRenderer renderer,
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
            }};//
           table.setModel(getTableModel());
           if(table.getRowCount()>0){
               table.setRowSelectionInterval(0, 0);
           }
           table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
           });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TableKeyPressed(evt);
            }
           });

          //jScrollPane1.removeAll();
          jScrollPane1.setViewportView(table);
          jScrollPane1.revalidate();
          jScrollPane1.repaint();
          table.revalidate();
          table.repaint();
         /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  table.requestFocus();
            }
        });*/
          //tableList.add(table);
          //JDialog d=new JDialog(new JFrame());
          //d.add(t);
          //d.setVisible(true);
    }
    public static class Detail implements SerializableRead {       
        private String acc;
        private String transtype;
        private double amt;
        private Timestamp date;
        private String narration;
        private String paymentref;
        private String transref;
        private String createdby;
        private Timestamp doe;
        private Timestamp deactdate;
        private String deactby;
        private String deactref;
        public void readValues(DataRead dr) throws BasicException {
            date=dr.getTimestamp(1);
            acc=dr.getString(2);
            transtype=dr.getString(3);
            narration=dr.getString(4);
            amt=dr.getDouble(5);
            paymentref=dr.getString(6);
            transref=dr.getString(7);
            createdby=dr.getString(8);
            doe=dr.getTimestamp(9);
            deactdate=dr.getTimestamp(10);
            deactby=dr.getString(11);
            deactref=dr.getString(12);
        }
        public String getDeactRef(){
          return deactref;
        }
        public String getCreatedby(){
            return createdby;
        }
        public Timestamp getDateOfEntry(){
           return doe;
        }
        public String getAccount(){
           return acc;
        }
        public String getTranstype(){
            return transtype;
        }
        public String getTransRef(){
            return transref;
        }
        public String getNarration(){
           return narration;
        }
        public String getPaymentref(){
            return paymentref;
        }
        public Timestamp getDate(){
            return date;
        }
        public double getAmount(){
            return amt;
        }
        public String getDeactby(){
          return deactby;
        }
        public Timestamp getDeactdate(){
          return deactdate;
        }
        
    }   
private String nar;
private String DeacRef;
       private void loadDetail() throws BasicException{
           List<Detail> list= new PreparedSentence(m_App.getSession()
                            , "SELECT A.DATE,AM.NAME,A.TRANSTYPE,A.NARRATION,A.AMOUNT,PAYMENTREF,A.TRANSREF,A.CREATEDBY,A.DATEOFENTRY,A.DEACTDATE,A.DEACTBY,A.DEACTREF FROM ACCOUNTJOURNAL A JOIN ACCOUNTMASTER AM ON A.ACCOUNTID=AM.ID WHERE A.TID=?"
                             , SerializerWriteString.INSTANCE, new SerializerReadClass(Detail.class)).list(acclist.getTid());
         TransactionDetail t=new TransactionDetail(new JFrame(), true,m_App);
         if(list.size()>0){
                 t.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
  //sameer:printing all narrations
                 for(int i=0;i<list.size();i++){
                     if(nar!=null)
                     nar=nar+"\n"+list.get(i).getNarration();//acclist.getNarration();
                     else
                         nar=list.get(i).getNarration();
                     
                     DeacRef = list.get(i).getDeactRef();
                 }
                 //t.showDialog(list, acclist.getNarration(),acclist.getTransref(),acclist.getID(),acclist.getPaymentRef(),acclist.getDate(),acclist.getTransno(),accountid,acclist.getTid());
                 
         }
        // t.showDialog(list, nar,acclist.getTransref(),acclist.getID(),acclist.getPaymentRef(),acclist.getDate(),acclist.getTransno(),accountid,acclist.getTid());
         t.showDialog(list, nar,acclist.getTransref(),acclist.getID(),DeacRef,acclist.getDate(),acclist.getTransno(),accountid,acclist.getTid());
         
         
         nar=null;
       }         
     
    
    private void forward(){
         w=new waitDialog(new JFrame(), true);
         int h=w.getSize().height;
         int w1=w.getSize().width;
         Toolkit toolkit = Toolkit.getDefaultToolkit();
		 Dimension scrnsize = toolkit.getScreenSize();
         w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
         int row=0;
         if(jScrollPane1.getViewport().getView() instanceof JTreeTable){
            row=treetable.getSelectedRow();
            accaudit=(AccountAudit) treetable.getTree().getSelectionPath().getLastPathComponent();
            accname=accaudit.getName();
            accountid=accaudit.getid();
         }else if(jScrollPane1.getViewport().getView() instanceof JTable){
            row=table.getSelectedRow();
            if(table!=null)
            acclist=(AccountsList) ledgerlist.get(row);
            
         }
       // treetable.getTree_TableModel().getValueAt(treetable.getTree().getSelectionPath().getLastPathComponent(), row).;
         Thread t=new Thread(
		 		new Runnable()
				{
					public void run()
					{
						try
						{
                           if(jScrollPane1.getViewport().getView() instanceof JTreeTable){
							loadReport(jTextField1.getText(),jTextField2.getText(),accaudit);
                           }else{                             
                             loadDetail();
                           }
                            w.hideDialog();
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
                            w.hideDialog();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);
            t.start();
            w.showDialog("Please wait.Loading Report...");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Date"));

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Execute");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .add(43, 43, 43)))
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .add(45, 45, 45)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jButton3)
                    .add(jButton4))
                .add(41, 41, 41)
                .add(jButton6)
                .add(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .add(7, 7, 7)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jButton4)
                    .add(jButton6))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jButton5)
                        .add(40, 40, 40)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(43, 43, 43)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1)
                    .add(jButton5)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       forward();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        try {
            fromdate = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException e) {
            fromdate = null;
        }
        fromdate = JCalendarDialog.showCalendar(this, fromdate);
        if (fromdate != null) {
             Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(fromdate.getTime());
            cal.set(Calendar.DATE, 1);
            fromdate.setTime(cal.getTimeInMillis());
            jTextField1.setText(Formats.DATE.formatValue(fromdate));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
        try {
            todate = (Date) Formats.DATE.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            todate = null;
        }
        todate = JCalendarDialog.showCalendar(this, todate);
        if (todate != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(todate.getTime());
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            todate.setTime(cal.getTimeInMillis());
           jTextField2.setText(Formats.DATE.formatValue(todate));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        back();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        printTreeTableModel();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         AccountAudit a=new AccountAudit();
        a.setName("Element");
        a.setDebitandCredit(0, 0);
        a.setSearchKey("");
        treetable=new JTreeTable(new treetableModel(a));
        try {
            treestructure();
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        if(treetable.getRowCount()>0){
            treetable.setRowSelectionInterval(0, 0);
        }
        DefaultTableCellRenderer dtcr =new DefaultTableCellRenderer();
        dtcr.setHorizontalTextPosition(JLabel.RIGHT);
        dtcr.setHorizontalAlignment(JLabel.RIGHT);
        treetable.setDefaultRenderer(treetable.getColumnClass(1), dtcr);
        treetable.setDefaultRenderer(treetable.getColumnClass(2), dtcr);
        treetable.updateUI();
        jScrollPane1.setViewportView(treetable);
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
        treetable.revalidate();
        treetable.repaint();
        treetable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeTableMouseClicked(evt);
            }
           });
        treetable.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeTableKeyPressed(evt);
            }
           });

         /*  TableColumn tcol=treetable.getColumn(0);
           tcol.setPreferredWidth(200);
           tcol=treetable.getColumn(1);
           tcol.setPreferredWidth(70);
           tcol=treetable.getColumn(2);
           tcol.setPreferredWidth(70);*/

       /* treetable.getTree().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeMouseClicked(evt);
            }
           });
        treetable.getTree().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeKeyPressed(evt);
            }
           });*/
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables



}

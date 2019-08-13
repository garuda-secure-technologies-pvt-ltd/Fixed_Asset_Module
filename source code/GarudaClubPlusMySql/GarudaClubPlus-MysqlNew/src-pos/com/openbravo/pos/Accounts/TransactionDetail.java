/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TransactionDetail.java
 *
 * Created on 29-Oct-2009, 12:07:43
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
//import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
//import com.openbravo.pos.Accounts.AuditTrail.Detail;
import com.openbravo.pos.Accounts.AuditTrail.Detail;
import com.openbravo.pos.clubmang.ReceiptDetail;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
//import java.awt.event.InputEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComponentInputMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author swathi
 */
public class TransactionDetail extends javax.swing.JDialog {
   // private List<AuditTrail.Detail> detaillist;
    String[] HEADERS = {"Account","Debit","Credit"};
    String[] HEADERS1 = {"Product Name","Rate","Qty","Amount"};
    String[] HEADERS2 = {"Particulars","Narration","Org Amt","Paid Amount","Bal Amt"};
    String[] HEADERS3 = {"Product Name","Rate","Qty","Taxtotal","Amount"};
    String[] ChequeHEADER = {"Date","ChequeNo","Bank","Amt","MemNo","RNO"};
    private Color LIGHT_GREEN,LIGHT_YELLOW,VERY_LIGHT_BLUE;
    private List<MyTableModel> tablemodellist;
    private String oldtid;
    private AppView m_App;
    private int index=0;
    private int mindex=0;
    private String transtno;
    private String accid;
    private List<String> tidlist;
    private String tid;
    /** Creates new form TransactionDetail */
    public TransactionDetail(java.awt.Frame parent, boolean modal,AppView app) {
        super(parent, modal);
        this.setBackground(Color.CYAN);
        this.repaint();
        
        initComponents();
        m_App=app;
        try {
            jButton1.setIcon(new ImageIcon(ImageIO.read(TransactionDetail.class.getResourceAsStream("/com/openbravo/images/menu-left.png"))));
            jButton2.setIcon(new ImageIcon(ImageIO.read(TransactionDetail.class.getResourceAsStream("/com/openbravo/images/menu-right.png"))));
            jButton1.setText(null);
            jButton2.setText(null);

        } catch (IOException ex) {
           ex.printStackTrace();
        }
        tablemodellist=new ArrayList<MyTableModel>();
        LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
        LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x40);
        VERY_LIGHT_BLUE = new Color(0x80, 0x80, 0xFF);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        Action Backwardaction=new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               backwardAction();
            }
        };
        KeyStroke key=KeyStroke.getKeyStroke(KeyEvent.VK_UP,InputEvent.ALT_DOWN_MASK);
        ComponentInputMap map1=new ComponentInputMap(jButton1);
        map1.put(key, "Escactionperformed");
        map1.setParent(jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton1.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map1);
        jButton1.getActionMap().put("Escactionperformed", Backwardaction);
        Action forwardaction=new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               forwardAction();
            }
        };
        key=KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,InputEvent.ALT_DOWN_MASK);
        ComponentInputMap map2=new ComponentInputMap(jButton2);
        map2.put(key, "Enteractionperformed");
        map2.setParent(jButton2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
        jButton2.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, map2);
        jButton2.getActionMap().put("Enteractionperformed", forwardaction);
        tidlist=new ArrayList<String>();
        jCheckBox1.setVisible(false);
        jCheckBox1.setText("Purchase Detail");
    }
    private void backwardAction(){
        if(jButton1.isEnabled()==true && index<tablemodellist.size()){
        if(jLabel1.getText().equals("Bill")){
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            try {
              if(mindex+1==tablemodellist.size()){
                List<RBDetail> list = new PreparedSentence(m_App.getSession(), "SELECT P.NAME,BI.DMULTIPLY,BI.RATE,b.createddate,b.createdby,b.taxtotal FROM bill b join billitem bi on b.id=bi.parentid join products p on bi.product=p.id where b.receipt=?" +
                        " UNION ALL SELECT P.NAME,BI.DMULTIPLY,BI.RATE,b.createddate,b.createdby,b.taxtotal FROM bill_arv b join billitem_arv bi on b.id=bi.parentid join products p on bi.product=p.id where b.receipt=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(RBDetail.class)).list(new Object[]{transtno,transtno});
                
                if(list.size()>0){
                        jButton1.setEnabled(false);
                    index++;
                    mindex=index;
                    this.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
                    MyTableModel model=getTableModel();
                    model.setList(list);
                    model.setHeader(HEADERS1);
                    jTable1.setModel(model);
                    tablemodellist.add(model);
                    jButton2.setEnabled(true);
                }else{
                    jButton1.setEnabled(false);
                }

                }else{
                    index++;
                    jTable1.setModel(tablemodellist.get(index));
                    if(index==mindex){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                    }
                    MyTableModel m=(MyTableModel)jTable1.getModel();
                    this.setTitle("Created By :"+m.getList().get(0).getCreatedby()+" Date :"+m.getList().get(0).getDate());

                }
            } catch (BasicException ex) {
              ex.printStackTrace();
            }
        }else if(jLabel1.getText().equals("Receipt")){
                try {
                if(mindex+1==tablemodellist.size()){
                    List<ReceiptDetail> list = new StaticSentence(m_App.getSession(), "SELECT A.BALANCEAMOUNT,F.NAME,A.PAYMENTREF,A.AMOUNT,?,A.NARRATION FROM ACCOUNTJOURNAL A JOIN FACILITY F ON A.TRANSREF=F.ID  WHERE A.ACCOUNTID=? AND A.PAYMENTREF LIKE '%'+?+' # %'", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ReceiptDetail.class)).list(new Object[]{transtno, accid, transtno});
                    
                if(list.size()>0){
                    jButton1.setEnabled(false);
                    index++;
                    mindex=index;
                   // this.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
                    MyTableModel model=getTableModel();
                    model.setList(list);
                    model.setHeader(HEADERS2);
                    jTable1.setModel(model);
                    tablemodellist.add(model);
                    jButton2.setEnabled(true);
                }else{
                    jButton1.setEnabled(false);
                }

                }else{
                    index++;
                    jTable1.setModel(tablemodellist.get(index));
                    if(index==mindex){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                    }


                    //MyTableModel m=(MyTableModel)jTable1.getModel();
                   // this.setTitle("Created By :"+m.getList().get(0).getCreatedby()+" Date :"+m.getList().get(0).getDate());

                }
                } catch (BasicException ex) {
                  ex.printStackTrace();
                }
        }else{
            try {
                if(mindex+1==tablemodellist.size()){
                List<Detail> list = new PreparedSentence(m_App.getSession(), "SELECT A.DATE,AM.NAME,A.TRANSTYPE,A.NARRATION,A.AMOUNT,PAYMENTREF,A.TRANSREF,A.CREATEDBY,A.DATEOFENTRY,A.DEACTDATE,A.DEACTBY,A.DEACTREF FROM ACCOUNTJOURNAL A JOIN ACCOUNTMASTER AM ON A.ACCOUNTID=AM.ID WHERE A.TID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(Detail.class)).list(oldtid);
                boolean tidstatus=true;
                for(String tid1:tidlist){
                   if(tid1.equals(list.get(0).getDeactRef())){
                       tidstatus=false;
                   }
                }
                if(list.size()>0 && tidstatus){
                    if(list.get(0).getDeactRef()==null){
                        jButton1.setEnabled(false);
                        oldtid=null;
                    }else{
                      oldtid=list.get(0).getDeactRef();
                    }
                    tidlist.add(oldtid);
                    tid=oldtid;
                    index++;
                    mindex=index;
                    this.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
                    MyTableModel model=getTableModel();
                    model.setList(list);
                    model.setHeader(HEADERS);
                    jTable1.setModel(model);
                    tablemodellist.add(model);
                    jButton2.setEnabled(true);
                }else{
                    jButton1.setEnabled(false);
                }
                }else{
                    index++;
                    jTable1.setModel(tablemodellist.get(index));
                    if(index==mindex){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                    }
                    MyTableModel m=(MyTableModel)jTable1.getModel();
                    this.setTitle("Created By :"+m.getList().get(0).getCreatedby()+" Date :"+m.getList().get(0).getDate());
                    if(m.getList().get(0).getDeactby()!=null){
                        jLabel6.setText("Deactivated Date");
                        jLabel7.setText(Formats.DATE.formatValue(m.getList().get(0).getDeactdate()));
                        jLabel8.setText("Deactivated By");
                        jLabel9.setText(m.getList().get(0).getDeactby());
                        jLabel6.setVisible(true);
                        jLabel7.setVisible(true);
                        jLabel8.setVisible(true);
                        jLabel9.setVisible(true);
                     }else{
                        jLabel6.setVisible(false);
                        jLabel7.setVisible(false);
                        jLabel8.setVisible(false);
                        jLabel9.setVisible(false);
                     }
                }
            } catch (BasicException ex) {
               ex.printStackTrace();
            }
        }
        if(jLabel1.getText().equals("Contra Voucher")){
            try{
            String narration=jTextArea1.getText();
            if(narration.startsWith("Cheque nos")){
                   List<Chequedetail> chdetail=new PreparedSentence(m_App.getSession()
                           , "SELECT CHNO , RNO, BANK, AMT,PTIME,TOTAL,SKEY  FROM (SELECT CH.CHEQUENO AS CHNO ,CH.RNO AS RNO,CH.BANK AS BANK,CH.AMOUNT AS AMT,P.PTIME AS PTIME,P.TOTAL AS TOTAL,C.SEARCHKEY AS SKEY FROM CHEQUE CH JOIN PAYMENTS_ARV P ON CH.RNO=P.RECEIPT JOIN CUSTOMERS C ON P.CUSTOMER=C.ID  WHERE CH.REF=?"
                             +" UNION ALL SELECT CH.CHEQUENO AS CHNO ,CH.RNO AS RNO,CH.BANK AS BANK,CH.AMOUNT AS AMT,P.PTIME AS PTIME,P.TOTAL AS TOTAL,C.SEARCHKEY AS SKEY FROM CHEQUE CH JOIN PAYMENTS P ON CH.RNO=P.RECEIPT JOIN CUSTOMERS C ON P.CUSTOMER=C.ID  WHERE CH.REF=?"+
                             "UNION ALL SELECT CH.CHEQUENO AS CHNO ,CH.RNO AS RNO,CH.BANK AS BANK,CH.AMOUNT AS AMT,P.PTIME AS PTIME,P.TOTAL AS TOTAL,P.CUSTOMER AS SKEY FROM CHEQUE CH JOIN PAYMENTS_ARV P ON CH.RNO=P.RECEIPT AND P.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)  WHERE CH.REF=?"
                             +" UNION ALL SELECT CH.CHEQUENO AS CHNO ,CH.RNO AS RNO,CH.BANK AS BANK,CH.AMOUNT AS AMT,P.PTIME AS PTIME,P.TOTAL AS TOTAL,P.CUSTOMER AS SKEY FROM CHEQUE CH JOIN PAYMENTS P ON CH.RNO=P.RECEIPT AND P.CUSTOMER NOT IN (SELECT ID FROM CUSTOMERS)  WHERE CH.REF=?) ORDER BY ptime"
                             ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(Chequedetail.class) ).list(new Object[]{tid,tid,tid,tid});
                   if(chdetail!=null && chdetail.size()>0){
                     index++;
                     mindex=index;
                   // this.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
                     MyTableModel model=getTableModel();
                     model.setList(chdetail);
                     model.setHeader(ChequeHEADER);
                     jTable1.setModel(model);
                     tablemodellist.add(model);
                   }
            }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
      }

    }
    private void forwardAction(){
         if(jButton2.isEnabled()==true && index<tablemodellist.size()){
        index--;
        jTable1.setModel(tablemodellist.get(index));
        if(index!=0){
           //jTable1.setModel(tablemodellist.get(index));
              MyTableModel m=(MyTableModel)jTable1.getModel();
              this.setTitle("Created By :"+m.getList().get(0).getCreatedby()+" Date :"+m.getList().get(0).getDate());
              if(m.getList().get(0).getDeactby()!=null){
                        jLabel6.setText("Deactivated Date");
                        jLabel7.setText(Formats.DATE.formatValue(m.getList().get(0).getDeactdate()));
                        jLabel8.setText("Deactivated By");
                        jLabel9.setText(m.getList().get(0).getDeactby());
                        jLabel6.setVisible(true);
                        jLabel7.setVisible(true);
                        jLabel8.setVisible(true);
                        jLabel9.setVisible(true);
               }else{
                      jLabel6.setVisible(false);
                      jLabel7.setVisible(false);
                      jLabel8.setVisible(false);
                      jLabel9.setVisible(false);
                }
             
        }
        jButton1.setEnabled(true);
        
      }
          if(index==0)
            jButton2.setEnabled(false);
    }
    public void showDialog(List<AuditTrail.Detail> list,String narration,String title,String id,String pref,Date d,String tno,String accountid,String tid){
        int flag=0;
        transtno=tno;
        accid=accountid;
        this.tid=tid;
        jLabel4.setText("Date");
        
        
        jLabel5.setText(Formats.DATE.formatValue(d));
        if(title.equals("Receipt") ){
            jLabel1.setText(title+" Voucher");
            if(pref==null)
                flag=1;
        }else if(title.equals("Payments") ){
            jLabel1.setText(title+" Voucher");
            if(pref==null)
                flag=1;
        } else if(title.equals("Journal") ){
            jLabel1.setText(title+" Voucher");
            if(pref==null)
                flag=1;
        } else if(title.equals("Contra") ){
            jLabel1.setText(title+" Voucher");
            if(pref==null)
                flag=1;
            if(narration.startsWith("Cheque nos"))
                flag=2;
        } else if(title.trim().equals("Purchase Journal") ){
            jLabel1.setText("Purchase Journal");
            if(pref==null)
                flag=1;
            jCheckBox1.setVisible(true);
        } else if(title.trim().equals("C Receipt") ){
            jLabel1.setText("Receipt");
        } else if(title.equals("Opening Balance") ){
            jLabel1.setText(title);
            flag=1;
        }else if(title.equals("Cheque handover") ){
            jLabel1.setText(title);
            flag=1;
        }else if(title.equals("Cash handover") ){
            jLabel1.setText(title);
            flag=1;
        }else{
            if(title.equals("Bar"))
                flag=2;
            else
                flag=1;
            jLabel1.setText("Bill");
            //flag=2;
        }
       oldtid=pref;
       jTextArea1.setText(narration);
       jTextArea1.setEditable(false);
       jLabel2.setText("Detail");
       jLabel3.setText("Narration");
       //String[] HEADERS = {"Account","Debit","Credit"};
       MyTableModel model=getTableModel();
       model.setList(list);
       model.setHeader(HEADERS);
       jTable1.setModel(model);
       tablemodellist.add(model);
       jButton2.setEnabled(false);
       if(flag==1){
           jButton1.setEnabled(false);
       }else if(flag==2){
           jButton1.setToolTipText("Detail");
       } else if(flag==0){
           jButton1.setToolTipText("Previous Entry");
       }
       setVisible(true);
    }
       private abstract class MyTableModel extends AbstractTableModel{
            public List list;
            public String[] HEADERS;
            private boolean flag=false;
            public void setList(List l){
            }
            public void setHeader(String[] HEADERS){
            }
            public List<AuditTrail.Detail> getList(){
                 return list;
            }
            public void setFlag(boolean flag){
               this.flag=flag;
            }
            public boolean getFlag(){
                return flag;
            }
       }
       private MyTableModel getTableModel() {
        return new MyTableModel() {
            @Override
            public void setList(List l){
                list=l;
            }
            @Override
            public void setHeader(String[] HEADERS){
                this.HEADERS=HEADERS;
            }
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS[column]);
            }
            public int getRowCount() {
                return list.size();
            }
            public int getColumnCount() {

                return HEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                if(list.get(row) instanceof AuditTrail.Detail){
                     AuditTrail.Detail l = (AuditTrail.Detail)list.get(row);
                     switch (column) {
                         case 0: return l.getAccount();
                         case 1: if(l.getTranstype().equals("D"))
                                  return l.getAmount();
                                else
                                  return null;
                         case 2: if(l.getTranstype().equals("C"))
                                  return l.getAmount();
                                else
                                  return null;
                        default: return null;
                    }
                }else if(list.get(row) instanceof RBDetail){
                     RBDetail l = (RBDetail)list.get(row);
                     if(getFlag()){
                       switch (column) {
                        case 0: return l.getName();
                        case 1: return l.getQty();
                        case 2: return l.getRate();
                        case 3: return l.getTax();
                        case 4: return (l.getRate() *l.getQty())+l.getTax();
                        default: return null;
                       }
                     }else{
                     switch (column) {
                        case 0: return l.getName();
                        case 1: return l.getQty();
                        case 2: return l.getRate();
                        case 3: return l.getRate() *l.getQty();
                        default: return null;
                     }
                     }
                }else if(list.get(row) instanceof ReceiptDetail){
                     ReceiptDetail l = (ReceiptDetail)list.get(row);
                     switch (column) {
                        case 0: return l.getFname();
                        case 1: return l.getNarration();
                        case 2: return l.getOrgAmount();
                        case 3: return l.getAmount();
                        case 4: return l.getBalAmount();
                        
                        default: return null;
                     }
                }else if(list.get(row) instanceof Chequedetail){
                     Chequedetail l = (Chequedetail)list.get(row);
                     switch (column) {
                        case 0: return Formats.DATE.formatValue(l.getDate());
                        case 1: return l.getChequeno();
                        case 2: return l.getBank();
                        case 3: if(l.getAmount()==null)
                                   return l.getTotal();
                                else
                                   return l.getAmount();
                        case 4: return l.getMemno();
                        case 5: return l.getReceiptNo();
                        default: return null;
                     }
                }else
                    return null;
                
            }
        };
    }
       public static class RBDetail implements SerializableRead{
          private String name;
          private double rate;
          private int qty;
          private double tax;
          private Timestamp createddate;
          private String createdby;

          public void readValues(DataRead dr) throws BasicException {
             name=dr.getString(1);
             rate=dr.getDouble(3);
             qty=dr.getInt(2);
             createddate=dr.getTimestamp(4);
             createdby=dr.getString(5);
             tax=dr.getDouble(6);
          }
          public double getTax(){
             return tax;
          }
         public String getName(){
           return name;
         }
         public double getRate(){
            return rate;
         }
         public int getQty(){
            return qty;
         }
         public String getCreatedby(){
            return createdby;
         }
         public Timestamp getDate(){
            return createddate;
         }
         private String getDeactby(){
            return null;
         }
       }

        public static class Chequedetail implements SerializableRead{
          private String chequeno;
          private String bank;
          private String memno;
          private String amount;
          private Timestamp date;
          private String rno;
          private String total;
            //CH.CHEQUENO,CH.RNO,CH.BANK,CH.AMOUNT,P.PTIME,P.TOTAL,C.SEARCHKEY
          public void readValues(DataRead dr) throws BasicException {
             chequeno=dr.getString(1);
             rno=dr.getString(2);
             bank=dr.getString(3);
             memno=dr.getString(7);
             date=dr.getTimestamp(5);
             total=String.valueOf(dr.getDouble(6));
             amount=String.valueOf(dr.getDouble(4));
          }
          public String getTotal(){
           return total;
          }
          public String getReceiptNo(){
             return rno;
          }
         public String getChequeno(){
           return chequeno;
         }
         public String getBank(){
            return bank;
         }
         public String getMemno(){
            return memno;
         }
         public String getAmount(){
            if(amount==null)
                return total;
            else
                return amount;
         }
         public Timestamp getDate(){
            return date;
         }
         //private String getDeactby(){
           // return null;
         //}
       }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
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
            }};
            jLabel3 = new javax.swing.JLabel();
            jScrollPane2 = new javax.swing.JScrollPane();
            jTextArea1 = new javax.swing.JTextArea();
            jLabel6 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jSeparator1 = new javax.swing.JSeparator();
            jCheckBox1 = new javax.swing.JCheckBox();
            jLabel10 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jButton1.setText("jButton1");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setText("jButton2");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("jLabel1");

            jLabel4.setText("jLabel4");

            jLabel5.setText("jLabel5");

            org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(91, 91, 91)
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 292, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 105, Short.MAX_VALUE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel5)
                        .add(jLabel4))
                    .addContainerGap())
                .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jLabel2.setText("jLabel2");

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

            jLabel3.setText("jLabel3");

            jTextArea1.setColumns(20);
            jTextArea1.setRows(5);
            jScrollPane2.setViewportView(jTextArea1);

            jLabel6.setText("jLabel6");

            jLabel7.setText("jLabel7");

            jLabel8.setText("jLabel8");

            jLabel9.setText("jLabel9");

            jCheckBox1.setText("jCheckBox1");
            jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jCheckBox1ActionPerformed(evt);
                }
            });

            jLabel10.setForeground(new java.awt.Color(200, 2, 2));
            jLabel10.setText("*  Note :  Duplicate/multiple narration shown above to show different ");

            jLabel11.setForeground(new java.awt.Color(189, 17, 17));
            jLabel11.setText("narration in system generated entries.");

            org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .add(75, 75, 75)
                            .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 230, Short.MAX_VALUE)
                            .add(jCheckBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(jLabel10))
                            .add(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
                .add(layout.createSequentialGroup()
                    .add(80, 80, 80)
                    .add(jLabel11)
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(5, 5, 5)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(jCheckBox1))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel3)
                    .add(11, 11, 11)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel6)
                        .add(jLabel7)
                        .add(jLabel8)
                        .add(jLabel9))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(jLabel10)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel11)
                    .addContainerGap(20, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     backwardAction();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      forwardAction();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
           // if( tablemodellist.get(index).getList().size()>0){
           // AuditTrail.Detail ad=(AuditTrail.Detail) tablemodellist.get(index).getList().get(0);
            try {
                List<RBDetail> list = new PreparedSentence(m_App.getSession(), "SELECT PDT.NAME,PI.RATE,PI.QTY,P.CRDATE,P.CREATEDBY,PI.TAXTOTAL FROM PURCHASEJOURNALMAIN P JOIN PURCHASEJOURNAL PI ON P.ID=PI.PARENT JOIN PRODUCTS PDT ON PI.ITEM=PDT.ID WHERE P.ID=? "
                        , SerializerWriteString.INSTANCE, new SerializerReadClass(RBDetail.class)).list(tid);
                if (list.size() > 0) {
                    //jButton1.setEnabled(false);
                    //index++;
                   // mindex = index;
                  //  this.setTitle("Created By :" + list.get(0).getCreatedby() + " Date :" + list.get(0).getDate());
                    MyTableModel model = getTableModel();
                    model.setList(list);
                    model.setFlag(true);
                    model.setHeader(HEADERS3);
                    jTable1.setModel(model);
                    //tablemodellist.add(model);
                   // jButton2.setEnabled(true);
                } 
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
          //}
        }else{
           jTable1.setModel(tablemodellist.get(index));
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}

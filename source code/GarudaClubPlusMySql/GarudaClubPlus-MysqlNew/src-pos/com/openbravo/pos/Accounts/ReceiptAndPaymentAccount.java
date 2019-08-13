/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReceiptAndPaymentAccount.java
 *
 * Created on 08-Dec-2009, 12:26:26
 */

package com.openbravo.pos.Accounts;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.JMessageDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountReports.AccountsList;
import com.openbravo.pos.Accounts.AuditTrail.Detail;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JTreeTable;
import com.openbravo.pos.clubmang.TreeTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComponentInputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.ExpandVetoException;

/**
 *
 * @author swathi
 */
public class ReceiptAndPaymentAccount extends javax.swing.JPanel implements JPanelView, BeanFactoryApp  {

    /** Creates new form ReceiptAndPaymentAccount */
    private TreeTableModel treetable;
    private waitDialog w;
    private JTreeTable tree;
    private ReceiptAndPaymentLogic rplogic;
    private String accname=null;
    private AppView m_App;
    private int i=0;
    private AccountsList acclist=null;
    private Account acc;
    private String accountid=null;
    private JTable table;
    private List<AccountsList> ledgerlist=new ArrayList<AccountsList>();
    private final static String[] HEADERS1 = {"Date","Account","Reference","Debit","Credit","Created by"};
    private List<JComponent> tablelist;
    public ReceiptAndPaymentAccount() {
        initComponents();
    }
    public String getTitle() {
       return "Receipt And Payment Statement";
    }

    public void activate() throws BasicException {
        
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
       return this;
    }
    public void init(AppView app) throws BeanFactoryException {
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
        m_App=app;
        jTextField1.setEditable(false);
       jTextField2.setEditable(false);
       jLabel1.setText("From");
       jLabel2.setText("To");
       jButton1.setText("From");
       jButton2.setText("To");
       jButton3.setText("Execute");
        jButton5.setText("Back");
       jButton4.setText("Preview Report");
       jButton4.setEnabled(false);
       jCheckBox1.setText("Detailed");
       jCheckBox1.setSelected(true);
    }

    public Object getBean() {
       return this;
    }
     private void TreeTableMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
               Account a=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign()!=null && (a.getSign().equals("S") || a.getSign().equals("PDT") )){
                  acc=a;
                  forward(a.getSign());
               }
             }
        }catch(Exception e){
          e.printStackTrace();
        }
    }
      private void TreeTableKeyPressed(java.awt.event.KeyEvent evt){
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
                   Account a=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign()!=null && (a.getSign().equals("S") || a.getSign().equals("PDT") )){
                    acc = a;
                    forward(a.getSign());
               }
          }
    }

     private void loadDetail() throws BasicException{
         List<Detail> list= new PreparedSentence(m_App.getSession()
                            , "SELECT A.DATE,AM.NAME,A.TRANSTYPE,A.NARRATION,A.AMOUNT,PAYMENTREF,A.TRANSREF,A.CREATEDBY,A.DATEOFENTRY,A.DEACTDATE,A.DEACTBY,A.DEACTREF FROM ACCOUNTJOURNAL A JOIN ACCOUNTMASTER AM ON A.ACCOUNTID=AM.ID WHERE A.TID=?"
                            , SerializerWriteString.INSTANCE, new SerializerReadClass(Detail.class)).list(acclist.getTid());
         TransactionDetail t=new TransactionDetail(new JFrame(), true,m_App);
         if(list.size()>0){
                 t.setTitle("Created By :"+list.get(0).getCreatedby()+" Date :"+list.get(0).getDate());
                 t.showDialog(list, acclist.getNarration(),acclist.getTransref(),acclist.getID(),acclist.getPaymentRef(),acclist.getDate(),acclist.getTransno(),accountid,acclist.getTid());
         }
    }
      private void forward(String sign){
        {
         w=new waitDialog(new JFrame(), true);
         int h=w.getSize().height;
         int w1=w.getSize().width;
         Toolkit toolkit = Toolkit.getDefaultToolkit();
		 Dimension scrnsize = toolkit.getScreenSize();
         w.setLocation( scrnsize.width/2-w1,scrnsize.height/2-h);
         int row=0;
         if(jScrollPane1.getViewport().getView() instanceof JTreeTable){
            row=tree.getSelectedRow();
            acc=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
            accname=acc.getAccountName();
            accountid=acc.getID();
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
							loadReport(jTextField1.getText(),jTextField2.getText(),acc);
                           }else{
                             loadDetail();
                           }
                            w.hideDialog();
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
                            w.hideDialog();
						}
					}
				}
			);
            t.start();
            w.showDialog("Please wait.Loading Report...");
         }
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

       public void backwardAction(){
        back();
    }
    private void back(){
        jScrollPane1.setViewportView(tree);
        try{
           if(tree.getRowCount()>0){
               tree.setRowSelectionInterval(0, 0);
           }
           java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                   tree.requestFocus();
                }
            });
        }catch(Exception e){
          e.printStackTrace();
        }
    }
    public void forwardAction(){
         if(jScrollPane1.getViewport().getView() instanceof JTreeTable){
             Account a=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
             forward(a.getSign());
         }else
             forward("");
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

     private void loadReport(String from,String to,Account acc1) throws BasicException, SQLException{
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
          AccountReports rep=new AccountReports(m_App);
          String addl=" and a.tid in (select a.tid from accountjournal a  join accountmaster am on a.accountid=am.id and  (am.parent='1.1.1.2' or am.parent='1.1.2') where  a.transref!='Contra' and a.transref !='Cash handover' and a.transref !='Cheque handover') ";
          ledgerlist = rep.loadReport(rscal,recal,fscal,fecal,acc1.getID(),list,addl);
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

       private void TableMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
                  forward("");
             }
        }catch(Exception e){
          e.printStackTrace();
        }
    }
    private void TableKeyPressed(java.awt.event.KeyEvent evt){
         if(evt.getKeyText(evt.getKeyCode()).equals("Enter")){
                    forward("");
          }
    }
    private void setAsInvisible(List<Account> list,Map<String,Account> map){
         for(Account acc1:list){
            Account account=map.get(acc1.getSKey());
            account.setVisible(false);
            if(acc1.getAccountList().size()>0)
                setAsInvisible(acc1.getAccountList(), map);
            map.put(acc1.getSKey(), acc1);

        }
    }
    private void treeCollapsed(TreeExpansionEvent event){
        Account a=(Account)event.getPath().getLastPathComponent();
        Map<String,Account> map=rplogic.getMap();
        a.setChilPrintStatus(false);
        map.put(a.getSKey(), a);
        setAsInvisible(a.getAccountList(), map);
    }
    private void treeExpanded(TreeExpansionEvent event){
        Account a=(Account)event.getPath().getLastPathComponent();
        Map<String,Account> map=rplogic.getMap();
        for(Account acc1:a.getAccountList()){
            acc1.setVisible(true);
            map.put(acc1.getSKey(), acc1);
        }

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jRadioZeroBalance = new javax.swing.JRadioButton();

        setLayout(null);

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

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

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .add(28, 28, 28)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(jCheckBox1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .add(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2)
                    .add(jCheckBox1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 690, 86);

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

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 680, 480);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(400, 90, 130, 23);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(550, 90, 130, 23);

        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(280, 90, 110, 23);

        jRadioZeroBalance.setText("Zero Balance Ignore");
        add(jRadioZeroBalance);
        jRadioZeroBalance.setBounds(50, 90, 150, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField1.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, 1);
            date.setTime(cal.getTimeInMillis());
            jTextField1.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date date;
        try {
            date = (Date) Formats.DATE.parseValue(jTextField2.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendar(this, date);
        if (date != null) {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            date.setTime(cal.getTimeInMillis());
            jTextField2.setText(Formats.DATE.formatValue(date));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
             
            Date sdate = (Date) Formats.DATE.parseValue(jTextField1.getText());
            Date edate = (Date) Formats.DATE.parseValue(jTextField2.getText());
            if(sdate!=null && edate != null)
            {
            rplogic= new ReceiptAndPaymentLogic(sdate, edate,m_App);
            rplogic.setZeroIgnoreFlag(jRadioZeroBalance.isSelected());//pratima
            try {
                rplogic.compute();
            } catch (SQLException ex) {
                Logger.getLogger(ReceiptAndPaymentAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(jCheckBox1.isSelected())
                treetable = rplogic.treetableModel1(rplogic.getMainElementDetail());
            else
                treetable = rplogic.treetableModel(rplogic.getMainElement());
           // else
           //     treetable = rplogic.treetableModel(rplogic.getMainElement());
            tree=new JTreeTable(treetable);
            DefaultTableCellRenderer dtcr =new DefaultTableCellRenderer();
            dtcr.setHorizontalTextPosition(JLabel.RIGHT);
            dtcr.setHorizontalAlignment(JLabel.RIGHT);
            //tree.getColumnModel().getColumn(1).setCellRenderer(dtcr);
            tree.setDefaultRenderer(tree.getColumnClass(1), dtcr);
            tree.updateUI();
           // tree.getColumn(0).setPreferredWidth(350);
           // tree.getColumn(1).setPreferredWidth(100);

            //jScrollPane1.removeAll();
            jScrollPane1.getViewport().setBackground(Color.white);
            jScrollPane1.setViewportView(tree);
            jScrollPane1.repaint();
            jScrollPane1.revalidate();
            tree.revalidate();
            tree.repaint();
            tree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeTableMouseClicked(evt);
             }
            });
            tree.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
             public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeTableKeyPressed(evt);
             }
            });
            tree.getTree().addTreeWillExpandListener(new TreeWillExpandListener() {

                public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                    treeExpanded(event);
                }

                public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                    treeCollapsed(event);
                }
            });
            jButton4.setEnabled(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please Select Start and End date ", "", JOptionPane.WARNING_MESSAGE);
            }
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected())
                treetable = rplogic.treetableModel1(rplogic.getMainElementDetail());
            else
                treetable = rplogic.treetableModel(rplogic.getMainElement());
        rplogic.refresh();
        tree=new JTreeTable(treetable);
        DefaultTableCellRenderer dtcr =new DefaultTableCellRenderer();
            dtcr.setHorizontalTextPosition(JLabel.RIGHT);
            dtcr.setHorizontalAlignment(JLabel.RIGHT);
            //tree.getColumnModel().getColumn(1).setCellRenderer(dtcr);
            tree.setDefaultRenderer(tree.getColumnClass(1), dtcr);
            tree.updateUI();
        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setViewportView(tree);
        jScrollPane1.repaint();
        jScrollPane1.revalidate();
        tree.revalidate();
        tree.repaint();
        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TreeTableMouseClicked(evt);
            }
        });
        tree.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreeTableKeyPressed(evt);
            }
        });
        tree.getTree().addTreeWillExpandListener(new TreeWillExpandListener() {

                public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                   treeExpanded(event);
                }

                public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                    treeCollapsed(event);
                }
        });
       
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // if(jCheckBox1.isSelected()){
            Date sdate = (Date) Formats.DATE.parseValue(jTextField1.getText());
            Date edate = (Date) Formats.DATE.parseValue(jTextField2.getText());
            FinancialReportPrinter frp = new FinancialReportPrinter(rplogic.getMap(), tree.getTree(), m_App, jCheckBox1.isSelected(), sdate, edate,"Receipt And Payment Account");
            frp.generateJasperReport();
            // }
        } catch (BasicException ex) {
           JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, ex.getMessage(), ex));
        }
       // }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        back();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioZeroBalance;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables





}

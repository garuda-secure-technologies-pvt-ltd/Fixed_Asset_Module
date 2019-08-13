package com.openbravo.pos.Accounts;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.AccountReports.AccountsList;
import com.openbravo.pos.Accounts.AuditTrail.Detail;
import com.openbravo.pos.Accounts.DivisionalAccountMaster.DivisionMasterBean;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.JTreeTable;
import com.openbravo.pos.clubmang.TreeTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import javax.swing.JComponent;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class DivisionalIncomeAndExpanditureReport extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form IncomeAndExpenditureAccount */
    private TreeTableModel treetable;
    private waitDialog w;
    private JTreeTable tree;
    private DivisionalCalculationLogic clogic;
    private AppView m_App;
    private Account acc;
    private String accountid=null;
    private String accname=null;
    private JTable table;
    private List<AccountsList> ledgerlist=new ArrayList<AccountsList>();
    private List<JComponent> tablelist;
    private AccountsList acclist=null;
    private ComboBoxValModel divModel;
    private final static String[] HEADERS1 = {"Date","Account","Reference","Debit","Credit","Created by"};
    private final static String[] HEADERS2 = {"Qty","Rate","Total"};
    private DataLogicSales dlsales;
    public DivisionalIncomeAndExpanditureReport() {
        initComponents();
    }
     public String getTitle() {
        return "Divisional Income And Expenditure Statement";
    }

    public void activate() throws BasicException {
        List<DivisionMasterBean> divList = new StaticSentence(m_App.getSession(), "SELECT dam.ID, dam.NAME, dam.ACTIVE, dam.CRBY, dam.CRDATE, count(dal.divid) as totlinkcount from divisionalaccountmaster dam left join divisionalaccountlink dal on (dam.id = dal.divid and dal.active is true) where dam.active is true group by dam.id"
                   ,SerializerWriteString.INSTANCE
                   ,new SerializerReadClass( DivisionalAccountMaster.DivisionMasterBean.class )).list();
        DivisionMasterBean dmb = new DivisionalAccountMaster.DivisionMasterBean();
        dmb.setId("All");
        dmb.setName("All");
         divList.add(0,dmb);
           DivisionMasterBean dmb2 = new DivisionalAccountMaster.DivisionMasterBean();
           dmb2.setId("General I&E");
        dmb2.setName("General I&E");
         divList.add(dmb2);
             divModel = new ComboBoxValModel(divList);
             jComboBox1.setModel(divModel);
        clogic=new DivisionalCalculationLogic();
        tree=new JTreeTable();
        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setViewportView(tree);
        jScrollPane1.repaint();
        jScrollPane1.revalidate();
        tree.revalidate();
        tree.repaint();
    }

     private void TreeTableMouseClicked(java.awt.event.MouseEvent evt){
         try{
             if(evt.getClickCount()==2){
               Account a=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
               if(a.getSign()!=null/* && (a.getSign().equals("S") || a.getSign().equals("PDT") )*/){
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
               if(a.getSign()!=null /*&& (a.getSign().equals("S") || a.getSign().equals("PDT")*/ ){
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
         if(sign.equals("PDT")){
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
            acc=(Account) tree.getTree().getSelectionPath().getLastPathComponent();
           table.setModel(getProductBreakUpDetail(acc));
           if(table.getRowCount()>0){
               table.setRowSelectionInterval(0, 0);
           }
          jScrollPane1.setViewportView(table);
          jScrollPane1.revalidate();
          jScrollPane1.repaint();
          table.revalidate();
          table.repaint();
         }else{
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
       cal.set(Calendar.AM_PM, Calendar.PM);
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
          ledgerlist = rep.loadReport(rscal,recal,fscal,fecal,acc1.getID(),list,"");
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
        public AbstractTableModel getProductBreakUpDetail(final Account a) {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS2[column]);
            }
            public int getRowCount() {
                return a.getBreakUpList().size();
            }
            public int getColumnCount() {

                return HEADERS2.length;
            }
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public Object getValueAt(int row, int column) {
                Object[] obj = a.getBreakUpList().get(row);
                double qty=0.0,rate=0.0;
                qty = Formats.ApproxTo2Decimals(Double.valueOf(obj[0].toString()));
                rate = Formats.ApproxTo2Decimals(Double.valueOf(obj[1].toString()));
                
                switch (column) {
                    case 0 : return qty;
                    case 1 : return rate;
                    case 2 : return Formats.ConvertDoubleToString(qty* rate);

                default: return null;
                }
            }
        };
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        dlsales=(DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
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
       jButton4.setEnabled(true);
    }

    public Object getBean() {
       return this;
    }
    private void setAsInvisible(List<Account> list,Map<String,Account> map){
         for(Account acc1:list){
            Account account=map.get(acc1.getSKey());
            if(account!=null)
            account.setVisible(false);
            if(acc1.getAccountList().size()>0)
                setAsInvisible(acc1.getAccountList(), map);
            map.put(acc1.getSKey(), acc1);

        }
    }
    private void treeCollapsed(TreeExpansionEvent event){
        Account a=(Account)event.getPath().getLastPathComponent();
        Map<String,Account> map=clogic.getMap();
        a.setChilPrintStatus(false);
         map.put(a.getSKey(), a);
        setAsInvisible(a.getAccountList(), map);
    }
    private void treeExpanded(TreeExpansionEvent event){
        Account a=(Account)event.getPath().getLastPathComponent();
        Map<String,Account> map=clogic.getMap();
        for(Account acc1:a.getAccountList()){
            acc1.setVisible(true);
            map.put(acc1.getSKey(), acc1);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setLayout(null);

        jLabel1.setText("jLabel1");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Division");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 670, 130);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(360, 140, 140, 23);
        add(jScrollPane1);
        jScrollPane1.setBounds(10, 170, 670, 440);

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(520, 140, 160, 23);

        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(210, 140, 140, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
             Date sdate = (Date) Formats.DATE.parseValue(jTextField1.getText());
            Date edate = (Date) Formats.DATE.parseValue(jTextField2.getText());
            if(sdate!=null && edate!=null && jComboBox1.getSelectedIndex()!=-1)
            {
                
            clogic = new DivisionalCalculationLogic(sdate, edate, m_App,dlsales);
            
            DivisionMasterBean d = (DivisionMasterBean) jComboBox1.getSelectedItem();
            
            if(!d.getId().equals("General I&E"))
            {
            clogic.Calculate(d.getId());
            }
            else
            {
                clogic.Calculate();
            }
            
            
            treetable = clogic.treetableModel(clogic.getMainAccount());
            tree=new JTreeTable(treetable);
           // tree.getColumn(0).setPreferredWidth(350);
           // tree.getColumn(1).setPreferredWidth(100);
            DefaultTableCellRenderer dtcr =new DefaultTableCellRenderer();
            dtcr.setHorizontalTextPosition(JLabel.RIGHT);
            dtcr.setHorizontalAlignment(JLabel.RIGHT);
            //tree.getColumnModel().getColumn(1).setCellRenderer(dtcr);
            tree.setDefaultRenderer(tree.getColumnClass(1), dtcr);
            tree.updateUI();
            //jScrollPane1.removeAll();
            jScrollPane1.setBackground(Color.white);
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
           
            
              
        }
            else
            {
                JOptionPane.showMessageDialog(null, "Please select start and end dates along with division ", "", JOptionPane.WARNING_MESSAGE);
            }

        } catch (BasicException ex) {
            ex.printStackTrace();
            new MessageInf(ex).show(getParent());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        FinancialReportPrinter frp=new FinancialReportPrinter(clogic.getMap(), tree.getTree(), m_App,false, clogic.getSdate(), clogic.getEdate(),"Divisional Income And Expenditure Account");
        frp.generateJasperReport();
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables



}

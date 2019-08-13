/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JOfferDetail.java
 *
 * Created on Feb 4, 2009, 12:22:43 PM
 */

package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.PeopleInfo;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 *
 * @author swathi
 */
public class JOfferDetail  extends javax.swing.JPanel implements JPanelView, BeanFactoryApp  {

    /** Creates new form JOfferDetail */
    private DataLogicSales m_dlSales;
    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private OfferTableDetail offermodel = null;
    private ComboBoxValModel cmodel;
    private String pid;
    public JOfferDetail() {

        initComponents();
    }
    public void init(AppView app) throws BeanFactoryException {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        offertable.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[] {Formats.STRING,Formats.STRING,Formats.DOUBLE,Formats.CURRENCY,Formats.DOUBLE,Formats.CURRENCY,Formats.TIMESTAMP,Formats.TIMESTAMP,Formats.BOOLEAN}));
      //  offertable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
        offertable.getTableHeader().setReorderingAllowed(false);
        offertable.setRowHeight(25);
        offertable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField4.setText(null);
        jTextField5.setText(null);
        //jTextField6.setVisible(false);
         //jTextField6.setText(null);
        jLabel9.setText("Applicable To");
        brate.setText(null);
        grate.setText("0");
    }
     public Object getBean() {
        return this;
    }

    public JComponent getComponent() {
        return this;
    }

   public String getTitle() {
        return "Offer Details";
    }

    public void activate() throws BasicException {
       // m_dlSales.getActivePeopleListWithOutCCAcc();
        List<PeopleInfo> pList=m_dlSales.getActivePeopleListWithOutCCAcc().list();
        List<PeopleInfo> pList1=new ArrayList<PeopleInfo>();
         for(int i=0;i<pList.size();i++){
               PeopleInfo pinfo=pList.get(i);
               //warehouse changes -start
                Object[] obj2=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ID,NAME,APPPASSWORD,CARD,ROLE,IMAGE,LOGINTIME,CLOSECASHTIME,OPENCASHTIME,CLOSESALE,OPENSALE,PRCATEGORIES FROM PEOPLE WHERE PEOPLE.ID=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})).find(pinfo.getID());

             if(obj2!=null){
                 String warehouse = null;
                if (obj2[11] != null) {
                    String[] wArr = obj2[11].toString().split("#");
                    warehouse = wArr[0];
                }
             AppUser appuser1=new AppUser(obj2[0].toString(), obj2[1].toString(),obj2[4].toString(),warehouse);
               //warehouse changes -end
             appuser1.fillPermissions(m_dlSystem);
             if(appuser1.hasPermission("sales")){
                  pList1.add(pinfo);
             }

             }
           }
        jTextField4.setText(null);
        jTextField5.setText(null);
        PeopleInfo pinfo=new PeopleInfo();
        pinfo.setID("ALL");
        pinfo.setName("ALL");
        pList1.add(0,pinfo);
        cmodel=new ComboBoxValModel(pList1);
        jComboBox1.setModel(cmodel);
        ButtonGroup();
        searchkey_radio.setSelected(true);
        loadData();
    }

     private void loadData() throws BasicException {
        //jComboBox1.setModel(cmodel);
         
         String OrderBy="";
        jComboBox1.setSelectedIndex(-1);
        if(searchkey_radio.isSelected()){
            OrderBy="RefCode";
        }
        if(date_radio.isSelected()){
             OrderBy="DateWise";
        }
        if(productName_radio.isSelected()){
            OrderBy="ProductWise";
        }
        offermodel = OfferTableDetail.loadInstance(m_App,OrderBy);
        offertable.setModel(offermodel.getdiscountTableModel());
        //jTextField6.setVisible(false);

        TableColumnModel jColumns = offertable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(60);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(140);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(60);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(60);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(50);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(50);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setPreferredWidth(120);
        jColumns.getColumn(6).setResizable(false);
        jColumns.getColumn(7).setPreferredWidth(120);
        jColumns.getColumn(7).setResizable(false);
        jColumns.getColumn(8).setPreferredWidth(60);
        jColumns.getColumn(8).setResizable(false);
        
         

     }
   public void ButtonGroup(){
       ButtonGroup bg = new ButtonGroup();
       bg.add(searchkey_radio);
       bg.add(date_radio);
       bg.add(productName_radio);
   }  
   public boolean deactivate() {
        return true;
    }
   private void insertOffer() throws BasicException, ParseException
   {
       if(jTextField1.getText()!=null && jTextField2.getText()!=null && jTextField3.getText()!=null && jTextField4.getText()!=null && jTextField5.getText()!=null && brate.getText()!=null && grate.getText()!=null && jComboBox1.getSelectedIndex()!=-1)
       {
        
       String id=UUID.randomUUID().toString();
       String appto=String.valueOf(jComboBox1.getSelectedItem());
       String name=pid; // Akshatha : pid fetches the product id instead of the query commented bellow

//         Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
//                        , "SELECT ID FROM PRODUCTS WHERE NAME = ? AND ID = ?"   //Akshatha : to fetch the correct product id
//                       ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
//                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(new Object[]{jTextField1.getText(),pid});
//
//         if(obj==null || obj[0]==null)
//         {
//             name="";
//             //msg
//         }
//         else
//         {
              //name=obj[0].toString();
                Object[] coun =(Object[]) new StaticSentence(m_App.getSession()
                ,"SELECT ID "
                +"FROM OFFER WHERE PRODUCT=? AND OFFER.TODATE >= ? AND ACTIVE=TRUE AND APPTO=? "

                ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
              ,new SerializerReadBasic( new Datas[]{Datas.STRING} )).find(new Object[]{name,Formats.TIMESTAMP.parseValue(jTextField4.getText()),appto} );
                if(coun==null || coun[0]==null)
                {
                   Object[] values = new Object[] {id,name,Double.parseDouble(jTextField2.getText()),Double.parseDouble(jTextField3.getText()),Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText()),Double.parseDouble(brate.getText()),Double.parseDouble(grate.getText()),true,appto};
                  Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.DOUBLE,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING};
                   new PreparedSentence(m_App.getSession()
                , "INSERT INTO OFFER (ID,PRODUCT,BUYQTY,GETQTY,FROMDATE,TODATE,BUYRATE,GETRATE,ACTIVE,APPTO) VALUES (?,?,?,?,?,?,?,?,?,?) "
                , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5,6,7,8,9})).exec(values);

                }
                else
                   if( JOptionPane.showConfirmDialog(this, "Offer for the specified product already exit.Do u want to over ride ?", "Offer Exist", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)
                   {
                        Datas[] datas1 = new Datas[] {Datas.BOOLEAN,Datas.TIMESTAMP, Datas.STRING, Datas.STRING};
                        // Object[] values1 = new Object[] {status,obj1[0].toString(),obj2[0].toString()};
                new PreparedSentence(m_App.getSession()
                , "UPDATE OFFER SET ACTIVE = ?,deactivatedate=? WHERE PRODUCT=? AND APPTO=?"
                , new SerializerWriteBasicExt(datas1,new int[]{0,1,2,3})).exec(new Object[]{false,new Date(),name,appto});
                        Object[] values = new Object[] {id,name,Double.parseDouble(jTextField2.getText()),Double.parseDouble(jTextField3.getText()),Formats.TIMESTAMP.parseValue(jTextField4.getText()),Formats.TIMESTAMP.parseValue(jTextField5.getText()),Double.parseDouble(brate.getText()),Double.parseDouble(grate.getText()),true,appto};
                    Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.DOUBLE,Datas.DOUBLE,Datas.BOOLEAN,Datas.STRING};
                   new PreparedSentence(m_App.getSession()
                   , "INSERT INTO OFFER (ID,PRODUCT,BUYQTY,GETQTY,FROMDATE,TODATE,BUYRATE,GETRATE,ACTIVE,APPTO) VALUES (?,?,?,?,?,?,?,?,?,?) "
                  , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5,6,7,8,9})).exec(values);
                   }

         
      
       
       
       }
       else
            JOptionPane.showMessageDialog(this, "Please fill all the details", "Incomplete Data", JOptionPane.OK_OPTION);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        startdate = new javax.swing.JButton();
        enddate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        offertable =  new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        searchkey_radio = new javax.swing.JRadioButton();
        date_radio = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        productName_radio = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        brate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        grate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        pRef = new javax.swing.JTextField();

        jLabel1.setText("Product Name :");

        jLabel2.setText("Buy Quantity  :");

        jLabel3.setText("Get Quantity :");

        jLabel4.setText("From             :");

        jLabel5.setText("To                :");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        startdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        startdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startdateActionPerformed(evt);
            }
        });

        enddate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        enddate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enddateActionPerformed(evt);
            }
        });

        offertable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(offertable);

        jLabel6.setText("Offer Details :");

        jButton3.setText("Deactivate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        searchkey_radio.setText("Ref Code");
        searchkey_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchkey_radioItemStateChanged(evt);
            }
        });

        date_radio.setText("Date ");
        date_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                date_radioItemStateChanged(evt);
            }
        });

        jLabel11.setText("Order by : ");

        productName_radio.setText("Product Name");
        productName_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productName_radioItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addGap(3, 3, 3)
                                .addComponent(searchkey_radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(date_radio)
                                .addGap(18, 18, 18)
                                .addComponent(productName_radio)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(searchkey_radio)
                    .addComponent(date_radio)
                    .addComponent(jLabel11)
                    .addComponent(productName_radio))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Buy Rate :");

        jLabel8.setText("Get Rate  :");

        jLabel9.setText("jLabel9");

        jLabel10.setText("Ref Code");

        pRef.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(pRef))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(brate, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(jButton2)
                                    .addComponent(grate)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(enddate, 0, 0, Short.MAX_VALUE)
                                        .addComponent(startdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)))))
                        .addGap(22, 22, 22))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(brate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(grate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(startdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enddate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(pRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            Date d1=(Date)Formats.TIMESTAMP.parseValue(jTextField4.getText());
            Date d2=(Date)Formats.TIMESTAMP.parseValue(jTextField5.getText());
            if(d1.compareTo(d2)<0){
                insertOffer();
                jTextField1.setText(null);
                jTextField2.setText(null);
                jTextField3.setText(null);
                jTextField4.setText(null);
                jTextField5.setText(null);
                brate.setText(null);
                grate.setText("0");
                loadData();
            
            }else{
                
                JOptionPane.showMessageDialog(this, "FromDate should be before the ToDate");
                jTextField4.setText(null);
                jTextField5.setText(null);
               }
            
            
        } catch (BasicException ex) {
            Logger.getLogger(JOfferDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JOfferDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void enddateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enddateActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField5.getText());
            
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        System.out.println(date);
        if (date != null) {
            // String[] arr=date.toString().split(" ");
          
            Calendar c=Calendar.getInstance();
            c.setTime(date);
            
            System.out.println(c.get(Calendar.HOUR));
            if(c.get(Calendar.HOUR_OF_DAY)==0 && c.get(Calendar.MINUTE)==0 && c.get(Calendar.SECOND)==0){
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            
            date = c.getTime();
        }
            jTextField5.setText(Formats.TIMESTAMP.formatValue(date));
            System.out.println(jTextField5.getText());
        }
}//GEN-LAST:event_enddateActionPerformed

    private void startdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startdateActionPerformed
        // TODO add your handling code here:
        Date date;
        try {
             //String[] arr=date.toString().split(" ");
            date = (Date) Formats.TIMESTAMP.parseValue(jTextField4.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if ((date != null)) {
            if(date.after(new Date())){
          //  String[] arr=date.toString().split(" ");
            jTextField4.setText(Formats.TIMESTAMP.formatValue(date));
        }else{
                            JOptionPane.showMessageDialog(this, "FromDate should be after the Current Date");
            jTextField4.setText(null);
            }
        }
}//GEN-LAST:event_startdateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        ProductInfoExt pd=JProductFinder.showMessage(this, m_dlSales );
         jTextField1.setText(pd.getName());
         Double rate=pd.getPriceSell();
         brate.setText(rate.toString());
         pid= pd.getID(); //Akshatha : to fetch the product id
       /* try {
            //Lokesh : To show tyhe warehouse
            String obj = (String) new PreparedSentence(m_App.getSession(), "SELECT NAME FROM LOCATIONS WHERE ID = ?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(pd.getWarehouse().toString());
            if(obj!=null)
            {*/
                pRef.setText(pd.getCode());
        //    }
            
        
      //  } catch (BasicException ex) {
     //       Logger.getLogger(JOfferDetail.class.getName()).log(Level.SEVERE, null, ex);
      //  }//
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row=-1;
        row=offertable.getSelectedRow();
        if(row!=-1){
        Object pid=offermodel.getdiscountTableModel().getValueAt(row, 9);
        //Object del=offermodel.getdiscountTableModel().getValueAt(row, 5);
        Object sdate=offermodel.getdiscountTableModel().getValueAt(row, 6);
        Object edate=offermodel.getdiscountTableModel().getValueAt(row, 7);
          Datas[] datas1 = new Datas[] {Datas.BOOLEAN,Datas.TIMESTAMP,Datas.STRING};
          try{
        new PreparedSentence(m_App.getSession()
                , "UPDATE OFFER SET ACTIVE = ?,deactivatedate=? WHERE ID=? "
                , new SerializerWriteBasicExt(datas1,new int[]{0,1,2})).exec(new Object[]{false,new Date(),pid.toString()});
               loadData();

          }
          catch(Exception e){
              e.printStackTrace();
          }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please Select a row in the table", "Cannot Deactivate", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchkey_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchkey_radioItemStateChanged
        try{ 
            loadData();
        }
        catch(BasicException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_searchkey_radioItemStateChanged

    private void date_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_date_radioItemStateChanged
      try{ 
            loadData();
        }
        catch(BasicException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_date_radioItemStateChanged

    private void productName_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productName_radioItemStateChanged
        try{ 
            loadData();
        }
        catch(BasicException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_productName_radioItemStateChanged

     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brate;
    private javax.swing.JRadioButton date_radio;
    private javax.swing.JButton enddate;
    private javax.swing.JTextField grate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTable offertable;
    private javax.swing.JTextField pRef;
    private javax.swing.JRadioButton productName_radio;
    private javax.swing.JRadioButton searchkey_radio;
    private javax.swing.JButton startdate;
    // End of variables declaration//GEN-END:variables



}

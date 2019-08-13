/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PdtConversionManager.java
 *
 * Created on Jan 31, 2009, 10:07:26 AM
 */
package com.openbravo.pos.panels;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.TableRendererBasic;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
//import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.DataLogicSystem;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.inventory.PdtConversionTable;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author swathi
 */
public class PdtConversionManager extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    /** Creates new form PdtConversionManager */
    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    //private SentenceList m_sentpdt1;
    //private SentenceList m_sentpdt2;
    private List<ProductInfoExt> m_sentpdt1;
    private List<ProductInfoExt> m_sentpdt2;
    private ComboBoxValModel m_ProductModel1;
    private ComboBoxValModel m_ProductModel2;
    private DataLogicSales dlSales = null;
    private PdtConversionTable pdtconverion;
    private ComboBoxValModel m_LocationModel1;
    private ComboBoxValModel m_LocationModel2;
    private SentenceList m_selLoc;
    private String loc1;
    private String loc2;
    private Session s;

    public PdtConversionManager() throws BasicException {
        initComponents();
    //  dlSales=new DataLogicSales() ;
    // m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");



    }

    public void init(AppView app) throws BeanFactoryException {
        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        //m_sentpdt = dlSales.getProductList();
       m_selLoc = dlSales.getLocationsList();

        try {
            //m_ProductModel1 = new ComboBoxValModel(m_sentpdt.list());
            //m_ProductModel2 = new ComboBoxValModel(m_sentpdt.list());
            m_LocationModel1 = new ComboBoxValModel(m_selLoc.list());
            //m_LocationModel2 = new ComboBoxValModel(m_selLoc.list());
            pdtqty1.setText(" ");
            pdtqty2.setText(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        pdtcontable.setDefaultRenderer(Object.class, new TableRendererBasic(
                new Formats[]{Formats.STRING, Formats.DOUBLE, Formats.STRING, Formats.DOUBLE, Formats.TIMESTAMP, Formats.STRING, Formats.BOOLEAN, Formats.STRING}));
        pdtcontable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(25, 25));
        pdtcontable.getTableHeader().setReorderingAllowed(false);
        pdtcontable.setRowHeight(25);        
        pdtcontable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //productlist1.setModel(m_ProductModel1);
        //productlist2.setModel(m_ProductModel2);
        location1.setModel(m_LocationModel1);
        //location2.setModel(m_LocationModel2);
        pdtqty1.setEnabled(true);
        pdtqty1.setEditable(true);
        pdtqty2.setEnabled(true);
        pdtqty2.setEditable(true);
        pdtunittype1.setEnabled(false);
        pdtunittype2.setEnabled(false);
        productlist1.setEnabled(true);
        productlist2.setEnabled(true);
        location1.setEnabled(true);
        //location2.setEnabled(true);


    }

    public Object getBean() {
        return this;
    }

    public String getTitle() {
        return "Product Conversion Manager";
    }

    public void activate() throws BasicException {
        jButton2.setVisible(false);
        //loadData(null,null);
    }

    public void loadData(String lo1, String lo2) throws BasicException {
        pdtcontable.setModel(new DefaultTableModel());


        // LoadData
        pdtconverion = PdtConversionTable.loadInstance(m_App, lo1, lo2);
        pdtqty1.setEnabled(true);
        pdtqty1.setEditable(true);
        pdtqty2.setEnabled(true);
        pdtqty2.setEditable(true);
        productlist1.setModel(m_ProductModel1);
        productlist2.setModel(m_ProductModel2);
        pdtunittype1.setEnabled(false);
        pdtunittype2.setEnabled(false);
        productlist1.setEnabled(true);
        productlist2.setEnabled(true);

        pdtcontable.setModel(pdtconverion.getPdtConversionModel());

        TableColumnModel jColumns = pdtcontable.getColumnModel();
        jColumns.getColumn(0).setPreferredWidth(200);
        jColumns.getColumn(0).setResizable(false);
        jColumns.getColumn(1).setPreferredWidth(50);
        jColumns.getColumn(1).setResizable(false);
        jColumns.getColumn(2).setPreferredWidth(200);
        jColumns.getColumn(2).setResizable(false);
        jColumns.getColumn(3).setPreferredWidth(50);
        jColumns.getColumn(3).setResizable(false);
        jColumns.getColumn(4).setPreferredWidth(80);
        jColumns.getColumn(4).setResizable(false);
        jColumns.getColumn(5).setPreferredWidth(80);
        jColumns.getColumn(5).setResizable(false);
        jColumns.getColumn(6).setPreferredWidth(80);
        jColumns.getColumn(6).setResizable(false);  
        jColumns.getColumn(7).setPreferredWidth(00);
        jColumns.getColumn(7).setResizable(false);




    }

    public JComponent getComponent() {
        return this;
    }

    private int checkForProduct(String pdt, String location) {
        //   Object[] obj;
        try {
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT COUNT(*) FROM CONVERTER WHERE ACTIVE = TRUE AND (PRODUCTFST = ?  OR  PRODUCTSCN = ?) AND (LOCATIONFST=? OR LOCATIONSCN=?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{pdt, pdt, location, location});


            if (obj != null) {
                return Integer.parseInt(obj[0].toString());
            }//else

        } catch (Exception e) {
        }
        return 0;
    }

    public boolean deactivate() {
        // se me debe permitir cancelar el deactivate
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productlist1 = new javax.swing.JComboBox();
        productlist2 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pdtunittype1 = new javax.swing.JTextField();
        pdtunittype2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pdtqty1 = new javax.swing.JTextField();
        pdtqty2 = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pdtcontable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        location1 = new javax.swing.JComboBox();
        location2 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setLayout(null);

        productlist1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productlist1ItemStateChanged(evt);
            }
        });
        add(productlist1);
        productlist1.setBounds(360, 60, 210, 20);

        productlist2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productlist2ItemStateChanged(evt);
            }
        });
        add(productlist2);
        productlist2.setBounds(360, 100, 210, 20);

        jLabel1.setText("Product 1 :");
        add(jLabel1);
        jLabel1.setBounds(280, 60, 90, 20);

        jLabel2.setText("Product 2 :");
        add(jLabel2);
        jLabel2.setBounds(280, 100, 90, 20);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Prd.Name");
        add(jLabel3);
        jLabel3.setBounds(340, 30, 80, 14);

        pdtunittype1.setEditable(false);
        add(pdtunittype1);
        pdtunittype1.setBounds(590, 60, 50, 20);

        pdtunittype2.setEditable(false);
        add(pdtunittype2);
        pdtunittype2.setBounds(590, 100, 50, 20);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Unit Type");
        add(jLabel4);
        jLabel4.setBounds(580, 30, 60, 14);
        add(pdtqty1);
        pdtqty1.setBounds(660, 60, 50, 20);
        add(pdtqty2);
        pdtqty2.setBounds(660, 100, 50, 20);

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        add(add);
        add.setBounds(690, 140, 70, 23);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ratio");
        add(jLabel5);
        jLabel5.setBounds(660, 30, 40, 14);

        pdtcontable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(pdtcontable);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 190, 750, 340);

        jButton1.setText("Deactivate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(610, 550, 150, 23);

        location1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                location1ItemStateChanged(evt);
            }
        });
        location1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                location1ActionPerformed(evt);
            }
        });
        add(location1);
        location1.setBounds(90, 60, 160, 20);

        location2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                location2ItemStateChanged(evt);
            }
        });
        add(location2);
        location2.setBounds(90, 100, 160, 20);

        jLabel6.setText("Location 1:");
        add(jLabel6);
        jLabel6.setBounds(10, 60, 70, 20);

        jLabel7.setText("Location 2:");
        add(jLabel7);
        jLabel7.setBounds(10, 100, 70, 20);

        jLabel8.setText("Loc.Name");
        add(jLabel8);
        jLabel8.setBounds(90, 30, 70, 14);

        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(220, 140, 70, 23);
    }// </editor-fold>//GEN-END:initComponents
    private String pdtid1;
    private String pdtid2;
    private void productlist1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productlist1ItemStateChanged
        // TODO add your handling code here:
        Object pdt1 = productlist1.getSelectedItem();
        if (pdt1 != null) {
            String selectedItem = pdt1.toString();
            String[] temparr = selectedItem.split(" - ");
            try {
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT UNITTYPE,ID FROM PRODUCTS WHERE REFERENCE = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(temparr[0]);
                if (obj1 != null) {
                    if (obj1[0] != null) {
                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM UNIT WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj1[0].toString());

                        pdtunittype1.setEnabled(true);
                        pdtunittype1.setText(obj[0].toString());
                    }
                    pdtid1 = obj1[1].toString();
                }

            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_productlist1ItemStateChanged

    private void productlist2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productlist2ItemStateChanged
        // TODO add your handling code here:
        Object pdt2 = productlist2.getSelectedItem();
        if (pdt2 != null) {
            String selectedItem = pdt2.toString();
            String[] temparr = selectedItem.split(" - ");
            try {
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT UNITTYPE,ID FROM PRODUCTS WHERE REFERENCE = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).find(temparr[0]);
                if (obj1 != null) {
                    if (obj1[0] != null) {
                        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM UNIT WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(obj1[0].toString());
                        pdtunittype2.setEnabled(true);
                        pdtunittype2.setText(obj[0].toString());
                    }
                    pdtid2 = obj1[1].toString();
                }

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_productlist2ItemStateChanged

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:        
        if (!pdtqty1.getText().equals(" ") && !pdtqty2.getText().equals(" ") && productlist1.getSelectedItem() != null && productlist2.getSelectedItem() != null && location1.getSelectedItem() != null && location2.getSelectedItem() != null) {
            int count1 = checkForProduct(pdtid1, loc1);
            int count2 = checkForProduct(pdtid2, loc2);
            if (count1==0 || count2==0) {
                String selecteditem1 = productlist1.getSelectedItem().toString();
                String selecteditem2 = productlist2.getSelectedItem().toString();
                final Date dnow;
                int flag = 0;
                if (selecteditem1.equals(selecteditem2)) {
                    //warning message
                    JOptionPane.showMessageDialog(this, "Both the selected item is same", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    //   int del=pdtqty1.getText().length();
                    //   String del1=pdtqty2.getText();
                    if (!pdtqty1.getText().equals(" ") && !pdtqty2.getText().equals(" ")) {
                        try {
                            Object[] o = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM CONVERTER WHERE PRODUCTFST = ?  AND  PRODUCTSCN = ? AND LOCATIONFST=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{pdtid1, pdtid2});

                            Object[] o1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM CONVERTER WHERE PRODUCTFST = ?  AND  PRODUCTSCN = ? AND LOCATIONSCN=? AND ACTIVE = TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).find(new Object[]{pdtid2, pdtid1});
                            dnow = new Date();

                            if (o == null && o1 == null) {

                                SentenceExec unitInsert = new PreparedSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(), "INSERT INTO CONVERTER(ID,LOCATIONFST,PRODUCTFST,NOFST,LOCATIONSCN,PRODUCTSCN,NOSEC,CREATEDDATE,USER_,ACTIVE) VALUES(?,?,?,?,?,?,?,?,?,?)", SerializerWriteParams.INSTANCE);
                                unitInsert.exec(new DataParams() {

                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, loc1);
                                        setString(3, pdtid1);
                                        setDouble(4, Double.parseDouble(pdtqty1.getText()));
                                        setString(5, loc2);
                                        setString(6, pdtid2);
                                        setDouble(7, Double.parseDouble(pdtqty2.getText()));
                                        setTimestamp(8, dnow);
                                        setString(9, m_App.getAppUserView().getUser().getName().toString());
                                        setBoolean(10, true);
                                    }
                                });

                                pdtqty1.setText(" ");
                                pdtqty2.setText(" ");
                                pdtunittype1.setText(" ");
                                pdtunittype1.setText(" ");
                                //  productlist2.setSelectedItem("");
                                // productlist1.setSelectedItem("");
                                flag = 1;
                                productlist2.setSelectedIndex(-1);
                                productlist1.setSelectedIndex(-1);
                                location1.setSelectedIndex(-1);
                                location2.setSelectedIndex(-1);
                                loadData(loc1, loc2);
                            } else {
                                JOptionPane.showMessageDialog(this, "Products Already exist", "Cannot Insert", JOptionPane.OK_OPTION);
                                pdtqty1.setText(" ");
                                pdtqty2.setText(" ");
                                pdtunittype1.setText(" ");
                                pdtunittype1.setText(" ");
                                //  productlist2.setSelectedItem("");
                                // productlist1.setSelectedItem("");
                                flag = 1;
                                productlist2.setSelectedIndex(-1);
                                productlist1.setSelectedIndex(-1);
                                location1.setSelectedIndex(-1);
                                location2.setSelectedIndex(-1);
                            }

                        } catch (Exception e) {
                            // e.printStackTrace();
                            if (flag == 1) {
                                productlist1.setSelectedIndex(-1);
                            }
                            try {
                                loadData(loc1,loc2);
                            } catch (Exception e1) {
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please specify the quantities", "Cannot Insert", JOptionPane.OK_OPTION);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product already exist", "Cannot Insert", JOptionPane.OK_OPTION);
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill the form compeletely", "Cannot Insert", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_addActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = pdtcontable.getSelectedRow();
        if(row!=-1){
        Boolean status = (Boolean) pdtcontable.getValueAt(row, 6);
        String pdtfst = pdtcontable.getValueAt(row, 0).toString();
        String pdtsec = pdtcontable.getValueAt(row, 2).toString();
        //String locationfst = pdtcontable.getValueAt(row, 7).toString();
        //String locationscn = pdtcontable.getValueAt(row, 8).toString();
        String cid = pdtcontable.getValueAt(row, 7).toString();//praveen;added to get id of the selected row

        if (status == true) {
            status = false;

            Datas[] datas = new Datas[]{Datas.BOOLEAN, Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING};
            try {
                //Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS WHERE NAME = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(pdtfst);
                //Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM PRODUCTS WHERE NAME = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(pdtsec);
                //if (obj1 != null && obj2 != null) {
                   // Object[] values = new Object[]{status, obj1[0].toString(), obj2[0].toString(),locationfst,locationscn};
                   // new PreparedSentence(m_App.getSession(), "UPDATE CONVERTER SET ACTIVE = ? WHERE PRODUCTFST = ? AND PRODUCTSCN=? AND LOCATIONFST=? AND LOCATIONSCN=?", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2,3,4})).exec(values);
                    //praveen:changed above query
                    Object[] val=new Object[]{status,cid};
                    new PreparedSentence(m_App.getSession(), "UPDATE CONVERTER SET ACTIVE = ? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.BOOLEAN,Datas.STRING}),null).exec(val);

                //}
                String temp = "";
                loadData(loc1, loc2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        }else{
            JOptionPane.showMessageDialog(this, "select any row","invalid action",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void load(){
        if(loc1!=null && loc2!=null){
        try {
            m_sentpdt1 =  dlSales.getProductList(loc1);
            m_sentpdt2 =   dlSales.getProductList(loc2);
            m_ProductModel1 = new ComboBoxValModel(m_sentpdt1);
            m_ProductModel2 = new ComboBoxValModel(m_sentpdt2);
            productlist1.setModel(m_ProductModel1);
            productlist2.setModel(m_ProductModel2);
            loadData(loc1, loc2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(this, "Select location1 and location2");
        }

    }



    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        load();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void location1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_location1ItemStateChanged
        // TODO add your handling code here:

        Object loct1 = location1.getSelectedItem();
        if (loct1 != null) {
            String selectedItem = loct1.toString();
            //String[] temparr = selectedItem.split(" - ");
            try {
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedItem);
                if (obj1 != null) {
                    if (obj1[0] != null) {
                        loc1 = obj1[0].toString();
                        List<LocationInfo> list = (List<LocationInfo>) dlSales.getLocationsInfoByParent(loc1);
                        m_LocationModel2 = new ComboBoxValModel(list);
                        location2.setModel(m_LocationModel2);
                        location2.setEnabled(true);
                        System.out.println(loc1);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_location1ItemStateChanged

    private void location2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_location2ItemStateChanged
        // TODO add your handling code here:
        Object loct2 = location2.getSelectedItem();
        if (loct2 != null) {
            String selectedItem = loct2.toString();
            //String[] temparr = selectedItem.split(" - ");
            try {
                Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(selectedItem);
                if (obj1 != null) {
                    if (obj1[0] != null) {
                        loc2 = obj1[0].toString();
                        System.out.println(loc2);
                    }
                }
                load();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_location2ItemStateChanged

    private void location1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_location1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_location1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox location1;
    private javax.swing.JComboBox location2;
    private javax.swing.JTable pdtcontable;
    private javax.swing.JTextField pdtqty1;
    private javax.swing.JTextField pdtqty2;
    private javax.swing.JTextField pdtunittype1;
    private javax.swing.JTextField pdtunittype2;
    private javax.swing.JComboBox productlist1;
    private javax.swing.JComboBox productlist2;
    // End of variables declaration//GEN-END:variables
}

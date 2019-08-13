package com.openbravo.pos.inventory;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.*;
import com.openbravo.data.gui.*;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import com.openbravo.pos.catalog.CatalogSelector;
import com.openbravo.pos.catalog.JCatalog;
import com.openbravo.pos.forms.*;
import com.openbravo.pos.catalog.JCatalog1;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.scanpal2.DeviceScanner;
import com.openbravo.pos.scanpal2.DeviceScannerException;
import com.openbravo.pos.scanpal2.ProductDownloaded;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author adrianromero
 */
public class StockManagement extends JPanel implements JPanelView {

    private AppView m_App;
    private DataLogicSystem m_dlSystem;
    private DataLogicSales m_dlSales;
    private TicketParser m_TTP;
    private CatalogSelector m_cat;
    private ComboBoxValModel m_ReasonModel;
    private java.util.List<CategoryInfo> m_sentlocations;
    private ComboBoxValModel m_LocationsModel;
    private ComboBoxValModel m_LocationsModelDes;
    private int lindex = 0;
    private JInventoryLines m_invlines;
    private JInventoryLines m_invlines1;
    private java.util.List<InventoryLine> list1 = new ArrayList<InventoryLine>();
    private java.util.List<InventoryLine> list2 = new ArrayList<InventoryLine>();
    
    /** Creates new form StockManagement */
    private String warehousename;

    public StockManagement() {
        // warehousename="";
    }
    /*  public String getname()
    {
    ret
    }*/

    public StockManagement(AppView app) {

        m_App = app;
        m_dlSystem = (DataLogicSystem) m_App.getBean("com.openbravo.pos.forms.DataLogicSystemCreate");
        m_dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
        m_TTP = new TicketParser(m_App.getDeviceTicket(), m_dlSystem);

        initComponents();
        //  warehousename="";
        btnDownloadProducts.setEnabled(m_App.getDeviceScanner() != null);


        // El modelo de locales
        try {
            //m_sentlocations = m_dlSales.getLocationsList().list();
        //    m_sentlocations = m_dlSales.getRootCategories();
        } catch (Exception e) {
        }
        m_LocationsModel = new ComboBoxValModel();
        m_LocationsModelDes = new ComboBoxValModel();

        m_ReasonModel = new ComboBoxValModel();
        boolean temp = m_App.getAppUserView().getUser().hasPermission("Inpurchase");

        // m_ReasonModel.add(MovementReason.IN_REFUND);
        //  m_ReasonModel.add(MovementReason.IN_MOVEMENT);
        //   m_ReasonModel.add(MovementReason.OUT_SALE);
        //   m_ReasonModel.add(MovementReason.OUT_REFUND);
        // m_ReasonModel.add(MovementReason.OUT_BREAK);
        //   m_ReasonModel.add(MovementReason.OUT_MOVEMENT);
        // m_ReasonModel.add(MovementReason.OUT_CROSSING);

        m_jreason.setModel(m_ReasonModel);

        m_cat = new JCatalog(m_dlSales);
        m_cat.getComponent().setPreferredSize(new Dimension(0, 245));
        m_cat.addActionListener(new CatalogListener());
        catcontainer.add(m_cat.getComponent(), BorderLayout.CENTER);

        // Las lineas de inventario
        m_invlines = new JInventoryLines();
        jPanel5.add(m_invlines, BorderLayout.CENTER);
        m_invlines1 = new JInventoryLines();
        jPanel7.add(m_invlines1, BorderLayout.CENTER);
        rnolabel.setVisible(false);
        rno.setVisible(false);
        rno.setText(null);

    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.StockMovement");
    }

    public JComponent getComponent() {
        return this;
    }

    public void activate() throws BasicException {
        java.util.List<CategoryInfo> categories = m_dlSales.getRootCategories();
        m_cat.loadCatalog(categories);
        rno.setText(null);
        m_ReasonModel = new ComboBoxValModel();
        //String del=MovementReason.IN_PURCHASE.toString();
        int flag = 0, flag1 = 0;
        java.util.List tlist = new ArrayList();
        if (m_App.getAppUserView().getUser().hasPermission("Inpurchase")) {

            for (int i = 0; i < m_ReasonModel.getSize(); i++) {
                if (m_ReasonModel.getElementAt(i).toString().equals(MovementReason.IN_PURCHASE.toString())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                m_ReasonModel.add(MovementReason.IN_PURCHASE);
            }

        }
        flag = 0;
        if (m_App.getAppUserView().getUser().hasPermission("RefundIn")) {

            for (int i = 0; i < m_ReasonModel.getSize(); i++) {
                if (m_ReasonModel.getElementAt(i).toString().equals(MovementReason.IN_REFUND.toString())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                m_ReasonModel.add(MovementReason.IN_REFUND);
            }

        }
        flag = 0;
        if (m_App.getAppUserView().getUser().hasPermission("Crossing")) {

            for (int i = 0; i < m_ReasonModel.getSize(); i++) {
                if (m_ReasonModel.getElementAt(i).toString().equals(MovementReason.OUT_CROSSING.toString())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                m_ReasonModel.add(MovementReason.OUT_CROSSING);
            }

        }
        flag = 0;
        if (m_App.getAppUserView().getUser().hasPermission("InSpilage")) {
            for (int i = 0; i < m_ReasonModel.getSize(); i++) {
                if (m_ReasonModel.getElementAt(i).toString().equals(MovementReason.IN_INSPILAGE.toString())) {
                    flag1 = 1;
                }
                break;
            }
            if (flag1 == 0) {
                m_ReasonModel.add(MovementReason.IN_INSPILAGE);
            }
        }
        flag1 = 0;
        if (m_App.getAppUserView().getUser().hasPermission("OutBreak")) {
            for (int i = 0; i < m_ReasonModel.getSize(); i++) {
                if (m_ReasonModel.getElementAt(i).toString().equals(MovementReason.OUT_BREAK.toString())) {
                    flag1 = 1;
                }
                break;
            }
            if (flag1 == 0) {
                m_ReasonModel.add(MovementReason.OUT_BREAK);
            }
        }


        m_jreason.setModel(m_ReasonModel);
        if (m_ReasonModel.getSize() > 0) {
            m_jreason.setSelectedIndex(0);
        }
        m_LocationsModel = new ComboBoxValModel();
        //   m_LocationsModel = new ComboBoxValModel(m_sentlocations);

        // m_jLocation.setModel(m_LocationsModel); // para que lo refresque
        // LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().
      //  m_LocationsModelDes = new ComboBoxValModel(m_sentlocations);
     //   m_jLocationDes.setModel(m_LocationsModelDes); // para que lo refresque
        java.util.List<CategoryInfo> cinfolist = new ArrayList();
        /* cinfolist.addAll(m_sentlocations);
        for(CategoryInfo cinfo:cinfolist){
        if(m_App.getAppUserView().getUser().hasPermission(cinfo.getName())){
        m_LocationsModel.add(cinfo);
        }
        }
        ComboBoxValModel del=m_LocationsModel;
        m_jLocation.setModel(m_LocationsModel);*/
        System.out.println("***" + LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId());
        Object[] obj = (Object[]) new StaticSentence(m_App.getSession(),
                "SELECT PRCATEGORIES FROM PEOPLE WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getId());
        String[] prcat;

        if (obj != null && obj[0] != null) {
            prcat = obj[0].toString().split("#");
            //  m_dlSales.getCategoriesList()
            //  if(prcat!=null && prcat[0]!=null)
            CategoryInfo temp = new CategoryInfo();
            int t = 0;
            for (int i = 0; i < prcat.length; i++) {
                System.out.println("***" + prcat[i]);
                CategoryInfo obj1 = (CategoryInfo) new StaticSentence(m_App.getSession(),
                        "SELECT ID,NAME,PARENTID,IMAGE FROM CATEGORIES WHERE ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(CategoryInfo.class)).find(prcat[i]);
                if (obj1 != null) {
                    temp = obj1;
                    m_LocationsModel.add(obj1);
                    //m_LocationsModel.setSelectedItem(obj1[0].toString());
                    t = 1;
                }
            }
            if (t == 1) {
                m_LocationsModel.setSelectedItem(temp);
            }
        }
        m_jLocation.setModel(m_LocationsModel);
        stateToInsert();

    /* java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
    m_jcodebar.requestFocus();
    }
    });     */
    }

    public void stateToInsert() {
        // Inicializamos las cajas de texto
        m_jdate.setText(Formats.TIMESTAMP.formatValue(DateUtils.getTodayMinutes()));
        //m_ReasonModel.setSelectedItem(MovementReason.OUT_CROSSING); // Antes Compras.
        // m_LocationsModel.setSelectedKey(1);

        m_LocationsModelDes.setSelectedKey(1);
        //m_LocationsModelDes.setSelectedKey(m_App.getInventoryLocation());
        m_invlines.clear();
        m_invlines1.clear();
        m_jcodebar.setText(null);
    }

    public boolean deactivate() {

        if (m_invlines.getCount() > 0 || m_invlines1.getCount() > 0) {
            int res = JOptionPane.showConfirmDialog(this, LocalRes.getIntString("message.wannasave"), LocalRes.getIntString("title.editor"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                saveData();
                return true;
            } else if (res == JOptionPane.NO_OPTION) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void addLine(ProductInfoExt oProduct, double dpor, double dprice) {
        m_invlines.addLine(new InventoryLine(oProduct, dpor, dprice, lindex));
        lindex++;
    }

    private void deleteLine(int index) {
        if (index < 0) {
            Toolkit.getDefaultToolkit().beep(); // No hay ninguna seleccionada
        } else {
            m_invlines.deleteLine(index);
        }
    }

    private void incProduct(double dPor, ProductInfoExt prod) {
        // precondicion: prod != null
        addLine(prod, dPor, prod.getPriceBuy());
    }

    private void incProductByCode(String sCode) {
        incProductByCode(sCode, 1.0);
    }

    private void incProductByCode(String sCode, double dQuantity) {
        // precondicion: sCode != null

        try {
            ProductInfoExt oProduct = m_dlSales.getProductInfoByCode(sCode);
            if (oProduct == null) {
                Toolkit.getDefaultToolkit().beep();
            } else {
                // Se anade directamente una unidad con el precio y todo
                incProduct(dQuantity, oProduct);
            }
        } catch (BasicException eData) {
            MessageInf msg = new MessageInf(eData);
            msg.show(this);
        }
    }

    private void addUnits(double dUnits) {
        int i = m_invlines.getSelectedRow();
        if (i >= 0) {
            InventoryLine inv = m_invlines.getLine(i);
            dUnits = Math.round(dUnits);
            double dunits = inv.getMultiply() + dUnits;
            if (dunits == 0.0) {
                deleteLine(i);
            } else if (dunits < 0) {
                JOptionPane.showMessageDialog(null, "Total Quantity should be greater then 0");
            } else {
                inv.setMultiply(inv.getMultiply() + dUnits);
                m_invlines.setLine(i, inv);
            }
        }
    }

    private void stateTransition(char cTrans) {

        if (cTrans == '\u007f') {
            m_jcodebar.setText(null);
        } else if (cTrans == '+') {
            if (m_jcodebar.getText() == null || m_jcodebar.getText().equals("")) {
                // anadimos una unidad 
                addUnits(1.0);
            } else {
                addUnits(Double.parseDouble(m_jcodebar.getText()));
                m_jcodebar.setText(null);
            }
        } else if (cTrans == '-') {
            if (m_jcodebar.getText() == null || m_jcodebar.getText().equals("")) {
                // anadimos una unidad 
                addUnits(-1.0);
            } else {
                addUnits(-Double.parseDouble(m_jcodebar.getText()));
                m_jcodebar.setText(null);
            }
        } else if (cTrans == ' ' || cTrans == '=') {
            if (m_invlines.getCount() == 0) {
                // No podemos grabar, no hay ningun registro.
                Toolkit.getDefaultToolkit().beep();
            } else {
                saveData();
            }
        } else {
            m_jcodebar.setText(m_jcodebar.getText() + cTrans);
        }
    }

    private void saveData() {
        try {
            Double num;
            Object[] obj = (Object[]) new StaticSentence(m_App.getSession(),
                    "SELECT MAX(NUM) FROM STOCKDIARY  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE})).find();
            if (obj == null || obj[0] == null) {
                num = 1.0;
            } else {
                num = Double.parseDouble(obj[0].toString());
                num++;
            }
            Date d = (Date) Formats.TIMESTAMP.parseValue(m_jdate.getText());
            MovementReason reason = (MovementReason) m_ReasonModel.getSelectedItem();

            if (reason == MovementReason.OUT_CROSSING) {
                // Es una doble entrada
                if (m_invlines1.getLines().size() > 0 && list1.size() > 0 && m_invlines1.getLines().size() == list1.size()) {

                    saveData1(new InventoryRecord(
                            d, MovementReason.IN_MOVEMENT,
                            (CategoryInfo) m_LocationsModelDes.getSelectedItem(),
                            m_invlines1.getLines()), new InventoryRecord(
                            d, MovementReason.OUT_MOVEMENT,
                            (CategoryInfo) m_LocationsModel.getSelectedItem(),
                            list1), num);
                    /*   saveData(new InventoryRecord(
                    d, MovementReason.IN_MOVEMENT,
                    (CategoryInfo) m_LocationsModelDes.getSelectedItem(),
                    m_invlines1.getLines()
                    ),num);*/
                    stateToInsert();

                } else {
                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.transfererror"), AppLocal.getIntString("message.transferetitle"), JOptionPane.WARNING_MESSAGE);

                }
            } else {
                // Es un movimiento

                saveData2(new InventoryRecord(
                        d, reason,
                        (CategoryInfo) m_LocationsModel.getSelectedItem(),
                        m_invlines.getLines()), num);
                stateToInsert();
            }


        } catch (BasicException eData) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, "No se ha podido guardar la informacion de movimiento de inventario", eData);
            msg.show(this);
        }
    }

    private void saveData1(InventoryRecord rec1, InventoryRecord rec, Double num) throws BasicException {

        // A grabar.
        SentenceExec sent = m_dlSales.getStockDiaryInsert();

        for (int i = 0; i < m_invlines1.getCount(); i++) {
            //if()
            Object[] diary = new Object[14];
            InventoryLine inv = rec.getLines().get(i);
            InventoryLine inv1 = rec1.getLines().get(i);
            String cid = ((CategoryInfo) m_jLocation.getSelectedItem()).getID();
            String cid1 = ((CategoryInfo) m_jLocationDes.getSelectedItem()).getID();
            diary[0] = UUID.randomUUID().toString();

            diary[7] = rec1.getReason().getKey();
            diary[8] = cid1;

            diary[9] = inv1.getProductID();
            diary[10] = rec1.getReason().samesignum(inv1.getMultiply());
            diary[11] = inv1.getPrice();
            diary[12] = num;
            diary[13] = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName().toString();

            diary[1] = rec.getDate();
            diary[2] = rec.getReason().getKey();
            diary[3] = cid;

            diary[4] = inv.getProductID();
            diary[5] = rec.getReason().samesignum(inv.getMultiply());
            diary[6] = inv.getPrice();


            sent.exec(diary);
        // m_dlSales.updateStockVolume(rec.getReason().samesignum(inv.getMultiply()), inv.getProductID());
        }

        printTicket(rec);
        printTicket(rec1);
        list1.clear();
    }

    private void saveData2(InventoryRecord rec, Double num) throws BasicException {
        String rno1 = rno.getText();
        //  if(rno1!=null)
        if (m_ReasonModel.getSelectedItem() == MovementReason.OUT_BREAK || m_ReasonModel.getSelectedItem() == MovementReason.IN_INSPILAGE) {
            rno.setText("    ");
            if (m_ReasonModel.getSelectedItem() == MovementReason.OUT_BREAK) {
                rno1 = "Break";
            } else {
                rno1 = "Inspilage";
            }
        }
        if (rno.getText().length() != 0) {
            SentenceExec sent = m_dlSales.getStockDiaryInsertinpdt();
            for (int i = 0; i < m_invlines.getCount(); i++) {
                InventoryLine il = m_invlines.getLine(i);

                Object[] pdt = (Object[]) new StaticSentence(m_App.getSession(),
                        "SELECT C.NAME,C.ID,C.PARENTID FROM PRODUCTS P,CATEGORIES C WHERE C.ID=P.CATEGORY AND P.ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING})).find(il.getProductID());
                Object parentid = pdt[2];
                String pid = "";
                Object[] catdetail = new Object[3];
                catdetail[1] = pdt[0];
                catdetail[2] = pdt[2];
                if (parentid == null) {
                    pid = pdt[1].toString();
                }
                while (parentid != null) {
                    catdetail = m_dlSales.getParentCategories(parentid.toString());
                    parentid = catdetail[0];
                    pid = catdetail[2].toString();
                }
                // String del1=catdetail[2].toString();

                Object ob = m_dlSales.getStockVolume(il.getProductID());
                Double oqty;
                if (ob != null) {
                    oqty = Double.parseDouble(ob.toString());
                } else {
                    oqty = 0.0;
                }
                oqty = oqty + rec.getReason().samesignum(il.getMultiply());
                if (catdetail[1].equals(rec.getLocation().getName()) && oqty > -1) {
                    Object[] diary = new Object[9];
                    diary[0] = UUID.randomUUID().toString();
                    diary[1] = rec.getDate();
                    String del = rec.getReason().getKey().toString();
                    diary[2] = rec.getReason().getKey();
                    diary[3] = pid;

                    InventoryLine inv = rec.getLines().get(i);
                    diary[4] = inv.getProductID();
                    diary[5] = rec.getReason().samesignum(inv.getMultiply());
                    diary[6] = inv.getPrice();

                    diary[7] = rno1;
                    diary[8] = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
                    sent.exec(diary);
                    
                    //Line removed because it was reducing stock in STOCKCURRENT twice
                    //m_dlSales.getStockCurrentInsert().exec(new Object[]{rec.getReason().samesignum(inv.getMultiply()), pid, inv.getProductID()});
                } else {
                    if (oqty <= -1) {
                        JOptionPane.showMessageDialog(this, "The Specified Stock Exceeds the current Stock", "Error", JOptionPane.OK_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(this, "The product does not belong to this category", "Error", JOptionPane.OK_OPTION);
                    }
                    m_invlines.deleteLine(i);
                    //  rec.removeline(i);
                    i--;
                //j++;
                }
            }
            if (m_invlines.getCount() > 0) {
                printTicket(rec);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please fill the receipt no", "Error", JOptionPane.OK_OPTION);
        }

    // si se ha grabado se imprime, si no, no.

    }

    private void printTicket(InventoryRecord invrec) {

        String sresource = m_dlSystem.getResourceAsXML("Printer.Inventory");
        if (sresource == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
            msg.show(this);
        } else {
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                script.put("inventoryrecord", invrec);
                script.put("createdby", m_App.getAppUserView().getUser().getName());
                String x = m_App.getAppUserView().getUser().getRole();
                script.put("role", LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString());
                script.put("host", m_App.getProperties().getHost());
                m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            } catch (TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
                msg.show(this);
            }
        }
    }

    private class CatalogListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            incProduct(1.0, (ProductInfoExt) e.getSource());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jNumberKeys = new com.openbravo.beans.JNumberKeys();
        jPanel4 = new javax.swing.JPanel();
        m_jcodebar = new javax.swing.JTextField();
        m_jEnter = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnDownloadProducts = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        m_jdate = new javax.swing.JTextField();
        m_jbtndate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        m_jreason = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        m_jLocation = new javax.swing.JComboBox();
        m_jDelete = new javax.swing.JButton();
        m_jUp = new javax.swing.JButton();
        m_jDown = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        m_jLocationDes = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        rno = new javax.swing.JTextField();
        rnolabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        catcontainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jNumberKeys.addJNumberEventListener(new com.openbravo.beans.JNumberEventListener() {
            public void keyPerformed(com.openbravo.beans.JNumberEvent evt) {
                jNumberKeysKeyPerformed(evt);
            }
        });
        jPanel2.add(jNumberKeys);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        m_jcodebar.setPreferredSize(new java.awt.Dimension(110, 19));
        m_jcodebar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jcodebarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(m_jcodebar, gridBagConstraints);

        m_jEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/barcode.png"))); // NOI18N
        m_jEnter.setFocusPainted(false);
        m_jEnter.setFocusable(false);
        m_jEnter.setRequestFocusEnabled(false);
        m_jEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jEnterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(m_jEnter, gridBagConstraints);

        jPanel2.add(jPanel4);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);

        btnDownloadProducts.setText("ScanPal");
        btnDownloadProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadProductsActionPerformed(evt);
            }
        });
        jPanel6.add(btnDownloadProducts);

        jPanel2.add(jPanel6);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        add(jPanel1, java.awt.BorderLayout.EAST);

        jPanel3.setLayout(null);

        jLabel1.setText(AppLocal.getIntString("label.stockdate")); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 150, 17);
        jPanel3.add(m_jdate);
        m_jdate.setBounds(160, 30, 200, 27);

        m_jbtndate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        m_jbtndate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtndateActionPerformed(evt);
            }
        });
        jPanel3.add(m_jbtndate);
        m_jbtndate.setBounds(370, 30, 40, 28);

        jLabel2.setText(AppLocal.getIntString("label.stockreason")); // NOI18N
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 60, 150, 17);

        m_jreason.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jreasonItemStateChanged(evt);
            }
        });
        m_jreason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jreasonActionPerformed(evt);
            }
        });
        jPanel3.add(m_jreason);
        m_jreason.setBounds(160, 60, 200, 20);

        jLabel8.setText(AppLocal.getIntString("label.warehouse")); // NOI18N
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 90, 150, 17);

        m_jLocation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jLocationItemStateChanged(evt);
            }
        });
        jPanel3.add(m_jLocation);
        m_jLocation.setBounds(160, 90, 200, 20);

        m_jDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/locationbar_erase.png"))); // NOI18N
        m_jDelete.setFocusPainted(false);
        m_jDelete.setFocusable(false);
        m_jDelete.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDelete.setRequestFocusEnabled(false);
        m_jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(m_jDelete);
        m_jDelete.setBounds(260, 280, 50, 50);

        m_jUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow22.png"))); // NOI18N
        m_jUp.setFocusPainted(false);
        m_jUp.setFocusable(false);
        m_jUp.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jUp.setRequestFocusEnabled(false);
        m_jUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jUpActionPerformed(evt);
            }
        });
        jPanel3.add(m_jUp);
        m_jUp.setBounds(260, 200, 50, 50);

        m_jDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow22.png"))); // NOI18N
        m_jDown.setFocusPainted(false);
        m_jDown.setFocusable(false);
        m_jDown.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jDown.setRequestFocusEnabled(false);
        m_jDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jDownActionPerformed(evt);
            }
        });
        jPanel3.add(m_jDown);
        m_jDown.setBounds(260, 240, 50, 50);

        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel5);
        jPanel5.setBounds(10, 160, 240, 190);

        m_jLocationDes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                m_jLocationDesItemStateChanged(evt);
            }
        });
        jPanel3.add(m_jLocationDes);
        m_jLocationDes.setBounds(160, 120, 200, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/2rightarrow.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(260, 160, 50, 40);

        jPanel7.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel7);
        jPanel7.setBounds(320, 160, 230, 190);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/2leftarrow.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(260, 323, 50, 40);
        jPanel3.add(rno);
        rno.setBounds(460, 90, 80, 27);

        rnolabel.setText("Voucher No :");
        jPanel3.add(rnolabel);
        rnolabel.setBounds(370, 90, 80, 20);

        jLabel3.setText("Warehouse To ");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 120, 100, 17);

        add(jPanel3, java.awt.BorderLayout.CENTER);

        catcontainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        catcontainer.setLayout(new java.awt.BorderLayout());
        add(catcontainer, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDownloadProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadProductsActionPerformed

        // Ejecutamos la descarga...
        DeviceScanner s = m_App.getDeviceScanner();
        try {
            s.connectDevice();
            s.startDownloadProduct();

            ProductDownloaded p = s.recieveProduct();
            while (p != null) {
                incProductByCode(p.getCode(), p.getQuantity());
                p = s.recieveProduct();
            }
        // MessageInf msg = new MessageInf(MessageInf.SGN_SUCCESS, "Se ha subido con exito la lista de productos al ScanPal.");
        // msg.show(this);
        } catch (DeviceScannerException e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.scannerfail2"), e);
            msg.show(this);
        } finally {
            s.disconnectDevice();
        }

    }//GEN-LAST:event_btnDownloadProductsActionPerformed

    private void m_jreasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jreasonActionPerformed

        m_jLocationDes.setEnabled(m_ReasonModel.getSelectedItem() == MovementReason.OUT_CROSSING);
        if (m_ReasonModel.getSelectedItem() == MovementReason.OUT_CROSSING || m_ReasonModel.getSelectedItem() == MovementReason.OUT_BREAK || m_ReasonModel.getSelectedItem() == MovementReason.IN_INSPILAGE) {
            rnolabel.setVisible(false);
            rno.setVisible(false);
            if (m_ReasonModel.getSelectedItem() != MovementReason.OUT_BREAK && m_ReasonModel.getSelectedItem() != MovementReason.IN_INSPILAGE) {
                jButton1.setEnabled(true);
                jButton2.setEnabled(true);
            } else {
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
            }
        } else {
            rno.setText(null);
            rno.setEnabled(true);
            rno.setVisible(true);
            rno.setEditable(true);

            //   String del=rno.getText();
            rnolabel.setVisible(true);
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }

    }//GEN-LAST:event_m_jreasonActionPerformed

    private void m_jDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDownActionPerformed

        m_invlines.goDown();

    }//GEN-LAST:event_m_jDownActionPerformed

    private void m_jUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jUpActionPerformed

        m_invlines.goUp();

    }//GEN-LAST:event_m_jUpActionPerformed

    private void m_jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jDeleteActionPerformed

        deleteLine(m_invlines.getSelectedRow());

    }//GEN-LAST:event_m_jDeleteActionPerformed

    private void m_jEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jEnterActionPerformed

        incProductByCode(m_jcodebar.getText());
        m_jcodebar.setText(null);

    }//GEN-LAST:event_m_jEnterActionPerformed

    private void m_jcodebarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jcodebarActionPerformed

        incProductByCode(m_jcodebar.getText());
        m_jcodebar.setText(null);

    }//GEN-LAST:event_m_jcodebarActionPerformed

    private void m_jbtndateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtndateActionPerformed

        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(m_jdate.getText());
        } catch (BasicException e) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            m_jdate.setText(Formats.TIMESTAMP.formatValue(date));
        }
    }//GEN-LAST:event_m_jbtndateActionPerformed

    private void jNumberKeysKeyPerformed(com.openbravo.beans.JNumberEvent evt) {//GEN-FIRST:event_jNumberKeysKeyPerformed

        stateTransition(evt.getKey());

    }//GEN-LAST:event_jNumberKeysKeyPerformed
    private int destno;
    private int sourceno;
    Object fmLocation;
    Object toLocation;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String pdtname1 = "";
        int row = m_invlines.getSelectedRow();
        InventoryLine il = m_invlines.getLine(row);
        String pid = il.getProductID();
        Double qty1 = il.getMultiply();
        Double stkvol = 0.0;
        Double qty2;
        Double qty3;
        Double ratio1;
        Double ratio2;
        String unittype = "";
        Double temp = 00.0;
        int t = 0;
        try {
            Object stk = m_dlSales.getStockVolume(pid);
            if (stk == null) {
                stkvol = 0.0;
            } else {
                stkvol = Double.parseDouble(stk.toString());
            }
        } catch (Exception e) {
        }
        if (stkvol < qty1) {
            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtylarge"), AppLocal.getIntString("message.stocktitle"), JOptionPane.WARNING_MESSAGE);
        } else {

            if (m_jLocationDes.getSelectedItem() != null && m_jLocation.getSelectedItem() != null) {
                try {
                    fmLocation = new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(m_jLocation.getSelectedItem().toString());
                    toLocation = new StaticSentence(m_App.getSession(), "SELECT ID FROM LOCATIONS WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(m_jLocationDes.getSelectedItem().toString());
                    if (!fmLocation.equals(toLocation)) {

                        //Arun

                        destno = m_jLocationDes.getSelectedIndex();
                        sourceno = m_jLocation.getSelectedIndex();

                        Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                                //"SELECT NOFST,PRODUCTSCN,NOSEC FROM CONVERTER WHERE PRODUCTFST = ? AND ACTIVE=TRUE "
                                "SELECT NOFST,PRODUCTSCN,NOSEC,LOCATIONSCN FROM CONVERTER WHERE PRODUCTFST = ? AND LOCATIONFST=? AND LOCATIONSCN=? AND  ACTIVE=TRUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, fmLocation.toString(), toLocation});
                        if (obj1 == null || obj1[0] == null) {
                            Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(),
                                    "SELECT NOFST,PRODUCTFST,NOSEC,LOCATIONFST FROM CONVERTER WHERE PRODUCTSCN = ? AND LOCATIONFST=? AND LOCATIONSCN=? AND ACTIVE=TRUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, toLocation.toString(), fmLocation.toString()});

                            if (obj2 != null && obj2[0] != null) {
                                ProductInfoExt obj3 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                        "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,p.inventrymaintain,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                                        " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj2[1].toString());

//                                System.out.println("**" + obj1[0]);
//                                System.out.println(obj1[2]);
                                ratio1 = (Double) obj2[0];
                                ratio2 = (Double) obj2[2];
                                qty2 = (ratio1 * qty1) / ratio2;
                                //praveen:cecking stock to transfer---start
                                qty3 = m_invlines1.containsproduct(obj3.getID().toString());
                                if (qty3 > 0.0) {
                                    qty3 = qty1 + ((ratio1 * qty3) / ratio2);
                                    if (stkvol >= qty3) {
                                        m_invlines1.addLine(new InventoryLine(obj3, qty2, temp, il.getIndex()));
                                        list1.add(il);
                                        //praveen:commented
                                        //m_dlSales.updateStockVolume1(-qty1, pid);
                                        deleteLine(m_invlines.getSelectedRow());
                                    } else {
                                        JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtylarge"), AppLocal.getIntString("message.stocktitle"), JOptionPane.WARNING_MESSAGE);
                                    }

                                } else {
                                    m_invlines1.addLine(new InventoryLine(obj3, qty2, temp, il.getIndex()));
                                    list1.add(il);
                                    //praveen:commented
                                    //m_dlSales.updateStockVolume1(-qty1, pid);
                                    deleteLine(m_invlines.getSelectedRow());
                                }
                            //praveen:cecking stock to transfer---end

                            } else {
                                Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                                        "SELECT C.NOFST,C.NOSEC,C1.NOFST,C1.NOSEC,C1.PRODUCTSCN FROM CONVERTER C,CONVERTER C1  WHERE  C.PRODUCTSCN=? and C.LOCATIONSCN=? AND  C1.LOCATIONSCN=? AND C.LOCATIONFST=C1.LOCATIONFST AND C.PRODUCTFST=C1.PRODUCTFST and c.active=true and c1.active=true",
                                        new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, fmLocation.toString(), toLocation.toString()});
                                if (obj3 != null && obj3[0] != null) {
                                    ProductInfoExt obj4 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                            "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,p.inventrymaintain,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3" +
                                            " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj3[4].toString());
                                    System.out.println(obj3[1]);
                                    System.out.println(obj3[3]);
                                    ratio1 = (Double) obj3[2];
                                    ratio2 = (Double) obj3[3];
                                    double ratio3 = (Double) obj3[0];
                                    double ratio4 = (Double) obj3[1];
                                    double qty4 = (ratio3 * qty1) / ratio4;
                                    qty2 = (ratio2 * qty4) / ratio1;
                                    qty3 = m_invlines1.containsproduct(obj4.getID().toString());
                                    if (qty3 > 0.0) {
                                        qty3 = qty1 + ((ratio1 * qty3) / ratio2);
                                        if (stkvol >= qty3) {
                                            m_invlines1.addLine(new InventoryLine(obj4, qty2, temp, il.getIndex()));
                                            list1.add(il);
                                            //m_dlSales.updateStockVolume1(-qty1, pid);
                                            deleteLine(m_invlines.getSelectedRow());
                                        } else {
                                            JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtylarge"), AppLocal.getIntString("message.stocktitle"), JOptionPane.WARNING_MESSAGE);
                                        }

                                    } else {
                                        m_invlines1.addLine(new InventoryLine(obj4, qty2, temp, il.getIndex()));
                                        list1.add(il);
                                        //praveen:commented
                                        //m_dlSales.updateStockVolume1(-qty1, pid);
                                        deleteLine(m_invlines.getSelectedRow());
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid operation", null, JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        } else {
                            String del = obj1[1].toString();
                            ProductInfoExt obj3 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                    "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,p.inventrymaintain,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3 " +
                                    " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj1[1].toString());
                            System.out.println(obj1[0]);
                            System.out.println(obj1[2]);
                            ratio1 = (Double) obj1[0];
                            ratio2 = (Double) obj1[2];
                            qty2 = (ratio2 * qty1) / ratio1;
                            //praveen:cecking stock to transfer---start
                            qty3 = m_invlines1.containsproduct(obj3.getID().toString());
                            if (qty3 > 0.0) {
                                qty3 = qty1 + ((ratio1 * qty3) / ratio2);
                                if (stkvol >= qty3) {
                                    m_invlines1.addLine(new InventoryLine(obj3, qty2, temp, il.getIndex()));
                                    list1.add(il);
                                    //m_dlSales.updateStockVolume1(-qty1, pid);
                                    deleteLine(m_invlines.getSelectedRow());
                                } else {
                                    JOptionPane.showMessageDialog(this, AppLocal.getIntString("message.qtylarge"), AppLocal.getIntString("message.stocktitle"), JOptionPane.WARNING_MESSAGE);
                                }

                            } else {
                                m_invlines1.addLine(new InventoryLine(obj3, qty2, temp, il.getIndex()));
                                list1.add(il);
                                //praveen:commented
                                //m_dlSales.updateStockVolume1(-qty1, pid);
                                deleteLine(m_invlines.getSelectedRow());
                            }
                        //praveen:cecking stock to transfer---end
//                         //praveen:commented
                        //m_dlSales.updateStockVolume1(-qty1, pid);
                        //deleteLine(m_invlines.getSelectedRow());
                        }


                    } else {
                        JOptionPane.showMessageDialog(this, "from and to warehouse option should be different", null, JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.scannerfail2"), e);
                    msg.show(this);
                }

            } else {
                if (m_jLocation.getSelectedItem() == null && m_jLocationDes.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Plese select a appropriate value for from and to warehouse option", null, JOptionPane.WARNING_MESSAGE);
                } else if (m_jLocation.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Plese select a appropriate value for from warehouse option", null, JOptionPane.WARNING_MESSAGE);
                } else if (m_jLocationDes.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Plese select a appropriate value for to warehouse option", null, JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String pdtname1;
        int row = m_invlines1.getSelectedRow();
        InventoryLine il = m_invlines1.getLine(row);
        String pid = il.getProductID();
        Double qty1 = il.getMultiply();
        Double stkvol = 0.0;
        Double qty2;
        Double ratio1;
        Double ratio2;
        String unittype = "";
        Double temp = 20.0;
        int t = 0;
        try {
            Object stk = m_dlSales.getStockVolume(pid);
            if (stk == null) {
                stkvol = 0.0;
            } else {
                stkvol = Double.parseDouble(stk.toString());
            }
        } catch (Exception e) {
        }

        {

            if (m_jLocation.getSelectedItem() != null && t == 0 && m_jLocationDes.getSelectedItem() != null) {
                try {
                    Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(),
                            //"SELECT NOFST,PRODUCTSCN,NOSEC FROM CONVERTER WHERE PRODUCTFST = ? AND ACTIVE=TRUE "
                            "SELECT NOFST,PRODUCTSCN,NOSEC,LOCATIONSCN FROM CONVERTER WHERE PRODUCTFST = ? AND LOCATIONFST=? AND LOCATIONSCN=? AND  ACTIVE=TRUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, toLocation.toString(), fmLocation.toString()});
                    if (obj1 == null || obj1[0] == null) {
                        Object[] obj2 = (Object[]) new StaticSentence(m_App.getSession(),
                                "SELECT NOFST,PRODUCTFST,NOSEC,LOCATIONFST FROM CONVERTER WHERE PRODUCTSCN = ? AND LOCATIONFST=? AND LOCATIONSCN=? AND ACTIVE=TRUE ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, fmLocation.toString(), toLocation.toString()});

                        if (obj2 != null && obj2[0] != null) {
                            ProductInfoExt obj3 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                    " SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE ,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3" +
                                    " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj2[1].toString());
                            ratio1 = (Double) obj2[0];
                            ratio2 = (Double) obj2[2];
                            qty2 = (ratio1 * qty1) / ratio2;
                            m_invlines.addLine(new InventoryLine(obj3, qty2, temp, lindex));
                            lindex++;
                            int j = 0;
                            for (j = 0; j < list1.size(); j++) {
                                InventoryLine ili = list1.get(j);
                                if (ili.getProductName().equals(obj3.getName()) && ili.getMultiply() == qty2 && ili.getIndex() == il.getIndex()) {
                                    list1.remove(j);
                                    break;
                                }
                            }
                            //praveen:commented
                            //m_dlSales.updateStockVolume1(qty2, obj2[1].toString());
                            m_invlines1.deleteLine(row);
                        } else {
                            Object[] obj3 = (Object[]) new StaticSentence(m_App.getSession(),
                                    "SELECT C.NOFST,C.NOSEC,C1.NOFST,C1.NOSEC,C1.PRODUCTSCN FROM CONVERTER C,CONVERTER C1  WHERE  C.PRODUCTSCN=? and C.LOCATIONSCN=? AND  C1.LOCATIONSCN=? AND C.LOCATIONFST=C1.LOCATIONFST AND C.PRODUCTFST=C1.PRODUCTFST and c.active=true and c1.active=true",
                                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING})).find(new Object[]{pid, toLocation.toString(), fmLocation.toString()});
                            if (obj3 != null && obj3[0] != null) {
                                ProductInfoExt obj4 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                        "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,p.inventrymaintain,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3" +
                                        " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj3[4].toString());
                                double ratio3 = (Double) obj3[0];
                                double ratio4 = (Double) obj3[1];
                                ratio1 = (Double) obj3[2];
                                ratio2 = (Double) obj3[3];
                                double qty4 = (ratio1 * qty1) / ratio2;
                                qty2 = (ratio4 * qty4) / ratio3;
                                m_invlines.addLine(new InventoryLine(obj4, qty2, temp, il.getIndex()));
                                lindex++;
                                int j = 0;
                                for (j = 0; j < list1.size(); j++) {
                                    InventoryLine ili = list1.get(j);
                                    if (ili.getProductName().equals(obj4.getName()) && ili.getMultiply() == qty2 && ili.getIndex() == il.getIndex()) {
                                        list1.remove(j);
                                        break;
                                    }
                                }
                                //praveen:commented
                                // m_dlSales.updateStockVolume1(qty2, obj1[1].toString());
                                m_invlines1.deleteLine(row);

                            }
                        }
                    } else {
                        if (obj1 != null && obj1[0] != null) {
                            ProductInfoExt obj3 = (ProductInfoExt) new StaticSentence(m_App.getSession(),
                                    "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, TC.ID, TC.NAME, P.CATEGORY, P.IMAGE, P.ATTRIBUTES, P.PRCATEGORY ,P.UNITTYPE,P.STOCKVOLUME,P.PACCOUNT,P.SACCOUNT,P.INVENTRYMAINTAIN,P.LOCATION , P.TAXCAT2 ,   P.TAXCAT3 , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT2) , (SELECT T.NAME FROM TAXCATEGORIES T WHERE T.ID=P.TAXCAT3) , P.BASIC2 , P.BASIC3" +
                                    " FROM PRODUCTS P  LEFT OUTER JOIN TAXCATEGORIES TC ON P.TAXCAT = TC.ID WHERE P.ID = ?", SerializerWriteString.INSTANCE, new SerializerReadClass(ProductInfoExt.class)).find(obj1[1].toString());

                            ratio1 = (Double) obj1[0];
                            ratio2 = (Double) obj1[2];
                            qty2 = (ratio2 * qty1) / ratio1;
                            m_invlines.addLine(new InventoryLine(obj3, qty2, temp, lindex));
                            lindex++;
                            int j = 0;
                            for (j = 0; j < list1.size(); j++) {
                                InventoryLine ili = list1.get(j);
                                if (ili.getProductName().equals(obj3.getName()) && ili.getMultiply() == qty2 && ili.getIndex() == il.getIndex()) {
                                    list1.remove(j);
                                    break;
                                }
                            }
                            //praveen:commented
                            // m_dlSales.updateStockVolume1(qty2, obj1[1].toString());
                            m_invlines1.deleteLine(row);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        saveData();
    }//GEN-LAST:event_jButton3ActionPerformed
    public int temp = 0;
    private void m_jLocationDesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jLocationDesItemStateChanged
        // TODO add your handling code here:
      /*  Object des= m_LocationsModelDes.getSelectedKey();
        if(m_invlines1.getLines().size()>0 && des !=(Object)1 && temp==0)
        {

        JOptionPane.showMessageDialog(this, "Cannot Change Warehouse ", "Cannot Change", JOptionPane.OK_OPTION);
        temp=1;
        stateToInsert();

        }else
        temp=0;*/
        lindex = 0;
        m_invlines.clear();
        m_invlines1.clear();
        list1 = new ArrayList<InventoryLine>();
    }//GEN-LAST:event_m_jLocationDesItemStateChanged

    private void m_jLocationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jLocationItemStateChanged
        lindex = 0;
        m_invlines.clear();
        m_invlines1.clear();
        list1 = new ArrayList<InventoryLine>();
        
       if(m_jLocation.getSelectedIndex()!=-1){
        
       ///////////////////////////////////////////////////////////////  edited by #aakash 
       try{ 
           
           
            String SelecedFromWarehouse=m_jLocation.getSelectedItem().toString();
            String CurrWarehouseId=null;
            List<CategoryInfo> WarehouseList = new ArrayList<CategoryInfo>();
                    
            m_jLocationDes.setSelectedIndex(-1);
            Boolean Crossing_flag=false;

            Object[] obj19 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT ID FROM locations where name=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(SelecedFromWarehouse);
            if(obj19!=null){
                CurrWarehouseId=obj19[0].toString();
            }
        
            WarehouseList  = getParentWareHouseList(CurrWarehouseId);
            
        
       
            Object[] obj20 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Crossing of products horizontally'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
            if(obj20!=null){ 
                  Boolean v20 = (Boolean)obj20[0];
                  if(v20){
                    Crossing_flag=true;
                   }
                  else{
                    Crossing_flag=false;
                  }
             }
             else{
                 Crossing_flag=false;
              }
            
          
            
           if(Crossing_flag){
                m_sentlocations = m_dlSales.getRootCategories();
                m_LocationsModelDes = new ComboBoxValModel(m_sentlocations);
                m_jLocationDes.setModel(m_LocationsModelDes); // para que lo refresque
               
            }  
            else{
                m_LocationsModelDes = new ComboBoxValModel(WarehouseList);
                m_jLocationDes.setModel(m_LocationsModelDes); // para que lo refresque
            }
            
        
       }
       catch(BasicException e){
           e.printStackTrace();
       }
        
       }
       
       
    }//GEN-LAST:event_m_jLocationItemStateChanged

    private void m_jreasonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_m_jreasonItemStateChanged
        m_jLocation.setSelectedIndex(-1);
    }//GEN-LAST:event_m_jreasonItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownloadProducts;
    private javax.swing.JPanel catcontainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private com.openbravo.beans.JNumberKeys jNumberKeys;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton m_jDelete;
    private javax.swing.JButton m_jDown;
    private javax.swing.JButton m_jEnter;
    private javax.swing.JComboBox m_jLocation;
    private javax.swing.JComboBox m_jLocationDes;
    private javax.swing.JButton m_jUp;
    private javax.swing.JButton m_jbtndate;
    private javax.swing.JTextField m_jcodebar;
    private javax.swing.JTextField m_jdate;
    private javax.swing.JComboBox m_jreason;
    private javax.swing.JTextField rno;
    private javax.swing.JLabel rnolabel;
    // End of variables declaration//GEN-END:variables


   public final List<CategoryInfo> getParentWareHouseList(String CurrWarehouseId) throws BasicException {
        return new PreparedSentence(m_App.getSession(), "select id,name,parentid,image from categories where location in (select id from locations l where\n" +
                                        "l.id=(select parent from locations where id= ? )\n" +
                                        "or l.parent= '"+CurrWarehouseId+"' ) and parentid is null\n" +
                                        "order by name", SerializerWriteString.INSTANCE, new SerializerReadClass(CategoryInfo.class)).list(CurrWarehouseId);
    }



}

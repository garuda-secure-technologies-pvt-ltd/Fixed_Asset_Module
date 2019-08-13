
package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.util.ReportUtils;
import com.openbravo.pos.util.StringParser;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SkinInfo;
import org.jvnet.substance.skin.SubstanceSkin;

/**
 *
 * @author adrianromero
 */
public class JPanelConfigGeneral extends javax.swing.JPanel implements PanelConfig {
    
    private DirtyManager dirty = new DirtyManager();

    static final String PRINTER_CONFIG_TYPE = "type";
    static final String PRINTER_CONFIG_CONN = "conn";
    static final String PRINTER_CONFIG_SERIAL = "serial";
    static final String PRINTER_CONFIG_JPOSPRINTER = "jposprinter";
    private Map<Integer, Map<String, String>> printersMap = new LinkedHashMap<Integer, Map<String, String>>();
    
    /** Creates new form JPanelConfigGeneral */
    public JPanelConfigGeneral() {
        
        initComponents();
        
        jtxtMachineHostname.getDocument().addDocumentListener(dirty);
        jcboLAF.addActionListener(dirty);   
        jcboMachineScreenmode.addActionListener(dirty);        
        jcboTicketsBag.addActionListener(dirty);
        
        jcboMachineDisplay.addActionListener(dirty);
        jcboConnDisplay.addActionListener(dirty);
        jcboSerialDisplay.addActionListener(dirty);
        m_jtxtJPOSName.getDocument().addDocumentListener(dirty);
        
        jcboMachineScale.addActionListener(dirty);
        jcboSerialScale.addActionListener(dirty);
        
        jcboMachineScanner.addActionListener(dirty);
        jcboSerialScanner.addActionListener(dirty);

        jPrinters.setModel(new JPrinterConfigTableModel());
        jPrinters.getColumnModel().getColumn(0).setPreferredWidth(70);
        jPrinters.getColumnModel().getColumn(0).setMaxWidth(70);
//        cboPrinters.addActionListener(dirty);

        jTextQTSeries.getDocument().addDocumentListener(dirty);
        jTextBillSeries.getDocument().addDocumentListener(dirty);
        jTextReceiptse.getDocument().addDocumentListener(dirty);
        
//        // Openbravo Skin
//        jcboLAF.addItem(new UIManager.LookAndFeelInfo("Openbravo", "com.openbravo.pos.skin.OpenbravoLookAndFeel"));
                
        // Installed skins
        LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
        for (int i = 0 ; i < lafs.length; i++) {
            jcboLAF.addItem(new LAFInfo(lafs[i].getName(), lafs[i].getClassName()));
        }
        
        // Substance skins
        new SubstanceLookAndFeel(); // TODO: Remove in Substance 5.0. Workaround for Substance 4.3 to initialize static variables
        Map<String, SkinInfo> skins = SubstanceLookAndFeel.getAllSkins();
        for (SkinInfo skin : skins.values()) {
            jcboLAF.addItem(new LAFInfo(skin.getDisplayName(), skin.getClassName()));
        }
        
        jcboLAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLAF();
            }
        });        
        
        jcboMachineScreenmode.addItem("window");
        jcboMachineScreenmode.addItem("fullscreen");
        
        jcboTicketsBag.addItem("simple");
        jcboTicketsBag.addItem("standard");
        jcboTicketsBag.addItem("restaurant");
        
        // Printer 1
//        jcboMachinePrinter.addItem("screen");
//        jcboMachinePrinter.addItem("printer");
//        jcboMachinePrinter.addItem("epson");
//        jcboMachinePrinter.addItem("tmu220");
//        jcboMachinePrinter.addItem("star");
//        jcboMachinePrinter.addItem("ithaca");
//        jcboMachinePrinter.addItem("surepos");
//        jcboMachinePrinter.addItem("javapos");
//        jcboMachinePrinter.addItem("Not defined");
        
//        jcboConnPrinter.addItem("serial");
//        jcboConnPrinter.addItem("file");
        
//        jcboSerialPrinter.addItem("COM1");
//        jcboSerialPrinter.addItem("COM2");
//        jcboSerialPrinter.addItem("COM3");
//        jcboSerialPrinter.addItem("COM4");
//        jcboSerialPrinter.addItem("LPT1");
//        jcboSerialPrinter.addItem("/dev/ttyS0");
//        jcboSerialPrinter.addItem("/dev/ttyS1");
//        jcboSerialPrinter.addItem("/dev/ttyS2");
//        jcboSerialPrinter.addItem("/dev/ttyS3");
        
        // Display
        jcboMachineDisplay.addItem("screen");
        jcboMachineDisplay.addItem("window");
        jcboMachineDisplay.addItem("javapos");
        jcboMachineDisplay.addItem("epson");
        jcboMachineDisplay.addItem("ld200");
        jcboMachineDisplay.addItem("surepos");
        jcboMachineDisplay.addItem("Not defined");
        
        jcboConnDisplay.addItem("serial");
        jcboConnDisplay.addItem("file");
        
        jcboSerialDisplay.addItem("COM1");
        jcboSerialDisplay.addItem("COM2");
        jcboSerialDisplay.addItem("COM3");
        jcboSerialDisplay.addItem("COM4");
        jcboSerialDisplay.addItem("COM5");
        jcboSerialDisplay.addItem("LPT1");
        jcboSerialDisplay.addItem("/dev/ttyS0");
        jcboSerialDisplay.addItem("/dev/ttyS1");
        jcboSerialDisplay.addItem("/dev/ttyS2");
        jcboSerialDisplay.addItem("/dev/ttyS3");
        
        // Scale
        jcboMachineScale.addItem("screen");
        jcboMachineScale.addItem("dialog1");
        jcboMachineScale.addItem("samsungesp");
        jcboMachineScale.addItem("Not defined");
        
        jcboSerialScale.addItem("COM1");
        jcboSerialScale.addItem("COM2");
        jcboSerialScale.addItem("COM3");
        jcboSerialScale.addItem("COM4");
         jcboSerialScale.addItem("COM5");
        jcboSerialScale.addItem("/dev/ttyS0");
        jcboSerialScale.addItem("/dev/ttyS1");
        jcboSerialScale.addItem("/dev/ttyS2");
        jcboSerialScale.addItem("/dev/ttyS3");
        
        // Scanner
        jcboMachineScanner.addItem("scanpal2");
        jcboMachineScanner.addItem("Not defined");
        
        jcboSerialScanner.addItem("COM1");
        jcboSerialScanner.addItem("COM2");
        jcboSerialScanner.addItem("COM3");
        jcboSerialScanner.addItem("COM4");
        jcboSerialScanner.addItem("COM5");
        jcboSerialScanner.addItem("/dev/ttyS0");
        jcboSerialScanner.addItem("/dev/ttyS1");
        jcboSerialScanner.addItem("/dev/ttyS2");
        jcboSerialScanner.addItem("/dev/ttyS3");    
        
        // Printers
        String[] printernames = ReportUtils.getPrintNames();
        for (String name : printernames) {
          cboPrinters.addItem(name);
        }
    }
    
    public boolean hasChanged() {
        return dirty.isDirty();
    }
    
    public Component getConfigComponent() {
        return this;
    }
    
    private boolean loadPrinterConfiguration(AppConfig config, int id) {
        String key = id == 1 ? "machine.printer" : "machine.printer." + id;
        String value = config.getProperty(key);
        if (value == null || value.trim().length() <= 0) {
            return false;
        }

        StringParser p = new StringParser(value);
        String sparam = unifySerialInterface(p.nextToken(':'));

        Map<String, String> printConfig = printersMap.get(id);
        if (printConfig == null) {
            printersMap.put(id, printConfig = new LinkedHashMap<String, String>());
        }

        if ("serial".equals(sparam) || "file".equals(sparam)) {
            printConfig.put(PRINTER_CONFIG_TYPE, "epson");
            printConfig.put(PRINTER_CONFIG_CONN, sparam);
            printConfig.put(PRINTER_CONFIG_SERIAL, p.nextToken(','));
        } else if("javapos".equals(sparam)) {
            printConfig.put(PRINTER_CONFIG_TYPE, sparam);
            printConfig.put(PRINTER_CONFIG_JPOSPRINTER, p.nextToken(','));
        } else {
            printConfig.put(PRINTER_CONFIG_TYPE, sparam);
            printConfig.put(PRINTER_CONFIG_CONN, unifySerialInterface(p.nextToken(',')));
            printConfig.put(PRINTER_CONFIG_SERIAL, p.nextToken(','));
        }
        return true;
    }

    private void loadPrinterConfigurations(AppConfig config) {
        printersMap.clear();

        for (int i = 1; loadPrinterConfiguration(config, i); i++) {
            //Nothing to do
        }

        refreshPrinterConfigModel();
    }

    private void refreshPrinterConfigModel() {
        ((JPrinterConfigTableModel) jPrinters.getModel()).refresh(printersMap);
    }
   
    public void loadProperties(AppConfig config) {
        
        jtxtMachineHostname.setText(config.getProperty("machine.hostname"));
       
        String lafclass = config.getProperty("swing.defaultlaf");
        jcboLAF.setSelectedItem(null);
        for (int i = 0 ; i < jcboLAF.getItemCount(); i++)  {
            LAFInfo lafinfo = (LAFInfo) jcboLAF.getItemAt(i);
            if (lafinfo.getClassName().equals(lafclass)) {
                jcboLAF.setSelectedIndex(i);
                break;
            }
        }
        // jcboLAF.setSelectedItem(new LookAndFeelInfo());
        
        jcboMachineScreenmode.setSelectedItem(config.getProperty("machine.screenmode"));
        jcboTicketsBag.setSelectedItem(config.getProperty("machine.ticketsbag"));

        loadPrinterConfigurations(config);
        
        StringParser p = new StringParser(config.getProperty("machine.display"));
        String sparam = unifySerialInterface(p.nextToken(':'));
        if ("serial".equals(sparam) || "file".equals(sparam)) {
            jcboMachineDisplay.setSelectedItem("epson");
            jcboConnDisplay.setSelectedItem(sparam);
            jcboSerialDisplay.setSelectedItem(p.nextToken(','));
        } else if ("javapos".equals(sparam)) {
            jcboMachineDisplay.setSelectedItem(sparam);
            m_jtxtJPOSName.setText(p.nextToken(','));
        } else {
            jcboMachineDisplay.setSelectedItem(sparam);
            jcboConnDisplay.setSelectedItem(unifySerialInterface(p.nextToken(',')));
            jcboSerialDisplay.setSelectedItem(p.nextToken(','));
        }   
        
        p = new StringParser(config.getProperty("machine.scale"));
        sparam = p.nextToken(':');
        jcboMachineScale.setSelectedItem(sparam);
        if ("dialog1".equals(sparam) || "samsungesp".equals(sparam)) {
            jcboSerialScale.setSelectedItem(p.nextToken(','));
        }
        
        p = new StringParser(config.getProperty("machine.scanner"));
        sparam = p.nextToken(':');
        jcboMachineScanner.setSelectedItem(sparam);
        if ("scanpal2".equals(sparam)) {
            jcboSerialScanner.setSelectedItem(p.nextToken(','));
        }    
        
        cboPrinters.setSelectedItem(config.getProperty("machine.printername"));

        jTextQTSeries.setText(config.getProperty("sequence.qtnumber"));
        jTextBillSeries.setText(config.getProperty("sequence.billnumber"));

        
        dirty.setDirty(false);        
    }
    
    private void savePrintConfiguration(AppConfig config, int id) {
        Map<String, String> printConfig = printersMap.get(id);
        String sMachinePrinter = comboValue(printConfig.get(PRINTER_CONFIG_TYPE));
        StringBuffer value = new StringBuffer(sMachinePrinter);
        if ("epson".equals(sMachinePrinter) || "tmu220".equals(sMachinePrinter) || "star".equals(sMachinePrinter) || "ithaca".equals(sMachinePrinter) || "surepos".equals(sMachinePrinter)) {
            value.append(":").append(comboValue(printConfig.get(PRINTER_CONFIG_CONN))).append(",").append(comboValue(printConfig.get(PRINTER_CONFIG_SERIAL)));
        } else if ("javapos".equals(sMachinePrinter)) {
            value.append(":").append(printConfig.get(PRINTER_CONFIG_JPOSPRINTER));
        }
        
        String key = id == 1 ? "machine.printer" : "machine.printer." + id;
        config.setProperty(key, value.toString());
        
    }

    private void savePrintConfigurations(AppConfig config) {
        for (int id : printersMap.keySet()) {
            savePrintConfiguration(config, id);
        }
    }
   
    public void saveProperties(AppConfig config) {

        config.setProperty("machine.hostname", jtxtMachineHostname.getText());
        
        LAFInfo laf = (LAFInfo) jcboLAF.getSelectedItem();
        config.setProperty("swing.defaultlaf", laf == null 
                ? System.getProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel")
                : laf.getClassName());
        
        config.setProperty("machine.screenmode", comboValue(jcboMachineScreenmode.getSelectedItem()));
        config.setProperty("machine.ticketsbag", comboValue(jcboTicketsBag.getSelectedItem()));
        
        savePrintConfigurations(config);
        
        String sMachineDisplay = comboValue(jcboMachineDisplay.getSelectedItem());
        if ("epson".equals(sMachineDisplay) || "ld200".equals(sMachineDisplay) || "surepos".equals(sMachineDisplay)) {
            config.setProperty("machine.display", sMachineDisplay + ":" + comboValue(jcboConnDisplay.getSelectedItem()) + "," + comboValue(jcboSerialDisplay.getSelectedItem()));
        } else if ("javapos".equals(sMachineDisplay)) {
            config.setProperty("machine.display", sMachineDisplay + ":" + m_jtxtJPOSName.getText());
        } else {
            config.setProperty("machine.display", sMachineDisplay);
        }
        
        // La bascula
        String sMachineScale = comboValue(jcboMachineScale.getSelectedItem());
        if ("dialog1".equals(sMachineScale) || "samsungesp".equals(sMachineScale)) {
            config.setProperty("machine.scale", sMachineScale + ":" + comboValue(jcboSerialScale.getSelectedItem()));
        } else {
            config.setProperty("machine.scale", sMachineScale);
        }
        
        // El scanner
        String sMachineScanner = comboValue(jcboMachineScanner.getSelectedItem());
        if ("scanpal2".equals(sMachineScale)) {
            config.setProperty("machine.scanner", sMachineScanner + ":" + comboValue(jcboSerialScanner.getSelectedItem()));
        } else {
            config.setProperty("machine.scanner", sMachineScanner);
        }
        
        config.setProperty("machine.printername", comboValue(cboPrinters.getSelectedItem()));       
        
        config.setProperty("sequence.qtnumber", jTextQTSeries.getText());
        config.setProperty("sequence.billnumber", jTextBillSeries.getText());
        config.setProperty("sequence.receiptnumber", jTextReceiptse.getText());

        dirty.setDirty(false);
    }
    
    private String unifySerialInterface(String sparam) {
        
        // for backward compatibility
        return ("rxtx".equals(sparam)) 
            ? "serial"
            : sparam;
    }
    
    private String comboValue(Object value) {
        return value == null ? "" : value.toString();
    }
    
    private void changeLAF() {                                        

        final LAFInfo laf = (LAFInfo) jcboLAF.getSelectedItem();
        if (laf != null && !laf.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
            // The selected look and feel is different from the current look and feel.
           SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {                       
                        String lafname = laf.getClassName();                       
                        Object laf = Class.forName(lafname).newInstance();

                        if (laf instanceof LookAndFeel){
                            UIManager.setLookAndFeel((LookAndFeel) laf);
                        } else if (laf instanceof SubstanceSkin) {                         
                            SubstanceLookAndFeel.setSkin((SubstanceSkin) laf); 
                        }
                    
                        SwingUtilities.updateComponentTreeUI(JPanelConfigGeneral.this.getTopLevelAncestor());
                    } catch (Exception e) {
                    }
                }
            });
        }        
    }      
        
    private static class LAFInfo {
        private String name;
        private String classname;
        
        public LAFInfo(String name, String classname) {
            this.name = name;
            this.classname = classname;
        }
        public String getName() {
            return name;
        }
        public String getClassName() {
            return classname;
        }
        @Override
        public String toString() {
            return name;
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtxtMachineHostname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcboMachineScreenmode = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jcboMachineDisplay = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jcboTicketsBag = new javax.swing.JComboBox();
        m_jDisplayParams = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlblConnDisplay = new javax.swing.JLabel();
        jcboConnDisplay = new javax.swing.JComboBox();
        jlblDisplayPort = new javax.swing.JLabel();
        jcboSerialDisplay = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        m_jtxtJPOSName = new javax.swing.JTextField();
        m_jScaleParams = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jlblPrinterPort4 = new javax.swing.JLabel();
        jcboSerialScale = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jcboMachineScale = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jcboMachineScanner = new javax.swing.JComboBox();
        m_jScannerParams = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jlblPrinterPort5 = new javax.swing.JLabel();
        jcboSerialScanner = new javax.swing.JComboBox();
        jcboLAF = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextBillSeries = new javax.swing.JTextField();
        jTextQTSeries = new javax.swing.JTextField();
        cboPrinters = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPrinters = new javax.swing.JTable();
        jPrinterAdd = new javax.swing.JButton();
        jPrinterEdit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextReceiptse = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(680, 370));
        setLayout(null);

        jtxtMachineHostname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtMachineHostnameActionPerformed(evt);
            }
        });
        add(jtxtMachineHostname);
        jtxtMachineHostname.setBounds(150, 40, 180, 20);

        jLabel5.setText(AppLocal.getIntString("Label.MachineName")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 40, 130, 14);

        jLabel6.setText(AppLocal.getIntString("Label.MachineScreen")); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(20, 100, 130, 14);

        jcboMachineScreenmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineScreenmodeActionPerformed(evt);
            }
        });
        add(jcboMachineScreenmode);
        jcboMachineScreenmode.setBounds(150, 100, 180, 20);

        jLabel9.setText(AppLocal.getIntString("Label.CashMachine")); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add(jLabel9);
        jLabel9.setBounds(20, 10, 660, 15);

        jLabel15.setText(AppLocal.getIntString("Label.MachineDisplay")); // NOI18N
        add(jLabel15);
        jLabel15.setBounds(20, 160, 130, 14);

        jcboMachineDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineDisplayActionPerformed(evt);
            }
        });
        add(jcboMachineDisplay);
        jcboMachineDisplay.setBounds(150, 160, 180, 20);

        jLabel16.setText(AppLocal.getIntString("Label.Ticketsbag")); // NOI18N
        add(jLabel16);
        jLabel16.setBounds(20, 130, 130, 14);

        jcboTicketsBag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboTicketsBagActionPerformed(evt);
            }
        });
        add(jcboTicketsBag);
        jcboTicketsBag.setBounds(150, 130, 180, 20);

        m_jDisplayParams.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(null);
        m_jDisplayParams.add(jPanel2, "empty");

        jPanel1.setLayout(null);

        jlblConnDisplay.setText(AppLocal.getIntString("label.machinedisplayconn")); // NOI18N
        jPanel1.add(jlblConnDisplay);
        jlblConnDisplay.setBounds(10, 0, 50, 14);
        jPanel1.add(jcboConnDisplay);
        jcboConnDisplay.setBounds(60, 0, 80, 20);

        jlblDisplayPort.setText(AppLocal.getIntString("label.machinedisplayport")); // NOI18N
        jPanel1.add(jlblDisplayPort);
        jlblDisplayPort.setBounds(160, 0, 50, 14);

        jcboSerialDisplay.setEditable(true);
        jPanel1.add(jcboSerialDisplay);
        jcboSerialDisplay.setBounds(210, 0, 130, 20);

        m_jDisplayParams.add(jPanel1, "comm");

        jPanel3.setLayout(null);

        jLabel20.setText(AppLocal.getIntString("Label.Name")); // NOI18N
        jPanel3.add(jLabel20);
        jLabel20.setBounds(10, 0, 50, 14);
        jPanel3.add(m_jtxtJPOSName);
        m_jtxtJPOSName.setBounds(60, 0, 150, 20);

        m_jDisplayParams.add(jPanel3, "javapos");

        add(m_jDisplayParams);
        m_jDisplayParams.setBounds(340, 160, 350, 20);

        m_jScaleParams.setLayout(new java.awt.CardLayout());

        jPanel16.setLayout(null);
        m_jScaleParams.add(jPanel16, "empty");

        jPanel17.setLayout(null);

        jlblPrinterPort4.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N
        jPanel17.add(jlblPrinterPort4);
        jlblPrinterPort4.setBounds(10, 0, 50, 14);

        jcboSerialScale.setEditable(true);
        jPanel17.add(jcboSerialScale);
        jcboSerialScale.setBounds(60, 0, 130, 20);

        m_jScaleParams.add(jPanel17, "comm");

        add(m_jScaleParams);
        m_jScaleParams.setBounds(340, 190, 350, 20);

        jLabel25.setText(AppLocal.getIntString("label.scale")); // NOI18N
        add(jLabel25);
        jLabel25.setBounds(20, 190, 130, 14);

        jcboMachineScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineScaleActionPerformed(evt);
            }
        });
        add(jcboMachineScale);
        jcboMachineScale.setBounds(150, 190, 180, 20);

        jLabel26.setText(AppLocal.getIntString("label.scanner")); // NOI18N
        add(jLabel26);
        jLabel26.setBounds(20, 220, 130, 14);

        jcboMachineScanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboMachineScannerActionPerformed(evt);
            }
        });
        add(jcboMachineScanner);
        jcboMachineScanner.setBounds(150, 220, 180, 20);

        m_jScannerParams.setLayout(new java.awt.CardLayout());

        jPanel18.setLayout(null);
        m_jScannerParams.add(jPanel18, "empty");

        jPanel19.setLayout(null);

        jlblPrinterPort5.setText(AppLocal.getIntString("label.machineprinterport")); // NOI18N
        jPanel19.add(jlblPrinterPort5);
        jlblPrinterPort5.setBounds(10, 0, 50, 14);

        jcboSerialScanner.setEditable(true);
        jPanel19.add(jcboSerialScanner);
        jcboSerialScanner.setBounds(60, 0, 130, 20);

        m_jScannerParams.add(jPanel19, "comm");

        add(m_jScannerParams);
        m_jScannerParams.setBounds(340, 220, 350, 20);
        add(jcboLAF);
        jcboLAF.setBounds(150, 70, 180, 20);

        jLabel2.setText(AppLocal.getIntString("label.looknfeel")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 70, 130, 14);
        add(jTextBillSeries);
        jTextBillSeries.setBounds(470, 70, 180, 20);

        jTextQTSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextQTSeriesActionPerformed(evt);
            }
        });
        add(jTextQTSeries);
        jTextQTSeries.setBounds(470, 40, 180, 20);

        cboPrinters.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(cboPrinters);
        cboPrinters.setBounds(150, 250, 180, 20);

        jLabel1.setText("Reports Printer"); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 250, 110, 14);

        jLabel3.setText("QT Series"); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(360, 40, 90, 14);

        jLabel4.setText("Bill Series"); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(360, 70, 90, 14);

        jPrinters.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Printer No", "Printer Configuration"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jPrinters.setAutoscrolls(false);
        jScrollPane1.setViewportView(jPrinters);
        jPrinters.getColumnModel().getColumn(0).setMinWidth(70);
        jPrinters.getColumnModel().getColumn(0).setPreferredWidth(70);
        jPrinters.getColumnModel().getColumn(0).setMaxWidth(70);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 290, 310, 70);

        jPrinterAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/editnew.png"))); // NOI18N
        jPrinterAdd.setToolTipText("Add New Printer"); // NOI18N
        jPrinterAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrinterAddActionPerformed(evt);
            }
        });
        add(jPrinterAdd);
        jPrinterAdd.setBounds(340, 290, 30, 25);

        jPrinterEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        jPrinterEdit.setToolTipText("Edit Printer Configuration"); // NOI18N
        jPrinterEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrinterEditActionPerformed(evt);
            }
        });
        add(jPrinterEdit);
        jPrinterEdit.setBounds(340, 320, 30, 25);

        jLabel7.setText("Receipt Series");
        add(jLabel7);
        jLabel7.setBounds(360, 110, 70, 14);
        add(jTextReceiptse);
        jTextReceiptse.setBounds(470, 110, 180, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jcboMachineScannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineScannerActionPerformed
        CardLayout cl = (CardLayout)(m_jScannerParams.getLayout());
        
        if ("scanpal2".equals(jcboMachineScanner.getSelectedItem())) {
            cl.show(m_jScannerParams, "comm");
        } else {
            cl.show(m_jScannerParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineScannerActionPerformed

    private void jcboMachineScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineScaleActionPerformed
        CardLayout cl = (CardLayout)(m_jScaleParams.getLayout());
        
        if ("dialog1".equals(jcboMachineScale.getSelectedItem()) || "samsungesp".equals(jcboMachineScale.getSelectedItem())) {
            cl.show(m_jScaleParams, "comm");
        } else {
            cl.show(m_jScaleParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineScaleActionPerformed

    private void jcboMachineDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineDisplayActionPerformed
        CardLayout cl = (CardLayout)(m_jDisplayParams.getLayout());
        
        if ("epson".equals(jcboMachineDisplay.getSelectedItem()) || "ld200".equals(jcboMachineDisplay.getSelectedItem()) || "surepos".equals(jcboMachineDisplay.getSelectedItem())) {
            cl.show(m_jDisplayParams, "comm");
        } else if ("javapos".equals(jcboMachineDisplay.getSelectedItem())) {
            cl.show(m_jDisplayParams, "javapos");
        } else {
            cl.show(m_jDisplayParams, "empty");
        }
    }//GEN-LAST:event_jcboMachineDisplayActionPerformed

    private void jTextQTSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextQTSeriesActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextQTSeriesActionPerformed

    private void jcboTicketsBagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboTicketsBagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboTicketsBagActionPerformed

    private void jtxtMachineHostnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtMachineHostnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtMachineHostnameActionPerformed

    private void jcboMachineScreenmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboMachineScreenmodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboMachineScreenmodeActionPerformed

    private void jPrinterEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrinterEditActionPerformed
        int i = jPrinters.getSelectedRow();
        if (i >= 0 && i < printersMap.size()) {
            int id = i + 1;
            PrinterConfigEditorDialog dialog = PrinterConfigEditorDialog.getDialog(this, id, printersMap.get(id));
            if (dialog.showDialog()) {
                dirty.setDirty(true);
                refreshPrinterConfigModel();
            }
        }
    }//GEN-LAST:event_jPrinterEditActionPerformed

    private void jPrinterAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrinterAddActionPerformed
        int id = printersMap.size() + 1;
        Map<String, String> printConfig = new LinkedHashMap<String, String>();
        PrinterConfigEditorDialog dialog = PrinterConfigEditorDialog.getDialog(this, id, printConfig);
        if (dialog.showDialog()) {
            //On ok add it to the map
            printersMap.put(id, printConfig);
            dirty.setDirty(true);
            refreshPrinterConfigModel();
        }
    }//GEN-LAST:event_jPrinterAddActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboPrinters;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jPrinterAdd;
    private javax.swing.JButton jPrinterEdit;
    private javax.swing.JTable jPrinters;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextBillSeries;
    private javax.swing.JTextField jTextQTSeries;
    private javax.swing.JTextField jTextReceiptse;
    private javax.swing.JComboBox jcboConnDisplay;
    private javax.swing.JComboBox jcboLAF;
    private javax.swing.JComboBox jcboMachineDisplay;
    private javax.swing.JComboBox jcboMachineScale;
    private javax.swing.JComboBox jcboMachineScanner;
    private javax.swing.JComboBox jcboMachineScreenmode;
    private javax.swing.JComboBox jcboSerialDisplay;
    private javax.swing.JComboBox jcboSerialScale;
    private javax.swing.JComboBox jcboSerialScanner;
    private javax.swing.JComboBox jcboTicketsBag;
    private javax.swing.JLabel jlblConnDisplay;
    private javax.swing.JLabel jlblDisplayPort;
    private javax.swing.JLabel jlblPrinterPort4;
    private javax.swing.JLabel jlblPrinterPort5;
    private javax.swing.JTextField jtxtMachineHostname;
    private javax.swing.JPanel m_jDisplayParams;
    private javax.swing.JPanel m_jScaleParams;
    private javax.swing.JPanel m_jScannerParams;
    private javax.swing.JTextField m_jtxtJPOSName;
    // End of variables declaration//GEN-END:variables
    
}

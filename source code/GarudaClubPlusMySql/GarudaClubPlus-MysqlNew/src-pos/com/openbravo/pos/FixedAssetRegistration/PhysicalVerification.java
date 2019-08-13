/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.FixedAssetRegistration;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import static com.openbravo.pos.FixedAssetRegistration.PhotogpDialog.getWindow;
import com.openbravo.pos.FixedAssetRegistration.PhysicalVerificationTableModel.PhysicalVerificationInfo;
import com.openbravo.pos.FixedAssetRegistration.FALocationsTableModel.FALocationsInfo;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author dev3
 */
public class PhysicalVerification extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    JFileChooser chooser;
    String choosertitle;
    File srcLogo = null;
    File srcLogo1 = null;
    private PhysicalVerificationTableModel phyver_table;
    String remarksp;
    private List<PhysicalVerificationTableModel.PhysicalVerificationInfo> data;
    String id;
    public static String PVID = null;
    String filename = null;
    String deactid;
    public String faid;
    String name1 = null;
    String nameofimg = null;
    private int flagkey;
    private ComboBoxValModel locationListModel;//added by pratima
    public SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyy_HHmmss");
    private List<FALocationsTableModel.FALocationsInfo> locationList = new ArrayList<FALocationsTableModel.FALocationsInfo>();
    
    /**
     * Creates new form PhysicalVerification
     */
    public PhysicalVerification(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public PhysicalVerification(java.awt.Dialog parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    public PhysicalVerification(java.awt.Frame parent, AppView app, boolean flag) {
        super(parent, true);

        this.app = app;
        this.flag = flag;
    }

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static PhysicalVerification getDialog(Component parent, AppView app, boolean flag) throws BasicException {

        Window window = getWindow(parent);

        PhysicalVerification bill;

        if (window instanceof Frame) {
            bill = new PhysicalVerification((Frame) window, app, flag);
        } else {
            bill = new PhysicalVerification((Dialog) window, app, flag);
        }

        return bill;

    }

    public boolean showDialog() {
        try {
            init();
            setVisible(true);

        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return true;
    }

    public void init() throws BasicException {
        initComponents();

        phyver_table = PhysicalVerificationTableModel.GetPhysicalVerificationTableModel(app);
        jTable1.setModel(phyver_table.getTableModel());
         jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add_but.setVisible(true);
        addchangs_but.setVisible(false);
        addChangsButton.setVisible(false);
        verdate_txt.setEditable(false);
        locationListModel = new ComboBoxValModel(getLocationList());//added by pratima
        jLocationComboBox.setModel(locationListModel);//added by pratima
         loadPageData();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        verdate_txt = new javax.swing.JTextField();
        verdate = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        verby_txt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        verremarks_txt = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        pbarcode_txt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        verphoto = new javax.swing.JButton();
        photolabel = new javax.swing.JLabel();
        Reset = new javax.swing.JButton();
        add_but = new javax.swing.JButton();
        addchangs_but = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLocationComboBox = new javax.swing.JComboBox<>();
        addChangsButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Edit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
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
        deact_but = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setText("Verification Date");

        verdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png")));
        verdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verdateActionPerformed(evt);
            }
        });

        jLabel23.setText("Verified By");

        jLabel24.setText("Verifier Remark");

        verremarks_txt.setColumns(20);
        verremarks_txt.setRows(5);
        jScrollPane3.setViewportView(verremarks_txt);

        jLabel25.setText("Barcode");

        jLabel26.setText("Photograp if any");

        verphoto.setText("Select Photo");
        verphoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verphotoActionPerformed(evt);
            }
        });

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        add_but.setText("Add");
        add_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_butActionPerformed(evt);
            }
        });

        addchangs_but.setText("Add Changes");
        addchangs_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addchangs_butActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Location");

        jLocationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLocationComboBoxActionPerformed(evt);
            }
        });

        addChangsButton.setText("Add Changes");
        addChangsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChangsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(verdate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(verdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pbarcode_txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(verby_txt, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addComponent(add_but, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(addChangsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(photolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(addchangs_but, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(verdate_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verby_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pbarcode_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(photolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(verphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reset)
                    .addComponent(add_but)
                    .addComponent(addchangs_but)
                    .addComponent(addChangsButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Physical Verification", jPanel9);

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        deact_but.setText("Deactivate");
        deact_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deact_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deact_but, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Edit)
                    .addComponent(deact_but))
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("View List", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verdateActionPerformed
        verdate.setEnabled(false);
        Date d;
        try {
            d = (Date) Formats.TIMESTAMP.parseValue(verdate_txt.getText());
        } catch (BasicException e) {
            d = null;
        }
        d = JCalendarDialog.showCalendarTimeHours(this, d);
        if (d != null) {

            verdate_txt.setText(Formats.TIMESTAMP.formatValue(d));
        }
    }//GEN-LAST:event_verdateActionPerformed

    private void verphotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verphotoActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));// File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        filename = f.getAbsolutePath();
        photolabel.setIcon(ResizeImage(filename));
        BufferedImage image = null;
        try {

//            String string = UUID.randomUUID().toString();
//            String[] parts = string.split("-");
//            String part1 = parts[0];
//            String part2 = parts[1];

            //you can either use URL or File for reading image using ImageIO
            File imagefile = new File(filename);
            image = ImageIO.read(imagefile);
            String memberNotemp1 = "s" + "m.jpg";
            //nameofimg = "\"" + "image" + part1 + ".jpg" + "\"";
             Calendar c = Calendar.getInstance();
             String currentDate=sdf.format(c.getTime());
             name1=FixedAsset2.fixedid+""+currentDate+".jpg";
           // name1 = "image" + part1 + ".jpg";
            String folderName=null;
            try{
             Object[] PhotoSrcobj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable where NAME = 'AssetPhotos'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                         folderName=(PhotoSrcobj1[0]).toString();

            } catch (BasicException e) {
            e.printStackTrace(); 
            }
           // ImageIO.write(image, "jpg", new File("./AssetPhotos/" + name1 + ""));
           ImageIO.write(image, "jpg", new File("./"+folderName+"/" + name1 + ""));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_verphotoActionPerformed
    private Icon ResizeImage(String filename) {

        ImageIcon MyImage = new ImageIcon(filename);
        Image img = MyImage.getImage();
        Image newimg = img.getScaledInstance(photolabel.getWidth(), photolabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        return image;
        
    }
    private void add_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_butActionPerformed

        try {

            if (pbarcode_txt.getText() != null && pbarcode_txt.getText().trim().length() > 0) {

                if (verdate_txt.getText() != null && verdate_txt.getText().trim().length() > 0) {
                    if ((verby_txt.getText() != null && verby_txt.getText().trim().length() > 0) && (verremarks_txt.getText() != null && verremarks_txt.getText().trim().length() > 0)) {
                        Transaction t = new Transaction(app.getSession()) {
                            @Override
                            protected Object transact() throws BasicException {

                                String filename1 = null;

                                Date verdatephoto = new Date();
                                Date effectivedate = new Date();
                                Calendar cal = Calendar.getInstance();

                                cal.setTimeInMillis(verdatephoto.getTime());

                                verdatephoto.setTime(cal.getTimeInMillis());
                                verdatephoto = (Date) Formats.TIMESTAMP.parseValue(verdate_txt.getText());

                                if (srcLogo1 != null) {
                                    filename1 = srcLogo1 + srcLogo1.getAbsoluteFile().getName();

                                }
                                //addedby pratima
                                  String location=null;
                                if(jLocationComboBox.getSelectedIndex()!=-1){
                                location=locationList.get( jLocationComboBox.getSelectedIndex()).getId();
                                }else{
                                    location="";
                                }
                                 //endedby pratima
                                PVID = UUID.randomUUID().toString();
                                Object[] PhotoSrcobj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable where NAME = 'AssetPhotos'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                                  String folderName=(PhotoSrcobj1[0]).toString();
                                String nameofpho="./"+folderName+"/" + name1;
                                Object[] countobj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT COUNT(*) FROM fa_physicalverification where fa_id = ?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(FixedAsset2.fixedid);
                                 System.out.println("count"+countobj1[0].toString());
                                 int count=Integer.parseInt(countobj1[0].toString());
                                 System.out.println("count"+count);
                                if(count>0){
                                    Object[] deacrefObj = (Object[]) new StaticSentence(app.getSession(), "SELECT ID FROM fa_physicalverification where active=true and fa_id=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(FixedAsset2.fixedid);
                                
                                  int update_PhysicalVerification = new PreparedSentence(app.getSession(), "update fa_physicalverification set active=false,daecreference=? where active=true and fa_id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{deacrefObj[0].toString(),FixedAsset2.fixedid});
                   
                                 }
                                Object[] param1 = new Object[]{PVID, FixedAsset2.fixedid, verdatephoto, verby_txt.getText().trim(), pbarcode_txt.getText().trim(), verremarks_txt.getText().trim(), nameofpho, app.getAppUserView().getUser().getName(), new Date(), true,location};//added location fileld by pratima
                                new PreparedSentence(app.getSession(), "insert into fa_physicalverification (id,fa_id,ver_date,ver_by,barcode_uid,ver_remarks,photo,created_by,created_date,active,location) values (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(param1);
                                flagkey=1;
                                reset();
                                return null;
                            }
                        };
                        t.execute();
                        if(flagkey==1){
                        JOptionPane.showMessageDialog(this, "Physical Verification DataS inserted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please ensure that Verified by and Verified remarks is not empty", null, JOptionPane.OK_OPTION);
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Please ensure that Date is not empty", null, JOptionPane.OK_OPTION);

                }

            } else {

                JOptionPane.showMessageDialog(this, "Please ensure that Barcode is not empty", null, JOptionPane.OK_OPTION);

            }
        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(PhysicalVerification.class.getName()).log(Level.SEVERE, null, e);
            new MessageInf(e).show(new JFrame());

        }
    }//GEN-LAST:event_add_butActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        reset();
    }//GEN-LAST:event_ResetActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
        if (jTable1.getSelectedRow() != -1) {
            addChangsButton.setVisible(true);
             int row1 = jTable1.getSelectedRow();
                    PhysicalVerificationInfo showdata1 = phyver_table.getList().get(row1);
                    if(showdata1.getActive()){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to Edit Data ?? ", "Editing Menu", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {

                if (jTable1.getSelectedRow() < phyver_table.getSize()) {
                    int row = jTable1.getSelectedRow();
                    PhysicalVerificationInfo showdata = phyver_table.getList().get(row);
                    verphoto.setVisible(false);
                    verdate.setEnabled(false);
                    verby_txt.setEditable(false);
                    pbarcode_txt.setEditable(false);
                    verdate_txt.setEditable(false);
                    String verifidate = showdata.getphotoDate();
                    String veriby = showdata.getVerifiedBy();
                    String barcode = showdata.getBarcode();

                    String link = showdata.getphotolink();

                    String remarks = showdata.getremarks();
                    
                    String location="";
                       try{
                       
                    location=  phyver_table.getLocationList(showdata.getLocation()).get(0).toString() ;
                    System.out.println("location"+location);
                     getLocationList();
                     for(int z=0;z<getLocationList().size();z++){
                   if( locationList.get(z).getId().equals(showdata.getLocation()))
                       jLocationComboBox.setSelectedIndex(z);
                     }
                   }catch(Exception ex){ex.printStackTrace();}
                         //added  by pratima
                            
                                     verremarks_txt.setText(remarks + "");
                    if (link != null) {
                        photolabel.setIcon(ResizeImage(link));
                    } else {
                        photolabel.setIcon(null);
                    }
                    verdate_txt.setText(verifidate + "");
                    verby_txt.setText(veriby + "");
                    pbarcode_txt.setText(barcode + "");

                    jTabbedPane1.setSelectedIndex(0);
                    
                   
                    add_but.setVisible(false);

                }
            }
        
        }else{ JOptionPane.showMessageDialog(this, "Please select active asset data in the table ", null, JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_EditActionPerformed

    private void addchangs_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addchangs_butActionPerformed
        
    }//GEN-LAST:event_addchangs_butActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        javax.swing.JTabbedPane tabpane = (javax.swing.JTabbedPane) evt.getSource();
        int tabno = tabpane.getSelectedIndex();

        if (tabno == 1) {
            try {
                phyver_table = PhysicalVerificationTableModel.GetPhysicalVerificationTableModel(app);

                jTable1.setModel(phyver_table.getTableModel());
                jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                 TableColumnModel cmodel = jTable1.getColumnModel();
                cmodel.getColumn(0).setPreferredWidth(30);
                cmodel.getColumn(1).setPreferredWidth(110);
                cmodel.getColumn(2).setPreferredWidth(100);
                cmodel.getColumn(3).setPreferredWidth(100);
                cmodel.getColumn(4).setPreferredWidth(100);

                cmodel.getColumn(5).setPreferredWidth(80);
                cmodel.getColumn(6).setPreferredWidth(130);

            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void deact_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deact_butActionPerformed

        if (jTable1.getSelectedRow() != -1) {
             int row = jTable1.getSelectedRow();
                    PhysicalVerificationInfo showdata = phyver_table.getList().get(row);
                    if(showdata.getActive()){
            int bill = JOptionPane.showConfirmDialog(jPanel1, " Do you want to deactivate ?? ", "Deactivation", JOptionPane.YES_NO_OPTION);
            if (bill == JOptionPane.YES_OPTION) {
               
                    deactid = showdata.getId();
                    deactphysver();

                
            }
        }else{
        JOptionPane.showMessageDialog(this, "Please select active asset data in the table ", null, JOptionPane.WARNING_MESSAGE);

        }
        }else{
        JOptionPane.showMessageDialog(this, "Please select row in the table ", null, JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_deact_butActionPerformed

    private void jLocationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLocationComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLocationComboBoxActionPerformed

    private void addChangsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChangsButtonActionPerformed
        // TODO add your handling code here:
        if (verremarks_txt.getText() != null && verremarks_txt.getText().trim().length() > 0) {
            int row = jTable1.getSelectedRow();
              PhysicalVerificationTableModel.PhysicalVerificationInfo data = phyver_table.getList().get(row);
//            for(int i=0;i<phyver_table.getList().size();i++){
//            if(phyver_table.getList().get(i).getActive())
//            data = phyver_table.getList().get(i);
//            }
            id = data.getId();

            remarksp = verremarks_txt.getText();

            Transaction t = new Transaction(app.getSession()) {

                @Override
                protected Object transact() throws BasicException {

                    Date verdatephoto = new Date();
                    Date effectivedate = new Date();
                    Calendar cal = Calendar.getInstance();

                    cal.setTimeInMillis(verdatephoto.getTime());
                    cal.set(Calendar.HOUR_OF_DAY, 00);
                    cal.set(Calendar.MINUTE, 00);
                    cal.set(Calendar.SECOND, 00);
                    cal.set(Calendar.MILLISECOND, 00);
                        
                     //addedby pratima
                     String location=null;
                                if(jLocationComboBox.getSelectedIndex()!=-1){
                                location=locationList.get( jLocationComboBox.getSelectedIndex()).getId();
                                }else{
                                    location="";
                                }
                    //endedby pratima
                    verdatephoto.setTime(cal.getTimeInMillis());
                    verdatephoto = (Date) Formats.TIMESTAMP.parseValue(verdate_txt.getText());
                    PVID = UUID.randomUUID().toString();
                    Object[] PhotoSrcobj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable where NAME = 'AssetPhotos'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                                  String folderName=(PhotoSrcobj1[0]).toString();
                    String nameofpho="./"+folderName+"/" + name1;
                    Object[] param1 = new Object[]{PVID, FixedAsset2.fixedid, verdatephoto, verby_txt.getText().trim(), pbarcode_txt.getText().trim(), verremarks_txt.getText().trim(), nameofpho, app.getAppUserView().getUser().getName(), new Date(), true,location};
                    new PreparedSentence(app.getSession(), "insert into fa_physicalverification (id,fa_id,ver_date,ver_by,barcode_uid,ver_remarks,photo,created_by,created_date,active,location) values (?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN,Datas.STRING})).exec(param1);

                    int update_PhysicalVerification = new PreparedSentence(app.getSession(), "update fa_physicalverification set active=false,daecreference=? where id=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{PVID,id});
                   flagkey=4;
                   reset(); 
                    return null;
                }
            };

            try {
                t.execute();
                if(flagkey==4){
                JOptionPane.showMessageDialog(this, "Updated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                }
            } catch (BasicException ex) {
                Logger.getLogger(PhysicalVerification.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                new MessageInf(ex).show(new JFrame());

            }

        } else {
            JOptionPane.showMessageDialog(this, "Enter remarks.... !! ", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_addChangsButtonActionPerformed
    private void deactphysver() {
        try {

            new PreparedSentence(app.getSession(), "update fa_physicalverification  set  active=0  , deacby=? , deacdate=?  where id = ? ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{app.getAppUserView().getUser().getName(), new Date(), deactid});

            phyver_table = PhysicalVerificationTableModel.GetPhysicalVerificationTableModel(app);
            jTable1.setModel(phyver_table.getTableModel());

            reset();
            JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        pbarcode_txt.setText(null);
        verdate_txt.setText(null);
        verremarks_txt.setText(null);
        addchangs_but.setVisible(false);
        addChangsButton.setVisible(false);
        add_but.setVisible(true);
        verby_txt.setText(null);
        photolabel.setIcon(null);
        verphoto.setVisible(true);

        verdate.setEnabled(true);
        verby_txt.setEditable(true);
        pbarcode_txt.setEditable(true);
        verdate_txt.setEditable(true);
        jLocationComboBox.setSelectedIndex(-1);//added by pratima
    }

    @Override
    public String getTitle() {
        return "Physical Verification";
    }
    //added by pratima
    
        public List getLocationList() throws BasicException {
         locationList = new ArrayList<FALocationsTableModel.FALocationsInfo>();
        List<String> locationList1 = new ArrayList<String>();
        locationList = new StaticSentence(app.getSession(), "select id,name,floor,building,block,type from fa_Locations where active=1 and type='lo' order by name ", null,  new SerializerReadClass(FALocationsTableModel.FALocationsInfo.class)).list();
        for(int i=0;i<locationList.size();i++){
         
            String strloc=locationList.get(i).getName()+","+getNameById(locationList.get(i).getBuilding())+","+getNameById(locationList.get(i).getBlock())+","+getNameById(locationList.get(i).getFloor());
            locationList1.add(strloc);
       }
         
        return locationList1;
    }
 //ended by pratima

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit;
    private javax.swing.JButton Reset;
    private javax.swing.JButton addChangsButton;
    private javax.swing.JButton add_but;
    private javax.swing.JButton addchangs_but;
    private javax.swing.JButton deact_but;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JComboBox<String> jLocationComboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pbarcode_txt;
    private javax.swing.JLabel photolabel;
    private javax.swing.JTextField verby_txt;
    private javax.swing.JButton verdate;
    private javax.swing.JTextField verdate_txt;
    private javax.swing.JButton verphoto;
    private javax.swing.JTextArea verremarks_txt;
    // End of variables declaration//GEN-END:variables

    private static class ExtensionsFilter extends FileFilter {

        private String message;
        private String[] extensions;

        public ExtensionsFilter(String message, String... extensions) {
            this.message = message;
            this.extensions = extensions;
        }

        public boolean accept(java.io.File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                String sFileName = f.getName();
                int ipos = sFileName.lastIndexOf('.');
                if (ipos >= 0) {
                    String sExt = sFileName.substring(ipos + 1);
                    for (String s : extensions) {
                        if (s.equalsIgnoreCase(sExt)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }

        public String getDescription() {
            return message;
        }
    }
 
    public String getNameById(String id){
    String name="";
    try{
 name= (String) new StaticSentence(app.getSession(), "select name from  fa_locations where id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return name;
}
    
    public void loadPageData(){
    for(int g=0;g< phyver_table.getList().size();g++){
        
     PhysicalVerificationInfo showdata = phyver_table.getList().get(g);
     if(showdata.getActive()){
                    verphoto.setVisible(false);
                    verdate.setEnabled(false);
                    verby_txt.setEditable(false);
                    pbarcode_txt.setEditable(false);
                    verdate_txt.setEditable(false);
                    String verifidate = showdata.getphotoDate();
                    String veriby = showdata.getVerifiedBy();
                    String barcode = showdata.getBarcode();

                    String link = showdata.getphotolink();

                    String remarks = showdata.getremarks();
                    
                    String location="";
                       try{
                       
                    location=  phyver_table.getLocationList(showdata.getLocation()).get(0).toString() ;
                    System.out.println("location"+location);
                     getLocationList();
                     for(int z=0;z<getLocationList().size();z++){
                   if( locationList.get(z).getId().equals(showdata.getLocation()))
                       jLocationComboBox.setSelectedIndex(z);
                     }
                   }catch(Exception ex){ex.printStackTrace();}
                         //added  by pratima
                            
                                     verremarks_txt.setText(remarks + "");
                    if (link != null) {
                        photolabel.setIcon(ResizeImage(link));
                    } else {
                        photolabel.setIcon(null);
                    }
                    verdate_txt.setText(verifidate + "");
                    verby_txt.setText(veriby + "");
                    pbarcode_txt.setText(barcode + "");

                    jTabbedPane1.setSelectedIndex(0);
                 
                  
                    add_but.setVisible(false);

                }
            }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FloorEditor.java
 *
 * Created on Apr 2, 2013, 6:55:32 PM
 */
package com.openbravo.pos.Booking;

import com.lowagie.text.Image;
import com.mysql.jdbc.Blob;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppLocal;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.export.ooxml.OoxmlZip;

/**
 *
 * @author user
 */
public class FloorEditor extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

    

   

  
    private AppView m_App;
   private List<FloorEditor.FloorTableInfo> data ;
   private List<FloorEditor.Guest_FloorTableInfo> data1 ;

   
   private ComboBoxValModel FloorListModel ;
   private ComboBoxValModel GuestFloorModel ;
   private FloorTableInfo  fti;
   private Guest_FloorTableInfo  G_fti;
   private FloorEditor FE;
   private FloorEditor GF;
   
   
   
   // CHANGEDD BY AAKASH......  AAKASH
   
   
   
   
   public FloorEditor() {
        initComponents();
        hall_panel.setVisible(false);
        guest_panel.setVisible(false);
        main.setVisible(true);
      
    }

    // for hall floor 
    public static FloorEditor loadInstanceFloorInfo(AppView app) throws BasicException{
        FloorEditor FloorInfo = new FloorEditor(); 
           
        try{
            FloorInfo.data = new ArrayList<FloorTableInfo>();
            FloorInfo.data = new StaticSentence(app.getSession(), " SELECT  ID , NAME , IMAGE , ICON1 , ICON2 , ICON3 , ICON4 FROM create_hall_floor  ", SerializerWriteString.INSTANCE, new SerializerReadClass(FloorEditor.FloorTableInfo.class)).list();
          
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
          return FloorInfo;
     }
   // for guest florr
    public static FloorEditor loadInstanceGuest_FloorInfo(AppView app) throws BasicException{
        FloorEditor Floor_guest_Info = new FloorEditor(); 
           
        try{
            Floor_guest_Info.data1 = new ArrayList<Guest_FloorTableInfo>();
            Floor_guest_Info.data1 = new StaticSentence(app.getSession(), " SELECT  ID , NAME , IMAGE , ICON1 , ICON2 , ICON3 , ICON4 FROM create_guestroom_floor  ", SerializerWriteString.INSTANCE, new SerializerReadClass(FloorEditor.Guest_FloorTableInfo.class)).list();
          
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
          return Floor_guest_Info;
     }
    
    
    
    
    public List<FloorTableInfo> getFloorInfo()
     {
         return data;
     }
   
    public List<Guest_FloorTableInfo> getGuest_FloorInfo()
     {
         return data1;
     }
   
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hall_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        m_jImage = new com.openbravo.data.gui.JImageEditor();
        FloorComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        Edit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        SaveAs = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        hall_ic2 = new javax.swing.JButton();
        hall_ic3 = new javax.swing.JButton();
        hall_ic4 = new javax.swing.JButton();
        hall_ic1 = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        guest_panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        m_jName1 = new javax.swing.JTextField();
        m_jImage1 = new com.openbravo.data.gui.JImageEditor();
        FloorComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        Edit_guest = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        save_guest = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        SaveAs_guest = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cancel_guest = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        room_ic2 = new javax.swing.JButton();
        room_ic3 = new javax.swing.JButton();
        room_ic4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        room_ic1 = new javax.swing.JButton();

        hall_panel.setName("hall_panel"); // NOI18N

        jLabel1.setText("Floor Name ");
        jLabel1.setName("jLabel1"); // NOI18N

        m_jName.setName("m_jName"); // NOI18N

        m_jImage.setName("m_jImage"); // NOI18N

        FloorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        FloorComboBox.setName("FloorComboBox"); // NOI18N
        FloorComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FloorComboBoxMouseClicked(evt);
            }
        });
        FloorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FloorComboBoxItemStateChanged(evt);
            }
        });
        FloorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FloorComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Available Foors ");
        jLabel2.setName("jLabel2"); // NOI18N

        Edit.setText("Edit");
        Edit.setName("Edit"); // NOI18N
        Edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        jPanel2.setName("jPanel2"); // NOI18N

        save.setText("Save");
        save.setName("save"); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setName("jPanel3"); // NOI18N

        SaveAs.setText("Save Changes");
        SaveAs.setName("SaveAs"); // NOI18N
        SaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        SaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SaveAs, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(SaveAs, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setText("jLabel3");
        jLabel3.setName("jLabel3"); // NOI18N

        jButton3.setText("Cancel");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel6.setName("jPanel6"); // NOI18N

        jLabel8.setText("Click here to select Icons");
        jLabel8.setName("jLabel8"); // NOI18N

        hall_ic2.setMaximumSize(new java.awt.Dimension(44, 48));
        hall_ic2.setMinimumSize(new java.awt.Dimension(44, 48));
        hall_ic2.setName("hall_ic2"); // NOI18N
        hall_ic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic2ActionPerformed(evt);
            }
        });

        hall_ic3.setName("hall_ic3"); // NOI18N
        hall_ic3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic3ActionPerformed(evt);
            }
        });

        hall_ic4.setName("hall_ic4"); // NOI18N
        hall_ic4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic4ActionPerformed(evt);
            }
        });

        hall_ic1.setName("hall_ic1"); // NOI18N
        hall_ic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hall_ic1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hall_ic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hall_ic3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hall_ic4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(hall_ic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hall_ic1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hall_ic2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hall_ic4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hall_ic3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        hall_ic2.setSize(44, 48);
        hall_ic1.setSize(44, 48);

        javax.swing.GroupLayout hall_panelLayout = new javax.swing.GroupLayout(hall_panel);
        hall_panel.setLayout(hall_panelLayout);
        hall_panelLayout.setHorizontalGroup(
            hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hall_panelLayout.createSequentialGroup()
                .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hall_panelLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hall_panelLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hall_panelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44)
                                .addComponent(FloorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(m_jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        hall_panelLayout.setVerticalGroup(
            hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hall_panelLayout.createSequentialGroup()
                .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(FloorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(m_jImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, hall_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(hall_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(hall_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))))
        );

        jLabel3.setVisible(false);

        main.setName("main"); // NOI18N

        jButton1.setText("Hall");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guest Room  ");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jButton2.setVisible(false);

        guest_panel.setName("guest_panel"); // NOI18N

        jLabel4.setText("Floor Name ");
        jLabel4.setName("jLabel4"); // NOI18N

        m_jName1.setName("m_jName1"); // NOI18N

        m_jImage1.setName("m_jImage1"); // NOI18N

        FloorComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        FloorComboBox1.setName("FloorComboBox1"); // NOI18N
        FloorComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FloorComboBox1MouseClicked(evt);
            }
        });
        FloorComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                FloorComboBox1ItemStateChanged(evt);
            }
        });
        FloorComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FloorComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Available Foors ");
        jLabel5.setName("jLabel5"); // NOI18N

        Edit_guest.setText("Edit");
        Edit_guest.setName("Edit_guest"); // NOI18N
        Edit_guest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/edit.png"))); // NOI18N
        Edit_guest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_guestActionPerformed(evt);
            }
        });

        jPanel4.setName("jPanel4"); // NOI18N

        save_guest.setText("Save");
        save_guest.setName("save_guest"); // NOI18N
        save_guest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        save_guest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_guestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(save_guest, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(save_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setName("jPanel5"); // NOI18N

        SaveAs_guest.setText("Save Changes");
        SaveAs_guest.setName("SaveAs_guest"); // NOI18N
        SaveAs_guest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/filesave.png"))); // NOI18N
        SaveAs_guest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAs_guestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SaveAs_guest, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(SaveAs_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel6.setText("jLabel3");
        jLabel6.setName("jLabel6"); // NOI18N

        cancel_guest.setText("Cancel");
        cancel_guest.setName("cancel_guest"); // NOI18N
        cancel_guest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_cancel.png"))); // NOI18N
        cancel_guest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_guestActionPerformed(evt);
            }
        });

        jLabel7.setText("Create Floor for Guest Room ");
        jLabel7.setName("jLabel7"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel9.setText("Click to select Icons");
        jLabel9.setName("jLabel9"); // NOI18N

        room_ic2.setName("room_ic2"); // NOI18N
        room_ic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_ic2ActionPerformed(evt);
            }
        });

        room_ic3.setName("room_ic3"); // NOI18N
        room_ic3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_ic3ActionPerformed(evt);
            }
        });

        room_ic4.setName("room_ic4"); // NOI18N
        room_ic4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_ic4ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        room_ic1.setName("room_ic1"); // NOI18N
        room_ic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                room_ic1ActionPerformed(evt);
            }
        });
        jScrollPane1.setViewportView(room_ic1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(room_ic3, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(room_ic4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(room_ic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(room_ic2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(room_ic4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(room_ic3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout guest_panelLayout = new javax.swing.GroupLayout(guest_panel);
        guest_panel.setLayout(guest_panelLayout);
        guest_panelLayout.setHorizontalGroup(
            guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guest_panelLayout.createSequentialGroup()
                .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(guest_panelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(guest_panelLayout.createSequentialGroup()
                                .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m_jName1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(FloorComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(m_jImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cancel_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Edit_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        guest_panelLayout.setVerticalGroup(
            guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guest_panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(m_jName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FloorComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(m_jImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(guest_panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)))
                .addGap(45, 45, 45)
                .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(guest_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancel_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Edit_guest, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel6.setVisible(false);
        jLabel7.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guest_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hall_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guest_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hall_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

   String Name = null; 
   BufferedImage Image = null; 
   FileInputStream   fis = null;
  BufferedImage Image_temp = null; 
   
   // for hall floor save operation
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
      if(m_jName.getText()!=null){
          if(m_jImage.getImage()!=null){
              
              Name = m_jName.getText();
              Image =  m_jImage.getImage();
              final File img = new File(m_jImage.filePath.getAbsolutePath());
             
              
              try {
                     fis = new FileInputStream(img);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               Transaction t = new Transaction(m_App.getSession()) {
                                        private Component MainButtons;
                            
                                        @Override
                                        protected Object transact() throws BasicException {


                                        int num =  new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO create_hall_floor(ID , NAME , IMAGE , CRBY , CRDATE, CRHOST , ICON1 , ICON2 , ICON3 , ICON4 ) VALUES (?,?,?,?,?,?,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.OBJECT ,Datas.STRING ,Datas.TIMESTAMP, Datas.STRING , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE })
                                        ).exec(new Object[]{UUID.randomUUID().toString(), Name ,fis ,m_App.getAppUserView().getUser().getName(),  new Date(), m_App.getProperties().getHost() , hall_icon1 , hall_icon2 , hall_icon3 , hall_icon4 });
                                        
                                        
                                        if(num !=1)
                                             {
                                                JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                             }



                                        return null;
                                        }
                                   };
                try {
                    t.execute();
                    
                     JOptionPane.showMessageDialog(this, " Data Saved Successfully..!! ", "Data Saved..!! ", JOptionPane.INFORMATION_MESSAGE);
                      loaddata();
                     reset();
                } catch (BasicException ex) {
                    ex.printStackTrace();
                    new MessageInf(ex).show(this);
                    
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                    
                    System.out.println(ex);
                }
                                      
             
              
              
          }
          else {
               JOptionPane.showMessageDialog(this, "Please Select Layout   ", " Floor Layout ", JOptionPane.ERROR_MESSAGE);
          }
          
      }
      else{
          JOptionPane.showMessageDialog(this, "Please Enter Floor Name  ", " Floor Name", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_saveActionPerformed

    
    
    
    BufferedImage bufferedImage = null;
    private void FloorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FloorComboBoxActionPerformed
      
   
    }//GEN-LAST:event_FloorComboBoxActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
       m_jName.setEditable(true);
       jPanel2.setVisible(false);
       jPanel3.setVisible(true);
    }//GEN-LAST:event_EditActionPerformed

    private void FloorComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FloorComboBoxMouseClicked
     if(FloorComboBox.getSelectedIndex()!=-1){
         Edit.setEnabled(true);
     }
    }//GEN-LAST:event_FloorComboBoxMouseClicked

    private void SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsActionPerformed
      if(m_jName.getText()!=null){
        if(m_jImage.getImage()!=null){
            Name = m_jName.getText();
            Image =  m_jImage.getImage();
            final File img = new File(m_jImage.filePath.getAbsolutePath());
             
              
              try {
                     fis = new FileInputStream(img);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
            
            String id = jLabel3.getText();
            try{
           
            
              
                
               int num =  new PreparedSentence(m_App.getSession(), "UPDATE create_hall_floor SET NAME=?, IMAGE=? , CRBY=? , CRDATE=? , CRHOST=? , ICON1=?  , ICON2=? , ICON3=? , ICON4=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.OBJECT, Datas.STRING ,Datas.TIMESTAMP , Datas.STRING , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE ,  Datas.STRING })).exec(new Object[]{Name , fis , m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(), hall_icon1 , hall_icon2 , hall_icon3 , hall_icon4  ,id});
               
               loaddata();
               JOptionPane.showMessageDialog(this, " Changes Saved Successfully..!  ", " Saved Changes ", JOptionPane.INFORMATION_MESSAGE);
               reset();
               
               
               
            }
            catch(BasicException ex){
                    ex.printStackTrace();
                    new MessageInf(ex).show(this);
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                   
                    
            }
            
            
        }  
        else{
            
            JOptionPane.showMessageDialog(this, "Please Select Layout ", " Floor Image", JOptionPane.ERROR_MESSAGE);
        }
          
      }
      else{
          JOptionPane.showMessageDialog(this, "Please Enter Floor Name  ", " Floor Name", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_SaveAsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
        hall_panel.setVisible(true);
         main.setVisible(false);
         jPanel3.setVisible(false);
         jPanel2.setVisible(true);
         reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       main.setVisible(true);
       hall_panel.setVisible(false);
      FloorComboBox.setSelectedIndex(-1);
       
      FloorComboBox1.setSelectedIndex(-1);
      reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void FloorComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FloorComboBox1MouseClicked
        
    }//GEN-LAST:event_FloorComboBox1MouseClicked

    private void FloorComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FloorComboBox1ActionPerformed
      
    }//GEN-LAST:event_FloorComboBox1ActionPerformed

    private void Edit_guestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_guestActionPerformed
        m_jName1.setEditable(true);
       jPanel4.setVisible(false);
       jPanel5.setVisible(true);
       
    }//GEN-LAST:event_Edit_guestActionPerformed

    
    
    // guest floor save action 
    
    private void save_guestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_guestActionPerformed
       
        if(m_jName1.getText()!=null && m_jName1.getText().trim().length()>0  ){
          if(m_jImage1.getImage()!=null){
              
              Name = m_jName1.getText();
              Image =  m_jImage1.getImage();
              final File img = new File(m_jImage1.filePath.getAbsolutePath());
             
              
              try {
                     fis = new FileInputStream(img);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               Transaction t = new Transaction(m_App.getSession()) {
                                        private Component MainButtons;
                            
                                        @Override
                                        protected Object transact() throws BasicException {


                                        int num =  new PreparedSentence(m_App.getSession()
                                        , "INSERT INTO create_guestroom_floor(ID , NAME , IMAGE , CRBY , CRDATE, CRHOST , ICON1 , ICON2 , ICON3 , ICON4) VALUES (?,?,?,?,?,? ,?,?,?,?)"
                                        , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING ,Datas.OBJECT ,Datas.STRING ,Datas.TIMESTAMP, Datas.STRING , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE , Datas.IMAGE})
                                        ).exec(new Object[]{UUID.randomUUID().toString(), Name ,fis ,m_App.getAppUserView().getUser().getName(),  new Date(), m_App.getProperties().getHost() , room_icon1 , room_icon2 , room_icon3 , room_icon4});
                                        
                                        
                                        if(num !=1)
                                             {
                                                JOptionPane.showMessageDialog(MainButtons, "Error in saving generated details to database."); 
                                             }



                                        return null;
                                        }
                                   };
                try {
                    t.execute();
                    
                     JOptionPane.showMessageDialog(this, " Data Saved Successfully..!! ", "Data Saved..!! ", JOptionPane.INFORMATION_MESSAGE);
                     loaddata2();
                     reset();
                } catch (BasicException ex) {
                    JOptionPane.showMessageDialog(this, " Image Size Should Be Maximum 64 kb..!  ", " Max Size ", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                                      
             
              
              
          }
          else {
               JOptionPane.showMessageDialog(this, "Please Select Layout   ", " Floor Layout ", JOptionPane.ERROR_MESSAGE);
          }
          
      }
      else{
          JOptionPane.showMessageDialog(this, "Please Enter Floor Name  ", " Floor Name", JOptionPane.ERROR_MESSAGE);
      }
    
        
    }//GEN-LAST:event_save_guestActionPerformed

    private void SaveAs_guestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAs_guestActionPerformed
        if(m_jName1.getText()!=null && m_jName1.getText().trim().length()>0  ){
        if(m_jImage1.getImage()!=null){
            Name = m_jName1.getText();
            Image =  m_jImage1.getImage();
            String id = jLabel6.getText();
            try{
           
               
               int num =  new PreparedSentence(m_App.getSession(), "UPDATE create_guestroom_floor SET NAME=?, IMAGE=? , CRBY=? , CRDATE=? , CRHOST=?  WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.IMAGE, Datas.STRING ,Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec(new Object[]{Name , Image , m_App.getAppUserView().getUser().getName(), new Date(), m_App.getProperties().getHost(),id});
               
               loaddata2();
               JOptionPane.showMessageDialog(this, " Changes Saved Successfully..!  ", " Saved Changes ", JOptionPane.INFORMATION_MESSAGE);
               reset();
               
               
               
            }
            catch(BasicException ex){
           
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Please Select Image To medium Size ", " Floor Image", JOptionPane.ERROR_MESSAGE);
                    
            }
            
            
        }  
        else{
            
            JOptionPane.showMessageDialog(this, "Please Select Layout ", " Floor Image", JOptionPane.ERROR_MESSAGE);
        }
          
      }
      else{
          JOptionPane.showMessageDialog(this, "Please Enter Floor Name  ", " Floor Name", JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_SaveAs_guestActionPerformed

    private void cancel_guestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_guestActionPerformed
        reset();
        FloorComboBox1.setSelectedIndex(-1);
        guest_panel.setVisible(false);
        main.setVisible(true);
    }//GEN-LAST:event_cancel_guestActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       main.setVisible(false);
       guest_panel.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void room_ic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_ic1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                   room_icon1 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(room_icon1);
                   
                   room_ic1.setIcon(ic);
                  
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_room_ic1ActionPerformed

    private void room_ic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_ic2ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                   room_icon2 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(room_icon2);
                   room_ic2.setIcon(ic);
                  
                   
                   
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_room_ic2ActionPerformed

    private void room_ic3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_ic3ActionPerformed
      JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                    room_icon3 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(room_icon3);
                   room_ic3.setIcon(ic);
                  
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_room_ic3ActionPerformed

    private void room_ic4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_room_ic4ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                room_icon4 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(room_icon4);
                   room_ic4.setIcon(ic);
                 
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_room_ic4ActionPerformed

    private void hall_ic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic2ActionPerformed
      JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                    hall_icon2 = ImageIO.read(sourceFile);
                    
                   ImageIcon ic = new ImageIcon(hall_icon2);
                   if(ic.getIconHeight()<45 && ic.getIconWidth()<45){
                        hall_ic2.setIcon(ic);
                       
                       
                   }
                   else{
                     JOptionPane.showMessageDialog(this, "Please Choose Image having dimension size 45x45.  ", "Error ", JOptionPane.ERROR_MESSAGE);      
                   }
                   
                
                   
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_hall_ic2ActionPerformed

    private void hall_ic3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic3ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                    hall_icon3 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(hall_icon3);
                   if(ic.getIconHeight()<45 && ic.getIconWidth()<45){
                     hall_ic3.setIcon(ic);
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Please Choose Image having dimension size 45x45.  ", "Error ", JOptionPane.ERROR_MESSAGE);   
                   }
                  
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_hall_ic3ActionPerformed

    private void hall_ic4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic4ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
             File sourceFile = fileChooser.getSelectedFile();
               try{
                   
                   hall_icon4 = ImageIO.read(sourceFile);
                   ImageIcon ic = new ImageIcon(hall_icon4);
                    
                    if(ic.getIconHeight()<45 && ic.getIconWidth()<45){
                    hall_ic4.setIcon(ic);
                    }
                    else{
                       JOptionPane.showMessageDialog(this, "Please Choose Image having dimension size 45x45.  ", "Error ", JOptionPane.ERROR_MESSAGE);     
                    }
              }
               catch(IOException ex){
                    Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        }
    }//GEN-LAST:event_hall_ic4ActionPerformed

    private void FloorComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FloorComboBox1ItemStateChanged
        if(FloorComboBox1.getSelectedIndex()!=-1){
            reset();
            Edit_guest.setEnabled(true);
           
            m_jName1.setText(null);
            jLabel6.setText(null);
            G_fti = (Guest_FloorTableInfo) FloorComboBox1.getSelectedItem(); 
            if(G_fti!=null){
                
               
                m_jName1.setText(G_fti.getName().toString());
                m_jImage1.setImage(G_fti.getImg());
                m_jName1.setEditable(false);
                jLabel6.setText(G_fti.getId().toString());
                jPanel4.setVisible(false);
                
            }
            
            
        }
        else{
             jPanel4.setVisible(true);
             jPanel5.setVisible(false);
             
             
             
        }
    }//GEN-LAST:event_FloorComboBox1ItemStateChanged

    private void FloorComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_FloorComboBoxItemStateChanged
        if(FloorComboBox.getSelectedIndex()!=-1){
            reset();
            Edit.setEnabled(true);
           
            m_jName.setText(null);
            jLabel3.setText(null);
            fti = (FloorTableInfo) FloorComboBox.getSelectedItem(); 
            if(fti!=null){
                
               
                m_jName.setText(fti.getName().toString());
                m_jImage.setImage(fti.getImg());
                m_jName.setEditable(false);
                jLabel3.setText(fti.getId().toString());
                jPanel2.setVisible(false);
                
                
                // for four icons 
                if(fti.getIcon1()!=null){
                   hall_icon1 = fti.getIcon1();
                   ImageIcon ic = new ImageIcon(hall_icon1);
                   hall_ic1.setIcon(ic);
                 }
                else{
                    ImageIcon ic = new ImageIcon();
                    hall_ic1.setIcon(ic);
                }
                
                if(fti.getIcon2()!=null){
                     hall_icon2 = fti.getIcon2();
                 ImageIcon ic2 = new ImageIcon(hall_icon2);
                 hall_ic2.setIcon(ic2);
                }
                else{
                     ImageIcon ic2 = new ImageIcon();
                 hall_ic2.setIcon(ic2);
                }
                
                
               if(fti.getIcon3()!=null)
               {
                  hall_icon3 = fti.getIcon3();
                 ImageIcon ic3 = new ImageIcon(hall_icon3);
                 hall_ic3.setIcon(ic3);  
               } 
               else{
                   ImageIcon ic3 = new ImageIcon();
                 hall_ic3.setIcon(ic3);   
               }
               
               
                if(fti.getIcon4()!=null){
                  hall_icon4 = fti.getIcon4();
                  ImageIcon ic4 = new ImageIcon(hall_icon4);
                  hall_ic4.setIcon(ic4);
                }
                else{
                    
                   ImageIcon ic4 = new ImageIcon();
                  hall_ic4.setIcon(ic4); 
                }
                
                
                 
                
                
            }
            
            
        }
        else{
            jPanel2.setVisible(true);
            jPanel3.setVisible(false);
        }
   
    }//GEN-LAST:event_FloorComboBoxItemStateChanged

    private void hall_ic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hall_ic1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File sourceFile = fileChooser.getSelectedFile();
            try{

                hall_icon1 = ImageIO.read(sourceFile);
                ImageIcon ic = new ImageIcon(hall_icon1);
                if(ic.getIconHeight()<45 && ic.getIconWidth()<45){
                     hall_ic1.setIcon(ic);
                }
                else{
                   JOptionPane.showMessageDialog(this, "Please Choose Image having dimension size 45x45.  ", "Error ", JOptionPane.ERROR_MESSAGE);    
                }
                
               

            }
            catch(IOException ex){
                Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_hall_ic1ActionPerformed

    BufferedImage room_icon1 ;
    BufferedImage room_icon2 ;
    BufferedImage room_icon3 ;
    BufferedImage room_icon4 ;
    
    BufferedImage hall_icon1 ;
    BufferedImage hall_icon2 ;
    BufferedImage hall_icon3 ;
    BufferedImage hall_icon4 ;
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit;
    private javax.swing.JButton Edit_guest;
    private javax.swing.JComboBox FloorComboBox;
    private javax.swing.JComboBox FloorComboBox1;
    private javax.swing.JButton SaveAs;
    private javax.swing.JButton SaveAs_guest;
    private javax.swing.JButton cancel_guest;
    private javax.swing.JPanel guest_panel;
    private javax.swing.JButton hall_ic1;
    private javax.swing.JButton hall_ic2;
    private javax.swing.JButton hall_ic3;
    private javax.swing.JButton hall_ic4;
    private javax.swing.JPanel hall_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private com.openbravo.data.gui.JImageEditor m_jImage;
    private com.openbravo.data.gui.JImageEditor m_jImage1;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jName1;
    private javax.swing.JPanel main;
    private javax.swing.JButton room_ic1;
    private javax.swing.JButton room_ic2;
    private javax.swing.JButton room_ic3;
    private javax.swing.JButton room_ic4;
    private javax.swing.JButton save;
    private javax.swing.JButton save_guest;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Floor Master";
    }

    
    public void loaddata() throws BasicException
    {
        try {
            FE = FloorEditor.loadInstanceFloorInfo(m_App);
            
        } catch (BasicException ex) {
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = (List<FloorTableInfo>) FE.getFloorInfo();
        if(data==null){
             data = new ArrayList<FloorTableInfo>();
        }
         
     FloorListModel   = new ComboBoxValModel(data);
     FloorComboBox.setModel(FloorListModel);
     //FloorComboBox.setSelectedIndex(-1);
       
    }
    public void loaddata2() throws BasicException
    {
        try {
            
            GF = FloorEditor.loadInstanceGuest_FloorInfo(m_App);
        } catch (BasicException ex) {
            Logger.getLogger(FloorEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        data1 = (List<Guest_FloorTableInfo>) GF.getGuest_FloorInfo();
        if(data1==null){
             data1 = new ArrayList<Guest_FloorTableInfo>();
        }
         
     GuestFloorModel   = new ComboBoxValModel(data1);
     FloorComboBox1.setModel(GuestFloorModel);
     //FloorComboBox.setSelectedIndex(-1);
       
    }
    public void activate() throws BasicException {
       reset();
         main.setVisible(true);
         hall_panel.setVisible(false);
         FloorComboBox.setSelectedIndex(-1);
         FloorComboBox1.setSelectedIndex(-1);
       
       main.setVisible(true);
        hall_panel.setVisible(false);
        guest_panel.setVisible(false);
       if(FloorComboBox.getSelectedIndex()==-1){
           Edit.setEnabled(false);
       }
         loaddata();
        loaddata2();
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
            return this;
    }

    public void init(AppView app) throws BeanFactoryException {
        this.m_App = app;
    }

    public Object getBean() {
         return this;
    }

    public void reset()
    {
        m_jName.setText(null);
        m_jName1.setText(null);
        
        Image = null;
        m_jImage.setImage(null);
        m_jImage1.setImage(null);
        Edit.setEnabled(false);
        Edit_guest.setEnabled(false);
        
        jPanel2.setVisible(true);
        jPanel3.setVisible(false);
        jLabel3.setText(null);
        
      
        jPanel4.setVisible(true);
        jPanel5.setVisible(false);
        jLabel6.setText(null);
        
        m_jName.setEditable(true);
        
        
         ImageIcon ic = new ImageIcon();
         hall_ic1.setIcon(ic);
         ImageIcon ic2 = new ImageIcon();
         hall_ic2.setIcon(ic);
         ImageIcon ic3 = new ImageIcon();
         hall_ic3.setIcon(ic);
         ImageIcon ic4 = new ImageIcon();
         hall_ic4.setIcon(ic);
         
         
    }
   
     
   public static class FloorTableInfo implements SerializableRead,IKeyed{

        public FloorTableInfo() {
            
        }
        
         public  String Id;
         public  String FloorName;
      
         private BufferedImage FloorImg;
         private BufferedImage hall_icn1 ; 
          private BufferedImage hall_icn2 ; 
           private BufferedImage hall_icn3 ; 
            private BufferedImage hall_icn4 ; 
         
            
          public String getId(){
              return Id;
          }
          public void setId(String Id){
              this.Id=Id;
          }
        
            public String getName(){
               return FloorName;
           }
           public void setName(String FloorName){
               this.FloorName=FloorName;
           }          
          
          public BufferedImage getImg(){
               return FloorImg;
           }
           public void setImg(BufferedImage FloorImg){
               this.FloorImg=FloorImg;
           }  
           public BufferedImage getIcon1(){
               return hall_icn1;
           }
           public void setIcon1(BufferedImage hall_icn1){
               this.hall_icn1=hall_icn1;
           }  
           public BufferedImage getIcon2(){
               return hall_icn2;
           }
           public void setIcon2(BufferedImage hall_icn2){
               this.hall_icn2=hall_icn2;
           }
           public BufferedImage getIcon3(){
               return hall_icn3;
           }
           public void setIcon3(BufferedImage hall_icn3){
               this.hall_icn3=hall_icn3;
           }
           public BufferedImage getIcon4(){
               return hall_icn4;
           }
           public void setIcon4(BufferedImage hall_icn4){
               this.hall_icn4=hall_icn4;
           }
           
           public String toString()
           {
               return FloorName;
           }
           
           
        public void readValues(DataRead dr) throws BasicException {
           Id = dr.getString(1);
            FloorName = dr.getString(2);
           FloorImg = ImageUtils.readImage(dr.getBytes(3));
            hall_icn1 =  ImageUtils.readImage(dr.getBytes(4));
            hall_icn2 =  ImageUtils.readImage(dr.getBytes(5));
            hall_icn3 =  ImageUtils.readImage(dr.getBytes(6));
            hall_icn4 =  ImageUtils.readImage(dr.getBytes(7));
        }             

        public Object getKey() {
            return this;
        }
    }


    public static class Guest_FloorTableInfo implements SerializableRead,IKeyed{

       
        public Guest_FloorTableInfo(){
            
        }
        
        public  String Id;
        public  String FloorName;
      
         private BufferedImage FloorImg;
         private BufferedImage room_icn1;
         private BufferedImage room_icn2;
         private BufferedImage room_icn3;
         private BufferedImage room_icn4;
         
         public String getId(){
              return Id;
          }
          public void setId(){
              this.Id=Id;
          }
        
            public String getName(){
               return FloorName;
           }
           public void setName(){
               this.FloorName=FloorName;
           }          
          
          public BufferedImage getImg(){
               return FloorImg;
           }
           public void setImg(){
               this.FloorImg=FloorImg;
           }  
            public BufferedImage getIcon1(){
               return room_icn1;
           }
           public void setIcon1(BufferedImage room_icn1){
               this.room_icn1=room_icn1;
           }  
           public BufferedImage getIcon2(){
               return room_icn2;
           }
           public void setIcon2(BufferedImage room_icn2){
               this.room_icn2=room_icn2;
           }
           public BufferedImage getIcon3(){
               return room_icn3;
           }
           public void setIcon3(BufferedImage room_icn3){
               this.room_icn3=room_icn3;
           }
           public BufferedImage getIcon4(){
               return room_icn4;
           }
           public void setIcon4(BufferedImage hall_icn4){
               this.room_icn4=room_icn4;
           }
           public String toString()
           {
               return FloorName;
           }
        
        
        
        
        
        
        public void readValues(DataRead dr) throws BasicException {
            Id = dr.getString(1);
            FloorName = dr.getString(2);
           FloorImg = ImageUtils.readImage(dr.getBytes(3));
           room_icn1 = ImageUtils.readImage(dr.getBytes(4));
           room_icn2 = ImageUtils.readImage(dr.getBytes(5));
           room_icn3 = ImageUtils.readImage(dr.getBytes(6));
           room_icn4 = ImageUtils.readImage(dr.getBytes(7));
        }

        
        public Object getKey() {
            return this;
        }
    
        
        
        
    }
   

    

   
}

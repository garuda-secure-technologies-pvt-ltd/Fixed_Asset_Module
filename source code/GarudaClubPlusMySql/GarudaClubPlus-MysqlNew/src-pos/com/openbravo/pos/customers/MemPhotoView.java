
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MemPhotoView.java
 *
 * Created on 19-Feb-2014, 10:23:48
 */
package com.openbravo.pos.customers;


import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.swingx.editors.ImageEditor;

/**
 * @author swathi
 */
public class MemPhotoView extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {

    AppView m_App;
    ImageEditor panel;
    JFileChooser filechooser1;
    File file;
    BufferedImage bi, bufferImage,memimage,dep1image,dep2image,dep3image,dep4image,dep5image;
    BufferedImage memsignim,dep1signim,dep2signim,dep3signim,dep4signim,dep5signim;
    String fname,path;
    String depName;
    String depMemno;
    int i;
    String pathm=null,pathd1=null,pathd2=null,pathd3=null,pathd4=null,pathd5=null;
    String pathmsign=null,pathd1sign=null,pathd2sign=null,pathd3sign=null,pathd4sign=null,pathd5sign=null;
    JButton button;
    String custId, dep1Id, dep2Id, dep3Id, dep4Id,dep5Id;
    String spouse=null;
    DefaultListModel model=new DefaultListModel();
    String selItem;
    String urlPath=null;
    List<String> MemPhotoEditList = new ArrayList<String>();
    Boolean PermissionFlag=false;
    
    
    
    public MemPhotoView() {
        initComponents();
        
  }
    Boolean Flag=true;
    private void load(){
         Vector ldata=getMemName();
        // DefaultListModel model=new DefaultListModel();
        jList1.setModel(model);
        jList1.setListData(ldata);
        jList1.addListSelectionListener(lsl);
        MemPhotoEditList = new ArrayList<String>(); 
        PermissionFlag = getPermissionToSavePhoto( m_App.getAppUserView().getUser().getRole());
        
        loadPernissionToChangePhoto();
    }
        
    ListSelectionListener lsl = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
                 
  	    if (!jList1.getValueIsAdjusting()) {
                   //jTextField1.setEditable(false);
                    selItem=jList1.getSelectedValue().toString();
                    loaddata();
                  
                 }else{
                         reset();
                      }
             }
         };
    
    
    private void setPhotoImageToLabel(){
        path=jTextField1.getText();
        try {
            
            if(pathm!=null)         
                imageLabel.setIcon(new ImageIcon(ImageIO.read(new File(pathm))));
            } catch (IOException ex) {
             ex.printStackTrace();
        }
        try {
            
            if(pathd2!=null) 
                labeldep2.setIcon(new ImageIcon(ImageIO.read(new File(pathd2))));
            } catch (IOException ex) {
            ex.printStackTrace();
                
        }
        try {
            
            if(pathd3!=null) 
                labeldep3.setIcon(new ImageIcon(ImageIO.read(new File(pathd3))));
                } catch (IOException ex) {
             ex.printStackTrace();
                     
        }
        try {
            if(pathd4!=null) 
                labeldep4.setIcon(new ImageIcon(ImageIO.read(new File(pathd4))));
        } catch (IOException ex) {
             ex.printStackTrace();
            
        }
        try {
            if(pathd5!=null) 
                labeldep5.setIcon(new ImageIcon(ImageIO.read(new File(pathd5))));
             

        } catch (IOException ex) {
             ex.printStackTrace();
             
        }
        try {
            if(pathd1!=null) 
                labeldep1.setIcon(new ImageIcon(ImageIO.read(new File(pathd1))));
        } catch (IOException ex) {
             ex.printStackTrace();
            
        }
    }
    
    private void setSignImageToLabel(){
       
        path=jTextField1.getText();
        try {
           
            
            if(pathmsign!=null) {        
                    memsignlb.setIcon(new ImageIcon(ImageIO.read(new File(pathmsign))));
            }} catch (IOException ex) {
             ex.printStackTrace();
        }
               
          try {
                if(pathd2sign!=null) {
                    dep2signlb.setIcon(new ImageIcon(ImageIO.read(new File(pathd2sign))));}
                } catch (IOException ex) {
            ex.printStackTrace();
        }
          try {
                if(pathd3sign!=null) {
                    dep3signlb.setIcon(new ImageIcon(ImageIO.read(new File(pathd3sign))));}
                } catch (IOException ex) {
            ex.printStackTrace();
        }
          try {
                if(pathd4sign!=null) {
                    dep4signlb.setIcon(new ImageIcon(ImageIO.read(new File(pathd4sign))));}
                } catch (IOException ex) {
             ex.printStackTrace();
        }
          try {
                if(pathd5sign!=null) {
                    
                    dep5signlb.setIcon(new ImageIcon(ImageIO.read(new File(pathd5sign))));}
                } catch (IOException ex) {
             ex.printStackTrace();
        }
           try {
                 if(pathd1sign!=null) {
                    dep1signlb.setIcon(new ImageIcon(ImageIO.read(new File(pathd1sign))));
                }
        } catch (IOException ex) {
             ex.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        labeldep1 = new javax.swing.JLabel();
        labeldep2 = new javax.swing.JLabel();
        labeldep3 = new javax.swing.JLabel();
        labeldep4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        memsignlb = new javax.swing.JLabel();
        dep1signlb = new javax.swing.JLabel();
        dep4signlb = new javax.swing.JLabel();
        dep2signlb = new javax.swing.JLabel();
        dep3signlb = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labeldep5 = new javax.swing.JLabel();
        dep5signlb = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        memName_label = new javax.swing.JLabel();
        Spouse_name_Lable = new javax.swing.JLabel();
        dependent1_label = new javax.swing.JLabel();
        dependent2_label = new javax.swing.JLabel();
        dependent3_label = new javax.swing.JLabel();
        dependent4_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        save_button = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jLabel1.setForeground(new java.awt.Color(204, 1, 1));
        jLabel1.setText("Member  no. :- ");
        jLabel1.setName("jLabel1"); // NOI18N

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Member Details"));
        jPanel1.setMaximumSize(new java.awt.Dimension(100, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 1000));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(125, 16, 16));
        jLabel3.setText("Member");
        jLabel3.setName("jLabel3"); // NOI18N

        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imageLabel.setName("imageLabel"); // NOI18N

        labeldep1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labeldep1.setName("labeldep1"); // NOI18N
        labeldep1.setVisible(false);

        labeldep2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labeldep2.setName("labeldep2"); // NOI18N
        labeldep2.setVisible(false);

        labeldep3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labeldep3.setName("labeldep3"); // NOI18N
        labeldep3.setVisible(false);

        labeldep4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labeldep4.setName("labeldep4"); // NOI18N
        labeldep4.setVisible(false);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setVisible(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVisible(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setName("jButton7"); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton7.setVisible(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setName("jButton8"); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton8.setVisible(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setVisible(false);

        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setVisible(false);

        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setVisible(false);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setVisible(false);

        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setVisible(false);

        jLabel9.setForeground(new java.awt.Color(25, 44, 191));
        jLabel9.setText("Member Spouse");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setVisible(false);

        jLabel10.setText("Name:");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setVisible(false);

        jLabel11.setForeground(new java.awt.Color(25, 63, 194));
        jLabel11.setText("1. Dependent ");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setVisible(false);

        jLabel12.setText("Name:");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setVisible(false);

        jLabel13.setForeground(new java.awt.Color(29, 62, 185));
        jLabel13.setText("2. Dependent");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setVisible(false);

        jLabel14.setText("Name:");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setVisible(false);

        jLabel15.setForeground(new java.awt.Color(8, 28, 204));
        jLabel15.setText("3. Dependent");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setVisible(false);

        jLabel16.setText("Name:");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVisible(false);

        jLabel17.setText("Name:");
        jLabel17.setName("jLabel17"); // NOI18N

        memsignlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        memsignlb.setName("memsignlb"); // NOI18N

        dep1signlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dep1signlb.setName("dep1signlb"); // NOI18N
        dep1signlb.setVisible(false);

        dep4signlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dep4signlb.setName("dep4signlb"); // NOI18N
        dep4signlb.setVisible(false);

        dep2signlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dep2signlb.setName("dep2signlb"); // NOI18N
        dep2signlb.setVisible(false);

        dep3signlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dep3signlb.setName("dep3signlb"); // NOI18N
        dep3signlb.setVisible(false);

        jButton10.setName("jButton10"); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setVisible(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton12.setVisible(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setName("jButton13"); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton13.setVisible(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setName("jButton14"); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton14.setVisible(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel18.setForeground(new java.awt.Color(48, 39, 217));
        jLabel18.setText("4. Dependent");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setVisible(false);

        jLabel19.setText("Name:");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setVisible(false);

        labeldep5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labeldep5.setName("labeldep5"); // NOI18N
        labeldep4.setVisible(false);

        dep5signlb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dep5signlb.setName("dep5signlb"); // NOI18N
        dep4signlb.setVisible(false);

        jButton15.setName("jButton15"); // NOI18N
        jButton15.setVisible(false);
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setName("jButton16"); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileopen.png")));
        jButton16.setVisible(false);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        memName_label.setText("jLabel2");
        memName_label.setName("memName_label"); // NOI18N

        Spouse_name_Lable.setText("jLabel2");
        Spouse_name_Lable.setName("Spouse_name_Lable"); // NOI18N

        dependent1_label.setText("jLabel2");
        dependent1_label.setName("dependent1_label"); // NOI18N

        dependent2_label.setText("jLabel2");
        dependent2_label.setName("dependent2_label"); // NOI18N

        dependent3_label.setText("jLabel2");
        dependent3_label.setName("dependent3_label"); // NOI18N

        dependent4_label.setText("jLabel2");
        dependent4_label.setName("dependent4_label"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(dependent4_label, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(dependent3_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(dependent2_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(memName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(Spouse_name_Lable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(dependent1_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(labeldep1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labeldep2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labeldep3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labeldep4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labeldep5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(jButton7, 0, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(memsignlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(710, 710, 710)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(664, 664, 664)
                                                        .addComponent(jLabel7))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(128, 128, 128)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(104, 104, 104)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(536, 536, 536))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(97, 97, 97)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dep3signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton13, 0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dep2signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(dep1signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton11, 0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dep5signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dep4signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton14, 0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18))
                        .addGap(0, 2184, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16, jButton5, jButton6, jButton7, jButton8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dep1signlb, dep2signlb, dep3signlb, dep4signlb, dep5signlb, memsignlb});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(memName_label))
                            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memsignlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(Spouse_name_Lable))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labeldep1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dep1signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(dependent1_label))
                            .addComponent(labeldep2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dep2signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dep3signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel13)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(dependent2_label)
                                    .addComponent(labeldep3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(283, 283, 283)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)))
                                .addGap(233, 233, 233))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel16)
                                                .addComponent(dependent3_label))
                                            .addComponent(dep4signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel18))
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labeldep4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labeldep5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(dependent4_label))
                                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dep5signlb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {imageLabel, labeldep1, labeldep2, labeldep3, labeldep4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel5, jLabel6, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16, jButton5, jButton6, jButton7, jButton8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dep1signlb, dep2signlb, dep3signlb, dep4signlb, dep5signlb, memsignlb});

        jScrollPane1.setViewportView(jPanel1);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setName("jList1"); // NOI18N
        jList1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        save_button.setText("Save");
        save_button.setName("save_button"); // NOI18N
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        jButton4.setText("Refresh");
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setName("jButton2"); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1leftarrow.png")));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setName("jButton9"); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1rightarrow.png")));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(599, 599, 599)
                                .addComponent(save_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addGap(350, 350, 350))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton9});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, save_button});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save_button)
                            .addComponent(jButton4))
                        .addGap(16, 16, 16))
                    .addComponent(jScrollPane2)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton9});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton4, save_button});

    }// </editor-fold>//GEN-END:initComponents
 
     public void setMemNo(String name){
         
  
        String MemNo=null;
        try {
            MemNo=(String) new StaticSentence(m_App.getSession()
                              ,"SELECT SEARCHKEY FROM CUSTOMERS WHERE NAME=?" ,SerializerWriteString.INSTANCE
                            ,SerializerReadString.INSTANCE).find(name);
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextField1.setText(MemNo);
         
     }
    
    public Vector getMemName(){
        
        Vector data=new Vector();
        List lst=null;
      
        try {
           lst= new StaticSentence(m_App.getSession()
                              ,"SELECT NAME FROM CUSTOMERS" ,null
                            ,SerializerReadString.INSTANCE).list();
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data.addAll(lst);
        return data;
   }
    
    private String getMemName(String memNo){
        String MemName=null;
        try {
            MemName=(String) new StaticSentence(m_App.getSession()
                              ,"SELECT  NAME FROM CUSTOMERS WHERE SEARCHKEY=?" ,SerializerWriteString.INSTANCE
                            ,SerializerReadString.INSTANCE).find(memNo);
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MemName;
    }
    
private BufferedImage getImage(int w,int h){
       
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
       filechooser1 = new javax.swing.JFileChooser();
       filechooser1.setName("filechooser");
       filechooser1.setDragEnabled(true);
       filechooser1.setFileFilter(filter);
       int returnVal = filechooser1.showOpenDialog(null);
       if (returnVal == JFileChooser.APPROVE_OPTION) {
       file = filechooser1.getSelectedFile();
       
       fname=file.getPath();
       System.out.println(fname);
       int index=fname.indexOf(".");
       fname=fname.substring(index+1, fname.length());
       
        if(!fname.matches("[jJ][pP][gG]") && !fname.matches("[pP][nN][gG]") && !fname.matches("[jJ][pP][eE][gG]") && !fname.matches("[gG][iI][fF]") ) {
             JOptionPane.showMessageDialog(null,
                                           "cannot load file. File must be of type png, jpeg, jpg or gif. \n Your file is of type " + fname,
                                            "Error: improper file",
                                            JOptionPane.OK_OPTION);
        }
           //if the file is of the proper type, display it to the user on the img JLabel.
       else{
 
            try {
                    bi = ImageIO.read(filechooser1.getSelectedFile());
                    //w = bi.getWidth(null);
                   // h = bi.getHeight(null);
                    if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                        BufferedImage bi2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                        Graphics big = bi2.getGraphics();
                        big.drawImage(bi, 0, 0, w, h, null);
                        bufferImage = bi = bi2;
                       // imageLabel=new JLabel(image);
                        System.out.println("Image loaded to buffer.");
                      } 
               } catch (IOException ex) {
                ex.printStackTrace();
                }
        
       return bufferImage;
        
     }
    }else {
        // ...
       }
 return null;
}
    
 
private Vector getDependencies(){
        
            Vector depdata=new Vector();
            
            String memNo=jTextField1.getText();
            String MemID=null;
            List list=new ArrayList();
            
        try {
           MemID=(String) new StaticSentence(m_App.getSession()
                    ,"SELECT ID FROM CUSTOMERS WHERE SEARCHKEY=?" ,SerializerWriteString.INSTANCE
                    ,SerializerReadString.INSTANCE).find(memNo);

           list= new StaticSentence(m_App.getSession()
                    ,"SELECT DNAME FROM MEMDEPENDENT WHERE MEMNO=? AND DTYPE<>?" ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                    ,SerializerReadString.INSTANCE).list(new Object[]{MemID,"Spouse"});
           
           spouse=(String) new StaticSentence(m_App.getSession()
                    ,"SELECT DNAME FROM MEMDEPENDENT WHERE MEMNO=? AND DTYPE=?" ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                    ,SerializerReadString.INSTANCE).find(new Object[]{MemID,"Spouse"});
           
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       depdata.addAll(list);
       return depdata;
}
    
    
private void addDependencies(){
        Vector v=getDependencies();
        //System.out.println(v.size());
       
        if(spouse!=null){
                Spouse_name_Lable.setVisible(true);
                jButton5.setVisible(true);
                labeldep1.setVisible(true);
                jLabel9.setVisible(true);
                jLabel10.setVisible(true);
                dep1signlb.setVisible(true);
                jButton11.setVisible(true);
                Spouse_name_Lable.setText(spouse);
        }
        
        
        if(v.isEmpty()){
             System.out.println("No dependencies");
        }else{
//            if(v.size()>0){
//                jTextField3.setVisible(true);
//                jButton5.setVisible(true);
//                labeldep1.setVisible(true);
//                jLabel9.setVisible(true);
//                jLabel10.setVisible(true);
//                dep1signlb.setVisible(true);
//                jButton11.setVisible(true);
//                jTextField3.setText(v.get(0).toString());
                if(v.size()>0){
                    if(v.get(0).toString()!=null){
                    dependent1_label.setVisible(true);
                    jButton6.setVisible(true);
                    labeldep2.setVisible(true);
                    jLabel11.setVisible(true);
                    jLabel12.setVisible(true);
                    dep2signlb.setVisible(true);
                    jButton12.setVisible(true);}
                    dependent1_label.setText(v.get(0).toString());
                    if(v.size()>1){
                        if(v.get(1).toString()!=null){
                        dependent2_label.setVisible(true);
                        jButton7.setVisible(true);
                        labeldep3.setVisible(true);
                        jLabel13.setVisible(true);
                        jLabel14.setVisible(true);
                        dep3signlb.setVisible(true);
                        jButton13.setVisible(true);}
                        dependent2_label.setText(v.get(1).toString());
                        if(v.size()>2 ){
                            if(v.get(2).toString()!=null){
                            dependent3_label.setVisible(true);
                            jButton8.setVisible(true);
                            labeldep4.setVisible(true);
                            jLabel15.setVisible(true);
                            jLabel16.setVisible(true);
                            dep4signlb.setVisible(true);
                            jButton14.setVisible(true);}
                            dependent3_label.setText(v.get(2).toString());
                            if(v.size()>3){
                                if(v.get(3).toString()!=null){
                                dependent4_label.setVisible(true);
                                jButton15.setVisible(true);
                                labeldep5.setVisible(true);
                                jLabel18.setVisible(true);
                                jLabel19.setVisible(true);
                                dep5signlb.setVisible(true);
                                jButton16.setVisible(true);
                                dependent4_label.setText(v.get(3).toString());}
                            }
                        }
                    }   
                }
            
           }
}
    
    
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
       
 
       if(jTextField1.getText()!=null){
            try {
                 custId= (String) new StaticSentence(m_App.getSession()
                           ,"SELECT id FROM CUSTOMERS WHERE SEARCHKEY=?"
                     ,SerializerWriteString.INSTANCE
                     ,SerializerReadString.INSTANCE).find(jTextField1.getText());
                 
                
            } catch (BasicException ex) {
               // Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value custid");
            }
       }
       else{
            System.out.print("memno field is null");
       }
      
       if(Spouse_name_Lable.getText()!=null){
            try {
                dep1Id =(String) new StaticSentence(m_App.getSession()
                          ,"SELECT ID "
                          +"FROM MEMDEPENDENT WHERE MEMNO=? AND DNAME=?"

                          ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                        ,SerializerReadString.INSTANCE).find(new Object[]{custId,Spouse_name_Lable.getText()} );
            } catch (BasicException ex) {
                //Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value dep1id");
            }
       } else{
            System.out.print("dep1 name field is null");
       }
       
       if(dependent1_label.getText()!=null){
            try {
                dep2Id = (String) new StaticSentence(m_App.getSession()
                          ,"SELECT ID "
                          +"FROM MEMDEPENDENT WHERE MEMNO=? AND DNAME=?"

                          ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                        ,SerializerReadString.INSTANCE).find(new Object[]{custId,dependent1_label.getText()});
            } catch (BasicException ex) {
                //Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value dep2id");
            }
       } else{
            System.out.print("dep2 name field is null");
       }
       
       if(dependent2_label.getText()!=null){
            try {
                dep3Id = (String) new StaticSentence(m_App.getSession()
                          ,"SELECT ID "
                          +"FROM MEMDEPENDENT WHERE MEMNO=? AND DNAME=?"

                          ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                        ,SerializerReadString.INSTANCE).find(new Object[]{custId,dependent2_label.getText()} );
            } catch (BasicException ex) {
               // Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value dep3id");
            }
       } else{
            System.out.print("dep3 name field is null");
       }
       
       
       if(dependent3_label.getText()!=null){
            try {
                dep4Id = (String) new StaticSentence(m_App.getSession()
                          ,"SELECT ID "
                          +"FROM MEMDEPENDENT WHERE MEMNO=? AND DNAME=?"

                          ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                        ,SerializerReadString.INSTANCE).find(new Object[]{custId,dependent3_label.getText()} );
            } catch (BasicException ex) {
                //Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value dep4id");
            }
       } else{
            System.out.print("dep4 name field is null");
       }
       
       
            if(dependent4_label.getText()!=null){
            try {
                dep5Id = (String) new StaticSentence(m_App.getSession()
                          ,"SELECT ID "
                          +"FROM MEMDEPENDENT WHERE MEMNO=? AND DNAME=?"

                          ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                        ,SerializerReadString.INSTANCE).find(new Object[]{custId,dependent4_label.getText()} );
            } catch (BasicException ex) {
                //Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("could not fecth value dep4id");
            }
       } else{
            System.out.print("dep4 name field is null");
       }
       
       System.out.println(custId);
       System.out.println(dep1Id);
       System.out.println(dep2Id);
       System.out.println(dep3Id);
       System.out.println(dep4Id);
       System.out.println(dep5Id);
        
      
       String random=UUID.randomUUID().toString();
      // int i=0;;
     
        path=jTextField1.getText().toLowerCase(); 
        pathm=pathd1=pathd2=pathd3=pathd4=null;
        pathmsign=pathd1sign=pathd2sign=pathd3sign=pathd4sign=null;
        
        if(memimage!=null)
            pathm=urlPath+"p"+path+"m.jpg";
        if(dep1image!=null)
            pathd1=urlPath+"p"+path+"s.jpg";
        if(dep2image!=null)
            pathd2=urlPath+"p"+path+"a.jpg";
        if(dep3image!=null)
            pathd3=urlPath+"p"+path+"b.jpg";
        if(dep4image!=null)
            pathd4=urlPath+"p"+path+"c.jpg";
        if(dep5image!=null)
            pathd5=urlPath+"p"+path+"d.jpg";
        
        if(memsignim!=null)
            pathmsign=urlPath+"s"+path+"m.jpg";
        if(dep1signim!=null)
            pathd1sign=urlPath+"s"+path+"s.jpg";
        if(dep2signim!=null)
            pathd2sign=urlPath+"s"+path+"a.jpg";
        if(dep3signim!=null)
            pathd3sign=urlPath+"s"+path+"b.jpg";
        if(dep4signim!=null)
            pathd4sign=urlPath+"s"+path+"c.jpg";
        if(dep5signim!=null)
            pathd5sign=urlPath+"s"+path+"d.jpg";
        
        try {
        
        String userName=LookupUtilityImpl.getInstance(m_App).getAppView().getAppUserView().getUser().getName();
        String memNODB= (String) new StaticSentence(m_App.getSession()
                          ,"SELECT  MEMNO FROM MEMPHOTODETAILS WHERE MEMNO=?" ,SerializerWriteString.INSTANCE
                        ,SerializerReadString.INSTANCE).find(jTextField1.getText());
        
        Object[] values = new Object[] {random,jTextField1.getText(),custId,pathm,dep1Id,pathd1,pathd1sign,dep2Id,pathd2,pathd2sign,dep3Id,pathd3,pathd3sign,dep4Id,pathd4,pathd4sign,dep5Id,pathd5,pathd5sign,userName,new Date(),pathmsign};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING};
        
          
       
           if(memNODB==null) {   
            i=new PreparedSentence(m_App.getSession()
             , "INSERT INTO MEMPHOTODETAILS (ID,MEMNO,MEMID,MLINK,DEP1ID,D1LINK,D1SLINK,DEP2ID,D2LINK,D2SLINK,DEP3ID,D3LINK,D3SLINK,DEP4ID,D4LINK,D4SLINK,DEP5ID,D5LINK,D5SLINK,CREATEDBY,TIME,MSLINK) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
             , new SerializerWriteBasicExt(datas, new int[] {0,1, 2, 3, 4, 5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21})).exec(values);
           }
           else{
               int result = JOptionPane.showConfirmDialog(null, 
                                    "Member Already Exists.. Do you want to overwrite the data?",null, JOptionPane.YES_NO_OPTION);
               
               if(result==JOptionPane.YES_OPTION){
                   
                   Datas[] datas1 = new Datas[] {Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING};
                   i=new PreparedSentence(m_App.getSession()
                , "UPDATE MEMPHOTODETAILS SET MEMID=?,MLINK=?,DEP1ID=?,D1LINK=?,D1SLINK=?,DEP2ID=?,D2LINK=?,D2SLINK=?,DEP3ID=?,D3LINK=?,D3SLINK=?,DEP4ID=?,D4LINK=?,D4SLINK=?,DEP5ID=?,D5LINK=?,D5SLINK=?,CREATEDBY=?,TIME=?,MSLINK=? WHERE MEMNO=?"
                , new SerializerWriteBasicExt(datas1,new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20})).exec(new Object[]{custId,pathm,dep1Id,pathd1,pathd1sign,dep2Id,pathd2,pathd2sign,dep3Id,pathd3,pathd3sign,dep4Id,pathd4,pathd4sign,dep5Id,pathd5,pathd5sign,userName,new Date(),pathmsign,jTextField1.getText()});
                   
               }
               else if(result==JOptionPane.NO_OPTION){
                   i=0;
               }
           }
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
           //JOptionPane.showMessageDialog(null, "Image field is empty ", "Cannot save", JOptionPane.ERROR_MESSAGE);
            
        }
        if(i>0){ 
                try {
                        saveMemPhoto();
                        saveMemSign();
                        JOptionPane.showMessageDialog(this, "Successfully Saved..");
                    } catch (IOException ex) {
                        Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
                      }
              }else{
                    System.out.println("could not add data to db");
                  } 
    
    }//GEN-LAST:event_save_buttonActionPerformed
   
    private void saveMemPhoto() throws IOException{
        
       
                    if(memimage!=null){  
                        ImageIO.write(memimage, "jpg", new File(pathm));
                    }
                    if(dep1image!=null){
                       ImageIO.write(dep1image, "jpg", new File(pathd1));
                    }
                    if(dep2image!=null){
                        ImageIO.write(dep2image, "jpg", new File(pathd2));
                    }
                    if(dep3image!=null){
                        ImageIO.write(dep3image, "jpg", new File(pathd3));
                    }
                    if(dep4image!=null){
                        ImageIO.write(dep4image, "jpg", new File(pathd4));
                    }
                    if(dep5image!=null){
                        ImageIO.write(dep5image, "jpg", new File(pathd5));
                    }
                    
    }
    
    private void saveMemSign() throws IOException{
      
           
                   if(memsignim!=null){ 
                        ImageIO.write(memsignim, "jpg", new File(pathmsign));
                    }
                    if(dep1signim!=null){
                        ImageIO.write(dep1signim, "jpg", new File(pathd1sign));
                    }
                    if(dep2signim!=null){
                        ImageIO.write(dep2signim, "jpg", new File(pathd2sign));
                    }
                    if(dep3signim!=null){
                        ImageIO.write(dep3signim, "jpg", new File(pathd3sign));
                    }
                    if(dep4signim!=null){
                        ImageIO.write(dep4signim, "jpg", new File(pathd4sign)); 
                    }
                    if(dep5signim!=null){
                        ImageIO.write(dep5signim, "jpg", new File(pathd5sign)); 
                    }
                
      }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
       imageLabel.setIcon(null);
       labeldep1.setIcon(null);
       labeldep2.setIcon(null);
       labeldep3.setIcon(null);
       labeldep4.setIcon(null);
       
       memsignlb.setIcon(null);
       dep1signlb.setIcon(null);
       dep2signlb.setIcon(null);
       dep3signlb.setIcon(null);
       dep4signlb.setIcon(null);
       
       memimage=dep1image=dep2image=dep3image=dep4image=null;
       memsignim=dep1signim=dep2signim=dep3signim=dep4signim=dep5signim=null;
    }//GEN-LAST:event_jButton4ActionPerformed
   
        private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
            dep4image = getImage(labeldep4.getWidth(), labeldep4.getHeight());         
            ImageIcon image = new ImageIcon(dep4image);          
            labeldep4.setIcon(image);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        dep3image = getImage(labeldep3.getWidth(), labeldep3.getHeight());         
        ImageIcon image = new ImageIcon(dep3image);          
        labeldep3.setIcon(image);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        dep2image = getImage(labeldep2.getWidth(), labeldep2.getHeight());         
        ImageIcon image = new ImageIcon(dep2image);          
        labeldep2.setIcon(image); 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        dep1image = getImage(labeldep1.getWidth(), labeldep1.getHeight());         
        ImageIcon image = new ImageIcon(dep1image);          
        labeldep1.setIcon(image);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
         memimage = getImage(imageLabel.getWidth(), imageLabel.getHeight());         
         ImageIcon image = new ImageIcon(memimage);         
         imageLabel.setIcon(image);    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        reset();
        
       // model=(DefaultListModel) jList1.getModel();
        
        if(!jList1.getValueIsAdjusting()){
            if(jList1.getSelectedIndex()==0){
                jButton2.setEnabled(false);
                jButton2.setDisabledIcon(null);
            }
         
        jList1.setSelectedIndex(jList1.getSelectedIndex()-1);
        loaddata();
        
        }else
            reset();
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       
        reset();
        
       // model=(DefaultListModel) jList1.getModel();
        if(!jList1.getValueIsAdjusting()){
        int size=jList1.getModel().getSize();
        if(jList1.getSelectedIndex()==size-1)
            jButton2.setEnabled(false);
          
        jList1.setSelectedIndex(jList1.getSelectedIndex()+1);
        loaddata();
        
        }else
            reset();
        
        
      
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         memsignim = getImage(memsignlb.getWidth(), memsignlb.getHeight());         
         ImageIcon image = new ImageIcon(memsignim);         
         memsignlb.setIcon(image); 
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
         dep1signim = getImage(dep1signlb.getWidth(), dep1signlb.getHeight());         
         ImageIcon image = new ImageIcon(dep1signim);         
         dep1signlb.setIcon(image);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
         
         dep2signim = getImage(dep2signlb.getWidth(), dep2signlb.getHeight());         
         ImageIcon image = new ImageIcon(dep2signim);         
         dep2signlb.setIcon(image);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
         dep3signim = getImage(dep3signlb.getWidth(), dep3signlb.getHeight());         
         ImageIcon image = new ImageIcon(dep3signim);         
         dep3signlb.setIcon(image);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        
         dep4signim = getImage(dep4signlb.getWidth(), dep4signlb.getHeight());         
         ImageIcon image = new ImageIcon(dep4signim);         
         dep4signlb.setIcon(image);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
         dep5image = getImage(labeldep5.getWidth(), labeldep5.getHeight());         
         ImageIcon image = new ImageIcon(dep5image);         
         labeldep5.setIcon(image);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
         dep5signim = getImage(dep5signlb.getWidth(), dep5signlb.getHeight());         
         ImageIcon image = new ImageIcon(dep5signim);         
         dep5signlb.setIcon(image);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
       
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jList1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList1KeyPressed

       if(evt.getKeyCode()==KeyEvent.VK_UP){
          
           evt.consume();
           reset();
           jList1.setSelectedIndex((jList1.getSelectedIndex())-1);
           loaddata();
           
        }
       
       if(evt.getKeyCode()==KeyEvent.VK_DOWN){
           
           evt.consume();
           reset();
            jList1.setSelectedIndex((jList1.getSelectedIndex())+1);
            loaddata();
            
        }
        
    }//GEN-LAST:event_jList1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
      String skey=null;
      List lst=null;
      skey=jTextField1.getText(); 
      
      
           try {
               
               Object[] obj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM CUSTOMERS where SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(skey);
               if(obj!=null){
                   if(obj[0]!=null){
                    String mname=obj[0].toString();
                    memName_label.setText(mname);
                    jList1.setSelectedValue(mname, true);
                    loaddata();
                    
                   
                    
                    
                    
                    
                    
                    
                   }
                   
               }
               else{
                   reset();
                  // loaddata();
                   //jList1.setSelectedIndex(-1);
                   //JOptionPane.showMessageDialog(this, "No Member Exists with the given Memid.."); 
               }
               
              
            } catch (BasicException ex) {
                Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
          
                
        
        
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

   

   public void reset(){
        
        //jTextField1.setText(null);
        memName_label.setText(null);
        Spouse_name_Lable.setText(null);
        dependent1_label.setText(null);
        dependent2_label.setText(null);
        dependent3_label.setText(null);
        dependent4_label.setText(null);
       
        imageLabel.setIcon(null);
        labeldep1.setIcon(null);
        labeldep2.setIcon(null);
        labeldep3.setIcon(null);
        labeldep4.setIcon(null);
        labeldep5.setIcon(null);
        
        memsignlb.setIcon(null);
        dep1signlb.setIcon(null);
        dep2signlb.setIcon(null);
        dep3signlb.setIcon(null);
        dep4signlb.setIcon(null);
        dep5signlb.setIcon(null);
        
        Spouse_name_Lable.setVisible(false);
        dependent1_label.setVisible(false);
        dependent2_label.setVisible(false);
        dependent3_label.setVisible(false);
        dependent4_label.setVisible(false);
       
        labeldep1.setVisible(false);
        labeldep2.setVisible(false);
        labeldep3.setVisible(false);
        labeldep4.setVisible(false);
        labeldep5.setVisible(false);
        
        dep1signlb.setVisible(false);
        dep2signlb.setVisible(false);
        dep3signlb.setVisible(false);
        dep4signlb.setVisible(false);
        dep5signlb.setVisible(false);
        
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jButton15.setVisible(false);
        
        jButton11.setVisible(false);
        jButton12.setVisible(false);
        jButton13.setVisible(false);
        jButton14.setVisible(false);
        jButton16.setVisible(false);
        
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        
        memimage=dep1image=dep2image=dep3image=dep4image=null;
        memsignim=dep1signim=dep2signim=dep3signim=dep4signim=dep5signim=null;
        
        jButton2.setEnabled(true);
        jButton9.setEnabled(true);
        
   }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Spouse_name_Lable;
    private javax.swing.JLabel dep1signlb;
    private javax.swing.JLabel dep2signlb;
    private javax.swing.JLabel dep3signlb;
    private javax.swing.JLabel dep4signlb;
    private javax.swing.JLabel dep5signlb;
    private javax.swing.JLabel dependent1_label;
    private javax.swing.JLabel dependent2_label;
    private javax.swing.JLabel dependent3_label;
    private javax.swing.JLabel dependent4_label;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labeldep1;
    private javax.swing.JLabel labeldep2;
    private javax.swing.JLabel labeldep3;
    private javax.swing.JLabel labeldep4;
    private javax.swing.JLabel labeldep5;
    private javax.swing.JLabel memName_label;
    private javax.swing.JLabel memsignlb;
    private javax.swing.JButton save_button;
    // End of variables declaration//GEN-END:variables


public void loaddata(){
        try {
            
            InetAddress IP=InetAddress.getLocalHost();
            String IPAddress = IP.getHostAddress();
            
            urlPath=(String) new StaticSentence(m_App.getSession()
                    ,"SELECT  VALUE FROM GENERALTABLE WHERE NAME=?" ,SerializerWriteString.INSTANCE
                    ,SerializerReadString.INSTANCE).find(IPAddress);
            
            
            if(urlPath!=null && urlPath.length()>0){
            
            if(selItem!=null && selItem.length()>0){
                memName_label.setText(selItem);
                setMemNo(selItem);
                addDependencies();
            }
            
            if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){
            
                path=jTextField1.getText().toLowerCase();
                pathm=urlPath+"p"+path+"m.jpg";
                pathd1=urlPath+"p"+path+"s.jpg";
                pathd2=urlPath+"p"+path+"a.jpg";
                pathd3=urlPath+"p"+path+"b.jpg";
                pathd4=urlPath+"p"+path+"c.jpg";
                pathd5=urlPath+"p"+path+"d.jpg";

                pathmsign=urlPath+"s"+path+"m.jpg";
                pathd1sign=urlPath+"s"+path+"s.jpg";
                pathd2sign=urlPath+"s"+path+"a.jpg";
                pathd3sign=urlPath+"s"+path+"b.jpg";
                pathd4sign=urlPath+"s"+path+"c.jpg";
                pathd5sign=urlPath+"s"+path+"d.jpg";

                setPhotoImageToLabel();
                setSignImageToLabel();
                
            }
            
                jButton2.setEnabled(true);
                jButton9.setEnabled(true);
            
                
            
            }
            else{
                 JOptionPane.showMessageDialog(this, "No destination path found..! \n Please enter path in general settings.");   
            }
            
        } catch (BasicException ex) {
            Logger.getLogger(MemPhotoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
       
        
}




    @Override
    public String getTitle() {
        return "Member Photo Panel";
    }

    @Override
    public void activate() throws BasicException {
       jTextField1.setText(null);        
       memName_label.setText(null);         
       Spouse_name_Lable.setText(null);         
       dependent1_label.setText(null);         
       dependent2_label.setText(null);         
       dependent3_label.setText(null); 
       dependent4_label.setText(null); 
       
       imageLabel.setIcon(null);        
       labeldep1.setIcon(null);         
       labeldep2.setIcon(null);         
       labeldep3.setIcon(null);         
       labeldep4.setIcon(null);  
       labeldep5.setIcon(null);
       
       memsignlb.setIcon(null);        
       dep1signlb.setIcon(null);         
       dep2signlb.setIcon(null);         
       dep3signlb.setIcon(null);         
       dep4signlb.setIcon(null);  
       dep5signlb.setIcon(null);
       
       Spouse_name_Lable.setVisible(false);         
       dependent1_label.setVisible(false);         
       dependent2_label.setVisible(false);         
       dependent3_label.setVisible(false); 
       dependent4_label.setVisible(false); 
       
       labeldep1.setVisible(false);         
       labeldep2.setVisible(false);         
       labeldep3.setVisible(false);         
       labeldep4.setVisible(false); 
       labeldep5.setVisible(false); 
       
       dep1signlb.setVisible(false);         
       dep2signlb.setVisible(false);         
       dep3signlb.setVisible(false);         
       dep4signlb.setVisible(false); 
       dep5signlb.setVisible(false); 
       
       jButton5.setVisible(false);         
       jButton6.setVisible(false);         
       jButton7.setVisible(false);         
       jButton8.setVisible(false); 
       jButton15.setVisible(false); 
       
       jButton11.setVisible(false);         
       jButton12.setVisible(false);         
       jButton13.setVisible(false);         
       jButton14.setVisible(false);
       jButton16.setVisible(false);
       
       jLabel8.setVisible(false);
       jLabel9.setVisible(false);
       jLabel10.setVisible(false);
       jLabel11.setVisible(false);
       jLabel12.setVisible(false);
       jLabel13.setVisible(false);
       jLabel14.setVisible(false);
       jLabel15.setVisible(false);
       jLabel16.setVisible(false);
       
       jLabel18.setVisible(false);
       jLabel19.setVisible(false);
       
       path = null;         
       pathm = null;         
       pathd1 = null;         
       pathd2 = null;         
       pathd3 = null;         
       pathd4 = null; 
       pathd5 =null;
       
       pathmsign=null;
       pathd1sign = null;         
       pathd2sign = null;         
       pathd3sign = null;         
       pathd4sign = null;  
       pathd5sign=null;
       
       jButton2.setEnabled(true);
       jButton9.setEnabled(true);
       memimage=dep1image=dep2image=dep3image=dep4image=dep5image=null;
       memsignim=dep1signim=dep2signim=dep3signim=dep4signim=dep5signim=null;
       jList1.setSelectedIndex(0);
       loaddata();
       
        PermissionFlag = getPermissionToSavePhoto( m_App.getAppUserView().getUser().getRole());
        
        loadPernissionToChangePhoto();
       
       
    }

    @Override
    public boolean deactivate() {
       return true;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void init(AppView app) throws BeanFactoryException {
        
        m_App=app;
        load();
        loaddata();
    }

    @Override
    public Object getBean() {
        return this;
    }
    
    
    
    public void loadPernissionToChangePhoto(){
        
        if(PermissionFlag){
            jButton1.setVisible(true);
            jButton10.setVisible(true);        
            jButton5.setVisible(true);
            jButton6.setVisible(true);       
            jButton7.setVisible(true);        
            jButton8.setVisible(true);        
            jButton15.setVisible(true);
            jButton11.setVisible(true);        
            jButton12.setVisible(true);        
            jButton13.setVisible(true);
            jButton14.setVisible(true);        
            jButton16.setVisible(true);        
            save_button.setVisible(true);
        }
        else{
            jButton1.setVisible(false);
            jButton10.setVisible(false);        
            jButton5.setVisible(false);
            jButton6.setVisible(false);       
            jButton7.setVisible(false);        
            jButton8.setVisible(false);        
            jButton15.setVisible(false);
            jButton11.setVisible(false);        
            jButton12.setVisible(false);        
            jButton13.setVisible(false);
            jButton14.setVisible(false);        
            jButton16.setVisible(false);
            save_button.setVisible(false);
            
        }
    }
    
    
    public Boolean getPermissionToSavePhoto(String UserName){
        Boolean Flag = false;
        // CHANGED FOR PHOTO PANEL USER PERMISSION 
              try{ 
                  
                Object obj = new StaticSentence(m_App.getSession(), "SELECT NAME FROM ROLES WHERE ID=?", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(UserName);   
                  if(obj!=null)
                  {
                     String RoleName = obj.toString(); 
                    Object[] obj81 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='User Permission list to edit member photo'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                         if(obj81!=null){
                             MemPhotoEditList = new ArrayList<String>();
                             String User_StrFull = obj81[0].toString();
                             String User_Arr[] = User_StrFull.split("#");
                             for(int i=0;i<User_Arr.length;i++){
                                 String x = User_Arr[i].toString();
                                 if(x.equals("ALL")){
                                    Flag=true;
                                    break;
                                 }
                                 else{
                                    if(RoleName.equals(x)){
                                        Flag=true;
                                        break;
                                    }
                                 }
                             }


                         }
                         else{
                             MemPhotoEditList = new ArrayList<String>();
                             Flag = true;
                         }
              
                  }
        
              }
              catch(BasicException e){
                  e.printStackTrace();
              }
        
        
        return Flag;
    }
    
 
   
}

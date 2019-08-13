
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;



    
        
    

        


    
   
public class DocumentUpload extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{
   

    private static void getDialog(DocumentUpload aThis, AppView m_App, boolean b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private AppView m_App;
    
    private JLabel label;
    private Object chooser;
    private File source;
    private BufferedImage Bufferedimg;
    private BufferedImage newbg;
    
    private Dimension preferredSize;
    private int  drag_status = 0,c1,c2,c3,c4;
    private Graphics g;
    private boolean[] ele;
    private int i;
 String Imagename=null;
    private RenderedImage bufferedImage;
    private String result;
    int index = 1;
    private byte[] image;
    
    
    

     public DocumentUpload() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jFrame5 = new javax.swing.JFrame();
        jFrame6 = new javax.swing.JFrame();
        jFrame7 = new javax.swing.JFrame();
        jFrame8 = new javax.swing.JFrame();
        jFrame9 = new javax.swing.JFrame();
        jFrame10 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        photo_upload = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        upload_button = new javax.swing.JButton();
        next_Button = new javax.swing.JButton();
        member_image = new javax.swing.JLabel();
        save_button = new javax.swing.JButton();
        member_no = new javax.swing.JLabel();
        memno_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        memberName_lable = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame5Layout = new javax.swing.GroupLayout(jFrame5.getContentPane());
        jFrame5.getContentPane().setLayout(jFrame5Layout);
        jFrame5Layout.setHorizontalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame5Layout.setVerticalGroup(
            jFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame6Layout = new javax.swing.GroupLayout(jFrame6.getContentPane());
        jFrame6.getContentPane().setLayout(jFrame6Layout);
        jFrame6Layout.setHorizontalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame6Layout.setVerticalGroup(
            jFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame7Layout = new javax.swing.GroupLayout(jFrame7.getContentPane());
        jFrame7.getContentPane().setLayout(jFrame7Layout);
        jFrame7Layout.setHorizontalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame7Layout.setVerticalGroup(
            jFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame8Layout = new javax.swing.GroupLayout(jFrame8.getContentPane());
        jFrame8.getContentPane().setLayout(jFrame8Layout);
        jFrame8Layout.setHorizontalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame8Layout.setVerticalGroup(
            jFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame9Layout = new javax.swing.GroupLayout(jFrame9.getContentPane());
        jFrame9.getContentPane().setLayout(jFrame9Layout);
        jFrame9Layout.setHorizontalGroup(
            jFrame9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame9Layout.setVerticalGroup(
            jFrame9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame10Layout = new javax.swing.GroupLayout(jFrame10.getContentPane());
        jFrame10.getContentPane().setLayout(jFrame10Layout);
        jFrame10Layout.setHorizontalGroup(
            jFrame10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame10Layout.setVerticalGroup(
            jFrame10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        photo_upload.setText("photos upload");

        upload_button.setText("Upload");
        upload_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_buttonActionPerformed(evt);
            }
        });

        next_Button.setText("Next>>");
        next_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_ButtonActionPerformed(evt);
            }
        });

        member_image.setText("sdsd");
        member_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                member_imageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                member_imageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                member_imageMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                member_imageMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                member_imageMouseReleased(evt);
            }
        });
        member_image.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                member_imageMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                member_imageMouseMoved(evt);
            }
        });
        member_image.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                member_imageFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                member_imageFocusLost(evt);
            }
        });
        member_image.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                member_imagePropertyChange(evt);
            }
        });

        save_button.setText("save");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        member_no.setText("Member No :- ");

        memno_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memno_textActionPerformed(evt);
            }
        });
        memno_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                memno_textKeyReleased(evt);
            }
        });

        memberName_lable.setText("MemberName :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(member_no)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(photo_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(member_image, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memberName_lable, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(56, 56, 56)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(upload_button)
                .addGap(63, 63, 63)
                .addComponent(save_button, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(next_Button)
                .addGap(393, 393, 393))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(member_no)
                    .addComponent(memno_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(memberName_lable))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(member_image, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(photo_upload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next_Button)
                    .addComponent(save_button)
                    .addComponent(upload_button))
                .addGap(39, 39, 39)
                .addComponent(jLabel3)
                .addGap(117, 117, 117))
        );

        member_image.setText("");

        jTabbedPane1.addTab("tab1", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void next_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_ButtonActionPerformed
       // spousePhotosAndDocuments spd;
       // try {
           // spd = spousePHOTOSaNDdOCUMENTS.getDialog(this, m_App,true);
           // spd.showDialog();
       // } catch (BasicException ex) {
        //  Logger.getLogger(spousePHOTOSaNDdOCUMENTS.class.getName()).log(Level.SEVERE, null, ex);
      //  }

    }//GEN-LAST:event_next_ButtonActionPerformed

    private void upload_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_buttonActionPerformed
       
  JFileChooser fileChooser = new JFileChooser();
  String fileChooserPath = null;
  try{
      fileChooserPath = getFileChooserPath();
  }
  catch(BasicException Ex){
 
  }
    
  
  
  fileChooser.setCurrentDirectory(new File(fileChooserPath));
      if(fileChooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION)
          

        {

           File sourceFile = fileChooser.getSelectedFile();
            String filename = sourceFile.getName();
            try
            {
                Bufferedimg= ImageIO.read(sourceFile);
                int type = Bufferedimg.getType()==0? BufferedImage.TYPE_INT_ARGB
                                       :Bufferedimg.getType();
                
           //      File img=new File("");
                
        
               
                newbg = resizeImage(Bufferedimg, type,member_image.getWidth() ,member_image.getHeight());
               ImageIcon icon =  new ImageIcon(newbg);
               member_image.setIcon(icon);
           member_image.setPreferredSize(new Dimension(100,100));
            member_image.revalidate();
              member_image.repaint();
     
            } catch (IOException ex) {
          ex.printStackTrace();
      }
            
        }
      
           
        

    


    }//GEN-LAST:event_upload_buttonActionPerformed
    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
 String savepics = null;
 BufferedImage b_Image=null;
  try
  {
  String src = savePics();
 
    String memno = memno_text.getText().trim();
    Imagename  =  memno_text.getText().trim();
   
             
//  Blob img =  (Blob) member_image.getIcon();
       ImageIcon icon=new ImageIcon(newbg); 
                       member_image.setIcon(icon); 

                    
 
 int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO KYM_DOC (ID,MEM_NO,form) VALUES (?,?,?)"                           
                                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.SERIALIZABLE})                         
                                  ).exec(new Object[]{UUID.randomUUID().toString(),memno,member_image});
 
 

 
File initial_image = new File(src + Imagename+".png");
b_Image=ImageIO.read(initial_image);
 ImageIO.write(b_Image,"png", initial_image);
 if(initial_image.exists())
 {
  int response = JOptionPane.showConfirmDialog(null,
                        "Overwrite existing file?", "Confirm Overwrite",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
       if (response == JOptionPane.CANCEL_OPTION){     
       writeFile(initial_image,member_image);
       } else{
           ImageIO.write(newbg,"png", initial_image);
       }
}

 
     } 
  catch( BasicException ex) {
            ex.printStackTrace();
  }
  catch(IOException ex)
  {
      ex.printStackTrace();
  }
         
   
        
   

       
    }//GEN-LAST:event_save_buttonActionPerformed

    private void member_imageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMousePressed
     repaint();
	c1=evt.getX();
        c2=evt.getY();
        
 
    }//GEN-LAST:event_member_imageMousePressed

    private void member_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseClicked
   repaint();
	c1=evt.getX();
        c2=evt.getY();
        
 
    }//GEN-LAST:event_member_imageMouseClicked

    private void member_imageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseReleased
       repaint();
           if(drag_status==1){
            c3=evt.getX();
             c4=evt.getY();
               try{
                 draggedScreen();
               }
               catch(Exception  e){
                   e.printStackTrace();
               }
           }
 
     
  
     
      
    }//GEN-LAST:event_member_imageMouseReleased

    private void member_imageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseEntered
        
    }//GEN-LAST:event_member_imageMouseEntered

    private void member_imageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseDragged
       repaint();
	drag_status=1;
	c3=evt.getX();
	c4=evt.getY();
  
    }//GEN-LAST:event_member_imageMouseDragged

    private void member_imagePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_member_imagePropertyChange
        
    }//GEN-LAST:event_member_imagePropertyChange

    private void member_imageMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_member_imageMouseMoved

    private void member_imageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_member_imageMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_member_imageMouseExited

    private void member_imageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_member_imageFocusGained
        // TODO add your handling code here:2
    }//GEN-LAST:event_member_imageFocusGained

    private void member_imageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_member_imageFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_member_imageFocusLost

    private void memno_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memno_textActionPerformed
     
      

        
    }//GEN-LAST:event_memno_textActionPerformed

    private void memno_textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memno_textKeyReleased
          if(memno_text!= null && memno_text.getText().trim().length()>0){
            String memno = memno_text.getText().trim();
        try {
            String obj =(String)new StaticSentence(m_App.getSession(), " SELECT NAME FROM customers where SEARCHKEY= ? ",
                    SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(memno);
            if(obj!=null){
                String memname = obj; 
                memberName_lable.setText(memname);
                memberName_lable.setVisible(true);
              
                
                
             
       String obj1 =(String)new StaticSentence(m_App.getSession(), "SELECT MEM_NO FROM KYM_DOC K, CUSTOMERS C WHERE K.MEM_NO=C.SEARCHKEY AND C.SEARCHKEY = ?",
                           SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(memno);
                  if(obj1!=null){
                        String memname2 = obj1; 
                       // member_image.setText(memname2);
                        member_image.setVisible(true);
                    }else
                    {
                      memberName_lable.setText("match not found");
                      memberName_lable.setForeground(Color.RED);
                   
                   }
            
            
      Image ig = null;     
     Object[] objZ = (Object[]) new StaticSentence(m_App.getSession(), "select form from kym_doc where MEM_NO = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.IMAGE})).find(memno);
        if(objZ!=null){
            if(objZ[0]!=null){
              //ig=(Image) objZ[0]; \
               byte[] buffered;
               try
               {
               buffered = serialize(objZ[0]);
               }catch(IOException ex)
               {
                   ex.printStackTrace();
               }
            }
        }
            


        }else
        {
            //memno_text.setVisible(false);
             memberName_lable.setVisible(false);
              member_image.setVisible(false);  
        }
        
        }catch(BasicException e4)
        {
            e4.printStackTrace();
        }
          }
    }//GEN-LAST:event_memno_textKeyReleased

    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame10;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JFrame jFrame5;
    private javax.swing.JFrame jFrame6;
    private javax.swing.JFrame jFrame7;
    private javax.swing.JFrame jFrame8;
    private javax.swing.JFrame jFrame9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel memberName_lable;
    private javax.swing.JLabel member_image;
    private javax.swing.JLabel member_no;
    private javax.swing.JTextField memno_text;
    private javax.swing.JButton next_Button;
    private javax.swing.JLabel photo_upload;
    private javax.swing.JButton save_button;
    private javax.swing.JButton upload_button;
    // End of variables declaration//GEN-END:variables
    
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
 public String getTitle() {
       return "Upload KYM Documents";
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
        m_App = app;
    }

    public Object getBean() {
        return  this;
    }
    
    
    public void loaddata( ) throws BasicException{
        
       

        
    }
    
    public void ButtonGrp(){
       
        
    }
    
    public void reset(){
        
       
    }

    private void draggedScreen() throws Exception {
        int w = c1 - c3;
        int h = c2 - c4;
      w = w * -1;
       h = h * -1;
        
        Robot robot = new Robot();
        
        BufferedImage img = robot.createScreenCapture(new Rectangle(c1,c2,w,h));
            
        System.out.println("Cropped image saved successfully."); 
        File outputfile = new File("F:\\cropped images\\"+"Cropped.png");
        ImageIO.write(img,"png",outputfile);
        
      int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB
                                                : img.getType();

                        
                        
      BufferedImage imgbg =  resizeImage(img, type, member_image.getWidth(), member_image.getHeight());
       ImageIcon icon=new ImageIcon(imgbg); 
                        member_image.setIcon(icon); 
               
       
       
      
    }
    
    
    
  public void paint(Graphics g)
    {
       super.paint(g);
        int w = c1 - c3;    
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        if(w<0){
            w = w * -1;
        }
        g.drawRect(c1, c2, w, h);
        
    
    }

    public static boolean writeFile(File outputfile,Object component){
          try {

            BufferedImage i1 = ImageIO.read(outputfile);
            BufferedImage bi = new BufferedImage(i1.getWidth(), i1.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
          ImageIO.write(bi, ".png", outputfile);
         // outputfile.getName();
        } catch (IOException e) {
            return false;
        }
        return true;
        
    }
   
      private static class SourceFileName {

        private static void setText(String toString) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public SourceFileName() {
        }
    }

    private static class PicFileName {

        private static void setText(String toString) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public PicFileName() {
        }
    }

    
   private BufferedImage resizeImage(BufferedImage originalImage , int type, Integer img_width , Integer img_heigth) 
           
           
{
    
BufferedImage resizedImage = new BufferedImage(img_width, img_heigth, type);
Graphics2D g = resizedImage.createGraphics();
g.drawImage(originalImage,0,0 , img_width, img_heigth, null);
g.dispose();
return resizedImage;

}
   
   public String savePics() throws BasicException{
       Object s = null;
       String p = null;
       String Name= "images";
       s = new StaticSentence(m_App.getSession(), " SELECT VALUE FROM generaltable where NAME = ? ",
               SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(Name);
       
       if(s!=null){
           p=s.toString();
                    }
       return p; 
       
   }


    
public String getFileChooserPath() throws BasicException{
    
    Object o = null;
       String t = null;
        
       String Name="File Chooser path"; 
        o =  new StaticSentence(m_App.getSession(), "SELECT VALUE FROM generaltable WHERE NAME= ? ", 
                             SerializerWriteString.INSTANCE,
                             SerializerReadString.INSTANCE).find(Name);
       
        
       if(o!=null){
          t=o.toString();
       }
     return t; 
}
}





 




 








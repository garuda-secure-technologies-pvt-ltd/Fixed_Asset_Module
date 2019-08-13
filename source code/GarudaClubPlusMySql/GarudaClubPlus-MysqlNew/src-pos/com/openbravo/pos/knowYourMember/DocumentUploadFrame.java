/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import static com.openbravo.pos.knowYourMember.DocumentUpload.writeFile;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

 
public class DocumentUploadFrame extends  javax.swing.JDialog {

    
    private AppView app;
     private boolean flag;
     private DocumentReceiptTableModel DocumentReceipt_Table_Model;
     private List<DocumentReceiptTableModel.DocumentReceiptListInfo> DocumentAllList = new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
     private String SelectedMember;
     private String FileNameInit;
     private String Memno;
     private int DocumentFlag;
     private String CustomerID;   
     private Boolean SaveStatus = false;
     
     
     
      public DocumentUploadFrame(java.awt.Frame parent, boolean modal , String FileNameInit , String Memno , int DocumentFlag) {
        super(parent, modal);
        initComponents();
       
    }
     
     
      public DocumentUploadFrame(java.awt.Dialog parent,  AppView app, boolean flag , String FileNameInit , String Memno , int DocumentFlag) {
        super(parent, true);
        this.FileNameInit=FileNameInit;
        this.app = app;
        this.flag = flag;
        this.Memno=Memno;
        this.DocumentFlag=DocumentFlag;
    }
    
     public DocumentUploadFrame(java.awt.Frame parent,  AppView app, boolean flag , String FileNameInit, String Memno , int DocumentFlag) {
        super(parent, true);
        this.FileNameInit=FileNameInit;
        this.app = app;
        this.flag = flag;
        this.Memno=Memno;
        this.DocumentFlag=DocumentFlag;
    }
    
     

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ImageDoc_Label = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("Upload Document Menu");

        jButton1.setText("Upload Document");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ImageDoc_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ImageDoc_Label.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        ImageDoc_Label.setMaximumSize(new java.awt.Dimension(34, 50));
        ImageDoc_Label.setMinimumSize(new java.awt.Dimension(34, 50));
        ImageDoc_Label.setPreferredSize(new java.awt.Dimension(34, 50));
        jScrollPane1.setViewportView(ImageDoc_Label);

        jButton2.setText("Save Document");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private BufferedImage Bufferedimg;
    private BufferedImage newbg;
    String SourceFilePath = "F:/" ;
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JFileChooser fileChooser = new JFileChooser();
            String fileChooserPath = null;
            try{
                fileChooserPath = getFileChooserPath();
            }
            catch(BasicException Ex){

            }
    
  
  
        fileChooser.setCurrentDirectory(new File(SourceFilePath));
        if(fileChooser.showOpenDialog(this)== JFileChooser.APPROVE_OPTION)
          

        {

           File sourceFile = fileChooser.getSelectedFile();
            String filename = sourceFile.getName();
              SourceFilePath = sourceFile.getPath();
            
            try
            {
                Bufferedimg = ImageIO.read(sourceFile);
                int type = Bufferedimg.getType()==0? BufferedImage.TYPE_INT_ARGB
                                       :Bufferedimg.getType();
                
           //      File img=new File("");
                newbg = resizeImage(Bufferedimg, type,ImageDoc_Label.getWidth() ,ImageDoc_Label.getHeight());
                ImageIcon icon =  new ImageIcon(newbg);
                ImageDoc_Label.setIcon(icon);
                ImageDoc_Label.setPreferredSize(new Dimension(100,100));
                ImageDoc_Label.revalidate();
                ImageDoc_Label.repaint();
                } catch (IOException ex) {
                 ex.printStackTrace();
             }
            
        }
      
           
        

    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(Memno!=null){
            if(FileNameInit!=null){
               try{  
                   
                    Object[] PhotoSrcobj = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable where NAME = 'KymDocuments'  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                    if(PhotoSrcobj!=null){
                        String Src = PhotoSrcobj[0].toString();
                        BufferedImage b_Image=Bufferedimg;   
                        File initial_image = new File(Src + FileNameInit+".jpg");
                       // b_Image=ImageIO.read(initial_image);
                         ImageIO.write(b_Image,"jpg", initial_image);
                         String FinalFilePath = initial_image.getAbsolutePath();
                         
                        // if(initial_image.exists())
                         //{
                        //  int response = JOptionPane.showConfirmDialog(null,
                          //                      "Overwrite existing file?", "Confirm Overwrite",
                          //                      JOptionPane.OK_CANCEL_OPTION,
                          //                      JOptionPane.QUESTION_MESSAGE);
                          //     if (response == JOptionPane.CANCEL_OPTION){     
                          //     writeFile(initial_image,ImageDoc_Label);
                           //    } else{
                           //        ImageIO.write(newbg,"png", initial_image);
                           //    }
                       // }   
                   
                       
                       
                       
                       
                        Object[] Custobj = (Object[]) new StaticSentence(app.getSession(), "SELECT id FROM customers where searchkey=?  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Memno);
                        if(Custobj!=null){
                            CustomerID = Custobj[0].toString();
                        }
                        
                        
                        Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT id FROM kym_doc where memno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Memno);
                        if(obj!=null){
                            // already present 
                             String Kym_Id =obj[0].toString();
                             
                             if(Kym_Id!=null){
                                 
                                 if(DocumentFlag==2){
                                    UploadDocumentMemberID( Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==3){
                                    UploadDocumentSpouseID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==4){
                                    UploadDocumentFatherID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==5){
                                    UploadDocumentMotherID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==6){
                                    UploadDocumentS1ID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==7){
                                    UploadDocumentS2ID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==8){
                                    UploadDocumentS3ID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==9){
                                    UploadDocumentD1ID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==10){
                                    UploadDocumentD2ID(Kym_Id,FinalFilePath);   
                                 }
                                 if(DocumentFlag==11){
                                    UploadDocumentD3ID(Kym_Id,FinalFilePath);   
                                 }
                                 
                                 
                                 
                                 
                             }
                             


                        }
                        else{
                            // New member 

                            

                            if(DocumentFlag==2){
                                    InsertDocumentMemberID(Memno, CustomerID, FinalFilePath);   
                                 }
                                 if(DocumentFlag==3){
                                    InsertDocumentSpouseID(Memno, CustomerID, FinalFilePath);   
                                 }
                                 if(DocumentFlag==4){
                                    InsertDocumentFatherID(Memno, CustomerID, FinalFilePath);   
                                 }
                                 if(DocumentFlag==5){
                                    InsertDocumentMotherID(Memno, CustomerID, FinalFilePath);  
                                 }
                                 if(DocumentFlag==6){
                                    InsertDocumentS1ID(Memno, CustomerID, FinalFilePath);   
                                 }
                                 if(DocumentFlag==7){
                                     InsertDocumentS2ID(Memno, CustomerID, FinalFilePath);   
                                 }
                                 if(DocumentFlag==8){
                                     InsertDocumentS3ID(Memno, CustomerID, FinalFilePath);     
                                 }
                                 if(DocumentFlag==9){
                                     InsertDocumentD1ID(Memno, CustomerID, FinalFilePath);      
                                 }
                                 if(DocumentFlag==10){
                                     InsertDocumentD2ID(Memno, CustomerID, FinalFilePath);    
                                 }
                                 if(DocumentFlag==11){
                                     InsertDocumentD3ID(Memno, CustomerID, FinalFilePath);   
                                 }
                            
                            




                        }
                
                        
                        
                        
                        
                    }
                    
                    JOptionPane.showMessageDialog(this, "Document Saved. ", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    SaveStatus = true;
               }
               catch(BasicException e){
                   e.printStackTrace();
                   SaveStatus = false;
               }
               catch(IOException ex)
                {
                    ex.printStackTrace();
                    SaveStatus = false;
                }
            }
        }   
        
    }//GEN-LAST:event_jButton2ActionPerformed
 
     
    private BufferedImage resizeImage(BufferedImage originalImage , int type, Integer img_width , Integer img_heigth) 
    {

        BufferedImage resizedImage = new BufferedImage(img_width, img_heigth, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage,0,0 , img_width, img_heigth, null);
        g.dispose();
        return resizedImage;
    }
    
    
    
    public String getFileChooserPath() throws BasicException{

        Object o = null;
           String t = null;

           String Name="File Chooser path"; 
            o =  new StaticSentence(app.getSession(), "SELECT VALUE FROM generaltable WHERE NAME= ? ", 
                                 SerializerWriteString.INSTANCE,
                                 SerializerReadString.INSTANCE).find(Name);


           if(o!=null){
              t=o.toString();
           }
         return t; 
    }
    
    public static DocumentUploadFrame getDialog(Component parent,  AppView app, boolean flag, String FileNameInit , String Memno , int DocumentFlag) throws BasicException {

        Window window = getWindow(parent);
        
        DocumentUploadFrame bill;
        
       

        if (window instanceof Frame) {
            bill = new DocumentUploadFrame((Frame) window , app, flag ,FileNameInit , Memno , DocumentFlag);
        } else {
            bill = new DocumentUploadFrame((Dialog) window, app, flag,FileNameInit , Memno , DocumentFlag);
        }
       
        return bill;
        
        
    }
    
     public Boolean showDialog() {
        try {
            init();
            setVisible(true);
           
        } catch (BasicException e) {
            new MessageInf(e).show(getParent());
        }
        return SaveStatus ;
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
    
   
   public void init() throws BasicException {
        initComponents();
        
          

    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageDoc_Label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables







public void UploadDocumentMemberID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET idproof=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentSpouseID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET spouseid=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}

public void UploadDocumentFatherID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET fatherid=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}

public void UploadDocumentMotherID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET motherid=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}

public void UploadDocumentS1ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET s1id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentS2ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET s2id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentS3ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET s3id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentD1ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET d1id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentD2ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET d2id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}
public void UploadDocumentD3ID( String Id , String url) throws BasicException{
    
     new PreparedSentence(app.getSession()
                        , "UPDATE kym_doc SET d3id=? WHERE Id=?"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})).exec(new Object[]{url,Id});

}















public void InsertDocumentMemberID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , idproof ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}    

public void InsertDocumentFatherID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , fatherid ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentMotherID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , motherid ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentSpouseID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , spouseid ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentS1ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , s1id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   

public void InsertDocumentS2ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , s2id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentS3ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , s3id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentD1ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , d1id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentD2ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , d2id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   
public void InsertDocumentD3ID( String Memno , String Memid, String url) throws BasicException{
    
    new PreparedSentence(app.getSession()
                        , "INSERT INTO kym_doc(ID,MEMNO,MEMID , d3id ) VALUES(?,?,?,?)"
                        , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING , Datas.STRING}))
            .exec(new Object[]{UUID.randomUUID().toString(),Memno, Memid , url });
}   


















}

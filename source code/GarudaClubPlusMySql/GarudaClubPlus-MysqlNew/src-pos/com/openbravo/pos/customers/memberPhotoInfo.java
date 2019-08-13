

package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.openbravo.pos.sms.MemberEmailList;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.net.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class memberPhotoInfo extends javax.swing.JDialog {

    private AppView app;
    private boolean flag;
    private String MemberNo;
    private String MemberName;
    private String urlPath;
    private String CardNo;
      
    public memberPhotoInfo(java.awt.Frame parent, boolean modal , String MemberNo , String MemberName) {
        super(parent, modal);
        initComponents();
        
    }

    
  
    
    public memberPhotoInfo(java.awt.Dialog parent,  AppView app, boolean flag , String MemberNo , String MemberName , String CardNo) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.MemberNo = MemberNo;
        this.MemberName = MemberName;
        this.CardNo= CardNo;
    }
    
     public memberPhotoInfo(java.awt.Frame parent,  AppView app, boolean flag ,  String MemberNo , String MemberName , String CardNo) {
        super(parent, true);
       
        this.app = app;
        this.flag = flag;
        this.MemberNo = MemberNo;
        this.MemberName = MemberName;
        this.CardNo= CardNo;
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        memberNo_Text = new javax.swing.JLabel();
        memberType_label = new javax.swing.JLabel();
        membername_text = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dependent_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DependentName_text = new javax.swing.JLabel();
        relation_text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Member No: ");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(180, 5, 5));
        jLabel1.setText("Member Information");

        jButton1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(186, 16, 16));
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        memberNo_Text.setText("jLabel5");

        memberType_label.setText("Member Name : ");

        membername_text.setText("jLabel7");

        jLabel5.setForeground(new java.awt.Color(27, 41, 189));
        jLabel5.setText("Photo");

        jLabel7.setForeground(new java.awt.Color(25, 70, 224));
        jLabel7.setText("signature");

        jLabel6.setText("Name on card : ");

        jLabel8.setText("Relation with member : ");

        DependentName_text.setText("jLabel9");

        relation_text.setText("jLabel10");

        javax.swing.GroupLayout dependent_panelLayout = new javax.swing.GroupLayout(dependent_panel);
        dependent_panel.setLayout(dependent_panelLayout);
        dependent_panelLayout.setHorizontalGroup(
            dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dependent_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addGroup(dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DependentName_text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(relation_text, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        dependent_panelLayout.setVerticalGroup(
            dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dependent_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(DependentName_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dependent_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(relation_text))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(333, 333, 333)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dependent_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberType_label)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberNo_Text, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(membername_text, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(memberNo_Text))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberType_label)
                    .addComponent(membername_text))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dependent_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static memberPhotoInfo getDialog(Component parent,  AppView app, boolean flag ,  String MemberNo , String MemberName , String CardNo) throws BasicException {

        Window window = getWindow(parent);
        
        memberPhotoInfo bill;
        
       

        if (window instanceof Frame) {
            bill = new memberPhotoInfo((Frame) window , app, flag , MemberNo , MemberName , CardNo);
        } else {
            bill = new memberPhotoInfo((Dialog) window, app, flag, MemberNo , MemberName , CardNo);
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
        urlPath=getUrlPath();
        
        memberNo_Text.setText(MemberNo);
        membername_text.setText(MemberName);
        Object[] deptObj = null;
        int flag = getCardUsedFlags(CardNo);
        if(flag==1){
            setPhotoToLable();
            dependent_panel.setVisible(false);
        }
        else if(flag==2){
            deptObj = getDependantDetails(CardNo);   // ID ,NAME , DTYPE,DMEMNO
            if(deptObj!=null){
                setDependantPhotoLable(deptObj);
                dependent_panel.setVisible(true);
            }
        }
        
        
        
        
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DependentName_text;
    private javax.swing.JPanel dependent_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel memberNo_Text;
    private javax.swing.JLabel memberType_label;
    private javax.swing.JLabel membername_text;
    private javax.swing.JLabel relation_text;
    // End of variables declaration//GEN-END:variables

public void setPhotoToLable(){
  try{  
        // FOR PHOTO
        String memberNotemp = "p"+MemberNo.toLowerCase()+"m.jpg";
//        String filename  = "./MemberPhotos/"+memberNotemp;
String filename  = urlPath+""+memberNotemp;
        File file = new File(filename);
        if(file!=null){
            URL url = file.toURI().toURL();
            BufferedImage bi = ImageIO.read(url);
            ImageIcon ic = new ImageIcon(bi);
            jLabel3.setText("");
            jLabel3.setIcon(ic);
        }
        else{
            jLabel3.setText("");
        }
        // FOR SIGNATURE
    
        String memberNotemp1 = "s"+MemberNo.toLowerCase()+"m.jpg";
       // String filename1  = "./MemberPhotos/"+memberNotemp1;
         String filename1  =  urlPath+""+memberNotemp1;
        File file1 = new File(filename1);
        if(file1!=null){
            URL url1 = file1.toURI().toURL();
            BufferedImage bi1 = ImageIO.read(url1);
            ImageIcon ic1 = new ImageIcon(bi1);
            jLabel4.setText("");
            jLabel4.setIcon(ic1);
        }
        else{
            jLabel4.setText("");
        }
        
  }
  catch(Exception e){
      e.printStackTrace();
      jLabel4.setText("");
      jLabel3.setText("");
      
  }
  
    
}


public void setDependantPhotoLable(Object[] DeptObj){          // ID ,NAME , DTYPE,DMEMNO
    String DependantNo = null;
    String TypeName =null;
    String DName = null;
    String DNo = null;
    String DmembeePhoto = null; 
    String DmemberSign = null; 
    String DType = null;
    try{
        if(DeptObj[3]!=null){
            DependantNo=DeptObj[3].toString().toLowerCase();
            DName = DeptObj[1].toString();
            DNo = DeptObj[3].toString();
            DType = DeptObj[2].toString();
            String t = "\\0";
            
            DependentName_text.setText(DName);
            relation_text.setText(DType);
            
            if(DependantNo.contains(t)){
                TypeName= "Spouse Name : ";
                DmembeePhoto="p"+MemberNo.toLowerCase()+"s.jpg";
                DmemberSign = "s"+MemberNo.toLowerCase()+"s.jpg";
            }
            else if(DependantNo.contains("\\1")){
                TypeName= "Dependant Name : ";
                DmembeePhoto="p"+MemberNo.toLowerCase()+"a.jpg";
                DmemberSign = "s"+MemberNo.toLowerCase()+"a.jpg";
            }
            else if(DependantNo.contains("\\2")){
                TypeName= "Dependant Name : ";
                DmembeePhoto="p"+MemberNo.toLowerCase()+"b.jpg";
                DmemberSign = "s"+MemberNo.toLowerCase()+"b.jpg";
            }
            else if(DependantNo.contains("\\3")){
                TypeName= "Dependant Name : ";
                DmembeePhoto="p"+MemberNo.toLowerCase()+"c.jpg";
                DmemberSign = "s"+MemberNo.toLowerCase()+"c.jpg";
            }
            else if(DependantNo.contains("\\3")){
                TypeName= "Dependant Name : ";
                DmembeePhoto="p"+MemberNo.toLowerCase()+"d.jpg";
                DmemberSign = "s"+MemberNo.toLowerCase()+"d.jpg";
            }



//            String PhotoFileUrlStr  = "./MemberPhotos/"+DmembeePhoto;
//            String SignFileUrlStr  = "./MemberPhotos/"+DmemberSign;
            String PhotoFileUrlStr  = urlPath+""+DmembeePhoto;
            String SignFileUrlStr  =urlPath+""+DmemberSign;
            File file = new File(PhotoFileUrlStr);
            if(file!=null){
                URL url = file.toURI().toURL();
                BufferedImage bi = ImageIO.read(url);
                ImageIcon ic = new ImageIcon(bi);
                jLabel3.setText("");
                jLabel3.setIcon(ic);
            }
            else{
                jLabel3.setText("");
            }


            File file1 = new File(SignFileUrlStr);
            if(file1!=null){
                URL url1 = file1.toURI().toURL();
                BufferedImage bi1 = ImageIO.read(url1);
                ImageIcon ic1 = new ImageIcon(bi1);
                jLabel4.setText("");
                jLabel4.setIcon(ic1);
            }
            else{
                jLabel4.setText("");
            }
            
            
            
        
        }
    }
    catch(FileNotFoundException e){
        e.printStackTrace();
       
    }
    catch(IOException ex){
        ex.printStackTrace();
        
    }
    
}



public int getCardUsedFlags(String CardNo){                   /////  if i=1 then MEMBER .. IF i=2 then DEPENDANT
    int i=0;
    try{
        Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT ID FROM CUSTOMERS WHERE CARD=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CardNo);
        if(obj1!=null){
            i=1;
        }
        else{
            Object[] obj2 = (Object[]) new StaticSentence(app.getSession(), "SELECT ID FROM memdependent WHERE CARD=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(CardNo);
            if(obj2!=null){
               i=2; 
            }
        }
   
        return i;/////  if i=1 then MEMBER .. IF i=2 then DEPENDANT
    }
    catch(BasicException e){
        e.printStackTrace();
        new MessageInf(e).show(new JFrame());
    }
    return i;
}



public Object[] getDependantDetails(String CardNo){     // ID ,NAME , DTYPE,DMEMNO
    Object[] obj3 =null;
    try{
       obj3= (Object[]) new StaticSentence(app.getSession(), "SELECT ID, DNAME,DTYPE,DMEMNO FROM memdependent WHERE CARD=? AND ACTIVE=1", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find(CardNo); 
       if(obj3!=null){
           return obj3;
       } 
       
    }
    catch(BasicException e){
        e.printStackTrace();
        new MessageInf(e).show(new JFrame());
    }
    return obj3;
}
//added by pratima
public String getUrlPath(){
  String urlPath=null;
    try {
            
            InetAddress IP=InetAddress.getLocalHost();
            String IPAddress = IP.getHostAddress();
            
            urlPath=(String) new StaticSentence(app.getSession()
                    ,"SELECT  VALUE FROM GENERALTABLE WHERE NAME=?" ,SerializerWriteString.INSTANCE
                    ,SerializerReadString.INSTANCE).find(IPAddress);
            }catch(BasicException e){
                e.printStackTrace();
            } catch(UnknownHostException e){
            e.printStackTrace();
        }
return urlPath;
}
}

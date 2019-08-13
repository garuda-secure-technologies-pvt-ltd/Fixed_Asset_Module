

package com.openbravo.pos.clubmang;


import com.openbravo.basic.BasicException;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.rpc.Call;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class JPanelmysql extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {
     
    private AppView mapp;
      public JPanelmysql() {
        initComponents();
      }

    private void close(POIFSFileSystem fs) {
      
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Browse = new javax.swing.JButton();
        Uploaddata = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        fileformat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        Browse.setText("Browse");
        Browse.setName("Browse"); // NOI18N
        Browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseActionPerformed(evt);
            }
        });

        Uploaddata.setText("Uploaddata");
        Uploaddata.setName("Uploaddata"); // NOI18N
        Uploaddata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploaddataActionPerformed(evt);
            }
        });

        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Extra File ");
        jLabel1.setName("jLabel1"); // NOI18N

        fileformat.setText("File Format");
        fileformat.setName("fileformat"); // NOI18N
        fileformat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileformatActionPerformed(evt);
            }
        });

        jLabel2.setText("Select File Format");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("While saving please Select the save type as Excel 97-2003 workbook");
        jLabel3.setName("jLabel3"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Uploaddata)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fileformat)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Browse)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fileformat)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Browse))
                .addGap(63, 63, 63)
                .addComponent(Uploaddata, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseActionPerformed
          JFileChooser fChooser = new JFileChooser();
        fChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fChooser.setCurrentDirectory(null);
        fChooser.showOpenDialog(null);
        jTextField1.setText(fChooser.getSelectedFile().getAbsolutePath());//          ODO add your handling code here:
}//GEN-LAST:event_BrowseActionPerformed

    private void UploaddataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UploaddataActionPerformed
       if (jTextField1.getText().length() > 0) {
           try {
              
                String memsekey=null;
                String memname=null;
                String sowo=null;
                String memfn=null;
                String memlstn=null;
                String mememail=null;
                String phn = null;
                String phn2 = null;
                String fax =null;
                String  mob= null;
                String add=null;
                String add2=null;
                String postl=null;
                String city=null;
                String region=null;
                String country=null;
                ArrayList al=new ArrayList();
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("D:/MYSQL26.xls"));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow row = sheet.getRow(0);
                int c = row.getPhysicalNumberOfCells();
                al.add(c);
                HSSFCell cell = null;
                cell = row.getCell((short) 0);
                if (cell != null) {
                    memsekey = cell.getRichStringCellValue().getString();
                    al.add(memsekey);
                    System.out.println(memsekey);
                }
                cell = row.getCell((short) 1);
                if (cell != null) {
                     memname = cell.getRichStringCellValue().getString();
                     al.add((memname));
                    System.out.println(memname);
                }
                cell = row.getCell((short) 2);
                if (cell != null) {
                     sowo = cell.getRichStringCellValue().getString();
                     al.add(sowo);
                    System.out.println(sowo);
                }
                cell = row.getCell((short) 3);
                if (cell != null) {
                     memfn = cell.getRichStringCellValue().getString();
                     al.add(memfn);
                    System.out.println(memfn);
                }
                cell = row.getCell((short) 4);
                if (cell != null) {
                    memlstn = cell.getRichStringCellValue().getString();
                     al.add(memlstn);
                    System.out.println(memlstn);
                } 
                cell = row.getCell((short) 5);
                if (cell != null) {
                  mememail= cell.getRichStringCellValue().getString();
                     al.add(mememail);
                    System.out.println(mememail);
                }
                 cell = row.getCell((short) 6);
                if (cell != null) {
                     phn =cell.getRichStringCellValue().getString();
                     al.add(phn);
                    System.out.println(phn);
               }
                cell = row.getCell((short) 7);
                if (cell != null) {
                      phn2 =cell.getRichStringCellValue().getString();
                     al.add( phn2);
                    System.out.println( phn2);
               }
               cell = row.getCell((short) 8);
                if (cell != null) {
                    fax =cell.getRichStringCellValue().getString();
                     al.add(fax);
                    System.out.println(fax);
              }
               cell = row.getCell((short) 9);
                if (cell != null) {
                     mob =cell.getRichStringCellValue().getString();
                     al.add(mob);
                    System.out.println(mob);
             }
              cell = row.getCell((short) 10);
                if (cell != null) {
                     add = cell.getRichStringCellValue().getString();
                     al.add(add);
                    System.out.println(add);
            }
            cell = row.getCell((short) 11);
                if (cell != null) {
                     add2 = cell.getRichStringCellValue().getString();
                     al.add(add2);
                    System.out.println(add2);
            }
            cell = row.getCell((short) 12);
                if (cell != null) {
                    postl = cell.getRichStringCellValue().getString();
                     al.add(postl);
                    System.out.println(postl);
            }
            cell = row.getCell((short) 13);
                if (cell != null) {
                    city = cell.getRichStringCellValue().getString();
                     al.add(city);
                    System.out.println(city);
            }
            cell = row.getCell((short) 14);
                if (cell != null) {
                     region = cell.getRichStringCellValue().getString();
                     al.add(region);
                    System.out.println(region);
            }
             cell = row.getCell((short) 15);
                if (cell != null) {
                    country = cell.getRichStringCellValue().getString();
                     al.add(country);
                    System.out.println(country);
           }
       
            Upload udata = new Upload();
          boolean result = udata.load(mapp,jTextField1.getText(),al);
         
         // Call.Close
       // Close(wb);
       //   release(fs);
//           if(fs.equals(null)){
//               JOptionPane.showMessageDialog(null, "FIle closed  successfully");
//           }else{
//              JOptionPane.showMessageDialog(null, "FIle not closed  ");
//           }
         // JOptionPane.showMessageDialog(null, "Uploaded successfully");
            if(result){
              JOptionPane.showMessageDialog(null,"Uploaded successfully");
               JOptionPane.showMessageDialog(null, "Please check the Error.txt file for the Alredy Registerd values");
                }
                else{
                   JOptionPane.showMessageDialog(null, "Please check the out.txt file for the values");
                  // fs.equals(null);
                }
                
           }
           catch (IOException ex) {
                ex.printStackTrace();
        }
           //finally{
               //if(fs!=null)
           //}
      }else{
            JOptionPane.showMessageDialog(null,"Please Browse the file");
      }
       
       
}//GEN-LAST:event_UploaddataActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void fileformatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileformatActionPerformed
     try {
            // TODO add your handling code here:
            

            Runtime rt=Runtime.getRuntime();
            rt.exec("cmd.exe /C start excel.exe /r GarudaMemberMaster");
        } catch (Exception ex) {
            ex.printStackTrace();    // TODO add your handling code here:
}//GEN-LAST:event_fileformatActionPerformed
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Browse;
    private javax.swing.JButton Uploaddata;
    private javax.swing.JButton fileformat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    

    public void init(AppView app) throws BeanFactoryException {
        mapp = app;
        jTextField1.setText(null);
    }

    public String getTitle() {
       return "Convert to DataBase";
    }

    public void activate() throws BasicException {
        jTextField1.setText(null);
    }

    public boolean deactivate() {
        return true;
    }

    public JComponent getComponent() {
         return this;
    }

    public Object getBean() {
       return this;
    }

    
}
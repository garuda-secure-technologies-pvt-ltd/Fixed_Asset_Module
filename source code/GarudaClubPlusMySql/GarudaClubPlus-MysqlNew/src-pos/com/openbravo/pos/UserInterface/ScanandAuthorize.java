/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import com.openbravo.pos.util.AltEncrypter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class ScanandAuthorize {
   private String filepath;

   public ScanandAuthorize(String filepath) {
        this.filepath = filepath;
   }

   public void Authorise() throws FileNotFoundException{
       //creates a hash value of the date and erites into the pendrive
     try{
      boolean status=true;
      File f=new File(filepath);
      if(f.exists()){
        if(JOptionPane.showConfirmDialog(null, "A folder garuda alredy exit.It will be deleted.Do you want to continue?", null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
          f.delete();
          f.mkdir();
         }else
            status=false;
      }else
          f.mkdir();
      if(status){
          f=new File(filepath+"/garuda.gar");
          FileOutputStream fo=new FileOutputStream(f);
          DateFormat dateformat=DateFormat.getInstance();
          dateformat=new SimpleDateFormat("dd MMM,yyyy");
          Date dnow=new Date();
          String sdata=dateformat.format(dnow);
          //calculates the hash value of date
          AltEncrypter en=new AltEncrypter("cypherkey" + "garuda");
          String data=en.encrypt(sdata);
          fo.write(data.getBytes());
          fo.close();
      }
     }catch(IOException e){
        e.printStackTrace();
     }
   }
    public void Scan(){
        //scans the pendrive for any virus
    }
}

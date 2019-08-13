/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import com.openbravo.pos.util.AltEncrypter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class DownloadedData {
    private String path;

    public DownloadedData(String path) {
        this.path = path;
    }

    private boolean Authenticate() {
        boolean status=false;
        File f=new File(path+"garuda/garuda.gar");
        if(f.exists()){
            FileReader fin = null;
            try {
                fin = new FileReader(f);
                BufferedReader bin = new BufferedReader(fin);
                String data=bin.readLine();
                AltEncrypter en=new AltEncrypter("cypherkey" + "garuda");
                data=en.decrypt(data);
                String[] arr=data.split(" # ");
                if(arr.length>1){
                   if(arr[1].equals("downloaded"))
                       status=true;
                }
                return status;
            }catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fin.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                 return status;
            }
        }
           return status;
    }

    private void Transfer(){
        
    }
}

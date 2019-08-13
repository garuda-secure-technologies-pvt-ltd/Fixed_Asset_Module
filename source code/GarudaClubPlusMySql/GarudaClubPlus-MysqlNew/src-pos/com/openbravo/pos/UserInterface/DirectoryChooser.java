/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author swathi
 */
public class DirectoryChooser {
     private String directorypath;
     public void show(){
         JFileChooser fchooser=new JFileChooser();
         fchooser.setCurrentDirectory(null);
         /*FileFilter f=new FileFilter() {

            @Override
            public boolean accept(File f) {
               if(f.isDirectory())
                  return true;
               else
                   return false;

            }

            @Override
            public String getDescription() {
               return "Directory only";
            }
          };*/
         // fchooser.setFileFilter(f);
          fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          fchooser.setAcceptAllFileFilterUsed(false);
          if(fchooser.showOpenDialog(new JFrame())==JFileChooser.APPROVE_OPTION){

              if(fchooser.getSelectedFile()==null)
                  directorypath=fchooser.getCurrentDirectory().getAbsolutePath();
              else
                  directorypath=fchooser.getSelectedFile().getAbsolutePath();
        }
     }
     public String getPath(){
         return directorypath;
     }
}

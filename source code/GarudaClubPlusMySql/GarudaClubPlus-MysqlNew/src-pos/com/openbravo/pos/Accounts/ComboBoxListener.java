/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 *
 * @author swathi
 */
public class ComboBoxListener extends  KeyAdapter{

        private SentenceList sent;
        private JTextComponent editor;
        private ComboBoxModel cmodel;
        private JComboBox jcbox;
        public ComboBoxListener(SentenceList sent,JTextComponent editor,ComboBoxModel cmodel,JComboBox jcbox){
             this.sent=sent;
             this.editor=editor;
             this.cmodel=cmodel;
             this.jcbox=jcbox;
        }
        @Override
        public void keyReleased(KeyEvent e) {
             try{
                 //!e.getKeyText(e.getKeyCode()).equals("Enter")
                 String text=editor.getText();
                 if(isAlpha(String.valueOf(e.getKeyChar())) || e.getKeyCode()==KeyEvent.VK_BACK_SPACE){//|| !e.getKeyText(e.getKeyCode()).equals("Backspace")){
                   //cmodel=new ComboBoxValModel(dlfac.getsubAccounts1(text.toUpperCase()));
                     List<AccountMasterExt> l=sent.list(text.toUpperCase()+"%");
                   cmodel=new ComboBoxValModel(l);
                   jcbox.setModel(cmodel);
                   editor.setText(text);
                   jcbox.showPopup();
                 }else if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(cmodel.getSize()<=0){
                       editor.setText(null);
                    }
                 }
             }
             catch(Exception e1){
               e1.printStackTrace();
             }
         }
     private boolean isAlpha(String s){
     s = s.toUpperCase();
     for (int i = 0; i < s.length(); i ++){
       int c = (int) s.charAt(i);
       if ((c < 65 || c > 90) && (c<47 || c>58) )
         return false;
      }
      return true;
    }
}

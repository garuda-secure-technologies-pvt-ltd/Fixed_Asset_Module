/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.pos.UserInterface.DownloadedData;
import com.openbravo.pos.forms.AppView;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class InboxModel {
    private  List<MailData> mlist;
    public static InboxModel LoadData(AppView app) throws BasicException{
        InboxModel imodel=new InboxModel();
        imodel.mlist=new PreparedSentence(app.getSession(), "SELECT _FROM,SUBJECT,CONTENT,DATE,ATTACHLOC FROM MAIL WHERE TOID=?"
                     , SerializerWriteString.INSTANCE, new SerializerReadClass(MailData.class)).list();
        return imodel;
    }
    public AbstractTableModel getTableModel(){
      return new AbstractTableModel() {

            public int getRowCount() {
               return mlist.size();
            }

            public int getColumnCount() {
                return 3;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                MailData d=mlist.get(rowIndex);
                switch(columnIndex){
                    case 1  : return d.getFrom();
                    case 2  : return d.getSubject();
                    case 3  : return d.getdate();
                    case 4  : return d.getBody();
                    default : return null;
                }
            }
        };
    }
}

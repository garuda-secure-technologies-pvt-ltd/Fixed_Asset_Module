/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.DataMangement;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class Archiving {
    private static AppView m_App;
    private static Date todate;
 
     public static void ArchiveQT(Date date,AppView app){
      try{
           m_App=app;
           todate=date;
          //(ID,CUSTOMER,PLACE,WAITER,FLOOR,PRCATEGORY,BILLED,BILLREF,CREATEDBY,CRDATE,REASON)
            Transaction t=new Transaction(app.getSession()) {

            @Override
            protected Object transact() throws BasicException {
               new PreparedSentence(m_App.getSession()
               , "INSERT INTO QTICKET_ARV  SELECT * FROM QTICKET Q WHERE Q.CRDATE < ? "
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[]{todate});
               new PreparedSentence(m_App.getSession()
               , "INSERT INTO QTITEMS_ARV (ID,LINE,PARENTID,PRODUCT,DMULTIPLY,RATE,ATTRIBUTES) SELECT Q.ID,Q.LINE,Q.PARENTID,Q.PRODUCT,Q.DMULTIPLY,Q.RATE,Q.ATTRIBUTES FROM QTITEMS Q,QTICKET QT WHERE Q.PARENTID=QT.ID AND QT.CRDATE < ? "
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[]{todate});
              if((new PreparedSentence(m_App.getSession(),"UPDATE ARCHIVEDTABLES SET LARVDATE = ? WHERE NAME = ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{todate,"QTITEMS"})<=0)){
                   new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO ARCHIVEDTABLES (LARVDATE,NAME) VALUES (?,?) "
                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{todate,"QTITEMS"});
              }
             if((new PreparedSentence(m_App.getSession(),"UPDATE ARCHIVEDTABLES SET LARVDATE = ? WHERE NAME = ? ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{todate,"QTICKET"})<=0)){
                   new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO ARCHIVEDTABLES (LARVDATE,NAME) VALUES (?,?) "
                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})).exec(new Object[]{todate,"QTICKET"});
             }
            
              new PreparedSentence(m_App.getSession()
               , "DELETE FROM QTICKET WHERE QTICKET.CRDATE < ? "
               , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[]{todate});
                 return null;
           }
        };
        t.execute();
       }catch(Exception e){
           MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("Error"), e);
                msg.show(JOptionPane.getRootFrame());
         e.printStackTrace();
       }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class ReprintTableModel {
    private static void loadReceipt(AppView app){
        // public SentenceList getCustomerList() {
        new StaticSentence(app.getSession()
            , new QBFBuilder("SELECT ID,NAME,SEARCHKEY,NDATE,AMT("+
            " SELECT R.ID,R.DATENEW AS NDATE FROM RECEIPTS R JOIN SUM(P.TOTAL) AS TOTAL FROM PAYMENTS P ON P.RECEIPT=R.ID JOIN C.SEARCHKEY,C.NAME FROM CUSTOMERS C ON P.CUSTOMER=C.ID WHERE  ?(QBF_FILTER) ORDER BY  C.SEARCHKEY", new String[] {"TAXID", "SEARCHKEY", "NAME"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadClass(ReprintTableModel.RTableLine.class));
   // }
    }
     public class RTableLine implements SerializableRead {
        private String id;
        private String mname;
        private String mno;
        private Timestamp date;
        private Double amount;
        public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            mname=dr.getString(2);
            mno=dr.getString(3);
            date=dr.getTimestamp(4);
            amount=dr.getDouble(5);
        }
         public String getId(){
           return id;
         }
         public String getMemname(){
           return mname;
         }
         public String getMemno(){
           return mno;
         }
         public Date getDate(){
           return (Date)date;
         }
         public Double getAmount(){
           return amount;
         }
     }

}

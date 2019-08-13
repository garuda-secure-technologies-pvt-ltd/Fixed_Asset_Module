/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataField;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class ReportsData {
    List<TrailBalanceLine> trialbal;
    public static ReportsData lodaInstance(AppView app) throws BasicException{
      ReportsData rd=new ReportsData();
      List trailtemp=new StaticSentence(app.getSession()
              , "SELECT  AM1.NAME ,AM2.NAME AS TONAME,(SELECT SUM(A1.AMOUNT)  FROM ACCOUNTJOURNAL A1 WHERE A1.ACCOUNTID=AM2.ID AND A1.TRANSTYPE='C' GROUP BY AM2.ID) AS CTOTAL,(SELECT SUM(A1.AMOUNT)  FROM ACCOUNTJOURNAL A1 WHERE A1.ACCOUNTID=AM2.ID AND A1.TRANSTYPE='D') AS DTOTAL,CASE WHEN AM1.PARENT IS NULL THEN NULL ELSE (SELECT NAME FROM ACCOUNTMASTER WHERE SEARCHKEY=AM1.PARENT) END "+
                "FROM ACCOUNTMASTER AM1,ACCOUNTMASTER AM2,ACCOUNTJOURNAL A "+
                "WHERE A.ACCOUNTID=AM2.ID AND AM2.PARENT=AM1.SEARCHKEY   "+
                "GROUP BY TONAME,AM1.NAME,PARENT "+
                "ORDER BY PARENT,AM1.NAME,AM2.NAME "
              ,null
              , new SerializerReadClass(ReportsData.TrailBalanceLine.class)).list();
      if(trailtemp!=null){
        rd.trialbal=trailtemp;
      }
      return rd;
    }

    public List<TrailBalanceLine> getTrailBalance(){
       return trialbal;
    }

   public static class TrailBalanceLine implements SerializableRead{
      private String parent;
      private String name;
      private String toname;
     // private String transtype;
      private Double ctotal;
       private Double dtotal;
        public void readValues(DataRead dr) throws BasicException {
           
            name=dr.getString(1);
            toname=dr.getString(2);
            ctotal=dr.getDouble(3);
            dtotal=dr.getDouble(4);
            parent=dr.getString(5);
        }
        public String getParent(){
            return parent;
        }
        public String getname(){
           return name;
        }
        public String gettoname(){
           return toname;
        }
        public Double getctotal(){
            return ctotal;
        }
        public Double getdtotal(){
           return dtotal;
        }
   }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import java.sql.Timestamp;

/**
 *
 * @author swathi
 */
//public class TempClass {
  /*   , "SELECT F.NAME,F.JOINFEE,F.RENEWALFEE,P.TYPE_,P.NO,P.DATE,MF.SDATE,MF.LBILLDATE FROM FACILITY F,MEMFACILITYUSAGE MF,PERIODICITY P WHERE MF.ACTIVE =TRUE AND MF.MEMNO = ? AND MF.FACILITYTYPE=F.ID AND F.RPERIODICITY=P.ID AND TYPE_='Standard' ORDER BY NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.INT,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}));
    }*/

//}
public class TempClass implements SerializableRead,IKeyed {
   private String id;
   private String name;
   private Double jfee;
   private String jacc;
   private String racc;
   private Double rfee;
   private String ptype;
   private int pnum;
   private String pdate;
   private Timestamp sDate;
   private Timestamp lbdate;
   private String period;
 //  private String staxid;
  // private String rperiod;


    public Object getKey() {
       return id;
    }
    @Override
    public String toString(){
      return name;
    }
    public String getid(){
      return id;
    }
    public String getname(){
      return name;
    }
    public Double getjfee(){
      return jfee;
    }
    public Double getrfee(){
     return rfee;
    }
    public String getptype(){
      return ptype;
    }
    public int getpnum(){
      return pnum;
    }
    public Timestamp getlbilldate(){
      return lbdate;
    }
    public Timestamp getsDate(){
     return sDate;
    }
    public String getracc(){
      return racc;//renewal account
    }
    public String getjacc(){
      return jacc;
    }
    public String getperiod(){
     return period;
    }
     public String getPdate(){
        return pdate;
      }
    public void readValues(DataRead dr) throws BasicException {
       id=dr.getString(1);
       name=dr.getString(2);
       jfee=dr.getDouble(3);
       rfee=dr.getDouble(4);
       ptype=dr.getString(5);
       pnum=dr.getInt(6);
       pdate=dr.getString(7);
       sDate=dr.getTimestamp(8);
       lbdate=dr.getTimestamp(9);
       racc=dr.getString(10);
       jacc=dr.getString(11);
       period=dr.getString(12);
      // staxid=dr.getString(12);//
    }

}

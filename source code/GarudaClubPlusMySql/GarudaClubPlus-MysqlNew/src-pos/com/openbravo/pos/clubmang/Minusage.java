package com.openbravo.pos.clubmang; 

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class Minusage implements SerializableRead, SerializableWrite, IKeyed {
     private String ID;
     private String Name;
     private String period;
     private String facilities;
     private double amount;
     private String minusagetype;
     private Date createddate;
     private double charge;
     private boolean active;
     private Date deactivateddate;
     private String deactivatedby;
     private String acchead;
     private String members;
     private String creditperiod;
     private String createdby;
     private String billsequence;
     private String servicetax;
     private String maxdebt;
     private String sms;
     private Date lastbilleddate;
     private Integer maxno;
      private String servicetax2;
       private String servicetax3;
       
    private boolean cascade1;
    private boolean cascade2;
    private boolean basic1;
    private boolean basic2;

 

     public void readValues(DataRead dr) throws BasicException {
         ID = dr.getString(1);
         Name = dr.getString(2);
         period = dr.getString(3);
         facilities = dr.getString(4);
         amount = dr.getDouble(5);
         minusagetype = dr.getString(6);
         createddate = dr.getTimestamp(7);
         charge = dr.getDouble(8);
         acchead = dr.getString(9);
         members = dr.getString(10);
         active = dr.getBoolean(11);
         creditperiod = dr.getString(12);
         createdby = dr.getString(13);
         billsequence = dr.getString(14);
         servicetax = dr.getString(15);
         maxdebt = dr.getString(16);
         sms = dr.getString(17);
         lastbilleddate= dr.getTimestamp(18);
         deactivateddate = dr.getTimestamp(19);
         deactivatedby = dr.getString(20);
         maxno=dr.getInt(21);
           servicetax2 = dr.getString(22);
             servicetax3 = dr.getString(23);
             cascade1= dr.getBoolean(24);
             cascade2= dr.getBoolean(25);
             basic2=dr.getBoolean(26);
             basic2=dr.getBoolean(27);
     
     }
     public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, ID);
        dp.setString(2, Name);
     }

     public void setID(String id){
        ID=id;
     }
     public void setName(String name){
        Name=name;
     }
     public String getid(){
         return ID;
     }
     public String getName(){
        return Name;
     }

     public String getDeactivatedby(){
        return deactivatedby;
     }
     public Date getDeactivateddate(){
        return deactivateddate;
     }
      public Date getLastbilleddate(){
        return lastbilleddate;
     }
     public boolean getActive(){
        return active;
     }
     public String toString(){
        return Name;
     }
     public String getAcchead(){
        return acchead;
     }
     public String getMembers(){
        return members;
     }
     public String getPeriod(){
         return period;
     }
      public String getFacilities(){
         return facilities;
     }
     public double getAmount(){
         return amount;
     }
     public String getMinusagetype(){
         return minusagetype;
     }
     public String getSms(){
         return sms;
     }
      public double getCharge(){
         return charge;
     }
     public Date getCreatedate(){
         return createddate;
     }

    public Object getKey() {
        return ID;
    }
    public String getCreditperiod(){
        return creditperiod;
     }
    public String getCreatedBy(){
        return createdby;
     }
       public String getBillSequence(){
        return billsequence;
     }
       public String getServiceTax(){
        return servicetax;
     }
        public String getMaxDebt(){
        return maxdebt;
     }
        public Integer getMaxNo(){
        return maxno;
     }
        public String getServiceTax2(){
        return servicetax2;
     }
        public String getServiceTax3(){
        return servicetax3;
     }
        public boolean getcascade1(){
        return cascade1;
     } 
        public boolean getcascade2(){
        return cascade2;
     } 
        public boolean getbasic1(){
        return basic1;
     }
        public boolean getbasic2(){
        return basic2;
     }




}

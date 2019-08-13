/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */////

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;

/**
 *
 * @author swathi
 */
public class Facility implements SerializableRead, SerializableWrite, IKeyed {
     private String ID;
     private String Name;
    // private boolean joinfee;
     private Double joinamt;
   //  private boolean renewalfee;
     private Double renewalamt;
     private String rperiod;
  //   private boolean usagefee;
     private Double usageamt;
     private String uperiod;
     private boolean echeck;
     private String dueperiod;
     private String jfeeaccount;
     private String renewalacc;
     private String usageacc;
     private String memtype;
     private Double debtmax;
     private String type;
     private String staxcat;
     private int appto;
       private String TaxCatID;
         private String TaxCatID1;
            private boolean cascade1;
               private boolean cascade2;
               private boolean basic1;
               private boolean basic2;
     
   //  private Boolean billbfusage;//bill before usage

     public void readValues(DataRead dr) throws BasicException {
         ID = dr.getString(1);
         Name = dr.getString(2);
      //   joinfee=dr.getBoolean(3);
         joinamt=dr.getDouble(3);
     //    renewalfee=dr.getBoolean(5);
         renewalamt=dr.getDouble(4);
         rperiod=dr.getString(5);
    //     usagefee=dr.getBoolean(8);
         usageamt=dr.getDouble(6);
         uperiod=dr.getString(7);
         echeck=dr.getBoolean(8);
         dueperiod=dr.getString(9);
         jfeeaccount=dr.getString(10);
         renewalacc=dr.getString(11);
         usageacc=dr.getString(12);
         memtype=dr.getString(13);
         debtmax=dr.getDouble(14);
         type=dr.getString(15);
         staxcat=dr.getString(16);
         appto=dr.getInt(17);
         TaxCatID=dr.getString(18);
         TaxCatID1=dr.getString(19);
         cascade1=dr.getBoolean(20);
         cascade2=dr.getBoolean(21);
          basic1=dr.getBoolean(22);
           basic2=dr.getBoolean(23);
      //   billbfusage=dr.getBoolean(20);
     }
//     public void writeValues(DataWrite dp) throws BasicException {
//        dp.setString(1,ID);
//        dp.setString(2, Name);
//     }
     public String getservicetax(){
      return staxcat;
     }
//      public String getTaxcat_2(){
//      return  TaxCatID;
//     }
//      public String getTaxcat_3(){
//      return  TaxCatID1;
//     }
       
     public void setID(String id){
        ID=id;
     }
     public void setName(String name){
        Name=name;
     }
    // public Boolean getBillBfUsage(){
    //  return billbfusage;
   //  }
     public String getType(){
       return type;
     }
     public String getMemtype(){
      return memtype;
     }
     public Double getdebtMax(){
       return debtmax;
     }
     public String getRenewalacc(){
       return renewalacc;
     }
     public String getUsageacc(){
       return usageacc;
     }
     public String getJoinfeeAccount(){
       return jfeeaccount;
     }
     public String getdueperiod(){
       return dueperiod;
     }
      public Object getKey() {
        return ID;
     }
     public String getid(){
         return ID;
     }
     public String getName(){
        return Name;
     }
 //  public boolean getjfee(){
 //        return joinfee;
 //  }
     public Double getjamt(){
        return joinamt;
    }
 //     public boolean getrfee(){
 //       return renewalfee;
 //    }
     public Double getramt(){
        return renewalamt;
     }
     public String getrperiod(){
        return rperiod;
     }
   //  public boolean getufee(){
   //     return usagefee;
  //   }
     public Double getuamt(){
        return usageamt;
     }
     public String getuperiod(){
        return uperiod;
     }
     public boolean getecheck(){
        return echeck;
     }

     public String toString(){
        return Name;
     }
          public String getTaxcat_2(){
//              System.out.println("Taxcat_2::::::"+ TaxCatID);
              return  TaxCatID;
          }
//     }
          public String getTaxcat_3(){
      return  TaxCatID1;
     }
          public Boolean getCascade1(){
//              System.out.println("cascade1::::::::"+cascade1);
        return cascade1;
    }
//           public Boolean getCascade1() {
////              System.out.println("taxcat_3"+taxcat_3);
//System.out.println("cascade1"+cascade1);
//            return cascade1;
//        }
//          public boolean getCascade2(){
//        return cascade2;
//     }
           public Boolean getCascade2() {
//             System.out.println("cascade2"+cascade2);
            return cascade2;
        }
//            public boolean getBasic1(){
//        return basic1;
//     }
            public Boolean getBasic1() {
//              System.out.println("taxcat_3"+taxcat_3);
//System.out.println("basic1"+basic1);
            return basic1;
        }
//              public boolean getBasic2(){
//        return basic2;
//     }
             public Boolean getBasic2() {
//              System.out.println("taxcat_3"+taxcat_3);
//System.out.println("basic2"+basic2);
            return basic2;
        }

    public void writeValues(DataWrite dp) throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    String getTaxcat_2() {
//
//        return  TaxCatID; //To change body of generated methods, choose Tools | Templates.
//    }
}

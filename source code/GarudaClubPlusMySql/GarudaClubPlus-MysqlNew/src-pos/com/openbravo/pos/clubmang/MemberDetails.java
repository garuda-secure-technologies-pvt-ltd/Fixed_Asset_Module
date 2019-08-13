/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;

import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author swathi
 */
public class MemberDetails implements SerializableRead, SerializableWrite{
    //  private Double dobalance;
    //  private Double cobalance;
      private String name;
      private String memno;
      private String memtype;
      private String address;
      private String contactno;
      private Date doj;
      private String memtaxcat;
      private boolean visible;
      private Date doe;
      private Date dob;

       public void readValues(DataRead dr) throws BasicException
       {
           name=dr.getString(1);
           memno=dr.getString(2);
           memtype=dr.getString(3);
           address=dr.getString(5);
           contactno=dr.getString(6);
           doj=dr.getTimestamp(4);
           memtaxcat=dr.getString(7);
           visible=dr.getBoolean(8);
           doe=dr.getTimestamp(9);
           dob=dr.getTimestamp(10);
       }
       public String getName(){
          return name;
       }
       public String getMemNo(){
          return memno;
       }
       public String getMemType(){
         return memtype;
       }
       public Date getDoj(){
         return doj;
       }
       public Date getDoe(){
        return doe;
       }

       public Date getDob(){
       return dob;
       }
       public String getMemTaxCategory(){
           return memtaxcat;
       }

       public String getContactNo(){
           return contactno;
       }
    //   public Double getDobalance(){
   //      return dobalance;
   //    }
   //    public Double getCobalance(){
    //     return cobalance;
   //    }
       public String getAddress(){
        return address;
       }
       public Boolean getVisible(){
           return visible;
       }



    public void writeValues(DataWrite dp) throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }




     

}

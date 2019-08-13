/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author swathi
 */
public class Periodicity implements SerializableRead, SerializableWrite, IKeyed {
     private String ID;
     private String Name;
     private String type;
     private int no;
     private String date;
     private int fmonth;
     private Boolean btype;
     private Boolean doj;
     private Boolean accurate;
     private String monthname="";
     private String createdby;
     private String months[]=new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
     

     public void readValues(DataRead dr) throws BasicException {
         ID = dr.getString(1);
         Name = dr.getString(2);
         type=dr.getString(3);
         no=dr.getInt(4);
         date=dr.getString(5);
         fmonth=dr.getInt(6);
         btype=dr.getBoolean(7);
         doj=dr.getBoolean(8);
         accurate=dr.getBoolean(9);
         createdby=dr.getString(10);
         if(fmonth!=-1){
           monthname=months[fmonth];
         }
     }
     public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, ID);
        dp.setString(2, Name);
     }
      public Object getKey() {
        return ID;
    }
      public String getcreatedby(){
        return createdby;
      }
     public String getid(){
         return ID;
     }
     public String getName(){
        return Name;
     }
      public String toString(){
        return Name;
    }
      public String getmonthname(){
         return monthname;
      }
      public String getdate(){
         return date;
      }
      public int getno(){
         return no;
      }
      public Boolean getdoj(){
        return doj;
      }
      public Boolean getaccurate(){
       return accurate;
      }
      public int getmonth(){
       return fmonth;
      }
       public Boolean getqbtype(){
       return btype;
      }
      public String gettype(){
        return type;
      }
}

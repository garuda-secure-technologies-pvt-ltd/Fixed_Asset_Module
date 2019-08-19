/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garuda
 */
public class OrderdFacilityBillReport extends BeanFactoryDataSingle{
    
     private Session s;
     private List<OrderdFacilityBillReport.FacilityBillInfo> FacilityBill_List;  

    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    //--payed or not
    //select c.searchkey,a.date,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref from accountjournal a,facility f,customers c where a.memid=c.id and a.transref=f.id and f.name="Lux Tax 1 Year" and a.date='2014-04-12 16:52:43' and a.cleardate is not null order by a.date,c.searchkey,a.cleardate ;
    //--bill till and payed or not
    //select c.searchkey,a.date,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref from accountjournal a,facility f,customers c where a.memid=c.id and a.transref=f.id and f.name="Lux Tax 1 Year" and a.date<='2014-08-08 1:14:11' and a.cleardate is not null order by a.date,c.searchkey,a.cleardate ;

    
    //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_R_M(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

           
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_M_R(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              
          
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_B_M(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_M_B(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_B_R(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_R_B(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_B_R_M(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

      
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_B_M_R(AppView app,Date date,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     
       
                 //----------------------------------bill_during monthly paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_R_M1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,  FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_M_R1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_B_M1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_M_B1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_B_R1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_R_B1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_B_R_M1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });


        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_B_M_R1(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     
                     //----------------------------------bill_during period paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_R_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,  FrmDate , ToDate });


            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_Payed_B_M_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_B_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_R_M_B11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_B_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Payed_M_R_B11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_B_R_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_B_M_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
    
                     //----------------------------------Clear_during monthly paid and not paid-------------------------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Clear_Payed_B_R_M1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,  FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Clear_Payed_B_M_R1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Clear_Payed_R_B_M1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_R_M_B1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R----------------------------------------------------------------------------------------------
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_M_B_R1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_M_R_B1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Clear_B_R_M1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Clear_B_M_R1(AppView app,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by c.searchkey,a.date", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
       
                      //----------------------------------Clear_during period paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Clear_Payed_B_R_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,  FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Clear_Payed_B_M_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Clear_Payed_R_B_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_R_M_B11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_M_B_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Clear_Payed_M_R_B11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Clear_B_R_M11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Clear_B_M_R11(AppView app,Date FrmDate ,Date ToDate,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by c.searchkey,a.date", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
   //----------------------------------bill_during,cleared during monthly,monthly paid and not paid(1587,1890)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_Clear_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_Clear_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Bill_Clear_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
       
                            //----------------------------------bill_during,cleared during period,monthly1 paid and not paid(1587,1890)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate_Bill_Clear1_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate_Bill_Clear1_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport Tilldate_Bill_Clear1_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear1_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear1_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport Tilldate_Bill_Clear1_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and  a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
       
      
 //----------------------------------bill_during,cleared during period,monthly1 paid and not paid(1899,2054)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate1_Bill_Clear_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });


            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate1_Bill_Clear_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate,FrmDate1 , ToDate1 });

              

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport Tilldate1_Bill_Clear_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
     
      
//----------------------------------bill_during,cleared during period,period1 paid and not paid(2061,2210)---------------------------------
     
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype ,FrmDate , ToDate, FrmDate1 , ToDate1 });
 
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport Tilldate1_Bill_Clear1_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1,String facilitytype )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and f.name=? and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ facilitytype , FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
       //--------------------------Facility All code starts here--------------------------
       
         public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_R_M(AppView app,Date date  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ date });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_M_R(AppView app,Date date  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_B_M(AppView app,Date date )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_M_B(AppView app,Date date )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_B_R(AppView app,Date date )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_R_B(AppView app,Date date )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_B_R_M(AppView app,Date date  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{   Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{date });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_B_M_R(AppView app,Date date  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  date });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     
       
                 //----------------------------------bill_during monthly paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_R_M1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_M_R1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_B_M1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

             
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_M_B1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_B_R1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_R_B1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_B_R_M1(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_B_M_R1(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
    //----------------------------------bill_during period paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_R_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{    FrmDate , ToDate });

    
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_B_M_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_B_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_R_M_B11(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_B_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Payed_M_R_B11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_B_R_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_B_M_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{ FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      
 //----------------------------------Clear_during monthly paid and not paid-------------------------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_B_R_M1(AppView app,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{    FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_B_M_R1(AppView app,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_R_B_M1(AppView app,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_R_M_B1(AppView app,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R----------------------------------------------------------------------------------------------
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_M_B_R1(AppView app,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_M_R_B1(AppView app,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Clear_B_R_M1(AppView app,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Clear_B_M_R1(AppView app,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      
 //----------------------------------Clear_during period paid and not paid---------------------------------
       
          //bill till and payed------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_B_R_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{    FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till payed  B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_B_M_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till payed R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_R_B_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till payed R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_R_M_B11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till payed M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_M_B_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till payed M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Clear_Payed_M_R_B11(AppView app,Date FrmDate ,Date ToDate )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=? and a.cleardate is not null \n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Clear_B_R_M11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Clear_B_M_R11(AppView app,Date FrmDate ,Date ToDate  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     
  //----------------------------------bill_during,cleared during monthly,monthly paid and not paid(1587,1890) )---------------------------------
    
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
       
 //----------------------------------bill_during,cleared during period,monthly1 paid and not paid(1587,1890)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate_Bill_Clear1_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and  a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{   Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
       
      
//----------------------------------bill_during,cleared during period,monthly1 paid and not paid(1899,2054)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1 )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
     
      
          //----------------------------------bill_during,cleared during period,period1 paid and not paid(2061,2210)---------------------------------
       
      
       
   //bill till ------------------ B-R-M
     public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_B_R_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.date,a.cleardate,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1 , ToDate1 });

            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     
    //bill till   B-M-R
     
      public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_B_M_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?  \n" +
                                                                            "order by a.date,c.searchkey,a.cleardate ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
     // bill till R_B_M
      
        public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_R_B_M1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,a.date,c.searchkey ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate,FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
      
      // bill till  R_M_B
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_R_M_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by a.cleardate,c.searchkey,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP   }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{   FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
     // bill till  M_B_R
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_M_B_R1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id   and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=? \n" +
                                                                            "order by c.searchkey,a.date,a.cleardate ", new SerializerWriteBasic(new Datas[]{   Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1 , ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }
       
     // bill till  M_R_B
      
       public static OrderdFacilityBillReport All_Tilldate1_Bill_Clear1_M_R_B1(AppView app,Date FrmDate ,Date ToDate,Date FrmDate1 ,Date ToDate1  )throws BasicException{
     OrderdFacilityBillReport Billed_facility= new OrderdFacilityBillReport(); 
         
          try{
            Billed_facility.FacilityBill_List = new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
            

            Billed_facility.FacilityBill_List = new StaticSentence(app.getSession(), "select a.date,c.searchkey,a.transno,a.amount,a.balanceamount,a.cleardate,a.narration,a.paymentref \n" +
                                                                            "from accountjournal a,facility f,customers c\n" +
                                                                            "where a.memid=c.id and a.transref=f.id  and a.date>=? and a.date<=? and a.cleardate>=? and a.cleardate<=?\n" +
                                                                            "order by c.searchkey,a.cleardate,a.date ", new SerializerWriteBasic(new Datas[]{  Datas.TIMESTAMP ,Datas.TIMESTAMP, Datas.TIMESTAMP ,Datas.TIMESTAMP  }) ,new SerializerReadClass(OrderdFacilityBillReport.FacilityBillInfo.class)).list(new Object[]{  FrmDate , ToDate, FrmDate1, ToDate1 });

              
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(OrderdFacilityBillReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_facility;
         
     }       
    
       
     
     public List<OrderdFacilityBillReport.FacilityBillInfo> getfacilityBillList(){
           if(FacilityBill_List!=null)
        {
            return FacilityBill_List;
        }
        else
            return new ArrayList<OrderdFacilityBillReport.FacilityBillInfo>();
      } 

public static class FacilityBillInfo implements SerializableRead,IKeyed
{

         private Date bill_Date;
         private String Searchkey;
         private String trans_NO;
         private Double amount;
         private Double bal_amount;
         private Date clr_Date;
         private String narration;
         private String pay_ref; 

        public Date getBill_Date() {
            return bill_Date;
        }

        public void setBill_Date(Date bill_Date) {
            this.bill_Date = bill_Date;
        }

        public String getSearchkey() {
            return Searchkey;
        }

        public void setSearchkey(String Searchkey) {
            this.Searchkey = Searchkey;
        }

        public String getTrans_NO() {
            return trans_NO;
        }

        public void setTrans_NO(String trans_NO) {
            this.trans_NO = trans_NO;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getBal_amount() {
            return bal_amount;
        }

        public void setBal_amount(Double bal_amount) {
            this.bal_amount = bal_amount;
        }

        public Date getClr_Date() {
            return clr_Date;
        }

        public void setClr_Date(Date clr_Date) {
            this.clr_Date = clr_Date;
        }

        public String getNarration() {
            return narration;
        }

        public void setNarration(String narration) {
            this.narration = narration;
        }

        public String getPay_ref() {
            return pay_ref;
        }

        public void setPay_ref(String pay_ref) {
            this.pay_ref = pay_ref;
        }
          
    
    
    
    
    @Override
        public void readValues(DataRead dr) throws BasicException {
            
        bill_Date=dr.getTimestamp(1);
          Searchkey=dr.getString(2);
          trans_NO=dr.getString(3);
          amount=dr.getDouble(4);
         bal_amount=dr.getDouble(5);
          clr_Date=dr.getTimestamp(6);
          narration=dr.getString(7);
         pay_ref=dr.getString(8); 
    
    }

        @Override
        public Object getKey() {
             return this;
        }
    
}


}
 
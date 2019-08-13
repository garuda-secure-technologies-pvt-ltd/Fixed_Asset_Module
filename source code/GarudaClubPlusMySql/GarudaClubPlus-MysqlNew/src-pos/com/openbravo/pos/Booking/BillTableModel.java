
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadDouble;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class BillTableModel extends BeanFactoryDataSingle{

    private static final String[] Hall_columnHeaders = new String[]{"Booking No.", "Name", "Hall Name", " Booking Date ","Rate" ,  "Amount"};
    private static final String[] Room_columnHeaders = new String[] {"Booking No.", "Name", "Room Type","Booking Date" ,  "No of Rooms","No of Days" , "Rate" ,  "Amount" , };
    
    private Session s;
    private AppView m_App;
    private int bill_item_length;
    private BookedRoomStatusTableModel.Room_StatusInfo rsi;
    private BookedHallStatusTableModel.HallStatusInfo hsi;
    private Double Tot_Amount;
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    
    
    
     public BillTableModel(AppView m_App) {
         this.m_App = m_App;
    }
    
    @Override
    public void init(Session s) {
         this.s=s;
    }

    public void setRsi(BookedRoomStatusTableModel.Room_StatusInfo rsi) {
        this.rsi = rsi;
    }
    
   public void setHsi(BookedHallStatusTableModel.HallStatusInfo hsi) {
        this.hsi = hsi;
    }
    
   
      public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return 1;
            }
          public int getColumnCount() {
                return Room_columnHeaders.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return Room_columnHeaders[column];
            }

          Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class ,  java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class ,  java.lang.Double.class 
            };
          boolean[] canEdit = new boolean[]{
            false, false, false, false, false , false ,false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
            //  BillTableModel.Bill_item_info r =rsi;
              
                
               switch(columnIndex){
                   
                   case 0: return rsi.getBOOKING_SEQ_NO();
                   case 1: return rsi.getMemberName();
                   case 2: return rsi.getROOM_TYPE();
                   case 3: return rsi.getBOOKING_DATE();
                   case 4: return rsi.getNO_OF_ROOMS_BOOKED();
                   case 5: return rsi.getNO_OF_DAYS();
                   case 6: return decimalFormat.format(rsi.getCHARGES());
                   case 7: return decimalFormat.format(getTotAmt_Room(rsi));
                       //return (rsi.getCHARGES()*rsi.getNO_OF_ROOMS_BOOKED() * rsi.getNO_OF_DAYS());
                  
                   
               }
                return null;
            }
          
          
        };
    } 
    
      
      
      public  AbstractTableModel getTableModel2()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return 1;
            }
          public int getColumnCount() {
                return Hall_columnHeaders.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return Hall_columnHeaders[column];
            }

          Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,  java.lang.Double.class ,  java.lang.Double.class 
            };
          boolean[] canEdit = new boolean[]{
            false, false, false, false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
            //  BillTableModel.Bill_item_info r =rsi;
              
                
                 switch(columnIndex){
                   
                   case 0: return hsi.getBOOKING_SEQ_NO();
                   case 1: return hsi.getMemberName();
                   case 2: return hsi.gethall_name();
                   case 3: return hsi.getBOOKING_DATE();
                   case 4: return decimalFormat.format(hsi.getCHARGES());
                   case 5: return decimalFormat.format(getTotAmt_Hall(hsi));
                  
                   
               }
                return null;
            }
          
          
        };
    } 
    
     // RECIEPT SERIES FROM SEQUENCE TABLE
      
        public List getRecieptSeries(AppView app , Date curr_date){
         
         List<Object> payment_list = new ArrayList<Object>();
         
         try {
            payment_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT ID FROM guestroom_booked_details WHERE LAST_PAYMENT_DATE < ? AND PAYMENT_FLAG = 0 ",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), SerializerReadString.INSTANCE).list(new Object[]{curr_date } );
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          return payment_list;
      }
     
      
      
      
      
      
      
    // CALCULATE TOTAL AMOUNT WITH ALL TAXES FOR ROOM .  
    public Double getTotAmt_Room(BookedRoomStatusTableModel.Room_StatusInfo rsi){
        
        
        Double rate = rsi.getCHARGES();
        int No_of_Days = rsi.getNO_OF_DAYS();
        int no_of_Booked_rooms = rsi.getNO_OF_ROOMS_BOOKED();
        
        Double Temp_1 = rate*No_of_Days*no_of_Booked_rooms;                             // TEMP_1 (WITHOUT ANY TAX CONSIST OF BASIC TARIFF )
     
        Double Tax1_Rate = getTax_Rate(m_App , rsi.getLUXURYTAX() );
        Double Tax2_Rate = getTax_Rate(m_App, rsi.getTAX2());         
        Double Tax3_Rate = getTax_Rate(m_App, rsi.getTAX3());
       
       
        Double Temp_2 = Temp_1 + ((Temp_1*Tax1_Rate));                              // TEMP_2 WITH BASIC TAX1 CALCULATED
       
                
        int Tax2_Basic = rsi.getBASIC1();
        int Tax2_Cascade = rsi.getCASCADE1();
        int Tax3_Basic = rsi.getBASIC2();
        int Tax3_Cascade = rsi.getCASCADE2();
        
        Double Temp_3;
        
        if(Tax2_Basic==1){
            Temp_3 = ((Temp_1*Tax2_Rate));                                          // TEMP_3 WITH BASIC  OR CASCADE TAX 2     
            Temp_3 = Temp_2 + Temp_3;
        }
        else{
            Temp_3 = ((Temp_2*Tax2_Rate));
            Temp_3 = Temp_2 + Temp_3;
            
        }
        
        Double Temp_4;
        
        if(Tax3_Basic==1){
            Temp_4 = ((Temp_1*Tax3_Rate));                                          // TEMP_4 WITH BASIC  OR CASCADE TAX 3   
            Temp_4 = Temp_3 + Temp_4;
        }
        else{
            Temp_4 = ((Temp_3*Tax3_Rate));
            Temp_4 = Temp_3 + Temp_4;
        }
        
       Tot_Amount = Temp_4; 
       return Tot_Amount;
    }
    
    
     // CALCULATE TOTAL AMOUNT WITH ALL TAXES FOR HALL .
     public Double getTotAmt_Hall(BookedHallStatusTableModel.HallStatusInfo hsi){
        Double Tot_Amount=0.0;
        
        Double rate = hsi.getCHARGES();
        
        
        Double Temp_1 = rate;                             // TEMP_1 (WITHOUT ANY TAX CONSIST OF BASIC TARIFF )
        
        Double Tax1_Rate = getTax_Rate(m_App , hsi.getLUXURYTAX_N());
        Double Tax2_Rate = getTax_Rate(m_App, hsi.getTAX2_N());
        Double Tax3_Rate = getTax_Rate(m_App, hsi.getTAX3_N());
        
        Double Temp_2 = Temp_1 + ((Temp_1*Tax1_Rate));                              // TEMP_2 WITH BASIC TAX1 CALCULATED
       
                
        int Tax2_Basic = hsi.getBASIC();
        int Tax2_Cascade = hsi.getCASCADE();
        int Tax3_Basic = hsi.getBASIC2();
        int Tax3_Cascade = hsi.getCASCADE2();
        
        Double Temp_3;
        
        if(Tax2_Basic==1){
            Temp_3 = ((Temp_1*Tax2_Rate));                                          // TEMP_3 WITH BASIC  OR CASCADE TAX 2     
            Temp_3 = Temp_2 + Temp_3;
        }
        else{
            Temp_3 = ((Temp_2*Tax2_Rate));
            Temp_3 = Temp_2 + Temp_3;
            
        }
        
        Double Temp_4;
        
        if(Tax3_Basic==1){
            Temp_4 = ((Temp_1*Tax3_Rate));                                          // TEMP_4 WITH BASIC  OR CASCADE TAX 3   
            Temp_4 = Temp_3 + Temp_4;
        }
        else{
            Temp_4 = ((Temp_3*Tax3_Rate));
            Temp_4 = Temp_3 + Temp_4;
        }
       Tot_Amount = Temp_4; 
       
       return Tot_Amount;
    }
    
    
    
    // GET RATE FROM TAXEX TABLE 
    public Double getTax_Rate(AppView app , String tax){
        Double Rate = 0.0;
        Object  o1 =null;
        String TAX_ID = null;
        Object o2 = null;
        
        // GET TAX ID FROM  TAX CATEGARY TABLE 
        ///////////////////////////
       try {
           
         
         o1  = new StaticSentence(app.getSession(), "SELECT ID FROM TAXCATEGORIES WHERE NAME = ? ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(tax);
        
           
        } catch (BasicException ex) {
           Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
     if(o1!=null){
            TAX_ID = o1.toString();
       }
      
       
       // GET TAX RATE FROM TAX TABLE  
       try {
           o2  = new StaticSentence(app.getSession(), "SELECT RATE FROM TAXES WHERE CATEGORY= ? ", SerializerWriteString.INSTANCE, SerializerReadDouble.INSTANCE).find(TAX_ID );
    
           
        } catch (BasicException ex) {
            Logger.getLogger(BillTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(o2!=null){
           Rate = Double.parseDouble(o2.toString());
       } 
       return Rate; 
       
    }
    /////////////////////////
      
}

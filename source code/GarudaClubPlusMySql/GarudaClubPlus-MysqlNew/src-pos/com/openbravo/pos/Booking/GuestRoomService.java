
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class GuestRoomService extends BeanFactoryDataSingle {
    private Session s;
    private int GrLength;
    private static List<GuestRoomService.BillInfo> data;
    private static List<GuestRoomService.BillInfo> data1;
    private final static String[] TABLEHEADERS = {"Bill No" , "Counter" , "Amount" , "Cr. Date"  };
    private final static String[] TABLEHEADERS2 = {"Cr Date" , "Bill No" , "Counter" , "Org. Amount" , "Adjust" , "Amt" , "Bal Amt" };
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    
    
    
    // CUSTOMER ID THROUGH BOOKED ROOM
     public String getCustomerID(AppView app , String s){
         
         Object CustID = new Object();
         
         try {
            CustID  =  new StaticSentence(app.getSession(), "SELECT ID FROM customers WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(s);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         return CustID.toString();
       }  
    
     
     
     
     // CHECK WHETHER CUSTOMER HAVE SHREADTICKETS
      public Object getShreadTickets(AppView app , String CustID){
         
         Object Shd_ID = new Object();
         
         try {
            Shd_ID  =  new StaticSentence(app.getSession(), "SELECT S.ID FROM sharedtickets S WHERE S.NAME = (SELECT C.SEARCHKEY FROM CUSTOMER C WHERE C.ID = ?)", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(CustID);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Shd_ID;
       }  
     
     
     
      //CALCULATE TOTAL BILL AMOUNT THROUGH ROOM NOS.
      
      public Double getTotalBillAmt(AppView app , String CustID){
         
         Object BL_AMT = new Object();
         Double d = 0.00; 
         try {
            BL_AMT  =  new StaticSentence(app.getSession(), "SELECT SUM(AMOUNT)+SUM(TAXTOTAL) FROM bill where customer = (SELECT ID FROM CUSTOMERS WHERE NAME=?) ", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(CustID);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(BL_AMT!=null){
            d = Double.parseDouble(BL_AMT.toString());
            return d;
        }
        else{
            return d;
        }
     }  
      
      
      // GET GUEST ROOM LINKED CUSTOMER NAME
       public String getCust_link_name(AppView app , String link_N ){
         
         Object o = new Object();
         
         try {
            o  =  new StaticSentence(app.getSession(), " SELECT CUSTOMER_N FROM guestroom_link WHERE ROOMNO=?  AND ACTIVE=1", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).find(link_N);
           
         } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(o!=null){
             return o.toString();
         }
         else{
             return null;
         }
       }  
      
    
      
      
       public static GuestRoomService load_Curr_Bills(AppView app , String c , String Custid , String RoomNo , Date CheckInDate) throws BasicException{
        GuestRoomService GuestInfo = new GuestRoomService(); 
         GuestRoomService GuestInfo1 = new GuestRoomService();
          GuestInfo1.data1 = new ArrayList<GuestRoomService.BillInfo>();
         
        
                    try{
                       
                        //commented by pratima
                      /*  GuestInfo.data = new StaticSentence(app.getSession(), "SELECT B.ID , (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE B.WAREHOUSE= L.ID) as warehouse , AMOUNT+TAXTOTAL , CREATEDDATE , 0.00 , 0.00 FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.PAID=0  AND B.CREATEDDATE >= ?      \n" +
                                                                                    "union\n" +
                                                                                    "SELECT G.ID , 'Guest Room' as warehouse , G.RATE+G.TAX_TOTAL-G.DISCOUNT , G.CRDATE , G.AMT_PAID , ((G.RATE+G.TAX_TOTAL)-(G.AMT_PAID)) FROM guestroom_bill G WHERE G.CUSTOMER=? AND ROOM_NO=? AND G.PAID=0",new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING  }), new SerializerReadClass(GuestRoomService.BillInfo.class)).list(new Object[]{c , CheckInDate ,Custid ,RoomNo   });
                       */
                      //added by pratima: In above query discount was not deducted and also old bills were displaying in pending bill table in form
                      GuestInfo.data = new StaticSentence(app.getSession(), "SELECT B.ID , (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE B.WAREHOUSE= L.ID) as warehouse , AMOUNT+TAXTOTAL , CREATEDDATE , 0.00 , 0.00 FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.PAID=0  AND B.CREATEDDATE >= ?      \n" +
                                                                                    "union\n" +
                                                                                    "SELECT G.ID , 'Guest Room' as warehouse , G.RATE+G.TAX_TOTAL-G.DISCOUNT , G.CRDATE , G.AMT_PAID , ((G.RATE+G.TAX_TOTAL-G.DISCOUNT)-(G.AMT_PAID)) FROM guestroom_bill G WHERE G.CUSTOMER=? AND ROOM_NO=? AND G.PAID=0 AND G.CHK_IN>=?",new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING,Datas.TIMESTAMP   }), new SerializerReadClass(GuestRoomService.BillInfo.class)).list(new Object[]{c , CheckInDate ,Custid ,RoomNo,CheckInDate   });
                       //ended by pratima
                      GuestInfo.GrLength = GuestInfo.data.size();
                        
                        }
                         catch(BasicException ex){
                             Logger.getLogger(GuestRoomService.class.getName()).log(Level.SEVERE, null, ex);
                       } 
              
       return GuestInfo;
     }
       
      
     // LOAD FOR ALREADY ADJUSTED AMOUNT ---------------------------------------------------------------------------------  
       
      public static GuestRoomService Load_Adjusted_Bills(AppView app , List AdvRecieptList , String link_N) throws BasicException{
        GuestRoomService GuestInfo = new GuestRoomService(); 
         GuestRoomService GuestInfo1 = new GuestRoomService();
          GuestInfo1.data1 = new ArrayList<GuestRoomService.BillInfo>();
         
           for(int i=0;i<AdvRecieptList.size();i++){
             String RecieptNo =  AdvRecieptList.get(i).toString();
              List<GuestRoomService.BillInfo> dataTemp = new ArrayList<BillInfo>();
               try{
                        dataTemp = new StaticSentence(app.getSession(), "SELECT B.ID , (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE B.WAREHOUSE= L.ID) , AMOUNT+TAXTOTAL , CREATEDDATE FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.RECEIPT=? AND B.PAID=1  ",new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING  }), new SerializerReadClass(GuestRoomService.BillInfo.class)).list(new Object[]{ link_N , RecieptNo  });
                        GuestInfo.GrLength = GuestInfo.data.size();
                        
                  }
               catch(BasicException ex){
                        Logger.getLogger(GuestRoomService.class.getName()).log(Level.SEVERE, null, ex);
                 } 
               GuestInfo.data.addAll(dataTemp);
               
           }
           
       return GuestInfo;
     }  
       
  // --------------------------------------------------------------------------------------------------------------------------------
    
   // LOAD TOTAL OF BILLED AMT ADJUSTED ----------------------------------------------------------------------------
      
       public Double getTotalRmServAmt( AppView app , List AdvRecieptList , String link_N ){
       Double  TotalRmServAmt=0.00; 
       Object obj = new Object();
        
       for(int i=0;i<AdvRecieptList.size();i++){
           String RecieptNo =  AdvRecieptList.get(i).toString();
           Double Tot_temp=0.00; 
           try {
                 obj  =  new StaticSentence(app.getSession(), "SELECT  SUM(AMOUNT+TAXTOTAL)  FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.RECEIPT=? AND B.PAID=1  ",new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING  }), SerializerReadString.INSTANCE).find(new Object[]{link_N , RecieptNo  } );


             } catch (BasicException ex) {
                 Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
             }
          
           if(obj!=null){
              Tot_temp = Double.parseDouble(obj.toString());
               
           }
            
          TotalRmServAmt = TotalRmServAmt +   Tot_temp;
       }
         
       return   TotalRmServAmt;
         
     }
     
    // - load all reciept numbers for advance recieved ---------------------------------------------------------------------
       
       
        public List getAdvanceRecieved_RecieptList(AppView app , String BookingId){
         
         List<Object> RecieptNo = new ArrayList<Object>();
         
         try {
            RecieptNo  = (List<Object>) new StaticSentence(app.getSession(), "SELECT RECIEPT_NO FROM advnce_agnst_guestroom WHERE booking_id=?", SerializerWriteString.INSTANCE, SerializerReadString.INSTANCE).list(BookingId);
           
         
        } catch (BasicException ex) {
            Logger.getLogger(BookedHallStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         return RecieptNo;
       }
       
       
       
    //------------------------------------------------------------------------------------------------------------------------
      
    public List<GuestRoomService.BillInfo> getBillInfo_Path()
    {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<GuestRoomService.BillInfo>();
    } 
      
      
       public static class BillInfo implements SerializableRead,IKeyed {
         
           String ID;
           String PLACE;
           Double AMOUNT;
           Boolean status=false;
           Double AdjAmt;
           Double BalAmt;
           Double roomCharges;
           Date CRDATE;
           
           
           public String getID(){
               return ID;
           }
           public void setID(String ID){
               this.ID = ID;
           }
           public String getPLACE(){
               return PLACE;
           }
           public void setPLACE(String PLACE){
               this.PLACE = PLACE;
           }
           public Double getAMOUNT(){
               return AMOUNT;
           }
           public void setAMOUNT(Double AMOUNT){
               this.AMOUNT = AMOUNT;
           }
           
            public Boolean  getStatus(){
               return status;
           }
           public void setstatus(Boolean status){
               this.status = status;
           }
           
            public Double getAdjAmt(){
               return AdjAmt;
           }
           public void setAdjAmt(Double AdjAmt){
               this.AdjAmt = AdjAmt;
           }
           
            public Double getBalAmt(){
               return BalAmt;
           }
           public void setBalAmt(Double BalAmt){
               this.BalAmt = BalAmt;
           }
           
            public Double getTotal() {
            return roomCharges;
           }
           
           public String getCRDATE(){
               String d = Formats.TIMESTAMP.formatValue(CRDATE);
               return d;
           }
           public void setCRDATE(Date CRDATE){
               this.CRDATE = CRDATE;
           }
            
            
        public void readValues(DataRead dr) throws BasicException {
            ID = dr.getString(1);
            PLACE = dr.getString(2);
            AMOUNT = dr.getDouble(3);
            CRDATE = dr.getTimestamp(4);
            AdjAmt = 0.00;
            BalAmt = dr.getDouble(6);
        }

        
        
        public Object getKey() {
           return this;
        }
           
       }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    //LOADING DATA FOR DISPLAYING TABLE
      public  AbstractTableModel getTableModel()
      {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class , java.lang.String.class, java.lang.Double.class, 
            };
          boolean[] canEdit = new boolean[]{
                false, false, false
            };
           @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
           
           public Object getValueAt(int rowIndex, int columnIndex) {
              GuestRoomService.BillInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getID();
                   case 1: return r.getPLACE();
                   case 2: return r.getAMOUNT();
                   case 3: return r.getCRDATE();
                   case 5: return r.getAdjAmt();
               }
                return null;
            }
          };
        } 
    
     //LOADING DATA FOR DISPLAYING TABLE WITH BOOLEAN VALUES
     
        public abstract class MyAbstractTableModel extends AbstractTableModel {

        protected JTextField roomCharges;
        protected JTextField totalAmtAdjusted;
         protected JTextField  unadjustTotal_text;
        public void settext(JTextField text ,JTextField text2  ,JTextField text3 ) {
            roomCharges = text;
            totalAmtAdjusted = text2;
            unadjustTotal_text = text3;
          }
       }
      
    

      
      
       public MyAbstractTableModel getTableModel2() {
        return new MyAbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(HEADERS[column]);
                return (TABLEHEADERS2[column]);
            }

            @Override
            public void settext(JTextField text ,JTextField text2 ,JTextField text3 ) {
                roomCharges = text;
                totalAmtAdjusted = text2;
                unadjustTotal_text = text3;
            }
            public Double unadjusted_total = 0.00;
            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {

                return TABLEHEADERS2.length;
            }
            Class[] types = new Class[]{
                 java.lang.String.class , java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class, java.lang.Double.class 
            };
            boolean[] canEdit = new boolean[]{
               false ,  false, false, false, true, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
               
                BillInfo r = data.get(row);
                
                if(column==4){
                    
                    Double Oamt = r.getAMOUNT();
                   
                    Double totServamt = 0.00;
                    Double TotAdjstAmt = 0.00;
                    Boolean status = Boolean.parseBoolean(aValue.toString());
                    
                    if (status) {
                        r.setAdjAmt(r.getBalAmt());
                        r.setBalAmt(0.00);
                        
                        //totServamt = Double.parseDouble(roomCharges.getText())+Oamt;
                        TotAdjstAmt = Double.parseDouble(totalAmtAdjusted.getText())+Oamt;
                        r.setstatus(status);
                        for(BillInfo r1 :data){
                            if(r1.getStatus()){
                              totServamt = totServamt+r1.getAdjAmt();
                            }
                        }
                        
                    } else {
                        //Oamt1 = 0.00;
                        r.setBalAmt(r.getAdjAmt());
                        r.setAdjAmt(0.00);
                        
                       
                        
                        //totServamt = Double.parseDouble(roomCharges.getText())-Oamt;
                        TotAdjstAmt = Double.parseDouble(totalAmtAdjusted.getText())-Oamt;
                        r.setstatus(status);
                         for(BillInfo r1 :data){
                            if(r1.getStatus()){
                              totServamt = totServamt+r1.getAdjAmt();
                            }
                        }
                        
                    }
                    
                    roomCharges.setText(decimalFormat.format(((totServamt))));
                    //totalAmtAdjusted.setText(decimalFormat.format(((TotAdjstAmt))));
                   
                    fireTableDataChanged();
                    
                    
                }
                
                if(column==5){
                    
                    Double Old_amt = r.getAdjAmt();
                    Double New_Adjst_Amt = Double.parseDouble(aValue.toString());
                    Double OrgAmt = r.getAMOUNT();
                     Double totServamt = 0.00;
                    if(New_Adjst_Amt<OrgAmt){
                        r.setAdjAmt(New_Adjst_Amt);
                        Double total_adjst_amt = New_Adjst_Amt-Old_amt;
                         for(BillInfo r1 :data){
                            if(r1.getStatus()){
                              totServamt = totServamt+r1.getAMOUNT();
                            }
                        }
                       // Double totServamt = Double.parseDouble(roomCharges.getText())+total_adjst_amt;
                        Double TotAdjstAmt = Double.parseDouble(totalAmtAdjusted.getText())+total_adjst_amt;

                        roomCharges.setText(decimalFormat.format(((totServamt))));
                       // totalAmtAdjusted.setText(decimalFormat.format(((TotAdjstAmt))));
                        fireTableDataChanged();
                    }
                    
                    
                  
                    
                    
                }
                
                
                
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                GuestRoomService.BillInfo r =data.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0:return r.getCRDATE();
                        case 1: return r.getID();
                        case 2: return r.getPLACE();
                        case 3: return r.getAMOUNT();
                        case 4: return r.getStatus();
                        case 5:return r.getAdjAmt();
                        case 6:return r.getBalAmt();
                        
                            
                      }
                     return null;
                }
           };
        }
    
   }
            



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


public class HallService  extends BeanFactoryDataSingle{
    
   private Session s;
    private int GrLength;
    private static List<HallService.BillInfo> data;
    private static List<HallService.BillInfo> data1;
    private final static String[] TABLEHEADERS = {"Bill No" , "Counter" , "Amount"  };
    private final static String[] TABLEHEADERS2 = {"Cr Date" , "Bill No" , "Counter" , "Org. Amount" , "Adjust" , "Amt" , "Bal Amt" };
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
      
    
    //public static HallService load_Curr_Bills(AppView app , String c , String CustID , String HallName) throws BasicException{//commented by pratima
      public static HallService load_Curr_Bills(AppView app , String c , String CustID , String HallName,String Check_in_id ) throws BasicException{
        HallService Hallinfo = new HallService(); 
         HallService GuestInfo1 = new HallService();
          GuestInfo1.data1 = new ArrayList<HallService.BillInfo>();
         
        
                    try{
                       
                        //if same member has two booking of same hall than below query were displaying both bills in pending bill table.It is corrected by taking bills corresponding to checkinid of each booking 
//                        Hallinfo.data = new StaticSentence(app.getSession(), "SELECT B.ID , (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE B.WAREHOUSE= L.ID) as warehouse , AMOUNT+TAXTOTAL , CREATEDDATE , AMOUNT+TAXTOTAL AS BLAMT FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.PAID=0\n" +
//                                                                              "union\n" +
//                                                                              "SELECT G.ID , 'HALL' as warehouse , G.RATE+G.TAX_TOTAL-G.DISCOUNT , G.CRDATE , IFNULL(G.BALANCEAMOUNT,0.00)  AS BLAMT FROM hall_bill G WHERE G.CUSTOMER=? AND HALL_NAME=? AND G.PAID=0", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING }) , new SerializerReadClass(HallService.BillInfo.class)).list(new Object[]{c ,CustID ,HallName  });//commented by pratima
//                        Hallinfo.GrLength = Hallinfo.data.size();
                         Hallinfo.data = new StaticSentence(app.getSession(), "SELECT B.ID , (SELECT L.RDISPLAYNAME   FROM LOCATIONS l WHERE B.WAREHOUSE= L.ID) as warehouse , AMOUNT+TAXTOTAL , CREATEDDATE , AMOUNT+TAXTOTAL AS BLAMT FROM BILL B WHERE CUSTOMER in (SELECT C.ID FROM CUSTOMERS C WHERE  C.NAME =? ) AND B.PAID=0\n" +
                                                                              "union\n" +
                                                                              "SELECT G.ID , 'HALL' as warehouse , G.RATE+G.TAX_TOTAL-G.DISCOUNT , G.CRDATE , IFNULL(G.BALANCEAMOUNT,0.00)  AS BLAMT FROM hall_bill G WHERE G.CUSTOMER=? AND HALL_NAME=? AND G.PAID=0 and CHECK_IN_ID=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.STRING , Datas.STRING, Datas.STRING }) , new SerializerReadClass(HallService.BillInfo.class)).list(new Object[]{c ,CustID ,HallName,Check_in_id  });
                       
                        
                        }
                         catch(BasicException ex){
                             Logger.getLogger(GuestRoomService.class.getName()).log(Level.SEVERE, null, ex);
                       } 
              
       return Hallinfo;
     }
    
    
      
     //----------------------------- GET CUSTOMER LINK NAME 
      
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
      
    
      
      
      
      public List<HallService.BillInfo> getBillInfo_Path()
    {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<HallService.BillInfo>();
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
            BalAmt = dr.getDouble(5);
            
        }

        
        
        public Object getKey() {
           return this;
        }
           
       }
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
      
      
      
      
      
      
       public abstract class MyAbstractTableModel extends AbstractTableModel {

        protected JTextField roomCharges;
        protected JTextField totalAmtAdjusted;
         protected JTextField  unadjustTotal_text;
        public void settext(JTextField text ,JTextField text2 , JTextField text3  ) {
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
            public void settext(JTextField text ,JTextField text2 , JTextField text3) {
                roomCharges = text;
                totalAmtAdjusted = text2;
                 unadjustTotal_text = text3;
            }

            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {

                return TABLEHEADERS2.length;
            }
            Class[] types = new Class[]{
                 java.lang.String.class , java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class 
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
               
                HallService.BillInfo r = data.get(row);
                
                if(column==4){
                     Double Oamt = r.getAMOUNT();
                     Double BalanceAmt = r.getBalAmt();
                    Double totServamt = Double.parseDouble(roomCharges.getText());
                    Double TotAdjstAmt = 0.00;
                    Boolean status = Boolean.parseBoolean(aValue.toString());
                    
                    if (status) {
                        r.setAdjAmt(BalanceAmt);
                        r.setBalAmt(0.00);
                       // totServamt = Double.parseDouble(roomCharges.getText())+Oamt;
                        //TotAdjstAmt = Double.parseDouble(totalAmtAdjusted.getText())+Oamt;
                        totServamt = BalanceAmt;
                        TotAdjstAmt = BalanceAmt;
                        roomCharges.setText(decimalFormat.format(BalanceAmt));
                        r.setstatus(status);
                    } else {
                        //Oamt1 = 0.00;
                        r.setAdjAmt(0.00);
                        r.setBalAmt(totServamt);
                        totServamt = totServamt;
                        TotAdjstAmt = totServamt;
                        roomCharges.setText("0.00");
                        r.setstatus(status);
                        
                    }
                    
                    //roomCharges.setText(decimalFormat.format(((totServamt))));
                   // totalAmtAdjusted.setText(decimalFormat.format(((TotAdjstAmt))));
                   
                    fireTableDataChanged();
                   
                    
                }
                
                if(column==5){
                       Double Old_amt = r.getAdjAmt();
                      Double New_Adjst_Amt = Double.parseDouble(aValue.toString());
                      Double OrgAmt = r.getAMOUNT();
                      if(New_Adjst_Amt<OrgAmt){
                        r.setAdjAmt(New_Adjst_Amt);
                        Double total_adjst_amt = New_Adjst_Amt-Old_amt;

                        Double totServamt = Double.parseDouble(roomCharges.getText())+total_adjst_amt;
                        Double TotAdjstAmt = Double.parseDouble(totalAmtAdjusted.getText())+total_adjst_amt;

                        roomCharges.setText(decimalFormat.format(((totServamt))));
                      //  totalAmtAdjusted.setText(decimalFormat.format(((TotAdjstAmt))));
                        fireTableDataChanged();
                      }
                    
                }
                
                
                
            }  
      
              public Object getValueAt(int rowIndex, int columnIndex) {
                HallService.BillInfo r =data.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0:return r.getCRDATE();
                        case 1: return r.getID();
                        case 2: return r.getPLACE();
                        case 3: return decimalFormat.format(r.getAMOUNT());
                        case 4: return r.getStatus();
                        case 5:return decimalFormat.format(r.getAdjAmt());
                        case 6:return decimalFormat.format(r.getBalAmt());
                        
                            
                      }
                     return null;
                }
           };
        }
    
   }
            
            
     
                
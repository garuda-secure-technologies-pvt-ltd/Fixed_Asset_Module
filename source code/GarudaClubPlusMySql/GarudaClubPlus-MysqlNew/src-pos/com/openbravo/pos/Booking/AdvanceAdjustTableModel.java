
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
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


public class AdvanceAdjustTableModel  extends BeanFactoryDataSingle{
    private Session s;
    private int GrLength;
    private static List<AdvanceAdjustTableModel.AdvanceInfo> data;
    private final static String[] TABLEHEADERS2 = {"Cr Date" , "Receipt No" , "Org. Amt" , "Adjust" , "Adj. Amt" , "Bal. Amt" };
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    // LOAD ALL ADVANCES ..... ....................................................................................# AAKASH
    
     public static AdvanceAdjustTableModel load_Curr_Bills(AppView app  , String BookingId) throws BasicException{
        AdvanceAdjustTableModel AdvanceInfo = new AdvanceAdjustTableModel(); 
           AdvanceInfo.data = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
              try{
                        AdvanceInfo.data = new StaticSentence(app.getSession(), "  SELECT RECIEPT_NO , ADVNCE_AMT , CRDATE , CRBY , BAL_AMT FROM advnce_agnst_guestroom\n" +
                                                                                    "WHERE BOOKING_ID= ?  ", new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), new SerializerReadClass(AdvanceAdjustTableModel.AdvanceInfo.class)).list(new Object[]{ BookingId  });
                        AdvanceInfo.GrLength = AdvanceInfo.data.size();
                 }
                 catch(BasicException ex){
                      ex.printStackTrace();
                     
                     
                     Logger.getLogger(AdvanceAdjustTableModel.class.getName()).log(Level.SEVERE, null, ex);
                     } 
              
       return AdvanceInfo;
     }
    
    
     
     
     // ---------------------------------------------------------------- LOAD ALL ADVANCES FOR HALL BOOKED  ............ # AAKASH
     
      public static AdvanceAdjustTableModel load_AdvanceList_Hall(AppView app  , String BookingId) throws BasicException{
        AdvanceAdjustTableModel AdvanceInfo = new AdvanceAdjustTableModel(); 
           AdvanceInfo.data = new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
              try{
                        AdvanceInfo.data = new StaticSentence(app.getSession(), "  SELECT RECIEPT_NO , ADVNCE_AMT , CRDATE , CRBY , BAL_AMT FROM advnce_agnst_hall\n" +
                                                                                    "WHERE BOOKING_ID= ?  ", new SerializerWriteBasic(new Datas[]{ Datas.STRING   }), new SerializerReadClass(AdvanceAdjustTableModel.AdvanceInfo.class)).list(new Object[]{ BookingId  });
                        AdvanceInfo.GrLength = AdvanceInfo.data.size();
                 }
                 catch(BasicException ex){
                        Logger.getLogger(AdvanceAdjustTableModel.class.getName()).log(Level.SEVERE, null, ex);
                     } 
              
       return AdvanceInfo;
     }
     
     
     
    
     public List<AdvanceAdjustTableModel.AdvanceInfo> getAdvanceList()
      {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<AdvanceAdjustTableModel.AdvanceInfo>();
      } 
     
     
     
     
     
     
     
     public static class AdvanceInfo implements SerializableRead,IKeyed {
         
           String RecieptNo;
           Double AdvAmt;
           Date CRDATE;
           String CrBy;
           Double AdjAmt=0.00;
           Double BalAmt;
           Double roomCharges;
           Boolean status=false;
           
           
           public String getRecieptNo(){
               return RecieptNo;
           }
           public void setRecieptNo(String RecieptNo){
               this.RecieptNo = RecieptNo;
           }
          
           public Double getAdvAmt(){
               return AdvAmt;
           }
           public void setAdvAmt(Double AdvAmt){
               this.AdvAmt = AdvAmt;
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
            RecieptNo = dr.getString(1);
            AdvAmt = dr.getDouble(2);
            CRDATE = dr.getTimestamp(3);
            CrBy = dr.getString(4);
            BalAmt = dr.getDouble(5);
            
        }

        
        
        public Object getKey() {
           return this;
        }
           
    }
    
     
     
     /// METHOS TO DISPLAY TABLE ABSTRACT  MODEL .---------------------------------------------------------------------------------# AAKASH
     
     
     
      public abstract class MyAbstractTableModel2 extends AbstractTableModel {

        protected JTextField totalAmtAdjusted;
        protected JTextField Tot_Bal_amt_text;
        protected JTextField roomCharges;
        public void settext(JTextField text , JTextField text2 ,  JTextField text3) {
            totalAmtAdjusted = text;
            Tot_Bal_amt_text = text2;
            roomCharges = text3;
          }
       }
      
     
      
      
      public MyAbstractTableModel2 getTableModel2() {
        return new MyAbstractTableModel2() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(HEADERS[column]);
                return (TABLEHEADERS2[column]);
            }

            @Override
            public void settext(JTextField text , JTextField text2 , JTextField text3 ) {
                totalAmtAdjusted = text;
                Tot_Bal_amt_text = text2;
                roomCharges = text3;
            }

            public int getRowCount() {
                return data.size();
            }

            public int getColumnCount() {

                return TABLEHEADERS2.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class , java.lang.Double.class 
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, true, false , false
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
               
             /*   AdvanceInfo r = data.get(row);
                
                if(column==3){
                    
                    double AdvAmt = r.getAdvAmt();
                    double Oamt1 = r.getAdvAmt();
                    Double TatUnAdjustedAmt = 0.00;
                    Double TotBalanceAmt = 0.00;
                    Double AdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText());
                    Double RoomCharge = Double.parseDouble(roomCharges.getText());
                    for (AdvanceInfo r1 : data) {
                          r1.setstatus(false);
                          
                    }
                    
                    boolean status = Boolean.parseBoolean(aValue.toString());
                    Double BalAmt=0.00;
                    BalAmt = r.getBalAmt();
                    if (status) {
                        //if(AdvAmt<AdjustedAmt)
                      //  {
                            
                         //TatUnAdjustedAmt = (r.getBalAmt());
                           if(RoomCharge>BalAmt){
                               r.setAdjAmt(BalAmt);
                               r.setBalAmt(BalAmt-BalAmt);
                               TatUnAdjustedAmt = (BalAmt);
                            }
                            else{
                               r.setAdjAmt(RoomCharge);
                               r.setBalAmt(BalAmt-RoomCharge); 
                               TatUnAdjustedAmt = (RoomCharge);
                            }
                            
                            TotBalanceAmt = Double.parseDouble(Tot_Bal_amt_text.getText());
                            TotBalanceAmt = TotBalanceAmt - (r.getAdjAmt());
                           // TatUnAdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText())-AdvAmt;
                           
                            r.setstatus(status);  
                        // }
                       // else{
                         //   r.setAdjAmt(AdjustedAmt);
                          //  BalAmt = r.getBalAmt();
                          //  r.setBalAmt(BalAmt-AdjustedAmt);
                          //  TotBalanceAmt =  Double.parseDouble(Tot_Bal_amt_text.getText()) -  (AdjustedAmt);
                            //TatUnAdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText())-AdjustedAmt;
                            
                          //  TatUnAdjustedAmt = 0.00;
                          //  r.setstatus(status);  
                            
                       // }
                        
                   
                    
                    } else {
                        Oamt1 = 0.00;
                        
                       // TatUnAdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText())+r.getAdjAmt();
                        TatUnAdjustedAmt = 0.00;
                        TotBalanceAmt = Double.parseDouble(Tot_Bal_amt_text.getText());
                        TotBalanceAmt =  (TotBalanceAmt + (r.getAdjAmt()));
                        r.setBalAmt(r.getBalAmt()+r.getAdjAmt());
                        r.setAdjAmt(0.00);
                        
                        r.setstatus(status);
                    }
                    
                    totalAmtAdjusted.setText(decimalFormat.format(((TatUnAdjustedAmt))));
                  //  Tot_Bal_amt_text.setText(decimalFormat.format(((TotBalanceAmt))));
                    fireTableDataChanged();
                    
                    
                }*/
             //commented by pratima 
             //added by pratima :to adjustbill in case of multiple receipts
             AdvanceInfo r = data.get(row);
                
                if(column==3){
                 
                    Double AdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText());
                    Double RoomCharge = Double.parseDouble(roomCharges.getText());
                    
                    if(RoomCharge>AdjustedAmt){
                
                    boolean status = Boolean.parseBoolean(aValue.toString());
                    Double BalAmt=0.00;
                    BalAmt = r.getBalAmt();
                  
                    if (status) {
                        
                           if((AdjustedAmt+BalAmt)<RoomCharge){
                               r.setAdjAmt(BalAmt);
                               r.setBalAmt(BalAmt-BalAmt);
                               AdjustedAmt=AdjustedAmt+BalAmt;
                            }
                            else{
                               r.setAdjAmt(RoomCharge-AdjustedAmt);
                               r.setBalAmt((AdjustedAmt+BalAmt)-RoomCharge); 
                              AdjustedAmt=RoomCharge;
                            }
                            
                           
                            r.setstatus(status);  
                     } else {
                       
                       AdjustedAmt = Double.parseDouble(totalAmtAdjusted.getText());
                       AdjustedAmt =  (AdjustedAmt - (r.getAdjAmt()));
                        r.setBalAmt(r.getBalAmt()+r.getAdjAmt());
                        r.setAdjAmt(0.00);
                        
                        r.setstatus(status);
                    }
                   
                    totalAmtAdjusted.setText(decimalFormat.format(AdjustedAmt));
                    fireTableDataChanged();
                    
                    
                } }
                  //ended by pratima  
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                AdvanceAdjustTableModel.AdvanceInfo r =data.get(rowIndex);
              
                
                    switch(columnIndex){

                        case 0: return r.getCRDATE();
                        case 1: return r.getRecieptNo();
                        case 2: return (r.getAdvAmt());
                        case 3: return r.getStatus();
                        case 4: return (r.getAdjAmt());
                        case 5: return (r.getBalAmt());
                   
                            
                      }
                     return null;
                }
           };
        }
      
      
     
     
     
    
}

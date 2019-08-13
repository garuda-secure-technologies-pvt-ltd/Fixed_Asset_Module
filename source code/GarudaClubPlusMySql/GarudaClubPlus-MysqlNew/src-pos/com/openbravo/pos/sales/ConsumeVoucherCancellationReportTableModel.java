/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
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
import com.openbravo.pos.panels.ConsumableBillReprintTableModel;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class ConsumeVoucherCancellationReportTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<ConsumeVoucherCancellationReportTableModel.BillInfo> data;
    private int size;
    private final static String[] RoomHeader = {"MIV No" , "Amount" , "Cancelled By" , "Cancelled Date" ,"Department", "Created Date"  , "Created By" };    
    
    
    
     @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    public static ConsumeVoucherCancellationReportTableModel LoadCancelVoucherList(AppView app , Date From_date , Date To_date) throws BasicException{
        ConsumeVoucherCancellationReportTableModel GuestInfo = new ConsumeVoucherCancellationReportTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<ConsumeVoucherCancellationReportTableModel.BillInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select r.billid , r.amount , r.cancelledby , r.cancelleddate , r.CREATEDDATE , p.name as createdby,\n" +
                                                                    "d.name as department\n" +
                                                                    "from consumereversedbill r  , people p , cpbill b , department d\n" +
                                                                    "where p.id=r.createdby and b.id=r.billid and d.id=b.deptid  and r.createddate>=? and r.createddate<=?    \n" +
                                                                    "union \n" +
                                                                    "select r.billid , r.amount , r.cancelledby , r.cancelleddate , r.CREATEDDATE , p.name as createdby,\n" +
                                                                    "d.name as department\n" +
                                                                    "from consumereversedbill r  , people p , cpbill_arv b , department d\n" +
                                                                    "where p.id=r.createdby and b.id=r.billid and d.id=b.deptid    and r.createddate>=? and r.createddate<=?    \n" +
                                                                    "order by createddate", 
                                                                    new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.TIMESTAMP , Datas.TIMESTAMP   }), new SerializerReadClass(ConsumeVoucherCancellationReportTableModel.BillInfo.class)).list(new Object[]{ From_date , To_date , From_date , To_date });
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumeVoucherCancellationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
     public List<ConsumeVoucherCancellationReportTableModel.BillInfo> getCancelVoucherList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<ConsumeVoucherCancellationReportTableModel.BillInfo>();
      }
    
    
     public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return RoomHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return RoomHeader[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false ,  false, false , false ,  false, false , false ,  false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              ConsumeVoucherCancellationReportTableModel.BillInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillID();
                   case 1: return r.getAmount();    
                   case 2: return r.getCancelledBy();
                   case 3: return r.getCancelledDate();
                   case 4: return r.getDepartment();     
                   case 5: return r.getCreatedDate();
                   case 6: return r.getCreatedBy();     
                 }
                return null;
            }
          
          
          };
        }  
      
    
    
    
     public static class BillInfo implements SerializableRead,IKeyed {

        private String BillID;
        private Double Amount;
        private String CancelledBy;
        private Date CancelledDate;
        private Date CreatedDate;
        private String CreatedBy;
        private String Department ;
         
         public String getBillID() {
            return BillID;
        }
        public void setBillID(String BillID){
            this.BillID=BillID;
        }
        public String getCancelledBy() {
            return CancelledBy;
        }
        public void setCancelledBy(String CancelledBy){
            this.CancelledBy=CancelledBy;
        }
        
        
        
        public Double getAmount() {
            return Amount;
        }
        public void setAmount(Double Amount){
            this.Amount=Amount;
        }

        public String  getCancelledDate() {
            String x = Formats.TIMESTAMP.formatValue(CancelledDate) ;
             return x;
        }
        public void setCancelledDate(Date CancelledDate){
            this.CancelledDate=CancelledDate;
        }

        

        public String  getCreatedDate() {
            String x = Formats.TIMESTAMP.formatValue(CreatedDate) ;
             return x;
        }
        public void setCreatedDate(Date CreatedDate){
            this.CreatedDate=CreatedDate;
        }
        public String getCreatedBy(){
             return CreatedBy;
        }
        public void setCreatedBy(String CreatedBy){
            this.CreatedBy=CreatedBy;
        }
        
        public String getDepartment(){
             return Department;
        }
        public void setDepartment(String Department){
            this.Department=Department;
        }
        
          
          public void readValues(DataRead dr) throws BasicException {
           
             
            BillID = dr.getString(1);
            Amount = dr.getDouble(2);
            CancelledBy = dr.getString(3);
            CancelledDate = dr.getTimestamp(4);
            CreatedDate = dr.getTimestamp(5);
            CreatedBy = dr.getString(6);
            Department = dr.getString(7);
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
    
    
    
    
    
    
    
    
    
    
}

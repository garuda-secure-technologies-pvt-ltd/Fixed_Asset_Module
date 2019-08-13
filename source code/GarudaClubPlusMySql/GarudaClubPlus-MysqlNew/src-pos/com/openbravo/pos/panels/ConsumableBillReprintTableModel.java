

package com.openbravo.pos.panels;

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
import com.openbravo.pos.Booking.BilledReportsTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.sms.EmailMasterTableModel;
import com.openbravo.pos.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class ConsumableBillReprintTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<ConsumableBillReprintTableModel.BillInfo> data;
    private List<ConsumableBillReprintTableModel.CloseDayInfo> CloseDaydata;
    private List<ConsumableBillReprintTableModel.VoucherListInfo> VoucherData;
    private List<ConsumableBillReprintTableModel.ProductListInfo> ProdData;
     
     
    private int size;
    private int size2;
    private int size3;
    private final static String[] RoomHeader = {"Sequence No:" , "Date Start" , "Date End" , "Amount" , "Created By" };    
    private final static String[] VoucherHeader = {"Voucher No" , "Department" , "Amount" , "Created Date"  };  
    private final static String[] ProductHeader = {"Product Name" , "Qty" , "Rate" , "Created Date"  }; 
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    
    public static ConsumableBillReprintTableModel loadEmailInfo(AppView app , String BillID) throws BasicException{
        ConsumableBillReprintTableModel GuestInfo = new ConsumableBillReprintTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<ConsumableBillReprintTableModel.BillInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select b.id , p.name , bi.qty , bi.rate , d.name , b.createddate , u.name\n" +
                                                                    "from cpbill b , cpbillitem bi , department d , products p , people u\n" +
                                                                    "where b.id=bi.billid and d.id = b.deptid and p.id=bi.productid and u.id=b.createdby and b.id=?\n" +
                                                                    "union\n" +
                                                                    "select b.id , p.name , bi.qty , bi.rate , d.name , b.createddate , u.name\n" +
                                                                    "from cpbill_arv b , cpbillitem_arv bi , department d , products p , people u\n" +
                                                                    "where b.id=bi.billid and d.id = b.deptid and p.id=bi.productid and u.id=b.createdby and b.id='"+BillID+"'", 
                                                                    SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableBillReprintTableModel.BillInfo.class)).list(BillID);
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumableBillReprintTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
 // GET CLOSE DAY  DATA    
    
     public static ConsumableBillReprintTableModel LoadCloseDayDetails(AppView app , Date FromDate , Date Todate) throws BasicException{
        ConsumableBillReprintTableModel GuestInfo = new ConsumableBillReprintTableModel(); 
    
     try{
            GuestInfo.CloseDaydata = new ArrayList<ConsumableBillReprintTableModel.CloseDayInfo>();
            GuestInfo.CloseDaydata = new StaticSentence(app.getSession(), "select c.id , c.sequence , c.datestart , c.dateend ,  p.name , r.name , c.amount\n" +
                                                                            "from closedsaleconsume c , people p , roles r\n" +
                                                                            "where p.id=c.user_  and r.id=c.role   and c.datestart>=? and c.dateend<=?   \n" +
                                                                            "order by sequence", 
                                                                            new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP    }), new SerializerReadClass(ConsumableBillReprintTableModel.CloseDayInfo.class)).list(new Object[]{ FromDate , Todate  });
           
            GuestInfo.size2 = GuestInfo.CloseDaydata.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumableBillReprintTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
  ///////////////////////////////////////////////////////////////////////////////
     
// GET VOUCHER DATA  BY CLOSE SALE SEQUENCE 
     
      public static ConsumableBillReprintTableModel LoadVoucherInfoList(AppView app , String Sequence) throws BasicException{
        ConsumableBillReprintTableModel GuestInfo = new ConsumableBillReprintTableModel(); 
    
     try{
            GuestInfo.VoucherData = new ArrayList<ConsumableBillReprintTableModel.VoucherListInfo>();
            GuestInfo.VoucherData = new StaticSentence(app.getSession(), "select c.id , d.name ,   c.createddate , c.amount \n" +
                                                                            "from cpbill_arv c , department d \n" +
                                                                            "where d.id=c.deptid  and closesaleseq like ?  \n" +
                                                                            "order by c.createddate ", 
                                                                             SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableBillReprintTableModel.VoucherListInfo.class)).list(Sequence);
           
            GuestInfo.size = GuestInfo.VoucherData.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumableBillReprintTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
  //////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      
   public static ConsumableBillReprintTableModel LoadProdData(AppView app , String Sequence) throws BasicException{
        ConsumableBillReprintTableModel GuestInfo = new ConsumableBillReprintTableModel(); 
    
     try{
            GuestInfo.ProdData = new ArrayList<ConsumableBillReprintTableModel.ProductListInfo>();
            GuestInfo.ProdData = new StaticSentence(app.getSession(), "select p.name , sum(bi.qty) , b.createddate  , (bi.rate)\n" +
                                                                        "from cpbillitem_arv bi , products p , cpbill_arv b\n" +
                                                                        "where p.id=bi.productid and b.id=bi.billid and b.closesaleseq like ?\n" +
                                                                        "group by p.name\n" +
                                                                        "order by b.createddate", 
                                                                             SerializerWriteString.INSTANCE, new SerializerReadClass(ConsumableBillReprintTableModel.ProductListInfo.class)).list(Sequence);
           
            GuestInfo.size3 = GuestInfo.ProdData.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(ConsumableBillReprintTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }    
      
      
      
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
      
    public List<ConsumableBillReprintTableModel.BillInfo> getBillList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<ConsumableBillReprintTableModel.BillInfo>();
      }
    
    
    public List<ConsumableBillReprintTableModel.CloseDayInfo> getCloseDayList(){
           if(CloseDaydata!=null)
        {
            return CloseDaydata;
        }
        else
            return new ArrayList<ConsumableBillReprintTableModel.CloseDayInfo>();
      }
    
    
     public List<ConsumableBillReprintTableModel.VoucherListInfo> getVoucherList(){
           if(VoucherData!=null)
        {
            return VoucherData;
        }
        else
            return new ArrayList<ConsumableBillReprintTableModel.VoucherListInfo>();
      }
    
    public List<ConsumableBillReprintTableModel.ProductListInfo> getProdList(){
           if(ProdData!=null)
        {
            return ProdData;
        }
        else
            return new ArrayList<ConsumableBillReprintTableModel.ProductListInfo>();
      }
    
    
    
    
    
      public static class BillInfo implements SerializableRead,IKeyed {

        private String BillID;
        private String ProdName;
        private int Qty;
        private Double Amount;
        private String DepartmentName;
        private Date CreatedDate;
        private String CreatedBy;
        
         
         public String getBillID() {
            return BillID;
        }
        public void setBillID(String BillID){
            this.BillID=BillID;
        }
        public String getProdName() {
            return ProdName;
        }
        public void setProdName(String ProdName){
            this.ProdName=ProdName;
        }
        
        public int getQty() {
            return Qty;
        }
        public void setQty(int Qty){
            this.Qty=Qty;
        }
        
        
        public Double getAmount() {
            return Amount;
        }
        public void setAmount(Double Amount){
            this.Amount=Amount;
        }

       

        public String getDepartmentName() {
            return DepartmentName;
        }
        public void setDepartmentName(String DepartmentName){
            this.DepartmentName=DepartmentName;
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
        
       
        
          
          public void readValues(DataRead dr) throws BasicException {
           
             
            BillID = dr.getString(1);
            ProdName = dr.getString(2);
            Qty = dr.getInt(3);
            Amount = dr.getDouble(4);
            DepartmentName = dr.getString(5);
            CreatedDate = dr.getTimestamp(6);
            CreatedBy = dr.getString(7);
             
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
      
      
      
      
      
      
       public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return CloseDaydata.size();
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
              ConsumableBillReprintTableModel.CloseDayInfo r =CloseDaydata.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getSequenceNo();
                   case 1: return r.getDateStart();
                   case 2: return r.getDateEnd();
                  
                   case 3: return r.getAmount();
                   case 4: return r.getCreatedBy();
                   case 5: return r.getAmount(); 
                   
                 
                 }
                return null;
            }
          
          
          };
        }  
      
      
      
      
      
      
      
      
      
      public static class CloseDayInfo implements SerializableRead,IKeyed {

        private String ID;
        private int SequenceNo ;
        private Date DateStart;
        private Date DateEnd;
        private String CreatedBy;
        private String Role;
        private Double Amount;
        
        
        
        public String getID() {
            return ID;
        }
        public void setID(String ID){
            this.ID=ID;
        }
         
         public int getSequenceNo() {
            return SequenceNo;
        }
        public void setSequenceNo(int SequenceNo){
            this.SequenceNo=SequenceNo;
        }
        public String getDateStart() {
            String x = Formats.TIMESTAMP.formatValue(DateStart) ;
            return x;
        }
        public void setDateStart(Date DateStart){
            this.DateStart=DateStart;
        }
        
        public String getDateEnd() {
            String x = Formats.TIMESTAMP.formatValue(DateEnd) ;
            return x;
        }
        public void setDateEnd(Date DateEnd){
            this.DateEnd=DateEnd;
        }
        
        
        public Double getAmount() {
            return Amount;
        }
        public void setAmount(Double Amount){
            this.Amount=Amount;
        }

       

        

      
        public String getCreatedBy(){
             return CreatedBy;
        }
        public void setCreatedBy(String CreatedBy){
            this.CreatedBy=CreatedBy;
        }
        
        public String getRole(){
             return Role;
        }
        public void setRole(String Role){
            this.Role=Role;
        }
        
          
          public void readValues(DataRead dr) throws BasicException {
           
             
            ID = dr.getString(1);
            SequenceNo = dr.getInt(2);
            DateStart = dr.getTimestamp(3);
            DateEnd = dr.getTimestamp(4);
            CreatedBy = dr.getString(5);
            Role = dr.getString(6);
            Amount = dr.getDouble(7);
              
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
      
     
      
       public  AbstractTableModel getVoucherTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return VoucherData.size();
            }
          public int getColumnCount() {
                return VoucherHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return VoucherHeader[column];
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
              ConsumableBillReprintTableModel.VoucherListInfo r =VoucherData.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getBillID();
                   case 1: return r.getDepartMent();
                   case 2: return r.getAmount();
                   case 3: return r.getCreatedDate();
                 }
                return null;
            }
          
          
          };
        }  
      
      
      
      
      
      
      
      
      
        public static class VoucherListInfo implements SerializableRead,IKeyed {

        private String BillID;
        private String DepartMent ;
        private Date CreatedDate;
        private Double Amount;
        
        
        
        public String getBillID() {
            return BillID;
        }
        public void setBillID(String BillID){
            this.BillID=BillID;
        }
         
         public String getDepartMent() {
            return DepartMent;
        }
        public void setDepartMent(String DepartMent){
            this.DepartMent=DepartMent;
        }
        
        
        public String getCreatedDate() {
            String x = Formats.TIMESTAMP.formatValue(CreatedDate) ;
            return x;
        }
        public void setCreatedDate(Date CreatedDate){
            this.CreatedDate=CreatedDate;
        }
        
      
        
        public Double getAmount() {
            return Amount;
        }
        public void setAmount(Double Amount){
            this.Amount=Amount;
        }

       public void readValues(DataRead dr) throws BasicException {
           
             
            BillID = dr.getString(1);
            DepartMent = dr.getString(2);
            CreatedDate = dr.getTimestamp(3);
            Amount = dr.getDouble(4);
          }
        public Object getKey() {
           return this;
        }
      }   
     
      
        
        
        
   public  AbstractTableModel getProductTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return ProdData.size();
            }
          public int getColumnCount() {
                return ProductHeader.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return ProductHeader[column];
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
              ConsumableBillReprintTableModel.ProductListInfo r =ProdData.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getProdName();
                   case 1: return r.getQty();
                   case 2: return r.getRate();
                   case 3: return r.getCreatedDate();
                 }
                return null;
            }
          
          
          };
        }        
        
        
        
        
        
        
    public static class ProductListInfo implements SerializableRead,IKeyed {

        private String ProdName;
        private int Qty ;
        private Date CreatedDate;
        private Double Rate;
        
        
        
        public String getProdName() {
            return ProdName;
        }
        public void setProdName(String ProdName){
            this.ProdName=ProdName;
        }
         
         public int getQty() {
            return Qty;
        }
        public void setDepartMent(int Qty){
            this.Qty=Qty;
        }
        
        
        public String getCreatedDate() {
            String x = Formats.TIMESTAMP.formatValue(CreatedDate) ;
            return x;
        }
        public void setCreatedDate(Date CreatedDate){
            this.CreatedDate=CreatedDate;
        }
        
      
        
        public Double getRate() {
            return Rate;
        }
        public void setRate(Double Rate){
            this.Rate=Rate;
        }

       public void readValues(DataRead dr) throws BasicException {
           
             
            ProdName = dr.getString(1);
            Qty = dr.getInt(2);
            CreatedDate = dr.getTimestamp(3);
            Rate = dr.getDouble(4);
          }
        public Object getKey() {
           return this;
        }
      }       
        
    
    
    
    
}

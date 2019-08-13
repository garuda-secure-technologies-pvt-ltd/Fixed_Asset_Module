

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
//import com.openbravo.pos.sms.EmailSentTableModel;
//import com.openbravo.pos.sms.MemberReceivableReportTableModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class MemberReceivableReportTableModel extends BeanFactoryDataSingle {
    private Session s;
     private final static String[] TABLEHEADERS = {"BillDate", "Mem No." , "Name" , "Facility" , "Transno" , "Amount" , "Cleardate","Paymentref"};
     private int size;
     private List<MemberReceivableReportTableModel.MemberInfo> data;
     
    //@Override
    public void init(Session s) {
        
       this.s=s;
    }
    
    public static MemberReceivableReportTableModel loadMemberInfo(AppView app , Date FmDate , Date ToDate) throws BasicException{
        MemberReceivableReportTableModel GuestInfo = new MemberReceivableReportTableModel(); 
        //by pratima
      Object[] obj1 = (Object[]) new StaticSentence(app.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Receipt Series for Member Receivable'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
          if(obj1!=null)  
          {
             String rseries= obj1[0].toString();
            rseries = rseries.trim();
        try{
            GuestInfo.data = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
//            GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
//                                                                    "from accountjournal a ,facility f,customers c\n" +
//                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
//                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like  'REC%' order by facility,date,cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
        
GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
                                                                    "from accountjournal a ,facility f,customers c\n" +
                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like '"+rseries+"%' order by facility,date,cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
        
GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(MemberReceivableReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
          }//if
     return GuestInfo;
  }
    
   public static MemberReceivableReportTableModel loadMemberInfoByFacility(AppView app , Date FmDate , Date ToDate , String Facility) throws BasicException{
        MemberReceivableReportTableModel GuestInfo = new MemberReceivableReportTableModel(); 
    
        try{
            GuestInfo.data = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
                                                                    "from accountjournal a ,facility f,customers c\n" +
                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like  'REC%' and f.name =? order by memno,cleardate , date ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING  }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate ,Facility  });
           GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(MemberReceivableReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  } 
    
    
  //   order by clear date 
    
    public static MemberReceivableReportTableModel loadMemberInfoOrderByClearDate(AppView app , Date FmDate , Date ToDate) throws BasicException{
        MemberReceivableReportTableModel GuestInfo = new MemberReceivableReportTableModel(); 
    
        try{
            GuestInfo.data = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
                                                                    "from accountjournal a ,facility f,customers c\n" +
                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like  'REC%' order by cleardate ,memno,facility ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
           GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(MemberReceivableReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  }
    
 //   order by Bill date 
    
    public static MemberReceivableReportTableModel loadMemberInfoOrderByBillDate(AppView app , Date FmDate , Date ToDate) throws BasicException{
        MemberReceivableReportTableModel GuestInfo = new MemberReceivableReportTableModel(); 
    
        try{
            GuestInfo.data = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
                                                                    "from accountjournal a ,facility f,customers c\n" +
                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like  'REC%' order by billdate,searchkey,cleardate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
           GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(MemberReceivableReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  }   
    
    
//   order by Bill date 
    
    public static MemberReceivableReportTableModel loadMemberInfoOrderByMemberNo(AppView app , Date FmDate , Date ToDate) throws BasicException{
        MemberReceivableReportTableModel GuestInfo = new MemberReceivableReportTableModel(); 
    
        try{
            GuestInfo.data = new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select a.date as billdate, c.searchkey as memno,c.name,f.name as facility ,a.transno,a.amount,a.cleardate , a.paymentref\n" +
                                                                    "from accountjournal a ,facility f,customers c\n" +
                                                                    "where  cleardate>=? and cleardate < ? and a.transref in (select id  from facility )\n" +
                                                                    " and a.transref=f.id and transtype='D' and a.memid=c.id and a.paymentref like  'REC%' order by searchkey,cleardate, billdate ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }) , new SerializerReadClass(MemberReceivableReportTableModel.MemberInfo.class)).list(new Object[]{ FmDate ,  ToDate   });
           GuestInfo.size = GuestInfo.data.size();
        }
        catch(BasicException ex){
            Logger.getLogger(MemberReceivableReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  }   
      
    
    public List<MemberReceivableReportTableModel.MemberInfo> getMemberReceivableList(){
        if(data!=null){
            return data;
        }
        else{
            return new ArrayList<MemberReceivableReportTableModel.MemberInfo>();
        }
    } 
    
    
    
    public int getSize(){
        return size;
    }
    
    
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              MemberReceivableReportTableModel.MemberInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   
                   //case 0: return (rowIndex+1);
                   case 0: return r.getDATE();
                   case 1: return r.getMemno();
                   case 2: return r.getName();
                   case 3: return r.getFacility();
                   case 4 :return r.getTransno();
                   case 5: return r.getAmount();
                   case 6: return r.getCleardate();
                   case 7: return r.getPaymentref();
                       
                       
                       
                 }
               return null;
            }
          
          
          };
        } 
     public static class MemberInfo implements SerializableRead,IKeyed {

        private Date DATE;
        private String Memno;
        private String Name;
        private String Facility;
        
        private String Transno;
        private Double Amount;
        private Date Cleardate;
        private String Paymentref;
        
       
         
          public String getDATE(){
              String x = Formats.TIMESTAMP.formatValue(DATE);
               return x;
          }
          public void setDATE(Date DATE){
              this.DATE=DATE;
          }
         
         public String getMemno(){
             return Memno;
         }
         public void setMemno(String Memno){
             this.Memno=Memno;
         }
         public String getName(){
             return Name;
         }
         public void setName(String Name)
         {
             this.Name=Name;
            
         }
         public String getFacility(){
             return Facility;
         }
         public void setFacility(String Facility){
             this.Facility=Facility;
         }
         
          public String getTransno(){
             return Transno;
         }
         public void setTransno(String Transno){
             this.Transno=Transno;
         }
         
          public Double getAmount(){
             return Amount;
         }
         public void setAmount(Double Amount){
             this.Amount=Amount;
         }
         
         
         public String getCleardate(){
              String x = Formats.TIMESTAMP.formatValue(Cleardate);
               return x;
          }
          public void setCleardate(Date Cleardate){
              this.Cleardate=Cleardate;
          }
          
           public String getPaymentref(){
             return Paymentref;
         }
         public void setPaymentref(String Paymentref){
             this.Paymentref=Paymentref;
         }
         
           
          
          public void readValues(DataRead dr) throws BasicException {
           
                
                DATE = dr.getTimestamp(1);
                Memno = dr.getString(2);
                Name= dr.getString(3);
                Facility = dr.getString(4);
                Transno = dr.getString(5);
                Amount = dr.getDouble(6);
                Cleardate=dr.getTimestamp(7);
                Paymentref=dr.getString(8);
                
                
             
              
          }

        public Object getKey() {
           return this;
        }
        
     }   
    
    
    
    
}

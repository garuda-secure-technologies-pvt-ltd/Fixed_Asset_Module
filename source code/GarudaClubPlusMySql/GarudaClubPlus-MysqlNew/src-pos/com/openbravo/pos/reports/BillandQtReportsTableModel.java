
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
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class BillandQtReportsTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<BillandQtReportsTableModel.BOTDetails> BOT_List;  
    private int Bill_Room_Length;
    private final static String[] RoomHeader = {"QT no." , "CR. Date" , "Product" , "Rate" , "Qty" , "Total" , "BillRef", "Created by" };
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    // LOAD FOR ALL BILLS ..... 
     public static BillandQtReportsTableModel LoadBillQTs(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BillandQtReportsTableModel Billed_Rooms = new BillandQtReportsTableModel(); 
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel.BOTDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems qt , qticket q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "union all \n" +
                                                                            "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems_arv qt , qticket_arv q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "order by 1 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BillandQtReportsTableModel.BOTDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate  });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BillandQtReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
    
     
     // ORDER BY CREATEDDATE 
     
     public static BillandQtReportsTableModel LoadBillQTsOrderbyCRdate(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BillandQtReportsTableModel Billed_Rooms = new BillandQtReportsTableModel(); 
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel.BOTDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems qt , qticket q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "union all \n" +
                                                                            "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems_arv qt , qticket_arv q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "order by 8 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BillandQtReportsTableModel.BOTDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate  });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BillandQtReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
     
     
     // ORDER BY CREATEDDATE 
     
     public static BillandQtReportsTableModel LoadBillQTsOrderbyCrby(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BillandQtReportsTableModel Billed_Rooms = new BillandQtReportsTableModel(); 
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel.BOTDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems qt , qticket q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "union all \n" +
                                                                            "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems_arv qt , qticket_arv q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "order by 7 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BillandQtReportsTableModel.BOTDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate  });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BillandQtReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
    
     
      
     public static BillandQtReportsTableModel LoadBillQTsOrderbyProduct(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BillandQtReportsTableModel Billed_Rooms = new BillandQtReportsTableModel(); 
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel.BOTDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems qt , qticket q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "union all \n" +
                                                                            "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems_arv qt , qticket_arv q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?\n" +
                                                                            "order by 3 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BillandQtReportsTableModel.BOTDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate  });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BillandQtReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
    
     
     public static BillandQtReportsTableModel LoadBillQTsOrderbyPendingQts(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         BillandQtReportsTableModel Billed_Rooms = new BillandQtReportsTableModel(); 
         
          try{
            Billed_Rooms.BOT_List = new ArrayList<BillandQtReportsTableModel.BOTDetails>();
            Billed_Rooms.BOT_List = new StaticSentence(app.getSession(), "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems qt , qticket q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ?   and q.billref is null  \n" +
                                                                            "union all \n" +
                                                                            "select q.id, qt.id, p.name as product , qt.rate,qt.dmultiply , q.billref,q.CREATEDBY , q.CRDATE\n" +
                                                                            "from qtitems_arv qt , qticket_arv q , products p\n" +
                                                                            "where qt.PARENTID = q.id and p.id=qt.PRODUCT \n" +
                                                                            "and q.crdate> ?  and q.crdate< ? and q.billref is null  \n" +
                                                                            "order by 1 ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }) ,new SerializerReadClass(BillandQtReportsTableModel.BOTDetails.class)).list(new Object[]{ FrmDate ,  ToDate ,FrmDate ,  ToDate  });

            Billed_Rooms.Bill_Room_Length = Billed_Rooms.BOT_List.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BillandQtReportsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return Billed_Rooms;
         
     }
     
     
      public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return BOT_List.size();
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
              BillandQtReportsTableModel.BOTDetails r =BOT_List.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return r.getQTID();
                   case 1: return r.getCRDATE();     
                   case 2: return r.getPRODUCT();
                   case 3: return r.getRATE();
                   case 4: return r.getQTY();
                   case 5: return (r.getRATE()*r.getQTY());
                   case 6: if(r.getBILLREF()!=null){
                                return r.getBILLREF();
                            }
                   else{
                       return "Pending";
                   }
                   case 7: return r.getCreatedBY();
                   
                   
                 
                 }
                return null;
            }
          
          
          };
        } 
     
      
      
     public List<BillandQtReportsTableModel.BOTDetails> getBOTList(){
           if(BOT_List!=null)
        {
            return BOT_List;
        }
        else
            return new ArrayList<BillandQtReportsTableModel.BOTDetails>();
      }
     
     
     
     
     
     public static class BOTDetails implements SerializableRead,IKeyed {
          
          private String QTID;
          private String QTItemID;
          private String PRODUCT;
          private Double RATE;
          private Double QTY;
          private String BILLREF;
          private String CreatedBY;
          private Date CRDATE;
         
          
          
          public String getQTID(){
              return QTID;
          }
          public void setQTID(String QTID){
              this.QTID=QTID;
          }
          
           public String getQTItemID(){
              return QTItemID;
          }
          public void setQTItemID(String QTItemID){
              this.QTItemID=QTItemID;
          }
          
          public String getPRODUCT(){
              return PRODUCT;
          }
          public void setPRODUCT(String PRODUCT){
              this.PRODUCT=PRODUCT;
          }
           public Double getRATE(){
              return RATE;
          }
          public void setRATE(Double RATE){
              this.RATE=RATE;
          }
           public String getCRDATE(){
               String x = Formats.TIMESTAMP.formatValue(CRDATE) ;
               return x;
          }
          public void setCRDATE(Date CRDATE){
              
              this.CRDATE=CRDATE;
          }
          
           public Double getQTY(){
              return QTY;
          }
          public void setQTY(Double QTY){
              this.QTY=QTY;
          }
           public String getBILLREF(){
              return BILLREF;
          }
          public void setBILLREF(String BILLREF){
              this.BILLREF=BILLREF;
          }
           public String getCreatedBY(){
              return CreatedBY;
          }
          public void setCreatedBY(String CreatedBY){
              this.CreatedBY=CreatedBY;
          }
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            QTID = dr.getString(1);
            QTItemID = dr.getString(2);
            PRODUCT = dr.getString(3);
            RATE = dr.getDouble(4);
            QTY = dr.getDouble(5);
            BILLREF = dr.getString(6);
           
            CreatedBY = dr.getString(7);
            CRDATE = dr.getTimestamp(8);
            
        }

        public Object getKey() {
           return this;
        }
          
    }
      
     
     
     
     
     
    
}

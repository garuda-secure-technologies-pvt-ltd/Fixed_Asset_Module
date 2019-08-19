
package com.openbravo.pos.inventory;

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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class PurchaseJournalReportNewTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<PurchaseJournalReportNewTableModel.PurchaseInfo> data;
    private int size;  
    private final static String[] RoomHeader = {  "DATE" , "Invoice No" , "Vendor" , "Tin No" , "Amount" , "Tax", "Total" , "Tax Perc"}; 
    DecimalFormat decimalFormat = new DecimalFormat("#0.00##"); 
     
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
      
    
    
    
    public static PurchaseJournalReportNewTableModel LoadPurchaseDetailsAll(AppView app , Date FrDate , Date ToDate) throws BasicException{
        PurchaseJournalReportNewTableModel GuestInfo = new PurchaseJournalReportNewTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<PurchaseJournalReportNewTableModel.PurchaseInfo>();
           /* GuestInfo.data = new StaticSentence(app.getSession(), "SELECT '',m.tno,m.invoiceno,m.deliverychallan,m.documentref,m.createdby , m.crdate , v.name , l.name ,\n" +
                                                                    "p.item,p.qty,p.rate,  sum(p.total) , sum(p.taxtotal)   , ifnull(round(((p.taxtotal*100)/p.total),2),0.00) as taxperc \n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>=? and m.crdate<=?    \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id,taxperc  order by m.crdate,m.invoiceno,taxperc ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }), new SerializerReadClass(PurchaseJournalReportNewTableModel.PurchaseInfo.class)).list(new Object[]{ FrDate ,  ToDate   });
            */
            
            /*
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT m.invoiceno, m.crdate , v.name  ,\n" +
                                                                    "p.qty,p.rate,  sum(p.total) , sum(p.taxtotal)   , ifnull(round(((p.taxtotal*100)/p.total),2),0.00) as taxperc ,m.id ,m.tno \n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>= ? and m.crdate<= ? \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id,taxperc  order by m.crdate,m.invoiceno,taxperc ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP   }), new SerializerReadClass(PurchaseJournalReportNewTableModel.PurchaseInfo.class)).list(new Object[]{ FrDate ,  ToDate   });
          */
            
           /* 
            GuestInfo.data = new StaticSentence(app.getSession(), "SELECT m.invoiceno, m.crdate , v.name  ,\n" +
                                                                    "0.00,0.00, 0.00 , 0.00  , 0.00 as taxperc ,m.id , m.tno  \n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>= ? and m.crdate<=?  \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id,taxperc\n" +
                                                                    "UNION ALL\n" +
                                                                    "SELECT m.invoiceno, m.crdate , v.name  ,\n" +
                                                                    "p.qty,p.rate,  sum(p.total) , sum(p.taxtotal)   , ifnull(round(((p.taxtotal*100)/p.total),2),0.00) as taxperc ,m.id , m.tno  \n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>= ? and m.crdate<= ?\n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id,taxperc\n" +
                                                                    "order by 2,1,taxperc ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.TIMESTAMP   }), new SerializerReadClass(PurchaseJournalReportNewTableModel.PurchaseInfo.class)).list(new Object[]{ FrDate ,  ToDate, FrDate ,  ToDate   });
            */
            
            
             GuestInfo.data = new StaticSentence(app.getSession(), "SELECT m.invoiceno, m.crdate , v.name  ,\n" +
                                                                    "p.qty,p.rate,  round(sum(p.total),2) , round(sum(p.taxtotal),2)   , ifnull(round(((p.taxtotal*100)/p.total),2),0.00) as taxperc ,m.id ,m.tno\n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>=?  and m.crdate<= ? \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id,taxperc\n" +
                                                                    "UNION ALL\n" +
                                                                    "SELECT m.invoiceno, m.crdate ,am.name  ,\n" +
                                                                    "0.00,0.00, round(a.amount,2) , 0.00  ,'98' as taxperc ,m.id ,m.tno\n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l , accountjournal a  , accountmaster am  \n" +
                                                                    "where p.parent=m.id and  m.crdate>=?  and m.crdate<=?  \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse and a.tid=m.id and a.narration='Additional charges' and a.active=1  and a.accountid=am.id    \n" +
                                                                    "group by m.id,taxperc\n" +
                                                                    "UNION ALL\n" +
                                                                    "SELECT m.invoiceno , m.crdate , 'Total'  ,\n" +
                                                                    "0.00,0.00, round(sum(p.total),2) , round(sum(p.taxtotal),2)   , '99' as taxperc ,m.id,m.tno\n" +
                                                                    "FROM purchasejournalmain m , purchasejournal p , vendor v , locations l\n" +
                                                                    "where p.parent=m.id and  m.crdate>= ? and m.crdate<=  ? \n" +
                                                                    "and v.id=m.vendor and l.id=m.warehouse  group by m.id\n" +
                                                                    "order by  2,1,taxperc", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.TIMESTAMP , Datas.TIMESTAMP ,Datas.TIMESTAMP , Datas.TIMESTAMP   }), new SerializerReadClass(PurchaseJournalReportNewTableModel.PurchaseInfo.class)).list(new Object[]{ FrDate ,  ToDate ,  FrDate ,  ToDate  ,  FrDate ,  ToDate   });
          
            
            
            
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(PurchaseJournalReportNewTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
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
              PurchaseJournalReportNewTableModel.PurchaseInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                  // case 0: return rowIndex+1;
                   case 0: return r.getCRDATE();
                   case 1: return r.getINVOICENO();
                   case 2: return r.getVENDORNAME();
                   case 3: return r.getTINNO();
                   case 4: return r.getTOTAL();
                   case 5: return decimalFormat.format(r.getTAXTOTAL());
                   case 6: return decimalFormat.format((r.getTOTAL()+r.getTAXTOTAL()));
                   case 7: return (r.getTaxPerc())+" %";
                 
                 }
                return null;
            }
          
          
          };
        } 
    
    
    public List<PurchaseJournalReportNewTableModel.PurchaseInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<PurchaseJournalReportNewTableModel.PurchaseInfo>();
      }
    
    
    
    
    
     public static class PurchaseInfo implements SerializableRead,IKeyed {

        private String ID;
        private String TINNO;
        private String INVOICENO;
      
        private String DELIVERYCHALLAN;
        private String DOCREF;
        private String CREATEDBY;
        private Date CRDATE;
        private String VENDORNAME;
        private String WAREHOUSE;
        private String PRODUCTID;
        private Double QTY;
        private Double RATE;
        private Double TOTAL;
        private Double TAXTOTAL;
        private Double TaxPerc ;
        
        
         public String getID(){
              return ID;
          }
          public void SETID(String ID){
              this.ID=ID;
          }
          public String getTINNO(){
              return TINNO;
          }
          public void setTINNO(String TINNO){
              this.TINNO = TINNO;
          }
          public String getINVOICENO(){
              return INVOICENO;
          }
          public void setINVOICENO(String INVOICENO){
              this.INVOICENO =INVOICENO;
          }
          public String getDELIVERYCHALLAN(){
              return DELIVERYCHALLAN;
          }
          public void setDELIVERYCHALLAN(String DELIVERYCHALLAN){
              this.DELIVERYCHALLAN=DELIVERYCHALLAN;
          }
          
          public String getDOCREF(){
              return DOCREF;
          }
          public void setDOCREF(String DOCREF){
              this.DOCREF=DOCREF;
          }
          
          public String getCREATEDBY(){
              return CREATEDBY;
          }
          public void setCREATEDBY(String CREATEDBY){
              this.CREATEDBY=CREATEDBY;
          }
       
          public String getCRDATE(){
              String x = Formats.DATE.formatValue(CRDATE) ;
              return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
       
          public String getVENDORNAME(){
              return VENDORNAME;
          }
          public void setVENDORNAME(String VENDORNAME){
              this.VENDORNAME=VENDORNAME;
          }
       
          public String getWAREHOUSE(){
              return WAREHOUSE;
          }
          public void setWAREHOUSE(String WAREHOUSE){
              this.WAREHOUSE=WAREHOUSE;
          }
       
          public String getPRODUCTID(){
              return PRODUCTID;
          }
          public void setPRODUCTID(String PRODUCTID){
              this.PRODUCTID=PRODUCTID;
          }
       
          public Double getQTY(){
              return QTY;
          }
          public void setQTY(Double QTY){
              this.QTY=QTY;
          }
       
          public Double getRATE(){
              return RATE;
          }
          public void setRATE(Double RATE){
              this.RATE=RATE;
          }
       
          public Double getTOTAL(){
              return TOTAL;
          }
          public void setTOTAL(Double TOTAL){
              this.TOTAL=TOTAL;
          }
          
          public Double getTAXTOTAL(){
              return TAXTOTAL;
          }
          public void setTAXTOTAL(Double TAXTOTAL){
              this.TAXTOTAL=TAXTOTAL;
          }
          public Double getTaxPerc(){
              return TaxPerc;
          }
          public void setTaxPerc(Double TaxPerc){
              this.TaxPerc=TaxPerc;
          }
          
       
          public void readValues(DataRead dr) throws BasicException {
           
             
              //  ID = dr.getString(1);
              //  TINNO = dr.getString(2);
                INVOICENO = dr.getString(1);
              //  DELIVERYCHALLAN = dr.getString(4);
              //  DOCREF=dr.getString(5);
              //  CREATEDBY=dr.getString(6);
                CRDATE=dr.getTimestamp(2);
                VENDORNAME=dr.getString(3);
               // WAREHOUSE=dr.getString(9);
              //  PRODUCTID=dr.getString(10);
                QTY=dr.getDouble(4);
                RATE=dr.getDouble(5);
                TOTAL=dr.getDouble(6);
                TAXTOTAL=dr.getDouble(7);
                TaxPerc=dr.getDouble(8);
                ID = dr.getString(9);
                TINNO = dr.getString(10);
          }

        public Object getKey() {
           return this;
        }

   }   
    
    
    
    
}

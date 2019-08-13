 
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

 
public class BankDetailsTableMOdel {
    
     private List<BankDetailsTableMOdel.BankInfo> list = new ArrayList<BankDetailsTableMOdel.BankInfo>();
    private final static String[] TABLEHEADERS = {"Sr No. ", "Bank Name" ,"Perc (%)" , "Min. Fixed Chrg" , "BankAccount" , "CR. By" };
    private int size;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    
    public  static BankDetailsTableMOdel loademailGroupNameList(AppView m_app) throws BasicException{
      BankDetailsTableMOdel d = new  BankDetailsTableMOdel();
       d.list=new ArrayList<BankDetailsTableMOdel.BankInfo>();
       
         d.list =  new StaticSentence(m_app.getSession(), " select b.id , b.name , b.perc, b.active , b.crby,b.crdate,a.name , b.fixedcharges , b.defaultflag from bank_details b , accountmaster a where a.id=b.accountid and b.active=1 order by b.name  ", 
               SerializerWriteString.INSTANCE, new SerializerReadClass(BankDetailsTableMOdel.BankInfo.class)).list();
       
         d.size = d.list.size();
         
     return d;

  }
    
  public List<BankDetailsTableMOdel.BankInfo> getEmailgroupList()
     {
         return list;
     }
    
    public int getSize(){
      return size;
  }
    
 
    
    public  AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return list.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
               BankDetailsTableMOdel.BankInfo s = list.get(row);

                switch (column) {

                case 0: return (row+1);
                case 1: return s.getNAME();
                case 2: return decimalFormat.format(s.getPERC());
                case 3: return decimalFormat.format(s.getFixedCharges());
                case 4: return s.getAccountid();
                case 5: return s.getcreatedby();
                    
                default: return null;
                }
            }
        };
    }
  
    
    
    
    public static class BankInfo implements SerializableRead{
    private String ID;
 
    private String NAME;
    private Double PERC;
    private int ACTIVE;
    private String createdby;
    private Date CRDATE;
    private String Accountid;
    private Double FixedCharges;
    private int DefaultBank;

    public void readValues(DataRead dr) throws BasicException
    {
        
         ID=dr.getString(1);
         NAME=dr.getString(2);
         PERC=dr.getDouble(3);
         ACTIVE=dr.getInt(4);
         createdby = dr.getString(5);
         CRDATE = dr.getTimestamp(6);
         Accountid = dr.getString(7);
         FixedCharges=dr.getDouble(8);
         DefaultBank = dr.getInt(9);
   }

    public String getCRDATE() {
        String D = Formats.TIMESTAMP.formatValue(CRDATE);
        return D;
    }
    public void setCrDate(Date CRDATE){
        this.CRDATE=CRDATE;
    }
    
    public String getID(){
     return ID;
    }
    
     public void setID(String ID){
        this.ID=ID;
    }
    
    public String getNAME(){
        return NAME;
    }
      public void setNAME(String NAME){
        this.NAME=NAME;
    }
    
    
    public int getActive(){
      return ACTIVE;
    }
    
    
   public void setActive(int ACTIVE){
        this.ACTIVE=ACTIVE;
    }
   
   
   public Double getPERC(){
       return PERC;
   }
   public void setPERC(Double PERC){
       this.PERC = PERC;
   }
    
   
    public String getcreatedby(){
     return createdby;
    }
    
    public void setcreatedby(String createdby){
        this.createdby=createdby;
    }
   
     
     public void setAccountid(String Accountid){
         this.Accountid=Accountid;
     }
     public String getAccountid(){
         return Accountid;
     }
     
    public void setFixedCharges(Double FixedCharges){
        this.FixedCharges=FixedCharges;
    } 
    public Double getFixedCharges(){
        return FixedCharges;
    }
    
    
    public int getDefaultBank(){
      return DefaultBank;
    }
    
    
   public void setDefaultBank(int DefaultBank){
        this.DefaultBank=DefaultBank;
    }
   
   
    
 } 
    
}

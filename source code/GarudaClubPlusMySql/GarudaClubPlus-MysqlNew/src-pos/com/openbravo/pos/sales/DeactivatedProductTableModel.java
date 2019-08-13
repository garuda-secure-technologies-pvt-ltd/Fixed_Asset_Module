

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.sms.EmailMasterTableModel;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class DeactivatedProductTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private int size;
    private List<DeactivatedProductTableModel.ProdInfo> data;
    private List<DeactivatedProductTableModel.ProdInfo> data2;
    private final static String[] TABLEHEADERS = {"Sr No." , "Prod. Ref" , "Prod. Name" , "Deac By" , "Deac Date" };
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
     public static DeactivatedProductTableModel LoadDeactivatedProducts(AppView app) throws BasicException{
        DeactivatedProductTableModel Info = new DeactivatedProductTableModel(); 
    
     try{
            Info.data = new ArrayList<DeactivatedProductTableModel.ProdInfo>();
            Info.data = new StaticSentence(app.getSession(), "SELECT D.ID , D.PRODUCTID, D.DEACDATE , D.DEACBY , D.DEACHOST , D.ACTIVE  , P.NAME , P.REFERENCE FROM deactiveproduct D , PRODUCTS P WHERE D.ACTIVE=1 AND P.ID=D.PRODUCTID ", SerializerWriteString.INSTANCE, new SerializerReadClass(DeactivatedProductTableModel.ProdInfo.class)).list();
           
            Info.size = Info.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(DeactivatedProductTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return Info;
  
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
              DeactivatedProductTableModel.ProdInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getProdRef();
                   case 2: return r.getProdName();
                   case 3: return r.getDeacBy();
                   case 4: return r.getDeacDate();
                       
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
     
     
     public int getSize()
      {
        return size;
      }
     
     public List<DeactivatedProductTableModel.ProdInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<DeactivatedProductTableModel.ProdInfo>();
      }
     
     
     
     
     
     
     public static class ProdInfo implements SerializableRead,IKeyed {

        private String ID;
        private String ProdID;
        private Date DeacDate;
        private String DeacBy;
        private String DeacHost;
        private int Active;
        private String ProdName; 
        private String ProdRef;
       
         
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getProdID(){
              return ProdID;
          }
          public void setProdID(String ProdID){
              this.ProdID = ProdID;
          }
          
           public String getDeacDate(){
              String x = Formats.TIMESTAMP.formatValue(DeacDate);
               return x;
          }
          public void setDeacDate(Date DeacDate){
              this.DeacDate=DeacDate;
          }
          
          public String getDeacBy(){
              return DeacBy;
          }
          public void setDeacBy(String DeacBy){
              this.DeacBy =DeacBy;
          }
        
          public int getACTIVE(){
              return Active;
          }
          public void setACTIVE(int Active){
              this.Active = Active;
          }
          
         
          
          public String getDeacHost(){
              return DeacHost;
          }
          public void setDeacHost(String DeacHost){
              this.DeacHost=DeacHost;
          }
          
          
          
          
         public String getProdName(){
              return ProdName;
          }
          public void setProdName(String ProdName){
              this.ProdName=ProdName;
          }
          
          
           public String getProdRef(){
              return ProdRef;
          }
          public void setProdRef(String ProdRef){
              this.ProdRef=ProdRef;
          }
         
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                ProdID = dr.getString(2);
                DeacDate = dr.getTimestamp(3);
                DeacBy = dr.getString(4);
                DeacHost = dr.getString(5);
                Active = dr.getInt(6);
                ProdName = dr.getString(7);
                ProdRef = dr.getString(8);
             
              
          }

        public Object getKey() {
           return this;
        }

     
     }
}

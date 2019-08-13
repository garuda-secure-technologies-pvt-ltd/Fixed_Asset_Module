/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class RevaluationTableModel extends BeanFactoryDataSingle{
     private Session s;
    private List<RevaluationTableModel.RevaluationInfo> data2;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
     public static String idoffa = null;
    FixedAsset2 fx = new FixedAsset2();
    public static String idfa = null;
    private final static String[] TABLEHEADERS1 = {"Sr No." , "DATE OF REVALUATION" , "REVALUED AMOUNT " ,  "REASON REV",  "CREATED_BY" ,"CREATED_DATE"};
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
     
    public static RevaluationTableModel GetRevaluationTableModel(AppView app) throws BasicException{
     RevaluationTableModel EmailidInfo = new RevaluationTableModel(); 
    
     try{
            EmailidInfo.data2 = new ArrayList<RevaluationTableModel.RevaluationInfo>();
            if (FixedAsset2.idf != null) {
                idoffa = FixedAsset2.idf;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,date_of_revaluation,revalued_amount,reason_rev,rev_acc_det,rev_doc_link,created_by,created_date from fa_revaluation where active=true and fa_id=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(RevaluationTableModel.RevaluationInfo.class)).list(idoffa);
             } else if (FixedAsset2.fixedid != null) {
                idfa = FixedAsset2.fixedid;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,date_of_revaluation,revalued_amount,reason_rev,rev_acc_det,rev_doc_link,created_by,created_date from fa_revaluation where active=true and fa_id=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(RevaluationTableModel.RevaluationInfo.class)).list(idfa);
           
            } else {
                idoffa = "a";
               EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,date_of_revaluation,revalued_amount,reason_rev,rev_acc_det,rev_doc_link,created_by,created_date from fa_revaluation where active=true and fa_id=?", SerializerWriteString.INSTANCE, new SerializerReadClass(RevaluationTableModel.RevaluationInfo.class)).list(idoffa);
           
            }
            
            
            EmailidInfo.size = EmailidInfo.data2.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(RevaluationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return EmailidInfo;
  
     }
    
       public int getSize()
      {
        return size;
      }
     
     public List<RevaluationTableModel.RevaluationInfo> getList(){
           if(data2!=null)
        {
            return data2;
        }
        else
            return new ArrayList<RevaluationTableModel.RevaluationInfo>();
      } 
        
        
        public  AbstractTableModel getTableModel()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data2.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS1.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS1[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  ,java.lang.String.class  , java.lang.String.class ,java.lang.String.class  , java.lang.String.class
             , java.lang.String.class};
          boolean[] canEdit = new boolean[]{
                false, false , false , false,false,false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
             RevaluationTableModel.RevaluationInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   case 0: return (rowIndex+1);
                   
                   case 1: return r.getDateOfRev();
                   case 2: return decimalFormat.format(r.getRevAmt());
                   case 3: return r.getRevRes();
                  
                  
                   case 4: return r.getCreatedBy();
                   case 5: return r.getCreatedDate();
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
       
        
    
     public static class RevaluationInfo implements SerializableRead,IKeyed {
         private String id;
         private String createdby;
         private Timestamp createddate;
         private Timestamp date_of_rev;
         private Double rev_amt;
         private String rev_res;
         private String rev_acc_det;
         private String doc_link;
         
         public String getDateOfRev(){
             String dateofrev=Formats.TIMESTAMP.formatValue(date_of_rev);
             return dateofrev;
         } 
         public void setDateOfRev(Timestamp date_of_rev){
             this.date_of_rev=date_of_rev;
         }
          public Double getRevAmt(){
          
              return rev_amt;
         
          } 
          public void setRevAmt(Double rev_amt){
              this.rev_amt=rev_amt;
          
          
          }
           public String getRevRes(){
          
              return rev_res;
         
          } 
          public void setRevAmt(String rev_res){
              this.rev_res=rev_res;
          
          
          }
          public String getRevActDet(){
              return rev_acc_det;
          }
          public void setRevActDet(String rev_acc_det){
              this.rev_acc_det=rev_acc_det;
          }
          public String getdoc_link(){
              return doc_link;
          }
          public void setdoc_link(String doc_link){
              this.doc_link=doc_link;
          }
          public String getId(){
              return id;
           }
           public void setId(String id){
              this.id=id;
           }
           public String getCreatedBy(){
              return createdby;
           }
           public void setCreatedBy(String createdby){
              this.createdby=createdby;
           }
           public String getCreatedDate(){
               String crddate=Formats.TIMESTAMP.formatValue(createddate);
              return crddate;
           }
           public void setCreatedDate(Timestamp createddate){
              this.createddate=createddate;
           }
         
         
        public void readValues(DataRead dr) throws BasicException {
          id=dr.getString(1);
          date_of_rev=dr.getTimestamp(2);
          rev_amt=dr.getDouble(3);
          rev_res=dr.getString(4);
          rev_acc_det=dr.getString(5);
          doc_link=dr.getString(6);
          createdby=dr.getString(7);
          createddate=dr.getTimestamp(8);
          
        }

        public Object getKey() {
           return this;
        }
         
         
         
     }
}

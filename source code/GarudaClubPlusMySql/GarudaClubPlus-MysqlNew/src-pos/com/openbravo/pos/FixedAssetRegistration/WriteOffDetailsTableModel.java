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
import static com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.idfa;
import static com.openbravo.pos.FixedAssetRegistration.MaintenanceTableModel.idoffa;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class WriteOffDetailsTableModel extends BeanFactoryDataSingle{
     private Session s;
    private List<WriteOffDetailsTableModel.WriteOffDetailsInfo> data2;
     public static String idoffa = null;
    FixedAsset2 fx = new FixedAsset2();
    public static String idfa = null;
    private int size;
    private final static String[] TABLEHEADERS1 = {"Sr No." ,"DATE_OF_WO","REASON_OF_WO","WO_INITIATED_BY","WO_APPROVED_BY","CREATED_BY","CREATED_DATE"};
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
     
    public static WriteOffDetailsTableModel GetWriteOffDetailsTableModel(AppView app) throws BasicException{
     WriteOffDetailsTableModel EmailidInfo = new WriteOffDetailsTableModel(); 
    
     try{
            EmailidInfo.data2 = new ArrayList<WriteOffDetailsTableModel.WriteOffDetailsInfo>();
            if (FixedAsset2.idf != null) {
                idoffa = FixedAsset2.idf;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  id,date_of_wo,reason_of_wo,wo_initiated_by,wo_approved_by,wo_doc_links,scrap_or_rec_val,voucher_details,created_by,created_date from fa_write_off_details where active=true and faid=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(WriteOffDetailsTableModel.WriteOffDetailsInfo.class)).list(idoffa);
             } else if (FixedAsset2.fixedid != null) {
                idfa = FixedAsset2.fixedid;
                EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  id,date_of_wo,reason_of_wo,wo_initiated_by,wo_approved_by,wo_doc_links,scrap_or_rec_val,voucher_details,created_by,created_date from fa_write_off_details where active=true  and faid=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(WriteOffDetailsTableModel.WriteOffDetailsInfo.class)).list(idfa);
           
            } else {
                idoffa = "a";
               EmailidInfo.data2 = new StaticSentence(app.getSession(), "select  id,date_of_wo,reason_of_wo,wo_initiated_by,wo_approved_by,wo_doc_links,scrap_or_rec_val,voucher_details,created_by,created_date from fa_write_off_details where active=true  and faid=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(WriteOffDetailsTableModel.WriteOffDetailsInfo.class)).list(idoffa);
           
            }
            
            
            
            EmailidInfo.size = EmailidInfo.data2.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(WriteOffDetailsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return EmailidInfo;
  
     }
    
       public int getSize()
      {
        return size;
      }
     
     public List<WriteOffDetailsTableModel.WriteOffDetailsInfo> getList(){
           if(data2!=null)
        {
            return data2;
        }
        else
            return new ArrayList<WriteOffDetailsTableModel.WriteOffDetailsInfo>();
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class  , java.lang.String.class ,java.lang.String.class  , java.lang.String.class
             };
          boolean[] canEdit = new boolean[]{
                false, false , false , false,false,false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
             WriteOffDetailsTableModel.WriteOffDetailsInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   case 0: return (rowIndex+1);
                  
                   case 1: return r.getDateOfWo();
                   case 2: return r.getWoRes();
                   case 3: return r.getInitiatedBy();
                   case 4: return r.getApprovedBy();
                  
                   
                   case 5: return r.getCreatedBy();  
                   case 6: return r.getCreatedDate();    
                 }
                return null;
            }
          
          
          };
        } 
       
        
    
     public static class WriteOffDetailsInfo implements SerializableRead,IKeyed {
         private String id;
         private String createdby;
         private Timestamp createddate;
         private Timestamp DATE_OF_WO;
         private String wo_res;
         private String wo_initiated_by;
         private String wo_app_by;
         private String SCRAP_OR_REC_VAL;
         private String VOUCHER_DETAILS;
         private String link;
         private String wovocdetails;
         
         public String getDateOfWo(){
             String dow=Formats.TIMESTAMP.formatValue(DATE_OF_WO);
             return dow;
         } 
         public void setDateOfWo(Timestamp DATE_OF_WO){
             this.DATE_OF_WO=DATE_OF_WO;
         }
          public String getWoRes(){
          
              return wo_res;
         
          } 
          public void setWoRes(String wo_res){
              this.wo_res=wo_res;
          
          
          }
          public String getSCRAP_OR_REC_VAL(){
          
              return SCRAP_OR_REC_VAL;
         
          } 
          public void setSCRAP_OR_REC_VAL(String wo_res){
              this.SCRAP_OR_REC_VAL=SCRAP_OR_REC_VAL;
          
          
          }
          public String getVOUCHER_DETAILS(){
          
              return VOUCHER_DETAILS;
         
          } 
          public void setVOUCHER_DETAILS(String VOUCHER_DETAILS){
              this.VOUCHER_DETAILS=VOUCHER_DETAILS;
          
          
          }
          public String getwovocdetails(){
          
              return wovocdetails;
         
          } 
          public void setwovocdetails(String wovocdetails){
              this.wovocdetails=wovocdetails;
          
          
          }
           public String getInitiatedBy(){
          
              return wo_initiated_by;
         
          } 
          public void setInitiatedBy(String wo_initiated_by){
              this.wo_initiated_by=wo_initiated_by;
          
          
          }
          public String getApprovedBy(){
              return wo_app_by;
          }
          public void setApprovedBy(String wo_app_by){
              this.wo_app_by=wo_app_by;
          }
          public String getdoc_link(){
              return link;
          }
          public void setdoc_link(String link){
              this.link=link;
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
          DATE_OF_WO=dr.getTimestamp(2);
          wo_res=dr.getString(3);
          wo_initiated_by=dr.getString(4);
          wo_app_by=dr.getString(5);
          link=dr.getString(6);
          SCRAP_OR_REC_VAL=dr.getString(7);
          VOUCHER_DETAILS=dr.getString(8);
          
          createdby=dr.getString(9);
          createddate=dr.getTimestamp(10);
          
        }

        public Object getKey() {
           return this;
        }
         
         
         
     }
    
}

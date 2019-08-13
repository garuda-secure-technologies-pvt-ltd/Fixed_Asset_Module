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
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
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
public class PhysicalVerificationTableModel extends BeanFactoryDataSingle{
    private Session s;
    private static AppView m_app;
    private List<PhysicalVerificationTableModel.PhysicalVerificationInfo> data2;
     public static  String idoffa=null;
   FixedAsset2 fx=new FixedAsset2();
   public static String idfa=null;
    public static String id;
    private int size;
    private final static String[] TABLEHEADERS1 = {"Sr No." ,"Verification Date" , "Verified By " ,  "Barcode",   "Remark","Created by", "Created Date","Location","Active" };//added location column by pratima
     private List<FALocationsTableModel.FALocationsInfo> locationList = new ArrayList<FALocationsTableModel.FALocationsInfo>();
    @Override
    public void init(Session s) {
       
       this.s=s;
    }
     
    public static PhysicalVerificationTableModel GetPhysicalVerificationTableModel(AppView app) throws BasicException{
    m_app=app;
        PhysicalVerificationTableModel EmailidInfo = new PhysicalVerificationTableModel(); 
   
     
        
        
     try{
         EmailidInfo.data2 = new ArrayList<PhysicalVerificationTableModel.PhysicalVerificationInfo>();
            
         if(FixedAsset2.idf!=null){
            idoffa=FixedAsset2.idf;
           
     
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,ver_date,ver_by,barcode_uid,photo,ver_remarks,created_by,created_date,location,active from fa_physicalverification where  fa_id=? order by ver_by ", SerializerWriteString.INSTANCE, new SerializerReadClass(PhysicalVerificationTableModel.PhysicalVerificationInfo.class)).list(idoffa);//added location column by pratima
           
           
            
        }else if(FixedAsset2.fixedid!=null){
        idfa=FixedAsset2.fixedid;
        EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,ver_date,ver_by,barcode_uid,photo,ver_remarks,created_by,created_date,location,active from fa_physicalverification where  fa_id=? order by ver_by ", SerializerWriteString.INSTANCE, new SerializerReadClass(PhysicalVerificationTableModel.PhysicalVerificationInfo.class)).list(idfa);//added location column by pratima
           
        }else{
        idoffa="a";
        EmailidInfo.data2 = new StaticSentence(app.getSession(), "select id,ver_date,ver_by,barcode_uid,photo,ver_remarks,created_by,created_date,location,active from fa_physicalverification where  fa_id=? order by ver_by ", SerializerWriteString.INSTANCE, new SerializerReadClass(PhysicalVerificationTableModel.PhysicalVerificationInfo.class)).list(idoffa);//added location column by pratima
        
        }
      EmailidInfo.size = EmailidInfo.data2.size();
        
        }
        catch(BasicException ex){
            Logger.getLogger(PhysicalVerificationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     return EmailidInfo;
     
  
     }
    
       public int getSize()
      {
        return size;
      }
     
     public List<PhysicalVerificationTableModel.PhysicalVerificationInfo> getList(){
           if(data2!=null)
        {
            return data2;
        }
        else
            return new ArrayList<PhysicalVerificationTableModel.PhysicalVerificationInfo>();
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
               java.lang.String.class ,java.lang.String.class   , java.lang.String.class ,java.lang.String.class  , java.lang.String.class
           , java.lang.String.class  , java.lang.String.class,java.lang.String.class  };
          boolean[] canEdit = new boolean[]{
                 false , false , false,false,false,false,false,false
            };//added one string field for location by pratima
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              PhysicalVerificationTableModel.PhysicalVerificationInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   case 0: return (rowIndex+1);
                   
                   case 1:  return r.getphotoDate();
                   case 2: return r.getVerifiedBy();
                   case 3: return r.getBarcode();
                   
                   case 4: return r.getremarks();
                   case 5: return r.getCreatedBy();
                   case 6: return r.getCreatedDate();
                   case 7:
                       String loc="";
                       try{
                      loc= getLocationList(r.getLocation()).get(0).toString() ;
                   }catch(Exception ex){ex.printStackTrace();}
                       return loc;  //added  by pratima
                     case 8: 
                         if(r.getActive()){
                         return "Active";
                         }else return "";
                         //added  by pratima
                       
                 }
                return null;
            }
          
          
          };
        } 
       
        
    
     public static class PhysicalVerificationInfo implements SerializableRead,IKeyed {
         private String id;
          private String photolink;
          private Date photoDate;
          private String remarks;
          private String barcode;
          private String verby;
          private String createdby;
          private Timestamp createddate;
          //added  by pratima 
          private String location;     
           private Boolean active;
         
           public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
        //ended  by pratima  
           public String getphotolink(){
              return photolink;
           }
           public void setphotolink(String photolink){
              this.photolink=photolink;
           }
          public String getId(){
              return id;
           }
           public void setId(String id){
              this.id=id;
           }
            public String getphotoDate(){
                String phdate=Formats.TIMESTAMP.formatValue(photoDate);
              return phdate;
          }
          public void setphotoDate(Date photoDate){
              this.photoDate = photoDate;
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
          
          
          public String getremarks(){
              return remarks;
          }
          public void setremarks(String remarks){
              this.remarks = remarks;
          }
         public String getBarcode(){
              return barcode;
          }
          public void setBarcode(String barcode){
              this.barcode = barcode;
          }
          public String getVerifiedBy(){
              return verby;
          }
          public void setVerifiedBy(String verby){
              this.verby = verby;
          }
         
         
        public void readValues(DataRead dr) throws BasicException {
          id=dr.getString(1);
          photoDate= dr.getTimestamp(2);
          verby=dr.getString(3);
          barcode=dr.getString(4);
          photolink = dr.getString(5);
          
          remarks= dr.getString(6);
          createdby=dr.getString(7);
          createddate=dr.getTimestamp(8);
          location= dr.getString(9);//added  by pratima  
          active=dr.getBoolean(10);
        }

        public Object getKey() {
           return this;
        }
         
         
         
     }
        //added by pratima
    
        public List getLocationList(String id) throws BasicException {
         locationList = new ArrayList<FALocationsTableModel.FALocationsInfo>();
        List<String> locationList1 = new ArrayList<String>();
        locationList = new StaticSentence(m_app.getSession(), "select id,name,floor,building,block,type from fa_Locations where active=1 and type='lo' and id=? order by name ", SerializerWriteString.INSTANCE,  new SerializerReadClass(FALocationsTableModel.FALocationsInfo.class)).list(id);
        for(int i=0;i<locationList.size();i++){
         
            String strloc=locationList.get(i).getName()+","+getNameById(locationList.get(i).getBuilding())+","+getNameById(locationList.get(i).getBlock())+","+getNameById(locationList.get(i).getFloor());
            locationList1.add(strloc);
       }
         
        return locationList1;
    }
        public String getNameById(String id){
    String name="";
    try{
 name= (String) new StaticSentence(m_app.getSession(), "select name from  fa_locations where id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(id);
    }catch(Exception ex){
        ex.printStackTrace();
    }
    return name;
}
}

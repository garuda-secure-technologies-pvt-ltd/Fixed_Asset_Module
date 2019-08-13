/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

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
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
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
public class VerifiedListOfAssetsTable_Model extends BeanFactoryDataSingle{
    
     private Session s;
    private List<VerifiedListOfAssetsTable_Model.AssetInfo> AssetList;
    private int asset_length;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
     private final static String[] ASSETHEADER = {"NAME" ,  "MAJ CLASSIFICATION "  , "ACCOUNT HEAD "  , "VENDOR " , "DATE OF PURCHASE" , " TOTAL COST","AGENCY FOR REPLACEMENT"};
     DecimalFormat decimalformat= new DecimalFormat("#0.00##");
    
   @Override
    public  void init(Session s){
        this.s=s;
    }
    
    
    public static VerifiedListOfAssetsTable_Model LoadAllFacrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClscrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster,fa_physicalverification p a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClscrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster,fa_physicalverification p a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static VerifiedListOfAssetsTable_Model LoadAllFabyActivecrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.active=true and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsbyActivecrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClsbyActivecrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc  from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? v order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
      public static VerifiedListOfAssetsTable_Model LoadAllFabyWocrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
       public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsbyWocrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClsByWocrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        
        
      public static VerifiedListOfAssetsTable_Model LoadAllFaordercrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsordercrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClsordercrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.id=p.fa_id  order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static VerifiedListOfAssetsTable_Model LoadAllFabyActiveordercrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.active=true and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsbyActiveordercrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClsbyActiveordercrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc  from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
      public static VerifiedListOfAssetsTable_Model LoadAllFabyWoordercrdt(AppView app,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
       public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsbyWoordercrdt(AppView app,String majcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        public static VerifiedListOfAssetsTable_Model LoadAllFabyMajClsSubClsByWoordercrdt(AppView app,String majcls,String subcls,Date from_date,Date to_date)throws BasicException{
         VerifiedListOfAssetsTable_Model fadetail = new VerifiedListOfAssetsTable_Model(); 
         
          try{
            fadetail.AssetList = new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n" +
"f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? and f.id=p.fa_id order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}) ,new SerializerReadClass(VerifiedListOfAssetsTable_Model.AssetInfo.class)).list(new Object[]{majcls,subcls,from_date,to_date});

            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(VerifiedListOfAssetsTable_Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        
      
    public AbstractTableModel getTableModel(){
     
         return new AbstractTableModel(){
         @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(ASSETHEADER[column]);
            }
            public int getRowCount() {
                return AssetList.size();
            }
            public int getColumnCount() {

                return ASSETHEADER.length;
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              VerifiedListOfAssetsTable_Model.AssetInfo b=AssetList.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return b.getName();
                       
                   
                   case 1: return b.getMajcls();
                   
                         
                   case 2: return b.getAccHead();
                   case 3: return b.getVendor();
                   case 4: return b.getPurchaseDate();
                   case 5: return decimalFormat.format(b.getcost());
                  
                   case 6: return b.getAgency();
                   
                       
                   default: return null;
                 
                 }
               
            }
         
         };
          
     }
     public List<VerifiedListOfAssetsTable_Model.AssetInfo> getFAList(){
           if(AssetList!=null)
        {
            return AssetList;
        }
        else
            return new ArrayList<VerifiedListOfAssetsTable_Model.AssetInfo>();
      }
     
      public static class AssetInfo implements SerializableRead,IKeyed{
        private String name;
        private String barcode;
        private String majcls;
        private String subhead;
        private String acchead;
        private int stdaln;
        private String asset;
        private String vendor;
        private Date purchseate;
        private Double totcost;
        private String sacdoc;
        private Date instdate;
        private Date commdate;
        private Date captdate;
        private Date puttousedate;
        private String agency;
        private Double rod;
        private Double wdv;
        private Double cor;  
          
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
        } 
        public String getBarcode(){
            return barcode;
        }
        public void setBarcode(String barcode){
            this.barcode=barcode;
        } 
        public String getMajcls(){
            return majcls;
        }
        public void setMajcls(String majcls){
            this.majcls=majcls;
        } 
        public String getSubHead(){
            return subhead;
        }
        public void setSubHead(String subhead){
            this.subhead=subhead;
        } 
        public String getAccHead(){
            return acchead;
        }
        public void setAccHead(String acchead){
            this.acchead=acchead;
        } 
        public String getVendor(){
            return vendor;
        }
        public void setVendor(String vendor){
            this.vendor=vendor;
        } 
        public String getAgency(){
            return agency;
        }
        public void setAgency(String agency){
            this.agency=agency;
        } 
        public String getAsset(){
            return asset;
        }
        public void setAsset(String asset){
            this.asset=asset;
        } 
         public String getScadoc(){
            return sacdoc;
        }
        public void setScadoc(String sacdoc){
            this.sacdoc=sacdoc;
        } 
        
        public String getPurchaseDate() {
            String purc = Formats.TIMESTAMP.formatValue(purchseate);
            return purc;
        }
        public void setPurchaseDate(Date purchasedate){
            this.purchseate=purchasedate;
        } 
        public Date getInstDate(){
             return instdate;
        }
        public void setInstDate(Date instdate){
            this.instdate=instdate;
        } 
        public Date getCommDate(){
            return commdate;
        }
        public void setCommDate(Date commdate){
            this.commdate=commdate;
        } 
        public Date getCptDate(){
            return captdate;
        }
        public void setCptDate(Date captdate){
            this.captdate=captdate;
        } 
        public Date getUseDate(){
            return puttousedate;
        }
        public void setUseDate(Date puttousedate){
            this.puttousedate=puttousedate;
        } 
         public Double getrod() {
            return rod;
        }

        public void setrod(Double rod) {
            this.rod = rod;
        }
         public Double getwdv() {
            
            return wdv;
        }

        public void setwdv(Double wdv) {
            this.wdv = wdv;
        }
         public Double getcor() {
         
            return cor;
        }

        public void setcor(Double cor) {
            this.cor = cor;
        }
         public Double getcost() {
          
            return totcost;
        }
         
        public void setcost(Double totcost) {
            this.totcost = totcost;
        }
        public int getstand_alone() {
            return stdaln;
        }

        public void setstand_alone(int stdaln) {
            this.stdaln = stdaln;
        }
        
        
         @Override
         public String toString(){
            return name;
        }
        @Override
        public Object getKey() {
           return this;
        }
     
          public void readValues(DataRead dr) throws BasicException{
              
           name=dr.getString(1);
           barcode=dr.getString(2);
           majcls=dr.getString(3);
           subhead=dr.getString(4);
           acchead=dr.getString(5);
           vendor=dr.getString(6);
          purchseate=dr.getTimestamp(7);
          totcost=dr.getDouble(8);
          agency=dr.getString(9);
          instdate=dr.getTimestamp(10);
          commdate=dr.getTimestamp(11);
          puttousedate=dr.getTimestamp(12);
          captdate=dr.getTimestamp(13);
          rod=dr.getDouble(14);
          wdv=dr.getDouble(15);
          cor=dr.getDouble(16);
          stdaln=dr.getInt(17);
          asset=dr.getString(18);
          sacdoc=dr.getString(19);
                      
          
          
          }
          
           
     }
 
    
}

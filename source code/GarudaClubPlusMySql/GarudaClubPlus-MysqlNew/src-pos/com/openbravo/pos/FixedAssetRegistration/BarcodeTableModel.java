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
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
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
public class BarcodeTableModel  extends BeanFactoryDataSingle{
   private Session s;
    private List<BarcodeTableModel.AssetInfo> AssetList;
    private int asset_length;
     private int flag;
     private boolean status1;
 private final static String[] TABLEHEADERS1 = {"Sr No.", "Name", "Location", "Barcode","Select"};
   
      DecimalFormat decimalformat= new DecimalFormat("#0.00##");
    
   @Override
    public  void init(Session s){
        this.s=s;
    }
    
    
     public static BarcodeTableModel LoadAllFa(AppView app,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
        //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where  f.name=?  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
         
            }else if(flag==1){
            // fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.id=a.fa_id and f.name=?  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
             fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
         
            }else{
           //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.id=a.fa_id and f.name=? and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
             fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
         
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFaforALL(AppView app,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
          //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l   where   p.fa_id=f.id and p.location=l.id  order by f.name ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
             fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
        
            }else if(flag==1){
          //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l  where f.id=a.fa_id   and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
        
            }else{
            // fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l  where f.id=a.fa_id   and p.fa_id=f.id and p.location=l.id  order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
        
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyMajCls(AppView app,String majcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
          //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l  where  f.maj_classification =?  and f.name=?  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
         
            }else if(flag==1){
           //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =?  and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
         
            }else{
         //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =?  and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyMajClsForALL(AppView app,String majcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where  f.maj_classification =?    and p.fa_id=f.id and p.location=l.id  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l  where  f.maj_classification =?  and  f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =?  and  f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyMajClsSubCls(AppView app,String majcls,String subcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
         //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=? and f.name=?  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         
            }else if(flag==1){
            // fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=? and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         
            }else{
          //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=? and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where   f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
      public static BarcodeTableModel LoadAllFabyMajClsSubClsForALL(AppView app,String majcls,String subcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=?    and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost ,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=?    and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where  f.maj_classification =? and f.sub_head_class=?   and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static BarcodeTableModel LoadAllFabyActive(AppView app,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
           // fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true  and f.name=?  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }else if(flag==1){
           //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.id=a.fa_id  and f.name=?  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }else{
          //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.id=a.fa_id and f.name=?  and p.fa_id=f.id and p.location=l.id  order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyActiveForALL(AppView app,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
        //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name   as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
         fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
            
            }else if(flag==1){
           //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=1 and f.id=a.fa_id  and f.active=true and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
         
            }else{
         //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=1 and f.id=a.fa_id  and f.active=true and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
         
            }
          
          
fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
    public static BarcodeTableModel LoadAllFabyMajClsbyActive(AppView app,String majcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
           // fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=? and f.name=?  and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?   order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
         
            }else if(flag==1){
          //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_costl.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=? and f.name=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?   order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
         
            }else{
          //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=? and f.name=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =?   order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
         
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyMajClsbyActiveForALL(AppView app,String majcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=? and f.active=1  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
     public static BarcodeTableModel LoadAllFabyMajClsSubClsbyActive(AppView app,String majcls,String subcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
         //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.sub_head_class=? and f.name=?  and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         
            }else if(flag==1){
       //      fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.sub_head_class=? and f.name=? and f.id=a.fa_id  and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
           
            }else{
         //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=? and f.sub_head_class=? and f.name=?  and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc where f.active=1 and  f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
      public static BarcodeTableModel LoadAllFabyMajClsSubClsbyActiveForALL(AppView app,String majcls,String subcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.sub_head_class=?   and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.sub_head_class=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_amc a,fa_physicalverification p,fa_locations l where f.active=true and f.maj_classification=?  and f.sub_head_class=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
      public static BarcodeTableModel LoadAllFabyWo(AppView app,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
         //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=?   and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.wo=w.id and  f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }else if(flag==1){
         //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.wo=w.id and f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }else{
           //  fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=? and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
            fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1 order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.wo=w.id and f.active=1 and f.name=?  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{asset});
        
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
       public static BarcodeTableModel LoadAllFabyWoForALL(AppView app,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id and f.active=true  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost ,l.name as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.active=true and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.active=true  and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
       public static BarcodeTableModel LoadAllFabyMajClsbyWo(AppView app,String majcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=? and f.maj_classification=?   and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=? and f.maj_classification=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name   as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.name=? and f.maj_classification=?  and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        public static BarcodeTableModel LoadAllFabyMajClsbyWoForALL(AppView app,String majcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
         fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id and f.maj_classification=?   and f.active=1 and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.maj_classification=?  and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name  as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id and f.maj_classification=?   and f.id=a.fa_id and f.active=1 and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        public static BarcodeTableModel LoadAllFabyMajClsSubClsByWo(AppView app,String majcls,String subcls,int flag,String asset)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
         //   fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.name=?  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.active=1 and  f.wo=w.id and f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
         
            }else if(flag==1){
        //     fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.active=1 and  f.wo=w.id and f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
           
            }else{
         //    fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.name=? and f.id=a.fa_id  and p.fa_id=f.id and p.location=l.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
           fadetail.AssetList = new StaticSentence(app.getSession(), " select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,f.active ,l.name from (select f1.*, p.fa_id as pid,p.location as ploc from  fa_master f1 left join fa_physicalverification p  on   f1.id=p.fa_id   and p.active=1  and f1.maj_classification =? and f1.sub_head_class=?  order by f1.name ) as f left join fa_locations l on l.id=f.ploc ,fa_write_off_details w where f.active=1 and  f.wo=w.id and f.name=? ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls,asset});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
         public static BarcodeTableModel LoadAllFabyMajClsSubClsByWoForALL(AppView app,String majcls,String subcls,int flag)throws BasicException{
         BarcodeTableModel fadetail = new BarcodeTableModel(); 
         
          fadetail.flag=flag;
          try{
            fadetail.AssetList = new ArrayList<BarcodeTableModel.AssetInfo>();
            if(flag==0){
            fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.active=1  and p.fa_id=f.id and p.location=l.id order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
            }else if(flag==1){
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.id=a.fa_id and f.active=1  and p.fa_id=f.id and p.location=l.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }else{
             fadetail.AssetList = new StaticSentence(app.getSession(), "select f.barcode,f.name,f.maj_classification,f.sub_head_class,f.total_cost,l.name as location from  fa_master f,fa_write_off_details w,fa_amc a,fa_physicalverification p,fa_locations l where f.wo=w.id  and f.maj_classification=? and f.sub_head_class=? and f.id=a.fa_id and f.active=1 and l.id=p.location and p.fa_id=f.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BarcodeTableModel.AssetInfo.class)).list(new Object[]{majcls,subcls});
          
            }
            fadetail.asset_length = fadetail.AssetList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BarcodeTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return fadetail;
         
     }
        
     
        
      
    
     public List<BarcodeTableModel.AssetInfo> getFAList(){
           if(AssetList!=null)
        {
            return AssetList;
        }
        else
            return new ArrayList<BarcodeTableModel.AssetInfo>();
      }
     
      public static class AssetInfo implements SerializableRead,IKeyed{
        private String name;
        private String barcode;
        private String majcls;
        private String subhead;
       private String location;
       private Boolean status=false;

       
       
        private Double totcost;
        
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
       
         public Double getcost() {
              
        
            return totcost;
        }
         
        public void setcost(Double totcost) {
            this.totcost = totcost;
        }
         public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
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
              
           name=dr.getString(2);
           barcode=dr.getString(1);
           majcls=dr.getString(3);
           subhead=dr.getString(4);
          
          totcost=dr.getDouble(5);
          location=dr.getString(6);
          
          
          
          }
          
           
     }
       public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {
            int i = 2, j = 0;

            public int getRowCount() {
                return AssetList.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS1.length;
            }

            @Override
            public String getColumnName(int column) {

                return TABLEHEADERS1[column];
            }

            Class[] types = new Class[]{
             java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.Boolean.class};
           @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{
                false, false, false, false,true
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
               
                if(column==4){
                    Boolean status = Boolean.parseBoolean(aValue.toString());
                    AssetInfo r = AssetList.get(row);
                    r.setStatus(status);
                }
                 fireTableDataChanged();
            }
             @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                BarcodeTableModel.AssetInfo r = AssetList.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);
                    case 1:
                        return r.getName();
                    case 2:
                        return r.getLocation();
                    case 3:
                        return r.getBarcode();
                   
                    case 4:
                        return r.getStatus();

                    
                }
                return null;
            }

        };
    }
 
    
}

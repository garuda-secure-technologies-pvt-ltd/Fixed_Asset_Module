/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import java.util.Date;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.Map;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.TreeTableModel;
import java.util.Calendar;
import java.util.List;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.ticket.CategoryInfo;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class CalculationLogicTwo {
    
     private Date sdate;
    private Date edate;
    private List<Account> acclist;
    private Account ie;
    private Map<String ,Account> map1;
     private Map<String,String> wareHouseNames = null;
    private AppView m_App;
    private final static String[] HEADERS = {"Account","Amount"};
    private DataLogicSales dlsales;
    Map<String, Map<String, Double>> temp = null;
   // private Account CIAccount;
    public CalculationLogicTwo(Date edate,AppView app,DataLogicSales dlsales) {
       // this.sdate = sdate;
        this.edate = edate;
        m_App=app;
        map1=new HashMap<String,Account>();
        this.dlsales=dlsales;
    }

    public CalculationLogicTwo() {
        map1=new HashMap<String,Account>();
    }
    public CalculationLogicTwo(Map<String ,Account> map,Date sdate, Date edate,AppView app,DataLogicSales dlsales) {
        map1=new HashMap<String,Account>();
        this.map1.putAll(map);
        this.sdate=sdate;
        this.edate=edate;
        this.m_App=app;
        this.dlsales=dlsales;
    }
    
    private double calculateStock(Date date,boolean status) throws BasicException{
        Map<String,List<Object[]>> pdtFinalMap=new HashMap<String, List<Object[]>>();
        List<ProductStock> pdtStock=new ArrayList<ProductStock>();
        List<String[]> productList=new ArrayList<String[]>();
        Object[] obj;
        Account atree=new Account();
        if(status){
            //startdate
               obj=(Object[])new PreparedSentence(m_App.getSession(), "SELECT AJ.DATE FROM ACCOUNTJOURNAL AJ JOIN ACCOUNTMASTER A ON AJ.ACCOUNTID=A.ID WHERE AJ.DATE<=? AND A.ID=? ORDER BY AJ.DATE DESC "
                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                           , new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(new Object[]{date,"27"});
               atree=map1.get("4.14");
               if(atree==null){
                 atree=new Account();
                 atree.setName("Opening Inventory");
                 atree.setSKey("4.14");
                 atree.setParent("4");
                // map1.get("4.5.1.3.1")
               }

        }else{ //enddate
               obj=(Object[])new PreparedSentence(m_App.getSession(), "SELECT AJ.DATE FROM ACCOUNTJOURNAL AJ JOIN ACCOUNTMASTER A ON AJ.ACCOUNTID=A.ID WHERE AJ.DATE<? AND A.ID=? ORDER BY AJ.DATE DESC "
                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                           , new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(new Object[]{date,"27"});
               //atree=map1.get("4.5.1.2.2");
              // if(atree==null){
                 atree=new Account();
                 atree.setName("Closing Inventory");
                 atree.setSKey("CI");
                 atree.setParent("4");
             //  }
        }
        // inpurchases
        if(obj!=null && obj[0]!=null){
            Date d=(Date)obj[0];
            if(date.toString().equals(d.toString())){
              // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<? AND P.DEACTREF IS  NULL  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE where COALESCE(Pj.RATE,0.0) !='0.00'  ORDER BY PDT.NAME DESC"
                    List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<? AND P.DEACTREF IS  NULL  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE   ORDER BY PDT.NAME DESC"
                       // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME DESC"    
                       , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d});
               pdtStock.addAll(pdtstkTemp);
            }else{
               // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<?  AND P.DEACTREF IS  NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE where COALESCE(Pj.RATE,0.0) !='0.00' ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "
               List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<?  AND P.DEACTREF IS  NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "
                // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "           
                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d,date});
               pdtStock.addAll(pdtstkTemp);
            }
        }else{
             // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  AND P.DEACTREF IS NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE where COALESCE(Pj.RATE,0.0) !='0.00' ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "
             List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  AND P.DEACTREF IS NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "
                      // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC,Pj.RATE, Pj.QTY DESC "             
            , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{date});
              pdtStock.addAll(pdtstkTemp);
        }
        //JOptionPane.showi
        // contains products inpurchases
        Map<String,List<ProductStock>> pdtMap=new HashMap<String, List<ProductStock>>();
        //group inpurchases by product
        for(ProductStock p:pdtStock){
            List<ProductStock> list=pdtMap.get(p.getProductID());
            if(list==null){
               list=new ArrayList<ProductStock>();
               productList.add(new String[]{p.getProductID(),p.getPtoductCategory()});
            }
            list.add(p);
            pdtMap.put(p.getProductID(), list);
        }
        //get the stock as per the date
        List<Object[]> stkDiaryList=new PreparedSentence(m_App.getSession(),
//                                                 "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
//                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3  and ( s2.receivedby!='Rejected' OR s2.receivedby is null )   join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
//                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent "
//                                               
                                                //commented and replaced by pratima   
                                                "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3    join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent "
                                                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                                                , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING})
                                                 ).list(new Object[]{date,date,date,date,date,date,date});
      //  String godowncat="dd9d4974-5705-4077-b6c3-048873429cf3";
        Map<String,Double> pdtStkList=new HashMap<String, Double>();
        List<String> pdtCatList=new ArrayList<String>();
        
        //
        temp = new HashMap<String, Map<String, Double>>();
        //calculate stock of each product
        Map<String, Double> wp = null;
        //
        for(Object[] objtemp:stkDiaryList){
            String product= String.valueOf(objtemp[0]);
            String productname= String.valueOf(objtemp[1]);
            String pwarehouse=String.valueOf(objtemp[10]);
            String productCategory=String.valueOf(objtemp[11]);
            if(!pdtCatList.contains(productCategory)){
                pdtCatList.add(productCategory);
            }

          if(pwarehouse==null){
                 double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                 pdtStkList.put(product, value);
                 
                 
            }else{
                LocationInfo linfo=dlsales.getLocationsInfoByID(productCategory);
                if(linfo.getParentID()!=null){

               Object[] objtemp1=(Object[])new PreparedSentence(m_App.getSession()
                                      , " SELECT PRODUCTFST,NOFST,PRODUCTSCN,NOSEC, locationscn FROM CONVERTER WHERE PRODUCTSCN=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE
                                      , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.INT,Datas.STRING,Datas.INT, Datas.STRING})).find(product);
               if(objtemp1==null || objtemp1[0]==null){
                   objtemp1=(Object[])new PreparedSentence(m_App.getSession()
                                      , " SELECT PRODUCTFST,NOFST,PRODUCTSCN,NOSEC, locationscn FROM CONVERTER WHERE PRODUCTFST=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE
                                      , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.INT,Datas.STRING,Datas.INT, Datas.STRING})).find(product);
                   if(objtemp1!=null && objtemp1[0]!=null){
                       product=String.valueOf(objtemp1[2]);
                       double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                       
                       value=(Double.parseDouble(objtemp1[3].toString()) * value)/Double.parseDouble(objtemp1[1].toString());
                       double val=0.0;
                       if(pdtStkList.containsKey(product))
                          val=pdtStkList.get(product);
                       pdtStkList.put(product, value+val);
                 
                       
                       //tejaswini
                         //wp = new HashMap<String, Double>();
                        //wp.put(String.valueOf(objtemp1[4]), value);
                        //temp.get(product).put(String.valueOf(objtemp1[4]), value);
                        //temp.get(product).put("T", value+val);
                    
                        if(temp.get(product)!=null)
                        {
                        temp.get(product).put(String.valueOf(objtemp1[4]), value);
                        temp.get(product).put("T", value+val);     // EDITED BU AKASH
                        //temp.get(product).put(String.valueOf(objtemp1[4]), value+val);
                        }
                       
                     
                 //tejaswini
                           
                   }
               }else{
                   product=String.valueOf(objtemp1[0]);
                   double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                    value=(Double.parseDouble(objtemp1[1].toString()) * value)/Double.parseDouble(objtemp1[3].toString());
                   double val=0.0;
                   if(pdtStkList.containsKey(product))
                        val=pdtStkList.get(product);
                   pdtStkList.put(product, value+val);
                   
                   
                   //tejaswini
                   //wp = new HashMap<String, Double>();
                 //wp.put(String.valueOf(objtemp1[4]), value);
                   if(temp.get(product)!=null)
                   {
                   temp.get(product).put(String.valueOf(objtemp1[4]), value);
                   temp.get(product).put("T", value+val);   
                   }
                   else
                   {
                       System.out.println("Product ID = " + product);
                       wp = new HashMap<String, Double>();
                        wp.put("T", value);
                 //List<Map<String, Double>> l = new ArrayList<Map<String, Double>>();
                 //l.add(0,wp);
                 //wp = new HashMap<String, Double>();
                 wp.put(String.valueOf(objtemp[2]), value);
                 //l.add(wp);
                 temp.put(product, wp);
                   }
                   //tejaswini
               }
            }else{
                double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                pdtStkList.put(product, value);
               
                //tejaswini
                wp = new HashMap<String, Double>();
                 wp.put("T", value);
                 //List<Map<String, Double>> l = new ArrayList<Map<String, Double>>();
                 //l.add(0,wp);
                 //wp = new HashMap<String, Double>();
                 wp.put(String.valueOf(objtemp[2]), value);
                 //l.add(wp);
                 temp.put(product, wp);
                 
                 //tejaswini
            }
            }
        }
        
        double total=0.0;
        for(String[] p : productList){
           if(pdtCatList.contains(p[1])){
            List<ProductStock> plist=pdtMap.get(p[0]);
            //System.out.println(p);
            Object qty1=pdtStkList.get(p[0]);
            double qty=0.0,oqty=0.0;
            if(qty1!=null){
                       qty=Double.parseDouble(qty1.toString());
                       oqty=qty;
            }
            List<Object[]> objList=new ArrayList<Object[]>();
            double amount=0.0;
           
            Account a=new Account();
            int i=0;
            for(ProductStock pstk:plist){
                
             // if(pstk.getProductID().equals(p)){
                double purLineQty=pstk.getProductQty();
               // ****************************************************** EDITED *********************************** 
                if(qty>purLineQty){
                    i++;
                    qty=qty-purLineQty;
                    Object[] obj1=new Object[]{purLineQty,pstk.getProductRate()};
                    amount+=purLineQty * pstk.getProductRate();
                    total+=purLineQty * pstk.getProductRate();
                    a.addBreakUp(obj1);
                    if(i==plist.size()){
                        System.out.println("Product :  "+pstk.getProductName()+ "  Totoal Qty : "+qty+"    ..... Purchase line Qty : "+purLineQty+ "  Purchase size :  "+plist.size()+"  Value of i : "+i);
                    
                        
                        //************************************************************
                            a.setSKey(pstk.getProductID());
                            a.setSign("PDT");
                            a.setName(pstk.getProductName()+" ("+Formats.ApproxTo2Decimals(oqty)+")");
                            //
                            System.out.println("pstk.getProductID() = " + temp.get(pstk.getProductID()));

                            if(temp.get(pstk.getProductID())!=null)
                            {
                            temp.get(pstk.getProductID()).put("A", amount);
                            temp.get(pstk.getProductID()).put("R", amount/oqty);
                            }
                            else
                            {
                               System.out.println("Not Found = " + pstk.getProductName());  
                              
                            }
                            //
                            if(status){
                                a.setDC(amount,0.0);
                            }else{
                                a.setDC(0.0,amount);
                            }
                            atree.addAccount(a);
                           
                            objList.add(obj1);
                           
                            map1.put(a.getSKey(), a);
                            pdtFinalMap.put(p[0], objList);
                            break;
                        
                    
                        //**************************************************************
                    }
                }else{
                    i=0;
                    if(qty<0){
                      String a1="";
                    }
                    a.setQty(oqty);
                    Object[] obj1=new Object[]{qty,pstk.getProductRate()};
                    a.addBreakUp(obj1);
                    amount+=qty * pstk.getProductRate();
                    total+=qty * pstk.getProductRate();
                    a.setSKey(pstk.getProductID());
                    a.setSign("PDT");
                    a.setName(pstk.getProductName()+" ("+Formats.ApproxTo2Decimals(oqty)+")");
                    //
                    System.out.println("pstk.getProductID() = " + temp.get(pstk.getProductID()));
                    
                    if(temp.get(pstk.getProductID())!=null)
                    {
                    temp.get(pstk.getProductID()).put("A", amount);
                    temp.get(pstk.getProductID()).put("R", amount/oqty);
                    }
                    else
                    {
                       System.out.println("Not Found = " + pstk.getProductName());  
                       //temp.get(pstk.getProductID()).put("A", amount);
                        /* wp = new HashMap<String, Double>();
                         wp.put("T", qty);
                          wp.put(pstk.getPtoductCategory(), qty);
                       
                        temp.put(pstk.getProductID(), wp);
                        temp.get(pstk.getProductID()).put("A", amount);
                        if(amount==0.0 && oqty==0.0){
                        temp.get(pstk.getProductID()).put("R", 0.0);
                        }
                        temp.get(pstk.getProductID()).put("R", amount/oqty);*/
                    }
                    //
                    if(status){
                        a.setDC(amount,0.0);
                    }else{
                        a.setDC(0.0,amount);
                    }
                    atree.addAccount(a);
                    //atree.addDC(amount, 0.0);
                    objList.add(obj1);
                    //Account attree=new Account();
                    //attree.addDC(amount,0.0);
                    map1.put(a.getSKey(), a);
                    pdtFinalMap.put(p[0], objList);
                    break;
                }
            //  }
            }
            }
        }
        
          //tejaswini
        
       // Map<String, List<Map<String, Double>>> temp = new HashMap<String, List<Map<String, Double>>>();
        double totalAmount = 0.0;
          String string="";
         String produ="";
         List<String> lst=new ArrayList<String>();
         
           Map<String, Double> l=new HashMap<String, Double>();
        for (Map.Entry<String, Map<String, Double>> entry : temp.entrySet()) {
           
             produ = entry.getKey();
            l = entry.getValue();
            Double d = 0.0;
            String s = "";
            for (Map.Entry<String, Double> entry1 : l.entrySet()) {
                 string = entry1.getKey();
                if(!string.equals("A") || !string.equals("R") || !string.equals("T") )
                {
                    s = s+"  " + string + " = " + l.get(string);
                }
                
               
                
            }
            
            
            
            String ProductName = (String) new PreparedSentence(m_App.getSession()
                                      , " SELECT NAME FROM PRODUCTS WHERE ID=?",SerializerWriteString.INSTANCE
                                      , SerializerReadString.INSTANCE).find(produ);
                
          
            
            
            
            
            System.out.println(ProductName);
            System.out.println(" " + s + "  Total " + l.get("T") + "   Rate=" + l.get("R") + "   Amount=" +  l.get("A") );
            if(l.get("A")!=null)
            totalAmount = totalAmount + l.get("A") ;
        }
          
  
       
        System.out.println("Total Amount = " + totalAmount);
        //tejaswini
      
        map1.put(atree.getSKey(), atree);
        System.out.println(total);
       return total;
    }
    private List<Account> CalculateFromTrailBalance(Date start,Date end) throws BasicException{

      List<Account> list=new PreparedSentence(m_App.getSession()
                                   , "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DEBIT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CREDIT FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  AND AJ.EDATE>=? AND AJ.EDATE<=? "
                                   +"  GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
                                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                                   , new SerializerReadClass(Account.class)).list(new Object[]{start,end});
   
      return list;

    }
    private void add(Account acc,Account at,String type,Map<String ,Account> map1 ){
        acc.setParent(type);
        at.addAccount(acc);
        map1.put(at.getSKey(), at);
        map1.put(acc.getSKey(), acc);
    }
    public Account getIncomeAndExpenditureDiff(){
        Account acc=map1.get("EIE");
        if(acc==null){
            acc=map1.get("EEI");
        }
        return acc;
    }
    public Account getClosingInventory(){
        Account acc=map1.get("CI");
        return acc;
    }
    public Map<String,Account> getMap(){
        return map1;
    }
    public Date getSdate(){
        return sdate;
    }
    public Date getEdate(){
        return edate;
    }

    public Account getMainAccount(){
        return ie;
    }

    public void generateIncomeAndExpenditureStatement() throws BasicException{
      //map1=new HashMap<String,Account>();
      Account a=new Account();
      a.setName("Income & Expenditure");
      a.setSKey("IE");
      a.setParent("");
      a.setDC(0, 0);
      map1.put("IE", a);
       a=new Account();
      a.setName("Income");
      a.setSKey("I");
      a.setParent("IE");
      a.setDC(0, 0);
      
      map1.put("I", a);
      Account t=map1.get("IE");
      t.getAccountList().add(a);
      //map.put("IE", );
      a=new Account();
      a.setName("Expenditure");
      a.setSKey("E");
      a.setParent("IE");
      a.setDC(0, 0);
      //Account t=map.get("IE");
      t.getAccountList().add(a);
      map1.put("E", a);
      Account at=map1.get("I");
      Account atexpence=map1.get("E");
      List<Account> alist=map1.get("4").getAccountList();
      //sales
      //for(Account acc:alist){
         Account acc=map1.get("4.1");
          if(acc!=null){
            acc.setParent("I");
            at.addAccount(acc);
           // map1.put(at.getSKey(), at);
            map1.put(acc.getSKey(), acc);
             // add(acc,at,"I",map1);
             // break;
          }
    //  }
      //Gross Revenue
     // for(Account acc:alist){
          acc=map1.get("4.2");
          if(acc!=null){
             Account attemp=map1.get("4.1");
             acc.addAmount(attemp.getAmount());
             acc.addCredit(attemp.getCamt());
            // map1.put(acc.getSKey(), acc);
             acc.setParent("I");
             at.addAccountOnly(acc);
           // map1.put(at.getSKey(), at);
            map1.put(acc.getSKey(), acc);
            // add(acc, at,"I",map1);
             // break;
          }
      //}
      //discount
     // for(Account acc:alist){
          acc=map1.get("4.13");
         if(acc!=null){
            acc.setParent("I");
            at.addAccount(acc);
            map1.put(acc.getSKey(), acc);
           //   add(acc,at,"I",map1);
             // break;
          }
          
     // }
      //net revenue
      //for(Account acc:alist){
          acc=map1.get("4.3");
          if(acc.getSKey().equals("4.3")){
             Account attemp=map1.get("4.2");
             acc.addAmount(attemp.getAmount());
             acc.addCredit(attemp.getCamt());
             attemp=map1.get("4.13");
             acc.subAmount(attemp.getAmount());
             acc.subCredit(attemp.getCamt());
           //  map1.put(acc.getSKey(), acc);
             acc.setParent("I");
            at.addAccountOnly(acc);
            map1.put(acc.getSKey(), acc);
            // add(acc, at,"I",map1);
             // break;
          }
          acc=map1.get("4.4");
          if(acc.getSKey().equals("4.4")){
              acc.setParent("I");
              at.addAccount(acc);
           // map1.put(at.getSKey(), at);
              map1.put(acc.getSKey(), acc);
            //  add(acc,at,"I",map1);
              //break;
          }
          //calculateStock(sdate, true);
          acc=map1.get("4.14");
          if(acc==null){
             acc=new Account();
             acc.setName("Opening Stock");
             acc.setSKey("4.14");
          }
          acc.setParent("E");
          //at.addAccount(acc);
          atexpence.addAccount(acc);
           // map1.put(at.getSKey(), at);
          map1.put(acc.getSKey(), acc);
          //add(acc, at,"I",map1);
          Calendar cal=Calendar.getInstance();
          cal.setTimeInMillis(edate.getTime());
          Date d=new Date();
          cal.add(Calendar.DATE, 1);
          d.setTime(cal.getTimeInMillis());
          calculateStock(d, false);
     // }
      //cost of goods sold
     // for(Account acc:alist){
          acc=map1.get("CI");
          if(acc!=null){
            acc.setParent("E");
            //atexpence.addAccount(acc);
            at.addAccount(acc);
            map1.put(acc.getSKey(), acc);
              //add(acc, atexpence,"E",map1);
          }
          acc=map1.get("4.5");
          if(acc.getSKey().equals("4.5")){
             // calculateStock(sdate, true);
            /*  Calendar cal=Calendar.getInstance();
              cal.setTimeInMillis(edate.getTime());
              Date d=new Date();
              cal.add(Calendar.DATE, 1);
              d.setTime(cal.getTimeInMillis());
              calculateStock(d, false);
              Account Acc1=map1.get("4.5.1.2.1");
              String skey="4.5.1.2.1";
              int flag=0;
              
              Acc1.setDC(Acc1.getAmount() * -1, 0.0);
              map1.put(Acc1.getSKey(), Acc1);
              double debit=Acc1.getAmount();
              while(flag==0){
                 
                 skey =skey.substring(0,skey.lastIndexOf("."));
                 Acc1=map1.get(skey);
                 Acc1.addAmount(debit);
                 if(skey.length()<=1){
                    flag=1;
                 }
              }
              Acc1=map1.get("4.5.1.2.2");

              skey="4.5.1.2.2";
              flag=0;
              debit=Acc1.getAmount();
              while(flag==0){
                 skey =skey.substring(0,skey.lastIndexOf("."));
                 Acc1=map1.get(skey);
                 Acc1.addAmount(debit);
                 if(skey.length()<=1){
                    flag=1;
                 }
              }*/
              acc.setParent("I");
              //atexpence.addAccount(acc);
              at.addAccount(acc);
              map1.put(acc.getSKey(), acc);
              //add(acc,atexpence,"E",map1);
            //  acc=map1.get(acc);
             // break;
          }
     // }
      //gross profit
     /* for(Account acc:alist){
          if(acc.getSKey().equals("4.6")){
             AccountTree attemp=map.get("4.3");
             acc.addAmount(attemp.getDebit());
             acc.addCredit(attemp.getCredit());
             attemp=map.get("4.5");
             acc.subAmount(attemp.getDebit());
             acc.subCredit(attemp.getCredit());
             add(acc, atexpence,"E",map1);
              break;
          }
      }*/
      //Operating Expenses
     // for(Account acc:alist){
          acc=map1.get("4.7");
          if(acc.getSKey().equals("4.7")){
            acc.setParent("E");
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
             // add(acc,atexpence,"E",map1);
             // break;
          }
     // }
      //Operating Income
    /*  for(Account acc:alist){
          if(acc.getSKey().equals("4.8")){
             AccountTree attemp=map.get("4.6");
             acc.addAmount(attemp.getDebit());
             acc.addCredit(attemp.getCredit());
             attemp=map.get("4.7");
             acc.subAmount(attemp.getDebit());
             acc.subCredit(attemp.getCredit());
             add(acc, at,"I",map1);
              break;
          }
      }*/
      //other Income
      //for(Account acc:alist){
          
     // }
      //other Expense
     // for(Account acc:alist){
          acc=map1.get("4.9");
          if(acc.getSKey().equals("4.9")){
            acc.setParent("E");
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
            //  add(acc,atexpence,"E",map1);
            //  break;
          }
    //  }
      //operating Profit before tax
     /* for(Account acc:alist){
          if(acc.getSKey().equals("4.10")){
             AccountTree attemp=map.get("4.8");
             acc.addAmount(attemp.getDebit());
             acc.addCredit(attemp.getCredit());
             attemp=map.get("4.4");
             acc.addAmount(attemp.getDebit());
             acc.addCredit(attemp.getCredit());
             attemp=map.get("4.9");
             acc.subAmount(attemp.getDebit());
             acc.subCredit(attemp.getCredit());
             add(acc, atexpence,"E",map1);
              break;
          }
      }*/
      //Income tax
      //for(Account acc:alist){
          acc=map1.get("4.11");
          if(acc.getSKey().equals("4.11")){
            acc.setParent("E");
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
             // add(acc,atexpence,"E",map1);
             // break;
          }
     // }
      //net profit
     /* for(Account acc:alist){
          if(acc.getSKey().equals("4.12")){
             AccountTree attemp=map.get("4.10");
             acc.addAmount(attemp.getDebit());
             acc.addCredit(attemp.getCredit());
             attemp=map.get("4.11");
             acc.subAmount(attemp.getDebit());
             acc.subCredit(attemp.getCredit());
             add(acc, atexpence,"E",map1);
              break;
          }
      }*/
     /* Account atree=new Account();
      Account atID=new Account();
      atID.setName("Increase/Decrease in inventory");
      atID.setSKey("4.5.1.3");
      atID.setParent("E");
      map.put("4.5.1.3.1", atree);
      calculateStock(sdate, true);
      Account a1=new Account();
      a1.setName("Opening Inventory");
      a1.setParent("4.5.1.3");
      a1.setDC(atree.getAmount(), 0.0);
      a1.setSKey("4.5.1.3.1");
      atID.getAccountList().add(a1);
      atID.addDC(a1.getAmount(), 0.0);
      atree=new Account();
      a1=new Account();
      map.put("4.5.1.3.2", atree);
      Calendar cal=Calendar.getInstance();
      cal.setTimeInMillis(edate.getTime());
      Date d=new Date();
      cal.add(Calendar.DATE, 1);
      d.setTime(cal.getTimeInMillis());
      calculateStock(d, false);
      a1.setName("Closing Inventory");
      a1.setParent("4.5.1.3");
      a1.setDC(-1 * atree.getAmount(), 0.0);
      a1.setSKey("4.5.1.3.2");
      atID.getAccountList().add(a1);
      atID.addDC(a1.getAmount(), 0.0);
      map.put("4.5.1.3", atID);
      atexpence.getAccountList().add(atID);
      atexpence.addDC(atID.getAmount(), 0.0);*/
      double iamt=at.getCamt()-at.getAmount();
      double eamt=atexpence.getAmount()-atexpence.getCamt();
      if(iamt!=eamt){
          if(iamt>eamt){
              Account acc1=new Account();
              acc1.setName("Excess Of Income Over Expense");
              acc1.setDC(iamt-eamt, 0);
              acc1.setSKey("EIE");
              acc1.setParent("IE");
              map1.put("EIE", acc1);
              t.getAccountList().add(acc1);
          }else{
              Account acc1=new Account();
              acc1.setName("Excess Of  Expense Over Income");
              acc1.setDC(0, eamt-iamt);
              acc1.setSKey("EEI");
              acc1.setParent("IE");
              map1.put("EEI", acc1);
              t.getAccountList().add(acc1);
          }
      }
      map1.put("I", at);
       map1.put("E", atexpence);
       //t.setDC(at.getAmount()+atexpence.getAmount(), at.getCamt()-atexpence.getCamt());
       ie=new Account(t);
       map1.put("IE", t);
     /* for(Account acc:alist){
          if(acc.getSKey().equals("4.1")){
              add(acc,at);
          }else if(acc.getSKey().equals("4.2")){
             AccountTree attemp=map1.get("4.1");
             acc.setAmount(attemp.getDebit());
             acc.setCredit(attemp.getCredit());
             add(acc, at);
          }else if(acc.getSKey().equals("4.3")){
               add(acc,at);
          }else if(acc.getSKey().equals("4.13")){
               add(acc,at);
          }else if(acc.getSKey().equals("4.3")){
               add(acc,at);
          }

      }*/
    /*  AccountTree at1=map1.get("4.1");
      //at1.get
      for(Account acc: eacclist){
           if(acc.getSKey().equals("4.1")){
               acc.setParent("I");
               AccountTree at=map.get("I");
               at.getAccountList().add(acc);
               while(map.containsKey(acc.getParent())){

               }
           }
      }*/
    }
     private void treestructure(Date start,Date end) throws BasicException{
      map1=new HashMap<String,Account>();
      List<String> templist=new ArrayList();
      List<Account> eacclist=CalculateFromTrailBalance(start,end);
      int flag=0,i=0;
      for(Account acc: eacclist){
         if(map1.containsKey(acc.getParent())){
             map1.put(acc.getSKey(), acc);
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             String tparent=null;
             Account at;
             flag=0;
             
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             if(flag==0){
               at.getAccountList().add(acc);
               flag=1;
             }
            if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map1.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
                flag=1;
            }else if(flag==1){
                flag=2;
            } else if(flag==2){
                parent="";
              flag=3;
            } else if(flag==3){
                flag=4;
               break;
            }

          }
         }else{
             //if the account does not exist include it
             Account acc1=new Account();
             acc1.setSKey(acc.getParent());
             acc1.setParent("No Parent");
             //acc1.getAccountList().add(acc);
             map1.put(acc.getParent(), acc1);
             map1.put(acc.getSKey(), acc);
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             Account at;
             flag=0;
             String tparent=null;
             //loop through its parents to update the debit and credit
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             if(flag==0){
                 at.getAccountList().add(acc);
                flag=1;
             }
          if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map1.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
                flag=1;
            }else if(flag==1){
                flag=2;
            } else if(flag==2){
                parent="";
              flag=3;
            } else if(flag==3){
                flag=4;
               break;
            }

          }
            // System.out.println(acc.getparent());
             if(acc.getSign().equals("S"))
             {
                 if(!templist.contains(acc.getParent())){
                     templist.add(acc.getParent());
                 }
             }
         }
      }
      for(String temp:templist){
           Account at=map1.get(temp);
           Collections.sort(at.getAccountList());
           map1.put(temp, at);
      }
    //  return map1;
    }
    public  AbstractTreeTableModel treetableModel(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

       // public treetableModel(Object data) {
       //     super(data);
      //  }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false, false
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }


        @Override
            public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
            }

        public Object getValueAt(Object node, int column) {
            Account a=(Account) node;
            double amount=0;
            try{
               // if(map1.containsKey(a.getSKey())){
                   // Account at=map1.get(a.getSKey());
                    // amount=at.getCamt()-at.getAmount();
                /*else
              if(map.containsKey(a.getSKey())){
                Account at=map.get(a.getSKey());
                amount=at.getCamt()-at.getAmount();
              }*///else{


                amount=a.getCamt()-a.getAmount();
                a.setVisible(true);
                map1.put(a.getSKey(), a);
             // }
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1: if(amount>=0)
                            return Formats.ConvertDoubleToString(amount)+" Cr";
                        else
                            return Formats.ConvertDoubleToString(amount *-1)+" Dr";
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
           Account del=map1.get(a.getSKey());

            if(a!=null && map1!=null && map1.get(a.getSKey())!=null ){
                
                  return map1.get(a.getSKey()).getAccountList().get(index);
            }else
               return null;
        }

        public int getChildCount(Object parent) {
           Account a=(Account) parent;
           Account del=map1.get(a.getSKey());
           if(a!=null && map1!=null && map1.get(a.getSKey())!=null ){
               
               return map1.get(a.getSKey()).getAccountList().size();
           }else
               return 0;
        }
        };
    }
    private Account getAccountbySearchkey(String skey) throws BasicException{
        Account acc=(Account)new PreparedSentence(m_App.getSession(), "SELECT NAME,ID,SEARCHKEY,0.0 FROM ACCOUNTMASTER WHERE SEARCHKEY=?"
                       , SerializerWriteString.INSTANCE, new SerializerReadClass(Account.class)).find(skey);
        return acc;
    }
    public void Calculate(String parentSkey) throws BasicException{
          treestructure(sdate, edate);
          generateIncomeAndExpenditureStatement();
    }
   
    public  List<StockBean> getList()
    {
      /* if(temp!=null){
            Map<String, String> wareHouse = getWareHouseNames();
            Map<String, String> prod = getProductNames();
            List<StockBean> list = new ArrayList<StockBean>();
           Set<String> s = temp.keySet();
           List<Map<String, Map<String, Double>>> l = new ArrayList<Map<String, Map<String, Double>>>();
      
           StockBean sbTemp = null;
            for (Map.Entry<String, Map<String, Double>> entry : temp.entrySet()) {
                String string = entry.getKey();
               // Account account = entry.getValue();
                
                Map<String, Double> m = temp.get(string);
                 
                for (Map.Entry<String, Double> entry1 : m.entrySet()) {
                    String string1 = entry1.getKey();
                    Double double1 = entry1.getValue();
                   sbTemp = new StockBean();
                    if(string1.equals("A"))
                    {
                        //sbTemp.setAmount(Math.round(m.get("A")));
                    }
                    else if(string1.equals("R"))
                    {
                        //sbTemp.setRate(Math.round(m.get("R")));
                    }
                    else if(string1.equals("T"))
                    {
                        //sbTemp.setTotal(Math.round(m.get("T")));
                    }
                    else
                    {
                        if(m.get("T")!=null)
                        {
                        sbTemp.setTotal(Math.round(m.get("T")*100)/100.00);
                        }
                        if(m.get("R")!=null)
                        {
                        sbTemp.setRate(Math.round(m.get("R")*100)/100.00);
                        }
                        if(m.get("A")!=null)
                        {
                        sbTemp.setAmount(Math.round(m.get("A")*100)/100.00);
                        }
                        
                    sbTemp.setWareHouse(wareHouse.get(string1));
                    sbTemp.setQty(Math.round(double1*100)/100.00);
                    
                    sbTemp.setProductName(prod.get(string));
                    if(double1!=0.0)
                    {
                    list.add(sbTemp);
                    }}
                }
                 
                
            }
            
        return list;
        }
        
        else
            return new ArrayList<StockBean>(); */
        List<String> lst=new ArrayList<String>();
         if(temp!=null){
            Map<String, String> war = new HashMap<String, String>();
            Map<String, String> wareHouse = getWareHouseNames();
            Map<String, String> prod = getProductNames();
            List<StockBean> list = new ArrayList<StockBean>();
           Set<String> s = temp.keySet();
          
           StockBean sbTemp = null;
            for (Map.Entry<String, Map<String, Double>> entry : temp.entrySet()) {
                String string = entry.getKey();
               // Account account = entry.getValue();
                
                Map<String, Double> m = temp.get(string);
                //||m.get("A")==0.0,||m.get("R")==0.0,||m.get("T")==0.0  m.get("R")==null||m.get("A")==null
                 if( m.get("A")==null||m.get("R")==null)
               {
                //lst.add(string);
               }
                 else{
                
                  sbTemp = new StockBean();
                  for (Map.Entry<String, Double> entry1 : m.entrySet()) {
                    String string1 = entry1.getKey();
                    Double double1 = entry1.getValue();
                   
                    if(string1.equals("A"))
                    {
                        //sbTemp.setAmount(Math.round(m.get("A")));
                    }
                    else if(string1.equals("R"))
                    {
                        //sbTemp.setRate(Math.round(m.get("R")));
                    }
                    else if(string1.equals("T"))
                    {
                        //sbTemp.setTotal(Math.round(m.get("T")));
                    }
                    else
                    {
                        if(m.get("T")!=null)
                        {
                        sbTemp.setTotQty(Math.round(m.get("T")*100)/100.00);
                        }
                        
                        if(m.get("R")!=null)
                        {
                        sbTemp.setRate(Math.round(m.get("R")*100)/100.00);
                        }
                        if(m.get("A")!=null)
                        {
                        sbTemp.setTotAmount(Math.round(m.get("A")*100)/100.00);
                        }
                        
                        if(sbTemp.getW1()==null)
                        {
                            sbTemp.setW1(wareHouse.get(string1));
                            sbTemp.setQ1(Math.round(double1*100)/100.00);
                        }
                        else if(sbTemp.getW2()==null)
                        {
                            sbTemp.setW2(wareHouse.get(string1));
                            sbTemp.setQ2(Math.round(double1*100)/100.00);
                        
                        }
                        
                        else if(sbTemp.getW3()==null)
                        {
                            sbTemp.setW3(wareHouse.get(string1));
                            sbTemp.setQ3(Math.round(double1*100)/100.00);
                        
                        }
                        
                        else if(sbTemp.getW4()==null)
                        {
                            sbTemp.setW4(wareHouse.get(string1));
                            sbTemp.setQ4(Math.round(double1*100)/100.00);
                        
                        }
                        
                        else if(sbTemp.getW5()==null)
                        {
                            sbTemp.setW5(wareHouse.get(string1));
                            sbTemp.setQ5(Math.round(double1*100)/100.00);
                        
                        }
                        else if(sbTemp.getW6()==null)
                        {
                            sbTemp.setW6(wareHouse.get(string1));
                            sbTemp.setQ6(Math.round(double1*100)/100.00);
                        
                        }
                        else if(sbTemp.getW7()==null)
                        {
                            sbTemp.setW7(wareHouse.get(string1));
                            sbTemp.setQ7(Math.round(double1*100)/100.00);
                        
                        }
                        else if(sbTemp.getW8()==null)
                        {
                            sbTemp.setW8(wareHouse.get(string1));
                            sbTemp.setQ8(Math.round(double1*100)/100.00);
                        
                        }
                        else if(sbTemp.getW9()==null)
                        {
                            sbTemp.setW9(wareHouse.get(string1));
                            sbTemp.setQ9(Math.round(double1*100)/100.00);
                        
                        }
                        else if(sbTemp.getW10()==null)
                        {
                            sbTemp.setW10(wareHouse.get(string1));
                            sbTemp.setQ10(Math.round(double1*100)/100.00);
                        
                        }
                       /*   else if(sbTemp.getW11()==null)
                        {
                            sbTemp.setW11(wareHouse.get(string1));
                            sbTemp.setQ11(Math.round(double1*100)/100.00);
                        
                        }                     
                         else if(sbTemp.getW12()==null)
                        {
                            sbTemp.setW12(wareHouse.get(string1));
                            sbTemp.setQ12(Math.round(double1*100)/100.00);
                        
                        }
                        
                         else if(sbTemp.getW13()==null)
                        {
                            sbTemp.setW13(wareHouse.get(string1));
                            sbTemp.setQ13(Math.round(double1*100)/100.00);
                        
                        }
                         else if(sbTemp.getW14()==null)
                        {
                            sbTemp.setW14(wareHouse.get(string1));
                            sbTemp.setQ14(Math.round(double1*100)/100.00);
                        
                        }
                         else if(sbTemp.getW15()==null)
                        {
                            sbTemp.setW15(wareHouse.get(string1));
                            sbTemp.setQ15(Math.round(double1*100)/100.00);
                        
                        } */
                        
 
                        sbTemp.setProduct(prod.get(string));
                        if(!war.containsKey(string1))
                        {
                            war.put(string1, wareHouse.get(string1));
                        }
                        
                   }
                }
            list.add(sbTemp); 
               }
            
            }
         wareHouseNames = war;
        return list;
        }
        else
            return new ArrayList<StockBean>();
         
        
    }
   
     public Map<String, String> getWHN()
    {
        if(wareHouseNames!=null)
        {
            return wareHouseNames;
        }
        else
           return new HashMap<String, String>();
    }
    
    public Map<String, String> getWareHouseNames()
    {
        try {
            List<Object[]> l = new PreparedSentence(m_App.getSession(), "SELECT ID,NAME FROM LOCATIONS"
                           , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list();
            
            Map<String, String> m = new HashMap<String, String>();
            for (Iterator<Object[]> it = l.iterator(); it.hasNext();) {
                Object[] q = it.next();
                m.put(q[0].toString(), q[1].toString());
            }
            
            return m;
        } catch (BasicException ex) {
            Logger.getLogger(CalculationLogicTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public Map<String, String> getWareHouseNames2()
    {
        try {
            //Taking only Store id and name 1st. 
            List<Object[]> s = new PreparedSentence(m_App.getSession(), "SELECT ID,NAME FROM LOCATIONS where  prefix != '' and parent is null and facility is null and customercurrentaccount is null"
                           , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list();
            
            
            Map<String, String> m = new HashMap<String, String>();
            m.put(s.get(0)[0].toString(), s.get(0)[1].toString());//Adding Store ID and NAME to MAP
            
             List<Object[]> l = new PreparedSentence(m_App.getSession(), "SELECT ID,NAME FROM LOCATIONS where  prefix != '' and parent = ? "
                           , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list(s.get(0)[0].toString());
            
           
            
            for (Iterator<Object[]> it = l.iterator(); it.hasNext();) {
                Object[] q = it.next();
                m.put(q[0].toString(), q[1].toString());
            }
            
            return m;
        } catch (BasicException ex) {
            Logger.getLogger(CalculationLogicTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Map<String, String> getProductNames()
    {
        try {
            List<Object[]> l = new PreparedSentence(m_App.getSession(), "SELECT ID,NAME FROM PRODUCTS"
                           , SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING})).list();
            
            Map<String, String> m = new HashMap<String, String>();
            for (Iterator<Object[]> it = l.iterator(); it.hasNext();) {
                Object[] q = it.next();
                m.put(q[0].toString(), q[1].toString());
            }
            
            return m;
        } catch (BasicException ex) {
            Logger.getLogger(CalculationLogicTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public class StockBean implements Comparable<StockBean>
    {
        /*String productName;
        String wareHouse;
        double qty;
        double amount;
        double rate;
        double total;
        
        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getWareHouse() {
            return wareHouse;
        }

        public void setWareHouse(String wareHouse) {
            this.wareHouse = wareHouse;
        }

        public double getQty() {
            return qty;
        }

        public void setQty(double qty) {
            this.qty = qty;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }*/
        
        private String product;
        private double totQty;
        private double rate;
        private double totAmount;
        private String w1;
        private String w2;
        private String w3;
        private String w4;
        private String w5;
        private double q1;
        private double q2;
        private double q3;
        private double q4;
        private double q5;
        private String w10;
        private String w6;
        private String w7;
        private String w8;
        private String w9;
        private double q6;
        private double q7;
        private double q8;
        private double q9;
        private double q10;
        //private String w11;
      //  private String w12;
       // private String w13;
       // private String w14;
       // private String w15;
       // private double q11;
       // private double q12;
       // private double q13;
       // private double q14;
       // private double q15;

      

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public double getTotQty() {
            return totQty;
        }

        public void setTotQty(double totQty) {
            this.totQty = totQty;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getTotAmount() {
            return totAmount;
        }

        public void setTotAmount(double totAmount) {
            this.totAmount = totAmount;
        }

        public String getW1() {
            return w1;
        }

        public void setW1(String w1) {
            this.w1 = w1;
        }

        public String getW2() {
            return w2;
        }

        public void setW2(String w2) {
            this.w2 = w2;
        }

        public String getW3() {
            return w3;
        }

        public void setW3(String w3) {
            this.w3 = w3;
        }

        public String getW4() {
            return w4;
        }

        public void setW4(String w4) {
            this.w4 = w4;
        }

        public String getW5() {
            return w5;
        }

        public void setW5(String w5) {
            this.w5 = w5;
        }

        public double getQ1() {
            return q1;
        }

        public void setQ1(double q1) {
            this.q1 = q1;
        }

        public double getQ2() {
            return q2;
        }

        public void setQ2(double q2) {
            this.q2 = q2;
        }

        public double getQ3() {
            return q3;
        }

        public void setQ3(double q3) {
            this.q3 = q3;
        }

        public double getQ4() {
            return q4;
        }

        public void setQ4(double q4) {
            this.q4 = q4;
        }

        public double getQ5() {
            return q5;
        }

        public void setQ5(double q5) {
            this.q5 = q5;
        }

        public String getW10() {
            return w10;
        }

        public void setW10(String w10) {
            this.w10 = w10;
        }

        public String getW6() {
            return w6;
        }

        public void setW6(String w6) {
            this.w6 = w6;
        }

        public String getW7() {
            return w7;
        }

        public void setW7(String w7) {
            this.w7 = w7;
        }

        public String getW8() {
            return w8;
        }

        public void setW8(String w8) {
            this.w8 = w8;
        }

        public String getW9() {
            return w9;
        }

        public void setW9(String w9) {
            this.w9 = w9;
        }

        public double getQ6() {
            return q6;
        }

        public void setQ6(double q6) {
            this.q6 = q6;
        }

        public double getQ7() {
            return q7;
        }

        public void setQ7(double q7) {
            this.q7 = q7;
        }

        public double getQ8() {
            return q8;
        }

        public void setQ8(double q8) {
            this.q8 = q8;
        }

        public double getQ9() {
            return q9;
        }

        public void setQ9(double q9) {
            this.q9 = q9;
        }

        public double getQ10() {
            return q10;
        }

        public void setQ10(double q10) {
            this.q10 = q10;
        }

        public int compareTo(StockBean o) {
           return this.product.compareToIgnoreCase(o.product);
        }
        
        
                
        
        
    }

}
  
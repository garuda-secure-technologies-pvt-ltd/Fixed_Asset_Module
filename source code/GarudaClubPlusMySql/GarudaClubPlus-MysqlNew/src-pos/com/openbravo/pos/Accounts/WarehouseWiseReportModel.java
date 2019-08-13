/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadDouble;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.Accounts.date;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.inventory.LocationInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author user
 */
public class WarehouseWiseReportModel {
    
     private List<StockDetailsline > stdl;
      private Date sdate;
    private Date edate;
    private List<Account> acclist;
    private Account ie;
    private Map<String ,Account> map1;
    private AppView m_App;
    private  List<Object[]> stkDiaryList;
    private DataLogicSales dlsales;
    public WarehouseWiseReportModel() {
        map1=new HashMap<String,Account>();
    }

    public WarehouseWiseReportModel(Date sdate, Date edate, AppView m_App,DataLogicSales dlsales) {
        this.dlsales=dlsales;
        this.sdate = sdate;
        this.edate = edate;
        this.m_App = m_App;
         map1=new HashMap<String,Account>();
    }
    

    public List<StockDetailsline> getStockDetailsline() {
        return stdl;
    }

    public void setStockDetailsline(List<StockDetailsline> stdl) {
        this.stdl = stdl;
    }
    public  List<Object[]> executeQuery(String warehoseId) throws BasicException
    {
        Date date=edate;
        stkDiaryList=new PreparedSentence(m_App.getSession(), "select pid,pdtname,cat, ob, pur, rec_, sales,out_, break,insp,lparent,pcat from ( "+
                                                 "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent )as temp where cat=? order by pdtname"
                                                 , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                                                 , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING})
                                                 ).list(new Object[]{date,date,date,date,date,date,date,warehoseId}); 
        
        
        
        
        
        return stkDiaryList;
    }
    
    public Object loadStockData() throws BasicException{
        WarehouseWiseReportModel dbmodel=null;
        Map<String,List<Object[]>> pdtFinalMap=new HashMap<String, List<Object[]>>();
        List<ProductStock> pdtStock=new ArrayList<ProductStock>();
        List<String[]> productList=new ArrayList<String[]>();
        Object[] obj;
        Account atree=new Account();
        Date date=edate;
        String warehouse="a65b86bf-fa79-46cd-b78f-5064d0c9f63d";
       // executeQuery(warehouse);
      /* List<Object[]> stkDiaryList=new PreparedSentence(m_App.getSession(), "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent "
                                                 , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                                                 , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING})
                                                 ).list(new Object[]{date,date,date,date,date,date,date}); 
              
        */
              obj=(Object[])new PreparedSentence(m_App.getSession(), "SELECT AJ.DATE FROM ACCOUNTJOURNAL AJ JOIN ACCOUNTMASTER A ON AJ.ACCOUNTID=A.ID WHERE AJ.DATE<? AND A.ID=? ORDER BY AJ.DATE DESC "
                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.STRING})
                           , new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP})).find(new Object[]{date,"27"});
               
               atree=new Account();
                 atree.setName("Closing Inventory");
                 atree.setSKey("CI");
                 atree.setParent("4");
               //atree=map1.get("4.5.1.2.2");
              // if(atree==null){
               //Lokesh(Closing Inventry from ClosingInventory Table)
                 
                  Date cicDate = new Date();
                 Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            cal.add(Calendar.DATE, -1);
            cicDate.setTime(cal.getTimeInMillis());
            
            
            Object[] obj1 = (Object[]) new StaticSentence(m_App.getSession(), "SELECT VALUE FROM GENERALTABLE where NAME='Reports To Use Manual Inventory Entry'", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.BOOLEAN})).find();
            
             if(obj1!=null && obj1[0]!=null && (Boolean)obj1[0])
             {
                 
               Object values = new StaticSentence(m_App.getSession(), "SELECT AMOUNT FROM CLOSINGINVENTORY WHERE DATE=? AND ACTIVE IS TRUE ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}), SerializerReadDouble.INSTANCE).find(new Object[]{cicDate}); 
               if(values!=null)
               {
                   double to = Double.parseDouble(values.toString());
                   List s = atree.getAccountList();
                   atree.setSubAccList(new ArrayList<Account>());
                       atree.setAmount(-to);
                   atree.setName("Closing Inventory(As Declared)");
                   map1.put(atree.getSKey(), atree);
                    System.out.println(to);
                    return to;
               }}
                if(obj!=null && obj[0]!=null){
            Date d=(Date)obj[0];
            if(date.toString().equals(d.toString())){
               List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME DESC"
                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d});
               pdtStock.addAll(pdtstkTemp);
            }else{
               List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE desc,Pj.RATE, Pj.QTY DESC "
                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d,date});
               pdtStock.addAll(pdtstkTemp);
            }
        }else{
//              List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC "
//                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
//                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{date});
              List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE desc,Pj.RATE, Pj.QTY DESC "
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
        
        stkDiaryList=new PreparedSentence(m_App.getSession(), "select pid,pdtname,cat, ob, pur, rec_, sales,out_, break,insp,lparent,pcat from ( "+
                                                 "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent )as temp where cat=? order by pdtname"
                                                 , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING})
                                                 , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING})
                                                 ).list(new Object[]{date,date,date,date,date,date,date,warehouse}); 
        
        Map<String,Double> pdtStkList=new HashMap<String, Double>();
        List<String> pdtCatList=new ArrayList<String>();
        //calculate stock of each product
        for(Object[] objtemp:stkDiaryList){
            String product= String.valueOf(objtemp[0]);
            String productname= String.valueOf(objtemp[1]);
           // if(productname.startsWith("(BAR")){
            //   String aa="";
           // }
            //String productname= String.valueOf(objtemp[1]);
            String pwarehouse=String.valueOf(objtemp[10]);
            String productCategory=String.valueOf(objtemp[11]);
            if(!pdtCatList.contains(productCategory)){
                pdtCatList.add(productCategory);
            }

            if(pwarehouse.equals("null")){
                 double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                
                 
                 pdtStkList.put(product, value);
            }else{
                LocationInfo linfo=dlsales.getLocationsInfoByID(productCategory);
                if(linfo.getParentID()!=null){

               Object[] objtemp1=(Object[])new PreparedSentence(m_App.getSession()
                                      , " SELECT PRODUCTFST,NOFST,PRODUCTSCN,NOSEC FROM CONVERTER WHERE PRODUCTSCN=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE
                                      , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.INT,Datas.STRING,Datas.INT})).find(product);
               if(objtemp1==null || objtemp1[0]==null){
                   objtemp1=(Object[])new PreparedSentence(m_App.getSession()
                                      , " SELECT PRODUCTFST,NOFST,PRODUCTSCN,NOSEC FROM CONVERTER WHERE PRODUCTFST=? AND ACTIVE=TRUE ",SerializerWriteString.INSTANCE
                                      , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.INT,Datas.STRING,Datas.INT})).find(product);
                   if(objtemp1!=null && objtemp1[0]!=null){
                       product=String.valueOf(objtemp1[2]);
                       double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                       
                       value=(Double.parseDouble(objtemp1[3].toString()) * value)/Double.parseDouble(objtemp1[1].toString());
                       double val=0.0;
                       if(pdtStkList.containsKey(product))
                        val=pdtStkList.get(product);
                       pdtStkList.put(product, value+val);
                   }
               }else{
                   product=String.valueOf(objtemp1[0]);
                   double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
                   value=(Double.parseDouble(objtemp1[1].toString()) * value)/Double.parseDouble(objtemp1[3].toString());
                   double val=0.0;
                   if(pdtStkList.containsKey(product))
                        val=pdtStkList.get(product);
                   pdtStkList.put(product, value+val);
               }
            }else{
                double value=Double.parseDouble(objtemp[3].toString())+Double.parseDouble(objtemp[4].toString())+Double.parseDouble(objtemp[5].toString())+Double.parseDouble(objtemp[6].toString())+Double.parseDouble(objtemp[7].toString())-Double.parseDouble(objtemp[8].toString())+Double.parseDouble(objtemp[9].toString());
               
                pdtStkList.put(product, value);
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
            for(ProductStock pstk:plist){
             // if(pstk.getProductID().equals(p)){
                double purLineQty=pstk.getProductQty();
                
                if(qty>purLineQty){
                    qty=qty-purLineQty;
                    Object[] obj2=new Object[]{purLineQty,pstk.getProductRate()};
                    amount+=purLineQty * pstk.getProductRate();
                    total+=purLineQty * pstk.getProductRate();
                    a.addBreakUp(obj2);
                    //objList.add(obj1);
                }else{
                    if(qty<0){
                      String a1="";
                    }
                    a.setQty(oqty);
                    Object[] obj3=new Object[]{qty,pstk.getProductRate()};
                    a.addBreakUp(obj3);
                    amount+=qty * pstk.getProductRate();
                    total+=qty * pstk.getProductRate();
                    a.setSKey(pstk.getProductID());
                    a.setSign("PDT");
                    a.setName(pstk.getProductName()+" ("+Formats.ApproxTo2Decimals(oqty)+")");
                    
                    
                        a.setDC(0.0,amount);
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
        
        
        
       //List s = atree.getAccountList();
        map1.put(atree.getSKey(), atree);
        System.out.println(atree.getAmount());
        System.out.println(total);
       return total;
       // return dbmodel;
    }
    
    
    
    
    
    public static class StockDetailsline implements SerializableRead {
        
         private String warehouseName;
        private Double rate;
        private Double qty;
        private Double total;
        private Double amount;
        private String productName;

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }
        
        
        
        
        public void readValues(DataRead dr) throws BasicException {
            
           warehouseName = dr.getString(1);
            rate = dr.getDouble(2);
            qty= dr.getDouble(3);
            total = dr.getDouble(4);
         productName = dr.getString(5);
            }
    }
}

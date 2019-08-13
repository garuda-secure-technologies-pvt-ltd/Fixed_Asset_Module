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
import com.openbravo.data.loader.SerializerReadDouble;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
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
import javax.swing.JOptionPane;
/**
 *
 * @author swathi
 */
public class CalculationLogic {
    private Date sdate;
    private Date edate;
    private List<Account> acclist;
    private Account ie;
    private Map<String ,Account> map1;
    private AppView m_App;
    private final static String[] HEADERS = {"Account","Amount"};
    private DataLogicSales dlsales;
    private boolean zeroIgnoreFlag=false;
   // private Account CIAccount;
    public CalculationLogic(Date sdate, Date edate,AppView app,DataLogicSales dlsales) {
        this.sdate = sdate;
        this.edate = edate;
        m_App=app;
        map1=new HashMap<String,Account>();
        this.dlsales=dlsales;
    }

    public CalculationLogic() {
        map1=new HashMap<String,Account>();
    }
    public CalculationLogic(Map<String ,Account> map,Date sdate, Date edate,AppView app,DataLogicSales dlsales) {
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
                   atree.setAmount(to);
                   atree.setName("Opening Inventory(Prev. Period Closing)");
                   map1.put(atree.getSKey(), atree);
                    System.out.println(to);
                    return to;
               }}
        
               
               
               
               

        }else{ //enddate
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
               
               //Lokesha
               
               
                 
             //  }
        }
        // inpurchases
        if(obj!=null && obj[0]!=null){
            Date d=(Date)obj[0];
            if(date.toString().equals(d.toString())){
              // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  where COALESCE(Pj.RATE,0.0) !='0.00' ORDER BY PDT.NAME DESC"
                List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<? AND P.DEACTREF IS  NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE   ORDER BY PDT.NAME DESC"    
                       , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d});
               pdtStock.addAll(pdtstkTemp);
            }else{
              // List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE where COALESCE(Pj.RATE,0.0) !='0.00' ORDER BY PDT.NAME,P.CRDATE DESC ,Pj.RATE, Pj.QTY DESC "
                 List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE>=? AND P.CRDATE<? AND P.DEACTREF IS  NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC ,Pj.RATE, Pj.QTY DESC "    
                , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{d,date});
               pdtStock.addAll(pdtstkTemp);
            }
        }else{
//              List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC "
//                           , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})
//                           , new SerializerReadClass(ProductStock.class)).list(new Object[]{date});
            //  List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<?  RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE where COALESCE(Pj.RATE,0.0) !='0.00' ORDER BY PDT.NAME,P.CRDATE DESC , Pj.RATE, Pj.QTY DESC"
               List<ProductStock> pdtstkTemp=new PreparedSentence(m_App.getSession(), "SELECT PDT.ID,PDT.NAME,PR.CATEGORY,COALESCE(Pj.RATE,0.0),COALESCE(Pj.QTY,0.0) FROM  PURCHASEJOURNALMAIN P join purchasejournal pj on p.id=pj.parent and P.CRDATE<? AND P.DEACTREF IS  NULL RIGHT OUTER JOIN PRODUCTS PDT ON Pj.ITEM=PDT.ID JOIN PDT_PRCAT PR ON PDT.ID=PR.ID JOIN LOCATIONS L ON PR.CATEGORY=L.ID AND L.PARENT IS NULL AND L.STOCK=TRUE  ORDER BY PDT.NAME,P.CRDATE DESC , Pj.RATE, Pj.QTY DESC"
                      
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
        List<Object[]> stkDiaryList=new PreparedSentence(m_App.getSession(), "select pid,pdtname,cat,sum(ob) as ob,sum(pur) as pur,sum(res_) as rec_,sum(sales) as sales,sum(out_) as out_,sum(break) as break,sum(insp) as insp,lparent,pcat from ( "+
                                                 " select p.id as pid,p.name as pdtname,p.location as cat,sum(s.units1) as ob,0.0 as pur,0.0 as res_,0.0  as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s on p.id=s.product1 and s.reason1=1 and rno='Opening Balance'  join locations l on p.location=l.id   where datenew < ? group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,sum(s1.units1) as pur ,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=1 and rno!='Opening Balance' join locations l on p.location=l.id    where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,sum(s1.units1) as res_,0.0 as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary s1 on p.id=s1.product1 and s1.reason1=4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,sum(s2.units1) as sales,0.0 as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-1 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,sum(s2.units ) as out_,0.0 as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product and s2.reason=-4 join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                 " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,sum((s2.units1 * -1)) as break,0.0 as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=-3  and ( s2.receivedby!='Rejected' OR s2.receivedby is null )  join locations l on p.location=l.id  where datenew < ?  group by p.name,pid,cat,lparent,pcat "+
                                                " union all select p.id as pid,p.name as pdtname,p.location as cat,0.0 as ob,0.0 as pur,0.0 as res_,0.0 as sales,0.0 as out_,0.0 as break,sum(s2.units1 ) as insp,l.parent as lparent,p.location  as pcat from products p join stockdiary  s2 on p.id=s2.product1 and s2.reason1=5 join locations l on p.location=l.id  where datenew < ? group by p.name,p.id,cat,lparent,pcat ) as stk  group by pdtname,pid,cat,lparent,pcat order by lparent "
                                                 , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
                                                 , new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.DOUBLE,Datas.STRING,Datas.STRING})
                                                 ).list(new Object[]{date,date,date,date,date,date,date});
    

 

//  String godowncat="dd9d4974-5705-4077-b6c3-048873429cf3";
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
            int i=0;
            for(ProductStock pstk:plist){
             // if(pstk.getProductID().equals(p)){
                double purLineQty=pstk.getProductQty();
                
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
                    //objList.add(obj1);
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
        
        
        
       //List s = atree.getAccountList();
        map1.put(atree.getSKey(), atree);
        System.out.println(total);
       return total;
    }
    private List<Account> CalculateFromTrailBalance(Date start,Date end) throws BasicException{
      List<Account> list=new ArrayList<Account>();
//        if(zeroIgnoreFlag){ 
//           list=new PreparedSentence(m_App.getSession()
//                                   , "select * from (SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DEBIT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CREDIT FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  AND AJ.EDATE>=? AND AJ.EDATE<=?\n" +
//" GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS having SUM(AJ.DEBIT+AJ.CURDEBIT)!=0 and SUM(AJ.CREDIT+AJ.CURCREDIT)!=0\n" +
//"                                           union SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DEBIT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CREDIT FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  AND AJ.EDATE>=?  AND AJ.EDATE<=?\n" +
//"GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS having am.parent= '4' or am.searchkey = '4') as temp ORDER BY temp.SEARCHKEY"
//                                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP})
//                                   , new SerializerReadClass(Account.class)).list(new Object[]{start,end,start,end});
//        }else{
            
        list=new PreparedSentence(m_App.getSession()
                                   , "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DEBIT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CREDIT FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  AND AJ.EDATE>=? AND AJ.EDATE<=? "
                                   +"  GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
                                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                                   , new SerializerReadClass(Account.class)).list(new Object[]{start,end});
      //  }
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
    
     
    List<Account>  alist=map1.get("4").getAccountList();
    
     //sales
      //for(Account acc:alist){
      //pratima
       
     
      Account acc=map1.get("4.1");
          if(acc!=null){
           
            acc.setParent("I");
            at.addAccount(acc);
           // map1.put(at.getSKey(), at);
            map1.put(acc.getSKey(), acc);
             // add(acc,at,"I",map1);
             // break;
         }
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
          calculateStock(sdate, true);
         
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
      
      System.out.println("iamt = " + iamt);
      System.out.println("eamt = " + eamt);
      if(iamt!=eamt){
          if(iamt>eamt){
              Account acc1=new Account();
              acc1.setName("Excess Of Income Over Expense");
              acc1.setDC(iamt-eamt, 0);
              acc1.setSKey("EIE");
              acc1.setParent("IE");
              map1.put("EIE", acc1);
              t.getAccountList().add(acc1);
              System.out.println("Excess Of Income Over Expense");
          }else{
              Account acc1=new Account();
              acc1.setName("Excess Of  Expense Over Income");
              acc1.setDC(0, eamt-iamt);
              acc1.setSKey("EEI");
              acc1.setParent("IE");
              map1.put("EEI", acc1);
              t.getAccountList().add(acc1);
              System.out.println("Excess Of  Expense Over Income");
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
    
    ////////////////////////////////////////////////pratima
       public void generateIncomeAndExpenditureStatement1() throws BasicException{
     
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
    
     
     List<Account>  alist=map1.get("4").getAccountList();
      
       Account acc=new Account();
      
       acc=map1.get("4.1");
          if(acc!=null){
            acc.setParent("I");
              if((acc.getCamt()-acc.getAmount())!=0){//p  
            at.addAccount(acc);
           map1.put(acc.getSKey(), acc);
              }
          }
    
       
        acc=map1.get("4.2");
          if(acc!=null){
             
             Account attemp=map1.get("4.1");
             acc.addAmount(attemp.getAmount());
             acc.addCredit(attemp.getCamt());
           
             acc.setParent("I");
             if((acc.getCamt()-acc.getAmount())!=0){//p  
             at.addAccountOnly(acc);
             map1.put(acc.getSKey(), acc);
             }
          }
      
        
     acc=map1.get("4.13");
         if(acc!=null){
            acc.setParent("I");
            if((acc.getCamt()-acc.getAmount())!=0)//p 
               { at.addAccount(acc);
                 map1.put(acc.getSKey(), acc);
               }
          }
          
    
       
        acc=map1.get("4.3");
          if(acc.getSKey().equals("4.3")){
             Account attemp=map1.get("4.2");
             acc.addAmount(attemp.getAmount());
             acc.addCredit(attemp.getCamt());
             attemp=map1.get("4.13");
             acc.subAmount(attemp.getAmount());
             acc.subCredit(attemp.getCamt());
          
             acc.setParent("I");
              if((acc.getCamt()-acc.getAmount())!=0){//p  
            at.addAccountOnly(acc);
            map1.put(acc.getSKey(), acc);
              }
          }
      
        acc=map1.get("4.4");
          if(acc.getSKey().equals("4.4")){
              acc.setParent("I");
             if((acc.getCamt()-acc.getAmount())!=0){//p  
              at.addAccount(acc);
              map1.put(acc.getSKey(), acc);
             }
          }
          calculateStock(sdate, true);
       
          acc=map1.get("4.14");
          if(acc==null){
             acc=new Account();
             acc.setName("Opening Stock");
             acc.setSKey("4.14");
          }
         if((acc.getCamt()-acc.getAmount())!=0){//p  
          acc.setParent("E");
            atexpence.addAccount(acc);
             map1.put(acc.getSKey(), acc);
         }
          Calendar cal=Calendar.getInstance();
          cal.setTimeInMillis(edate.getTime());
          Date d=new Date();
          cal.add(Calendar.DATE, 1);
          d.setTime(cal.getTimeInMillis());
          calculateStock(d, false);
     
        
         acc=map1.get("CI");
          if(acc!=null){
            acc.setParent("E");
            //atexpence.addAccount(acc);
            if((acc.getCamt()-acc.getAmount())!=0){//p  
            at.addAccount(acc);
            map1.put(acc.getSKey(), acc);
            }
          }
        
         acc=map1.get("4.5");
          if(acc.getSKey().equals("4.5")){
         
              acc.setParent("I");
              //atexpence.addAccount(acc);
              if((acc.getCamt()-acc.getAmount())!=0){//p  
              at.addAccount(acc);
              map1.put(acc.getSKey(), acc);
              }
          }
    
  
     acc=map1.get("4.7");
          if(acc.getSKey().equals("4.7")){
            acc.setParent("E");
            if((acc.getCamt()-acc.getAmount())!=0){//p  
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
            }
            // add(acc,atexpence,"E",map1);
             // break;
          }
     
      //other Income
      
    
     acc=map1.get("4.9");
          if(acc.getSKey().equals("4.9")){
            acc.setParent("E");
             if((acc.getCamt()-acc.getAmount())!=0){//p  
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
             }
          }
    //  }
      //operating Profit before tax
     
      //Income tax
      
     
      acc=map1.get("4.11");
          if(acc.getSKey().equals("4.11")){
            acc.setParent("E");
             if((acc.getCamt()-acc.getAmount())!=0){//p  
            atexpence.addAccount(acc);
            map1.put(acc.getSKey(), acc);
             }
          }
    
    
      //net profit
     
      double iamt=at.getCamt()-at.getAmount();
      double eamt=atexpence.getAmount()-atexpence.getCamt();
      
      System.out.println("iamt = " + iamt);
      System.out.println("eamt = " + eamt);
      if(iamt!=eamt){
          if(iamt>eamt){
              Account acc1=new Account();
              acc1.setName("Excess Of Income Over Expense");
              acc1.setDC(iamt-eamt, 0);
              acc1.setSKey("EIE");
              acc1.setParent("IE");
              map1.put("EIE", acc1);
              t.getAccountList().add(acc1);
              System.out.println("Excess Of Income Over Expense");
          }else{
              Account acc1=new Account();
              acc1.setName("Excess Of  Expense Over Income");
              acc1.setDC(0, eamt-iamt);
              acc1.setSKey("EEI");
              acc1.setParent("IE");
              map1.put("EEI", acc1);
              t.getAccountList().add(acc1);
              System.out.println("Excess Of  Expense Over Income");
          }
      }
      for(int i=0;i<at.getSubAccList().size();i++){
        Account subAccat=at.getSubAccList().get(i); 
        System.out.println("subAccat names"+subAccat.getAccountName());
        for(int j=0;j<subAccat.getSubAccList().size();j++){
        Account subOfsubAccat=subAccat.getSubAccList().get(j);
               if((subOfsubAccat.getCamt()-subOfsubAccat.getAmount())==0){
                    map1.remove(subOfsubAccat.getSKey());
                    subAccat.getSubAccList().remove(j);
                    j--;
               }else{
                   for(int k=0;k<subOfsubAccat.getSubAccList().size();k++){
                        Account subOfsubOfsubAccat=subOfsubAccat.getSubAccList().get(k);
                        if((subOfsubOfsubAccat.getCamt()-subOfsubOfsubAccat.getAmount())==0){
                        map1.remove(subOfsubOfsubAccat.getSKey());
                        subOfsubAccat.getSubAccList().remove(k);
                        k--;
                        }else{
                                for(int l=0;l<subOfsubOfsubAccat.getSubAccList().size();l++){
                                    Account s_subOfsubOfsubAccat=subOfsubOfsubAccat.getSubAccList().get(l);
                                    if((s_subOfsubOfsubAccat.getCamt()-s_subOfsubOfsubAccat.getAmount())==0){
                                    map1.remove(s_subOfsubOfsubAccat.getSKey());
                                    subOfsubOfsubAccat.getSubAccList().remove(l);
                                    l--;
                                    }
                                }
                            }
                    }
                }
        }   
      }
      
       for(int i=0;i<atexpence.getSubAccList().size();i++){
        Account subAccat=atexpence.getSubAccList().get(i); 
        System.out.println("subAccat names"+subAccat.getAccountName());
        for(int j=0;j<subAccat.getSubAccList().size();j++){
        Account subOfsubAccat=subAccat.getSubAccList().get(j);
               if((subOfsubAccat.getCamt()-subOfsubAccat.getAmount())==0){
                    map1.remove(subOfsubAccat.getSKey());
                    subAccat.getSubAccList().remove(j);
                    j--;
               }else{
                   for(int k=0;k<subOfsubAccat.getSubAccList().size();k++){
                        Account subOfsubOfsubAccat=subOfsubAccat.getSubAccList().get(k);
                        if((subOfsubOfsubAccat.getCamt()-subOfsubOfsubAccat.getAmount())==0){
                        map1.remove(subOfsubOfsubAccat.getSKey());
                        subOfsubAccat.getSubAccList().remove(k);
                        k--;
                        }else{
                                for(int l=0;l<subOfsubOfsubAccat.getSubAccList().size();l++){
                                    Account s_subOfsubOfsubAccat=subOfsubOfsubAccat.getSubAccList().get(l);
                                    if((s_subOfsubOfsubAccat.getCamt()-s_subOfsubOfsubAccat.getAmount())==0){
                                    map1.remove(s_subOfsubOfsubAccat.getSKey());
                                    subOfsubOfsubAccat.getSubAccList().remove(l);
                                    l--;
                                    }
                                }
                            }
                    }
                }
        }   
      }
      
      map1.put("I", at);
       map1.put("E", atexpence);
       //t.setDC(at.getAmount()+atexpence.getAmount(), at.getCamt()-atexpence.getCamt());
       ie=new Account(t);
       map1.put("IE", t);
     
    
    }
       
    /////////////////////////////////////////////ended by pratima
    
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
         if(zeroIgnoreFlag) generateIncomeAndExpenditureStatement1();
             else   generateIncomeAndExpenditureStatement();
    }
    public void setZeroIgnoreFlag(boolean flag){
    if(flag)
    {
        this.zeroIgnoreFlag=true;
    }
    else {
        this.zeroIgnoreFlag=false;
    }
    }
}

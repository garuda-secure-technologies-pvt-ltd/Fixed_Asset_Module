/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.get;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class ProductWiseTotalModel {
     private List<productTotalSales> productlist;
     private List<productTotalSales1> productlist1;
    private productTotalSales psales;
     DecimalFormat decimalFormat = new DecimalFormat("#0.00");
   
    public ProductWiseTotalModel() {
    }

    
    public static ProductWiseTotalModel loadInstance(AppView app,  Date FmDate , Date ToDate ) throws BasicException {
        ProductWiseTotalModel pm = new ProductWiseTotalModel();
        List<ProductWiseTotalModel.productTotalSales> li = new StaticSentence(app.getSession(),
                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),SUM(TAX1),SUM(TAX2),SUM(TAX3),(SUM(QUANTITY*RATE)+SUM(TAX1)+SUM(TAX2)+SUM(TAX3)) AS TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM\n" +
"(SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
"SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID\n" +
"JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
"GROUP BY L.NAME,C.NAME ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
"UNION ALL\n" +
"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
"SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,C.NAME AS CATNAME,\n" +
"P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID\n" +
"JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
"GROUP BY L.NAME,C.NAME  ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS PRODUCT GROUP BY LOCNAME,CATNAME ,PRODUCT_NAME ,PID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
"ORDER BY 10;",

//                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY L.NAME,C.NAME  ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY LOCNAME,CATNAME ,PRODUCT_NAME ,PID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 10;",
//                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//" FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//" WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY LOCNAME,CATNAME ,PID ,PRODUCT_NAME ,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 10;",
//  "SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
// 
//        "SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3, (SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,I.DMULTIPLY AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3, (SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ProductWiseTotalModel.productTotalSales.class)).list(new Object[]{ FmDate ,  ToDate , FmDate ,  ToDate  });
//       for(int i=0;i<li.size();i++){
//           System.out.println("list values are::"+li.get(i).getTax1());
//       }
       
       if (li == null) {
            pm.productlist = new ArrayList<ProductWiseTotalModel.productTotalSales>();
        } else {
            pm.productlist = li;
        }
        return pm;
    }
    public static ProductWiseTotalModel loadInstance1(AppView app, Date FmDate , Date ToDate , String WID) throws BasicException {
        ProductWiseTotalModel pm = new ProductWiseTotalModel();
        List<ProductWiseTotalModel.productTotalSales> li = new StaticSentence(app.getSession(),
                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),SUM(TAX1),SUM(TAX2),SUM(TAX3),(SUM(QUANTITY*RATE)+SUM(TAX1)+SUM(TAX2)+SUM(TAX3)) AS TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
"GROUP BY L.NAME,C.NAME ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
"UNION ALL\n" +
"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
"SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
"GROUP BY L.NAME,C.NAME  ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS PRODUCT GROUP BY LOCNAME,CATNAME ,PRODUCT_NAME ,PID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
"ORDER BY 10;",
//                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
//"GROUP BY L.NAME,C.NAME  ,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY LOCNAME,CATNAME ,PRODUCT_NAME ,PID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 10;",
//                "SELECT PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//" FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//" WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=? AND B.WAREHOUSE=?\n" +
//"GROUP BY L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY LOCNAME,CATNAME ,PID ,PRODUCT_NAME ,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 10",
//                 "SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
// 
//        "SELECT P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,I.DMULTIPLY AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? AND B.WAREHOUSE=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING,Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ProductWiseTotalModel.productTotalSales.class)).list(new Object[]{ FmDate ,  ToDate ,WID, FmDate ,  ToDate , WID});
//       for(int i=0;i<li.size();i++){
//           System.out.println("list values are::"+li.get(i).getName());
//       
//       }
       
       if (li == null) {
            pm.productlist = new ArrayList<ProductWiseTotalModel.productTotalSales>();
        } else {
            pm.productlist = li;
        }
        return pm;
    }
     public static ProductWiseTotalModel loadInstance2(AppView app,  Date FmDate , Date ToDate ) throws BasicException {
        ProductWiseTotalModel pm = new ProductWiseTotalModel();
        List<ProductWiseTotalModel.productTotalSales1> li = new StaticSentence(app.getSession(),
                "SELECT IFNULL (HSN_CODE,''),PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),SUM(TAX1),SUM(TAX2),SUM(TAX3),(SUM(QUANTITY*RATE)+SUM(TAX1)+SUM(TAX2)+SUM(TAX3)) AS TOTAL,LOCNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT UPPER(IFNULL(P.HSN_Code,'')) AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,P.ID AS PID,\n" +
"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
"GROUP BY P.HSN_Code,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
"UNION ALL\n" +
"SELECT UPPER(IFNULL(P.HSN_Code,'')) AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
"SUM(I.TAX1) AS TAX1,SUM(I.TAX2) AS TAX2,SUM(I.TAX3) AS TAX3,L.NAME AS LOCNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
"WHERE B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
"GROUP BY P.HSN_Code,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS PRODUCT GROUP BY HSN_CODE,PRODUCT_NAME ,BID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
"ORDER BY 1 DESC;",
//               "SELECT IFNULL (HSN_CODE,''),PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT UPPER(IFNULL(P.HSN_Code,'')) AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT UPPER(IFNULL(P.HSN_Code,'')) AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=?  AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,P.NAME ,P.ID,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY HSN_CODE,PRODUCT_NAME ,BID,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 1 DESC;",
//                "SELECT HSN_CODE,PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.HSN_Code AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.HSN_Code AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=?  AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY HSN_CODE,PID ,PRODUCT_NAME ,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 1 DESC;",
//               "SELECT HSN_CODE,PRODUCT_NAME,BID,SUM(QUANTITY),RATE,SUM(AMOUNT),TAX1,TAX2,TAX3,TOTAL,LOCNAME,CATNAME,PID,TAX1ID,TAX2ID,TAX3ID FROM (SELECT P.HSN_Code AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,\n" +
//"I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID  JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE  B.CREATEDDATE>=? AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID\n" +
//"UNION ALL\n" +
//"SELECT P.HSN_Code AS HSN_CODE,P.NAME AS PRODUCT_NAME,I.PRODUCT AS BID,\n" +
//"SUM(I.DMULTIPLY)AS QUANTITY,I.RATE AS RATE ,SUM(I.DMULTIPLY*I.RATE) AS AMOUNT,\n" +
//"I.TAX1 AS TAX1,I.TAX2 AS TAX2,I.TAX3 AS TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS TOTAL,L.NAME AS LOCNAME,C.NAME AS CATNAME,P.ID AS PID,I.TAX1ID AS TAX1ID,I.TAX2ID AS TAX2ID,I.TAX3ID AS TAX3ID\n" +
//"FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON P.CATEGORY=C.ID JOIN LOCATIONS L ON L.ID = B.WAREHOUSE\n" +
//"WHERE B.CREATEDDATE>=?  AND  B.CREATEDDATE<=?\n" +
//"GROUP BY P.HSN_Code,L.NAME,C.NAME ,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID)AS WAITERPRODUCT GROUP BY HSN_CODE,LOCNAME,CATNAME ,PID ,PRODUCT_NAME ,RATE,TAX1ID,TAX2ID,TAX3ID\n" +
//"ORDER BY 10;",
//  "SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3,(SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) AS 'TOTAL',L.NAME AS 'LOCNAME',C.NAME AS 'WAREHOUSE',P.ID FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID JOIN PEOPLE E ON B.CREATEDBY=E.NAME JOIN CATEGORIES C ON C.ID=P.CATEGORY JOIN LOCATIONS L ON E.PRCATEGORIES=L.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY L.NAME,C.NAME,P.ID ,P.NAME ,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
// 
//        "SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,SUM(I.DMULTIPLY) AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3, (SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL B JOIN BILLITEM I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID UNION ALL SELECT  P.NAME AS 'PRODUCT NAME',I.PRODUCT,I.DMULTIPLY AS 'QUANTITY',I.RATE,SUM(I.DMULTIPLY*I.RATE) AS 'AMOUNT',I.TAX1,I.TAX2,I.TAX3, (SUM(I.DMULTIPLY*I.RATE)+I.TAX1+I.TAX2+I.TAX3) FROM BILL_ARV B JOIN BILLITEM_ARV I ON B.ID=I.PARENTID JOIN PRODUCTS P ON I.PRODUCT=P.ID WHERE B.CREATEDDATE>=? AND B.CREATEDDATE<=? GROUP BY P.ID ,I.PRODUCT,I.RATE,I.TAX1ID,I.TAX2ID,I.TAX3ID",
new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ProductWiseTotalModel.productTotalSales1.class)).list(new Object[]{ FmDate ,  ToDate , FmDate ,  ToDate  });
//       for(int i=0;i<li.size();i++){
//           System.out.println("list values are::"+li.get(i).getTax11());
//       }
       
       if (li == null) {
            pm.productlist1 = new ArrayList<ProductWiseTotalModel.productTotalSales1>();
        } else {
            pm.productlist1 = li;
        }
        return pm;
    }
    public List<productTotalSales> getProductlist() {
        return productlist;
    }
    
    public int GetSize(){
      return productlist.size();
  }
    
     private final static String[] TABLEHEADERS1 = {"Sl.No","Product Name","Qty", "Rate", "Amount",  "Tax1", "Tax2", "Tax3", "Total", "Warehouse"};
    
    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS1[column]);
            }
            public int getRowCount() {
                return productlist.size(); 
            }
            public int getColumnCount() {

                return TABLEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                ProductWiseTotalModel.productTotalSales l = productlist.get(row);
                switch (column) {
                    case 0:return (row+ 1);
                    
                    
                    case 1:
                        //  dlSales.loadCustomerExt(customerInfo.getId())
                        // return dlSales.loadCustomerExt(l.getMemname()).getSearchkey();
                        // return searchkey;
                        return l.getName();
                    case 2:
                        //  return dlSales.loadCustomerExt(l.getMemname()).getName();
                        // return memname3;
                        return   decimalFormat.format(l.getDmultiply());
                        
                    case 3: return decimalFormat.format(l.getRate());
                    case 4: return decimalFormat.format(l.getAmount());
                    case 5:  return decimalFormat.format(l.getTax1());
//                        if(l.getTax1()==null){
//                        l.setTax1(0.0);
//                    }
//                    else{
//                        return l.getTax1();
//                    }
                        
                        
//                        return l.getTax1();
                    case 6: return decimalFormat.format(l.getTax2());
//                        if(l.getTax2()==null){
//                        l.setTax1(0.0);
//                    }
//                    else{
//                        return l.getTax2();
//                    }
//                        return l.getTax2();
                    case 7:return decimalFormat.format(l.getTax3());
//                        if(l.getTax3()==null){
//                        l.setTax3(0.0);
//                    }
//                    else{
//                        return l.getTax3();
//                    }
                        
//                        return l.getTax3();
                            case 8: return decimalFormat.format(l.getTotal());
//                         case 8:
//                                 if(l.getActive()==true)
//                                 return "Active";
//                                 else
//                                     return "Deactive";
                        
                          case 9: return l.getLocname();
                        
                        
                    default: return null;
                }
                
              
            }
        };
    }
      public List<productTotalSales1> getProductlist1() {
        return productlist1;
    }
    public int GetSize1(){
      return productlist1.size();
  }
    private final static String[] TABLEHEADERS2 = {"Sl.No","HSN_Code","Product Name","Qty", "Rate", "Amount",  "Tax1", "Tax2", "Tax3", "Total", "Warehouse"};
    
       public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS2[column]);
            }
            public int getRowCount() {
                return productlist1.size(); 
            }
            public int getColumnCount() {

                return TABLEHEADERS2.length;
            }
            public Object getValueAt(int row, int column) {
                ProductWiseTotalModel.productTotalSales1 l = productlist1.get(row);
                switch (column) {
                    case 0:return (row+ 1);
                    
                    case 1:return l.getHsn_code();
                    case 2:
                        //  dlSales.loadCustomerExt(customerInfo.getId())
                        // return dlSales.loadCustomerExt(l.getMemname()).getSearchkey();
                        // return searchkey;
                        return l.getName1();
                    case 3:
                        //  return dlSales.loadCustomerExt(l.getMemname()).getName();
                        // return memname3;
                        return   decimalFormat.format(l.getDmultiply1());
                        
                    case 4: return decimalFormat.format(l.getRate1());
                    case 5: return decimalFormat.format(l.getAmount1());
                    case 6:  return decimalFormat.format(l.getTax11());
//                        if(l.getTax1()==null){
//                        l.setTax1(0.0);
//                    }
//                    else{
//                        return l.getTax1();
//                    }
                        
                        
//                        return l.getTax1();
                    case 7: return decimalFormat.format(l.getTax21());
//                        if(l.getTax2()==null){
//                        l.setTax1(0.0);
//                    }
//                    else{
//                        return l.getTax2();
//                    }
//                        return l.getTax2();
                    case 8:return decimalFormat.format(l.getTax31());
//                        if(l.getTax3()==null){
//                        l.setTax3(0.0);
//                    }
//                    else{
//                        return l.getTax3();
//                    }
                        
//                        return l.getTax3();
                            case 9: return decimalFormat.format(l.getTotal1());
//                         case 8:
//                                 if(l.getActive()==true)
//                                 return "Active";
//                                 else
//                                     return "Deactive";
                        
                          case 10: return l.getLocname1();
                        
                        
                    default: return null;
                }
                
              
            }
        };
    }
    

  public static class productTotalSales implements SerializableRead {

  
        private String name;
        private String id;
        private Double dmultiply;
        private Double rate;
        private Double amount;
        private Double tax1;
        private Double tax2;
        private Double tax3;
        private Double total;
        private String locname;
        private String warehouse;

        public void readValues(DataRead dr) throws BasicException {
          
            name = dr.getString(1);
            id = dr.getString(2);
            dmultiply= dr.getDouble(3);
            rate = dr.getDouble(4);
            amount = dr.getDouble(5);
            tax1 = dr.getDouble(6);
            tax2 = dr.getDouble(7);
            tax3 = dr.getDouble(8);
            total = dr.getDouble(9);
            locname = dr.getString(10);
            warehouse = dr.getString(11);
            
        }
 
       
        public String getName() {
            
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
         public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getDmultiply() {
            return dmultiply;
        }

        public void setDmultiply(Double dmultiply) {
            this.dmultiply = dmultiply;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }
         public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getTax1() {
            if(tax1==null){
                tax1=0.0;
            }
            return tax1;
        }
     

        public void setTax1(Double tax1) {
            
            this.tax1 = tax1;
        
        }
        public Double getTax2() {
            if(tax2==null){
                tax2=0.0;
            }
            return tax2;
        }

        public void setTax2(Double tax2) {
            
                 this.tax2 = tax2;
            }
        public Double getTax3() {
            if(tax3==null){
                tax3=0.0;
            }
            return tax3;
        }

        public void setTax3(Double tax3) {
           
                 this.tax3 = tax3;
            
        }
         public Double getTotal() {
           if(total==null){
            total=amount;
            }
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }
         public String getLocname() {
            return locname;
        }

        public void setLocname(String locname) {
            this.locname = locname;
        }
         public String getWarehouse() {

           return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }
       
       
        }
   public static class productTotalSales1 implements SerializableRead {

        private String hsn_code;
        private String name1;
        private String id1;
        private Double dmultiply1;
        private Double rate1;
        private Double amount1;
        private Double tax11;
        private Double tax21;
        private Double tax31;
        private Double total1;
        private String locname1;
        private String warehouse1;

        public void readValues(DataRead dr) throws BasicException {
            hsn_code=dr.getString(1);
            name1= dr.getString(2);
            id1= dr.getString(3);
            dmultiply1= dr.getDouble(4);
            rate1 = dr.getDouble(5);
            amount1 = dr.getDouble(6);
            tax11 = dr.getDouble(7);
            tax21 = dr.getDouble(8);
            tax31 = dr.getDouble(9);
            total1 = dr.getDouble(10);
            locname1 = dr.getString(11);
            warehouse1 = dr.getString(12);
            
        }
  public String getHsn_code() {
            
            return hsn_code;
        }

        public void setHsn_code(String hsn_code) {
            this.hsn_code= hsn_code;
        }
       
        public String getName1() {
            
            return name1;
        }

        public void setName(String name1) {
            this.name1 = name1;
        }
        
         public String getId1() {
            return id1;
        }

        public void setId1(String id1) {
            this.id1 = id1;
        }

        public Double getDmultiply1() {
            return dmultiply1;
        }

        public void setDmultiply1(Double dmultiply1) {
            this.dmultiply1 = dmultiply1;
        }

        public Double getRate1() {
            return rate1;
        }

        public void setRate1(Double rate1) {
            this.rate1 = rate1;
        }
         public Double getAmount1() {
            return amount1;
        }

        public void setAmount1(Double amount1) {
            this.amount1 = amount1;
        }

        public Double getTax11() {
//            System.out.println("tax1 values::::::"+tax11);
            if(tax11==null){
                tax11=0.0;
            }
            return tax11;
        }
     

        public void setTax11(Double tax11) {
            
            this.tax11= tax11;
        
        }
        public Double getTax21() {
            if(tax21==null){
                tax21=0.0;
            }
            return tax21;
        }

        public void setTax21(Double tax21) {
            
                 this.tax21 = tax21;
            }
        public Double getTax31() {
            if(tax31==null){
                tax31=0.0;
            }
            return tax31;
        }

        public void setTax31(Double tax31) {
           
                 this.tax31 = tax31;
            
        }
         public Double getTotal1() {
           if(total1==null){
            total1=amount1;
            }
            return total1;
        }

        public void setTotal1(Double total1) {
            this.total1 = total1;
        }
         public String getLocname1() {
            return locname1;
        }

        public void setLocname1(String locname1) {
            this.locname1 = locname1;
        }
         public String getWarehouse1() {

           return warehouse1;
        }

        public void setWarehouse1(String warehouse1) {
            this.warehouse1 = warehouse1;
        }
       
       
        }
}

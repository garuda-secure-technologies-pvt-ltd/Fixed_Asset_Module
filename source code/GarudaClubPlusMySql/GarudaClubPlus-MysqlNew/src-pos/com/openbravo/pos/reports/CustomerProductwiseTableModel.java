
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerProductwiseTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<CustomerProductwiseTableModel.DetailBillInfo> data;
    private int size;
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    public static CustomerProductwiseTableModel LoadBillDetails(AppView app , String WarehouseID,Date FromDate , Date ToDate , int OrderFlag) throws BasicException{
        CustomerProductwiseTableModel GuestInfo = new CustomerProductwiseTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
            /*GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? and c.id=b.customer\n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? and c.id=b.customer\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                "\n" +
                                                                "order by 1,3,line", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID ,FromDate , ToDate, WarehouseID ,FromDate , ToDate,WarehouseID,FromDate , ToDate , WarehouseID });
                                                                   */ 
            
            if(OrderFlag==0){
             GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  b.warehouse=? and c.id=b.customer \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  b.warehouse=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  b.warehouse=? and c.id=b.customer  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  b.warehouse=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "order by 1 ,9,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID ,FromDate , ToDate, WarehouseID ,FromDate , ToDate,WarehouseID,FromDate , ToDate , WarehouseID });
             
            }
            else{
                GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  b.warehouse=? and c.id=b.customer \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  b.warehouse=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  b.warehouse=? and c.id=b.customer  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  b.warehouse=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "order by 10 ,1,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID ,FromDate , ToDate, WarehouseID ,FromDate , ToDate,WarehouseID,FromDate , ToDate , WarehouseID });
             
            }
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CustomerProductwiseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    public static CustomerProductwiseTableModel LoadBillDetailsForOneMember(AppView app , String WarehouseID,Date FromDate , Date ToDate , String MemberID, int OrderFlag) throws BasicException{
        CustomerProductwiseTableModel GuestInfo = new CustomerProductwiseTableModel(); 
        String MemberGuest = MemberID+"#Gues%";
     try{
            GuestInfo.data = new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
            /*GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? and c.id=b.customer and b.customer=? \n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? and c.id=b.customer and b.customer=? \n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and b.warehouse=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like ?   \n" +
                                                                "\n" +
                                                                "order by 1,3,line", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID,MemberID ,FromDate , ToDate, WarehouseID ,MemberGuest ,FromDate , ToDate,WarehouseID,MemberID ,FromDate , ToDate , WarehouseID ,MemberGuest });
                                                                    */
            if(OrderFlag==0){
            GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                    "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                    "b.createddate <=?  and  b.warehouse=? and c.id=b.customer and b.customer=? \n" +
                                                                    "\n" +
                                                                    "union all\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                    "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                    "b.createddate <=?  and  b.warehouse=? \n" +
                                                                    "and c.id = substring_index(b.customer,'#',1) and b.customer like  ? \n" +
                                                                    "\n" +
                                                                    "UNION ALL\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                    "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                    "b.createddate <=? and  b.warehouse=? and c.id=b.customer  and b.customer=? \n" +
                                                                    "and c.id=b.customer   \n" +
                                                                    "\n" +
                                                                    "UNION all\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                    "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                    "b.createddate <=? and  b.warehouse=? \n" +
                                                                    "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                    "\n" +
                                                                    "order by 1 ,9,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID,MemberID ,FromDate , ToDate, WarehouseID ,MemberGuest ,FromDate , ToDate,WarehouseID,MemberID ,FromDate , ToDate , WarehouseID ,MemberGuest });
           
            
            }
            else{
                GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                    "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                    "b.createddate <=?  and  b.warehouse=? and c.id=b.customer and b.customer=? \n" +
                                                                    "\n" +
                                                                    "union all\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                    "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                    "b.createddate <=?  and  b.warehouse=? \n" +
                                                                    "and c.id = substring_index(b.customer,'#',1) and b.customer like  ? \n" +
                                                                    "\n" +
                                                                    "UNION ALL\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                    "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                    "b.createddate <=? and  b.warehouse=? and c.id=b.customer  and b.customer=? \n" +
                                                                    "and c.id=b.customer   \n" +
                                                                    "\n" +
                                                                    "UNION all\n" +
                                                                    "\n" +
                                                                    "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                    "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                    "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                    "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                    "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                    "b.createddate <=? and  b.warehouse=? \n" +
                                                                    "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                    "\n" +
                                                                    "order by 10 ,1,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , WarehouseID,MemberID ,FromDate , ToDate, WarehouseID ,MemberGuest ,FromDate , ToDate,WarehouseID,MemberID ,FromDate , ToDate , WarehouseID ,MemberGuest });
           
            
            }
            
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CustomerProductwiseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
    
    public static CustomerProductwiseTableModel LoadBillDetailsForPerticularProduct(AppView app , String PRODUCT ,Date FromDate , Date ToDate, int OrderFlag) throws BasicException{
        CustomerProductwiseTableModel GuestInfo = new CustomerProductwiseTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
           /* GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? and c.id=b.customer\n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? and c.id=b.customer\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                "\n" +
                                                                "order by 1,3,line", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , PRODUCT ,FromDate , ToDate, PRODUCT ,FromDate , ToDate,PRODUCT,FromDate , ToDate , PRODUCT });
                                                                    */
        if(OrderFlag==0){    
            
          GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? and c.id=b.customer \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? and c.id=b.customer  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "order by 1 ,9,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , PRODUCT ,FromDate , ToDate, PRODUCT ,FromDate , ToDate,PRODUCT,FromDate , ToDate , PRODUCT });
        }
        else{
            GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? and c.id=b.customer \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? and c.id=b.customer  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like '%guest%'\n" +
                                                                        "\n" +
                                                                        "order by 10 ,1,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , PRODUCT ,FromDate , ToDate, PRODUCT ,FromDate , ToDate,PRODUCT,FromDate , ToDate , PRODUCT });
                     
            
        }
            
            
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CustomerProductwiseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
     public static CustomerProductwiseTableModel LoadBillDetailsForOneMemberByProductName(AppView app , String ProductId,Date FromDate , Date ToDate , String MemberID, int OrderFlag) throws BasicException{
        CustomerProductwiseTableModel GuestInfo = new CustomerProductwiseTableModel(); 
        String MemberGuest = MemberID+"#Gues%";
     try{
            GuestInfo.data = new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
            /*GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? and c.id=b.customer and b.customer=? \n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem bt , bill b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                "\n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,c.name as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? and c.id=b.customer and b.customer=? \n" +
                                                                "union all\n" +
                                                                "\n" +
                                                                "select c.searchkey,CONCAT(c.name,' -Guest') as member ,bt.parentid as billno ,b.createddate , bt.line ,p.name as product ,\n" +
                                                                "bt.dmultiply,bt.rate,bt.total , bt.attributes\n" +
                                                                "from billitem_arv bt , bill_arv b , products p , customers c\n" +
                                                                "where b.createddate>=? and b.createddate<=? and\n" +
                                                                "bt.parentid=b.id and p.id=bt.product and bt.product=? \n" +
                                                                "and c.id = substring_index(b.customer,'#',1) and b.customer like ?   \n" +
                                                                "\n" +
                                                                "order by 1,3,line", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , ProductId,MemberID ,FromDate , ToDate, ProductId ,MemberGuest ,FromDate , ToDate,ProductId,MemberID ,FromDate , ToDate , ProductId ,MemberGuest });
                                                                */
            if(OrderFlag==0){
            GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? and c.id=b.customer and b.customer=? \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? and c.id=b.customer and b.customer=?  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like ?  \n" +
                                                                        "\n" +
                                                                        "order by 1 ,9,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , ProductId,MemberID ,FromDate , ToDate, ProductId ,MemberGuest ,FromDate , ToDate,ProductId,MemberID ,FromDate , ToDate , ProductId ,MemberGuest });            
            
            }
            else{
                GuestInfo.data = new StaticSentence(app.getSession(), "select c.searchkey,c.name as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? and c.id=b.customer and b.customer=? \n" +
                                                                        "\n" +
                                                                        "union all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member ,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate , qt.line , qt.ATTRIBUTES\n" +
                                                                        "from qticket q, qtitems qt , products p , bill b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product and b.createddate>=? and \n" +
                                                                        "b.createddate <=?  and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like ? \n" +
                                                                        "\n" +
                                                                        "UNION ALL\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,c.name as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? and c.id=b.customer and b.customer=?  \n" +
                                                                        "and c.id=b.customer   \n" +
                                                                        "\n" +
                                                                        "UNION all\n" +
                                                                        "\n" +
                                                                        "select c.searchkey,CONCAT(c.name,' -Guest') as member,q.id as QTID ,q.crdate as QTCrDate , p.name as product, \n" +
                                                                        "qt.rate , qt.dmultiply as qty, (qt.rate*qt.dmultiply) as amount , b.id as BillID, \n" +
                                                                        "b.createddate as BillCrDate ,qt.line, qt.ATTRIBUTES\n" +
                                                                        "from qticket_arv q, qtitems_arv qt , products p , bill_arv b , customers c\n" +
                                                                        "where q.id=qt.parentid and b.id=q.billref and p.id=qt.product  and b.createddate>=? and \n" +
                                                                        "b.createddate <=? and  qt.product=? \n" +
                                                                        "and c.id = substring_index(b.customer,'#',1) and b.customer like ?  \n" +
                                                                        "\n" +
                                                                        "order by 10 ,1,4", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP ,Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP, Datas.STRING,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING ,Datas.TIMESTAMP,Datas.TIMESTAMP,  Datas.STRING ,Datas.STRING  }), new SerializerReadClass(CustomerProductwiseTableModel.DetailBillInfo.class)).list(new Object[]{ FromDate , ToDate , ProductId,MemberID ,FromDate , ToDate, ProductId ,MemberGuest ,FromDate , ToDate,ProductId,MemberID ,FromDate , ToDate , ProductId ,MemberGuest });            
            
            }
            
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(CustomerProductwiseTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
    
    
    
    public List<CustomerProductwiseTableModel.DetailBillInfo> getList(){
        if(data!=null){
            return data;
        }
        else{
            return new ArrayList<CustomerProductwiseTableModel.DetailBillInfo>();
        }
    }
    
    
    public static class DetailBillInfo implements SerializableRead,IKeyed {

        private String Memno;
        private String MemName;
        private String BillNo;
        private Date CRDATE;
        private int Line;
        
        private String ProductName;
        private int DMultiply;
        private Double Rate;
        private Double Total;
         private Properties Attributes = new Properties();
        private String QTNo;
        private Date QTCRDATE;
        
        
         public String getMemno(){
              return Memno;
          }
          public void setMemno(String Memno){
              this.Memno=Memno;
          }
          public String getMemName(){
              return MemName;
          }
          public void setMemName(String MemName){
              this.MemName = MemName;
          }
          public String getBillNo(){
              return BillNo;
          }
          public void setBillNo(String BillNo){
              this.BillNo =BillNo;
          }
        
          
         
          
          public int getLine(){
              return Line;
          }
          public void setLine(int Line){
              this.Line=Line;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          public String getProductName(){
              return ProductName;
          }
          public void setProductName(String ProductName){
              this.ProductName=ProductName;
          }
          
           public int getDMultiply(){
              return DMultiply;
          }
          public void setDMultiply(int DMultiply){
              this.DMultiply=DMultiply;
          }
          
          public Double getRate(){
              return Rate;
          }
          public void setRate(Double Rate){
              this.Rate=Rate;
          }
          public Double getTotal(){
              return Total;
          } 
          public void setTotal(Double Total){
              this.Total=Total;
          }
          
          public Properties getAttributes(){
              return Attributes;
          }
          public void setAttributes(Properties Attributes){
              this.Attributes=Attributes;
          }
          
          
          public String getQTNo(){
              return QTNo;
          }
          public void setQTNo(String QTNo){
              this.QTNo=QTNo;
          }
           public String getQTCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(QTCRDATE);
               return x;
          }
          public void setQTCRDATE(Date QTCRDATE){
              this.QTCRDATE=QTCRDATE;
          }
          public String getRemarks() {
            return Attributes.getProperty("qt.remarks");
          }
          
          
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                Memno = dr.getString(1);
                MemName = dr.getString(2);
                BillNo = dr.getString(9);
                Line = dr.getInt(11);
                CRDATE = dr.getTimestamp(10);
                ProductName = dr.getString(5);
                DMultiply = dr.getInt(7);
                Rate = dr.getDouble(6);
                Total = dr.getDouble(8);
                //Attributes=dr.getBytes(12);
                QTNo=dr.getString(3);
                QTCRDATE=dr.getTimestamp(4);
                
                Attributes = new Properties();
                try {
                    byte[] img = dr.getBytes(12);
                    if (img != null) {
                        Attributes.loadFromXML(new ByteArrayInputStream(img));
                    }
                } catch (IOException e) {
                    //TODO logging
                }
                
                
                
          }

        public Object getKey() {
           return this;
        }

       
     }   
    
}

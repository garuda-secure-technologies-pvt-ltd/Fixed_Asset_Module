/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;

import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class BillLogicApply {
    private DataLogicSales dlSales;
    private BillLogic dlBills;
    private Qticket dlQTs;
    private List<QticketInfo> qtlist;
    private Map<String, BillLineInfo> billLineMap = new HashMap<String, BillLineInfo>();
    private BillInfo bInfo;

    public BillLogicApply(DataLogicSales dlSales, Qticket dlQTs, BillLogic dlBills, List<QticketInfo> qtList) {
        this.dlSales = dlSales;
        this.dlBills = dlBills;
        this.dlQTs = dlQTs;
        this.qtlist = qtList;

        dispatchBill();
    }

//    public BillInfo getBillInfo(){
//       return bInfo;
//    }
    private void dispatchBill(){
        //copy all qtitems of all qtinfo of this customer and put it in billitems and generate one billinfo;
        BillInfo binfoTemp=new BillInfo();
        bInfo = new BillInfo();
        binfoTemp.setCreatedBy(dlBills.getAppView().getAppUserView().getUser().getName()); //TODO change to ID later
        binfoTemp.setCreatedDate(new Date());
        binfoTemp.setLines(new ArrayList<BillLineInfo>());
        bInfo.setCreatedBy(dlBills.getAppView().getAppUserView().getUser().getName()); //TODO change to ID later
        bInfo.setCreatedDate(new Date());
        bInfo.setLines(new ArrayList<BillLineInfo>());
        Double bqty=0.0;
        Double gqty=0.0;
        Double brate=0.0;
        Double grate=0.0;
        AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
        for (QticketInfo qtInfo : qtlist) {
            bInfo.setCustomer(qtInfo.getCustomer());
            bInfo.setPlace(qtInfo.getPlace());
            bInfo.setWaiter(qtInfo.getWaiter());
            bInfo.setFloor(qtInfo.getFloor());
            bInfo.setWarehouse(qtInfo.getWarehouse());
             //praveen:initiator changes---start
            bInfo.setInitiator(qtInfo.getInitiator());
             //praveen:initiator changes---end
            binfoTemp.setCustomer(qtInfo.getCustomer());
            binfoTemp.setPlace(qtInfo.getPlace());
            binfoTemp.setWaiter(qtInfo.getWaiter());
            binfoTemp.setFloor(qtInfo.getFloor());
            Map<String,String> pdtDiscStatus=new HashMap<String, String>();
            for (QTicketLineInfo qtlInfo : qtInfo.getLines()) {
                bqty=0.0;gqty=0.0;
                Object[] obj1;
                try {
                    String st=pdtDiscStatus.get(qtlInfo.getProduct());
                    if(st==null){
                         obj1 = (Object[]) new StaticSentence(m_App.getSession()
                        , "SELECT APPTO FROM OFFER O,QTITEMS Q,QTICKET QT WHERE O.PRODUCT = ? AND QT.ID=? AND Q.PARENTID=QT.ID AND Q.PRODUCT = O.PRODUCT AND (O.APPTO=QT.CREATEDBY OR O.APPTO='ALL') AND O.FROMDATE <= QT.CRDATE AND  QT.CRDATE<= case when o.active=1 then  O.TODATE else o.deactivatedate end"
                       ,new SerializerWriteBasic (new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(new Object[]{qtlInfo.getProduct(),qtlInfo.getParentid()});
                    }else{
                         if(st.equals(""))
                             obj1=null;
                         else if(st.equals("ALL"))
                             obj1=new Object[]{"ALL"};
                         else
                             obj1=new Object[]{st};
                    }
                    
                    if(obj1==null || obj1[0]==null || obj1[0].toString().equals("ALL")){
               /*       BillLineInfo blInfo = billLineMap.get(qtlInfo.getProduct());
                     
                      if (blInfo == null) {
                       blInfo = new BillLineInfo();
                       blInfo.setProduct(LookupUtilityImpl.getInstance(null).getProductsMap().get(qtlInfo.getProduct()));
                       blInfo.setParentid(qtlInfo.getParentid());
                       billLineMap.put(qtlInfo.getProduct(), blInfo);
                     }*/
                        ////////////////////////////////////////////////////////by pratima
                          BillLineInfo blInfo = new BillLineInfo();
                       blInfo.setProduct(LookupUtilityImpl.getInstance(null).getProductsMap().get(qtlInfo.getProduct()));
                       blInfo.setParentid(qtlInfo.getParentid());
                     ///////////////////////////////////////////////////////
                       if(obj1==null || obj1[0]==null){
                        blInfo.setDiscountStatus(false);
                        pdtDiscStatus.put(qtlInfo.getProduct(), "");
                      }else{
                          blInfo.setDiscountStatus(true);
                          pdtDiscStatus.put(qtlInfo.getProduct(), "ALL");
                      }
                     blInfo.setMultiply(blInfo.getMultiply() + qtlInfo.getMultiply());
                     blInfo.setRate( qtlInfo.getRate() );
                       billLineMap.put(qtlInfo.getID(), blInfo);//added by pratima
                }else{
                        pdtDiscStatus.put(qtlInfo.getProduct(), qtInfo.getCreatedBy());
                     /* BillLineInfo blInfo = billLineMap.get(qtlInfo.getProduct()+qtInfo.getCreatedBy());
                      if (blInfo == null) {
                       blInfo = new BillLineInfo();
                       blInfo.setProduct(LookupUtilityImpl.getInstance(null).getProductsMap().get(qtlInfo.getProduct()));
                       blInfo.setParentid(qtlInfo.getParentid());
                       billLineMap.put(qtlInfo.getProduct()+qtInfo.getCreatedBy(), blInfo);
                     }*/
                     /////////////////////////////////////////////////////by pratima
                      BillLineInfo  blInfo = new BillLineInfo();
                       blInfo.setProduct(LookupUtilityImpl.getInstance(null).getProductsMap().get(qtlInfo.getProduct()));
                       blInfo.setParentid(qtlInfo.getParentid());
                    ////////////////////////////////////////////////////////////////
                      blInfo.setDiscountStatus(true);
                     blInfo.setMultiply(blInfo.getMultiply() + qtlInfo.getMultiply());
                     blInfo.setRate( qtlInfo.getRate() );
                     billLineMap.put(qtlInfo.getID()+qtInfo.getCreatedBy(), blInfo);//added by pratima

                }
                } catch (BasicException ex) {
                  ex.printStackTrace();
                }
                
            }
        }

        double amount = 0.0;
        double amttemp=0.0,subamt=0.0;
        int qty=0;
        int temp=0;
        int temp1=0;
        int oqty=0;
        int fqty=0;//flag=0;
        for (BillLineInfo biInfo : billLineMap.values()) {

            BillLineInfo biInfo1 = biInfo.copybill();
            biInfo1.setID(UUID.randomUUID().toString());
            qty=biInfo.getMultiply();//flag=0;
             
                try{
                    if(biInfo.getDiscountStatus()){
             /*   Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT BUYQTY,BUYRATE,GETQTY,GETRATE FROM OFFER O,QTITEMS Q,QTICKET QT WHERE O.PRODUCT = ? AND QT.ID=? AND Q.PARENTID=QT.ID AND Q.PRODUCT = O.PRODUCT AND (O.APPTO=QT.CREATEDBY OR O.APPTO='ALL') AND O.FROMDATE <= QT.CRDATE AND O.TODATE >= QT.CRDATE AND O.ACTIVE=TRUE"
                       ,new SerializerWriteBasic (new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find(new Object[]{biInfo.getProduct().getID(),biInfo.getParentid()});
               */
             /////////////////////////////////////////////by pratima
              Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT BUYQTY,BUYRATE,GETQTY,GETRATE FROM OFFER O,QTITEMS Q,QTICKET QT WHERE O.PRODUCT = ? AND QT.ID=? AND Q.PARENTID=QT.ID AND Q.PRODUCT = O.PRODUCT AND (O.APPTO=QT.CREATEDBY OR O.APPTO='ALL') AND O.FROMDATE <= QT.CRDATE AND QT.CRDATE <= case when o.active=1 then  O.TODATE else o.deactivatedate end"
                       ,new SerializerWriteBasic (new Datas[]{Datas.STRING,Datas.STRING})
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING})).find(new Object[]{biInfo.getProduct().getID(),biInfo.getParentid()});
               
             /////////////////////////////////////////////////////////
                if(obj==null || obj[0]==null || obj[1]==null || obj[2]==null || obj[3]==null)
                {
                    bqty=0.0;
                    gqty=0.0;
                    brate=0.0;
                    grate=0.0;
                   // flag=1;
                }
                else
                {
                    bqty=Double.parseDouble(obj[0].toString());
                    brate=Double.parseDouble(obj[1].toString());
                    gqty=Double.parseDouble(obj[2].toString());
                    grate=Double.parseDouble(obj[3].toString());
                }

                    }else{
                        bqty=0.0;
                        gqty=0.0;
                        brate=0.0;
                        grate=0.0;
                    }
                }
                catch(Exception e){
                }
             temp=0;temp1=0;fqty=0;subamt=0.0;
             int tqty=0;
                if(bqty!=0.0 && brate!=0.0)
                {

                    qty=biInfo.getMultiply();
                    while(qty>0)
                    {
                        temp1=qty;
                        qty=qty-bqty.intValue();
                        if(qty>0)
                        {
                            tqty=qty;
                            qty=qty-gqty.intValue();
                            if(qty>=0)
                            fqty=fqty+gqty.intValue();
                            else
                                fqty=fqty+tqty;
                            temp=temp+bqty.intValue();
                        } else if(qty==0)
                        {

                            temp=temp+bqty.intValue();
                        }
                        else
                            temp=temp+temp1;
                    }
                   // int del=qty %(bqty.intValue()+gqty.intValue());
                  //  temp=(biInfo.getMultiply()/bqty);
                 // temp1= Math.round(temp);
                    qty=(int)(temp);
                    if(fqty==0)
                    {
                        biInfo.setMultiply(qty);
                        biInfo.setRate(brate);
                       // biInfo1.setMultiply(fqty)
                         biInfo.setAmount(qty*brate);
                      amount += qty*brate;
                    }
                    else{
                        biInfo.setMultiply(qty);
                        biInfo.setRate(brate);
                        biInfo1.setMultiply(fqty);
                        biInfo1.setRate(grate);

                     biInfo.setAmount(qty*brate );
                     biInfo1.setAmount( fqty*grate);
                      amount += qty*brate + fqty*grate;
                      binfoTemp.addLine(biInfo1);
                    }
                     // biInfo.setMultiply(biInfo.getMultiply());
                }
            // qty=biInfo.getMultiply()+qti;
                else
                {
                        // edited by akash
                    if(brate==0.00 && bqty!=0.00){
                        biInfo.setRate(brate);
                        biInfo.setAmount(biInfo.getMultiply()*brate);
                        amount += biInfo.getMultiply()*brate;
                    }
                    else{
                       biInfo.setAmount(biInfo.getMultiply()*biInfo.getRate());
                        amount += biInfo.getMultiply()*biInfo.getRate();  
                    }
                    
                }
            binfoTemp.addLine(biInfo);
        }
        binfoTemp.setAmount(amount);
        List<BillLineInfo> biInfoList=binfoTemp.getLines();
        Map<String,BillLineInfo> biInfomap=new HashMap<String, BillLineInfo>();
        
        for(BillLineInfo info:biInfoList){
            BillLineInfo blInfo = biInfomap.get(info.getProduct().getID()+info.getRate());
                // qtlInfo.getID();
                if (blInfo == null) {
                    blInfo = new BillLineInfo();
                    blInfo.setProduct(info.getProduct());
                    blInfo.setParentid(info.getParentid());
                    biInfomap.put(blInfo.getProduct().getID()+info.getRate(), blInfo);
                }
                blInfo.setMultiply(blInfo.getMultiply() + info.getMultiply());

                blInfo.setRate( info.getRate() );
                blInfo.setAmount(blInfo.getRate()*blInfo.getMultiply());
        }
        for (BillLineInfo biInfo : biInfomap.values()) {
            bInfo.addLine(biInfo);
        }
        bInfo.setAmount(amount);

    }

    public BillInfo getBillInfo() {
        return bInfo;
    }

    public boolean saveBill(String type) throws Exception {
        //qtlist
        return dlBills.saveBill(bInfo, qtlist, dlQTs,type);
    }

}

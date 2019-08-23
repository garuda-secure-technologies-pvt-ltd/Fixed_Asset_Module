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
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import com.openbravo.pos.clubmang.DataLogicFacilities;

/**
 *
 * @author dev3
 */
public class FixedAssetTableModel extends BeanFactoryDataSingle {
     private int flag;
    private Session s;
   
    private List<FixedAssetTableModel.FixedAssetInfo> data2;
DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private int size;
    private final static String[] TABLEHEADERS1 = {"Sr No.", "NAME", "MAJ_CLASSIFICATION",  "ACCOUNT_HEAD", "VENDOR", "DATE_OF_PURCHASE", "TOTAL_COST", "CREATED_BY", "CREATED_DATE"};
       private static DataLogicFacilities dlfac;
    @Override
    public void init(Session s) {
        this.s = s;
       
    }

    public static FixedAssetTableModel GetFixedAssetTableModel(AppView app, int flag) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        FixedAssetTableModel EmailidInfo = new FixedAssetTableModel();
        EmailidInfo.flag = flag;
        try {
            EmailidInfo.data2 = new ArrayList<FixedAssetTableModel.FixedAssetInfo>();
            
            if(flag==0){
            
            EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT F.ID,F.BARCODE,F.NAME,F.MAJ_CLASSIFICATION,F.SUB_HEAD_CLASS,F.SUB_CLASSIFICATION,A.NAME,F.IS_STAND_ALONE_ASSET,F.LINK,V.NAME ,F.DATE_OF_PURCHASE,F.TOTAL_COST,F.SCANNED_DOC,F.DATE_OF_INSTALLETION,F.DATE_OF_COMMISSION,F.DATE_PUT_TO_USE,F.DATE_OF_CAPITALIZATION,F.STRAIGHTLINE_OR_WDV,F.RATE_OF_DEPRECATION,F.WDV_DATE_OF_FAR,F.COST_OF_REPLACEMENT,F.HOW_CALCULATED,F.AGENCY_FOR_REPLACEMENT,F.CREATED_BY,F.CREATED_DATE,F.ACTIVE,F.MAKE,F.MODEL,F.WDVDATE FROM FA_MASTER F,ACCOUNTMASTER A,VENDOR V  \n"
                    + "WHERE V.ID=F.VENDOR AND A.ID=F.ACCOUNT_HEAD AND F.ACTIVE=TRUE ORDER BY F.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FixedAssetTableModel.FixedAssetInfo.class)).list();
            
            }else if(flag==1){
             EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT F.ID,F.BARCODE,F.NAME,F.MAJ_CLASSIFICATION,F.SUB_HEAD_CLASS,F.SUB_CLASSIFICATION,A.NAME,F.IS_STAND_ALONE_ASSET,F.LINK,V.NAME,F.DATE_OF_PURCHASE,F.TOTAL_COST,F.SCANNED_DOC,F.DATE_OF_INSTALLETION,F.DATE_OF_COMMISSION,F.DATE_PUT_TO_USE,F.DATE_OF_CAPITALIZATION,F.STRAIGHTLINE_OR_WDV,F.RATE_OF_DEPRECATION,F.WDV_DATE_OF_FAR,F.COST_OF_REPLACEMENT,F.HOW_CALCULATED,F.AGENCY_FOR_REPLACEMENT,F.CREATED_BY,F.CREATED_DATE,F.ACTIVE,F.MAKE,F.MODEL,F.WDVDATE FROM FA_MASTER F,ACCOUNTMASTER A,VENDOR V  \n"
                    + "WHERE V.ID=F.VENDOR AND A.ID=F.ACCOUNT_HEAD AND F.ACTIVE=TRUE ORDER BY F.MAJ_CLASSIFICATION  ", SerializerWriteString.INSTANCE, new SerializerReadClass(FixedAssetTableModel.FixedAssetInfo.class)).list();
           
            }else if(flag==2){
             EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT F.ID,F.BARCODE,F.NAME,F.MAJ_CLASSIFICATION,F.SUB_HEAD_CLASS,F.SUB_CLASSIFICATION,A.NAME,F.IS_STAND_ALONE_ASSET,F.LINK,V.NAME,F.DATE_OF_PURCHASE,F.TOTAL_COST,F.SCANNED_DOC,F.DATE_OF_INSTALLETION,F.DATE_OF_COMMISSION,F.DATE_PUT_TO_USE,F.DATE_OF_CAPITALIZATION,F.STRAIGHTLINE_OR_WDV,F.RATE_OF_DEPRECATION,F.WDV_DATE_OF_FAR,F.COST_OF_REPLACEMENT,F.HOW_CALCULATED,F.AGENCY_FOR_REPLACEMENT,F.CREATED_BY,F.CREATED_DATE,F.ACTIVE,F.MAKE,F.MODEL,F.WDVDATE FROM FA_MASTER F,ACCOUNTMASTER A,VENDOR V   \n"
                    + "WHERE V.ID=F.VENDOR AND A.ID=F.ACCOUNT_HEAD AND F.ACTIVE=TRUE ORDER BY F.CREATED_DATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(FixedAssetTableModel.FixedAssetInfo.class)).list();
           
            }else{
             EmailidInfo.data2 = new StaticSentence(app.getSession(), "SELECT F.ID,F.BARCODE,F.NAME,F.MAJ_CLASSIFICATION,F.SUB_HEAD_CLASS,F.SUB_CLASSIFICATION,A.NAME,F.IS_STAND_ALONE_ASSET,F.LINK,V.NAME,F.DATE_OF_PURCHASE,F.TOTAL_COST,F.SCANNED_DOC,F.DATE_OF_INSTALLETION,F.DATE_OF_COMMISSION,F.DATE_PUT_TO_USE,F.DATE_OF_CAPITALIZATION,F.STRAIGHTLINE_OR_WDV,F.RATE_OF_DEPRECATION,F.WDV_DATE_OF_FAR,F.COST_OF_REPLACEMENT,F.HOW_CALCULATED,F.AGENCY_FOR_REPLACEMENT,F.CREATED_BY,F.CREATED_DATE,F.ACTIVE,F.MAKE,F.MODEL,F.WDVDATE FROM FA_MASTER F,ACCOUNTMASTER A,VENDOR V   \n"
                    + "WHERE V.ID=F.VENDOR AND A.ID=F.ACCOUNT_HEAD AND F.ACTIVE=TRUE ORDER BY V.NAME", SerializerWriteString.INSTANCE, new SerializerReadClass(FixedAssetTableModel.FixedAssetInfo.class)).list();
           
            
            }
            
            
            EmailidInfo.size = EmailidInfo.data2.size();

        } catch (BasicException ex) {
            Logger.getLogger(FixedAssetTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EmailidInfo;

    }

    public int getSize() {
        return size;
    }

    public List<FixedAssetTableModel.FixedAssetInfo> getList() {
        if (data2 != null) {
            return data2;
        } else {
            return new ArrayList<FixedAssetTableModel.FixedAssetInfo>();
        }
    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            int i = 2, j = 0;

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
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
               java.lang.String.class, java.lang.String.class,
            };
            boolean[] canEdit = new boolean[]{
                false, false, false,
                false, false, false, false, false, false,false
                   };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                FixedAssetTableModel.FixedAssetInfo r = data2.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);

                    case 1:
                        return r.getname();
                    case 2:
                       // return r.getmajor_cls();
                        try{
                         return dlfac.getaccountbyid(r.getmajor_cls()).getName();
                        }catch(Exception e){}
                    case 3:
                        return r.getac_head();

                    case 4:
                        if(r.getvendor()!=null||r.getvendor().trim().length()>0||(!r.getvendor().equals("null")))
                        return r.getvendor();
                        else return "";
                    case 5:
                        return r.getdop();
                    case 6:
                        if(r.getcost()!=null)
                        return decimalFormat.format(r.getcost());
                        else return "";
                    case 7:
                        return r.getCreatedBy();
                    case 8:
                        return r.getCreatedDate();

                }
                return null;
            }

        };
    }

    public static class FixedAssetInfo implements SerializableRead, IKeyed {

        private String id;
        private String createdby;
        private Timestamp createddate;
        private String name;
        private String major_cls;
        private String sub_head;
        private String sub_cls;
        private String ac_head;
        private int stand_alone;
        private String link;
        private String vendor;
        private Date dop;
        private Double cost;
        private String doc;
        private Date doi;
        private Date docomm;
        private Date dou;
        private Date docapt;
        private int strt;
        private double rod;
        private double wdv;
        private double cor;
        private String howcal;
        private String agency;
        private String barcode;
        private String make;
        private String model;
        private Date wdvDate;

       
        public String getbarcode() {
            return barcode;
        }

        public void setbarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getvendor() {
           if(vendor!=null|| vendor.trim().length()>0)
            return vendor;
           else return "";
        }

        public void setvendor(String vendor) {
            this.vendor = vendor;
        }

        public String gethowcal() {
           
            return howcal;
        }

        public void sethowcal(String howcal) {
            this.howcal = howcal;
        }

        public String getagency() {
            return agency;
        }

        public void setagency(String agency) {
            this.agency = agency;
        }

        public String getlink() {
            return link;
        }

        public void setlink(String link) {
            this.link = link;
        }

        public Double getrod() {
           // String ro = Formats.DOUBLE.formatValue(rod);
            return rod;
        }

        public void setrod(Double rod) {
            this.rod = rod;
        }

        public Double getwdv() {
           // String wd = Formats.DOUBLE.formatValue(wdv);

            return wdv;
        }

        public void setwdv(Double wdv) {
            this.wdv = wdv;
        }

        public Double getcor() {
         //   String co = Formats.DOUBLE.formatValue(cor);

            return cor;
        }

        public void setcor(Double cor) {
            this.cor = cor;
        }

        public Double getcost() {
         //   String cos = Formats.DOUBLE.formatValue(cost);

            return cost;
        }

        public void setcost(Double cost) {
            this.cost = cost;
        }

        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

        public String getdoc() {
            return doc;
        }

        public void setdoc(String doc) {
            this.doc = doc;
        }

        public String getmajor_cls() {
            return major_cls;
        }

        public void setmajor_cls(String major_cls) {
            this.major_cls = major_cls;
        }

        public String getsub_head() {
            return sub_head;
        }

        public void setsub_head(String sub_head) {
            this.sub_head = sub_head;
        }

        public String getsub_cls() {
            return sub_cls;
        }

        public void setsub_cls(String sub_cls) {
            this.sub_cls = sub_cls;
        }

        public String getac_head() {
            return ac_head;
        }

        public void setac_head(String ac_head) {
            this.ac_head = ac_head;
        }

        public int getstand_alone() {
            return stand_alone;
        }

        public void setstand_alone(int stand_alone) {
            this.stand_alone = stand_alone;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedBy() {
            return createdby;
        }

        public void setCreatedBy(String createdby) {
            this.createdby = createdby;
        }

        public String getCreatedDate(){
               String crddate=Formats.TIMESTAMP.formatValue(createddate);
              return crddate;
           }

        public void setCreatedDate(Timestamp createddate) {
            this.createddate = createddate;
        }

        public String getdop() {
            String purc = Formats.TIMESTAMP.formatValue(dop);
            return purc;
        }

        public void setdop(Date dop) {
            this.dop = dop;
        }

        public String getdoi() {
           if(doi!=null){
            String inst = Formats.TIMESTAMP.formatValue(doi);
            return inst;}
           else return "";
        }

        public void setdoi(Date doi) {
            this.doi = doi;
        }

        public String getdocomm() {
            if(docomm!=null){
            String comm = Formats.TIMESTAMP.formatValue(docomm);
            return comm;
            }else return "";
        }

        public void setdocomm(Date docomm) {
            this.docomm = docomm;
        }

        public String getdou() {
           
            if(dou!=null){
                String used = Formats.TIMESTAMP.formatValue(dou);
            return used;
            }
            else return "";
           
        }

        public void setdou(Date dou) {
            this.dou = dou;
        }

        public String getdocapt() {
           if(docapt!=null){
            String capt = Formats.TIMESTAMP.formatValue(docapt);
            return capt;
           }
            else return "";
        }

        public void setdocapt(Date docapt) {
            this.docapt = docapt;
        }

        public int getstrt() {
            return strt;
        }

        public void setstrt(int strt) {
            this.strt = strt;
        }
  public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
        public String getWdvDate() {
        return    Formats.TIMESTAMP.formatValue(wdvDate);
         //  return wdvDate;
        }

        public void setWdvDate(Date wdvDate) {
            this.wdvDate = wdvDate;
        }
      
        @Override
        public String toString() {
            return name;
        }

        @Override
        public Object getKey() {
            return id;
        }

        public void readValues(DataRead dr) throws BasicException {
          
            id = dr.getString(1);
            barcode = dr.getString(2);
            name = dr.getString(3);
            major_cls = dr.getString(4);
            sub_head = dr.getString(5);
            
            if(dr.getString(6)!=null)
            sub_cls = dr.getString(6);
            ac_head = dr.getString(7);
            stand_alone = dr.getInt(8);
            
            if(dr.getString(9)!=null)
            link = dr.getString(9);
           
            if(dr.getString(10)!=null)
            vendor = dr.getString(10);
           
            if(dr.getString(11)!=null)
            dop = dr.getTimestamp(11);
            
           
            cost = dr.getDouble(12);
           
            if(dr.getString(13)!=null)
            doc = dr.getString(13);
            
            if(dr.getString(14)!=null)
            doi = dr.getTimestamp(14);
          
            if(dr.getString(15)!=null)
            docomm = dr.getTimestamp(15);
           
            if(dr.getString(16)!=null)
            dou = dr.getTimestamp(16);
           
            if(dr.getString(17)!=null)
            docapt = dr.getTimestamp(17);
            
            strt = dr.getInt(18);
           
            if(dr.getString(19)!=null)
            rod = dr.getDouble(19);
           
            if(dr.getString(20)!=null)
            wdv = dr.getDouble(20);
            
            if(dr.getString(21)!=null)
            cor = dr.getDouble(21);
          
             if(dr.getString(22)!=null)
            howcal = dr.getString(22);
          
              if(dr.getString(23)!=null)
             agency = dr.getString(23);
          
              
              createdby = dr.getString(24);
            createddate = dr.getTimestamp(25);
            if(dr.getString(27)!=null)
            make=dr.getString(27);
            if(dr.getString(28)!=null)
            model=dr.getString(28);
            if(dr.getTimestamp(29)!=null)
            wdvDate=dr.getTimestamp(29);        }

    }

}



package com.openbravo.pos.panels;


import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.inventory.ConsolidateStockModel;
import java.util.ArrayList;
import java.util.List;

 



public class ConsolidateStockCloseSaleModel {
    
 private List<ConsolidateStockReport> consolidate;
 private ConsolidateStockReport  consol; 



public ConsolidateStockCloseSaleModel loadInstance(AppView app, Object[] val) throws BasicException {
        ConsolidateStockCloseSaleModel wm = new ConsolidateStockCloseSaleModel();
        List<ConsolidateStockReport> li=null;
        Object val1 = new Object[]{val[1],val[0],val[1],val[2],val[0],val[1] , val[2] , val[0] , val[1],val[0],val[1],val[2],val[0],val[1] , val[2] , val[0] };
        Object obj = new StaticSentence(app.getSession(), "select parent from locations where id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(val[0].toString());
      
       li = new StaticSentence(app.getSession(),
                    "SELECT PNAME,BAL,INN,OUTT,SUM(INN+OUTT+BAL),CAT,UNITS1,PRODUCTID,RATE FROM\n" +
                    "(SELECT PNAME,PID,PREF,SUM(BAL) AS BAL,SUM(INN) AS INN,SUM(OUTT) AS OUTT,CAT AS CAT,UNITS AS UNITS1,PRODUCTID AS PRODUCTID , RATE AS RATE  FROM\n" +
                    "( \n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, IFNULL(SUM(UNITS1),0.00) AS BAL , 0.00 AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS , P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1   and s.datenew<?  and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,SUM(UNITS1) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS ,  P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1  and s.units1<0  and s.datenew>=? and  s.datenew<= ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,0.0 AS OUTT ,\n" +
                    "SUM(UNITS1) AS INN ,C.NAME AS CAT,U.NAME AS UNITS , P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1  and s.units1>0 and s.datenew>= ? and  s.datenew<= ? and \n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY  \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                    "\n" +
                    "\n" +
                    "UNION all\n" +
                    "\n" +
                    "\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, IFNULL(SUM(UNITS),0.00) AS BAL , 0.00 AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS ,  P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT   and s.datenew< ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,SUM(UNITS) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS ,  P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT  and s.units<0  and s.datenew>=? and  s.datenew<= ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,0.0 AS OUTT ,\n" +
                    "SUM(UNITS) AS INN ,C.NAME AS CAT,U.NAME AS UNITS , P.ID AS PRODUCTID , P.PRICESELL AS RATE \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT  and s.units>0 and s.datenew>= ? and  s.datenew<= ? and \n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY  \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS\n" +
                    ")AS SD\n" +
                    "GROUP BY PNAME,PID,PREF,CAT ) AS DDFD GROUP BY PNAME,PID,PREF ORDER BY CAT,PNAME",
                new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP ,  Datas.STRING, Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.STRING, Datas.TIMESTAMP,Datas.TIMESTAMP ,  Datas.STRING, Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING}),
                new SerializerReadClass(ConsolidateStockReport.class)).list(val1);
        
        
        
        
        if (li == null) {
            wm.consolidate = new ArrayList<ConsolidateStockReport>();
        } else {
            wm.consolidate = li;
        }
        return wm;
    }


    public ConsolidateStockReport getConsol() {
        return consol;
    }

    public void setConsol(ConsolidateStockReport consol) {
        this.consol = consol;
    }

    public List<ConsolidateStockCloseSaleModel.ConsolidateStockReport> getConsolidate() {
        return consolidate;
    }

    public void setConsolidate(List<ConsolidateStockCloseSaleModel.ConsolidateStockReport> consolidate) {
        this.consolidate = consolidate;
    }



    public static class ConsolidateStockReport implements SerializableRead {

        private String pname;
        private Double inn;
        private Double outt;
        private Double current;
        private String catName;
        private String units;
        private Double OpBal;
        private String PID;
        private Double Rate;
        
        public void readValues(DataRead dr) throws BasicException {
            pname = dr.getString(1);
            OpBal = dr.getDouble(2);
            inn = dr.getDouble(3);
            outt = dr.getDouble(4);
            current = dr.getDouble(5);
            catName = dr.getString(6);
            units = dr.getString(7);
            PID=dr.getString(8);
            Rate=dr.getDouble(9);
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public Double getCurrent() {
            return current;
        }

        public void setCurrent(Double current) {
            this.current = current;
        }

        public Double getInn() {
            return inn;
        }

        public void setInn(Double inn) {
            this.inn = inn;
        }

        public Double getOutt() {
            return outt;
        }

        public void setOutt(Double outt) {
            this.outt = outt;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getUnits() {
            return units;
        }
        
        public void setOpBal(Double OpBal){
            this.OpBal=OpBal;
        }
        public Double getOpBal(){
            return OpBal;
        }
        
        public String getPID() {
            return PID;
        }

        public void setPID(String PID) {
            this.PID = PID;
        }
        
        public Double getRate() {
            return Rate;
        }

        public void setRate(Double Rate) {
            this.Rate = Rate;
        }
        
    }

}
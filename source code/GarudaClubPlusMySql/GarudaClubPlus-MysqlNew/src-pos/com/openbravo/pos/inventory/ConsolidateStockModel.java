
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author swathi
 */
public class ConsolidateStockModel {

    private List<ConsolidateStockReport> consolidate;
    private ConsolidateStockReport consol;
    
    public ConsolidateStockModel() {
    }

    public ConsolidateStockModel loadInstance(AppView app, Object[] val) throws BasicException {
        ConsolidateStockModel wm = new ConsolidateStockModel();
        List<ConsolidateStockReport> li=null;
        Object val1 = new Object[]{val[1],val[0],val[1],val[2],val[0],val[1] , val[2] , val[0] , val[1],val[0],val[1],val[2],val[0],val[1] , val[2] , val[0] };
        Object obj = new StaticSentence(app.getSession(), "select parent from locations where id=?",SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).find(val[0].toString());
       // if(obj!=null){
        
      /*  
        
        if(false){
            li = new StaticSentence(app.getSession(),
                "SELECT PNAME,INN,OUTT,SUM(INN+OUTT),CAT,UNITS1 FROM(SELECT PNAME,PID,PREF,SUM(INN) AS INN,SUM(OUTT) AS OUTT,CAT AS CAT,UNITS AS UNITS1 FROM "+
                 "( SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, SUM(UNITS) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS "+
                 " FROM PRODUCTS P LEFT OUTER JOIN STOCKDIARY S ON P.ID=S.PRODUCT AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY "+
				 " AND S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE  AND (S.RECEIVEDBY IS NULL OR S.RECEIVEDBY!='Rejected') "+
				 " GROUP BY PNAME,PID,PREF,CAT,UNITS1 "+
                 " UNION ALL "+
                 " SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, SUM(UNITS1) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS "+
                 " FROM PRODUCTS P LEFT OUTER JOIN STOCKDIARY S ON P.ID=S.PRODUCT1 AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY "+
				 " AND S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE AND RNO='QT' AND (S.RECEIVEDBY IS NULL OR S.RECEIVEDBY!='Rejected') "+
				 " GROUP BY PNAME,PID,PREF,CAT,UNITS1 "+
                 " UNION ALL "+
                 " SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.0 AS OUTT,SUM(UNITS1) AS INN,C.NAME AS CAT,U.NAME AS UNITS "+
                 " FROM PRODUCTS P LEFT OUTER JOIN STOCKDIARY S ON P.ID=S.PRODUCT1 AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY "+
				 " AND S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE  AND RNO!='QT' AND (S.RECEIVEDBY IS NULL OR S.RECEIVEDBY!='Rejected') "+
				 " GROUP BY PNAME,PID,PREF,CAT,UNITS1 "+
                 " UNION ALL "+
                 " SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.0 AS OUTT,SUM(UNITS1) AS INN,C.NAME AS CAT,U.NAME AS UNITS "+
                 " FROM PRODUCTS P LEFT OUTER JOIN STOCKDIARY S ON P.ID=S.PRODUCT1 AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY "+
				 " AND S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE  AND RNO IS NULL AND (S.RECEIVEDBY IS NULL OR S.RECEIVEDBY!='Rejected') "+
				 " GROUP BY PNAME,PID,PREF,CAT,UNITS1) AS SD "+
                 " GROUP BY PNAME,PID,PREF,CAT ) AS DDFD GROUP BY PNAME,PID,PREF ORDER BY CAT,PNAME",
                new SerializerWriteBasic(new Datas[]{ Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadClass(ConsolidateStockReport.class)).list(val1);
        }else{
            li = new StaticSentence(app.getSession(),
                "SELECT PNAME,INN,OUTT,SUM(INN+OUTT),CAT,UNITS1 FROM " +
                " (SELECT PNAME,PID,PREF,SUM(INN) AS INN,SUM(OUTT) AS OUTT,CAT AS CAT,UNITS AS UNITS1 FROM " +
                " ( SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, SUM(UNITS) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS" +
                "  FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY AND  S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1 " +
                " UNION ALL " +
                " SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF,0.0 AS OUTT ,SUM(UNITS1) AS INN ,C.NAME AS CAT,U.NAME AS UNITS " +
                " FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1 AND P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY AND  S.DATENEW<=? JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1)AS SD " +
                " GROUP BY PNAME,PID,PREF,CAT ) AS DDFD GROUP BY PNAME,PID,PREF ORDER BY CAT,PNAME",
                new SerializerWriteBasic(new Datas[]{ Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP}),
                new SerializerReadClass(ConsolidateStockReport.class)).list(val);
        }
        
        
        */
        
        
        li = new StaticSentence(app.getSession(),
                    "SELECT PNAME,BAL,INN,OUTT,SUM(INN+OUTT+BAL),CAT,UNITS1 FROM\n" +
                    "(SELECT PNAME,PID,PREF,SUM(BAL) AS BAL,SUM(INN) AS INN,SUM(OUTT) AS OUTT,CAT AS CAT,UNITS AS UNITS1 FROM\n" +
                    "( \n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, IFNULL(SUM(UNITS1),0.00) AS BAL , 0.00 AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS\n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1   and s.datenew<?  and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                    "UNION ALL\n" +
//                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,SUM(UNITS1) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS\n" +
//                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1  and s.units1<0  and s.datenew>=? and  s.datenew<= ? and\n" +
//                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
//                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                            ///commented and replaced by pratima
                              "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,SUM(UNITS1) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS\n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1  and s.units1<0  and s.datenew>=? and  s.datenew<= ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +/////////////////////
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,0.0 AS OUTT ,\n" +
                    "SUM(UNITS1) AS INN ,C.NAME AS CAT,U.NAME AS UNITS \n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT1  and s.units1>0 and s.datenew>= ? and  s.datenew<= ? and \n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY  \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS1\n" +
                    "\n" +
                    "\n" +
                    "UNION all\n" +
                    "\n" +
                    "\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, IFNULL(SUM(UNITS),0.00) AS BAL , 0.00 AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS\n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT   and s.datenew< ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,SUM(UNITS) AS OUTT,0.0 AS INN,C.NAME AS CAT,U.NAME AS UNITS\n" +
                    "FROM PRODUCTS P JOIN STOCKDIARY S ON P.ID=S.PRODUCT  and s.units<0  and s.datenew>=? and  s.datenew<= ? and\n" +
                    "P.LOCATION=? JOIN CATEGORIES C ON C.ID=P.CATEGORY    \n" +
                    "JOIN UNIT U ON U.ID=P.UNITTYPE GROUP BY PNAME,PID,PREF,CAT,UNITS\n" +
                    "UNION ALL\n" +
                    "SELECT P.NAME AS PNAME,P.ID AS PID,P.REFERENCE AS PREF, 0.00 AS BAL ,0.0 AS OUTT ,\n" +
                    "SUM(UNITS) AS INN ,C.NAME AS CAT,U.NAME AS UNITS \n" +
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

    public List<ConsolidateStockReport> getConsolidate() {
        return consolidate;
    }

    public void setConsolidate(List<ConsolidateStockReport> consolidate) {
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

        public void readValues(DataRead dr) throws BasicException {
            pname = dr.getString(1);
            OpBal = dr.getDouble(2);
            inn = dr.getDouble(3);
            outt = dr.getDouble(4);
            current = dr.getDouble(5);
            catName = dr.getString(6);
            units = dr.getString(7);
            
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
        
        
    }

    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author swathi
 */
public class TaxReportModel {
    private List<TaxReportLine> taxline;
    private Double totalDebitAmount;

    public TaxReportModel(){

    }

    public static TaxReportModel loadinstance(AppView app,String accname,Date from,Date to) throws BasicException{
        TaxReportModel t = new TaxReportModel();
        Object[] val = new Object[]{accname,from,to,accname,from,to,accname,from,to};
        Object[] values = new Object[]{accname,from,to};
        List l = new StaticSentence(app.getSession(), "SELECT NAME,TRANSTYPE,SUM(AMOUNT) FROM (SELECT F.NAME AS NAME,SUM(A.AMOUNT) AS AMOUNT,A.TRANSTYPE AS TRANSTYPE FROM ACCOUNTJOURNAL A,FACILITY F WHERE F.ID=A.TRANSREF AND A.TRANSTYPE='C' AND A.ACCOUNTID=? AND A.DATE>=? AND A.DATE<=? and a.active=true GROUP BY A.TRANSREF " +
                " UNION ALL " +
                " SELECT A.TRANSREF AS NAME,SUM(A.AMOUNT) AS AMOUNT,A.TRANSTYPE AS TRANSTYPE FROM ACCOUNTJOURNAL A WHERE A.ACCOUNTID=? AND A.TRANSTYPE='C' AND A.TRANSREF NOT IN ( SELECT ID FROM FACILITY) AND A.DATE>=? AND A.DATE<=? and a.transref!='' and a.active=true GROUP BY A.TRANSREF " +
                " UNION ALL " +
                " SELECT 'Others' AS NAME,SUM(A.AMOUNT) AS AMOUNT,A.TRANSTYPE AS TRANSTYPE FROM ACCOUNTJOURNAL A WHERE A.ACCOUNTID=? AND A.TRANSTYPE='C' AND A.TRANSREF NOT IN ( SELECT ID FROM FACILITY) AND A.DATE>=? AND A.DATE<=? and a.transref='' and a.active=true GROUP BY A.TRANSREF )AS TAXREPORT GROUP BY NAME ORDER BY NAME",
                new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),new SerializerReadClass(TaxReportLine.class)).list(val);
        if(l!=null){
            t.taxline = l;
        }else{
            t.taxline = new ArrayList<TaxReportLine>();
        }
        Object obj = new StaticSentence(app.getSession(), "SELECT SUM(AMOUNT) FROM ACCOUNTJOURNAL WHERE ACCOUNTID=? AND TRANSTYPE='D' AND DATE>=? AND DATE<=? and active=true",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}),SerializerReadString.INSTANCE).find(values);
        if(obj!=null)
            t.totalDebitAmount = Double.valueOf(obj.toString());
        else
            t.totalDebitAmount = 0.0;
        return t;
    }

    public List<TaxReportLine> getTaxline()
    {
        return taxline;
    }

    public void setTaxline(List<TaxReportLine> taxline)
    {
        this.taxline = taxline;
    }

    public Double getTotalDebitAmount()
    {
        return totalDebitAmount;
    }

    public void setTotalDebitAmount(Double totalDebitAmount)
    {
        this.totalDebitAmount = totalDebitAmount;
    }


    public static class TaxReportLine implements SerializableRead{
        private String perticuler;
        private String transtype;
        private Double amount;


        public void readValues(DataRead dr) throws BasicException
        {
           perticuler  = dr.getString(1);
           transtype = dr.getString(2);
           amount = dr.getDouble(3);
        }

        public Double getAmount()
        {
            return amount;
        }

        public void setAmount(Double amount)
        {
            this.amount = amount;
        }

        public String getPerticuler()
        {
            return perticuler;
        }

        public void setPerticuler(String perticuler)
        {
            this.perticuler = perticuler;
        }

        public String getTranstype()
        {
            return transtype;
        }

        public void setTranstype(String transtype)
        {
            this.transtype = transtype;
        }



    }

}

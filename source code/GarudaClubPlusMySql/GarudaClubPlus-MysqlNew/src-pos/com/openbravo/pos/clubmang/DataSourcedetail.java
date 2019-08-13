

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountReports;
import com.openbravo.pos.Accounts.AccountReports.AccountsList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourcedetail implements JRDataSource {

    private int m_nIdx;
    private int m_nIdx1;
    private List<AccountReports.AccountsList> v;
    private Date edate;
    private Date sdate;

    public DataSourcedetail() {
        this(new ArrayList<AccountReports.ReportData>());
    }

    public DataSourcedetail(List<AccountReports.ReportData> v) {
        m_nIdx = -1;
    //	this.v = v;
    }

    public DataSourcedetail(List<AccountReports.AccountsList> v, Date date, Date date1) {
        m_nIdx = -1;
        this.v = v;
        edate = date;
        sdate = date1;
    }

    private DataSourcedetail(ArrayList<AccountsList> arrayList) {
        this.v=arrayList;
    }

    private DataSourcedetail(List<AccountsList> acclist,String d) {
        this.v = acclist;
    }
 

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        AccountReports.AccountsList curr = v.get(m_nIdx);
        //AccountReports.AccountsList curr1= v1.get(m_nIdx1);

        if (curr == null ) {
            return null;
        }
        if (sName.equals("amount")) {
            o = curr.getAmt();
        } else if (sName.equals("transtype")) {
            o = curr.getTranstype();
        } else if (sName.equals("transref")) {
            o = curr.getTransref();
        } else if (sName.equals("narration")) {
            o = curr.getNarration();
        } else if (sName.equals("name")) {
            o = curr.getAccountName();
        } else if (sName.equals("transtype1")) {
            o = curr.gettranstype1();
        } else if (sName.equals("date")) {
            o = curr.getDate();
        } else if (sName.equals("transno")) {
            o = curr.getTransno();
        } else if (sName.equals("type")) {
            o = curr.getType();
         } else if (sName.equals("amount1")){
             o = curr.getAmount1();
         }else if (sName.equals("accdet")) {
             if(curr.getAcclist()!=null)
                o = new DataSourcedetail(curr.getAcclist(),null);
             else
                 o=new DataSourcedetail(new ArrayList<AccountReports.AccountsList>());
         }

        return o;

    }

    public boolean next() throws JRException {
        m_nIdx++;
        m_nIdx1++;
        return (m_nIdx < v.size());
    }
}


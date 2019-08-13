/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.AccountJournalReport.AccountJournalData;
import com.openbravo.pos.Accounts.AccountReports;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourceforTrailBalancePeriod implements JRDataSource{
     private int m_nIdx;
	private List<AccountReports.TrailBalanceDataPeriod> v;


	public DataSourceforTrailBalancePeriod() {
		this (new ArrayList<AccountReports.TrailBalanceDataPeriod>());
	}

  

	public DataSourceforTrailBalancePeriod(List<AccountReports.TrailBalanceDataPeriod> v) {
		m_nIdx = -1;
		this.v = v;
	}
  /*  public List<AccountReports.ReportData>  getFieldValue(JRField field) throws JRException {
            List<AccountReports.ReportData> temp=new ArrayList<AccountReports.ReportData>();
            //AccountReports.ReportData temp=new AccountReports.ReportData();
            String sName = field.getName();
		    AccountReports.ReportData curr = v.get(m_nIdx);
             if(sName.equals("mainCategoryList"))
              temp.add(v.get(m_nIdx));
               //  temp=curr;
                    // v.get(m_nIdx);
            return temp;

    }*/

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		AccountReports.TrailBalanceDataPeriod curr = v.get(m_nIdx);

		if (curr== null)
			return null;


		if (sName.equals("name"))
			o = curr.getName();
		else if (sName.equals("debit_f"))
			o = curr.getdebit_f();
        else if (sName.equals("credit_f"))
			o = curr.getcredit_f();
        else if (sName.equals("debit_d"))
			o = curr.getdebit_d();
        else if (sName.equals("credit_d"))
			o = curr.getcredit_d();
        else if (sName.equals("debit_t"))
			o = curr.getdebit_t();
        else if (sName.equals("credit_t"))
			o = curr.getcredit_t();
        else if (sName.equals("level1"))
			o = curr.getLevel1();
        else if (sName.equals("Summary"))
			o = curr.getSummary();

		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

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
public class DataSourceforTrailBalance implements JRDataSource{
     private int m_nIdx;
	private List<AccountReports.TrailBalanceData> v;


	public DataSourceforTrailBalance() {
		this (new ArrayList<AccountReports.TrailBalanceData>());
	}

  

	public DataSourceforTrailBalance(List<AccountReports.TrailBalanceData> v) {
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

		AccountReports.TrailBalanceData curr = v.get(m_nIdx);

		if (curr== null)
			return null;


		if (sName.equals("name"))
			o = curr.getName();
		else if (sName.equals("debit"))
			o = curr.getdebit();
        else if (sName.equals("credit"))
			o = curr.getcredit();
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

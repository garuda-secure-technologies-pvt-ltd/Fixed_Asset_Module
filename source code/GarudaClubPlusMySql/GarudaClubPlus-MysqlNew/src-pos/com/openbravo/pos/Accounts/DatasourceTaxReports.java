/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

/**
 *
 * @author swathi
 */
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DatasourceTaxReports implements JRDataSource {
	private int m_nIdx;
	private List<TaxReportModel.TaxReportLine> v;
    private int i;


	public DatasourceTaxReports() {
		this (new ArrayList<TaxReportModel.TaxReportLine>());
	}

	public DatasourceTaxReports(List<TaxReportModel.TaxReportLine> v) {
		m_nIdx = -1;
		this.v = v;
	}


	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		TaxReportModel.TaxReportLine curr = v.get(m_nIdx);

		if (curr== null)
			return null;
        if (sName.equals("perticular"))
			o = curr.getPerticuler();
        else if (sName.equals("amount"))
			o = curr.getAmount();
        else if (sName.equals("transtype"))
			o = curr.getTranstype();
        else if (sName.equals("slno"))
			o = ++i;

		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}



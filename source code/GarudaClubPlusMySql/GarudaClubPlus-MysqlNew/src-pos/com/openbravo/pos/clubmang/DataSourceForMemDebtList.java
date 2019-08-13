/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//praveen:added to print debitlist
package com.openbravo.pos.clubmang;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForMemDebtList implements JRDataSource {
	private int m_nIdx;
	private List<MemDebtBillingTableModel.Facilityline> v;
        



	public DataSourceForMemDebtList() {
		this (new ArrayList<MemDebtBillingTableModel.Facilityline>());
	}

	public DataSourceForMemDebtList(List<MemDebtBillingTableModel.Facilityline> v) {
		m_nIdx = -1;
		this.v = v;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		MemDebtBillingTableModel.Facilityline curr = v.get(m_nIdx);


		if (curr== null)
			return null;

		if (sName.equals("fname"))
			o = curr.getfname();
		else if (sName.equals("date"))
			o = curr.getBilledDate();
        else if (sName.equals("duedate"))
			o = curr.getduedate();
        else if (sName.equals("amount"))
			o = curr.getamount();
        else if (sName.equals("narration"))
			o = curr.getNarration();
        else if (sName.equals("amt"))
			o = curr.getamt();
       

		
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}
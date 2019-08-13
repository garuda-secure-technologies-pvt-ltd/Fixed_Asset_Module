/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

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

public class DataSource implements JRDataSource {
	private int m_nIdx;
	private List<FacilityBillingTableModel.Facilityline> v;


	public DataSource() {
		this (new ArrayList<FacilityBillingTableModel.Facilityline>());
	}

	public DataSource(List<FacilityBillingTableModel.Facilityline> v) {
		m_nIdx = -1;
		this.v = v;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		FacilityBillingTableModel.Facilityline curr = v.get(m_nIdx);

		if (curr== null)
			return null;

		if (sName.equals("slno"))
			o = curr.getSlNo();
		else if (sName.equals("memno"))
			o = curr.getmno();
        else if (sName.equals("mname"))
			o = curr.getmname();
        else if (sName.equals("sdate"))
			o = curr.getsdate();
        else if (sName.equals("lbilldate"))
			o = curr.getlbilldate();
        else if (sName.equals("no"))
			o = curr.getno();
        else if (sName.equals("amount"))
			o = curr.getamt();

		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}




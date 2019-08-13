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

public class DataSourceFacilityUsage implements JRDataSource {
	private int m_nIdx;
	private List<MemFacilityTableModel.Facilityline> v;
    int i=0;


	public DataSourceFacilityUsage() {
		this (new ArrayList<MemFacilityTableModel.Facilityline>());
	}

	public DataSourceFacilityUsage(List<MemFacilityTableModel.Facilityline> v) {
		m_nIdx = -1;
		this.v = v;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		MemFacilityTableModel.Facilityline curr = v.get(m_nIdx);

		if (curr== null)
			return null;

		if (sName.equals("slno"))
			o = ++i;
		else if (sName.equals("memno"))
			o = curr.getmno();
        else if (sName.equals("memname"))
			o = curr.getmname();
        else if (sName.equals("startdate"))
			o = curr.getsdate();
        else if (sName.equals("lastbilldate"))
			o = curr.getlbilldate();
        else if (sName.equals("createdby"))
			o = curr.getcrby();

		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}




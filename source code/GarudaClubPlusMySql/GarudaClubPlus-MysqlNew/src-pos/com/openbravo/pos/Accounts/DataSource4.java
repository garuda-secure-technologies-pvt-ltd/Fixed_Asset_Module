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

public class DataSource4 implements JRDataSource {
	private int m_nIdx;
	private List<MinUsageCustomer> v;


	public DataSource4() {
		this (new ArrayList<MinUsageCustomer>());
	}

	public DataSource4(List<MinUsageCustomer> v) {
		m_nIdx = -1;
		this.v = v;
	}

    
	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		MinUsageCustomer curr = v.get(m_nIdx);

		if (curr== null)
			return null;
        if (sName.equals("memno"))
			o = curr.getSearchkey();
        else if (sName.equals("mname"))
			o = curr.getName();
        else if (sName.equals("amount"))
			o = curr.getUsageTotal();
        else if (sName.equals("Charge"))
            o = curr.getCamtTotal();
        else if (sName.equals("showUsage"))
			o = curr.getUsageDisplay();
        else if (sName.equals("showCharge"))
            o = curr.getChargeDisplay();


		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}



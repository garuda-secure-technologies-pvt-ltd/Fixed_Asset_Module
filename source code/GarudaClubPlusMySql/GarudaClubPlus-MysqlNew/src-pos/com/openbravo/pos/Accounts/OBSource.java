/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.OBAdjustment.OBData;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class OBSource implements JRDataSource {
	private int m_nIdx;
	private List<OBData> v;
    private boolean flag;

	public OBSource() {
		this (new ArrayList<OBData>(),true);
	}

	public OBSource(List<OBData> v,boolean flag) {
		m_nIdx = -1;
		this.v = v;
        this.flag=flag;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;
		String sName = field.getName();
		OBData curr = v.get(m_nIdx);
		if (curr== null)
			return null;
		if (sName.equals("account"))
			o = curr.getAccount();
		else if (sName.equals("amount"))
			o = curr.getAmount();
        else if (sName.equals("level"))
			o = curr.getLevel();
        else if (sName.equals("summary"))
			o = curr.getSummary();
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

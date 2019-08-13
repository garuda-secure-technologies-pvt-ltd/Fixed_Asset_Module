/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.inventory;

//praveen:added to print debitlist


import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourcePurchaseIndent implements JRDataSource {
	private int m_nIdx;
	private List<PurchaseIndentModel.PrintPurchaseIndent> v;



	public DataSourcePurchaseIndent() {
		this (new ArrayList<PurchaseIndentModel.PrintPurchaseIndent>());
	}

	public DataSourcePurchaseIndent(List<PurchaseIndentModel.PrintPurchaseIndent> v) {
		m_nIdx = -1;
		this.v = v;
	}



	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		PurchaseIndentModel.PrintPurchaseIndent curr = v.get(m_nIdx);


		if (curr== null)
			return null;

		if (sName.equals("product"))
			o = curr.getProduct();
		else if (sName.equals("roqty"))
			o = curr.getQty();
        else if (sName.equals("orderedqty"))
			o = curr.getAppqty();
        else if (sName.equals("pordrate"))
			o = curr.getRate();
        else if (sName.equals("ordrate"))
			o = curr.getApprate();
        else if (sName.equals("vendor"))
			o = curr.getAppvendor();
        else if (sName.equals("remark"))
            o = curr.getRemarks();
        else if (sName.equals("slno"))
            o = curr.getSlno();
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

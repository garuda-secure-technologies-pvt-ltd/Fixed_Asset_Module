/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForjBRStatement implements JRDataSource {
	private int m_nIdx;
	private List<jBRStatementModel. jBRStatement> v;



	public DataSourceForjBRStatement() {
		this (new ArrayList<jBRStatementModel. jBRStatement>());
	}

	public DataSourceForjBRStatement(List<jBRStatementModel. jBRStatement> v) {
		m_nIdx = -1;
		this.v = v;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

	String sName = field.getName();

		jBRStatementModel.jBRStatement curr = v.get(m_nIdx);


		if (curr== null){
			return null;
                }
//
		if (sName.equals("BankDates"))
			o = curr.getBankDates();
		else if (sName.equals("Narration"))
			o = curr.getNarrate();
        else if (sName.equals("Amount"))
			o = curr.getAmount();
        else if (sName.equals("Dates"))
			o = curr.getDates();
//        

		
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourceForMemberStatement3 implements JRDataSource {
	private int m_nIdx;
	private List<MemberStatementModel2.Facilityline> v;



	public DataSourceForMemberStatement3() {
		this (new ArrayList<MemberStatementModel2.Facilityline>());
	}

	public DataSourceForMemberStatement3(List<MemberStatementModel2.Facilityline> v) {
		m_nIdx = -1;
		this.v = v;
	}



	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		MemberStatementModel2.Facilityline curr = v.get(m_nIdx);


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
        else if (sName.equals("orgamt"))
			o = curr.getamt();
        else if (sName.equals("narration"))
			o = curr.getNarration();       
        else if (sName.equals("transno"))
            o = curr.gettransno();
        else if (sName.equals("prvdues"))
			o = curr.getPrDues();
        else if (sName.equals("prvbal"))
			o = curr.getPrBalance();
        else if (sName.equals("amtreceived"))
			o = curr.getAmountRecived();
        else if (sName.equals("memno"))
			o = curr.getMemNo();
        else if (sName.equals("memname"))
            o = curr.getMemName();
        else if (sName.equals("memaddr"))
            o = curr.getAddress();
        else if (sName.equals("type"))
            o = curr.getTransType();
         else if (sName.equals("obtype"))
            o = curr.getOptranstype();
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

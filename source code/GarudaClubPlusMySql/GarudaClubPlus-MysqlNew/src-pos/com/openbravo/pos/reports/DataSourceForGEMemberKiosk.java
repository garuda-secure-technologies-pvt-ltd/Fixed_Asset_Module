/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.reports;

import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author user
 */
public class DataSourceForGEMemberKiosk implements JRDataSource {

   private int m_nIdx;
	private List<KioskGuestEntryReportTableModel.KioskBean> v;



	public DataSourceForGEMemberKiosk() {
		this (new ArrayList<KioskGuestEntryReportTableModel.KioskBean>());
	}

	public DataSourceForGEMemberKiosk(List<KioskGuestEntryReportTableModel.KioskBean> v) {
		m_nIdx = -1;
		this.v = v;
	}



	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		KioskGuestEntryReportTableModel.KioskBean curr = v.get(m_nIdx);
                SimpleDateFormat sdfFrom  = new SimpleDateFormat("dd-MMM-yyyy");

		if (curr== null)
			return null;

		if (sName.equals("rno"))
			o = curr.getReceiptno();
		else if (sName.equals("date"))
			o = sdfFrom.format(curr.getDate());
        else if (sName.equals("mname"))
			o = curr.getMemName();
        else if (sName.equals("gcat"))
			o = curr.getgCat();
        else if (sName.equals("amount"))
			o = curr.gettAmt();
        else if (sName.equals("num"))
			o = curr.getNum();
        else if (sName.equals("names"))
            o = curr.getgNames();
                else if (sName.equals("memNum"))
            o = curr.getMemNum();
                
                
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
    
}

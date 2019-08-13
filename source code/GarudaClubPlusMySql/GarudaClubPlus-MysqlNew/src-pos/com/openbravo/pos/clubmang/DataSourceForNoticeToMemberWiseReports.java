/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;


import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import java.text.SimpleDateFormat;
import java.util.List;


class DataSourceForNoticeToMemberWiseReports implements JRDataSource  {

    private int m_nIdx;
	private List<DueListNoticeTableModel.NoticeTrackedBean> v;
        private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    
    
    public DataSourceForNoticeToMemberWiseReports() {
        this (new ArrayList<DueListNoticeTableModel.NoticeTrackedBean>());
    }
  

	public DataSourceForNoticeToMemberWiseReports(List<DueListNoticeTableModel.NoticeTrackedBean> v) {
		m_nIdx = -1;
		this.v = v;
	}
    

    public Object getFieldValue(JRField jrf) throws JRException {
       
        Object o = null;

		String sName = jrf.getName();

		DueListNoticeTableModel.NoticeTrackedBean curr = v.get(m_nIdx);

		if (curr== null)
			return null;

		if (sName.equals("slNo"))
			o = m_nIdx+1;
        else if (sName.equals("memSHipNo"))
			o = curr.getMemSearchKey();
        else if (sName.equals("memname"))
			o = curr.getMemberName();
        else if (sName.equals("noticeName"))
			o = curr.getDisplayName();
        else if (sName.equals("generatedDate"))
			o = sdf.format(curr.getGeneratedDate());
        else if (sName.equals("dueDate"))
			o = sdf.format(curr.getDueDateAsOnGeneratedDate());
        else if (sName.equals("payByDate"))
			o = sdf.format(curr.getPayByDate());
        else if (sName.equals("dueAmount"))
			o = curr.getDueAmount();
        else if (sName.equals("commMode"))
                    if(curr.getCommMadeBy()!=null && curr.getCommMadeBy().length()>0 )
			o = curr.getCommMadeBy();
                    else
                        o = "-";
        else if (sName.equals("dateOfDispatch"))
            if(curr.getDataOfDispatch()!=null && curr.getDataOfDispatch().toString().length()>0 )
			o = sdf.format(curr.getGeneratedDate());
                    else
                        o = "-";
        else if (sName.equals("receivedRef"))
            if(curr.isReceivedRef())
			o = "Yes";
            else
                o = "No";
        else if (sName.equals("recBy"))
			if(curr.getReceivedBy()!=null && curr.getReceivedBy().length()>0 )
			o = curr.getReceivedBy();
                    else
                        o = "-";
         else if (sName.equals("recDate"))
			if(curr.getReceivedDate()!=null && curr.getReceivedDate().toString().length()>0 )
			o = sdf.format(curr.getReceivedDate());
                    else
                        o = "-";
        else if (sName.equals("status"))
			if(curr.isActive())
			o = "Active";
                    else
                        o = "Deactivated";
       else if (sName.equals("notes"))
			if(curr.getaNotes()!=null &&curr.getaNotes().length()>0 )
			o = "Additional Details: " + curr.getaNotes();
                    else
                        o = "";         

		
		return o;
    }
    public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

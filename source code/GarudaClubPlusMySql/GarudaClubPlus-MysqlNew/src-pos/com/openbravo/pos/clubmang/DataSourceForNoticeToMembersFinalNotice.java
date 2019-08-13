/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

//praveen:added to print debitlist


import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForNoticeToMembersFinalNotice implements JRDataSource {
	private int m_nIdx;
        private int m_nIdx2;
	private List<DueListNoticeTableModel.Facilityline2> v;
        private List<List<DueListNoticeTableModel.NoticeTrackedBean>> v1;
        private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        private String ref = null;
        private int num = 0;
        DueListNoticeTableModel.NoticeTrackedBean ntb = null;


	public DataSourceForNoticeToMembersFinalNotice() {
		this (new ArrayList<DueListNoticeTableModel.Facilityline2>(), new ArrayList<List<DueListNoticeTableModel.NoticeTrackedBean>>());
             
	}

	public DataSourceForNoticeToMembersFinalNotice(List<DueListNoticeTableModel.Facilityline2> v, List<List<DueListNoticeTableModel.NoticeTrackedBean>> v1) {
		m_nIdx = -1;
		this.v = v;
                this.v1 = v1;
                ref = v.get(0).getMemNo();
                ntb = v1.get(0).get(0);
	}
        
        


	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		DueListNoticeTableModel.Facilityline2 curr = v.get(m_nIdx);
                List<DueListNoticeTableModel.NoticeTrackedBean> ntbList = v1.get(0);
		if (curr== null)
			return null;
                
                if(!ref.equals(curr.getMemNo()))
                {
                    ref = curr.getMemNo();
                    
                    for (int i = 0; i < v1.size(); i++) {
                        List<DueListNoticeTableModel.NoticeTrackedBean> noticeTB = v1.get(i);
                        if(noticeTB.get(0).getMemSearchKey().equals(ref))
                        {
                            ntb = noticeTB.get(0);
                            break;
                        }
                        
                    }
                    
//                    for (Iterator<List<DueListNoticeTableModel.NoticeTrackedBean>> it = v1.iterator(); it.hasNext();) {
//                        List<DueListNoticeTableModel.NoticeTrackedBean> noticeTB = it.next();
//                        if(ref.equals(noticeTB.get().getMemSearchKey()))
//                        {
//                            ntb = noticeTB;
//                            break;
//                        }
//                    }
//                    num++;
//                    ntb = v1.get(num);
                }
               
                
               
		if (sName.equals("fname"))
                    o = curr.getfname();
		else if (sName.equals("date"))
			o = curr.getBilledDate();
        else if (sName.equals("duedate"))
			o = curr.getduedate();
        else if (sName.equals("orgamt"))
			o = curr.getamount();
        else if (sName.equals("amount"))
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
         else if (sName.equals("previousNoticeName"))
             o = ntb.getDisplayName();
         else if (sName.equals("pGeneratedDate"))
             o = sdf.format(ntb.getSelectedDate());
         else if (sName.equals("pDueAmount"))
             o = ntb.getDueAmount();
         else if(sName.equals("pPayByDate"))
             o = sdf.format(ntb.getPayByDate());
         else if(sName.equals("pDueDate"))
             o = sdf.format(ntb.getDueDateAsOnGeneratedDate());
                
         else if(sName.equals("NoticeName"))
         {
             o = ntb.getDisplayName();
         }
         else if(sName.equals("DateOfGeneration"))
         {
             o = sdf.format(ntb.getGeneratedDate());
         }
         else if(sName.equals("DueDate"))
         {
             o = sdf.format(ntb.getDueDateAsOnGeneratedDate());
         }
         else if(sName.equals("PayByDate"))
         {
             o = sdf.format(ntb.getPayByDate());
         }
         else if(sName.equals("DueAMountDetails"))
         {
             o =ntb.getDueAmount() ;
          
         }
		return o;
                
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}
/*

package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceForNoticeToMembersOtherThanFirst implements JRDataSource {
	private int m_nIdx;
        
	private List<DueListNoticeTableModel.Facilityline2> v;
        private List<List<DueListNoticeTableModel.NoticeTrackedBean>> v1;
        private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        private List<DueListNoticeTableModel.NoticeTrackedBean> pNoticeList;
        private int num = 0;
        private int num2 = 0;
        private String ref = null;

	public DataSourceForNoticeToMembersOtherThanFirst() {
		this (new ArrayList<DueListNoticeTableModel.Facilityline2>(), new ArrayList<List<DueListNoticeTableModel.NoticeTrackedBean>>());
             
	}

	public DataSourceForNoticeToMembersOtherThanFirst(List<DueListNoticeTableModel.Facilityline2> v, List<List<DueListNoticeTableModel.NoticeTrackedBean>> v1) {
		m_nIdx = -1;
		this.v = v;
                this.v1 = v1;
                ref = v.get(0).getAddress();
	}
        
        


	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;

		String sName = field.getName();

		DueListNoticeTableModel.Facilityline2 curr = v.get(m_nIdx);
                DueListNoticeTableModel.NoticeTrackedBean ntb = v1.get(0).get(0);
               DueListNoticeTableModel.NoticeTrackedBean ntb2 = null;
                List<DueListNoticeTableModel.NoticeTrackedBean> ntbList = v1.get(0);

		if (curr== null)
			return null;
                if(ref.equals(curr.getAddress()))
                {
                    
                }
                else
                {
                    ref = curr.getAddress();
                    num++;
                    ntbList = v1.get(num);
                }
                

		if (sName.equals("fname"))
			o = curr.getfname();
		else if (sName.equals("date"))
			o = curr.getBilledDate();
        else if (sName.equals("duedate"))
			o = curr.getduedate();
        else if (sName.equals("orgamt"))
			o = curr.getamount();
        else if (sName.equals("amount"))
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
         else if (sName.equals("previousNoticeName"))
             o = ntb.getNoticeName();
         else if (sName.equals("pGeneratedDate"))
             o = sdf.format(ntb.getGeneratedDate());
         else if (sName.equals("pDueAmount"))
             o = ntb.getDueAmount();
         else if(sName.equals("pPayByDate"))
             o = sdf.format(ntb.getPayByDate());
         else if(sName.equals("pDueDate"))
             o = sdf.format(ntb.getDueDateAsOnGeneratedDate());
         else if(sName.equals("NoticeName"))
             if(num2>ntbList.size())
             {
                 ntb2 = ntbList.get(num2);
                 o = ntb2.getNoticeName();
                 num2++;
                 
//                 if(v1.get(m_nIdx).get(0).getMemSearchKey().equals(curr.getMemNo()))
//                 {
//                   o =  v1.get(m_nIdx).get(0).getNoticeName();
//                     
//                 }
//                 else
//                 {
//                     o = null;
//                 }
             }
//                else
//             {
//                 o = null;
//             }
		return o;
                
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}*/
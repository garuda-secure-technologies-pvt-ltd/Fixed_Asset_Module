/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DueListDataSource implements JRDataSource{
   private int m_nIdx;
   private int temp=0;
	private List<DueListTableModel.ReportLine> v;


	public DueListDataSource() {
		this (new ArrayList());
	}

	public DueListDataSource(List v) {
		m_nIdx = -1;
		this.v = v;
	}
    public void setTemp(int temp){
      this.temp=temp;
    }
	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;
        int i=0;
		String sName = field.getName();
		DueListTableModel.ReportLine curr = v.get(m_nIdx);
		if (curr== null)
			return null;
		if (sName.equals("memno"))
			o = curr.getSearchkey();
        else if (sName.equals("memname"))
			o = curr.getName();
        else {
            int flag=0;
            while(flag==0){
              //  List del=curr.getFacilityList();
            if(sName.equals("facility"+(i+1))){
                o=curr.getFacilityList().get(temp+i-1).getFacilityName();
                flag=1;
                //temp++;
            }else if(sName.equals("status"+(i+1))){
                o=curr.getFacilityList().get(temp+i-1).getLastBillPeriod();
                flag=1;
                //temp++;
            }else if(sName.equals("fdue"+(i+1))){
                Double a=0.0;
                if(curr.getFacilityList().get(temp+i-1).getDueAmount()!=null)
                    a=curr.getFacilityList().get(temp+i-1).getDueAmount();
                o=a;
                flag=1;
                //temp++;
            }else if(sName.equals("foverdue"+(i+1))){
                Double a=0.0;
                if(curr.getFacilityList().get(temp+i-1).getOverDueAmount()!=null)
                    a=curr.getFacilityList().get(temp+i-1).getOverDueAmount();
                o=a;
                flag=1;
                //temp++;
            }else if(i>3){
                o=null;
                i++;
                flag=1;
            }else{
                i++;
                if((temp+i)>curr.getFacilityList().size())
                    flag=1;

                
            }
            }
           // temp++;
        }
		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}

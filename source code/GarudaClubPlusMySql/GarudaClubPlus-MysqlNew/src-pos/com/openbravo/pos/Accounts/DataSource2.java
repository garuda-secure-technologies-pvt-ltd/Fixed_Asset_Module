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
public class DataSource2 implements JRDataSource {
	private int m_nIdx;
	private List<Account> v;
    private boolean flag;

	public DataSource2() {
		this (new ArrayList<Account>(),true);
	}

	public DataSource2(List<Account> v,boolean flag) {
		m_nIdx = -1;
		this.v = v;
        this.flag=flag;
	}

	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;
		String sName = field.getName();
		Account curr = v.get(m_nIdx);
		if (curr== null)
			return null;
		if (sName.equals("account"))
			o = curr.getAccountName();
		else if (sName.equals("level"))
			o = curr.getPrintLevel();
        else if (sName.equals("amount")){
          if(flag==true){
              double amt;
			if( curr.getSKey().contains("R"))
                amt= curr.getCamt();
            else if( curr.getSKey().contains("P"))
                amt= curr.getAmount();
            else
                amt=curr.getAmount()-curr.getCamt();
                o=amt;
          }else{
                double amt=curr.getAmount()-curr.getCamt();
                if(amt<0){
                   amt=amt*-1;
                }
                o=amt;
          }
        }else if (sName.equals("type"))
			o = "";
        else if (sName.equals("status"))
			o = curr.getChilPrintStatus();


		return o;
	}

	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx < v.size());
	}
}


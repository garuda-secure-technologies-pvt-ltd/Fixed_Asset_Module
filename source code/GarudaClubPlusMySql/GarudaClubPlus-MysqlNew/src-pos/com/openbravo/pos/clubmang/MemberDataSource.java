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
public class MemberDataSource implements JRDataSource {
     private int m_nIdx;
    private int temp=0;
    private int i=0;
	private List<MemberDetails> v;


	public MemberDataSource() {
		this (new ArrayList());
	}

	public MemberDataSource(List v) {
		m_nIdx = -1;
		this.v = v;
	}
    public void setTemp(int temp){
      this.temp=temp;
    }
	public Object getFieldValue(JRField field) throws JRException {
		Object o = null;
        String sName = field.getName();
	    MemberDetails curr = v.get(m_nIdx);
        if (curr== null)
			return null;
         if(sName.equals("column1"))
            o = ++i;
        else if (sName.equals("column2"))
			o = curr.getName();
        else if (sName.equals("memno"))
			o = curr.getMemNo();
        else if (sName.equals("column4"))
			o = curr.getMemType();
        else if (sName.equals("column5"))
			o = curr.getDoj();
        else if (sName.equals("column6"))
			o = curr.getAddress();
        else if (sName.equals("column7"))
			o = curr.getContactNo();
        else if(sName.equals("column8"))
            o = curr.getMemTaxCategory();
        else if(sName.equals("column9"))
            o = curr.getVisible();
        else if(sName.equals("column10"))
            o = curr.getDoe();
        else if(sName.equals("column11"))
            o = curr.getDob();
		return o;

           // temp++;


	}

    public boolean next() throws JRException {
        m_nIdx++;
		return (m_nIdx < v.size());
    }







}
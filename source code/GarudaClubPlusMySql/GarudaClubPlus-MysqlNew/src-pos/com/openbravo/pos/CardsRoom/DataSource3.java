/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSource3 implements JRDataSource{
     private int m_nIdx;
	 private int cnt=2;
     private int max;
     private List v;

	public DataSource3(int max,List list) {
        this.max=max;
        v=list;
        m_nIdx=-1;
	}





    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;
		String sName = field.getName();
		if (sName.equals("cnt")){
			o=cnt;
            cnt++;
        }else o=null;
		return o;
    }


	public boolean next() throws JRException {
		m_nIdx++;
		return (m_nIdx <= max);
	}
}


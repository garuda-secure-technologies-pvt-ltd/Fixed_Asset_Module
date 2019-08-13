/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import com.openbravo.pos.clubmang.MemberStatementModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author user
 */
public class DataSourceForCurrentStock implements JRDataSource{
    
     private int m_nIdx;
	private List<CalculationLogicTwo.StockBean> v;
        
        
        public DataSourceForCurrentStock() {
		this (new ArrayList<CalculationLogicTwo.StockBean>());
	}
        public DataSourceForCurrentStock(List<CalculationLogicTwo.StockBean> v) {
		m_nIdx = -1;
		this.v = v;
	}


    

    public boolean next() throws JRException {
       
        	m_nIdx++;
		return (m_nIdx < v.size());
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

		String sName = field.getName();

		          CalculationLogicTwo.StockBean curr;
        curr = v.get(m_nIdx);

             
		/*if (curr== null)
			return null;

		if (sName.equals("prod"))
			o = curr.getProductName();
		else if (sName.equals("warehousename"))
			o = curr.getWareHouse();
                else if (sName.equals("QUANTITY"))
			o = curr.getQty();
                else if (sName.equals("TotQty"))
			o = curr.getTotal();
                else if (sName.equals("Rate"))
			o = curr.getRate();
                else if (sName.equals("TotAmo"))
			o = curr.getAmount(); */
                
             
		 if (curr== null)
			return null;

		if (sName.equals("prod"))
			o = curr.getProduct();
		else if (sName.equals("W1"))
			o = curr.getW1();
                else if (sName.equals("W2"))
			o = curr.getW2();
                else if (sName.equals("W3"))
			o = curr.getW3();
                else if (sName.equals("W4"))
			o = curr.getW4();
                else if (sName.equals("W5"))
			o = curr.getW5();
                
                else if (sName.equals("Q1"))
			o = curr.getQ1();
                else if (sName.equals("Q2"))
			o = curr.getQ2();
                else if (sName.equals("Q3"))
			o = curr.getQ3();
                else if (sName.equals("Q4"))
			o = curr.getQ4();
                else if (sName.equals("Q5"))
			o = curr.getQ5();
               else if (sName.equals("W6"))
			o = curr.getW6();
                else if (sName.equals("W7"))
			o = curr.getW7();
                else if (sName.equals("W8"))
			o = curr.getW8();
                else if (sName.equals("W9"))
			o = curr.getW9();
                else if (sName.equals("W10"))
			o = curr.getW10();
                
                else if (sName.equals("Q6"))
			o = curr.getQ6();
                else if (sName.equals("Q7"))
			o = curr.getQ7();
                else if (sName.equals("Q8"))
			o = curr.getQ8();
                else if (sName.equals("Q9"))
			o = curr.getQ9();
                else if (sName.equals("Q10"))
			o = curr.getQ10();
               
                else if (sName.equals("TotQty"))
			o = curr.getTotQty();
                else if (sName.equals("Rate"))
			o = curr.getRate();
                else if (sName.equals("TotAmo"))
			o = curr.getTotAmount();
               
                
                
		return o;
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.jdbc.core.RowMapper;


public class TransactionInfo implements Serializable{
    private String receiptno;
    private String amount;
    private String tdate;
    private String transtype;
    private String facility;
    private String transref;

    public String getTransref() {
        return transref;
    }

    public void setTransref(String transref) {
        this.transref = transref;
    }


    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }
    public String getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(String receiptno) {
        this.receiptno = receiptno;
    }

    public static final class TransactionInfoRowMapper implements RowMapper<TransactionInfo>
    {

		public TransactionInfo mapRow(ResultSet rs, int arg1)
				throws SQLException {
			
			
			 TransactionInfo transinfo = new TransactionInfo();
		        DateFormat df=new SimpleDateFormat("dd/MM/yy");
		        DecimalFormat nf1=new DecimalFormat("###0.00");
		        transinfo.setTdate(df.format(rs.getDate("DATE")));
		        transinfo.setReceiptno(rs.getString("TRANSNO"));
		        transinfo.setTranstype(rs.getString("TRANSTYPE"));
		        transinfo.setAmount(nf1.format(rs.getDouble("AMOUNT")));
		        if(rs.getString(5)!=null)
		        transinfo.setFacility(rs.getString("NAME"));
		        else
		            transinfo.setFacility(rs.getString("TRANSREF"));
			
			
			return transinfo;
		}
    	
    }
    

}


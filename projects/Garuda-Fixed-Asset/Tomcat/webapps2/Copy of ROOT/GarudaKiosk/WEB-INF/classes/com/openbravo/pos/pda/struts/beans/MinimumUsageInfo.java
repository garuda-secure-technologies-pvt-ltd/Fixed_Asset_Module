package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class MinimumUsageInfo implements Serializable{
	   private String mname;
	   
	    private double limit;
	    private double usage;
	   
	    private Date lBilldate;
	    private String ptype;

	    private boolean pdoj;
	    private int pno;
	    private String pdate;
	    private boolean paccurate;
	    private int pfmonth;

	    public int getPfmonth() {
	        return pfmonth;
	    }

	    public void setPfmonth(int pfmonth) {
	        this.pfmonth = pfmonth;
	    }
	    public boolean isPaccurate() {
	        return paccurate;
	    }

	    public void setPaccurate(boolean paccurate) {
	        this.paccurate = paccurate;
	    }

	    public String getPdate() {
	        return pdate;
	    }

	    public void setPdate(String pdate) {
	        this.pdate = pdate;
	    }

	    public int getPno() {
	        return pno;
	    }

	    public void setPno(int pno) {
	        this.pno = pno;
	    }
	    public String getPtype() {
	        return ptype;
	    }
	    public void setPtype(String ptype) {
	        this.ptype = ptype;
	    }
	    public Date getLBilldate() {
	        return lBilldate;
	    }
	    public void setLBilldate(Date lBilldate) {
	        this.lBilldate = lBilldate;
	    }
	    public double getLimit() {
	        return limit;
	    }

	    public void setLimit(double limit) {
	        this.limit = limit;
	    }

	    public String getMname() {
	        return mname;
	    }

	    public void setMname(String mname) {
	        this.mname = mname;
	    }

	    public boolean isPdoj() {
	        return pdoj;
	    }

	    public void setPdoj(boolean pdoj) {
	        this.pdoj = pdoj;
	    }

	    
	    public double getUsage() {
	        return usage;
	    }

	    public void setUsage(double usage) {
	        this.usage = usage;
	    }

	    public static final class MinimumUsageInfoRowMapper  implements RowMapper
	    {

	    	 
	    	
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				MinimumUsageInfo minfo = new MinimumUsageInfo();

	            minfo.setMname(rs.getString("NAME"));
	           
	            minfo.setLimit(rs.getDouble("ALIMIT"));
	            
	            minfo.setLBilldate(rs.getDate("LBILLDATE"));
	            minfo.setPtype(rs.getString("PERIODTYPE"));
	            minfo.setPno(rs.getInt("NO"));
	            minfo.setPdate(rs.getString("DATE"));
	            minfo.setPaccurate(rs.getBoolean("ACCURATE"));
	            minfo.setPdoj(rs.getBoolean("DOJ"));


	        return minfo;

			}
	    	
	    }
	    

}

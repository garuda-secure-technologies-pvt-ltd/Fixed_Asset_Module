package com.openbravo.pos.pda.struts.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

public class EventsInfo {
	 private String ename;
	    private String stime;
	    private String etime;
	    private String edate;
	    private String description;

	    public String getEdate() {
	        return edate;
	    }

	    public void setEdate(String edate) {
	        this.edate = edate;
	    }

	    public String getEtime() {
	        return etime;
	    }

	    public void setEtime(String etime) {
	        this.etime = etime;
	    }

	    public String getStime() {
	        return stime;
	    }

	    public void setStime(String stime) {
	        this.stime = stime;
	    }


	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    

	    public String getEname() {
	        return ename;
	    }

	    public void setEname(String ename) {
	        this.ename = ename;
	    }
	    public static final class EventsInfoRowMapper implements RowMapper{
	    	
	    	
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				DateFormat df=new SimpleDateFormat("dd-MM-yy");
		        EventsInfo eventinfo = new EventsInfo();
		        eventinfo.setEname(rs.getString("NAME"));
		        eventinfo.setDescription(rs.getString("DESCRIPTION"));
		        eventinfo.setEdate(df.format(rs.getDate("EDATE")));

		        eventinfo.setStime(rs.getString("STIME"));
		        eventinfo.setEtime(rs.getString("ETIME"));

		        
		        return eventinfo;
			}
	    	
	    }
}

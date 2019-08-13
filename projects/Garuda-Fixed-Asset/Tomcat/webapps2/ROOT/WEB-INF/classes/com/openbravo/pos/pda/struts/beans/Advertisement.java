package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Advertisement implements Serializable {
	
	private String id, path, linkToWeb, notes, panelName;
	private String crdate;
	private boolean active;
	private int timeinterval;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLinkToWeb() {
		return linkToWeb;
	}
	public void setLinkToWeb(String linkToWeb) {
		this.linkToWeb = linkToWeb;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public int getTimeinterval() {
		return timeinterval;
	}
	public void setTimeinterval(int timeinterval) {
		this.timeinterval = timeinterval;
	}
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}


	public static final class AdvertisementRowMapper implements RowMapper<Advertisement>
	{

		public Advertisement mapRow(ResultSet rs, int arg1)
				throws SQLException {
			// TODO Auto-generated method stub
			
			Advertisement as = new Advertisement();
			as.setId(rs.getString("ID"));
			as.setPath(rs.getString("PATH"));
			as.setLinkToWeb(rs.getString("LINKTOWEB"));
			as.setTimeinterval(rs.getInt("TIMEINTERVAL"));
			as.setPanelName(rs.getString("PANELNAME"));
			
			return as;
		}
		
	}
	

}

package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

public class UpcomingEvents implements Serializable{

	private String id, path, linkToWeb, notes, panelName;
	private String crDate, evtDate, fromDate, toDate;
	private boolean active;
	private int timeInSec;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
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
	public String getCrDate() {
		return crDate;
	}
	public void setCrDate(String crDate) {
		this.crDate = crDate;
	}
	public String getEvtDate() {
		return evtDate;
	}
	public void setEvtDate(String evtDate) {
		this.evtDate = evtDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getTimeInSec() {
		return timeInSec;
	}
	public void setTimeInSec(int timeInSec) {
		this.timeInSec = timeInSec;
	}


	public static final class UpcominEventRowMapper implements RowMapper<UpcomingEvents>
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		public UpcomingEvents mapRow(ResultSet rs, int arg1)
				throws SQLException {
			UpcomingEvents ue = new UpcomingEvents();
			ue.setId(rs.getString("ID"));
			ue.setPath(rs.getString("PATH"));
			ue.setLinkToWeb(rs.getString("LINKTOWEB"));
			ue.setNotes(rs.getString("NOTES"));
			ue.setCrDate(sdf.format(rs.getDate("CRDATE")));
			ue.setEvtDate(sdf.format(rs.getDate("EVENTDATE")));
			ue.setActive(rs.getBoolean("ACTIVE"));
			ue.setPanelName(rs.getString("PANELNAME"));
			ue.setTimeInSec(rs.getInt("TIMEINTERVAL"));
		
			
			
			
			return ue;
		}
		
	}
}

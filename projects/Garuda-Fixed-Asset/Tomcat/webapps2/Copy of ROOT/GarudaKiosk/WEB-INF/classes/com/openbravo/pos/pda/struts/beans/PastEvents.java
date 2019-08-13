package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

public class PastEvents implements Serializable{

	private String id, path, notes, linkToWeb, panelName;
	private String crDate, evtDate;
	private boolean active;
	private int timeInterval;
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public int getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLinkToWeb() {
		return linkToWeb;
	}
	public void setLinkToWeb(String linkToWeb) {
		this.linkToWeb = linkToWeb;
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
	
	public static final class PastEventsRowMapper implements RowMapper<PastEvents>
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		public PastEvents mapRow(ResultSet rs, int arg1) throws SQLException {
			PastEvents ps = new PastEvents();
			
			ps.setId(rs.getString("ID"));
			ps.setPath(rs.getString("PATH"));
			ps.setEvtDate(sdf.format(rs.getDate("EVENTDATE")));
			ps.setNotes(rs.getString("NOTES"));
			ps.setLinkToWeb(rs.getString("LINKTOWEB"));
			ps.setPanelName(rs.getString("PANELNAME"));
			ps.setTimeInterval(rs.getInt("TIMEINTERVAL"));
			
			return ps;
		}
		
	}
	
}

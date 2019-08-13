package com.openbravo.pos.pda.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.EventsInfo;
import com.openbravo.pos.pda.struts.beans.EventsInfo.EventsInfoRowMapper;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ShowEventsAction extends ActionSupport implements SessionAware{

	private Map session;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
	
	
	public ShowEventsAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}

	@Override
	public String execute() throws Exception {
		
		List einfo = new ArrayList<EventsInfo>();
			
		einfo = jdbcTemplate.query("SELECT NAME,DESCRIPTION,EDATE,STIME,ETIME FROM EVENTS WHERE EDATE>=CURDATE() AND ACTIVE=1 ORDER BY EDATE", new EventsInfoRowMapper());
		session.put("eventsInfo", einfo);
		
		return Constants.SUCCESS;
	}

	
	
	
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
}

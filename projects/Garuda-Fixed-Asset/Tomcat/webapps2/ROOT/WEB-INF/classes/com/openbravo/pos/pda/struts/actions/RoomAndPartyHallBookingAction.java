package com.openbravo.pos.pda.struts.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class RoomAndPartyHallBookingAction  extends ActionSupport implements SessionAware{
	
    private JdbcTemplate jdbcTemplate;
    private Map session;
	
    @Override
    public String execute() throws Exception {
    	
    	return Constants.FAILURE;
    }
    
    
    
    public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
 
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.pda.struts.actions;
import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.Advertisement;
import com.openbravo.pos.pda.struts.beans.Advertisement.AdvertisementRowMapper;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.CompanyInfo.CompanyInfoMapper;
import com.openbravo.pos.pda.struts.beans.PastEvents;
import com.openbravo.pos.pda.struts.beans.PastEvents.PastEventsRowMapper;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents.UpcominEventRowMapper;
import com.openbravo.pos.pda.util.PropertyUtils;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

public class HomePageAction extends ActionSupport implements SessionAware{
    
    /**
	 * 
	 */
    private List<UpcomingEvents> upcom;
    private List<PastEvents> events;
    private List<Advertisement> advt;
    private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private Map session;
  
    
    
	public HomePageAction() {
        try
        {
        	JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        	jdbcTemplate = jdbcImp.getJdbcTemplate();
       
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public String execute() {
        try {
            	
            	cInfo = jdbcTemplate.queryForObject("SELECT ID,CLUBNAME, ADDRESS, CLUBLOGO, WEBSITE, ACTIVATEFECILITYREQ, DEACTIVATEFECILITYREQ FROM CLUBDETAILSKIOSK WHERE ACTIVE = TRUE", new CompanyInfoMapper());
            	cInfo.setUpcmngEvts(jdbcTemplate.query("SELECT * FROM PANELONE WHERE ACTIVE = TRUE order by CRDATE", new UpcominEventRowMapper()));
            	cInfo.setPastEvt(jdbcTemplate.query("SELECT * FROM PANELTWO WHERE ACTIVE=TRUE AND FROMDATE < CURDATE() AND TODATE > CURDATE() ORDER BY CRDATE", new PastEventsRowMapper()));
            	cInfo.setAdvt(jdbcTemplate.query("SELECT * FROM PANELTHREE WHERE ACTIVE=TRUE AND FROMDATE < CURDATE() AND TODATE > CURDATE() ORDER BY CRDATE", new AdvertisementRowMapper()));
               
        } catch (Exception ex) {
        	
        	addActionError("Something went wrong..!!!!");
        	addActionError(ex.getMessage());
        	Logger.getLogger(HomePageAction.class.getName()).log(Level.SEVERE, null, ex);
        	return Constants.ERROR;
            
        }
       session.put("home", cInfo);
       if(session.get("user")!=null)
       {
    	   return Constants.ALREADYLOGGEDIN;
       }
     
    return Constants.SUCCESS;
    }

    public void setcInfo(CompanyInfo comI)
    {
        cInfo = comI;
    }
    public CompanyInfo getcInfo() {
        return cInfo;
    }
    public List<UpcomingEvents> getUpcom() {
		return upcom;
	}
	public void setUpcom(List<UpcomingEvents> upcom) {
		this.upcom = upcom;
	}
    public void setSession(Map session) {
        this.session = session;
    }
    public Map getSession() {
		return session;
	}

	public List<PastEvents> getEvents() {
		return events;
	}

	public void setEvents(List<PastEvents> events) {
		this.events = events;
	}

	public List<Advertisement> getAdvt() {
		return advt;
	}

	public void setAdvt(List<Advertisement> advt) {
		this.advt = advt;
	}
	
	
 
}

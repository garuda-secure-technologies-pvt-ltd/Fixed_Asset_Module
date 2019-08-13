package com.openbravo.pos.pda.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.openbravo.pos.pda.struts.beans.ActiveFacility;
import com.openbravo.pos.pda.struts.beans.ActiveFacility.ActiveFelicitiesRowMapper;
import com.opensymphony.xwork2.ActionSupport;

public class ActiveFacilitiesAction extends ActionSupport implements SessionAware {
	private Map session;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
	
	
	public ActiveFacilitiesAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}

	@Override
	public String execute() throws Exception {
		uInfo = (UserInfo) session.get("user");
		
		try
		{
		 ArrayList<ActiveFacility> activefacility = new ArrayList<ActiveFacility>();
		 activefacility = (ArrayList<ActiveFacility>) jdbcTemplate.query("SELECT M.ID,F.NAME,F.RENEWALAMT,M.STATUS_,F.TYPE_,F.NAME,CASE WHEN M.USERID IS NOT NULL THEN( SELECT DNAME FROM MEMDEPENDENT M1,MEMFACILITYUSAGE M WHERE M1.ID = M.USERID AND NULL) END,M.USERID,FM.TYPE_,M.FACMANGREF FROM CUSTOMERS C, MEMFACILITYUSAGE M JOIN FACILITY F ON  M.FACILITYTYPE=F.ID JOIN PERIODICITY P ON P.ID=F.RPERIODICITY LEFT OUTER JOIN FACILITYMANAGER FM ON M.ID=FM.MFUID AND FM.APPROVEDBY IS NULL WHERE C.SEARCHKEY=? AND M.MEMNO=C.ID AND M.STATUS_ !=2 AND M.ACTIVE=TRUE AND F.RENEWALAMT>0", new Object[]{uInfo.getSearchkey()}, new ActiveFelicitiesRowMapper());
		 
		 if(activefacility!=null)
		 {
			 HashMap<String, String> map1 = new HashMap<String, String>();
		        for (ActiveFacility af : activefacility) {
		            map1.put(af.getMfuId(), af.getFacilityName());
		        }
		     session.put("map1", map1);
			 session.put("activefacility", activefacility);
			 return Constants.SUCCESS;
		 }
		 else
		 {
			 addActionError("Sorry Unable to Process your request. Please Try again later..");
				return Constants.FAILURE;
		 }
		
		}
		catch(Exception e)
		{
			
			addActionError("Sorry Unable to Process your request. Please Try again later..");
			return Constants.FAILURE;
		}
		
		
		
	}

	public Map getSession() {
		return session;
	}


	public void setSession(Map session) {
		this.session = session;
	}
}

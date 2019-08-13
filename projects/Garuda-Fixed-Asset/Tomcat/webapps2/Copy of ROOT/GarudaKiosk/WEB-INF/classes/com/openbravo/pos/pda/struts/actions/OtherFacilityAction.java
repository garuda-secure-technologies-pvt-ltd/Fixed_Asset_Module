package com.openbravo.pos.pda.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.OtherFacility;
import com.openbravo.pos.pda.struts.beans.OtherFacility.OtherFacilityRowMapper;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class OtherFacilityAction extends ActionSupport implements SessionAware {
	private Map session;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
	
	
	public OtherFacilityAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}

	@Override
	public String execute() throws Exception {
		
		uInfo = (UserInfo) session.get("user");
		ArrayList<OtherFacility> otherfacility = new ArrayList<OtherFacility>();
		otherfacility = (ArrayList<OtherFacility>) jdbcTemplate.query("SELECT F.ID,F.NAME,F.JOINAMT,F.RENEWALAMT,F.NAME FROM FACILITY F WHERE  F.ACTIVE =TRUE AND F.TYPE_='Optional'  AND (F.ID NOT IN (SELECT M.FACILITYTYPE FROM MEMFACILITYUSAGE M,CUSTOMERS C WHERE C.SEARCHKEY=? AND C.ID=M.MEMNO AND  M.ACTIVE=TRUE) OR F.APPTO=1) AND F.RENEWALAMT>0  ORDER BY F.NAME", new Object[]{uInfo.getSearchkey()}, new OtherFacilityRowMapper());
		
		session.put("otherfacility", otherfacility );
		
		HashMap<String, String> map = new HashMap<String, String>();

        for (OtherFacility ofac : otherfacility) {
            map.put(ofac.getFId(), ofac.getFacilityName());
        }


        session.put("map", map);

		return Constants.SUCCESS;
	}
	
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
}

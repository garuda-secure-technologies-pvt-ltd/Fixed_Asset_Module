package com.openbravo.pos.pda.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.Advertisement;
import com.openbravo.pos.pda.struts.beans.BalanceInfo;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.PastEvents;
import com.openbravo.pos.pda.struts.beans.TransactionInfo;
import com.openbravo.pos.pda.struts.beans.TransactionInfo.TransactionInfoRowMapper;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class TransactionAction extends ActionSupport implements SessionAware {
	

	private List<UpcomingEvents> upcom;
    private List<PastEvents> events;
    private List<Advertisement> advt;
    private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplate2;
    private Map session;
   
    public TransactionAction() {
    	JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}
    
    @Override
    public String execute() throws Exception {
    	cInfo = (CompanyInfo) session.get("home");
		setUpcom(cInfo.getUpcmngEvts());
		setEvents(cInfo.getPastEvt());
		setAdvt(cInfo.getAdvt());
		
		UserInfo uinfo = (UserInfo) session.get("user");
		List<TransactionInfo> tinfo = new ArrayList<TransactionInfo>();
		try
		{
		tinfo = jdbcTemplate.query("SELECT AJ.DATE,AJ.TRANSNO,AJ.TRANSTYPE,AJ.AMOUNT,F.SMSFORM,AJ.TRANSREF FROM ACCOUNTJOURNAL AJ LEFT JOIN FACILITY F ON AJ.TRANSREF=F.ID WHERE AJ.ACCOUNTID=?  ORDER BY AJ.DATE DESC LIMIT 5", new Object[]{uinfo.getAccid()}, new TransactionInfoRowMapper());	
		//binfo.setBal(jdbcTemplate.queryForLong("SELECT SUM(A.CREDIT+A.CURCREDIT-A.DEBIT-A.CURDEBIT) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=?", new Object[]{uinfo.getAccid()}));
		}
		catch(Exception e)
		{
			addActionError(e.getMessage());
			return Constants.ERROR;
		}
		
		session.put("tinfo", tinfo);
		
    	return Constants.SUCCESS;
    }

	public List<UpcomingEvents> getUpcom() {
		return upcom;
	}

	public void setUpcom(List<UpcomingEvents> upcom) {
		this.upcom = upcom;
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

	public CompanyInfo getcInfo() {
		return cInfo;
	}

	public void setcInfo(CompanyInfo cInfo) {
		this.cInfo = cInfo;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate2() {
		return jdbcTemplate2;
	}

	public void setJdbcTemplate2(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate2 = jdbcTemplate2;
	}

	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}
    

}

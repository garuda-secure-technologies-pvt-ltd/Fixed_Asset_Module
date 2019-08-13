package com.openbravo.pos.pda.struts.actions;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.Advertisement;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.PasswordBean;
import com.openbravo.pos.pda.struts.beans.PastEvents;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo.UserInfoRowMapper;
import com.openbravo.pos.pda.util.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport implements SessionAware {

	private Map session;
	private PasswordBean bean;
    private JdbcTemplate jdbcTemplate;
    private String Validation = "";
    
    public ChangePassword() {
    	JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}
    
public void validate() {
		
		if(bean!=null)
		{
			if(!bean.getNpass().equals(bean.getCpass()))
			{
				Validation = Constants.LOGINFAILED;
			}
		}
		else
		{
			
			Validation = Constants.LOGINFAILED;
		}
		
	}
	
	@Override
	public String execute() throws Exception {
		UserInfo ui = (UserInfo) session.get("user");
		if(!Validation.equals(Constants.FAILURE))
		{
			String newPass = bean.getNpass();
			String confirmPass = bean.getCpass();
	        String oldPass = StringUtils.hashString(bean.getOpass());
	        
	        if(oldPass.equals(jdbcTemplate.queryForObject("SELECT PASSWORD FROM CUSTOMERS WHERE SEARCHKEY = ?", new Object[]{ui.getSearchkey()}, String.class)));
	        {
	        String encPass=StringUtils.hashString(newPass);
	       int count = jdbcTemplate.update("UPDATE CUSTOMERS SET PASSWORD=?,PASSREST=TRUE WHERE ID=?", new Object[]{encPass, ui.getMid()});
	        
	       if(count==1)
	       {
	    	   jdbcTemplate.update("INSERT INTO PASSWORDLOG(ID,CUSTID,PASSWORD,CRDATE,CREATEDBY) VALUES (?,?,?,?,?)", new Object[]{UUID.randomUUID().toString(), ui.getMid(), encPass, new Date(), ui.getName() });
	    	   addActionMessage("Your Password Changed Successfully.");
	    	   
	    	   ui.setPassword1(null);
	    	   session.remove("user");
	    	   session.put("user", ui);
	    	   
	    	   return Constants.SUCCESS;
	       }
	       }
	        
		}
		else
		{
			return Constants.LOGINFAILED;
		}
		 session.remove("user");
  	   addActionError("Unable to process your request. Please Try Agian Later");
  	   return Constants.FAILURE;
	}
    
    
    
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public PasswordBean getBean() {
		return bean;
	}
	public void setBean(PasswordBean bean) {
		this.bean = bean;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String getValidation() {
		return Validation;
	}
	public void setValidation(String validation) {
		Validation = validation;
	}
    
    
    
    
	
}

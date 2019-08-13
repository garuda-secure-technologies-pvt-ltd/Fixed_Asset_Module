package com.openbravo.pos.pda.struts.actions;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.opensymphony.xwork2.ActionSupport;

public class NewsLetterAction extends ActionSupport implements SessionAware {

	private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private Map session;
    private InputStream inputStream;
	
    public NewsLetterAction() {
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
    public String execute() throws Exception {
    	
    	Object o = jdbcTemplate.queryForObject("SELECT NEWSLETTER FROM CLUBDETAILSKIOSK WHERE ACTIVE = TRUE",String.class);
    	
    	if(o!=null && !o.equals(""))
    	{
    		String fileName = o.toString();
    		inputStream = new DataInputStream(
  			  new FileInputStream(fileName));
    	return Constants.SUCCESS;
    	}
    	else
    	{
    		return Constants.FAILURE;
    	}
    }
    public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}

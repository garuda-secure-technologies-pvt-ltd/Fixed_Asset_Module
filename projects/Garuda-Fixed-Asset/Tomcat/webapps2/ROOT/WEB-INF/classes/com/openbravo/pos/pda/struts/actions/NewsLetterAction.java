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
    	
    	Object o1 = jdbcTemplate.queryForObject("SELECT NEWSLETTERPATH FROM CLUBDETAILSKIOSK WHERE ACTIVE = TRUE",String.class);
    	Object o = jdbcTemplate.queryForObject("SELECT NEWSLETTER FROM CLUBDETAILSKIOSK WHERE ACTIVE = TRUE",String.class);
    	
    	if(o1!=null && !o1.equals("")&& o!=null && !o.equals("") )
    	{
    		try {
    			String path = o1.toString();
				String fileName = o.toString();
				inputStream = new DataInputStream(
				  new FileInputStream(path+fileName));
				  return Constants.SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return Constants.POPUPCLOSE;
			}
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

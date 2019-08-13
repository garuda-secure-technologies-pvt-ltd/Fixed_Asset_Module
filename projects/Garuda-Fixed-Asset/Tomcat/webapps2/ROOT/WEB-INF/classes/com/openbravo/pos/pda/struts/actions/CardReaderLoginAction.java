package com.openbravo.pos.pda.struts.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.CardReader;
import com.openbravo.pos.pda.dao.CardSwipeNotifier;
import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo.UserInfoRowMapper;
import com.openbravo.pos.pda.util.PropertyUtils;
import com.opensymphony.xwork2.ActionSupport;

public class CardReaderLoginAction extends ActionSupport implements SessionAware{
	private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private Map session;
    private UserInfo bean;
   
    public CardReaderLoginAction() {
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
    	
    	cInfo = (CompanyInfo) session.get("home");
    	
    	if( bean!=null && bean.getSearchkey()!=null)
        {
    		UserInfo user = null;
    				try
    				{
    				user = (UserInfo) jdbcTemplate.queryForObject("SELECT SEARCHKEY, PASSWORD,NAME,ACCOUNT,ID,JOINDATE,PASSREST FROM CUSTOMERS WHERE card = ? and VISIBLE = TRUE", new Object[]{bean.getSearchkey().toString()} ,new UserInfoRowMapper());
    				
    				if(user==null || user.getAccid()==null || user.getAccid().equals(""))
        			{
        				addActionError("You Have Swiped Invalid Card!!");
        				return Constants.FAILURE;
        			}
    				
    					session.put("user", user);
    					if(!user.isPassrest())
    					{
    						user.setPassword1(user.getPassword());
    						return Constants.RESTPASS;
    					}
        				
        			
        			return Constants.SUCCESS;
    				
    				}
    				catch(Exception e)
    				{
    					addActionError("Unable to process your card..!!");
    				}
        		}
        	
    	session.put("home", cInfo);
    	addActionMessage("Unable to process your request..!!!");
    	return Constants.FAILURE;
    }
    
   

	public void setSession(Map session) {
		this.session = session;
    }
    public Map getSession() {
		return session;
	}

	public UserInfo getBean() {
		return bean;
	}

	public void setBean(UserInfo bean) {
		this.bean = bean;
	}
	
}

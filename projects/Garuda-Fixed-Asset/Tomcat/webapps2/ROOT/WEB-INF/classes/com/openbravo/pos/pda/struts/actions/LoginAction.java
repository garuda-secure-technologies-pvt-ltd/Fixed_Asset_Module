package com.openbravo.pos.pda.struts.actions;

import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;
import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.Advertisement;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.PastEvents;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo.UserInfoRowMapper;
import com.openbravo.pos.pda.util.CryptUtils;
import com.openbravo.pos.pda.util.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private Map session;
	private UserInfo bean;
	private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private List<UpcomingEvents> upcom;
    private List<PastEvents> events;
    private List<Advertisement> advt;
    private String Validation = "";
    
	public LoginAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}
	
	public void validate() {
		
		if(bean!=null && bean.getSearchkey()!=null && !bean.getSearchkey().equals(""))
		{
		int num = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CUSTOMERS WHERE SEARCHKEY = ?", new Object[]{bean.getSearchkey().toString()});
		if(num==1)
		{
			int q = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM CUSTOMERS WHERE SEARCHKEY = ? AND VISIBLE = TRUE", new Object[]{bean.getSearchkey().toString()});
			
			if(q!=1)
			{
				Validation = Constants.FAILURE;
				addFieldError("bean.searchkey", "Your Membership is Deactivated!!");
			}
		}
		else
		{
			Validation = Constants.FAILURE;
			addFieldError("bean.searchkey", "Membership number does not exist!!!");
		}
		}
		else
		{
			Validation = Constants.FAILURE;
		}
		
	}
	
	@Override
	public String execute() throws Exception {
		cInfo = (CompanyInfo) session.get("home");
		setUpcom(cInfo.getUpcmngEvts());
		setEvents(cInfo.getPastEvt());
		setAdvt(cInfo.getAdvt());
		
		if(!Validation.equals(Constants.FAILURE))
		{
			String pass = StringUtils.hashString(bean.getPassword());
			UserInfo user = null;
			String sqlStr = "SELECT SEARCHKEY, PASSWORD,NAME,ACCOUNT,ID,JOINDATE,PASSREST FROM CUSTOMERS WHERE UPPER(SEARCHKEY) = ? AND VISIBLE = TRUE AND PASSWORD ";
	        String end = "";
	        if (bean.getPassword().equals("")) {
	        	sqlStr =sqlStr +  "IS NULL";
	            List<UserInfo> liqa =	jdbcTemplate.query(sqlStr, new Object[]{bean.getSearchkey().toString()} ,new UserInfoRowMapper());
	            if(liqa.size()>0)
	            {
	            user = liqa.get(0);
	            }
	            else
	            {
            	addActionError("You Have Entered Wrong Password!!");
            	return Constants.FAILURE;
	            }
	            		
	            
	        } else {
	        	sqlStr =sqlStr + " = ?";
	            List<UserInfo> liqa = jdbcTemplate.query(sqlStr, new Object[]{bean.getSearchkey().toString(), pass} ,new UserInfoRowMapper());
	            if(liqa.size()>0)
	            {
	            user = liqa.get(0);
	            }
	            else
	            {
	            	addActionError("You Have Entered Wrong Password!!");
	            	return Constants.FAILURE;
	            }
	        }
			
		if(user!=null)
		{
			session.put("user", user);
			
			if(!user.isPassrest())
			{
				return Constants.RESTPASS;
			}
			
			
			return Constants.SUCCESS;
		}
		else
		{
			addActionError("You Have Entered Wrong Password!!");
			return Constants.FAILURE;
		}
		}
		
		return Constants.FAILURE;
	}
	
	
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public UserInfo getBean() {
		return bean;
	}
	public void setBean(UserInfo bean) {
		this.bean = bean;
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
	
	
}

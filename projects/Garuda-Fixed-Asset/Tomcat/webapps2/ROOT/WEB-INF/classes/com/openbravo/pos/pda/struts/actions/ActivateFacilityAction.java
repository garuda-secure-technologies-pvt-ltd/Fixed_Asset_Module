package com.openbravo.pos.pda.struts.actions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.Connection;
import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.ActivateInfo;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ActivateFacilityAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private Map session;
	private UserInfo bean;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
	private String[] mfuid;
	
	public ActivateFacilityAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
        
	}
	
	@Override
	public String execute() throws Exception {
		
		CompanyInfo cInfo = (CompanyInfo) session.get("home");
		
		if(cInfo.isActivationRequestApproval())
		{
		String[] fId = mfuid;
		String as = bean.getSearchkey();
		ActivateInfo atinfo = new ActivateInfo();
        String date = bean.getSearchkey();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date temp = new Date();
        try {
            temp = df.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Date now=new Date();
        if(now.compareTo(temp)>0)
        {
             addActionError("Please Enter Activation Date in dd/MM/yyyy format");
        return Constants.FAILURE;

        }
		uInfo = (UserInfo) session.get("user");
		atinfo = saveMethod(fId, temp, uInfo.getMid());
		session.put("atinfo", atinfo);
		return Constants.SUCCESS;
		}
		else
		{
			addActionError("Sorry Activation of Fecilities through KIOSK is Disabled. Please Contact in office..!!");
			return Constants.FAILURE;
		}
		
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

	public String[] getMfuid() {
		return mfuid;
	}

	public void setMfuid(String[] mfuid) {
		this.mfuid = mfuid;
	}
	
	public ActivateInfo saveMethod(String[] fId, Date date, String memid)
	{
		ActivateInfo atinfo = new ActivateInfo();
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;        
        PreparedStatement ps3 = null;      
        java.sql.Date date3 = new java.sql.Date(new Date().getTime());
        java.sql.Date date5 = new java.sql.Date(date.getTime());
        String[] fid = fId;
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> temp1 = new ArrayList<String>();  
        String[] suc = new String[20];
        String[] fai = new String[20];
        String sqlStr = "INSERT INTO FACILITYMANAGER(ID,MFUID,TYPE_,FROM_,INITIATEDBY,INITIATEDDATE) VALUES (?,?,?,?,?,?)";
        String sqlStr1 = "INSERT INTO MEMFACILITYUSAGE(ID,MEMNO,ACTIVE,FACILITYTYPE,STATUS_,SDATE,CREATEDBY,CRDATE,FACMANGREF) VALUES (?,?,?,?,?,?,?,?,?)";
        String sqlStr3 = "SELECT F.ID FROM MEMFACILITYUSAGE M,FACILITYMANAGER F WHERE M.MEMNO=? AND M.FACILITYTYPE=? AND M.ID=F.MFUID AND F.STATUS_ IS NULL";
        try {
            //get connection
            con = (Connection) jdbcTemplate.getDataSource().getConnection();
            for (int j = 0; j < fid.length; j++) {
                try {
                    ps3 = con.prepareStatement(sqlStr3);
                    ps3.setString(1, memid);
                    ps3.setString(2, fid[j]);
                    ResultSet r = ps3.executeQuery();
                    if (r.next()) {                        
                        temp1.add(fid[j]);
                    } else {
                        con.setAutoCommit(false);
                        String fmid = UUID.randomUUID().toString();
                        String mfuid = UUID.randomUUID().toString();
                        ps1 = con.prepareStatement(sqlStr1);
                        ps1.setString(1, mfuid);
                        ps1.setString(2, memid);
                        ps1.setBoolean(3, false);
                        ps1.setString(4, fid[j]);
                        ps1.setInt(5, 5);
                        ps1.setDate(6, date5);
                        ps1.setString(7, memid);
                        ps1.setDate(8, date3);
                        ps1.setString(9,fmid );
                        ps1.executeUpdate();
                        ps = con.prepareStatement(sqlStr);
                        ps.setString(1, fmid);
                        ps.setString(2, mfuid);
                        ps.setInt(3, 5);
                        ps.setDate(4, date5);
                        ps.setString(5, memid);
                        ps.setDate(6, date3);
                        ps.executeUpdate();                       
                        con.commit();
                        temp.add(fid[j]);                       
                    }
                } catch (SQLException sQLException) {
                    con.rollback();
                    sQLException.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // close the resources
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        temp.toArray(suc);
        temp1.toArray(fai);

        atinfo.setSucc(suc);
        atinfo.setFail(fai);
        
        return atinfo;
	}

	
}

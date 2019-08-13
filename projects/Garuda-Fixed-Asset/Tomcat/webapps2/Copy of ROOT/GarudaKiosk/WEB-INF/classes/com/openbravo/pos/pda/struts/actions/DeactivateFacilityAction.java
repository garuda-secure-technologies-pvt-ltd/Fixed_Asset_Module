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

import org.apache.commons.validator.Constant;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4ResultSet;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.DeactivateInfo;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class DeactivateFacilityAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private Map session;
	private UserInfo bean;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
	private String[] mfuid;
	
	public DeactivateFacilityAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
        
	}

	@Override
	
	public String execute() throws Exception {
		CompanyInfo cInfo = (CompanyInfo) session.get("home");
		if(cInfo.isDeActivationRequestApproval())
		{
		DeactivateInfo dinfo = new DeactivateInfo();
		String date = bean.getSearchkey();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date temp = new Date();        
        try {
            temp = (Date) df.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            addActionError("Please Enter date in dd/mm/yyyy format!!!");
            return Constants.FAILURE;

        }
        Date now=new Date();
        
        if(now.compareTo(temp)>0)
        {
             addActionError("Date Cannot be lesser than today. Please select date after today..!!");
             return Constants.FAILURE;
        }
        uInfo = (UserInfo) session.get("user");
        
        Connection con = (Connection) jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        
        
        String[] i = new String[20];
        String[] l = new String[20];
        java.sql.Date date3 = new java.sql.Date(new Date().getTime());
        java.sql.Date date5 = new java.sql.Date(temp.getTime());
        ArrayList<String> temp1 = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        String[] mfuid = getMfuid();
        String sqlStr1 = "SELECT * FROM FACILITYMANAGER WHERE MFUID=? AND STATUS_ IS NULL";
        String sqlStr =  "INSERT INTO FACILITYMANAGER(ID,MFUID,TYPE_,FROM_,INITIATEDBY,INITIATEDDATE,MESSAGE) VALUES (?,?,?,?,?,?,?)";
        String sqlStr2 = "UPDATE MEMFACILITYUSAGE SET STATUS_=?,FACMANGREF=? WHERE ID=?";
        int j = 0;
        try {
        	for (j = 0; j < mfuid.length; j++) {
                ps1 = con.prepareStatement(sqlStr1);
                ps1.setString(1, mfuid[j]);
                ResultSet k = ps1.executeQuery();
                if (k.next()) {
                    temp1.add(mfuid[j]);
                } else {
                    try {
                        con.setAutoCommit(false);
                        ps = con.prepareStatement(sqlStr);
                        ps2 = con.prepareStatement(sqlStr2);
                        String manid = UUID.randomUUID().toString();
                        ps.setString(1, manid);
                        ps.setString(2, mfuid[j]);
                        ps.setInt(3, 3);
                        ps.setDate(4, date5);
                        ps.setString(5, uInfo.getMid());
                        ps.setDate(6, date3);
                        ps.setString(7, "stop dis facility from" + date5);
                        ps2.setInt(1, 3);
                        ps2.setString(2, manid);
                        ps2.setString(3, mfuid[j]);
                        ps.executeUpdate();
                        ps2.executeUpdate();
                        con.commit();
                        temp2.add(mfuid[j]);
                    } catch (SQLException sq1) {
                        con.rollback();
                        addActionError(sq1.getMessage());
                        return Constants.ERROR;
                       
                    }
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
        temp1.toArray(i);
        temp2.toArray(l);
        dinfo.setMsg(i);
        dinfo.setSucc(l);	
        	
        session.put("deinfo", dinfo);
		return Constants.SUCCESS;
		}
		else
		{
			addActionError("Sorry Deactivation of Fecilities through KIOSK is Disabled. Please Contact in office..!!");
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
	
	
	
}

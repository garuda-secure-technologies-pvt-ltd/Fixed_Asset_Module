package com.openbravo.pos.pda.struts.actions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.Connection;
import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class CancelRequestAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private Map session;
	private UserInfo bean;
	private UserInfo uInfo;
    private JdbcTemplate jdbcTemplate;
    private String id;
	
	public CancelRequestAction() {
		JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
        
	}

	@Override
	public String execute() throws Exception {
		
		String mfuid = getId(); 
		
		int count=0;
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        int status = 0;
        String sqlStr = "DELETE FROM FACILITYMANAGER WHERE MFUID=? AND STATUS_ IS NULL";
        String sqlStr1 = "UPDATE MEMFACILITYUSAGE SET STATUS_=? WHERE ID=? ";
        String sqlStr2 = "DELETE FROM MEMFACILITYUSAGE WHERE ID=?";
        String sqlStr3 = "SELECT STATUS_ FROM MEMFACILITYUSAGE WHERE ID=?";
        
        try {
            con = (Connection) jdbcTemplate.getDataSource().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, mfuid);
            ps.executeUpdate();
            ps3 = con.prepareStatement(sqlStr3);
            ps3.setString(1, mfuid);
            rs = ps3.executeQuery();
            if (rs.next()) {
                status = rs.getInt(1);
            }
            if (status == 3) {
                ps1 = con.prepareStatement(sqlStr1);
                ps1.setInt(1, 0);
                ps1.setString(2, mfuid);
                ps1.executeUpdate();
            } else {
                ps2 = con.prepareStatement(sqlStr2);
                ps2.setString(1, mfuid);
                ps2.executeUpdate();
            }
            con.commit();
            count=1;
        } catch (Exception ex) {
            try {
            	
                con.rollback();
                addActionError("Unable to process your request to send Deactivation Request. Please try afain later or contact your Administrator..!!");
                return Constants.FAILURE;
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            {
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
           
        }
		
        if(count==1)
		return Constants.SUCCESS;
        else
        	return Constants.FAILURE;
	}
	
	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

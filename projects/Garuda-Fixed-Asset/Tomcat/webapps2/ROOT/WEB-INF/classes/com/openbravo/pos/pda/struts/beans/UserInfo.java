package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserInfo implements Serializable{

    private String searchkey;
    private String password;
    private String password1;
    private String name;
    private String accid;
    private String mid;
    private Date joindate;
    private boolean passrest;

    public boolean isPassrest() {
        return passrest;
    }
    public void setPassrest(boolean passrest) {
        this.passrest = passrest;
    }
    public Date getJoindate() {
        return joindate;
    }
    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
    
    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public static final class UserInfoRowMapper implements RowMapper<UserInfo>
    {

		public UserInfo mapRow(ResultSet rs, int arg1) throws SQLException {
			
			UserInfo user = new UserInfo();
			user.setSearchkey(rs.getString(1));
            if (rs.getString(2) == null) {
                user.setPassword("");
            } else {
                user.setPassword(rs.getString(2));
            }
            user.setName(rs.getString(3));
            user.setAccid(rs.getString(4));
            user.setMid(rs.getString(5));
            user.setJoindate(rs.getDate(6));
            user.setPassrest(rs.getBoolean(7));
			return user;
		}
    	
    }
}

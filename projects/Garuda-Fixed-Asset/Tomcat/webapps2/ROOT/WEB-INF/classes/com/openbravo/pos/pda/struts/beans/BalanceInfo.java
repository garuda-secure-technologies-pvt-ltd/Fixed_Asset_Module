package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BalanceInfo implements Serializable {
    private String accid;
    private String searchKey;
    private String name;
    private double bal;

   

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static final class BalanceInfoRowMapper implements RowMapper<BalanceInfo>
    {

		public BalanceInfo mapRow(ResultSet rs, int arg1) throws SQLException {
    	BalanceInfo binfo = null;
        if (rs.next()) {
            binfo = new BalanceInfo();

            binfo.setBal(Double.parseDouble(rs.getString(1)));
        		}
        return binfo;
		}
    }
    
}

package com.openbravo.pos.pda.struts.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OtherFacility {
	
	private String facilityName;
    private String fId;
    private String fNmae;
    private double joiningAmt;
     private double renewalAmt;
     private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    public String getFNmae() {
        return fNmae;
    }

    public void setFNmae(String fNmae) {
        this.fNmae = fNmae;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getFId() {
        return fId;
    }

    public void setFId(String fId) {
        this.fId = fId;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public double getJoiningAmt() {
        return joiningAmt;
    }

    public void setJoiningAmt(double joiningAmt) {
        this.joiningAmt = joiningAmt;
    }

    public double getRenewalAmt() {
        return renewalAmt;
    }

    public void setRenewalAmt(double renewalAmt) {
        this.renewalAmt = renewalAmt;
    }
    
    public static final class OtherFacilityRowMapper implements RowMapper<OtherFacility>
    {

		public OtherFacility mapRow(ResultSet rs, int arg1)
				throws SQLException {
			OtherFacility othfac = null;

	        othfac = new OtherFacility();
	        othfac.setFId(rs.getString(1));

	        othfac.setFacilityName(rs.getString(2));
	        othfac.setJoiningAmt(rs.getDouble(3));

	        othfac.setRenewalAmt(rs.getDouble(4));
	        othfac.setFNmae(rs.getString(5));
	        othfac.setStatus("not requested");
			return othfac;
		}
    	
    }

}

package com.openbravo.pos.pda.struts.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ActiveFacility {
    private String memId;
    private String fId;
    private String mfuId;
    private String facilityName;
    private double renewalAmount;
    private String status;
    private String type;
    private String fName;
    private String dName;
    
    

    public String getDName() {
        return dName;
    }

    public void setDName(String dName) {
        this.dName = dName;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }


    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMfuId() {
        return mfuId;
    }

    public void setMfuId(String mfuId) {
        this.mfuId = mfuId;
    }

    public String getFId() {
        return fId;
    }

    public void setFId(String fId) {
        this.fId = fId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public double getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(double renewalAmount) {
        this.renewalAmount = renewalAmount;
    }


public static final class ActiveFelicitiesRowMapper implements RowMapper
{

	
			
			
	public Object mapRow(ResultSet rs, int arg1) throws SQLException {
		ActiveFacility actfac = null;
        actfac = new ActiveFacility();
        actfac.setMfuId(rs.getString(1));
        actfac.setFacilityName(rs.getString(2));
        actfac.setRenewalAmount(rs.getDouble(3));
        actfac.setType(rs.getString(5));
        actfac.setFName(rs.getString(6));
        if (rs.getString(7) == null) {
            actfac.setDName("Member");
        } else {
            actfac.setDName(rs.getString(7));
        }
        switch (rs.getInt(4)) {
            case 0:
                actfac.setStatus("In Usage");
                break;
            case 1:
                actfac.setStatus("Suspended");
                break;
            case 2:
                actfac.setStatus("Deactivated");
                break;
            case 3:
                actfac.setStatus("Stop Req. under process");
                break;
            case 4:
                actfac.setStatus("Suspension Req. under process");
                break;
            case 5:
                actfac.setStatus("Start Req. under process");
                break;
            /*default:
            actfac.setStatus("In Usage");*/
        }
        return actfac;
    }
	}
	
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.pda.struts.beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author swathi
 */
public class CompanyInfo implements Serializable {
    
    private String name;
    private String id;
    private String desc;
    private Date date;
    private String path;
    private String url;
    private String address;
    private Date todaydate;
    private Boolean flag;
    private String logoPath;
    private String webSite;
    private List<Advertisement> advt;
    private List<UpcomingEvents> upcmngEvts;
    private List<PastEvents> pastEvt;
    private boolean ActivationRequestApproval;
    private boolean DeActivationRequestApproval;
    
	public String getLogoPath() {
        return logoPath;
    }
    
    public List<Advertisement> getAdvt() {
		return advt;
	}

	public void setAdvt(List<Advertisement> advt) {
		this.advt = advt;
	}

	public List<UpcomingEvents> getUpcmngEvts() {
		return upcmngEvts;
	}

	public void setUpcmngEvts(List<UpcomingEvents> upcmngEvts) {
		this.upcmngEvts = upcmngEvts;
	}

	public List<PastEvents> getPastEvt() {
		return pastEvt;
	}

	public void setPastEvt(List<PastEvents> pastEvt) {
		this.pastEvt = pastEvt;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(Date todaydate) {
        this.todaydate = todaydate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    public boolean isActivationRequestApproval() {
		return ActivationRequestApproval;
	}

	public void setActivationRequestApproval(boolean activationRequestApproval) {
		ActivationRequestApproval = activationRequestApproval;
	}

	public boolean isDeActivationRequestApproval() {
		return DeActivationRequestApproval;
	}

	public void setDeActivationRequestApproval(boolean deActivationRequestApproval) {
		DeActivationRequestApproval = deActivationRequestApproval;
	}

	public static final class CompanyInfoMapper implements RowMapper<CompanyInfo>
    {
       
        public CompanyInfo mapRow(ResultSet rs, int i) throws SQLException {
            CompanyInfo ci = new CompanyInfo();
            ci.setAddress(rs.getString("ADDRESS"));
            ci.setId(rs.getString("ID"));
            ci.setDesc(rs.getString("CLUBNAME"));
            ci.setWebSite(rs.getString("WEBSITE"));
            ci.setLogoPath(rs.getString("CLUBLOGO"));
            ci.setActivationRequestApproval(rs.getBoolean("ACTIVATEFECILITYREQ"));
            ci.setDeActivationRequestApproval(rs.getBoolean("DEACTIVATEFECILITYREQ"));
            return ci;
            
        }
        
    }
    
    
}

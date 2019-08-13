package com.openbravo.pos.pda.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.jdbc.core.JdbcTemplate;

import com.openbravo.pos.pda.dao.JdbcDaoImpl;
import com.openbravo.pos.pda.struts.Constants;
import com.openbravo.pos.pda.struts.MinUsageLogic;
import com.openbravo.pos.pda.struts.beans.Advertisement;
import com.openbravo.pos.pda.struts.beans.BillPeriods;
import com.openbravo.pos.pda.struts.beans.CompanyInfo;
import com.openbravo.pos.pda.struts.beans.MinimumUsageInfo;
import com.openbravo.pos.pda.struts.beans.MinimumUsageInfo.MinimumUsageInfoRowMapper;
import com.openbravo.pos.pda.struts.beans.PastEvents;
import com.openbravo.pos.pda.struts.beans.UpcomingEvents;
import com.openbravo.pos.pda.struts.beans.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class MinUsageAction extends ActionSupport implements SessionAware
{
    private CompanyInfo cInfo = null;
    private JdbcTemplate jdbcTemplate;
    private UserInfo uInfo;
    private Map session;
   
    public MinUsageAction() {
    	JdbcDaoImpl jdbcImp = new JdbcDaoImpl();
        jdbcTemplate = jdbcImp.getJdbcTemplate();
	}

    @Override
    public String execute() throws Exception {
    	 uInfo = (UserInfo) session.get("user");
    	 BillPeriods bp = null;
         double usage, limit;
         String msg="";
         List<MinimumUsageInfo> muinfos = new ArrayList<MinimumUsageInfo>();
         List<MinimumUsageInfo> muinfosTemp = new ArrayList<MinimumUsageInfo>();
         
         muinfos = (ArrayList<MinimumUsageInfo>) jdbcTemplate.query("SELECT M.NAME,M.AMOUNT AS ALIMIT ,MU.LBILLDATE,P.TYPE_ AS PERIODTYPE,P.NO,P.DATE,P.ACCURATE,P.DOJ,P.FMONTH  FROM MINUSAGE M,MEMMINUSAGE MU,PERIODICITY P WHERE M.ACHEAD!='NULL' AND M.ID=MU.USAGETYPE AND P.ID=M.PERIOD AND MU.MEMNO=?", new Object []{uInfo.getMid()}, new MinimumUsageInfoRowMapper());
         muinfosTemp = muinfos;
         MinUsageLogic mulogic = new MinUsageLogic();
         /*  for (MinimumUsageInfo muinfo : muinfos) {
         usage = 0.0;
         if (uinfo.getJoindate() != null || muinfo.getLBilldate() != null) {
         bp = mulogic.findBillPeriods(uinfo.getJoindate(), muinfo);
         usage = m.findUsage(bp, uinfo.getMid());

         limit = muinfo.getLimit();
         muinfo.setLimit(limit);
         muinfo.setUsage(usage);
         }
         }*/
         for (int i = 0; i < muinfos.size(); i++) {
             MinimumUsageInfo muinfo = muinfos.get(i);
             usage = 0.0;
             if (uInfo.getJoindate() != null || muinfo.getLBilldate() != null) {
                 bp = mulogic.findBillPeriods(uInfo.getJoindate(), muinfo);
                 usage = jdbcTemplate.queryForLong("SELECT SUM(AMT) FROM (SELECT SUM(AMOUNT) AS AMT FROM BILL WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=? UNION ALL SELECT SUM(AMOUNT) AS AMT FROM BILL_ARV WHERE CUSTOMER=? AND CREATEDDATE>=? AND CREATEDDATE<=?) as amt", new Object[]{uInfo.getMid(), bp.getStartDate(), bp.getEndDate(), uInfo.getMid(), bp.getStartDate(), bp.getEndDate()});

                 limit = muinfo.getLimit();
                 muinfo.setLimit(limit);
                 muinfo.setUsage(usage);
             }
             else
             {
                 String mname=muinfo.getMname();
                 muinfosTemp.remove(i);
                 msg=msg+mname+" ";
             }
         }
         if(!"".equals(msg))
         {
              msg="Unable to process your request for "+msg+" minusages";
         }
         session.put("msg",msg);
         session.put("muinfo", muinfosTemp);
         return Constants.SUCCESS;
     }
    
    
	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

    
}

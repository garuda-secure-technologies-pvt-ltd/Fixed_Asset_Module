package com.openbravo.pos.pda.struts;

import java.util.Calendar;
import java.util.Date;

import com.openbravo.pos.pda.struts.beans.BillPeriods;
import com.openbravo.pos.pda.struts.beans.MinimumUsageInfo;

public class MinUsageLogic {
	
	 public BillPeriods findBillPeriods(Date joinDate, MinimumUsageInfo muinfo) {
	        // List<BillPeriods> bperiods = new ArrayList<BillPeriods>();
	        BillPeriods bp = new BillPeriods();
	        Date d = new Date();

	        Date startdate = calculateStartDate(muinfo, joinDate);
	        Date enddate = startdate;
	        do {
	            //bp = new BillPeriods();
	            Calendar cal1 = Calendar.getInstance();
	            cal1.setTimeInMillis(enddate.getTime());
	            cal1.add(Calendar.DATE, +1);
	            enddate.setTime(cal1.getTimeInMillis());
	            bp.setStartDate(enddate);
	            enddate = addOnePeriod(enddate, muinfo);
	            bp.setEndDate(enddate);
	        //bperiods.add(bp);
	        } while (d.compareTo(enddate) >= 0);

	        return bp;
	    }

	    
	    public Date addOnePeriod(Date d, MinimumUsageInfo muinfo) {
	        Date edate = new Date();

	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTimeInMillis(d.getTime());
	        if (muinfo.isPdoj() == true) {
	            if (muinfo.getPtype().equals("Days")) {
	                cal1.add(Calendar.DATE, muinfo.getPno());

	            } else if (muinfo.getPtype().equals("Months")) {
	                cal1.add(Calendar.MONTH, muinfo.getPno());

	            } else if (muinfo.getPtype().equals("Quaterly")) {
	                cal1.add(Calendar.MONTH, 3);

	            } else if (muinfo.getPtype().equals("Years")) {
	                cal1.add(Calendar.YEAR, muinfo.getPno());

	            }
	        } else {
	            if (muinfo.getPtype().equals("Days")) {
	                cal1.add(Calendar.DATE, muinfo.getPno());

	            } else if (muinfo.getPtype().equals("Months")) {
	                cal1.set(Calendar.DATE, Integer.parseInt(muinfo.getPdate()));
	                cal1.add(Calendar.MONTH, muinfo.getPno());

	            } else if (muinfo.getPtype().equals("Quaterly")) {
	                cal1.set(Calendar.DATE, Integer.parseInt(muinfo.getPdate()));
	                cal1.add(Calendar.MONTH, 3);

	            } else if (muinfo.getPtype().equals("Years")) {
	                cal1.set(Calendar.DATE, Integer.parseInt(muinfo.getPdate()));
	                cal1.set(Calendar.MONTH, muinfo.getPfmonth());
	                cal1.add(Calendar.YEAR, muinfo.getPno());

	            }
	        }
	        cal1.add(Calendar.DATE, -1);
	        edate.setTime(cal1.getTimeInMillis());

	        return edate;

	    }

	    public Date calculateStartDate(MinimumUsageInfo muinfo, Date joinDate) {
	        Date d = new Date();
	        Date temp1 = null;
	        Date sdate = new Date();
	        if (muinfo.getLBilldate() == null) {
	            if (muinfo.isPaccurate() == true || muinfo.isPdoj() == true) {
	                temp1 = joinDate;
	                if (temp1.compareTo(d) < 0) {
	                    Calendar cal1 = Calendar.getInstance();
	                    cal1.setTimeInMillis(temp1.getTime());
	                    cal1.set(Calendar.HOUR_OF_DAY, 00);
	                    cal1.set(Calendar.MINUTE, 00);
	                    cal1.set(Calendar.SECOND, 00);
	                    cal1.set(Calendar.MILLISECOND, 00);
	                    sdate.setTime(cal1.getTimeInMillis());
	                    return sdate;
	                }

	            } else {
	                temp1 = joinDate;
	                if (temp1.compareTo(d) < 0) {
	                    Calendar cal1 = Calendar.getInstance();
	                    cal1.setTimeInMillis(temp1.getTime());
	                    cal1.set(Calendar.HOUR_OF_DAY, 00);
	                    cal1.set(Calendar.MINUTE, 00);
	                    cal1.set(Calendar.SECOND, 00);
	                    cal1.set(Calendar.MILLISECOND, 00);
	                    cal1.set(Calendar.DATE, Integer.parseInt(muinfo.getPdate()));
	                    sdate.setTime(cal1.getTimeInMillis());
	                    return sdate;
	                }
	            }
	        } else {
	            temp1 = muinfo.getLBilldate();


	            if (temp1.compareTo(d) < 0) {
	                Calendar cal1 = Calendar.getInstance();
	                cal1.setTimeInMillis(temp1.getTime());
	                cal1.set(Calendar.HOUR_OF_DAY, 00);
	                cal1.set(Calendar.MINUTE, 00);
	                cal1.set(Calendar.SECOND, 00);
	                cal1.set(Calendar.MILLISECOND, 00);


	                sdate.setTime(cal1.getTimeInMillis());
	                return sdate;
	            }
	        }

	        return sdate;

	    }

}

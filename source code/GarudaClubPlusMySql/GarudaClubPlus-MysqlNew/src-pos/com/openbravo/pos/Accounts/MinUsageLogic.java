package com.openbravo.pos.Accounts;

import com.openbravo.pos.clubmang.Periodicity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MinUsageLogic {

    public List<BillPeriods> findBillPeriods(Periodicity p, Date lbilldate) {
        List<BillPeriods> bperiods = new ArrayList<BillPeriods>();
        BillPeriods bp = null;
        Date d = new Date();
        Date startdate = new Date(lbilldate.getTime());
        Date enddate = startdate;
        System.out.println(enddate.toString());

        while (d.compareTo(enddate) >= 0) {
            bp = new BillPeriods();
            Calendar cal1 = Calendar.getInstance();
            cal1.setTimeInMillis(enddate.getTime());
            cal1.set(Calendar.HOUR_OF_DAY, 00);
            cal1.set(Calendar.MINUTE, 00);
            cal1.set(Calendar.SECOND, 00);
            cal1.set(Calendar.MILLISECOND, 00);
            cal1.add(Calendar.DATE,+1);
            enddate.setTime(cal1.getTimeInMillis());
            bp.setStartDate(enddate);
            enddate = addOnePeriod(enddate, p);
            bp.setEndDate(enddate);
            enddate=new Date(enddate.getTime());
            bperiods.add(bp);
            System.out.println(bperiods.toString());
        }
        bperiods.remove(bp);
        return bperiods;
    }

    public Date addOnePeriod(Date d, Periodicity p1) {
        Date edate = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(d.getTime());

        if (p1.gettype().equals("Days")) {
            cal1.add(Calendar.DATE, p1.getno());
        } else if (p1.gettype().equals("Months")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.add(Calendar.MONTH, p1.getno());
        } else if (p1.gettype().equals("Quaterly")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.add(Calendar.MONTH, 3);
        } else if (p1.gettype().equals("Years")) {
            cal1.set(Calendar.DATE, Integer.parseInt(p1.getdate()));
            cal1.set(Calendar.MONTH, p1.getmonth());
            cal1.add(Calendar.YEAR, p1.getno());
        }
        cal1.add(Calendar.DATE, -1);
        cal1.set(Calendar.HOUR_OF_DAY, 23);
        cal1.set(Calendar.MINUTE, 59);
        cal1.set(Calendar.SECOND, 59);
        edate.setTime(cal1.getTimeInMillis());
        return edate;
    }
}
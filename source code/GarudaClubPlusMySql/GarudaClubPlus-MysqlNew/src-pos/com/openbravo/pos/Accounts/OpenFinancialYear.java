/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 *
 * @author swathi
 */
public class OpenFinancialYear {    

    public static boolean open(AppView app) throws BasicException, SQLException {
        //praveen:added to calculate facility limit
           //caluculateFacilityLimit(app.getSession());
        return open(app.getSession());
    }

    public static boolean open(Session session) throws BasicException, SQLException {
        LookupUtilityImpl.getInstance(null).reloadGeneralSettingsMap();
        Map<String, GeneralSettingInfo> settings = LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
        Date sdate = (Date) Formats.DATE.parseValue(settings.get("Datestart").getValue());
        Date edate = (Date) Formats.DATE.parseValue(settings.get("Dateend").getValue());
        Calendar ocal = Calendar.getInstance();//copy financial years start date
        ocal.setTimeInMillis(sdate.getTime());
        Calendar ocal1 = Calendar.getInstance();//financial years end date
        ocal1.setTimeInMillis(edate.getTime());
        Calendar cal = Calendar.getInstance();//financial years start date
        cal.setTimeInMillis(sdate.getTime());
        Calendar fecal1 = Calendar.getInstance();//copy financial years end date -1
        fecal1.setTimeInMillis(edate.getTime());
        fecal1.add(Calendar.DATE, -1);
        Calendar cal1 = Calendar.getInstance();//copy financial years start date
        cal1.setTimeInMillis(cal.getTimeInMillis());
        //if (ocal1.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)) {
          //  cal1.set(Calendar.DATE, ocal1.get(Calendar.DATE));
          //  cal1.add(Calendar.DATE, 1);
       // } else {
            cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            cal1.add(Calendar.DATE, 1);
       // }
        sdate.setTime(cal.getTimeInMillis());
        edate.setTime(cal1.getTimeInMillis());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yy_HH_mm_ss");
        String ajback = "accountjournal_backup_"+sdf.format(new Date(System.currentTimeMillis()));
        String ajPeriodback = "ajperiodtotals_backup_"+sdf.format(new Date(System.currentTimeMillis()));
        
        
        
        Object[] countobj = (Object[]) new PreparedSentence(session, "SELECT COUNT(*) FROM ACCOUNTJOURNAL ", null, new SerializerReadBasic(new Datas[]{Datas.INT})).find();
        int count = 0;
        if (countobj != null && countobj[0] != null) {
            count = Integer.parseInt(countobj[0].toString());
        }
        if (count > 0) {
            new PreparedSentence(session, "CREATE TABLE "+ajback+" like accountjournal").exec();
            new PreparedSentence(session, "INSERT INTO "+ajback+" SELECT * FROM accountjournal").exec();
            new PreparedSentence(session, "CREATE TABLE "+ajPeriodback+" like ajperiodtotals").exec();
            new PreparedSentence(session, "INSERT INTO "+ajPeriodback+" SELECT * FROM ajperiodtotals").exec();
            Date sdatetemp = new Date();
            Date edatetemp = new Date();
            Object[] obj = (Object[]) new PreparedSentence(session, "SELECT MAX(DATE),MIN(DATE) FROM ACCOUNTJOURNAL", null, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP})).find();
            if (obj != null) {
                if (obj[0] != null) {
                    edatetemp = (Date) Formats.DATE.parseValue(Formats.DATE.formatValue(obj[0]));
                }
                if (obj[1] != null) {
                    sdatetemp = (Date) Formats.DATE.parseValue(Formats.DATE.formatValue(obj[1]));
                }
            }
            Calendar caltemp1 = Calendar.getInstance();
            Calendar ocaltemp1 = Calendar.getInstance();
            Calendar caltemp = Calendar.getInstance();
            caltemp1.setTimeInMillis(sdatetemp.getTime());
            ocaltemp1.setTimeInMillis(edatetemp.getTime());
            caltemp.setTimeInMillis(sdatetemp.getTime());
           // if (ocaltemp1.get(Calendar.MONTH) == caltemp1.get(Calendar.MONTH)) {
             //   caltemp1.set(Calendar.DATE, ocaltemp1.get(Calendar.DATE));
             //   caltemp1.add(Calendar.DATE, 1);
            //} else {
                caltemp1.set(Calendar.DATE, caltemp.getActualMaximum(Calendar.DATE));
                caltemp1.add(Calendar.DATE, 1);
            //}
            sdatetemp.setTime(caltemp.getTimeInMillis());
            edatetemp.setTime(caltemp1.getTimeInMillis());
            CloseFinancialYear.TransferAndUpdate(session, caltemp, caltemp1, ocaltemp1, sdatetemp, edatetemp);
        }

        List<String> list = new ArrayList<String>();
        ResultSet rs = session.getConnection().getMetaData().getTables(null, null, "AJ%", null);
        String tableName = null;
        int minYear = 9999;
        int currYear = 0;
        while (rs.next()) {
            tableName = rs.getString("TABLE_NAME").toUpperCase();
            try {
                //retrive the minimum year
                currYear = Integer.parseInt(tableName.substring(tableName.length() - 4, tableName.length()));
                if (currYear < minYear) {
                    minYear = currYear;
                }
            } catch (NumberFormatException exp) {
            }
            list.add(tableName);
        }

        Calendar minCal = Calendar.getInstance();
        minCal.setTime(new Date());
        minCal.set(Calendar.DATE, 31);
        minCal.set(Calendar.MONTH, Calendar.DECEMBER);
        minCal.set(Calendar.YEAR, minYear - 1);

        Calendar fyStartCal = Calendar.getInstance();
        fyStartCal.setTimeInMillis(cal.getTimeInMillis());
        fyStartCal.set(Calendar.DATE, 1);
        while (fyStartCal.after(minCal)) {
            fyStartCal.add(Calendar.MONTH, -1);
            String name1 = "AJ_" + fyStartCal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK).toUpperCase() + fyStartCal.get(Calendar.YEAR);
            if (list.contains(name1.toUpperCase())) {
                try {

                    new PreparedSentence(session, "INSERT INTO AJUNADJUSTED SELECT * FROM " + name1 + " WHERE BALANCEAMOUNT > 0 AND ADJUSTED = FALSE AND MEMID IS NOT NULL").exec();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        while (rs.next()) {
            list.add(rs.getString("TABLE_NAME"));
        }
        while (!cal.after(ocal1)) {
            String name = "AJ_" + cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.UK).toUpperCase() + cal.get(Calendar.YEAR);
            if (list.contains(name)) {
                try {
                    new PreparedSentence(session, "INSERT INTO ACCOUNTJOURNAL SELECT * FROM " + name + " WHERE DATE < ?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec(new Object[]{edate});

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cal.setTimeInMillis(edate.getTime());
             sdate.setTime(cal.getTimeInMillis());
            cal1.setTime(cal.getTime());
           // if (ocal1.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)) {
              //  cal1.set(Calendar.DATE, ocal1.get(Calendar.DATE));
              //  cal1.add(Calendar.DATE, 1);
         //   } else {
                cal1.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                cal1.add(Calendar.DATE, 1);
          //  }
            edate.setTime(cal1.getTimeInMillis());
        }
        new PreparedSentence(session, "DELETE FROM TRAILBALANCE").exec();
//         try {
//
//            new PreparedSentence(session, "INSERT INTO TRAILBALANCE (ID,ACCOUNTID,OBDEBIT,OBCREDIT,CURDEBIT,CURCREDIT) " +
//                    " SELECT AM.ID,AM.ID,(SELECT COALESCE (SUM(A.DEBIT),0.0) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=AM.ID AND A.EDATE < ? ),(SELECT COALESCE (SUM(A.CREDIT),0.0)  FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=AM.ID AND A.EDATE < ?)  " +
//                    ",(SELECT COALESCE(SUM(A.DEBIT),0.0) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=AM.ID AND A.EDATE > ? AND A.EDATE < ?) ,(SELECT COALESCE(SUM(A.CREDIT),0.0) FROM AJPERIODTOTALS A WHERE A.ACCOUNTID=AM.ID AND A.EDATE > ? AND A.EDATE < ?)  FROM ACCOUNTMASTER AM ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP})).exec(new Object[]{sdate, sdate, sdate, edate, sdate, edate});
//
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
       List<Object[]> tlist = new PreparedSentence(session, "SELECT AM.ID,AM.ID,COALESCE(SUM(A.DEBIT),0.0),COALESCE(SUM(A.CREDIT),0.0),COALESCE(SUM(A1.DEBIT),0.0),COALESCE(SUM(A1.CREDIT),0.0) FROM ACCOUNTMASTER AM LEFT JOIN AJPERIODTOTALS A ON A.ACCOUNTID=AM.ID AND A.EDATE <? LEFT JOIN AJPERIODTOTALS A1 ON A1.ACCOUNTID=AM.ID  AND A1.EDATE > ? AND A1.EDATE < ? GROUP BY AM.ID", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).list(new Object[]{sdate, sdate, edate});
        for (Object[] obj : tlist) {
            new PreparedSentence(session, "INSERT INTO TRAILBALANCE (ID,ACCOUNTID,OBDEBIT,OBCREDIT,CURDEBIT,CURCREDIT) VALUES (?,?,?,?,?,?) ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE})).exec(obj);
        }

        

        return true;
    }
//    public  static void caluculateFacilityLimit(Session s) throws BasicException{
//        new PreparedSentence(s, "DELETE FROM FACILITYLIMITMASTER").exec();
//        CaluculateLimit.calculateFacilityLimitToAll(s);
//    }
}

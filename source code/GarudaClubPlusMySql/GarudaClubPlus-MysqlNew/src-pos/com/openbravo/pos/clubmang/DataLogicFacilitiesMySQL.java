/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import java.util.Date;
import java.util.List;

/**
 *
 * @author swathi
 */
public class DataLogicFacilitiesMySQL extends DataLogicFacilities {
    @Override
    //praveen:added these statements
    public final List<FacilityBillingTableModel.Facilityline> getMembersToBeBilled(String type, Date dnow, int no, String facility) throws BasicException {
        if(type.equals("mm")){
          type="TIMESTAMPDIFF(MONTH,M.LBILLDATE,?)";
      }else if(type.equals("yy")){
          type="TIMESTAMPDIFF(YEAR,M.LBILLDATE,?)";
      }else if(type.equals("dd")){
          type="DATEDIFF(M.LBILLDATE,?)";
      }



        return new StaticSentence(s, "SELECT M.ID,C.SEARCHKEY,C.NAME,F.RENEWALAMT,M.SDATE,M.LBILLDATE,CASE WHEN M.LBILLDATE IS NULL THEN  " +type+ "  ELSE " +type+ " END AS NUMB ,F.DUEPERIOD,C.ACCOUNT,M.MEMNO,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=M.USERID)END,M.USERID,C.MOBILE " + "FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND C.VISIBLE=TRUE AND NUMB >= ? AND M.FACILITYTYPE=? ", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP, Datas.STRING, Datas.TIMESTAMP,Datas.STRING, Datas.INT, Datas.STRING}), new SerializerReadClass(FacilityBillingTableModel.Facilityline.class)).list(new Object[]{  dnow,dnow,  no, facility});
    }
   
public final List<FacilityBillingTableModel.Facilityline> getMembersToBeBilled1(Date dnow,Date edatePlusOne, String facility, String type) throws BasicException {
        //praveen:added these statements
          if(type.equals("mm")){
          type="TIMESTAMPDIFF(MONTH,M.LBILLDATE,?)";
      }else if(type.equals("yy")){
          type="TIMESTAMPDIFF(YEAR,M.LBILLDATE,?)";
      }else if(type.equals("dd")){
          type="DATEDIFF(M.LBILLDATE,?)";

      }
 return new StaticSentence(s, "SELECT M.ID,C.SEARCHKEY,C.NAME,F.RENEWALAMT,M.SDATE,M.LBILLDATE, "+type+" ,F.DUEPERIOD,C.ACCOUNT,M.MEMNO,CASE WHEN M.USERID IS NULL THEN NULL ELSE (SELECT DNAME FROM MEMDEPENDENT WHERE ID=M.USERID) END,M.USERID,C.MOBILE " + "FROM MEMFACILITYUSAGE M,FACILITY F ,CUSTOMERS C WHERE M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE AND C.ID=M.MEMNO AND C.VISIBLE=TRUE AND M.FACILITYTYPE=? AND M.LBILLDATE<? ORDER BY C.SEARCHKEY", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,  Datas.STRING, Datas.TIMESTAMP}), new SerializerReadClass(FacilityBillingTableModel.Facilityline.class)).list(new Object[]{ edatePlusOne, facility, dnow});
    }


}

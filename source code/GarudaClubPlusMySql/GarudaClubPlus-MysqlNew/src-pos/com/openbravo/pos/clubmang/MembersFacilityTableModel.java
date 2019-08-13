/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MembersFacilityTableModel {
  private List<Facilityline> fac;
  private final static String[] FACILITYHEADERS = {"Facility Name","Used By","Status"};
  private MembersFacilityTableModel()
  {
  }
  

  public static MembersFacilityTableModel emptyinstance()
  {
      MembersFacilityTableModel d=new MembersFacilityTableModel();
      d.fac=new ArrayList<Facilityline>();
      return d;//F.RPERIODICITY=P.ID AND  ,PERIODICITY P ,P.NAME
  }
  public static MembersFacilityTableModel loadInstance(AppView app,String memid) throws BasicException{
      MembersFacilityTableModel d = new MembersFacilityTableModel();
      d.fac=new ArrayList<Facilityline>();
     /* List<Facilityline> dlist = new StaticSentence(app.getSession()
                ,"SELECT F.NAME,M.SDATE,M.LBILLDATE,M.STATUS,F.JOINAMT,F.RENEWALAMT,F.USAGEAMT,P.NAME ,F.TYPE_,F.ID,M.ID,F.RPERIODICITY,F.APPTO,CASE WHEN M.USERID IS NOT NULL THEN SELECT DNAME FROM MEMDEPENDENT WHERE ID = M.USERID ELSE NULL END,M.USERID,FM.TYPE_,M.FACMANGREF  "
                +"FROM MEMFACILITYUSAGE M JOIN FACILITY F ON  M.FACILITYTYPE=F.ID JOIN PERIODICITY P ON P.ID=F.RPERIODICITY LEFT OUTER JOIN FACILITYMANAGER FM ON M.ID=FM.MFUID AND FM.APPROVEDBY IS NULL WHERE M.MEMNO=? AND STATUS != ? "
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
              ,new SerializerReadClass( MembersFacilityTableModel.Facilityline.class )).list(new Object[]{memid,2});*/

      //PRAVEEN_changed above sql statement
      //praveen_changed status to status_
      List<Facilityline> dlist = new StaticSentence(app.getSession()
                ,"SELECT F.NAME,M.SDATE,M.LBILLDATE,M.STATUS_,F.JOINAMT,F.RENEWALAMT,F.USAGEAMT,P.NAME ,F.TYPE_,F.ID,M.ID,F.RPERIODICITY,F.APPTO,CASE WHEN M.USERID IS NOT NULL THEN( SELECT DNAME FROM MEMDEPENDENT M1,MEMFACILITYUSAGE M WHERE M1.ID = M.USERID AND NULL) END,M.USERID,FM.TYPE_,M.FACMANGREF  "
                +"FROM MEMFACILITYUSAGE M JOIN FACILITY F ON  M.FACILITYTYPE=F.ID AND M.ACTIVE=TRUE JOIN PERIODICITY P ON P.ID=F.RPERIODICITY LEFT OUTER JOIN FACILITYMANAGER FM ON M.ID=FM.MFUID AND FM.APPROVEDBY IS NULL WHERE M.MEMNO=? AND M.STATUS_ != ? "  //praveen:
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
              ,new SerializerReadClass( MembersFacilityTableModel.Facilityline.class )).list(new Object[]{memid,2});
      if(dlist==null){
             d.fac=new ArrayList<Facilityline>();
         }else{
      /*    for(Facilityline f:dlist){
           for(Facilityline f1:d.fac){   
            if(f.getfname().equals(f1.getfname())){
               if(f.isActive()){

                 break;
               }else{
                  if(f.isActive()==false && f1.isActive()==false){
                    Calendar cal=Calendar.getInstance();
                    Calendar cal1=Calendar.getInstance();
                    cal.setTimeInMillis(f.getsdate().getTime());
                    cal1.setTimeInMillis(f1.getsdate().getTime());
                    if(cal.before(cal1)){
                        d.fac.add(f);
                        d.fac.remove(f1);
                        break;
                    }
                  }
               }
            }
           }
           if(d.fac.size()<=0){
             d.fac.add(f);
           }
          }*/
          d.fac=dlist;
         }
     return d;
  }
  //praveen:added to get standard facilities
  public static MembersFacilityTableModel loadInstance(AppView app,String memid,String fac) throws BasicException{
      MembersFacilityTableModel d = new MembersFacilityTableModel();
      d.fac=new ArrayList<Facilityline>();
      List<Facilityline> dlist = new StaticSentence(app.getSession()
                ,"SELECT F.NAME,M.SDATE,M.LBILLDATE,M.STATUS_,F.JOINAMT,F.RENEWALAMT,F.USAGEAMT,P.NAME ,F.TYPE_,F.ID,M.ID,F.RPERIODICITY,F.APPTO,CASE WHEN M.USERID IS NOT NULL THEN( SELECT DNAME FROM MEMDEPENDENT M1,MEMFACILITYUSAGE M WHERE M1.ID = M.USERID AND NULL) END,M.USERID,FM.TYPE_,M.FACMANGREF  "
                +"FROM MEMFACILITYUSAGE M JOIN FACILITY F ON  M.FACILITYTYPE=F.ID JOIN PERIODICITY P ON P.ID=F.RPERIODICITY LEFT OUTER JOIN FACILITYMANAGER FM ON M.ID=FM.MFUID AND FM.APPROVEDBY IS NULL WHERE M.MEMNO=? AND M.STATUS_ != ? AND F.TYPE_= ? AND M.ACTIVE=TRUE"
              ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT,Datas.STRING})
              ,new SerializerReadClass( MembersFacilityTableModel.Facilityline.class )).list(new Object[]{memid,2,fac});
      if(dlist==null){
             d.fac=new ArrayList<Facilityline>();
         }else{
          d.fac=dlist;
         }
     return d;
  }
  //end...............
  public List<Facilityline> getfacilityline()
     {
         return fac;
     }
  public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(FACILITYHEADERS[column]);
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {

                return FACILITYHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {
                case 0: return l.getfname();
                case 1: if(l.getUserName()==null)
                          return "Member";
                        else
                          return l.getUserName();
                case 2: switch(l.getStatus()){//statusmeanings
                          case 0:  return "In Usage";
                          case 1:  return "Suspended";
                          case 2:  return "Deactivated";
                          case 3:  return "Request for deactivation of this facility is under processing";
                          case 4:  return "Request for suspension of this facility is under processing";
                          case 5:  return "Request for starting this facility is under processing";
                          default: return null;//return l.getStatus();
                        }
                case 3: return l.getsdate();
                case 4: return l.getlbilldate();
                case 5: return l.getJoinFee();
                case 6: return l.getRenewalFee();
                case 7: return l.getUsageFee();
                case 8: return l.getRPeriod();
                
                default: return null;
                }
            }
        };
    }


public static class Facilityline implements SerializableRead{
   
    private String fname;
    private Timestamp sdate;
    private Timestamp lbdate;
    private int status;
    private Double joinfee;
    private Double renewalfee;
    private Double usagefee;
    private String rperiod;
    private String ftype;
    private String fid;
    private String mid;
    private String rperiodid;
    private int appto;
    private String username;
    private String userid;
    private String facmangreq;

    public void readValues(DataRead dr) throws BasicException
    {
        fname=dr.getString(1);
        sdate=dr.getTimestamp(2);
        lbdate=dr.getTimestamp(3);
        if(dr.getInt(4)==null)
        status=-1;
        else
            status=dr.getInt(4);
        joinfee=dr.getDouble(5);
        renewalfee=dr.getDouble(6);
        usagefee=dr.getDouble(7);
        rperiod=dr.getString(8);
        ftype=dr.getString(9);
        fid=dr.getString(10);
        mid=dr.getString(11);
        rperiodid=dr.getString(12);
        appto=dr.getInt(13); // 0 indicates applicable to member and 1 indicates applicable to member dependent
        username=dr.getString(14);
        userid=dr.getString(15);
        if(dr.getInt(16)!=null)
            status=dr.getInt(16);
        facmangreq=dr.getString(17);
       // if(userid !=null)
         //   fname=fname+ " : "+username;
   }
    public String getFacilityManagerReference(){
        return facmangreq;
    }

    public String getUserName(){
       return username;
    }
    public String getUserID(){
       return userid;
    }
    public int getApplicableTo(){
       return appto;
    }
    public String getRperiodId(){
      return rperiodid;
    }
    public String getFtype(){
     return ftype;
    }
    public String getMid(){
      return mid;
    }
    public String getFid(){
     return fid;
    }

    public Timestamp getsdate(){
      return sdate;
    }
    public Timestamp getlbilldate(){
      return lbdate;
    }
    public String getfname(){
       return fname;
    }
    public int getStatus(){
       return status;
    }
    public Double getJoinFee(){
       return joinfee;
    }
    public Double getRenewalFee(){
       return renewalfee;
    }
     public Double getUsageFee(){
       return usagefee;
    }
     public String getRPeriod(){
       return rperiod;
     }
        
     public String toString(){
        return fname;
     }
 }

}

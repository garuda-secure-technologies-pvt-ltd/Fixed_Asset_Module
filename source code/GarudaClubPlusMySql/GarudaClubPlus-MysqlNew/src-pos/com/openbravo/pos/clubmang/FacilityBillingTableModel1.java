/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class FacilityBillingTableModel1 {
   private List<Facilityline> fac;
    private final static String[] FACILITYHEADERS = {"No","Member name","Startdate","last bill date","No Of Period","Amt",""};
  private FacilityBillingTableModel1()
   {
   }

  public static FacilityBillingTableModel1 emptyinstance()
  {
      FacilityBillingTableModel1 d=new FacilityBillingTableModel1();
      d.fac=new ArrayList<Facilityline>();
      return d;
  }
  public static FacilityBillingTableModel1 loadInstance(AppView app,Facility fac1,DataLogicFacilities dlfac) throws BasicException{
      FacilityBillingTableModel1 d = new FacilityBillingTableModel1();
      DebtTypeTableModel.DebtTypeline periodtype=dlfac.getDebtTypebyid(fac1.getdueperiod());
      Periodicity p=dlfac.getPerioicitybyid(fac1.getrperiod());
      String type=null;
       if(p.gettype().equals("Months")){
          type="mm";
      }else if(p.gettype().equals("Years")){
          type="yy";
      }else if(p.gettype().equals("Days")){
          type="dd";
      }else if(p.gettype().equals("Quaterly")){
          type="mm";
      }
      Date date=new Date();
      Date edate=new Date();
      Date duedate=new Date();
      FacilityLogic flogic=new FacilityLogic(dlfac);
      Calendar cal1=Calendar.getInstance();
      cal1.setTimeInMillis(date.getTime());
      int billabledate=cal1.get(Calendar.DATE);
      if(p.getdoj()==false){
          cal1.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal1).getTimeInMillis());
      }
      date.setTime(cal1.getTimeInMillis());
      billabledate=cal1.get(Calendar.DATE);
      flogic.setTemp(date);
      edate.setTime(flogic.calculateEndDate(date,p,billabledate,1,new Date()).getTime());
      List<FacilityBillingTableModel1.Facilityline> memlist=new ArrayList<Facilityline>();
      // List<FacilityBillingTableModel1.Facilityline> memlist1=new ArrayList<Facilityline>();
      Calendar edatePlusOnceCal=Calendar.getInstance();
        edatePlusOnceCal.setTimeInMillis(edate.getTime());
        edatePlusOnceCal.add(Calendar.DATE, 1);
        Date edatePlusOne=new Date(edatePlusOnceCal.getTimeInMillis());
        List list = dlfac.getMembersToBeBilled1(edate,edatePlusOne, fac1.getid(), type);
      if(list!=null){
        memlist=list;
      }
      for(FacilityBillingTableModel1.Facilityline line:memlist){
        if(line.getno()>0){
          if(line.getno()>1){
            line.setamt(line.getamt()*line.getno());
           d.fac.add(line);
          }else
             d.fac.add(line);
        }
      }
     return d;
  }


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
             Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class,java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false,true
            };
             @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            public Object getValueAt(int row, int column) {
                Facilityline l = fac.get(row);

                switch (column) {

                case 0: return l.getmno();
                case 1: return l.getmname();
             //   case 2: return l.getfname();
                case 2: return l.getsdate();
                case 3: return l.getlbilldate();
            //    case 5: return l.getcrby();
                case 4:return l.getno();
              //  case 5:return l.getramt();
                case 5: return l.getamt();
                case 6: return l.getbillit();
                case 7: return l.getbillabledate();
                case 8:return l.getid();
                case 9: return l.getaccid();
                case 10: return l.getcustomeraccount();
                case 11: return l.getmemid();
                default: return null;
                }
            }
        };
    }

private static int temp=0;
public static class Facilityline implements SerializableRead{
    private String id;
    private String mno;
    private String mname;
 //   private String fname;
    private Double ramt;
    private Timestamp sdate;
    private Timestamp lbdate;
 //   private String createdby;
    private Double amt=0.0;
    private int no;
    private Timestamp billabledate;
    private String accid;
    private Boolean billit=true;
    private String caccount;
    private String memid;
    private int slno=0;

    public void readValues(DataRead dr) throws BasicException
    {
         slno=++temp;
        id=dr.getString(1);
        mno=dr.getString(2);
        mname=dr.getString(3);
       // fname=dr.getString(4);
        ramt=dr.getDouble(4);
        sdate=dr.getTimestamp(5);
        lbdate=dr.getTimestamp(6);
       // createdby=dr.getString(8);
        no=dr.getInt(7);
        accid=dr.getString(8);
        caccount=dr.getString(9);
        memid=dr.getString(10);
        billabledate=lbdate;
        amt=ramt* no;
   }
    public String getmemid(){
      return memid;
    }
    public String getcustomeraccount(){
      return  caccount;
    }
    public Boolean getbillit(){
       return billit;
    }
    public String getaccid(){
        return accid;
    }
    public Double getamt(){
       return amt;
    }
    public Timestamp getbillabledate(){
      return billabledate;
    }
    public void setbillabledate(Timestamp date){
      billabledate=date;
    }
    public Double getramt(){
       return ramt;
    }
    public int getno(){
     return no;
    }
     public void setno(int nno){
      no=nno;
    }
    public void setamt(Double amount){
        amt=amount;
    }
    public String getid() {
     return id;
    }
   public String getmno(){
     return mno;
   }
  //  public String getcrby(){
  //      return createdby;
 //   }
    public Timestamp getsdate(){
      return sdate;
    }
     public Timestamp getlbilldate(){
      return lbdate;
    }
    public String getmname(){
       return mname;
    }
    public String getSlNo(){
        return String.valueOf(slno);
    }
 //   public String getfname(){
 //      return fname;
 //   }

 }
}

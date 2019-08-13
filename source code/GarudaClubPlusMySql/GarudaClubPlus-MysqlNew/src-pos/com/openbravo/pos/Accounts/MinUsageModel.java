/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

/**
 *
 * @author swathi
 */
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
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MinUsageModel {
      private List<MinUsageline> fac;
     
      private int flag;
    private final static String[] MINUSAGEHEADERS = {"NAME","PERIOD","MINIMUM USAGE","CREATED DATE","CREATEDBY","CHARGE","ACCOUNT HEAD","APPLICABLE TO","DEBTTYPE","SEQUENCE","MAXNO","TAX","MAXDEBT","SMS","LASTBILLEDDATE"};
    private final static String[] MINUSAGEHEADERS1 = {"NAME","PERIOD","MINIMUM USAGE","CREATED DATE","CREATEDBY","CHARGE","ACCOUNT HEAD","APPLICABLE TO","DEBTTYPE","SEQUENCE","MAXNO","TAX","MAXDEBT","SMS","LASTBILLEDDATE","DEACTIVATED BY","DEACTIVATED DATE"};

  private MinUsageModel()
   {
   }

  public static MinUsageModel emptyinstance()
  {
      MinUsageModel d=new MinUsageModel();
      d.fac=new ArrayList<MinUsageline>();
      return d;
  }
  public static MinUsageModel loadInstance(AppView app,int flag) throws BasicException {
      MinUsageModel d = new MinUsageModel();
      d.flag=flag;
     List dlist;
     if(flag==1){
             dlist= new StaticSentence(app.getSession()
//                ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
//                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID"// ine:53- TAXES REPLACED BY TAXCATEGORIES
              ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID AND  M.ACTIVE=1  "// ine:53- TAXES REPLACED BY TAXCATEGORIES
                     
                     ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass(MinUsageModel.MinUsageline.class )).list();
     }else{
          dlist= new StaticSentence(app.getSession()
                ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID  "//line:59 TAXES REPLACED BY TAXCATEGORIES
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass(MinUsageModel.MinUsageline.class )).list();
     }
     if(dlist==null)
     {
         d.fac=new ArrayList<MinUsageline>();
     }
     else
     {
         d.fac=dlist;
     }

     return d;

  }
  
  //ADD BY LIPI 
//   public static MinUsageModel loadInstance1(AppView app,int flag) throws BasicException {
//      MinUsageModel d = new MinUsageModel();
//      d.flag=flag;
//     List dlist;
//     if(flag==1){
//             dlist= new StaticSentence(app.getSession()
////                ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
////                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID"// ine:53- TAXES REPLACED BY TAXCATEGORIES
//              ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
//                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID AND  M.ACTIVE=1"// AND M.ACTIVE=1 "// ine:53- TAXES REPLACED BY TAXCATEGORIES
//                     
//                     ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass(MinUsageModel.MinUsageline.class )).list();
//     }else{
//          dlist= new StaticSentence(app.getSession()
//                ,"SELECT M.NAME,P.NAME,M.AMOUNT,M.CREATEDDATE,M.CREATEDBY,M.CHARGE,A.NAME,CASE WHEN M.MEMBERS='ALL' THEN 'ALL' ELSE (SELECT M.NAME FROM MEMTYPE M WHERE M.MEMBERS = M.ID) END,D.NAME,M.BILLSEQUENCE,M.MAXNO,T.NAME,M.MAXDEBT,M.SMS,M.LASTBILLEDDATE,M.DEACTIVATEDBY,M.DEACTIVATEDDATE"
//                +",M.ID FROM MINUSAGE M,PERIODICITY P,ACCOUNTMASTER A,DEBTTYPE D,TAXCATEGORIES T WHERE M.PERIOD=P.ID AND M.ACHEAD=A.ID AND M.CREDITPERIOD=D.ID AND M.SERVICETAX=T.ID "//line:59 TAXES REPLACED BY TAXCATEGORIES
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass(MinUsageModel.MinUsageline.class )).list();
//     }
//     if(dlist==null)
//     {
//         d.fac=new ArrayList<MinUsageline>();
//     }
//     else
//     {
//         d.fac=dlist;
//     }
//
//     return d;
//
//  }
//  
  
  
  
  

  public List<MinUsageline> getMinUsageline()
     {
         return fac;
     }
 
  public AbstractTableModel getMinUsageModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                if(flag==1)
                //return AppLocal.getIntString(MINUSAGEHEADERS[column]);
                    //sanjeev:commented above line and added below line
                    return MINUSAGEHEADERS[column];
                else
                     //return AppLocal.getIntString(MINUSAGEHEADERS1[column]);
                    return MINUSAGEHEADERS1[column];
            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {
              if(flag==1)
                  return MINUSAGEHEADERS.length;
              else
                  return MINUSAGEHEADERS1.length;
            }
            public Object getValueAt(int row, int column) {
                MinUsageline l = fac.get(row);

                switch (column) {
                  case 0 : return l.getName();
                  case 1: return l.getPeriod();
                  case 2: return l.getMinUsage();
                  case 3: return l.getCreatedDate();
                  case 4: return l.getCreatedBy();
                  case 5: return l.getCharge();
                  case 6: return l.getAccHead();
                  case 7: return l.getAppTo();
                  case 8: return l.getCreditperiod();
                  case 9: return l.getBillSequence();
                  case 10: return l.getMaxno();
                  case 11: return l.getServiceTax();
                  case 12: return l.getMaxDebt();
                  case 13: return l.getSms();
                  case 14: return l.getLastbilleddate();
                  case 15: return l.getDeactivatedBy();
                  case 16: return l.getDeactivatedDate();
                  case 17: return l.getid(); 
//                  case 18: return l.getServiceTax2();
//                  case 19: return l.getServiceTax3();

                default: return null;
                }
            }
        };
    }



public static class MinUsageline implements SerializableRead{
    private String id;
    private String name;
    private String period;
    private Double  uamt;
    private Double  amt;
    private Timestamp crdate;
    private String acchead;
    private String appto;
    private String deactivatedby;
    private Timestamp deactivateddate;
    private String creditperiod;
    private String createdby;
    private String billsequence;
    private String servicetax;
    private String maxdebt;
    private String sms;
    private Timestamp lastbilleddate;
    private String maxbillsequence;
    private Integer maxno; 
   
//        private Object ServiceTax2;
//        private Object ServiceTax3;

    public void readValues(DataRead dr) throws BasicException
    {
      
        name=dr.getString(1);
        period=dr.getString(2);
        uamt=dr.getDouble(3);
        crdate=dr.getTimestamp(4);
        createdby=dr.getString(5);
        amt=dr.getDouble(6);
        acchead=dr.getString(7);
        appto=dr.getString(8);
        creditperiod = dr.getString(9);
        billsequence = dr.getString(10);
        maxno = dr.getInt(11);
        servicetax = dr.getString(12);
        maxdebt = dr.getString(13);
        sms = dr.getString(14);
        lastbilleddate=dr.getTimestamp(15);
        deactivatedby=dr.getString(16);
        deactivateddate=dr.getTimestamp(17);
        id=dr.getString(18);
//        servicetax2=dr.getString(19);
//       servicetax3=dr.getString(20);

       
    }
    public String getPeriod(){
       return period;
    }
   
    public String getAccHead(){
      return acchead;
    }
     public Integer getMaxno(){
      return maxno;
    }
    public String getAppTo(){
      return appto;
    }
  
    public String getid() {
     return id;
    }
    public String getDeactivatedBy(){
     return deactivatedby;
    }
    public Timestamp getDeactivatedDate(){
        return deactivateddate;
    }
   
    public Timestamp getCreatedDate(){
      return crdate;
    }
     public Timestamp getLastbilleddate(){
      return lastbilleddate;
    }
    public String getName(){
       return name;
    }
    public Double getMinUsage()
    {
        return uamt;
    }
    public Double getCharge()
    {
        return amt;
    }
    public Double getuamt()
    {

        return uamt;
    }
    public String getCreditperiod(){
        return creditperiod;
     }
    public String getCreatedBy(){
        return createdby;
     }
     public String getSms(){
        return sms;
     }
       public String getBillSequence(){
        return billsequence;
     }
       public String getServiceTax(){
        return servicetax;
     }
        public String getMaxDebt(){
        return maxdebt;
     }
        public String getMaxBillSequence(){
        return maxbillsequence;
     }

//        private Object getServiceTax2() {
//            return ServiceTax2;
//        }
//
//        private Object getServiceTax3() {
//             return ServiceTax3;        }
}
 
}

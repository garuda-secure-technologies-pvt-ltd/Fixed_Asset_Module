/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author swathi
 */
public class ManualFacilityBillingTableModel {
  private List<Receiptline> fac;
   DecimalFormat decimalFormat = new DecimalFormat("#0.00");
  private final static String[] RECEIPTHEADERS = {"Facility","User","From","To","Amount"};


  public static ManualFacilityBillingTableModel emptyinstance()
  {
      ManualFacilityBillingTableModel d=new ManualFacilityBillingTableModel();
      d.fac=new ArrayList<Receiptline>();
      return d;
  }
  public List<Receiptline> getReceiptlist(){
         return fac;
  }
  public void addReceiptLine(Receiptline r){
     // Receiptline r=new Receiptline();
      fac.add(r);
  }
   public void RemoveReceiptLine(int i){
     // Receiptline r=new Receiptline();
      fac.remove(i);
  }
  public Receiptline getReceiptline(){
    return new Receiptline();
  }
  public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            @Override
            public String getColumnName(int column) {

                //return AppLocal.getIntString(RECEIPTHEADERS[column]);
                return RECEIPTHEADERS[column];

            }
            public int getRowCount() {
                return fac.size();
            }
            public int getColumnCount() {
                return RECEIPTHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                Receiptline l = fac.get(row);

                switch (column) {
              //  case 0: return l.getname();
                case 0: return l.getFacilityName();
                case 1: return l.getUserName();
                case 2: return Formats.DATE.formatValue(l.getSdate());
                case 3: return Formats.DATE.formatValue(l.getEdate());
                case 4: return decimalFormat.format(l.getAmount());

                default: return null;
                }
            }
        };
    }
public  class Receiptline  {


   // private String rperiod;
    private String userid;
    private String username;
    private String facilityid;
    private String facilityName;
    private Timestamp fdate;
    private Timestamp edate;
    private double amount;
    private Timestamp duedate;
    private Timestamp lbilldate;
    private String facc;
    private double taxamt;
    private String billnum;
    private int nperiods;
    public Receiptline(){
        userid=null;
    }
    public String getFacilityAccount(){
        return facc;
    }
    public void setNoOfPeriod(int num){
        nperiods=num;
    }
    public int getNoOfPeriod(){
       return nperiods;
    }
    public void setFacilityAccount(String acc){
        facc=acc;
    }
     public String getBillNum(){
        return billnum;
    }
    public void setBillNum(String bnum){
        billnum=bnum;
    }
    public String getUserName(){
        return username;
    }

    public void setuserName(String uname){
      username=uname;
    }
    public void setAmount(double amt){
        this.amount=amt;
    }
    public Double getAmount(){
      return amount;
    }
     public void setTaxamt(double amt){
        this.taxamt=amt;
    }
    public Double getTaxamt(){
      return taxamt;
    }
    public String getUserId(){
      return userid;
    }
     public void setUserid(String id){
      userid=id;
    }
    public void setFacilityName(String fac){
      facilityName=fac;
    }
    public String getFacilityName(){
      return facilityName;
    }
    public String getFacilityid(){
       return facilityid;
    }
    public void setFacilityId(String facid){
      facilityid=facid;
    }
    public Timestamp getSdate(){
      return fdate;
    }
    public void setSdate(Date date){
      fdate=new Timestamp(new Date().getTime());
      fdate.setTime(date.getTime());
    }
    public Timestamp getEdate(){
      return edate;
    }
    public void setEdate(Date date){
      edate=new Timestamp(new Date().getTime());
      edate.setTime(date.getTime());
    }
    public Timestamp getDuedate(){
      return duedate;
    }
    public void setDuedate(Date date){
      duedate=new Timestamp(new Date().getTime());
      duedate.setTime(date.getTime());
    }
     public Timestamp getlBilldate(){
      return lbilldate;
    }
    public void setlBilldate(Date date){
      lbilldate=new Timestamp(new Date().getTime());
      lbilldate.setTime(date.getTime());
    }
    public String PrintSdate(){
      return Formats.DATE.formatValue(fdate);
    }
     public String PrintEdate(){
      return Formats.DATE.formatValue(edate);
    }
 }
}

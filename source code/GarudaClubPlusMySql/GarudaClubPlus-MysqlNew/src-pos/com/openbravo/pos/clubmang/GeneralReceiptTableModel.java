/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author swathi
 */
public class GeneralReceiptTableModel {
    private List<Receiptline> fac;
    private final static String[] RECEIPTHEADERS = {"Account","Amount"};
  private GeneralReceiptTableModel()
  {

  }

  public static GeneralReceiptTableModel emptyinstance()
  {
      GeneralReceiptTableModel d=new GeneralReceiptTableModel();
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
                return (RECEIPTHEADERS[column]);
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
                case 0: return l.getaccountName();
                case 1: return l.getAmount();
                
                default: return null;
                }
            }
        };
    }
public  class Receiptline  {
    
   
   // private String rperiod;
    private String accountname;
    private String accountid;
    private double amount;
    public Receiptline(){
    }
    public String getaccountName(){
        return accountname;
    }
    public String printaccountName(){
       return StringUtils.encodeXML(accountname);
    }
    public void setAmount(double amt){
        this.amount=amt;
    }
    public Double getAmount(){
      return amount;
    }
     public void setAccountid(String acc){
      accountid=acc;
    }
    public void setAccountName(String acc){
      accountname=acc;
    }

    public String getaccount(){
       return accountid;
    }

 }
}

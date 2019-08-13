/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
public class Account implements SerializableRead,Comparable,IKeyed{
     private String name;
     private String id;
     private String skey;
     private double amount=0;
     private String parent;
     private double camt=0;
     private String operands;
     private List<Account> subAccList;
     private String sign;
     private double qty=0;
     private List<Object[]> breakUpList;
     private boolean visible=false;
     private int plevel=0;
     private boolean printChild=false;
     public void readValues(DataRead dr) throws BasicException {

         name=dr.getString(1);
         System.out.print(name);
         id=dr.getString(2);
         skey=dr.getString(3);
         parent=dr.getString(4);
         if(parent==null)
             parent="";
         sign=dr.getString(5);
         operands=dr.getString(6);
         amount=dr.getDouble(7);
         camt=dr.getDouble(8);
         subAccList=new ArrayList<Account>();
         breakUpList=new ArrayList<Object[]>();
     }


     public Account(List<Account> a){
        subAccList=new ArrayList<Account>();
        if(a!=null)
        subAccList.addAll(a);
        breakUpList=new ArrayList<Object[]>();
     }
     public Account(){
         subAccList=new ArrayList<Account>();
         name="";
         id="";
         skey="";
         parent="";
         sign="";
         operands="";
         amount=0;
         camt=0;
         breakUpList=new ArrayList<Object[]>();
     }
     public Account(Account a){
         subAccList=new ArrayList<Account>();
         subAccList.addAll(a.getAccountList());
         name=a.getAccountName();
         id=a.getID();
         skey=a.getSKey();
         parent=a.getParent();
         sign=a.getSign();
         operands=a.getOperands();
         amount=a.getAmount();
         camt=a.getCamt();
         breakUpList=new ArrayList<Object[]>();
         breakUpList.addAll(a.getBreakUpList());
     }
     public Account(List<Account> acclist,double debit,double credit) {
           this.subAccList=new ArrayList<Account>();
           if(acclist!=null)
          this.subAccList.addAll(acclist);
          this.amount=debit;
          this.camt=credit;
       }
     public void addAmount(double amt){
             amount+=amt;
     }
    //public void addAmount(double amt){
      //       amount+=amt;
     //}
     public List<Object[]> getBreakUpList(){
        return breakUpList;
     }
     public void setQty(double qty){
         this.qty=qty;
     }
     public void addBreakUp(Object[] obj){
         breakUpList.add(obj);
     }
     public double getQty(double qty){
         return qty;
     }
     public void addAccount(Account acc){
            subAccList.add(acc);
            amount+=acc.getAmount();
            camt+=acc.getCamt();
     }
     public void addAccount(Account acc,int index){
            subAccList.add(index,acc);
            amount+=acc.getAmount();
            camt+=acc.getCamt();
     }
     
     public void addAccountOnly(Account acc){
            subAccList.add(acc);
          //  amount=0;
          //  camt=0;
     }
     public void addAccountOnly(Account acc,int index){
            subAccList.add(index,acc);
          //  amount=0;
          //  camt=0;
     }
     public void addCredit(double amt){
             camt+=amt;
     }
     public void subAmount(double amt){
             amount-=amt;
     }
     public void subCredit(double amt){
             camt-=amt;
     }
     public String getOperands(){
        return operands;
     }
     public void setParent(String parent){
             this.parent=parent;
     }
     public String getSign(){
       return sign;
     }
     public double getCamt(){
         return camt;
     }
     public String getParent(){
       return parent;
     }
   /*  public void setSubAccList(List<Account> list){
         subAccList=list;
     }*/
     public String getID(){
        return id;
     }
     public String getAccountName(){
        return name;
     }
     public String getSKey(){
        return skey;
     }
     public double getAmount(){
        // if(amount==null)
      //       return 0.0;
      //   else
           return amount;
     }
     public double getAmount1(){
       return camt-amount;
     }
      public void addDC(double debit,double credit){
            this.amount+=debit;
            this.camt+=credit;
        }
    //  public void setDC(double debit,double credit){
    //        this.amount+=debit;
    //        this.camt+=credit;
    //    }
        public void setDC(double debit,double credit){
            this.amount=debit;
            this.camt=credit;
        }
        public List<Account> getAccountList(){
          
         // if(subAccList.size()!=0)
          return subAccList;
        //  else return new ArrayList<Account>();
        }
     //   public void setAccountList(List<Account> l){
     //       subAccList=l;
     //   }
        public void setName(String name){
           this.name=name;
        }
        public void setSign(String sign){
           this.sign=sign;
        }
        public void setSKey(String skey){
           this.skey=skey;
        }
         public int compareTo(Object o) {
        if(o instanceof Account){
            Account t=(Account) o;
            
            return this.getAccountName().toUpperCase().compareTo(t.getAccountName().toUpperCase());
        }else
            return 0;
    }
    public boolean isVisible(){
      return visible;
    }
    public void setVisible(boolean visible){
        this.visible=visible;
    }
    public int getPrintLevel(){
      return plevel;
    }
    public void setPrintLevel(int level){
      plevel=level;
    }
    public boolean getChilPrintStatus(){
       return printChild;
    }
    public void setChilPrintStatus(boolean status){
       printChild=status;
    }
    public Object getKey() {
       return name;
    }

    public List<Account> getSubAccList() {
        return subAccList;
    }

    public void setSubAccList(List<Account> subAccList) {
        this.subAccList = subAccList;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    @Override
    public String toString(){
        return name;
    }
}

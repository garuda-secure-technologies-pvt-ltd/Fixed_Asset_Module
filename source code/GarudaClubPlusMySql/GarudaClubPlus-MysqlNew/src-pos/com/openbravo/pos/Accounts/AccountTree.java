/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
 public class AccountTree{
       private List<Account> acclist;
       private double debit=0.0;
       private double credit=0.0;

       public AccountTree(Account a) {
            this.acclist = new ArrayList<Account>();
            acclist.add(a);
            debit=0.0;
            credit=0.0;
       }

       public AccountTree(List<Account> acclist,double debit,double credit) {
           this.acclist=new ArrayList<Account>();
           if(acclist!=null)
          this.acclist.addAll(acclist);
          this.debit=debit;
          this.credit=credit;
       }

       public AccountTree() {
            this.acclist = new ArrayList<Account>();
            debit=0.0;
            credit=0.0;
        }
        public void addDC(double debit,double credit){
            this.debit+=debit;
            this.credit+=credit;
        }
        public void setDC(double debit,double credit){
            this.debit=debit;
            this.credit=credit;
        }
        public List<Account> getAccountList(){
            return acclist;
        }
        public void addAccount(Account a){
            acclist.add(a);
        }
        public void addAccount(Account a,int index){
            acclist.add(index,a);
        }
        public void setAccountList(List<Account> l){
            acclist=l;
        }
        public double getDebit(){
            return debit;
        }
        public double getCredit(){
            return credit;
        }

    }

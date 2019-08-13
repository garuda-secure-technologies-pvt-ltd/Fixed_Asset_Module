/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.TreeTableModel;
import java.util.Date;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author swathi
 */
public class ReceiptAndPaymentLogicNew {
   private Date sdate;
   private Date edate;
   private AppView m_App;
   private List<Account> OBacclist;
   private List<Account> CLacclist;
   private List<Account> dcAcclist;
   private Account mainele;
   private Account maineledetail;
  // private Map<String ,Account> mapdetail;
   private Map<String ,Account> mapcondensed;
   private Map<String,Account> map1;
   private final static String[] HEADERS = {"Account","Amount"};
    public ReceiptAndPaymentLogicNew(Date sdate, Date edate,AppView app) {
        this.sdate = sdate;
        this.edate = edate;
        m_App=app;
     //   mapdetail=new HashMap<String, Account>();
        mapcondensed=new HashMap<String, Account>();
        map1=new HashMap<String, Account>();
        OBacclist=new ArrayList<Account>();
        CLacclist=new ArrayList<Account>();
        dcAcclist=new ArrayList<Account>();
    }

    public void getOpeningAndClosingBalance() throws BasicException{
      OBacclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,SUM(AJ.DEBIT+AJ.CURDEBIT),SUM(AJ.CREDIT+AJ.CURCREDIT) FROM ACCOUNTMASTER Am JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID WHERE (AM.PARENT='1.1.1.2' OR AM.parent='1.1.2') AND AJ.EDATE<=? GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),new SerializerReadClass(Account.class) ).list(new Object[]{sdate});
      CLacclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,SUM(AJ.DEBIT+AJ.CURDEBIT),SUM(AJ.CREDIT+AJ.CURCREDIT) FROM ACCOUNTMASTER Am LEFT JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID WHERE (AM.PARENT='1.1.1.2' OR AM.parent='1.1.2') AND AJ.EDATE<=?  GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP}),new SerializerReadClass(Account.class) ).list(new Object[]{edate});
    }
    public void getCreditAndDebitList() throws BasicException{
      dcAcclist=new PreparedSentence(m_App.getSession(), "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,SUM(AJ.DEBIT+AJ.CURDEBIT),SUM(AJ.CREDIT+AJ.CURCREDIT) FROM ACCOUNTMASTER Am JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID WHERE   AJ.EDATE>? AND AJ.EDATE<=? AND (AM.SIGN='C' OR AM.SIGN='D' OR AM.SIGN='E' OR AM.SUMMARY=FALSE) GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
              , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP}),new SerializerReadClass(Account.class) ).list(new Object[]{sdate,edate});
    }
    public void compute() throws BasicException{
      map1=new HashMap<String,Account>();
     // mapcondensed=new HashMap<String, Account>();
      getOpeningAndClosingBalance();
      getCreditAndDebitList();
      if(OBacclist==null)
          OBacclist=new ArrayList<Account>();
      if(CLacclist==null)
          CLacclist=new ArrayList<Account>();
      if(dcAcclist==null)
          dcAcclist=new ArrayList<Account>();
      Account recp=new Account();
      Account recpdetail=new Account();
      recp.setName("Receipt");
      recp.setSKey("R");
      recp.setParent("RP");
      recp.setDC(0, 0);
      recpdetail.setName("Receipt");
      recpdetail.setSKey("RD");
      recpdetail.setParent("RPD");
      recpdetail.setDC(0, 0);
      mainele=new Account();
      maineledetail=new Account();
      mainele.setName("Receipt And Payment");
      mainele.setSKey("RP");
      maineledetail.setName("Receipt And Payment");
      maineledetail.setSKey("RPD");
      Account payments=new Account();
      Account paymentsdetail=new Account();
      Account ob=new Account();
      Account cb=new Account();
      payments.setName("Payments");
      payments.setSKey("P");
      payments.setParent("RP");
      payments.setDC(0, 0);
      paymentsdetail.setName("Payments");
      paymentsdetail.setSKey("PD");
      paymentsdetail.setParent("RPD");
      paymentsdetail.setDC(0, 0);
      ob.setName("Opening Balance");
      ob.setSKey("OB");
      ob.setParent("R");
      ob.setDC(0, 0);
      map1.put(recpdetail.getSKey(), recpdetail);
      map1.put(paymentsdetail.getSKey(), paymentsdetail);
      for(Account acc:OBacclist){
         acc.setParent("OB");
         acc.setSKey(acc.getSKey()+"O");
         map1.put(acc.getSKey(), acc);
         ob.addAccount(acc);
      }
      map1.put(ob.getSKey(), ob);
      recp.addAccount(ob);
      ob.setParent("RD");
      recpdetail.addAccount(ob);
      List<String> templist=new ArrayList();
      int flag=0;
       for(Account acc: dcAcclist){
           Account accCopy=new Account(acc);
         if(map1.containsKey(acc.getParent())){
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             String tparent=null;
             Account at;
             //Account dat;
             flag=3;
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             //dat=map1.get(parent)
             if(flag==3){
               at.addAccount(acc);
               //flag=0;
             }
            if(tparent==null || !tparent.equals(parent))
              if(flag!=3){
                at.addDC(debit, credit);

              }else
                  flag=0;
             tparent=parent;
            map1.put(parent, at);
             map1.put(acc.getSKey(), acc);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                 parent="";
              flag=2;
            }  else if(flag==2){
                flag=3;
               break;
            }
          }
         }else{
             map1.put(acc.getSKey(), acc);
            /* double diff=acc.getAmount()-acc.getCamt();
             if(diff>0){
                Account atemp=new Account(acc);
                atemp.setSKey(atemp.getSKey()+"D");
                atemp.setParent(atemp.getParent()+"D");
                map1.put(atemp.getSKey(), atemp);
             }else if(diff<0){
                Account atemp=new Account(acc);
                atemp.setSKey(atemp.getSKey()+"C");
                atemp.setParent(atemp.getParent()+"C");
                map1.put(atemp.getSKey(), atemp);
             }*/
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             Account at;
             flag=3;
             String tparent=null;
             int temp=0;
          while(map1.containsKey(parent)){
              at=map1.get(parent);
              if(temp==0){
                 at.addAccount(acc);
              }
              if(flag==3){
               flag=0;
              }
             if(tparent==null || !tparent.equals(parent))
               if(temp!=0)
                  at.addDC(debit, credit);
               else
                  temp=1;
             tparent=parent;
             map1.put(parent, at);
             map1.put(acc.getSKey(), acc);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                flag=1;
            } else if(flag==1){
                parent="";
              flag=2;
            } else if(flag==2){
                flag=3;
               break;
            }

          }


            /* if(acc.getSign().equals("S")){
                 if(!templist.contains(acc.getSKey())){
                     templist.add(acc.getSKey());
                 }
             }*/
         }
         double amt=accCopy.getAmount()-accCopy.getCamt();
         boolean status=true;
        // Account accCopy=new Account(acc);
         if(amt>=0){
             accCopy.setParent(accCopy.getParent()+"R");
             accCopy.setSKey(accCopy.getSKey()+"R");
         }else if(amt<0){
             accCopy.setParent(accCopy.getParent()+"P");
             accCopy.setSKey(accCopy.getSKey()+"P");
             status=false;
         }
         if(map1.containsKey(accCopy.getParent())){
             String parent=accCopy.getParent();
             double debit=accCopy.getAmount();
             double credit=accCopy.getCamt();
             String tparent=null;
             Account at;
             //Account dat;
             flag=3;
             int i=0;
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             //dat=map1.get(parent)
             if(flag==3){
               at.addAccount(accCopy);
               //flag=0;
             }
            if(tparent==null || !tparent.equals(parent))
              if(flag!=3){
                at.addDC(debit, credit);

              }else
                  flag=0;
             tparent=parent;
            map1.put(parent, at);
            //accCopy.setSKey(accCopy.get)
             map1.put(accCopy.getSKey(), accCopy);
             if(accCopy.getSign().equals("C")){
              //if()
                 if(i==0){
               if(status){
                   //Receipt
                accCopy.setParent("RD");
                Account parentacc=map1.get("RD");
                parentacc.addAccount(accCopy);
                map1.put(parentacc.getSKey(), parentacc);
                Account accTemp=new Account(accCopy);
                accTemp.setParent("PD");
                accTemp.setSKey(accTemp.getSKey().replace("R", "P"));
                accTemp.setDC(0, 0);
                map1.put(accTemp.getSKey(), accTemp);
                Account parentacc1=map1.get("PD");
                parentacc1.addAccount(accTemp);
                map1.put(parentacc1.getSKey(), parentacc1);
               }else{
                accCopy.setParent("PD");
                Account parentacc=map1.get("PD");
                parentacc.addAccount(accCopy);
                map1.put(parentacc.getSKey(), parentacc);
                Account accTemp=new Account(accCopy);
                accTemp.setParent("RD");
                accTemp.setSKey(accTemp.getSKey().replace("P", "R"));
                accTemp.setDC(0, 0);
                map1.put(accTemp.getSKey(), accTemp);
                Account parentacc1=map1.get("RD");
                parentacc1.addAccount(accTemp);
                map1.put(parentacc1.getSKey(), parentacc1);
               }
                 }
                 i=1;
             }
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                 parent="";
              flag=2;
            }  else if(flag==2){
                flag=3;
               break;
            }
          }
         }else{
             map1.put(accCopy.getSKey(), accCopy);
            /* double diff=accCopy.getAmount()-accCopy.getCamt();
             if(diff>0){
                Account atemp=new Account(accCopy);
                atemp.setSKey(atemp.getSKey()+"D");
                atemp.setParent(atemp.getParent()+"D");
                map1.put(atemp.getSKey(), atemp);
             }else if(diff<0){
                Account atemp=new Account(accCopy);
                atemp.setSKey(atemp.getSKey()+"C");
                atemp.setParent(atemp.getParent()+"C");
                map1.put(atemp.getSKey(), atemp);
             }*/


             String parent=accCopy.getParent();
             double debit=accCopy.getAmount();
             double credit=accCopy.getCamt();
             Account at;
             flag=3;
             String tparent=null;
             int temp=0;
             if(accCopy.getSign().equals("C")){
               if(status){
                   //Receipt
                accCopy.setParent("RD");
                Account parent1=map1.get("RD");
                parent1.addAccount(accCopy);
                map1.put(parent1.getSKey(), acc);
               }else{
                accCopy.setParent("PD");
                Account parent1=map1.get("PD");
                parent1.addAccount(accCopy);
                map1.put(parent1.getSKey(), acc);
               }
               temp=1;
             }
          while(map1.containsKey(parent)){
              at=map1.get(parent);
              if(temp==0){
                 at.addAccount(accCopy);
              }
              if(flag==3){
               flag=0;
              }
             if(tparent==null || !tparent.equals(parent))
               if(temp!=0)
                  at.addDC(debit, credit);
               else
                  temp=1;
             tparent=parent;
             map1.put(parent, at);
             map1.put(accCopy.getSKey(), accCopy);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
            }else if(flag==0){
                flag=1;
            } else if(flag==1){
                parent="";
              flag=2;
            } else if(flag==2){
                flag=3;
               break;
            }

          }


            /* if(acc.getSign().equals("S")){
                 if(!templist.contains(acc.getSKey())){
                     templist.add(acc.getSKey());
                 }
             }*/
         }
      }
      /*for(String temp:templist){
           Account at=map1.get(temp);
           Collections.sort(at.getAccountList());
           map1.put(temp, at);
      }*/
      for(Account acc:dcAcclist){
          if(acc.getSign().equals("C")){
              Account atemp=map1.get(acc.getSKey());
              if(atemp!=null &&(atemp.getAmount()>0 || atemp.getCamt()>0)){
                 //recpdetail.addAccount(atemp);
                 //paymentsdetail.addAccount(atemp);
                 double tempamt=atemp.getAmount()-atemp.getCamt();
                 if(tempamt>0){
                    recp.addAccount(atemp);
                 }else if(tempamt<0){
                    payments.addAccount(atemp);
                 }
              }

          }
      }
      cb.setName("Closing Balance");
      cb.setSKey("CB");
      cb.setParent("P");
      cb.setDC(0, 0);
      for(Account acc:CLacclist){
         //acc.setParent("CB");
         acc.setSKey(acc.getSKey()+"C");
         map1.put(acc.getSKey(), acc);
         cb.addAccountOnly(acc);
         cb.addCredit(acc.getAmount());
         cb.addAmount(acc.getCamt());
      }
      map1.put(cb.getSKey(), cb);
      payments.addAccount(cb);
      cb.setParent("PD");
      paymentsdetail.addAccount(cb);
      map1.put("R",recp);
      map1.put("P", payments);
      map1.put("RD",recpdetail);
      map1.put("PD", paymentsdetail);
      mainele.addAccount(recp);
      mainele.addAccount(payments);
      maineledetail.addAccount(recpdetail);
      maineledetail.addAccount(paymentsdetail);
      map1.put("RP", mainele);
      map1.put("RPD", maineledetail);
    }
    public Account getMainElement(){
     return mainele;
    }
    public Account getMainElementDetail(){
     return maineledetail;
    }

     public  AbstractTreeTableModel treetableModel(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false
            };
             @Override
         public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
         }


        @Override
         public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
         }

        public Object getValueAt(Object node, int column) {
            Account a=(Account) node;
            double amount=0;
            try{
                if(map1.containsKey(a.getSKey())){
                    Account at=map1.get(a.getSKey());
                     amount=at.getCamt()-at.getAmount();
                }/*else
              if(map1.containsKey(a.getSKey())){
                Account at=map1.get(a.getSKey());
                //amount=at.getCredit()-at.getDebit();
              }else{
                amount=a.getCamt()-a.getAmount();
              }*/
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1: if(amount>=0)
                          return Formats.ConvertDoubleToString(amount)+" Cr";
                        else
                          return Formats.ConvertDoubleToString(amount * -1)+" Dr";
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
                  return map1.get(a.getSKey()).getAccountList().get(index);
              else
                  return null;

        }

        public int getChildCount(Object parent) {
           Account a=(Account) parent;
           if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
             return map1.get(a.getSKey()).getAccountList().size();
           else
               return 0;
        }
        };
    }
      public  AbstractTreeTableModel treetableModel1(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.String.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false
            };
             @Override
         public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
         }


        @Override
         public boolean isCellEditable(Object node, int columnIndex) {
                return canEdit [columnIndex];
         }

        public Object getValueAt(Object node, int column) {
            Account a=(Account) node;
            double amount=0;
            int status=0;
            try{
              //  if(map1.containsKey(a.getSKey())){
               //     Account at=map1.get(a.getSKey());
               //      amount=at.getCamt()-at.getAmount();
              //  }
                /*else
              if(map1.containsKey(a.getSKey())){
                Account at=map1.get(a.getSKey());
                //amount=at.getCredit()-at.getDebit();
              }else{
                amount=a.getCamt()-a.getAmount();
              }*/
                Account at=map1.get(a.getSKey());
                Account at1=map1.get(a.getSKey());
                String parent=at.getParent();
                while(!parent.equals("RPD") && !parent.equals("")){
                   if(at.getParent().equals("RD")){
                       status=1;
                       break;
                   }else if(at.getParent().equals("PD")){
                       status=2;
                       break;
                   }
                   at1=map1.get(at1.getParent());
                   parent=at1.getParent();
                }
                if(status==0){
                  amount=at.getCamt()-at.getAmount();
                }else if(status==1){
                  amount=at.getAmount();
                } else if(status==2){
                  amount=at.getCamt();
                }

            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1: if(amount>=0)
                          return Formats.ConvertDoubleToString(amount);
                        else
                          return Formats.ConvertDoubleToString(amount * -1);
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
                  return map1.get(a.getSKey()).getAccountList().get(index);
              else
                  return null;

        }

        public int getChildCount(Object parent) {
           Account a=(Account) parent;
           if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null)
             return map1.get(a.getSKey()).getAccountList().size();
           else
               return 0;
        }
        };
    }
}

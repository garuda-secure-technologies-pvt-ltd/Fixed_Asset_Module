/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.AbstractTreeTableModel;
import com.openbravo.pos.clubmang.GeneralSettingInfo;
import com.openbravo.pos.clubmang.TreeTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class BalanceSheetCalculationLogic {
    private Date sdate;
    private Date edate;
    private AppView m_App;
    private Map<String ,Account> map1;
    private final static String[] HEADERS = {"Account","Amount"};
    private Account bshoz;
    private Account bsver;
     private boolean zeroIgnoreFlag=false;
    public BalanceSheetCalculationLogic(){
    }
    public BalanceSheetCalculationLogic(Date edate,AppView app){
           Map<String,GeneralSettingInfo> gs=LookupUtilityImpl.getInstance(null).getGeneralSettingsMap();
           GeneralSettingInfo sinfo=gs.get("Datestart");
           try {
            sdate = (Date) Formats.DATE.parseValue(sinfo.getValue());

           } catch (BasicException ex) {
            Logger.getLogger(BalanceSheetCalculationLogic.class.getName()).log(Level.SEVERE, null, ex);
           }
          // this.sdate=sdate;
           this.edate=edate;
           m_App=app;
           map1=new HashMap<String,Account>();
    }
    public void generateReport(DataLogicSales dlsales) throws BasicException{
          treestructure();
          generateBalanceSheet(dlsales);
    }
    public Map<String,Account> getMap(){
        return map1;
    }
    public Date getStartDate(){
       return sdate;
    }
    public Date getEndDate(){
       return edate;
    }
    private List<Account> CalculateFromTrailBalance() throws BasicException{
         List<Account> list=new PreparedSentence(m_App.getSession()
                                   , "SELECT AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS,COALESCE(SUM(AJ.DEBIT+AJ.CURDEBIT),0.0) AS DEBIT,COALESCE(SUM(AJ.CREDIT+AJ.CURCREDIT),0.0) AS CREDIT FROM ACCOUNTMASTER AM LEFT OUTER JOIN AJPERIODTOTALS AJ ON AM.ID=AJ.ACCOUNTID  AND AJ.EDATE>=? AND AJ.EDATE<=? "
                                   +"  GROUP BY AM.NAME,AM.ID,AM.SEARCHKEY,AM.PARENT,AM.LEVEL_,AM.OPERANDS ORDER BY AM.SEARCHKEY"
                                   , new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP})
                                   , new SerializerReadClass(Account.class)).list(new Object[]{sdate,edate});
        return list;
    }

     private void treestructure() throws BasicException{
      map1=new HashMap<String,Account>();
      List<String> templist=new ArrayList();
      List<Account> eacclist=CalculateFromTrailBalance();
      int flag=0,i=0;
      for(Account acc: eacclist){
         if(map1.containsKey(acc.getParent())){
             map1.put(acc.getSKey(), acc);
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             String tparent=null;
             Account at;
             flag=0;

          while(map1.containsKey(parent)){
             at=map1.get(parent);
             if(flag==0){
               at.getAccountList().add(acc);
               flag=1;
             }
            if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map1.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
                flag=1;
            }else if(flag==1){
                flag=2;
            } else if(flag==2){
                parent="";
              flag=3;
            } else if(flag==3){
                flag=4;
               break;
            }

          }
         }else{
             //if the account does not exist include it
             Account acc1=new Account();
             acc1.setSKey(acc.getParent());
             acc1.setParent("No Parent");
             //acc1.getAccountList().add(acc);
             map1.put(acc.getParent(), acc1);
             map1.put(acc.getSKey(), acc);
             String parent=acc.getParent();
             double debit=acc.getAmount();
             double credit=acc.getCamt();
             Account at;
             flag=0;
             String tparent=null;
             //loop through its parents to update the debit and credit
          while(map1.containsKey(parent)){
             at=map1.get(parent);
             if(flag==0){
                 at.getAccountList().add(acc);
                flag=1;
             }
          if(tparent==null || !tparent.equals(parent))
            at.addDC(debit, credit);
             tparent=parent;
            map1.put(parent, at);
            if(parent.contains(".")){
                parent=parent.substring(0, parent.lastIndexOf("."));
                flag=1;
            }else if(flag==1){
                flag=2;
            } else if(flag==2){
                parent="";
              flag=3;
            } else if(flag==3){
                flag=4;
               break;
            }

          }
            // System.out.println(acc.getparent());
             if(acc.getSign().equals("S"))
             {
                 if(!templist.contains(acc.getParent())){
                     templist.add(acc.getParent());
                 }
             }
         }
      }
      for(String temp:templist){
           Account at=map1.get(temp);
           Collections.sort(at.getAccountList());
           map1.put(temp, at);
      }
      //return map1;
    }

     private void generateBalanceSheet(DataLogicSales dlsales) throws BasicException{
         CalculationLogic cal=new CalculationLogic(map1, sdate, edate, m_App,dlsales);
         
         
         
         
         cal.generateIncomeAndExpenditureStatement();
         
          Account ie = null;
         
         if(cal.getIncomeAndExpenditureDiff()!=null)
         {
         
         System.out.println("Account = " + cal.getIncomeAndExpenditureDiff().toString() + " + acc Name= " + cal.getIncomeAndExpenditureDiff().getAccountName()+ " + acc ID= " + cal.getIncomeAndExpenditureDiff().getID() );

System.out.println("Operands = " + cal.getIncomeAndExpenditureDiff().getOperands() + " + Parent= " + cal.getIncomeAndExpenditureDiff().getParent()+ " + sKey= " + cal.getIncomeAndExpenditureDiff().getSKey() );
System.out.println("Sign = " + cal.getIncomeAndExpenditureDiff().getSign() + " + acc Size= " + cal.getIncomeAndExpenditureDiff().getAccountList().size()+ " + Amount= " + cal.getIncomeAndExpenditureDiff().getAmount() );
System.out.println("Amount 1 = " + cal.getIncomeAndExpenditureDiff().getAmount1() + " + Breakuplist= " + cal.getIncomeAndExpenditureDiff().getBreakUpList().size()+ " + Camt= " + cal.getIncomeAndExpenditureDiff().getCamt() );
System.out.println("child ps = " + cal.getIncomeAndExpenditureDiff().getChilPrintStatus() + " + SubAccList()= " + cal.getIncomeAndExpenditureDiff().getSubAccList().size()+ " + Camt= " + cal.getIncomeAndExpenditureDiff().getCamt() );
         ie=new Account(cal.getIncomeAndExpenditureDiff());
         ie.setDC(ie.getCamt(), ie.getAmount());
         }
         Account ci=new Account(cal.getClosingInventory());
         ci.setDC(ci.getCamt(), ci.getAmount());
         bshoz=new Account();
         bshoz.setName("Balance Sheet");
         bshoz.setSKey("BSH");
         bshoz.setParent("");
         bshoz.setDC(0, 0);
         bsver=new Account();
         bsver.setName("Balance Sheet");
         bsver.setSKey("BSV");
         bsver.setParent("");
         bsver.setDC(0, 0);
         Account accver=new Account();
         accver.setName("Sources Of Fund");
         accver.setSKey("SOF");
         Account atemp=new Account();
         atemp.setName("Share Holder Fund");
         atemp.setSKey("SHF");
         Account acc2=map1.get("3");
         acc2.getAccountList().clear();
         acc2.addAccountOnly(map1.get("3.1"));
         acc2.addAccountOnly(map1.get("3.2"));
         acc2.addAccountOnly(map1.get("3.3"));
         map1.put(acc2.getSKey(), acc2);
         atemp.addAccount(acc2);
         if(ie!=null)
         {
         atemp.addAccount(ie);
         }
         
         accver.addAccount(atemp);
         map1.put(atemp.getSKey(), atemp);
         atemp=new Account();
         atemp.setName("Loan Fund");
         atemp.setSKey("LF");
         acc2=map1.get("2.2");
         atemp.addAccount(acc2);
         map1.put(atemp.getSKey(), atemp);
         accver.addAccount(atemp);
         map1.put(accver.getSKey(), accver);
         bsver.addAccount(accver);
         accver=new Account();
         accver.setName("Application Of Funds");
         accver.setSKey("AOF");
         atemp=new Account();
         atemp.setName("Fixed Assets");
         atemp.setSKey("FA");
         acc2=map1.get("1.2");
         atemp.addAccount(acc2);
         map1.put(atemp.getSKey(), atemp);
         accver.addAccount(atemp);
         Account atemp1=new Account();
         atemp1.setName("Current Assets, Loan & Advances");
         atemp1.setSKey("CALA");
         acc2=map1.get("1.1");
         atemp1.addAccount(acc2);
         atemp1.addAccount(ci);
         //accver.addAccountOnly(atemp1);
         Account atemp2=new Account();
         atemp2.setName("Current Liablilities ");
         atemp2.setSKey("CLA");
         acc2=map1.get("2.1");
         atemp2.addAccount(acc2);
         accver.addAccountOnly(atemp1);
         accver.addAccountOnly(atemp2);
         atemp=new Account();
         atemp.setName("Net Current Assets");
         atemp.setSKey("NCA");
         atemp.addDC(atemp1.getAmount(), atemp1.getCamt());
         atemp.addDC(atemp2.getAmount(), atemp2.getCamt());
         accver.addAccount(atemp);
         
         bsver.addAccount(accver);
         map1.put(accver.getSKey(), accver);
         map1.put(bsver.getSKey(), bsver);
         //Assets
         Account acc=map1.get("1");
         acc.setParent("BS");
         acc.getAccountList().clear();
         acc.setDC(0.0, 0.0);
         Account subacc=map1.get("1.2");
         acc.addAccount(subacc);
         subacc=map1.get("1.1");
         acc.addAccount(subacc);
         acc.addAccount(ci);
         
         bshoz.addAccountOnly(acc);
         map1.put(acc.getSKey(), acc);
         //Liabilities and Owner equity
         Account acc1=new Account();
         acc1.setSKey("LOE");
         acc1.setName("Liabilities And Owner's Equity");
         acc=map1.get("3");
         //acc.setParent("LOE");
         acc1.addAccount(acc);
         acc=map1.get("2");
         acc.setParent("LOE");
         acc.getAccountList().clear();
         acc.setDC(0.0, 0.0);
         //Account totalL=new Account();
        // totalL.setSKey("TL");
        // totalL.setName("Totalk");
         subacc=map1.get("2.2");
       //  totalL.addDC(subacc.getAmount(), subacc.getCamt());
         acc.addAccount(subacc);
         subacc=map1.get("2.1");
        // totalL.addDC(subacc.getAmount(), subacc.getCamt());
         acc.addAccount(subacc);
         
        // acc.addAccountOnly(totalL);
         map1.put(acc.getSKey(), acc);
         acc1.addAccount(acc);
         if(ie!=null)
         {
         acc1.addAccount(ie);
         }
        // acc.addAccount(acc1);
         bshoz.addAccountOnly(acc1);
         map1.put(acc1.getSKey(), acc1);
         //bs.addAccount(acc);
         map1.put(bshoz.getSKey(), bshoz);
         //////////////////////////////////////////////pratima:to not display zero amount entries
    if(zeroIgnoreFlag){
      for(int h=0;h<2;h++){   
      if(h==0)
          removeZeroAmountEntries(bsver);
         else removeZeroAmountEntries(bshoz);
      }    

      
    }
         ////////////////////////////////////////////////ended by pratima
    }
    public Account getBalanceSheetAccHoz(){
        return bshoz;
    }
    public Account getBalanceSheetAccVer(){
        return bsver;
    }
    public void refresh(){
      for(Account acc:map1.values()){
         acc.setVisible(false);
         map1.put(acc.getSKey(), acc);
      }
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
                /*while(!parent.equals("RPD") && !parent.equals("")){
                   if(at.getParent().equals("RD")){
                       status=1;
                       break;
                   }else if(at.getParent().equals("PD")){
                       status=2;
                       break;
                   }
                   at1=map1.get(at1.getParent());
                   parent=at1.getParent();
                }*/
               

                    amount=a.getAmount()-a.getCamt();
                    
                /*if(status==0){
                  amount=at.getCamt()-at.getAmount();
                }else if(status==1){
                  amount=at.getAmount();
                } else if(status==2){
                  amount=at.getCamt();
                }*/
                a.setVisible(true);
                map1.put(a.getSKey(), a);
            }catch(Exception e){
                //new MessageInf(e).show(new JFrame());
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1://if(amount>=0)
                          return Formats.ConvertDoubleToString(amount);
                       // else
                       //   return Formats.ConvertDoubleToString(amount * -1)+"Dr";
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;
           
            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null){
               // a.setVisible(true);
                map1.put(a.getSKey(), a);
                return map1.get(a.getSKey()).getAccountList().get(index);
            }else
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

     public  AbstractTreeTableModel treetableModel(Object data) {
        return new AbstractTreeTableModel(data) {
        public int getColumnCount() {
           return HEADERS.length;
        }

        public String getColumnName(int column) {
            return HEADERS[column];
        }
         Class[] types = new Class [] {
                  TreeTableModel.class, java.lang.Double.class,TreeTableModel.class, java.lang.Double.class
            };
         boolean[] canEdit = new boolean [] {
                 true, false,true, false
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
                /*while(!parent.equals("RPD") && !parent.equals("")){
                   if(at.getParent().equals("RD")){
                       status=1;
                       break;
                   }else if(at.getParent().equals("PD")){
                       status=2;
                       break;
                   }
                   at1=map1.get(at1.getParent());
                   parent=at1.getParent();
                }*/


                    amount=a.getAmount()-a.getCamt();

                /*if(status==0){
                  amount=at.getCamt()-at.getAmount();
                }else if(status==1){
                  amount=at.getAmount();
                } else if(status==2){
                  amount=at.getCamt();
                }*/
                a.setVisible(true);
                map1.put(a.getSKey(), a);
            }catch(Exception e){
               e.printStackTrace();
            }
            switch(column){
                case 0: return a.getAccountName();
                case 1://if(amount>=0)
                          return Formats.ConvertDoubleToString(amount);
                       // else
                       //   return Formats.ConvertDoubleToString(amount * -1)+"Dr";
            }
         return null;
        }

        public Object getChild(Object parent, int index) {
           Account a=(Account) parent;

            if(a!=null && map1!=null && a.getSKey()!=null && map1.get(a.getSKey())!=null && map1.get(a.getSKey()).getAccountList()!=null){
               // a.setVisible(true);
                map1.put(a.getSKey(), a);
                return map1.get(a.getSKey()).getAccountList().get(index);
            }else
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
/////////////////pratima:to remove zero amount entries
  public void setZeroIgnoreFlag(boolean flag){
    if(flag)
    {
        this.zeroIgnoreFlag=true;
    }
    else {
        this.zeroIgnoreFlag=false;
    }
    }
  public void removeZeroAmountEntries(Account mainAcc){
          for(int i=0;i<bsver.getSubAccList().size();i++){
            Account subAcc=bsver.getSubAccList().get(i); 
      //  System.out.println("subAccat names"+subAccat.getAccountName());
            for(int j=0;j<subAcc.getSubAccList().size();j++){
               Account subOfsubAcc=subAcc.getSubAccList().get(j);
               if((subOfsubAcc.getCamt()-subOfsubAcc.getAmount())==0){
                    map1.remove(subOfsubAcc.getSKey());
                    subAcc.getSubAccList().remove(j);
                    j--;
               }else{
                 for(int k=0;k<subOfsubAcc.getSubAccList().size();k++){
                   Account subOfsubOfsubAcc=subOfsubAcc.getSubAccList().get(k);
                   if((subOfsubOfsubAcc.getCamt()-subOfsubOfsubAcc.getAmount())==0){
                        map1.remove(subOfsubOfsubAcc.getSKey());
                        subOfsubAcc.getSubAccList().remove(k);
                        k--;
                   }else{
                      for(int l=0;l<subOfsubOfsubAcc.getSubAccList().size();l++){
                        Account s_subOfsubOfsubAcc=subOfsubOfsubAcc.getSubAccList().get(l);
                        if((s_subOfsubOfsubAcc.getCamt()-s_subOfsubOfsubAcc.getAmount())==0){
                            map1.remove(s_subOfsubOfsubAcc.getSKey());
                            subOfsubOfsubAcc.getSubAccList().remove(l);
                            l--;
                        }else{
                             for(int m=0;m<s_subOfsubOfsubAcc.getSubAccList().size();m++){
                               Account subs_subOfsubOfsubAcc=s_subOfsubOfsubAcc.getSubAccList().get(m);
                               if((subs_subOfsubOfsubAcc.getCamt()-subs_subOfsubOfsubAcc.getAmount())==0){
                                    map1.remove(subs_subOfsubOfsubAcc.getSKey());
                                    s_subOfsubOfsubAcc.getSubAccList().remove(m);
                                    m--;
                                }else{
                                    for(int n=0;n<subs_subOfsubOfsubAcc.getSubAccList().size();n++){
                                        Account s_subs_subOfsubOfsubAcc=subs_subOfsubOfsubAcc.getSubAccList().get(n);
                                        if((s_subs_subOfsubOfsubAcc.getCamt()-s_subs_subOfsubOfsubAcc.getAmount())==0){
                                            map1.remove(s_subs_subOfsubOfsubAcc.getSKey());
                                            subs_subOfsubOfsubAcc.getSubAccList().remove(n);
                                            n--;
                                        }
                                    }
                                }
                             }               
                        }
                     }
                   }
                }
              }
           }   
        }
  
  }//method
     /////////////////////////////
}

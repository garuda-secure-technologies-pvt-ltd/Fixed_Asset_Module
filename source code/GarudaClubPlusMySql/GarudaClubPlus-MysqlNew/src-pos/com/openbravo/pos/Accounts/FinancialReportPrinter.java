/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.data.loader.Session;
import com.openbravo.pos.clubmang.DataSourceProvider;
import com.openbravo.pos.clubmang.JasperReportNew;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author swathi
 */
public class FinancialReportPrinter {
    private Map<String,Account> map;
    private JTree tree;
    private List<Account> printableAccountList;
    private int level=0;
    private AppView m_App;
    private boolean flag;
    private Date sdate;
    private Date edate;
    private String reportType;
    public FinancialReportPrinter(Map<String, Account> map, JTree tree,AppView app,boolean flag,Date sdate,Date edate,String reportType) {
        this.map = map;
        this.tree = tree;
        printableAccountList=new ArrayList<Account>();
        m_App=app;
        this.flag=flag;
        this.sdate=sdate;
        this.edate=edate;
        this.reportType=reportType;
    }
   

    public void generateJasperReport(){
      Account acc=(Account)  tree.getModel().getRoot();
      acc.setPrintLevel(level);
      printableAccountList.add(acc);
      List<Account> accList=new ArrayList<Account>();
      accList.addAll(acc.getAccountList());
      if(accList.size()>0)
         level++;
      for(Account acc1:accList){
        int flag1= computeChileAccount(acc1);
         if(flag1==1){
          Account newAcc=new Account();
          newAcc.setName(null);
          newAcc.setSKey(acc1.getSKey());
          newAcc.setDC(acc1.getAmount(), acc1.getCamt());
          newAcc.setPrintLevel(acc1.getPrintLevel() *-1);
          printableAccountList.add(newAcc);
         }
      }
       DataSourceProvider data1=new DataSourceProvider(printableAccountList);
       DataSource2 ds=new DataSource2(printableAccountList,flag);
       data1.setDataSource(ds);
        Map reportparam=new HashMap();
       Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession();
       reportparam.put("cname",s.getCompanyName() );
       reportparam.put("caddress", s.getCompanyAddress());
       reportparam.put("reportType", reportType);
       reportparam.put("sdate",sdate );
       reportparam.put("edate",edate );
       reportparam.put("curdate",new Date() );
       boolean flag1=true;
       if(reportType.equals("Balance Sheet"))
           flag1=true;
       else
           flag1=false;
       reportparam.put("balanceSheet",flag1 );
       JasperPrint jp=JasperReportNew.runReport(m_App, "./reports/com/openbravo/reports/financialReport.jrxml", reportparam, false,data1,true,"");
    }
    /*public void generateJasperReportIE(){
      Account acc=(Account)  tree.getModel().getRoot();
      acc.setPrintLevel(level);
      printableAccountList.add(acc);
      List<Account> accList=new ArrayList<Account>();
      accList.addAll(acc.getAccountList());
      if(accList.size()>0)
         level++;
      for(Account acc1:accList){
        int flag1= computeChileAccount(acc1);
         if(flag1==1){
          Account newAcc=new Account();
          newAcc.setName(null);
          newAcc.setSKey(acc1.getSKey());
          newAcc.setDC(acc1.getAmount(), acc1.getCamt());
          newAcc.setPrintLevel(acc1.getPrintLevel() *-1);
          printableAccountList.add(newAcc);
         }
      }
       DataSourceProvider data1=new DataSourceProvider(printableAccountList);
       DataSource2 ds=new DataSource2(printableAccountList,flag);
       data1.setDataSource(ds);
       generateJasperReport("Receipt And Payment Account", data1);
    }*/
    

    private int computeChileAccount(Account a){
     // if(a.getAccountList().size()>0)
        int flag1=0;
        int index=0;
        if(level==0){
         String aa="";
        }
        if(a.isVisible()){
            flag1=1;
       a.setPrintLevel(level);
       index=printableAccountList.size();
       printableAccountList.add(a);
       List<Account> accList=new ArrayList<Account>();
       accList.addAll(a.getAccountList());
       if(accList.size()>0)
         level++;
       int flag=0,flag2;
       for(Account acc:accList){
        
         flag2= computeChileAccount(acc);
         if(flag==0){
           flag=flag2;
         }
         
       }
        if(accList.size()>0){
          level--;
          if(flag==1 && level != 1){
          Account newAcc=new Account();
          newAcc.setName(null);
          newAcc.setSKey(a.getSKey());
          newAcc.setDC(a.getAmount(), a.getCamt());
          newAcc.setPrintLevel(a.getPrintLevel() *-1);
          printableAccountList.remove(index);
          a.setChilPrintStatus(true);
          printableAccountList.add(index,a);
          printableAccountList.add(newAcc);
          }
        }
    }
        return flag1;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.pos.forms.AppView;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class Report {
    private AccountMaster account;
    private String from;
    private String to;
    private AppView app;

    public Report(AccountMaster account, String from, String to,AppView app) {
        this.account = account;
        this.from = from;
        this.to = to;
        this.app=app;
    }

    public void getReport(){
           AccountReports r=new AccountReports(app);
           r.loadReport(from, to, account, account.getName()+"_"+from+"_"+to, "Ledger",false);
    }
}

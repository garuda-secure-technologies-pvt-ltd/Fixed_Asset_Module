/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.pos.Accounts.PurchaseJournalReport;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author swathi
 */
public class DataSourceForPurchaseJournal implements JRDataSource {

    private int m_nIdx;
    private List<PurchaseJournalReport.PurchaseJournalData> v;
    private String prevTid = "";
    private boolean flag;

    public DataSourceForPurchaseJournal() {
        this(new ArrayList<PurchaseJournalReport.PurchaseJournalData>());
        prevTid = "";
        flag = false;

    }

    public DataSourceForPurchaseJournal(List<PurchaseJournalReport.PurchaseJournalData> v) {
        m_nIdx = -1;
        this.v = v;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        PurchaseJournalReport.PurchaseJournalData curr = v.get(m_nIdx);

        if (curr == null) {
            return null;
        }


        if (sName.equals("date")) {
            o = curr.getDate().toString();
        } else if (sName.equals("transno")) {
            o = curr.getTransno();
        } else if (sName.equals("vendor")) {
            o = curr.getVendor();
        } else if (sName.equals("invno")) {
            o = curr.getInvno();
        } else if (sName.equals("dcno")) {
            o = curr.getDcno();
        } else if (sName.equals("docref")) {
            o = curr.getDocref();
        } else if (sName.equals("amount")) {
            o = curr.getAmount();
        } else if (sName.equals("pid")) {
            o = curr.getPid();
        } else if (sName.equals("pmid")) {
            o = curr.getPmid();
        } else if (sName.equals("tamount")) {//$F{transid}.equals("PJ")?$F{rate}.toString():($F{transtype}.equals("D")?$F{accamount}.toString():"")
            o = curr.getTamount();
        } else if (sName.equals("rate")) {
            o = curr.getRate();
        } else if (sName.equals("qty")) {
            o = curr.getQty();
        } else if (sName.equals("item")) {
            o = curr.getItem();
        } else if (sName.equals("id")) {
            o = curr.getPid();
        } else if (sName.equals("name")) {
            o = curr.getName();
        } else if (sName.equals("transid")) {
            o = curr.getTransid();
        } else if (sName.equals("transtype")) {
            o = curr.getTranstype();
        } else if (sName.equals("narration")) {
            o = curr.getNarration();
        } else if (sName.equals("accname")) {
            o = curr.getAccname();
        } else if (sName.equals("accamount")) {
            o = curr.getAccamount();
        }else if (sName.equals("accamounttext")) {
            o = curr.getAccamount().toString();
        } else if (sName.equals("totaltext")) {
            o = "Total";
        } else if (sName.equals("pname")) {
            o = "Product Name";
        } else if (sName.equals("quantity")) {
            o = "Quantity";
        } else if (sName.equals("rte")) {
            o = "Rate";
        } else if (sName.equals("sumamount")) {
            o = "Amount";
        } else if (sName.equals("zero")) {
            o =0.0;
        } else if ((sName.equals("two"))){
            o = 2.0;
        }

        return o;
    }
}

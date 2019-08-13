/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dev1
 */
public class Lib_Billinfo implements SerializableWrite, SerializableRead, Serializable{

            private String id;
            private int sl_no;
            private String mem_id;
            private String book_id;
            private Date issue_date;
            private Date to_be_retn_date;
            private Date return_date;
            private double due_charge;
            private boolean flag;
            private List <Lib_Billinfo> lines;  
            private CustomerInfoExt Customer;
             private List<PaymentInfo> payments;
    private List<TicketTaxInfo> taxes;
            
            
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSl_no() {
        return sl_no;
    }

    public void setSl_no(int sl_no) {
        this.sl_no = sl_no;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Date getTo_be_retn_date() {
        return to_be_retn_date;
    }

    public void setTo_be_retn_date(Date to_be_retn_date) {
        this.to_be_retn_date = to_be_retn_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public double getDue_charge() {
        return due_charge;
    }

    public void setDue_charge(double due_charge) {
        this.due_charge = due_charge;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public List<Lib_Billinfo> getLines() {
        return lines;
    }

    public void setLines(List<Lib_Billinfo> l) {
        lines = l;
    }
    public CustomerInfoExt getCustomer() {
        return Customer;
    }

    public void setCustomer(CustomerInfoExt value) {
        Customer = value;

    }
    public String getCustomerId() {
        return Customer != null ? Customer.getId() : null;
    }
     public List<PaymentInfo> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentInfo> l) {
        payments = l;
    }

    public void resetPayments() {
        payments = new ArrayList<PaymentInfo>();
    }

    public List<TicketTaxInfo> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TicketTaxInfo> l) {
        taxes = l;
    }

    public void resetTaxes() {
        taxes = null;
    }
    public double getTotal() throws BasicException {

        return (new Lib_IssueBooksPanel().getFineCharge());
    }
    
    @Override
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(3, mem_id);
        dp.setString(4, book_id);
        dp.setTimestamp(5, issue_date);
        dp.setTimestamp(6, to_be_retn_date);
        dp.setTimestamp(7, return_date);
        dp.setInt(2, sl_no);
        dp.setDouble(8, due_charge);
        dp.setBoolean(9, flag);
        dp.setString(2, getCustomerId());
    }

    @Override
    public void readValues(DataRead dr) throws BasicException {
       id= dr.getString(1);
       mem_id= dr.getString(3);
       book_id= dr.getString(5);
       issue_date=dr.getTimestamp(5);
       to_be_retn_date=dr.getTimestamp(6);
       return_date= dr.getTimestamp(7);       
        sl_no=dr.getInt(2);
        due_charge=dr.getDouble(8);
         flag= dr.getBoolean(9);
         Customer = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(dr.getString(2));
    }
    
}


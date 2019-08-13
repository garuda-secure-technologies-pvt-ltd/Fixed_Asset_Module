/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.util.Date;

/**
 *
 * @author user
 */
public class MinUsageCustomer implements SerializableRead {

    private String cid;
    private String name;
    private String searchkey;
    private String account;
    private String mobile;
    private Date lastBillDate;
    private double camt;
    private Double camtTotal = 0.0;
    private double limitTotal;
    private String narration;
    private Date billDate;
    private String usageDisplay;
    private double usageTotal;
    private Date enddate;
    private String newMinUsageRef;
    private String limitDisplay;
    private String chargeDisplay;
    private boolean deactivate = false;
    private String facilities;

    public boolean isDeactivate() {
        return deactivate;
    }

    public void setDeactivate(boolean deactivate) {
        this.deactivate = deactivate;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getChargeDisplay() {
        return chargeDisplay;
    }

    public void setChargeDisplay(String chargeDisplay) {
        this.chargeDisplay = chargeDisplay;
    }

    public String getLimitDisplay() {
        return limitDisplay;
    }

    public void setLimitDisplay(String limitDisplay) {
        this.limitDisplay = limitDisplay;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getNewMinUsageRef() {
        return newMinUsageRef;
    }

    public void setNewMinUsageRef(String newMinUsageRef) {
        this.newMinUsageRef = newMinUsageRef;
    }

    public String getUsageDisplay() {
        return usageDisplay;
    }

    public void setUsageDisplay(String usageDisplay) {
        this.usageDisplay = usageDisplay;
    }

    public double getUsageTotal() {
        return usageTotal;
    }

    public void setUsageTotal(double usageTotal) {
        this.usageTotal = usageTotal;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Boolean getBillit() {
        return billit;
    }

    public void setBillit(Boolean billit) {
        this.billit = billit;
    }
    private Boolean billit = true;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public double getLimitTotal() {
        return limitTotal;
    }

    public void setLimitTotal(double limitTotal) {
        this.limitTotal = limitTotal;
    }

    public Double getCamtTotal() {
        return camtTotal;
    }

    public void setCamtTotal(Double camtTotal) {
        this.camtTotal = camtTotal;
    }

    public double getCamt() {
        return camt;
    }

    public void setCamt(double camt) {
        this.camt = camt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getLastBillDate() {
        return lastBillDate;
    }

    public void setLastBillDate(Date lastBillDate) {
        this.lastBillDate = lastBillDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public void readValues(DataRead dr) throws BasicException {
        cid = dr.getString(1);
        name = dr.getString(2);
        searchkey = dr.getString(3);
        account = dr.getString(4);
        mobile = dr.getString(5);
        lastBillDate = dr.getTimestamp(6);
        enddate = dr.getTimestamp(7);
        newMinUsageRef = dr.getString(8);
        facilities = dr.getString(9);

    }
}

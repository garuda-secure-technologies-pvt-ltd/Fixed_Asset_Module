/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.pos.mant.WaiterInfo;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author swathi
 */
public class QticketInfo implements SerializableRead, SerializableWrite{
    
    private static final long  serialVersionUID=6L; 
    
    private String id;
    private CustomerInfoExt customer;
    private String waiter;
    private String place;
    private String floor;
    private String prCategory;
    private boolean billed;
    private String billref;
    private String createdby;
    private Date createddate;
    private List <QTicketLineInfo> lines;
     //warehouse changes -start
    private String warehouse;
     //warehouse changes -end
     //praveen:initiator changes---start
    private String initiator;
     //praveen:initiator changes---end

    /** Creates a new instance of SharedTicketInfo */
    public QticketInfo() {

        id = null; //increment
        customer = null;
        waiter = null;
        place = null;
        floor = null;
        prCategory = null;
        createdby = null;
    }

    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        customer = LookupUtilityImpl.getInstance(null).getDataLogicCustomers().getCustomerByID(dr.getString(2));
        waiter = dr.getString(3);
        place = dr.getString(4);
        floor = dr.getString(5);
        prCategory = dr.getString(6);
        billed = dr.getBoolean(7);
        billref = dr.getString(8);
        createdby = dr.getString(9);
        createddate = dr.getTimestamp(10);
         //warehouse changes -start
        warehouse = dr.getString(11);
         //warehouse changes -end
         //praveen:initiator changes---start
        initiator = dr.getString(12);
         //praveen:initiator changes---end
    }
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(2, getCustomerId());
        dp.setString(3, waiter);
        dp.setString(4, place);
        dp.setString(5, floor);
        dp.setString(6, prCategory);
        dp.setBoolean(7, billed);
        dp.setString(8, billref);
        dp.setString(9, createdby);
        dp.setTimestamp(10, createddate);
         //warehouse changes -start
        dp.setString(11, warehouse);
         //praveen:initiator changes---start
        dp.setString(12, initiator);

    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }
     //praveen:initiator changes---end
 //warehouse changes -end

    public String getId() {
        return id;
    }

    public void setID(String value) {
        id = value;
    }

    public String getCustomerId() {
        return customer != null ? customer.getId() : null;
    }

    public CustomerInfoExt getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfoExt value) {
        customer = value;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String value) {
        waiter = value;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String value) {
        place = value;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String value) {
        floor = value;
    }

    public String getprCategory() {
        return prCategory;
    }

    public void setprCategory(String value) {
        prCategory = value;
    }

    public boolean isBilled() {
        return billed;
    }

    public void setBilled(boolean value) {
        billed = value;
    }

    public String getBillref() {
        return billref;
    }

    public void setBillref(String value) {
        billref = value;
    }

    public String getCreatedBy() {
        return createdby;
    }

    public void setCreatedby(String value) {
        createdby = value;
    }

    public Date getCreatedDate() {
        return createddate;
    }

    public void setCreatedDate(Date value) {
        createddate = value;
    }

    public List<QTicketLineInfo> getLines() {
        return lines;
    }

    public void setLines(List<QTicketLineInfo> l) {
        lines = l;
    }

    public QTicketLineInfo getLine(int index){
        return lines != null ? lines.get(index) : null;
    }


    public int getLinesCount() {
        return lines != null ? lines.size() : 0;
    }


    public void addLine(QTicketLineInfo oLine) {
        oLine.setTicket(id, lines.size());
        lines.add(oLine);
    }

    public void insertLine(int index, QTicketLineInfo oLine) {
        lines.add(index, oLine);
        refreshLines();
    }

    public void setLine(int index, QTicketLineInfo oLine) {
        oLine.setTicket(id, index);
         lines.set(index, oLine);
    }

    public void removeLine(int index) {
       lines.remove(index);
        refreshLines();
    }

    private void refreshLines() {
        for (int i = 0; i < lines.size(); i++) {
            getLine(i).setTicket(id, i);
        }
    }

    public String printDate() {
        return Formats.TIMESTAMP.formatValue(getCreatedDate());
    }

    public String printWaiter() {
        WaiterInfo w = LookupUtilityImpl.getInstance(null).getWaiterMap().get(waiter);
        return w == null ? "" : w.getName();
    }

    public String printFloor() {
        FloorsInfo f = LookupUtilityImpl.getInstance(null).getFloorMap().get(floor);
        return f == null ? "" : f.getName();
    }
    public String printHost()
    {
       
     return    LookupUtilityImpl.getInstance(null).getAppView().getProperties().getHost();
    }
    public String printRole()
    {
        String x = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        return LookupUtilityImpl.getInstance(null).getRoleMap().get(x).toString();
    }
    
    public String printArticlesCount() {
        return Formats.DOUBLE.formatValue(new Double(getArticlesCount()));
    }

    public double getArticlesCount() {
        double dArticles = 0.0;
        QTicketLineInfo oLine;

        for (Iterator<QTicketLineInfo> i = lines.iterator(); i.hasNext();) {
            oLine = i.next();
            dArticles += oLine.getMultiply();
        }

        return dArticles;
    }

}

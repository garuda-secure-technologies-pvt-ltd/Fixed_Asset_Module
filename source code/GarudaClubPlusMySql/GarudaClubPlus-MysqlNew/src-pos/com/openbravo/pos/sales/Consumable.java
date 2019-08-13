/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Consumable  implements SerializableRead, Externalizable{
    private String sharedTicketId;
    private String deptName;
    private String createdBy;
    private Date createdDate;
    private int billType;
    private List<ConsumableProductList> cpList;   
   

    public void readValues(DataRead dr) throws BasicException {
        sharedTicketId = dr.getString(1);
         deptName=dr.getString(2);
         createdBy=dr.getString(3);
         createdDate=dr.getTimestamp(4);
         billType = dr.getInt(5);
         cpList = new ArrayList<ConsumableProductList>();
         
    }    

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(sharedTicketId);
       out.writeObject(deptName);
       out.writeObject(createdBy);
       out.writeObject(createdDate);
       out.writeObject(billType);
       out.writeObject(cpList);       
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
         sharedTicketId = (String) in.readObject();
        deptName = (String) in.readObject();
         createdBy = (String) in.readObject();
        createdDate = (Date) in.readObject();
        billType = (Integer) in.readObject();
        cpList = (List<ConsumableProductList>) in.readObject();       
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    
    
    public String getSharedTicketId() {
        return sharedTicketId;
    }

    public void setSharedTicketId(String sharedTicketId) {
        this.sharedTicketId = sharedTicketId;
    }
   
    

    public List<ConsumableProductList> getCpList() {
        return cpList;
    }

    public void setCpList(List<ConsumableProductList> cpList) {
        this.cpList = cpList;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    

}

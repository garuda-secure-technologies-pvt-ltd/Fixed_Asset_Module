package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;

public class CustomerPasswordInfo implements SerializableRead, SerializableWrite {

    private String custId;
    private String phoneNo;
    private String cName;

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void readValues(DataRead dr) throws BasicException {
        custId = dr.getString(1);
        phoneNo = dr.getString(2);
        cName = dr.getString(3);

    }

    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, custId);
        dp.setString(2, phoneNo);
        dp.setString(3, cName);

    }

}

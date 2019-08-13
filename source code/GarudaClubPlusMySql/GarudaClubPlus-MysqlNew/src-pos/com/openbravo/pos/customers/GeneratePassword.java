package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.util.Hashcypher;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GeneratePassword {

    
   private DataLogicFacilities dfac;

    public String generatePasswordToAll(Session session, String appUser, List<CustomerPasswordInfo> cust) throws BasicException {
        String msg = null;
        for (CustomerPasswordInfo c : cust) {
            generatePasswordTrans(session, appUser, c.getCustId(), c.getCName(), c.getPhoneNo());
        }
        msg = "success";
        return msg;
    }

    public String generatePasswordForIndividual(Session session, String appUser, String custid, String phone, String cname) throws BasicException {
        String msg = null;
        generatePasswordTrans(session, appUser, custid, phone, cname);
        msg = "success";
        return msg;
    }

    public void updateCustomers(Session session, String appuser, String custid, String password) throws BasicException {
        new PreparedSentence(session, "UPDATE CUSTOMERS SET PASSWORD=?,PASSWORDGENERATED=TRUE,PASSREST=FALSE WHERE ID=?",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{password, custid});
    }

    public void generatePasswordTrans(Session session, String appUser, String memid, String mobile, String name) throws BasicException {
        java.sql.Date d = new java.sql.Date(new Date().getTime());
        String password1 = UUID.randomUUID().toString().substring(0, 8);
        System.out.println(password1);
        String password = Hashcypher.hashString(password1);
        new PreparedSentence(session, "INSERT INTO PASSWORDLOG(ID,CUSTID,PASSWORD,CRDATE,CREATEDBY) VALUES(?,?,?,?,?)",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{UUID.randomUUID().toString(), memid, password, d, appUser});
        updateCustomers(session, appUser, memid, password);
        String msg = "Dear Member,\rYour new password is " + password1 +" thank u";
        if (mobile != null && mobile.toString().trim().length() == 10) {            
            dfac.updatetosendMsg(msg, memid, mobile, 2);//updatetosendMsg(msg, memid, mobile.toString(), 2);
        }

    }
}

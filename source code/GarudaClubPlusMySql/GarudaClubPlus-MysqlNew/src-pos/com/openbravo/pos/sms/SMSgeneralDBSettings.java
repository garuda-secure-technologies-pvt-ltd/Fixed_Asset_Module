/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Session;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryData;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.Billpage;
import com.openbravo.pos.util.ICallBack;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Akash
 */
public class SMSgeneralDBSettings extends BeanFactoryDataSingle
{
    private Session session;
    public static final String SMS_QT_ID =  "sms-101";
    public static final String SMS_BILL_ID =  "sms-102";
    public static final String SMS_FACILITY_ID =  "sms-103";
    public static final String SMS_GUEST_CHRG_DEBIT_ID =  "sms-104";
    public static final String SMS_GUEST_CHRG_CASH_ID =  "sms-105";
    public static final String SMS_CREDIT_CONF_ID = "sms-106";
    
    public static final String SMS_QT_NAME =  "Master message for QT";
    public static final String SMS_BILL_NAME =  "Mastre message for BILL";
    public static final String SMS_FACILITY_NAME =  "Master message for Account";
    public static final String SMS_GUEST_CHRG_DEBIT_NAME =  "Master message for Guest charges - A/c debit";
    public static final String SMS_GUEST_CHRG_CASH_NAME =  "Master message for Guest Charges - Cash";
     public static final String SMS_CREDIT_CONF_NAME =  "Master message for credit confirmation";
    
    public static final String SMS_BILL_KEY = "###BILLNO###";
    public static final String SMS_DTM_KEY = "###DTM###";
    public static final String SMS_FACILITY_KEY = "###FCLTNO###";
    public static final String SMS_ACCOUNT_KEY = "###ACTNO###";
    public static final String SMS_WHAREHOUSE_NAME_KEY = "###WAREHOUSE###";
    public static final String SMS_ROLE_KEY = "###ROLE###";
    public static final String SMS_MEMBER_NAME_KEY = "###MEMNAME###";
    public static final String SMS_MEMBER_NO_KEY = "###MEMNO###";
    public static final String SMS_TOT_AMOUNT_KEY = "###TOTAMOUNT###";
    public static final String SMS_CUST_BAL_BEFORE = "###CUSTBALBEFORE###";
    public static final String SMS_CUST_BAL_AFTER = "###CUSTBALAFTER###";
    public static final String SMS_DUE_DATE_KEY = "###DUEDATE###";
    
    private List<SmsMasterInfo> smsMasterClassList; 
    
    @Override
    public void init(Session s) 
    {
        this.session = s;
    }
    
    public void setSMSflag(final String smsMasterId ,final String smsMasterName,final String active, final String message, final List<String> facilityList , final ICallBack iCallback)
    {
        try 
        {
            Transaction t = new Transaction(session) 
            {
                public Object transact() throws BasicException 
                {

                    // delete facility linked with master and insert it again
                    deleteLinkedFacility(smsMasterId);
                    if(facilityList != null && facilityList.size() > 0)
                    {
                         insertFacilityIds(smsMasterId,facilityList);
                    }
                        
                    // query to store if need to send SMS or not
                    if( new PreparedSentence(session
                            , "UPDATE SMS_MASTER SET ACTIVE=? , MESSAGE = ? WHERE ID=?"
                            , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}))
                            .exec(new Object[]{active, message, smsMasterId}) <= 0)
                    {

                        new PreparedSentence(session
                                , "INSERT INTO SMS_MASTER(ID,NAME,ACTIVE,MESSAGE) VALUES(?,?,?,?)"
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING, Datas.STRING}))
                                .exec(new Object[]{smsMasterId, smsMasterName, active, message });

                    }

                    iCallback.onSuccess();
                    return null;

                }
            };
            t.execute();
        }
        catch (BasicException ex) 
        {
            iCallback.onError();
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // get facility list for QT , bill , and sharedtickets
    public List getAllFacilityList()
    {
        List<Object> fac_list = new ArrayList<Object>();
        try 
        {
            fac_list  = (List<Object>) new StaticSentence(session, "SELECT NAME FROM FACILITY WHERE ACTIVE=1 ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
            fac_list.add(0,"ALL");
            return fac_list;
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean insertFacilityIds(String smsMasterID, List<String> fac_list)
    {
        String ids = null;
        try 
        {
            // Insert linked facility to facility sms master
            
            for(int i=0; i<fac_list.size();i++)
            {
                if(fac_list.get(i).equals("ALL"))
                {
                    new PreparedSentence(session
                    , "INSERT INTO SMS_MASTER_FAC(ID, SMS_MASTER_ID, FACILITY_ID) VALUES( ? , ? , ? )"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}))
                        .exec(new Object[]{UUID.randomUUID().toString(), smsMasterID, "ALL"});
                }
                else
                {
                    new PreparedSentence(session
                    , "INSERT INTO SMS_MASTER_FAC(ID, SMS_MASTER_ID, FACILITY_ID) VALUES( ? , ? , (SELECT ID FROM FACILITY WHERE NAME = ? AND ACTIVE=1) )"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}))
                        .exec(new Object[]{UUID.randomUUID().toString(), smsMasterID, fac_list.get(i)});
                }
                
            }
            return true;
            
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
      
    }
    
    public void deleteLinkedFacility(String smsMasterID)
    {
        try 
        {
            // delete existing sms ids from sms_master_fac 
            
            new PreparedSentence(session
                    , "DELETE FROM  SMS_MASTER_FAC WHERE SMS_MASTER_ID=?"
                    , new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{smsMasterID});
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SmsMasterInfo> getSmsMasterList()
    {
        try 
        {
            smsMasterClassList = new StaticSentence(session,
                    " SELECT ID , NAME, MESSAGE, ACTIVE FROM SMS_MASTER ",
                    SerializerWriteString.INSTANCE  ,
                    new SerializerReadClass(SmsMasterInfo.class)).list();
            
            if(smsMasterClassList.size() > 0)
            {
                for(int i=0; i<smsMasterClassList.size(); i++)
                {
                   
                    List<String> all_list  = (List<String>) new StaticSentence(session, "SELECT FACILITY_ID FROM SMS_MASTER_FAC WHERE SMS_MASTER_ID=? ", 
                            SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE)
                            .list(smsMasterClassList.get(i).getsmsId());
                    if(all_list.contains("ALL"))
                    {
                        smsMasterClassList.get(i).setFacilityList(all_list);
                    }
                    else
                    {
                        List<String> fac_list  = (List<String>) new StaticSentence(session, "SELECT NAME FROM FACILITY WHERE ID IN (SELECT FACILITY_ID FROM SMS_MASTER_FAC WHERE SMS_MASTER_ID=?) ", 
                            SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE)
                            .list(smsMasterClassList.get(i).getsmsId());
                        smsMasterClassList.get(i).setFacilityList(fac_list);
                    }
                }
            }
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return smsMasterClassList;
    }
    
    
    
    public static class SmsMasterInfo implements SerializableRead,IKeyed
{

        private String smsId;
        private String smsName;
        private String message;
        private String active;
        private List<String> facilityList;
         

        public String getsmsId() 
        {
            return smsId;
        }

        public void setsmsId(String smsId) 
        {
            this.smsId = smsId;
        }

        public String getSmsName() 
        {
            return smsName;
        }

        public void setSmsName(String smsName) 
        {
            this.smsName = smsName;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public String getActive() 
        {
            return active;
        }
        
        public List<String> getFacilityList()
        {
            return facilityList;
        }
        public void setFacilityList(List<String> facList)
        {
            this.facilityList = facList;
        }
        
        
        
        @Override
        public void readValues(DataRead dr) throws BasicException 
        {

            smsId=dr.getString(1);
            smsName=dr.getString(2);
            message=dr.getString(3);
            active=dr.getString(4);
        }

        @Override
        public Object getKey() {
             return this;
        }
    }
    
    
    
    public boolean getSMSvalue(String id)
    {
        Object[] obj;
        try 
        {
            obj = (Object[]) new StaticSentence(session, "SELECT ACTIVE FROM SMS_MASTER WHERE ID=? ", SerializerWriteString.INSTANCE , new SerializerReadBasic(new Datas[]{Datas.INT}) ).find(id);
            if((obj != null) && obj[0] != null  && (int)obj[0] == 1) 
            {
                return true;
            }
            else 
                return false;
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getMessage(String id)
    {
        Object[] obj;
        try 
        {
            obj = (Object[]) new StaticSentence(session, "SELECT MESSAGE FROM SMS_MASTER WHERE ID=? ", SerializerWriteString.INSTANCE , new SerializerReadBasic(new Datas[]{Datas.STRING}) ).find(id);
            if((obj != null) && obj[0] != null) 
            {
                return obj[0].toString();
            }
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    
    public void insertSMStoActiveMsgTable(String message, String mobile, String memid)
    {
        try 
        {
            String id = UUID.randomUUID().toString();
            new PreparedSentence(session, "INSERT INTO activemsgtable(ID,Message,SENDTO,PRIORITY,CNT) VALUES (?,?,?,?,?) ", 
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.INT}))
                    .exec(new Object[]{id, message, mobile, 1, 0});
            
            
            new PreparedSentence(session, "INSERT INTO AUTOMSG(ID,MESSAGE,SENTDATE,MEMID) VALUES (?,?,?,?) ", 
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING}))
                    .exec(new Object[]{id, message, new Date(), memid});
            
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    // check for facility enabled
    public boolean isFacilityEnable(String smsID, String facilityID)
    {
        Object[] obj;
        try 
        {
            obj = (Object[]) new StaticSentence(session, "SELECT FACILITY_ID FROM SMS_MASTER_FAC WHERE SMS_MASTER_ID=? AND  ( FACILITY_ID=? OR FACILITY_ID = 'ALL')" , 
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}) , 
                    new SerializerReadBasic(new Datas[]{Datas.STRING}) ).find(new Object[]{smsID,facilityID});
            if((obj != null) && obj[0] != null && obj[0].toString().trim().length() > 0 ) 
            {
                return true;
            }
            else 
                return false;
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getCustIdFromGuestID(CustomerInfoExt guestCust)
    {
        String[] strArr = guestCust.getId().split("#");
        if(strArr != null && strArr.length > 0)
        {
            return strArr[0].toString();
        }
        return null;
    }
    
    public String getFacilityId(String wareHouse)
    {
        try 
        {
            Object[] obj = (Object[]) new StaticSentence(session,
                    "SELECT FACILITY FROM LOCATIONS WHERE ID = ? ", 
                    SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING}))
                    .find(wareHouse);
            if (obj == null)
                return "";
            else
                return obj[0].toString();
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(SMSgeneralDBSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // get role name 
    
    public String getRoleName()
    {
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        Object[] roleObj;
        try 
        {
            roleObj = (Object[]) new StaticSentence(m_App.getSession(), "SELECT NAME FROM ROLES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(m_App.getAppUserView().getUser().getRole());
            if (roleObj != null && roleObj[0] != null) 
            {
                return roleObj[0].toString();
            }
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getFacDueDate(String FacID)
    {
        AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
        Object[] facDueDateID;
        try 
        {
            facDueDateID = (Object[]) new StaticSentence(m_App.getSession(), " SELECT DUEPERIOD FROM facility F WHERE F.ID=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(FacID);
            
            if (facDueDateID != null && facDueDateID[0] != null) 
            {
                return facDueDateID[0].toString();
            }
        } 
        catch (BasicException ex) 
        {
            Logger.getLogger(Billpage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;

/**
 *
 * @author swathi
 */
public class FacilityManagerLogic {
    private AppView m_App;

    public FacilityManagerLogic(AppView m_App) {
        this.m_App = m_App;
    }

    public final void AssignFacilityToMember(Object[] Values) throws BasicException{
         new PreparedSentence(m_App.getSession()//praveen:changed status to status_
                   ,"INSERT INTO MEMFACILITYUSAGE(ID,MEMNO,ACTIVE,FACILITYTYPE,STATUS_,SDATE,CREATEDBY,CRDATE) VALUES (?,?,?,?,?,?,?,?)"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP})).exec(Values);
    }
    public final void AssignFacilityToMemberDependent(Object[] Values) throws BasicException{
        new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO MEMFACILITYUSAGE(ID,MEMNO,ACTIVE,FACILITYTYPE,STATUS_,SDATE,CREATEDBY,CRDATE,USERID) VALUES (?,?,?,?,?,?,?,?,?)"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.INT,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
                   ).exec(Values);
    }
    public final void sendStartRequest(Object[] Values) throws BasicException{
        new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO FACILITYMANAGER(ID,MFUID,TYPE_,FROM_,INITIATEDBY,INITIATEDDATE) VALUES (?,?,?,?,?,?)"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP})
                   ).exec(Values);
        UpdateMemFacilityManagerReference(Values[0].toString(),Values[1].toString());
    }
    
    public final void sendStopRequest(Object[] Values) throws BasicException{
        new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO FACILITYMANAGER(ID,MFUID,TYPE_,FROM_,INITIATEDBY,INITIATEDDATE,MESSAGE) VALUES (?,?,?,?,?,?,?)"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
                   ).exec(Values);
        UpdateMemFacilityManagerReference(Values[0].toString(),Values[1].toString());
    }
    public final void sendSuspendRequest(Object[] Values) throws BasicException{
        new PreparedSentence(m_App.getSession()
                   ,"INSERT INTO FACILITYMANAGER(ID,MFUID,TYPE_,FROM_,TO_,INITIATEDBY,INITIATEDDATE,MESSAGE) VALUES (?,?,?,?,?,?,?,?)"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.INT,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
                   ).exec(Values);
        UpdateMemFacilityManagerReference(Values[0].toString(),Values[1].toString());
    }
    public final void UpdateMemFacilityManagerReference(String fmid,String mfuid) throws BasicException{
         new PreparedSentence(m_App.getSession()
                   ,"UPDATE MEMFACILITYUSAGE SET FACMANGREF=? WHERE ID=?"
                   ,new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                   ).exec(new Object[]{fmid,mfuid});
    }
 //   public final void UpdateMemFacilityManagerReference(Object facmangid,Object mfuid){

  //  }
}

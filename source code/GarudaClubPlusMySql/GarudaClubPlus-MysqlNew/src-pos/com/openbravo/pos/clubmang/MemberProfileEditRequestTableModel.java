/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
public class MemberProfileEditRequestTableModel {
     private final static List<PeditRequest> requestList=new ArrayList<PeditRequest>();
     public static MemberProfileEditRequestTableModel emptyInstance(){
           MemberProfileEditRequestTableModel tmodel=new MemberProfileEditRequestTableModel();
           return tmodel;
     }
     private static class PeditRequest implements SerializableRead{
        private Timestamp date;
        private String mname;
        private String mskey;
        private String initiator;

        public void readValues(DataRead dr) throws BasicException {
            date=dr.getTimestamp(1);
            mname=dr.getString(2);
           // mskey=dr.gets
        }

     }
}

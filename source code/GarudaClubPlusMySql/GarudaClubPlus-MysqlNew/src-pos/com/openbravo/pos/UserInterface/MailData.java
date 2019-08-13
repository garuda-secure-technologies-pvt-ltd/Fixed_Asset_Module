/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.UserInterface;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.util.Date;


public class MailData implements SerializableRead{
     private String from;
     private Date date;
     private String subject;
     private String body;
     private String attachloc;

     public String getFrom(){
       return from;
     }
     public String getSubject(){
       return subject;
     }
     public String getBody(){
       return body;
     }
     public Date getdate(){
       return date;
     }
     public String getAttachLocation(){
        return attachloc;
     }
    public void readValues(DataRead dr) throws BasicException {
        from=dr.getString(1);
        date=dr.getTimestamp(4);
        subject=dr.getString(2);
        body=dr.getString(3);
        attachloc=dr.getString(5);
    }
}

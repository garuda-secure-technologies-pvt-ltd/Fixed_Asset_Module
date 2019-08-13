

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;
//import java.sql.Timestamp;

/**
 *
 * @author adrianromero
 */
public class SharedTicketInfo implements SerializableRead, SerializableWrite {
    
    private String id;
    private String name;
   // private Timestamp lqttime;
    
    /** Creates a new instance of SharedTicketInfo */
    public SharedTicketInfo() {
    }
    
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        name = dr.getString(2);
     //   lqttime=dr.getTimestamp(3);
    }   
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(2, name);
      //  dp.setTimestamp(3, lqttime);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
   // public Timestamp getLqtTime(){
  //   return lqttime;
  //  }
}

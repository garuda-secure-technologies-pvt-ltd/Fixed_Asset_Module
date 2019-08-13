

package com.openbravo.pos.sales;

import java.util.List;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.ticket.TicketInfo;
//import java.sql.Timestamp;
//import java.util.Date;
import java.util.UUID;

/**
 *
 * @author adrianromero
 */
public class DataLogicReceiptsstd1 extends BeanFactoryDataSingle {
    
    private Session s;
    
    /** Creates a new instance of DataLogicReceipts */
    public DataLogicReceiptsstd1() {
    }
    
    public void init(Session s){
        this.s = s;
    }
     
    public final TicketInfo getSharedTicket(String Id,String counter,int type) throws BasicException {
        
        if (Id == null) {
            return null; 
        } else {
            Object[]record = (Object[]) new StaticSentence(s
                    , "SELECT CONTENT FROM SHAREDTICKETS WHERE CID = ? AND COUNTER=?"
                    , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING})
                    , new SerializerReadBasic(new Datas[] {Datas.SERIALIZABLE})).find(new Object[]{Id,counter});
            return record == null ? null : (TicketInfo) record[0];
           // return (TicketInfo) record[0];
        }
    } 
    
    public final List<SharedTicketInfo> getSharedTicketList( String counter,int type) throws BasicException {
        
        return (List<SharedTicketInfo>) new StaticSentence(s
                , "SELECT CID, NAME FROM SHAREDTICKETS WHERE COUNTER=? and type=? ORDER BY NAME"
                ,  new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
                , new SerializerReadClass(SharedTicketInfo.class)).list(new Object[]{counter,type});
    }
    
    public final List<SharedTicketInfo> getConsumableSharedTicketList( String counter,int type) throws BasicException {
        
        return (List<SharedTicketInfo>) new StaticSentence(s
                , "SELECT ID, NAME FROM SHAREDTICKETS WHERE COUNTER=? and type=? and name is  null ORDER BY NAME"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
                , new SerializerReadClass(SharedTicketInfo.class)).list(new Object[]{counter,type});
    }
	
	public final List<SharedTicketInfo> getConsumableSharedTicketList1( String counter,int type) throws BasicException {

        return (List<SharedTicketInfo>) new StaticSentence(s
                , "SELECT ID, NAME FROM SHAREDTICKETS WHERE COUNTER=? and type=? and name is not null ORDER BY NAME"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
                , new SerializerReadClass(SharedTicketInfo.class)).list(new Object[]{counter,type});
    }
    
    public final void updateSharedTicket(final String id, final TicketInfo ticket,String counter) throws BasicException {
         
        Object[] values = new Object[] {id, ticket.getCustomer().getSearchkey(), ticket,counter};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.SERIALIZABLE,Datas.STRING};
        new PreparedSentence(s
                , "UPDATE SHAREDTICKETS SET NAME = ?, CONTENT = ? WHERE CID = ? AND COUNTER=?"
                , new SerializerWriteBasicExt(datas, new int[] {1, 2, 0,3})).exec(values);

    }
  /* public final void updateLastQtTimeOfSharedTicket(Date date,final String id,String counter) throws BasicException {

        Object[] values = new Object[] {date,id, counter};
        Datas[] datas = new Datas[] {Datas.TIMESTAMP, Datas.STRING,Datas.STRING};
        new PreparedSentence(s
                , "UPDATE SHAREDTICKETS SET LASTQTTIME = ? WHERE CID = ? AND COUNTER = ?"
                , new SerializerWriteBasicExt(datas, new int[] { 0, 1, 2})).exec(values);

    }*/

     public final int checkSharedTicket(final String id, final TicketInfo ticket,final String counter) throws BasicException {

     //   Object[] values = new Object[] {id, ticket.getCustomer().getSearchkey(), ticket,counter};
     //   Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.SERIALIZABLE,Datas.STRING};
       int count=0;
       Object[] o= (Object[])new  StaticSentence(s
            , "SELECT ID FROM SHAREDTICKETS WHERE CID=? AND COUNTER=?"

            , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING})
            ,new SerializerReadBasic(new Datas[] {Datas.STRING})).find(new Object[]{id,counter});
       if(o!=null)
           return o.length;
      return count;
    }
     //praveen:initiator changes---start
    public final void insertSharedTicket(final String id, final TicketInfo ticket,final String counter,final String initiator,final int type) throws BasicException {
        
        Object[] values = new Object[] {UUID.randomUUID().toString(),id, ticket.getCustomer().getSearchkey(), ticket,counter,initiator,type};
        Datas[] datas = new Datas[] {Datas.STRING,Datas.STRING, Datas.STRING, Datas.SERIALIZABLE,Datas.STRING,Datas.STRING,Datas.INT};
        
        new PreparedSentence(s
            , "INSERT INTO SHAREDTICKETS (ID,CID, NAME,CONTENT,COUNTER,INITIATOR,TYPE) VALUES (?,?,?,?,?,?,?)"
            , new SerializerWriteBasicExt(datas, new int[] {0, 1, 2,3,4,5,6})).exec(values);
    }
     //praveen:initiator changes---end
    
    public final void deleteSharedTicket(final String id,final String counter) throws BasicException {

        Object[] values = new Object[] {id,counter};
        Datas[] datas = new Datas[] {Datas.STRING,Datas.STRING};
        new StaticSentence(s
            , "DELETE FROM SHAREDTICKETS WHERE CID = ? AND COUNTER = ?"
            , new SerializerWriteBasicExt(datas,new int[]{0,1})).exec(values);
    }   
    
    //SHIV  CREATED
      public final List<SharedTicketInfo> getSharedTicketListstd( String counter,int type) throws BasicException {
        
        return (List<SharedTicketInfo>) new StaticSentence(s
                , "SELECT CID, NAME FROM SHAREDTICKETS WHERE COUNTER=? and type=? ORDER BY NAME"
                ,  new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.INT})
                , new SerializerReadClass(SharedTicketInfo.class)).list(new Object[]{counter,type});
    }
      
      
      public final void deleteSharedTicketstd(final String id,final String counter) throws BasicException {

        Object[] values = new Object[] {id, counter};
        Datas[] datas = new Datas[] {Datas.STRING,Datas.STRING};
        new StaticSentence(s
            , "DELETE FROM SHAREDTICKETS WHERE CID = ? AND COUNTER = ?"
            , new SerializerWriteBasicExt(datas,new int[]{0,1})).exec(values);
    }  
      
      
       public final void insertSharedTicketstd(final String id, final CustomerInfoExt customer,final String counter,final String initiator,final int type) throws BasicException {
        
        Object[] values = new Object[] {UUID.randomUUID().toString(),id,customer.getSearchkey(),null,counter,initiator,type};
        Datas[] datas = new Datas[] {Datas.STRING,Datas.STRING, Datas.STRING, Datas.SERIALIZABLE,Datas.STRING,Datas.STRING,Datas.INT};
        
        new PreparedSentence(s
            , "INSERT INTO SHAREDTICKETS (ID,CID, NAME,CONTENT,COUNTER,INITIATOR,TYPE) VALUES (?,?,?,?,?,?,?)"
            , new SerializerWriteBasicExt(datas, new int[] {0, 1, 2,3,4,5,6})).exec(values);
    }
       
       
        public final TicketInfo getSharedTicketstd(String Name,String counter,int type) throws BasicException {
        
        if (Name == null) {
            return null; 
        } else {
            Object[]record = (Object[]) new StaticSentence(s
                    , "SELECT CONTENT FROM SHAREDTICKETS WHERE NAME = ? AND COUNTER=? and type=?"
                    , new SerializerWriteBasic(new Datas[] {Datas.STRING,Datas.STRING,Datas.INT})
                    , new SerializerReadBasic(new Datas[] {Datas.SERIALIZABLE})).find(new Object[]{Name,counter,type});
            return record == null ? null : (TicketInfo) record[0];
           // return (TicketInfo) record[0];
        }
    } 
      
}

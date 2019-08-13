

package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
//import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SentenceExecTransaction;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.BeanFactoryDataSingle;

/**
 *
 * @author adrianromero
 */
public class DataLogicCustomers extends BeanFactoryDataSingle {
    
    protected Session s;
    private TableDefinition tcustomers;
    private static Datas[] customerdatas = new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.INT, Datas.BOOLEAN, Datas.STRING};
    
    public void init(Session s){
        
        this.s = s;
        tcustomers = new TableDefinition(s
            , "CUSTOMERS"
            , new String[] { "ID", "TAXID", "SEARCHKEY", "NAME", "NOTES", "VISIBLE", "CARD"
                           , "FIRSTNAME", "LASTNAME", "EMAIL", "PHONE", "PHONE2", "FAX"
                           , "ADDRESS", "ADDRESS2", "POSTAL", "CITY", "REGION", "COUNTRY"
                           , "TAXCATEGORY","IMAGE","ACCOUNT","MEMTYPE","SOWO"
                           , "MOBILE","SERVICETAXCAT","ENTTAXCAT","SIGNATURE","SPONSOR1","SPONSOR2","SPONSOR3"
                           , "JOINDATE","TERDATE","DOB","OPENINGBALANCE","REFERENCEID","CONFIRMDATE","EFFECTIVEDATE"}
            , new String[] { "ID", AppLocal.getIntString("label.taxid"), AppLocal.getIntString("label.searchkey"), AppLocal.getIntString("label.name"), AppLocal.getIntString("label.notes"), "VISIBLE", "CARD"
                           , AppLocal.getIntString("label.firstname"), AppLocal.getIntString("label.lastname"), AppLocal.getIntString("label.email"), AppLocal.getIntString("label.phone"), AppLocal.getIntString("label.phone2"), AppLocal.getIntString("label.fax")
                           , AppLocal.getIntString("label.address"), AppLocal.getIntString("label.address2"), AppLocal.getIntString("label.postal"), AppLocal.getIntString("label.city"), AppLocal.getIntString("label.region"), AppLocal.getIntString("label.country")
                           , "TAXCATEGORY","IMAGE","ACCOUNT","MEMTYPE","SOWO"
                           , "MOBILE","SERVICETAXCAT","ENTTAXCAT","SIGNATURE","SPONSOR1","SPONSOR2","SPONSOR3"
                           , "JOINDATE","TERDATE","DOB","OPENINGBALANCE","REFERENCEID","CONFIRMDATE","EFFECTIVEDATE"}
            , new Datas[] { Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING
                          , Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING
                          , Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING
                          , Datas.STRING,Datas.IMAGE,Datas.STRING,Datas.STRING,Datas.STRING
                          , Datas.STRING,Datas.STRING,Datas.STRING,Datas.IMAGE,Datas.STRING,Datas.STRING,Datas.STRING
                          , Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.TIMESTAMP,Datas.DOUBLE,Datas.STRING,Datas.TIMESTAMP,Datas.TIMESTAMP}
            , new Formats[] { Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN, Formats.STRING
                            , Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING
                            , Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING
                            , Formats.STRING,Formats.NULL,Formats.STRING,Formats.STRING,Formats.STRING
                            , Formats.STRING,Formats.STRING,Formats.STRING,Formats.NULL,Formats.STRING,Formats.STRING,Formats.STRING
                            , Formats.TIMESTAMP,Formats.TIMESTAMP,Formats.TIMESTAMP,Formats.DOUBLE,Formats.STRING,Formats.TIMESTAMP,Formats.TIMESTAMP}

            , new int[] {0}
        );   
        
    }
    
    // CustomerList list
    public SentenceList getCustomerList() {
        return new StaticSentence(s
            , new QBFBuilder("SELECT ID, TAXID, SEARCHKEY, NAME,MOBILE FROM CUSTOMERS WHERE VISIBLE = TRUE AND ?(QBF_FILTER) ORDER BY  SEARCHKEY", new String[] {"TAXID", "SEARCHKEY", "NAME"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        CustomerInfo c = new CustomerInfo(dr.getString(1));
                        c.setTaxid(dr.getString(2));
                        c.setSearchkey(dr.getString(3));
                        c.setName(dr.getString(4));
                        c.setMobile(dr.getString(5));
                       // c.setMemType(dr.getString(5));
                        return c;
                    }                
                });
    }

    public SentenceList getCustomerList1() {
        return new StaticSentence(s
            , new QBFBuilder("SELECT ID, TAXID, SEARCHKEY, NAME,MOBILE FROM CUSTOMERS WHERE VISIBLE = TRUE AND CARD IS NULL AND ?(QBF_FILTER) ORDER BY  SEARCHKEY", new String[] {"TAXID", "SEARCHKEY", "NAME"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        CustomerInfo c = new CustomerInfo(dr.getString(1));
                        c.setTaxid(dr.getString(2));
                        c.setSearchkey(dr.getString(3));
                        c.setName(dr.getString(4));
                        c.setMobile(dr.getString(5));
                       // c.setMemType(dr.getString(5));
                        return c;
                    }
                });
    }
  
    public CustomerInfoExt getCustomerByID(String id) throws BasicException {
     final   String idarr[]=id.split("#");
     final String cid=id;
     final int temp1;
        int  temp=1;
        if(idarr.length>1)
        {
            id=idarr[0];
            temp=0;
        }
        temp1=temp;
        return (CustomerInfoExt) new PreparedSentence(s
            , "SELECT ID, TAXID, SEARCHKEY, NAME, TAXCATEGORY, CARD, NOTES, VISIBLE,ACCOUNT,MEMTYPE,SOWO "+
                            " ,MOBILE,SERVICETAXCAT,ENTTAXCAT" +
                            ",JOINDATE,DOB,OPENINGBALANCE FROM CUSTOMERS WHERE ID = ?"
            , SerializerWriteString.INSTANCE
            , new SerializerRead() {
                    // int temp1=temp;
                    public Object readValues(DataRead dr) throws BasicException {
                         CustomerInfoExt c;
                        if(temp1==0)
                              c = new CustomerInfoExt(cid);
                        else
                         c = new CustomerInfoExt(dr.getString(1));
                        c.setTaxid(dr.getString(2));
                        c.setSearchkey(dr.getString(3));
                        c.setName(dr.getString(4));
                        c.setTaxCustomerID(dr.getString(5));
                        c.setCard(dr.getString(6));
                       // c.setMaxdebt(dr.getDouble(7));
                        
                        c.setNotes(dr.getString(7));
                        c.setVisible(dr.getBoolean(8));
                     //   c.setCurdate(dr.getTimestamp(21));
                     //   c.setCurdebt(dr.getDouble(22));
                        //,MEMTYPE,FINGERPRINTDATA,SOWO "+
                        //    " ,MOBILE,SERVICETAXCAT,ENTTAXCAT,SIGNATURE,SPONSOR1,SPONSOR2,SPONSOR3" +
                       //     ",JOINDATE,TERDATE,DOB,OPENINGBALANCE
                       
                        c.setaccount(dr.getString(9));
                        if(temp1==0)
                            c.setName(c.getName()+" : "+idarr[1]);
                        c.setMemType(dr.getString(10));
                       
                        c.setSoWo(dr.getString(11));
                        c.setMobile(dr.getString(12));
                        c.setSTax(dr.getString(13));
                        c.setETax(dr.getString(14));
                        
                        c.setjDate(dr.getTimestamp(15));
                       
                        c.setDOB(dr.getTimestamp(16));
                        c.setopeningbalance(dr.getDouble(17));
                      //  c.setopeningbalance(dr.getDouble(23));
                        return c;
                    }
                }).find(id);
    }

    public int updateCustomerExt(final CustomerInfoExt customer) throws BasicException {
     
        return new PreparedSentence(s
                , "UPDATE CUSTOMERS SET NOTES = ? WHERE ID = ?"
                , SerializerWriteParams.INSTANCE      
                ).exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, customer.getNotes());
                        setString(2, customer.getId());
                }});        
    }
    
    public final SentenceList getReservationsList() {
        return new PreparedSentence(s
            , "SELECT R.ID, R.CREATED, R.DATENEW, C.CUSTOMER, CUSTOMERS.TAXID, CUSTOMERS.SEARCHKEY, COALESCE(CUSTOMERS.NAME, R.TITLE),  R.CHAIRS, R.ISDONE, R.DESCRIPTION " +
              "FROM RESERVATIONS R LEFT OUTER JOIN RESERVATION_CUSTOMERS C ON R.ID = C.ID LEFT OUTER JOIN CUSTOMERS ON C.CUSTOMER = CUSTOMERS.ID " +
              "WHERE R.DATENEW >= ? AND R.DATENEW < ?"
            , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP, Datas.TIMESTAMP})
            , new SerializerReadBasic(customerdatas));             
    }
    
    public final SentenceExec getReservationsUpdate() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {  
    
                new PreparedSentence(s
                    , "DELETE FROM RESERVATION_CUSTOMERS WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
                if (((Object[]) params)[3] != null) {
                    new PreparedSentence(s
                        , "INSERT INTO RESERVATION_CUSTOMERS (ID, CUSTOMER) VALUES (?, ?)"
                        , new SerializerWriteBasicExt(customerdatas, new int[]{0, 3})).exec(params);                
                }
                return new PreparedSentence(s
                    , "UPDATE RESERVATIONS SET ID = ?, CREATED = ?, DATENEW = ?, TITLE = ?, CHAIRS = ?, ISDONE = ?, DESCRIPTION = ? WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0, 1, 2, 6, 7, 8, 9, 0})).exec(params);
            }
        };
    }
    
    public final SentenceExec getReservationsDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {  
    
                new PreparedSentence(s
                    , "DELETE FROM RESERVATION_CUSTOMERS WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
                return new PreparedSentence(s
                    , "DELETE FROM RESERVATIONS WHERE ID = ?"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0})).exec(params);
            }
        };
    }
    
    public final SentenceExec getReservationsInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {  
    
                int i = new PreparedSentence(s
                    , "INSERT INTO RESERVATIONS (ID, CREATED, DATENEW, TITLE, CHAIRS, ISDONE, DESCRIPTION) VALUES (?, ?, ?, ?, ?, ?, ?)"
                    , new SerializerWriteBasicExt(customerdatas, new int[]{0, 1, 2, 6, 7, 8, 9})).exec(params);

                if (((Object[]) params)[3] != null) {
                    new PreparedSentence(s
                        , "INSERT INTO RESERVATION_CUSTOMERS (ID, CUSTOMER) VALUES (?, ?)"
                        , new SerializerWriteBasicExt(customerdatas, new int[]{0, 3})).exec(params);                
                }
                return i;
            }
        };
    }
    public final Object getparentaccountbyid(String id) throws BasicException{
        Object pid="";
             Object[] obj=(Object[]) new StaticSentence(s
                                   ,"SELECT A.ID FROM ACCOUNTMASTER A,ACCOUNTMASTER A1 WHERE A.SEARCHKEY=A1.PARENT AND A1.ID=?"
                                  ,SerializerWriteString.INSTANCE
                                  ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find(id);
             if(obj!=null && obj[0]!=null){
               pid=obj[0];
             }
             return pid;
    }
    public final Object[] getaccountofcustomer(String id) throws BasicException{
       // Object pid=null;
      Object[] obj=(Object[]) new StaticSentence(s
                                   ,"SELECT A.PARENT,A.ID,C.ACCOUNT FROM CUSTOMERS C,ACCOUNTMASTER A WHERE C.ID=? AND C.ACCOUNT=A.ID"
                                  ,SerializerWriteString.INSTANCE
                                  ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING})).find(id);
     // i//f(obj!=null)
       //   pid=obj[0];
      return obj;

    }

    public final TableDefinition getTableCustomers() {
        return tcustomers;
    }  
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;
import java.util.List;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SentenceExec;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteBasicExt;
import com.openbravo.data.loader.SerializerWriteBuilder;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author swathi
 */
public class Qticket extends BeanFactoryDataSingle{
    private Session s;
    private DataLogicSales dlSales;
    private AppView appView;
    static String qtID=null;
    /** Creates a new instance of DataLogicReceipts */
    public Qticket() {
    }

    public void init(Session s){
        this.s = s;
    }

    public void setDataLogicSales(DataLogicSales dls) {
        this.dlSales = dls;
    }

    public void setAppView(AppView app) {
        this.appView = app;
    }

    public AppView getAppView() {
        return appView;
    }

//    public final TicketInfo getQTicket(String Id) throws BasicException {
//
//        if (Id == null) {
//            return null;
//        } else {
//            Object[]record = (Object[]) new StaticSentence(s
//                    , "SELECT CONTENT FROM QTICKET WHERE ID = ?"
//                    , SerializerWriteString.INSTANCE
//                    , new SerializerReadBasic(new Datas[] {Datas.SERIALIZABLE})).find(Id);
//            return record == null ? null : (TicketInfo) record[0];
//        }
//    }
//warehouse changes -start
     //praveen:initiator changes---start
    public final List<QticketInfo> getQTicketList(String customer) throws BasicException {

         return (List<QticketInfo>) new PreparedSentence(s
                , "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE, WAREHOUSE,INITIATOR FROM QTICKET WHERE CUSTOMER = ? ORDER BY ID"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(QticketInfo.class)).list(customer);
    }

    public final List<QticketInfo> getPendingQTicketList(String customer) throws BasicException {

         return (List<QticketInfo>) new PreparedSentence(s
                , "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE, WAREHOUSE,INITIATOR FROM QTICKET WHERE CUSTOMER = ? AND BILLED = FALSE ORDER BY ID"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(QticketInfo.class)).list(customer);
    }

    public final List<QticketInfo> getQTicketListForUser(final String customer, final AppUser user) throws BasicException {
         return (List<QticketInfo>) new PreparedSentence(s
                , "SELECT Q.ID, Q.CUSTOMER, Q.WAITER, Q.PLACE, Q.FLOOR, Q.PRCATEGORY, Q.BILLED, Q.BILLREF, Q.CREATEDBY, Q.CRDATE, WAREHOUSE,INITIATOR FROM QTICKET Q JOIN PEOPLE ON PEOPLE.NAME=Q.CREATEDBY JOIN ROLES ON ROLES.ID=PEOPLE.ROLE WHERE Q.BILLED=FALSE AND Q.CUSTOMER = ? AND ROLES.ID = ?   ORDER BY Q.ID"
                , SerializerWriteParams.INSTANCE
                , new SerializerReadClass(QticketInfo.class)).list(new DataParams() {
                    public void writeValues() throws BasicException {
                        setString(1, customer);
                        setString(2, user.getRole());
                    }
                });
    }
             //praveen:initiator changes---end
    //warehouse changes -end
             /*   public final List<QticketInfo> getQTicketListForAll( final String user) throws BasicException {
         return (List<QticketInfo>) new PreparedSentence(s
                , "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE FROM QTICKET WHERE CREATEDBY = ? AND BILLED = FALSE ORDER BY ID"
                , SerializerWriteParams.INSTANCE
                , new SerializerReadClass(QticketInfo.class)).list(new DataParams() {
                    public void writeValues() throws BasicException {
                       
                        setString(1, user);
                    }
                });

    }*/

  
    //}

    public final List<QTicketLineInfo> getQTicketLineList(String ticketId) throws BasicException {

         return (List<QTicketLineInfo>) new PreparedSentence(s
                , "SELECT ID, LINE, PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES FROM QTITEMS WHERE PARENTID = ? ORDER BY LINE"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(QTicketLineInfo.class)).list(ticketId);
    }

    public final int updateQTicket(final String id, final QticketInfo qticket) throws BasicException {

        Object[] values = new Object[] {id, qticket.getCustomerId(), qticket.getPlace(), qticket.getWaiter(), qticket.getprCategory(), qticket.isBilled(), qticket.getBillref(),qticket.getCreatedBy(), qticket.getCreatedDate()};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.TIMESTAMP};
        int cnt=new PreparedSentence(s
                , "UPDATE QTICKET SET CUSTOMER = ?, PLACE  = ?, WAITER = ?, PRCATEGORY = ?, BILLED = ?, BILLREF = ?, CREATEDBY = ?, CRDATE = ?  WHERE ID = ? AND BILLED=FALSE"
                , new SerializerWriteBasicExt(datas, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0})).exec(values);
        return cnt;
    }

//    public final void insertQTicket(final String id, final QticketInfo qticket) throws BasicException {
//
//        Object[] values = new Object[] {id, qticket.getCustomer(), qticket.getPlace(), qticket.getWaiter(), qticket.getprCategory(), qticket.isBilled(), qticket.getBillref(), qticket.getCreatedBy(), qticket.getCreatedDate()};
//        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.STRING, Datas.STRING, Datas.BOOLEAN};
//        new PreparedSentence(s
//            , "INSERT INTO QTICKET (ID, CUSTOMER, PLACE, WAITER, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE) VALUES (?, ?, ?)"
//            , new SerializerWriteBasicExt(datas, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8})).exec(values);
//    }
  /*   private String getNextQTicketID() throws BasicException {
         String prefix = appView.getProperties().getProperty("sequence.qtnumber");
         if (prefix == null) {
             //TODO warn about configuration
             prefix = "A";
         }
         StringBuffer sb = new StringBuffer(prefix);
         return sb.append(dlSales.getNextQTicketIndex()).toString();
     }*/
      private String getNextQTicketID(String createdby) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String qtnum;
        //String uname=appView.getAppUserView().getUser().getName();
        String uname = appView.getAppUserView().getUser().getRole();
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.QTSERIES,SEQUENCEDETAIL.QTMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            qtnum = obj[0].toString() + max.intValue();
            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET QTMAX=?  WHERE ACTIVE=TRUE AND  USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?)", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            return qtnum;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
            return "";
        }
    }
      private int flag1=0;
     public final boolean saveQTicket(final QticketInfo qticket) throws BasicException {
       flag1=0;
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {

                // Set Receipt Id
                if (qticket.getId() == null) {
                    String temp=getNextQTicketID(qticket.getCreatedBy());
                    qticket.setID(temp);
                    if(temp.equals("")){
                    
                    flag1=1;
                    return false;
                    }
                    //qtl.setParentid(qticket.getId());
                }
                
                // new ticket
                //warehouse changes -start
                 //praveen:initiator changes---start
                new PreparedSentence(s
                    , "INSERT INTO QTICKET (ID, CUSTOMER, PLACE, WAITER, FLOOR, PRCATEGORY, BILLED, BILLREF, CREATEDBY, CRDATE,WAREHOUSE,INITIATOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() {

                    @Override
                    public void writeValues() throws BasicException {
                        setString(1, qticket.getId());
                        qtID=qticket.getId();
                        setString(2, qticket.getCustomerId());
                        setString(3, qticket.getPlace());
                        setString(4, qticket.getWaiter());
                        setString(5, qticket.getFloor());
                        setString(6, qticket.getprCategory());
                        setBoolean(7, qticket.isBilled());
                        setString(8, qticket.getBillref());
                        setString(9, qticket.getCreatedBy());
                        setTimestamp(10, qticket.getCreatedDate());
                        setString(11, qticket.getWarehouse());
                        setString(12, qticket.getInitiator());
                    }
                });
                 //praveen:initiator changes---end
//warehouse changes -end
                SentenceExec ticketlineinsert = new PreparedSentence(s
                    , "INSERT INTO QTITEMS (ID, LINE ,PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES) VALUES (?,?, ?, ?, ?, ?, ?)"
                    , SerializerWriteBuilder.INSTANCE);

                for (QTicketLineInfo l : qticket.getLines()) {
                    if (l.getParentid()==null) {
                        l.setParentid(qticket.getId());
                    }
                    ticketlineinsert.exec(l);
                }
                
/////////////////////////////////aaa                
            AppView app = LookupUtilityImpl.getInstance(null).getAppView();
            AppUser a = app.getAppUserView().getUser();
      String str = a.toString();
      String astr = qticket.getCustomerId();
      String w = qticket.getWarehouse();
      int x = 0;
      List l = dlSales.getRestWarehouse().list();
      for(int i=0;i<l.size();i++){
          Object obl = l.get(i);
          if(obl.toString().equals(w)){
              x=1;
          }
      }
      final String a1 = astr.substring(0,36);
      
      
   final Object[]objr = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT NAME FROM ROLES WHERE ID = (SELECT ROLE FROM PEOPLE WHERE NAME = '"+str+"')"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      Object[]obj = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT RCOUNTERS FROM QTKASSIGN where RCOUNTERS like '%"+objr[0].toString()+"%'"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      
              if(obj!=null){
                  if(x==1){
                new PreparedSentence(app.getSession()
                    , "INSERT INTO QTKITCHEN (ID, CUSTOMER, PLACE, WAITER, FLOOR, BILLED, COUNTER, CREATEDBY, CRDATE, PREPARED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() {

                    @Override
                    public void writeValues() throws BasicException {
                        setString(1, qticket.getId());
                        //setString(2, a1);           *****************************  EDITED BY AAKASH  *******************************8
                        setString(2, qticket.getCustomerId());
                        setString(3, qticket.getPlace());
                        setString(4, qticket.getWaiter());
                        setString(5, qticket.getFloor());
                        setBoolean(6, qticket.isBilled());
                        setString(7, objr[0].toString());
                        setString(8, qticket.getCreatedBy());
                        setTimestamp(9, qticket.getCreatedDate());
                        setString(10, "0");
                        
                    }
                });
              }
                  
              
                  
              }
//////////////////////////////aaa                
              
               return null;
            }
        };
        t.execute();
        if(flag1==1)
            return false;
        else
            return true;
     }

     public final void insertQTicketLine(QticketInfo qtInfo, QTicketLineInfo qtlInfo) throws BasicException {
         SentenceExec ticketlineinsert = new PreparedSentence(s
            , "INSERT INTO QTITEMS (ID, LINE ,PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES) VALUES (?,?, ?, ?, ?, ?, ?)"
            , SerializerWriteBuilder.INSTANCE);

        if (qtlInfo.getParentid()==null) {
            qtlInfo.setParentid(qtInfo.getId());
        }
        ticketlineinsert.exec(qtlInfo);
     }

     
    public final void deleteQTicket(final String id) throws BasicException {

        new StaticSentence(s
            , "DELETE FROM QTICKET WHERE ID = ?"
            , SerializerWriteString.INSTANCE).exec(id);
    }

}

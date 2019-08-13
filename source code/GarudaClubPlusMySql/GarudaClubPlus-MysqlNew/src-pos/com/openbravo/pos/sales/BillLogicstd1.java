package com.openbravo.pos.sales;

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
import com.openbravo.pos.admin.RoleInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.FloorsInfo;
//import com.openbravo.pos.sales.BillInfo;
//import com.openbravo.pos.sales.BillLineInfo;
import com.openbravo.pos.sales.restaurant.CounterTotals;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author swathi
 */
public class BillLogicstd1 extends BeanFactoryDataSingle {

    private Session s;
    private DataLogicSales dlSales;
    private AppView appView;
    private FloorsInfo lb;
    private List<RoleInfo> rolelist;

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public BillLogicstd1() {
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

    public final List<BillInfostd1> getBillList1() throws BasicException {
        //praveen:start-------displaying pending list as per role
        String role = appView.getAppUserView().getUser().getRole();
        List<BillInfostd1> list = new ArrayList<BillInfostd1>();
        List<BillInfostd1> list1 = new ArrayList<BillInfostd1>();         
        rolelist = dlSales.getCatalogListToRole(role);
        StringBuffer condition1 = new StringBuffer("");
        Object[] params1 = new Object[rolelist.size()];
        Datas[] data1 = new Datas[rolelist.size()];
        int i = 0;
        for (RoleInfo r : rolelist) {
            data1[i] = Datas.STRING;
            params1[i] = r.getID();
            condition1.append(" ? ,");
            i++;
        }
        if (condition1.length() > 0) {
            condition1.deleteCharAt(condition1.lastIndexOf(","));
        }
        //praveen:initiator changes---start
        String warehouse = null;
        String[] warehouses = null;

        Object obj = appView.getAppUserView().getUser().getWarehouse();
        if (obj != null) {
            warehouses = obj.toString().split("#");
            warehouse = warehouses[0];
        }
        if (warehouses != null) {
            Object[] params = new Object[warehouses.length];
            Datas[] data = new Datas[warehouses.length];
            StringBuffer condition = new StringBuffer("");
            for (int j = 0; j < warehouses.length; j++) {
                data[j] = Datas.STRING;
                params[j] = warehouses[j].toString();
                condition.append(" ? , ");
            }
            if (condition.length() > 0) {
                condition.deleteCharAt(condition.lastIndexOf(","));
            }
            list = (List<BillInfostd1>) new StaticSentence(s, "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME,BILL.AMOUNT , BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS,WAITER WHERE  BILL.FLOOR=FLOORS.ID AND BILL.PAID=FALSE AND bill.warehouse in  (" + condition.toString() + ") group by bill.id ORDER BY BILL.ID,BILL.CREATEDDATE,BILL.CREATEDBY", new SerializerWriteBasic(data), new SerializerReadClass(BillInfostd1.class)).list(params);
            //temporary fix.
//            list1 = (List<BillInfo>) new StaticSentence(s, "SELECT BILL.ID, BILL.CUSTOMER, BILL.WAITER, BILL.PLACE, FLOORS.NAME,BILL.AMOUNT , BILL.CREATEDBY, BILL.CREATEDDATE, BILL.PAID, BILL.RECEIPT,BILL.WAREHOUSE,BILL.INITIATOR,Bill.taxtotal FROM BILL,FLOORS,WAITER WHERE  BILL.FLOOR=FLOORS.ID AND BILL.PAID=FALSE AND bill.waiter=waiter.id and bill.warehouse not in (" + condition.toString() +") and waiter.counter in(select category from sequencedetail where username='"+role+"') group by bill.id ORDER BY BILL.ID,BILL.CREATEDDATE,BILL.CREATEDBY", new SerializerWriteBasic(data), new SerializerReadClass(BillInfo.class)).list(params);
//            if(list1!=null || list1.size()>0){
//                list.addAll(list1);
//            }
        } else {
            list = null;
        }
        if (list == null || list.size() <= 0) {
            list = new ArrayList<BillInfostd1>();
        }
        //praveen:initiator changes---end
        //warehouse changes - end
        //praveen:end
        return list;
    }

    public final List<CounterTotals> getPendingBillTotal() throws BasicException {

        return (List<CounterTotals>) new StaticSentence(s, "SELECT SUM(BILL.AMOUNT),LOCATIONS.NAME FROM BILL,LOCATIONS WHERE BILL.PAID=false AND BILL.WAREHOUSE=LOCATIONS.ID GROUP BY LOCATIONS.ID,LOCATIONS.NAME ", null, new SerializerReadClass(CounterTotals.class)).list();
    }
//warehouse changes - start

    public final List<BillInfostd1> getBillList() throws BasicException {

        return (List<BillInfostd1>) new StaticSentence(s, "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, AMOUNT, CREATEDBY, CREATEDDATE, PAID, RECEIPT, WAREHOUSE, INITIATOR,taxtotal FROM BILL ORDER BY CREATEDBY,ID,CREATEDDATE", null, new SerializerReadClass(BillInfostd1.class)).list();
    }

    public final BillInfostd1 getBillInfo(String id) throws BasicException {

        return (BillInfostd1) new StaticSentence(s, "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, AMOUNT, CREATEDBY, CREATEDDATE, PAID, RECEIPT,WAREHOUSE, INITIATOR , taxtotal FROM BILL WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfostd1.class)).find(id);
    }
    
    public final BillInfostd1 getBillInfostd(String id) throws BasicException {

        return (BillInfostd1) new StaticSentence(s, "SELECT ID, CUSTOMER, WAITER, PLACE, FLOOR, AMOUNT, CREATEDBY, CREATEDDATE, PAID, RECEIPT,WAREHOUSE, INITIATOR , taxtotal FROM BILL WHERE ID=? ", SerializerWriteString.INSTANCE, new SerializerReadClass(BillInfostd1.class)).find(id);
    }
//warehouse changes - end

    public final void insertBillItem(BillLineInfostd1 bline, int size) throws BasicException {
        int qty = bline.getMultiply();
        Double rate = bline.getRate();
        Double total = qty * rate;
        Object[] value = new Object[]{UUID.randomUUID().toString(), bline.getParentid(), bline.getProduct().getID(), qty, rate, total,bline.getGtax1id(),bline.getGtax1(),bline.getGtax2id(),bline.getGtax2cas(),bline.getGtax2(),bline.getGtax3id(),bline.getGtax3cas(),bline.getGtax3()};
        // Datas type[]=new Datas{Datas.STRING,};
        new PreparedSentence(s, "INSERT INTO BILLITEM (ID, LINE ,PARENTID, PRODUCT, DMULTIPLY, RATE,TOTAL,Tax1ID,Tax1,Tax2ID,T2_Cas,Tax2,Tax3ID,T3_Cas,Tax3) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE,Datas.STRING, Datas.DOUBLE,Datas.STRING,Datas.INT, Datas.DOUBLE,Datas.STRING,Datas.INT, Datas.DOUBLE})).exec(value);
    }

    public final List<BillLineInfostd1> getBillLineList(String billId) throws BasicException {

        return (List<BillLineInfostd1>) new PreparedSentence(s, "SELECT ID, LINE, PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES,TOTAL FROM BILLITEM WHERE PARENTID = ? ORDER BY LINE", SerializerWriteString.INSTANCE, new SerializerReadClass(BillLineInfostd1.class)).list(billId);
    }
    
    public final List<BillLineInfostd1> getBillLineListstd(String billId) throws BasicException {

        return (List<BillLineInfostd1>) new PreparedSentence(s, "SELECT ID, LINE, PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES,TOTAL FROM BILLITEM WHERE PARENTID = ? ORDER BY LINE", SerializerWriteString.INSTANCE, new SerializerReadClass(BillLineInfostd1.class)).list(billId);
    }

    /*
     * WARNING: This method should be called in a transaction so that all the SQL operation performed here are atomic
     */
    public final boolean markBillAsPaid(final BillInfostd1 bill) throws BasicException {
        boolean status = true;
        Object[] values = new Object[]{bill.getID(), bill.isPaid(), bill.getReceiptRef()};
        Datas[] datas = new Datas[]{Datas.STRING, Datas.BOOLEAN, Datas.STRING};
        int cnt = new PreparedSentence(s, "UPDATE BILL SET PAID = ? ,RECEIPT = ? WHERE ID = ? AND PAID=FALSE", new SerializerWriteBasicExt(datas, new int[]{1, 2, 0})).exec(values);
        if (cnt != 0) {
            PreparedSentence l = new PreparedSentence(s, "UPDATE BILLITEM SET ATTRIBUTES = ? WHERE ID = ?", SerializerWriteParams.INSTANCE);
            for (final BillLineInfostd1 line : bill.getLines()) {
                l.exec(new DataParams() {

                    @Override
                    public void writeValues() throws BasicException {
                        try {
                            ByteArrayOutputStream o = new ByteArrayOutputStream();
                            line.getProperties().storeToXML(o, AppLocal.APP_NAME, "UTF-8");
                            setBytes(1, o.toByteArray());
                        } catch (IOException e) {
                            setBytes(1, null);
                        }
                        setString(2, line.getID());
                    }
                });
            }
        } else {
            status = false;
            JOptionPane.showMessageDialog(null, "Error Occured...Try again...", null, JOptionPane.WARNING_MESSAGE);
        }
        return status;
    }

    /*  private String getNextBillID(BillInfo bill) throws BasicException {
    String prefix = appView.getProperties().getProperty("sequence.billnumber");
    if (prefix == null) {
    //TODO warn about configuration
    prefix = "A";
    }
    StringBuffer sb = new StringBuffer(prefix);
    //TODO get floor billsl
    String temp = LookupUtilityImpl.getInstance(null).getFloorMap().get(bill.getFloor()).getBillsl();
    sb.append(""+temp);
    return sb.append(""+getlastbillno(bill.getFloor())).toString();
    //return sb.append(dlSales.getNextBillNumberIndex()).toString();
    }*/
    public String getNextBillID(BillInfostd1 bill) throws BasicException {
        //praveen:sequencedetail:inserting id instead of names
        String billnum;
        //String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();

        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();
        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.BSERIES,SEQUENCEDETAIL.BMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE SEQUENCEDETAIL.USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE  AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, bill.getCreatedBy()});
        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            billnum = obj[0].toString() + max.intValue();
            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET BMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, bill.getCreatedBy()});
            return billnum;
            
           
        } else {
              JOptionPane.showMessageDialog(null, "Please Specify the Bill Series", "Cannot Create Bill", JOptionPane.OK_OPTION);
              return "";
           
        }
    }

    public final String getlastbillno(String floor) throws BasicException {
        lb = (FloorsInfo) new PreparedSentence(s, "SELECT ID, NAME, BILLSL, LASTBILL FROM FLOORS WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadClass(FloorsInfo.class)).find(floor);
        String i = (lb.getLastBillsl());
        updatelastbillno();
        return i;
    }

    public final void updatelastbillno() throws BasicException {
        int x = Integer.parseInt(lb.getLastBillsl());
        x++;
        String temp = "" + x;
        Object[] values = new Object[]{lb.getID(), temp};
        Datas[] data = new Datas[]{Datas.STRING, Datas.STRING};
        new PreparedSentence(s, "UPDATE FLOORS SET LASTBILL = ? WHERE ID = ?", new SerializerWriteBasicExt(data, new int[]{1, 0})).exec(values);
    }
      private int flag1 = 0;
    private boolean berror = false;

  
   
     public final boolean saveBillstd(final BillInfostd1 bill, final List<QticketInfo> qtInfos, final Qticket dlQTs, final String type,final TicketInfo customer,final TicketInfo ticket) throws Exception {
        flag1 = 0;
        berror = false;

 //      Transaction t = new Transaction(s) {

  //          public Object transact() throws BasicException {
                final Date date = new Date();
                AppUser user = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser();
                Object[] obj = (Object[]) new StaticSentence(s, "SELECT OPENSALE,prcategories FROM PEOPLE WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).find(user.getId());
                String warehouse = null;
                if (obj != null) {
                    if (obj[0] != null) {
                        Date d = (Date) obj[0];
                        Calendar cal1 = Calendar.getInstance();
                        Calendar cal2 = Calendar.getInstance();
                        cal1.setTimeInMillis(date.getTime());
                        cal2.setTimeInMillis(d.getTime());
                        if (cal1.before(cal2)) {
                            if (JOptionPane.showConfirmDialog(null, "Present Time is less than Open sale Time.Previous Open sale Time is " + d + " .Do you want to override the open sale time ?", "Error-System Time was reset", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                                berror = true;
                            } else {
                                new PreparedSentence(s, "UPDATE PEOPLE SET OPENSALE=? WHERE ID=?", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING})).exec(new Object[]{date, user.getId()});
                            }
                        }
                    }
//warehouse changes - start

                    if (obj[1] != null) {
                        String[] warehouses = obj[1].toString().split("#");
                        warehouse = warehouses[0];
                    }
                }

                if (warehouse == null) {
                    warehouse = user.getWarehouse();
                }
                final String wh = warehouse;
                 bill.setCreatedBy(getAppView().getAppUserView().getUser().getName()); 
                //warehouse changes - end
                if (berror == false) {
                    if (bill.getID() != null) {
                        String bno = getNextBillID(bill);

                        if (bno.equals("")) {
                            flag1 = 1;
                            return false;

                        }
                        bill.setID(bno);
                        
                  
                   
                    }
                     if (type.equals("debt")) {
                        bill.setPaid(true);
                       // bill.getReceiptRef();
                    }
                          bill.getReceiptRef();                    
                        //TODO change to ID later
                         bill.setCreatedDate(new Date());
                         bill.setLines(new ArrayList<BillLineInfostd1>());
                     //    bill.setInitiator(ticket.getInitiator());
                         
                         bill.setPlace(ticket.getPlaceId());
                         bill.setWaiter(ticket.getWaiterId());
                         bill.setFloor(ticket.getFloorId());
     //////////////////////////RECIPT REFERENCE FOR CASH/////////////////////////////////////////
                          if (type.equals("debt")) {
                          
                          }
                          else{
                         if (bill.getReceiptRef() == null) {
                            System.out.println(bill.getCreatedBy());
                            final String rno = getNextReceiptID(bill.getCreatedBy());
                     
                            bill.setReceiptRef(rno);
                            
                            if (rno.equals("")) {
                                flag1 = 1;
                                return false;
                                
                            }
                          }
                          }
    //////////////////////////RECIPT REFERENCE FOR CASH/////////////////////////////////////////                    
                          //     bill.setPaid(true);
                     
               //       new PreparedSentence(s, "INSERT INTO BILL (ID,CUSTOMER,AMOUNT,TAXTOTAL,WAREHOUSE,INITIATOR) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?)", SerializerWriteParams.INSTANCE).exec(new DataParams() {
    new PreparedSentence(s, "INSERT INTO BILL (ID, CUSTOMER, AMOUNT, CREATEDBY,CREATEDDATE, PAID, TAXTOTAL,WAREHOUSE,INITIATOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )", SerializerWriteParams.INSTANCE).exec(new DataParams() {
                        @Override
                        public void writeValues() throws BasicException {
                            setString(1, bill.getID());
                            setString(2,customer.getCustomerId());
                          //  setString(3, bill.getPlace());
                          //  setString(4, bill.getWaiter());
                          //  setString(5, bill.getFloor());
                             setDouble(3, ticket.getSubTotal());
                             setString(4, bill.getCreatedBy());
                             setTimestamp(5, bill.getCreatedDate());
                             setBoolean(6, bill.isPaid());
                            
                             setDouble(7, ticket.getTax());
                             setString(8, wh);
                             setString(9, bill.getInitiator());
                            
                          
                        }
                    });
                      
                          

                    SentenceExec taxlinesinsert = new PreparedSentence(s, "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)", SerializerWriteParams.INSTANCE);
                      for (final TicketTaxInfo tickettax : ticket.getTaxes()) {    
                    if (tickettax.getTax() > 0) {
                             
                    taxlinesinsert.exec(new DataParams() {
                                  
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setString(2, bill.getID());
                                        setString(3, tickettax.getTaxInfo().getId());
                                        setDouble(4, ticket.getSubTotal());
                                        setDouble(5, ticket.getTax());
                                    }
                                });
                               }
                      }
                        
                    
                    //shiv:initiator changes---end
//warehouse changes - end
                    SentenceExec Billlineinsert = new PreparedSentence(s, "INSERT INTO BILLITEM (ID, LINE ,PARENTID, PRODUCT, DMULTIPLY, RATE, ATTRIBUTES,TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?,?)", SerializerWriteParams.INSTANCE);

                    for (final TicketLineInfo t : ticket.getLines()) {
                         Billlineinsert.exec(new DataParams() {
                                  
                                    public void writeValues() throws BasicException {
                                        setString(1, UUID.randomUUID().toString());
                                        setInt(2,t.getTicketLine());
                                        setString(3,bill.getID());
                                        setString(4,t.getProductID());
                                        setDouble(5,t.getMultiply());
                                        setDouble(6,t.getPrice());
                                        setBytes(7, null);
                                        setDouble(8,t.getPrice()*t.getMultiply()+t.getTax());
                                    }
                                });
                        }
                       
                           
                    System.out.println(customer.getCustomerId());
                  
                    
                           
                    if (type.equals("debt")){
                         if (bill.getReceiptRef() == null) {
                            System.out.println(bill.getCreatedBy());
                            final String rno = null;
                     
                            bill.setReceiptRef(rno);
                            
                         //   if (rno.equals("")) {
                           //     flag1 = 1;
                             //   return false;
                                
                          //  }
                     
                            
                        }
                          
                         
                       
                      
      new PreparedSentence(s, "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{date, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), customer.getCustomerId(), bill.getID(), ticket.getTotal(), bill.getWaiter()});
                 
                
            }  
  
          
    
                    //final Date date = new Date();
           /*       if (type.equals("debt")) {
                        if (bill.getReceiptRef() == null) {
                            System.out.println(bill.getCreatedBy());
                            String rno = getNextReceiptID(bill.getCreatedBy());
                            bill.setReceiptRef(rno);
                            if (rno.equals("")) {
                                flag1 = 1;
                                return false;
                            }
                        }
                        //praveen:changed  bill.getReceiptRef() to uuid for column id in creditconflist
                        new PreparedSentence(s, "INSERT INTO CREDITCONFLIST (  DATENEW, RUSER,CUSTOMER,BILLREF,AMOUNT,WAITER) VALUES ( ?, ?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING})).exec(new Object[]{date, LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName(), customer.getCustomerId(), bill.getID(), bill.getTotal(), bill.getWaiter()});
                    //bill.setPaid(true);
                    // boolean berror1= LookupUtilityImpl.getInstance(null).getDataLogicBill().markBillAsPaid(bill);
                    }   */
                }   
            //    return null;
       //     }
        //    };
       // t.execute();
        if (flag1 == 0) {
            if (berror == true) {
                JOptionPane.showMessageDialog(null, "Please reset the system time or consult your system admin", "Sorry Cannot Create Bill", JOptionPane.OK_OPTION);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    public String getNextReceiptID(String createdby) throws BasicException {
        //shiv:sequencedetail:inserting id instead of names
        String receiptnum;
        // String uname=LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
        String uname = LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getRole();

        Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RSERIES,SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL,PEOPLE P WHERE USERNAME=? AND SEQUENCEDETAIL.CATEGORY=P.ROLE AND P.NAME=? AND ACTIVE=TRUE", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.DOUBLE})).find(new Object[]{uname, createdby});
//     Object[] obj=(Object[])new  StaticSentence(s
//            , "SELECT '',max(SEQUENCEDETAIL.RMAX) FROM SEQUENCEDETAIL,PEOPLE P,PEOPLE P1,ROLES R,ROLES R1 WHERE USERNAME=R1.NAME AND R1.ID=P1.ROLE  AND SEQUENCEDETAIL.CATEGORY=R.NAME AND R.ID=P.ROLE  AND ACTIVE=TRUE"
//            ,null
//          ,new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.DOUBLE})).find();
        if (obj != null) {
            Double max = Double.parseDouble(obj[1].toString());
            max++;
            receiptnum = obj[0].toString() + max.intValue();

            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ACTIVE=TRUE AND USERNAME = ?  AND CATEGORY=(SELECT ROLE FROM PEOPLE WHERE NAME=?) ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE, Datas.STRING, Datas.STRING})).exec(new Object[]{max, uname, createdby});
            return receiptnum;
            
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Receipt Series", "Cannot Create Receipt", JOptionPane.OK_OPTION);
            return null;
            
            
        }
       
    }

    public final void deleteBill(final String id) throws BasicException {

        new StaticSentence(s, "DELETE FROM BILL WHERE ID = ?", SerializerWriteString.INSTANCE).exec(id);
    }
    
    //added by pratima
    public String getCreditConfID() throws BasicException {
         Object[] obj = (Object[]) new StaticSentence(s, "SELECT SEQUENCEDETAIL.RMAX FROM SEQUENCEDETAIL WHERE ID=?", new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadBasic(new Datas[]{ Datas.DOUBLE})).find(new Object[]{"CREDITCONFLISTID"});
  if (obj != null) {
                String creditConfID =  obj[0].toString();
                 int creditConfID1=(int)Double.parseDouble(creditConfID);
                creditConfID =String.valueOf(creditConfID1);
            Double max = Double.parseDouble(obj[0].toString());
           
            max++;
            

            new StaticSentence(s, "UPDATE SEQUENCEDETAIL SET RMAX=?  WHERE ID=? ", new SerializerWriteBasic(new Datas[]{Datas.DOUBLE,Datas.STRING})).exec(new Object[]{max,"CREDITCONFLISTID"});
            return creditConfID;
        } else {
            JOptionPane.showMessageDialog(null, "Please Specify the Credit Confirmation ID", "Cannot update Credit Confirmation table", JOptionPane.OK_OPTION);
            return null;
        }
  }//ended by pratima
    
    
}



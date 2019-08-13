

package com.openbravo.pos.ticket;

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfoExt;
//import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.openbravo.pos.sales.restaurant.Place;

/**
 *
 * @author adrianromero
 */
public class TicketInfo implements SerializableRead, Externalizable {
    private static final long  serialVersionUID=1L; 
    private static DateFormat m_dateformat = new SimpleDateFormat("hh:mm");
 
    private String m_sId;
    private int m_iTicketId;
    private java.util.Date m_dDate;
    private Properties attributes;
    private UserInfo m_User;
    private CustomerInfoExt m_Customer;
    private WaiterInfo m_waiter;
    private PlacesInfo m_place;
    private FloorsInfo m_floor;
    private String m_sActiveCash;
    private List<TicketLineInfo> m_aLines;
    
    private List<PaymentInfo> payments; 
    private List<TicketTaxInfo> taxes;
  
    /** Creates new TicketModel */
    public TicketInfo() {
        m_sId = UUID.randomUUID().toString();
        m_iTicketId = 0; // incrementamos
        m_dDate = new Date();
        attributes = new Properties();
        m_User = null;
        m_Customer = null;
        m_waiter = null;
        m_place = null;
        m_floor = null;
        m_sActiveCash = null;
        m_aLines = new ArrayList<TicketLineInfo>(); // vacio de lineas
        
        payments = new ArrayList<PaymentInfo>();        
        taxes = null;
    }
    public void writeExternal(ObjectOutput out) throws IOException  {
        // This is only to serialize tickets that are not in the bag of outstanding tickets
        out.writeObject(m_sId);
        out.writeInt(m_iTicketId);    
        out.writeObject(m_Customer);
        out.writeObject(m_dDate);
        out.writeObject(attributes);
        out.writeObject(m_aLines);
        out.writeObject(m_waiter);
        out.writeObject(m_place);
        out.writeObject(m_floor);
        out.writeObject(m_User);
        out.writeObject(m_sActiveCash);
    }   
    
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // esto es solo para serializar tickets que no estan en la bolsa de tickets pendientes
        m_sId = (String) in.readObject();
        m_iTicketId = in.readInt();
        m_Customer = (CustomerInfoExt) in.readObject();
        m_dDate = (Date) in.readObject();
        attributes = (Properties) in.readObject();
        m_aLines = (List<TicketLineInfo>) in.readObject();
        m_waiter = (WaiterInfo) in.readObject();
        m_place = (PlacesInfo) in.readObject();
        m_floor = (FloorsInfo) in.readObject();
        m_User = (UserInfo) in.readObject();
        m_sActiveCash = (String) in.readObject();
        
        payments = new ArrayList<PaymentInfo>();        
        taxes = null;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sId = dr.getString(1);
        m_iTicketId = dr.getInt(2).intValue();
        m_dDate = dr.getTimestamp(3);
        m_sActiveCash = dr.getString(4);
        try {
            byte[] img = dr.getBytes(5);
            if (img != null) {
                attributes.loadFromXML(new ByteArrayInputStream(img));
            }
        } catch (IOException e) {
        } 
        m_User = new UserInfo(dr.getString(6), dr.getString(7)); 
        m_Customer = new CustomerInfoExt(dr.getString(8));
        m_waiter = null;
        m_place = null;
        m_floor = null;
        m_aLines = new ArrayList<TicketLineInfo>();
        
        payments = new ArrayList<PaymentInfo>(); 
        taxes = null;
    }
    
    public TicketInfo copyTicket() {
        TicketInfo t = new TicketInfo();

        t.m_iTicketId = m_iTicketId;
        t.m_dDate = m_dDate;
        t.m_sActiveCash = m_sActiveCash;
        t.attributes = (Properties) attributes.clone(); 
        t.m_User = m_User;
        t.m_Customer = m_Customer;
        t.m_waiter = m_waiter;
        t.m_place = m_place;
        t.m_floor = m_floor;
        t.m_aLines = new ArrayList<TicketLineInfo>(); 
        for (TicketLineInfo l : m_aLines) {
            t.m_aLines.add(l.copyTicketLine());
        }
        t.refreshLines();
        
        t.payments = new LinkedList<PaymentInfo>(); 
        for (PaymentInfo p : payments) {
            t.payments.add(p.copyPayment());
        }
        
        // taxes are not copied, must be calculated again.
        
        return t;
    }
    
    public String getId() {
        return m_sId;
    }
    
    public int getTicketId(){
        return m_iTicketId;
    }
    public void setTicketId(int iTicketId) {
        m_iTicketId = iTicketId;
        // refreshLines();
    }   
    
    public String getName(Object info) {
        
        StringBuffer name = new StringBuffer();
        
        if (getCustomerId() != null) {
            name.append(m_Customer.toString());
            name.append(" - ");
        }
        
        if (info == null) {
            if (m_iTicketId == 0) {
                name.append("(" + m_dateformat.format(m_dDate) + " " + Long.toString(m_dDate.getTime() % 1000) + ")");
            } else {
                name.append(Integer.toString(m_iTicketId));
            }
        } else {
            name.append(info.toString());
        }
        
        return name.toString();
    }
   // public String getlDate(){
  //    return  m_dDate.toString();
  //  }
    public String getName() {
        return getName(null);
    }
    
    public java.util.Date getDate() {
        return m_dDate;
    }
    public void setDate(java.util.Date dDate) { 
        m_dDate = dDate;
    }
    public UserInfo getUser() {
        return m_User;
    }    
    public void setUser(UserInfo value) {        
        m_User = value;
    }   
    
    public CustomerInfoExt getCustomer() {
        return m_Customer;
    }
    public void setCustomer(CustomerInfoExt value) {
        m_Customer = value;
    }
    public String getCustomerId() {
      /*  if (m_Customer == null) {
            return null;
        } else {
            String customer[] =m_Customer.getId().split("#");
            //if()
            if(customer.length>1)
            {
              /*  String name[]=m_Customer.getName().split(" : ");
                if(name.length>1)
                    ;
                else
                m_Customer.setName(m_Customer.getName()+" : "+customer[1]);
                return customer[0];
            }
            else*/
            return m_Customer.getId();
       // }
    }

    public WaiterInfo getWaiter() {
        return m_waiter;
    }
    public String getWaiterId() {
        if (m_waiter == null) {
            return null;
        } else {
            return m_waiter.getID();
        }
    }

    public PlacesInfo getPlace() {
        return m_place;
    }
    public String getPlaceId() {
        if (m_place == null) {
            return null;
        } else {
            return m_place.getID();
        }
    }

     public FloorsInfo getFloor() {
        return m_floor;
    }
    public String getFloorId() {
        if (m_floor == null) {
            return null;
        } else {
            return m_floor.getID();
        }
    }

    public void setWaiter(WaiterInfo waiter) {
        m_waiter = waiter;
    }
    public void setPlace(PlacesInfo place) {
        m_place = place;
    }
    public void setFloor(FloorsInfo floor) {
        m_floor = floor;
    }
    
    public void setActiveCash(String value) {     
        m_sActiveCash = value;
    }    
    public String getActiveCash() {
        return m_sActiveCash;
    }
    
    public String getProperty(String key) {
        return attributes.getProperty(key);
    }
    
    public String getProperty(String key, String defaultvalue) {
        return attributes.getProperty(key, defaultvalue);
    }
    
    public void setProperty(String key, String value) {
        attributes.setProperty(key, value);
    }
    
    public Properties getProperties() {
        return attributes;
    }
    
    public TicketLineInfo getLine(int index){
        return m_aLines.get(index);
    }

    public void deleteLine(int index){
         m_aLines.remove(index);
    }
    
    public void addLine(TicketLineInfo oLine) {

       oLine.setTicket(m_sId, m_aLines.size());
       m_aLines.add(oLine);
    }
    
    public void insertLine(int index, TicketLineInfo oLine) {
        m_aLines.add(index, oLine);
        refreshLines();        
    }
    
    public void setLine(int index, TicketLineInfo oLine) {
        oLine.setTicket(m_sId, index);
        m_aLines.set(index, oLine);     
    }
    
    public void removeLine(int index) {
        m_aLines.remove(index);
        refreshLines();        
    }
    
    private void refreshLines() {         
        for (int i = 0; i < m_aLines.size(); i++) {
            getLine(i).setTicket(m_sId, i);
        } 
    }
    
    public int getLinesCount() {
        return m_aLines.size();
    }
    
    public double getArticlesCount() {
        double dArticles = 0.0;
        TicketLineInfo oLine;
            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            dArticles += oLine.getMultiply();
        }
        
        return dArticles;
    }
    
    public double getSubTotal() {
        double sum = 0.0;
        for (TicketLineInfo line : m_aLines) {
            sum += line.getSubValue();
        } 
        
        return sum;
    }
    
    public double getTax() {

        double sum = 0.0;
        if (hasTaxesCalculated()) {
            for (TicketTaxInfo tax : taxes) {
                sum += tax.getTax();
            }            
        } else {                   
            for (TicketLineInfo line : m_aLines) {
                sum += line.getTax();
            }        
        }        
        return sum;
    }
    
    public double getTotal() { 
        /////////for getTax()/////////////////////////////////shiv
        AppView app=LookupUtilityImpl.getInstance(null).getAppView();
         Double tax=0.0;
       /* try {
            
            Object[] obj = (Object[]) new StaticSentence(app.getSession(), "SELECT RATEROUNDOFF FROM TAXES WHERE ID=?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                            if(obj[0].equals("yes")){
                                Object f= new Float(Math.round(getTax()));
                                String st= f.toString();
                              tax = new Double(st);
                            }else if(obj[0].equals("yesnearest")){
                                 Object f= new Float(Math.round(getTax()));
                                String st= f.toString();
                              tax = new Double(st);
                            }else if(obj[0].equals("yesnext")){
                                 Object f= new Float(Math.round(getTax())+1);
                                String st= f.toString();
                              tax = new Double(st);
                            }else if(obj[0].equals("yesprevious")){
                                 Object f= new Float(Math.round(getTax())-1);
                                 String st= f.toString();
                                  tax = new Double(st);
                            }else{
                                Object f= new Float(getTax());
                                 String st= f.toString();
                                 tax = new Double(st);
                               }   
        } catch (BasicException ex) {
            Logger.getLogger(TicketInfo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        /////////////////////////////////////////shiv
        return getSubTotal() + getTax();
    }
    
    ////SHIV ADDED
   
    /////////////////////////
    
    
    public double getTotalPaid() {
        
        double sum = 0.0;
        for (PaymentInfo p : payments) {
            if (!"debtpaid".equals(p.getName())) {
                sum += p.getTotal();
            }                    
        }
        return sum;
    }
    
    public List<TicketLineInfo> getLines() {
        return m_aLines;
    }    
    
    public void setLines(List<TicketLineInfo> l) {
        m_aLines = l;
    }
    
    public List<PaymentInfo> getPayments() {
        return payments;
    }
    
    public void setPayments(List<PaymentInfo> l) {
        payments = l;
    }
    
    public void resetPayments() {
        payments = new ArrayList<PaymentInfo>();
    }
    
    public List<TicketTaxInfo> getTaxes() {
        return taxes;        
    }
    
    public boolean hasTaxesCalculated() {
        return taxes != null;
    }
    
    public void setTaxes(List<TicketTaxInfo> l) {
        taxes = l;
    }
    
    public void resetTaxes() {
        taxes = null;
    }
    
    public TicketTaxInfo getTaxLine(TaxInfo tax) {
      
        for (TicketTaxInfo taxline : taxes) {            
            if (tax.getId().equals(taxline.getTaxInfo().getId())) {
                return taxline;
            }
        }
        
        return new TicketTaxInfo(tax);
    }
    
    @Deprecated
    public TicketTaxInfo[] getTaxLines() {
        
        return taxes.toArray(new TicketTaxInfo[taxes.size()]);
    }
    
    public String printId() {
        if (m_iTicketId > 0) {
            // valid ticket id
            return Formats.INT.formatValue(new Integer(m_iTicketId));
        } else {
            return "";
        }
    }
    public String printDate() {
        return Formats.TIMESTAMP.formatValue(m_dDate);
    }
    public String printUser() {
        return m_User == null ? "" : m_User.getName();
    }
    public String printCustomer() {
        return m_Customer == null ? "" : m_Customer.getName();
    }
    public String printArticlesCount() {
        return Formats.DOUBLE.formatValue(new Double(getArticlesCount()));
    }
    
    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
    public String printTax() {
        return Formats.CURRENCY.formatValue(new Double(getTax()));
    }    
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    public String printTotalPaid() {
        return Formats.CURRENCY.formatValue(new Double(getTotalPaid()));
    }
}

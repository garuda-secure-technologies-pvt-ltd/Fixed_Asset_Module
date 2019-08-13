package com.openbravo.pos.sales;

import java.util.*;
import com.openbravo.basic.BasicException;
//import com.openbravo.data.gui.FindInfo;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
//import java.sql.Timestamp;

/**
 *
 * @author a
 */
public class QTDTableModel {

   private List<qtdetailsline> dis;
   private AppView m_App;
    private final static String[] QTKHEADERS = {"Sl.No.","MemNo","Counter","Floor","Table","Waiter","QT Time","CreatedBy","PreparedTime","DeliveredTime","Reverse"};
  private QTDTableModel()
   {
   }

  public static QTDTableModel emptyinstance()
  {
      QTDTableModel d=new QTDTableModel();
      d.dis=new ArrayList<qtdetailsline>();
      return d;
  }
  public static QTDTableModel loadInstance(AppView app) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
       lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE BILLED = FALSE ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
       lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%')  WHERE BILLED = FALSE ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
       dlist.addAll(lst);
       dlist.addAll(lst_guest);
       
       
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  
  public static QTDTableModel loadInstanceDate(AppView app,String temp,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      for(int i=0;i<ct.length;i++){
        
          lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.crdate like '"+temp+"%' and q.counter = '"+ct[i]+"' ORDER BY q.delvrdtime desc"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
          lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%') WHERE q.crdate like '"+temp+"%' and q.counter = '"+ct[i]+"' ORDER BY q.delvrdtime desc"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
          
          
          dlist.addAll(lst);
          dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  
  public static QTDTableModel loadInstanceFloor(AppView app,String temp,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      Date da = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(da);
            String date = s.substring(0,10);
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      for(int i=0;i<ct.length;i++){
        
          lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.floor = '"+temp+"' and q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.floor"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
          lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%') WHERE q.floor = '"+temp+"' and q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.floor"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
          
          
          dlist.addAll(lst);
          dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceFloorAll(AppView app,String date,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      for(int i=0;i<ct.length;i++){
       
          lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.floor"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       
          lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%')  WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.floor"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       
          dlist.addAll(lst);
          dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceCounter(AppView app,String temp) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
         Date da = new Date();
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(da);
            String date = s.substring(0,10);
      
             lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+temp+"' and q.crdate like '"+date+"%' ORDER BY q.counter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
             
             lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%')  WHERE q.counter = '"+temp+"' and q.crdate like '"+date+"%' ORDER BY q.counter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
         dlist.addAll(lst);    
         dlist.addAll(lst_guest);    
            
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceCounterAll(AppView app,String date,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      for(int i=0;i<ct.length;i++){
       lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.counter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%')  WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.counter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       
       dlist.addAll(lst);
       dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceWaiter(AppView app,String temp) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      Date da = new Date();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(da);
            String date = s.substring(0,10);
         
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();      
            
      lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.waiter = (select id from waiter where name = '"+temp+"') and q.crdate like '"+date+"%' ORDER BY q.waiter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
      lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gue%')  WHERE q.waiter = (select id from waiter where name = '"+temp+"') and q.crdate like '"+date+"%' ORDER BY q.waiter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
      dlist.addAll(lst);
      dlist.addAll(lst_guest);
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceWaiterAll(AppView app,String date,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      for(int i=0;i<ct.length;i++){
       
       lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.waiter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       
       lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gues%')  WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+date+"%' ORDER BY q.waiter"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
       
       
       
       dlist.addAll(lst);
       dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  public static QTDTableModel loadInstanceMemNo(AppView app,String temp) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      
      Date da = new Date();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(da);
            String date = s.substring(0,10);
      
       lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.customer = (select id from customers where searchkey like '"+temp+"') and q.crdate like '"+date+"%' ORDER BY q.crdate"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
       
       lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.customer like concat((select id from customers where searchkey like '"+temp+"'),'#gue%')  and q.crdate like '"+date+"%' ORDER BY q.crdate"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
      
       dlist.addAll(lst);
       dlist.addAll(lst_guest);
       
       
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  
  public static QTDTableModel loadInstanceMemNoAll(AppView app,String temp,String[] ct) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_guest = new ArrayList();
      for(int i=0;i<ct.length;i++){
        lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+temp+"%' ORDER BY c.searchkey"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
        lst_guest = new StaticSentence(app.getSession()
                ,"select concat(c.searchkey,'-Guest'),r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen_arv q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer like concat(c.id,'#gues%')  WHERE q.counter = '"+ct[i]+"' and q.crdate like '"+temp+"%' ORDER BY c.searchkey"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list();
        
        dlist.addAll(lst);
        dlist.addAll(lst_guest);
      }
      
     if(dlist==null){
         d.dis=new ArrayList<qtdetailsline>();
     }
     else{
         d.dis=dlist;
     }
     return d;
  }
  
  
  
  
  public static QTDTableModel loadInstance1(AppView app, String[] lst) throws BasicException {
      QTDTableModel d = new QTDTableModel();
      List dlist = new ArrayList();
         
      for(int i=0;i<lst.length;i++){
      dlist = new StaticSentence(app.getSession()
                ,"select f.name,p.name,w.name,crdate,createdby,prepared,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE and createdby = ? ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTDTableModel.qtdetailsline.class )).list(lst[i]);
      }
      
     if(dlist==null)
     {
         d.dis=new ArrayList<qtdetailsline>();
     }
     else
     {
         d.dis=dlist;
     }

     return d;

  }
  public List<qtdetailsline> getqtdetailsline()
     {
         return dis;
     }
  public AbstractTableModel getqtdetailsTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(QTKHEADERS[column]);
            }
            public int getRowCount() {
                return dis.size();
            }
            public int getColumnCount() {

                return QTKHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                qtdetailsline l = dis.get(row);

                switch (column) {

                 case 0 : return row + 1;
                case 1: return l.getcustomerid();
                case 2: return l.getcounter();
                case 3: return l.getfloor();
                case 4: return l.getplace();
                case 5: return l.getwaiter();
                case 6: return l.getcrdate();
                case 7: return l.getcreatedby();
                case 8: return l.getPrepdTime();
                case 9: return l.getDelrvdTime();
                case 10: return l.isSelectedReverse();
                default: return null;
                }
            }
        };
    }


public static class qtdetailsline implements SerializableRead{
    private String id;
    private String customerid;
    private String counter;
    private String waiter;
    private String place;
    private String floor;
    private String prcategory;
    private String createdby;
    private Timestamp crdate;
    private String prepdtime;
    private String delrvdtime;
    private boolean reverse;
    public void readValues(DataRead dr) throws BasicException
    {

        customerid = dr.getString(1);
        counter = dr.getString(2);
        floor=dr.getString(3);
        place=dr.getString(4);
        waiter=dr.getString(5);
        crdate=dr.getTimestamp(6);
        createdby=dr.getString(7);
        prepdtime = dr.getString(8);
        delrvdtime = dr.getString(9);
        id=dr.getString(10);
        reverse = false;
    }
 ////////////////////////////////////////////////////>>>s
    
    public boolean isSelectedReverse() {
            return reverse;
    }

    public void setStatusReverse(boolean status) {
            reverse = status;
    }
 ////////////////////////////////////////////////////>>>e
    
    public String getPrepdTime() {
            return prepdtime;
    }

    public void setPrepdTime(String status) {
            prepdtime = status;
    }
    
    public String getDelrvdTime() {
            return delrvdtime;
    }

    public void setDelrvdTime(String status) {
            delrvdtime = status;
    }
    
    public Timestamp getcrdate(){
      return crdate;
    }
    public String getcreatedby(){
        return createdby;
    }
    public String getid() {
     return id;
    }
    public String getprcategory(){
       return prcategory;
    }
    public String getcustomerid()
    {
        return customerid;
    }
    public String getcounter()
    {
        return counter;
    }
    public String getwaiter()
    {
        return waiter;
    }
    public String getplace()
    {
         /* ProductInfo pInfo = LookupUtilityImpl.getInstance(null).getProductsMap().get(productid);
        return pInfo.getName();*/
        return place;
    }
    public String getfloor()
    {
        return floor;
    }
     public String printcustomerid()
    {
        return customerid;
    }
     public String printcounter()
    {
        return counter;
    }
    public String printwaiter()
    {
        return waiter;
    }
    public String printplace()
    {
        return place;
    }
    public String printfloor()
    {
        return floor;
    }
    public String printprcategory()
    {
        return prcategory;
    }

}



/////////////////////////////////////////////////////////////////


public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {

            @Override
            public String getColumnName(int column) {
                //return AppLocal.getIntString(QTKHEADERS[column]);
                return (QTKHEADERS[column]);
            }

            public int getRowCount() {
                return dis.size();
            }

            public int getColumnCount() {

                return QTKHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                qtdetailsline l = dis.get(row);
                if (column == 10) {
                  if(JOptionPane.showConfirmDialog(null, "Are you sure you want to reverse", "Reverse To QTList", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        try {
                            AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();
                            new PreparedSentence(m_App.getSession(), "INSERT INTO QTKITCHEN (ID, CUSTOMER, PLACE, WAITER, FLOOR, BILLED, COUNTER, CREATEDBY, CRDATE, PREPTIME, DELVRDTIME, PREPARED) SELECT Q.ID,Q.CUSTOMER,Q.PLACE,Q.WAITER,Q.FLOOR,Q.BILLED,Q.COUNTER,Q.CREATEDBY,Q.CRDATE, Q.PREPTIME, Q.DELVRDTIME,'0' FROM QTKITCHEN_ARV Q WHERE Q.ID = '"+l.getid()+"'", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                            new PreparedSentence(m_App.getSession(), "DELETE FROM QTKITCHEN_ARV WHERE ID= '"+l.getid()+"' ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                            dis.remove(row);
                        } catch (BasicException ex) {
                            Logger.getLogger(QTDTableModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                  l.setStatusReverse(Boolean.parseBoolean(aValue.toString()));
                 fireTableDataChanged();
                }
            }

            public Object getValueAt(int row, int column) {
                qtdetailsline l = dis.get(row);

                switch (column) {

                    case 0 : return row + 1;
                case 1: return l.getcustomerid();
                case 2: return l.getcounter();
                case 3: return l.getfloor();
                case 4: return l.getplace();
                case 5: return l.getwaiter();
                case 6: return l.getcrdate();
                case 7: return l.getcreatedby();
                case 8: return l.getPrepdTime();
                case 9: return l.getDelrvdTime();
                case 10: return l.isSelectedReverse();

                    default:
                        return null;
                }
            }
        };
    }

}

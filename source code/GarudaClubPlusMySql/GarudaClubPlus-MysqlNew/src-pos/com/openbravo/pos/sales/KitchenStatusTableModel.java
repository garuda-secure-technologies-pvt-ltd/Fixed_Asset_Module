/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppUser;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author a
 */
public class KitchenStatusTableModel {
   private List<qtdetailsline> dis;
   private AppView m_App;
    private final static String[] QTKHEADERS = {"Sl.No.","MemNo","Counter","Floor","Table","Waiter","QT Time","CreatedBy","PreparedTime"};
  private KitchenStatusTableModel()
   {
   }

  public static KitchenStatusTableModel emptyinstance()
  {
      KitchenStatusTableModel d=new KitchenStatusTableModel();
      d.dis=new ArrayList<qtdetailsline>();
      return d;
  }
  public static KitchenStatusTableModel loadInstance(AppView app) throws BasicException {
      KitchenStatusTableModel d = new KitchenStatusTableModel();
//     List dlist = new StaticSentence(app.getSession()
//                ,"select f.name,p.name,w.name,crdate,createdby,prcategory,q.id from  QTicket q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE ORDER BY q.ID"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
      Date da = new Date();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = dateFormat.format(da);
            String date = s.substring(0,10);
      
      List dlist = null;
      AppUser a = app.getAppUserView().getUser();
      String rl = app.getAppUserView().getUser().getRole();
      String s1 = a.toString();
      Object[]obj = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT NAME FROM ROLES WHERE ID = '"+rl+"'"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
//      Object[]obj1 = (Object[]) new StaticSentence(app.getSession()
//               ,"SELECT RCOUNTERS FROM QTKASSIGN WHERE ID = '"+obj[0].toString()+"'"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      if(obj != null){
          
//            String data= obj1[0].toString();
//            String[] strtemp= Formats.STRING.formatValue(obj1[0]).split("\r\n");
            
//                for(int i=0;i<strtemp.length;i++){
//                    AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
//                
//                if(obj!=null){
//                    data=data+obj[0]+"\r\n";
//                    }
//                }
              
       dlist = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,preptime,delvrdtime,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+obj[0].toString()+"' and q.crdate like '"+date+"%' and q.prepared = '1' ORDER BY q.preptime desc"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( KitchenStatusTableModel.qtdetailsline.class )).list();
       
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
  public static KitchenStatusTableModel loadInstance1(AppView app, String lst) throws BasicException {
      KitchenStatusTableModel d = new KitchenStatusTableModel();
      List dlist = null;
   //   for(int i=0;i<lst.length;i++){
//        dlist = new StaticSentence(app.getSession()
//                ,"select f.name,p.name,w.name,crdate,createdby,prcategory,q.id from  QTicket q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE and createdby = ? ORDER BY q.ID"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
 //     }
      
      dlist = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+lst+"' ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( KitchenStatusTableModel.qtdetailsline.class )).list(lst);
      
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
    }
 
    
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
                //return AppLocal.getIntString(FACILITYHEADERS[column]);
                return (QTKHEADERS[column]);
            }

            public int getRowCount() {
                return dis.size();
            }

            public int getColumnCount() {

                return QTKHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false
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

                    default:
                        return null;
                }
            }
        };
    }


}

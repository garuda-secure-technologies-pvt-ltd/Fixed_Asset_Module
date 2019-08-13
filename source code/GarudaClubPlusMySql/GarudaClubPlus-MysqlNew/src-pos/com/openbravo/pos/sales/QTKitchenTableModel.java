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
public class QTKitchenTableModel {
   private List<qtdetailsline> dis;
   private AppView m_App;
    private final static String[] QTKHEADERS = {"Sl.No.","MemNo","QT No.","Counter","Floor","Table","Waiter","QT Time","CreatedBy","Print","Prepared","Delivered"};
  private QTKitchenTableModel()
   {
   }

  public static QTKitchenTableModel emptyinstance()
  {
      QTKitchenTableModel d=new QTKitchenTableModel();
      d.dis=new ArrayList<qtdetailsline>();
      return d;
  }
  public static QTKitchenTableModel loadInstance(AppView app) throws BasicException {
      QTKitchenTableModel d = new QTKitchenTableModel();
//     List dlist = new StaticSentence(app.getSession()
//                ,"select f.name,p.name,w.name,crdate,createdby,prcategory,q.id from  QTicket q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE ORDER BY q.ID"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_Guest = new ArrayList();
      AppUser a = app.getAppUserView().getUser();
      String s = a.toString();
      Object[]obj = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT ROLE FROM PEOPLE WHERE NAME = '"+s+"'"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      Object[]obj1 = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT RCOUNTERS FROM QTKASSIGN WHERE ID = '"+obj[0].toString()+"'"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      if(obj1 != null){
          
            String data= obj1[0].toString();
            String[] strtemp= Formats.STRING.formatValue(obj1[0]).split("\r\n");
            
//                for(int i=0;i<strtemp.length;i++){
//                    AppView m_App=LookupUtilityImpl.getInstance(null).getAppView();
//                
//                if(obj!=null){
//                    data=data+obj[0]+"\r\n";
//                    }
//                }
              
      for(int i=0;i<strtemp.length;i++){
      // lst = new StaticSentence(app.getSession()
             //   ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+strtemp[i]+"' ORDER BY q.crdate"
             // ,SerializerWriteString.INSTANCE
             // ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
       // ********** CHANGED BY ##AKASH FOR DISPLAYING GUEST IN QTKITCHEN
       lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER =c.id and q.counter = '"+strtemp[i]+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
       lst_Guest = new StaticSentence(app.getSession()
                ,"select CONCAT(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER like concat(c.id,'#gu%') and q.counter = '"+strtemp[i]+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
       
       
       dlist.addAll(lst);
       
       if(lst_Guest.size()>0){
           dlist.addAll(lst_Guest);
       } 
       
       
      }
      
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
  public static QTKitchenTableModel loadInstance1(AppView app, String lst) throws BasicException {
      QTKitchenTableModel d = new QTKitchenTableModel();
      
      List dlist = new ArrayList();
      List Lst = new ArrayList();
      List Lst_Guest = new ArrayList();
   //   for(int i=0;i<lst.length;i++){
//        dlist = new StaticSentence(app.getSession()
//                ,"select f.name,p.name,w.name,crdate,createdby,prcategory,q.id from  QTicket q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE and createdby = ? ORDER BY q.ID"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
 //     }
      
    //  dlist = new StaticSentence(app.getSession()
                //,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+lst+"' ORDER BY q.ID"
            //  ,SerializerWriteString.INSTANCE
            //  ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
      
      
      Lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER=c.id and q.counter = '"+lst+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
      
      
      Lst_Guest = new StaticSentence(app.getSession()
                ,"select CONCAT(c.searchkey,'-Guest') ,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER like concat(c.id,'#gue%') and q.counter = '"+lst+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
      
      
      
      dlist.addAll(Lst);
      dlist.addAll(Lst_Guest);
      
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
  
  
  public static QTKitchenTableModel loadInstance2(AppView app,List l) throws BasicException {
      QTKitchenTableModel d = new QTKitchenTableModel();
      List dlist = new ArrayList();
      List lst = new ArrayList();
      List lst_Guest = new ArrayList();
   //   for(int i=0;i<lst.length;i++){
//        dlist = new StaticSentence(app.getSession()
//                ,"select f.name,p.name,w.name,crdate,createdby,prcategory,q.id from  QTicket q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join customers c on q.customer=c.id WHERE BILLED = FALSE and createdby = ? ORDER BY q.ID"
//              ,SerializerWriteString.INSTANCE
//              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list(lst);
 //     }
                
                for(int i=0;i<l.size();i++){
                    String st = l.get(i).toString();
                    Object[]obj = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT ID FROM ROLES WHERE NAME = '"+st+"'"
              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
                    
//                    Object[]obj = (Object[]) new StaticSentence(app.getSession()
//               ,"SELECT ROLE FROM PEOPLE WHERE NAME = '"+o[0].toString()+"'"
//              ,SerializerWriteString.INSTANCE,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      Object[]obj1 = (Object[]) new StaticSentence(app.getSession()
               ,"SELECT RCOUNTERS FROM QTKASSIGN WHERE ID = '"+obj[0].toString()+"'"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
      if(obj1 != null){
            String[] strtemp = Formats.STRING.formatValue(obj1[0]).split("\r\n");
      for(int j=0;j<strtemp.length;j++){
     // lst = new StaticSentence(app.getSession()
             //   ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  QTkitchen q join places p on  q.place=p.id join waiter w on q.waiter=w.id join floors f on q.floor=f.id join roles r on (SELECT ROLE FROM people where NAME = q.createdby)=r.id join customers c on q.customer=c.id WHERE q.counter = '"+strtemp[j]+"' ORDER BY q.crdate"
            //  ,SerializerWriteString.INSTANCE
            //  ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
      lst = new StaticSentence(app.getSession()
                ,"select c.searchkey,r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER =c.id and q.counter = '"+strtemp[j]+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
      lst_Guest = new StaticSentence(app.getSession()
                ,"select CONCAT(c.searchkey,'-Guest'),r.name,f.name,p.name,w.name,crdate,createdby,prepared,q.id from  \n" +
                    "QTkitchen q , places p,waiter w,floors f ,roles r ,customers c  WHERE   q.place=p.id  and  q.waiter=w.id \n" +
                    "and q.floor=f.id and  (SELECT ROLE FROM people where\n" +
                    "NAME = q.createdby)=r.id and  q.CUSTOMER like concat(c.id,'#gu%') and q.counter = '"+strtemp[j]+"'    \n" +
                    "ORDER BY q.ID"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKitchenTableModel.qtdetailsline.class )).list();
      
      
      dlist.addAll(lst);
      
      if(lst_Guest.size()>0){
          dlist.addAll(lst_Guest);
      }
      
      
      
      
      }
      }
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
                case 2: return l.getid();
                case 3: return l.getcounter();
                case 4: return l.getfloor();
                case 5: return l.getplace();
                case 6: return l.getwaiter();
                case 7: return l.getcrdate();
                case 8: return l.getcreatedby();
                case 9: return l.isSelectedPrint();
                case 10: return l.isSelectedPrepd();
                case 11: return l.isSelectedDelrvd();
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
    private boolean selectedprint;
    private boolean selectedprepd;
    private boolean selecteddelrvd;
    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(9);
    //    createdby=dr.getString(4);
        waiter=dr.getString(5);
        customerid = dr.getString(1);
        counter = dr.getString(2);
        place=dr.getString(4);
        floor=dr.getString(3);
  //      prcategory=dr.getString(6);
        createdby=dr.getString(7);
        crdate=dr.getTimestamp(6);
        selectedprint = false;
        selectedprepd = dr.getBoolean(8);
        selecteddelrvd = false;
    }
 ////////////////////////////////////////////////////>>>s
    public boolean isSelectedPrint() {
            return selectedprint;
    }

    public void setStatusPrint(boolean status) {
            selectedprint = status;
    }
    
    public boolean isSelectedPrepd() {
            return selectedprepd;
    }

    public void setStatusPrepd(boolean status) {
            selectedprepd = status;
    }
    
    public boolean isSelectedDelrvd() {
            return selecteddelrvd;
    }

    public void setStatusDelrvd(boolean status) {
            selecteddelrvd = status;
    }
 
////////////////////////////////////////////////////>>>e
    
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
                java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false,false, false, false, false, false, false, false, true, true, true
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
                if (column == 9) {
                    QTKitchen qtk = new QTKitchen();
                    qtk.qtReprint(l.getid());
               //     l.setStatusPrint(Boolean.parseBoolean(aValue.toString()));
                 fireTableDataChanged();
                } else if (column == 10) {
        
          
                  try{
                        AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();  
                  new PreparedSentence(m_App.getSession(), "UPDATE QTKITCHEN SET PREPARED= "+aValue.toString()+" WHERE ID= '"+l.getid()+"' ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                  
                        if(Boolean.parseBoolean(aValue.toString())){
                            Date d = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String sd = dateFormat.format(d);
                        //    String sd = d.toString();
                  new PreparedSentence(m_App.getSession(), "UPDATE QTKITCHEN SET PREPTIME= '"+sd+"' WHERE ID= '"+l.getid()+"' ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec();
                        }
                        
                  }catch(Exception e){
                   e.printStackTrace();
                   }
                        
                       
                    l.setStatusPrepd(Boolean.parseBoolean(aValue.toString()));
                 fireTableDataChanged();
                 
                 
                 
                } else if (column == 11) {
                    if(Boolean.parseBoolean(aValue.toString())){
                     if(!l.isSelectedPrepd()){
                   JOptionPane.showMessageDialog(null, "It is not prepared to be Delivered", "MESSAGE", JOptionPane.WARNING_MESSAGE);
                     } else {
                        if (JOptionPane.showConfirmDialog(null, "Are you sure it is Delivered", "MESSAGE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            l.setStatusDelrvd(true);
                            dis.remove(row);
                            
                            
                            
                            
                            try{        
                                AppView m_App= LookupUtilityImpl.getInstance(null).getAppView();
                                Date d = new Date();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String sd = dateFormat.format(d);
                           //     String sd = d.toString();
                                new PreparedSentence(m_App.getSession(), "UPDATE QTKITCHEN SET DELVRDTIME= '"+sd+"' WHERE ID= '"+l.getid()+"' ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP})).exec();
                                new PreparedSentence(m_App.getSession(), "INSERT INTO QTKITCHEN_ARV (ID, CUSTOMER, PLACE, WAITER, FLOOR, BILLED, COUNTER, CREATEDBY, CRDATE, PREPTIME, DELVRDTIME) SELECT Q.ID,Q.CUSTOMER,Q.PLACE,Q.WAITER,Q.FLOOR,Q.BILLED,Q.COUNTER,Q.CREATEDBY,Q.CRDATE, Q.PREPTIME, Q.DELVRDTIME FROM QTKITCHEN Q WHERE Q.ID = '"+l.getid()+"'", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();
                                new PreparedSentence(m_App.getSession(), "DELETE FROM QTKITCHEN WHERE ID= '"+l.getid()+"' ", new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec();

                            }catch(Exception e){
                             e.printStackTrace();
                             }

                            
                            
                            
                            
                  
                        }
                    }

//                    dis.remove(row);
//                    boolean status = Boolean.parseBoolean(aValue.toString());
//                    if (status) {
//                        l.setStatusDelrvd(status);
//                    } else {
//                        l.setStatusDelrvd(status);
//                    }
                    fireTableDataChanged();
                  }
                }
            }

            public Object getValueAt(int row, int column) {
                qtdetailsline l = dis.get(row);

                switch (column) {

                    case 0 : return row + 1;
                case 1: return l.getcustomerid();
                case 2: return l.getid();
                case 3: return l.getcounter();
                case 4: return l.getfloor();
                case 5: return l.getplace();
                case 6: return l.getwaiter();
                case 7: return l.getcrdate();
                case 8: return l.getcreatedby();
                case 9: return l.isSelectedPrint();
                case 10: return l.isSelectedPrepd();
                case 11: return l.isSelectedDelrvd();

                    default:
                        return null;
                }
            }
        };
    }


}

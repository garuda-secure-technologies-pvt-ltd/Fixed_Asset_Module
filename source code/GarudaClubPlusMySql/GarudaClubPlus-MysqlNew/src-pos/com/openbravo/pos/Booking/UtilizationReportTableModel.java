
package com.openbravo.pos.Booking;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilizationReportTableModel extends BeanFactoryDataSingle{
    private Session s;
    private int bookedHallLength;
    private int bookedHallDetailSize;
    private List<UtilizationReportTableModel.HallStatusInfo> status_data;
    private List<UtilizationReportTableModel.HallStatusInfo_MemID> status_data1;
  
     
     
    @Override
    public void init(Session s) {
        this.s=s;
    }
    
    
      public static UtilizationReportTableModel loadInstanceBooked_Hall_Status(AppView app , Date FrmDate , Date ToDate)throws BasicException{
         UtilizationReportTableModel Booked_Hall_Status = new UtilizationReportTableModel(); 
         
          try{
            Booked_Hall_Status.status_data = new ArrayList<UtilizationReportTableModel.HallStatusInfo>();
            Booked_Hall_Status.status_data = new StaticSentence(app.getSession(), "SELECT STATUS , BOOKED FROM\n" +
                                                                                    "(SELECT 'BOOKED' AS STATUS  , COUNT(*) AS BOOKED  FROM hall_booked_details H\n" +
                                                                                    "WHERE H.STATUS=2 AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?\n" +
                                                                                    "union\n" +
                                                                                    "SELECT 'BLOCKED' AS STATUS , COUNT(*) AS BOOKED  FROM hall_booked_details H\n" +
                                                                                    "WHERE H.STATUS=3  AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?\n" +
                                                                                    ")\n" +
                                                                                    " as TEMP", new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  }) ,new SerializerReadClass(UtilizationReportTableModel.HallStatusInfo.class)).list(new Object[]{ FrmDate ,  ToDate , FrmDate ,  ToDate  });

            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(UtilizationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
    
      
       public static UtilizationReportTableModel loadInstanceBooked_Hall_Status_HallID(AppView app , Date FrmDate , Date ToDate , String HallID)throws BasicException{
         UtilizationReportTableModel Booked_Hall_Status = new UtilizationReportTableModel(); 
         
          try{
            Booked_Hall_Status.status_data1 = new ArrayList<UtilizationReportTableModel.HallStatusInfo_MemID>();
            Booked_Hall_Status.status_data1 = new StaticSentence(app.getSession(), "SELECT  'BOOKED' , (SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=2 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?) AS STATUS1 ,\n" +
                                                                                    "(SELECT DATEDIFF(?,?)) AS AVAILABLE\n" +
                                                                                    "FROM hall_master M where M.active=true and M.NAME=?\n" +
                                                                                    "union\n" +
                                                                                    "SELECT  'BLOCKED' , (SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=3 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?) AS STATUS1 ,\n" +
                                                                                    "(SELECT DATEDIFF(?,?)) AS AVAILABLE\n" +
                                                                                    "FROM hall_master M where M.active=true and M.NAME=?\n" +
                                                                                    "\n" +
                                                                                    "union\n" +
                                                                                    "SELECT  'Partially Blocked' , (SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=4 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?) AS STATUS1 ,\n" +
                                                                                    "(SELECT DATEDIFF(?,?)) AS AVAILABLE\n" +
                                                                                    "FROM hall_master M where M.active=true and M.NAME=?\n" +
                                                                                    "union\n" +
                                                                                    "\n" +
                                                                                    "select 'Available' , SUM((SELECT DATEDIFF(?,?))-(SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=2 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?) -\n" +
                                                                                    "(SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=3 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?) -\n" +
                                                                                    "(SELECT  COUNT(DISTINCT H.BOOKING_DATE)  FROM hall_booked_details H WHERE H.STATUS=4 AND M.ID=H.HALL_NAME\n" +
                                                                                    "AND H.BOOKING_DATE_EX >=? AND H.BOOKING_DATE_EX <=?)) AS STATUS1 ,\n" +
                                                                                    "(SELECT DATEDIFF(?,?)) AS AVAILABLE\n" +
                                                                                    "FROM hall_master M where M.active=true and M.NAME=?",
                                                                                     new SerializerWriteBasic(new Datas[]{ Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP , Datas.STRING , Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP  ,  Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.TIMESTAMP , Datas.TIMESTAMP ,  Datas.TIMESTAMP , Datas.TIMESTAMP  , Datas.STRING  }) 
                                                                                    ,new SerializerReadClass(UtilizationReportTableModel.HallStatusInfo_MemID.class)).list(new Object[]{  FrmDate , ToDate , ToDate , FrmDate , HallID ,FrmDate , ToDate , ToDate , FrmDate , HallID , FrmDate , ToDate , ToDate , FrmDate , HallID , ToDate , FrmDate , FrmDate , ToDate , FrmDate , ToDate , FrmDate , ToDate , ToDate , FrmDate , HallID });

            Booked_Hall_Status.bookedHallLength = Booked_Hall_Status.status_data1.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(UtilizationReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return Booked_Hall_Status;
         
     }
      
      
    
      
       public List<UtilizationReportTableModel.HallStatusInfo> getHallList(){
           if(status_data!=null)
        {
            return status_data;
        }
        else
            return new ArrayList<UtilizationReportTableModel.HallStatusInfo>();
      }
      
    
       
       
       
        
      public static class HallStatusInfo implements SerializableRead,IKeyed {
          
          private String HALL_NAME;
          private int STATUS;
          
          
     
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String gethall_name(){
              return HALL_NAME;
          }
          public void setHall_name(String HALL_NAME){
              this.HALL_NAME=HALL_NAME;
          }
          
         
          
        public void readValues(DataRead dr) throws BasicException {
           
            HALL_NAME = dr.getString(1);
            STATUS = dr.getInt(2);
           
        }

        public Object getKey() {
           return this;
        }
          
    }
      
      
      
       public List<UtilizationReportTableModel.HallStatusInfo_MemID> getHallList_withID(){
           if(status_data1!=null)
        {
            return status_data1;
        }
        else
            return new ArrayList<UtilizationReportTableModel.HallStatusInfo_MemID>();
      }
      
      
      
      
      // FOR PERTICULAR MEMBER .... #AAKASH
      
        public static class HallStatusInfo_MemID implements SerializableRead,IKeyed {
          
          private String Label;
          private int STATUS;
          private int total;
          
     
          public int getStatus(){
              return STATUS;
          }
          public void setStatus(int STATUS){
              this.STATUS=STATUS;
          }
          
           public String getLabel(){
              return Label;
          }
          public void setLabel(String Label){
              this.Label=Label;
          }
          
          public int gettotal(){
              return total;
          }
          public void settotal(int total){
              this.total=total;
          }
          
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            Label = dr.getString(1);
            STATUS = dr.getInt(2);
            total = dr.getInt(3);
            
        }

        public Object getKey() {
           return this;
        }
          
    }
      
        
        
      
    
}

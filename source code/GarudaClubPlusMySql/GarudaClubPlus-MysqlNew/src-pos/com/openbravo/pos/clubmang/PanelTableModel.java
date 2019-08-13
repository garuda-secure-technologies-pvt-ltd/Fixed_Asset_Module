/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.pos.Accounts.DueListNoticeTableModel;
import com.openbravo.pos.Accounts.DueListNoticeTableModel.FacilityLine;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.lang.String;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.eclipse.swt.graphics.Path;
/**
 *
 * @author user
 */
public class PanelTableModel extends BeanFactoryDataSingle{
    
    protected Session s;
  
    private final static String[] TABLEHEADERS = {"Image Name","Link To Web","Event Date","Notes", "Panel Name" , "Active" , "From Date" ,"To Date" , "Time Interval" };
    private final static String[] TABLEHEADERS2 = { "Advetiser Name" , "Path" , "Link To Web"  , "Notes" , "Panel Name" , "Active" , "From Date" , "To Date" , "Time In Interval" };
    private List<PanelTableModel.PanelTableInfo> data;
    private List<PanelTableModel.AdvertizeInfo> data2;
    private List<PanelTableInfo> getPath;
    private String LinkToImg;
    private List<AdvertizeInfo> getAddPath;
    private int panelonelength;
    public PanelTableModel() {
    }
    
    
    

    public PanelTableModel(List<PanelTableInfo> data) {
        this.data = data;
    }
    
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
     public static PanelTableModel loadInstancePanelInfo(AppView app) throws BasicException{
        PanelTableModel PanelInfo = new PanelTableModel(); 
           
        try{
            PanelInfo.data = new ArrayList<PanelTableInfo>();
            PanelInfo.data = new StaticSentence(app.getSession(), " SELECT  PATH ,LINKTOWEB , EVENTDATE , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL, ID FROM panelone ORDER BY EVENTDATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.PanelTableInfo.class)).list();
            List<PanelTableInfo> l = new StaticSentence(app.getSession(), " SELECT PATH ,LINKTOWEB , EVENTDATE , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL, ID FROM paneltwo ORDER BY EVENTDATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.PanelTableInfo.class)).list();
            PanelInfo.panelonelength = PanelInfo.data.size();
            PanelInfo.data.addAll(l);
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(PanelTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        
       
          return PanelInfo;
     }
     
     public static PanelTableModel loadInstancePanelInfoActivated(AppView app) throws BasicException{
        PanelTableModel PanelInfo = new PanelTableModel(); 
           
        try{
            PanelInfo.data = new ArrayList<PanelTableInfo>();
            PanelInfo.data = new StaticSentence(app.getSession(), " SELECT  PATH ,LINKTOWEB , EVENTDATE , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL , ID FROM panelone WHERE ACTIVE = TRUE ORDER BY EVENTDATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.PanelTableInfo.class)).list();
            List<PanelTableInfo> l = new StaticSentence(app.getSession(), " SELECT PATH ,LINKTOWEB , EVENTDATE , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL , ID FROM paneltwo WHERE ACTIVE = TRUE ORDER BY EVENTDATE ", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.PanelTableInfo.class)).list();
            PanelInfo.panelonelength = PanelInfo.data.size();
            PanelInfo.data.addAll(l);
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(PanelTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        
       
          return PanelInfo;
     }
     
     
     
     
     
      public static PanelTableModel loadInstanceAdvertizementInfo (AppView app) throws BasicException{
        PanelTableModel AdInfo = new PanelTableModel(); 
           
        try{
            AdInfo.data2= new ArrayList<AdvertizeInfo>();
            AdInfo.data2 = (ArrayList<AdvertizeInfo>) new StaticSentence(app.getSession(), "SELECT  AdvertiserName , PATH ,LINKTOWEB  , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL , ID FROM panelthree ", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.AdvertizeInfo.class)).list();
          
           // AdvertizeInfo.data2.add(AdInfo);
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(PanelTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
          return AdInfo;
     }
    public static PanelTableModel loadInstanceAdvertizementInfoActived (AppView app) throws BasicException{
        PanelTableModel AdInfo = new PanelTableModel(); 
           
        try{
            AdInfo.data2= new ArrayList<AdvertizeInfo>();
            AdInfo.data2 = (ArrayList<AdvertizeInfo>) new StaticSentence(app.getSession(), "SELECT  AdvertiserName , PATH ,LINKTOWEB , NOTES , PANELNAME , Active , FROMDATE , TODATE , TIMEINTERVAL , ID FROM panelthree WHERE ACTIVE = TRUE", SerializerWriteString.INSTANCE, new SerializerReadClass(PanelTableModel.AdvertizeInfo.class)).list();
          
           // AdvertizeInfo.data2.add(AdInfo);
        
        
        }
        catch(BasicException ex){
            
            Logger.getLogger(PanelTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     
          return AdInfo;
     }
    
     
     

    public List<PanelTableInfo> getData() {
        return data;
    }

    public void setData(List<PanelTableInfo> data) {
        this.data = data;
    }
    
    public List<AdvertizeInfo> getDate(){
        return data2;
    }
    public void setDate (List<AdvertizeInfo> data2){
        this.data2 = data2;
    }
    
    
     public  BufferedImage load(int row,int col){
                BufferedImage bi = (BufferedImage) getTableModel().getValueAt(row, col);
                
               return bi;
            }
            
    
   public  AbstractTableModel getTableModel()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class, java.lang.String.class, java.security.Timestamp.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.security.Timestamp.class , java.security.Timestamp.class , java.lang.Integer.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false ,false , false 
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
           
            
            
            
            
            public Object getValueAt(int rowIndex, int columnIndex) {
              PanelTableInfo r =data.get(rowIndex);
               SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                
               switch(columnIndex){
                   
                  
                   case 0: return r.getPath();
                   case 1: return r.getLink();
                  
                   case 2: 
                       
                       if(r.getEventDate()!=null)
                       {
                           try
                           {
                       String s = (sdf.format(r.getEventDate())).toString();
                               return s;
                               }
                           catch(Exception e)
                           {
                               return "";
                           }
                       }
                       else
                       {
                           return "";
                       }
                   case 3: return r.getNotes();
                   case 4: return r.getPanelName();
                   case 5: if (r.getActive()==1){
                       
                         return "Active";
                         }
                          else {
                          return "Deactive" ;
                         }
                       
                   case 6: return (sdf.format(r.getFromDate())).toString();
                   case 7: return (sdf.format(r.getToDate())).toString();
                   case 8: return r.getTimeInInterval();
                   
                   
               }
                return null;
            }
          
          
        };
    } 
    public List<AdvertizeInfo> getImgPath()
    {
        if(data2!=null)
        {
            return data2;
        }
        else
            return new ArrayList<AdvertizeInfo>();
    }
    public List<PanelTableInfo> getPanelImgPath()
    {
        if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<PanelTableInfo>();
    }
    
    public int getPanelOneSize()
    {
        return panelonelength;
    }
   
   
    public AbstractTableModel getTableModel2()
    {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return data2.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS2.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS2[column];
            }

          Class[] types = new Class[]{
                  java.lang.String.class,java.lang.String.class, java.lang.String.class, java.security.Timestamp.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.security.Timestamp.class , java.security.Timestamp.class , java.lang.Integer.class
            };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false ,false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
          
          
            public Object getValueAt(int rowIndex, int columnIndex) {
              AdvertizeInfo r2 =data2.get(rowIndex);
               SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                
               switch(columnIndex){
                   
                  
                   case 0: return r2.getAdvertiser();
                   case 1: return r2.getPath();
                   case 2: return r2.getLink();
                  
                   //case 3: return  (sdf.format(r2.getEventDate())).toString();
                   case 3: return r2.getNotes();
                   case 4: return r2.getPanelName();
                   case 5: if (r2.getActive()==1){
                       
                         return "Active";
                         }
                          else {
                          return "Deactive" ;
                         }
                       
                   case 6:
                       if(r2.getFromDate()!=null)
                       {
                           try
                           {
                       return (sdf.format(r2.getFromDate())).toString();
                           }
                           catch(Exception e)
                           {
                               return "";
                           }
                       }
                       return "";
                   case 7: 
                       if(r2.getToDate()!=null)
                       {
                           try
                           {
                       return (sdf.format(r2.getToDate())).toString();
                           }
                           catch(Exception e)
                           {
                               return "";
                           }
                       }
                       return "";
                       
                       
                   case 8: return r2.getTimeInInterval();
                   
                   
               }
                return null;
            }
          
          
        };
    } 
   
   


public static class PanelTableInfo implements SerializableRead,IKeyed {
        
    
        private String Id;
       
        private String Path;
        private String Link_to_web;
        private Timestamp Cr_date;
        private Timestamp Event_date;
        private String Notes;
        private String Panel_Name;
        private int Active;
        private Timestamp From_date;
        private Timestamp To_date;
        private int Time_in_interval;
        private String Cr_by;
        private String Cr_host;
        private String Deac_by;
        private Timestamp Deac_date;
        private String Deac_host;
        

          
          public String getId(){
              return Id;
          }
          public void setId(){
              this.Id=Id;
          }
          
               
          public String getPath(){
              return Path;
          }
          public void setPath(String Path){
          this.Path = Path;
          }
          
          public String getLink(){
              return Link_to_web;
          }
          public void setLink(String Link_to_web){
                this.Link_to_web=Link_to_web;
            }
          
          public Timestamp getCrDate(){
              return Cr_date;
          }
          
          public void setCrdate(Timestamp Cr_date){
              this.Cr_date=Cr_date;
          }
          
          
          public Timestamp getEventDate(){
              return Event_date;
              
          }
          public void setEventDate(Timestamp Event_date){
              this.Event_date=Event_date;
          }
          public String getNotes(){
              return Notes;
          }
          public void setNotes(String Notes){
              this.Notes=Notes;
          }
          
          public String getPanelName(){
              return Panel_Name;
          }
          
          public void setPanelName(String Panel_Name){
              this.Panel_Name=Panel_Name;
             }
          public int getActive() {
            return Active;
             }

          public void setActive(int Active) {
            this.Active = Active;
          }
          
          
          public Timestamp getFromDate(){
              return From_date;
          }
          public void setFromDate(Timestamp From_date){
              this.From_date=From_date;
          }
          public Timestamp getToDate(){
              return To_date;
          }
          public void setToDate(Timestamp To_date){
              this.To_date=To_date;
          }
          public int getTimeInInterval(){
              return Time_in_interval;
          }
          
          public void setTimeInInterval(int Time_in_interval){
             this.Time_in_interval=Time_in_interval;
          }
          
          public String getCrBy(){
              
              return Cr_by;
          }
          public void setCrBy(String Cr_by){
              this.Cr_by=Cr_by;
          }
          
          public String getCrHost(){
              
              return Cr_host;
          }
          
          public void setCrHost(String Cr_host){
              this.Cr_host=Cr_host;
          }
          
          public String getDescBy(){
              
              return Deac_by;
          }
          
          public void setDeacBy(String Desc_by){
              this.Deac_by=Deac_by;
          }
           public Timestamp getDeacDate(){
              
              return Deac_date;
          }
          
          public void setDeacDate(Timestamp Deac_date){
              this.Deac_date= Deac_date;
          }
          
         
          public String getDeacHost(){
              return Deac_host;
          }
          
          
          public void setDeacHost(String Deac_host){
              this.Deac_host=Deac_host;
          }
          
        public void readValues(DataRead dr) throws BasicException {
          
           Path = dr.getString(1);
           Link_to_web = dr.getString(2);
           //Cr_date = dr.getTimestamp(3);
           Event_date = dr.getTimestamp(3);
           Notes = dr.getString(4);
           Panel_Name = dr.getString(5);
           Active = dr.getInt(6);
           From_date = dr.getTimestamp(7);
           To_date = dr.getTimestamp(8);
           Time_in_interval = dr.getInt(9);
           Id = dr.getString(10);
           //Cr_by = dr.getString(11);
           //Cr_host = dr.getString(12);
           //Deac_by = dr.getString(13);
          // Deac_date = dr.getTimestamp(14);
          // Deac_host = dr.getString(15);
           
        }

        public Object getKey() {
            return this;
        }
        
    
    
    }

public static class AdvertizeInfo implements SerializableRead,IKeyed {
       
        private String Id;
       
        private String Path;
        private String Link_to_web;
        private Timestamp Cr_date;
        //private Timestamp Event_date;
        private String Notes;
        private String Panel_Name;
        private int Active;
        private Timestamp From_date;
        private Timestamp To_date;
        private int Time_in_interval;
        private String Advertiser_Name;
        

          
          public String getId(){
              return Id;
          }
          public void setId(){
              this.Id=Id;
          }
          
               
          public String getPath(){
              return Path;
          }
          public void setPath(String Path){
          this.Path = Path;
          }
          
          public String getLink(){
              return Link_to_web;
          }
          public void setLink(String Link_to_web){
                this.Link_to_web=Link_to_web;
            }
          
          public Timestamp getCrDate(){
              return Cr_date;
          }
          
          public void setCrdate(Timestamp Cr_date){
              this.Cr_date=Cr_date;
          }
          
         /* 
          public Timestamp getEventDate(){
              return Event_date;
              
          }
          public void setEventDate(Timestamp Event_date){
              this.Event_date=Event_date;
          }
           
           */
          public String getNotes(){
              return Notes;
          }
          public void setNotes(String Notes){
              this.Notes=Notes;
          }
          
          public String getPanelName(){
              return Panel_Name;
          }
          
          public void setPanelName(String Panel_Name){
              this.Panel_Name=Panel_Name;
             }
          public int getActive() {
            return Active;
             }

          public void setActive(int Active) {
            this.Active = Active;
          }
          
          
          public Timestamp getFromDate(){
              return From_date;
          }
          public void setFromDate(Timestamp From_date){
              this.From_date=From_date;
          }
          public Timestamp getToDate(){
              return To_date;
          }
          public void setToDate(Timestamp To_date){
              this.To_date=To_date;
          }
          public int getTimeInInterval(){
              return Time_in_interval;
          }
          
          public void setTimeInInterval(int Time_in_interval){
             this.Time_in_interval=Time_in_interval;
          }
          
         public String getAdvertiser(){
             return Advertiser_Name;
         }
         public void setAdvertiser(String Advertiser_Name){
             this.Advertiser_Name = Advertiser_Name;
             
         } 
         
        public void readValues(DataRead dr) throws BasicException {
          
            
           Advertiser_Name = dr.getString(1);
           Path = dr.getString(2);
           Link_to_web = dr.getString(3);
           //Cr_date = dr.getTimestamp(3);
           //Event_date = dr.getTimestamp(4);
           Notes = dr.getString(4);
           Panel_Name = dr.getString(5);
           Active = dr.getInt(6);
           From_date = dr.getTimestamp(7);
           To_date = dr.getTimestamp(8);
           Time_in_interval = dr.getInt(9);
           Id=dr.getString(10);
           //Cr_by = dr.getString(11);
           //Cr_host = dr.getString(12);
           //Deac_by = dr.getString(13);
          // Deac_date = dr.getTimestamp(14);
          // Deac_host = dr.getString(15);
           
        }

        public Object getKey() {
            return this;
        }
        
    
    
    }


}
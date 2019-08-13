

package com.openbravo.pos.EVM;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class VotingStatusTableModel extends BeanFactoryDataSingle{
    
    
     private AppView m_App;
     private Session s;
     private final static String[] TABLEHEADERS = {"Sr No." , "Polling Station"  , "Voting Slip Issued" };
     private final static String[] TABLEHEADERS2 = {"Sr No." , "Polling Booth Name"  , "Vote Cast" };
     
     
     private List<VotingStatusTableModel.VotingInfo> data;
     private List<VotingStatusTableModel.VoteCatedInfo> data2;
     private int size;
      private int size2;
     
      @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
     public int getSize()
      {
        return size;
      }
     
     public List<VotingStatusTableModel.VotingInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<VotingStatusTableModel.VotingInfo>();
      }
     
    
     
      public static VotingStatusTableModel LoadPollingStationInfo(AppView app) throws BasicException{
        VotingStatusTableModel GuestInfo = new VotingStatusTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<VotingStatusTableModel.VotingInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "(SELECT p.name ,COUNT(*)  FROM evm_barcode e , people p  where p.id=e.crby and p.visible=1 group by e.crby)\n" +
                                                                    "union\n" +
                                                                    "(select 'Total' , COUNT(*) from evm_barcode e ) order by 1", SerializerWriteString.INSTANCE, new SerializerReadClass(VotingStatusTableModel.VotingInfo.class)).list();

            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(VotingStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
      
      
// LOAD VOTE CASTED. .......................... 
      
       public static VotingStatusTableModel LoadVoteCastedDetails(AppView app) throws BasicException{
        VotingStatusTableModel GuestInfo = new VotingStatusTableModel(); 
    
     try{
            GuestInfo.data2 = new ArrayList<VotingStatusTableModel.VoteCatedInfo>();
            GuestInfo.data2 = new StaticSentence(app.getSession(), "(SELECT p.name ,COUNT(*)  FROM evm_barcode e , people p  where p.id=e.PB and p.visible=1 and e.votecast=1 group by e.pb)\n" +
                                                                    "union\n" +
                                                                    "(select 'Total' , COUNT(*) from evm_barcode e where e.votecast=1) order by 1", SerializerWriteString.INSTANCE, new SerializerReadClass(VotingStatusTableModel.VoteCatedInfo.class)).list();

            GuestInfo.size2 = GuestInfo.data2.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(VotingStatusTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
     
      
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
      
      
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              VotingStatusTableModel.VotingInfo r =data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: if(r.getPollingStationName().equals("Total")){
                                    return "Total";
                                }else{
                                    return (rowIndex+1);
                                }
                   case 1: return r.getPollingStationName();
                   case 2: return r.getVotingSlipNos();
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
      
      
 // table model for vote casted /////////////////////////////////////////////////////////////////////
         
           public  AbstractTableModel getTableModel2()
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
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,
            };
          boolean[] canEdit = new boolean[]{
                false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              VotingStatusTableModel.VoteCatedInfo r =data2.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: if(r.getPollingBoothName().equals("Total")){
                                    return "Total";
                                }else{
                                    return (rowIndex+1);
                                }
                   case 1: return r.getPollingBoothName();
                   case 2: return r.getVoteCatedNos();
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
      
         
         
         
           
           
           
           
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
           
           
           
           
           
           
           
           
           
           
           
           
     public static class VotingInfo implements SerializableRead,IKeyed {

        private String PollingStationName;
        private int VotingSlipNos;
        
         
        
        
         public String getPollingStationName(){
              return PollingStationName;
          }
          public void setPollingStationName(String PollingStationName){
              this.PollingStationName=PollingStationName;
          }
          public int getVotingSlipNos(){
              return VotingSlipNos;
          }
          public void setVotingSlipNos(int VotingSlipNos){
              this.VotingSlipNos = VotingSlipNos;
          }
         
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                PollingStationName = dr.getString(1);
                VotingSlipNos = dr.getInt(2);
                
              
                
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
     
     
       public static class VoteCatedInfo implements SerializableRead,IKeyed {

        private String PollingBoothName;
        private int VoteCatedNos;
        
         
        
        
         public String getPollingBoothName(){
              return PollingBoothName;
          }
          public void setPollingBoothName(String PollingBoothName){
              this.PollingBoothName=PollingBoothName;
          }
          public int getVoteCatedNos(){
              return VoteCatedNos;
          }
          public void setVoteCatedNos(int VoteCatedNos){
              this.VoteCatedNos = VoteCatedNos;
          }
         
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                PollingBoothName = dr.getString(1);
                VoteCatedNos = dr.getInt(2);
                
              
                
          }

        public Object getKey() {
           return this;
        }

       
     }   
     
     
    
}

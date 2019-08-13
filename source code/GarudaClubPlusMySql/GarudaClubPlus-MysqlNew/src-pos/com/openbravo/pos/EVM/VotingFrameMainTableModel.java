

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



public class VotingFrameMainTableModel extends BeanFactoryDataSingle{
    
     private AppView m_App;
     private Session s;
     private int size;
     private List<VotingFrameMainTableModel.ContestantInfo> data;
     
     
     @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
     public int getSize()
      {
        return size;
      }
     
     public List<VotingFrameMainTableModel.ContestantInfo> getList(){
           if(data!=null)
        {
            return data;
        }
        else
            return new ArrayList<VotingFrameMainTableModel.ContestantInfo>();
      }
      
     
     
     
       public static VotingFrameMainTableModel loadEmailInfo(AppView app) throws BasicException{
        VotingFrameMainTableModel GuestInfo = new VotingFrameMainTableModel(); 
    
     try{
            GuestInfo.data = new ArrayList<VotingFrameMainTableModel.ContestantInfo>();
            GuestInfo.data = new StaticSentence(app.getSession(), "select ID , SRNO , MEMNAME , MEMID , CRBY , CRDATE , CRHOST , VALIDVOTES , ENGNAME , KNDNAME , PHOTO  from evm_candidates WHERE ACTIVE=1 ORDER BY SRNO,MEMNAME ", SerializerWriteString.INSTANCE, new SerializerReadClass(VotingFrameMainTableModel.ContestantInfo.class)).list();
           
            GuestInfo.size = GuestInfo.data.size();
            
        
        
        }
        catch(BasicException ex){
            Logger.getLogger(VotingFrameMainTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
     return GuestInfo;
  
     }
    
     
       
       
       
       
       
       
       
       public static class ContestantInfo implements SerializableRead,IKeyed {

        private String ID;
        private String SRNO;
        private String MEMNAME;
        private String MEMID;
        private String CRBY;
        private Date CRDATE;
        private String CRHOST;
        private int VALIDVOTES;
        private Date LASTVALIDVOTE;
        private BufferedImage ENGNAME;
        private BufferedImage KNDNAME;
        private BufferedImage PHOTO;
         
        
        
         public String getID(){
              return ID;
          }
          public void setID(String ID){
              this.ID=ID;
          }
          public String getSRNO(){
              return SRNO;
          }
          public void setSRNO(String SRNO){
              this.SRNO = SRNO;
          }
          public String getMEMNAME(){
              return MEMNAME;
          }
          public void setMEMNAME(String MEMNAME){
              this.MEMNAME =MEMNAME;
          }
        
          public String getMEMID(){
              return MEMID;
          }
          public void setMEMID(String MEMID){
              this.MEMID = MEMID;
          }
          
         
          
          public String getCRBY(){
              return CRBY;
          }
          public void setCRBY(String CRBY){
              this.CRBY=CRBY;
          }
          
        
           public String getCRDATE(){
              String x = Formats.TIMESTAMP.formatValue(CRDATE);
               return x;
          }
          public void setCRDATE(Date CRDATE){
              this.CRDATE=CRDATE;
          }
          
          public String getCRHOST(){
              return CRHOST;
          }
          public void setSMTPSERVER(String CRHOST){
              this.CRHOST=CRHOST;
          }
          
          
           public int getVALIDVOTES(){
              return VALIDVOTES;
          }
          public void setVALIDVOTES(int VALIDVOTES){
              this.VALIDVOTES=VALIDVOTES;
          }
          
          public Date getLASTVALIDVOTE(){
              return LASTVALIDVOTE;
          }
          public void setLASTVALIDVOTE(Date LASTVALIDVOTE){
              this.LASTVALIDVOTE=LASTVALIDVOTE;
          }
          public BufferedImage getENGNAME(){
              return ENGNAME;
          } 
          public void setENGNAME(BufferedImage ENGNAME){
              this.ENGNAME=ENGNAME;
          }
           public BufferedImage getKNDNAME(){
              return KNDNAME;
          } 
          public void setKNDNAME(BufferedImage KNDNAME){
              this.KNDNAME=KNDNAME;
          }
           public BufferedImage getPHOTO(){
              return PHOTO;
          } 
          public void setPHOTO(BufferedImage PHOTO){
              this.PHOTO=PHOTO;
          }
          
          public void readValues(DataRead dr) throws BasicException {
           
             
                ID = dr.getString(1);
                SRNO = dr.getString(2);
                MEMNAME = dr.getString(3);
                MEMID = dr.getString(4);
                CRBY = dr.getString(5);
                CRDATE = dr.getTimestamp(6);
                CRHOST = dr.getString(7);
                VALIDVOTES = dr.getInt(8);
                
                ENGNAME = ImageUtils.readImage(dr.getBytes(9));
                KNDNAME = ImageUtils.readImage(dr.getBytes(10));
                PHOTO=    ImageUtils.readImage(dr.getBytes(11));
              
                
          }

        public Object getKey() {
           return this;
        }

       
     }   
       
       
     
     
     
}

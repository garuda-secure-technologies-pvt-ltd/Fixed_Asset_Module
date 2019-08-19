 
package com.openbravo.pos.knowYourMember;
 
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
import com.openbravo.pos.sms.EmailMasterTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class DocumentReceiptTableModel extends BeanFactoryDataSingle{
    
    
    
    private Session s;
    int DocumentLength;
    private List<DocumentReceiptTableModel.DocumentReceiptListInfo> status_data;
    private final static String[] TABLEHEADERS1 = {"Sr No." , "Mem No." , "Member Name " , "Document Submitted" };
    
    
    
    @Override
    public void init(Session s) {
         this.s=s;
    }
    
    
    
     public static DocumentReceiptTableModel loadInstanceDocumentReports(AppView app , String Memno)throws BasicException{
         DocumentReceiptTableModel DocumentData = new DocumentReceiptTableModel(); 
         
          try{
            DocumentData.status_data = new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
            DocumentData.status_data = new StaticSentence(app.getSession(), "  SELECT FORMNO,MEMNO,MEMBERNAME,RECEIPTNO,MEMTYPE,IDPROOF,PHOTO,SPOUSEID,SPOUSEPHOTO,FATHERID,FATHERPHOTO,\n" +
                                                                                "MOTHERID,MOTHERPHOTO,SONID,SONPHOTO,DAUGHTERID,DAUGHTERPHOTO,DATEFORMAT,SPDMATCHES,PHONEMATCHES,EMAILMATCHES,\n" +
                                                                                "CRDATE,  CRBY , SONNOS , DAUGHTERNOS , DOBFLAG , DOMFLAG , DOCFLAG , DOPFLAG , ID ,  s1id , s2id , s3id , d1id , d2id , d3id , S1PHOTO , S2PHOTO , S3PHOTO , D1PHOTO , D2PHOTO , D3PHOTO , NOS_PHOTOS , NOS_DOC  FROM kym_documentreceipt where memno=?   ", 
                                                new SerializerWriteBasic(new Datas[]{Datas.STRING  }) ,
                                                new SerializerReadClass(DocumentReceiptTableModel.DocumentReceiptListInfo.class)).list(new Object[]{Memno });

            DocumentData.DocumentLength = DocumentData.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(DocumentReceiptTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return DocumentData;
         
     }
     
     
     
     
      public static DocumentReceiptTableModel loadInstanceDocumentReportsAll(AppView app )throws BasicException{
         DocumentReceiptTableModel DocumentData = new DocumentReceiptTableModel(); 
         
          try{
            DocumentData.status_data = new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
            DocumentData.status_data = new StaticSentence(app.getSession(), "  SELECT FORMNO,MEMNO,MEMBERNAME,RECEIPTNO,MEMTYPE,IDPROOF,PHOTO,SPOUSEID,SPOUSEPHOTO,FATHERID,FATHERPHOTO,\n" +
                                                                                "MOTHERID,MOTHERPHOTO,SONID,SONPHOTO,DAUGHTERID,DAUGHTERPHOTO,DATEFORMAT,SPDMATCHES,PHONEMATCHES,EMAILMATCHES,\n" +
                                                                                "CRDATE,  CRBY , SONNOS , DAUGHTERNOS , DOBFLAG , DOMFLAG , DOCFLAG , DOPFLAG , ID ,  s1id , s2id , s3id , d1id , d2id , d3id , S1PHOTO , S2PHOTO , S3PHOTO , D1PHOTO , D2PHOTO , D3PHOTO , NOS_PHOTOS , NOS_DOC  FROM kym_documentreceipt  order by memno   ", 
                                                new SerializerWriteBasic(new Datas[]{Datas.STRING  }) ,
                                                new SerializerReadClass(DocumentReceiptTableModel.DocumentReceiptListInfo.class)).list(new Object[]{" " });

            DocumentData.DocumentLength = DocumentData.status_data.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(DocumentReceiptTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
             
         return DocumentData;
         
     }
     
     
     
     
     
     
      public List<DocumentReceiptTableModel.DocumentReceiptListInfo> getDocumentList(){
           if(status_data!=null)
        {
            return status_data;
        }
        else
            return new ArrayList<DocumentReceiptTableModel.DocumentReceiptListInfo>();
      }
      
     
     
      
      public  AbstractTableModel getTableModel2()
       {
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return status_data.size();
            }
          public int getColumnCount() {
                return TABLEHEADERS1.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return TABLEHEADERS1[column];
            }

          Class[] types = new Class[]{
               java.lang.String.class  , java.lang.String.class ,java.lang.String.class ,java.lang.String.class
            };
          boolean[] canEdit = new boolean[]{
                false, false , false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              DocumentReceiptTableModel.DocumentReceiptListInfo r =status_data.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return (rowIndex+1);
                   case 1: return r.getMemberNo().toUpperCase();
                   case 2: return r.getMemberName();
                   case 3: return r.getCRDATE();
                       
                       
                       
                       
                 }
                return null;
            }
          
          
          };
        } 
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
     
      public static class DocumentReceiptListInfo implements SerializableRead,IKeyed {
          
            
            String ID ;
            String ReceiptNo;
            String FormNo  ;
            String MemberNo ;
            String MemberName  ;
            String MemberType  ;
            int MemberNameMatches = 0;
            String MemberIDProof;
            int MemberPhoto;

            String SpouseID;
            int SpousePhoto;

            String FatherId;
            int FatherPhoto;

            String MotherId;
            int MotherPhoto;
            String SonID;
            String DaughterId;

            int SonNos;
            int DaughterNos;

            int SPD_Matches;
            int PhoneNoMatches ;
            int EmailIdMatches;

            String SonPhoto;
            String DaughterPhoto;
            String DateFormat ;

            int Date_3;
            int Date_10 = 0;
            int Date_13 = 0;
            int Date_14=0;

            String CrBy; 
            Date CRDATE;
            
            int S1Photo;
            int S2Photo;            
            int S3Photo;
            int D1Photo;
            int D2Photo;
            int D3Photo;
            
            String S1IdProof;
            String S2IdProof; 
            String S3IdProof; 
            String D1IdProof; 
            String D2IdProof; 
            String D3IdProof; 
            
            int NosPhoto;
            int NosDoc;

        public int getS1Photo() {
            return S1Photo;
        }

        public void setS1Photo(int S1Photo) {
            this.S1Photo = S1Photo;
        }

        public int getS2Photo() {
            return S2Photo;
        }

        public void setS2Photo(int S2Photo) {
            this.S2Photo = S2Photo;
        }

        public int getS3Photo() {
            return S3Photo;
        }

        public void setS3Photo(int S3Photo) {
            this.S3Photo = S3Photo;
        }

        public int getD1Photo() {
            return D1Photo;
        }

        public void setD1Photo(int D1Photo) {
            this.D1Photo = D1Photo;
        }

        public int getD2Photo() {
            return D2Photo;
        }

        public void setD2Photo(int D2Photo) {
            this.D2Photo = D2Photo;
        }

        public int getD3Photo() {
            return D3Photo;
        }

        public void setD3Photo(int D3Photo) {
            this.D3Photo = D3Photo;
        }

        public String getS1IdProof() {
            return S1IdProof;
        }

        public void setS1IdProof(String S1IdProof) {
            this.S1IdProof = S1IdProof;
        }

        public String getS2IdProof() {
            return S2IdProof;
        }

        public void setS2IdProof(String S2IdProof) {
            this.S2IdProof = S2IdProof;
        }

        public String getS3IdProof() {
            return S3IdProof;
        }

        public void setS3IdProof(String S3IdProof) {
            this.S3IdProof = S3IdProof;
        }

        public String getD1IdProof() {
            return D1IdProof;
        }

        public void setD1IdProof(String D1IdProof) {
            this.D1IdProof = D1IdProof;
        }

        public String getD2IdProof() {
            return D2IdProof;
        }

        public void setD2IdProof(String D2IdProof) {
            this.D2IdProof = D2IdProof;
        }

        public String getD3IdProof() {
            return D3IdProof;
        }

        public void setD3IdProof(String D3IdProof) {
            this.D3IdProof = D3IdProof;
        }

        public int getNosPhoto() {
            return NosPhoto;
        }

        public void setNosPhoto(int NosPhoto) {
            this.NosPhoto = NosPhoto;
        }

        public int getNosDoc() {
            return NosDoc;
        }

        public void setNosDoc(int NosDoc) {
            this.NosDoc = NosDoc;
        }
            
            
            
             public String getCrBy() {
                return CrBy;
            }

            public void setCrBy(String CrBy) {
                this.CrBy = CrBy;
            }
            
            
             public Date getCRDATE() {
                return CRDATE;
            }

            public void setCRDATE(Date CRDATE) {
                this.CRDATE = CRDATE;
            }
            
            
            
            
            
             public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }
            
            public String getReceiptNo() {
                return ReceiptNo;
            }

            public void setReceiptNo(String ReceiptNo) {
                this.ReceiptNo = ReceiptNo;
            }

            public String getFormNo() {
                return FormNo;
            }

            public void setFormNo(String FormNo) {
                this.FormNo = FormNo;
            }

            public String getMemberNo() {
                return MemberNo;
            }

            public void setMemberNo(String MemberNo) {
                this.MemberNo = MemberNo;
            }

            public String getMemberName() {
                return MemberName;
            }

            public void setMemberName(String MemberName) {
                this.MemberName = MemberName;
            }

            public String getMemberType() {
                return MemberType;
            }

            public void setMemberType(String MemberType) {
                this.MemberType = MemberType;
            }

            public int getMemberNameMatches() {
                return MemberNameMatches;
            }

            public void setMemberNameMatches(int MemberNameMatches) {
                this.MemberNameMatches = MemberNameMatches;
            }

            public String getMemberIDProof() {
                return MemberIDProof;
            }

            public void setMemberIDProof(String MemberIDProof) {
                this.MemberIDProof = MemberIDProof;
            }

            public int getMemberPhoto() {
                return MemberPhoto;
            }

            public void setMemberPhoto(int MemberPhoto) {
                this.MemberPhoto = MemberPhoto;
            }

            public String getSpouseID() {
                return SpouseID;
            }

            public void setSpouseID(String SpouseID) {
                this.SpouseID = SpouseID;
            }

            public int getSpousePhoto() {
                return SpousePhoto;
            }

            public void setSpousePhoto(int SpousePhoto) {
                this.SpousePhoto = SpousePhoto;
            }

            public String getFatherId() {
                return FatherId;
            }

            public void setFatherId(String FatherId) {
                this.FatherId = FatherId;
            }

            public int getFatherPhoto() {
                return FatherPhoto;
            }

            public void setFatherPhoto(int FatherPhoto) {
                this.FatherPhoto = FatherPhoto;
            }

            public String getMotherId() {
                return MotherId;
            }

            public void setMotherId(String MotherId) {
                this.MotherId = MotherId;
            }

            public int getMotherPhoto() {
                return MotherPhoto;
            }

            public void setMotherPhoto(int MotherPhoto) {
                this.MotherPhoto = MotherPhoto;
            }

            public String getSonID() {
                return SonID;
            }

            public void setSonID(String SonID) {
                this.SonID = SonID;
            }

            public String getDaughterId() {
                return DaughterId;
            }

            public void setDaughterId(String DaughterId) {
                this.DaughterId = DaughterId;
            }

            public int getSonNos() {
                return SonNos;
            }

            public void setSonNos(int SonNos) {
                this.SonNos = SonNos;
            }

            public int getDaughterNos() {
                return DaughterNos;
            }

            public void setDaughterNos(int DaughterNos) {
                this.DaughterNos = DaughterNos;
            }

            public int getSPD_Matches() {
                return SPD_Matches;
            }

            public void setSPD_Matches(int SPD_Matches) {
                this.SPD_Matches = SPD_Matches;
            }

            public int getPhoneNoMatches() {
                return PhoneNoMatches;
            }

            public void setPhoneNoMatches(int PhoneNoMatches) {
                this.PhoneNoMatches = PhoneNoMatches;
            }

            public int getEmailIdMatches() {
                return EmailIdMatches;
            }

            public void setEmailIdMatches(int EmailIdMatches) {
                this.EmailIdMatches = EmailIdMatches;
            }

            public String getSonPhoto() {
                return SonPhoto;
            }

            public void setSonPhoto(String SonPhoto) {
                this.SonPhoto = SonPhoto;
            }

            public String getDaughterPhoto() {
                return DaughterPhoto;
            }

            public void setDaughterPhoto(String DaughterPhoto) {
                this.DaughterPhoto = DaughterPhoto;
            }

            public String getDateFormat() {
                return DateFormat;
            }

            public void setDateFormat(String DateFormat) {
                this.DateFormat = DateFormat;
            }

            public int getDate_3() {
                return Date_3;
            }

            public void setDate_3(int Date_3) {
                this.Date_3 = Date_3;
            }

            public int getDate_10() {
                return Date_10;
            }

            public void setDate_10(int Date_10) {
                this.Date_10 = Date_10;
            }

            public int getDate_13() {
                return Date_13;
            }

            public void setDate_13(int Date_13) {
                this.Date_13 = Date_13;
            }

            public int getDate_14() {
                return Date_14;
            }

            public void setDate_14(int Date_14) {
                this.Date_14 = Date_14;
            }
    
          
          
         
          
          
        public void readValues(DataRead dr) throws BasicException {
           
            
           FormNo = dr.getString(1);
           MemberNo = dr.getString(2);
           MemberName         = dr.getString(3);
          ReceiptNo   = dr.getString(4);
          MemberType = dr.getString(5);
          MemberIDProof   = dr.getString(6);      
          MemberPhoto    = dr.getInt(7);     
          SpouseID      = dr.getString(8);   
          SpousePhoto   = dr.getInt(9);      
          FatherId      = dr.getString(10);     
          FatherPhoto = dr.getInt(11);
          MotherId       = dr.getString(12);  
          MotherPhoto     = dr.getInt(13);    
          SonID         = dr.getString(14);
          SonPhoto        = dr.getString(15); 
          DaughterId      = dr.getString(16);   
          DaughterPhoto        = dr.getString(17); 
          DateFormat         = dr.getString(18);
          SPD_Matches         = dr.getInt(19);
          PhoneNoMatches      = dr.getInt(20);   
          EmailIdMatches      = dr.getInt(21);   
          CRDATE         = dr.getTimestamp(22);
          CrBy         = dr.getString(23);
          SonNos         = dr.getInt(24);
          DaughterNos         = dr.getInt(25);
          Date_3 = dr.getInt(26);
          Date_10         = dr.getInt(27);
          Date_13         = dr.getInt(28);
          Date_14         = dr.getInt(29);
          ID = dr.getString(30);
          S1IdProof = dr.getString(31);
          S2IdProof = dr.getString(32);
          S3IdProof = dr.getString(33);
          D1IdProof = dr.getString(34);
          D2IdProof = dr.getString(35);
          D3IdProof = dr.getString(36);
          
          S1Photo = dr.getInt(37);
          S2Photo = dr.getInt(38);
          S3Photo = dr.getInt(39);
          D1Photo = dr.getInt(40);
          D2Photo = dr.getInt(41);
          D3Photo = dr.getInt(42);
          
          NosPhoto = dr.getInt(43);
          NosDoc = dr.getInt(44);
          
            
        }

        public Object getKey() {
           return this;
        }
          
    }
     
    
     
     
     
     
     
     
    
    
    
}

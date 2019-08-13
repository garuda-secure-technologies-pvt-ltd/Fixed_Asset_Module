/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dev1
 */
public class Lib_IssueTableModel {
    private static AppView m_App;
    private static List<Object> autname= new ArrayList<Object>();
    private List<lib_Issueline> lbl;
    private List<lib_Issueline> lb;
    private List<Lib_IssueTableModel.lib_Issueline> blist;
    private List<Lib_IssueTableModel.lib_Issueline> alist;
    private int size;
    private final static String[] ISSUEHEADERS = {"Sl_No","MemberName","BookName","Author","IssueDate","ReturnDate","Dep-Type","Status"}; 
    private final static String[] ISSUELIST = {"Sl_No","MemberNo","MemberName","BookName","IssueDate","ReturnDate"};
    private int flag;
    private static int i=0;
    private static List<Object> bookname_list = new ArrayList<Object>();
    public static List<Object> memname_list = new ArrayList<Object>();
    public static List<Object> chngdate = new ArrayList<Object>();
    private static int j=0;
    private List<lib_Issueline1> lb1;
    private List<lib_Issueline1> l1;
    private List<Lib_IssueTableModel.lib_Issueline1> blist1;
    private List<Lib_IssueTableModel.lib_Issueline1> alist1;
    
    
    private Lib_IssueTableModel(){
        
    }
    
    public static Lib_IssueTableModel emptyinstance()
  {
      Lib_IssueTableModel lib_a=new Lib_IssueTableModel();
      lib_a.lb=new ArrayList<lib_Issueline>();
      return lib_a;
  } 
    
     public static Lib_IssueTableModel loadInstance(AppView app,List book_id) throws BasicException{
         Lib_IssueTableModel l = new Lib_IssueTableModel();
         //l.flag=flag;
         try
      {
          
       l.alist1 = new StaticSentence(app.getSession()
                , "select l.id,sl_no,m.name,m.SEARCHKEY,b.name,Issue_date,to_be_retn_dt from lib_bookissue_retn l,customers m,lib_bookmaster b where l.mem_id=m.id and l.book_id=b.id and l.book_id=? and flag=1 order by sl_no"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_IssueTableModel.lib_Issueline1.class)).list(book_id.get(0).toString());
          
                l.size=l.alist1.size();
         if(l.alist1==null)
         {
             l.lb1=new ArrayList<Lib_IssueTableModel.lib_Issueline1>();
         }
         else
        {
            l.lb1=l.alist1;
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_IssueTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return l;
     }

    
     public List<lib_Issueline> getfacilityline()
     {
         return lb;
     }
     
      public DefaultTableModel getTableModel() {
        return new DefaultTableModel() {
            

            @Override
            public String getColumnName(int column) {
                //if(flag==1){
                return AppLocal.getIntString(ISSUELIST[column]);
                
            }
            
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                return lb1.size();
            }
            public int getColumnCount() {
                 //if(flag==1){
                return ISSUELIST.length;
                 
            }
            public Object getValueAt(int row, int column) {
                Lib_IssueTableModel.lib_Issueline1 l1 = lb1.get(row);

                switch (column) {
                case 0: return row+1;
                case 1: return l1.getMemno();
                case 2:return l1.getMemname();
                case 3:return l1.getBookname();
                case 4:return l1.getIssue_date();
                case 5:return l1.getTo_be_retn_date();
                /*case 5:return l.getReturn_date();
                case 6:return l.getSl_no();
                case 7:return l.getDue_charge();
                case 8:return l.isFlag();  */ 
                default: return null;
                }
            }
        };
    }
      public static class lib_Issueline1 implements SerializableRead,IKeyed{ 
          private String id1;
          private int sl_no;
          private Date issue_date;
          private Date to_be_retn_date;
          private String memno;
          private String memname;
          private String bookname;
          
          
         
          public String getId1() {
            return id1;
        }

        public void setId1(String id1) {
            this.id1 = id1;
        }

        public int getSl_no() {
            return sl_no;
        }

        public void setSl_no(int sl_no) {
            this.sl_no = sl_no;
        }

        public Date getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(Date issue_date) {
            this.issue_date = issue_date;
        }

        public Date getTo_be_retn_date() {
            return to_be_retn_date;
        }

        public void setTo_be_retn_date(Date to_be_retn_date) {
            this.to_be_retn_date = to_be_retn_date;
        }

        public String getMemno() {
            return memno;
        }

        public void setMemno(String memno) {
            this.memno = memno;
        }

        public String getMemname() {
            return memname;
        }

        public void setMemname(String memname) {
            this.memname = memname;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }
          
          

        @Override
        public void readValues(DataRead dr) throws BasicException {
            id1=dr.getString(1);
            sl_no=dr.getInt(2);
            memname=dr.getString(3);
            memno=dr.getString(4);
            bookname=dr.getString(5);
            issue_date=dr.getTimestamp(6);
            to_be_retn_date=dr.getTimestamp(7);
            
        }

        @Override
        public Object getKey() {
            return id1;
        }
      }
     
     public static class lib_Issueline implements SerializableRead,IKeyed{ 
          
            private String id;
            private int sl_no;
            private String mem_id;
            private String book_id;
            private Date issue_date;
            private Date to_be_retn_date;
            private Date return_date;
            private double due_charge;
            private boolean flag;
            private String dep_type;

       
        @Override
        public void readValues(DataRead dr) throws BasicException {
            id=dr.getString(1);
            sl_no=dr.getInt(2);
            mem_id=dr.getString(3);
            book_id=dr.getString(4);
            issue_date=dr.getTimestamp(5);
            to_be_retn_date=dr.getTimestamp(6);
            return_date=dr.getTimestamp(7);
            //due_charge=dr.getDouble(8);
            flag=dr.getBoolean(9);
            dep_type=dr.getString(10);
        }
        
         public String getDep_type() {
            return dep_type;
        }

        public void setDep_type(String dep_type) {
            this.dep_type = dep_type;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSl_no() {
            return sl_no;
        }

        public void setSl_no(int sl_no) {
            this.sl_no = sl_no;
        }

        public String getMem_id() {
            return mem_id;
        }

        public void setMem_id(String mem_id) {
            this.mem_id = mem_id;
        }

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
        }

        public Date getIssue_date() {
            return issue_date;
        }

        public void setIssue_date(Date issue_date) {
            this.issue_date = issue_date;
        }

        public Date getTo_be_retn_date() {
            return to_be_retn_date;
        }

        public void setTo_be_retn_date(Date to_be_retn_date) {
            this.to_be_retn_date = to_be_retn_date;
        }

        public Date getReturn_date() {
            return return_date;
        }

        public void setReturn_date(Date return_date) {
            this.return_date = return_date;
        }

        public double getDue_charge() {
            return due_charge;
        }

        public void setDue_charge(double due_charge) {
            this.due_charge = due_charge;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        
        
        @Override
        public Object getKey() {
            return id;
        }
     }
     public int getSize()
      {
        return size;
      }
     
    public List<Lib_IssueTableModel.lib_Issueline> getList(){
           if(blist!=null)
        {
            return blist;
        }
        else
            return new ArrayList<Lib_IssueTableModel.lib_Issueline>();
      }
    
    
    public static Lib_IssueTableModel loadIssuedetails(AppView app,List membid,List loadbook ) throws BasicException{

        Lib_IssueTableModel li = new Lib_IssueTableModel();
         m_App=app;
         try
      {
          
       li.blist = new StaticSentence(app.getSession()
                ,"SELECT A.ID,A.Sl_No,A.mem_id,A.book_id,A.issue_date,A.to_be_retn_dt,A.return_date,A.due_charge,A.flag,A.d_type FROM LIB_BOOKISSUE_RETN A WHERE A.mem_id=? and A.flag =TRUE ORDER BY A.Sl_No" 
//"SELECT b.NAME,b.RefNo,a.name,p.name,l.Name,b.sms,c.name,b.Edition,b.Keywords,b.media,i.Name,b.AllocatedNor,b.copies,b.CREATEDBY,b.CREATEDHOST,b.DEACBY,b.DEACHOST,b.DEACTREFERENCE,b.ID,b.active,b.vend_doid,available_flag FROM lib_bookmaster b,lib_author a,lib_categories c,lib_publisher p,lib_language l,lib_issuerules i WHERE b.ACTIVE =TRUE and a.id=b.Author and p.id=b.publisher and c.id=b.category and l.id=b.language and i.id=b.IssueRules and b.available_flag=true ORDER BY b.NAME"
                , SerializerWriteString.INSTANCE
                ,new SerializerReadClass(Lib_IssueTableModel.lib_Issueline.class)).list(membid.get(0).toString());
       
       //List<Object> aut_list = new ArrayList<Object>();
           //aut_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT c.name FROM lib_author c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          bookname_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT b.name FROM lib_bookmaster b, lib_bookissue_retn i WHERE b.active=1 and b.available_flag=1 and b.id=i.book_id and i.mem_id=? and i.flag=1 ORDER BY i.Sl_No",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(membid.get(0).toString());
      
//i=bookname_list.size();
           autname=(List<Object>) new StaticSentence(app.getSession(), "SELECT a.name FROM lib_author a,lib_bookmaster b, lib_bookissue_retn i WHERE a.active=1 and b.available_flag=1 and b.id=i.book_id and a.id=b.author and i.mem_id=? and i.flag=1 ORDER BY i.Sl_No",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(membid.get(0).toString());
           if(loadbook.size()>0){ 
               
               li.blist.add(new lib_Issueline());
               bookname_list.add(loadbook.get(0));
               autname.add(loadbook.get(1));
               if(loadbook.size()==3){
                   chngdate.add(loadbook.get(2));
               }
      }
           memname_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT NAME FROM CUSTOMERS WHERE id = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(membid.get(0).toString());
               
       
                li.size=li.blist.size();
         if(li.blist==null)
         {
             li.lb=new ArrayList<Lib_IssueTableModel.lib_Issueline>();
         }
         else
        {
            li.lb=li.blist;
            //li.lb.add((lib_Issueline)loadbook);
        }
      }catch(BasicException ex){
            Logger.getLogger(Lib_IssueTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         return li;
     }
    
    public DefaultTableModel getTableModel1() {
            return new DefaultTableModel() {
                
            

            @Override
            public String getColumnName(int column) {
                //if(flag==1){
                return AppLocal.getIntString(ISSUEHEADERS[column]);
                
            }
            @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
             return false;
            }
            public int getRowCount() {
                
                return lb.size();
            }
            public int getColumnCount() {
                 //if(flag==1){
                return ISSUEHEADERS.length;
                 
            }
            
            public Object getValueAt(int row, int column) {
                Lib_IssueTableModel.lib_Issueline l = lb.get(row);

                switch (column) {
                case 0: return row+1;
                case 1:return memname_list.get(0).toString();    
                case 2:return bookname_list.get(row);
                case 3:return autname.get(row);
                case 4:return l.getIssue_date();
                case 5:return l.getTo_be_retn_date();
                case 6:return l.getDep_type();
                case 7:if(l.isFlag()==true)
                        return "Issued";
                       else
                        return "Not Issued";
                
                default: return null;
                }
            }
        };
    }
    
    /*public Lib_BookTableModel getNorBooksIssue(AppView app){
        List<Lib_BookTableModel> Norbook = new ArrayList<Lib_BookTableModel>();
        try {
            Norbook = (List<Lib_BookTableModel>) new StaticSentence(app.getSession(), "SELECT ID FROM lib_bookissue_retn WHERE memid = ? and flag=true",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
        } catch (BasicException ex) {
            Logger.getLogger(Lib_IssueTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Lib_BookTableModel)Norbook;
    }
    */
    
   /* public String getbookname() throws BasicException{
          List<Object> aut_list = new ArrayList<Object>();
          String booknm = null;
          
          if(bookname_list.size()>0){
              aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_bookmaster c WHERE c.active=1 and c.id = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(bookname_list.get(0));
           //aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_bookmaster c WHERE c.active=1 and c.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book.get(i).toString());
           booknm=aut_list.get(0).toString();
          }
          bookname_list.remove(0);
          //i++;
         // break;
          //len--;
          return booknm;
          
      }*/
    
   /* public String getauthorname() throws BasicException{
          List<Object> aut_list = new ArrayList<Object>();
          String autnm = null;
          
          if(i>0){
              aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_bookmaster c WHERE c.active=1 and c.id = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(bookname_list.get(i));
           //aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_bookmaster c WHERE c.active=1 and c.id=?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(book.get(i).toString());
           autnm=aut_list.get(0).toString();
          }
         // autname
          i--;
          return autnm;
          
      }*/
    
   /* public String getStatus(){
        //if(bookname_list.size()>0)
        aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_bookmaster c WHERE c.active=1 and c.id = ?",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(membid);
            return null;
        }*/
    
}

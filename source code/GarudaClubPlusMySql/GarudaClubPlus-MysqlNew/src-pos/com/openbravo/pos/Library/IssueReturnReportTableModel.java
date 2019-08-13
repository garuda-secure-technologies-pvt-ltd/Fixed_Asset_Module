/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinuta
 */
public class IssueReturnReportTableModel extends BeanFactoryDataSingle{
    
    private Session s;
    private List<IssueReturnReportTableModel.IssueReturnBook> BookList;
     private int Book_Length;
     private final static String[] IssueBookHeader = {"Customer" , "MeMId " , "Book " , "Issue Date " , "Actual Return Date " , "Return Date " , "DueCharge", "Dependent" , "DueDays" , " No Of Fine" };
    
      DecimalFormat decimalformat= new DecimalFormat("#0.00##");
    
    @Override
    public  void init(Session s){
        this.s=s;
    }
    
     public static IssueReturnReportTableModel LoadIssueBookReport_AllToAllBookbyBook(AppView app)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
     public static IssueReturnReportTableModel LoadIssueBookReport_AllToAllBookbyCustomer(AppView app)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID\n"+"order by C.NAME\n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
     public static IssueReturnReportTableModel LoadIssueBookReport_AllToAllBookbyMemId(AppView app)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID\n"+"order by C.SEARCHKEY\n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
     public static IssueReturnReportTableModel LoadIssueBookReport_AllToAllBookbyIssueDate(AppView app)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID\n"+"order by I.Issue_date\n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
     public static IssueReturnReportTableModel LoadIssueBookReport_AllToAllBookbyPending(AppView app)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND I.return_date is null\n"+"order by I.return_date \n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
      public static IssueReturnReportTableModel LoadIssueBookReport_AllToOneBookByCust(AppView app,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND B.Name=?\n"+"order by C.NAME\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
      public static IssueReturnReportTableModel LoadIssueBookReport_AllToOneBookByBook(AppView app,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND B.Name=?\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
      public static IssueReturnReportTableModel LoadIssueBookReport_AllToOneBookByMemId(AppView app,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND B.Name=?\n"+"order by C.SEARCHKEY\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
      public static IssueReturnReportTableModel LoadIssueBookReport_AllToOneBookByIsuueDate(AppView app,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND B.Name=?\n"+"order by I.Issue_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
      public static IssueReturnReportTableModel LoadIssueBookReport_AllToOneBookByPending(AppView app,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND B.Name=? AND I.return_date is null\n"+"order by I.return_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
       public static IssueReturnReportTableModel LoadIssueBookReport_OneToAllBookByCust(AppView app,String ctype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=?\n"+"order by C.NAME\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
        public static IssueReturnReportTableModel LoadIssueBookReport_OneToAllBookByBook(AppView app,String ctype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=?\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
         public static IssueReturnReportTableModel LoadIssueBookReport_OneToAllBookByMemID(AppView app,String ctype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=?\n"+"order by C.SEARCHKEY\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
          public static IssueReturnReportTableModel LoadIssueBookReport_OneToAllBookByPending(AppView app,String ctype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND I.return_date is null\n"+"order by I.return_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
           public static IssueReturnReportTableModel LoadIssueBookReport_OneToAllBookByIssueDate(AppView app,String ctype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=?\n"+"order by I.Issue_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
     
     public static IssueReturnReportTableModel LoadIssueBookReport_OneToOneBookByCust(AppView app,String ctype,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND B.Name=?\n"+"order by C.NAME\n", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype,btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    public static IssueReturnReportTableModel LoadIssueBookReport_OneToOneBookByBook(AppView app,String ctype,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND B.Name=?\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype,btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    
    public static IssueReturnReportTableModel LoadIssueBookReport_OneToOneBookByMemId(AppView app,String ctype,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND B.Name=?\n"+"order by C.SEARCHKEY=?\n", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype,btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    
    public static IssueReturnReportTableModel LoadIssueBookReport_OneToOneBookByIssueDate(AppView app,String ctype,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND B.Name=?\n"+"order by I.Issue_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype,btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    
    public static IssueReturnReportTableModel LoadIssueBookReport_OneToOneBookByPending(AppView app,String ctype,String btype)throws BasicException{
         IssueReturnReportTableModel BookDetail = new IssueReturnReportTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT C.NAME,C.SEARCHKEY,B.Name,I.Issue_date,I.to_be_retn_dt,I.return_date,I.due_charge,I.d_type,IFNULL(I.duedays, 0),IFNULL(I.nor_fine, 0)\n"+" FROM customers C,lib_bookmaster B,lib_bookissue_retn I\n"+"where B.ID=I.book_id AND I.mem_id=C.ID AND C.SEARCHKEY=? AND B.Name=? AND I.return_date is null\n"+"order by I.return_date\n", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}) ,new SerializerReadClass(IssueReturnReportTableModel.IssueReturnBook.class)).list(new Object[]{ctype,btype});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(IssueReturnReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    
    
    
    public AbstractTableModel getTableModel(){
     
         return new AbstractTableModel(){
         @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(IssueBookHeader[column]);
            }
            public int getRowCount() {
                return BookList.size();
            }
            public int getColumnCount() {

                return IssueBookHeader.length;
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              IssueReturnReportTableModel.IssueReturnBook b=BookList.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return b.getCname();
                       
                   case 1: return b.getMemid();
                   case 2: return b.getBname();
                   case 3: return b.getIssueDate();
                         
                   case 4: return b.getTo_be_rtn_date();
                   case 5: return b.getReturn_Date();
                   case 6: return b.getDueCharge();
                   case 7: return b.getD_type();
                  
                   case 8: return b.getNor_days();
                   case 9: return b.getNor_fine();
             
                   default: return null;
                 
                 }
               
            }
         
         };
          
     }
     
    
    
    
    public List<IssueReturnReportTableModel.IssueReturnBook> getBookIssueList(){
           if(BookList!=null)
        {
            return BookList;
        }
        else
            return new ArrayList<IssueReturnReportTableModel.IssueReturnBook>();
      }
     
     
    
   public static class IssueReturnBook implements SerializableRead,IKeyed{
       private String cname;
       
       private String memid;
       private Date issue_date;
       private Date to_be_retn_dt;
       private Double due_charge;
       private String d_type;
       private int nor_days;
       private int nor_fine;
       private String bname;
       private Date return_date;
       
       public String getCname(){
           return cname;
       }
       public void setCname(String cname){
           this.cname=cname;
       }
        
        public String getMemid(){
           return memid;
       }
       public void setMemid(String memid){
           this.memid=memid;
       }
        public Date getIssueDate(){
           return issue_date;
       }
       public void setIssueDate(Date issue_date){
           this.issue_date=issue_date;
       }
        public Date getTo_be_rtn_date(){
           return to_be_retn_dt;
       }
       public void setto_be_rtn_date(Date to_be_rtn_date){
           this.to_be_retn_dt=to_be_rtn_date;
       }
        public Double getDueCharge(){
           return due_charge;
       }
       public void setDueCharge(Double due_charge){
           this.due_charge=due_charge;
       }
        public String getD_type(){
           return d_type;
       }
       public void setD_type(String d_type){
           this.d_type=d_type;
       }
        public int getNor_days(){
           return nor_days;
       }
       public void setNor_days(int nor_days){
           this.nor_days=nor_days;
       }
        public int getNor_fine(){
           return nor_fine;
       }
       public void setNor_fine(int nor_fine){
           this.nor_fine=nor_fine;
       }
        public String getBname(){
           return bname;
       }
       public void setBname(String bname){
           this.bname=bname;
       }
        public Date getReturn_Date(){
           return return_date;
       }
       public void setReturn_Date(Date return_date){
           this.return_date=return_date;
       }
   
       public void readValues(DataRead dr) throws BasicException{
           
           cname=dr.getString(1);
           
           memid=dr.getString(2);
            bname=dr.getString(3);
           issue_date=dr.getTimestamp(4);
           to_be_retn_dt=dr.getTimestamp(5);
           return_date=dr.getTimestamp(6);
           due_charge=dr.getDouble(7);
           d_type=dr.getString(8);
           nor_days=dr.getInt(9);
           nor_fine=dr.getInt(10);
          
           
       
       }
       public Object getKey() {
           return this;
        }
     
       
       
   }
    
}


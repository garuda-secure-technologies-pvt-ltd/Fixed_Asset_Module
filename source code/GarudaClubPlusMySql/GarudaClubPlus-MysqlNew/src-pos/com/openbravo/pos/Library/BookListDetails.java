

package com.openbravo.pos.Library;
import ar.com.fdvs.dj.domain.builders.DataSetFactory;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Library.BookDetails;
import com.openbravo.pos.Library.BookTableModel;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinuta
 */
public class BookListDetails extends BeanFactoryDataSingle{
    
    private Session s;
    private List<BookListDetails.BookListInfo> BookList;
    private int Book_Length;
     private final static String[] BookHeader = {"RefNo" , "Name " , "Author " , "Publisher " , "Language " , "Category " , "Edition", "Media " , "Copies" , " Availability","Vendor" };
     DecimalFormat decimalformat= new DecimalFormat("#0.00##");
    
     @Override
    public  void init(Session s){
        this.s=s;
    }
    
    
    public static BookListDetails LoadBookReport_ALL(AppView app  )throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_categories C,lib_publisher P,lib_vendor V,lib_language L,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Vend_doid=V.ID AND B.Language=L.ID AND B.Media = M.ID\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{ }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
     }
    public static BookListDetails LoadBookReport_CategAuthorType(AppView app ,String Ctype,String Atype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_categories C,lib_publisher P,lib_vendor V,lib_media M,lib_language L\n"+"where B.Author=A.ID AND B.Category=C.ID  AND C.NAME=? AND A.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ctype,Atype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
     }
    
   public static BookListDetails LoadBookReport_AuthorType(AppView app ,String Atype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where A.NAME=? AND B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Vend_doid=V.ID\n"+"order by B.Name" , new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Atype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
     }
    
   public static BookListDetails LoadBookReport_CategType(AppView app ,String Ctype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where C.NAME=? AND B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Vend_doid=V.ID\n"+"order by B.Name" , new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ctype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
     }
   public static BookListDetails LoadBookReport_PublishType(AppView app ,String Ptype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where P.NAME=? AND B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Vend_doid=V.ID\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ptype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   public static BookListDetails LoadBookReport_MediaType(AppView app ,String mtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Media=M.ID AND B.Author=A.ID AND B.Category=C.ID  AND M.Name=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{mtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   
   public static BookListDetails LoadBookReport_PublishAuthType(AppView app ,String Ptype,String atype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where  B.Publisher=P.ID  AND B.Author=A.ID AND B.Category=C.ID AND  P.NAME=? AND A.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ptype,atype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   public static BookListDetails LoadBookReport_PublishCategType(AppView app ,String Ptype,String Ctype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND P.NAME=? AND C.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ptype,Ctype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   public static BookListDetails LoadBookReport_AuthPublishCategType(AppView app ,String Ptype,String Ctype,String atype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND P.NAME=? AND C.NAME=? AND A.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ptype,Ctype,atype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   public static BookListDetails LoadBookReport_AuthPublishCategmedType(AppView app ,String Ptype,String Ctype,String atype,String mtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND P.NAME=? AND C.NAME=? AND A.NAME=? AND M.Name=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{Ptype,Ctype,atype,mtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
   public static BookListDetails LoadBookReport_MediaAuthtype(AppView app,String atype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND A.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{atype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
    public static BookListDetails LoadBookReport_MediaCattype(AppView app,String ctype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND C.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{ctype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_MediaPubtype(AppView app,String ptype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND P.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{ptype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_MediaAuthCattype(AppView app,String atype,String ctype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND A.NAME=? AND C.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{atype,ctype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
    public static BookListDetails LoadBookReport_MediaAuthPubtype(AppView app,String atype,String ptype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND A.NAME=? AND P.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{atype,ptype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_MediacatPubtype(AppView app,String mtype,String ctype,String ptype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND M.Name=? AND C.NAME=? AND P.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{mtype,ctype,ptype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     
    public static BookListDetails LoadBookReport_VendorType(AppView app ,String vtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Media=M.ID AND B.Author=A.ID AND B.Category=C.ID  AND B.Vend_doid=V.ID AND V.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
    
    
    
   public static BookListDetails LoadBookReport_VendCattype(AppView app,String vtype,String ctype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND C.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_VendPubtype(AppView app,String vtype,String ptype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND P.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ptype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_VendMedtype(AppView app,String vtype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
     public static BookListDetails LoadBookReport_VendAuthtype(AppView app,String vtype,String atype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND A.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,atype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
    
   public static BookListDetails LoadBookReport_vendcatPubtype(AppView app,String vtype,String ctype,String ptype )throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND C.NAME=? AND P.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype,ptype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
   public static BookListDetails LoadBookReport_VendAutPubtype(AppView app,String vtype,String atype,String ptype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND A.NAME=? AND P.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,atype,ptype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
   public static BookListDetails LoadBookReport_VendAuthMedtype(AppView app,String vtype,String atype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND A.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,atype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
   public static BookListDetails LoadBookReport_Vendauthcattype(AppView app,String vtype,String ctype,String atype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND C.NAME=? AND A.NAME=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype,atype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
   public static BookListDetails LoadBookReport_VendCatMEdtype(AppView app,String vtype,String ctype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND C.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
   public static BookListDetails LoadBookReport_VendPubMedtype(AppView app,String vtype,String ptype,String mtype)throws BasicException{
   
       BookListDetails bookreport =new BookListDetails();
       try{
           bookreport.BookList=new ArrayList<BookListDetails.BookListInfo>();
           bookreport.BookList=new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Media=M.ID AND V.NAME=? AND P.NAME=? AND M.Name=?"+"order by B.Name\n",new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING}),new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ptype,mtype});
           
           bookreport.Book_Length=bookreport.BookList.size();
       
       }catch(BasicException ex){
           Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE,null,ex);
       
       }
   
   return bookreport;
   
   }
    
    
     public static BookListDetails LoadBookReport_AuthPublishCategVendType(AppView app ,String atype,String ptype,String ctype,String vtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND A.NAME=? AND P.NAME=? AND C.NAME=? AND V.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{atype,ptype,ctype,vtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
     public static BookListDetails LoadBookReport_VendAuthCatMedType(AppView app ,String vtype,String atype,String ctype,String mtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND V.NAME=? AND A.NAME=? AND C.NAME=? AND M.Name=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,atype,ctype,mtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
     public static BookListDetails LoadBookReport_VendAuthPubMed(AppView app ,String vtype,String atype,String ptype,String mtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND V.NAME=? AND A.NAME=? AND P.NAME=? AND M.Name=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,atype,ptype,mtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
    
     public static BookListDetails LoadBookReport_VendCatPubMed(AppView app ,String vtype,String ctype,String ptype,String mtype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND V.NAME=? AND C.NAME=? AND P.NAME=? AND M.Name=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype,ptype,mtype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
     public static BookListDetails LoadBookReport_VendCatPubMedAuth(AppView app ,String vtype,String ctype,String ptype,String mtype,String atype)throws BasicException{
                BookListDetails bookreport = new BookListDetails(); 
                
          try{
             
              
            bookreport.BookList = new ArrayList<BookListDetails.BookListInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(),"SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_publisher P,lib_language L,lib_categories C,lib_vendor V,lib_media M\n"+"where B.Publisher=P.ID AND B.Category=C.ID AND B.Media=M.ID AND B.Author=A.ID AND V.NAME=? AND C.NAME=? AND P.NAME=? AND M.Name=? AND A.NAME=?\n"+"order by B.Name\n" , new SerializerWriteBasic(new Datas[]{  Datas.STRING ,Datas.STRING ,Datas.STRING,Datas.STRING,Datas.STRING}) ,new SerializerReadClass(BookListDetails.BookListInfo.class)).list(new Object[]{vtype,ctype,ptype,mtype,atype});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookListDetails.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return bookreport;
         
   }
    
    
    
    
    
    public AbstractTableModel getTableModel(){
     
         return new AbstractTableModel(){
         @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(BookHeader[column]);
            }
            public int getRowCount() {
                return BookList.size();
            }
            public int getColumnCount() {

                return BookHeader.length;
            }
          
          public Object getValueAt(int rowIndex, int columnIndex) {
              BookListDetails.BookListInfo b=BookList.get(rowIndex);
              
                
               switch(columnIndex){
                   
                   case 0: return b.getRefNo();
                       
                   case 1: return b.getName();
                   case 2: return b.getAuthor();
                   case 3: return b.getPublisher();
                         
                   case 4: return b.getLanguage();
                   case 5: return b.getCategory();
                   case 6: return b.getEdition();
                   case 7: return b.getMedia();
                  
                   case 8: return b.getCopies();
                   case 9: return b.getAvailability();
                   case 10:return b.getVendor();
                   default: return null;
                 
                 }
               
            }
         
         };
          
     }
     
     
     public List<BookListDetails.BookListInfo> getBookList(){
           if(BookList!=null)
        {
            return BookList;
        }
        else
            return new ArrayList<BookListDetails.BookListInfo>();
      }
     
     
     public static class BookListInfo implements SerializableRead,IKeyed{
         
         private String RefNo;
         private String Name;
         private String Author;
         private String Publisher;
         private String Language;
         private String Category;
         private String Edition;
         private String Media;
         private String Copies;
         private String Availability;
         private String Vendor;
         
         
        public String getRefNo(){
              return RefNo;
          }
          public void setRefNo(String RefNo){
              this.RefNo=RefNo;
          }
          
          public String getName(){
              return Name;
          }
          public void setName(String Name){
              this.Name=Name;
          }
          
          public String getAuthor(){
              return Author;
          }
          public void setAuthor(String Author){
              this.Author=Author;
          }
          
          public String getPublisher(){
              return Publisher;
          }
          public void setPublisher(String Publisher){
              this.Publisher=Publisher;
          }
          
          public String getLanguage(){
              return Language;
          }
          public void setLanguage(String Language){
              this.Language=Language;
          }
          
          public String getCategory(){
              return Category;
          }
          public void setCategory(String Category){
              this.Category=Category;
          }
          
          public String getEdition(){
              return Edition;
          }
          public void setEdition(String Edition){
              this.Edition=Edition;
          }
          
          public String getMedia(){
              return Media;
          }
          public void setMedia(String Media){
              this.Media=Media;
          }
          
          public String getCopies(){
              return Copies;
          }
          public void setCopies(String Copies){
              this.Copies=Copies;
          }
          
          public String getAvailability(){
              return Availability;
          }
          public void setAvailability(String Availability){
              this.Availability=Availability;
          }
          public String getVendor(){
              return Vendor;
          }
          public void setVendor(String Vendor){
              this.Vendor=Vendor;
          }
          
     
          public void readValues(DataRead dr) throws BasicException{
              RefNo=dr.getString(1);
              Name=dr.getString(2);
              Author=dr.getString(3);
              Publisher=dr.getString(4);
              Language=dr.getString(5);
              Category=dr.getString(6);
              Edition=dr.getString(7);
              Media=dr.getString(8);
              Copies=dr.getString(9);
              Availability=dr.getString(10);
              Vendor=dr.getString(11);
                      
                      
          
          
          }
          
           public Object getKey() {
           return this;
        }
     
     }
 
          
    }
    


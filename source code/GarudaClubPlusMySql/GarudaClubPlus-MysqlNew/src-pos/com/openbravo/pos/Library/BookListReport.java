/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev1
 */
public class BookListReport extends BeanFactoryDataSingle {
    
    private Session s;
     private List<BookListReport.GetSetMethod> Book_List; 

    @Override
    public void init(Session s) {
       this.s=s;
    }
 public static  BookListReport LoadAllBooks(AppView app)throws BasicException{
          BookListReport All_Books = new BookListReport(); 
          
          
          try{
            All_Books.Book_List = new ArrayList<BookListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

            All_Books.Book_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,RefNo AS REFERENCE_NO,Name AS NAME,Author AS AUTHOR,Publisher AS PUBLISHER,Language AS LANGUAGE,Category AS CATEGORY,Edition AS EDITION,Media AS MEDIA,copies AS NO OF COPIES,available_flag AS AVAILABLE from lib_bookmaster,(SELECT @rownum:=0) r  ORDER BY RefNo", null ,new SerializerReadClass(BookListReport.GetSetMethod.class)).list();
     
     }
          
          catch(BasicException ex){
            
            Logger.getLogger(BookListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Books;
     }
     
     //2
     
     public static BookListReport LoadAllBooksPerticularType(AppView app,String bktype)throws BasicException{
          BookListReport All_Books_Perticular_Type = new BookListReport(); 
          
          
          try{
            All_Books_Perticular_Type.Book_List = new ArrayList<BookListReport.GetSetMethod>();
            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();

  All_Books_Perticular_Type.Book_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,RefNo AS REFERENCE_NO,Name AS NAME,Author AS AUTHOR,Publisher AS PUBLISHER,Language AS LANGUAGE,Category AS CATEGORY,Edition AS EDITION,Media AS MEDIA,copies AS NO OF COPIES,available_flag AS AVAILABLE from lib_bookmaster,(SELECT @rownum:=0) r WHERE Name in "+bktype+ " ORDER BY RefNo"  , new SerializerWriteBasic(new Datas[]{  Datas.STRING }  )  ,new SerializerReadClass(BookListReport.GetSetMethod.class)).list(new Object[]{  bktype });
     
     }
        
          catch(BasicException ex){
            
            Logger.getLogger(BookListReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return All_Books_Perticular_Type;
     }
     
     //3
     
//      public static  BookListReport LoadAllVisibleBooks(AppView app)throws BasicException{
//          BookListReport All_Visible_Books = new BookListReport(); 
//          
//          
//          try{
//            All_Visible_Books.Book_List = new ArrayList<BookListReport.GetSetMethod>();
//            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();
//
//  All_Visible_Books.Book_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r WHERE C.VISIBLE=TRUE  ORDER BY C.SEARCHKEY" , null  ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list( );
//     
//     }
//          
//          catch(BasicException ex){
//            
//            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//          return All_Visible_Members;
//     }
//     
//     //4
//      
//      public static  MemberListReport LoadAllVisibleMembersPerticularType(AppView app,String memty)throws BasicException{
//          MemberListReport All_Visible_Members_Perticular_Type = new MemberListReport(); 
//          
//          
//          try{
//            All_Visible_Members_Perticular_Type.Member_List = new ArrayList<MemberListReport.GetSetMethod>();
//            //Billed_Rooms.Member_List = new StaticSentence(app.getSession(), " SELECT C.NAME AS NAME,C.ADDRESS AS ADDRESS,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID) WHERE m.name=?", new SerializerWriteBasic() ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list();
//
//  All_Visible_Members_Perticular_Type.Member_List = new StaticSentence(app.getSession(), " SELECT @rownum:=@rownum+1 ‘ROWNO’,C.NAME AS NAME,C.SEARCHKEY AS MEM_NO,CONCAT(C.ADDRESS,C.ADDRESS2) AS ADDRESS,CONCAT(C.CITY,C.POSTAL) AS CITY,COUNTRY AS COUNTRY,C.PHONE AS CONTACT_NO,M.NAME AS TYPE_OF_MEM,C.PHONE2 AS OFFICENO,C.MOBILE AS MOBILENO FROM CUSTOMERS C join MEMTYPE M on(C.MEMTYPE=M.ID),(SELECT @rownum:=0) r WHERE C.VISIBLE=TRUE AND M.NAME in "+memty+ "  order by C.SEARCHKEY" , new SerializerWriteBasic(new Datas[]{  Datas.STRING }  )  ,new SerializerReadClass(MemberListReport.GetSetMethod.class)).list(new Object[]{ memty  });
//     
//     }
//          
//          catch(BasicException ex){
//            
//            Logger.getLogger(MemberListReport.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//          return All_Visible_Members_Perticular_Type;
//     }
      
      
      
      
      
      public List<BookListReport.GetSetMethod> getBookList(){
           if(Book_List!=null)
        {
            return Book_List;
        }
        else
            return new ArrayList<BookListReport.GetSetMethod>();
      } 
      
      
    
    
    
    
    
    
    
    
    public static class GetSetMethod implements SerializableRead,IKeyed {
    private String name;
    private String author;
    private String publisher;
    private String language;
    private String category;
    private String edition;
    private String media;
    private String copies;
    private String available_flag;
    private String refno;
    
    
     public String getRefNo(){
        return refno;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getPublisher(){
        return publisher;
    }
    public String getLanguage(){
        return language;
    }
    public String getMedia(){
     return media;
    }
    public String getCopies(){
        return copies;
     
    }
    public String getAvailableFlag(){
        return available_flag;
    }
    
    public void setRefNo(String RefNo){
        this.refno=RefNo;
    }
    
     public void setName(String name){
        this.name=name;
    }
     
    public void setAuthor(String author){
        this.author=author;
    }
    
     public void setPublisher(String publisher){
        this.name=publisher;
    }
     
       public void setLanguage(String language){
        this.language=language;
    }
        public void setCategory(String category){
        this.category=category;
    }
         public void setEdition(String edition){
        this.edition=edition;
    }
          public void setMedia(String media){
        this.media=media;
    }
           public void setCopies(String copies){
        this.copies=copies;
    }
            public void setAvailabilitys(String available_flag){
        this.available_flag=available_flag;
    }
    
    
        

        @Override
        public void readValues(DataRead dr) throws BasicException {
           refno=dr.getString(1);
        name=dr.getString(2);
        author=dr.getString(3);
        publisher=dr.getString(4);
        language=dr.getString(5);
        category=dr.getString(6);
        edition=dr.getString(7);
        media=dr.getString(8);
        copies=dr.getString(9);
        available_flag=dr.getString(10);
        }

        @Override
        public Object getKey() {
            return this; 
        }
    }
    
}

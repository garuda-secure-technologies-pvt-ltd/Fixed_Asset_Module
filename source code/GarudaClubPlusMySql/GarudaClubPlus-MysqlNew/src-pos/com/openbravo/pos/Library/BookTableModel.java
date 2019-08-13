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
import com.openbravo.pos.forms.AppLocal;


import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinuta
 */
public class BookTableModel extends BeanFactoryDataSingle{
    private Session s;
    private List<BookTableModel.BookInfo> BookList;
    private int Book_Length;
     private final static String[] BookHeader = {"RefNo" , "Name " , "Author " , "Publisher " , "Language " , "Category " , "Edition", "Media " , "Copies" , " Availability","Vendor" };
     DecimalFormat decimalformat= new DecimalFormat("#0.00##");
    
   @Override
    public  void init(Session s){
        this.s=s;
    }
    
    public static BookTableModel LoadBookReport_All(AppView app)throws BasicException{
         BookTableModel BookDetail = new BookTableModel(); 
         
          try{
            BookDetail.BookList = new ArrayList<BookTableModel.BookInfo>();
            BookDetail.BookList = new StaticSentence(app.getSession(), "SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag\n"+"FROM lib_bookmaster B,lib_author A,lib_categories C,lib_publisher P,lib_language L,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID  AND B.Language=L.ID AND B.Media = M.ID\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{}) ,new SerializerReadClass(BookTableModel.BookInfo.class)).list(new Object[]{});

            BookDetail.Book_Length = BookDetail.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
          return BookDetail;
         
     }
    
    
     
      
    
     
      
     
     
     public static BookTableModel LoadBookReport_bookType(AppView app , String BookType)throws BasicException{
         BookTableModel bookreport = new BookTableModel(); 
         
          try{
            bookreport.BookList = new ArrayList<BookTableModel.BookInfo>();
            bookreport.BookList = new StaticSentence(app.getSession(), "SELECT B.RefNo,B.Name,A.NAME,P.NAME,L.Name,C.NAME,B.Edition,M.Name,B.copies,B.available_flag,V.NAME\n"+"FROM lib_bookmaster B,lib_author A,lib_categories C,lib_publisher P,lib_vendor V,lib_language L,lib_media M\n"+"where B.Author=A.ID AND B.Category=C.ID AND B.Publisher=P.ID AND B.Vend_doid=V.ID AND B.Language=L.ID AND B.Media = M.ID AND C.NAME=?\n"+"order by B.Name\n", new SerializerWriteBasic(new Datas[]{  Datas.STRING  }) ,new SerializerReadClass(BookTableModel.BookInfo.class)).list(new Object[]{BookType});

            bookreport.Book_Length = bookreport.BookList.size();
            
        }
        catch(BasicException ex){
            
            Logger.getLogger(BookTableModel.class.getName()).log(Level.SEVERE, null, ex);
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
              BookTableModel.BookInfo b=BookList.get(rowIndex);
              
                
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
                   default: return null;
                 
                 }
               
            }
         
         };
          
     }
     
     
     public List<BookTableModel.BookInfo> getBookList(){
           if(BookList!=null)
        {
            return BookList;
        }
        else
            return new ArrayList<BookTableModel.BookInfo>();
      }
     
     
     public static class BookInfo implements SerializableRead,IKeyed{
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
                      
                      
          
          
          }
          
           public Object getKey() {
           return this;
        }
     
     }
 
          
    }
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.DataWrite;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializableWrite;

/**
 *
 * @author dev1
 */
public class Book implements SerializableRead,SerializableWrite{
    
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
    
    
    
    
    
    
    
    
    
   

    @Override
    public void writeValues(DataWrite dp) throws BasicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readValues(DataRead dr) throws BasicException {
         //To change body of generated methods, choose Tools | Templates.
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
}

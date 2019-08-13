/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
//import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteParams;
//import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
//import com.openbravo.data.loader.StaticSentence;
//import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.customers.CustomerInfoExt2;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.io.FileInputStream;
//import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author swathi
 */
public class InsertMainAccounts {
    public  void accessConnector(String filename){
        List<CustomerInfoExt2> cusinfo=new ArrayList<CustomerInfoExt2>();
 try {
    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    HSSFSheet sheet = wb.getSheetAt(0);
    HSSFRow row;
    HSSFCell cell;
    HSSFCell cell1;
    HSSFCell cell2;
    HSSFCell cell3;
    HSSFCell cell4;
    HSSFCell cell5;
    HSSFCell cell6;
    HSSFCell cell7;
    HSSFCell cell8;
    HSSFCell cell9;
    HSSFCell cell10;

    Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession();

    int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();

    int cols = 0; // No of columns
    int tmp = 0;

    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }
  java.util.Date d=new java.util.Date();
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        if(row != null) {
          //  for(int c = 0; c < cols; c++) {
                cell = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                 cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                 cell8= row.getCell((short)8);
                cell9 = row.getCell((short)9);
                cell10 = row.getCell((short)10);
                String id=UUID.randomUUID().toString();
                if(cell != null ) {
                    // Your code here
                  //  if(c)
                    String temp="";
                    try{
                        temp=cell8.toString();

                    }catch(Exception e){
                    }
                    boolean del=Boolean.parseBoolean(cell5.toString());
                    boolean del1=Boolean.parseBoolean(cell6.toString());
                   

                      Object[] value=new Object[]{UUID.randomUUID().toString(),cell.toString(),cell1,cell2,cell3,cell4,Boolean.parseBoolean(cell5.toString()),Boolean.parseBoolean(cell6.toString()),cell7,temp,cell9,cell10,d,new Double(0.0),true};
                   new PreparedSentence(s
                  , "INSERT INTO ACCOUNTMASTER(ID,SEARCHKEY,NAME,DESCRIPTION,TYPE_,SIGN,DOCUMENT,SUMMARY,DEFAULT_,PARENT,LEVEL_,OPERANDS,STARTDATE,OPENINGBALANCE,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.OBJECT,Datas.STRING,Datas.OBJECT,Datas.OBJECT,Datas.OBJECT,Datas.OBJECT,Datas.BOOLEAN,Datas.BOOLEAN,Datas.OBJECT,Datas.STRING,Datas.OBJECT,Datas.OBJECT,Datas.TIMESTAMP,Datas.OBJECT,Datas.BOOLEAN})
                  ).exec(value);

                }
          //  }
        }
    }
} catch(Exception ioe) {
    ioe.printStackTrace();
}

    }



}



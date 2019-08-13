/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Library;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.Accounts.AccountMaster;
import com.openbravo.pos.Accounts.AccountMasterExt;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author dev1
 */
public class Lib_DetailsFeatcher {
    Date date;
    ArrayList<Object> list3 = new ArrayList<Object>();
   
     /*public  void vendorXL(String filename,AppView m_App,DataLogicFacilities dlfac,Component comp){
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
             
             
             Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession(); 
             int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();

    int cols = 0; // No of columns
    int tmp = 0;
    int c=0,cnt=0;
    String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
    list3.add(sheet.getRow(0));
    ArrayList name = new ArrayList();
    ArrayList acct = new ArrayList();
    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }
    java.util.Date d=new java.util.Date();
    
     if(list3.size()==1){
         
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
       //int col=row.getLastCellNum(); 
    if(row != null){
        cell7 = row.getCell((short)7);
        cell9 = row.getCell((short)9);
        if(cell9==null)
           c2="";
        else
           c2=ConvertDoubleToString(cell9.getNumericCellValue());
        if(cell7==null)
           c3="";
        else
           c3=cell7.toString();
        //c3=cell7.toString();
        //c2=ConvertDoubleToString(cell9.getNumericCellValue());
        c4=row.getCell((short)0).toString();
        String temp="null";
                    
                    List<Object> vend_lis = new ArrayList<Object>();
                                    vend_lis  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT A.NAME FROM ACCOUNTMASTER A WHERE  LEVEL_='S' AND ACTIVE=TRUE ORDER BY A.NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                                   
                                    
                        if(cell7!=null){
                            for(int i=0; i<vend_lis.size(); i++)
                            {
                               String tempo= vend_lis.get(i).toString();
                              
                              if(tempo.equalsIgnoreCase(c3))
                                {
                                     List<Object> vend_list1 = new ArrayList<Object>();
                                    vend_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM ACCOUNTMASTER  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)vend_list1.get(0);
                                    
                                    break;
                                }
                           
                            }
                            if(temp.equals("null") || !(c2.equals("1")||c2.equals("2"))){
                                acct.add(c3);
                                if(!list3.contains(row))
                               list3.add(row);
                            }
                        }
                        int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM LIB_VENDOR WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c4).toString());              
                       if(count!=0) {
                           //JOptionPane.showMessageDialog(comp, "Vendor with the name "+c4+" already exist", null, JOptionPane.OK_OPTION);
                          if(!list3.contains(row))
                            list3.add(row);  
                          name.add(c4);
                       }
        }else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper vendor type XL Sheet.pls check..", null, JOptionPane.OK_OPTION);
                        break;            
            }
    }
    if(list3.size()>1){
        
     writeToVendXl(name,acct);
        JOptionPane.showMessageDialog(comp, " "+(list3.size()-1)+" number of Rows ERRORS found", "Error", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(comp, "Errored rows are copied to /home/dev1/Documents/tempvendor.xls...pls check.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
     
    }
    }
     if(list3.size()==1){
       //JOptionPane.showMessageDialog(comp, "No errors found..Do you want to insert", null, JOptionPane.OK_OPTION);  
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        
        //int col=row.getLastCellNum();
        //here 10 is the cell size.it changes when the nor of cell(n) changes 
        if(row != null )//&&col==10 ) 
        {
          //  for(int c = 0; c < cols; c++) {
                cell = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                cell8 = row.getCell((short)8);
                cell9 = row.getCell((short)9);
                
                //String id=UUID.randomUUID().toString();
                if(cell != null ||cell1 != null ||cell2 != null ||cell3 != null ||cell4 != null ||cell5 != null ||cell6 != null ||cell7 != null ||cell8 != null ||cell9 != null ) {
                    
                  //  if(c)
                    //String c1=null, c2=null,c3=cell7.toString(), c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
                    if(cell==null)
                        c4="";
                    else
                        c4=cell.toString();
                    if(cell1==null)
                        c5="";
                    else
                        c5=cell1.toString();
                    if(cell2==null)
                        c6="";
                    else
                        c6=cell2.toString();
                    if(cell3==null)
                        
                        c1="";
                    else
                        c1=cell3.toString();
                    if(cell4==null)
                        
                        c7="";
                    else
                        c7=cell4.toString();
                    
                    if(cell5==null)
                        c8="";
                    else
                        c8=cell5.toString();
                    if(cell6==null)
                        c9="";
                    else
                        c9=cell6.toString();
                    if(cell8==null)
                        c0="";
                    else
                        c0=cell8.toString();
                    
                    if(cell9==null)
                        c2="1";
                    else
                        c2=ConvertDoubleToString(cell9.getNumericCellValue());
                    if(cell7==null)
                        c3="";
                    else
                        c3=cell7.toString();
                    String temp="null";
                    
                    List<Object> vend_list = new ArrayList<Object>();
                                    vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT A.NAME FROM ACCOUNTMASTER A WHERE  LEVEL_='S' AND ACTIVE=TRUE ORDER BY A.NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                                   
                                    try{
                        if(cell7!=null){
                            for(int i=0; i<vend_list.size(); i++)
                            {
                               String tempo= vend_list.get(i).toString();
                              
                              if(tempo.equalsIgnoreCase(c3))
                                {
                                     List<Object> vend_list1 = new ArrayList<Object>();
                                    vend_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM ACCOUNTMASTER  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)vend_list1.get(0);
                                    
                                    break;
                                }
                              
                            
                            }
                           
                }
                        
                        c1=ConvertDoubleToString(cell3.getNumericCellValue());
                        
                        //c2=ConvertDoubleToString(cell9.getNumericCellValue());
                       c=Integer.parseInt(c2);
                    }catch(Exception e){
                    }
                   
                        Object[] value=new Object[]{UUID.randomUUID().toString(),true,c4,c5,c6,c1,c7,c8,c9,temp,c0,c,m_App.getAppUserView().getUser().getName(),d,m_App.getProperties().getHost()};
                   new PreparedSentence(s
                  , "INSERT INTO LIB_VENDOR (ID,ACTIVE,NAME,ADDRESS,CONTACTPERSON,CONTACTNUM,PANNO,TINNO,CST,ACCOUNT,INSTRUCTION,Vendflag,CREATEDBY,CRDATE,CREATEDHOST) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.INT,Datas.STRING,Datas.TIMESTAMP,Datas.STRING})
                  ).exec(value);
                   cnt++;
                                    
                }
                }
         
    }
    JOptionPane.showMessageDialog(comp, " "+cnt+" number of rows successfully inserted", null, JOptionPane.OK_OPTION);
    list3.clear();
         }else{
                        JOptionPane.showMessageDialog(comp, "Data not inserted.. Please correct ERRORS and submit again", "Error", JOptionPane.OK_OPTION);
             }   

    //JOptionPane.showMessageDialog(comp, "Created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
         }catch(Exception ioe) {
    ioe.printStackTrace();
}
         
     }*/
     public static String ConvertDoubleToString(double value){
        DecimalFormat twoDForm = new DecimalFormat("##.#");
		return twoDForm.format(value);
    }
     /*public void writeToVendXl(ArrayList nm,ArrayList acc) throws IOException{
        FileOutputStream fos = null;
        ArrayList error =new ArrayList();
         //double c = 0,cx1 = 0;
         try {
             fos=new FileOutputStream(new File("/home/dev1/Documents/tempVendor.xls"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lib_DetailsFeatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("temp");
            HSSFRow row;
            HSSFRow r;
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
             int rcount=list3.size();
                for(int i=0;i<rcount;i++){
                    r=(HSSFRow)list3.get(i);
                row = spreadSheet.createRow((short) i);
                cell = row.createCell((short)0);
                cell1 = row.createCell((short)1);
                cell2 = row.createCell((short)2);
                cell3 = row.createCell((short)3);
                cell4 = row.createCell((short)4);
                cell5 = row.createCell((short)5);
                cell6 = row.createCell((short)6);
                cell7 = row.createCell((short)7);
                cell8 = row.createCell((short)8);
                cell9 = row.createCell((short)9);
                cell10 = row.createCell((short)10);
                String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
                    if(r.getCell((short)0)==null)
                        c0="";
                    else
                        c0=r.getCell((short)0).toString();
                    if(r.getCell((short)1)==null)
                        c1="";
                    else
                        c1=r.getCell((short)1).toString();
                    if(r.getCell((short)2)==null)
                        c2="";
                    else
                        c2=r.getCell((short)2).toString();
                    if(r.getCell((short)3)==null)
                        c3="";
                    else
                         c3=r.getCell((short)3).toString();
                    if(r.getCell((short)4)==null)
                        c4="";
                    else
                        c4=r.getCell((short)4).toString();
                    if(r.getCell((short)5)==null)
                        c5="";
                    else
                        c5=r.getCell((short)5).toString();
                    if(r.getCell((short)6)==null)
                        c6="";
                    else
                        c6=r.getCell((short)6).toString();
                    if(r.getCell((short)7)==null)
                        c7="";
                    else
                        c7=r.getCell((short)7).toString();
                    if(r.getCell((short)8)==null)
                        c8="";
                    else
                        c8=r.getCell((short)8).toString();
                    if(r.getCell((short)9)==null)
                        c9="";
                    else
                        c9=r.getCell((short)9).toString();
               cell.setCellValue(c0);
               cell1.setCellValue(c1);
               cell2.setCellValue(c2);
               if(i>0){
                   int cc9=0;
                   long cc3=0;
                   if(c3.equals("")){
                      
                    cell3.setCellValue(c3);
                   }else{
                       c3=ConvertDoubleToString(r.getCell((short)3).getNumericCellValue());
                   cc3=Long.parseLong(c3);
                       cell3.setCellValue(cc3);
                   }
                   if(c9.equals("")){
                      
                    cell9.setCellValue(c9);
                   }else{
                       c9=ConvertDoubleToString(r.getCell((short)9).getNumericCellValue());
                   cc9=Integer.parseInt(c9);
                  cell9.setCellValue(cc9);
                   }
                
                if(!(cc9==1 ||cc9==2)){
                    cell10.setCellValue("flag(1or2)");
                }
                for(int k=0;k<nm.size();k++){
                    if(c0.equalsIgnoreCase(nm.get(k).toString())){
                       //cell10.setCellValue("name exist");
                       error.add("name exist");
                    }
                }for(int k=0;k<nm.size();k++){
                    if(c7.equalsIgnoreCase(acc.get(k).toString())){
                       //cell10.setCellValue("name exist");
                       error.add("account");
                    }
                }
                cell10.setCellValue(error.toString());
               }else{
               cell.setCellValue(c0);   
               cell3.setCellValue(c3);
               cell9.setCellValue(c9);
               cell10.setCellValue("ErrorStatus");
               }
               cell4.setCellValue(c4);
               cell5.setCellValue(c5);
               cell6.setCellValue(c6);
               cell7.setCellValue(c7);
               cell8.setCellValue(c8);
                error.clear();
               }
            workBook.write(fos);
            //list3.clear();
     }*/
      public  void publisherXL(String filename,AppView m_App,DataLogicFacilities dlfac,Component comp){
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
             
             
             Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession(); 
             int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();
   // int col=0;
    int cols = 0; // No of columns
    int tmp = 0;
    int cnt=0;
    String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
    list3.add(sheet.getRow(0));
    ArrayList name = new ArrayList();
    ArrayList lang = new ArrayList();
    ArrayList vend_do = new ArrayList();
    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }
    java.util.Date d1=new java.util.Date();
    if(list3.size()==1){
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();
    if(row != null && col==10){
        cell8 = row.getCell((short)8);
        cell9 = row.getCell((short)9);
        if(cell8==null)
            c8="";
        else
           c8=cell8.toString();
        if(cell9==null)
            c9="";
        else
            c9=cell9.toString();
        
        c0=row.getCell((short)0).toString();
        String temp="null",temp1="null";
                    
                     List<Object> pub_list = new ArrayList<Object>();
                                    pub_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> pub_list1 = new ArrayList<Object>();
                                    pub_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT v.name FROM lib_vendor v WHERE v.active=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
           
                                    
                        if(cell8!=null){
                            for(int i=0; i<pub_list.size(); i++)
                            {
                               String tempo= pub_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c8))
                                {
                                     List<Object> publ_list1 = new ArrayList<Object>();
                                    publ_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "select l.id from lib_language l where l.name=? and l.active=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)publ_list1.get(0);
                                  
                                    break;
                                }
                           }
                            }if(temp.equals("null")){
                            lang.add(c8);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                            
                 
                        if(cell9!=null){
                            for(int i=0; i<pub_list1.size(); i++)
                            {
                               String tempo1= pub_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c9))
                                {
                                     List<Object> publ_list2 = new ArrayList<Object>();
                                    publ_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT v.id FROM lib_vendor v  WHERE v.name=? and v.ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)publ_list2.get(0);
                                  
                                    break;
                                }
                           }
                            }if(temp1.equals("null")){
                            vend_do.add(c9);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                 
                        int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_publisher WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c0).toString());              
                       if(count!=0) {
                           //JOptionPane.showMessageDialog(comp, "Vendor with the name "+c4+" already exist", null, JOptionPane.OK_OPTION);
                          if(!list3.contains(row))
                            list3.add(row);  
                            name.add(c0);
                       }
        }else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper Publisher type XL Sheet.pls check..", null, JOptionPane.OK_OPTION);
                        break;            
            }
    }
    if(list3.size()>1){
     JOptionPane.showMessageDialog(comp, " "+(list3.size()-1)+" number of Rows ERROR out of "+(rows-1)+" rows", "Error", JOptionPane.INFORMATION_MESSAGE);   
     String flname = new Lib_DetailsFetcherPanel().errorFilePath();
     writeToPublisherXl(name,lang,vend_do,flname);
        
        JOptionPane.showMessageDialog(comp, "Error Report is saved under "+flname+" Directory...pls check.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
     
    }
    }
    if(list3.size()==1){
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();
        //here 10 is the cell size.it changes when the nor of cell(n) changes 
        if(row != null&& col==10) {
          //  for(int c = 0; c < cols; c++) {
                cell  = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                cell8 = row.getCell((short)8);
                cell9 = row.getCell((short)9);
               
                String id=UUID.randomUUID().toString();
                if(cell != null ||cell1 != null ||cell2 != null ||cell3 != null ||cell4 != null ||cell5 != null ||cell6 != null ||cell7 != null ||cell8 != null ||cell9 != null ) {
                    
                  //  if(c)
                    //String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
                    if(cell==null)
                        c0="";
                    else
                        c0=cell.toString();
                    if(cell1==null)
                        c1="";
                    else
                        c1=cell1.toString();
                    if(cell2==null)
                        c2="";
                    else
                        c2=cell2.toString();
                    if(cell3==null)
                        c3="";
                    else
                        c3=ConvertDoubleToString(cell3.getNumericCellValue());
                    if(cell4==null)
                        c4="";
                    else
                        c4=cell4.toString();
                    if(cell5==null)
                        c5="";
                    else
                        c5=cell5.toString();
                    if(cell6==null)
                        c6="";
                    else
                        c6=cell6.toString();
                    if(cell7==null)
                        c7="";
                    else
                        c7=ConvertDoubleToString(cell7.getNumericCellValue());
                    if(cell8==null)
                        c8="";
                    else
                        c8=cell8.toString();
                    if(cell9==null)
                        c9="";
                    else
                        c9=cell9.toString();
                    String temp="null",temp1="null";
                    
                    List<Object> pub_list = new ArrayList<Object>();
                                    pub_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> pub_list1 = new ArrayList<Object>();
                                    pub_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT v.name FROM lib_vendor v WHERE v.active=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                

                                    try{
                        if(cell8!=null){
                            for(int i=0; i<pub_list.size(); i++)
                            {
                               String tempo= pub_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c8))
                                {
                                     List<Object> publ_list1 = new ArrayList<Object>();
                                    publ_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "select l.id from lib_language l where l.name=? and l.active=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)publ_list1.get(0);
                                  
                                    break;
                                }
                           }
                            
                 }
                        if(cell9!=null){
                            for(int i=0; i<pub_list1.size(); i++)
                            {
                               String tempo1= pub_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c9))
                                {
                                     List<Object> publ_list2 = new ArrayList<Object>();
                                    publ_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT v.id FROM lib_vendor v  WHERE v.name=? and v.ACTIVE=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)publ_list2.get(0);
                                  
                                    break;
                                }
                           }
                            
                 }
                        
                    }catch(Exception e){
                    }
                      
                      Object[] value=new Object[]{id,c0,c1,c2,c3,c4,c5,c6,c7,temp,temp1,m_App.getAppUserView().getUser().getName(),d1,m_App.getProperties().getHost(),true};
                   new PreparedSentence(s
                  , "INSERT INTO lib_publisher (ID,NAME,ADDRESS,ADDRESS2,POSTAL,CITY,STATE,COUNTRY,PHONE,language,VENDOR_ID,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN})
                  ).exec(value);
                   cnt++;
                    
                }
          
        }
    }
    JOptionPane.showMessageDialog(comp, " "+cnt+" number of rows inserted", null, JOptionPane.OK_OPTION);
    list3.clear();
    
    }else{
                        JOptionPane.showMessageDialog(comp, "Data not inserted.. Please correct ERRORS and submit again", "Error", JOptionPane.OK_OPTION);
             }
             
         }catch(Exception ioe) {
    ioe.printStackTrace();
}
         
     }
      
      public void writeToPublisherXl(ArrayList nm,ArrayList lng,ArrayList vend,String filename) throws IOException{
        FileOutputStream fos = null;
        ArrayList error = new ArrayList();
         //double c = 0,cx1 = 0;
         try {
           
             fos=new FileOutputStream(new File(filename+"/errorPublisher.xls"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lib_DetailsFeatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("publisher");
            HSSFRow row;
            HSSFRow r;
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
             int rcount=list3.size();
                for(int i=0;i<rcount;i++){
                    r=(HSSFRow)list3.get(i);
                row = spreadSheet.createRow((short) i);
                cell = row.createCell((short)0);
                cell1 = row.createCell((short)1);
                cell2 = row.createCell((short)2);
                cell3 = row.createCell((short)3);
                cell4 = row.createCell((short)4);
                cell5 = row.createCell((short)5);
                cell6 = row.createCell((short)6);
                cell7 = row.createCell((short)7);
                cell8 = row.createCell((short)8);
                cell9 = row.createCell((short)9);
                cell10 = row.createCell((short)10);
                String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c9=null, c0=null;
                    if(r.getCell((short)0)==null)
                        c0="";
                    else
                        c0=r.getCell((short)0).toString();
                    if(r.getCell((short)1)==null)
                        c1="";
                    else
                        c1=r.getCell((short)1).toString();
                    if(r.getCell((short)2)==null)
                        c2="";
                    else
                        c2=r.getCell((short)2).toString();
                    if(r.getCell((short)3)==null)
                        c3="";
                    else
                         c3=r.getCell((short)3).toString();
                    if(r.getCell((short)4)==null)
                        c4="";
                    else
                        c4=r.getCell((short)4).toString();
                    if(r.getCell((short)5)==null)
                        c5="";
                    else
                        c5=r.getCell((short)5).toString();
                    if(r.getCell((short)6)==null)
                        c6="";
                    else
                        c6=r.getCell((short)6).toString();
                    if(r.getCell((short)7)==null)
                        c7="";
                    else
                        c7=r.getCell((short)7).toString();
                    if(r.getCell((short)8)==null)
                        c8="";
                    else
                        c8=r.getCell((short)8).toString();
                    if(r.getCell((short)9)==null)
                        c9="";
                    else
                        c9=r.getCell((short)9).toString();
               cell.setCellValue(c0);
               cell1.setCellValue(c1);
               cell2.setCellValue(c2);
               if(i>0){
                   if(c3.equals("")){
                      
                    cell3.setCellValue(c3);
                   }else{
                       c3=ConvertDoubleToString(r.getCell((short)3).getNumericCellValue());
                  int cc3=Integer.parseInt(c3);
                       cell3.setCellValue(cc3);
                   }
                  if(c7.equals("")){
                     
                    cell7.setCellValue(c7); 
                  }else{
                      c7=ConvertDoubleToString(r.getCell((short)7).getNumericCellValue());
                  long cc7=Long.parseLong(c7);
                      cell7.setCellValue(cc7);
                  }
                  for(int k=0;k<nm.size();k++){
                    if(c0.equalsIgnoreCase(nm.get(k).toString())){
                       //cell10.setCellValue("name exist");
                        error.add("name exist");
                    }
                }for(int k=0;k<lng.size();k++){
                    if(c8.equalsIgnoreCase(lng.get(k).toString())){
                       //cell10.setCellValue("language");
                        error.add("language");
                    }
                }for(int k=0;k<vend.size();k++){
                    if(c9.equalsIgnoreCase(vend.get(k).toString())){
                       //cell10.setCellValue("vendor");
                        error.add("vendor");
                    }
                }
                cell10.setCellValue(error.toString());
                  
               }else{
               cell.setCellValue(c0);    
               cell3.setCellValue(c3);
               cell7.setCellValue(c7);
               cell10.setCellValue("ErrorStatus");
               }
               cell4.setCellValue(c4);
               cell5.setCellValue(c5);
               cell6.setCellValue(c6);
               cell9.setCellValue(c9);
               cell8.setCellValue(c8);
               error.clear();
               
            }
            workBook.write(fos);
            //list3.clear();
     }
      
       /*public  void categoryXL(String filename,AppView m_App,DataLogicFacilities dlfac,Component comp){
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
    java.util.Date d2=new java.util.Date();
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=row.getLastCellNum();
        //here 9 is the cell size.it changes when the nor of cell(n) changes 
        if(row != null && col==9 ) {
          
                cell  = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                cell8 = row.getCell((short)8);
               
                String id=UUID.randomUUID().toString();
                if(cell != null ||cell1 != null ||cell2 != null ||cell3 != null ||cell4 != null ||cell5 != null ||cell6 != null ||cell7 != null ||cell8 != null ) {
                    
                  
                    String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c7=null, c8=null, c0=null;
                    if(cell==null)
                        c0="";
                    else
                        c0=cell.toString();
                    if(cell1==null)
                        c1="";
                    else
                        c1=cell1.toString();
                    if(cell2==null)
                        c2="";
                    else
                        c2=cell2.toString();
                    if(cell3==null)
                        c3="";
                    else
                        c3=cell3.toString();
                    if(cell4==null)
                        c4="";
                    else
                        c4=cell4.toString();
                    if(cell5==null)
                        c5="";
                    else
                        c5=cell5.toString();
                    if(cell6==null)
                        c6="";
                    else
                        c6=cell6.toString();
                    if(cell7==null)
                        c7="";
                    else
                        c7=cell7.toString();
                    if(cell8==null)
                        c8="";
                    else
                        c8=cell8.toString();
                   
                    String temp="null",temp1="null",temp2="null",temp3="null",temp4="null",temp5="null";
                   
                    List<Object> cat_list = new ArrayList<Object>();
                                    cat_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> cat_list1 = new ArrayList<Object>();
                                    cat_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE  c.parentid in(select c.id FROM lib_categories c where active =1) and active =1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                    
                    List<Object> cat_list2 = new ArrayList<Object>();
                                    cat_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT A.NAME FROM ACCOUNTMASTER A WHERE  LEVEL_='S' AND ACTIVE=TRUE ORDER BY A.NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                 
                    
                    List<Object> cat_list3 = new ArrayList<Object>();
                                    cat_list3  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT NAME FROM FACILITY WHERE ACTIVE=TRUE ORDER BY NAME",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                 
                                           
                                    
                                    
                                    try{
                        if(cell1!=null){
                            for(int i=0; i<cat_list.size(); i++)
                            {
                               String tempo = cat_list.get(i).toString();
                           
                              if(tempo.equalsIgnoreCase(c1))
                                {
                                     List<Object> catog_list1 = new ArrayList<Object>();
                                    catog_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp = (String)catog_list1.get(0);
                                 break;
                                }
                           }
                 }
                        if(cell4!=null){
                            for(int i=0; i<cat_list1.size(); i++)
                            {
                               String tempo1= cat_list1.get(i).toString();
                           
                              if(tempo1.equalsIgnoreCase(c4))
                                {
                                     List<Object> catog_list2 = new ArrayList<Object>();
                                    catog_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_categories c WHERE  c.parentid in(select c.id FROM lib_categories c where active =1) and active =1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)catog_list2.get(0);
                                 
                                    break;
                                }
                           }
                 }
                        
                        if(cell5!=null){
                            for(int i=0; i<cat_list2.size(); i++)
                            {
                               String tempo1= cat_list2.get(i).toString();
                           
                              if(tempo1.equalsIgnoreCase(c5))
                                {
                                     List<Object> catog_list3 = new ArrayList<Object>();
                                    catog_list3  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM ACCOUNTMASTER  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp2 =(String)catog_list3.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(cell6!=null){
                            for(int i=0; i<cat_list2.size(); i++)
                            {
                               String tempo1= cat_list2.get(i).toString();
                          
                              if(tempo1.equalsIgnoreCase(c6))
                                {
                                     List<Object> catog_list4 = new ArrayList<Object>();
                                    catog_list4  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM ACCOUNTMASTER  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp3 =(String)catog_list4.get(0);
                               
                                    break;
                                }
                           }
                        }    
                            if(cell7!=null){
                            for(int i=0; i<cat_list2.size(); i++)
                            {
                               String tempo1= cat_list2.get(i).toString();
                             
                              if(tempo1.equalsIgnoreCase(c7))
                                {
                                     List<Object> catog_list5 = new ArrayList<Object>();
                                    catog_list5  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM ACCOUNTMASTER  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp4 =(String)catog_list5.get(0);
                                  
                                    break;
                                }
                           }
                            }   
                            if(cell8!=null){
                            for(int i=0; i<cat_list3.size(); i++)
                            {
                               String tempo1= cat_list3.get(i).toString();
                          
                              if(tempo1.equalsIgnoreCase(c8))
                                {
                                     List<Object> catog_list6 = new ArrayList<Object>();
                                    catog_list6  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM FACILITY  WHERE name=? ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp5 =(String)catog_list6.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        
                    }catch(Exception e){
                    }
                       int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_categories WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c0).toString());              
                       if(count==0) {
                      Object[] value=new Object[]{id,c0,temp,c2,c3,temp1,temp2,temp3,temp4,temp5,m_App.getAppUserView().getUser().getName(),d2,m_App.getProperties().getHost(),true};
                   new PreparedSentence(s
                  , "INSERT INTO lib_categories (ID,NAME,parentid,Address,DispName,Primaryparent,RevenueHead,DisposalHead,CustCurrAcct,Facility,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN})
                  ).exec(value);
                    JOptionPane.showMessageDialog(comp, "Created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                        JOptionPane.showMessageDialog(comp, "Category with the name "+c0+" already exist", null, JOptionPane.OK_OPTION);
                    }
                }
       }else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper category type XL.pls check..", null, JOptionPane.OK_OPTION);
        break;            
        }
    }
             
         }catch(Exception ioe) {
    ioe.printStackTrace();
}
         
     }*/
       
     
      public  void authorXL(String filename,AppView m_App,DataLogicFacilities dlfac,Component comp){
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
             HSSFCell cell11;
             HSSFCell cell12;
             
             
             Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession(); 
             int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();

    int cols = 0; // No of columns
    int tmp = 0,cnt=0;
    String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null;
    list3.add(sheet.getRow(0));
    ArrayList name = new ArrayList();
    ArrayList lang1 = new ArrayList();
    ArrayList catgy = new ArrayList();
    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }
    Date d1=new Date();
    if(list3.size()==1){
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();
    if(row != null && col==13){
        cell1 = row.getCell((short)1);
        cell3 = row.getCell((short)3);
        if(cell1==null)
            c1="";
        else
           c1=cell1.toString();
        if(cell3==null)
            c3="";
        else
            c3=cell3.toString();
        
        c0=row.getCell((short)0).toString();
        String temp="null",temp1="null";
                    
                    List<Object> cat_list2 = new ArrayList<Object>();
                                    cat_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> lang_list2 = new ArrayList<Object>();
                                    lang_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                

                                    
                        if(cell1!=null){
                            for(int i=0; i<cat_list2.size(); i++)
                            {
                               String tempo= cat_list2.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c1))
                                {
                                     List<Object> catg_list1 = new ArrayList<Object>();
                                    catg_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)catg_list1.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(temp.equals("null")){
                            catgy.add(c1);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                        if(cell3!=null){
                            for(int i=0; i<lang_list2.size(); i++)
                            {
                               String tempo1= lang_list2.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c3))
                                {
                                     List<Object> lan_list = new ArrayList<Object>();
                                    lan_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)lan_list.get(0);
                                  
                                    break;
                                }
                           }
                 }if(temp1.equals("null")){
                            lang1.add(c3);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                 
                        int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_author WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c0).toString());              
                       if(count!=0) {
                           //JOptionPane.showMessageDialog(comp, "Vendor with the name "+c4+" already exist", null, JOptionPane.OK_OPTION);
                          if(!list3.contains(row))
                            list3.add(row);  
                            name.add(c0);
                       }
        }else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper Author type XL Sheet.pls check..", null, JOptionPane.OK_OPTION);
                        break;            
            }
    }
    if(list3.size()>1){
      
        JOptionPane.showMessageDialog(comp, " "+(list3.size()-1)+" number of Rows ERROR out of "+(rows-1)+" rows", "Error", JOptionPane.INFORMATION_MESSAGE);   
     String flname = new Lib_DetailsFetcherPanel().errorFilePath();
     writeToAuthorXl(name,lang1,catgy,flname);
        
        JOptionPane.showMessageDialog(comp, "Error Report is saved under "+flname+" Directory...pls check.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
     
    }
    }
    if(list3.size()==1){
    for(int r = 1; r <rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();

        //here 13 is the cell size.it changes when the nor of cell(n) changes 
        if(row != null && col==13 ) {
          //  for(int c = 0; c < cols; c++) {
                cell  = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                cell8 = row.getCell((short)8);
                cell9 = row.getCell((short)9);
                cell10 = row.getCell((short)10);
                cell11 = row.getCell((short)11);
                cell12 = row.getCell((short)12);
                String id=UUID.randomUUID().toString();
                if(cell != null ||cell1 != null ||cell2 != null ||cell3 != null ||cell4 != null ||cell5 != null ||cell6 != null ||cell7 != null ||cell8 != null ||cell9 != null ||cell10 != null ||cell11 != null ||cell12 != null) {
                    
                  //  if(c)
                    //String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null;
                    
                    if(cell==null)
                        c0="";
                    else
                        c0=cell.toString();
                    if(cell1==null)
                        c1="";
                    else
                        c1=cell1.toString();
                    if(cell2==null)
                        c2="";
                    else
                        c2=cell2.toString();
                    if(cell3==null)
                        c3="";
                    else
                        c3=cell3.toString();
                    if(cell4==null)
                        c4="";
                    else
                        c4=cell4.toString();
                    if(cell5==null)
                        c5="";
                    else
                        c5=cell5.toString();
                    if(cell6==null)
                        c6="";
                    else
                        c6=cell6.toString();
                    if(cell7!=null)
                        date=cell7.getDateCellValue();
                    
                    if(cell8==null)
                        c8="";
                    else
                        c8=cell8.toString();
                    if(cell9==null)
                        c9="";
                    else
                        c9=ConvertDoubleToString(cell9.getNumericCellValue());
                    if(cell10==null)
                        c10="";
                    else
                        c10=cell10.toString();
                    if(cell11==null)
                        c11="";
                    else
                        c11=cell11.toString();
                    if(cell12==null)
                        c12="";
                    else
                        c12=cell12.toString();
                    String temp="null",temp1="null";
                    
                    List<Object> cat_list = new ArrayList<Object>();
                                    cat_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> lang_list1 = new ArrayList<Object>();
                                    lang_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                

                                    try{
                        if(cell1!=null){
                            for(int i=0; i<cat_list.size(); i++)
                            {
                               String tempo= cat_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c1))
                                {
                                     List<Object> catg_list1 = new ArrayList<Object>();
                                    catg_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)catg_list1.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(cell3!=null){
                            for(int i=0; i<lang_list1.size(); i++)
                            {
                               String tempo1= lang_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c3))
                                {
                                     List<Object> lang_list2 = new ArrayList<Object>();
                                    lang_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)lang_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        
                    }catch(Exception e){
                    }
                                    
                       
                      Object[] value=new Object[]{id,c0,temp,c2,temp1,c4,c5,c6,date,c8,c9,c10,c11,c12,m_App.getAppUserView().getUser().getName(),d1,m_App.getProperties().getHost(),true};
                   new PreparedSentence(s
                  , "INSERT INTO lib_author (ID,NAME,Categories,WRITEUP,language,CITY,REGION,COUNTRY,DOB,EMAIL,PHONE,ADDRESS,NOTES,PHOTO,CREATEDBY,CRDATE,CREATEDHOST,ACTIVE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.BOOLEAN})
                  ).exec(value);
                    cnt++;
                
                }
          
        }
    }
    JOptionPane.showMessageDialog(comp, " "+cnt+" number of rows inserted", null, JOptionPane.OK_OPTION);
    list3.clear();
    }else{
                        JOptionPane.showMessageDialog(comp, "Data not inserted.. Please correct ERRORS and submit again", "Error", JOptionPane.OK_OPTION);
             }      
         }catch(Exception ioe) {
    ioe.printStackTrace();
}
         
     }
      public void writeToAuthorXl(ArrayList nm,ArrayList lng,ArrayList cat,String filename) throws IOException{
        FileOutputStream fos = null;
        ArrayList error = new ArrayList();
         //double c = 0,cx1 = 0;
         try {
             fos=new FileOutputStream(new File(filename+"/errorAuthor.xls"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lib_DetailsFeatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("Author");
            HSSFRow row;
            HSSFRow r;
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
            HSSFCell cell11;
            HSSFCell cell12;
            HSSFCell cell13;
           
             int rcount=list3.size();
                for(int i=0;i<rcount;i++){
                    r=(HSSFRow)list3.get(i);
                row = spreadSheet.createRow((short) i);
                cell = row.createCell((short)0);
                cell1 = row.createCell((short)1);
                cell2 = row.createCell((short)2);
                cell3 = row.createCell((short)3);
                cell4 = row.createCell((short)4);
                cell5 = row.createCell((short)5);
                cell6 = row.createCell((short)6);
                cell7 = row.createCell((short)7);
                cell8 = row.createCell((short)8);
                cell9 = row.createCell((short)9);
                cell10 = row.createCell((short)10);
                cell11 = row.createCell((short)11);
                cell12 = row.createCell((short)12);
                cell13 = row.createCell((short)13);
               
                String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null,c7=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null ,c13=null;
                    if(r.getCell((short)0)==null)
                        c0="";
                    else
                        c0=r.getCell((short)0).toString();
                    if(r.getCell((short)1)==null)
                        c1="";
                    else
                        c1=r.getCell((short)1).toString();
                    if(r.getCell((short)2)==null)
                        c2="";
                    else
                        c2=r.getCell((short)2).toString();
                    if(r.getCell((short)3)==null)
                        c3="";
                    else
                         c3=r.getCell((short)3).toString();
                    if(r.getCell((short)4)==null)
                        c4="";
                    else
                        c4=r.getCell((short)4).toString();
                    if(r.getCell((short)5)==null)
                        c5="";
                    else
                        c5=r.getCell((short)5).toString();
                    if(r.getCell((short)6)==null)
                        c6="";
                    else
                        c6=r.getCell((short)6).toString();
                    if(r.getCell((short)7)==null)
                        c7="";
                    else
                        c7=r.getCell((short)7).toString();
                        //date=r.getCell((short)7).getDateCellValue();
                    if(r.getCell((short)8)==null)
                        c8="";
                    else
                        c8=r.getCell((short)8).toString();
                    if(r.getCell((short)9)==null)
                        c9="";
                    else
                        c9=r.getCell((short)9).toString();
                    if(r.getCell((short)10)==null)
                        c10="";
                    else
                        c10=r.getCell((short)10).toString();
                    if(r.getCell((short)11)==null)
                        c11="";
                    else
                        c11=r.getCell((short)11).toString();
                    if(r.getCell((short)12)==null)
                        c12="";
                    else
                        c12=r.getCell((short)12).toString();
                    
               cell.setCellValue(c0);
               cell1.setCellValue(c1);
               cell2.setCellValue(c2);
               if(i>0){
                   
                  if(c9.equals("")){
                     
                    cell9.setCellValue(c9); 
                  }else{
                      c9=ConvertDoubleToString(r.getCell((short)9).getNumericCellValue());
                  long cc9=Long.parseLong(c9);
                      cell9.setCellValue(cc9);
                  }
                  if(c7.equals("")){
                     
                    cell7.setCellValue(c7); 
                  }else{
              
             Date dat = r.getCell((short)7).getDateCellValue();
             //String str =(String)dat.toString();
             SimpleDateFormat parseFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
             Date dt = null;
                      try {
                          dt = parseFormat.parse(dat.toString());
                      } catch (ParseException ex) {
                          Logger.getLogger(Lib_DetailsFeatcher.class.getName()).log(Level.SEVERE, null, ex);
                      }
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
             String result = format.format(dt);
                      cell7.setCellValue(result);
                  }
                  
                  for(int k=0;k<nm.size();k++){
                    if(c0.equalsIgnoreCase(nm.get(k).toString())){
                       error.add("name exist");
                    }
                }for(int k=0;k<lng.size();k++){
                    if(c3.equalsIgnoreCase(lng.get(k).toString())){
                       error.add("language");
                    }
                }for(int k=0;k<cat.size();k++){
                    if(c1.equalsIgnoreCase(cat.get(k).toString())){
                      error.add("category");
                    }
                }
                cell13.setCellValue(error.toString());
                
               }else{
               cell7.setCellValue(c7);    
               //cell11.setCellValue(c11);
               cell9.setCellValue(c9);
               cell13.setCellValue("ErrorStatus");
               
               }
               cell3.setCellValue(c3);
               cell4.setCellValue(c4);
               cell5.setCellValue(c5);
               cell6.setCellValue(c6);
               //cell7.setCellValue(c7); 
               //cell9.setCellValue(c9);
               cell8.setCellValue(c8);
               cell10.setCellValue(c10);
               cell11.setCellValue(c11);
               cell12.setCellValue(c12);
               //cell13.setCellValue(c13);
               error.clear();
               
               
            }
            workBook.write(fos);
            //list3.clear();
     }
      
       public  void booksXL(String filename,AppView m_App,DataLogicFacilities dlfac,Component comp){
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
             HSSFCell cell11;
             HSSFCell cell12;
             HSSFCell cell13;
             HSSFCell cell14;
             
             Session s=LookupUtilityImpl.getInstance(null).getAppView().getSession(); 
             int rows; // No of rows
    rows = sheet.getPhysicalNumberOfRows();

    int cols = 0; // No of columns
    int tmp = 0;
    int cnt=0;
     String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null,c7=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null ,c13=null,c14=null;
    list3.add(sheet.getRow(0));
    ArrayList name1 = new ArrayList();
    ArrayList lang1 = new ArrayList();
    ArrayList vend_do1 = new ArrayList();
    ArrayList catg1 = new ArrayList();
    ArrayList issue1 = new ArrayList();
    ArrayList autr1 = new ArrayList();
    ArrayList pub1 = new ArrayList();
    ArrayList media1 = new ArrayList();
    //ArrayList autr = new ArrayList();

    // This trick ensures that we get the data properly even if it doesn't start from first few rows
    for(int i = 0; i < 10 || i < rows; i++) {
        row = sheet.getRow(i);
        if(row != null) {
            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
            if(tmp > cols) cols = tmp;
        }
    }
    Date d1=new Date();
    if(list3.size()==1){
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();
    if(row != null && col==15){
        cell6 = row.getCell((short)6);
        cell4 = row.getCell((short)4);
        cell2 = row.getCell((short)2);
        cell3 = row.getCell((short)3);
        cell10 = row.getCell((short)10);
        cell9 = row.getCell((short)9); 
        cell13 = row.getCell((short)13);
        cell14 = row.getCell((short)14);
        
        if(cell6==null)
             c6="";
        else
             c6=cell6.toString();
        if(cell4==null)
             c4="";
        else
             c4=cell4.toString();
        if(cell2==null)
             c2="";
        else
             c2=cell2.toString();
        if(cell3==null)
             c3="";
        else
             c3=cell3.toString();
        
        if(cell9==null)
            c9="";
        else
            c9=cell9.toString();
        if(cell10==null)
            c10="";
        else
            c10=cell10.toString();
        if(cell13==null)
            c13="";
        else
            c13=cell13.toString();
        if(cell14==null)
            c14="";
        else
            c14=ConvertDoubleToString(cell14.getNumericCellValue());
        //c8=cell8.toString();
        //c9=cell9.toString();
        c0=row.getCell((short)0).toString();
        String temp="null",temp1="null",temp2="null",temp3="null",temp4="null",temp5="null",temp6="null";
                    
                     List<Object> cat_list = new ArrayList<Object>();
                                    cat_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> lang_list1 = new ArrayList<Object>();
                                    lang_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                                    
                     List<Object> aut_list = new ArrayList<Object>();
                                    aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_author c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> pub_list = new ArrayList<Object>();
                                    pub_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_publisher c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                    
                    List<Object> med_list1 = new ArrayList<Object>();
                                    med_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_media m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                                    
                     List<Object> issu_list = new ArrayList<Object>();
                                    issu_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.name FROM lib_issuerules i WHERE i.active=1 ORDER by i.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> vend_list = new ArrayList<Object>();
                                    vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_vendor m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                                
                        if(cell6!=null){
                             for(int i=0; i<cat_list.size(); i++)
                            {
                                  String tempo= cat_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c6))
                                {
                                     List<Object> catg_list1 = new ArrayList<Object>();
                                    catg_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)catg_list1.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(temp.equals("null")){
                            catg1.add(c6);
                            if(!list3.contains(row))
                               list3.add(row);
                           }
                        if(cell4!=null){
                            for(int i=0; i<lang_list1.size(); i++)
                            {
                               String tempo1= lang_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c4))
                                {
                                     List<Object> lang_list2 = new ArrayList<Object>();
                                    lang_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)lang_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }if(temp1.equals("null")){
                            lang1.add(c4);
                            if(!list3.contains(row))
                               list3.add(row);
                           }
                        
                        
                        if(cell2!=null){
                            for(int i=0; i<aut_list.size(); i++)
                            {
                               String tempo= aut_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c2))
                                {
                                     List<Object> at_list1 = new ArrayList<Object>();
                                    at_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT a.id FROM lib_author a  WHERE a.name=? and a.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp2 =(String)at_list1.get(0);
                                    break;
                                }
                           }
                 }
                        if(temp2.equals("null")){
                            autr1.add(c2);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                        if(cell3!=null){
                            for(int i=0; i<pub_list.size(); i++)
                            {
                               String tempo1= pub_list.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c3))
                                {
                                     List<Object> pub_list2 = new ArrayList<Object>();
                                    pub_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_publisher c  WHERE c.name=? and c.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp3 =(String)pub_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(temp3.equals("null")){
                            pub1.add(c3);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                        if(cell9!=null){
                            for(int i=0; i<med_list1.size(); i++)
                            {
                               String tempo1= med_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c9))
                                {
                                    List<Object> med_list2 = new ArrayList<Object>();
                                    med_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_media i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp4 =(String)med_list2.get(0);
                                    break;
                                }
                           }
                 }
                        
                   if(temp4.equals("null")){
                            media1.add(c9);
                            if(!list3.contains(row))
                               list3.add(row);
                            }     
                        
                        if(cell10!=null){
                            for(int i=0; i<issu_list.size(); i++)
                            {
                               String tempo= issu_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c10))
                                {
                                    List<Object> issu_list1 = new ArrayList<Object>();
                                    issu_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_issuerules i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp5 =(String)issu_list1.get(0);
                                    break;
                                }
                           }
                 }
                        if(temp5.equals("null")){
                            issue1.add(c10);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                        if(cell13!=null){
                            for(int i=0; i<vend_list.size(); i++)
                            {
                               String tempo1= vend_list.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c13))
                                {
                                     List<Object> vend_list2 = new ArrayList<Object>();
                                    vend_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_vendor i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp6 =(String)vend_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(temp6.equals("null")){
                            vend_do1.add(c13);
                            if(!list3.contains(row))
                               list3.add(row);
                            }
                        if(cell14!=null){
                            //int cc14=Integer.parseInt(c14);
                           if(!c14.equals("1") && !c14.equals("0")){
                               if(!list3.contains(row))
                               list3.add(row);
                           }
                            
                        }
                 
                        int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_bookmaster WHERE REFNO=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c0).toString());              
                       if(count!=0) {
                           //JOptionPane.showMessageDialog(comp, "Vendor with the name "+c4+" already exist", null, JOptionPane.OK_OPTION);
                          if(!list3.contains(row))
                            list3.add(row);  
                            name1.add(c0);
                       }
        }else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper Book type XL Sheet.pls check..", null, JOptionPane.OK_OPTION);
                        break;            
            }
    }if(list3.size()>1){
        
        JOptionPane.showMessageDialog(comp, " "+(list3.size()-1)+" number of Rows ERROR out of "+(rows-1)+" rows", "Error", JOptionPane.INFORMATION_MESSAGE);   
     String flname = new Lib_DetailsFetcherPanel().errorFilePath();
     writeToBookXl(name1,lang1,vend_do1,pub1,catg1,media1,issue1,autr1,flname);
        
        JOptionPane.showMessageDialog(comp, "Error Report is saved under "+flname+" Directory...pls check.. ", "Error", JOptionPane.INFORMATION_MESSAGE);
     
    }
    }
    if(list3.size()==1){
        int cc14 = 0;
    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        int col=sheet.getRow(0).getLastCellNum();
        //here 14 is the cell size.it changes when the nor of cell(n) changes 
        if(row != null && col==15 ) {
          //  for(int c = 0; c < cols; c++) {
                cell  = row.getCell((short)0);
                cell1 = row.getCell((short)1);
                cell2 = row.getCell((short)2);
                cell3 = row.getCell((short)3);
                cell4 = row.getCell((short)4);
                cell5 = row.getCell((short)5);
                cell6 = row.getCell((short)6);
                cell7 = row.getCell((short)7);
                cell8 = row.getCell((short)8);
                cell9 = row.getCell((short)9);
                cell10 = row.getCell((short)10);
                cell11 = row.getCell((short)11);
                cell12 = row.getCell((short)12);
                cell13 = row.getCell((short)13);
               cell14 = row.getCell((short)14);
                
                String id=UUID.randomUUID().toString();
                if(cell != null ||cell1 != null ||cell2 != null ||cell3 != null ||cell4 != null ||cell5 != null ||cell6 != null ||cell7 != null ||cell8 != null ||cell9 != null ||cell10 != null ||cell11 != null ||cell12 != null||cell13 != null ) {
                    
                  
                    //String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null,c7=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null ,c13=null;
                    
                    if(cell==null)
                        c0="";
                    else
                        c0=cell.toString();
                    if(cell1==null)
                        c1="";
                    else
                        c1=cell1.toString();
                    if(cell2==null)
                        c2="";
                    else
                        c2=cell2.toString();
                    if(cell3==null)
                        c3="";
                    else
                        c3=cell3.toString();
                    if(cell4==null)
                        c4="";
                    else
                        c4=cell4.toString();
                    if(cell5==null)
                        c5="";
                    else
                        c5=cell5.toString();
                    if(cell6==null)
                        c6="";
                    else
                        c6=cell6.toString();
                    if(cell7==null)
                        c7="";
                    else
                        c7=ConvertDoubleToString(cell7.getNumericCellValue());
                    if(cell8==null)
                        c8="";
                    else
                        c8=cell8.toString();
                    if(cell9==null)
                        c9="";
                    else
                        c9=cell9.toString();
                    if(cell10==null)
                        c10="";
                    else
                        c10=cell10.toString();
                    if(cell11==null)
                        c11="";
                    else
                        c11=ConvertDoubleToString(cell11.getNumericCellValue());
                    if(cell12==null)
                        c12="";
                    else
                        c12=ConvertDoubleToString(cell12.getNumericCellValue());
                    if(cell13==null)
                        c13="";
                    else
                        c13=cell13.toString();
                    if(cell14==null)
                        c14="";
                    else
                        c14=ConvertDoubleToString(cell14.getNumericCellValue());
                    String temp="null",temp1="null",temp2="null",temp3="null",temp4="null",temp5="null",temp6="null";
                    
                    List<Object> cat_list = new ArrayList<Object>();
                                    cat_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_categories c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> lang_list1 = new ArrayList<Object>();
                                    lang_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.name FROM lib_language l WHERE l.active=1 ORDER by l.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                                    
                     List<Object> aut_list = new ArrayList<Object>();
                                    aut_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_author c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> pub_list = new ArrayList<Object>();
                                    pub_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.name FROM lib_publisher c WHERE c.active=1 ORDER by c.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                    
                    List<Object> med_list1 = new ArrayList<Object>();
                                    med_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_media m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                                    
                     List<Object> issu_list = new ArrayList<Object>();
                                    issu_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.name FROM lib_issuerules i WHERE i.active=1 ORDER by i.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
                    
                    List<Object> vend_list = new ArrayList<Object>();
                                    vend_list  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT m.name FROM lib_vendor m WHERE m.active=1 ORDER by m.name",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();                
                
                                    try{
                        if(cell6!=null){
                             for(int i=0; i<cat_list.size(); i++)
                            {
                                  String tempo= cat_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c6))
                                {
                                     List<Object> catg_list1 = new ArrayList<Object>();
                                    catg_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT id FROM lib_categories  WHERE name=? and ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp =(String)catg_list1.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        
                        if(cell4!=null){
                            for(int i=0; i<lang_list1.size(); i++)
                            {
                               String tempo1= lang_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c4))
                                {
                                     List<Object> lang_list2 = new ArrayList<Object>();
                                    lang_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT l.id FROM lib_language l  WHERE l.name=? and l.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp1 =(String)lang_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        
                        
                        if(cell2!=null){
                            for(int i=0; i<aut_list.size(); i++)
                            {
                               String tempo= aut_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c2))
                                {
                                     List<Object> at_list1 = new ArrayList<Object>();
                                    at_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT a.id FROM lib_author a  WHERE a.name=? and a.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp2 =(String)at_list1.get(0);
                                    break;
                                }
                           }
                 }
                        if(cell3!=null){
                            for(int i=0; i<pub_list.size(); i++)
                            {
                               String tempo1= pub_list.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c3))
                                {
                                     List<Object> pub_list2 = new ArrayList<Object>();
                                    pub_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT c.id FROM lib_publisher c  WHERE c.name=? and c.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp3 =(String)pub_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(cell9!=null){
                            for(int i=0; i<med_list1.size(); i++)
                            {
                               String tempo1= med_list1.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c9))
                                {
                                    List<Object> med_list2 = new ArrayList<Object>();
                                    med_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_media i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp4 =(String)med_list2.get(0);
                                    break;
                                }
                           }
                 }
                        
                        
                        if(cell10!=null){
                            for(int i=0; i<issu_list.size(); i++)
                            {
                               String tempo= issu_list.get(i).toString();
                            
                              if(tempo.equalsIgnoreCase(c10))
                                {
                                    List<Object> issu_list1 = new ArrayList<Object>();
                                    issu_list1  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_issuerules i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo);
                                    temp5 =(String)issu_list1.get(0);
                                    break;
                                }
                           }
                 }
                        if(cell13!=null){
                            for(int i=0; i<vend_list.size(); i++)
                            {
                               String tempo1= vend_list.get(i).toString();
                            
                              if(tempo1.equalsIgnoreCase(c13))
                                {
                                     List<Object> vend_list2 = new ArrayList<Object>();
                                    vend_list2  = (List<Object>) new StaticSentence(m_App.getSession(), "SELECT i.id FROM lib_vendor i  WHERE i.name=? and i.ACTIVE=1",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list(tempo1);
                                    temp6 =(String)vend_list2.get(0);
                                  
                                    break;
                                }
                           }
                 }
                        if(c14!=null){
                             cc14=Integer.parseInt(c14);
                        }
                        
                    }catch(Exception e){
                    }
                       
                      /*int count=Integer.valueOf(new StaticSentence(m_App.getSession()
                            , "SELECT COUNT(*) FROM lib_bookmaster WHERE NAME=? AND ACTIVE=TRUE"
                            ,SerializerWriteString.INSTANCE
                            ,SerializerReadInteger.INSTANCE).find(c1).toString());              
                       if(count==0) {  */          
                      Object[] value=new Object[]{id,c0,c1,temp2,temp3,temp1,c5,temp,c7,c8,temp4,temp5,c11,c12,true,m_App.getAppUserView().getUser().getName(),d1,m_App.getProperties().getHost(),temp6,cc14};
                   new PreparedSentence(s
                  , "INSERT INTO lib_bookmaster (ID,RefNo,NAME,Author,Publisher,language,sms,Category,Edition,Keywords,Media,IssueRules,AllocatedNor,copies,ACTIVE,CREATEDBY,CREATEDATE,CREATEDHOST,Vend_doid,available_flag) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                  , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.STRING,Datas.TIMESTAMP,Datas.STRING,Datas.STRING,Datas.INT})
                  ).exec(value);
                   cnt++;
                    //JOptionPane.showMessageDialog(comp, "Created Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                /*}else{
                        JOptionPane.showMessageDialog(comp, "Book with the name "+c1+" already exist", null, JOptionPane.OK_OPTION);
                    }*/
                       }
                
         
        }/*else{
                        JOptionPane.showMessageDialog(comp, "This is not a proper book type XL.pls check..", null, JOptionPane.OK_OPTION);
        break;            
        }*/
    }
    JOptionPane.showMessageDialog(comp, " "+cnt+" number of rows inserted", null, JOptionPane.OK_OPTION);
    list3.clear();
    }
    else{
                        JOptionPane.showMessageDialog(comp, "Data not inserted.. Please correct ERRORS and submit again", "Error", JOptionPane.OK_OPTION);
             }
             
         }catch(Exception ioe) {
    ioe.printStackTrace();
}
         
     }
       
       public void writeToBookXl(ArrayList nm,ArrayList lng,ArrayList vend,ArrayList pub,ArrayList cat,ArrayList med,ArrayList issuerls,ArrayList aut,String filename) throws IOException{
        FileOutputStream fos = null;
        ArrayList error = new ArrayList();
         //double c = 0,cx1 = 0;
         try {
             fos=new FileOutputStream(new File(filename+"/errorBook.xls"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lib_DetailsFeatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("book");
            HSSFRow row;
            HSSFRow r;
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
            HSSFCell cell11;
            HSSFCell cell12;
            HSSFCell cell13;
            HSSFCell cell14;
            HSSFCell cell15;
             int rcount=list3.size();
                for(int i=0;i<rcount;i++){
                    r=(HSSFRow)list3.get(i);
                row = spreadSheet.createRow((short) i);
                cell = row.createCell((short)0);
                cell1 = row.createCell((short)1);
                cell2 = row.createCell((short)2);
                cell3 = row.createCell((short)3);
                cell4 = row.createCell((short)4);
                cell5 = row.createCell((short)5);
                cell6 = row.createCell((short)6);
                cell7 = row.createCell((short)7);
                cell8 = row.createCell((short)8);
                cell9 = row.createCell((short)9);
                cell10 = row.createCell((short)10);
                cell11 = row.createCell((short)11);
                cell12 = row.createCell((short)12);
                cell13 = row.createCell((short)13);
                cell14 = row.createCell((short)14);
                cell15 = row.createCell((short)15);
                String c1=null, c2=null,c3=null, c4=null, c5=null, c6=null,c7=null, c8=null, c9=null, c0=null,c10=null, c11=null, c12=null ,c13=null,c14=null;
                    if(r.getCell((short)0)==null)
                        c0="";
                    else
                        c0=r.getCell((short)0).toString();
                    if(r.getCell((short)1)==null)
                        c1="";
                    else
                        c1=r.getCell((short)1).toString();
                    if(r.getCell((short)2)==null)
                        c2="";
                    else
                        c2=r.getCell((short)2).toString();
                    if(r.getCell((short)3)==null)
                        c3="";
                    else
                         c3=r.getCell((short)3).toString();
                    if(r.getCell((short)4)==null)
                        c4="";
                    else
                        c4=r.getCell((short)4).toString();
                    if(r.getCell((short)5)==null)
                        c5="";
                    else
                        c5=r.getCell((short)5).toString();
                    if(r.getCell((short)6)==null)
                        c6="";
                    else
                        c6=r.getCell((short)6).toString();
                    if(r.getCell((short)7)==null)
                        c7="";
                    else
                        c7=r.getCell((short)7).toString();
                    if(r.getCell((short)8)==null)
                        c8="";
                    else
                        c8=r.getCell((short)8).toString();
                    if(r.getCell((short)9)==null)
                        c9="";
                    else
                        c9=r.getCell((short)9).toString();
                    if(r.getCell((short)10)==null)
                        c10="";
                    else
                        c10=r.getCell((short)10).toString();
                    if(r.getCell((short)11)==null)
                        c11="";
                    else
                        c11=r.getCell((short)11).toString();
                    if(r.getCell((short)12)==null)
                        c12="";
                    else
                        c12=r.getCell((short)12).toString();
                    if(r.getCell((short)13)==null)
                        c13="";
                    else
                        c13=r.getCell((short)13).toString();
                    if(r.getCell((short)14)==null)
                        c14="";
                    else
                        c14=r.getCell((short)14).toString();
                    
                    
               cell.setCellValue(c0);
               cell1.setCellValue(c1);
               cell2.setCellValue(c2);
               if(i>0){
                   if(c11.equals("")){
                      
                    cell11.setCellValue(c11);
                   }else{
                       c11=ConvertDoubleToString(r.getCell((short)11).getNumericCellValue());
                  int cc11=Integer.parseInt(c11);
                       cell11.setCellValue(cc11);
                   }
                  if(c7.equals("")){
                     
                    cell7.setCellValue(c7); 
                  }else{
                      c7=ConvertDoubleToString(r.getCell((short)7).getNumericCellValue());
                  long cc7=Long.parseLong(c7);
                      cell7.setCellValue(cc7);
                  }
                  if(c12.equals("")){
                     
                    cell12.setCellValue(c12); 
                  }else{
                      c12=ConvertDoubleToString(r.getCell((short)12).getNumericCellValue());
                  long cc12=Long.parseLong(c12);
                      cell12.setCellValue(cc12);
                  }
                  if(c14.equals("")){
                     
                    cell14.setCellValue(c14); 
                  }else{
                      c14=ConvertDoubleToString(r.getCell((short)14).getNumericCellValue());
                  long cc14=Long.parseLong(c14);
                      cell14.setCellValue(cc14);
                      if(cc14>1||cc14<0){
                          error.add("available_flag");
                      }
                  }
                  for(int k=0;k<nm.size();k++){
                    if(c0.equalsIgnoreCase(nm.get(k).toString())){
                       //cell14.setCellValue("RefNo exist");
                        error.add("RefNo exist");
                    }
                }for(int k=0;k<lng.size();k++){
                    if(c4.equalsIgnoreCase(lng.get(k).toString())){
                       //cell14.setCellValue("language");
                        error.add("language");
                    }
                }for(int k=0;k<vend.size();k++){
                    if(c13.equalsIgnoreCase(vend.get(k).toString())){
                       //cell14.setCellValue("vendor");
                        error.add("vendor");
                    }
                }for(int k=0;k<aut.size();k++){
                    if(c2.equalsIgnoreCase(aut.get(k).toString())){
                       //cell14.setCellValue("author");
                        error.add("author");
                    }
                }for(int k=0;k<pub.size();k++){
                    if(c3.equalsIgnoreCase(pub.get(k).toString())){
                      // cell14.setCellValue("publisher");
                        error.add("publisher");
                    }
                }for(int k=0;k<cat.size();k++){
                    if(c6.equalsIgnoreCase(cat.get(k).toString())){
                      // cell14.setCellValue("category");
                        error.add("category");
                    }
                }for(int k=0;k<med.size();k++){
                    if(c9.equalsIgnoreCase(med.get(k).toString())){
                       //cell14.setCellValue("media");
                        error.add("media");
                    }
                }for(int k=0;k<issuerls.size();k++){
                    if(c10.equalsIgnoreCase(issuerls.get(k).toString())){
                       //cell14.setCellValue("issuerules");
                        error.add("issuerules");
                    }
                }
                
            cell15.setCellValue(error.toString());
                //} 
               }else{
               cell7.setCellValue(c7);    
               cell11.setCellValue(c11);
               cell12.setCellValue(c12);
               cell14.setCellValue(c14);
               cell15.setCellValue("ErrorStatus");
               
               }
               cell3.setCellValue(c3);
               cell4.setCellValue(c4);
               cell5.setCellValue(c5);
               cell6.setCellValue(c6);
               cell9.setCellValue(c9);
               cell8.setCellValue(c8);
               cell10.setCellValue(c10);
               //cell11.setCellValue(c11);
               //cell12.setCellValue(c12);
               cell13.setCellValue(c13);
               error.clear();
               
               
            }
            workBook.write(fos);
            //list3.clear();
     }
    
}

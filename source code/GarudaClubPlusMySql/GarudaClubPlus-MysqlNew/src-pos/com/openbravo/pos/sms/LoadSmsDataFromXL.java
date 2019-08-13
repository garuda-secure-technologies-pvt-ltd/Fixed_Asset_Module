/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.format.Formats;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author swathi
 */
public class LoadSmsDataFromXL {
    private List<String> headernam=new ArrayList();
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow hrow;
    private int hrownum;
    //private ComboBoxValModel nmodel;
    //private ComboBoxValModel mmodel;
    public void LoadFile(String filename) throws IOException{
       fs = new POIFSFileSystem(new FileInputStream(filename));
       wb = new HSSFWorkbook(fs);
       sheet = wb.getSheetAt(0);
    }
    public List<String> getHeaderList(){
       int noofrows=sheet.getPhysicalNumberOfRows();
      // HSSFRow row=null;
       int cellnum=0;
       int j=0;
       while(j<noofrows){
           hrow=sheet.getRow(j);
           if(hrow!=null){
           cellnum=hrow.getPhysicalNumberOfCells();
            if(cellnum>0){
              hrownum=j;
              break;
            }
           }else{
               j++;
               noofrows++;
            }
       }
       if(hrow!=null){
         for(int i=0;i<cellnum;i++){
          if(hrow.getCell((short)i)!=null)
             headernam.add(hrow.getCell((short)i).getRichStringCellValue().getString());
          else{
             cellnum++;
          }
         }
       }
       return headernam;
       //nmodel=new ComboBoxValModel(headernam);
       //mmodel=new ComboBoxValModel(headernam);

    }
    public String LoadData(String ncol,String mcol){
        int ncolnum=-1;
        int mcolnum=-1;
        String data=null;
        if(hrow!=null){
             int noofrows=sheet.getPhysicalNumberOfRows();

             int noofcells=hrow.getLastCellNum();

             for(int i=0;i<noofcells;i++){
                 String del=hrow.getCell((short)i).getRichStringCellValue().getString();
               if(hrow.getCell((short)i)!=null){
                 if(ncol!=null){
                  if(hrow.getCell((short)i).getRichStringCellValue().getString().equals(ncol)){
                      ncolnum=i;
                  }
                 }if(hrow.getCell((short)i).getRichStringCellValue().getString().equals(mcol)){
                      mcolnum=i;
                  }
                  if(ncolnum!=-1 && mcolnum!=-1){
                      break;
                  }
               }
             }
             if(ncol!=null || mcol!=null){
              for(int i=hrownum+1;i<noofrows;i++){
                 HSSFRow row=sheet.getRow(i);
                 if(row!=null){
                      double val=row.getCell((short)mcolnum).getNumericCellValue();
                    // Long.parseLong(data)
                    // NumberFormat f=NumberFormat.getInstance();
                     DecimalFormat twoDForm = new DecimalFormat("#0");
		             String del=twoDForm.format(val);
                 if(ncolnum==-1){

                    
                     //String del=String.valueOf(Long.parseLong(del));
                     //Double.
                  if(row.getCell((short)mcolnum)!=null){
                  if(data==null)
                      data=del;
                  else
                      data+=del;
                  }
                 }else{
                   if(row.getCell((short)ncolnum)!=null){
                     if(data==null)
                      data=row.getCell((short)ncolnum).toString()+" # "+del;
                     else
                      data+=" : "+row.getCell((short)ncolnum).toString()+" # "+del;
                   }
                }
              }
              }
             }
        }
        return data;
    }

}

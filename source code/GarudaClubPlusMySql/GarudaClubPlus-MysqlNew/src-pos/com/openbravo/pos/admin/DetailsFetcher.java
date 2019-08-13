/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.admin;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.Session;
//import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfoExt2;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.io.FileInputStream;
//import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author swathi
 */
public class DetailsFetcher {
    public  void accessConnector(String filename){
        List<CustomerInfoExt2> cusinfo=new ArrayList<CustomerInfoExt2>();
 try {
    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    int count=wb.getNumberOfSheets();
    for(int j=0;j<count;j++){
    HSSFSheet sheet = wb.getSheetAt(j);
    HSSFRow row;

    HSSFCell cell;
    HSSFCell cell1;
    HSSFCell cell2;
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

    for(int r = 1; r < rows; r++) {
        row = sheet.getRow(r);
        if(row != null) {
          //  for(int c = 0; c < cols; c++) {
                cell = row.getCell((short)0);
                cell1 = row.getCell((short)1);
               // cell2=new HSSFCell();
               // cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2 = row.getCell((short)2);
                String id=UUID.randomUUID().toString();
                int flag=0;
                if((cell != null || cell1!=null || cell2!=null) ) {
                    String c1=null,c2=null,c3=null;
                    if(cell==null)
                        c1="";
                    else
                        c1=cell.toString();
                    if(cell1==null)
                        c2="";
                    else
                        c2=cell1.toString();
                    if(cell2==null)
                        c3="";
                    else{
                        try{
                        c3=ConvertDoubleToString(cell2.getNumericCellValue());
                        }catch(Exception e){
                          flag=1;
                        }
                        
                    }
                    if(c3!=null && !c3.equals("") && flag==0){
                        try{
                    new PreparedSentence(s,
                            "INSERT INTO TEMP(ID, MEMNO, NAME, MOBILE) VALUES (?, ?, ?, ?)",
                            new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING,Datas.STRING,Datas.STRING}))
                        .exec(new Object[] {id,c1,c2,c3});
                        }catch(Exception exa){
                        }
                    }
                  
                }
          //  }
        }
    }
    }
} catch(Exception ioe) {
    ioe.printStackTrace();
}

    }

public static String ConvertDoubleToString(double value){
        DecimalFormat twoDForm = new DecimalFormat("##.#");
		return twoDForm.format(value);
    }

}

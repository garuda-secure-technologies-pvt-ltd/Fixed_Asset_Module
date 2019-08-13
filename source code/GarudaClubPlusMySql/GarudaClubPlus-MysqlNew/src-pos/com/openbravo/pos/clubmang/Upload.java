package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import com.openbravo.pos.clubmang.appendfile;
public class Upload {

    private Object FileUtils;
    private int exec;
    public String sysDate = null;
    //String[] names = new String[50]
    // String Array[] arr=new Array[];
    ArrayList al = new ArrayList();
    // ArrayList<String> al=new ArrayList<String>();
    //private Session mapp;
    // private AppView mapp;
    public boolean load(AppView mapp, String filename, ArrayList al) {

        // List<CustomerInfoExt2> cusinfo=new ArrayList<CustomerInfoExt2>();

        boolean result = false;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            int count = wb.getNumberOfSheets();
             ArrayList a1 = new ArrayList();
            for (int j = 0; j < count; j++) {
                int i;
                HSSFSheet sheet = wb.getSheetAt(j);
                HSSFRow row = null;

                HSSFCell cell;
                //HSSFCell cell1;
                //HSSFCell cell2;

                int rows; // No of rows
                rows = sheet.getPhysicalNumberOfRows();

                if (rows != 0) {
                    int cols = 0; // No of columns
                    int tmp = 0;
                    // for( i=0;i<=rows;i++)
                    // {
                    int nfcount = sheet.getRow(0).getPhysicalNumberOfCells();
                    int ffcount = (Integer) al.get(0);


                    String memsekey = sheet.getRow(0).getCell((short) 0).getRichStringCellValue().getString();
                    String memname = sheet.getRow(0).getCell((short) 1).getRichStringCellValue().getString();
                    String sowo = sheet.getRow(0).getCell((short) 2).getRichStringCellValue().getString();
                    String memfn = sheet.getRow(0).getCell((short) 3).getRichStringCellValue().getString();
                    String memlstn = sheet.getRow(0).getCell((short) 4).getRichStringCellValue().getString();
                    String mememail = sheet.getRow(0).getCell((short) 5).getRichStringCellValue().getString();
                    String phn = sheet.getRow(0).getCell((short) 6).getRichStringCellValue().getString();
                    String phn2 = sheet.getRow(0).getCell((short) 7).getRichStringCellValue().getString();
                    String fax = sheet.getRow(0).getCell((short) 8).getRichStringCellValue().getString();
                    String mob = sheet.getRow(0).getCell((short) 9).getRichStringCellValue().getString();
                    String add = sheet.getRow(0).getCell((short) 10).getRichStringCellValue().getString();
                    String add2 = sheet.getRow(0).getCell((short) 11).getRichStringCellValue().getString();
                    String postl = sheet.getRow(0).getCell((short) 12).getRichStringCellValue().getString();
                    String city = sheet.getRow(0).getCell((short) 13).getRichStringCellValue().getString();
                    String region = sheet.getRow(0).getCell((short) 14).getRichStringCellValue().getString();
                    String country = sheet.getRow(0).getCell((short) 15).getRichStringCellValue().getString();





                    String mse = (String) al.get(1);
                    String mmn = (String) al.get(2);
                    String msowo = (String) al.get(3);
                    String mfn = (String) al.get(4);
                    String mlst = (String) al.get(5);
                    String mmail = (String) al.get(6);
                    String mphn = (String) al.get(7);
                    String mphn2 = (String) al.get(8);
                    String mfax = (String) al.get(9);
                    String mmob = (String) al.get(10);
                    String madd = (String) al.get(11);
                    String madd2 = (String) al.get(12);
                    String mpostl = (String) al.get(13);
                    String mcity = (String) al.get(14);
                    String mregion = (String) al.get(15);
                    String mcountry = (String) al.get(16);

                    // This trick ensures that we get the data properly even if it doesn't start from first few rows
                    for (i = 0; i < 15 || i < rows; i++) {
                        row = sheet.getRow(i);
                        if (row != null) {
                            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                            if (tmp > cols) {
                                cols = tmp;
                            }
                        }
                    }
                    //Session ses=new Session(filename, tmp, filename);
                    if (ffcount == nfcount && mse.equals(memsekey) && mmn.equals(memname) & msowo.equals(sowo) && mfn.equals(memfn) && mlst.equals(memlstn) && mmail.equals(mememail) && mphn.equals(phn) && phn2.equals(mphn2) && mfax.equals(fax) && mmob.equals(mob) && madd.equals(add) && madd2.equals(add2) && mpostl.equals(postl) && mcity.equals(city) && mregion.equals(region) && mcountry.endsWith(country)) {

                        for (int r = 1; r < rows; r++) {
                            //for(int k=memSekey;k<100;k++)

                            //    for (int r = 1; r < 20; r++) {
                            row = sheet.getRow(r);
                            //  int r=row.getRowNum();
                            String memSekey = null;
                            String memName = null;
                            String Sowo = null;
                            String memFn = null;
                            String memLstn = null;
                            String memEmail = null;
                            String Phn = null;
                            String Phn2 = null;
                            String Fax = null;
                            String Mob = null;
                            String Add = null;
                            String Add2 = null;
                            String Postl = null;
                            String City = null;
                            String Region = null;
                            String Country = null;
                            if (row != null) {
                                //  for(int c = 0; c < cols; c++) {
                                cell = row.getCell((short) 0);
                                if (cell != null) {
                                    memSekey = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 1);
                                if (cell != null) {
                                    memName = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 2);
                                if (cell != null) {
                                    Sowo = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 3);
                                if (cell != null) {
                                    memFn = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 4);
                                if (cell != null) {
                                    memLstn = cell.getRichStringCellValue().getString();
                                }

                                cell = row.getCell((short) 5);
                                if (cell != null) {
                                    memEmail = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 6);
                                if (cell != null) {
                                    Phn = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 7);
                                if (cell != null) {
                                    Phn2 = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 8);
                                if (cell != null) {
                                    Fax = cell.getRichStringCellValue().getString();
                                }

                                cell = row.getCell((short) 9);
                                if (cell != null) {
                                    Mob = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 10);
                                if (cell != null) {
                                    Add = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 11);
                                if (cell != null) {
                                    Add2 = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 12);

                                if (cell != null) {
                                    Postl = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 13);
                                if (cell != null) {
                                    City = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 14);
                                if (cell != null) {

                                    Region = cell.getRichStringCellValue().getString();
                                }
                                cell = row.getCell((short) 15);
                                if (cell != null) {
                                    Country = cell.getRichStringCellValue().getString();
                                }




                                // int flag = 0;
                                if ((memSekey != null)) {
                                    //&& memName != null &&  Sowo != null &&  memFn != null && memLstn !=null &&  memEmail != null &&  Phn  && Phn2 != null && Fax != null && Mob != null && Add != null && Add2 != null && Postl != null && City != null && Region != null)) {
                                    //result=true;
                                    System.out.println(memSekey + "" + memName + " " + Sowo + "" + memFn + "" + memLstn + "" + memEmail + "" + Phn + "" + Phn2 + "" + Fax + "" + Mob + "" + Add + "" + Add2 + "" + Postl + "" + City + "" + Region + "" + Country);
                                    // String testStr = memSekey + " - " + Country;
                                    List<Object[]> obj = new ArrayList<Object[]>();
                                    List D = (List) new StaticSentence(mapp.getSession(), "SELECT SEARCHKEY FROM CUSTOMERS where  SEARCHKEY =?", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING})).list(new Object[]{memSekey});
//                                  Connection memCon = mapp.getSession().getConnection();
//                                   String qry = "Select SEARCHKEY from  customers";
//                                    PreparedStatement memPstm = memCon.prepareStatement(qry);
//                                // PreparedStatement memPstm = mapp.prepareStatement(qry);
////                                    //memPstm.setString(1, testStr);
//                                  ResultSet memRs = memPstm.executeQuery();
//                                   ArrayList<String> alist = new ArrayList<String>();                                   
//                                   while (memRs.next()) {
//                                        alist.add(memRs.getString(1));
//                                    }
                                    if (D.isEmpty()) {
                                        newRegistratios(mapp, memSekey, memName, Sowo, memFn, memLstn, memEmail, Phn, Phn2, Fax, Mob, Add, Add2, Postl, City, Region, Country);
                                    } else {
//                                       
                                        JOptionPane.showMessageDialog(null, memSekey + "-Alredyregisterd!!");
                                        //appendfile ap= new appendfile();
                                        // ap.get();
//                                        FileWriter fstream = new FileWriter("out.txt", true);
//                                        BufferedWriter out = new BufferedWriter(fstream);
                                     //   out.append(memSekey);
                                       
                                        if(!memSekey.equals(null)){
                                            a1.add(memSekey);
                                        }
                                        BufferedWriter writer = null;
                                        try {
                                            
                                           
                                            writer = new BufferedWriter(new FileWriter("Error.txt"));
                                            for(int k=0;k<=a1.size();k++){
                                              memSekey = (String) a1.get(k);
                                            writer.write(memSekey + ",");
                                            }

                                        } catch (Exception ioe ) {
                                        } finally {
                                            try {
                                                if (writer != null) {
                                                    writer.close();
                                                }
                                            } catch (Exception ioe) {
                                            }
                                        }
                                        //Printwriter out1= new Printwriter("dup.txt");
                                        result = true;
                                    }
                                } //else {
                            // JOptionPane.showMessageDialog(null, "Error in File Content ");
                            // result = false;
                            }
                        }

                    //}
                    //else {
                    //JOptionPane.showMessageDialog(null, "Incorrect File Format ");
                    }

                }

            }
        // Call.Close(fs);
        } catch (Exception ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Occured While Loading");

        }
        return result;
    }
    //return result;   
    // return result;
    //   }
    // private void newRegistratios(String memSekey, String memName, String Sowo, String memFn, String memLstn, String memEmail, String Phn, String Phn2, String Fax, String Mob, String Add, String Add2, String Postl, String City, String Region, String Country) {
    //throw new UnsupportedOperationException("Not yet implemented");
    public int newRegistratios(AppView mapp, String memSekey, String memName, String Sowo, String memFn, String memLstn, String memEmail, String Phn, String Phn2, String Fax, String Mob, String Add, String Add2, String Postl, String City, String Region, String Country) {

        try {
            boolean a = true;
            Object[] obj = new Object[]{UUID.randomUUID().toString(), memSekey, null, memName, null, null, Add, Add2, Postl, City, Region, Country, memFn, memLstn, memEmail, Phn, Phn2, Fax, null, a, null, null, null, Sowo, Mob, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
            int exec = new StaticSentence(mapp.getSession(), "INSERT INTO CUSTOMERS (ID,SEARCHKEY,TAXID,NAME,TAXCATEGORY,CARD,ADDRESS,ADDRESS2,POSTAL,CITY,REGION,COUNTRY,FIRSTNAME,LASTNAME,EMAIL,PHONE,PHONE2,FAX,NOTES,VISIBLE,IMAGE,MEMTYPE,FINGERPRINTDATA,SOWO,MOBILE,SERVICETAXCAT,ENTTAXCAT,SIGNATURE,SPONSOR1,SPONSOR2,SPONSOR3,JOINDATE,TERDATE,DOB,ACCOUNT,OPENINGBALANCE,PASSWORD,SENTMSG,UNSENTMSG,PASSWORDGENERATED,PASSREST,REFERENCEID,CONFIRMDATE,effectivedate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.IMAGE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING, Datas.NULL, Datas.NULL, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), null).exec(obj);

//            if(exec>0){
//                JOptionPane.showMessageDialog(null,"uploded successfull");
//                }
//              else{
//                JOptionPane.showInputDialog("insertion failed");
        } catch (Exception ex) {
            System.out.println("unable to connect Database!!" + ex);
        }
        return exec;
    }
}
//            Connection conn = Session.getConnection();
//            Connection esslVConn = conn.getConnection();

//            SimpleDateFormat Dformat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss.mmm");
//            Date date = new Date();
//            sysDate = Dformat.format(date);
//
//
//
//            PreparedStatement pstm = esslVConn.prepareStatement("SELECT Name FROM Department ");
//
//            ResultSet categoryRs = pstm.executeQuery();
//            ArrayList<String> CategoryName = new ArrayList();
//            while (categoryRs.next()) {
//                CategoryName.add(categoryRs.getString(1));
//            }
//            categoryRs.close();
//            if (!CategoryName.contains(memName)) {
//
//                String deptInQry = "INSERT INTO Department ([NAME],createdById,CreatedDate,ModifiedById,ModifiedDate) VALUES (?,1,?,1,?)";
//                PreparedStatement esslpstmDptInsert = esslVConn.prepareStatement(deptInQry);
//                esslpstmDptInsert.setString(1, memName);
//                esslpstmDptInsert.setString(2, sysDate);
//                esslpstmDptInsert.setString(3, sysDate);
//                esslpstmDptInsert.executeUpdate();
//
//            }
//
//            // V3 insert
//            PreparedStatement esslVSecectDpt = esslVConn.prepareStatement("SELECT ID FROM DEPARTMENT WHERE NAME='" + memName + "'");
//            ResultSet dptIDRs = esslVSecectDpt.executeQuery();
//            String dptID = null;
//            while (dptIDRs.next()) {
//                dptID = dptIDRs.getString(1);
//
//            }
//
//            String[] EmpcodeArr = memNum.split("-");
//            String staffOne = EmpcodeArr[0];
//            String staffTwo = EmpcodeArr[1];
//            char EmpGenderCh = Gender.charAt(0);
//            String EmpGenderChStr = Character.toString(EmpGenderCh);
//
//            // Device staffcode modification
//            StringBuffer staffCodeBufferOne = new StringBuffer(staffOne);
//            StringBuffer staffCodeBufferTwo = new StringBuffer(staffTwo);
//            StringBuffer staffCodeCatch = new StringBuffer();
//            staffCodeBufferOne.insert(0, memCategory);
//
//
//            if (staffCodeBufferOne.charAt(0) == 'M') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "1");
//            } else if (staffCodeBufferOne.charAt(0) == 'S') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "2");
//            } else if (staffCodeBufferOne.charAt(0) == 'A') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "3");
//            } else if (staffCodeBufferOne.charAt(0) == 'B') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "4");
//            } else if (staffCodeBufferOne.charAt(0) == 'C') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "5");
//            } else if (staffCodeBufferOne.charAt(0) == 'D') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "6");
//            } else if (staffCodeBufferOne.charAt(0) == 'E') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "7");
//            } else if (staffCodeBufferOne.charAt(0) == 'F') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "8");
//            } else if (staffCodeBufferOne.charAt(0) == 'G') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "9");
//            } else if (staffCodeBufferOne.charAt(0) == 'H') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "10");
//            } else if (staffCodeBufferOne.charAt(0) == 'I') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "11");
//            } else if (staffCodeBufferOne.charAt(0) == 'J') {
//                staffCodeCatch.append(memberCodeCreate(staffOne, staffCodeBufferTwo));
//                staffCodeBufferTwo.delete(0, staffCodeBufferTwo.length());
//                staffCodeBufferTwo.append(staffCodeCatch);
//                staffCodeBufferTwo.insert(0, "12");
//            }
//
//            System.out.println(staffCodeBufferTwo);
//            String DeviceStaffCode = staffCodeBufferTwo.toString();
//
//            String essVInsertStaff = "INSERT INTO STAFF (Name,departmentid,staffcode,staffcode1,staffcode2,devicecode,gender,doj,doc,createdDate,ModifiedDate,stafflatestpunchtime,CreatedById,ModifiedById,specialHolidayGroupId,Designation) values (?,?,?,?,?,?,?,?,?,?,?,?,2,2,2,?)";
//            PreparedStatement esslVpstSt = esslVConn.prepareStatement(essVInsertStaff);
//            esslVpstSt.setString(1, memType);
//            esslVpstSt.setString(2, dptID);  //int
//            esslVpstSt.setString(3, memNum + " - " + memCategory);
//            esslVpstSt.setString(4, staffOne);
//            esslVpstSt.setString(5, DeviceStaffCode); //int
//            esslVpstSt.setString(6, DeviceStaffCode); //int
//            esslVpstSt.setString(7, EmpGenderChStr);
//            esslVpstSt.setString(8, sysDate);
//            esslVpstSt.setString(9, sysDate);
//            esslVpstSt.setString(10, sysDate);
//            esslVpstSt.setString(11, sysDate);
//            esslVpstSt.setString(12, sysDate);
//            esslVpstSt.setString(13, "Member");
//            esslVpstSt.executeUpdate();
//
//            String essVInsertBio = "INSERT INTO StaffBiometricDetails (DeviceStaffCode,privilege,password,Fp1,companyId) values (?,0,'0',?,1)";
//            PreparedStatement esslVBioPstm = esslVConn.prepareStatement(essVInsertBio);
//            esslVBioPstm.setString(1, DeviceStaffCode);
//            esslVBioPstm.setString(2, "");
//            esslVBioPstm.executeUpdate();
//
//
//            //FingerPrint DataBase Connection
//
//            String esslStaffId = "Select id from staff where DeviceCode ='" + staffCodeBufferTwo + "'";
//            PreparedStatement esslStaffidPstm = esslVConn.prepareStatement(esslStaffId);
//            ResultSet esslStaffIdRs = esslStaffidPstm.executeQuery();
//            String esslStaffIdName = null;
//
//            while (esslStaffIdRs.next()) {
//                esslStaffIdName = esslStaffIdRs.getString(1);
//            }
//            esslStaffIdRs.close();
//
//            PreparedStatement FpPstm = conn.prepareStatement("Insert Into MemberMasters (MemNumber,MemName,DeviceStaffCode,Fp1,FpImage,CardNumberMain,CardNumber,V3ID,FPcheck,memType,Active) Values (?,?,?,?,?,?,?,?,?,?,?)");
//            FpPstm.setString(1, memNum + " - " + memCategory);
//            FpPstm.setString(2, memType);
//            FpPstm.setString(3, DeviceStaffCode);
//            FpPstm.setString(4, "");
//            FpPstm.setString(5, "");
//            FpPstm.setString(6, "");
//            FpPstm.setInt(7, 0);
//            FpPstm.setString(8, esslStaffIdName);
//            FpPstm.setString(9, "False");
//            FpPstm.setString(10, memCategory);
//            FpPstm.setString(11, "1");
//            FpPstm.executeUpdate();
//
// } catch (Exception ex) {
// System.out.println("unable to connect Database!!" + ex);
//   }
//  }
// }
//    boolean load(String text, ArrayList al) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
    


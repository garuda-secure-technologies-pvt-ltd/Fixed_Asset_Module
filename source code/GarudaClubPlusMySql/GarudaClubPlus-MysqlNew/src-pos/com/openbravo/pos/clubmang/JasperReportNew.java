/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.Session;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.util.JRViewer300;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author swathi
 */
public class JasperReportNew {


    // private static JasperPrint jp;
    
     public static JasperPrint runReport(AppView app,String reportFile,Map reportparams,boolean print,DataSourceProvider data1,boolean display,String filename) {
       JasperPrint  jp=new JasperPrint();
         try{
        // Thread thread1;
           JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
          // Connection jdbcConnection = connectDB(databaseName, userName, password);
         //  JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(list);
       //    DataSourceProvider data1=new DataSourceProvider(list);
           JRDataSource data= data1.create(jasperReport);

          /*  Map reportparams = new HashMap();
            reportparams.put("Facility", param[0]);
            reportparams.put("Rate", param[1]);
            reportparams.put("Period", param[2]);
            reportparams.put("billnum", param[3]);
            reportparams.put("billedby", param[4]);*/
          // Session s=app.getSession();
         //  Connection con=s.getConnection();
        //   if(con==null){
         //      app.getSession().connect();
         //      con=s.getConnection();
         //  }
         //  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,data);
             jp = JasperFillManager.fillReport(jasperReport, reportparams, data);
             
              try{
             if(print==false && display==false){
                 JasperExportManager.exportReportToPdfFile(jp, "./TempReports/"+filename+".pdf");
             }else if(print==true)
                   printdata(jp);
             else if(display==true) 
               JasperViewer.viewReport(jp,false);

           }catch(Exception e){
            e.printStackTrace();
           }

           
     }catch(Exception ex) {
           String connectMsg = "Could not create the report " + ex.getMessage() + " " + ex.getLocalizedMessage();
           System.out.println(connectMsg);
     }
     return jp;
}

     
       private static void printdata(final JasperPrint jasperPrint){
         Thread thread =
			new Thread(
				new Runnable()
				{
					public void run()
					{
						try
						{
                                                    
							JasperPrintManager.printReport(jasperPrint, true);
						}
						catch (Exception ex)
						{
							ex.printStackTrace();
							//JOptionPane.showMessageDialog(JRViewer300.this, getBundleString("error.printing"));
						}
					}
				}
			);

		thread.start();
       }
}

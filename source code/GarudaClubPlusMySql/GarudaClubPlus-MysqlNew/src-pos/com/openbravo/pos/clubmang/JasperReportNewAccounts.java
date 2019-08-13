/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.data.loader.Session;
import com.openbravo.pos.Accounts.AccountReportsExcel;
//import com.openbravo.pos.Accounts.AccountReportsExcel.ParameterAccounts;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.util.JRViewer300;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRReportFont;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author swathi
 */
public class JasperReportNewAccounts {


    // private static JasperPrint jp;
    
     public static JasperPrint runReport(AppView app,String reportFile,List<AccountReportsExcel.ParameterAccounts> paramList,boolean print,boolean display,String filename) {
       JasperPrint  jp=new JasperPrint();
       List< JasperPrint> list = new ArrayList< JasperPrint>();
       JRPdfExporter exp = new JRPdfExporter();
         try{
         for(int i=0;i<paramList.size();i++){
            Map reportparams = new HashMap();
          
           reportparams =paramList.get(i).getParamsMap();
           DataSourceProvider data1=paramList.get(i).getDatatemp();
           JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
           JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
      
           JRDataSource data= data1.create(jasperReport);
           jp = JasperFillManager.fillReport(jasperReport, reportparams, data);
           list.add(jp);
           }//for
             
             
//           exp.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, list);  
//            exp.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File("E:/Pratima/testingReportfiles/testAccounts.pdf")); 
//           exp.exportReport();
//             exp.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File("E:/Pratima/testingReportfiles/testAccounts.xls")); 
//           exp.exportReport();
//             exp.setParameter(JRPdfExporterParameter.OUTPUT_FILE, new File("E:/Pratima/testingReportfiles/testAccounts.cvs")); 
//           exp.exportReport();
//            
           jp=  getCombinedReport(list);
             
             
             
             
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
      
       
       public static JasperPrint getCombinedReport(List reports) 
{ 

JasperPrint combinedReport = new JasperPrint(); 

//temp variable 
JasperPrint print; 
for(int i = 0; i < reports.size(); i++) 
{ 
print = (JasperPrint) reports.get(i); 

copyPages(combinedReport, print); 
try{        
copyFonts(combinedReport, print); 
}catch(JRException  ex){}
//} 
copyProperties(combinedReport, print); 
}
return combinedReport; 
} 
private static void copyFonts(final JasperPrint combinedReport, JasperPrint 
filledReport)throws JRException 
{ 
List fonts = filledReport.getFontsList(); 

if (fonts == null) 
{ 
return; 
} 

for (Iterator iter = fonts.iterator(); iter.hasNext();) 
{ 
JRReportFont font = (JRReportFont) iter.next(); 
if (!combinedReport.getFontsMap().containsKey(font.getName())) 
{ 
combinedReport.addFont(font); 
} 
} 
} 

private static void copyPages(final JasperPrint combinedReport, JasperPrint 
filledReport) 
{ 
List pages = filledReport.getPages(); 

if (pages == null) 
{ 
return; 
} 

for (Iterator iter = pages.iterator(); iter.hasNext();) 
{ 
JRPrintPage page = (JRPrintPage) iter.next(); 
combinedReport.addPage(page); 
} 
} 


private static void copyProperties(final JasperPrint target, JasperPrint source) 
{ 
target.setDefaultFont(source.getDefaultFont()); 
target.setName(source.getName()); 
target.setOrientation(source.getOrientation()); 
target.setPageHeight(source.getPageHeight()); 
target.setPageWidth(source.getPageWidth()); 
} 
}

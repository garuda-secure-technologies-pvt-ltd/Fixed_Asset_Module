/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;

import com.openbravo.pos.forms.AppView;
import java.beans.*;
import java.io.Serializable;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author User
 */
public class JasperReportNew2 implements Serializable {
     public static JasperPrint runReport(AppView app,String reportFile,Map reportparams,boolean print,DataSourceProvider2 data1,boolean display,String filename) {
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
    
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public JasperReportNew2() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}

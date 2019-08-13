/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.clubmang;


import com.openbravo.pos.reports.DataSourceProductTotal1;
import java.beans.*;
import java.io.Serializable;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author User
 */
public class DataSourceProvider2 implements Serializable {
    
     private List list;
    private List list1;
    private JRField[] fields;
    private JRDataSource datasource;

    public DataSourceProvider2() {
    }

     public DataSourceProvider2(List list) {
        this.list = list;
    }

    public DataSourceProvider2(List list, List list1) { //praveen:added to set two list for detailed ledger list
        this.list = list;
        this.list1 = list1;
    }
    public void setDataSource2(DataSourceProductTotal1 ds) {
       this.datasource = ds;
    }
    
     public JRDataSource create(JasperReport report) throws JRException {
        return datasource;
    }

    
    
    
    
    
    
    
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
//    public DataSourceProvider2() {
//        propertySupport = new PropertyChangeSupport(this);
//    }
    
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

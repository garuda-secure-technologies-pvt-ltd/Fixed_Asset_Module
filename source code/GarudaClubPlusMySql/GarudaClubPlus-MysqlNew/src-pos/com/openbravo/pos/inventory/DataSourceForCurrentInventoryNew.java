
package com.openbravo.pos.inventory;

import com.openbravo.format.Formats;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForCurrentInventoryNew implements JRDataSource{
    
    private int m_nIdx;
    private List<CurrentInventoryTableModel.InventoryInfo> v;

    public DataSourceForCurrentInventoryNew() {
        this(new ArrayList<CurrentInventoryTableModel.InventoryInfo>());
    }

    public DataSourceForCurrentInventoryNew(List<CurrentInventoryTableModel.InventoryInfo> v) {
        m_nIdx = -1;
       this.v=v;
    }

    @Override
    public boolean next() throws JRException {
         m_nIdx++;
        return (m_nIdx < v.size());
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
          Object o=null;

        String sName = field.getName();

        CurrentInventoryTableModel.InventoryInfo curr = v.get(m_nIdx);


        if (curr==null) {
         return null;
        }
      

        if (sName.equals("WAREHOUSE")) {
            o = curr.getLOCATION();
        } else if (sName.equals("CATEGORY")) {
            o = curr.getCATEGORY();
        } else if (sName.equals("PRODUCT")) {
            o = curr.getPRODUCT();
        } 
        
        else if (sName.equals("SVALUE")) {
            o = curr.getUNITS();
        } 
        else if (sName.equals("UNITTYPE")) {
            o = curr.getUNITTYPE();
        } 
        
        return o;
    }
    
    
}

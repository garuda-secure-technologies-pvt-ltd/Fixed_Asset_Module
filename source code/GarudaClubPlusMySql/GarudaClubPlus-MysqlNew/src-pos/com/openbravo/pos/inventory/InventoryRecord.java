
package com.openbravo.pos.inventory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.openbravo.format.Formats;
import com.openbravo.pos.ticket.CategoryInfo;
import java.util.ArrayList;

/**
 *
 * @author adrianromero
 */
public class InventoryRecord {
    
    private Date m_dDate;
    private MovementReason m_reason;
    private CategoryInfo m_locationOri;
    private List<InventoryLine> m_invlines=new ArrayList<InventoryLine>();
    
    /** Creates a new instance of InventoryRecord */
    public InventoryRecord(Date d, MovementReason reason, CategoryInfo location, List<InventoryLine> invlines) {
        m_dDate = d;
        m_reason = reason;
        m_locationOri = location;
        m_invlines = invlines;
    }
    
    public Date getDate() {
        return m_dDate;
    }   
    public MovementReason getReason() {
        return m_reason;
    }    
    public CategoryInfo getLocation() {
        return m_locationOri;
    }   
    
    public List<InventoryLine> getLines() {
        return m_invlines;
    }
    public void removeline(int i){
      m_invlines.remove(i);
    }
    
    public boolean isInput() {
        return m_reason.isInput();
    }
    
    public double getSubTotal() {
        double dSuma = 0.0;
        InventoryLine oLine;            
        for (Iterator<InventoryLine> i = m_invlines.iterator(); i.hasNext();) {
            oLine = i.next();
            dSuma += oLine.getSubValue();
        }        
        return dSuma;
    }
    
    public String printDate() {
        return Formats.TIMESTAMP.formatValue(m_dDate);
    }    
    public String printLocation() {
        return m_locationOri.toString();
    }
    public String printReason() {
        return m_reason.toString();
    }    
    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
  /*  public String printunittype(){
        return
    }*/
}

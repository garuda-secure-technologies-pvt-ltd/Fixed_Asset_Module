///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.openbravo.pos.sales;
//
//import com.openbravo.basic.BasicException;
//import com.openbravo.data.gui.MessageInf;
//import com.openbravo.pos.forms.DataLogicSales;
//import com.openbravo.pos.forms.LookupUtilityImpl;
//import com.openbravo.pos.sales.restaurant.*;
//import com.openbravo.pos.ticket.CategoryInfo;
//import com.openbravo.pos.ticket.ProductInfo;
//import javax.swing.table.AbstractTableModel;
//import com.openbravo.pos.ticket.TicketInfo;
//import java.awt.Component;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.TreeMap;
//
///**
// *
// * @author a
// */
//public class QTKPrintTableModel extends AbstractTableModel {
//
//    private static final String[] columnHeaders = new String[] {"Sl No", "Product", "Quantity", "Rate"};
//
//    private Component parent;
//    private DataLogicSales dlSales;
//    private List<QTicketLineInfo> m_rows;
//
//    public QTKPrintTableModel (Component parent, DataLogicSales dlSales) {
//        this.parent = parent;
//        this.dlSales = dlSales;
//    }
//
//    public void refresh(QticketInfo qticket) {
//        this.m_rows = qticket == null || qticket.getLines() == null ? new ArrayList<QTicketLineInfo>() : qticket.getLines();
//        fireTableDataChanged();
//    }
//
//    public String getColumnName(int column) {
//        return columnHeaders[column];
//    }
//
//    public int getRowCount() {
//        return m_rows != null ? m_rows.size() : 0;
//    }
//    public int getColumnCount() {
//        return columnHeaders.length;
//    }
//    public Object getValueAt(int row, int column) {
//       QTicketLineInfo i = m_rows.get(row);
//            switch (column) {
//                case 0: return i.getLine();
//                case 1:
//                    ProductInfo pInfo = LookupUtilityImpl.getInstance(null).getProductsMap().get(i.getProduct());
//                    return pInfo != null ? pInfo.getName() : null;
//                case 2: return i.getMultiply();
//                case 3: return i.getRate();
//                default: return null;
//         }
//    }
// public boolean isCellEditable(int row, int column) {
//            return false;
//        }
// 
// public void clear() {
//            int old = getRowCount();
//            if (old > 0) { 
//                m_rows.clear();
//                fireTableRowsDeleted(0, old - 1);
//            }
//        }
//    
//        public List<QTicketLineInfo> getLines() {
//            return m_rows;
//        }
//    
//        public QTicketLineInfo getRow(int index) {
//            return m_rows.get(index);
//        }
//        
//        public void setRow(int index, TicketInfo oLine){
//            throw new UnsupportedOperationException("QT item list is not modifiable");
//        }        
//        
//        public void addRow(QTicketLineInfo oLine) {
//            
//            insertRow(m_rows.size(), oLine);
//        }
//        
//        public void insertRow(int index, QTicketLineInfo oLine) {
//            
//            m_rows.add(index, oLine);
//            fireTableRowsInserted(index, index);
//        }
//        
//        public void removeRow(int row) {
//            m_rows.remove(row);
//            fireTableRowsDeleted(row, row);
//        }        
//}
//










///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author a
 */
public class QTKPrintTableModel {
   private List<qtdetailsline> dis;
    private static final String[] QTKHEADERS = new String[] {"Sl No", "QT Number", "Product", "Quantity", "Rate"};
  private QTKPrintTableModel()
   {
   }

  public static QTKPrintTableModel emptyinstance()
  {
      QTKPrintTableModel d=new QTKPrintTableModel();
      d.dis=new ArrayList<qtdetailsline>();
      return d;
  }
  public static QTKPrintTableModel loadInstance(AppView app, String cust) throws BasicException {
      QTKPrintTableModel d = new QTKPrintTableModel();
     List dlist = new StaticSentence(app.getSession()
                ,"SELECT q.ID, q.LINE, q.PARENTID, p.NAME, q.DMULTIPLY, q.RATE, q.ATTRIBUTES FROM QTITEMS q join products p on  q.product=p.id WHERE PARENTID = ? ORDER BY LINE"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( QTKPrintTableModel.qtdetailsline.class )).list(cust);
     if(dlist==null)
     {
         d.dis=new ArrayList<qtdetailsline>();
     }
     else
     {
         d.dis=dlist;
     }

     return d;

  }
  public List<qtdetailsline> getqtdetailsline()
     {
         return dis;
     }
  public AbstractTableModel getqtdetailsTableModel() {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(QTKHEADERS[column]);
            }
            public int getRowCount() {
                return dis.size();
            }
            public int getColumnCount() {

                return QTKHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
                qtdetailsline l = dis.get(row);

                switch (column) {

                 case 0 : return row + 1;
                case 1: return l.getParentid();
                case 2: return l.getProduct();
                case 3: return l.getDmultiply();
                case 4: return l.getRate();
                case 5: return l.getid();
                default: return null;
                }
            }
        };
    }


public static class qtdetailsline implements SerializableRead{
    private String id;
    private String line;
    private String parentid;
    private String product;
    private String dmultiply;
    private String rate;
    private String attributes;

    public void readValues(DataRead dr) throws BasicException
    {

        id=dr.getString(1);
    //    createdby=dr.getString(4);
        line=dr.getString(2);
        parentid=dr.getString(3);
        product=dr.getString(4);
        dmultiply=dr.getString(5);
        rate=dr.getString(6);
        attributes=dr.getString(7);
    }
    
    
    public String getid() {
     return id;
    }
    public String getLine(){
      return line;
    }
    public String getParentid(){
        return parentid;
    }
    public String getProduct(){
       return product;
    }
    public String getDmultiply()
    {
        return dmultiply;
    }
    public String getRate()
    {
        return rate;
    }
    public String getAttributes()
    {
        return attributes;
    }
    
    
    //////
     public String printcustomerid()
    {
        return id;
    }
    public String printwaiter()
    {
        return line;
    }
    public String printplace()
    {
        return parentid;
    }
    public String printfloor()
    {
        return product;
    }
    public String printprcategory()
    {
        return dmultiply;
    }

}



/////////////////////////////////////////////////////////////////


//public AbstractTableModel getTableModel() {
//        return new AbstractTableModel() {
//
//            @Override
//            public String getColumnName(int column) {
//                //return AppLocal.getIntString(FACILITYHEADERS[column]);
//                return (QTKHEADERS[column]);
//            }
//
//            public int getRowCount() {
//                return dis.size();
//            }
//
//            public int getColumnCount() {
//
//                return QTKHEADERS.length;
//            }
//            Class[] types = new Class[]{
//                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
//            };
//            boolean[] canEdit = new boolean[]{
//                false, false, false, false, false, false, true, true, true
//            };
//
//            @Override
//            public Class getColumnClass(int columnIndex) {
//                return types[columnIndex];
//            }
//
//            @Override
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//
//            @Override
//            public void setValueAt(Object aValue, int row, int column) {
//                qtdetailsline l = dis.get(row);
//                if (column == 6) {
//                    QTKitchen qtk = new QTKitchen();
//                    qtk.qtReprint(l.getid());
//               //     l.setStatusPrint(Boolean.parseBoolean(aValue.toString()));
//                // fireTableDataChanged();
//                } else if (column == 7) {
//      //              l.setStatusPrepd(Boolean.parseBoolean(aValue.toString()));
//               //  fireTableDataChanged();
//                } else if (column == 8) {
////                    dis.remove(row);
////                    boolean status = Boolean.parseBoolean(aValue.toString());
////                    if (status) {
////                        l.setStatusDelrvd(status);
////                    } else {
////                        l.setStatusDelrvd(status);
////                    }
//                    fireTableDataChanged();
//                }
//            }
//
//            public Object getValueAt(int row, int column) {
//                qtdetailsline l = dis.get(row);
//
//                switch (column) {
//
//                    case 0 : return row + 1;
//                case 1: return l.getfloor();
//                case 2: return l.getplace();
//                case 3: return l.getwaiter();
//                case 4: return l.getcrdate();
//                case 5: return l.getcreatedby();
//                case 6: return l.isSelectedPrint();
//                case 7: return l.isSelectedPrepd();
//                case 8: return l.isSelectedDelrvd();
//
//                    default:
//                        return null;
//                }
//            }
//        };
//    }


}

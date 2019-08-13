/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.pos.forms.AppLocal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class VoucherEntryTableModel {

    private static String[] HEADERS1={"","Particulars","Debit","Credit"};

    private List<VoucherEntry> voucher=new ArrayList<VoucherEntry>();
     public static VoucherEntryTableModel loadInstance(){
          VoucherEntryTableModel p=new VoucherEntryTableModel();
          p.voucher=new ArrayList<VoucherEntry>();
          return p;
     }

      public List<VoucherEntry> getVoucherEntrylist(){
       return voucher;
     }

public abstract class MyTableModel extends AbstractTableModel{
     public void setCellEditable(int index,boolean flag){

     }
}

public MyTableModel getTableModelforVoucherEntries() {
        return new MyTableModel()  {
            Class[] types = new Class [] {
                java.lang.String.class,java.lang.String.class,java.lang.Double.class,java.lang.Double.class
            };
            @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(HEADERS1[column]);
            }
            @Override
            public int getRowCount() {
                return voucher.size();
            }
            @Override
            public int getColumnCount() {

                return HEADERS1.length;
            }
             boolean[] canEdit = new boolean [] {
                true ,true,false,true
            };
            @Override
             public void setCellEditable(int index,boolean flag){
               canEdit[index]=flag;
             }
           @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            @Override
           public void setValueAt(Object aValue, int row, int column) {
               VoucherEntry l = voucher.get(row);
                switch (column) {
                case 0: l.setType(aValue);
                           break;
                case 1:  l.setname(aValue);
                         break;
                case 2:  l.setdebit(aValue);
                         break;
                case 3:  l.setcredit(aValue);
                         break;
                default:  break ;
                }

            }
            @Override
            public Object getValueAt(int row, int column) {
                VoucherEntry l = voucher.get(row);
                switch (column) {
                case 0: return l.getType();
                case 1: return l.getparticular();
                case 2: return l.getdebit();
                case 3: return l.getcredit();
                
                default: return null;
                }
            }
        };
    }

public static class VoucherEntry implements SerializableRead{
       private String type;
       private String particular;
       private Double credit;
       private Double debit;
     
     /*  public VoucherEntry(String type){
           this.type=type;
           particular="";
         //  credit=0.0;
         //  debit=0.0;
       }*/
        public void readValues(DataRead dr) throws BasicException {
            type=dr.getString(1);
            particular=dr.getString(2);
            debit=dr.getDouble(3);
           credit=dr.getDouble(4);
        }
        public String getType(){
          return type;
        }
        public void setType(Object type){
         this.type=type.toString();
        }
        public String getparticular(){
          return particular;
        }
        public void setname(Object name){
         particular=name.toString();
        }
        public Double getdebit(){
          return debit;
        }
        public void setdebit(Object amt){
          debit=Double.valueOf(amt.toString());
        }
         public Double getcredit(){
          return credit;
        }
        public void setcredit(Object amt){
          credit=Double.valueOf(amt.toString());
        }

}

}

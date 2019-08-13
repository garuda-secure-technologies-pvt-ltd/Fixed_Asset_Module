/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.adminUtils;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class CurrentStockTableModel {
       private List<CurrentStockTable> cslist;
       private static String[] HEADER=new String[]{"PRODUCT NAME","PRODUCT ID","TOTAL(IN+OUT)"};
       

       public static CurrentStockTableModel loadData(AppView app) throws BasicException{
           CurrentStockTableModel p=new CurrentStockTableModel();
           List<CurrentStockTable> l=null;
          
                l=new PreparedSentence(app.getSession()
                      , "select pname,pid,(out_+in_)as Total from (select pname,pid,sum(in_) as in_,sum(out_) as out_ from ( select p.name as pname,p.id as pid,sum(units) as in_,0.0 as out_  from products p join stockdiary s on p.id=s.product group by pname,pid "+
                        " union all select p.name as pname,p.id as pid,0.0 as in_ ,sum(units1) as out_  from products p join stockdiary s on p.id=s.product1 group by pname,pid  )as cs1 group by pname,pid )as cs2"
                      , null
                      , new SerializerReadClass(CurrentStockTableModel.CurrentStockTable.class)).list();


           if(l==null)
               p.cslist=new ArrayList();
           else
               p.cslist=l;
           return p;
       }
       public List<CurrentStockTable> getCurrentStockList(){
          return cslist;
       }
       public AbstractTableModel getTableModel(){
          return new AbstractTableModel() {

            public int getRowCount() {
               return  cslist.size();
            }
            @Override
             public String getColumnName(int column) {
                 return HEADER[column];
             }
            public int getColumnCount() {
               return HEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                CurrentStockTable cs=(CurrentStockTable)cslist.get(rowIndex);
                switch(columnIndex){
                    case 0: return cs.getPname();
                    case 1: return cs.getPid();
                    case 2: return cs.getTotal();


                    default: return null;
                }
            }
        };
       }
       public static class CurrentStockTable implements SerializableRead{

           private String pname;

        public String getPid() {
            return pid;
        }

        public String getPname() {
            return pname;
        }

        public int getTotal() {
            return total;
        }
           private String pid;
           private int total ;



        public void readValues(DataRead dr) throws BasicException {
             pname=dr.getString(1);
             pid=dr.getString(2);
             total=dr.getInt(3);
        }

       }
}

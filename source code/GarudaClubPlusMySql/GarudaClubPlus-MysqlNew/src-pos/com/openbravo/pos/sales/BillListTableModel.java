/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.mant.PlacesInfo;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.pos.sales.restaurant.CounterTotals;
//import com.openbravo.pos.panels.PaymentsModel.CounterTotals;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class BillListTableModel extends AbstractTableModel{
    private static final String[] columnHeaders = new String[] {"Sl No", "Member Name", "Member ID", "Bill No","AMOUNT","Waiter","Floor","Created By","Date"};


    private List<BillInfo> m_rows;
   // private List<CounterTotals> m_totals;
    public BillListTableModel (List<BillInfo> blinfo) {
        m_rows = blinfo;
       // this.m_totals=totals;
    }

    public void refresh(List<BillInfo> blinfo) {
        m_rows = blinfo;
       // this.m_totals=totals;
        fireTableDataChanged();
    }

     public String getColumnName(int column) {
        return columnHeaders[column];
    }

     public void clear() {
            int old = getRowCount();
            if (old > 0) {
                m_rows.clear();
                fireTableRowsDeleted(0, old - 1);
            }
        }


    public int getRowCount() {
        return m_rows.size();
    }

    public int getColumnCount() {
        return columnHeaders.length;
    }

    @SuppressWarnings("empty-statement")
    public Object getValueAt(int rowIndex, int columnIndex) {
        BillInfo i = m_rows.get(rowIndex);
      //  PlacesInfo p=new PlacesInfo();
         String counter="";
         AppView m_App = LookupUtilityImpl.getInstance(null).getAppView();
          String[] counterarr=new String[5];
     /*   String waitername="";
        String floor1="";
       
       
         try {
            
                 Object[] obj=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM WAITER WHERE ID=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(i.getWaiter());

              if(obj== null)
                 waitername="";
              else{
                   waitername=obj[0].toString();
               }
                  Object[] obj1=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT NAME FROM FLOORS WHERE ID=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(i.getFloor());

              if(obj1== null)
                 floor1="";
             else{
                floor1=obj1[0].toString();
             }

          */
           String custid="";
            String customer="";
//            if(columnindex)
        try{
          customer =i.getCustomer().getId();
               String cust1="";
            String cust2="";
             String temp1[]=customer.split(" ");
            String temp[]=customer.split("#");
            try{
             Object[] cust=(Object[]) new StaticSentence(LookupUtilityImpl.getInstance(null).getAppView().getSession(),
                "SELECT NAME,SEARCHKEY FROM CUSTOMERS WHERE ID = ? ",
                SerializerWriteString.INSTANCE
                , new SerializerReadBasic(new Datas[] { Datas.STRING,Datas.STRING})).find(temp[0]);

             if(cust!=null && cust[0] != null)
             {
              cust1=cust[0].toString();
              cust2=cust[1].toString();
             }


              }
            catch(Exception e)
            {
            }
             if(temp1.length>1)
               custid=cust2+":"+"G"+temp1[1];
             else
                 custid=cust2;

            if(temp.length>1)
                customer= cust1+" : "+temp[1];
            else
             customer= cust1;
            
            Object[] obj2=(Object[])   new StaticSentence(m_App.getSession()
                        , "SELECT ROLES.NAME FROM ROLES,PEOPLE WHERE PEOPLE.ROLE=ROLES.ID AND PEOPLE.NAME=?"
                       ,SerializerWriteString.INSTANCE
                       ,new SerializerReadBasic(new Datas[] { Datas.STRING})).find(i.getCreatedBy());

              if(obj2== null)
              {
                   counter="  ";
              // counterarr;
              }

              else{
                   counter=obj2[0].toString();

               }
                 counterarr=counter.split(" ");

         }

         catch(Exception e)
         {
         }
            String waiter="";
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return customer;
            case 2: return custid;
            case 3: return i.getID();
            case 4:return i.getAmountPlusTax();
            case 5:try{
                    WaiterInfo w=LookupUtilityImpl.getInstance(null).getWaiterMap().get(i.getWaiter());
                    waiter=w.getName();
                    return waiter;
                   }catch(Exception e){
                   return waiter;
                   }

            case 6: return i.getFloor();
            case 7: return counterarr[0];

            case 8: return Formats.DATE.formatValue(i.getCreatedDate());
            default: return null;
        }
    }
}

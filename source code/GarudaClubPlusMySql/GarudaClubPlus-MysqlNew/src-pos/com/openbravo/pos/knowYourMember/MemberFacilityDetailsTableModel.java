
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MemberFacilityDetailsTableModel {

  private  List<FacilityDetailsClass> FacilityList = new ArrayList<FacilityDetailsClass>();
   List<Object> facilitydetails = new ArrayList<Object>();
    
  private final static String[] TABLEHEADERS = {"Sr No. ", "FacilityStartDate" ," FacilityEndDate"  };

  private int FacilityListsize;


 public class FacilityDetailsClass{
        private String FacilityStartDate;
        private String FacilityEndDate;
        
        public String GetFacilityStartDate(){
            return FacilityStartDate;
        }
        public void setFacilityStartDate(String Name){
            this.FacilityStartDate=FacilityStartDate;
        }
        public String GetFacilityEndDate(){
            return FacilityEndDate;
        }
        public void setFacilityEndDate(String Name){
            this.FacilityEndDate=FacilityEndDate;
        }
     }
     
     
     
     
    private MemberFacilityDetailsTableModel()
    {
    }
    
    public  MemberFacilityDetailsTableModel loadFacilityInfoAll(AppView m_app ,List<FacilityDetailsClass> list ) throws BasicException{
        
        MemberFacilityDetailsTableModel details = new  MemberFacilityDetailsTableModel();
       details.FacilityList=new ArrayList<FacilityDetailsClass>();
       
         details.FacilityList =  list;
         details.FacilityListsize = details.FacilityList.size();
         return details;
     
}
    
    
 public  AbstractTableModel getTableModel(List<FacilityDetailsClass> FacilityList1) {
        
        FacilityList=FacilityList1;
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(TABLEHEADERS[column]);
            }
            public int getRowCount() {
                return FacilityList.size();
            }
            public int getColumnCount() {

                return TABLEHEADERS.length;
            }
            public Object getValueAt(int row, int column) {
              FacilityDetailsClass s = FacilityList.get(row);

                switch (column) {

                
                case 0: return s.GetFacilityStartDate();
                case 1: return s.GetFacilityEndDate();
                
                    
                default: return null;
                }
            }
        };
    }
     
  
      
}

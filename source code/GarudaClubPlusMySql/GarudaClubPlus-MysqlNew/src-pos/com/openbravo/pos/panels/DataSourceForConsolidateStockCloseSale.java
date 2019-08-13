
package com.openbravo.pos.panels;


import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DataSourceForConsolidateStockCloseSale implements JRDataSource{
    
    
    private int m_nIdx;
    private List<ConsolidateStockCloseSaleModel.ConsolidateStockReport> v;
    private List<CloseSaleTableModel.ProdList> v2;
    public DataSourceForConsolidateStockCloseSale() {
        this(new ArrayList<ConsolidateStockCloseSaleModel.ConsolidateStockReport>() , new ArrayList<CloseSaleTableModel.ProdList>() ); 
    }

    public DataSourceForConsolidateStockCloseSale(List<ConsolidateStockCloseSaleModel.ConsolidateStockReport> v  , List<CloseSaleTableModel.ProdList> v2) {
        m_nIdx = -1;
        this.v = v;
        this.v2=v2;
        
    }

    public Object getFieldValue(JRField field) throws JRException {
        Object o = null;

        String sName = field.getName();

        ConsolidateStockCloseSaleModel.ConsolidateStockReport curr = v.get(m_nIdx);


        if (curr == null) {
            return null;
        }

        if (sName.equals("pname")) {
            o = curr.getPname();
        } else if (sName.equals("intotal")) {
            o = curr.getInn();
        } else if (sName.equals("outtotal")) {
           if(curr.getOutt()<0.00){
                o = ((-1)*curr.getOutt());
           }
           else{
                o = curr.getOutt();
           }
           
            
            
        } else if (sName.equals("currentvalue")) {
            o = curr.getCurrent();
        } else if (sName.equals("catname")) {
            o = curr.getCatName();
        } else if (sName.equals("units")) {
            o = curr.getUnits();
        }
        else if (sName.equals("opbal")) {
            o = curr.getOpBal();
        }
        
        
        else if (sName.equals("TOTALSALE")) {
            if(curr.getOutt()==0){
                o = 0.00;
            }
            else{
               String Prodid= curr.getPname();
                Double Rate = 0.00;
                Double Qty=0.00;
                Double Amount = 0.00;
                for(int i=0;i<v2.size();i++){
                    String id = v2.get(i).getProdid();
                    if(id.equals(Prodid)){
                        Rate=v2.get(i).getRate();
                        Qty=Qty+v2.get(i).getQty();
                        
                    }
                }
                Amount=Rate*Qty;
                o=Rate;
                
                
                
            }
        }
        
        else if (sName.equals("RATE")) {
            
            if(curr.getOutt()==0){
                o = curr.getRate();
            }
            else{
                String Prodid= curr.getPname();
                Double Rate = 0.00;
                
                for(int i=0;i<v2.size();i++){
                    String id = v2.get(i).getProdid();
                    if(id.equals(Prodid)){
                        Rate=Rate+v2.get(i).getRate();
                        
                    }
                }
                
                o=Rate;
            }
            
            
        }   
            
        else if (sName.equals("QTSALE")) {
            if(curr.getOutt()==0){
                o = 0.00;
            }
            else{
                String Prodid= curr.getPname();
                Double Rqty = 0.00;
                
                for(int i=0;i<v2.size();i++){
                    String id = v2.get(i).getProdid();
                    if(id.equals(Prodid)){
                        Rqty=Rqty+v2.get(i).getQty();
                        
                    }
                }
                
                o=Rqty;
                
            }
            
        }   
        
        
        
        
        
        
        return o;
    }

    public boolean next() throws JRException {
        m_nIdx++;
        return (m_nIdx < v.size());
    }

    
}

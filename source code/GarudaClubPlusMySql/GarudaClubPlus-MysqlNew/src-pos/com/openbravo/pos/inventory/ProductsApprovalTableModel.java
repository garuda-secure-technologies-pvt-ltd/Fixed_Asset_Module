
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;


public class ProductsApprovalTableModel extends BeanFactoryDataSingle{
    protected Session s;
   // private final static String[] PROD_HERDERS = {"CrDate","CreatedBy","CrHost","Reference","Barcode","Codetype","Name","PriceBuy","PriceSell","Category","PrintCat.","TaxCat","StockCost","StockVolume","Iscom","Isscale","Attribute","UnitType","Paccount","Saccount","Warehouse","Invetrymaintain","Maxstock","Minstock","reorderquantity","Taxcat2","Taxcat3","Basic2","Basic3","Tax3Cascade"};
   private final static String[] PROD_HERDERS = {"CrDate","CreatedBy","CrHost","Reference","Barcode","Name","RequestType","Status"};
   
    private List<ProductsApprovalTableModel.PrdApvInfo> prod_data; 
    private int prod_data_length;
   DecimalFormat decimalFormat = new DecimalFormat("#0.00##");
    public ProductsApprovalTableModel(){}
    public ProductsApprovalTableModel(List<ProductsApprovalTableModel.PrdApvInfo> data1) {
        this.prod_data = data1;
    }
    
    @Override
    public void init(Session s) {
       this.s=s;
    }

    
     public static ProductsApprovalTableModel LoadProductApprovalData(AppView app)throws BasicException{
         ProductsApprovalTableModel product_Apr = new ProductsApprovalTableModel(); 
         
          try{
       product_Apr.prod_data = new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
              System.out.println("in method");
              
            product_Apr.prod_data = new StaticSentence(app.getSession(), "Select id, cr_date,reference,code,name,pricebuy,pricesell,category,prcategory,taxcat,iscom,isscale,unittype,location,inventrymaintain,taxcat2,taxcat3,basic2,basic3,tax3cascade,pr_id,codetype,hsn_code,stockvolume,paccount,saccount,maxstocklevel,minstocklevel,reorderquantity,reorderlevel,requesttype,cr_by,cr_host,cr_state,catalog_flag  from products_approval order by cr_date desc",null ,new SerializerReadClass(ProductsApprovalTableModel. PrdApvInfo.class)).list();
            product_Apr.prod_data_length = product_Apr.prod_data.size();
            System.out.println("prodlistsize"+product_Apr.prod_data.size());
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                    
            Logger.getLogger(ProductsApprovalTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return  product_Apr;
     } 
    
     public static ProductsApprovalTableModel LoadProductRequestData(AppView app,String reference)throws BasicException{
         ProductsApprovalTableModel product_Apr = new ProductsApprovalTableModel(); 
         
          try{
       product_Apr.prod_data = new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
       Object[] param=new Object[]{reference};
               product_Apr.prod_data = new StaticSentence(app.getSession(), "Select id, cr_date,reference,code,name,pricebuy,pricesell,category,prcategory,taxcat,iscom,isscale,unittype,location,inventrymaintain,taxcat2,taxcat3,basic2,basic3,tax3cascade,pr_id,codetype,hsn_code,stockvolume,paccount,saccount,maxstocklevel,minstocklevel,reorderquantity,reorderlevel,requesttype,cr_by,cr_host,cr_state,Catalog_flag from products_approval where reference=? and cr_state is null order by cr_date desc",new SerializerWriteBasic(new Datas[]{Datas.STRING}) ,new SerializerReadClass(ProductsApprovalTableModel. PrdApvInfo.class)).list(param);
            if( product_Apr.prod_data!=null){
                
               product_Apr.prod_data_length = product_Apr.prod_data.size();
            System.out.println("prodlistsize"+product_Apr.prod_data.size());}
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                    
            Logger.getLogger(ProductsApprovalTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return  product_Apr;
     } 
     public static ProductsApprovalTableModel LoadProductPendingRequestData(AppView app)throws BasicException{
         ProductsApprovalTableModel product_Apr = new ProductsApprovalTableModel(); 
         
          try{
       product_Apr.prod_data = new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
       
               product_Apr.prod_data = new StaticSentence(app.getSession(), "Select id, cr_date,reference,code,name,pricebuy,pricesell,category,prcategory,taxcat,iscom,isscale,unittype,location,inventrymaintain,taxcat2,taxcat3,basic2,basic3,tax3cascade,pr_id,codetype,hsn_code,stockvolume,paccount,saccount,maxstocklevel,minstocklevel,reorderquantity,reorderlevel,requesttype,cr_by,cr_host,cr_state,Catalog_flag from products_approval where  cr_state is null order by cr_date desc",null ,new SerializerReadClass(ProductsApprovalTableModel. PrdApvInfo.class)).list();
            product_Apr.prod_data_length = product_Apr.prod_data.size();
            System.out.println("prodlistsize"+product_Apr.prod_data.size());
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                    
            Logger.getLogger(ProductsApprovalTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return  product_Apr;
     } 
      public static ProductsApprovalTableModel LoadProductRequestDataByDate(AppView app,Date dateFrom,Date dateTo)throws BasicException{
         ProductsApprovalTableModel product_Apr = new ProductsApprovalTableModel(); 
         
          try{
       product_Apr.prod_data = new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
       
               product_Apr.prod_data = new StaticSentence(app.getSession(), "Select id, cr_date,reference,code,name,pricebuy,pricesell,category,prcategory,taxcat,iscom,isscale,unittype,location,inventrymaintain,taxcat2,taxcat3,basic2,basic3,tax3cascade,pr_id,codetype,hsn_code,stockvolume,paccount,saccount,maxstocklevel,minstocklevel,reorderquantity,reorderlevel,requesttype,cr_by,cr_host,cr_state,Catalog_flag from products_approval where cr_date>=? and cr_date<=? order by cr_date desc",new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP,Datas.TIMESTAMP,}) ,new SerializerReadClass(ProductsApprovalTableModel. PrdApvInfo.class)).list(new Object[]{dateFrom,dateTo});
            product_Apr.prod_data_length = product_Apr.prod_data.size();
            System.out.println("prodlistsize"+product_Apr.prod_data.size());
            
        }
        catch(BasicException ex){
             ex.printStackTrace();
                    
            Logger.getLogger(ProductsApprovalTableModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return  product_Apr;
     } 
    public List<ProductsApprovalTableModel.PrdApvInfo> getList(){
           if(prod_data!=null)
        {
            return prod_data;
        }
        else
            return new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
      } 
         
     
   
     
      public  AbstractTableModel getTableModel()
    {
          //   System.out.println("products approval table model::::");
        return new AbstractTableModel(){
          int i=2,j=0;
          
          public int getRowCount() {
                return prod_data.size();
            }
          public int getColumnCount() {
                return PROD_HERDERS.length;
            }
          
          @Override
          public String getColumnName(int column) {
                
                return PROD_HERDERS[column];
            }

          Class[] types = new Class[]{
         
//               java.lang.String.class,java.lang.Double.class ,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Double.class ,java.lang.Double.class ,
//              java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Double.class ,java.lang.Double.class ,java.lang.Integer.class ,java.lang.Integer.class ,java.lang.String.class, java.lang.String.class, java.lang.String.class, 
//              java.lang.String.class, java.lang.String.class,java.lang.Integer.class ,java.lang.Integer.class ,java.lang.Integer.class ,java.lang.Integer.class ,java.lang.String.class,java.lang.String.class,java.lang.Integer.class ,java.lang.Integer.class ,
//              java.lang.Integer.class , java.lang.String.class
               java.lang.String.class,java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.Double.class ,java.lang.Double.class ,java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.Integer.class ,java.lang.Integer.class ,java.lang.String.class, java.lang.String.class,
             java.lang.Integer.class ,java.lang.String.class,java.lang.String.class,java.lang.Integer.class ,java.lang.Integer.class ,
              java.lang.Integer.class,java.lang.String.class,java.lang.String.class,java.lang.Integer.class,java.lang.Integer.class,java.lang.Integer.class,java.lang.Integer.class,
              java.lang.String.class, java.lang.String.class, java.lang.String.class
          };
          boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false , false , false , false , false,
                false, false, false, false, false, false , false , false , false ,false,
                 false, false ,false, false, false, false , false , false , false //, false, false , false
            };
            
          
          
          @Override
            public void setValueAt(Object aValue, int row, int column) {
            

              
            }
           public Object getValueAt(int rowIndex, int columnIndex) {
              ProductsApprovalTableModel.PrdApvInfo r =prod_data.get(rowIndex);
              
               switch(columnIndex){
                //case 0: return r.getID();
                   case 0: return r.getCR_DATE();
                   case 1: return r.getCR_BY();
                   case 2: return r.getCR_HOST();
                   case 3: return r.getREFERENCE();
                   case 4: return r.getBARCODE();
                   case 5: return r.getNAME();
                   case 6: return r.getREQUESTTYPE();
                    case 7: 
                        if(r.getCR_STATE()!=null){
                          if(r.getCR_STATE().equals("1"))
                                  return "approved";
                          else return "rejected";
                        }
                        else   return  "pending";  
                         //case 6: return decimalFormat.format(r.getPRICEBUY());
                  // case 7: return decimalFormat.format(r.getPRICESELL());
                  // case 7: return r.getCATEGORY();
                 //  case 8: return r.getPRINTCAT();
                 // case 9: return r.getTAXCAT();
                //   case 12: return decimalFormat.format(r.getSTOCKCOST());
               //   case 13: return decimalFormat.format(r.getSTOCKVOL());
                  
                //  case 10: return r.getISCOM();
                //  case 11: return r.getISSCALE();
              //    case 16: return r.getATTRIBUTE();
                //  case 12: return r.getUNITTYPE();
              //    case 18: return r.getPACCOUNT();
            //      case 19: return r.getSACCOUNT();
                 // case 13: return r.getWAREHOUSE();
                 // case 14: return r.getINV_MAINTAIN();
           //       case 22: return r.getMAXSTOCK();
            //      case 23: return r.getMINSTOCK();
           //       case 24: return r.getREORDERQTY();
                 // case 15: return r.getTAXCAT2();
                //  case 16: return r.getTAXCAT3();
               //   case 17: return r.getBASIC2();
               //   case 18: return r.getBASIC3();
               //   case 19: return r.getTAX3CASCADE();
               //   case 20: return r.getPR_ID();
//                      if(r.getPR_ID()!=null)
//                      return r.getPR_ID();
//                      else return "";
//                  
                  
                   
               }
                return null;
            }
          
          
        };
    } 
      
      
      
     
      
   
     
   
   
        
        
     
         public List<ProductsApprovalTableModel. PrdApvInfo> getProductApprovalList(){
           if(prod_data!=null)
        {
            return prod_data;
        }
        else
            return new ArrayList<ProductsApprovalTableModel.PrdApvInfo>();
      }  
    
    
     
      
     public int getHallSize()
    {
        return prod_data_length;
    }
     
      public static class PrdApvInfo implements SerializableRead,IKeyed {

          private String ID;

       
          private Date CR_DATE;
          private String CR_BY;
          private String CR_HOST;


          private String  REFERENCE ; 
          private String BARCODE;
          private String CODETYPE;
          private String NAME;   
          private Double PRICEBUY;
          private Double PRICESELL ; 
          private String CATEGORY;
          private String PRINTCAT;
          private String TAXCAT;
//          private Double STOCKCOST;
          private String HSN_Code;
          private Double STOCKVOL;
          private int ISCOM;
          private int ISSCALE;
        //  private Byte[] ATTRIBUTE;

       
          private String UNITTYPE;
          private String PACCOUNT;
          private String SACCOUNT;
          private String WAREHOUSE;
          private int INV_MAINTAIN;
          private String MAXSTOCK;
          private String MINSTOCK;
          private String REORDERQTY;
          private String REORDERLEVEL;
          private String TAXCAT2;
          private String TAXCAT3;
          private int BASIC2;
          private int BASIC3;
          private int TAX3CASCADE;
          private String PR_ID;
         private String REQUESTTYPE;
        private String CR_STATE;
        private int Catalog_flag;

        
        
        public int getCatalog_flag() {
           // System.out.println("Catalog_flag::::"+Catalog_flag);
            return Catalog_flag;
        }

        public void setCatalog_flag(int Catalog_flag) {
            this.Catalog_flag = Catalog_flag;
        }

       

         public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public Date getCR_DATE() {
            return CR_DATE;
        }

        public void setCR_DATE(Date CR_DATE) {
            this.CR_DATE = CR_DATE;
        }

        public String getCR_BY() {
            return CR_BY;
        }

        public void setCR_BY(String CR_BY) {
            this.CR_BY = CR_BY;
        }

        public String getCR_HOST() {
            return CR_HOST;
        }

        public void setCR_HOST(String CR_HOST) {
            this.CR_HOST = CR_HOST;
        }

        public String getREFERENCE() {
            return REFERENCE;
        }

        public void setREFERENCE(String REFERENCE) {
            this.REFERENCE = REFERENCE;
        }

        public String getBARCODE() {
            return BARCODE;
        }

        public void setBARCODE(String BARCODE) {
            this.BARCODE = BARCODE;
        }

        public String getCODETYPE() {
            return CODETYPE;
        }

        public void setCODETYPE(String CODETYPE) {
            this.CODETYPE = CODETYPE;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public Double getPRICEBUY() {
            return PRICEBUY;
        }

        public void setPRICEBUY(Double PRICEBUY) {
            this.PRICEBUY = PRICEBUY;
        }

        public Double getPRICESELL() {
            return PRICESELL;
        }

        public void setPRICESELL(Double PRICESELL) {
            this.PRICESELL = PRICESELL;
        }

        public String getCATEGORY() {
            return CATEGORY;
        }

        public void setCATEGORY(String CATEGORY) {
            this.CATEGORY = CATEGORY;
        }

        public String getPRINTCAT() {
            return PRINTCAT;
        }

        public void setPRINTCAT(String PRINTCAT) {
            this.PRINTCAT = PRINTCAT;
        }

        public String getTAXCAT() {
            return TAXCAT;
        }

        public void setTAXCAT(String TAXCAT) {
            this.TAXCAT = TAXCAT;
        }

//        public Double getSTOCKCOST() {
//            return STOCKCOST;
//        }
//
//        public void setSTOCKCOST(Double STOCKCOST) {
//            this.STOCKCOST = STOCKCOST;
//        }

        public String getHSN_Code() {
            return HSN_Code;
        }

        public void setHSN_Code(String HSN_Code) {
            this.HSN_Code = HSN_Code;
        }
        

        public Double getSTOCKVOL() {
            return STOCKVOL;
        }

        public void setSTOCKVOL(Double STOCKVOL) {
            this.STOCKVOL = STOCKVOL;
        }

        public int getISCOM() {
            return ISCOM;
        }

        public void setISCOM(int ISCOM) {
            this.ISCOM = ISCOM;
        }

        public int getISSCALE() {
            return ISSCALE;
        }

        public void setISSCALE(int ISSCALE) {
            this.ISSCALE = ISSCALE;
        }

//     

        public String getUNITTYPE() {
            return UNITTYPE;
        }

        public void setUNITTYPE(String UNITTYPE) {
            this.UNITTYPE = UNITTYPE;
        }

        public String getPACCOUNT() {
            return PACCOUNT;
        }

        public void setPACCOUNT(String PACCOUNT) {
            this.PACCOUNT = PACCOUNT;
        }

        public String getSACCOUNT() {
            return SACCOUNT;
        }

        public void setSACCOUNT(String SACCOUNT) {
            this.SACCOUNT = SACCOUNT;
        }

        public String getWAREHOUSE() {
            return WAREHOUSE;
        }

        public void setWAREHOUSE(String WAREHOUSE) {
            this.WAREHOUSE = WAREHOUSE;
        }

        public int getINV_MAINTAIN() {
            return INV_MAINTAIN;
        }

        public void setINV_MAINTAIN(int INV_MAINTAIN) {
            this.INV_MAINTAIN = INV_MAINTAIN;
        }

        public String getMAXSTOCK() {
            return MAXSTOCK;
        }

        public void setMAXSTOCK(String MAXSTOCK) {
            this.MAXSTOCK = MAXSTOCK;
        }

        public String getMINSTOCK() {
            return MINSTOCK;
        }

        public void setMINSTOCK(String MINSTOCK) {
            this.MINSTOCK = MINSTOCK;
        }

        public String getREORDERQTY() {
            return REORDERQTY;
        }

        public void setREORDERQTY(String REORDERQTY) {
            this.REORDERQTY = REORDERQTY;
        }
         public String getREORDERLEVEL() {
            return REORDERLEVEL;
        }

        public void setREORDERLEVEL(String REORDERLEVEL) {
            this.REORDERLEVEL = REORDERLEVEL;
        }
        public String getTAXCAT3() {
            return TAXCAT3;
        }

        public void setTAXCAT1(String TAXCAT1) {
            this.TAXCAT3 = TAXCAT3;
        }

        public String getTAXCAT2() {
            return TAXCAT2;
        }

        public void setTAXCAT2(String TAXCAT2) {
            this.TAXCAT2 = TAXCAT2;
        }

        public int getBASIC2() {
            return BASIC2;
        }

        public void setBASIC2(int BASIC2) {
            this.BASIC2 = BASIC2;
        }

        public int getBASIC3() {
            return BASIC3;
        }

        public void setBASIC3(int BASIC3) {
            this.BASIC3 = BASIC3;
        }

        public int getTAX3CASCADE() {
            return TAX3CASCADE;
        }

        public void setTAX3CASCADE(int TAX3CASCADE) {
            this.TAX3CASCADE = TAX3CASCADE;
        }

        public String getPR_ID() {
            return PR_ID;
        }

        public void setPR_ID(String PR_ID) {
            this.PR_ID = PR_ID;
        }
          
          public String getREQUESTTYPE() {
            return REQUESTTYPE;
        }

        public void setREQUESTTYPE(String REQUESTTYPE) {
            this.REQUESTTYPE = REQUESTTYPE;
        }
  public String getCR_STATE() {
            return CR_STATE;
        }

        public void setCR_STATE(String CR_STATE) {
            this.CR_STATE = CR_STATE;
        }
         
          
        public void readValues(DataRead dr) throws BasicException {

             ID =dr.getString(1);
           CR_DATE= dr.getTimestamp(2);
         
          REFERENCE=dr.getString(3); 
           BARCODE=dr.getString(4);
           
          NAME=dr.getString(5);   
        PRICEBUY = dr.getDouble(6);
           PRICESELL = dr.getDouble(7) ; 
           CATEGORY=dr.getString(8);
           PRINTCAT=dr.getString(9);
          TAXCAT=dr.getString(10);
          ISCOM= dr.getInt(11);
        ISSCALE= dr.getInt(12);
         // ATTRIBUTE=dr.getString();
           UNITTYPE=dr.getString(13);
          
        WAREHOUSE=dr.getString(14);
           INV_MAINTAIN=dr.getInt(15);
          
           
           TAXCAT2=dr.getString(16);
           TAXCAT3=dr.getString(17);
         BASIC2=dr.getInt(18);
           BASIC3=dr.getInt(19);
          TAX3CASCADE=dr.getInt(20);
           PR_ID=dr.getString(21);
          CODETYPE=dr.getString(22);
//           STOCKCOST = dr.getDouble(23);
            HSN_Code = dr.getString(23);
           STOCKVOL=dr.getDouble(24);
            PACCOUNT=dr.getString(25);
          SACCOUNT=dr.getString(26);

         MAXSTOCK=dr.getString(27);
        MINSTOCK=dr.getString(28);
       REORDERQTY=dr.getString(29);
       REORDERLEVEL=dr.getString(30);
       REQUESTTYPE=dr.getString(31);
         CR_BY=dr.getString(32);
          CR_HOST=dr.getString(33);
          CR_STATE=dr.getString(34);
          Catalog_flag=dr.getInt(35);
          
        }

        public Object getKey() {
           return this;
        }
        
      }
      
      
      
      
     
       
     
      
}

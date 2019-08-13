/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.inventory;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class PurchaseIndentApprovalModel {

    private final static String[] COLUMNHEADERS = {"Seq.No", "Cr.Date", "CreatedBy", "Remark"};
    private final static String[] COLUMNHEADERS1 = {null,"Product", "Qty", "Ind.Qty", "Rate", "Ind.Rate", "Vendor", "Remark"};//for forwarder
    private final static String[] COLUMNHEADERS2 = {null,"Product", "Qty", "Ind.Qty", "Rate", "Ind.Rate", "Vendor", "Remark","ForwardedBy","ForwardedDate"};//for approver
    private final static String[] COLUMNHEADERS3 = {null,"Product", "Qty", "Ind.Qty", "Rate", "Ind.Rate", "Vendor", "Remark","ForwardedBy","ForwardedDate","ApprovedBy","ApprovedDate"};//for initiator
    private List<PurchaseIndentApprovalModel.PurchaseIndentApproverLine> purIndent;
    private List<PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine> purIndentDetails;
    private int user;

    public PurchaseIndentApprovalModel() {
    }

    public static PurchaseIndentApprovalModel loademptyInstance() {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        p.purIndent = new ArrayList<PurchaseIndentApproverLine>();
        return p;
    }

    public static PurchaseIndentApprovalModel loadInstanceModelForwarder(AppView app) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverLine> purIndList;
        purIndList = new StaticSentence(app.getSession(), "SELECT P.ID,P.SEQNO,P.CRDATE,P1.NAME,P.REMARK FROM PURCHASEINDENT P,PEOPLE P1,PURCHASEINDENTDETAILS PD WHERE P.REMARK IS NULL AND P.CREATEDBY=P1.ID AND PD.PURCHASEINDENTID=P.ID AND PD.FORWARDEDBY IS NULL GROUP BY P.ID order by p.seqno", null, new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverLine.class)).list();
        if (purIndList != null) {
            p.purIndent = purIndList;
        } else {
            p.purIndent = new ArrayList<PurchaseIndentApproverLine>();
        }
        return p;
    }

    public static PurchaseIndentApprovalModel loadInstanceModelInitiater(AppView app) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverLine> purIndList;
        purIndList = new StaticSentence(app.getSession(), "SELECT P.ID,P.SEQNO,P.CRDATE,P1.NAME,P.REMARK FROM PURCHASEINDENT P,PEOPLE P1,PURCHASEINDENTDETAILS PD WHERE P.CREATEDBY=P1.ID AND PD.PURCHASEINDENTID=P.ID AND PD.PURCHASEORDERREF IS NULL GROUP BY P.ID order by p.seqno", null, new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverLine.class)).list();
        if (purIndList != null) {
            p.purIndent = purIndList;
        } else {
            p.purIndent = new ArrayList<PurchaseIndentApproverLine>();
        }
        return p;
    }
    public static PurchaseIndentApprovalModel loadInstanceModelApprover(AppView app) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverLine> purIndList;
        purIndList = new StaticSentence(app.getSession(), "SELECT P.ID,P.SEQNO,P.CRDATE,P1.NAME,P.REMARK FROM PURCHASEINDENT P,PEOPLE P1,PURCHASEINDENTDETAILS PD WHERE P.REMARK IS NULL AND P.CREATEDBY=P1.ID AND PD.PURCHASEINDENTID=P.ID AND PD.FORWARDEDBY IS NOT NULL AND PD.APPROVEDBY IS NULL AND PD.ORDEREDFLAG IS NULL GROUP BY P.ID order by p.seqno", null, new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverLine.class)).list();
        if (purIndList != null) {
            p.purIndent = purIndList;
        } else {
            p.purIndent = new ArrayList<PurchaseIndentApproverLine>();
        }
        return p;
    }

    public static PurchaseIndentApprovalModel loadinstanceModelFarwarder1(AppView app, String purIndentId) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverDetailsLine> puDetLine;
        puDetLine = new StaticSentence(app.getSession(), "SELECT P.ID,P.PURCHASEINDENTID,P1.NAME,P.INTQTY,P.APPQTY,P.INTRATE,P.APPRATE,V.NAME,P.REMARKS,P.PRODUCTID,NULL,NULL,NULL,NULL,NULL FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID AND P.FORWARDEDBY IS NULL", SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine.class)).list(purIndentId);
        if (puDetLine != null) {
            p.purIndentDetails = puDetLine;
        } else {
            p.purIndentDetails = new ArrayList<PurchaseIndentApproverDetailsLine>();
        }
        return p;
    }

    public static PurchaseIndentApprovalModel loadinstanceModelApprover1(AppView app, String purIndentId) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverDetailsLine> puDetLine;
        puDetLine = new StaticSentence(app.getSession(), "SELECT P.ID ,P.PURCHASEINDENTID ,P1.NAME ,P.INTQTY,P.APPQTY,P.INTRATE,P.APPRATE ,V.NAME ,P.REMARKS,P.PRODUCTID,PE1.NAME,P.FORWARDEDDATE,NULL,NULL,NULL  FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID AND PE1.ID=P.FORWARDEDBY AND P.FORWARDEDBY IS NOT NULL AND  P.APPROVEDBY IS NULL AND P.ORDEREDFLAG IS NULL",
                SerializerWriteString.INSTANCE, new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine.class)).list(purIndentId);
        if (puDetLine != null) {
            p.purIndentDetails = puDetLine;
        } else {
            p.purIndentDetails = new ArrayList<PurchaseIndentApproverDetailsLine>();
        }
        return p;
    }

    public static PurchaseIndentApprovalModel loadinstanceModelInitiater1(AppView app, String purIndentId) throws BasicException {
        PurchaseIndentApprovalModel p = new PurchaseIndentApprovalModel();
        List<PurchaseIndentApproverDetailsLine> puDetLine;
        puDetLine = new StaticSentence(app.getSession(), "SELECT ID,PUID,NAME,INTQTY,APPQTY,INTRATE,APPRATE,VNAME,REMARK,PRID,FNAME,FDATE,ANAME,ADATE,FLAG FROM"+
                    "(SELECT P.ID AS ID ,P.PURCHASEINDENTID AS PUID,P1.NAME AS NAME ,P.INTQTY AS INTQTY,P.APPQTY AS APPQTY ,P.INTRATE AS INTRATE,P.APPRATE AS APPRATE ,V.NAME AS VNAME ,P.REMARKS AS REMARK,P.PRODUCTID AS PRID,PE1.NAME AS FNAME,P.FORWARDEDDATE AS FDATE ,PE2.NAME AS ANAME,P.APPROVEDDATE AS ADATE,P.ORDEREDFLAG AS FLAG  FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1,PEOPLE PE2 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID AND PE1.ID=P.FORWARDEDBY AND PE2.ID=P.APPROVEDBY AND P.ORDEREDFLAG IS NULL "+
                    " UNION ALL "+
                    "SELECT P.ID AS ID,P.PURCHASEINDENTID AS PUID,P1.NAME AS NAME,P.INTQTY AS INTQTY,P.APPQTY AS APPQTY ,P.INTRATE AS INTRATE,P.APPRATE AS APPRATE,V.NAME AS VNAME,P.REMARKS AS REMARK,P.PRODUCTID AS PRID,PE1.NAME AS FNAME,P.FORWARDEDDATE AS FDATE ,PE2.NAME AS ANAME,P.APPROVEDDATE AS ADATE ,P.ORDEREDFLAG AS FLAG FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1,PEOPLE PE2 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID AND PE1.ID=P.FORWARDEDBY AND PE2.ID=P.APPROVEDBY AND P.ORDEREDFLAG IS NOT NULL "+
                    " UNION ALL "+
                    "SELECT P.ID AS ID,P.PURCHASEINDENTID AS PUID,P1.NAME AS NAME,P.INTQTY AS INTQTY,P.APPQTY AS APPQTY ,P.INTRATE AS INTRATE,P.APPRATE AS APPRATE,V.NAME AS VNAME,P.REMARKS AS REMARK,P.PRODUCTID AS PRID,PE1.NAME AS FNAME,P.FORWARDEDDATE AS FDATE ,NULL AS ANAME,P.APPROVEDDATE AS ADATE ,P.ORDEREDFLAG AS FLAG FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID AND PE1.ID=P.FORWARDEDBY AND P.ORDEREDFLAG IS NOT NULL "+
                    " UNION ALL "+
                    "SELECT P.ID AS ID,P.PURCHASEINDENTID AS PUID,P1.NAME AS NAME,P.INTQTY AS INTQTY,P.APPQTY AS APPQTY ,P.INTRATE AS INTRATE,P.APPRATE AS APPRATE,V.NAME AS VNAME,P.REMARKS AS REMARK,P.PRODUCTID AS PRID,PE1.NAME AS FNAME,P.FORWARDEDDATE AS FDATE ,NULL AS ANAME,P.APPROVEDDATE AS ADATE ,P.ORDEREDFLAG AS FLAG FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID  AND PE1.ID=P.FORWARDEDBY AND P.ORDEREDFLAG IS NULL" +
                    " UNION ALL " +
                    "SELECT P.ID AS ID,P.PURCHASEINDENTID AS PUID,P1.NAME AS NAME,P.INTQTY AS INTQTY,P.APPQTY AS APPQTY ,P.INTRATE AS INTRATE,P.APPRATE AS APPRATE,V.NAME AS VNAME,P.REMARKS AS REMARK,P.PRODUCTID AS PRID,NULL AS FNAME,P.FORWARDEDDATE AS FDATE ,NULL AS ANAME,P.APPROVEDDATE AS ADATE ,P.ORDEREDFLAG AS FLAG FROM PURCHASEINDENTDETAILS P,PRODUCTS P1,VENDOR V,PEOPLE PE1 WHERE P.PURCHASEINDENTID=? AND P.PRODUCTID=P1.ID AND P.APPVENDOR=V.ID  AND P.ORDEREDFLAG IS NULL ) AS PURCHASEINDENT  GROUP BY 1",
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING,Datas.STRING}), new SerializerReadClass(PurchaseIndentApprovalModel.PurchaseIndentApproverDetailsLine.class)).list(new Object[]{purIndentId,purIndentId,purIndentId,purIndentId,purIndentId});
        if (puDetLine != null) {
            p.purIndentDetails = puDetLine;
        } else {
            p.purIndentDetails = new ArrayList<PurchaseIndentApproverDetailsLine>();
        }
        return p;
    }
    

    public List<PurchaseIndentApproverLine> getPurchaseIndentApproverList() {
        return purIndent;
    }

    public List<PurchaseIndentApproverDetailsLine> getPurcheApproverDetailsList() {
        return purIndentDetails;
    }   

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public AbstractTableModel getPurchaseIndentApprover() {
        return new AbstractTableModel() {

            public int getRowCount() {
                return purIndent.size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS.length;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.util.Date.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS[column]);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentApproverLine p = purIndent.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.getSeqNo();
                    case 1:
                        return p.getCrDate();
                    case 2:
                        return p.getCreatedBy();
                    case 3:
                        return p.getRemark();
                    case 4:
                        return p.getId();
                }
                return p;
            }
        };
    }
    
    public AbstractTableModel getPurchaseIndentApproverDetails() {
        return new AbstractTableModel() {

            public Class[] getTypes() {
                return types;
            }

            public int getRowCount() {
                return purIndentDetails.size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS2.length;
            }
            
            Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean[]{
                true,false, false, false, false, false, true, false,false,false
            };

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS2[column]);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
//               if(getUser()==2){
                return canEdit[columnIndex];
//                }else{
//                  return canEdit1[columnIndex];
//                }
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        p.setSelect(Boolean.parseBoolean(aValue.toString()));
                        fireTableDataChanged();
                        break;
                    case 3:
                        p.setOrderedQty1(Integer.parseInt(aValue.toString()));
                        String remark = p.getRemark()+" : "+"indent quantity is changed to "+p.getOrderedQty1()+"("+p.getOrderedQty()+")";
                        p.setRemark(remark);
                        fireTableDataChanged();
                        break;
                    case 5:
                        p.setOrderedRate(Double.parseDouble(aValue.toString()));
                        String remark2 = p.getRemark()+" : "+"indent rate is changed to "+p.getOrderedRate()+"("+p.getRate()+")";
                        p.setRemark(remark2);
                        fireTableDataChanged();
                        break;
                    case 6:
                        p.setVendor(aValue.toString());
                        String remark1 = p.getRemark()+" : "+"vendor is changed";
                        p.setRemark(remark1);
                        fireTableDataChanged();
                        break;

                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.isSelect();
                    case 1:
                        return p.getProduct();
                    case 2:
                        return p.getQty();
                    case 3:
                        return p.getOrderedQty1();
                    case 4:
                        return p.getRate();
                    case 5:
                        return p.getOrderedRate();
                    case 6:
                        return p.getVendor();
                    case 7:
                        return p.getRemark();
                    case 8:
                        return p.getForwardBy();
                    case 9:
                        return p.getForwordedDate();
                }
                return p;
            }
        };
    }

    public AbstractTableModel getPurchaseIndentForderDetails() {
        return new AbstractTableModel() {

            public Class[] getTypes() {
                return types;
            }

            public int getRowCount() {
                return purIndentDetails.size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS1.length;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            
            boolean[] canEdit = new boolean[]{
                true,false, false, false, false, false, false, false
            };           

            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS1[column]);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {               
                return canEdit[columnIndex];
               
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        p.setSelect(Boolean.parseBoolean(aValue.toString()));
                        fireTableDataChanged();
                        break;
                    case 3:
                        p.setOrderedQty1(Integer.parseInt(aValue.toString()));
                        String remark = p.getRemark()+" : "+"order quantity is changed to "+p.getOrderedQty1()+"("+p.getOrderedQty()+")";
                        p.setRemark(remark);
                        fireTableDataChanged();
                        break;
                    case 6:
                        p.setVendor(aValue.toString());
                        String remark1 = p.getRemark()+" : "+"vendor is changed";
                        p.setRemark(remark1);
                        fireTableDataChanged();
                        break;

                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.isSelect();
                    case 1:
                        return p.getProduct();
                    case 2:
                        return p.getQty();
                    case 3:
                        return p.getOrderedQty1();
                    case 4:
                        return p.getRate();
                    case 5:
                        return p.getOrderedRate();
                    case 6:
                        return p.getVendor();
                    case 7:
                        return p.getRemark();                    
                }
                return p;
            }
        };
    }

    public AbstractTableModel getPurchaseIndentInitiaterDetails() {
        return new AbstractTableModel() {

            public Class[] getTypes() {
                return types;
            }

            public int getRowCount() {
                return purIndentDetails.size();
            }

            public int getColumnCount() {
                return COLUMNHEADERS3.length;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            
            boolean[] canEdit = new boolean[]{
                true,false, false, false, false, false, false, false, false, false, false, false
            };

           
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(COLUMNHEADERS3[column]);
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {              
                return canEdit[columnIndex];
                
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        p.setSelect(Boolean.parseBoolean(aValue.toString()));
                        fireTableDataChanged();
                        break;
                    case 3:
                        p.setOrderedQty1(Integer.parseInt(aValue.toString()));
                        String remark = p.getRemark()+" : "+"order quantity is changed to "+p.getOrderedQty1()+"("+p.getOrderedQty()+")";
                        p.setRemark(remark);
                        fireTableDataChanged();
                        break;
                    case 6:
                        p.setVendor(aValue.toString());
                        String remark1 = p.getRemark()+" : "+"vendor is changed";
                        p.setRemark(remark1);
                        fireTableDataChanged();
                        break;

                }
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                PurchaseIndentApproverDetailsLine p = purIndentDetails.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return p.isSelect();
                    case 1:
                        return p.getProduct();
                    case 2:
                        return p.getQty();
                    case 3:
                        return p.getOrderedQty1();
                    case 4:
                        return p.getRate();
                    case 5:
                        return p.getOrderedRate();
                    case 6:
                        return p.getVendor();
                    case 7:
                        return p.getRemark();
                    case 8:
                        return p.getForwardBy();
                    case 9:
                        return p.getForwordedDate();
                    case 10:
                        return p.getApprovedBy();
                    case 11:
                        return p.getApprovedDate();
                }
                return p;
            }
        };
    }

    public static class PurchaseIndentApproverLine implements SerializableRead {

        private String id;
        private int seqNo;
        private Date crDate;
        private String createdBy;
        private String remark;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            seqNo = dr.getInt(2);
            crDate = dr.getTimestamp(3);
            createdBy = dr.getString(4);
            remark = dr.getString(5);

        }

        public Date getCrDate() {
            return crDate;
        }

        public void setCrDate(Date crDate) {
            this.crDate = crDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String forwardBy) {
            this.remark = forwardBy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(int seqNo) {
            this.seqNo = seqNo;
        }
    }

    public static class PurchaseIndentApproverDetailsLine implements SerializableRead {

        private String id;
        private String purchaseIndentId;
        private String product;
        private int qty;
        private int orderedQty;
        private Double rate;
        private Double orderedRate;
        private String vendor;
        private String remark;
        private String productId;
        private int orderedQty1;
        private boolean select;
        private String forwardBy;
        private Date forwordedDate;
        private String approvedBy;      
        private Date approvedDate;
        private boolean orderedFlag;

        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            purchaseIndentId = dr.getString(2);
            product = dr.getString(3);
            qty = dr.getInt(4);
            orderedQty = dr.getInt(5);
            rate = dr.getDouble(6);
            orderedRate = dr.getDouble(7);
            vendor = dr.getString(8);
            remark = dr.getString(9);
            productId = dr.getString(10);
            forwardBy = dr.getString(11);
            forwordedDate = dr.getTimestamp(12);
            approvedBy = dr.getString(13);
            approvedDate = dr.getTimestamp(14);
            if(dr.getBoolean(15)!=null){
            orderedFlag = dr.getBoolean(15);
            }
            orderedQty1 = orderedQty;
            select = false;

        }

        public Date getForwordedDate() {
            return forwordedDate;
        }

        public void setForwordedDate(Date forwordedDate) {
            this.forwordedDate = forwordedDate;
        }
        

        public boolean isOrderedFlag() {
            return orderedFlag;
        }

        public void setOrderedFlag(boolean orderedFlag) {
            this.orderedFlag = orderedFlag;
        }

        public Date getApprovedDate() {
            return approvedDate;
        }

        public void setApprovedDate(Date approvedDate) {
            this.approvedDate = approvedDate;
        }

        public String getApprovedBy() {
            return approvedBy;
        }

        public void setApprovedBy(String approvedBy) {
            this.approvedBy = approvedBy;
        }

        public String getForwardBy() {
            return forwardBy;
        }

        public void setForwardBy(String forwardBy) {
            this.forwardBy = forwardBy;
        }
        
        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public int getOrderedQty1() {
            return orderedQty1;
        }

        public void setOrderedQty1(int orderedQty1) {
            this.orderedQty1 = orderedQty1;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getOrderedQty() {
            return orderedQty;
        }

        public void setOrderedQty(int orderedQty) {
            this.orderedQty = orderedQty;
        }

        public Double getOrderedRate() {
            return orderedRate;
        }

        public void setOrderedRate(Double orderedRate) {
            this.orderedRate = orderedRate;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getPurchaseIndentId() {
            return purchaseIndentId;
        }

        public void setPurchaseIndentId(String purchaseIndentId) {
            this.purchaseIndentId = purchaseIndentId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }
    }
}

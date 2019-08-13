
package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SequenceForMySQL;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;

/**
 *
 * @author adrianromero
 */
public class DataLogicSalesMySQL extends DataLogicSales {
    
    /** Creates a new instance of SentenceContainerMySQL */
    public DataLogicSalesMySQL() {
    }   
    //praveen:added two fields
    //sameer:selecting data from products table along with 4 newly added columns
    public final SentenceList getProductCatQBF() {
        return new StaticSentence(s
            , new QBFBuilder(
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.CATEGORY, P.TAXCAT, P.IMAGE, P.HSN_Code, P.STOCKVOLUME, NOT (C.PRODUCT IS NULL), C.CATORDER, P.ATTRIBUTES, P.PRCATEGORY,P.UNITTYPE,P.PACCOUNT,P.SACCOUNT,null,P.LOCATION,P.INVENTRYMAINTAIN,P.MAXSTOCKLEVEL,P.MINSTOCKLEVEL,P.REORDERLEVEL,P.REORDERQUANTITY , P.TAXCAT2 , P.TAXCAT3 , P.BASIC2 , P.BASIC3  " +
                "FROM PRODUCTS P LEFT OUTER JOIN PRODUCTS_CAT C ON P.ID = C.PRODUCT " +
                "WHERE ?(QBF_FILTER) " +
                "ORDER BY P.REFERENCE", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"},1)
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING })
            , new SerializerReadBasic(productcatDatas));
    }    

    public final Integer getNextTicketIndex() throws BasicException {
        return (Integer) new SequenceForMySQL(s, "TICKETSNUM", SerializerReadInteger.INSTANCE).find();
    }

    @Override
    public Integer getNextQTicketIndex() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getNextBillNumberIndex() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getNextReceiptNumberIndex() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    //praveen:added below statements
    public Integer getNextTransNumberIndex() throws BasicException {
        int seqNumber =  (Integer) new StaticSentence(s, "SELECT MAX_VALUE FROM BILLSEQUENCE WHERE NAME=?", SerializerWriteString.INSTANCE, SerializerReadInteger.INSTANCE).find("collectiontransfer_seq");
         new StaticSentence(s, "UPDATE BILLSEQUENCE SET MAX_VALUE=MAX_VALUE+1 WHERE NAME=?", SerializerWriteString.INSTANCE, null).exec("collectiontransfer_seq");
        return seqNumber;
    }
}
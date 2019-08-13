

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFBuilder;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadInteger;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;

/**
 *
 * @author adrianromero
 */
public class DataLogicSalesPostgreSQL extends DataLogicSales {
    
    /** Creates a new instance of SentenceContainerPostgreSQL */
    public DataLogicSalesPostgreSQL() {
    }
    
    public final SentenceList getProductCatQBF() {
        return new StaticSentence(s
            , new QBFBuilder(
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.CATEGORY, P.TAXCAT, P.IMAGE, P.HSN_Code, P.STOCKVOLUME, NOT (C.PRODUCT IS NULL), C.CATORDER, P.ATTRIBUTES " +
                "FROM PRODUCTS P LEFT OUTER JOIN PRODUCTS_CAT C ON P.ID = C.PRODUCT " +
                "WHERE ?(QBF_FILTER) " +
                "ORDER BY P.REFERENCE", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadBasic(productcatDatas));
    }    

    public final Integer getNextTicketIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "SELECT NEXTVAL('TICKETSNUM')", null, SerializerReadInteger.INSTANCE).find();
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
    public Integer getNextTransNumberIndex() throws BasicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
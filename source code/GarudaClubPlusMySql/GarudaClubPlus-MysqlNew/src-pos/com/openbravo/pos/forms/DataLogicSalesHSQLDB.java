

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
public class DataLogicSalesHSQLDB extends DataLogicSales {
    
    /** Creates a new instance of SentenceContainerHSQLDB */
    public DataLogicSalesHSQLDB() {
    }

    public final SentenceList getProductCatQBF() {
        return new StaticSentence(s
            , new QBFBuilder(
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.CATEGORY, P.TAXCAT, P.IMAGE, P.HSN_Code, P.STOCKVOLUME, NOT (C.PRODUCT IS NULL), C.CATORDER, P.ATTRIBUTES, P.PRCATEGORY,P.UNITTYPE,P.PACCOUNT,P.SACCOUNT,null " +
                "FROM PRODUCTS P LEFT OUTER JOIN PRODUCTS_CAT C ON P.ID = C.PRODUCT " +
                "WHERE ?(QBF_FILTER) " +
                "ORDER BY P.REFERENCE", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"},1)
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadBasic(productcatDatas));
    }    

    public final Integer getNextTicketIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "CALL NEXT VALUE FOR TICKETSNUM", null, SerializerReadInteger.INSTANCE).find();
    }

    @Override
    public final Integer getNextQTicketIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "CALL NEXT VALUE FOR QTICKETNUM", null, SerializerReadInteger.INSTANCE).find();
    }

    @Override
    public final Integer getNextBillNumberIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "CALL NEXT VALUE FOR BILLNUM", null, SerializerReadInteger.INSTANCE).find();
    }

    @Override
    public final Integer getNextReceiptNumberIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "CALL NEXT VALUE FOR RECEIPTSNUM", null, SerializerReadInteger.INSTANCE).find();
    }
     @Override
    public final Integer getNextTransNumberIndex() throws BasicException {
        return (Integer) new StaticSentence(s, "CALL NEXT VALUE FOR NUM", null, SerializerReadInteger.INSTANCE).find();
    }

}

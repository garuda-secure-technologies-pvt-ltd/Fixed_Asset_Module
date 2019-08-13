

package com.openbravo.pos.forms;

import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;

/**
 *
 * @author adrianromero
 */
public class DataLogicSystemHSQLDB extends DataLogicSystem {
    
    /** Creates a new instance of DataLogicSystemHSQLDB */
    public DataLogicSystemHSQLDB() {
    }
    
    public void init(Session s) {
        super.init(s);
        
        m_sInitScript = "/com/openbravo/pos/scripts/hsqldb";

        m_peoplevisible = new StaticSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE, LOGINTIME, OPENCASHTIME, CLOSECASHTIME, OPENSALE, CLOSESALE,CASHACCOUNT,CHEQUEACCOUNT,PRCATEGORIES FROM PEOPLE WHERE VISIBLE = TRUE ORDER BY NAME "
            , null
            , peopleread);   
        
        m_peoplebycard = new PreparedSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE, LOGINTIME, OPENCASHTIME, CLOSECASHTIME,CASHACCOUNT,CHEQUEACCOUNT FROM PEOPLE WHERE CARD = ? AND VISIBLE = TRUE"
            , SerializerWriteString.INSTANCE
            , peopleread); 
    }
    
}

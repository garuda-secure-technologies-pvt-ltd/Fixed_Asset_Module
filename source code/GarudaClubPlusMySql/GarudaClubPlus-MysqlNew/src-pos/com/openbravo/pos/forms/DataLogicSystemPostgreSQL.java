

package com.openbravo.pos.forms;

import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;

/**
 *
 * @author adrianromero
 */
public class DataLogicSystemPostgreSQL extends DataLogicSystem {
    
    /** Creates a new instance of DataLogicSystemPostgre */
    public DataLogicSystemPostgreSQL() {
    }
    
    public void init(Session s) {
        super.init(s);
        
        m_sInitScript = "/com/openbravo/pos/scripts/postgresql";
 
        m_peoplevisible = new StaticSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE FROM PEOPLE WHERE VISIBLE = TRUE"
            , null
            , peopleread);  
        
        m_peoplebycard = new PreparedSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE FROM PEOPLE WHERE CARD = ? AND VISIBLE = TRUE"
            , SerializerWriteString.INSTANCE
            , peopleread);         
    }    
}

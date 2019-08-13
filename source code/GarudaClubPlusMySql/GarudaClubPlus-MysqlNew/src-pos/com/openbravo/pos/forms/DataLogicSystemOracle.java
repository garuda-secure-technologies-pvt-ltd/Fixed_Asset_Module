

package com.openbravo.pos.forms;

import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;

/**
 *
 * @author adrianromero
 */
public class DataLogicSystemOracle extends DataLogicSystem {
    
    /** Creates a new instance of DataLogicSystemOracle */
    public DataLogicSystemOracle() {    
    }
    
    public void init(Session s) {
        super.init(s);
        
        m_sInitScript = "/com/openbravo/pos/scripts/oracle";
 
        m_peoplevisible = new StaticSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE FROM PEOPLE WHERE VISIBLE = 1"
            , null
            , peopleread);
        
        m_peoplebycard = new PreparedSentence(s
            , "SELECT ID, NAME, APPPASSWORD, CARD, ROLE, IMAGE FROM PEOPLE WHERE CARD = ? AND VISIBLE = 1"
            , SerializerWriteString.INSTANCE
            , peopleread);         
    }  
}


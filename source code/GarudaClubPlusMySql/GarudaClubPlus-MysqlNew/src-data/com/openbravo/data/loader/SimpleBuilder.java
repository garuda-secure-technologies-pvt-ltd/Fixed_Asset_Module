

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author  adrian
 */
public class SimpleBuilder implements ISQLBuilderStatic {
    
    private String m_sSentence;
    
    /** Creates a new instance of SimpleBuilder */
    public SimpleBuilder(String sSentence) {
        m_sSentence = sSentence;
    }
    
    public String getSQL(SerializerWrite sw, Object params) throws BasicException {
        return m_sSentence;
    }
    
}

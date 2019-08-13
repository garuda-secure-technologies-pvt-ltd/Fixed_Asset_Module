

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

public abstract class SentenceExecAdapter implements SentenceExec {
    
    public int exec() throws BasicException {
        return exec(null);
    }
    public abstract int exec(Object params) throws BasicException;    
}

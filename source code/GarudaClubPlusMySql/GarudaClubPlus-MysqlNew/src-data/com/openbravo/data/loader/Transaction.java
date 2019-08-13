

package com.openbravo.data.loader;

import java.sql.SQLException;
import com.openbravo.basic.BasicException;

/**
 *
 * @author adrianromero
 * Created on 26 de febrero de 2007, 21:50
 *
 */
public abstract class Transaction<T> {
    
    private Session s;
    
    /** Creates a new instance of Transaction */
    public Transaction(Session s) {
        this.s = s;
    }
    
    public final T execute() throws BasicException {
        
        if (s.isTransaction()) {
            return transact();
        } else {
            try {
                try {    


                    s.begin();
                    T result = transact();
                    s.commit();
                    return result;
                } catch (Exception e) {
                    s.rollback();
                   // if(e instanceof BasicException)
                   // throw (BasicException)e;
                  //  else
                   //     throw new BasicException();
                    throw e;
                }
            } catch (Exception eSQL) {
                throw new BasicException("Transaction error", eSQL);
            }
        }
    }
    
    protected abstract T transact() throws BasicException;
}

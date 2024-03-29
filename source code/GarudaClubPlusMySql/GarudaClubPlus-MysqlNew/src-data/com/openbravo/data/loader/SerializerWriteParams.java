

package com.openbravo.data.loader;

import com.openbravo.basic.BasicException;

/**
 *
 * @author adrianromero
 */
public class SerializerWriteParams  implements SerializerWrite<DataParams>{

   public static final SerializerWrite INSTANCE = new SerializerWriteParams();
    
    public void writeValues(DataWrite dp, DataParams obj) throws BasicException {
        obj.setDataWrite(dp);
        obj.writeValues();
        obj.setDataWrite(null);
    }     
}

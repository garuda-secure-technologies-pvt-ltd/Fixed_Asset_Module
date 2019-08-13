

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;

/**
 *
 * @author adrianromero
 */
public interface ProcessAction {
   
    public MessageInf execute() throws BasicException;
}

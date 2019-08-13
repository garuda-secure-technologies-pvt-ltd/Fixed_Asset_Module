
package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;

/**
 *
 * @author adrianromero
 */
public interface ReportEditorCreator extends EditorCreator {
    
    public void init(AppView app);
    public void activate() throws BasicException;
    public SerializerWrite getSerializerWrite();
    public Component getComponent();
}

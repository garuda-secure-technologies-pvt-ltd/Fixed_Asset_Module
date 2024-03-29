

package com.openbravo.editor;

import com.openbravo.format.Formats;

public class JEditorCurrency extends JEditorNumber {
    
	private static final long serialVersionUID = 5096754100573262803L;
	
	/** Creates a new instance of JEditorCurrency */
    public JEditorCurrency() {
    }
    
    protected Formats getFormat() {
        return Formats.CURRENCY;
    }
    protected int getMode() {
        return EditorKeys.MODE_DOUBLE;
    }  
}

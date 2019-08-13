
package com.openbravo.editor;

/**
 *
 * @author adrian
 */
public class JEditorStringNumber extends JEditorText {
    
    /** Creates a new instance of JEditorStringNumber */
    public JEditorStringNumber() {
        super();
    }
    
    protected int getMode() {
        return EditorKeys.MODE_INTEGER_POSITIVE;
    } 
        
    protected int getStartMode() {
        return MODE_123;
    }    
}

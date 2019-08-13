/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.editor;

/**
 *
 * @author swathi
 */
public class JEditorString1 extends jEditorText1 {

    /** Creates a new instance of JEditorString */
    public JEditorString1() {
        super();
    }

    protected final int getMode() {
        return EditorKeys.MODE_STRING;
    }

    protected int getStartMode() {
        return MODE_Abc1;
    }

}
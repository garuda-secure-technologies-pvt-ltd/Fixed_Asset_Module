
package com.openbravo.data.gui;

import javax.swing.*;
import com.openbravo.format.Formats;
import com.openbravo.data.user.BrowseListener;
import com.openbravo.data.user.BrowsableEditableData;
import com.openbravo.data.user.StateListener;

/**
 *
 * @author  adrianromero
 */
public class JCounter extends JPanel implements BrowseListener, StateListener  {
    
    /** Creates new form JCounter */
    public JCounter(BrowsableEditableData bd) {
        initComponents();
        bd.addBrowseListener(this);
        bd.addStateListener(this);
    }
    
    public void updateState(int iState) {
        if (iState == BrowsableEditableData.ST_INSERT) {
             // Insert
            jlblIndex.setText("*");
        }
    }  

    public void updateIndex(int iIndex, int iCounter) {

        if (iIndex >= 0 && iIndex < iCounter) {
            jlblIndex.setText(Formats.INT.formatValue(new Integer(iIndex + 1)));
        } else {
            jlblIndex.setText("-");
        }
        jlblCounter.setText(Formats.INT.formatValue(new Integer(iCounter)));
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblIndex = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblCounter = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(80, 20));
        setMinimumSize(new java.awt.Dimension(80, 20));
        setPreferredSize(new java.awt.Dimension(80, 20));

        jlblIndex.setText("XX");
        add(jlblIndex);

        jLabel2.setText("/");
        add(jLabel2);

        jlblCounter.setText("XX");
        add(jlblCounter);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jlblCounter;
    private javax.swing.JLabel jlblIndex;
    // End of variables declaration//GEN-END:variables
    
}
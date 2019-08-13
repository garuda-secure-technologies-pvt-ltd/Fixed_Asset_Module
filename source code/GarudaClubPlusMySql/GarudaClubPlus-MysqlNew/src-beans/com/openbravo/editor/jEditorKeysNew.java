/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jEditorKeysNew.java
 *
 * Created on Jul 7, 2009, 4:30:03 PM
 */

package com.openbravo.editor;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

/**
 *
 * @author swathi
 */
public class jEditorKeysNew extends javax.swing.JPanel {
    private final static char[] SET_DOUBLE = {'\u007f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-'};
    private final static char[] SET_DOUBLE_POSITIVE = {'\u007f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
    private final static char[] SET_INTEGER = {'\u007f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
    private final static char[] SET_INTEGER_POSITIVE = {'\u007f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    protected EventListenerList listeners = new EventListenerList();

    private EditorComponent editorcurrent ;
    private static boolean capson;
    private boolean shiftpressed;
    
    private char[] keysavailable;
    private boolean m_bMinus;
    private boolean m_bKeyDot;

    /** Creates new form JEditorKeys */
    public jEditorKeysNew() {
        initComponents();
        capson=false;
     /*   m_jKey0.addActionListener(new MyKeyNumberListener('0'));
        m_jKey1.addActionListener(new MyKeyNumberListener('1'));
        m_jKey2.addActionListener(new MyKeyNumberListener('2'));
        m_jKey3.addActionListener(new MyKeyNumberListener('3'));
        m_jKey4.addActionListener(new MyKeyNumberListener('4'));
        m_jKey5.addActionListener(new MyKeyNumberListener('5'));
        m_jKey6.addActionListener(new MyKeyNumberListener('6'));
        m_jKey7.addActionListener(new MyKeyNumberListener('7'));
        m_jKey8.addActionListener(new MyKeyNumberListener('8'));
        m_jKey9.addActionListener(new MyKeyNumberListener('9'));
        m_jKeyDot.addActionListener(new MyKeyNumberListener('.'));
        m_jCE.addActionListener(new MyKeyNumberListener('\u007f'));
        m_jMinus.addActionListener(new MyKeyNumberListener('-'));     */
//      m_jBack.addActionListener(new MyKeyNumberListener('\u0008'));
//      m_jMode.addActionListener(new MyKeyNumberListener('\u0010'));

      //  editorcurrent = null;
      //  setMode(MODE_STRING);
        doEnabled(false);
    }
    public JComponent getComponent(){
       return this;
    }
   // @Override
    public void setComponentOrientation(ComponentOrientation o) {
        // Nothing to change
    }

    public void addActionListener(ActionListener l) {
        listeners.add(ActionListener.class, l);
    }
    public void removeActionListener(ActionListener l) {
        listeners.remove(ActionListener.class, l);
    }

    protected void fireActionPerformed() {
        EventListener[] l = listeners.getListeners(ActionListener.class);
        ActionEvent e = null;
        for (int i = 0; i < l.length; i++) {
            if (e == null) {
                e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
            }
            ((ActionListener) l[i]).actionPerformed(e);
        }
    }

    private void doEnabled(boolean b) {
       /* m_jKey0.setEnabled(b);
        m_jKey1.setEnabled(b);
        m_jKey2.setEnabled(b);
        m_jKey3.setEnabled(b);
        m_jKey4.setEnabled(b);
        m_jKey5.setEnabled(b);
        m_jKey6.setEnabled(b);
        m_jKey7.setEnabled(b);
        m_jKey8.setEnabled(b);
        m_jKey9.setEnabled(b);
        m_jKeyDot.setEnabled(b && m_bKeyDot);
        m_jCE.setEnabled(b);
        m_jMinus.setEnabled(b && m_bMinus);*/
    }

/*    public void setMode(int iMode) {
        switch (iMode) {
            case MODE_DOUBLE:
                m_bMinus = true;
                m_bKeyDot = true;
                keysavailable = SET_DOUBLE;
                break;
            case MODE_DOUBLE_POSITIVE:
                m_bMinus = false;
                m_bKeyDot = true;
                keysavailable = SET_DOUBLE_POSITIVE;
                break;
            case MODE_INTEGER:
                m_bMinus = true;
                m_bKeyDot = false;
                keysavailable = SET_INTEGER;
                break;
            case MODE_INTEGER_POSITIVE:
                m_bMinus = false;
                m_bKeyDot = false;
                keysavailable = SET_INTEGER_POSITIVE;
                break;
            case MODE_STRING:
            default:
                m_bMinus = true;
                m_bKeyDot = true;
                keysavailable = null;
                break;
        }
    }*/

    private class MyKeyNumberListener implements java.awt.event.ActionListener {

        private char m_cCad;

        public MyKeyNumberListener(char cCad){
            m_cCad = cCad;
        }
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            // como contenedor de editores
            if (editorcurrent != null) {
                editorcurrent.transChar(m_cCad);
            }
        }
    }

    public void setActive(EditorComponent e, int iMode) {

        if (editorcurrent != null) {
            editorcurrent.deactivate();
        }
        editorcurrent = e;  // e != null
       // setMode(iMode);
        doEnabled(true);

        // keyboard listener activation
      //  m_txtKeys.setText(null);
      //  java.awt.EventQueue.invokeLater(new Runnable() {
     //       public void run() {
     //           m_txtKeys.requestFocus();
     //       }
     //   });
    }

    public void setInactive(EditorComponent e) {

        if (e == editorcurrent && editorcurrent != null) { // e != null
            editorcurrent.deactivate();
            editorcurrent = null;
           // setMode(MODE_STRING);
            doEnabled(false);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        d1 = new javax.swing.JButton();
        d2 = new javax.swing.JButton();
        d3 = new javax.swing.JButton();
        d4 = new javax.swing.JButton();
        d5 = new javax.swing.JButton();
        d6 = new javax.swing.JButton();
        d7 = new javax.swing.JButton();
        d8 = new javax.swing.JButton();
        d9 = new javax.swing.JButton();
        equals = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Q = new javax.swing.JButton();
        W = new javax.swing.JButton();
        E = new javax.swing.JButton();
        R = new javax.swing.JButton();
        T = new javax.swing.JButton();
        Y = new javax.swing.JButton();
        U = new javax.swing.JButton();
        I = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        square_open_brachet = new javax.swing.JButton();
        comma = new javax.swing.JButton();
        A = new javax.swing.JButton();
        S = new javax.swing.JButton();
        D = new javax.swing.JButton();
        F = new javax.swing.JButton();
        G = new javax.swing.JButton();
        H = new javax.swing.JButton();
        J = new javax.swing.JButton();
        K = new javax.swing.JButton();
        single_quote = new javax.swing.JButton();
        Z = new javax.swing.JButton();
        X = new javax.swing.JButton();
        C = new javax.swing.JButton();
        V = new javax.swing.JButton();
        B = new javax.swing.JButton();
        N = new javax.swing.JButton();
        enter = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        d10 = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        tab = new javax.swing.JButton();
        P1 = new javax.swing.JButton();
        square_open_brachet1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        caps = new javax.swing.JButton();
        L1 = new javax.swing.JButton();
        semicolon = new javax.swing.JButton();
        M1 = new javax.swing.JButton();
        comma1 = new javax.swing.JButton();
        comma2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        comma3 = new javax.swing.JButton();
        comma4 = new javax.swing.JButton();
        comma5 = new javax.swing.JButton();
        comma6 = new javax.swing.JButton();
        comma7 = new javax.swing.JButton();
        comma8 = new javax.swing.JButton();
        comma9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        d1.setText("<html>!<br>"+
            "1</html>" );
        add(d1);
        d1.setBounds(40, 0, 40, 40);

        d2.setText("<html>@<br>" +
            "2</html>" );
        add(d2);
        d2.setBounds(80, 0, 40, 40);

        d3.setText("<html>#<br>" +
            "3</html>" );
        add(d3);
        d3.setBounds(120, 0, 40, 40);

        d4.setText("<html>$<br>" +
            "4</html>" );
        add(d4);
        d4.setBounds(160, 0, 40, 40);

        d5.setText("<html>%<br>" +
            "5</html>" );
        add(d5);
        d5.setBounds(200, 0, 40, 40);

        d6.setText("<html>^<br>"+
            "6</html>");
        add(d6);
        d6.setBounds(240, 0, 40, 40);

        d7.setText("<html>&<br>" +
            "7</html>" );
        add(d7);
        d7.setBounds(280, 0, 40, 40);

        d8.setText("<html>*<br>" +
            "8</html>" );
        add(d8);
        d8.setBounds(320, 0, 40, 40);

        d9.setText("<html>(<br>" +
            "9</html>" );
        add(d9);
        d9.setBounds(360, 0, 40, 40);

        equals.setText("<html>+<br>" +
            "=</html>" );
        add(equals);
        equals.setBounds(480, 0, 40, 40);

        jButton1.setText("BackSpace");
        add(jButton1);
        jButton1.setBounds(520, 0, 90, 40);

        Q.setText("Q");
        add(Q);
        Q.setBounds(50, 40, 40, 40);

        W.setText("W");
        add(W);
        W.setBounds(90, 40, 43, 40);

        E.setText("E");
        add(E);
        E.setBounds(130, 40, 40, 40);

        R.setText("R");
        add(R);
        R.setBounds(170, 40, 40, 40);

        T.setText("T");
        add(T);
        T.setBounds(210, 40, 39, 40);

        Y.setText("Y");
        add(Y);
        Y.setBounds(250, 40, 40, 40);

        U.setText("U");
        add(U);
        U.setBounds(290, 40, 40, 40);

        I.setText("I");
        add(I);
        I.setBounds(330, 40, 40, 40);

        jButton2.setText("O");
        add(jButton2);
        jButton2.setBounds(370, 40, 40, 40);

        square_open_brachet.setText("<html>}<br>" +
            "]</html>" );
        add(square_open_brachet);
        square_open_brachet.setBounds(490, 40, 40, 40);

        comma.setText("<html>?<br>" +
            "/</html>" );
        add(comma);
        comma.setBounds(440, 120, 40, 40);

        A.setText("A");
        add(A);
        A.setBounds(60, 80, 40, 40);

        S.setText("S");
        add(S);
        S.setBounds(100, 80, 40, 40);

        D.setText("D");
        add(D);
        D.setBounds(140, 80, 40, 40);

        F.setText("F");
        add(F);
        F.setBounds(180, 80, 40, 40);

        G.setText("G");
        add(G);
        G.setBounds(220, 80, 40, 40);

        H.setText("H");
        add(H);
        H.setBounds(260, 80, 40, 40);

        J.setText("J");
        add(J);
        J.setBounds(300, 80, 40, 40);

        K.setText("K");
        add(K);
        K.setBounds(340, 80, 40, 40);

        single_quote.setText("<html>\"<br>" +
            "\'</html>" );
        add(single_quote);
        single_quote.setBounds(460, 80, 40, 40);

        Z.setText("Z");
        add(Z);
        Z.setBounds(80, 120, 40, 40);

        X.setText("X");
        add(X);
        X.setBounds(120, 120, 40, 40);

        C.setText("C");
        add(C);
        C.setBounds(160, 120, 40, 40);

        V.setText("V");
        add(V);
        V.setBounds(200, 120, 40, 40);

        B.setText("B");
        add(B);
        B.setBounds(240, 120, 40, 40);

        N.setText("N");
        add(N);
        N.setBounds(280, 120, 40, 40);

        enter.setText("Enter");
        add(enter);
        enter.setBounds(500, 80, 110, 40);

        jButton3.setText("<html>~<br>" +
            "`</html>" );
        add(jButton3);
        jButton3.setBounds(0, 0, 40, 40);

        d10.setText("<html>)<br>" +
            "0</html>" );
        add(d10);
        d10.setBounds(400, 0, 40, 40);

        minus.setText("<html>_<br>" +
            "-</html>" );
        add(minus);
        minus.setBounds(440, 0, 40, 40);

        tab.setText("Tab");
        add(tab);
        tab.setBounds(0, 40, 50, 40);

        P1.setText("P");
        add(P1);
        P1.setBounds(410, 40, 40, 40);

        square_open_brachet1.setText("<html>{<br>" +
            "[</html>" );
        add(square_open_brachet1);
        square_open_brachet1.setBounds(450, 40, 40, 40);

        jButton4.setText("<html>|<br>" +
            "\\</html>" );
        add(jButton4);
        jButton4.setBounds(530, 40, 80, 40);

        caps.setText("Caps Lock");
        add(caps);
        caps.setBounds(0, 80, 60, 40);

        L1.setText("L");
        add(L1);
        L1.setBounds(380, 80, 40, 40);

        semicolon.setText("<html>:<br>" +
            ";</html>" );
        add(semicolon);
        semicolon.setBounds(420, 80, 40, 40);

        M1.setText("M");
        add(M1);
        M1.setBounds(320, 120, 40, 40);

        comma1.setText("<html><<br>" +
            ",</html>" );
        add(comma1);
        comma1.setBounds(360, 120, 40, 40);

        comma2.setText("space bar");
        add(comma2);
        comma2.setBounds(160, 160, 290, 40);

        jButton5.setText("Shift");
        add(jButton5);
        jButton5.setBounds(480, 120, 130, 40);

        jButton6.setText("Shift");
        add(jButton6);
        jButton6.setBounds(0, 120, 80, 40);

        comma3.setText("<html>><br>" +
            ".</html>" );
        add(comma3);
        comma3.setBounds(400, 120, 40, 40);

        comma4.setText("clear");
        add(comma4);
        comma4.setBounds(550, 160, 60, 40);

        comma5.setText("alt");
        add(comma5);
        comma5.setBounds(450, 160, 50, 40);

        comma6.setText("alt");
        add(comma6);
        comma6.setBounds(110, 160, 50, 40);

        comma7.setText("ctrl");
        add(comma7);
        comma7.setBounds(60, 160, 50, 40);

        comma8.setText("ctrl");
        add(comma8);
        comma8.setBounds(500, 160, 50, 40);

        comma9.setText("clear");
        add(comma9);
        comma9.setBounds(0, 160, 60, 40);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Caps lock off");
        add(jLabel1);
        jLabel1.setBounds(500, 200, 110, 14);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A;
    private javax.swing.JButton B;
    private javax.swing.JButton C;
    private javax.swing.JButton D;
    private javax.swing.JButton E;
    private javax.swing.JButton F;
    private javax.swing.JButton G;
    private javax.swing.JButton H;
    private javax.swing.JButton I;
    private javax.swing.JButton J;
    private javax.swing.JButton K;
    private javax.swing.JButton L1;
    private javax.swing.JButton M1;
    private javax.swing.JButton N;
    private javax.swing.JButton P1;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton S;
    private javax.swing.JButton T;
    private javax.swing.JButton U;
    private javax.swing.JButton V;
    private javax.swing.JButton W;
    private javax.swing.JButton X;
    private javax.swing.JButton Y;
    private javax.swing.JButton Z;
    private javax.swing.JButton caps;
    private javax.swing.JButton comma;
    private javax.swing.JButton comma1;
    private javax.swing.JButton comma2;
    private javax.swing.JButton comma3;
    private javax.swing.JButton comma4;
    private javax.swing.JButton comma5;
    private javax.swing.JButton comma6;
    private javax.swing.JButton comma7;
    private javax.swing.JButton comma8;
    private javax.swing.JButton comma9;
    private javax.swing.JButton d1;
    private javax.swing.JButton d10;
    private javax.swing.JButton d2;
    private javax.swing.JButton d3;
    private javax.swing.JButton d4;
    private javax.swing.JButton d5;
    private javax.swing.JButton d6;
    private javax.swing.JButton d7;
    private javax.swing.JButton d8;
    private javax.swing.JButton d9;
    private javax.swing.JButton enter;
    private javax.swing.JButton equals;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton minus;
    private javax.swing.JButton semicolon;
    private javax.swing.JButton single_quote;
    private javax.swing.JButton square_open_brachet;
    private javax.swing.JButton square_open_brachet1;
    private javax.swing.JButton tab;
    // End of variables declaration//GEN-END:variables

}

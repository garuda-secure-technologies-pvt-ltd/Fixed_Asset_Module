/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EsslDisplay.java
 *
 * Created on 13-Oct-2011, 18:42:38
 */

package com.openbravo.pos.ESSLDisplay;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class EsslDisplay extends javax.swing.JFrame {

  
    public EsslDisplay() {
        initComponents();
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(screenSize.width, screenSize.height);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        poweredby = new javax.swing.JLabel();
        m_jLblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Connect");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectAction(evt);
            }
        });

        poweredby.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poweredby.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bci/poweredby.png"))); // NOI18N
        poweredby.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        poweredby.setName("poweredby"); // NOI18N
        poweredby.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        m_jLblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jLblTitle.setText("Window.Title");
        m_jLblTitle.setName("m_jLblTitle"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bci/logo.png"))); // NOI18N
        jLabel1.setText("<html><center>Garuda Club Plus is a Club Management System with integrated POS<br>" +
            "Copyright \u00A9 2007-2012 Garuda Secure Technologies Pvt.Ltd.<br>" +
            "<br>" +
            "<br>" +
            "See the License for more details.<br>" +
            "<br>" +
            "You should have received a copy of the License along with this program; if not, write to the Garuda Secure Technologies Pvt.Ltd, Bangalore, India ." +
            "<br>" +
            "</center>");
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 1024));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(m_jLblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poweredby, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jButton1)
                .addContainerGap(394, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 38, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 38, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poweredby, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(m_jLblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 49, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 50, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectAction
        // TODO add your handling code here:
        
        StartMembersPhoto s=new StartMembersPhoto();
        
        try {
            s.connect();
            
        } catch (IOException ex) {
            //TODO : display error msg to frontend
           ex.printStackTrace();
        } catch (SQLException ex) {
             //TODO : display error msg to frontend
            JOptionPane.showMessageDialog(null, "Error Occured While connecting to database ", "Error", JOptionPane.ERROR_MESSAGE);
           ex.printStackTrace();
        }
        setVisible(false);

    }//GEN-LAST:event_connectAction

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EsslDisplay().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel m_jLblTitle;
    private javax.swing.JLabel poweredby;
    // End of variables declaration//GEN-END:variables

}

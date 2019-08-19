

package com.openbravo.pos.sms;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class GreetingWishesMaster extends javax.swing.JPanel implements JPanelView,BeanFactoryApp{

   private String prefix;
   private AppView m_App;
   private GreetingWishesTableModel BirthdayWishes_Table_Model;
   private GreetingWishesTableModel FestivalWish_Table_Model;
   
   
   
   
    public GreetingWishesMaster() {
        initComponents();
        
        jPanel1.setVisible(true);
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        birtdy_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        prefix_option_panel = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton7 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        wishing_text = new javax.swing.JTextField();
        dependent_name_text = new javax.swing.JTextField();
        auto_option = new javax.swing.JRadioButton();
        manual_option = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        birthdy_optionPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        member_option = new javax.swing.JRadioButton();
        dependent_option = new javax.swing.JRadioButton();
        festival_panel_option1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        festival_name = new javax.swing.JTextField();
        date_text = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        birthdy_option = new javax.swing.JRadioButton();
        festival_option = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        birthdy_Deactive_Button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
            Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
            if (c instanceof JComponent) {
                JComponent jc = (JComponent)c;
                jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));

                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    jc.setBackground(Color.lightGray);
                }
                else {
                    jc.setBackground(Color.white);
                }
                if(isCellSelected(rowIndex, vColIndex))
                jc.setBackground(Color.cyan);
            }
            return c;
        }};
        festival_Deactive_Button = new javax.swing.JButton();

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setForeground(new java.awt.Color(153, 0, 0));

        jLabel1.setText("Message :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("0");

        jLabel2.setText("/125");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        jRadioButton3.setText("Dear Sir");
        jRadioButton3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton3StateChanged(evt);
            }
        });
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });

        jRadioButton4.setText("Dear Mam");
        jRadioButton4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton4StateChanged(evt);
            }
        });
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });

        jRadioButton5.setText("Dear Sir/Mam");
        jRadioButton5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton5StateChanged(evt);
            }
        });
        jRadioButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton5ItemStateChanged(evt);
            }
        });

        jRadioButton6.setText("Dear $Name$");
        jRadioButton6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton6StateChanged(evt);
            }
        });
        jRadioButton6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton6ItemStateChanged(evt);
            }
        });

        jLabel7.setText("Select a Prefix :");

        jRadioButton7.setText("Other Prefix");
        jRadioButton7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton7StateChanged(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel8.setText("Prefix");

        jRadioButton8.setText("No Prefix");
        jRadioButton8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton8StateChanged(evt);
            }
        });
        jRadioButton8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton8ItemStateChanged(evt);
            }
        });

        jRadioButton9.setText("Dear Member");
        jRadioButton9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton9StateChanged(evt);
            }
        });
        jRadioButton9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton9ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout prefix_option_panelLayout = new javax.swing.GroupLayout(prefix_option_panel);
        prefix_option_panel.setLayout(prefix_option_panelLayout);
        prefix_option_panelLayout.setHorizontalGroup(
            prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefix_option_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prefix_option_panelLayout.createSequentialGroup()
                .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, prefix_option_panelLayout.createSequentialGroup()
                        .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton8)
                            .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(jRadioButton4)))))
                .addGap(16, 16, 16))
            .addGroup(prefix_option_panelLayout.createSequentialGroup()
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        prefix_option_panelLayout.setVerticalGroup(
            prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefix_option_panelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton5)
                    .addComponent(jRadioButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton3))
                .addGap(2, 2, 2)
                .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prefix_option_panelLayout.createSequentialGroup()
                        .addComponent(jRadioButton9)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prefix_option_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Activate ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        wishing_text.setText("Wishing ");
        wishing_text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wishing_textKeyPressed(evt);
            }
        });

        dependent_name_text.setText("$(Dependent Name)");

        auto_option.setText("Auto ");

        manual_option.setText("Manual");

        jButton2.setText("Check Message Format ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Prefix :");

        jLabel6.setText("Message Sending Mode : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(manual_option)
                        .addGap(18, 18, 18)
                        .addComponent(auto_option))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wishing_text, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(dependent_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(prefix_option_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wishing_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dependent_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(prefix_option_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(auto_option)
                    .addComponent(manual_option)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        dependent_name_text.setEditable(false);

        jLabel4.setText("Send to  :   ");

        member_option.setText("Only Members ");
        member_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                member_optionItemStateChanged(evt);
            }
        });

        dependent_option.setText("Dependent");
        dependent_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dependent_optionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout birthdy_optionPanel1Layout = new javax.swing.GroupLayout(birthdy_optionPanel1);
        birthdy_optionPanel1.setLayout(birthdy_optionPanel1Layout);
        birthdy_optionPanel1Layout.setHorizontalGroup(
            birthdy_optionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdy_optionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(member_option)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dependent_option)
                .addContainerGap(408, Short.MAX_VALUE))
        );
        birthdy_optionPanel1Layout.setVerticalGroup(
            birthdy_optionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdy_optionPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(birthdy_optionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(member_option)
                    .addComponent(dependent_option)))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Name of festival : ");

        jButton3.setText("Date");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout festival_panel_option1Layout = new javax.swing.GroupLayout(festival_panel_option1);
        festival_panel_option1.setLayout(festival_panel_option1Layout);
        festival_panel_option1Layout.setHorizontalGroup(
            festival_panel_option1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(festival_panel_option1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(festival_name, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(date_text, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        festival_panel_option1Layout.setVerticalGroup(
            festival_panel_option1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(festival_panel_option1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(festival_panel_option1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(festival_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout birtdy_panelLayout = new javax.swing.GroupLayout(birtdy_panel);
        birtdy_panel.setLayout(birtdy_panelLayout);
        birtdy_panelLayout.setHorizontalGroup(
            birtdy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birtdy_panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(birtdy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(birtdy_panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(birtdy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthdy_optionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(festival_panel_option1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        birtdy_panelLayout.setVerticalGroup(
            birtdy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birtdy_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(birthdy_optionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(festival_panel_option1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        birthdy_option.setForeground(new java.awt.Color(153, 0, 0));
        birthdy_option.setText("Birthday Wishes");
        birthdy_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                birthdy_optionItemStateChanged(evt);
            }
        });

        festival_option.setForeground(new java.awt.Color(153, 0, 0));
        festival_option.setText("Festivals / Occations Wishes ");
        festival_option.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                festival_optionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(birtdy_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(birthdy_option, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(festival_option, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdy_option)
                    .addComponent(festival_option))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(birtdy_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Greetings & Wishes Master", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        birthdy_Deactive_Button.setForeground(new java.awt.Color(153, 0, 0));
        birthdy_Deactive_Button.setText("Deactivate ");
        birthdy_Deactive_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthdy_Deactive_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(birthdy_Deactive_Button)
                .addGap(127, 127, 127))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(birthdy_Deactive_Button)
                .addContainerGap(229, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of Active Birthday Wishes ", jPanel3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        festival_Deactive_Button.setForeground(new java.awt.Color(153, 0, 0));
        festival_Deactive_Button.setText("Deactivate ");
        festival_Deactive_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                festival_Deactive_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(festival_Deactive_Button)
                .addGap(214, 214, 214))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(festival_Deactive_Button)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List of Active Festival / Occational Wishes", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        if(jRadioButton7.isSelected()==true){
            int size=160-jTextField3.getText().length()-1;
            jLabel2.setText("/"+size);
            jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
            prefix=jTextField3.getText();
        }
        
        if(dependent_option.isSelected()){
            
            int size=160-wishing_text.getText().length()-1;
            jLabel2.setText("/"+size);
            jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
            prefix=wishing_text.getText(); 
            
            
            
            
        }
        
        
        
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        if (!evt.getKeyText(evt.getKeyCode()).equals("Backspace")) {

            String message=jTextArea1.getText();
            Pattern pattern=Pattern.compile("\\&");
            Matcher matcher=pattern.matcher(message);
            if(matcher.find())
            {

                JOptionPane.showMessageDialog(this,"Please do not use special character-'&' ");

            }
        }
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jRadioButton3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton3StateChanged
        if(jRadioButton3.isSelected()==true)
        prefix="Dear Sir";
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);
    }//GEN-LAST:event_jRadioButton3StateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        if(jRadioButton3.isSelected()==true)
        prefix="Dear Sir,";
        int size= 160-prefix.length()-1;
        jLabel2.setText("/"+size);
        jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void jRadioButton4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton4StateChanged
        if(jRadioButton4.isSelected()==true)
        prefix="Dear Mam";
        //  int size= 160-prefix.length();
        //   jLabel2.setText("/"+size);
    }//GEN-LAST:event_jRadioButton4StateChanged

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        if(jRadioButton4.isSelected()==true)
        prefix="Dear Mam,";
        int size= 160-prefix.length()-1;
        jLabel2.setText("/"+size);
        jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton5StateChanged
        // if(jRadioButton5.isSelected()==true)
        // {
            //    prefix="Dear Sir/Mam";
            //}
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);

    }//GEN-LAST:event_jRadioButton5StateChanged

    private void jRadioButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton5ItemStateChanged
        if(jRadioButton5.isSelected()==true)
        {
            prefix="Dear Sir/Mam,";
        }
        int size= 160-prefix.length()-1;
        jLabel2.setText("/"+size);
        jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jRadioButton5ItemStateChanged

    private void jRadioButton6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton6StateChanged
        if(jRadioButton6.isSelected()==true)
        prefix="Dear";
        // int size= 160-prefix.length();
        // jLabel2.setText("/125");
    }//GEN-LAST:event_jRadioButton6StateChanged

    private void jRadioButton6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton6ItemStateChanged
        if(jRadioButton6.isSelected()==true)
        prefix="Dear";
        int size= 160-prefix.length();
        jLabel2.setText("/124");
        jTextArea1.setDocument(new MyPlainDocument(124,jLabel3));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jRadioButton6ItemStateChanged

    private void jRadioButton7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton7StateChanged
        if(jRadioButton7.isSelected()==true){
            jTextArea1.setEditable(true);
            jLabel8.setVisible(true);
            jTextField3.setVisible(true);

            jTextField3.requestFocus(true);
            if(jTextField3.getText()==null||jTextField3.getText().length()<=0)
            {
                jLabel2.setText("/160");
                prefix=jTextField3.getText();
            }
            else
            {
                prefix=jTextField3.getText();
                //  int size=160-jTextField3.getText().length();
                //  jLabel2.setText("/"+size);
            }
        }else{
            jLabel8.setVisible(false);
            jTextField3.setVisible(false);
            jTextField3.setText(null);
        }
    }//GEN-LAST:event_jRadioButton7StateChanged

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            int size=160-jTextField3.getText().length()-1;
            jLabel2.setText("/"+size);
            jTextArea1.setDocument(new MyPlainDocument(size,jLabel2));
        }

    }//GEN-LAST:event_jTextField3KeyPressed

    private void jRadioButton8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton8StateChanged
        if(jRadioButton8.isSelected()==true){
            prefix="";
            jLabel2.setText("/160");
        }else
        {
            if(jRadioButton7.isSelected()==true){
                prefix=jTextField3.getText();
            }

            int size= 160-prefix.length();
            if(jRadioButton6.isSelected()==true)
            {
                jLabel2.setText("/125");
            }
            else
            jLabel2.setText("/"+size);
        }
    }//GEN-LAST:event_jRadioButton8StateChanged

    private void jRadioButton8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton8ItemStateChanged
        if(jRadioButton8.isSelected()==true){
            prefix="";
            jLabel2.setText("/160");
            int size= 160;
            jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
            jTextArea1.setEditable(true);
        }
    }//GEN-LAST:event_jRadioButton8ItemStateChanged

    private void jRadioButton9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton9StateChanged
        if(jRadioButton9.isSelected()==true)
        prefix="Dear Member";
        // int size= 160-prefix.length();
        // jLabel2.setText("/"+size);
    }//GEN-LAST:event_jRadioButton9StateChanged

    private void jRadioButton9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton9ItemStateChanged
        if(jRadioButton9.isSelected()==true)
        prefix="Dear Member,";
        int size= 160-prefix.length()-1;
        jLabel2.setText("/"+size);
        jTextArea1.setDocument(new MyPlainDocument(size,jLabel3));
        jTextArea1.setEditable(true);
    }//GEN-LAST:event_jRadioButton9ItemStateChanged

    private void birthdy_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_birthdy_optionItemStateChanged
        
        if(birthdy_option.isSelected()){
          birtdy_panel.setVisible(true);
          birthdy_optionPanel1.setVisible(true);  
          festival_panel_option1.setVisible(false);
          
        }
        else{
          birtdy_panel.setVisible(true);
          birthdy_optionPanel1.setVisible(false);    
        }
        
        
        
        
    }//GEN-LAST:event_birthdy_optionItemStateChanged

    private void dependent_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dependent_optionItemStateChanged
        if(dependent_option.isSelected()){
            
            prefix_option_panel.setVisible(false);
            wishing_text.setVisible(true);
            dependent_name_text.setVisible(true);
            jTextArea1.setText(null);
            manual_option.setSelected(true);
            wishing_text.setText("Wishing ");
            jPanel2.setVisible(true);  
            
            jLabel5.setVisible(true);
            
        }
        
        
        
        
    }//GEN-LAST:event_dependent_optionItemStateChanged

    private void member_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_member_optionItemStateChanged
        
        if(member_option.isSelected()){
            
            wishing_text.setVisible(false);
            dependent_name_text.setVisible(false);
            prefix_option_panel.setVisible(true);
            jTextArea1.setText(null);
            
            manual_option.setSelected(true);
            wishing_text.setText("Wishing ");
            jPanel2.setVisible(true);  
            jLabel5.setVisible(false);
        }
        
        
        
    }//GEN-LAST:event_member_optionItemStateChanged

    String Message;
    String TextArea;
    int memberFlag=0;
    int AutoFlag = 0;
    int BirthdayFlag=0;
    int Active=0;
    String Name;
    String AutoName;
    String member;
    int MemberNameFlag;
    int checkflag=0;
    String FestivalName;
    Date FestivalDate;
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     Message = null; 
      
     if(birthdy_option.isSelected()){
         
         
       
     
     if(jTextArea1.getText()!=null && jTextArea1.getText().trim().length()>1 ){
        TextArea = jTextArea1.getText().trim();
                checkflag=0;
                if(member_option.isSelected()){

                            Message =  prefix + "\r" + TextArea;
                            System.out.println("length :"+Message.length());
                            
                            if(birthdy_option.isSelected()){
                                BirthdayFlag=1;
                                Name = "Birthday";

                            }
                            else{
                                BirthdayFlag=0;
                                Name = "Festival";
                            }

                            
                            
                            
                            if(member_option.isSelected()){
                                memberFlag=1;
                                member = "Members";
                            }
                            else{
                                memberFlag=0;
                                member = "Dependents";
                            }
                            
                            
                            
                            
                            if(manual_option.isSelected()){
                                AutoFlag=0;
                                AutoName="Manual Mode";
                            }
                            else{
                                AutoFlag=1;
                                AutoName="Auto Mode";
                            }

                           
                            if(jRadioButton6.isSelected()){
                                MemberNameFlag=1;
                            }
                            else{
                                MemberNameFlag=0;
                            }
                            
                            
                            
                            Active=1;
                            try {
                                BirthdayWishes_Table_Model = GreetingWishesTableModel.loadBirthdayWish(m_App);
                            } catch (BasicException ex) {
                                Logger.getLogger(GreetingWishesMaster.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            for(int i=0;i<BirthdayWishes_Table_Model.getSize();i++){
                               GreetingWishesTableModel.BirthdayWishesTableInfo showdata = BirthdayWishes_Table_Model.getList().get(i); 
                               int birthdyFlag = showdata.getBIRTHDAYFLAG();
                               int memFlag = showdata.getMEMFLAG();
                               
                               if(memFlag==memberFlag && birthdyFlag==BirthdayFlag){
                                   checkflag=1;
                               }
                               
                                
                                
                            }
                            
                           if(checkflag==0){ 
                            

                           int submit_hall = JOptionPane.showConfirmDialog(birtdy_panel, "Are you Sure you want to Activate Birthdy Wishes for "+member+"..??    \n\n  The mode of sending message will be "+AutoName+".  \n \n " , "Birthday Wishes Activation" , JOptionPane.YES_NO_OPTION);
                            if(submit_hall == JOptionPane.YES_OPTION){
             
                            
                            
                            

                           Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                           @Override      
                           protected Object transact() throws BasicException {   



                                int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO sms_greetings (ID ,NAME , MESSAGE , MEMFLAG , AUTOFLAG , BIRTHDAYFLAG , CRBY , CRDATE , CRHOST ,ACTIVE , PREFIX , MEMNAME , MESSAGE1 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.INT, Datas.INT ,Datas.INT  , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.INT , Datas.STRING})                         
                                ).exec(new Object[]{UUID.randomUUID().toString(), Name , Message ,memberFlag , AutoFlag , BirthdayFlag ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active , prefix ,MemberNameFlag , TextArea });                                                                                                


                                 return null;                                      
                                   }                            
                               };                 

                               try {                 
                                   t.execute();          

                                   JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                   reset();
                                   Loaddata();
                                   

                               }
                            catch (BasicException ex) {                    
                                       ex.printStackTrace();
                                       new MessageInf(ex).show(new JFrame());
                                       
                                   } 
                     

                               
                            }
                           }
                           else{
                              JOptionPane.showMessageDialog(this, "Already exsist..!  \n\n Please deactivate incase if you want to create new.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                               
                               
                               



                  }
                
                
                
               //  CODE FOR DEPENDENTS..............................................................  
                
                
                
               if(dependent_option.isSelected()){

                   prefix = wishing_text.getText();
                   
                   Message =  prefix + "\r" + TextArea;
                            System.out.println("length :"+Message.length());
                            
                            if(birthdy_option.isSelected()){
                                BirthdayFlag=1;
                                Name = "Birthday";

                            }
                            else{
                                BirthdayFlag=0;
                                Name = "Festival";
                            }

                            
                            
                            
                            if(member_option.isSelected()){
                                memberFlag=1;
                                member = "Members";
                            }
                            else{
                                memberFlag=0;
                                member = "Dependents";
                            }
                            
                            
                            
                            
                            if(manual_option.isSelected()){
                                AutoFlag=0;
                                AutoName="Manual Mode";
                            }
                            else{
                                AutoFlag=1;
                                AutoName="Auto Mode";
                            }

                           
                            
                            MemberNameFlag=1;
                            checkflag=0;
                            
                            
                            for(int i=0;i<BirthdayWishes_Table_Model.getSize();i++){
                               GreetingWishesTableModel.BirthdayWishesTableInfo showdata = BirthdayWishes_Table_Model.getList().get(i); 
                               int birthdyFlag = showdata.getBIRTHDAYFLAG();
                               int memFlag = showdata.getMEMFLAG();
                               
                               if(memFlag==memberFlag && birthdyFlag==BirthdayFlag){
                                   checkflag=1;
                               }
                               
                                
                                
                            }
                            
                            
                            Active=1;
                            
                           if(checkflag==0){

                           int submit_hall = JOptionPane.showConfirmDialog(birtdy_panel, "Are you Sure you want to Activate Birthdy Wishes for "+member+"..??    \n\n  The mode of sending message will be "+AutoName+".  \n \n " , "Birthday Wishes Activation" , JOptionPane.YES_NO_OPTION);
                            if(submit_hall == JOptionPane.YES_OPTION){
             
                            
                            
                            

                           Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                           @Override      
                           protected Object transact() throws BasicException {   



                                int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO sms_greetings (ID ,NAME , MESSAGE , MEMFLAG , AUTOFLAG , BIRTHDAYFLAG , CRBY , CRDATE , CRHOST ,ACTIVE , PREFIX , MEMNAME , MESSAGE1 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.STRING ,Datas.INT, Datas.INT ,Datas.INT  , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.STRING , Datas.INT , Datas.STRING})                         
                                ).exec(new Object[]{UUID.randomUUID().toString(), Name , Message ,memberFlag , AutoFlag , BirthdayFlag ,  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active , prefix ,MemberNameFlag , TextArea });                                                                                                


                                 return null;                                      
                                   }                            
                               };                 

                               try {                 
                                   t.execute();          

                                   JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                   reset();
                                   Loaddata();
                                   

                               }
                            catch (BasicException ex) {                    
                                       ex.printStackTrace();
                                       new MessageInf(ex).show(new JFrame());
                                       
                                   } 
                     

                               
                            }
                          
                           }
                           else{
                                JOptionPane.showMessageDialog(this, "Already exsist..!  \n\n Please deactivate incase if you want to create new.", "Warning", JOptionPane.WARNING_MESSAGE);
                           }
                               
                   
                   
                   
                   
                   
                   
                   






               }
      }
      else{
           JOptionPane.showMessageDialog(this, "Please enter greeting message..!", "Warning", JOptionPane.WARNING_MESSAGE);
      }
     
     
     }
     
     
     // CODE FOR FESTIVAL SMSS..............................................................................................................
     
     if(festival_option.isSelected()){
         
         if(jTextArea1.getText()!=null && jTextArea1.getText().trim().length()>1 ){ 
            if(festival_name.getText()!=null && festival_name.getText().trim().length()>0){
                if(date_text.getText()!=null && date_text.getText().trim().length()>0){
     
                   TextArea = jTextArea1.getText().trim();
                    
                   Message =  prefix + "\r" + TextArea;  
                    
                   FestivalName = festival_name.getText().trim();
                    
                   try {
                        FestivalDate = (Date) Formats.DATE.parseValue(date_text.getText());
                    } catch (BasicException ex) {
                        Logger.getLogger(GreetingWishesMaster.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                   
                    if(jRadioButton6.isSelected()){
                         MemberNameFlag=1;
                    }
                    else{
                         MemberNameFlag=0;
                    }
                   
                   Active = 1;
                   
                    if(manual_option.isSelected()){
                                AutoFlag=0;
                                AutoName="Manual Mode";
                            }
                            else{
                                AutoFlag=1;
                                AutoName="Auto Mode";
                            }
                   
                    List<Object> OldDates = new ArrayList<Object>();
                    try{
                        OldDates = getFestivalDates(m_App);
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                                       new MessageInf(ex).show(new JFrame());
                    }
                   
                    int datesameflag=0;
                    
                    
                    for(int i=0;i<OldDates.size() ; i++ ){
                        Date d  = null;
                        try{
                         d = (Date) Formats.DATE.parseValue(OldDates.get(i).toString());
                        }
                        catch(Exception ex){
                            
                        }
                        
                        if(d.equals(FestivalDate)){
                            datesameflag = 1;
                            break;
                        }
                        
                        
                    }
                    
                   if(datesameflag==0){
             
                   Transaction t = new Transaction(m_App.getSession()) {                                                                                     

                           @Override      
                           protected Object transact() throws BasicException {   



                                    int   insert_data =  new PreparedSentence(m_App.getSession()  , "INSERT INTO sms_festival (ID ,NAME , DATE , PRIFIX , MEMNAMEFLAG , MESSAGE , SENT , CRBY , CRDATE ,CRHOST , ACTIVE , AUTOFLAG ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)"                           
                                    , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING, Datas.TIMESTAMP ,Datas.STRING, Datas.INT ,Datas.STRING  , Datas.INT , Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.INT , Datas.INT })                         
                                    ).exec(new Object[]{UUID.randomUUID().toString(), FestivalName , FestivalDate ,prefix , MemberNameFlag , TextArea , 0 ,   m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , Active , AutoFlag });                                                                                                


                                    
                                    
                                    
                               return null;                                      
                                   }                            
                               };                 

                               try {                 
                                   t.execute();          

                                   JOptionPane.showMessageDialog(this, "Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                   reset();
                                   Loaddata();
                                   

                               }
                            catch (BasicException ex) {                    
                                       ex.printStackTrace();
                                       new MessageInf(ex).show(new JFrame());
                                       
                             } 
             
             
                   }
                   else{
                       JOptionPane.showMessageDialog(this, "Date Entered cannot be same.....!!!", "Warning", JOptionPane.INFORMATION_MESSAGE);
                   }
             
             
             
             
             
             
             
             
             
             
     
                }
                else{
                     JOptionPane.showMessageDialog(this, "Please enter Date  on which u want to send SMS!", "Warning", JOptionPane.WARNING_MESSAGE); 
                }
     
            }
            else{
                  JOptionPane.showMessageDialog(this, "Please enter Name of Festival!", "Warning", JOptionPane.WARNING_MESSAGE); 
            }
     
         }
         else{
             JOptionPane.showMessageDialog(this, "Please enter greeting message..!", "Warning", JOptionPane.WARNING_MESSAGE); 
         }
     
      }
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void wishing_textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wishing_textKeyPressed
       if (evt.getKeyText(evt.getKeyCode()).equals("Enter")) {
            int size=160-wishing_text.getText().length()-25;
            jLabel2.setText("/"+size);
            jTextArea1.setDocument(new MyPlainDocument(size,jLabel2));
        }
    }//GEN-LAST:event_wishing_textKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextArea1.getText()!=null && jTextArea1.getText().trim().length()>1){
            
        Message = null;     
        TextArea = jTextArea1.getText();
        Message =  prefix + "\r" + TextArea;
        System.out.println("length :"+Message.length());    
            
            
         JOptionPane.showMessageDialog(this, "Message :  \n \n  "+Message+ "\n \n Message Length = "+Message.length() , "Warning", JOptionPane.INFORMATION_MESSAGE); 
         
         
         
            
        }
        else{
            
          JOptionPane.showMessageDialog(this, "Please enter greeting message..!", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void festival_optionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_festival_optionItemStateChanged
       birtdy_panel.setVisible(true);
       
       if(festival_option.isSelected()){
           festival_panel_option1.setVisible(true);
           birthdy_optionPanel1.setVisible(false);
           member_option.setSelected(true);
           
       }
       else{
           festival_panel_option1.setVisible(false);
       }
       
    }//GEN-LAST:event_festival_optionItemStateChanged

    private void festival_Deactive_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_festival_Deactive_ButtonActionPerformed
         if(jTable2.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel4, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable2.getSelectedRow()<FestivalWish_Table_Model.getSize1()){
               int row = jTable2.getSelectedRow();
               GreetingWishesTableModel.FestivalWishInfo showdata = FestivalWish_Table_Model.getList1().get(row);
                 
               
               String id = showdata.getID();
               
               
               
                // DEACTIVATE LINKED ROOM
               try {
                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE sms_festival  SET ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , id  });
                  
               
                      reset();
                      Loaddata();
                      
                      JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                      
               } catch (BasicException ex) {
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
               
               
                 
             
              }
             }
         }
    }//GEN-LAST:event_festival_Deactive_ButtonActionPerformed

    private void birthdy_Deactive_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthdy_Deactive_ButtonActionPerformed
        if(jTable1.getSelectedRow()!=-1){
             int bill = JOptionPane.showConfirmDialog(jPanel4, " Do you want to deactivate ?? " , "Deactivation" , JOptionPane.YES_NO_OPTION);
             if(bill == JOptionPane.YES_OPTION)
             {   
             
              if(jTable1.getSelectedRow()<BirthdayWishes_Table_Model.getSize()){
               int row = jTable1.getSelectedRow();
               GreetingWishesTableModel.BirthdayWishesTableInfo showdata = BirthdayWishes_Table_Model.getList().get(row);
                 
               
               String id = showdata.getID();
               
               
               
                // DEACTIVATE LINKED ROOM
               try {
                      int update_Guest_master =  new PreparedSentence(m_App.getSession(), "UPDATE sms_greetings  SET ACTIVE=0  , DEACBY=? , DEACDATE=? , DEACHOST=? WHERE ID = ? "
                                                                           , new SerializerWriteBasic(new Datas[]{ Datas.STRING , Datas.TIMESTAMP , Datas.STRING , Datas.STRING })).exec
                                                                            (new Object[]{  m_App.getAppUserView().getUser().getName() ,new Date(), m_App.getProperties().getHost() , id  });
                  
               
                      reset();
                      Loaddata();
                      
                      JOptionPane.showMessageDialog(this, "De-Activated Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE);
                      
               } catch (BasicException ex) {
                       ex.printStackTrace();
                       new MessageInf(ex).show(new JFrame());
                  }
               
               
                 
             
              }
             }
         }
    }//GEN-LAST:event_birthdy_Deactive_ButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Date date;

        try {
            date = (Date) Formats.TIMESTAMP.parseValue(date_text.getText());
        } catch (BasicException ex) {
            date = null;
        }
        date = JCalendarDialog.showCalendarTimeHours(this, date);
        if (date != null) {

            date_text.setText(Formats.DATE.formatValue(date));
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton auto_option;
    private javax.swing.JPanel birtdy_panel;
    private javax.swing.JButton birthdy_Deactive_Button;
    private javax.swing.JRadioButton birthdy_option;
    private javax.swing.JPanel birthdy_optionPanel1;
    private javax.swing.JTextField date_text;
    private javax.swing.JTextField dependent_name_text;
    private javax.swing.JRadioButton dependent_option;
    private javax.swing.JButton festival_Deactive_Button;
    private javax.swing.JTextField festival_name;
    private javax.swing.JRadioButton festival_option;
    private javax.swing.JPanel festival_panel_option1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton manual_option;
    private javax.swing.JRadioButton member_option;
    private javax.swing.JPanel prefix_option_panel;
    private javax.swing.JTextField wishing_text;
    // End of variables declaration//GEN-END:variables

    public String getTitle() {
        return "Greetings &  Wishes Master";
    }

    public void activate() throws BasicException {
        reset();
        birtdy_panel.setVisible(false);
        
        member_option.setSelected(true);
        jRadioButton5.setSelected(true);
        buttongrp();
        Loaddata();
        
        
        
    }

    public boolean deactivate() {
       return true;
    }

    public JComponent getComponent() {
        return this;
    }

    public void init(AppView app) throws BeanFactoryException {
         m_App=app;
    }

    public Object getBean() {
         return this;
    }


   private class MyPlainDocument extends PlainDocument {
        private JLabel label;
        private int max;
       
        public MyPlainDocument (int max){
            //this.label=label;
            this.max=max;
           // this.textarea=null;
        }
        public MyPlainDocument (int max,JLabel label){
            this.label=label;
            this.max=max;
           // this.textarea=null;
        }
     
        
        
        
        

        @Override
        public void insertString(int offset, String str, AttributeSet a) throws
		BadLocationException {
         if(label!=null){
         
            int len=getLength() + str.length();
            int oldlen=getLength();
            if (!((len) > max)) {
			   super.insertString(offset, str, a);
               label.setText(String.valueOf(len));
		    }else{
                if (oldlen < max) {
                   String str1=str.substring(0, max-oldlen);
                   super.insertString(offset, str1, a);
                }else
                Toolkit.getDefaultToolkit().beep();
            }
                //label.setText(String.valueOf(max));
         }else{
             int oldlen=getLength();
		   if (!((getLength() + str.length()) > max)) {
			super.insertString(offset, str, a);
		   }else{
                if (oldlen < max) {
                   String str1=str.substring(0, max-oldlen);
                   super.insertString(offset, str1, a);
                }else
                   Toolkit.getDefaultToolkit().beep();
            }
      }
	}

    }


   
   
   
   public void buttongrp(){
       
       ButtonGroup bgp = new ButtonGroup();
      
       bgp.add(jRadioButton3);
       bgp.add(jRadioButton4);
       bgp.add(jRadioButton5);
       bgp.add(jRadioButton6);
       bgp.add(jRadioButton7);
       bgp.add(jRadioButton8);
       bgp.add(jRadioButton9);
       
        ButtonGroup bgp1 = new ButtonGroup();
        
        bgp1.add(member_option);
        bgp1.add(dependent_option);
        
        
       ButtonGroup bgp2 = new ButtonGroup();
        
        bgp2.add(birthdy_option);
        bgp2.add(festival_option);
       
       ButtonGroup bgp3 = new ButtonGroup();
        
        bgp3.add(auto_option);
        bgp3.add(manual_option);
        
        
   }

   
   public void Loaddata() throws BasicException{
       
        //birtdy_panel.setVisible(false);
        member_option.setSelected(true);
        jRadioButton5.setSelected(true);
        birthdy_option.setSelected(false);
       festival_option.setSelected(false);
        buttongrp();
       
       BirthdayWishes_Table_Model = GreetingWishesTableModel.loadBirthdayWish(m_App);
       jTable1.setModel(BirthdayWishes_Table_Model.getTableModel()); 
        
       FestivalWish_Table_Model = GreetingWishesTableModel.loadFestivalWishes(m_App);
       jTable2.setModel(FestivalWish_Table_Model.getTableModel2()); 
        
   }
  
   
   public void reset(){
       
       birthdy_option.setSelected(false);
       festival_option.setSelected(false);
      
       jTextArea1.setText(null);
       festival_name.setText(null);
       date_text.setText(null);
       
   }

   
   
    //LIST OF USERNAME  FROM EMAIL
        public List getFestivalDates(AppView app ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(app.getSession(), "SELECT DATE FROM sms_festival  WHERE active=1 ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
     
   
   

}

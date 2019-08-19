
package com.openbravo.pos.knowYourMember;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.SmsTableModel;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.JPanelView;
 import com.openbravo.pos.knowYourMember.MemberForm.ChildrenClass;
import com.openbravo.pos.sms.SmsConnection;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListModel;


public class MemberCompareMenu extends javax.swing.JPanel implements JPanelView, BeanFactoryApp {
    private AppView m_app;
    public List<ChildrenClass> ChildrenClassList = new ArrayList<ChildrenClass>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   
    public MemberCompareMenu() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        MemberMobile = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        MemberName = new javax.swing.JLabel();
        kymMemberName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        KYMMOBILENO = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        KYMRESNO = new javax.swing.JLabel();
        MemberPhone = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        MemberCity = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        Memberdob = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        KymMemberdateofbirth = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        MemberPostal = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        postal = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        Memberjoindate = new javax.swing.JLabel();
        KymMembersince = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        MemberMemType = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        KYMMEMBERTYPE = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Xarr = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        MemberAddress = new javax.swing.JTextArea();
        Membername = new javax.swing.JLabel();
        Memberpostal = new javax.swing.JLabel();
        Membermemtype = new javax.swing.JLabel();
        ErrorMessage = new javax.swing.JLabel();
        Kymmobileno = new javax.swing.JLabel();
        City = new javax.swing.JLabel();
        Postal = new javax.swing.JLabel();
        Kymmembertype = new javax.swing.JLabel();
        kymmembername1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Facilitylist1_JList = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        Facilitylist = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        KYMMember_Spouse_Name = new javax.swing.JLabel();
        Membername2 = new javax.swing.JLabel();
        Membername3 = new javax.swing.JLabel();
        Member_Spouse_Name = new javax.swing.JLabel();
        Member_Dependent_Name = new javax.swing.JLabel();
        Membername4 = new javax.swing.JLabel();
        Membername5 = new javax.swing.JLabel();
        kymdependent = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        KymMember_sports = new javax.swing.JLabel();
        KymMember_spcltalent = new javax.swing.JLabel();
        KymMember_sports2 = new javax.swing.JLabel();
        KymMember_EmailID = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        KymMember_Profession = new javax.swing.JLabel();
        Membername6 = new javax.swing.JLabel();
        KymMember_date_of_marriage = new javax.swing.JLabel();
        Membername7 = new javax.swing.JLabel();
        Membername8 = new javax.swing.JLabel();
        KymMember_MotherName = new javax.swing.JLabel();
        KymMember_FatherName = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        KymMember_MobileType = new javax.swing.JLabel();
        Membername9 = new javax.swing.JLabel();
        KymMember_mother_date_of_birth = new javax.swing.JLabel();
        Membername10 = new javax.swing.JLabel();
        KymMember_father_date_of_marriage = new javax.swing.JLabel();
        memberMother_name = new javax.swing.JLabel();
        Membername11 = new javax.swing.JLabel();
        MOTHER_DOB_MEMBER = new javax.swing.JLabel();
        Membername12 = new javax.swing.JLabel();
        Membername13 = new javax.swing.JLabel();
        member_father_name = new javax.swing.JLabel();
        Membername14 = new javax.swing.JLabel();
        mem_father_dob = new javax.swing.JLabel();
        Membername15 = new javax.swing.JLabel();
        member_dom = new javax.swing.JLabel();
        Membername16 = new javax.swing.JLabel();
        member_profeesion = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        member_mobiletype = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        member_emailid = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        member_sports = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        member_special_talent = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jPanel1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jPanel1CaretPositionChanged(evt);
            }
        });

        jLabel1.setText("Membership No : ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Name :");

        jLabel14.setText("According to KYM");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 255));
        jLabel21.setText("According to Customer");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 255));
        jLabel32.setText("According to KYM");

        MemberMobile.setText("Mobile :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Name : ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Address :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Address :");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Mobile :");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Mobile :");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Phone :");

        KYMRESNO.setText("phone :");

        MemberPhone.setText("Phone:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Phone :");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("City :");

        MemberCity.setText("City :");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("City :");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText("DOB :");

        Memberdob.setText("DOB :");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("DOB :");

        KymMemberdateofbirth.setText("DOB:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Postal :");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Postal :");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Member Since :");

        Memberjoindate.setText("MemberSince :");

        KymMembersince.setText("Membersince :");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Member Since :");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Member Type :");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Member Type :");

        Xarr.setColumns(20);
        Xarr.setRows(5);
        jScrollPane2.setViewportView(Xarr);

        MemberAddress.setColumns(20);
        MemberAddress.setRows(5);
        jScrollPane3.setViewportView(MemberAddress);

        Membername.setText("Name :");

        Memberpostal.setText("Postal :");

        Membermemtype.setText("Member Type :");

        ErrorMessage.setText("Message :");

        Kymmobileno.setText("Mobile :");

        City.setText("City :");

        Postal.setText("Postal :");

        Kymmembertype.setText("Member Type :");

        kymmembername1.setText("Name : ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Facility name :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Facility name :");

        Facilitylist1_JList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Facilitylist1_JList);

        Facilitylist.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(Facilitylist);

        jButton1.setText("Facility Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        KYMMember_Spouse_Name.setText("SpouseName :");

        Membername2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername2.setText("Spouse Name :");

        Membername3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername3.setText("Spouse Name :");

        Member_Spouse_Name.setText("SpouseName :");

        Member_Dependent_Name.setText("DepName :");

        Membername4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername4.setText("Dependent Name :");

        Membername5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername5.setText("Dependent Name :");

        kymdependent.setText("DepName :");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setText("Sports :");

        KymMember_sports.setText("Sports :");

        KymMember_spcltalent.setText("Specialtalent :");

        KymMember_sports2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        KymMember_sports2.setText("Special Talent :");

        KymMember_EmailID.setText("Emailid:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("EmailID :");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("Profession:");

        KymMember_Profession.setText("Profession:");

        Membername6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername6.setText("Date Of Marriage :");

        KymMember_date_of_marriage.setText("dom:");

        Membername7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername7.setText("Mother Name :");

        Membername8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername8.setText("Father Name :");

        KymMember_MotherName.setText("MotherName :");

        KymMember_FatherName.setText("FatherName :");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setText("Mobile  Type:");

        KymMember_MobileType.setText("Mobiletype :");

        Membername9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername9.setText("Mother DOB :");

        KymMember_mother_date_of_birth.setText("MotherDOB :");

        Membername10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername10.setText("Father DOB :");

        KymMember_father_date_of_marriage.setText("Fatherdob :");

        memberMother_name.setText("MotherName :");

        Membername11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername11.setText("Mother Name :");

        MOTHER_DOB_MEMBER.setText("MotherDOB :");

        Membername12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername12.setText("Mother DOB :");

        Membername13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername13.setText("Father Name :");

        member_father_name.setText("FatherName:");

        Membername14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername14.setText("Father Name :");

        mem_father_dob.setText("FatherDOB:");

        Membername15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername15.setText("Date Of Marriage :");

        member_dom.setText("Dom:");

        Membername16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Membername16.setText("Profession :");

        member_profeesion.setText("Profession:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("MobileType :");

        member_mobiletype.setText("Mobiletype :");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText("EmailID :");

        member_emailid.setText("Emailid:");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setText("Sports:");

        member_sports.setText("Sports:");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setText("Special Talent");

        member_special_talent.setText("Specialtalent:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(195, 195, 195)
                                        .addComponent(MemberPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(62, 62, 62)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(345, 345, 345)
                                                        .addComponent(postal, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(136, 136, 136)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(kymMemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KYMMOBILENO, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(301, 301, 301)
                                                .addComponent(KYMMEMBERTYPE, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(Membername2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                                                    .addComponent(Membername4, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(Membername11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(30, 58, Short.MAX_VALUE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(Membername12, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                                .addComponent(MemberPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                                .addComponent(MemberCity, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Memberdob, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                                .addComponent(Memberpostal, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                                .addComponent(Memberjoindate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Membermemtype, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Member_Spouse_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(Member_Dependent_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                                                .addComponent(MemberMobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(member_mobiletype, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                                            .addComponent(Membername, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(memberMother_name, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(MOTHER_DOB_MEMBER, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_profeesion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_emailid, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_sports, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_special_talent, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_father_name, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(mem_father_dob, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(member_dom, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(100, 100, 100))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(Membername13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(317, 317, 317))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Membername15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Membername14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(Membername5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Membername3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(KymMember_sports2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Membername6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Membername7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Membername8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Membername9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(Membername10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(21, 21, 21)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(kymmembername1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KymMembersince, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(Kymmobileno, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KYMRESNO, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(City, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(Postal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(KymMemberdateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KymMember_sports, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(KymMember_spcltalent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Kymmembertype, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(KymMember_EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KymMember_Profession, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(KymMember_MobileType, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addComponent(kymdependent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(KymMember_FatherName, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                                                                    .addComponent(KymMember_date_of_marriage, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                                                                    .addComponent(KymMember_father_date_of_marriage, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(KymMember_mother_date_of_birth, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                                                                    .addComponent(KymMember_MotherName, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(KYMMember_Spouse_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(36, 36, 36)
                                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(204, 204, 204)
                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGap(101, 101, 101)
                                                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGap(34, 34, 34)
                                                                        .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(0, 801, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(MemberMemType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGap(31, 31, 31))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(Membername16, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(376, 376, 376))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(kymMemberName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ErrorMessage)
                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(MemberName)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(kymmembername1))
                                                .addGap(9, 9, 9))
                                            .addComponent(Membername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)))
                                .addGap(12, 12, 12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(KYMMember_Spouse_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Membername3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Member_Spouse_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Membername2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KYMMOBILENO)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Membername7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_MotherName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memberMother_name)
                            .addComponent(Membername11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Membername9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_mother_date_of_birth, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MOTHER_DOB_MEMBER)
                            .addComponent(Membername12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Membername8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_FatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Membername13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member_father_name))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Membername10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_father_date_of_marriage, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Membername14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mem_father_dob))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Membername6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_date_of_marriage, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Membername15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member_dom))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Member_Dependent_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Membername4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Membername5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kymdependent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel24))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel10)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(KymMember_Profession)
                            .addComponent(Membername16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member_profeesion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(Kymmobileno)
                            .addComponent(MemberMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(KymMember_MobileType)
                            .addComponent(jLabel34)
                            .addComponent(member_mobiletype, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KymMember_EmailID, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel35)
                            .addComponent(member_emailid))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30)
                                .addComponent(MemberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27))
                            .addComponent(KYMRESNO, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(City)
                            .addComponent(MemberCity)
                            .addComponent(jLabel28))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MemberMemType)
                                    .addComponent(KYMMEMBERTYPE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel41)
                                        .addComponent(KymMemberdateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Memberdob)
                                        .addComponent(jLabel29))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(city)))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(MemberPostal)
                                        .addComponent(postal))
                                    .addComponent(Postal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(KymMembersince, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel45)
                                                    .addComponent(Memberpostal)
                                                    .addComponent(jLabel43))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Memberjoindate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel50)
                                            .addComponent(Kymmembertype)
                                            .addComponent(Membermemtype)
                                            .addComponent(jLabel47))))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jSeparator14)
                                .addGap(38, 38, 38)))))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KymMember_sports, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(member_sports))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(member_special_talent)
                            .addComponent(KymMember_sports2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KymMember_spcltalent, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))))
        );

        jScrollPane5.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1827, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jPanel1CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1CaretPositionChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        MemberFacilityDetailsForm memberdetails;
        try
        {
            String Memno=jTextField1.getText().trim();
            String FacilityName = Facilitylist.getSelectedValue().toString();

            memberdetails = MemberFacilityDetailsForm.getDialog(this, m_app, true,Memno,FacilityName);
            memberdetails.showDialog();
        }
        catch(BasicException ex)
        {
            Logger.getLogger(MemberFacilityDetailsForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(jTextField1.getText()!=null && jTextField1.getText().trim().length()>0){

            String memno=jTextField1.getText().trim();
            try
            {

                Object[] obj = (Object[]) new StaticSentence(m_app.getSession(), "SELECT NAME FROM CUSTOMERS where SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj!=null){
                    String memberOrgname = obj[0].toString();
                    Membername.setText(memberOrgname);
                    Membername.setVisible(true);

                }
                else
                {
                    ErrorMessage.setText("No Match Found");
                    ErrorMessage.setForeground(Color.RED);
                    Membername.setText(null);

                }

                Object[] obj2 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT membername FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj2!=null){

                    String memberOrgname = obj2[0].toString();
                    kymmembername1.setText(memberOrgname);
                    kymmembername1.setVisible(true);

                }
                else
                {
                    ErrorMessage.setText("No Match Found");
                    ErrorMessage.setForeground(Color.RED);
                    kymmembername1.setText(null);
                }

                Object[] obj3 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT ADDRESS FROM CUSTOMERS where SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj3!=null) {
                    if(obj3[0]!=null)
                    {
                        String memberOrgaddress = obj3[0].toString();
                        MemberAddress.setText(memberOrgaddress);
                        MemberAddress.setVisible(true);

                    }
                }
                else
                {
                    MemberAddress.setText(null);
                }
                Object[] obj4 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT MOBILE FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj4!=null){
                    if(obj4[0]!=null)
                    {
                        String memberOrgmobile = obj4[0].toString();
                        MemberMobile.setText(memberOrgmobile);
                        MemberMobile.setVisible(true);

                    }
                }
                else
                {
                    MemberMobile.setText(null);
                }
                Object[] obj5 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT MOBILENO FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj5!= null){
                    if(obj5[0]!= null)
                    {
                        String memberOrgmobileno = obj5[0].toString();
                        Kymmobileno.setText(memberOrgmobileno);
                        Kymmobileno.setVisible(true);
                    }
                }
                else
                {
                    Kymmobileno.setText(null);
                }
                Object[] obj6 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT Phone FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj6 !=null){
                    if(obj6[0] !=null){
                        String memberOrgphone = obj6[0].toString();
                        MemberPhone.setText(memberOrgphone);
                        MemberPhone.setVisible(true);
                    }
                }
                else
                {
                    MemberPhone.setText(null);
                }
                Object[] obj7 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT RESNO FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj7!=null){
                    if(obj7[0]!= null)
                    {
                        String memberOrgresno = obj7[0].toString();
                        KYMRESNO.setText(memberOrgresno);
                        KYMRESNO.setVisible(true);
                    }
                }
                else
                {
                    KYMRESNO.setText(null);
                }
                Object[] obj8 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT City FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj8!=null){
                    if(obj8[0] !=null){
                        String memberOrgcity = obj8[0].toString();
                        MemberCity.setText(memberOrgcity);
                        MemberCity.setVisible(true);

                    }
                }
                else
                {
                    MemberCity.setText(null);
                }
                Object[] obj9 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT Postal FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj9!=null){
                    if(obj9[0] !=null){
                        String memberOrgpostal = obj9[0].toString();
                        Memberpostal.setText(memberOrgpostal);
                        Memberpostal.setVisible(true);

                    }
                }
                else
                {
                    Memberpostal.setText(null);
                }
                Object[] obj10 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT DOB FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj10!=null){
                    if(obj10[0]!= null)
                    {
                        String memberOrgdob= obj10[0].toString();

                        Date MemberDOB;
                        MemberDOB= (Date)formatter.parse(memberOrgdob);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgdob = sdfDestination.format(MemberDOB);

                        Memberdob.setText(memberOrgdob);
                        Memberdob.setVisible(true);
                    }
                }
                else
                {
                    Memberdob.setText(null);
                }
                Object[] obj11 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT DATEOFBIRTH FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj11!=null){
                    if(obj11[0] !=null){
                        String memberOrgdateofbirth = obj11[0].toString();
                        Date KymDate;
                        KymDate =(Date) formatter.parse(memberOrgdateofbirth);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgdateofbirth = sdfDestination.format(KymDate);

                        KymMemberdateofbirth.setText(memberOrgdateofbirth);
                        KymMemberdateofbirth.setVisible(true);
                    }
                }
                else
                {
                    KymMemberdateofbirth.setText(null);
                }
                Object[] obj12 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT JOINDATE FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj12!=null){
                    if(obj12[0] !=null){
                        String memberOrgjoindate = obj12[0].toString();
                        Date MemberJoin;
                        MemberJoin =(Date) formatter.parse(memberOrgjoindate);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgjoindate = sdfDestination.format(MemberJoin);
                        Memberjoindate.setText(memberOrgjoindate);
                        Memberjoindate.setVisible(true);
                    }
                }
                else
                {
                    Memberjoindate.setText(null);
                }
                Object[] obj13 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT MEMBERSINCE FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj13!=null){
                    if(obj13[0] !=null){
                        String memberOrgsince = obj13[0].toString();
                        Date KymSince;
                        KymSince =(Date) formatter.parse(memberOrgsince);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgsince = sdfDestination.format(KymSince);
                        KymMembersince.setText(memberOrgsince);
                        KymMembersince.setVisible(true);
                    }
                }
                else
                {
                    KymMembersince.setText(null);
                }
                Object[] obj14 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT MEMTYPE FROM CUSTOMERS WHERE SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj14!=null){
                    if(obj14[0] !=null){
                        String memberOrgmemtype = obj14[0].toString();

                        Membermemtype.setText(memberOrgmemtype);
                        Membermemtype.setVisible(true);
                        Object[] obj141 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT name FROM memtype WHERE ID= ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memberOrgmemtype);

                        if(obj141 !=null){
                            if(obj141[0] !=null){
                                String memberOrgmemtype1= obj141[0].toString();
                                Membermemtype.setText(memberOrgmemtype1);
                                Membermemtype.setVisible(true);
                            }
                        }

                    }
                }
                else
                {
                    Membermemtype.setText(null);
                }
                Object[] obj15 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT MEMBERTYPE FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj15!=null){
                    if(obj15[0] !=null){
                        String memberOrgmembertype = obj15[0].toString();
                        Kymmembertype.setText(memberOrgmembertype);
                        Kymmembertype.setVisible(true);
                    }
                }
                else
                {
                    Kymmembertype.setText(null);
                }
                Object[] obj16 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT * FROM CUSTOMERS where SEARCHKEY=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj16!=null){
                    if(obj16[0] !=null){
                        String errormessage =null;
                        ErrorMessage.setText(errormessage);
                        ErrorMessage.setVisible(true);

                    }
                }
                Object[] obj17 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT * FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj17!=null){
                    if(obj17[0] !=null){
                        String errormessage =null;
                        ErrorMessage.setText(errormessage);
                        ErrorMessage.setVisible(true);

                    }
                }
                else
                {
                    ErrorMessage.setText("No Match Found");
                    ErrorMessage.setForeground(Color.RED);
                }
                Object[] obj18 = (Object[]) new StaticSentence(m_app.getSession(), "select m.dname from memdependent m,customers c where m.dtype like '%spouse%' and m.active =1 and m.memno=c.id and c.searchkey = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj18!=null){
                    if(obj18[0] !=null){
                        String spousename =obj18[0].toString();
                        Member_Spouse_Name.setText(spousename);
                        Member_Spouse_Name.setVisible(true);

                    }
                }
                else
                {
                     Member_Spouse_Name.setText(null);
                }
                Object[] obj19 = (Object[]) new StaticSentence(m_app.getSession(), "select spoucename from kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj19!=null){
                    if(obj19[0] !=null){
                        String kymspousename =obj19[0].toString();
                        KYMMember_Spouse_Name.setText(kymspousename);
                        KYMMember_Spouse_Name.setVisible(true);

                    }
                }
                else
                {
                    KYMMember_Spouse_Name.setText(null);
                }
                Object[] obj20 = (Object[]) new StaticSentence(m_app.getSession(), "select m.dname from memdependent m,customers c where m.dtype like '%son%' and m.active =1 and m.memno=c.id and c.searchkey = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj20!=null){
                    if(obj20[0] !=null){
                        String deptname =obj20[0].toString();
                        Member_Dependent_Name.setText(deptname);
                        Member_Dependent_Name.setVisible(true);

                    }
                }
                else
                {
                    Member_Dependent_Name.setText(null);
                }
              
                 Object[] obj22 = (Object[]) new StaticSentence(m_app.getSession(), "select playsports from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj22!=null){
                    if(obj22[0] !=null){
                        String kymsports =obj22[0].toString();
                        KymMember_sports.setText(kymsports);
                        KymMember_sports.setVisible(true);

                    }
                }
                else
                {
                    KymMember_sports.setText(null);
                }
                Object[] obj23 = (Object[]) new StaticSentence(m_app.getSession(), "select specialtalent from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj23!=null){
                    if(obj23[0] !=null){
                        String kymtalent =obj23[0].toString();
                        KymMember_spcltalent.setText(kymtalent);
                        KymMember_spcltalent.setVisible(true);

                    }
                }
                else
                {
                    KymMember_spcltalent.setText(null);
                }
                Object[] obj24 = (Object[]) new StaticSentence(m_app.getSession(), "select emailid from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj24!=null){
                    if(obj24[0] !=null){
                        String kymemailid =obj24[0].toString();
                        KymMember_EmailID.setText(kymemailid);
                        KymMember_EmailID.setVisible(true);

                    }
                }
                else
                {
                    KymMember_EmailID.setText(null);
                }
                 Object[] obj25 = (Object[]) new StaticSentence(m_app.getSession(), "select proffesion from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj25!=null){
                    if(obj25[0] !=null){
                        String kymprofession =obj25[0].toString();
                        KymMember_Profession.setText(kymprofession);
                        KymMember_Profession.setVisible(true);

                    }
                }
                else
                {
                    KymMember_Profession.setText(null);
                }
                Object[] obj26 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT dateofmarriage FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj26!=null){
                    if(obj26[0] !=null){
                        String memberOrgdom = obj26[0].toString();
                        Date Kymdateofmarriage;
                        Kymdateofmarriage =(Date) formatter.parse(memberOrgdom);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgdom = sdfDestination.format(Kymdateofmarriage);
                        KymMember_date_of_marriage.setText(memberOrgdom);
                        KymMember_date_of_marriage.setVisible(true);
                    }
                }
                else
                {
                    KymMember_date_of_marriage.setText(null);
                }
                Object[] obj27 = (Object[]) new StaticSentence(m_app.getSession(), "select mothername from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj27!=null){
                    if(obj27[0] !=null){
                        String kymmothername =obj27[0].toString();
                        KymMember_MotherName.setText(kymmothername);
                        KymMember_MotherName.setVisible(true);

                    }
                }
                else
                {
                    KymMember_MotherName.setText(null);
                }
                Object[] obj28 = (Object[]) new StaticSentence(m_app.getSession(), "select fathername from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj28!=null){
                    if(obj28[0] !=null){
                        String kymfathername =obj28[0].toString();
                        KymMember_FatherName.setText(kymfathername);
                        KymMember_FatherName.setVisible(true);

                    }
                }
                else
                {
                    KymMember_FatherName.setText(null);
                }
                Object[] obj29 = (Object[]) new StaticSentence(m_app.getSession(), "select mobiletype from kymmember where memberno =  ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj29!=null){
                    if(obj29[0] !=null){
                        String kymmobileType =obj29[0].toString();
                        KymMember_MobileType.setText(kymmobileType);
                        KymMember_MobileType.setVisible(true);

                    }
                }
                else
                {
                    KymMember_MobileType.setText(null);
                }
                Object[] obj30 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT motherdob FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj30!=null){
                    if(obj30[0] !=null){
                        String memberOrgmotherdob = obj30[0].toString();
                        Date Kymmotherdob;
                        Kymmotherdob =(Date) formatter.parse(memberOrgmotherdob);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgmotherdob = sdfDestination.format(Kymmotherdob);
                        KymMember_mother_date_of_birth.setText(memberOrgmotherdob);
                        KymMember_mother_date_of_birth.setVisible(true);
                    }
                }
                else
                {
                    KymMember_mother_date_of_birth.setText(null);
                }
                Object[] obj31 = (Object[]) new StaticSentence(m_app.getSession(), "SELECT fatherdob FROM kymmember where memberno=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(memno);
                if(obj31!=null){
                    if(obj31[0] !=null){
                        String memberOrgfatherdom = obj31[0].toString();
                        Date Kymfatherdob;
                        Kymfatherdob =(Date) formatter.parse(memberOrgfatherdom);
                        SimpleDateFormat sdfDestination = new SimpleDateFormat("dd-MM-yyyy");
                        memberOrgfatherdom = sdfDestination.format(Kymfatherdob);
                        KymMember_father_date_of_marriage.setText(memberOrgfatherdom);
                        KymMember_father_date_of_marriage.setVisible(true);
                    }
                }
                else
                {
                    KymMember_father_date_of_marriage.setText(null);
                }
                 Object[] obj32 = (Object[]) new StaticSentence(m_app.getSession(),
                  "SELECT DEPENDENTDETAILS FROM KYMMEMBER where memberno=? ", 
                  SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING  })).find(memno); 
            
               ChildrenClassList = new ArrayList<ChildrenClass>();
               
               if(obj32!=null){
                   if(obj32[0]!=null){
                        String DependentDetailstr=obj32[0].toString();
                        String Classlevelstr[] = DependentDetailstr.split("#");
                        for(int i=0;i<Classlevelstr.length;i++){
                            String ChildLevel=Classlevelstr[i].toString();
                            String ChildArr[]=ChildLevel.split("%");
                            
                            if(ChildArr.length>0){
                                String name=ChildArr[0].toString();
                                kymdependent.setText(name);
                                kymdependent.setVisible(true);
                            }
                        }
                   }
               }
                else
                {
                    kymdependent.setText(null);
                }
                
                
                
                
                String Kymcity = GetCity(m_app);
                City.setText(Kymcity);
                City.setVisible(true);

                String Kympostal = GetPostal(m_app);
                Postal.setText(Kympostal);
                Postal.setVisible(true);

                String x1 = GetAddress(m_app);
                Xarr.setText(x1);
                Xarr.setVisible(true);

                List<Object> kymfacility = getFacilityList(m_app);
                Facilitylist1_JList.setVisible(true);
                Facilitylist1_JList.setModel(new MemberCompareMenu.ItemsListModel(kymfacility));

                List memberfacility = getFacilitylist();
                Facilitylist.setModel(new MemberCompareMenu.ItemsListModel(memberfacility));
                Facilitylist.setVisible(true);

            }

            catch(BasicException e){
               e.printStackTrace();
            }
            catch (ParseException ex) {
                ex.printStackTrace();
            }

        }
        else
        {
            ErrorMessage.setText("No Match Found");
            ErrorMessage.setForeground(Color.RED);
            ErrorMessage.setText(null);
            Membername.setVisible(false);
            Membername.setText(null);
            kymmembername1.setVisible(false);
            kymmembername1.setText(null);
            MemberAddress.setVisible(false);
            MemberAddress.setText(null);
            MemberMobile.setVisible(false);
            Kymmobileno.setVisible(false);
            Kymmobileno.setText(null);
            MemberPhone.setVisible(false);
            KYMRESNO.setVisible(false);
            KYMRESNO.setText(null);
            MemberCity.setVisible(false);
            MemberCity.setText(null);
            Memberpostal.setVisible(false);
            Memberpostal.setText(null);
            Memberdob.setVisible(false);
            KymMemberdateofbirth.setVisible(false);
            Memberjoindate.setVisible(false);
            KymMembersince.setVisible(false);
            Membermemtype.setVisible(false);
            Kymmembertype.setVisible(false);
            Kymmembertype.setText(null);
            City.setVisible(false);
            Postal.setVisible(false);
            Postal.setText(null);
            Xarr.setVisible(false);
            Xarr.setText(null);
            City.setText(null);
            MemberPhone.setText(null);
            MemberMobile.setText(null);
            Memberdob.setText(null);
            KymMemberdateofbirth.setText(null);
            Memberjoindate.setText(null);
            KymMembersince.setText(null);
            KYMMember_Spouse_Name.setVisible(false);
            KYMMember_Spouse_Name.setText(null);
            Member_Spouse_Name.setVisible(false);
            Member_Spouse_Name.setText(null);
            Member_Dependent_Name.setText(null);
            Member_Dependent_Name.setVisible(false);
            kymdependent.setText(null);
            kymdependent.setVisible(false);
            KymMember_sports.setText(null);
            KymMember_sports.setVisible(false);
            KymMember_spcltalent.setText(null);
            KymMember_spcltalent.setVisible(false);
            KymMember_EmailID.setText(null);
            KymMember_EmailID.setVisible(false);
            KymMember_date_of_marriage.setText(null);
            KymMember_date_of_marriage.setVisible(false);
            KymMember_MotherName.setText(null);
            KymMember_MotherName.setVisible(false);
            KymMember_MobileType.setText(null);
            KymMember_MobileType.setVisible(false);
            KymMember_father_date_of_marriage.setText(null);
            KymMember_father_date_of_marriage.setVisible(false);
            KymMember_mother_date_of_birth.setText(null);
            KymMember_mother_date_of_birth.setVisible(false);
            Facilitylist.setModel(new MemberCompareMenu.ItemsListModel(null));
            Facilitylist.setVisible(false);
            Facilitylist1_JList.setVisible(false);
            Facilitylist1_JList.setModel(new MemberCompareMenu.ItemsListModel(null));
            memberMother_name.setVisible(false);
            KymMember_Profession.setVisible(false);
            KymMember_FatherName.setVisible(false);
            MOTHER_DOB_MEMBER.setVisible(false);
            member_father_name.setVisible(false);
            mem_father_dob.setVisible(false);
            member_dom.setVisible(false);
            member_profeesion.setVisible(false);
            member_mobiletype.setVisible(false);
            member_emailid.setVisible(false);
            member_sports.setVisible(false);
            member_special_talent.setVisible(false);
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel City;
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JList Facilitylist;
    private javax.swing.JList Facilitylist1_JList;
    private javax.swing.JLabel KYMMEMBERTYPE;
    private javax.swing.JLabel KYMMOBILENO;
    private javax.swing.JLabel KYMMember_Spouse_Name;
    private javax.swing.JLabel KYMRESNO;
    private javax.swing.JLabel KymMember_EmailID;
    private javax.swing.JLabel KymMember_FatherName;
    private javax.swing.JLabel KymMember_MobileType;
    private javax.swing.JLabel KymMember_MotherName;
    private javax.swing.JLabel KymMember_Profession;
    private javax.swing.JLabel KymMember_date_of_marriage;
    private javax.swing.JLabel KymMember_father_date_of_marriage;
    private javax.swing.JLabel KymMember_mother_date_of_birth;
    private javax.swing.JLabel KymMember_spcltalent;
    private javax.swing.JLabel KymMember_sports;
    private javax.swing.JLabel KymMember_sports2;
    private javax.swing.JLabel KymMemberdateofbirth;
    private javax.swing.JLabel KymMembersince;
    private javax.swing.JLabel Kymmembertype;
    private javax.swing.JLabel Kymmobileno;
    private javax.swing.JLabel MOTHER_DOB_MEMBER;
    private javax.swing.JTextArea MemberAddress;
    private javax.swing.JLabel MemberCity;
    private javax.swing.JLabel MemberMemType;
    private javax.swing.JLabel MemberMobile;
    private javax.swing.JLabel MemberName;
    private javax.swing.JLabel MemberPhone;
    private javax.swing.JLabel MemberPostal;
    private javax.swing.JLabel Member_Dependent_Name;
    private javax.swing.JLabel Member_Spouse_Name;
    private javax.swing.JLabel Memberdob;
    private javax.swing.JLabel Memberjoindate;
    private javax.swing.JLabel Membermemtype;
    private javax.swing.JLabel Membername;
    private javax.swing.JLabel Membername10;
    private javax.swing.JLabel Membername11;
    private javax.swing.JLabel Membername12;
    private javax.swing.JLabel Membername13;
    private javax.swing.JLabel Membername14;
    private javax.swing.JLabel Membername15;
    private javax.swing.JLabel Membername16;
    private javax.swing.JLabel Membername2;
    private javax.swing.JLabel Membername3;
    private javax.swing.JLabel Membername4;
    private javax.swing.JLabel Membername5;
    private javax.swing.JLabel Membername6;
    private javax.swing.JLabel Membername7;
    private javax.swing.JLabel Membername8;
    private javax.swing.JLabel Membername9;
    private javax.swing.JLabel Memberpostal;
    private javax.swing.JLabel Postal;
    private javax.swing.JTextArea Xarr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel city;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel kymMemberName;
    private javax.swing.JLabel kymdependent;
    private javax.swing.JLabel kymmembername1;
    private javax.swing.JLabel mem_father_dob;
    private javax.swing.JLabel memberMother_name;
    private javax.swing.JLabel member_dom;
    private javax.swing.JLabel member_emailid;
    private javax.swing.JLabel member_father_name;
    private javax.swing.JLabel member_mobiletype;
    private javax.swing.JLabel member_profeesion;
    private javax.swing.JLabel member_special_talent;
    private javax.swing.JLabel member_sports;
    private javax.swing.JLabel postal;
    // End of variables declaration//GEN-END:variables
public String getTitle() {
        
        return "Member Comparision Menu";
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public void activate() throws BasicException {
       Membername.setVisible(false);
       kymmembername1.setVisible(false);
       MemberAddress.setVisible(false);
       Memberjoindate.setVisible(false);
       KymMembersince.setVisible(false);
       Membermemtype.setVisible(false);
       Kymmembertype.setVisible(false);
       MemberMobile.setVisible(false);
       MemberPhone.setVisible(false);
       MemberCity.setVisible(false);
       Memberdob.setVisible(false);
       Memberpostal.setVisible(false);
       Xarr.setVisible(false);
       Kymmobileno.setVisible(false);
       KYMRESNO.setVisible(false);
       City.setVisible(false);
       KymMemberdateofbirth.setVisible(false);
       Postal.setVisible(false);
       Member_Spouse_Name.setVisible(false);
       KYMMember_Spouse_Name.setVisible(false);
       Member_Dependent_Name.setVisible(false);
       kymdependent.setVisible(false);
       KymMember_sports.setVisible(false);
       KymMember_spcltalent.setVisible(false);
       KymMember_EmailID.setVisible(false);
       KymMember_date_of_marriage.setText(null);
       KymMember_MotherName.setVisible(false);
       KymMember_MobileType.setVisible(false);
       KymMember_father_date_of_marriage.setVisible(false);
       KymMember_mother_date_of_birth.setVisible(false);
       Facilitylist1_JList.setVisible(false);
       Facilitylist.setVisible(false);
       memberMother_name.setVisible(false);
       KymMember_FatherName.setVisible(false);
       KymMember_Profession.setVisible(false);
       MOTHER_DOB_MEMBER.setVisible(false);
       member_father_name.setVisible(false);
       mem_father_dob.setVisible(false);
       member_dom.setVisible(false);
       member_profeesion.setVisible(false);
       member_mobiletype.setVisible(false);
       member_emailid.setVisible(false);
       member_sports.setVisible(false);
       member_special_talent.setVisible(false);
    }

    public boolean deactivate() {
       
        return true;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComponent getComponent() {
        return this;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void init(AppView app) throws BeanFactoryException {
       this.m_app=app;
        
    }

    public Object getBean() {
        return this;
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void reset() {
       
    }
   
public String GetCity(AppView app) throws BasicException
{       
            String memno;
            memno = jTextField1.getText().trim();
            String city1=null;
            Object  o = null;
            o  =  new StaticSentence(app.getSession(), "SELECT RESADDRESS FROM KYMMEMBER WHERE memberno=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(memno);
           if(o!=null)
          {
              String x = o.toString();  
              
              String Xarr[] = x.split("#");
              
              for(int i=0;i<Xarr.length;i++)
              {
                  city1 = Xarr[4].toString();
                  
              }
              return city1;
          }
          else
          {
              return city1;
          }
}
          public String GetPostal(AppView app) throws BasicException
          {
          
          String postal1 = null;
          String memno;
          memno = jTextField1.getText().trim();
          Object  o = null;
          o  =  new StaticSentence(app.getSession(), "SELECT RESADDRESS FROM KYMMEMBER WHERE memberno=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(memno);
          if(o!=null)
          {
              String x = o.toString();  
              String Xarr[] = x.split("#");
              
              for(int i=0;i<Xarr.length;i++)
              {
                  postal1=Xarr[6].toString();
              }
              return postal1;
          }
          else
          {
              return postal1;
          }
      }
          public String GetAddress(AppView app) throws BasicException
          {
          
           String line1="";
           String line2="";
           String line3="" ;
           String city="";
           String state="";
           String country="";
           String postal="";
           String memno;
           String resaddress1 = null;
          memno = jTextField1.getText().trim();
          Object  o = null;
          o  =  new StaticSentence(app.getSession(), "SELECT RESADDRESS FROM KYMMEMBER WHERE memberno=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(memno);
          if(o!=null)
          {
           String x = o.toString();  
           String[] Xarr = x.split("#");   
           for(int i = 0;i<Xarr.length;i++)
           {
               line1 = Xarr[0].toString();
               line2 = Xarr[1].toString();
               line3 = Xarr[2].toString();
               city = Xarr[3].toString();
               state = Xarr[4].toString();
               country = Xarr[5].toString();
               postal = Xarr[6].toString();
               
           }
           String x1 = line1 +"\n"+line2 +"\n"+line3+"\n"+city+"\n"+state+"\n"+country+"\n"+postal+"\n";
           return x1;
           
          }
          else
          {
               return null;  
          }
          }
          
          
          public List<Object> getFacilityList(AppView app) throws BasicException
          {
          List<Object> FacilityList1 = new ArrayList<Object>();
          String memno;
          memno = jTextField1.getText().trim();
          Object  o = null;
          o  =  new StaticSentence(app.getSession(), "SELECT facilities FROM KYMMEMBER WHERE memberno=?  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).find(memno);
          if(o!=null)
          {
           String x = o.toString();  
           String [] Xarr11 = x.split("#");   
           for(int i = 0;i<Xarr11.length;i++)
           {
               FacilityList1.add(Xarr11[i].toString());
           }
           return FacilityList1;
           
          }
          else
          {
               return FacilityList1;  
          }
          }
          
          
         
          public List getFacilitylist() throws  BasicException{
          List<Object> Temp = new ArrayList<Object>();
          String memno;
          memno = jTextField1.getText().trim();
          Temp =  new StaticSentence(m_app.getSession(), " select m.name from memfacilityusage f , customers c , facility m where f.memno=c.id and f.facilitytype=m.id and  f.active=1 and c.searchkey=? order by m.name ", SerializerWriteString.INSTANCE,SerializerReadString.INSTANCE).list(memno); 
          return Temp;
          }
          
          
          private class ItemsListModel extends AbstractListModel {
          private java.util.List items;
          public ItemsListModel(java.util.List items) {
          this.items = items;
          }
          @Override
          public int getSize() {
           return items.size();
          }
          @Override
          public Object getElementAt(int i) {
            return items.get(i);
        }
     }    
          
}
              

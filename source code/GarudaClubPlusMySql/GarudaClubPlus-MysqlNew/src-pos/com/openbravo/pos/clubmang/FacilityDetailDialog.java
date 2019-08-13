package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.beans.JCalendarDialog;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Transaction;
import com.openbravo.format.Formats;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.customers.DataLogicCustomers;
import com.openbravo.pos.forms.AppView;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author swathi
 */
public class FacilityDetailDialog extends javax.swing.JDialog {

    /** Creates new form FacilityDetailDialog */
    private AppView m_App;
    private DataLogicFacilities dmang;
    private MembersFacilityTableModel.Facilityline detail;
    private CustomerInfo cinfo;
    private ComboBoxValModel depmodel;
    private FacilityManagerLogic fmLogic;

    public FacilityDetailDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dname.setEditable(false);
    }

    protected FacilityDetailDialog(Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dname.setEditable(false);
    }

    public FacilityDetailDialog(Frame parent, AppView app) {
        this(parent, true);
        this.m_App = app;
    }

    public FacilityDetailDialog(Dialog parent, AppView app) {
        this(parent, true);
        this.m_App = app;
    }
    /*   public FacilityDetailDialog(Frame parent) {
    this(parent, true);
    //this.m_App=app;
    }

    public FacilityDetailDialog(Dialog parent) {
    this(parent, true);
    //this.m_App=app;
    }*/

    protected static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    public static FacilityDetailDialog getDialog(Component parent, AppView app) {

        Window window = getWindow(parent);

        FacilityDetailDialog ct;

        if (window instanceof Frame) {
            ct = new FacilityDetailDialog((Frame) window, app);
        } else {
            ct = new FacilityDetailDialog((Dialog) window, app);
        }

        return ct;
    }
    /*
    public static FacilityDetailDialog getDialog(Component parent) {

    Window window = getWindow(parent);

    FacilityDetailDialog ct;

    if (window instanceof Frame) {
    ct = new FacilityDetailDialog((Frame) window);
    } else {
    ct = new FacilityDetailDialog((Dialog) window);
    }

    return ct;
    }*/
    /*  private void setVisibility(){
    //   jEditorKeys1.setVisible(false);
    //   jEditorString11.setVisible(false);
    jLabel11.setVisible(false);
    jlable15.setVisible(false);
    m_date.setVisible(false);
    jButton1.setVisible(false);
    // jLabel12.setVisible(false);
    //  jEditorIntegerPositive1.setVisible(false); ...
    // jButton4.setVisible(false);...
    enter.setVisible(false);
    }*/

    public void showDialog(MembersFacilityTableModel.Facilityline mfmodel, int row, CustomerInfo cinfo, DataLogicFacilities dlfac) throws BasicException {
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        fmLogic = new FacilityManagerLogic(m_App);
        dmang = dlfac;
        jEditorString11.addEditorKeys(jEditorKeys1);
        jEditorString11.activate();
        jComboBox1.setVisible(false);
        dname.setVisible(true);
        if (mfmodel.getUserID() != null) {
            dname.setText(mfmodel.getUserName());
        } else {
            dname.setVisible(false);
            jLabel12.setVisible(false);
        }
        // jEditorKeys1.setEnabled(false);
        //  jEditorString11.setEnabled(false);
        jSpinner1.setModel(new SpinnerNumberModel(1, 1, 20, 1));
        // setVisibility();
        dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        this.cinfo = cinfo;
        detail = mfmodel;
        jButton1.setVisible(false);
        Object name = mfmodel.getfname();
        Object jfee = mfmodel.getJoinFee();
        Object rfee = mfmodel.getRenewalFee();
        Object ufee = mfmodel.getUsageFee();
        Object rperiod1 = mfmodel.getRPeriod();
        Object sdate1 = mfmodel.getsdate();
        Object lbdate = mfmodel.getlbilldate();
        Object status = mfmodel.getStatus();
        if (name != null) {
            fname.setText(name.toString());
        }
        if (jfee != null) {
            jamt.setText(jfee.toString());
        } else {
            jamt.setText("0.00");
        }
        if (rfee != null) {
            ramt.setText(rfee.toString());
        } else {
            ramt.setText("0.00");
        }
        if (ufee != null) {
            uamt.setText(ufee.toString());
        } else {
            uamt.setText("0.00");
        }
        if (rperiod1 != null) {
            rperiod.setText(rperiod1.toString());
        } else {
            jLabel10.setVisible(false);
            rperiod.setVisible(false);
        }
        if (sdate1 != null) {
            jPanel6.setVisible(true);
            sdate.setText(Formats.DATE.formatValue(sdate1));
            if (lbdate != null) {
                lbillon.setVisible(true);
                jLabel9.setVisible(true);
                lbillon.setText(Formats.DATE.formatValue(lbdate));
            } else {
                lbillon.setVisible(false);
                //lbillon.setText(null);
                jLabel9.setVisible(false);
            }
        } else {
            jPanel6.setVisible(false);
        }

        /* sfrom.setVisible(false);
        sto.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);*/
        jPanel3.setVisible(false);
        jPanel5.setVisible(false);
        jPanel7.setVisible(false);
        //  start.setVisible(false);
        if (status != null) {
            switch (Integer.parseInt(status.toString())) {
                case 0:   //facility already started
                    stop.setVisible(true);
                    suspend.setVisible(true);
                    start.setVisible(false);
                    withdraw.setVisible(false);
                    requestbtn.setVisible(true);
                    //jButton1.setVisible(true);
                    break;
                case 1:
                    stop.setVisible(true);
                    suspend.setVisible(false);
                    start.setVisible(true);
                    withdraw.setVisible(false);
                    requestbtn.setVisible(true);
                    //jButton1.setVisible(false);
                    break;
                case 3:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    withdraw.setVisible(true);
                    withdraw.setText("withdraw facility stopage request");
                    requestbtn.setVisible(false);
                    break;
                case 4:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    withdraw.setVisible(true);
                    withdraw.setText("withdraw facility suspension request");
                    requestbtn.setVisible(false);
                    break;
                case 5:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    withdraw.setVisible(true);
                    withdraw.setText("withdraw facility start request");
                    requestbtn.setVisible(false);
                    break;
            }
        }
        /* if(status.toString().equals("0")){
        stop.setVisible(true);
        suspend.setVisible(true);
        start.setVisible(false);
        }else if(status.toString().equals("1")){
        start.setVisible(true);
        stop.setVisible(true);
        suspend.setVisible(false);
        } */
        requestbtn.setEnabled(false);
        setVisible(true);

    }
//praveen: added to deactivate  standard facility
    //start-------

    public void showDialogOfStandardFacility(MembersFacilityTableModel.Facilityline mfmodel, int row, CustomerInfo cinfo, DataLogicFacilities dlfac) throws BasicException {
        fmLogic = new FacilityManagerLogic(m_App);
        dmang = dlfac;
        requestbtn.setVisible(false);
        withdraw.setVisible(false);
        jEditorString11.addEditorKeys(jEditorKeys1);
        jEditorString11.activate();
        jComboBox1.setVisible(false);
        dname.setVisible(true);
        if (mfmodel.getUserID() != null) {
            dname.setText(mfmodel.getUserName());
        } else {
            dname.setVisible(false);
            jLabel12.setVisible(false);
        }
        // jEditorKeys1.setEnabled(false);
        //  jEditorString11.setEnabled(false);
        jSpinner1.setModel(new SpinnerNumberModel(1, 1, 20, 1));
        // setVisibility();
        dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        this.cinfo = cinfo;
        detail = mfmodel;
        jButton1.setVisible(false);
        Object name = mfmodel.getfname();
        Object jfee = mfmodel.getJoinFee();
        Object rfee = mfmodel.getRenewalFee();
        Object ufee = mfmodel.getUsageFee();
        Object rperiod1 = mfmodel.getRPeriod();
        Object sdate1 = mfmodel.getsdate();
        Object lbdate = mfmodel.getlbilldate();
        Object status = mfmodel.getStatus();
        if (name != null) {
            fname.setText(name.toString());
        }
        if (jfee != null) {
            jamt.setText(jfee.toString());
        } else {
            jamt.setText("0.00");
        }
        if (rfee != null) {
            ramt.setText(rfee.toString());
        } else {
            ramt.setText("0.00");
        }
        if (ufee != null) {
            uamt.setText(ufee.toString());
        } else {
            uamt.setText("0.00");
        }
        if (rperiod1 != null) {
            rperiod.setText(rperiod1.toString());
        } else {
            jLabel10.setVisible(false);
            rperiod.setVisible(false);
        }
        if (sdate1 != null) {
            jPanel6.setVisible(true);
            sdate.setText(Formats.DATE.formatValue(sdate1));
            if (lbdate != null) {
                lbillon.setVisible(true);
                jLabel9.setVisible(true);
                lbillon.setText(Formats.DATE.formatValue(lbdate));
            } else {
                lbillon.setVisible(false);
                //lbillon.setText(null);
                jLabel9.setVisible(false);
            }
        } else {
            jPanel6.setVisible(false);
        }

        /* sfrom.setVisible(false);
        sto.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);*/
        jPanel3.setVisible(false);
        jPanel5.setVisible(false);
        jPanel7.setVisible(false);
        //  start.setVisible(false);
        if (status != null) {
            switch (Integer.parseInt(status.toString())) {
                case 0:   //facility already started
                    stop.setVisible(true);
                    suspend.setVisible(true);
                    start.setVisible(false);
                    //withdraw.setVisible(false);
                    jButton4.setVisible(false);
                    //requestbtn.setVisible(true);
                    jButton3.setVisible(true);

                    //jButton1.setVisible(true);
                    break;
                case 1:
                    stop.setVisible(true);
                    suspend.setVisible(false);
                    start.setVisible(true);
                    jButton4.setVisible(false);
                    jButton3.setVisible(true);
                    //jButton1.setVisible(false);
                    break;
                case 3:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    jButton4.setVisible(true);
                    jButton4.setText("withdraw stop req.");
                    jButton3.setVisible(false);
                    break;
                case 4:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    jButton4.setVisible(true);
                    jButton4.setText("withdraw suspn. req.");
                    jButton3.setVisible(false);
                    break;
                case 5:
                    stop.setVisible(false);
                    suspend.setVisible(false);
                    start.setVisible(false);
                    jButton4.setVisible(true);
                    jButton4.setText("withdraw start req.");
                    jButton3.setVisible(false);
                    break;
            }
        }
        /* if(status.toString().equals("0")){
        stop.setVisible(true);
        suspend.setVisible(true);
        start.setVisible(false);
        }else if(status.toString().equals("1")){
        start.setVisible(true);
        stop.setVisible(true);
        suspend.setVisible(false);
        } */
        jButton3.setEnabled(true);
        setVisible(true);
    }

   //end----------

    private void calculateSuspensionToDate() throws BasicException {
        FacilityLogic flogic = new FacilityLogic(dmang);
        Periodicity p = dmang.getPerioicitybyid(detail.getRperiodId());
        Calendar cal = Calendar.getInstance();
        Date date = (Date) Formats.TIMESTAMP.parseValue(sfrom.getText());
        cal.setTimeInMillis(date.getTime());
        int billabledate = cal.get(Calendar.DATE);
        if (p.getdoj() == false) {
            cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
        }
        date.setTime(cal.getTimeInMillis());
        billabledate = cal.get(Calendar.DATE);
        date.setTime(flogic.calculateEndDate1(date, p, billabledate, Integer.valueOf(jSpinner1.getModel().getValue().toString())).getTime());
        sto.setText(Formats.DATE.formatValue(date));
    }

    public void showDialog1(MembersFacilityTableModel.Facilityline mfmodel, int row, CustomerInfo cinfo, DataLogicFacilities dlfac) throws BasicException {
        // jEditorString11.addEditorKeys(jEditorKeys1);
        //   jEditorString11.activate();
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        fmLogic = new FacilityManagerLogic(m_App);
        dmang = dlfac;
        dname.setVisible(false);
        if (mfmodel.getApplicableTo() == 1) {
            jComboBox1.setVisible(true);
            List dlist = dmang.getOtherDependentsOfMember().list(new Object[]{cinfo.getId(), mfmodel.getFid()});
            depmodel = new ComboBoxValModel(dlist);
            jComboBox1.setModel(depmodel);
            jComboBox1.setSelectedIndex(-1);
        } else {
            jComboBox1.setVisible(false);
            jLabel12.setVisible(false);
        }
        dmang = (DataLogicFacilities) m_App.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
        detail = mfmodel;
        this.cinfo = cinfo;
        //dname.setText(mfmodel.getUserName());
        //jLabel6.setText("Start From");
      /*jLabel9.setVisible(false);
        sdate.setVisible(false);
        //        jLabel12.setVisible(false);
        jSpinner1.setVisible(false);
        jLabel6.setVisible(false);
        lbillon.setVisible(false);
        start.setSelected(true);
        jButton1.setVisible(false);
        jButton2.setVisible(true);*/
        jPanel6.setVisible(false);
        jPanel3.setVisible(false);
        jPanel5.setVisible(true);
        jPanel7.setVisible(false);
        Object name = mfmodel.getfname();
        Object jfee = mfmodel.getJoinFee();
        Object rfee = mfmodel.getRenewalFee();
        Object ufee = mfmodel.getUsageFee();
        Object rperiod1 = mfmodel.getRPeriod();
        /* jLabel11.setVisible(false);
        jEditorKeys1.setVisible(false);
        jEditorString11.setVisible(false);*/
        enter.setVisible(false);
        jlable15.setText("Start Facility From");
        // Object sdate1=mfmodel.getsdate();
        //Object lbdate=mfmodel.getlbilldate();
        //  Object status=mfmodel.getStatus();
        if (name != null) {
            fname.setText(name.toString());
        }
        if (jfee != null) {
            jamt.setText(jfee.toString());
        } else {
            jamt.setText("0.00");
        }
        if (rfee != null) {
            ramt.setText(rfee.toString());
        } else {
            ramt.setText("0.00");
        }
        if (ufee != null) {
            uamt.setText(ufee.toString());
        } else {
            uamt.setText("0.00");
        }
        if (rperiod1 != null) {
            rperiod.setText(rperiod1.toString());
        } else {
            jLabel10.setVisible(false);
            rperiod.setVisible(false);
        }

        /* sfrom.setVisible(false);
        sto.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);*/
        start.setVisible(true);
        stop.setVisible(false);
        suspend.setVisible(false);
        withdraw.setVisible(false);
        start.setSelected(true);
        setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        requestbtn = new javax.swing.JButton();
        withdraw = new javax.swing.JButton();
        enter = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        suspend = new javax.swing.JRadioButton();
        start = new javax.swing.JRadioButton();
        stop = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        m_date = new javax.swing.JTextField();
        jlable15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        sdate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lbillon = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        sfrom = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        sto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jEditorKeys1 = new com.openbravo.editor.JEditorKeys();
        jEditorString11 = new com.openbravo.editor.JEditorString1();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jamt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ramt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rperiod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        uamt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        dname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Image");

        requestbtn.setText("Send Request");
        requestbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestbtnActionPerformed(evt);
            }
        });

        withdraw.setText("Withdraw Stop Facility Request");
        withdraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawActionPerformed(evt);
            }
        });

        enter.setText("Enter");
        enter.setEnabled(false);
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });

        buttonGroup1.add(suspend);
        suspend.setText("Suspend Facility");
        suspend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspendActionPerformed(evt);
            }
        });

        buttonGroup1.add(start);
        start.setText("Start Facility");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        buttonGroup1.add(stop);
        stop.setText("Stop Facility");
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(suspend, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(stop, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suspend)
                    .addComponent(start)
                    .addComponent(stop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        m_date.setEditable(false);
        m_date.setMinimumSize(new java.awt.Dimension(100, 20));

        jlable15.setText("Date");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlable15, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(m_date, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlable15)
                    .addComponent(m_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setText("Started From ");

        sdate.setEditable(false);

        jLabel9.setText("Last Bill On");

        lbillon.setEditable(false);
        lbillon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbillonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbillon, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(lbillon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );

        jPanel3.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 123));

        jLabel7.setText("Suspended From");

        sfrom.setEditable(false);

        jLabel8.setText("Suspended To");

        sto.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel13.setText("No. Of Period");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sfrom)
                    .addComponent(sto, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(sfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(sto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Reason");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jEditorString11, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEditorKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEditorString11, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jEditorKeys1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Withdraw request");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Send Request");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(withdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(requestbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(380, 380, 380)
                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(withdraw, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(requestbtn)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jLabel1.setText("Facility Name");

        fname.setEditable(false);

        jLabel2.setText("Joining Fees");

        jamt.setEditable(false);

        jLabel3.setText("Renewal Fees");

        ramt.setEditable(false);

        jLabel10.setText("Period");

        rperiod.setEditable(false);

        jLabel4.setText("Usage Fees");

        uamt.setEditable(false);

        jLabel12.setText("Dependent");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ramt, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(uamt)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rperiod, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(56, 56, 56))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jamt)
                                .addGap(262, 262, 262))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fname)
                                .addGap(168, 168, 168)))
                        .addGap(10, 10, 10))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, Short.MAX_VALUE)
                        .addGap(464, 464, 464))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ramt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(rperiod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(uamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int flag;
    private void requestbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestbtnActionPerformed
        flag = 0;
        if (start.isSelected() == true) {
            try {
                if (detail.getApplicableTo() == 0 && m_date.getText() != null) {
                    flag = 1;
                } else if (detail.getApplicableTo() == 1 && m_date.getText() != null && jComboBox1.getSelectedIndex() != -1) {
                    flag = 2;
                }
                if (flag != 0) {
                    if (JOptionPane.showConfirmDialog(this, "Do you want to start the usage of this facility", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                String id = UUID.randomUUID().toString();
                                if (flag == 1) {
                                    Object[] values = new Object[]{id, cinfo.getId(), false, detail.getFid(), 5, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date()};
                                    fmLogic.AssignFacilityToMember(values);
                                } else {
                                    MemberDependent mdep = (MemberDependent) jComboBox1.getSelectedItem();
                                    Object[] values = new Object[]{id, cinfo.getId(), false, detail.getFid(), 5, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date(), mdep.getID()};
                                    fmLogic.AssignFacilityToMemberDependent(values);
                                }
                                Object[] values = new Object[]{UUID.randomUUID().toString(), id, 5, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date()};
                                fmLogic.sendStartRequest(values);
                                return null;
                            }
                        };
                        t.execute();
                        JOptionPane.showMessageDialog(null, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Incomplete Form", "Please fill the form completely", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (stop.isSelected() == true) {
            try {
                if (detail.getFtype().equals("Standard")) {
                    JOptionPane.showMessageDialog(this, "Sorry cannot stop a standard facility", "Cannot Stop", JOptionPane.OK_OPTION);
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Are you sure you want to stop the facility usage ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                Object[] Values = new Object[]{UUID.randomUUID().toString(), detail.getMid(), 3, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date(), jEditorString11.getText()};
                                fmLogic.sendStopRequest(Values);
                                return null;
                            }
                        };
                        t.execute();
                        JOptionPane.showMessageDialog(this, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (suspend.isSelected() == true) {
            try {
                if (sfrom.getText().length() > 0 && sto.getText().length() > 0 && jEditorString11.getText().length() > 0) {

                    if (JOptionPane.showConfirmDialog(this, "Are you sure you want do suspend this facility from " + sfrom.getText() + " to " + sto.getText(), null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                Date sfdate = (Date) Formats.DATE.parseValue(sfrom.getText());
                                Date stdate = (Date) Formats.DATE.parseValue(sto.getText());
                                Object[] Values = new Object[]{UUID.randomUUID().toString(), detail.getMid(), 4, sfdate, stdate, m_App.getAppUserView().getUser().getName(), new Date(), jEditorString11.getText()};
                                fmLogic.sendSuspendRequest(Values);
                                return null;
                            }
                        };
                        t.execute();

                        JOptionPane.showMessageDialog(this, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}//GEN-LAST:event_requestbtnActionPerformed

    private void withdrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to withdraw the request ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        new PreparedSentence(m_App.getSession(), "UPDATE MEMFACILITYUSAGE SET FACMANGREF=? WHERE ID=?",
                                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING})).exec(new Object[]{null, detail.getMid()});
                        new PreparedSentence(m_App.getSession(), "UPDATE FACILITYMANAGER SET APPROVEDBY=?,APPROVEDDATE=?,STATUS_=? WHERE ID=?",
                                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.BOOLEAN, Datas.STRING})).exec(new Object[]{m_App.getAppUserView().getUser().getId(), new Date(), false, detail.getFacilityManagerReference()});

                        return null;
                    }
                };
                t.execute();
                JOptionPane.showMessageDialog(null, "The request is withdrawn successfully", null, JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_withdrawActionPerformed

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
}//GEN-LAST:event_enterActionPerformed

    private void suspendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspendActionPerformed
        if (suspend.isSelected() == true) {
            jEditorString11.addEditorKeys(jEditorKeys1);
            jEditorString11.activate();
            jEditorString11.reset();
            jPanel7.setVisible(true);
            jPanel6.setVisible(true);
            jPanel3.setVisible(true);
            jPanel5.setVisible(false);
            requestbtn.setEnabled(true);
            jButton3.setEnabled(true);

            try {
                Date d = (Date) Formats.DATE.parseValue(lbillon.getText());
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(d.getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);
                cal.add(Calendar.DATE, 1);
                d.setTime(cal.getTimeInMillis());
                sfrom.setText(Formats.DATE.formatValue(d));
                calculateSuspensionToDate();
            } catch (BasicException ex) {
                ex.printStackTrace();
            }

        // jButton1.setVisible(true);
        }
    }//GEN-LAST:event_suspendActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Date date;
            try {
                date = (Date) Formats.DATE.parseValue(sfrom.getText());
            } catch (BasicException e) {
                date = null;
            }
            date = JCalendarDialog.showCalendar(this, date);
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                Date d = new Date();
                cal.setTimeInMillis(d.getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);
                cal1.setTimeInMillis(date.getTime());
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if (cal1.before(cal) || cal1.equals(cal)) {
                    JOptionPane.showMessageDialog(this, "Please select a new date  ", "Cannot Process-old date selected", JOptionPane.OK_OPTION);
                } else {
                    Periodicity p = dmang.getPerioicitybyid(detail.getRperiodId());
                    FacilityLogic flogic = new FacilityLogic(dmang);
                    /* if(detail.getlbilldate()==null){
                    d.setTime(detail.getsdate().getTime());
                    cal.setTimeInMillis(d.getTime());
                    }
                    else{
                    d.setTime(detail.getlbilldate().getTime());
                    cal.setTimeInMillis(d.getTime());
                    cal.add(Calendar.DATE, 1);
                    d.setTime(cal.getTimeInMillis());
                    }*/
                    if (p.getqbtype() == true) {//post billing
                        if (cal1.after(cal) || cal1.equals(cal)) {
                            sfrom.setText(Formats.DATE.formatValue(d));
                        } else {

                            JOptionPane.showMessageDialog(this, "Please select a date after " + Formats.DATE.formatValue(d), "Error", JOptionPane.OK_OPTION);
                        }
                    } else {
                        int billabledate = cal.get(Calendar.DATE);
                        if (p.getdoj() == false) {
                            cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
                        }
                        d.setTime(cal.getTimeInMillis());
                        billabledate = cal.get(Calendar.DATE);
                        //temp=new Date();
                        //  flogic.setTemp(d);
                        d.setTime(flogic.calculateEndDate1(d, p, billabledate, 1).getTime());
                        cal.setTimeInMillis(d.getTime());
                        if (cal1.before(cal) || cal1.equals(cal)) {
                            JOptionPane.showMessageDialog(this, "Please select a new date  ", "Please select a date after " + Formats.DATE.formatValue(d), JOptionPane.OK_OPTION);
                        } else {
                            sfrom.setText(Formats.DATE.formatValue(date));
                        }
                    }
                    calculateSuspensionToDate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        if (stop.isSelected() == true) {
            jlable15.setText("Stop Facility From");
            //jlable15.setVisible(true);
            m_date.setText(null);
            //jButton4.setVisible(true);
            //jEditorString11.setVisible(false);
            //jEditorKeys1.setVisible(false);
            jPanel7.setVisible(true);
            //jLabel11.setVisible(false);
            jPanel5.setVisible(true);
            jPanel6.setVisible(true);
            jPanel3.setVisible(false);
            enter.setVisible(false);
            requestbtn.setEnabled(true);
            jButton3.setEnabled(true);
            jButton2.setVisible(false);
            try {
                Date d = (Date) Formats.DATE.parseValue(lbillon.getText());
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(d.getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);
                cal.add(Calendar.DATE, 1);
                d.setTime(cal.getTimeInMillis());
                m_date.setText(Formats.DATE.formatValue(d));
            } catch (BasicException ex) {
                ex.printStackTrace();
            }
        /* if(sfrom.isEditable()==true){
        sfrom.setEditable(false);
        sfrom.setVisible(false);
        sto.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jButton1.setVisible(false);
        //               jLabel12.setVisible(false);
        //   jEditorIntegerPositive1.setVisible(false);
        }*/
        }
    }//GEN-LAST:event_stopActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        if (start.isSelected() == true) {
            jlable15.setText("Start Facility From");
            jPanel5.setVisible(true);
            m_date.setText(null);
            jPanel7.setVisible(false);
            jPanel6.setVisible(false);
            jPanel3.setVisible(false);
            enter.setVisible(false);
        }
    }//GEN-LAST:event_startActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        Object val = jSpinner1.getModel().getValue();
        try {
            calculateSuspensionToDate();
        } catch (Exception e) {
            jSpinner1.getModel().setValue(val);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Date date;
            try {
                date = (Date) Formats.DATE.parseValue(m_date.getText());
            } catch (BasicException e) {
                date = null;
            }
            date = JCalendarDialog.showCalendar(this, date);
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            if (date != null) {
                Date d = new Date();
                cal.setTimeInMillis(d.getTime());
                cal.set(Calendar.HOUR_OF_DAY, 00);
                cal.set(Calendar.MINUTE, 00);
                cal.set(Calendar.SECOND, 00);
                cal.set(Calendar.MILLISECOND, 00);
                cal1.setTimeInMillis(date.getTime());
                cal1.set(Calendar.HOUR_OF_DAY, 00);
                cal1.set(Calendar.MINUTE, 00);
                cal1.set(Calendar.SECOND, 00);
                cal1.set(Calendar.MILLISECOND, 00);
                if (cal1.before(cal) || cal1.equals(cal)) {
                    JOptionPane.showMessageDialog(this, "Please select a new date ", "Cannot Process-old date selected", JOptionPane.OK_OPTION);
                } else /* if(jlable15.getText().equals("Stop Facility From")){
                Periodicity p=dmang.getPerioicitybyid(detail.getRperiodId());
                FacilityLogic flogic=new FacilityLogic(dmang);
                if(detail.getlbilldate()==null){
                d.setTime(detail.getsdate().getTime());
                cal.setTimeInMillis(d.getTime());
                } else{
                d.setTime(detail.getlbilldate().getTime());
                cal.setTimeInMillis(d.getTime());
                cal.add(Calendar.DATE, 1);
                }
                if(p.getqbtype()==true){//post billing
                if(cal1.after(cal)|| cal1.equals(cal)){
                m_date.setText(Formats.DATE.formatValue(date));

                }else{
                d.setTime(cal.getTimeInMillis());
                JOptionPane.showMessageDialog(this, "Please select a date after "+Formats.DATE.formatValue(d), "Error", JOptionPane.OK_OPTION);
                }
                }else{
                int billabledate=cal.get(Calendar.DATE);
                if(p.getdoj()==false){
                cal.setTimeInMillis(flogic.getBillableDate(p, billabledate, cal).getTimeInMillis());
                }
                d.setTime(cal.getTimeInMillis());
                billabledate=cal.get(Calendar.DATE);
                //temp=new Date();
                //  flogic.setTemp(d);
                d.setTime(flogic.calculateEndDate1(d,p,billabledate,1).getTime());
                cal.setTimeInMillis(d.getTime());
                if(cal1.before(cal) || cal1.equals(cal)){
                JOptionPane.showMessageDialog(this, "Please select a new date  ", "Please select a date after "+Formats.DATE.formatValue(d), JOptionPane.OK_OPTION);
                }else
                m_date.setText(Formats.DATE.formatValue(date));
                }
                }else  if(jlable15.getText().equals("Start Facility From")){
                m_date.setText(Formats.DATE.formatValue(date));
                }*/ {
                    m_date.setText(Formats.DATE.formatValue(date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lbillonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbillonActionPerformed
    }//GEN-LAST:event_lbillonActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    }//GEN-LAST:event_jComboBox1ItemStateChanged


    //praveen: added buttons to activate or deactivate standard facility
    //start------
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        flag = 0;
        if (start.isSelected() == true) {
            try {
                if (detail.getApplicableTo() == 0 && m_date.getText() != null) {
                    flag = 1;
                }
                if (flag != 0) {
                    if (JOptionPane.showConfirmDialog(this, "Do you want to start the usage of this facility", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                String id = UUID.randomUUID().toString();
                                if (flag == 1) {
                                    Object[] values = new Object[]{id, cinfo.getId(), false, detail.getFid(), 5, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date()};
                                    fmLogic.AssignFacilityToMember(values);
                                }
                                Object[] values = new Object[]{UUID.randomUUID().toString(), id, 5, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date()};
                                fmLogic.sendStartRequest(values);
                                return null;
                            }
                        };
                        t.execute();
                        JOptionPane.showMessageDialog(null, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Incomplete Form", "Please fill the form completely", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (stop.isSelected() == true) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Are you sure you want to stop the facility usage ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Transaction t = new Transaction(m_App.getSession()) {

                        @Override
                        protected Object transact() throws BasicException {
                            Object[] Values = new Object[]{UUID.randomUUID().toString(), detail.getMid(), 3, Formats.TIMESTAMP.parseValue(m_date.getText()), m_App.getAppUserView().getUser().getName(), new Date(), jEditorString11.getText()};
                            fmLogic.sendStopRequest(Values);
                            return null;
                        }
                    };
                    t.execute();
                    JOptionPane.showMessageDialog(this, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                    dispose();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (suspend.isSelected() == true) {
            try {
                if (sfrom.getText().length() > 0 && sto.getText().length() > 0 && jEditorString11.getText().length() > 0) {

                    if (JOptionPane.showConfirmDialog(this, "Are you sure you want do suspend this facility from " + sfrom.getText() + " to " + sto.getText(), null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Transaction t = new Transaction(m_App.getSession()) {

                            @Override
                            protected Object transact() throws BasicException {
                                Date sfdate = (Date) Formats.DATE.parseValue(sfrom.getText());
                                Date stdate = (Date) Formats.DATE.parseValue(sto.getText());
                                Object[] Values = new Object[]{UUID.randomUUID().toString(), detail.getMid(), 4, sfdate, stdate, m_App.getAppUserView().getUser().getName(), new Date(), jEditorString11.getText()};
                                fmLogic.sendSuspendRequest(Values);
                                return null;
                            }
                        };
                        t.execute();

                        JOptionPane.showMessageDialog(this, "The request is sent for processing", null, JOptionPane.PLAIN_MESSAGE);
                        dispose();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to withdraw the request ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Transaction t = new Transaction(m_App.getSession()) {

                    @Override
                    protected Object transact() throws BasicException {
                        new PreparedSentence(m_App.getSession(), "DELETE FROM FACILITYMANAGER WHERE MFUID=? AND STATUS_ IS NULL",
                                new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{detail.getMid()});
                        if (detail.getStatus() == 3 || detail.getStatus() == 4) {
                            new PreparedSentence(m_App.getSession(), "UPDATE MEMFACILITYUSAGE SET STATUS_=?,FACMANGREF=? WHERE ID=?",
                                    new SerializerWriteBasic(new Datas[]{Datas.INT, Datas.STRING, Datas.STRING})).exec(new Object[]{0, null, detail.getMid()});

                        } else if (detail.getStatus() == 5) {
                            new PreparedSentence(m_App.getSession(), " DELETE FROM MEMFACILITYUSAGE WHERE ID=?",
                                    new SerializerWriteBasic(new Datas[]{Datas.STRING})).exec(new Object[]{detail.getMid()});

                        }


                        return null;

                    }
                };
                t.execute();
                JOptionPane.showMessageDialog(null, "The request is withdrawn successfully", null, JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //end-----------------

    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField dname;
    private javax.swing.JButton enter;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private com.openbravo.editor.JEditorKeys jEditorKeys1;
    private com.openbravo.editor.JEditorString1 jEditorString11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jamt;
    private javax.swing.JLabel jlable15;
    private javax.swing.JTextField lbillon;
    private javax.swing.JTextField m_date;
    private javax.swing.JTextField ramt;
    private javax.swing.JButton requestbtn;
    private javax.swing.JTextField rperiod;
    private javax.swing.JTextField sdate;
    private javax.swing.JTextField sfrom;
    private javax.swing.JRadioButton start;
    private javax.swing.JTextField sto;
    private javax.swing.JRadioButton stop;
    private javax.swing.JRadioButton suspend;
    private javax.swing.JTextField uamt;
    private javax.swing.JButton withdraw;
    // End of variables declaration//GEN-END:variables
}

package com.openbravo.pos.reports;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import com.openbravo.pos.forms.JPanelView;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppLocal;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.design.*;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.BaseSentence;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.user.EditorCreator;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.BeanFactoryApp;
import com.openbravo.pos.forms.BeanFactoryException;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.sales.TaxesLogic;
import com.openbravo.pos.ticket.StockDiaryReportType;
import com.openbravo.pos.util.JRViewer300;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class JPanelReport extends JPanel implements JPanelView, BeanFactoryApp {

    private JRViewer300 reportviewer = null;
    private JasperReport jr = null;
    private EditorCreator editor = null;
    protected AppView m_App;
    protected SentenceList taxsent;
    protected TaxesLogic taxeslogic;
    private DataLogicSales dlSales;

    /** Creates new form JPanelReport */
    public JPanelReport() {

        initComponents();
    }
    /*    public void initrep(){
    try {

    InputStream in = getClass().getResourceAsStream(getReport() + ".jasper");
    if (in == null) {
    // read and compile the report
    JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream(getReport() + ".jrxml"));
    jr = JasperCompileManager.compileReport(jd);
    } else {
    // read the compiled report
    ObjectInputStream oin = new ObjectInputStream(in);
    jr = (JasperReport) oin.readObject();
    oin.close();
    }
    } catch (Exception e) {
    MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreport"), e);
    msg.show(this);
    jr = null;
    }
    }*/

    public void init(AppView app, int temp) throws BeanFactoryException {

        m_App = app;
        if (temp != 1) {
            dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.forms.DataLogicSalesCreate");
            taxsent = dlSales.getTaxList();

            editor = getEditorCreator();
            if (editor instanceof ReportEditorCreator) {
                jPanelFilter.add(((ReportEditorCreator) editor).getComponent(), BorderLayout.CENTER);
            }

            reportviewer = new JRViewer300(null);

            add(reportviewer, BorderLayout.CENTER);
        }
        // initrep();
        try {
            String del = getReport();
            InputStream in = getClass().getResourceAsStream(getReport() + ".jasper");
            if (in == null) {
                // read and compile the report
                JasperDesign jd = JRXmlLoader.load(getClass().getResourceAsStream(getReport() + ".jrxml"));
                jr = JasperCompileManager.compileReport(jd);
            } else {
                // read the compiled report

                ObjectInputStream oin = new ObjectInputStream(in);
                jr = (JasperReport) oin.readObject();
                oin.close();
            }
        } catch (Exception e) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreport"), e);
            msg.show(this);
            jr = null;
        }
    }

    public Object getBean() {
        return this;
    }

    protected abstract String getReport();

    protected abstract String getResourceBundle();

    protected abstract BaseSentence getSentence();

    protected abstract ReportFields getReportFields();

    protected EditorCreator getEditorCreator() {
        return null;
    }

    public JComponent getComponent() {
        return this;
    }

    public void activate() throws BasicException {

        setVisibleFilter(true);
        taxeslogic = new TaxesLogic(taxsent.list());
    }

    public boolean deactivate() {

        reportviewer.loadJasperPrint(null);
        return true;
    }

    protected void setVisibleButtonFilter(boolean value) {
        jToggleFilter.setVisible(value);
    }

    protected void setVisibleFilter(boolean value) {
        jToggleFilter.setSelected(value);
        jToggleFilterActionPerformed(null);
    }

    private void launchreport() {
        int i = 0;
        m_App.waitCursorBegin();

        if (jr != null) {
            try {

                // Archivo de recursos
                String res = getResourceBundle();

                // Parametros y los datos
                Object params = (editor == null) ? null : editor.createValue();
                Map reportparams = new HashMap();
                reportparams.put("ARG", params);
                if (res != null) {
                    reportparams.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle(res));
                }
                reportparams.put("TAXESLOGIC", taxeslogic);
                reportparams.put("REPORT_CONNECTION", m_App.getSession().getConnection());
                // JParamsDatesInterval dateobj=new JParamsDatesInterval();

                Date date = (Date) JParamsDatesInterval.sstartdate;

                Date edate = (Date) JParamsDatesInterval.senddate;

                if (date != null) {
                    Timestamp ts = new Timestamp(date.getTime());
                    reportparams.put("startdate", ts);
                }
                if (edate != null) {
                    Timestamp ets = new Timestamp(edate.getTime());
                    reportparams.put("enddate", ets);//sameer: removed ets.getTime()
                }
                reportparams.put("product", StockDiaryReportType.pdtid);
                String cat = (String) StockDiaryReportType.category;
                reportparams.put("category", cat);
                //reportparams.put("Printcategory", cat);
                reportparams.put("pname", StockDiaryReportType.pdtname);
                reportparams.put("vendor", PurchasejournalReport.vendor);
                reportparams.put("rname", JParamsLocation.locationname);
                JRDataSource data = new JRDataSourceBasic(getSentence(), getReportFields(), params);
                // Construyo el mapa de los parametros.
                try {
                    Object[] obj = dlSales.getCompanyDetail(m_App.getSession().getDatabaseName());
                } catch (Exception e) {
                }
                String companyName = m_App.getSession().getCompanyName();
                String companyAddress = m_App.getSession().getCompanyAddress();
                reportparams.put("narration", JParamsDate.narration);
                //if(obj!=null)
                reportparams.put("companyname", companyName);
                reportparams.put("address", companyAddress);

                JasperPrint jp = JasperFillManager.fillReport(jr, reportparams, data);

                reportviewer.loadJasperPrint(jp);

                setVisibleFilter(false);

            } catch (SQLException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreportdata"), e);
                msg.show(this);
            } catch (MissingResourceException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadresourcedata"), e);
                msg.show(this);
            } catch (JRException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfillreport"), e);
                msg.show(this);
                e.printStackTrace();
            } catch (BasicException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadreportdata"), e);
                msg.show(this);
                e.printStackTrace();
            }
        }

        m_App.waitCursorEnd();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jPanelFilter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jToggleFilter = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout());

        jPanelHeader.setLayout(new java.awt.BorderLayout());

        jPanelFilter.setLayout(new java.awt.BorderLayout());
        jPanelHeader.add(jPanelFilter, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jToggleFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1downarrow.png"))); // NOI18N
        jToggleFilter.setSelected(true);
        jToggleFilter.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/1uparrow.png"))); // NOI18N
        jToggleFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleFilterActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleFilter);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/launch.png"))); // NOI18N
        jButton1.setText(AppLocal.getIntString("Button.ExecuteReport")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jPanelHeader.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(jPanelHeader, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        launchreport();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleFilterActionPerformed

        jPanelFilter.setVisible(jToggleFilter.isSelected());

    }//GEN-LAST:event_jToggleFilterActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelFilter;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JToggleButton jToggleFilter;
    // End of variables declaration//GEN-END:variables

}

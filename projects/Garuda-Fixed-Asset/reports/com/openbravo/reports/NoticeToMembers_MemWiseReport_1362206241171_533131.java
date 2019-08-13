/*
 * Generated by JasperReports - 02/03/13 12:07
 */
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import java.util.*;
import java.math.*;
import java.text.*;
import java.io.*;
import java.net.*;

import net.sf.jasperreports.engine.*;
import java.util.*;
import net.sf.jasperreports.engine.data.*;


/**
 *
 */
public class NoticeToMembers_MemWiseReport_1362206241171_533131 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_companyAddress = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_noticeName = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_designation = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_received = null;
    private JRFillParameter parameter_date = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_header2 = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_footer2 = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_header1 = null;
    private JRFillParameter parameter_pdues = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_duedate = null;
    private JRFillParameter parameter_totaldues = null;
    private JRFillParameter parameter_note = null;
    private JRFillParameter parameter_memno = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_paymentDate = null;
    private JRFillParameter parameter_generatedDate = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_companyName = null;
    private JRFillParameter parameter_footer = null;
    private JRFillParameter parameter_sign = null;
    private JRFillParameter parameter_memAddress = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_pbal = null;
    private JRFillParameter parameter_amountreceivable = null;
    private JRFillParameter parameter_membername = null;
    private JRFillParameter parameter_amountpayable = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillField field_memno = null;
    private JRFillField field_memaddr = null;
    private JRFillField field_slNo = null;
    private JRFillField field_payByDate = null;
    private JRFillField field_generatedDate = null;
    private JRFillField field_status = null;
    private JRFillField field_receivedRef = null;
    private JRFillField field_noticeName = null;
    private JRFillField field_recBy = null;
    private JRFillField field_commMode = null;
    private JRFillField field_date = null;
    private JRFillField field_type = null;
    private JRFillField field_orgamt = null;
    private JRFillField field_dateOfDispatch = null;
    private JRFillField field_amtreceived = null;
    private JRFillField field_recDate = null;
    private JRFillField field_dueAmount = null;
    private JRFillField field_memname = null;
    private JRFillField field_obtype = null;
    private JRFillField field_notes = null;
    private JRFillField field_memSHipNo = null;
    private JRFillField field_dueDate = null;
    private JRFillField field_fname = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_memReport_COUNT = null;
    private JRFillVariable variable_tdebit = null;
    private JRFillVariable variable_totalsdues = null;
    private JRFillVariable variable_detach = null;
    private JRFillVariable variable_paidby = null;
    private JRFillVariable variable_memsig = null;
    private JRFillVariable variable_amountdue = null;
    private JRFillVariable variable_memno = null;
    private JRFillVariable variable_cuthere = null;
    private JRFillVariable variable_memreq = null;
    private JRFillVariable variable_compu = null;
    private JRFillVariable variable_thanku = null;
    private JRFillVariable variable_bci = null;
    private JRFillVariable variable_tcredit = null;
    private JRFillVariable variable_amtbal = null;


    /**
     *
     */
    public void customizedInit(
        Map pm,
        Map fm,
        Map vm
        )
    {
        initParams(pm);
        initFields(fm);
        initVars(vm);
    }


    /**
     *
     */
    private void initParams(Map pm)
    {
        parameter_companyAddress = (JRFillParameter)pm.get("companyAddress");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_noticeName = (JRFillParameter)pm.get("noticeName");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_designation = (JRFillParameter)pm.get("designation");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_received = (JRFillParameter)pm.get("received");
        parameter_date = (JRFillParameter)pm.get("date");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_header2 = (JRFillParameter)pm.get("header2");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_footer2 = (JRFillParameter)pm.get("footer2");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_header1 = (JRFillParameter)pm.get("header1");
        parameter_pdues = (JRFillParameter)pm.get("pdues");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_duedate = (JRFillParameter)pm.get("duedate");
        parameter_totaldues = (JRFillParameter)pm.get("totaldues");
        parameter_note = (JRFillParameter)pm.get("note");
        parameter_memno = (JRFillParameter)pm.get("memno");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_paymentDate = (JRFillParameter)pm.get("paymentDate");
        parameter_generatedDate = (JRFillParameter)pm.get("generatedDate");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_companyName = (JRFillParameter)pm.get("companyName");
        parameter_footer = (JRFillParameter)pm.get("footer");
        parameter_sign = (JRFillParameter)pm.get("sign");
        parameter_memAddress = (JRFillParameter)pm.get("memAddress");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_pbal = (JRFillParameter)pm.get("pbal");
        parameter_amountreceivable = (JRFillParameter)pm.get("amountreceivable");
        parameter_membername = (JRFillParameter)pm.get("membername");
        parameter_amountpayable = (JRFillParameter)pm.get("amountpayable");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_memno = (JRFillField)fm.get("memno");
        field_memaddr = (JRFillField)fm.get("memaddr");
        field_slNo = (JRFillField)fm.get("slNo");
        field_payByDate = (JRFillField)fm.get("payByDate");
        field_generatedDate = (JRFillField)fm.get("generatedDate");
        field_status = (JRFillField)fm.get("status");
        field_receivedRef = (JRFillField)fm.get("receivedRef");
        field_noticeName = (JRFillField)fm.get("noticeName");
        field_recBy = (JRFillField)fm.get("recBy");
        field_commMode = (JRFillField)fm.get("commMode");
        field_date = (JRFillField)fm.get("date");
        field_type = (JRFillField)fm.get("type");
        field_orgamt = (JRFillField)fm.get("orgamt");
        field_dateOfDispatch = (JRFillField)fm.get("dateOfDispatch");
        field_amtreceived = (JRFillField)fm.get("amtreceived");
        field_recDate = (JRFillField)fm.get("recDate");
        field_dueAmount = (JRFillField)fm.get("dueAmount");
        field_memname = (JRFillField)fm.get("memname");
        field_obtype = (JRFillField)fm.get("obtype");
        field_notes = (JRFillField)fm.get("notes");
        field_memSHipNo = (JRFillField)fm.get("memSHipNo");
        field_dueDate = (JRFillField)fm.get("dueDate");
        field_fname = (JRFillField)fm.get("fname");
    }


    /**
     *
     */
    private void initVars(Map vm)
    {
        variable_PAGE_NUMBER = (JRFillVariable)vm.get("PAGE_NUMBER");
        variable_COLUMN_NUMBER = (JRFillVariable)vm.get("COLUMN_NUMBER");
        variable_REPORT_COUNT = (JRFillVariable)vm.get("REPORT_COUNT");
        variable_PAGE_COUNT = (JRFillVariable)vm.get("PAGE_COUNT");
        variable_COLUMN_COUNT = (JRFillVariable)vm.get("COLUMN_COUNT");
        variable_memReport_COUNT = (JRFillVariable)vm.get("memReport_COUNT");
        variable_tdebit = (JRFillVariable)vm.get("tdebit");
        variable_totalsdues = (JRFillVariable)vm.get("totalsdues");
        variable_detach = (JRFillVariable)vm.get("detach");
        variable_paidby = (JRFillVariable)vm.get("paidby");
        variable_memsig = (JRFillVariable)vm.get("memsig");
        variable_amountdue = (JRFillVariable)vm.get("amountdue");
        variable_memno = (JRFillVariable)vm.get("memno");
        variable_cuthere = (JRFillVariable)vm.get("cuthere");
        variable_memreq = (JRFillVariable)vm.get("memreq");
        variable_compu = (JRFillVariable)vm.get("compu");
        variable_thanku = (JRFillVariable)vm.get("thanku");
        variable_bci = (JRFillVariable)vm.get("bci");
        variable_tcredit = (JRFillVariable)vm.get("tcredit");
        variable_amtbal = (JRFillVariable)vm.get("amtbal");
    }


    /**
     *
     */
    public Object evaluate(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header1.getValue()));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_sign.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_designation.getValue()));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer.getValue()));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_generatedDate.getValue()));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer2.getValue()));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_paymentDate.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getValue()).equals( "D" )?((java.lang.Double)field_orgamt.getValue()).doubleValue():0.0));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("PLEASE DETACH THIS STUB AND ENCLOSE WITH YOUR REMITTANCE.");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)("Paid by Cash/Cheque/DD............................Dt.........................Drawn on ..........................For Rs..........................");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)("Member's signature.................................");//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)("AMOUNT DUE");//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)("MEMBERSHIP NO.");//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("..................................................................................cut here.................................................................................................");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("Members are requested to register theis Mobile numbers with the office for SMS updates.");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)("This is a computer generated statement and does not require signature.");//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)("Thanking you");//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)("in the name of "+((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getValue()).equals( "C" )?((java.lang.Double)field_orgamt.getValue()).doubleValue():0.0));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_memno.getValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getValue()));//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memSHipNo.getValue()));//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyAddress.getValue()));//$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=37$
                break;
            }
            case 38 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_slNo.getValue()));//$JR_EXPR_ID=38$
                break;
            }
            case 39 : 
            {
                value = (java.lang.String)(((java.lang.String)field_noticeName.getValue()) + "  Dated:  "+((java.lang.String)field_generatedDate.getValue())+",  Due Date of: "+ ((java.lang.String)field_dueDate.getValue())+",  PayBy Date of: "+((java.lang.String)field_payByDate.getValue())+",  Due Amount of: " + com.openbravo.format.Formats.CURRENCY.formatValue(((java.lang.Double)field_dueAmount.getValue()))+".  Communication Mode:   " +((java.lang.String)field_commMode.getValue())+"   , Date of Dispatch:  " + ((java.lang.String)field_dateOfDispatch.getValue())+"  , Received Reference:  " + ((java.lang.String)field_receivedRef.getValue())+"  ,  Received By:  " + ((java.lang.String)field_recBy.getValue()) + "  , Received Date:  "+ ((java.lang.String)field_recDate.getValue())+ "   ,  Notice Status:  " +((java.lang.String)field_status.getValue())+".  " + ((java.lang.String)field_notes.getValue()));//$JR_EXPR_ID=39$
                break;
            }
            case 40 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));//$JR_EXPR_ID=40$
                break;
            }
            case 41 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));//$JR_EXPR_ID=41$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateOld(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header1.getValue()));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_sign.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_designation.getValue()));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer.getValue()));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_generatedDate.getValue()));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer2.getValue()));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_paymentDate.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getOldValue()).equals( "D" )?((java.lang.Double)field_orgamt.getOldValue()).doubleValue():0.0));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("PLEASE DETACH THIS STUB AND ENCLOSE WITH YOUR REMITTANCE.");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)("Paid by Cash/Cheque/DD............................Dt.........................Drawn on ..........................For Rs..........................");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)("Member's signature.................................");//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)("AMOUNT DUE");//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)("MEMBERSHIP NO.");//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("..................................................................................cut here.................................................................................................");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("Members are requested to register theis Mobile numbers with the office for SMS updates.");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)("This is a computer generated statement and does not require signature.");//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)("Thanking you");//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)("in the name of "+((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getOldValue()).equals( "C" )?((java.lang.Double)field_orgamt.getOldValue()).doubleValue():0.0));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_memno.getOldValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getOldValue()));//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memSHipNo.getOldValue()));//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyAddress.getValue()));//$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=37$
                break;
            }
            case 38 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_slNo.getOldValue()));//$JR_EXPR_ID=38$
                break;
            }
            case 39 : 
            {
                value = (java.lang.String)(((java.lang.String)field_noticeName.getOldValue()) + "  Dated:  "+((java.lang.String)field_generatedDate.getOldValue())+",  Due Date of: "+ ((java.lang.String)field_dueDate.getOldValue())+",  PayBy Date of: "+((java.lang.String)field_payByDate.getOldValue())+",  Due Amount of: " + com.openbravo.format.Formats.CURRENCY.formatValue(((java.lang.Double)field_dueAmount.getOldValue()))+".  Communication Mode:   " +((java.lang.String)field_commMode.getOldValue())+"   , Date of Dispatch:  " + ((java.lang.String)field_dateOfDispatch.getOldValue())+"  , Received Reference:  " + ((java.lang.String)field_receivedRef.getOldValue())+"  ,  Received By:  " + ((java.lang.String)field_recBy.getOldValue()) + "  , Received Date:  "+ ((java.lang.String)field_recDate.getOldValue())+ "   ,  Notice Status:  " +((java.lang.String)field_status.getOldValue())+".  " + ((java.lang.String)field_notes.getOldValue()));//$JR_EXPR_ID=39$
                break;
            }
            case 40 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));//$JR_EXPR_ID=40$
                break;
            }
            case 41 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));//$JR_EXPR_ID=41$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


    /**
     *
     */
    public Object evaluateEstimated(int id) throws Throwable
    {
        Object value = null;

        switch (id)
        {
            case 0 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header1.getValue()));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_sign.getValue()));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_designation.getValue()));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer.getValue()));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_generatedDate.getValue()));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_footer2.getValue()));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_paymentDate.getValue()));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getValue()).equals( "D" )?((java.lang.Double)field_orgamt.getValue()).doubleValue():0.0));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.String)("PLEASE DETACH THIS STUB AND ENCLOSE WITH YOUR REMITTANCE.");//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.String)("Paid by Cash/Cheque/DD............................Dt.........................Drawn on ..........................For Rs..........................");//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.String)("Member's signature.................................");//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.String)("AMOUNT DUE");//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.String)("MEMBERSHIP NO.");//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.String)("..................................................................................cut here.................................................................................................");//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.String)("Members are requested to register theis Mobile numbers with the office for SMS updates.");//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)("This is a computer generated statement and does not require signature.");//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)("Thanking you");//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)("in the name of "+((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.Double)(new java.lang.Double(((java.lang.String)field_type.getValue()).equals( "C" )?((java.lang.Double)field_orgamt.getValue()).doubleValue():0.0));//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.Object)(((java.lang.String)field_memno.getValue()));//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getValue()));//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memSHipNo.getValue()));//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header2.getValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=35$
                break;
            }
            case 36 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyAddress.getValue()));//$JR_EXPR_ID=36$
                break;
            }
            case 37 : 
            {
                value = (java.util.Date)(new java.util.Date());//$JR_EXPR_ID=37$
                break;
            }
            case 38 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)field_slNo.getValue()));//$JR_EXPR_ID=38$
                break;
            }
            case 39 : 
            {
                value = (java.lang.String)(((java.lang.String)field_noticeName.getValue()) + "  Dated:  "+((java.lang.String)field_generatedDate.getValue())+",  Due Date of: "+ ((java.lang.String)field_dueDate.getValue())+",  PayBy Date of: "+((java.lang.String)field_payByDate.getValue())+",  Due Amount of: " + com.openbravo.format.Formats.CURRENCY.formatValue(((java.lang.Double)field_dueAmount.getValue()))+".  Communication Mode:   " +((java.lang.String)field_commMode.getValue())+"   , Date of Dispatch:  " + ((java.lang.String)field_dateOfDispatch.getValue())+"  , Received Reference:  " + ((java.lang.String)field_receivedRef.getValue())+"  ,  Received By:  " + ((java.lang.String)field_recBy.getValue()) + "  , Received Date:  "+ ((java.lang.String)field_recDate.getValue())+ "   ,  Notice Status:  " +((java.lang.String)field_status.getValue())+".  " + ((java.lang.String)field_notes.getValue()));//$JR_EXPR_ID=39$
                break;
            }
            case 40 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));//$JR_EXPR_ID=40$
                break;
            }
            case 41 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));//$JR_EXPR_ID=41$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}

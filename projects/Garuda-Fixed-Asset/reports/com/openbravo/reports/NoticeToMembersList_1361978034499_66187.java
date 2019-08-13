/*
 * Generated by JasperReports - 27/02/13 20:43
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
public class NoticeToMembersList_1361978034499_66187 extends JREvaluator
{


    /**
     *
     */
    private JRFillParameter parameter_total = null;
    private JRFillParameter parameter_companyAddress = null;
    private JRFillParameter parameter_desg1 = null;
    private JRFillParameter parameter_REPORT_TIME_ZONE = null;
    private JRFillParameter parameter_noticeName = null;
    private JRFillParameter parameter_REPORT_FILE_RESOLVER = null;
    private JRFillParameter parameter_desg = null;
    private JRFillParameter parameter_REPORT_PARAMETERS_MAP = null;
    private JRFillParameter parameter_date = null;
    private JRFillParameter parameter_REPORT_CLASS_LOADER = null;
    private JRFillParameter parameter_REPORT_URL_HANDLER_FACTORY = null;
    private JRFillParameter parameter_REPORT_DATA_SOURCE = null;
    private JRFillParameter parameter_IS_IGNORE_PAGINATION = null;
    private JRFillParameter parameter_REPORT_MAX_COUNT = null;
    private JRFillParameter parameter_REPORT_TEMPLATES = null;
    private JRFillParameter parameter_REPORT_LOCALE = null;
    private JRFillParameter parameter_REPORT_VIRTUALIZER = null;
    private JRFillParameter parameter_sign1 = null;
    private JRFillParameter parameter_REPORT_SCRIPTLET = null;
    private JRFillParameter parameter_REPORT_CONNECTION = null;
    private JRFillParameter parameter_companyName = null;
    private JRFillParameter parameter_header = null;
    private JRFillParameter parameter_sign = null;
    private JRFillParameter parameter_REPORT_FORMAT_FACTORY = null;
    private JRFillParameter parameter_REPORT_RESOURCE_BUNDLE = null;
    private JRFillField field_facility3 = null;
    private JRFillField field_memno = null;
    private JRFillField field_status3 = null;
    private JRFillField field_status1 = null;
    private JRFillField field_status2 = null;
    private JRFillField field_foverdue1 = null;
    private JRFillField field_memname = null;
    private JRFillField field_fdue1 = null;
    private JRFillField field_fdue3 = null;
    private JRFillField field_facility2 = null;
    private JRFillField field_fdue2 = null;
    private JRFillField field_facility1 = null;
    private JRFillVariable variable_PAGE_NUMBER = null;
    private JRFillVariable variable_COLUMN_NUMBER = null;
    private JRFillVariable variable_REPORT_COUNT = null;
    private JRFillVariable variable_PAGE_COUNT = null;
    private JRFillVariable variable_COLUMN_COUNT = null;
    private JRFillVariable variable_Total = null;
    private JRFillVariable variable_temp = null;
    private JRFillVariable variable_slno = null;


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
        parameter_total = (JRFillParameter)pm.get("total");
        parameter_companyAddress = (JRFillParameter)pm.get("companyAddress");
        parameter_desg1 = (JRFillParameter)pm.get("desg1");
        parameter_REPORT_TIME_ZONE = (JRFillParameter)pm.get("REPORT_TIME_ZONE");
        parameter_noticeName = (JRFillParameter)pm.get("noticeName");
        parameter_REPORT_FILE_RESOLVER = (JRFillParameter)pm.get("REPORT_FILE_RESOLVER");
        parameter_desg = (JRFillParameter)pm.get("desg");
        parameter_REPORT_PARAMETERS_MAP = (JRFillParameter)pm.get("REPORT_PARAMETERS_MAP");
        parameter_date = (JRFillParameter)pm.get("date");
        parameter_REPORT_CLASS_LOADER = (JRFillParameter)pm.get("REPORT_CLASS_LOADER");
        parameter_REPORT_URL_HANDLER_FACTORY = (JRFillParameter)pm.get("REPORT_URL_HANDLER_FACTORY");
        parameter_REPORT_DATA_SOURCE = (JRFillParameter)pm.get("REPORT_DATA_SOURCE");
        parameter_IS_IGNORE_PAGINATION = (JRFillParameter)pm.get("IS_IGNORE_PAGINATION");
        parameter_REPORT_MAX_COUNT = (JRFillParameter)pm.get("REPORT_MAX_COUNT");
        parameter_REPORT_TEMPLATES = (JRFillParameter)pm.get("REPORT_TEMPLATES");
        parameter_REPORT_LOCALE = (JRFillParameter)pm.get("REPORT_LOCALE");
        parameter_REPORT_VIRTUALIZER = (JRFillParameter)pm.get("REPORT_VIRTUALIZER");
        parameter_sign1 = (JRFillParameter)pm.get("sign1");
        parameter_REPORT_SCRIPTLET = (JRFillParameter)pm.get("REPORT_SCRIPTLET");
        parameter_REPORT_CONNECTION = (JRFillParameter)pm.get("REPORT_CONNECTION");
        parameter_companyName = (JRFillParameter)pm.get("companyName");
        parameter_header = (JRFillParameter)pm.get("header");
        parameter_sign = (JRFillParameter)pm.get("sign");
        parameter_REPORT_FORMAT_FACTORY = (JRFillParameter)pm.get("REPORT_FORMAT_FACTORY");
        parameter_REPORT_RESOURCE_BUNDLE = (JRFillParameter)pm.get("REPORT_RESOURCE_BUNDLE");
    }


    /**
     *
     */
    private void initFields(Map fm)
    {
        field_facility3 = (JRFillField)fm.get("facility3");
        field_memno = (JRFillField)fm.get("memno");
        field_status3 = (JRFillField)fm.get("status3");
        field_status1 = (JRFillField)fm.get("status1");
        field_status2 = (JRFillField)fm.get("status2");
        field_foverdue1 = (JRFillField)fm.get("foverdue1");
        field_memname = (JRFillField)fm.get("memname");
        field_fdue1 = (JRFillField)fm.get("fdue1");
        field_fdue3 = (JRFillField)fm.get("fdue3");
        field_facility2 = (JRFillField)fm.get("facility2");
        field_fdue2 = (JRFillField)fm.get("fdue2");
        field_facility1 = (JRFillField)fm.get("facility1");
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
        variable_Total = (JRFillVariable)vm.get("Total");
        variable_temp = (JRFillVariable)vm.get("temp");
        variable_slno = (JRFillVariable)vm.get("slno");
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
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Double)(new Double(0.0));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new java.lang.Integer(  (((java.lang.Integer)variable_slno.getValue()).intValue()) +1 ));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_date.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memno.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_foverdue1.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_slno.getValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg.getValue()):null);//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg1.getValue()):null);//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign.getValue()):null);//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign1.getValue()):null);//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getValue()));//$JR_EXPR_ID=35$
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
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Double)(new Double(0.0));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new java.lang.Integer(  (((java.lang.Integer)variable_slno.getOldValue()).intValue()) +1 ));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_date.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memno.getOldValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getOldValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_foverdue1.getOldValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null || ((java.lang.String)field_status2.getOldValue())==null || ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null || ((java.lang.String)field_status2.getOldValue())==null || ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null && ((java.lang.String)field_status2.getOldValue())==null && ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null && ((java.lang.String)field_status2.getOldValue())==null && ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null && ((java.lang.String)field_status2.getOldValue())==null && ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null && ((java.lang.String)field_status2.getOldValue())==null && ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getOldValue())==null || ((java.lang.String)field_status2.getOldValue())==null || ((java.lang.String)field_status3.getOldValue())==null));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_slno.getOldValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg.getValue()):null);//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg1.getValue()):null);//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign.getValue()):null);//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign1.getValue()):null);//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getOldValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getOldValue()));//$JR_EXPR_ID=35$
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
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=0$
                break;
            }
            case 1 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=1$
                break;
            }
            case 2 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=2$
                break;
            }
            case 3 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=3$
                break;
            }
            case 4 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=4$
                break;
            }
            case 5 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=5$
                break;
            }
            case 6 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=6$
                break;
            }
            case 7 : 
            {
                value = (java.lang.Integer)(new Integer(1));//$JR_EXPR_ID=7$
                break;
            }
            case 8 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=8$
                break;
            }
            case 9 : 
            {
                value = (java.lang.Double)(new Double(0.0));//$JR_EXPR_ID=9$
                break;
            }
            case 10 : 
            {
                value = (java.lang.Integer)(new java.lang.Integer(  (((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()) +1 ));//$JR_EXPR_ID=10$
                break;
            }
            case 11 : 
            {
                value = (java.lang.Integer)(new Integer(0));//$JR_EXPR_ID=11$
                break;
            }
            case 12 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_header.getValue()));//$JR_EXPR_ID=12$
                break;
            }
            case 13 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_companyName.getValue()));//$JR_EXPR_ID=13$
                break;
            }
            case 14 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_noticeName.getValue()));//$JR_EXPR_ID=14$
                break;
            }
            case 15 : 
            {
                value = (java.lang.String)(((java.lang.String)parameter_date.getValue()));//$JR_EXPR_ID=15$
                break;
            }
            case 16 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memno.getValue()));//$JR_EXPR_ID=16$
                break;
            }
            case 17 : 
            {
                value = (java.lang.String)(((java.lang.String)field_memname.getValue()));//$JR_EXPR_ID=17$
                break;
            }
            case 18 : 
            {
                value = (java.lang.Double)(((java.lang.Double)field_foverdue1.getValue()));//$JR_EXPR_ID=18$
                break;
            }
            case 19 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=19$
                break;
            }
            case 20 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=20$
                break;
            }
            case 21 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=21$
                break;
            }
            case 22 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=22$
                break;
            }
            case 23 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=23$
                break;
            }
            case 24 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null && ((java.lang.String)field_status2.getValue())==null && ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=24$
                break;
            }
            case 25 : 
            {
                value = (java.lang.Boolean)(new Boolean(((java.lang.String)field_status1.getValue())==null || ((java.lang.String)field_status2.getValue())==null || ((java.lang.String)field_status3.getValue())==null));//$JR_EXPR_ID=25$
                break;
            }
            case 26 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_slno.getEstimatedValue()));//$JR_EXPR_ID=26$
                break;
            }
            case 27 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg.getValue()):null);//$JR_EXPR_ID=27$
                break;
            }
            case 28 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_desg1.getValue()):null);//$JR_EXPR_ID=28$
                break;
            }
            case 29 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign.getValue()):null);//$JR_EXPR_ID=29$
                break;
            }
            case 30 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?((java.lang.String)parameter_sign1.getValue()):null);//$JR_EXPR_ID=30$
                break;
            }
            case 31 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=31$
                break;
            }
            case 32 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=32$
                break;
            }
            case 33 : 
            {
                value = (java.lang.String)(((java.lang.Integer)variable_slno.getEstimatedValue()).intValue()==((java.lang.Integer)parameter_total.getValue()).intValue()?".                                        									":null);//$JR_EXPR_ID=33$
                break;
            }
            case 34 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));//$JR_EXPR_ID=34$
                break;
            }
            case 35 : 
            {
                value = (java.lang.Integer)(((java.lang.Integer)variable_PAGE_NUMBER.getEstimatedValue()));//$JR_EXPR_ID=35$
                break;
            }
           default :
           {
           }
        }
        
        return value;
    }


}

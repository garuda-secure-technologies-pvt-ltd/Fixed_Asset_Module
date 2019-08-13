/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.FixedAssetRegistration;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.IKeyed;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dev3
 */
public class ListOfAMCnNonAMCReportTableModel extends BeanFactoryDataSingle {
private List<ListOfAMCnNonAMCReportTableModel.NonamcInfo> nonamclist;
    private Session s;
     private List<ListOfAMCnNonAMCReportTableModel.NonamcInfo> data;
    private int flag;
    private int size;
    private int nonamc_length;
   private final static String[] TABLEHEADERS2 = {"Sr No.","ASSET", "VENDOR_NAME", "CONTACT_PERSON", "CONTACT_DETAILS", "REMARKS"};
 @Override
    public void init(Session s) {
        this.s = s;
    }

    
    
  public static ListOfAMCnNonAMCReportTableModel LoadAllNonAMC(AppView app, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id order by f.name  ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
   public static ListOfAMCnNonAMCReportTableModel LoadforPartFANonAMC(AppView app, int flag,String asset) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{asset});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{asset});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{asset});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
     
     public static ListOfAMCnNonAMCReportTableModel LoadAllActiveNonAMC(AppView app, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true order by f.name  ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadAllWoNonAMC(AppView app, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id order by f.name  ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            } else {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCnNonAMCReportTableModel LoadAllActiveNonAMCbymajcls(AppView app,String majcls, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true and f.maj_classification =?  order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true  and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadAllWoNonAMCbymajcls(AppView app,String majcls, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            } else {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCnNonAMCReportTableModel LoadAllActiveNonAMCbymajclssubcls(AppView app,String majcls,String subcls, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true and f.maj_classification =?  order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true  and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.active=true and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadAllWoNonAMCbymajclssubcls(AppView app,String majcls,String subcls, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});

            } else {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v,fa_write_off_details w where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,subcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadAllNonAMCbymaj(AppView app, String majcls,int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag=flag;
       
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            
            if (flag == 0) {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id  and f.maj_classification =?  order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id  and f.maj_classification =?  order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification =?  order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadAllNonAMCbymajnSub(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
     public static ListOfAMCnNonAMCReportTableModel LoadforPartFANonAMCbymaj(AppView app, String majcls,int flag,String asset) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag=flag;
       
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            
            if (flag == 0) {

                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id  and f.maj_classification =? and f.name=? order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,asset});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id  and f.maj_classification =? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,asset});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification =? and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls,asset});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCnNonAMCReportTableModel LoadforPartFANonAMCbymajnSub(AppView app, String majcls, String subcls, int flag,String asset) throws BasicException {
        ListOfAMCnNonAMCReportTableModel amcdetail = new ListOfAMCnNonAMCReportTableModel();
        amcdetail.flag = flag;
        try {
            amcdetail.nonamclist = new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
            if (flag == 0) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.name  ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls,asset});
            } else if (flag == 1) {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls,asset});

            } else {
                amcdetail.nonamclist = new StaticSentence(app.getSession(), "select f.name ,v.name,n.remarks,n.contact_person,n.contact_details from fa_nonamc n, fa_master f,fa_amc a,vendor v where v.id=n.vendor_name and f.id=n.fa_id and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=?  and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCnNonAMCReportTableModel.NonamcInfo.class)).list(new Object[]{majcls, subcls,asset});

            }
            amcdetail.nonamc_length = amcdetail.nonamclist.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCnNonAMCReportTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    
    public int getSize() {
        return size;
    }

   
   public List<ListOfAMCnNonAMCReportTableModel.NonamcInfo> getNonAMCList() {
        if (nonamclist != null) {
            return nonamclist;
        } else {
            return new ArrayList<ListOfAMCnNonAMCReportTableModel.NonamcInfo>();
        }
    }
    
    
    
    public AbstractTableModel getTableModel1() {
        return new AbstractTableModel() {
            int i = 2, j = 0;

            public int getRowCount() {
                return nonamclist.size();
            }

            public int getColumnCount() {
                return TABLEHEADERS2.length;
            }

            @Override
            public String getColumnName(int column) {

                return TABLEHEADERS2[column];
            }

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                ListOfAMCnNonAMCReportTableModel.NonamcInfo r = nonamclist.get(rowIndex);

                switch (columnIndex) {
                    case 0:
                        return (rowIndex + 1);
                         case 1:
                        return r.getasset();
                    case 2:
                        return r.getvendor();
                    case 3:
                        return r.getcontact_per();
                    case 4:
                        return r.getcontact_det();
                    case 5:
                        return r.getRemark();
                       
                   
                   

                }
                return null;
            }

        };
    }
    
    
    
     public static class NonamcInfo implements SerializableRead, IKeyed {
         private String Id;
        private Boolean active;
       
        private String remark;
        private String asset;

        private String vendor;
        private String contact_per;

        private String contact_det;

        private String createdby;
        private Timestamp createddate;

        
          public String getID(){
            return Id;
        }
        public void setID(String id){
            this.Id=id;
        }
         public String getasset(){
            return asset;
        }
        public void setasset(String asset){
            this.asset=asset;
        }
        public Boolean isActive(){
            return active;
        }
        public void setActive(Boolean active){
        this.active=active;
        }
        public String getvendor() {
            return vendor;
        }

        public void setvendor(String vendor) {
            this.vendor = vendor;
        }

        public String getcontact_per() {
            return contact_per;

        }

        public void setcontact_per(String contact_per) {
            this.contact_per = contact_per;
        }

        public String getcontact_det() {
            return contact_det;
        }

        public void setcontact_det(String contact_det) {
            this.contact_det = contact_det;
        }

        public String getCreatedBy() {
            return createdby;
        }

        public void setCreatedBy(String createdby) {
            this.createdby = createdby;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Timestamp getCreatedDate() {
            return createddate;
        }

        public void setCreatedDate(Timestamp createddate) {
            this.createddate = createddate;
        }

        public void readValues(DataRead dr) throws BasicException {
             asset=dr.getString(1);
            vendor = dr.getString(2);
             remark = dr.getString(3);
            contact_per = dr.getString(4);
            contact_det = dr.getString(5);
          
           
        }

        public Object getKey() {
            return this;
        }

    }
}

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
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

/**
 *
 * @author dev3
 */
public class ListOfAMCTable_model extends BeanFactoryDataSingle {

    private Session s;
    private List<ListOfAMCTable_model.AMCInfo> AMCList;
    private int amc_length;
    private final static String[] AMCHEADER = {"ASSET", "CONTRACTOR", "PERIOD", "APPROVED BY", "START_DATE ", "END_DATE", "REMINDER_DATE ", "APPROVED_DATE"};
    DecimalFormat decimalformat = new DecimalFormat("#0.00##");
    DecimalFormat df = new DecimalFormat("#%");
    private int flag;

    @Override
    public void init(Session s) {
        this.s = s;
    }
      public static ListOfAMCTable_model Empty(AppView app) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
       // amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
           amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
 
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCTable_model LoadAllAMC(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
     public static ListOfAMCTable_model LoadforParticularFAAMC(AppView app, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllAMCbymaj(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;

        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();

            if (flag == 0) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id   and f.maj_classification =?  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id  and f.maj_classification =?  order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id  and f.maj_classification =?  order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
public static ListOfAMCTable_model LoadforPArtFAAMCbymaj(AppView app, String majcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;

        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();

            if (flag == 0) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id   and f.maj_classification =? and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id  and f.maj_classification =? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id  and f.maj_classification =? and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllAMCbymajnSub(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
     public static ListOfAMCTable_model LoadforPartFAAMCbymajnSub(AppView app, String majcls, String subcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }


    public static ListOfAMCTable_model LoadAllActiveAMC(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMC(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllAMCActive(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCTable_model LoadforParticularFAAMCActive(AppView app, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllLastAMC(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCTable_model LoadforPartFALastAMC(AppView app, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.name=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.name=? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.name=? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCActive(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCLast(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  order by f.name   desc\n"
                        + "limit 1   ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCActive(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCLast(AppView app, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w ,vendor v where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCbymajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =?  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCbymajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select vv.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllAMCActivemajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
public static ListOfAMCTable_model LoadforPartFAAMCActivemajcls(AppView app, String majcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllLastAMCmajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
public static ListOfAMCTable_model LoadforPartFALastAMCmajcls(AppView app, String majcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.name=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.name=? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.name=? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCActivemajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =?  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCLastmajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  and f.maj_classification =? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =?  order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a ,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =?  order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCActivemajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCLastmajcls(AppView app, String majcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    //for subcls
    public static ListOfAMCTable_model LoadAllActiveAMCbymajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCbymajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllAMCActivemajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.sub_head_class=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and  f.id=a.fa_id and a.active=true and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }
    public static ListOfAMCTable_model LoadforPartFAAMCActivemajclssubcls(AppView app, String majcls, String subcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =? and f.sub_head_class=? and f.name=?  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.maj_classification =?  and f.sub_head_class=? and f.name=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and  f.id=a.fa_id and a.active=true and f.maj_classification =?  and f.sub_head_class=? and f.name=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllLastAMCmajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and  f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }public static ListOfAMCTable_model LoadforPartFALastAMCmajclssubcls(AppView app, String majcls, String subcls, int flag,String asset) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and  f.id=a.fa_id and f.active=true and f.maj_classification =?  and f.sub_head_class=? and f.name=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});
            } else if (flag == 1) {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? and f.name=?  order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            } else {

                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =?  and f.sub_head_class=? and f.name=?  order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls,asset});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCActivemajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =? and f.sub_head_class=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllActiveAMCLastmajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true  and f.maj_classification =? and f.sub_head_class=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,vendor v where v.id=a.contractor and f.id=a.fa_id and f.active=true and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls,subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCActivemajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and a.active=true and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public static ListOfAMCTable_model LoadAllWoAMCLastmajclssubcls(AppView app, String majcls, String subcls, int flag) throws BasicException {
        ListOfAMCTable_model amcdetail = new ListOfAMCTable_model();
        amcdetail.flag = flag;
        try {
            amcdetail.AMCList = new ArrayList<ListOfAMCTable_model.AMCInfo>();
            if (flag == 0) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by f.name desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});
            } else if (flag == 1) {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w ,vendor v where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.reminder_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            } else {
                amcdetail.AMCList = new StaticSentence(app.getSession(), "select f.name,v.name,a.particular,a.period,a.remark,a.approved_by,a.start_date,a.end_date,a.reminder_date,a.approved_date,a.rate,a.account,a.amount,a.doc_link,a.initiator,a.initiated_date from fa_master f,fa_amc a,fa_write_off_details w,vendor v  where v.id=a.contractor and f.id=a.fa_id and f.wo=w.id and f.maj_classification =? and f.sub_head_class=? order by a.initiated_date desc\n"
                        + "limit 1", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAMCTable_model.AMCInfo.class)).list(new Object[]{majcls, subcls});

            }
            amcdetail.amc_length = amcdetail.AMCList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAMCTable_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amcdetail;

    }

    public AbstractTableModel getTableModel() {

        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(AMCHEADER[column]);
            }

            public int getRowCount() {
                return AMCList.size();
            }

            public int getColumnCount() {

                return AMCHEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                ListOfAMCTable_model.AMCInfo b = AMCList.get(rowIndex);

                switch (columnIndex) {

                    case 0:
                        return b.getASSET();
                    case 1:
                        return b.getCONTRACTOR();

                    case 2:
                        return b.getPERIOD();

                    case 3:
                        return b.getAPPROVED_BY();
                    case 4:
                        if (b.getSTART_DATE() != null) {
                            String strdat = Formats.TIMESTAMP.formatValue(b.getSTART_DATE());

                            return strdat;
                            
                        }

                    case 5:
                        if (b.getEND_DATE() != null) {
                            String enddt = Formats.TIMESTAMP.formatValue(b.getEND_DATE());

                            return enddt;
                            
                        }

                    case 6:
                        if (b.getREMINDER_DATE() != null) {
                            String remdt = Formats.TIMESTAMP.formatValue(b.getREMINDER_DATE());

                            return remdt;
                               
                        }

                    case 7:
                        if (b.getAPPROVED_DATE() != null) {
                            String appdt = Formats.TIMESTAMP.formatValue(b.getAPPROVED_DATE());

                            return appdt;
                           
                        }

                    default:
                        return null;

                }

            }

        };

    }

    public List<ListOfAMCTable_model.AMCInfo> getAMCList() {
        if (AMCList != null) {
            return AMCList;
        } else {
            return new ArrayList<ListOfAMCTable_model.AMCInfo>();
        }
    }

    public static class AMCInfo implements SerializableRead, IKeyed {

        private String CONTRACTOR;
        private String PARTICULAR;
        private String PERIOD;
        private String REMARK;
        private String DOC_LINK;
        private String ASSET;
        private String APPROVED_BY;

        private Date START_DATE;
        private String created_by;

        private Date created_date;

        private Date END_DATE;
        private Date REMINDER_DATE;
        private Date APPROVED_DATE;

        private Double RATE;
        private String ACCOUNT;
        private Double AMOUNT;

        public String getCONTRACTOR() {
            return CONTRACTOR;
        }

        public void setCONTRACTOR(String CONTRACTOR) {
            this.CONTRACTOR = CONTRACTOR;
        }

        public String getASSET() {
            return ASSET;
        }

        public void setASSET(String ASSET) {
            this.ASSET = ASSET;
        }

        public String getPARTICULAR() {
            return PARTICULAR;
        }

        public void setPARTICULAR(String PARTICULAR) {
            this.PARTICULAR = PARTICULAR;
        }

        public String getPERIOD() {
            return PERIOD;
        }

        public void setPERIOD(String PERIOD) {
            this.PERIOD = PERIOD;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getDOC_LINK() {
            return DOC_LINK;
        }

        public void setDOC_LINK(String DOC_LINK) {
            this.DOC_LINK = DOC_LINK;
        }

        public String getAPPROVED_BY() {
            return APPROVED_BY;
        }

        public void setAPPROVED_BY(String APPROVED_BY) {
            this.APPROVED_BY = APPROVED_BY;
        }
        
        public String getCreated_BY() {
            return created_by;
        }

        public void setCreated_BY(String created_by) {
            this.created_by = created_by;
        }

        public Date getSTART_DATE() {
            String DATE_FORMAT_NOW = "dd MMM,yyyy HH:mm:ss a ";
            Date date2 = null;
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            String stringDate = sdf.format(START_DATE);
            try {
                date2 = sdf.parse(stringDate);

            } catch (Exception e) {
                //Exception handling
            }
            return date2;
            
        }

        public void setSTART_DATE(Date START_DATE) {
            this.START_DATE = START_DATE;
        }

        public Date getEND_DATE() {

            return END_DATE;
        }

        public void setEND_DATE(Date END_DATE) {
            this.END_DATE = END_DATE;
        }
        public Date getCreated_DATE() {

            return created_date;
        }

        public void setCreated_DATE(Date created_date) {
            this.created_date = created_date;
        }

        public Date getREMINDER_DATE() {

            return REMINDER_DATE;
        }

        public void setREMINDER_DATE(Date REMINDER_DATE) {
            this.REMINDER_DATE = REMINDER_DATE;
        }

        public Date getAPPROVED_DATE() {

            return APPROVED_DATE;
        }

        public void setAPPROVED_DATE(Date APPROVED_DATE) {
            this.APPROVED_DATE = APPROVED_DATE;
        }

        public Double getRATE() {

            return RATE;
        }

        public void setRATE(Double RATE) {
            this.RATE = RATE;
        }

        public String getACCOUNT() {

            return ACCOUNT;
        }

        public void setACCOUNT(String ACCOUNT) {
            this.ACCOUNT = ACCOUNT;
        }

        public Double getAMOUNT() {

            return AMOUNT;
        }

        public void setAMOUNT(Double AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        @Override
        public Object getKey() {
            return this;
        }

        public void readValues(DataRead dr) throws BasicException {
            ASSET = dr.getString(1);
            CONTRACTOR = dr.getString(2);
            PARTICULAR = dr.getString(3);
            PERIOD = dr.getString(4);
            REMARK = dr.getString(5);
            APPROVED_BY = dr.getString(6);
            START_DATE = dr.getTimestamp(7);
            END_DATE = dr.getTimestamp(8);
            REMINDER_DATE = dr.getTimestamp(9);
            APPROVED_DATE = dr.getTimestamp(10);
            RATE = dr.getDouble(11);
            ACCOUNT = dr.getString(12);
            AMOUNT = dr.getDouble(13);
            DOC_LINK = dr.getString(14);
            created_by=dr.getString(15);
            created_date=dr.getTimestamp(16);

        }

    }

}

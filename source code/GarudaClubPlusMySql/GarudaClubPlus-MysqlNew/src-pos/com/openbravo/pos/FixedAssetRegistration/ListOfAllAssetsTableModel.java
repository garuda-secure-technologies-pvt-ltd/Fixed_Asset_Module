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
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
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
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;


/**
 *
 * @author dev3
 */
public class ListOfAllAssetsTableModel extends BeanFactoryDataSingle {

    private Session s;
    private List<ListOfAllAssetsTableModel.AssetInfo> AssetList;
    private int asset_length;
    private final static String[] ASSETHEADER = {"NAME", "MAJ CLASSIFICATION ", "ACCOUNT HEAD ", "VENDOR ", "DATE OF PURCHASE", " TOTAL COST", "AGENCY FOR REPLACEMENT"};
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static DataLogicFacilities dlfac;
    @Override
    public void init(Session s) {
        this.s = s;
    }
   
    public static ListOfAllAssetsTableModel LoadAllFa(AppView app) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head  order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
 public static ListOfAllAssetsTableModel LoadpartFAFa(AppView app,String asset) throws BasicException {
      dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head  and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajCls(AppView app, String majcls) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=?  order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadforPartFAFabyMajCls(AppView app, String majcls,String asset) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.name=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFAFabyMajClsSubCls(AppView app, String majcls, String subcls,String asset) throws BasicException {
        dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
 public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubCls(AppView app, String majcls, String subcls) throws BasicException {
      dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");
   
     ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActive(AppView app) throws BasicException {
      dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.active=true order by f.name", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActive(AppView app, String majcls) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActive(AppView app, String majcls, String subcls) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWo(AppView app) throws BasicException {
         dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWo(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWo(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFaorder(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head  order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsorder(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsorder(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadforPartFaorder(AppView app,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head  and f.name=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsorder(AppView app, String majcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=?  and f.name=? order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClsorder(AppView app, String majcls, String subcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActiveorder(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.active=true order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActiveorder(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActiveorder(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWoorder(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWoorder(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWoorder(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id order by f.date_of_purchase", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFacrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClscrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClscrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadforPartFacrdt(AppView app, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? and f.name=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClscrdt(AppView app, String majcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.name", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClscrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActivecrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActivecrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActivecrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWocrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWocrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWocrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFaordercrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsordercrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsordercrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=?  order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadforPartFaordercrdt(AppView app, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.created_date>=? and f.created_date<=? and f.name=?  order by f.name ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsordercrdt(AppView app, String majcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.name=?  order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClsordercrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.name=?  order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActiveordercrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActiveordercrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActiveordercrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWoordercrdt(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWoordercrdt(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWoordercrdt(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
   
    //verified assets
    
  
    public static ListOfAllAssetsTableModel LoadAllFaforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head order by f.name ", new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
     public static ListOfAllAssetsTableModel LoadforPartFAFaforVer(AppView app,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.name=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
     public static ListOfAllAssetsTableModel LoadforPartFAFabyMajClsforVer(AppView app, String majcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.name=? order by f.name", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFAFabyMajClsSubClsforVer(AppView app, String majcls, String subcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActiveforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.active=true order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActiveforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,fa_physicalverification p,vendor v,accountmaster a where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.active=true order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActiveforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,fa_physicalverification p,vendor v,accountmaster a where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.active=true order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWoforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,fa_physicalverification p,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.wo=w.id order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWoforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,fa_physicalverification p,accountmaster a,fa_write_off_details w where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and maj_classification=? and f.wo=w.id order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWoforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,fa_physicalverification p,vendor v,accountmaster a,fa_write_off_details w where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id order by f.name", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFaorderforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,fa_physicalverification p,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsorderforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? order by f.date_of_purchase", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsorderforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? order by f.date_of_purchase",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
     public static ListOfAllAssetsTableModel LoadforPartFaorderforVer(AppView app,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,fa_physicalverification p,vendor v,accountmaster a where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.name=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{ Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsorderforVer(AppView app, String majcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.name=? order by f.date_of_purchase",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClsorderforVer(AppView app, String majcls, String subcls,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.name=? order by f.date_of_purchase",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActiveorderforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.active=true order by f.date_of_purchase",
                    
                    new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActiveorderforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.active=true order by f.date_of_purchase", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActiveorderforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.active=true order by f.date_of_purchase", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWoorderforVer(AppView app) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.wo=w.id order by f.date_of_purchase",
                    
                    new SerializerWriteBasic(new Datas[]{}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWoorderforVer(AppView app, String majcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and maj_classification=? and f.wo=w.id order by f.date_of_purchase", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWoorderforVer(AppView app, String majcls, String subcls) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id order by f.date_of_purchase", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFacrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.created_date>=? and f.created_date<=? order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClscrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.created_date>=? and f.created_date<=? order by f.name",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClscrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadforPartFacrdtforVer(AppView app, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.created_date>=? and f.created_date<=? and f.name=? order by f.name", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClscrdtforVer(AppView app, String majcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.name", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClscrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP,Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyActivecrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head  and f.id=p.fa_id and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActivecrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActivecrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWocrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor  and f.id=p.fa_id and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWocrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWocrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFaordercrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.created_date>=? and f.created_date<=? order by f.name ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsordercrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsordercrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=?  order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
 public static ListOfAllAssetsTableModel LoadforPartFaordercrdtforVer(AppView app, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.created_date>=? and f.created_date<=? and f.name=? order by f.name ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsordercrdtforVer(AppView app, String majcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadforPartFabyMajClsSubClsordercrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date,String asset) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.created_date>=? and f.created_date<=? and f.name=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date,asset});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    public static ListOfAllAssetsTableModel LoadAllFabyActiveordercrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyActiveordercrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsbyActiveordercrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate  from fa_master f,vendor v,accountmaster a,fa_physicalverification p where  v.id=f.vendor and a.id=f.account_head and f.id=p.fa_id and f.maj_classification=? and f.sub_head_class=? and f.active=true and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyWoordercrdtforVer(AppView app, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ", 
                    
                    new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsbyWoordercrdtforVer(AppView app, String majcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and maj_classification=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }

    public static ListOfAllAssetsTableModel LoadAllFabyMajClsSubClsByWoordercrdtforVer(AppView app, String majcls, String subcls, Date from_date, Date to_date) throws BasicException {
        ListOfAllAssetsTableModel fadetail = new ListOfAllAssetsTableModel();
 dlfac=(DataLogicFacilities) app.getBean("com.openbravo.pos.clubmang.DataLogicFacilitiesCreate");

        try {
            fadetail.AssetList = new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
            fadetail.AssetList = new StaticSentence(app.getSession(), "select  f.name,f.barcode,f.maj_classification ,f.sub_head_class,a.name,v.name,f.date_of_purchase,f.total_cost,f.agency_for_replacement,f.date_of_installetion,\n"
                    + "f.date_of_commission,f.date_put_to_use,f.date_of_capitalization,f.rate_of_deprecation,f.wdv_date_of_far,f.cost_of_replacement,f.is_stand_alone_asset,f.link,f.scanned_doc,f.created_by,f.created_date,f.id,f.make,f.model,f.wdvdate from fa_master f,vendor v,accountmaster a,fa_write_off_details w,fa_physicalverification p where  v.id=f.vendor and f.id=p.fa_id and a.id=f.account_head and f.maj_classification=? and f.sub_head_class=? and f.wo=w.id and f.created_date>=? and f.created_date<=? order by f.date_of_purchase ",
                    
                    new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP}), new SerializerReadClass(ListOfAllAssetsTableModel.AssetInfo.class)).list(new Object[]{majcls, subcls, from_date, to_date});

            fadetail.asset_length = fadetail.AssetList.size();

        } catch (BasicException ex) {

            Logger.getLogger(ListOfAllAssetsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fadetail;

    }
    

    public AbstractTableModel getTableModel() {

        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(ASSETHEADER[column]);
            }

            public int getRowCount() {
                return AssetList.size();
            }

            public int getColumnCount() {

                return ASSETHEADER.length;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                ListOfAllAssetsTableModel.AssetInfo b = AssetList.get(rowIndex);

                switch (columnIndex) {

                    case 0:
                        return b.getName();

                    case 1:
                        return b.getMajcls();

                    case 2:
                        return b.getAccHead();
                    case 3:
                        return b.getVendor();
                    case 4:
                        if (b.getPurchaseDate() != null) {
                            String purc = Formats.TIMESTAMP.formatValue(b.getPurchaseDate());

                            return purc;
                        }
                    case 5:
                      //  return decimalFormat.format(b.getcost());
                       return b.getcost();
                       
                    case 6:
                        return b.getAgency();

                    default:
                        return null;

                }

            }

        };

    }

    public List<ListOfAllAssetsTableModel.AssetInfo> getFAList() {
        if (AssetList != null) {
            return AssetList;
        } else {
            return new ArrayList<ListOfAllAssetsTableModel.AssetInfo>();
        }
    }

    public static class AssetInfo implements SerializableRead, IKeyed {
        private String id;

        private String name;
        private String barcode;
        private String majcls;
        private String subhead;
        private String acchead;
        private int stdaln;
        private String asset;
        private String vendor;
        private String created_by;
        private Date created_date;
        private Date purchseate;
        private Double totcost;
        private String sacdoc;
        private Date instdate;
        private Date commdate;
        private Date captdate;
        private Date puttousedate;
        private String agency;
        private Double rod;
        private Double wdv;
        private Double cor;
        private String make;
        private String model;
        private Date wdvDate;
        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
        public Date getWdvDate() {
            return wdvDate;
        }

        public void setWdvDate(Date wdvDate) {
            this.wdvDate = wdvDate;
        }
      
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public Date getcreated_Date() {
            return created_date;
        }

        public void setcreated_Date(Date created_date) {
            this.created_date = created_date;
        }

        public String getMajcls() {
           // return majcls;
           String majclsName="";
       try{
                        majclsName= dlfac.getaccountbyid(majcls).getName();
                        }catch(Exception e){}
        return majclsName;
 }

        public void setMajcls(String majcls) {
            this.majcls = majcls;
        }

        public String getSubHead() {
            return subhead;
        }

        public void setSubHead(String subhead) {
            this.subhead = subhead;
        }

        public String getAccHead() {
            return acchead;
        }

        public void setAccHead(String acchead) {
            this.acchead = acchead;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getAsset() {
            return asset;
        }

        public void setAsset(String asset) {
            this.asset = asset;
        }

        public String getScadoc() {
            return sacdoc;
        }

        public void setScadoc(String sacdoc) {
            this.sacdoc = sacdoc;
        }

        public Date getPurchaseDate() {

            return purchseate;
        }

        public void setPurchaseDate(Date purchasedate) {
            this.purchseate = purchasedate;
        }

        public Date getInstDate() {

            return instdate;
        }

        public void setInstDate(Date instdate) {
            this.instdate = instdate;
        }

        public Date getCommDate() {

            return commdate;
        }

        public void setCommDate(Date commdate) {
            this.commdate = commdate;
        }

        public Date getCptDate() {

            return captdate;
        }

        public void setCptDate(Date captdate) {
            this.captdate = captdate;
        }

        public Date getUseDate() {

            return puttousedate;
        }

        public void setUseDate(Date puttousedate) {
            this.puttousedate = puttousedate;
        }

        public Double getrod() {

            return rod;
        }

        public void setrod(Double rod) {
            this.rod = rod;
        }

        public Double getwdv() {

            return wdv;
        }

        public void setwdv(Double wdv) {
            this.wdv = wdv;
        }

        public Double getcor() {

            return cor;
        }

        public void setcor(Double cor) {
            this.cor = cor;
        }

        public Double getcost() {

            return totcost;
        }

        public void setcost(Double totcost) {
            this.totcost = totcost;
        }

        public String getstand_alone() {
            if (stdaln == 1) {
                return "yes";

            } else {

                return "No ";
            }
        }

        public void setstand_alone(int stdaln) {
            this.stdaln = stdaln;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public Object getKey() {
            return this;
        }

        public void readValues(DataRead dr) throws BasicException {

            name = dr.getString(1);
            barcode = dr.getString(2);
            majcls = dr.getString(3);
            subhead = dr.getString(4);
            acchead = dr.getString(5);
            vendor = dr.getString(6);
            purchseate = dr.getTimestamp(7);
            totcost = dr.getDouble(8);
            agency = dr.getString(9);
            instdate = dr.getTimestamp(10);
            commdate = dr.getTimestamp(11);
            puttousedate = dr.getTimestamp(12);
            captdate = dr.getTimestamp(13);
            rod = dr.getDouble(14);
            wdv = dr.getDouble(15);
            cor = dr.getDouble(16);
            stdaln = dr.getInt(17);
            asset = dr.getString(18);
            sacdoc = dr.getString(19);
            created_by=dr.getString(20);
            created_date=dr.getTimestamp(21);
            id=dr.getString(22);
            make=dr.getString(23);
            model=dr.getString(24);
            wdvDate=dr.getTimestamp(25);
        }

    }

}

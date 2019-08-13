package com.openbravo.pos.Accounts;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
//import com.openbravo.format.Formats;
import com.openbravo.format.Formats;
import com.openbravo.pos.clubmang.DataLogicFacilities;
import com.openbravo.pos.clubmang.DebtTypeTableModel;
import com.openbravo.pos.clubmang.Facility;
//import com.openbravo.pos.clubmang.FacilityInfo;
//import com.openbravo.pos.clubmang.Periodicity;
import com.openbravo.pos.clubmang.FacilityLogic;
import com.openbravo.pos.clubmang.Minusage;
import com.openbravo.pos.clubmang.Periodicity;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.DataLogicSales;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author swathi
 */
public class MinUsageDataModel {

    private List<MinUsageDataModel.ReportLine> data;
    private List<MinUsageCustomer> data1;
    //private int columncnt=0;
    private Double taxtotal = 0.0;
    private Double total = 0.0;
    private List<Facility> flist;
    private List<String> hlist = new ArrayList<String>();
    private final static String[] USAGEHEADER = {"NAME", "SEARCHKEY", "USAGE", "LIMIT","MUC TOTAL"};

    public static MinUsageDataModel loadEmptyInstance() {
        MinUsageDataModel d = new MinUsageDataModel();
        //d.data = new ArrayList<MinUsageDataModel.ReportLine>();
        d.data1 = new ArrayList<MinUsageCustomer>();
        return d;
    }

    public static MinUsageDataModel loadData(AppView app, String type, double a, Date sdate, Date edate, double a1, String usage) throws BasicException {
        MinUsageDataModel d = new MinUsageDataModel();
        String query = null;
        String querytemp = null;
        String queryOthers = null;


        d.data = new ArrayList<MinUsageDataModel.ReportLine>();
        List<Object[]> obj = new ArrayList<Object[]>();
        obj = new PreparedSentence(app.getSession(), "select id,name,searchkey,sum(amount),account,mobile from(select c.id as id,c.name as name,c.searchkey as searchkey,sum(b.amount)as amount,c.account,c.mobile from bill_arv b,customers c,memminusage m where c.id=b.customer and c.id=m.memno and createddate>=? and createddate<=? and c.id=m.memno and m.usagetype=? and c.visible=true and m.active=true group by c.name,c.id,c.searchkey,account,mobile union select c.id,c.name,c.searchkey,0.0 as amount,c.account,c.mobile from customers c,memminusage m where c.visible=true and m.active=true and c.id=m.memno and m.usagetype=?) as minusage group by id,name,searchkey,account,mobile order by searchkey", new SerializerWriteBasic(new Datas[]{Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.STRING}), new SerializerReadBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING})).list(new Object[]{sdate, edate, usage, usage});
        List<ReportLine> rlist = new ArrayList<ReportLine>();
        String id = null, name = null;
        double amount = 0.0, camt = 0.0;
        for (Object[] objtemp : obj) {
            id = String.valueOf(objtemp[0]);
            name = String.valueOf(objtemp[1]);
            String searchkey = String.valueOf(objtemp[2]);
            amount = Double.parseDouble(String.valueOf(objtemp[3]));
            String account = String.valueOf(objtemp[4]);
            String mobile = String.valueOf(objtemp[5]);
            if (type.equals("1")) {
                if (amount >= a) {
                    camt = 0.0;
                } else if (amount < a) {
                    camt = a1;
                }
            } else if (type.equals("2")) {
                if (amount >= a) {
                    camt = 0.0;
                } else if (amount < a) {
                    double a2 = a - amount;
                    if (a2 > a1) {
                        camt = a1;
                    } else if (a2 < a1) {
                        camt = a2;
                    }
                }

            } else if (type.equals("3")) {
                if (amount >= a) {
                    camt = 0.0;
                } else if (amount < a) {
                    double a13 = a / 3;
                    double a23 = a13 * 2;
                    if (amount < a13) {
                        camt = a1;
                    } else if (amount >= a13 && amount < a23) {
                        camt = (a1 / 3) * 2;
                    } else if (amount >= a23 && amount < a) {
                        camt = a1 / 3;
                    }
                }

            }
            ReportLine r = new ReportLine(id, name, searchkey, amount, camt, account, mobile);
            d.data.add(r);
        }

        return d;
    }
    public static MinUsageDataModel loadData(AppView app, List<MinUsageCustomer> allCustomers) throws BasicException {
        MinUsageDataModel d = new MinUsageDataModel();
        d.data1 = allCustomers;
        return d;
    }

    public List<ReportLine> getReportline() {
        return data;
    }
    public List<MinUsageCustomer> getMinUsageCustomer() {
        return data1;
    }


   public AbstractTableModel getUsageModel() {
        return new AbstractTableModel() {

            public String getColumnName(int column) {
                return AppLocal.getIntString(USAGEHEADER[column]);
            }

            public int getRowCount() {
                return data1.size();
            }

            public int getColumnCount() {
                return USAGEHEADER.length;
            }

            /*  public Object getValueAt(int row, int column) {
            ReportLine l = data.get(row);
            switch (column) {
            case 0:
            return l.getName();
            case 1:
            return l.getSearchkey();
            case 2:
            return l.getAmount();
            case 3:
            return l.getChargableAmount();
            case 4:
            return l.getAccount();
            case 5:
            return l.getMobile();
            case 6:
            return l.getid();
            case 7:
            return l.getsdate();
            case 8:
            return l.getlbilldate();
            case 9:
            return l.getamt();
            case 10:
            return l.getno();
            case 11:
            return l.getbillabledate();
            case 12:
            return l.getbillit();
            case 13:
            return l.getcustomeraccount();
            case 15:
            return l.getDueDate();


            default:
            return null;
            }
            }*/
            public Object getValueAt(int row, int column) {
                MinUsageCustomer l = data1.get(row);
                switch (column) {
                    case 0:
                        return l.getName();
                    case 1:
                        return l.getSearchkey();
                    case 2:
                        return l.getUsageDisplay();
                    case 3:
                        return l.getLimitDisplay();
                    case 4:
                        return l.getChargeDisplay();
                    case 5:
                        return l.getUsageTotal();
                    case 6:
                        return l.getCamtTotal();



                    default:
                        return null;
                }
            }
        };
    }
    private static int temp = 0;
    public static class ReportLine implements SerializableRead {
        private String name;
        private String searchkey;
        private double amount;
        private double camt;
        private String account;
        private String mobile;
        private String id;
        private Timestamp sdate;
        private Timestamp lbdate;
        private Double amt = 0.0;
        private int no;
        private Timestamp billabledate;
        private Boolean billit = true;
        private String caccount;
        private int slno = 0;
        private double tax = 0.0;
        private double total = 0.0;
        private Date duedate;

        public ReportLine(String id, String name, String searchkey, double amount, double camt, String account, String mobile) {
            this.id = id;
            this.name = name;
            this.searchkey = searchkey;
            this.amount = amount;
            this.camt = camt;
            this.account = account;
            this.mobile = mobile;

        }
        public String getName() {
            return name;
        }

        public String getSearchkey() {
            return searchkey;
        }
        public String getAccount() {
            return account;
        }

        public String getMobile() {
            return mobile;
        }

        public double getAmount() {
            return amount;
        }

        public double getChargableAmount() {
            return camt;
        }

        public Date getDueDate() {
            return duedate;
        }

        public void setDueDate(Date date) {
            duedate.setTime(date.getTime());
        }

        public double getTax() {
            return tax;
        }

        public double getTotal() {
            return total;
        }

        public void setTax(double tax) {
            this.tax = tax;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getcustomeraccount() {
            return caccount;
        }

        public Boolean getbillit() {
            return billit;
        }

        public Double getamt() {
            return amt;
        }

        public Timestamp getbillabledate() {
            return billabledate;
        }

        public void setbillabledate(Date date) {
            billabledate.setTime(date.getTime());
        }

        public int getno() {
            return no;
        }

        public void setno(int nno) {
            no = nno;
        }

        public void setamt(Double amount) {
            amt = amount;
        }

        public String getid() {
            return id;
        }
        public Timestamp getsdate() {
            return sdate;
        }
        public Timestamp getlbilldate() {
            return lbdate;
        }

        public String getSlNo() {
            return String.valueOf(slno);
        }
        public void readValues(DataRead dr) throws BasicException {
            slno = ++temp;
            id = dr.getString(1);
            name = dr.getString(2);
            searchkey = dr.getString(3);
            amount = dr.getDouble(4);
            camt = dr.getDouble(5);
            account = dr.getString(6);
            mobile = dr.getString(7);
            sdate = dr.getTimestamp(8);
            lbdate = dr.getTimestamp(9);
            no = dr.getInt(10);
            caccount = dr.getString(11);
            mobile = dr.getString(12);
            duedate = new Date();
            billabledate = new Timestamp(new Date().getTime());
            tax = 0.0;
            total = 0.0;
        }
    }
}



package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import java.util.List;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;


public class UserAuthorisationModel {
    private List<UserAuthorisationLine> ualist;
    private List<UserAuthorisationLine> ualist1;     
    private final static String[] APPROVALHEADERS = {"Member No","Member Name","Date","Requested By","Approved By","Approved Date","Status"};
    private final static String[] APPROVALHEADERS1 = {"Member No","Member Name","Date","Requested By","Approved By","Approved Date","Status"};

    private UserAuthorisationModel(){
        
    }
    public static UserAuthorisationModel emptyinstance(){
        UserAuthorisationModel f=new UserAuthorisationModel();
        f.ualist=new ArrayList<UserAuthorisationLine>();
        return f;
    }
    
    public static UserAuthorisationModel loadinstance(AppView app) throws BasicException{
        UserAuthorisationModel f=new UserAuthorisationModel();
        List ulist = new StaticSentence(app.getSession(), "SELECT U.ID,U.MEMID,U.RDATE,U.REQUESTEDBY,U.APPROVEDBY,U.APPDATE,U.STATUS_,C.NAME,C.SEARCHKEY,U.WAITER,U.PLACE,U.FLOOR,U.COUNTER FROM USERAUTHORISATION U,CUSTOMERS C,WAITER W  WHERE C.ID=U.MEMID AND W.ID=U.WAITER AND WAITER IS NOT NULL AND U.STATUS_ IS NULL",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(UserAuthorisationModel.UserAuthorisationLine.class)).list();
        if(ulist==null){
            f.ualist=new ArrayList<UserAuthorisationLine>();
        }else{
            f.ualist=ulist;
        }
        return f;
    }
    
   public static UserAuthorisationModel loadinstance1(AppView app) throws BasicException{
    UserAuthorisationModel f=new UserAuthorisationModel();
    List uclist = new StaticSentence(app.getSession(), "SELECT U.ID,U.MEMID,U.RDATE,U.REQUESTEDBY,U.APPROVEDBY,U.APPDATE,U.STATUS_,C.NAME,C.SEARCHKEY,null,null,null,U.COUNTER FROM USERAUTHORISATION U,CUSTOMERS C  WHERE C.ID=U.MEMID AND WAITER IS  NULL AND U.STATUS_ IS NULL",
    SerializerWriteString.INSTANCE,
    new SerializerReadClass(UserAuthorisationModel.UserAuthorisationLine.class)).list();
    if(uclist==null){
    f.ualist1=new ArrayList<UserAuthorisationLine>();
    }else{
    f.ualist1=uclist;
    }
    return f;
    }

    public List<UserAuthorisationLine> getApprovalLines(){
        return ualist;
    }

    public List<UserAuthorisationLine> getApprovalLines1(){
        return ualist1;
    }
    

    public AbstractTableModel getApprovalTableModel(){
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(APPROVALHEADERS[column]);
            }

            public int getRowCount() {
               return  ualist.size();
            }

            public int getColumnCount() {
                return APPROVALHEADERS.length;
            }

            public Object getValueAt(int row, int column) {
                UserAuthorisationLine l=ualist.get(row);
                switch(column){
                    case 0:return l.getSkey();
                    case 1:return l.getMemname();
                    case 2:return l.getDate();
                    case 3:return l.getRequestedby();
                    case 4:return l.getApprovedby();                                      
                    case 5:return l.getAppdate();
                    case 6:return l.getStatus();
                    case 7:return l.getMemid();
                    case 8:return l.getId();
					case 9:return l.getWaiter();
					case 10:return l.getPlace();
					case 11:return l.getFloor();
                    case 12:return l.getRole();
					
                    
                    default:return null;

                }

            }
        };
    }

    public AbstractTableModel getCreditApprovalTableModel(){
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return AppLocal.getIntString(APPROVALHEADERS1[column]);
            }

            public int getRowCount() {
               return  ualist1.size();
            }

            public int getColumnCount() {
                return APPROVALHEADERS1.length;
            }

            public Object getValueAt(int row, int column) {
                UserAuthorisationLine l=ualist1.get(row);
                switch(column){
                    case 0:return l.getSkey();
                    case 1:return l.getMemname();
                    case 2:return l.getDate();
                    case 3:return l.getRequestedby();
                    case 4:return l.getApprovedby();
                    case 5:return l.getAppdate();
                    case 6:return l.getStatus();
                    case 7:return l.getMemid();
                    case 8:return l.getId();
                    case 9:return l.getRole();                  

                    default:return null;

                }

            }
        };
    }
    
    public static class UserAuthorisationLine implements SerializableRead{
        private String id;
        private String memid;
        private Date date;
        private String requestedby;
        private String approvedby;
        private Date appdate;
        private Boolean status;
        private String memname;
        private String skey;
		private String waiter;
		private String place;
		private String floor;
        private String role;

        public String getRole() {
            return role;
        }
		


        public void readValues(DataRead dr) throws BasicException {
            id = dr.getString(1);
            memid = dr.getString(2);
            date = dr.getTimestamp(3);
            requestedby = dr.getString(4);
            approvedby = dr.getString(5);
            appdate = dr.getTimestamp(6);
            status = dr.getBoolean(7);
            memname = dr.getString(8);
            skey = dr.getString(9);
			waiter = dr.getString(10);
			place = dr.getString(11);
			floor = dr.getString(12);
            role = dr.getString(13);

        }
		public String getWaiter() {
            return waiter;
        }
		public String getPlace() {
            return place;
        }
		public String getFloor() {
            return floor;
        }

        public String getSkey() {
            return skey;
        }

        public String getMemname() {
            return memname;
        }

        public Date getAppdate() {
            return appdate;
        }

        public String getApprovedby() {
            return approvedby;
        }

        public Date getDate() {
            return date;
        }

        public String getId() {
            return id;
        }

        public String getMemid() {
            return memid;
        }

        public String getRequestedby() {
            return requestedby;
        }

        public Boolean getStatus() {
            return status;
        }
        
        
    }

    


}


package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.AppView;
import java.util.Date;
import java.util.List;


public class FacilityLimitCheck {
    Boolean bool=true;
    private List<FacilityLimitMaster> faclim;
    public Object[] checkFacilityLimit(AppView app,String memid,String facid) throws BasicException{
        FacilityLimitCheck flc=new FacilityLimitCheck();
        Object[] val=new Object[]{memid,facid};
        Object[] obj=  (Object[]) new StaticSentence(app.getSession(), "SELECT FM.MEMID,F.NAME,FM.FACID,FM.ELIGEBLE,FM.AMOUNT,FM.CALDATE,F.AMOUNTLIMIT FROM FACILITYLIMITMASTER FM,FACILITY F  WHERE MEMID=? AND FACID=? AND FM.PFID=F.ID",
                 new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING}),
                 new SerializerReadBasic(new Datas[]{Datas.STRING,Datas.STRING,Datas.STRING,Datas.BOOLEAN,Datas.DOUBLE,Datas.TIMESTAMP,Datas.DOUBLE})).find(val);
                 //new SerializerReadClass(FacilityLimitCheck.FacilityLimitMaster.class)).find(new Object[]{memid,facid});
        return obj;
    }
    /*public Object[] check(AppView app,CustomerInfoExt c){
    Object[] obj = null;
    try {
    String s=app.getAppUserView().getUser().getRole();
    if("4".equals(s)){
    String id="2";
    obj = checkFacilityLimit(app, c.getId(),id);
    }
    else{
    String id="1";
    obj = checkFacilityLimit(app, c.getId(),id);
    }

    } catch (BasicException ex) {
    ex.printStackTrace();
    }
    return obj;

    }*/
    public List<FacilityLimitMaster> getCheckList(){
        return faclim;
    }
    public class FacilityLimitMaster implements SerializableRead{
        private String memid;
        private String pfid;
        private String facid;
        private Boolean  flag;
        private Double amount;

        public String getPfid() {
            return pfid;
        }

        public Double getAmount() {
            return amount;
        }

        public Date getDate() {
            return date;
        }

        public String getFacid() {
            return facid;
        }

        public Boolean getFlag() {
            return flag;
        }

        public String getMemid() {
            return memid;
        }
        private Date date;

        public void readValues(DataRead dr) throws BasicException {
            memid=dr.getString(1);
            pfid=dr.getString(2);
            facid=dr.getString(3);
            flag=dr.getBoolean(4);
            amount=dr.getDouble(5);
            date=dr.getTimestamp(6);
        }

    }


}

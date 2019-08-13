/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.clubmang.MemberStatementModel.addressline;
import com.openbravo.pos.forms.AppView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author swathi
 */
public class MemberAddressModel {
    private List<MemberCategoryLine> memcat;
    private List<addressline> memaddr;


    public MemberAddressModel(){

    }

    public static MemberAddressModel loadInstance(AppView app) throws BasicException{
      MemberAddressModel d = new MemberAddressModel();

         List dlist = new StaticSentence(app.getSession()               
                ,"SELECT NAME,SUM(VNO),SUM(INVNO) FROM (SELECT M.NAME AS NAME , COUNT(C.ID) AS VNO,0 AS INVNO FROM CUSTOMERS C,MEMTYPE M WHERE M.ID=C.MEMTYPE AND C.VISIBLE=TRUE GROUP BY MEMTYPE " +
                " UNION ALL " +
                " SELECT M.NAME AS NAME ,0 AS VNO,COUNT(C.ID) AS  INVNO FROM CUSTOMERS C,MEMTYPE M WHERE M.ID=C.MEMTYPE AND C.VISIBLE=FALSE GROUP BY MEMTYPE )AS SDFH GROUP BY NAME ORDER BY NAME"
              ,SerializerWriteString.INSTANCE
              ,new SerializerReadClass( MemberAddressModel.MemberCategoryLine.class )).list();
         if(dlist==null)
         {
             d.memcat=new ArrayList<MemberCategoryLine>();
         }
         else
        {
            d.memcat=dlist;
        }

     return d;

  }

    public static MemberAddressModel loadInstance1(AppView app) throws BasicException{
      MemberAddressModel d = new MemberAddressModel();
         List<addressline> adr = new StaticSentence(app.getSession(),
                "SELECT ADDRESS,ADDRESS2,POSTAL,CITY,SEARCHKEY,NAME,phone,MObile FROM CUSTOMERS WHERE VISIBLE=TRUE order by searchkey",
                SerializerWriteString.INSTANCE,
                new SerializerReadClass(MemberStatementModel.addressline.class)).list();
        String addr = null;
        if (adr != null) {
            for (addressline a : adr) {
                addr = a.getSearchKey() + " \r\n" + a.getMemname();
                if(a.getAddress()!=null)
                 addr = addr + " \r\n" + a.getAddress();
                if(a.getAddress2()!=null)
                    addr = addr + " \r\n" + a.getAddress2();
                if(a.getCity()!=null)
                    addr = addr + " \r\n" + a.getCity();
                if(a.getPostal()!=null)
                    addr = addr + " \r\n" + "Pin: " + a.getPostal();
                if(a.getPhone()!=null)
                    addr = addr + " \r\n" + "Phone: "+ a.getPhone();
                if(a.getPhone2()!=null)
                    addr = addr + " \r\n" + "Phone(M): "+a.getPhone2();
                a.setFaddress(addr);
            }
        }
        if(adr!=null)
        d.memaddr = adr;
        else
        d.memaddr = new ArrayList<addressline>();

     return d;

  }    

    public List<MemberCategoryLine> getMemcat()
    {
        return memcat;
    }

    public void setMemcat(List<MemberCategoryLine> memcat)
    {
        this.memcat = memcat;
    }

    public List<addressline> getMemaddr()
    {
        return memaddr;
    }

    public void setMemaddr(List<addressline> memaddr)
    {
        this.memaddr = memaddr;
    }

    
    public static class MemberCategoryLine implements SerializableRead{
        private String catsname;
        private String catname;
        private int visiblenor;
        private int nonvisiblenor;


        public void readValues(DataRead dr) throws BasicException
        {           
            catname = dr.getString(1);
            visiblenor = dr.getInt(2);
            nonvisiblenor = dr.getInt(3);
        }

        public String getCatname()
        {
            return catname;
        }

        public void setCatname(String catname)
        {
            this.catname = catname;
        }

        public String getCatsname()
        {
            return catsname;
        }

        public void setCatsname(String catsname)
        {
            this.catsname = catsname;
        }

        public int getNonvisiblenor()
        {
            return nonvisiblenor;
        }

        public void setNonvisiblenor(int nonvisiblenor)
        {
            this.nonvisiblenor = nonvisiblenor;
        }

        public int getVisiblenor()
        {
            return visiblenor;
        }

        public void setVisiblenor(int visiblenor)
        {
            this.visiblenor = visiblenor;
        }

    }

}

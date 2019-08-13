/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.format.Formats;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author swathi
 */
/*
        id int identity,
 name varchar not null,
 parent varchar not null,
 ccamount double default 0.0,
 _min int not null,
 _max int not null,
 withtoken boolean not null,
 singlepayment boolean not null,
 crdate timestamp not null,
 createdby varchar not null,
 Gseq varchar not null,
 seqmax int not null,
 printref varchar not null
*/
public class GameInfo implements SerializableRead{
    protected int id;
    protected String name;
    protected int parent;
    protected double clubCollection;
    protected int _min;
    protected int _max;
    //protected boolean withToken;
    protected Date crdate;
    protected String crby;
    protected String Gseq;
    protected int seqmax;
    protected String printRef;
    protected boolean active;
    protected String pname;
    protected String taxCat;
    protected boolean perplayer;
    protected String tokenref;
    protected int paymenttime;//0-before 1-after
    protected double gameAmt;
    public GameInfo() {
    }
    

    public GameInfo(int id, String name, int parent, double clubCollection, int _min, int _max,  Timestamp crdate, String crby, String Gseq, int seqmax, String printRef,boolean active,String deactby,Date deactdate) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.clubCollection = clubCollection;
        this._min = _min;
        this._max = _max;
        //this.withToken = withToken;
        this.crdate = crdate;
        this.crby = crby;
        this.Gseq = Gseq;
        this.seqmax = seqmax;
        this.printRef = printRef;
        this.active=active;
       // this.deactby=deactby;
        //this.deactdate=deactdate;
    }
    public void readValues(DataRead dr) throws BasicException {
        this.id = dr.getInt(1);
        this.name = dr.getString(2);
        if(dr.getInt(3)==null)
            this.parent=-1;
        else
             this.parent = dr.getInt(3);
        this.clubCollection = dr.getDouble(4);
        this._min = dr.getInt(5);
        this._max = dr.getInt(6);
        this.crdate = dr.getTimestamp(7);
        this.crby = dr.getString(8);
        this.Gseq = dr.getString(9);
        this.seqmax = dr.getInt(10);
        this.printRef = dr.getString(11);
        this.active=dr.getBoolean(12);
        this.pname=dr.getString(13);
        this.taxCat=dr.getString(14);
        this.perplayer=dr.getBoolean(15);
        this.tokenref=dr.getString(16);
        //this.paymenttime=dr.getInt(17);
        this.gameAmt=dr.getDouble(17);
    }
    //public boolean isCCPerPlayer(){
    //   return perplayer;
   // }
    public double getGameAmount(){
       return gameAmt;
    }
    public int getPaymentTime(){
        return paymenttime;
    }
    public boolean isCCperPlayer(){
      return perplayer;
    }
    public String getTokenref(){
      return tokenref;
    }
    public String getParentName(){
       return pname;
    }
    public String getTaxCategory(){
       return taxCat;
    }
    public boolean isActive(){
      return active;
    }
    public int getID(){
       return id;
    }
    public String getName(){
      return name;
    }
    public int getParent(){
        return parent;
    }
    public double getClubCollection(){
      return clubCollection;
    }
    public String printClubCollection(){
      return Formats.ConvertDoubleToString(clubCollection);
    }
    public int getMin(){
       return _min;
    }
    public int getMax(){
       return _max;
    }
   // public boolean WithToken(){
     //  return withToken;
    //}
    public String getCreatedBy(){
     return crby;
    }
    public Date getCreatedDate(){
      return crdate;
    }
    public int getSequenceMax(){
      return seqmax;
    }
    public String getSequence(){
      return Gseq;
    }
    public String getprintref(){
       return printRef;
    }

    @Override
    public String toString() {
        return name;
    }


}

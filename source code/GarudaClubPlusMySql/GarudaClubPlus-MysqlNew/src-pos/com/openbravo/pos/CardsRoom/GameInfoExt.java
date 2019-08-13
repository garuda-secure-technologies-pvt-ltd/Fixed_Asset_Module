/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.ImageUtils;
import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class GameInfoExt extends GameInfo {
    private String deactby;
    private Date deactdate;
    private BufferedImage m_Image;
    private int cnt=0;

    /*public GameInfoExt(int id, String name, String parent, double clubCollection, int _min, int _max, boolean withToken, Timestamp crdate, String crby, String Gseq, int seqmax, String printRef,boolean active,String deactby,Date deactdate) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.clubCollection = clubCollection;
        this._min = _min;
        this._max = _max;
        this.withToken = withToken;
        this.crdate = crdate;
        this.crby = crby;
        this.Gseq = Gseq;
        this.seqmax = seqmax;
        this.printRef = printRef;
        this.active=active;
        this.deactby=deactby;
        this.deactdate=deactdate;
    }*/
    @Override
    public void readValues(DataRead dr) throws BasicException {
        this.id = dr.getInt(1);
        this.name = dr.getString(2);
        if(dr.getInt(3)==null)
            this.parent=-1;
        else
             this.parent = dr.getInt(3);
        //this.parent = dr.getInt(3);
        this.clubCollection = dr.getDouble(4);
        this._min = dr.getInt(5);
        this._max = dr.getInt(6);
        //this.withToken = dr.getBoolean(7);
        this.crdate = dr.getTimestamp(7);
        this.crby = dr.getString(8);
        this.Gseq = dr.getString(9);
        this.seqmax = dr.getInt(10);
        this.printRef = dr.getString(11);
        this.active=dr.getBoolean(12);
        this.deactby=dr.getString(13);
        this.deactdate=dr.getTimestamp(14);
        this.pname=dr.getString(15);
        m_Image = ImageUtils.readImage(dr.getBytes(16));
        cnt=dr.getInt(17);
        this.taxCat=dr.getString(18);
        this.perplayer=dr.getBoolean(19);
        this.tokenref=dr.getString(20);
        this.gameAmt=dr.getDouble(21);
    }
    public String getDeactBy(){
       return deactby;
    }
    public Date getDeactDate(){
       return deactdate;
    }
    public BufferedImage getImage(){
      return m_Image;
    }
    public int getChildCount(){
      return cnt;
    }
}

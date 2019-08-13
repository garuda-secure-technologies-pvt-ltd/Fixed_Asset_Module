/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import java.util.Date;

/**
 *
 * @author swathi
 */
public class CancelRequestInfo implements SerializableRead {
  private String id;
  private String initBy;
  private Date initDate;
  private String gamelogid;
  private String table;
  private int nop;
  private double clubCollection;
  private String gameSeq;
  private String gameName;
  private String custName;
  private String custSKey;
  //private String role;

   //GR.ID,GR.INITIATEDBY,GR.INITIATEDDATE,GL.ID,GL.TABLE,GL.NOP,GL.CLUBCOLLECTION,GL.GAMESEQ,G.NAME,C.NAME,C.SEARCHKEY
    public void readValues(DataRead dr) throws BasicException {
       id=dr.getString(1);
       initBy=dr.getString(2);
       initDate=dr.getTimestamp(3);
       gamelogid=dr.getString(4);
       table=dr.getString(5);
       nop=dr.getInt(6);
       clubCollection=dr.getDouble(7);
       gameSeq=dr.getString(8);
       gameName=dr.getString(9);
       custName=dr.getString(10);
       custSKey=dr.getString(11);
       /*gameid=dr.getString(2);
       initby=dr.getString(3);
       initdate=dr.getTimestamp(4);
       processedby=dr.getString(5);
       processedDate=dr.getTimestamp(6);
       status=dr.getInt(7);*/
    }

    public String getID(){
      return id;
    }
    public String getInitBy(){
       return initBy;
    }
    public Date getInitDate(){
       return initDate;
    }
    public String getgameLogID(){
      return gamelogid;
    }
    public String getTable(){
       return table;
    }
    public int getNoOfPlayers(){
       return nop;
    }
    public double getClubCollection(){
       return clubCollection;
    }
    public String getGameSeq(){
       return gameSeq;
    }
    public String getGameName(){
       return gameName;
    }
    public String getCustName(){
       return custName;
    }
    public String getCustSkey(){
       return custSKey;
    }

}

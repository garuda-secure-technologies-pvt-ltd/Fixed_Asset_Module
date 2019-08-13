/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.pos.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author swathi
 */
public class GameLog implements SerializableRead{

    private String id;
    private String jackid;
    private int gameid;
    private int nop;
    private String paymentref;
    private Date crdate;
    private String crby;
    private String jackName;
    private String jackNo;
    private String gameName;
    private String tableId;
    private String tableName;
    private List<PlayeresData> playerList=new ArrayList<PlayeresData>();
    private double clubcollection;
    private String seqNo;
    private double gameAmt;

    public GameLog() {
    }
    public GameLog(String jackID,String jackName,String jackNo,String crby,Date crdate){
      this.jackName=jackName;
      this.jackid=jackID;
      this.jackNo=jackNo;
      this.crby=crby;
      this.crdate=crdate;
    }
    //G.ID,G.JACKID,G.GAMEID,G.NOP,G.PAYMENTREF,G.CRDATE,G.CRBY,C.NAME,C.SEARCHKEY,G1.NAME,P.ID,P.NAME,G.CLUBCOLLECTION,G.GAMESEQ
    public void readValues(DataRead dr) throws BasicException {
        id=dr.getString(1);
        jackid=dr.getString(2);
        if(dr.getInt(3)==null)
            gameid=-1;
        else
            gameid=dr.getInt(3);
        if(dr.getInt(4)==null)
            nop=0;
        else
            nop=dr.getInt(4);
        paymentref=dr.getString(5);
        crdate=dr.getTimestamp(6);
        crby=dr.getString(7);
        jackName=dr.getString(8);
        jackNo=dr.getString(9);
        gameName=dr.getString(10);
        tableId=dr.getString(11);
        tableName=dr.getString(12);
        if(dr.getDouble(13)==null)
            clubcollection=0;
        else
            clubcollection=dr.getDouble(13);
        seqNo=dr.getString(14);
        //gameAmt=dr.getDouble(15);
    }
    public void setGameAmount(double amt){
        gameAmt=amt;
    }
    public double getGameAmount(){
       return gameAmt;
    }
    public double getClubCollection(){
       return clubcollection;
    }
    public void setClubCollection(double val){
      clubcollection=val;
    }
    public void addPlayer(PlayeresData ele){
      playerList.add(ele);
    }
    public void addPlayers(List<PlayeresData> ele){
      playerList.addAll(ele);
    }
    public List<PlayeresData> getPalyersList(){
       return playerList;
    }
    public void setSeqNo(String seq){
       seqNo=seq;
    }
    public String getSeqNo(){
       return seqNo;
    }
    public String getTableID(){
      return tableId;
    }
    public String getTableName(){
       return tableName;
    }
    public void setTableID(String id){
      tableId=id;
    }
    public void setTableName(String name){
       tableName=name;
    }
    public String getJackName(){
      return jackName;
    }
    public String getJackNo(){
      return jackNo;
    }
    public String getGameName(){
       return gameName;
    }
    public String getID(){
       return id;
    }
    public String getJackID(){
       return jackid;
    }
    public int getGameID(){
       return gameid;
    }
    public void setGameID(int id){
       gameid=id;
    }
    public void setID(String id){
       this.id=id;
    }
    public void setGameName(String name){
       gameName=name;
    }
    public int getNoOfPlayers(){
      return nop;
    }
    public void setNoOfPlayers(int nop){
      this.nop=nop;
    }
    public String getPaymentRef(){
      return paymentref;
    }
    public void setPaymentRef(String pref){
      paymentref=pref;
    }
    public String getCreatedBy(){
      return crby;
    }
    public void setCreatedBy(String crby){
      this.crby=crby;
    }
    public void setCreatedDate(Date d){
      crdate=new Date(d.getTime());
    }
    public Date getCreatedDate(){
      return crdate;
    }

}

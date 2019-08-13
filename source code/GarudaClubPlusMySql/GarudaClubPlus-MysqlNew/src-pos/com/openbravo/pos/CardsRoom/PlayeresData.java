/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import com.openbravo.data.loader.SerializerRead;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
public class PlayeresData implements SerializableRead{

    private String memName;
    private String memID;
    private List<TokenElements> tokenList;
    private double totalTokenValue;
    private String guestName;
    private String tokenLogID;
    private String memNo;
    private int tokenCombCount=0;
    public PlayeresData(String memName, String memID, List<TokenElements> tokenList, double totalTokenValue,String guestName,String tokenLogID,String memNo) {
        this.memName = memName;
        this.memID = memID;
        this.tokenList = tokenList;
        this.totalTokenValue = totalTokenValue;
        this.guestName=guestName;
        this.tokenLogID=tokenLogID;
        this.memNo=memNo;
    }

    public PlayeresData() {
    }
    

    public void readValues(DataRead dr) throws BasicException {
        memID=dr.getString(1);
        memName=dr.getString(2);
        totalTokenValue=dr.getDouble(3);
        memNo=dr.getString(4);
        guestName=dr.getString(5);
        tokenLogID=dr.getString(6);
        tokenList=new ArrayList<TokenElements>();
    }
    public String getGuestName(){
        return guestName;
    }
    public String getMemberNo(){
      return memNo;
    }
    public String getMemberName(){
       return memName;
    }
    public String getMemberID(){
       return memID;
    }
    public double getTokenTotalValue(){
        return totalTokenValue;
    }
    public int getTokenCombCnt(){
        return tokenCombCount;
    }
    public void setTokenCombCnt(int cnt){
        tokenCombCount=cnt;
    }
    //public List<TokenElements> getTokenList(){
    //   return tokenList;
   // }
    public void addToken(TokenElements tele){
        tokenList.add(tele);
    }
    public String getTokenLogID(){
       return tokenLogID;
    }
    public void setTokenLogID(String id){
       tokenLogID=id;
    }

}

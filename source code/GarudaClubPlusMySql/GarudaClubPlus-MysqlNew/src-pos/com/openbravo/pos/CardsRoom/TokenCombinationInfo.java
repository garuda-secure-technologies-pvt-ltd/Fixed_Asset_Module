/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.CardsRoom;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.SerializableRead;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author swathi
 */
public class TokenCombinationInfo implements SerializableRead{
    private String tokenID;
    private String tokenName;
    private double totalVal;
    private List<TokenElements> tElements;
     public void readValues(DataRead dr) throws BasicException {
        tokenID=dr.getString(1);
        tokenName=dr.getString(2);
        totalVal=dr.getDouble(3);
    }

    public TokenCombinationInfo() {
    }
     
    public TokenCombinationInfo(String tokenID, String tokenName, double totalVal, List<TokenElements> tElements) {
        this.tokenID = tokenID;
        this.tokenName = tokenName;
        this.totalVal = totalVal;
        this.tElements = new ArrayList<TokenElements>(tElements);
    }

    public String getTokenID(){
       return tokenID;
    }
    public String getTokenName(){
       return tokenName;
    }
    public double getTotalVAlue(){
       return totalVal;
    }
    public List<TokenElements> getTokenElements(){
       return tElements;
    }
    public void addToken(TokenElements ele){
       tElements.add(ele);
    }

    @Override
    public String toString() {
        return tokenName;
    }

   
}

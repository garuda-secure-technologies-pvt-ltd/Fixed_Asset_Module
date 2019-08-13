/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.forms;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.mant.WaiterInfo;
import com.openbravo.data.loader.*;


public class WaiterInfoForCardAccess {

    public final WaiterInfo getActiveCardWaiterByCard(String card,Session s) throws BasicException {
        return (WaiterInfo) new PreparedSentence(s, "SELECT W.ID,W.NAME,W.COUNTER,W.CARDNO FROM WAITER W WHERE W.CARDNO=?", SerializerWriteString.INSTANCE, new SerializerReadClass(WaiterInfo.class)).find(card);
    }

}

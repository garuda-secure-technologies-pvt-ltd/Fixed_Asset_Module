/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.reports;

import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.QBFCompareEnum;
import com.openbravo.data.loader.SerializerWrite;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.LookupUtilityImpl;
import java.awt.Component;

/**
 *
 * @author swathi
 */
public class GetCurrentUser extends javax.swing.JPanel implements ReportEditorCreator{
    
    String user;
   public GetCurrentUser()    {
    }

   private String getUser() {
        return LookupUtilityImpl.getInstance(null).getAppView().getAppUserView().getUser().getName();
   }

    public void init(AppView app) {

    }

    public void activate() throws BasicException {

    }

    public SerializerWrite getSerializerWrite() {
         return new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING});
    }

    public Component getComponent() {
        return this;
    }

    public Object createValue() throws BasicException {
        Object user = getUser();
        return new Object[] { 
            user == null? QBFCompareEnum.COMP_NONE : QBFCompareEnum.COMP_EQUALS,
            user
        };
    }

}



package com.openbravo.pos.sms;

import com.openbravo.data.loader.Session;
import com.openbravo.pos.forms.BeanFactoryDataSingle;


public class SendEmailTableModel  extends BeanFactoryDataSingle{
    private Session s;

    
    
    
    @Override
    public void init(Session s) {
       this.s=s;
    }
    
    
    
    
    
}

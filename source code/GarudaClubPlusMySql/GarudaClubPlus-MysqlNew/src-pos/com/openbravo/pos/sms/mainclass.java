/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sms;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */

public class mainclass {
    private static CardReader1 cr;
    public static void main(String args[]){
         cr = new CardReader1("COM5",0);
        try {
            cr.ConfigurePort();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.Accounts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author swathi
 */
public class NewClass {

    public static void main(String[] args){
        try {
            DateFormat d = new SimpleDateFormat("HH:mm a");
            String timeObject = "06:00AM";
            Date tObject = (Date) d.parse(timeObject);
            System.out.print(tObject);
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
    }
}

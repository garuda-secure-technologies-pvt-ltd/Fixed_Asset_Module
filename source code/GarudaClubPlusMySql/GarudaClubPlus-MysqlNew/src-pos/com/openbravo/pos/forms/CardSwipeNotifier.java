/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.forms;


import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.mant.WaiterInfo;



public interface  CardSwipeNotifier {

 public  void cardswiped(WaiterInfo waiter);
 public void cardswiped(String custCard);

}
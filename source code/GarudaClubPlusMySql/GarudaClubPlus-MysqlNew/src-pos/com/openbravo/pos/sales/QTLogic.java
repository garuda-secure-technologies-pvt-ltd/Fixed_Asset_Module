/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.sales;

import java.util.ArrayList;
import java.util.List;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.forms.DataLogicSales;
import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.LookupUtilityImpl;
import com.openbravo.pos.printer.TicketParser;
import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.sales.QticketInfo;
import com.openbravo.pos.scripting.ScriptEngine;
import com.openbravo.pos.scripting.ScriptException;
import com.openbravo.pos.scripting.ScriptFactory;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;



/**
 *
 * @author swathi
 */
public class QTLogic{

    private DataLogicSales dlSales;
    //private String pdt;
    private List tlf;
    private int notlf;
    private TicketInfo ticket;
    private Qticket tick;
    private QTicketLineInfo qtl;
    private Map<String, QticketInfo> qtMap;
    private TicketParser m_TTP;

    public QTLogic(TicketInfo t, DataLogicSales dlSales, Qticket qticket) {
        this.ticket = t;
        this.dlSales = dlSales;
        this.tick = qticket;
        tlf = ticket.getLines();

        notlf = tlf.size();

        qtMap = new TreeMap<String, QticketInfo>();
      }

    public void dispatchQT () throws BasicException {
        for(int j=0; j<notlf; j++) {
             TicketLineInfo t = ticket.getLine(j);
             ProductInfoExt p1 = dlSales.getProductInfo(t.getProductID());
             QticketInfo qt = null;
             if (!qtMap.containsKey(p1.getPRCategory())) {
                 qt = new QticketInfo();
                 qt.setLines(new ArrayList<QTicketLineInfo>());
                 qtMap.put(p1.getPRCategory(), qt);
             } else {
                 qt = qtMap.get(p1.getPRCategory());
             }
             qtl = new QTicketLineInfo();
             qt.setCustomer(ticket.getCustomer());
             qt.setPlace(ticket.getPlaceId());
             qt.setWaiter(ticket.getWaiterId());
             //TODO setfloor
             qt.setFloor(ticket.getFloorId());
             qt.setprCategory(p1.getPRCategory());
             qt.setBilled(false);
             qt.setCreatedby(tick.getAppView().getAppUserView().getUser().getName()); //TODO change to ID later
             qt.setCreatedDate(new Date());
//             boolean bool = dlSales.getDecimalWarehouseAllowed(ticket.getLine(0).getWarehouse());
//             if(bool){
//                 qtl.setMultiply((int) t.getMultiply());
//             }else{
             qtl.setMultiply((int) t.getMultiply());
//             }
             qtl.setProduct(t.getProductID());
             qtl.setParentid(qt.getId());
             qtl.setRate(t.getValue());
             qtl.setProperties(t.getProperties());
             qt.insertLine(qt.getLinesCount(), qtl);
             if(ticket.getLines().size()>0)
             qt.setWarehouse(ticket.getLine(0).getWarehouse());
             else
                 throw new BasicException("Error occured while setting the ware house");
              //praveen:initiator changes---start
             String s=dlSales.getInitiator(ticket.getCustomerId(), tick.getAppView().getAppUserView().getUser().getRole());
             if(!"null".equals(s))
             qt.setInitiator(s);
              //praveen:initiator changes---end
            // System.out.println(p1.get);

        }
    }

    public Collection<QticketInfo> getQTickets() {
        return qtMap.values();
    }

}

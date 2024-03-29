//    Openbravo POS is a point of sales application designed for touch screens.
//    http://sourceforge.net/projects/openbravopos
//
//    Copyright (c) 2007 openTrends Solucions i Sistemes, S.L
//    Modified by Openbravo SL on March 22, 2007
//    These modifications are copyright Openbravo SL
//    Author/s: A. Romero
//    You may contact Openbravo SL at: http://www.openbravo.com
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package com.openbravo.possync;

import java.util.List;
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.DataParams;
import com.openbravo.data.loader.DataRead;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.ImageUtils;
import com.openbravo.data.loader.PreparedSentence;
import com.openbravo.data.loader.SerializerRead;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.SerializerWriteBasic;
import com.openbravo.data.loader.SerializerWriteParams;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.data.loader.Transaction;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.forms.BeanFactoryDataSingle;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.panels.ChequeDetail;
import com.openbravo.pos.payment.ChequeDetails;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;

/**
 *
 * @author adrianromero
 * Created on 5 de marzo de 2007, 19:56
 *
 */
public abstract class DataLogicIntegration extends BeanFactoryDataSingle {
    
    protected Session s;

    /** Creates a new instance of DataLogicIntegration */
    public DataLogicIntegration() {
    }
    
    public void init(Session s) {
        this.s = s;
    }
     
    public abstract void syncCustomersBefore() throws BasicException;
    
    public abstract void syncCustomer(final CustomerInfoExt customer) throws BasicException;
        
    
    public void syncProductsBefore() throws BasicException {
        new StaticSentence(s, "DELETE FROM PRODUCTS_CAT").exec();
    }   
    
    public void syncTaxCategory(final TaxCategoryInfo taxcat) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Tax in a transaction
                
                // Try to update                
                if (new PreparedSentence(s, 
                            "UPDATE TAXCATEGORIES SET NAME = ?  WHERE ID = ?",
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, taxcat.getName());
                                setString(2, taxcat.getID());                                    
                            }}) == 0) {
                       
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                            "INSERT INTO TAXCATEGORIES(ID, NAME) VALUES (?, ?)", 
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, taxcat.getID());
                                setString(2, taxcat.getName());
                            }});
                }
                
                return null;
            }
        };
        t.execute();                   
    }
    
    public void syncTax(final TaxInfo tax) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Tax in a transaction
                
                // Try to update                
                if (new PreparedSentence(s, 
                            "UPDATE TAXES SET NAME = ?, CATEGORY = ?, CUSTCATEGORY = ?, PARENTID = ?, RATE = ?, RATECASCADE = ? WHERE ID = ?",
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, tax.getName());
                                setString(2, tax.getTaxCategoryID());
                                setString(3, tax.getTaxCustCategoryID());
                                setString(4, tax.getParentID());
                                setDouble(5, tax.getRate());
                                setBoolean(6, tax.isCascade());
                                setString(7, tax.getId());       
                            }}) == 0) {
                       
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                            "INSERT INTO TAXES(ID, NAME, CATEGORY, CUSTCATEGORY, PARENTID, RATE, RATECASCADE) VALUES (?, ?, ?, ?, ?, ?, ?)", 
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, tax.getId());
                                setString(2, tax.getName());
                                setString(3, tax.getTaxCategoryID());
                                setString(4, tax.getTaxCustCategoryID());
                                setString(5, tax.getParentID());                                
                                setDouble(6, tax.getRate());
                                setBoolean(7, tax.isCascade());
                            }});
                }
                
                return null;
            }
        };
        t.execute();                   
    }
    
    public void syncCategory(final CategoryInfo cat) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Category in a transaction
                
                // Try to update
                if (new PreparedSentence(s, 
                            "UPDATE CATEGORIES SET NAME = ?, IMAGE = ? WHERE ID = ?", 
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                 setString(1, cat.getName());
                                 setBytes(2, ImageUtils.writeImage(cat.getImage()));
                                 setString(3, cat.getID());                                   
                            }}) == 0) {
                       
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                        "INSERT INTO CATEGORIES(ID, NAME, IMAGE) VALUES (?, ?, ?)",
                        SerializerWriteParams.INSTANCE
                        ).exec(new DataParams() { public void writeValues() throws BasicException {
                            setString(1, cat.getID());
                            setString(2, cat.getName());
                            setBytes(3, ImageUtils.writeImage(cat.getImage()));
                        }});
                }
                return null;        
            }
        };
        t.execute();        
    }    
    
    public void syncProduct(final ProductInfoExt prod) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Product in a transaction
                
                // Try to update
                if (new PreparedSentence(s, 
                            "UPDATE PRODUCTS SET REFERENCE = ?, CODE = ?, NAME = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAXCAT = ?, IMAGE = ?, PRCATEGORY =?,UNITTYPE=?,PACCOUNT=?,SACCOUNT=?  WHERE ID = ?",
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, prod.getReference());
                                setString(2, prod.getCode());
                                setString(3, prod.getName());
                                setDouble(4, prod.getPriceBuy());
                                setDouble(5, prod.getPriceSell());
                                setString(6, prod.getCategoryID());
                                setString(7, prod.getTaxCategoryID());
                                setBytes(8, ImageUtils.writeImage(prod.getImage()));
                                setString(9, prod.getPRCategory());
                               setString(10,prod.getUnitType());
                               setString(11, prod.getAccount());
                               setString(12, prod.getsAccount());
                                setString(13,prod.getID());

                            }}) == 0) {
                            
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                            "INSERT INTO PRODUCTS (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, IMAGE, HSN_Code, STOCKVOLUME, PRCATEGORY,UNITTYPE,PACCOUNT,SACCOUNT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)",
                            SerializerWriteParams.INSTANCE
                            ).exec(new DataParams() { public void writeValues() throws BasicException {
                                setString(1, prod.getID());
                                setString(2, prod.getReference());
                                setString(3, prod.getCode());
                                setString(4, prod.getName());
                                setBoolean(5, prod.isCom());
                                setBoolean(6, prod.isScale());
                                setDouble(7, prod.getPriceBuy());
                                setDouble(8, prod.getPriceSell());
                                setString(9, prod.getCategoryID());
                                setString(10, prod.getTaxCategoryID());
                                setBytes(11, ImageUtils.writeImage(prod.getImage()));
                                setString(12, null);
                                setDouble(13, 0.0);
                                setString(14, prod.getPRCategory());
                                setString(15,prod.getUnitType());
                                setString(16, prod.getAccount());
                                setString(17, prod.getsAccount());
                            }});
                }
                        
                // Insert in catalog
                new StaticSentence(s, 
                        "INSERT INTO PRODUCTS_CAT(PRODUCT, CATORDER) VALUES (?, NULL)",
                        SerializerWriteString.INSTANCE
                        ).exec(prod.getID());   
                
                return null;        
            }
        };
        t.execute();     
    }
    
    public List getTickets() throws BasicException {
        //R>MONEY IS REPLACED BY R.MONEY
        return new PreparedSentence(s
                , "SELECT T.ID, T.TICKETID, R.DATENEW, R.ID, P.ID, P.NAME, C.ID, C.TAXID, C.SEARCHKEY, C.NAME FROM RECEIPTS R JOIN TICKETS T ON R.ID = T.ID LEFT OUTER JOIN PEOPLE P ON T.PERSON = P.ID LEFT OUTER JOIN CUSTOMERS C ON T.CUSTOMER = C.ID WHERE T.STATUS = 0"
                , null
                , new SerializerReadClass(TicketInfo.class)).list();
    }
    public List getTicketLines(final String ticket) throws BasicException {
        return new PreparedSentence(s
                , "SELECT L.TICKET, L.LINE, L.PRODUCT, L.UNITS, L.PRICE, T.ID, T.NAME, T.CATEGORY, T.CUSTCATEGORY, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER, L.ATTRIBUTES " +
                  "FROM TICKETLINES L, TAXES T WHERE L.TAXID = T.ID AND L.TICKET = ?"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(TicketLineInfo.class)).list(ticket);
    }
    public List getTicketPayments(final String ticket) throws BasicException {
        return new PreparedSentence(s
                , "SELECT TOTAL, PAYMENT FROM PAYMENTS WHERE RECEIPT = ?"
                , SerializerWriteString.INSTANCE
                , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2));
                    }                
                }).list(ticket);
    }
    public List getTicketPaymentsNew(final String ticket) throws BasicException {
        return new PreparedSentence(s
                , "SELECT TOTAL,PAYMENT,CHEQUENO,BANK,AMOUNT FROM ( SELECT P.TOTAL AS TOTAL,P.PAYMENT AS PAYMENT,C.CHEQUENO AS CHEQUENO,C.BANK AS BANK,C.AMOUNT AS AMOUNT FROM PAYMENTS P,CHEQUE C WHERE P.RECEIPT = ? AND P.PAYMENT ='cheque' AND P.RECEIPT=C.RNO"+
                   " UNION   SELECT TOTAL AS TOTAL, PAYMENT AS PAYMENT,NULL  AS CHEQUENO,NULL AS BANK,0.0 AS AMOUNT FROM PAYMENTS WHERE  PAYMENT != 'cheque' AND RECEIPT = ?  )"
                , new SerializerWriteBasic(new Datas[]{Datas.STRING,Datas.STRING})
                , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        if(dr.getString(2)!=null && dr.getString(2).equals("cheque")){
                             ChequeDetails cd =new ChequeDetails();
                             cd.setAmount(dr.getDouble(5));
                             cd.setChequeno(dr.getString(3));
                             cd.setBank(dr.getString(4));
                         return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2),cd);
                        }else{
                            return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2));
                        }
                    }
                }).list(new Object[]{ticket,ticket});
    }
    

    public void execTicketUpdate() throws BasicException {
        new StaticSentence(s, "UPDATE TICKETS SET STATUS = 1 WHERE STATUS = 0").exec();
    }
}

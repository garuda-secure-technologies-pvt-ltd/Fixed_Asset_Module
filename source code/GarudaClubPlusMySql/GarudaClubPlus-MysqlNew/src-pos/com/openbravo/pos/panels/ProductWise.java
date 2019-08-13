/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

/**
 *
 * @author user
 */
public class ProductWise {
    String name;
    Double qty;
    Double rate;
    Double totalrate;
    String productId;
    String productCategory;
    int inv_maintain;

    public int getInv_maintain() {
        return inv_maintain;
    }

    public void setInv_maintain(int inv_maintain) {
        this.inv_maintain = inv_maintain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTotalrate() {
        return totalrate;
    }

    public void setTotalrate(Double totalrate) {
        this.totalrate = totalrate;
    }
            

}

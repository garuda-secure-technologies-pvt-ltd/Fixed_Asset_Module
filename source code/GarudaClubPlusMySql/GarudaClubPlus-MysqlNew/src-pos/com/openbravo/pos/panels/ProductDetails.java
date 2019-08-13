/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.panels;

/**
 *
 * @author user
 */
public class ProductDetails {
    String productId;
    String productSalesAccount;
    String productPurchaseAccount;
    Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPurchaseAccount() {
        return productPurchaseAccount;
    }

    public void setProductPurchaseAccount(String productPurchaseAccount) {
        this.productPurchaseAccount = productPurchaseAccount;
    }

    public String getProductSalesAccount() {
        return productSalesAccount;
    }

    public void setProductSalesAccount(String productSalesAccount) {
        this.productSalesAccount = productSalesAccount;
    }



}

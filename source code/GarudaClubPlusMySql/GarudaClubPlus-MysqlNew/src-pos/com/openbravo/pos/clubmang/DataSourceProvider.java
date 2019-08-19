/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.pos.clubmang;


import com.openbravo.pos.Accounts.AccountListDataSource;
import com.openbravo.pos.Accounts.AuditTrialDataSource;
import com.openbravo.pos.Accounts.DataSource2;
import com.openbravo.pos.Accounts.DataSource4;
//import com.openbravo.pos.Accounts.DataSourceForSaleReport2;
import com.openbravo.pos.Accounts.DataSourceForCurrentStock;
import com.openbravo.pos.Accounts.DataSourceForWarehouseReport;
import com.openbravo.pos.Accounts.DataSourceForjBRStatement;
import com.openbravo.pos.Accounts.DataSourceForjBrStatements;
//import com.openbravo.pos.Accounts.DataSourceProviderSaleReport1;
import com.openbravo.pos.Accounts.DatasourceTaxReports;
import com.openbravo.pos.Accounts.DueListDataSource;
import com.openbravo.pos.Accounts.OBSource;


import com.openbravo.pos.CardsRoom.DataSource3;
import com.openbravo.pos.inventory.DataSourceForConsolidateStock;
import com.openbravo.pos.inventory.DataSourceForDialyConsolidateReport;
import com.openbravo.pos.inventory.DataSourceForMultitaxWarehouseList;
import com.openbravo.pos.inventory.DataSourceForPurchaseOrder;
import com.openbravo.pos.inventory.DataSourcePurchaseIndent;
import com.openbravo.pos.panels.CloseSaleProdSource;
import com.openbravo.pos.panels.ClosedCashDataSource;
import com.openbravo.pos.panels.ClosedCashDataSource1;
import com.openbravo.pos.panels.ClosedSaleDataSource;
import com.openbravo.pos.panels.ClosedSaleDataSource1;
import com.openbravo.pos.panels.ClosedSaleDataSource1new;
import com.openbravo.pos.reports.DataSourceCreditBills;
import com.openbravo.pos.reports.DataSourceForGEMemberKiosk;
import com.openbravo.pos.reports.DataSourceForRecieptDetails;
import com.openbravo.pos.reports.DataSourceForStocktransfer;
import com.openbravo.pos.reports.DataSourceReceipt;
import com.openbravo.pos.reports.DataSourceReceiptGeneral;
import com.openbravo.pos.reports.DataSourceReceiptGuest;
import com.openbravo.pos.reports.DataSourceReceiptMember;
import com.openbravo.pos.reports.DataSourceWaiterTotal;
import com.openbravo.pos.sms.DataSourceForSMSDelReport;
import com.openbravo.pos.inventory.DataSourceForProdCatelog2;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import com.openbravo.pos.panels.DataSourceForMemberList;
import com.openbravo.pos.Accounts.DatasourceTrial;
import com.openbravo.pos.inventory.DataSourceForCurrentInventoryNew;
import com.openbravo.pos.reports.DataSourceForBillCancellation;
import com.openbravo.pos.reports.DataSourceForCustomerWiseSaleNew;
import com.openbravo.pos.reports.DataSourceForFacilityBilling;
import com.openbravo.pos.reports.DataSourceForQTCancellation;
import com.openbravo.pos.inventory.DataSourceForCurrentInventoryNew;
import com.openbravo.pos.inventory.DataSourceForPurchaseReportNew;
import com.openbravo.pos.inventory.DataSourceForPJ_TaxwiseReport;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.sales.DataSourceforConsumableVoucherList;
import com.openbravo.pos.sales.DataSourceForConsumeCancelledVoucher;
import com.openbravo.pos.sales.DatasourceForConsumeBillReprint;
import com.openbravo.pos.sales.DataSourceProviderCloseDay;
import com.openbravo.pos.Accounts.DataSourceForDaywiseSaleReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForAMCDetail;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeGenrator;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForListOfFADetail;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForNonAMCReport;


import com.openbravo.pos.panels.ConsolidateStockCloseSaleModel;
;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
//import com.openbravo.pos.reports.DataSourceProductTotal;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
//import com.openbravo.pos.reports.DataSourceProductTotal;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;
import com.openbravo.pos.reports.DataSourceForQtDetailReport;
import com.openbravo.pos.reports.DataSourceForCustomerProductReport;
import com.openbravo.pos.panels.DataSourceForConsolidateStockCloseSale;

import com.openbravo.pos.sales.DataSourceforConsumableProductList;
import com.openbravo.pos.reports.DataSourceForBotReports;
import com.openbravo.pos.reports.DataSourceForMemberReceivableReport;
import com.openbravo.pos.FixedAssetRegistration.DataSourceForBarcodeDataSheet;

public class DataSourceProvider implements JRDataSourceProvider {

    private List list;
    private List list1;
    private JRField[] fields;
    private JRDataSource datasource;

    public DataSourceProvider() {
    }

     public DataSourceProvider(List list) {
        this.list = list;
    }

    public DataSourceProvider(List list, List list1) { //praveen:added to set two list for detailed ledger list
        this.list = list;
        this.list1 = list1;
    }

    public void setDataSource(CloseSaleProdSource ds) {
            this.datasource=ds;
    }
   

    public void setDataSource(DataSourceCreditBills ds) {
        this.datasource = ds;
    }
    public void setDataSource(DataSourceForGEMemberKiosk ds) {
        this.datasource = ds;
    }



    public void setDataSource(DataSourceForConsolidateStock ds) {
        this.datasource = ds;
    }

    public void setDataSource(DataSourceForDialyConsolidateReport ds) {
        this.datasource = ds;
    }

    public void setDataSource(DataSourceForMemDebtList datasource) { //praveen:added for printing MemDebtBill
        this.datasource = datasource;
    }
    
    public void setDataSource(DataSourceForNoticeToMemberWiseReports datasource) { //praveen:added for printing MemDebtBill
        this.datasource = datasource;
    }
    public void setDataSource(DataSourceForCreditList datasource){ //praveen:added for printing credit list
        this.datasource = datasource;
    }
    
    public void setDataSource(DataSourceForjBRStatement datasource) { //Vandana:added for printing Bank Statement
        this.datasource = datasource;
    }
    public void setDataSource(DataSourceForjBrStatements datasource){ //Vandana:added for printing Bank Statement
        this.datasource = datasource;
    }

//    public void setDataSource(DataSourceProviderSaleReport1 datasource){ //Vandana:added for printing SaleReport
//        this.datasource = datasource;
//    }
//    public void setDataSource(DataSourceForSaleReport2 datasource){ //Vandana:added for printing SaleReport
//        this.datasource = datasource;
//    }
    
    
    
    public void setDataSource(DataSourceForStocktransfer ds) {
        this.datasource = ds;
    }
    public void setDataSource(DataSourceForNoticeToMembersList ds) {
        this.datasource = ds;
    }

    public void setDataSource(DataSourceReceipt ds)
    {
        this.datasource = ds;
    }

    public void setDataSource(DataSourceReceiptGeneral ds)
    {
        this.datasource = ds;
    }

    public void setDataSource(DataSourceReceiptGuest ds)
    {
       this.datasource = ds;
    }

    public void setDataSource(DataSourceReceiptMember ds)
    {
         this.datasource = ds;
    }

    public void setDataSource(DataSourceWaiterTotal ds) {
        this.datasource = ds;
    }
//     public void setDataSource(DataSourceProductTotal ds) {
//        this.datasource = ds;
//    }
    
//      public void setDataSource1(DataSourceProductTotal ds) {
//       this.datasource=(JRDataSource) ds;
//        }
    
    public void setDataSource(DataSourcedetail datasource){ //praveen:added for printing detail ledger  list
        this.datasource = datasource;
    }
    public void setDataSource(DataSourceForMemberStatement datasource){ //praveen:added for printing detail ledger  list
        this.datasource = datasource;
    }
     public void setDataSource(DataSourceForMemberStatement2 datasource){ //praveen:added for printing detail ledger  list
        this.datasource = datasource;
    }
    public void setDataSource (DataSourcePurchaseIndent datasource){
        this.datasource = datasource;

    }
    
    public void setDataSource(DataSourceForMemberStatement5 datasource){ //praveen:added for printing detail ledger  list
        this.datasource = datasource;
    }

    public void setDataSource (DataSourceForPurchaseOrder datasource){
        this.datasource = datasource;

    }

    public void setDataSource(AccountListDataSource ds) {
        this.datasource=ds;
    }

    public void setDataSource(AuditTrialDataSource ds) {
        this.datasource=ds;
    }
    public void setDataSource(DataSourceForPurchaseJournal datasource){
       this.datasource=datasource;
    }
    

    public void setDataSource(ClosedCashDataSource ds) {
        this.datasource=ds;
    }

    public void setDataSource(ClosedCashDataSource1 ds) {
        this.datasource=ds;
    }

    public void setDataSource(ClosedSaleDataSource ds) {
        this.datasource=ds;
    }
   public void setDataSource(ClosedSaleDataSource1 ds) {
        this.datasource=ds;
    }
    public void setDataSource(ClosedSaleDataSource1new ds) {
        this.datasource=ds;
    }
    public void setDataSource(DataSource1 datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSource3 datasource){
       this.datasource=datasource;
    }
     public void setDataSource(DataSource4 datasource){
       this.datasource=datasource;
    }

    public void setDataSource(Datasource2 ds) {
        this.datasource=ds;
    }

    public void setDataSource(DatasourceTaxReports ds)
    {
        this.datasource = ds;
    }
    public void setDataSource(OBSource datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSource datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSource2 datasource){
       this.datasource=datasource;
    }
    //added teju
    
   public void setDataSource(DataSourceForWarehouseReport datasource){
       this.datasource=datasource;
    }
    
    public void setDataSource(DueListDataSource datasource){
       this.datasource=datasource;
    }
     public void setDataSource(MemberDataSource datasource){
       this.datasource=datasource;
    }
     
     public void setDataSource(DataSourceForNoticeToMembers datasource){// Lokesh: Added for Notice To members
       this.datasource=datasource;
    }
     
     public void setDataSource(DataSourceForNoticeToMembersOtherThanFirst datasource){// Lokesh: Added for Notice To members
       this.datasource=datasource;
    }
     
      public void setDataSource(DataSourceForNoticeToMembersFinalNotice datasource){// Lokesh: Added for Notice To members
       this.datasource=datasource;
    }
     
    public void setDataSource(DataSourceforTrailBalance datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSourceforTrailBalancePeriod datasource){
       this.datasource=datasource;
    }
     public void setDataSource(DataSourceForAccountJournal datasource){
       this.datasource=datasource;
    }
      public void setDataSource(DataSourceForAccountContra datasource){
       this.datasource=datasource;
    }
    public JRDataSource create(JasperReport report) throws JRException {
        return datasource;
    }

     public void setDataSource(DataSourceForSMSDelReport ds) {
            this.datasource=ds;
    }
    
    
    public void dispose(JRDataSource dataSource) throws JRException {
            // nothing to dispose
    }
    public void setFields(JRField[] fields){
      this.fields=fields;
    }
	public JRField[] getFields(JasperReport arg0) throws JRException, UnsupportedOperationException {
		/* fields = new JRField[7];
		fields[0] = (JRField)new JRBasicField("SlNo", "slno", java.lang.String.class, "java.lang.String");
		fields[1] = (JRField)new JRBasicField("Mem No", "memno", java.lang.String.class, "java.lang.String");
        fields[2] = (JRField)new JRBasicField("Mem Name", "mname", java.lang.String.class, "java.lang.String");
        fields[3] = (JRField)new JRBasicField("Start Date", "sdate", java.util.Date.class, "java.util.Date");
        fields[4] = (JRField)new JRBasicField("Last Bill Date", "lbilldate", java.util.Date.class, "java.util.Date");
        fields[5] = (JRField)new JRBasicField("Num", "no", java.lang.Integer.class, "java.lang.Integer");
        fields[6] = (JRField)new JRBasicField("Amount", "amount", java.lang.Double.class, "java.lang.Double");*/
		return fields;
	}

	public boolean supportsGetFieldsOperation() {
		// TODO Auto-generated method stub
		return true;
	}

    public void setDataSource(DataSourceFacilityUsage ds)
    {
        this.datasource = ds;
    }

    void setDataSource(DataSourceMemberAddrss ds)
    {
        this.datasource = ds;
    }

    void setDataSource(DataSourceMemberSummary ds)
    {
        this.datasource = ds;
    }
    
    
    public void setDataSource(DataSourceForCurrentStock ds) {
            this.datasource=ds;
    }
     
      public void setDataSource(DataSourceForMemberStatement3 datasource){ //AAKSH:added for printing detail ledger  list
        this.datasource = datasource;
    }
       public void setDataSource(DataSourceForMemberStatement4 datasource){ //AAKASH:added for printing detail ledger  list
        this.datasource = datasource;
    } 
     
    public void setDataSource(DataSourceForRecieptDetails ds) {
            this.datasource=ds;
    }
     
  
   
    public void setDataSource(DataSourceForProdCatelog2 ds) {    
            this.datasource=ds;
    } 
    public void setDataSource(DataSourceForMemberList ds) {    
            this.datasource=ds;
   } 

   public void setDataSource(DataSourceForMultitaxWarehouseList  ds) {
            this.datasource=ds;
    }

  public void setDataSource(DatasourceTrial  ds) {
            this.datasource=ds;
    }

  public void setDataSource(DataSourceForCustomerWiseSaleNew  ds) {
            this.datasource=ds;
    }
  
   public void setDataSource(DataSourceForFacilityBilling  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForBillCancellation  ds) {
            this.datasource=ds;
    }
   public void setDataSource(DataSourceForQTCancellation  ds) {
            this.datasource=ds;
    }
   public void setDataSource(DataSourceForCurrentInventoryNew  ds) {
            this.datasource=ds;
    }
   //public void setDataSource(DataSourceForPurchaseReportNew  ds) {
    //        this.datasource=ds;
    //}
   public void setDataSource(DataSourceForPJ_TaxwiseReport  ds) {
            this.datasource=ds;
    }
   
   public void setDataSource(DataSourceForQtDetailReport  ds) {
            this.datasource=ds;
    }
   
   public void setDataSource(DataSourceforConsumableVoucherList  ds) {
            this.datasource=ds;
    }
   
   public void setDataSource(DataSourceForConsumeCancelledVoucher  ds) {
            this.datasource=ds;
    }
   
   public void setDataSource(DatasourceForConsumeBillReprint  ds) {
            this.datasource=ds;
    }
   public void setDataSource(DataSourceProviderCloseDay  ds) {
            this.datasource=ds;
    }
   public void setDataSource(DataSourceForDaywiseSaleReport  ds) {
            this.datasource=ds;
    }
   public void setDataSource(DataSourceForCustomerProductReport  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForConsolidateStockCloseSale  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForGuestEntryReport  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceforConsumableProductList  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForBotReports  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForMemberReceivableReport  ds) {
            this.datasource=ds;
    }
   
    public void setDataSource(DataSourceForBarcodeGenrator  ds) {
            this.datasource=ds;
    }
    public void setDataSource(DataSourceForAMCDetail  ds) {
            this.datasource=ds;
    }
    
    public void setDataSource(DataSourceForListOfFADetail  ds) {
            this.datasource=ds;
    } 
    public void setDataSource(DataSourceForNonAMCReport  ds) {
            this.datasource=ds;
    } 
    
 
    //pratima:added for printing account detail in excel format
     public void setDataSource(DataSourcedetailExcel datasource){ 
        this.datasource = datasource;
    }
    public void setDataSource(DataSource1Excel datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSourceforTrailBalanceExcel datasource){
       this.datasource=datasource;
    }
    public void setDataSource(DataSourceForBarcodeDataSheet  datasource){
       this.datasource=datasource;
    }
    //ended by pratima

//   
//
//    
   

    
   

   

   
}





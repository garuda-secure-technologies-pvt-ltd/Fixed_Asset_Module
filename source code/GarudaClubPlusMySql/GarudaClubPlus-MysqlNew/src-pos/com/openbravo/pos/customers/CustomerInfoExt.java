

package com.openbravo.pos.customers;

//import com.openbravo.format.Formats;
//import com.openbravo.pos.util.RoundUtils;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author adrianromero
 */
public class CustomerInfoExt extends CustomerInfo {
    
    protected String taxcustomerid;
    protected String notes;
    protected boolean visible;
    protected String card;
  
   
    
    protected String account;
    //
    protected String memtype;
   
    protected String sowo;
    protected String mobile;
    protected String stax;
    protected String etax;
   
   
    protected Timestamp joindate;
   
    protected Date dob;
    protected Double openingbalance;
   
    /** Creates a new instance of UserInfoBasic */
    public CustomerInfoExt(String id) {
        super(id);
    }
    public Double getopeningbalance(){
      return openingbalance;
    }
    public void setopeningbalance(Double bal){
      openingbalance=bal;
    }
    public Date getDOB(){
      return dob;
    }
    public void setDOB(Date d){
     dob=d;
    }
   
    public Timestamp getjDate(){
     return joindate;
    }
    public void setjDate(Timestamp date){
      joindate=date;
    }
   
    
    public String getmobile(){
     return mobile;
    }
    public void setMobile(String no){
     mobile=no;
    }
    public String getETax(){
      return etax;
    }
    public String getSTax(){
      return stax;
    }
    public void setETax(String taxid){
     etax=taxid;
    }
    public void setSTax(String taxid){
     stax=taxid;
    }

    public String getMemtype(){
     return memtype;
    }
    public void setMemType(String type){
     memtype=type;
    }
   
    public String getSoWo(){
     return sowo;
    }
    public void setSoWo(String name){
     sowo=name;
    }
    public String getTaxCustCategoryID() {
        return taxcustomerid;
    }
    
    public void setTaxCustomerID(String taxcustomerid) {
        this.taxcustomerid = taxcustomerid;
    }
    
    public String getNotes() {
        return notes;
    }
    public String getaccount(){
      return account;
    }
    public void setaccount(String acc){
       this.account=acc;
    }

  

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public void setid(String id)
    {
        this.id=id;
    }
 /*   public Double getMaxdebt() {
        return maxdebt;
    }
    
    public String printMaxDebt() {       
        return Formats.CURRENCY.formatValue(RoundUtils.getValue(getMaxdebt()));
    }
    
    public void setMaxdebt(Double maxdebt) {
        this.maxdebt = maxdebt;
    }

    public Date getCurdate() {
        return curdate;
    }

    public void setCurdate(Date curdate) {
        this.curdate = curdate;
    }

    public Double getCurdebt() {
        return curdebt;
    }
    
    public String printCurDebt() {       
        return Formats.CURRENCY.formatValue(RoundUtils.getValue(getCurdebt()));
    }
    
    public void setCurdebt(Double curdebt) {
        this.curdebt = curdebt;
    }*/

   
}

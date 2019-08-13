
package com.openbravo.pos.payment;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.SerializerReadBasic;
import com.openbravo.data.loader.SerializerReadString;
import com.openbravo.data.loader.SerializerWriteString;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.forms.AppLocal;
import javax.swing.*;
import com.openbravo.pos.forms.AppView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class PaymentPanelBasic extends javax.swing.JPanel implements PaymentPanel {

    private double m_dTotal;
    private String m_sTransactionID;
    private JPaymentNotifier m_notifier;
    private Double OtherCharges=0.00;
    private AppView App;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    String sTransactionID;
    Double TotalPayableAmount=0.00;
    String BankIDForCard=null;
    String AccountIDSelected=null;
    
    
    
    /** Creates new form PaymentPanelSimple */
    public PaymentPanelBasic(JPaymentNotifier notifier) {
        
        m_notifier = notifier;
        initComponents();
    }
    
    public JComponent getComponent(){
        return this;
    }
    
    public void activate(String sTransaction, double dTotal,AppView App) {
        
        m_sTransactionID = sTransaction;
        m_dTotal = dTotal;
        this.App = App;
        loadBankDetails();
        
        jLabel1.setText(
                m_dTotal > 0.0
                ? AppLocal.getIntString("message.paymentgatewayext")
                : AppLocal.getIntString("message.paymentgatewayextrefund"));
        
        m_notifier.setStatus(true, true);            
    }
    
    public PaymentInfoMagcard getPaymentInfoMagcard() {

       if(transaction_text.getText()!=null && transaction_text.getText().length()>0){
        String tarnsictionNoByUser =transaction_text.getText(); 
        
            if (m_dTotal > 0.0) {
                return new PaymentInfoMagcard(
                        BankNameSelected,
                        "", 
                        "",
                        AccountIDSelected,
                        BankIDForCard,
                        tarnsictionNoByUser,
                        m_sTransactionID,
                        m_dTotal,OtherCharges);

            } else {
                return new PaymentInfoMagcardRefund( 
                        "",
                        "", 
                        "",
                        AccountIDSelected,
                        BankIDForCard,
                        tarnsictionNoByUser,
                        m_sTransactionID,
                        m_dTotal,OtherCharges);
            }
        
    
    
       }
       else{
            JOptionPane.showMessageDialog(null, "Please enter transaction number.", "Cannot Create Receipt", JOptionPane.OK_OPTION);
       }
    
       return null;
    } 
        
   
        
   
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bank_combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        perc_label = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        transaction_text = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bankCharges_label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalPayableAmount_text = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fixedCharges_label = new javax.swing.JLabel();
        percAmount_label = new javax.swing.JLabel();

        setNextFocusableComponent(jPanel1);

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 100));

        bank_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        bank_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bank_comboItemStateChanged(evt);
            }
        });

        jLabel2.setText("Select Bank :");

        jLabel3.setText("Bank Perc. Chrg. (%)");

        perc_label.setText("jLabel4");

        jLabel4.setText("Enter Transaction No. ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Make the card payment and enter transactionID ");

        jLabel6.setText("Total Bank Charges : ");

        bankCharges_label.setText("jLabel7");

        jLabel7.setText("Total Payable Amount : ");

        totalPayableAmount_text.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        totalPayableAmount_text.setText("jLabel8");

        jLabel8.setText("Minimum  Charges :");

        fixedCharges_label.setText("jLabel9");

        percAmount_label.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bank_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(bankCharges_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalPayableAmount_text, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                            .addComponent(fixedCharges_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(percAmount_label, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(perc_label, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(transaction_text, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bank_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(perc_label)
                    .addComponent(percAmount_label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(fixedCharges_label))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(bankCharges_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(totalPayableAmount_text))
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(transaction_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        jPanel1.getAccessibleContext().setAccessibleParent(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    String BankNameSelected;
    
    private void bank_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bank_comboItemStateChanged
        if(bank_combo.getSelectedIndex()!=-1){
            BankNameSelected = bank_combo.getSelectedItem().toString();
            Double Perc = 0.00;
            try{
                 Object[] obj = (Object[]) new StaticSentence(App.getSession(), "SELECT perc , fixedcharges , id , accountid  FROM bank_details where active=1 and name=? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.DOUBLE , Datas.DOUBLE , Datas.STRING , Datas.STRING })).find(BankNameSelected);
                 if(obj!=null){
                    BankIDForCard = obj[2].toString();
                    AccountIDSelected = obj[3].toString();
                    Perc=Double.parseDouble(obj[0].toString());
                    perc_label.setText("( "+decimalFormat.format(Perc)+" % )");
                    Double PercAmount = ((m_dTotal*Perc)/100);
                    OtherCharges = ((m_dTotal*Perc)/100);
                    Double fixedCharges = 0.00;
                    if(obj[1]!=null){
                        fixedCharges = Double.parseDouble(obj[1].toString());
                        if(fixedCharges>OtherCharges){
                          OtherCharges=fixedCharges;
                        }
                        
                    }
                    fixedCharges_label.setText(decimalFormat.format(fixedCharges));
                    bankCharges_label.setText(decimalFormat.format(OtherCharges));
                    TotalPayableAmount = m_dTotal+OtherCharges;
                    totalPayableAmount_text.setText(decimalFormat.format(TotalPayableAmount));
                    percAmount_label.setText(decimalFormat.format(PercAmount));
                 }
                 else{
                    Perc=0.00;
                    perc_label.setText("( "+decimalFormat.format(Perc)+" % )");
                    OtherCharges = ((m_dTotal*Perc)/100);
                    Double fixedCharges = 0.00;
                    Double PercAmount = ((m_dTotal*Perc)/100);
                    OtherCharges=OtherCharges+fixedCharges;
                    fixedCharges_label.setText(decimalFormat.format(fixedCharges));
                    bankCharges_label.setText(decimalFormat.format(OtherCharges));
                    TotalPayableAmount = m_dTotal+OtherCharges;
                    totalPayableAmount_text.setText(decimalFormat.format(TotalPayableAmount));
                    percAmount_label.setText(decimalFormat.format(PercAmount));
                 }
                
                
            }
            catch(BasicException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_bank_comboItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bankCharges_label;
    private javax.swing.JComboBox<String> bank_combo;
    private javax.swing.JLabel fixedCharges_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel percAmount_label;
    private javax.swing.JLabel perc_label;
    private javax.swing.JLabel totalPayableAmount_text;
    private javax.swing.JTextField transaction_text;
    // End of variables declaration//GEN-END:variables
    
    public List<Object> BankNameList  = new ArrayList<Object>(); 
    private ComboBoxValModel BankModel; 
    private String DefaultBankName;
    public void loadBankDetails() {
        try{   
            BankNameList  = new ArrayList<Object>();
            BankNameList = getBankNameList( App);
            DefaultBankName = getDefaultBank(App);
            BankModel = new ComboBoxValModel(BankNameList);
            bank_combo.setModel(BankModel);
            if(DefaultBankName!=null){
                for(int i=0;i<BankNameList.size();i++){
                    String x = BankNameList.get(i).toString();
                    if(DefaultBankName.equals(x)){
                        bank_combo.setSelectedItem(x);
                        break;
                    }
                     
                } 
            }
            else{
               for(int i=0;i<BankNameList.size();i++){
                    String x = BankNameList.get(i).toString();
                    bank_combo.setSelectedItem(x);
                } 
            }
            
            
        }
        catch(BasicException e){
            e.printStackTrace();
        }
    }
    
    public List getBankNameList(AppView App ) throws BasicException{
          List<Object> Mem_list = new ArrayList<Object>();
           Mem_list  = (List<Object>) new StaticSentence(App.getSession(), "SELECT name from bank_details where active=1 order by name  ",  SerializerWriteString.INSTANCE , SerializerReadString.INSTANCE).list();
          
          return Mem_list;
      }
    
    public String getDefaultBank(AppView App ){
        String DefaultBankName = null;
        try{
            

            Object[] obj = (Object[]) new StaticSentence(App.getSession(), "SELECT name from bank_details where defaultflag=1 and active=1 order by name  ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find();
            if(obj!=null){
                if(obj[0]!=null){
                    DefaultBankName = obj[0].toString();
                }
            }
            
            }
        catch(BasicException e){
            e.printStackTrace();
        }
        return DefaultBankName;
    }
}

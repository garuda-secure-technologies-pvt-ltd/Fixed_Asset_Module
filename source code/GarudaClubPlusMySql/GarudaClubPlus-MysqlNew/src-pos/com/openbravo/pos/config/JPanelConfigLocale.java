

package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import java.awt.Component;
import java.util.Arrays;
import java.util.Locale;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;

/**
 *
 * @author  adrianromero
 */
public class JPanelConfigLocale extends javax.swing.JPanel implements PanelConfig {
    
    private DirtyManager dirty = new DirtyManager();
    
    private final static String DEFAULT_VALUE = "(Default)";
    
    /** Creates new form JPanelConfigLocale */
    public JPanelConfigLocale() {
        
        initComponents();
        
        jcboLocale.addActionListener(dirty);
        jcboInteger.addActionListener(dirty);
        jcboDouble.addActionListener(dirty);
        jcboCurrency.addActionListener(dirty);
        jcboPercent.addActionListener(dirty);
        jcboDate.addActionListener(dirty);
        jcboTime.addActionListener(dirty);
        jcboDatetime.addActionListener(dirty);
        
        Locale[] availablelocales = Locale.getAvailableLocales();
        Arrays.sort(availablelocales, new LocaleComparator());
        
        jcboLocale.addItem(new LocaleInfo (null));
        for (int i = 0 ; i < availablelocales.length; i++) {
            jcboLocale.addItem(new LocaleInfo(availablelocales[i]));
        }
        
        jcboInteger.addItem(DEFAULT_VALUE);
        jcboInteger.addItem("#0");
        jcboInteger.addItem("#,##0");
        
        jcboDouble.addItem(DEFAULT_VALUE);
        jcboDouble.addItem("#0.0");//##0.00
        jcboDouble.addItem("##0.00");
        jcboDouble.addItem("#,##0.#");
        
        jcboCurrency.addItem(DEFAULT_VALUE);
        jcboCurrency.addItem("\u00A4 #0.00");
        jcboCurrency.addItem("'$' #,##0.00");
        
        jcboPercent.addItem(DEFAULT_VALUE);
        jcboPercent.addItem("#,##0.##%");
        
        jcboDate.addItem(DEFAULT_VALUE);
//        jcboDate.addItem(DEFAULT_VALUE);
        
        jcboTime.addItem(DEFAULT_VALUE);
        
        jcboDatetime.addItem(DEFAULT_VALUE);
               
    }
    
    public boolean hasChanged() {
        return dirty.isDirty();
    }
    
    public Component getConfigComponent() {
        return this;
    }
   
    public void loadProperties(AppConfig config) {
        
        String slang = config.getProperty("user.language");
        String scountry = config.getProperty("user.country");
        String svariant = config.getProperty("user.variant");
        
        if (slang != null && !slang.equals("") && scountry != null && svariant != null) {                    
            Locale currentlocale = new Locale(slang, scountry, svariant);
            for (int i = 0 ; i < jcboLocale.getItemCount(); i++)  {
                LocaleInfo l = (LocaleInfo) jcboLocale.getItemAt(i);
                if (currentlocale.equals(l.getLocale())) {
                    jcboLocale.setSelectedIndex(i);
                    break;
                }
            }        
        } else {
            jcboLocale.setSelectedIndex(0);
        }
        
        jcboInteger.setSelectedItem(writeWithDefault(config.getProperty("format.integer")));
        jcboDouble.setSelectedItem(writeWithDefault(config.getProperty("format.double")));
        jcboCurrency.setSelectedItem(writeWithDefault(config.getProperty("format.currency")));
        jcboPercent.setSelectedItem(writeWithDefault(config.getProperty("format.percent")));
        jcboDate.setSelectedItem(writeWithDefault(config.getProperty("format.date")));
        jcboTime.setSelectedItem(writeWithDefault(config.getProperty("format.time")));
        jcboDatetime.setSelectedItem(writeWithDefault(config.getProperty("format.datetime")));
               
        dirty.setDirty(false);
    }
    
    public void saveProperties(AppConfig config) {
        
        Locale l = ((LocaleInfo) jcboLocale.getSelectedItem()).getLocale();
        if (l == null) {
            config.setProperty("user.language", "");
            config.setProperty("user.country", "");
            config.setProperty("user.variant", "");
        } else {
            config.setProperty("user.language", l.getLanguage());
            config.setProperty("user.country", l.getCountry());
            config.setProperty("user.variant", l.getVariant());
        }
         
        config.setProperty("format.integer", readWithDefault(jcboInteger.getSelectedItem()));
        config.setProperty("format.double", readWithDefault(jcboDouble.getSelectedItem()));
        config.setProperty("format.currency", readWithDefault(jcboCurrency.getSelectedItem()));
        config.setProperty("format.percent", readWithDefault(jcboPercent.getSelectedItem()));
        config.setProperty("format.date", readWithDefault(jcboDate.getSelectedItem()));
        config.setProperty("format.time", readWithDefault(jcboTime.getSelectedItem()));
        config.setProperty("format.datetime", readWithDefault(jcboDatetime.getSelectedItem()));
        
        dirty.setDirty(false);
    }
    
    private String readWithDefault(Object value) {
        if (DEFAULT_VALUE.equals(value)) {
            return "";
        } else {
            return value.toString();
        }
    }
    
    private Object writeWithDefault(String value) {
        if (value == null || value.equals("") || value.equals(DEFAULT_VALUE)) {
            return DEFAULT_VALUE;
        } else {
            return value.toString();
        }
    }
    
    private static class LocaleInfo {
        private Locale locale;
        
        public LocaleInfo(Locale locale) {
            this.locale = locale;
        }
        public Locale getLocale() {
            return locale;
        }
        @Override
        public String toString() {
            return locale == null 
                    ? "(System default)" 
                    : locale.getDisplayName();
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcboLocale = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jcboInteger = new javax.swing.JComboBox();
        jcboDouble = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcboCurrency = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcboPercent = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcboDate = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jcboTime = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jcboDatetime = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(680, 290));
        setLayout(null);

        jLabel9.setText(AppLocal.getIntString("label.locale")); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        add(jLabel9);
        jLabel9.setBounds(20, 10, 660, 16);

        jLabel5.setText(AppLocal.getIntString("label.locale")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 40, 130, 15);
        add(jcboLocale);
        jcboLocale.setBounds(150, 40, 170, 20);

        jLabel1.setText(AppLocal.getIntString("label.integer")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 70, 130, 15);

        jcboInteger.setEditable(true);
        add(jcboInteger);
        jcboInteger.setBounds(150, 70, 170, 20);

        jcboDouble.setEditable(true);
        add(jcboDouble);
        jcboDouble.setBounds(150, 100, 170, 20);

        jLabel2.setText(AppLocal.getIntString("label.double")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 100, 130, 15);

        jcboCurrency.setEditable(true);
        add(jcboCurrency);
        jcboCurrency.setBounds(150, 130, 170, 20);

        jLabel3.setText(AppLocal.getIntString("label.currency")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 130, 130, 15);

        jcboPercent.setEditable(true);
        add(jcboPercent);
        jcboPercent.setBounds(150, 160, 170, 20);

        jLabel4.setText(AppLocal.getIntString("label.percent")); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(20, 160, 130, 15);

        jLabel6.setText(AppLocal.getIntString("label.date")); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(20, 190, 130, 15);

        jcboDate.setEditable(true);
        add(jcboDate);
        jcboDate.setBounds(150, 190, 170, 20);

        jLabel7.setText(AppLocal.getIntString("label.time")); // NOI18N
        add(jLabel7);
        jLabel7.setBounds(20, 220, 130, 15);

        jcboTime.setEditable(true);
        add(jcboTime);
        jcboTime.setBounds(150, 220, 170, 20);

        jLabel8.setText(AppLocal.getIntString("label.datetime")); // NOI18N
        add(jLabel8);
        jLabel8.setBounds(20, 250, 130, 15);

        jcboDatetime.setEditable(true);
        add(jcboDatetime);
        jcboDatetime.setBounds(150, 250, 170, 20);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox jcboCurrency;
    private javax.swing.JComboBox jcboDate;
    private javax.swing.JComboBox jcboDatetime;
    private javax.swing.JComboBox jcboDouble;
    private javax.swing.JComboBox jcboInteger;
    private javax.swing.JComboBox jcboLocale;
    private javax.swing.JComboBox jcboPercent;
    private javax.swing.JComboBox jcboTime;
    // End of variables declaration//GEN-END:variables
    
}

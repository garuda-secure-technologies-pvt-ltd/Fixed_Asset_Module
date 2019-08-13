

package com.openbravo.pos.forms;

import RMI.ComputePi;
import java.util.Locale;
import javax.swing.UIManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.admin.CardReader;
import com.openbravo.pos.instance.InstanceQuery;
//import java.io.File;
//import java.net.BindException;
//import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import org.hsqldb.Server;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceSkin;

/**
 *
 * @author adrianromero
 */
public class StartPOS {
    
    /** Creates a new instance of StartPOS */
    private StartPOS() {
    }
    
    public static Server server;
    public static boolean registerApp() {
                       
        // vemos si existe alguna instancia        
        InstanceQuery i = null;
        try {
            i = new InstanceQuery();
            i.getAppMessage().restoreWindow();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }  
    }

        //praveen:cardreader function
     private static void startCardReader(AppConfig config) {
        try {
            String portNumber = config.getProperty("card.portnumber");
            boolean cardAccessOnlyFlag=false;
            if(config.getProperty("cardAccessOnly")!=null)
                cardAccessOnlyFlag=Boolean.valueOf(config.getProperty("cardAccessOnly"));
            CardReader cr = new CardReader(portNumber,cardAccessOnlyFlag);
            System.out.println(portNumber);
            cr.ConfigurePort();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public static void main (final String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                AppConfig config = new AppConfig(args);
                config.load();
                
                // set Locale.
                String slang = config.getProperty("user.language");
                String scountry = config.getProperty("user.country");
                String svariant = config.getProperty("user.variant");
                if (slang != null && !slang.equals("") && scountry != null && svariant != null) {                                        
                    Locale.setDefault(new Locale(slang, scountry, svariant));
                }
                
                // Set the format patterns
                Formats.setIntegerPattern(config.getProperty("format.integer"));
                Formats.setDoublePattern(config.getProperty("format.double"));
                Formats.setCurrencyPattern(config.getProperty("format.currency"));
                Formats.setPercentPattern(config.getProperty("format.percent"));
                Formats.setDatePattern(config.getProperty("format.date"));
                Formats.setTimePattern(config.getProperty("format.time"));
                Formats.setDateTimePattern(config.getProperty("format.datetime"));               

                
                // Set the look and feel.
                try {             
                    
                    Object laf = Class.forName(config.getProperty("swing.defaultlaf")).newInstance();
                    
                    if (laf instanceof LookAndFeel){
                        UIManager.setLookAndFeel((LookAndFeel) laf);
                    } else if (laf instanceof SubstanceSkin) {                      
                        SubstanceLookAndFeel.setSkin((SubstanceSkin) laf);                   
                    }
                } catch (Exception e) {
                }
               
                String screenmode = config.getProperty("machine.screenmode");
                startCardReader( config);
                if ("fullscreen".equals(screenmode)) {
                    JRootKiosk rootkiosk = new JRootKiosk();
                    rootkiosk.initFrame(config);
                } else {
                    JRootFrame rootframe = new JRootFrame(); 
                    rootframe.initFrame(config);
                 //   rootframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }

            }
        });    
    }    
}

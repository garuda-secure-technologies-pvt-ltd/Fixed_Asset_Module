/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import com.openbravo.data.gui.MessageInf;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;

/**
 *
 * @author swathi
 */
public  class ComputePi {
    private static Compute comp;
    private static Registry registry;
    public static void Configure(String host) {
        System.setProperty("java.security.policy", "C:\\java.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "SMS";
           // registry = LocateRegistry.getRegistry(host);
            //comp = (Compute) registry.lookup(name);
             comp = (Compute) Naming.lookup("rmi://"+host+":1099/SMS");

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            new MessageInf(e).show(new JFrame());
        }
    }
    public static void setvalue(boolean val) throws RemoteException{
        comp.Alert(val);
        //registry.rebind("SMS", comp);
    }
}


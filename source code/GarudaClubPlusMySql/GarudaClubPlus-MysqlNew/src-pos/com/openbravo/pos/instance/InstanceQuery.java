

package com.openbravo.pos.instance;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author adrianromero
 */
public class InstanceQuery {
    
    private AppMessage m_appstub;
    
    /** Creates a new instance of InstanceQuery */
    public InstanceQuery() throws RemoteException, NotBoundException {
        
        Registry registry = LocateRegistry.getRegistry();           
        m_appstub = (AppMessage) registry.lookup("AppMessage");
    }
    
    public AppMessage getAppMessage() {
        return m_appstub;
    }
}

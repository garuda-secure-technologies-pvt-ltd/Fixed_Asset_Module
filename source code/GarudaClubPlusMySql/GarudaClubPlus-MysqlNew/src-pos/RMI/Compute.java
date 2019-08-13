/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author swathi
 */
public interface Compute extends Remote {
    public void Alert(boolean flag) throws RemoteException;
}

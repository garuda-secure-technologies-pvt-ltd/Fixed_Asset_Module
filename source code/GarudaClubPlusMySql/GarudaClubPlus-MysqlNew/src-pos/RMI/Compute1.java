/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import java.rmi.RemoteException;

/**
 *
 * @author swathi
 */
public class Compute1 implements Compute {
    public static boolean  flag;
    public Compute1() throws RemoteException{
    }
    public void Alert(boolean flag1) throws RemoteException {
        flag=flag1;
    }
}

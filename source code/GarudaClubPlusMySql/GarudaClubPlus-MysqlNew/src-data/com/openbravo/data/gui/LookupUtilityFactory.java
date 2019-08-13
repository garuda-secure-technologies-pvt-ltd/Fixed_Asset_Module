/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openbravo.data.gui;

/**
 *
 * @author swathi
 */
public class LookupUtilityFactory {
    private static ILookupUtility singleton;

    public static ILookupUtility getInstance() {
        return singleton;
    }

    public static void init(ILookupUtility lu) {
        singleton = lu;
    }
}

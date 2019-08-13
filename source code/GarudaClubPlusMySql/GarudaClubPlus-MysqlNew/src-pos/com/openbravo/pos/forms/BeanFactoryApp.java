
package com.openbravo.pos.forms;

/**
 *
 * @author adrianromero
 */
public interface BeanFactoryApp extends BeanFactory {

    public void init(AppView app) throws BeanFactoryException;

}

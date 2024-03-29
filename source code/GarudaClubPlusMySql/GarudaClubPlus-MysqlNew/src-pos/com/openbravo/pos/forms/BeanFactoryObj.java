

package com.openbravo.pos.forms;

/**
 *
 * @author adrianromero
 */
public class BeanFactoryObj implements BeanFactory {
    
    private Object bean = null;
    
    /** Creates a new instance of BeanFactoryObj */
    public BeanFactoryObj(Object bean) {
        this.bean = bean;
    }
    
    public Object getBean() {
        return bean;
    }
    
}

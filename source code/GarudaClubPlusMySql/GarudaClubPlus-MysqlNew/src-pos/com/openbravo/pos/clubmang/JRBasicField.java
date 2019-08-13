/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */////

package com.openbravo.pos.clubmang;

/**
 *
 * @author swathi
 */
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;




public class JRBasicField implements JRField {
	private String description;
	private String name;
	private Class valueClass;
	private String valueClassName;


	public JRBasicField(String description, String name, Class valueClass, String valueClassName) {
		super();
		this.description = description;
		this.name = name;
		this.valueClass = valueClass;
		this.valueClassName = valueClassName;
	}


	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Class getValueClass() {
		return valueClass;
	}

	public String getValueClassName() {
		return valueClassName;
	}

	public void setDescription(String desc) {
		description = desc;
	}

    public boolean hasProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JRPropertiesMap getPropertiesMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JRPropertiesHolder getParentProperties() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public Object clone(){
        return null;
    }

}




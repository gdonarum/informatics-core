package com.limray.informatics.core;

public abstract class InformaticsAttribute implements Comparable<Object> {
	private String name;
	private boolean required;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the valueIndex
	 */
	public abstract String getValue();

	/**
	 * @param valueIndex the valueIndex to set
	 */
	public abstract void setValue(String value);
	
	/**
	 * Check if the attribute value is valid.
	 */
	public boolean isValid() {
		// see if required field is set
		if(isRequired() && (getValue()==null || getValue().isEmpty()))
			return false;
		return true;
		// TODO: extend this method in child classes.  Make sure to call parent.isValid()
	}
}

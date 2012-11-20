package com.limray.informatics.core;

import java.util.ArrayList;

public class SelectionAttribute extends InformaticsAttribute {
	private ArrayList<String> enumerations = new ArrayList<String>();
	private int valueIndex = -1;

	/**
	 * @return the enumerations
	 */
	public ArrayList<String> getEnumerations() {
		return enumerations;
	}
	
	public void addEnumeration(String value, boolean isDefault) {
		if(isDefault) {
			valueIndex=enumerations.size();
		}
		enumerations.add(value);
	}

	/**
	 * @return the valueIndex
	 */
	public String getValue() {
		if(valueIndex>=0 && valueIndex<enumerations.size())
			return enumerations.get(valueIndex);
		return "";
	}

	/**
	 * @param valueIndex the valueIndex to set
	 */
	public void setValue(String value) {
		this.valueIndex = enumerations.indexOf(value);
	}

	public int compareTo(Object arg0) {
		try {
			return this.getValue().compareTo(((SelectionAttribute)arg0).getValue());
		} catch (Exception e) {}
		return 0;
	}

}

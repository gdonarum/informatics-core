package com.limray.informatics.core;

public class TextAttribute extends InformaticsAttribute {
	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public int compareTo(Object arg0) {
		try {
			return this.getValue().compareTo(((TextAttribute)arg0).getValue());
		} catch (Exception e) {}
		return 0;
	}

}

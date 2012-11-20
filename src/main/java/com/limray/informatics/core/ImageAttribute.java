package com.limray.informatics.core;

/**
 * Images will be uploaded and stored in the Image/Document Library.
 * Will need to handle multiple images.
 * @author greg
 *
 */
public class ImageAttribute extends InformaticsAttribute {
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
			return this.getValue().compareTo(((ImageAttribute)arg0).getValue());
		} catch (Exception e) {}
		return 0;
	}

}

/**
 * This file is part of LIMRAY Informatics, a suite of tools for scientists.
 * Copyright (C) 2012  Gregory Donarum
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

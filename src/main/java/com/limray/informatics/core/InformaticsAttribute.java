/**
 * This file is part of Limray Informatics, a suite of tools for scientists.
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

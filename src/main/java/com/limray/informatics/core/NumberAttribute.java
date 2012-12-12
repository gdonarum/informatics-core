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

import java.util.ArrayList;

public class NumberAttribute extends InformaticsAttribute {
	private int intValue = Integer.MIN_VALUE;
	private double doubleValue = Double.NaN;
	private boolean decimal = false;
	private ArrayList<String> units = new ArrayList<String>();
	private int valueIndex = 0;

	@Override
	public String getValue() {
		String out = "";
		if(decimal && doubleValue!=Double.NaN) {
			out += doubleValue;	// TODO: we can add decimal places formatting here
		} else if(!decimal && intValue!=Integer.MIN_VALUE) {
			out += intValue;
		}
		if(units.size()>0)
			out += " " + units.get(valueIndex);
		return out;
	}

	@Override
	public void setValue(String value) {
		// split the value and units
		String[] split = value.split(" ");
		if(split.length>0) {
			if(decimal)
				doubleValue = Double.parseDouble(split[0]);
			else
				intValue = Integer.parseInt(split[0]);
		}
		if(split.length>1) {
			this.valueIndex = units.indexOf(split[1]);
		}
	}

	/**
	 * @return the decimal
	 */
	public boolean isDecimal() {
		return decimal;
	}

	/**
	 * @param decimal the decimal to set
	 */
	public void setDecimal(boolean decimal) {
		this.decimal = decimal;
	}

	/**
	 * @return the units
	 */
	public ArrayList<String> getUnits() {
		return units;
	}
	
	public void addUnit(String value, boolean isDefault) {
		if(isDefault) {
			valueIndex=units.size();
		}
		units.add(value);
	}
	
	protected Double getComparableDoubleValue() {
		if(isDecimal() && doubleValue!=Double.NaN)
			return new Double(doubleValue);
		if(!isDecimal() && intValue!=Integer.MIN_VALUE)
			return new Double(intValue);
		return new Double(0);
	}

	public int compareTo(Object arg0) {
		// ignore units 
		// TODO: is there a way to handle units
		try {
			return this.getComparableDoubleValue().compareTo(((NumberAttribute)arg0).getComparableDoubleValue());
		} catch (Exception e) {}
		return 0;
	}

}

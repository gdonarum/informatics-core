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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateAttribute extends InformaticsAttribute {
	private Date date;
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	@Override
	public String getValue() {
		if(date!=null)
			return df.format(date);
		return null;
	}

	@Override
	public void setValue(String value) {
		try {
			this.date = df.parse(value);
		} catch (ParseException e) {
			// TODO: do we have to do anything here?
			e.printStackTrace();
		}		
	}

	public int compareTo(Object arg0) {
		try {
			return this.getValue().compareTo(((DateAttribute)arg0).getValue());
		} catch (Exception e) {}
		return 0;
	}

}

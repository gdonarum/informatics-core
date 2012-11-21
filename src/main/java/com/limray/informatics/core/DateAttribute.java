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

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
import java.util.Properties;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The properties use the attribute names concatenated in accordance with their XML XPath.
 * ex:
 * machine.name=testname
 * 
 * For now we will just use a one dimensional list.  In the future we will need groups
 * to handle a hierarchical data structure.
 * 
 * @author greg
 *
 */
public class InformaticsEntity {
	private String name = "UNSET";
	// TODO: make this a hierarchy instead of an array
	private ArrayList<InformaticsAttribute> attributes = new ArrayList<InformaticsAttribute>();
	
	public InformaticsEntity() {
		
	}
	
	public void setDefinition(Element xml) {
		// TODO: need validation checks
		this.setName(xml.getAttribute("name"));
		NodeList list = xml.getChildNodes();
		for(int i=0; i<list.getLength(); i++) {
			try {
				this.attributes.add(AttributeHelper.getAttribute((Element)list.item(i)));
			} catch (Exception e) {
				// only do elements
			}
		}
	}
	
	/**
	 * Values as properties are used for persistence.
	 * Use this method to set values from your persistence object.
	 * @param props
	 */
	public void setValues(Properties props) {
		for(InformaticsAttribute a : attributes) {
			String val = props.getProperty(getPropertyName(a));
			if(val!=null)
				a.setValue(val);
		}
	}
	
	/**
	 * Values as properties are used for persistence.
	 * Use this method to get the values you need to persist.
	 * @return
	 */
	public Properties getValues() {
		Properties props = new Properties();
		for(InformaticsAttribute a : attributes) {
			if(a.getValue()!=null)
				props.setProperty(getPropertyName(a), a.getValue());
		}
		return props;
	}
	
	private String getPropertyName(InformaticsAttribute a) {
		return this.name + "." + a.getName();
	}
	
	public ArrayList<InformaticsAttribute> getAttributes() {
		return this.attributes;
	}
	
	public String toString() {
		return getValues().toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public InformaticsAttribute getAttribute(String name) throws InformaticsCoreException {
		for(InformaticsAttribute att : getAttributes()) {
			if(att.getName().equals(name))
				return att;
		}
		throw new InformaticsCoreException("Invalid Attribute Name");
	}

}

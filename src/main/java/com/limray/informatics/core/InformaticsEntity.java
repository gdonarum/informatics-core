/**
 * 
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
	private long primaryKey = 0;
	
	public InformaticsEntity() {
		
	}
	
	public long getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(long primaryKey) {
		this.primaryKey=primaryKey;
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

}

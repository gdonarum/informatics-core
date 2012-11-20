package com.limray.informatics.core;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AttributeHelper {
	public static InformaticsAttribute getAttribute(Element element) {
		InformaticsAttribute out = new TextAttribute(); // TODO: do I need to use a useless default here?
		String nodeName = element.getNodeName();
		if(nodeName.equals("text"))
			out = getTextAttribute(element);
		else if(nodeName.equals("selection"))
			out = getSelectionAttribute(element);
		else if(nodeName.equals("number"))
			out = getNumberAttribute(element);
		else if(nodeName.equals("date"))
			out = getDateAttribute(element);
		out.setName(element.getAttribute("name"));
		out.setRequired(element.getAttribute("required").equals("true"));
		return out;
	}
	
	private static TextAttribute getTextAttribute(Element element) {
		TextAttribute out = new TextAttribute();
		return out;
	}
	
	private static SelectionAttribute getSelectionAttribute(Element element) {
		SelectionAttribute out = new SelectionAttribute();
		NodeList list = element.getElementsByTagName("enumeration");
		for(int i=0; i<list.getLength(); i++) {
			Element enu = (Element)list.item(i);
			boolean def = enu.getAttribute("default").equals("true");
			out.addEnumeration(enu.getAttribute("value"), def);
		}
		return out;
	}
	
	private static NumberAttribute getNumberAttribute(Element element) {
		NumberAttribute out = new NumberAttribute();
		out.setDecimal(element.getAttribute("decimal").equals("true"));
		NodeList list = element.getElementsByTagName("unit");
		for(int i=0; i<list.getLength(); i++) {
			Element enu = (Element)list.item(i);
			boolean def = enu.getAttribute("default").equals("true");
			out.addUnit(enu.getAttribute("value"), def);
		}
		return out;
	}
	
	private static DateAttribute getDateAttribute(Element element) {
		DateAttribute out = new DateAttribute();
		return out;
	}
}

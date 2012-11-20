/**
 * 
 */
package com.limray.informatics.core;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author greg
 *
 */
public class Tester {
	
	public static InformaticsEntity getNewEntity() {
		InformaticsEntity entity = new InformaticsEntity();
		try {
			File xmlFile = new File("E:/MyProjects/Liferay/liferay-sdk-6.1.0/portlets/Informatics-portlet/docroot/BioSample.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			//read the element into the helper
			entity.setDefinition(doc.getDocumentElement());
		} catch (Exception e) {
			
		}
			
		return entity;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// open xml file
			//File xmlFile = new File("E:/MyProjects/Liferay/liferay-sdk-6.1.0/portlets/Informatics-portlet/docroot/Machine.xml");
			File xmlFile = new File("E:/MyProjects/Liferay/liferay-sdk-6.1.0/portlets/Informatics-portlet/docroot/BioSample.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());
			
			// set some values
			Properties props = new Properties();
			props.setProperty("machine.name", "laptop");
			props.setProperty("bio-sample.name", "Blood Sample");
			
			entity.setValues(props);
			
			System.out.println(entity);
			
			InformaticsEntity entity2 = new InformaticsEntity();
			entity2.setDefinition(doc.getDocumentElement());
			entity2.setValues(entity.getValues());
			System.out.println(entity2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

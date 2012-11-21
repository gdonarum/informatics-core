/**
 *
 */
package com.limray.informatics.core;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author greg
 *
 */
public class EntityTest {

    private Document doc;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
		try {
			File bioDef = new File(ClassLoader.getSystemResource("BioSample.xml").toURI());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(bioDef);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        doc = null;
    }

	@Test
	public void testDefinition() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());
			assertEquals(entity.getAttributes().size(),6);
	}

	@Test
	public void testTextAttributes() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());

			// set some values
			Properties props = new Properties();
			props.setProperty("junk.name", "laptop");
			props.setProperty("bio-sample.name", "Blood Sample");

			entity.setValues(props);
			assertEquals(entity.getAttributes().get(0).getValue(), "Blood Sample");
	}

	@Test
	public void testSelectionAttributes() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());

			// set some values
			Properties props = new Properties();
			props.setProperty("junk.name", "laptop");
			props.setProperty("bio-sample.matrix", "Tissue");

			entity.setValues(props);
			assertEquals(entity.getAttributes().get(1).getValue(), "Tissue");
	}

	@Test
	public void testSelectionDefault() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());

			try {
				assertEquals(entity.getAttribute("matrix").getValue(), "unknown");
			} catch (InformaticsCoreException e) {
				fail("Invalid attribute name");
			}
	}

	@Test
	public void testNumberAttributes() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());
			try {
				entity.getAttribute("volume").setValue("5.123456789123456 mL");
			} catch (InformaticsCoreException e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
			assertEquals(entity.getAttributes().get(2).getValue(), "5.123456789123456 mL");
	}

	@Test
	public void testNumberDefaultUnits() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());
			try {
				entity.getAttribute("volume").setValue("5");
			} catch (InformaticsCoreException e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
			assertEquals(entity.getAttributes().get(2).getValue(), "5.0 L");
	}

	@Test
	public void testDate() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());
			try {
				entity.getAttribute("received").setValue("2012-11-20 21:02:35");
			} catch (InformaticsCoreException e) {
				// TODO Auto-generated catch block
				fail(e.getMessage());
			}
			assertEquals(entity.getAttributes().get(4).getValue(), "2012-11-20 21:02:35");
	}

	@Test
	public void testLoadProperties() {

			//read the element into the helper
			InformaticsEntity entity = new InformaticsEntity();
			entity.setDefinition(doc.getDocumentElement());

			// set some values
			Properties props = new Properties();
			props.setProperty("junk.name", "laptop");
			props.setProperty("bio-sample.name", "Blood Sample");
			props.setProperty("bio-sample.source", "DoD");

			entity.setValues(props);

			InformaticsEntity entity2 = new InformaticsEntity();
			entity2.setDefinition(doc.getDocumentElement());
			entity2.setValues(entity.getValues());
			assertEquals(entity2.getAttributes().size(),6);
			assertEquals(entity2.getAttributes().get(0).getValue(), "Blood Sample");
			assertEquals(entity2.getAttributes().get(1).getValue(), "unknown");
			assertEquals(entity2.getAttributes().get(3).getValue(), "DoD");

			// check all attributes
			for(int i=0; i<entity.getAttributes().size(); i++) {
				assertEquals(entity.getAttributes().get(i).getName(),entity.getAttributes().get(i).getName());
				assertEquals(entity.getAttributes().get(i).getValue(),entity.getAttributes().get(i).getValue());
			}
	}

}

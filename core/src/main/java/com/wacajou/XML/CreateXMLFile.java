package com.wacajou.XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CreateXMLFile {
	private Document doc;
	private Element rootElement;
	private String path;
	private String filename;
	
	public void createFile(String path, String filename) throws ParserConfigurationException {
		this.path = path;
		this.filename = filename;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();

	}
	public void setElement(String rootElementName){
		rootElement = doc.createElement(rootElementName);
		doc.appendChild(rootElement);
	}

	public void setAttribute(String attributeName, String attributeValue){
		Element attribute = doc.createElement(attributeName);
		attribute.appendChild(doc.createTextNode(attributeValue));
		rootElement.appendChild(attribute);
	}
	public void saveFile() throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path + "\\" + filename));
		transformer.transform(source, result);
		// Output to console for testing
		
		StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);
	}
}

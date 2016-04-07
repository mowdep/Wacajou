package com.wacajou.XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifXMLFile {
	private Document doc;
	private String path;
	private String filename;

	public void getFile(String path, String filename) throws ParserConfigurationException, SAXException, IOException {
		this.path = path;
		this.filename = filename;
		File inputFile = new File(path + "\\" + filename);
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(inputFile);
	}

	public void addAttribute(String attributeName, String attributeValue){
		Node rootElement = doc.getFirstChild();
		Element attribute = doc.createElement(attributeName);
		attribute.appendChild(doc.createTextNode(attributeValue));
		rootElement.appendChild(attribute);
	}
	public void modifAttribut(String attributeName, String attributeValue) {
		Node attribute = doc.getFirstChild();
		System.out.println(attribute.getNodeName());
		NodeList list = attribute.getChildNodes();
		for (int temp = 0; temp < list.getLength(); temp++) {
			Node node = list.item(temp);
			System.out.println(node.getNodeName());
			if (node.getNodeName().equals(attributeName)) {
				System.out.println("Ancienne value : " + node.getTextContent());
				node.setTextContent(attributeValue);
				System.out.println("Nouvelle value : " + node.getTextContent());
			}
		}
	}

	public void save() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		System.out.println("-----------Modified File-----------");
		StreamResult consoleResult = new StreamResult(new File(path + "\\" + filename));
		transformer.transform(source, consoleResult);
	}
}

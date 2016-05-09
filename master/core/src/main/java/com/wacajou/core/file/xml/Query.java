package com.wacajou.core.file.xml;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Query {

	private Document doc;

	public void getFile(String path, String filename) throws ParserConfigurationException, SAXException, IOException {
		File inputFile = new File(path + "\\" + filename + ".xml");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.parse(inputFile);
	}
	public Map<String, String> getAllElement(){
		Map<String, String> map = new HashMap<String, String>();
		NodeList node = doc.getChildNodes();
		for(int i =  0; i < node.getLength(); i++) {
			NodeList nList = node.item(i).getChildNodes();
			for(int j = 0; j < nList.getLength(); j++){
				String itemName = nList.item(j).getNodeName();
				String itemValue = nList.item(j).getNodeValue();
				map.put(itemName, itemValue);
			}
		}		
		return map;
	}
}
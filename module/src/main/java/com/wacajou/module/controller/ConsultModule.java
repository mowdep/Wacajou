package com.wacajou.module.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.wacajou.XML.QueryXMLFile;
import com.wacajou.entity.Module;
import com.wacajou.entity.Parcours;
import com.wacajou.module.model.DAOModule;
import com.wacajou.module.model.DAOParcoursModule;

public class ConsultModule {
	private int id;
	public Module module;
	private String PATH;
	public String description;
	public File img;
	
	public ConsultModule(String id){
		if(ValidationId(id)){
			DAOModule DAOmodule = new DAOModule();
			try {
				module = DAOmodule.getModuleById(this.id);
				getFile();
			} catch (SQLException | ParserConfigurationException | SAXException | IOException e) {
				
				e.printStackTrace();
			}
		}else{
			
		}
	}
	
	private void getFile() throws ParserConfigurationException, SAXException, IOException{
		String filename = module.getPath();
		QueryXMLFile xmlFile = new QueryXMLFile();
		xmlFile.getFile(PATH, filename);
		Map<String, String> mapFile = xmlFile.getAllElement();
		img = new File(mapFile.get("image"));
		description = mapFile.get("description");
		
	}
	
	private void getParcours(){
		DAOParcoursModule DAOparcoursmodule = new DAOParcoursModule();
		List<Parcours> listParcours = DAOparcoursmodule.getByModuleId(module.getId());
		// TODO
		
	}
	private boolean ValidationId(String id) {
		try {
			this.id = Integer.parseInt(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

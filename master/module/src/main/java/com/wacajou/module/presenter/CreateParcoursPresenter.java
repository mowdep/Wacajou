package com.wacajou.module.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.vaadin.server.ServiceException;
import com.wacajou.core.file.io.MoveFile;
import com.wacajou.core.file.xml.Create;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.data.jpa.service.impl.ParcoursServiceImpl;
import com.wacajou.module.ui.view.CreateParcoursView;
import com.wacajou.module.ui.view.ParcoursViewHandler;

public class CreateParcoursPresenter implements ParcoursViewHandler {

	private CreateParcoursView view;
	private ParcoursServiceImpl service;

	private Create XMLFile;
	private String name;
	private String image;
	private String domain;
	private String description;
	private String path;
	private List<String> modules;
	private List<Module> listModule = null;

	public CreateParcoursPresenter(CreateParcoursView moduleView, ParcoursServiceImpl moduleService) {
		this.view = moduleView;
		this.service = moduleService;
		path = System.getProperty("application.PATH") + "Wacajou\\parcours";
	}

	@Override
	public void onViewEnter(String parameters) {
	}

	@Override
	public void create() {
		/* Récupération des données */
		try {
			getParams();
			XMLFile = new Create();
			XMLFile.createFile(path, name + "_V0");
			XMLFile.setElement("parcours");
			XMLFile.setAttribute("name", name);
			XMLFile.setAttribute("description", description);
			XMLFile.setAttribute("domain", domain);

			if (image != null) {
				String extension = "";
				for (int i = 0; i < image.length(); i++) {
					if (image.charAt(i) == '.') {
						extension = image.substring(i);
					}
				}
				System.out.println("extension : " + extension);
				MoveFile file = new MoveFile();
				file.moveFile(image, path + "\\" + name + extension);
				image = path + "\\" + name + extension;
			} else
				image = "";
			
			XMLFile.setAttribute("image", image);
			Parcours parcours = service.Create(name);
			for(int i = 0; i < listModule.size(); i++)
				service.setParcoursModule(parcours, listModule.get(i), false);
			XMLFile.saveFile();
			view.afterSuccessfulCreate();
		} catch (NullPointerException | TransformerException | ServiceException | ParserConfigurationException e) {
			e.printStackTrace();
			view.afterNonSuccessfulCreate("Cannot create Parcours");
		}
	}

	private void getParams() {
		name = view.getParcoursIdentifiant().getValue() + "-" + view.getParcoursName().getValue();
		description = view.getParcoursDescription().getValue();
		domain = (String) view.getParcoursDomain().getValue().toString();
		image = view.getParcoursImage();
		modules = view.getModuleAdded();
	}

	@Override
	public void urlChange(String params) {
	}

	@Override
	public void addModule(String moduleName) {
		// TODO Auto-generated method stub
		try {
			listModule = new ArrayList<Module>();
			for (int i = 0; i < modules.size(); i++)
				listModule.add(service.getModule(modules.get(i)));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delModule(String moduleName) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getModules() {
		List<String> listReturn = new ArrayList<String>();
		List<Module> listModuleToAddComboBox = service.getAllModule();
		for(int i = 0; i < listModuleToAddComboBox.size(); i++){
			listReturn.add(listModuleToAddComboBox.get(i).getName());
		}
		return listReturn;
	}
}

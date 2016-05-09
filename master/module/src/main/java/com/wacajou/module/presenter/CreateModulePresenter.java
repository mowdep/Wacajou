package com.wacajou.module.presenter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.vaadin.server.ServiceException;
import com.wacajou.core.file.io.MoveFile;
import com.wacajou.core.file.xml.Create;
import com.wacajou.module.model.ModuleServiceImpl;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;

public class CreateModulePresenter implements ModuleViewHandler {

	private CreateModuleView view;
	private ModuleServiceImpl service;

	private Create XMLFile;
	private String name;
	private String image;
	private String domain;
	private String description;
	private String path;

	public CreateModulePresenter(CreateModuleView moduleView,
			ModuleServiceImpl moduleService) {
		this.view = moduleView;
		this.service = moduleService;
		path = System.getProperty("application.PATH") + "Wacajou\\module";
	}

	@Override
	public void onViewEnter(String parameters) {
	}

	@Override
	public void create() {
		/* Récupération des données */
		getParams();
		try {

			XMLFile = new Create();
			XMLFile.createFile(path, name + "_V0");
			XMLFile.setElement("module");
			XMLFile.setAttribute("name", name);
			XMLFile.setAttribute("description", description);
			XMLFile.setAttribute("domaine", domain);

			String extension = "";
			for (int i = 0; i < image.length(); i++) {
				if (image.charAt(i) == '.') {
					extension = image.substring(i);
				}
			}
			System.out.println(image);
			System.out.println(extension);
			if (!image.equals("")) {
				MoveFile file = new MoveFile();
				file.moveFile(image, path + "\\" + name + extension);
				image = path + "\\" + name + extension;
			}
			XMLFile.setAttribute("image", image);
			service.Create(name, domain);
			XMLFile.saveFile();
			view.afterSuccessfulCreate();

		} catch (TransformerException | ServiceException
				| ParserConfigurationException e) {
			{
				e.printStackTrace();
				view.afterNonSuccessfulCreate("Cannot create Module");
			}
		}
	}

	private void getParams() {
		name = view.getModuleIdentifiant().getValue() + "-"
				+ view.getModuleName().getValue();
		description = view.getModuleDescription().getValue();
		domain = (String) view.getModuleDomain().getValue().toString();
		image = view.getModuleImage();
	}

	@Override
	public void urlChange(String params) {
	}
}

package com.wacajou.module.presenter;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinSession;
import com.wacajou.core.file.xml.Query;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.module.model.ModuleServiceImpl;
import com.wacajou.module.ui.view.ConsultModuleView;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;

public class ConsultModulePresenter implements ModuleViewHandler {
	private ConsultModuleView view;
	private ModuleServiceImpl service;

	private Query XMLFile;
	private String path;
	
	public ConsultModulePresenter(ConsultModuleView moduleView,
			ModuleServiceImpl moduleService) {
		this.view = moduleView;
		this.service = moduleService;
		path = System.getProperty("application.PATH")
				+ "Wacajou\\module";
		/*
		 * try { // service.Consult(moduleName); } catch (ServiceException e) {
		 * view.ErrorService(); e.printStackTrace(); }
		 */
	}

	@Override
	public void urlChange(String params) {
		try {
			Module module = service.Consult(params);
			XMLFile = new Query();
			XMLFile.getFile(path, module.getPath());
			Map<String, String> MapElement = XMLFile.getAllElement();
			view.setModule(module, MapElement, null);
		} catch (ServiceException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			view.ErrorService(service.getError());
		}
	}

	@Override
	public void onViewEnter(String parameters) {
	}

	@Override
	public void create() {
	}

}

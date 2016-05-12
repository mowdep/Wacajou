package com.wacajou.module.current.presenter;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.vaadin.server.ServiceException;
import com.wacajou.core.file.xml.Query;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.service.impl.ModuleServiceImpl;
import com.wacajou.module.current.component.ConsultModuleComponent;
import com.wacajou.module.current.view.ConsultModuleHandler;
import com.wacajou.module.current.view.CreateModuleHandler;


public class ConsultModulePresenter implements ConsultModuleHandler {
	private ModuleServiceImpl service;
	private ConsultModuleComponent view;
	private Query XMLFile;
	private String path;

	public ConsultModulePresenter(ConsultModuleComponent moduleView, ModuleServiceImpl moduleServiceImpl) {
		this.view = moduleView;
		this.service = moduleServiceImpl;
		path = System.getProperty("application.PATH") + "Wacajou\\module";
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
}

package com.wacajou.module.presenter;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.ServiceException;
import com.wacajou.module.model.ModuleServiceImpl;
import com.wacajou.module.ui.view.ModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;

public class ModulePresenter implements ModuleViewHandler {

	private ModuleView view;
	private ModuleServiceImpl service;

	public ModulePresenter(ModuleView moduleView, ModuleServiceImpl moduleService) {
		this.view = moduleView;
		this.service = moduleService;
	}
	
	@Override
	public void onViewEnter(String parameters) {
	}

	@Override
	public void create() {
		/* Récupération des données */
		String identifiant = view.getModuleIdentifiant().getValue();
		String name = view.getModuleName().getValue();
		String description = view.getModuleDescription().getValue();
		String domain = (String) view.getModuleDomain().getValue();
		String image = view.getModuleImage();
		System.out.println(image);
		try {
			service.Create(name, identifiant, image, description, domain);
			view.afterSuccessfulCreate();
		} catch (ServiceException e) {
			System.err.println("Erreur lors de la création d'un module");
			view.afterNonSuccessfulCreate(service.getError());
		}
	}
}

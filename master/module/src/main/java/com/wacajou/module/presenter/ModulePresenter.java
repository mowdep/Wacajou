package com.wacajou.module.presenter;

import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.module.model.ModuleService;
import com.wacajou.module.ui.view.ModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;

public class ModulePresenter implements ModuleViewHandler {

	private ModuleView view;
	private ModuleService service;
	
	public ModulePresenter(ModuleView moduleView, ModuleService moduleService) {
		this.view = moduleView;
		this.service = moduleService;
	}

	public void onViewEnter(String parameters){
		
	}
}

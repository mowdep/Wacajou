package com.wacajou.module.ui.view.impl;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.ui.view.ModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;

public class ModuleViewImpl extends VerticalLayout implements ModuleView{

	private ModuleViewHandler handler;

	private TextField txtModule;
	
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {	}

	@Override
	public void setHandler(ModuleViewHandler handler) {
		  this.handler = handler;		
	}

	@Override
	public void init() {
		// TODO Implements all components you need
		txtModule = new TextField("Module");
		addComponent(txtModule);
	}

	@Override
	public TextField getModuleTxtField() {
		return txtModule;
	}

}

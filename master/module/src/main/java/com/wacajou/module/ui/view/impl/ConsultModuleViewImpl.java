package com.wacajou.module.ui.view.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.module.ui.view.ConsultModuleView;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;
import com.wacajou.module.ui.view.message.ModuleMessage;

public class ConsultModuleViewImpl extends VerticalLayout implements ConsultModuleView {
	private ModuleViewHandler handler;
	private Label moduleName;
	private Label moduleParcours;
	private Label moduleDomain;
	private TextArea moduleDescription;
	private Image moduleImage;
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters() != null){
	           // split at "/", add each part as a label
	           String[] msgs = event.getParameters().split("/");
	           for (String msg : msgs) {
	        	   String[] args = msg.split("=");
	        	   System.out.println(msg);
	        	   if(args[0].equals("module")){
	        		   handler.urlChange(args[1]);
	        	   }
	           }
	       }
	}

	@Override
	public void setHandler(ModuleViewHandler handler) {
		this.handler = handler;
	}

	@Override
	public void init() {
		final GridLayout grid = new GridLayout(12,12);
		grid.setSizeFull();
		
		moduleName = new Label();
		grid.addComponent(moduleName, 0, 0, 3, 0);
		
		moduleDomain = new Label();
		grid.addComponent(moduleDomain, 0, 1, 3, 1);
		
		moduleParcours = new Label();
		grid.addComponent(moduleParcours, 4, 2, 11, 3);
		
		moduleImage = new Image();
		grid.addComponent(moduleImage, 1, 5, 4, 10);
		
		moduleDescription = new TextArea();
		grid.addComponent(moduleDescription, 1, 11, 6, 11);
		
		
		
		addComponent(new Label("lol"));
	}

	@Override
	public void setModule(Module module, Map<String,String> MapElement, List<Parcours> parcours) {
		moduleName.setCaption(module.getName());
		moduleDomain.setCaption(module.getDomain());
		moduleDescription.setCaption(MapElement.get("description"));
		String file;
		System.out.println("file : " + (file = MapElement.get("image")));
		moduleImage.setSource(new FileResource(new File(file)));
		String parcoursValue = "";
		for(int i = 0; i < parcours.size(); i++){
			parcoursValue = parcoursValue + ", " + parcours.get(i).getName();
		}
		moduleParcours.setCaption(parcoursValue);
	}

	@Override
	public void ErrorService(String error) {
		new Notification(ModuleMessage.MODULE_NON_SUCCESS_CONSULT, error,
				com.vaadin.ui.Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
	}

	@Override
	public void setName(Object attribute) {
		System.out.println(attribute);
	}
	

}

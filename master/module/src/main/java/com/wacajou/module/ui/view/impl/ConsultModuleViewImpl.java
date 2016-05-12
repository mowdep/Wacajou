package com.wacajou.module.ui.view.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.module.ui.view.ConsultModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;
import com.wacajou.module.ui.view.message.Message;

public class ConsultModuleViewImpl extends VerticalLayout implements ConsultModuleView {
	private static final long serialVersionUID = 8287893077152702836L;

	private ModuleViewHandler handler;
	private TextField moduleName;
	private TextField moduleParcours;
	private TextField moduleDomain;
	private TextArea moduleDescription;
	private Image moduleImage;

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() != null) {
			// split at "/", add each part as a label
			String[] msgs = event.getParameters().split("/");
			for (String msg : msgs) {
				String[] args = msg.split("=");
				System.out.println("Url split : " + msg);
				if (args[0].equals("module")) {
					String urlTransfert = args[1].replace("_", " ");
					System.out.println("Url transform : " + urlTransfert);
					handler.urlChange(urlTransfert);
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
		final GridLayout grid = new GridLayout(12, 12);
		grid.setSizeFull();

		moduleName = new TextField();
		moduleName.setWidth(500, Unit.PIXELS);
		moduleName.setReadOnly(true);
		grid.addComponent(moduleName, 0, 0, 3, 0);

		moduleDomain = new TextField();
		moduleDomain.setWidth(500, Unit.PIXELS);
		moduleDomain.setReadOnly(true);
		grid.addComponent(moduleDomain, 0, 1, 3, 1);

		moduleParcours = new TextField();
		moduleParcours.setWidth(500, Unit.PIXELS);
		moduleParcours.setReadOnly(true);
		grid.addComponent(moduleParcours, 4, 2, 11, 3);

		moduleImage = new Image();
		moduleImage.setAlternateText("Image not found on the server");
		grid.addComponent(moduleImage, 0, 5, 4, 10);

		moduleDescription = new TextArea();
		moduleDescription.setWidth(700, Unit.PIXELS);
		moduleDescription.setReadOnly(true);
		grid.addComponent(moduleDescription, 0, 11, 6, 11);
		
		addComponent(grid);
		Button buttonSave = new Button("Save change");
		buttonSave.setVisible(false);
		addComponent(buttonSave);
		
		Button buttonModif = new Button("Modifier le module");
		buttonModif.addClickListener(new ClickListener(){
			public void buttonClick(ClickEvent event) {
				moduleDescription.setReadOnly(false);
				moduleName.setReadOnly(false);
				moduleDomain.setReadOnly(false);
				buttonSave.setVisible(true);
			}
		});
		addComponent(buttonModif);
	}

	@Override
	public void setModule(Module module, Map<String, String> MapElement, List<Parcours> parcours) {
		moduleName.setValue(module.getName());
		moduleDomain.setValue(module.getDomain());
		if (MapElement.containsKey("description"))
			if (MapElement.get("description") != null) {
				moduleDescription.setValue(MapElement.get("description"));
			}
		String file;
		if (MapElement.containsKey("image")) {
			if (MapElement.get("image") != null) {
				System.out.println("file : " + (file = MapElement.get("image")));
				moduleImage.setSource(new FileResource(new File(file)));
			}
		}
		String parcoursValue = "";
		if (parcours != null) {
			for (int i = 0; i < parcours.size(); i++) {
				parcoursValue = parcoursValue + ", " + parcours.get(i).getName();
			}
		}
		moduleParcours.setValue(parcoursValue);

	}

	@Override
	public void ErrorService(String error) {
		new Notification(Message.MODULE_NON_SUCCESS_CONSULT, error, com.vaadin.ui.Notification.Type.ERROR_MESSAGE)
				.show(Page.getCurrent());
	}

	@Override
	public void setName(Object attribute) {
		System.out.println(attribute);
	}

}

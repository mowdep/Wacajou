package com.wacajou.module.ui.view.impl;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.core.file.io.ImageUploader;
import com.wacajou.module.ui.view.ModuleView;
import com.wacajou.module.ui.view.ModuleViewHandler;
import com.wacajou.module.ui.view.message.ModuleMessage;

public class ModuleViewImpl extends VerticalLayout implements ModuleView {

	private ModuleViewHandler handler;
	private ComboBox moduleDomain;
	private TextField moduleName;
	private TextField moduleIdentifiant;
	private TextArea moduleDescription;
	private Embedded moduleImage;
	private Button moduleSave;
	private ModuleMessage moduleMessage;
	private String lang = "fr";
	private String image;
	private ImageUploader receiver;
	
	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
		handler.onViewEnter("");
	}

	@Override
	public void setHandler(ModuleViewHandler handler) {
		this.handler = handler;
	}

	@Override
	public void init() {
		// TODO Implements all components you need
		final FormLayout formLayout = new FormLayout();
		moduleName = new TextField(moduleMessage.MODULE_NAME_LABEL);
		moduleName.setRequired(true);
		moduleName.setDescription(moduleMessage.MODULE_NAME_DESCRIPTION);
		moduleName.addValidator(new NullValidator(lang, false));
		formLayout.addComponent(moduleName);

		moduleIdentifiant = new TextField(moduleMessage.MODULE_IDENTIFIANT_LABEL);
		moduleIdentifiant.setRequired(true);
		moduleIdentifiant.setDescription(moduleMessage.MODULE_IDENTIFIANT_DESCRIPTION);
		moduleIdentifiant.addValidator(new NullValidator(lang, false));
		formLayout.addComponent(moduleIdentifiant);

		moduleDomain = new ComboBox(moduleMessage.MODULE_DOMAIN_LABEL);
		moduleDomain.setRequired(true);
		moduleDomain.setDescription(moduleMessage.MODULE_DOMAIN_DESCRIPTION);
		formLayout.addComponent(moduleDomain);

		moduleDescription = new TextArea(moduleMessage.MODULE_DESCRIPTION_LABEL);
		moduleDescription.setDescription(moduleMessage.MODULE_DESCRIPTION_DESCIPTION);
		formLayout.addComponent(moduleDescription);

		Embedded moduleImage = new Embedded("Uploaded Image");
		moduleImage.setVisible(false);

		// Implement both receiver that saves upload in a file and
		// listener for successful upload
		
		receiver = new ImageUploader(moduleImage);
		
		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);

		// Put the components in a panel
		Panel panel = new Panel("Image du module");
		Layout panelContent = new VerticalLayout();
		panelContent.addComponents(upload, moduleImage);
	    panel.setContent(panelContent);
	    formLayout.addComponent(panel);
	    
	    moduleSave = new Button(moduleMessage.MODULE_BUTTON_LABEL);
		moduleSave.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				handler.create();
			}
		});
		formLayout.addComponent(moduleSave);
		
		addComponent(formLayout);
	}

	@Override
	public TextField getModuleName() {
		return moduleName;
	}

	@Override
	public TextField getModuleIdentifiant() {
		return moduleIdentifiant;
	}

	@Override
	public TextArea getModuleDescription() {
		return moduleDescription;
	}

	@Override
	public String getModuleImage() {
		if(receiver.file != null){
			image = receiver.file.getPath();
		}
		return image;
	}

	@Override
	public Button getModuleSave() {
		return moduleSave;
	}

	@Override
	public ComboBox getModuleDomain() {
		return moduleDomain;
	}

	@Override
	public void afterSuccessfulCreate() {
		new Notification(moduleMessage.MODULE_SUCCESS_CREATE, moduleMessage.MODULE_SUCCESS_DESCRIPTION,
				com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
	}

	@Override
	public void afterNonSuccessfulCreate(String error) {
		new Notification(moduleMessage.MODULE_NON_SUCCESS_CREATE, error,
				com.vaadin.ui.Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
	}
}

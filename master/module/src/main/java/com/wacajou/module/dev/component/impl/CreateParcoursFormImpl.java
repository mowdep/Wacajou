package com.wacajou.module.dev.component.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.core.file.io.ImageUploader;
import com.wacajou.data.jpa.domain.Domain;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.data.jpa.service.impl.ModuleServiceImpl;
import com.wacajou.module.ui.view.message.Message;
import com.wacajou.module.current.component.CreateModuleForm;
import com.wacajou.module.current.presenter.CreateModulePresenter;
import com.wacajou.module.current.view.CreateModuleHandler;
import com.wacajou.module.dev.component.CreateParcoursForm;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class CreateParcoursFormImpl extends VerticalLayout implements CreateParcoursForm {

	@Autowired
	private final ModuleRepository moduleRepository;
	private CreateParcoursPresenter CreateParcoursPresenter;
	private CreateModuleHandler handler;

	private Module module;

	private ComboBox moduleDomain;
	private TextField moduleName;
	private TextField moduleIdentifiant;
	private TextArea moduleDescription;
	private String lang = "fr";
	private String image;
	private ImageUploader receiver;

	Button save = new Button("Save", FontAwesome.SAVE);
	CssLayout actions = new CssLayout(save);

	@Override
	public void enter(ViewChangeEvent event) {
		
	}


	@Override
	public void setHandler(CreateModuleHandler handler) {
		this.handler = handler;
	}

	@Autowired
	public CreateParcoursFormImpl(ModuleRepository moduleRepository) {

		this.moduleRepository = moduleRepository;

		CreateParcoursPresenter = new CreateParcoursPresenter(this, new ModuleServiceImpl(moduleRepository));
		this.setHandler(CreateParcoursPresenter);

		moduleName = new TextField("Nom");
		moduleIdentifiant = new TextField("Identifiant");
		moduleDescription = new TextArea("Description");
		moduleDomain = new ComboBox("Domaine");
		this.moduleName.setRequired(true);
		this.moduleIdentifiant.setRequired(true);
		this.moduleDomain.setRequired(true);
		this.moduleDomain.addItems(Domain.values());

		Embedded moduleImage = new Embedded("Image");
		moduleImage.setVisible(false);
		moduleImage.setWidth(200, Unit.PIXELS);
		moduleImage.setHeight(400, Unit.PIXELS);

		receiver = new ImageUploader(moduleImage);

		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);

		// Put the components in a panel

		addComponents(moduleName, moduleIdentifiant, moduleDomain, moduleDescription, upload, moduleImage, actions);

		setSpacing(true);
		save.addClickListener(e -> handler.create());
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
		if (receiver.file != null) {
			image = receiver.file.getPath();
		}
		return image;
	}

	@Override
	public ComboBox getModuleDomain() {
		return moduleDomain;
	}

	@Override
	public void afterSuccessfulCreate() {
		new Notification(Message.MODULE_SUCCESS_CREATE, Message.MODULE_SUCCESS_DESCRIPTION,
				com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
	}

	@Override
	public void afterNonSuccessfulCreate(String error) {
		new Notification(Message.MODULE_NON_SUCCESS_CREATE, error, com.vaadin.ui.Notification.Type.ERROR_MESSAGE)
				.show(Page.getCurrent());
	}
}

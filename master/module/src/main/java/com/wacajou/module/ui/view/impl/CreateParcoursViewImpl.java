package com.wacajou.module.ui.view.impl;

import java.util.List;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.core.component.CustomComboBoxComponent;
import com.wacajou.core.file.io.ImageUploader;
import com.wacajou.data.jpa.domain.Domain;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.CreateParcoursView;
import com.wacajou.module.ui.view.ModuleViewHandler;
import com.wacajou.module.ui.view.ParcoursViewHandler;
import com.wacajou.module.ui.view.message.Message;

public class CreateParcoursViewImpl extends VerticalLayout implements CreateParcoursView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParcoursViewHandler handler;
	private ComboBox parcoursDomain;
	private TextField parcoursName;
	private TextField parcoursIdentifiant;
	private TextArea parcoursDescription;
	private Button parcoursSave;
	private String lang = "fr";
	private String image;
	private ImageUploader receiver;
	private CustomComboBoxComponent moduleCombo;

	@Override
	public void enter(ViewChangeEvent viewChangeEvent) {
		handler.onViewEnter("");
	}

	@Override
	public void setHandler(ParcoursViewHandler handler) {
		this.handler = handler;
	}

	@Override
	public void init() {
		// TODO Implements all components you need
		final FormLayout formLayout = new FormLayout();
		parcoursName = new TextField(Message.PARCOURS_NAME_LABEL);
		parcoursName.setRequired(true);
		parcoursName.setDescription(Message.PARCOURS_NAME_DESCRIPTION);
		parcoursName.addValidator(new NullValidator(lang, false));
		formLayout.addComponent(parcoursName);

		parcoursIdentifiant = new TextField(Message.PARCOURS_IDENTIFIANT_LABEL);
		parcoursIdentifiant.setRequired(true);
		parcoursIdentifiant.setDescription(Message.PARCOURS_IDENTIFIANT_DESCRIPTION);
		parcoursIdentifiant.addValidator(new NullValidator(lang, false));
		formLayout.addComponent(parcoursIdentifiant);

		parcoursDomain = new ComboBox(Message.PARCOURS_DOMAIN_LABEL);
		parcoursDomain.setRequired(true);
		parcoursDomain.setDescription(Message.PARCOURS_DOMAIN_DESCRIPTION);
		parcoursDomain.addItems(Domain.ELECTRONIC);
		parcoursDomain.addItems(Domain.INFORMATIC);
		parcoursDomain.addItems(Domain.LANGUES);
		parcoursDomain.addItems(Domain.MANAGERIAL);
		parcoursDomain.addItems(Domain.OTHER);
		
		formLayout.addComponent(parcoursDomain);

		parcoursDescription = new TextArea(Message.PARCOURS_DESCRIPTION_LABEL);
		parcoursDescription.setDescription(Message.PARCOURS_DESCRIPTION_DESCRIPTION);
		formLayout.addComponent(parcoursDescription);

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
	    
		moduleCombo = new CustomComboBoxComponent(handler.getModules());
		formLayout.addComponent(moduleCombo);
		
		parcoursSave = new Button(Message.PARCOURS_BUTTON_LABEL);
		parcoursSave.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				handler.create();
			}
		});
		formLayout.addComponent(parcoursSave);
		
		addComponent(formLayout);
	}

	@Override
	public void afterSuccessfulCreate() {
		new Notification(Message.PARCOURS_SUCCESS_CREATE, Message.PARCOURS_SUCCESS_DESCRIPTION,
				com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
	}

	@Override
	public void afterNonSuccessfulCreate(String error) {
		new Notification(Message.PARCOURS_NON_SUCCESS_CREATE, error,
				com.vaadin.ui.Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
	}

	@Override
	public TextField getParcoursName() {
		return parcoursName;
	}

	@Override
	public TextField getParcoursIdentifiant() {
		return parcoursIdentifiant;
	}

	@Override
	public ComboBox getParcoursDomain() {
		return parcoursDomain;
	}

	@Override
	public TextArea getParcoursDescription() {
		return parcoursDescription;
	}

	@Override
	public String getParcoursImage() {
		if(receiver.file != null){
			image = receiver.file.getPath();
		}
		return image;
	}

	@Override
	public List<String> getModuleAdded() {
		return moduleCombo.getValueToAdd();
	}
}

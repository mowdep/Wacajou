package com.wacajou.module.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.controller.CreateModule;
import com.wacajou.module.message.LangText;

@UIScope
@SpringView(name = CreateViewModule.VIEW_NAME)
public class CreateViewModule extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "create";
	private int valueStatut = 4;
	private String lang = "en";
	private LangText lab;

	@PostConstruct
	void init() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		lang = "fr";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("lang")) {
				// lang = cookie.getValue();
				lang = "fr";
			}
		}
		lab = new LangText(lang);
		setMargin(true);
		setSpacing(true);

		final FormLayout form = new FormLayout();
		
		final TextField name = new TextField();
		name.setRequired(true);
		name.setInputPrompt(lab.LABEL_NAME_PARCOURS);
		name.setDescription(lab.LABEL_NAME_PARCOURS);
		name.addValidator(new NullValidator(lang, false));
		form.addComponent(name);

		final TextField id_respo = new TextField();
		id_respo.setInputPrompt(lab.LABEL_ID_RESPO_PARCOURS);
		id_respo.setDescription(lab.LABEL_ID_RESPO_PARCOURS);

		form.addComponent(id_respo);

		final TextArea desc = new TextArea();
		desc.setInputPrompt(lab.LABEL_DESC_PARCOURS);
		desc.setDescription(lab.LABEL_DESC_PARCOURS);
		form.addComponent(desc);
		
		final Embedded image = new Embedded("Uploaded Image");
		image.setVisible(false);

		// Implement both receiver that saves upload in a file and
		// listener for successful upload
		class ImageUploader implements Receiver, SucceededListener {
		    public File file;

		    public OutputStream receiveUpload(String filename,
		                                      String mimeType) {
		        // Create upload stream
		        FileOutputStream fos = null; // Stream to write to
		        try {
		            // Open the file for writing.
		            file = new File("B:\\tmp\\uploads\\" + filename);
		            fos = new FileOutputStream(file);
		        } catch (final java.io.FileNotFoundException e) {
		            new Notification("Could not open file<br/>",
		                             e.getMessage(),
		                             Notification.Type.ERROR_MESSAGE)
		                .show(Page.getCurrent());
		            return null;
		        }
		        return fos; // Return the output stream to write to
		    }

		    public void uploadSucceeded(SucceededEvent event) {
		        // Show the uploaded file in the image viewer
		        image.setVisible(true);
		        image.setSource(new FileResource(file));
		    }
		};
		final ImageUploader receiver = new ImageUploader();

		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);

		// Put the components in a panel
		Panel panel = new Panel("Cool Image Storage");
		Layout panelContent = new VerticalLayout();
		panelContent.addComponents(upload, image);
	    panel.setContent(panelContent);
	    form.addComponent(panel);

		final Button submit = new Button(lab.BUTTON_SUBMIT_PARCOURS);
		submit.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(name.isValid()){
					
					// Creation de la variable englobant les variable récupérer
					String[] cmd = new String[4];
					cmd[0] = name.getValue();
					cmd[1] = desc.getValue();
					cmd[2] = id_respo.getValue();
					cmd[3] = receiver.file.getPath();
					
					// Execution de la class pour créer le module ( controller )
					CreateModule nwModule = new CreateModule(lang, cmd);
									
					// Affichage des erreurs / Sucess de la création de module
					if(nwModule.erreur != null){
						new Notification(nwModule.erreur, Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
					}else{
						new Notification(nwModule.sucess, Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
					}
				}else{
					new Notification("Les champs ne sont pas rempli ou non valide", Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
				}
			}
		});
		form.addComponent(submit);

		addComponent(form);
	}

	public void enter(ViewChangeEvent event) {

	}
}

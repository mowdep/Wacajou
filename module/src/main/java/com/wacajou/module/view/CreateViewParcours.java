package com.wacajou.module.view;

import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.component.AddModuleComponent;
import com.wacajou.entity.Module;
import com.wacajou.module.controller.CreateModule;
import com.wacajou.module.controller.CreateParcours;
import com.wacajou.module.message.LangText;

@SpringView(name = CreateViewParcours.VIEW_NAME)
public class CreateViewParcours extends VerticalLayout implements View {
	public static final String VIEW_NAME = "createParcours";

	private String lang = "en";
	private LangText lab;

	@PostConstruct
	public void init() {

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

		final FormLayout formLayout = new FormLayout();

		final TextField nameComponent = new TextField();
		nameComponent.setRequired(true);
		nameComponent.setInputPrompt(lab.LABEL_NAME_PARCOURS);
		nameComponent.setDescription(lab.LABEL_NAME_PARCOURS);
		nameComponent.addValidator(new NullValidator(lang, false));
		formLayout.addComponent(nameComponent);

		final TextField idRespoComponent = new TextField();
		idRespoComponent.setInputPrompt(lab.LABEL_ID_RESPO_PARCOURS);
		idRespoComponent.setDescription(lab.LABEL_ID_RESPO_PARCOURS);

		formLayout.addComponent(idRespoComponent);

		final TextArea descriptionComponent = new TextArea();
		descriptionComponent.setInputPrompt(lab.LABEL_DESC_PARCOURS);
		descriptionComponent.setDescription(lab.LABEL_DESC_PARCOURS);
		formLayout.addComponent(descriptionComponent);

		final AddModuleComponent addModuleComponent = new AddModuleComponent(
				"Modules");
		formLayout.addComponent(addModuleComponent);

		final AddModuleComponent addModuleOptionalComponent = new AddModuleComponent(
				"Modules Optionels");
		formLayout.addComponent(addModuleOptionalComponent);

		final Image img = new Image();

		final Button submit = new Button(lab.BUTTON_SUBMIT_PARCOURS);
		submit.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (nameComponent.isValid()) {

					// Creation de la variable englobant les variable récupérer
					String[] values = new String[4];
					values[0] = nameComponent.getValue();
					values[1] = descriptionComponent.getValue();
					values[2] = idRespoComponent.getValue();

					Vector<String> module = addModuleComponent.getModuleToAdd();
					Vector<String> moduleOptional = addModuleOptionalComponent
							.getModuleToAdd();

					// Execution de la class pour créer le module ( controller )
					CreateParcours nwParcours = new CreateParcours(lang,
							values, module, moduleOptional);

					// Affichage des erreurs / Sucess de la création de module
					if (nwParcours.erreur != null)
						new Notification(nwParcours.erreur,
								Notification.Type.ERROR_MESSAGE).show(Page
								.getCurrent());
					else
						new Notification(nwParcours.sucess,
								Notification.Type.HUMANIZED_MESSAGE).show(Page
								.getCurrent());

				} else
					new Notification(
							"Les champs ne sont pas rempli ou non valide",
							Notification.Type.HUMANIZED_MESSAGE).show(Page
							.getCurrent());

			}
		});
		formLayout.addComponent(submit);

		addComponent(formLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

package com.wacajou.module.view;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
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
		setHeight(400, Unit.PIXELS);
		setWidth(400, Unit.PIXELS);
		setMargin(true);
		setSpacing(true);

		final FormLayout form = new FormLayout();
		final TextField id = new TextField();
		id.setRequired(true);
		id.setInputPrompt(lab.LABEL_ID_PARCOURS);
		id.setDescription(lab.LABEL_ID_PARCOURS);
		id.addValidator(new NullValidator("Valeur non valide", false));
		form.addComponent(id);
		
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

		final Image img = new Image();

		final Button submit = new Button(lab.BUTTON_SUBMIT_PARCOURS);
		submit.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if(id.isValid() && name.isValid()){
					
					// Creation de la variable englobant les variable récupérer
					String[] cmd = new String[4];
					cmd[0] = id.getValue();
					cmd[1] = name.getValue();
					cmd[2] = desc.getValue();
					cmd[3] = id_respo.getValue();
					
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

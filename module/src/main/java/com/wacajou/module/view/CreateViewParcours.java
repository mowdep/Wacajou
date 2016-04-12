package com.wacajou.module.view;

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
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.wacajou.component.AddModuleComponent;
import com.wacajou.entity.Module;
import com.wacajou.module.controller.CreateModule;
import com.wacajou.module.message.LangText;

@SpringView(name = CreateViewParcours.VIEW_NAME)
public class CreateViewParcours extends VerticalLayout implements View{
	public static final String VIEW_NAME = "test";

	private String lang = "en";
	private LangText lab;
	
	@PostConstruct
	public void init(){
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

		EntityManager em = JPAContainerFactory.createEntityManagerForPersistenceUnit("isep");
		CachingMutableLocalEntityProvider<Module> entityProvider = new CachingMutableLocalEntityProvider<Module>(
				Module.class, em);
		JPAContainer<Module> JPAModule = new JPAContainer<Module>(Module.class);
		JPAModule.setEntityProvider(entityProvider);
		AddModuleComponent comp = new AddModuleComponent("test", JPAModule);
		comp.setHeight(500, Unit.PIXELS);
		form.addComponent(comp);
		
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
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}

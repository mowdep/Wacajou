package com.wacajou.module.view;


import javax.annotation.PostConstruct;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.entity.Module;

@UIScope
@SpringView(name = CreateView.VIEW_NAME)
public class CreateView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "create";

	@PostConstruct
	void init(){
		JPAContainer<Module> JPAModule = JPAContainerFactory.make(Module.class, "isep");
		Module module = new Module();
		module.setModule_name("test");
		module.setId_respo(000);
		JPAModule.createEntityItem(module);
		JPAModule.addEntity(module);
		setMargin(true);
        setSpacing(true);
		final FormLayout form = new FormLayout();
		final TextField id = new TextField("Identifiant du module");
		id.setRequired(true);
		form.addComponent(id);
		
		final TextField name = new TextField("Nom du module");
		name.setRequired(true);
		form.addComponent(name);
		
		final TextField id_respo = new TextField("Identifiant du responsable");
		
		addComponent(form);
	}
	public void enter(ViewChangeEvent event) {
		//addComponent(new Label("echo something"));
	}
}

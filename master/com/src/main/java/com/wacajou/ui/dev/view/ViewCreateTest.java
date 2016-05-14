package com.wacajou.ui.dev.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.current.component.impl.CreateModuleFormImpl;
import com.wacajou.module.dev.component.impl.CreateParcoursFormImpl;

@SpringView(name = ViewCreateTest.VIEW_NAME)
public class ViewCreateTest extends VerticalLayout implements View {
	public static final String VIEW_NAME = "devcreate";

	@Autowired
	private CreateModuleFormImpl moduleForm;

	@Autowired
	private CreateParcoursFormImpl parcoursForm;

	@Autowired
	public ViewCreateTest(CreateModuleFormImpl moduleForm, CreateParcoursFormImpl parcoursForm) {
		this.parcoursForm = parcoursForm;
		this.moduleForm = moduleForm;

		parcoursForm.setVisible(false);
		moduleForm.setVisible(false);
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.addComponents(parcoursForm, moduleForm);
		addComponent(mainLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters() != null){
			String urlFragment = event.getParameters();
			System.out.println(urlFragment);
			String[] fragment = urlFragment.split("/");
			if(fragment[0].equals("module")){
				moduleForm.setVisible(true);
			}else if(fragment[0].equals("parcours")){
				parcoursForm.setVisible(true);
			}else{
				parcoursForm.setVisible(true);
				addComponent(new Label("Test duo components"));
				moduleForm.setVisible(true);
			}
			
		}
	}

}

package com.wacajou.ui.current.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.current.component.impl.CreateModuleFormImpl;

@SpringView(name = ViewCreate.VIEW_NAME)
public class ViewCreate extends VerticalLayout implements View {
	public static final String VIEW_NAME = "create";

	@Autowired
	private CreateModuleFormImpl moduleForm;

	@Autowired
	public ViewCreate(CreateModuleFormImpl moduleForm) {
		this.moduleForm = moduleForm;

		moduleForm.setVisible(false);
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.addComponents(moduleForm);
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
			}else{
				addComponent(new Label("Test duo components"));
			}
			
		}
	}

}

package com.wacajou.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.current.component.ConsultModuleComponent;
import com.wacajou.module.current.component.impl.CreateModuleFormImpl;
import com.wacajou.module.current.component.impl.MinimizeModuleComponentImpl;
import com.wacajou.module.ui.view.ConsultModuleView;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.impl.CreateModuleViewImpl;
import com.wacajou.ui.current.view.ViewCreate;

@SuppressWarnings("serial")
@SpringUI( path = "/test")
@Theme("valo")
public class VaadinUI extends UI {
	
	@Autowired
	SpringViewProvider viewProvider;

	@Autowired
	private MinimizeModuleComponentImpl moduleMinimize;
	@Autowired 
	private ConsultModuleComponent moduleConsult;
	@Autowired
	private CreateModuleFormImpl moduleForm;
//	@Autowired
//	private CreateModuleFormImpl parcoursForm;
	
	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		HorizontalLayout actions = new HorizontalLayout();
		Button buttonSwitch = new Button("Créer un parcours");
		Navigator navigator = new Navigator(this, this);
		ViewCreate createView = new ViewCreate(moduleForm /*, parcoursForm */);
		navigator.addView(ViewCreate.VIEW_NAME, createView);
		
		setNavigator(navigator);
		navigator.addProvider(viewProvider);
		actions.addComponent(buttonSwitch);
		
		VerticalLayout mainLayout = new VerticalLayout(actions, moduleConsult, moduleMinimize);
		
		setContent(mainLayout);
		
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		
		buttonSwitch.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				buttonSwitch.setCaption("Créer un module");
			}
		});		
	}
}

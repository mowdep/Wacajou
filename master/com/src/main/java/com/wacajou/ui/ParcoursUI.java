package com.wacajou.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.data.jpa.repository.ParcoursModuleRepository;
import com.wacajou.data.jpa.repository.ParcoursRepository;
import com.wacajou.data.jpa.service.impl.ParcoursServiceImpl;
import com.wacajou.module.presenter.CreateParcoursPresenter;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.CreateParcoursView;
import com.wacajou.module.ui.view.impl.CreateParcoursViewImpl;

@Theme("valo")
@SpringUI(path = "/parcours")
public class ParcoursUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpringViewProvider viewProvider;

	@Autowired
	private ParcoursRepository parcoursRepository;
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private ParcoursModuleRepository parcoursModuleRepository;
	private CreateParcoursPresenter createParcoursPresenter;

	@Override
	protected void init(VaadinRequest request) {
		
		Navigator navigator = new Navigator(this, this);

		CreateParcoursView createView = new CreateParcoursViewImpl();
		
		createParcoursPresenter = new CreateParcoursPresenter(createView, new ParcoursServiceImpl(parcoursRepository, moduleRepository, parcoursModuleRepository));
		
		createView.setHandler(createParcoursPresenter);
		
		createView.init();
		
		navigator.addView(CreateModuleView.VIEW_NAME, createView);
		
		setNavigator(navigator);
		navigator.addProvider(viewProvider);
	}

	public void enter(ViewChangeEvent event) {
	}

}

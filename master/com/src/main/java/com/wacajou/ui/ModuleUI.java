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
import com.wacajou.data.jpa.service.impl.ModuleServiceImpl;
import com.wacajou.module.presenter.ConsultModulePresenter;
import com.wacajou.module.presenter.CreateModulePresenter;
import com.wacajou.module.ui.view.ConsultModuleView;
import com.wacajou.module.ui.view.CreateModuleView;
import com.wacajou.module.ui.view.impl.ConsultModuleViewImpl;
import com.wacajou.module.ui.view.impl.CreateModuleViewImpl;

@Theme("valo")
@SpringUI(path = "/module")
public class ModuleUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpringViewProvider viewProvider;

	@Autowired
	private ModuleRepository moduleRepository;
	private CreateModulePresenter CreateModulePresenter;
	private ConsultModulePresenter ConsultModulePresenter;

	@Override
	protected void init(VaadinRequest request) {
		
		Navigator navigator = new Navigator(this, this);

		CreateModuleView createView = new CreateModuleViewImpl();
		ConsultModuleView consultView = new ConsultModuleViewImpl();
		
		CreateModulePresenter = new CreateModulePresenter(createView, new ModuleServiceImpl(moduleRepository));
		ConsultModulePresenter = new ConsultModulePresenter(consultView, new ModuleServiceImpl(moduleRepository));
		
		consultView.setHandler(ConsultModulePresenter);
		createView.setHandler(CreateModulePresenter);
		
		createView.init();
		consultView.init();
		
		navigator.addView(CreateModuleView.VIEW_NAME, createView);
		navigator.addView(ConsultModuleView.VIEW_NAME, consultView);
		
		setNavigator(navigator);
		navigator.addProvider(viewProvider);
	}

	public void enter(ViewChangeEvent event) {
	}

}

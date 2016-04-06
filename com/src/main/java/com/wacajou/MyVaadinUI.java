package com.wacajou;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.model.User;

import module.view.CreateView;

@Theme("valo")
@SpringUI
public class MyVaadinUI extends UI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);
		
		final CssLayout navBar = new CssLayout();
		navBar.addComponent(createNavigationButton("Defaut view", DefautView.VIEW_NAME));
		navBar.addComponent(createNavigationButton("Create view", CreateView.VIEW_NAME));

		root.addComponent(navBar);
		
		final Panel viewContainer = new Panel();
		viewContainer.setSizeFull();
		root.addComponent(viewContainer);
		
		Navigator navigator = new Navigator(this, viewContainer);
		navigator.addProvider(viewProvider);
		
	}

	private Button createNavigationButton(String caption, String viewName) {
		Button button = new Button(caption);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		return button;
	}
}

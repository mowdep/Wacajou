package com.wacajou.module.view;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.boot.test.SpringApplicationConfiguration;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;



@Theme("valo")
@SpringUI(path="/module")
public class UIModule extends UI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
    private SpringViewProvider viewProvider;

	protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Create View",
        		CreateViewModule.VIEW_NAME));
        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
        button.addClickListener(new ClickListener(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;


			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(viewName);
				
			}   	
        });
        return button;
    }
}

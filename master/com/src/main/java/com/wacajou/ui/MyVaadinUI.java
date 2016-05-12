package com.wacajou.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SpringUI
public class MyVaadinUI extends UI{
	
	private static final long serialVersionUID = 3888847515424522175L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// TODO Generate admin consol interface of configuration
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);
		
		
	}

}

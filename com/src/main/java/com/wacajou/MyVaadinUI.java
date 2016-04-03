package com.wacajou;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.model.User;

@Theme("valo")
@SpringUI
public class MyVaadinUI extends UI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// TODO Auto-generated method stub
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);
	
		JPAContainer<User> user =
			    JPAContainerFactory.make(User.class, "isep");
		User users = new User();
		users.inscrire("test", "1", "test@mail");
		user.addEntity(users);

	}

}

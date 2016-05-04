package com.wacajou.module.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.module.model.ModuleServiceImpl;
import com.wacajou.module.presenter.ModulePresenter;
import com.wacajou.module.ui.view.ModuleView;
import com.wacajou.module.ui.view.impl.ModuleViewImpl;
import com.wacajou.core.ui.view.AccessDenied;

@Theme("valo")
@SpringUI(path = "/module")
public class ModuleUI extends UI {
	@Autowired
	SpringViewProvider viewProvider;

	@Autowired
	private ModuleRepository moduleRepository;
	
    @Override
    protected void init(VaadinRequest request) {
    	Navigator navigator = new Navigator(this, this);

        ModuleView moduleView = new ModuleViewImpl();
        ModulePresenter modulePresenter = new ModulePresenter(moduleView, new ModuleServiceImpl(moduleRepository));
        moduleView.setHandler(modulePresenter);
        moduleView.init();
        navigator.addView("", moduleView);
        setNavigator(navigator);
        navigator.addProvider(viewProvider);
        navigator.navigateTo("");
    }
}

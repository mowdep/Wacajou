package com.wacajou.module.current.component.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.data.jpa.service.impl.ModuleServiceImpl;
import com.wacajou.module.current.component.MinimizeModuleComponent;
import com.wacajou.module.current.presenter.MinimizeModulePresenter;
import com.wacajou.module.current.view.ConsultModuleHandler;
import com.wacajou.module.current.view.CreateModuleHandler;
import com.wacajou.module.ui.view.message.Message;

@SuppressWarnings("serial")
@SpringComponent
@UIScope
public class MinimizeModuleComponentImpl extends VerticalLayout implements MinimizeModuleComponent{
	@Autowired
	private final ModuleRepository moduleRepository;
	private MinimizeModulePresenter MinimizeModulePresenter;

	private ConsultModuleHandler handler;

	private Module module;
	private Label moduleName;
	private Label moduleDomain;
	private Image moduleImage;
	private TextArea moduleDescription;
	
	@Override
	public void setHandler(ConsultModuleHandler handler) {
		this.handler = handler;
	}

	@Autowired
	public MinimizeModuleComponentImpl(ModuleRepository moduleRepository){
		this.moduleRepository = moduleRepository;
		
		MinimizeModulePresenter = new MinimizeModulePresenter(this, new ModuleServiceImpl(moduleRepository));
		this.setHandler(MinimizeModulePresenter);
		
		moduleName = new Label("");
		moduleImage = new Image();
		moduleImage.setWidth(300, Unit.PIXELS);
		moduleImage.setHeight(400, Unit.PIXELS);
		moduleDomain = new Label("");
		moduleDescription = new TextArea("jgklhfkhdghslqkhfjhlq hgsdfkg hg dsgfkjg kqjdgsfj gjkdsg fjg sdgjk dsqjgfqs j");
		moduleDescription.setReadOnly(true);
		moduleDescription.setWidth(300, Unit.PIXELS);
		Button buttonExpand = new Button("Expand");
		
		addComponents(moduleName, moduleDomain, moduleImage, moduleDescription, buttonExpand);
		setWidth(300, Unit.PIXELS);
		
	}

	@Override
	public void setModule(Module module, Map<String, String> MapElement, List<Parcours> parcours) {
		moduleName.setValue(module.getName());
		moduleDomain.setValue(module.getDomain());
		if (MapElement.containsKey("description"))
			if (MapElement.get("description") != null) {
				moduleDescription.setValue(MapElement.get("description"));
			}
		String file;
		if (MapElement.containsKey("image")) {
			if (MapElement.get("image") != null) {
				System.out.println("file : " + (file = MapElement.get("image")));
				moduleImage.setSource(new FileResource(new File(file)));
			}
		}
	}

	@Override
	public void ErrorService(String error) {
		new Notification(Message.MODULE_NON_SUCCESS_CONSULT, error, com.vaadin.ui.Notification.Type.ERROR_MESSAGE)
				.show(Page.getCurrent());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() != null) {
			// split at "/", add each part as a label
			String[] msgs = event.getParameters().split("/");
			for (String msg : msgs) {
				String[] args = msg.split("=");
				System.out.println("Url split : " + msg);
				if (args[0].equals("module")) {
					String urlTransfert = args[1].replace("_", " ");
					System.out.println("Url transform : " + urlTransfert);
					handler.urlChange(urlTransfert);
				}
			}
		}			
	}
}

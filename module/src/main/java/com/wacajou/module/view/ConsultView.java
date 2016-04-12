package com.wacajou.module.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.entity.Module;
import com.wacajou.module.controller.ConsultModule;


@UIScope
@SpringView(name = ConsultView.VIEW_NAME)
public class ConsultView extends VerticalLayout implements View{
	public static final String VIEW_NAME = "module";
		
	private Label title;
	private Label parcours;
	private Image image;
	private TextArea desc;
	
	@PostConstruct
	public void init(){
		final GridLayout grid = new GridLayout(12, 10);
		
		title = new Label();
		title.setCaptionAsHtml(true);
		grid.addComponent(title, 0, 0, 5, 0 );

		parcours = new Label();
		parcours.setCaptionAsHtml(true);
		grid.addComponent(parcours, 4, 2, 7, 3 );

		image = new Image();
		image.setHeight(400, Unit.PIXELS);
		image.setWidth(200, Unit.PIXELS);
		image.setAlternateText("Image non disponible");
		grid.addComponent(image, 0, 1, 3, 6 );

		desc = new TextArea();
		desc.setReadOnly(true);
		desc.setCaptionAsHtml(true);
		grid.addComponent(desc, 0, 7, 4, 9);

		addComponent(grid);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		String param = event.getParameters();
		ConsultModule module = new ConsultModule(param);
		
		title.setCaption("<h1> Parcours : " + module.module.getModule_name() +"</h1>");
		
		
	}

}

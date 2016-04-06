package com.wacajou;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = DefautView.VIEW_NAME )
public class DefautView extends VerticalLayout implements View{
	public static final String VIEW_NAME = "";
	
	@PostConstruct
	void init(){
		addComponent(new Label("Defaut view"));
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}

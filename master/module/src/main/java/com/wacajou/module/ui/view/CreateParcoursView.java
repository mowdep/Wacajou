package com.wacajou.module.ui.view;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public interface CreateParcoursView extends View {
	public static final String VIEW_NAME = "create";
	
	void setHandler(ParcoursViewHandler handler);
    void init();
    TextField getParcoursName();
    TextField getParcoursIdentifiant();
    ComboBox getParcoursDomain();
    TextArea getParcoursDescription();
    String getParcoursImage();
    List<String> getModuleAdded();
    void afterSuccessfulCreate();
    void afterNonSuccessfulCreate(String error);
  
}

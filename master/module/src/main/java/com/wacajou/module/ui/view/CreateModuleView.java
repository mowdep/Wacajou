package com.wacajou.module.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public interface CreateModuleView extends View {
	public static final String VIEW_NAME = "create";
	void setHandler(ModuleViewHandler handler);
    void init();
    TextField getModuleName();
    TextField getModuleIdentifiant();
    ComboBox getModuleDomain();
    TextArea getModuleDescription();
    String getModuleImage();
    Button getModuleSave();
    void afterSuccessfulCreate();
    void afterNonSuccessfulCreate(String error);
  
}

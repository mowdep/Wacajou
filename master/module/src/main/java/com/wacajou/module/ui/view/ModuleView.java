package com.wacajou.module.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public interface ModuleView extends View {
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

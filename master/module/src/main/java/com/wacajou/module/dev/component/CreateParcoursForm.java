package com.wacajou.module.dev.component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.wacajou.module.current.view.CreateModuleHandler;

public interface CreateParcoursForm extends Component {
	void setHandler(CreateModuleHandler handler);
	void afterSuccessfulCreate();
    void afterNonSuccessfulCreate(String error);
	void enter(ViewChangeEvent viewChangeEvent);
	TextField getModuleName();
    TextField getModuleIdentifiant();
    ComboBox getModuleDomain();
    TextArea getModuleDescription();
    String getModuleImage();
}

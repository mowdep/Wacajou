package com.wacajou.module.ui.view;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;

public interface ConsultModuleView extends View {
	public static final String VIEW_NAME = "consult";
	
	void setHandler(ModuleViewHandler handler);
    void init();
	void setName(Object attribute);
	void ErrorService(String error);
	void setModule(Module module, Map<String, String> MapElement,
			List<Parcours> parcours);
}
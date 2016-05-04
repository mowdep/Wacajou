package com.wacajou.module.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.TextField;

public interface ModuleView extends View {
	void setHandler(ModuleViewHandler handler);
    void init();
    TextField getModuleTxtField();
  /*  TextField getTxtUsername();

    TextField getTxtPassword();
    Button getBtnLogin();

    void afterSuccessfulLogin();*/
}

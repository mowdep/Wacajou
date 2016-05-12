package com.wacajou.module.current.component;

import java.util.List;
import java.util.Map;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.module.current.view.ConsultModuleHandler;
import com.wacajou.module.current.view.CreateModuleHandler;

public interface MinimizeModuleComponent extends Component {

	void enter(ViewChangeEvent viewChangeEvent);
	void ErrorService(String error);
	void setModule(Module module, Map<String, String> MapElement,
			List<Parcours> parcours);

	void setHandler(ConsultModuleHandler handler);
}

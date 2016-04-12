package com.wacajou.component;

import java.util.Vector;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.entity.Module;

public class AddModuleComponent extends CustomComponent {
	public AddModuleComponent(String message, final JPAContainer JPAmodule) {

		Panel panel = new Panel();
		final VerticalLayout panelContent = new VerticalLayout();
		panelContent.setMargin(true);
		// Very useful
		panel.setContent(panelContent);

		// Compose from multiple components
		Label label = new Label(message);

		HorizontalLayout layoutCombo = new HorizontalLayout();
		final ComboBox combo = new ComboBox();

		for (int i = 0; i < JPAmodule.size(); i++) {
			Module module = (Module) JPAmodule.getItem(JPAmodule.getIdByIndex(i)).getEntity();
			combo.addItem(module.getId() + " - " + module.getModule_name());
		}

		Button buttonCombo = new Button("Add");
		buttonCombo.setId("Add_button");
		buttonCombo.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (event.getButton().getId().equals("Add_button")) {
					String value = (String) combo.getValue();
					String[] valueSep = value.split(" - ");
					Label labCombo = new Label(value);
					Module modulrem = JPAmodule.getEntityProvider().getEntityManager().find(Module.class, Integer.parseInt(valueSep[0]));
					panelContent.addComponent(labCombo);
					panelContent.setSizeFull();
					JPAmodule.removeItem(modulrem);
				}
			};
		});
		layoutCombo.addComponent(combo);
		layoutCombo.addComponent(buttonCombo);

		panelContent.addComponent(layoutCombo);
		// The composition root MUST be set
		setCompositionRoot(panel);
	}
}

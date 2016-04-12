package com.wacajou.component;

import java.util.Vector;

import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.entity.Module;

public class AddModuleComponent extends CustomComponent {

	private Vector<String> moduleToAdd = new Vector<String>();

	public Vector<String> getModuleToAdd() {
		return moduleToAdd;
	}
	
	// Composant permettant de créer auttomatiquement la selection des modules
	public AddModuleComponent(String message) {
		EntityManager em = JPAContainerFactory
				.createEntityManagerForPersistenceUnit("isep");
		CachingMutableLocalEntityProvider<Module> entityProvider = new CachingMutableLocalEntityProvider<Module>(
				Module.class, em);
		JPAContainer<Module> JPAmodule = new JPAContainer<Module>(Module.class);
		JPAmodule.setEntityProvider(entityProvider);
		
		final VerticalLayout panelContent = new VerticalLayout();
	
		Label label = new Label(message);
		panelContent.addComponent(label);
		
		HorizontalLayout layoutCombo = new HorizontalLayout();
		final ComboBox combo = new ComboBox();

		for (int i = 0; i < JPAmodule.size(); i++) {
			Module module = (Module) JPAmodule.getItem(
					JPAmodule.getIdByIndex(i)).getEntity();
			combo.addItem(module.getModule_name());
		}

		Button buttonCombo = new Button("Add");
		buttonCombo.setId("Add_button");
		layoutCombo.addComponent(combo);
		layoutCombo.addComponent(buttonCombo);
		
		buttonCombo.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (event.getButton().getId().equals("Add_button")) {
					Object value = combo.getValue();
					if (value != null) {
						final String valueRecup = combo.getItemCaption(value);
						moduleToAdd.add(valueRecup);
						final HorizontalLayout layoutDisplay = new HorizontalLayout();

						final Label labCombo = new Label(valueRecup);
						layoutDisplay.addComponent(labCombo);
						Button del = new Button("Del");
						del.addClickListener(new ClickListener() {
							public void buttonClick(ClickEvent event) {
								combo.addItem(labCombo.getValue());
								layoutDisplay.removeAllComponents();
								panelContent.removeComponent(layoutDisplay);
								moduleToAdd.remove(valueRecup);
							}
						});

						layoutDisplay.addComponent(del);
						layoutDisplay.setSpacing(true);

						panelContent.addComponent(layoutDisplay);
						combo.removeItem(value);
						combo.commit();
					}
				}
			};
		});
		
		panelContent.setSpacing(true);
		layoutCombo.setSpacing(true);
		
		panelContent.addComponent(layoutCombo);
		
		setCompositionRoot(panelContent);
		
	}
}

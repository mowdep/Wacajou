package com.wacajou.core.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CustomComboBoxComponent extends CustomComponent {

	private Vector<String> valueToAdd = new Vector<String>();

	public List<String> getValueToAdd() {
		List<String> valueReturn = new ArrayList<String>();
		for(int i = 0; i < valueToAdd.size(); i++)
			valueReturn.add(valueToAdd.elementAt(i));
		return valueReturn;
	}

	public CustomComboBoxComponent(List<String> valueCombo) {
		final VerticalLayout layout = new VerticalLayout();

		HorizontalLayout layoutComboBox = new HorizontalLayout();

		final ComboBox combo = new ComboBox();

		for (int i = 0; i < valueCombo.size(); i++)
			combo.addItem(valueCombo.get(i));

		Button addButton = new Button("Add");
		addButton.setId("addButton");
		
		layoutComboBox.addComponent(combo);
		layoutComboBox.addComponent(addButton);
		
		
		addButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (event.getButton().getId().equals("addButton")) {
					Object value = combo.getValue();
					if (value != null) {
						final String valueRecup = combo.getItemCaption(value);
						valueToAdd.add(valueRecup);

						final HorizontalLayout layoutDisplay = new HorizontalLayout();

						final Label labValueAdded = new Label(valueRecup);
						layoutDisplay.addComponent(labValueAdded);

						Button delValue = new Button("Delete");
						delValue.addClickListener(new ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								combo.addItem(labValueAdded.getValue());
								layoutDisplay.removeAllComponents();
								layout.removeComponent(layoutDisplay);
								valueToAdd.remove(valueRecup);
							}
						});

						layoutDisplay.addComponent(delValue);
						layoutDisplay.setSpacing(true);

						layout.addComponent(layoutDisplay);
						combo.removeItem(value);
						combo.commit();
					}
				}
			}
		});
		
		layout.setSpacing(true);
		layoutComboBox.setSpacing(true);
		
		layout.addComponent(layoutComboBox);
		setCompositionRoot(layout);
	}
}

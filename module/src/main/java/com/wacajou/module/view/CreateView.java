package com.wacajou.module.view;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.wacajou.module.controller.CreateModule;
import com.wacajou.module.message.LangText;

@UIScope
@SpringView(name = CreateView.VIEW_NAME)
public class CreateView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "create";
	private int valueStatut = 4;
	private String lang = "en";
	private LangText lab;

	@PostConstruct
	void init() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		lang = "fr";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("lang")) {
				// lang = cookie.getValue();
				lang = "fr";
			}
		}
		lab = new LangText(lang);
		setHeight(400, Unit.PIXELS);
		setWidth(400, Unit.PIXELS);
		setMargin(true);
		setSpacing(true);

		final FormLayout form = new FormLayout();
		final TextField id = new TextField();
		id.setRequired(true);
		id.setInputPrompt(lab.LABEL_ID_MODULE);
		id.setDescription(lab.LABEL_ID_MODULE);
		form.addComponent(id);
		
		final TextField name = new TextField();
		name.setRequired(true);
		name.setInputPrompt(lab.LABEL_NAME_MODULE);
		name.setDescription(lab.LABEL_NAME_MODULE);
		form.addComponent(name);

		final TextField id_respo = new TextField();
		id_respo.setInputPrompt(lab.LABEL_ID_RESPO_MODULE);
		id_respo.setDescription(lab.LABEL_ID_RESPO_MODULE);

		form.addComponent(id_respo);

		final TextArea desc = new TextArea();
		desc.setInputPrompt(lab.LABEL_DESC_MODULE);
		desc.setDescription(lab.LABEL_DESC_MODULE);
		form.addComponent(desc);

		final Image img = new Image();

		final Button submit = new Button(lab.BUTTON_SUBMIT_MODULE);
		submit.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				CreateModule nwModule = new CreateModule();
				System.out.println(nwModule.create(id.getValue(), name.getValue()));
			}
		});
		form.addComponent(submit);

		addComponent(form);
	}

	public void enter(ViewChangeEvent event) {

	}
}

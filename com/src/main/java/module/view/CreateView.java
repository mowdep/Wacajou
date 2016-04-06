package module.view;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = CreateView.VIEW_NAME)
public class CreateView extends VerticalLayout implements View {
	public static final String VIEW_NAME = "create";

	@PostConstruct
	void init(){
		final FormLayout form = new FormLayout();
		final TextField id = new TextField("Identifiant du module");
		id.setRequired(true);
		form.addComponent(id);
		
		final TextField name = new TextField("Nom du module");
		name.setRequired(true);
		form.addComponent(name);
		
		final TextField id_respo = new TextField("Identifiant du responsable");
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}

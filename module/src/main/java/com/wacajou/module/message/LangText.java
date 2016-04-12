package com.wacajou.module.message;

public class LangText {
	public String lang = "fr";

	public String LABEL_ID_PARCOURS = "";
	public String LABEL_NAME_PARCOURS = "";
	public String LABEL_ID_RESPO_PARCOURS = "";
	public String LABEL_IMAGE_MODULE = "";
	public String LABEL_DESC_PARCOURS = "";
	public String BUTTON_SUBMIT_PARCOURS = "";
	public String BUTTON_EXPLORE = "";

	public LangText() {

	}

	public LangText(String lang) {
		if (lang.equals("fr")) {

			LABEL_ID_PARCOURS = "Identifiant du module";
			LABEL_NAME_PARCOURS = "Nom du module";
			LABEL_ID_RESPO_PARCOURS = "Id du responsable module";
			LABEL_IMAGE_MODULE = "Image :";
			LABEL_DESC_PARCOURS = "Description";
			BUTTON_SUBMIT_PARCOURS = "Créer";
			BUTTON_EXPLORE = "Parcourir";

		} else if (lang.equals("en")) {

		}
	}
}

package com.wacajou.module.message;

public class LangText {
	public String lang = "fr";

	public String LABEL_ID_MODULE = "";
	public String LABEL_NAME_MODULE = "";
	public String LABEL_ID_RESPO_MODULE = "";
	public String LABEL_IMAGE_MODULE = "";
	public String LABEL_DESC_MODULE = "";
	public String BUTTON_SUBMIT_MODULE = "";
	public String BUTTON_EXPLORE = "";

	public LangText() {

	}

	public LangText(String lang) {
		if (lang.equals("fr")) {

			LABEL_ID_MODULE = "Identifiant du module";
			LABEL_NAME_MODULE = "Nom du module";
			LABEL_ID_RESPO_MODULE = "Id du responsable module";
			LABEL_IMAGE_MODULE = "Image :";
			LABEL_DESC_MODULE = "Description";
			BUTTON_SUBMIT_MODULE = "Créer";
			BUTTON_EXPLORE = "Parcourir";

		} else if (lang.equals("en")) {

		}
	}
}

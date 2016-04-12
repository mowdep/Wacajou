package com.wacajou.module.message;

public class Erreur {
	public String lang = "fr";
	public String MESSAGE_MODULE_PRESENT;
	public String MESSAGE_MODULE_VALIDATION;
	public String MESSAGE_XML_CREATE;
	public String MESSAGE_MODULE_SQL;
	public String MESSAGE_XML_MODIF;
	public String MESSAGE_PARCOURS_PRESENT;
	public String MESSAGE_PARCOURS_SQL;
	
	public Erreur(){
		
	}
	public Erreur(String lang){
		if(lang.equals("fr")){
			MESSAGE_MODULE_PRESENT = "Module déjà existant.";
			MESSAGE_MODULE_VALIDATION = "Les valeurs rentrées ne correspondent pas aux données demandées."; 
			MESSAGE_MODULE_SQL = "Erreur lors de la création du module";
			MESSAGE_XML_MODIF = "Erreur lors de la création d'un attribut";
			MESSAGE_XML_CREATE = "Erreur lors de la création du module";

		}else if(lang.equals("en")){
			
		}
	}
	

}

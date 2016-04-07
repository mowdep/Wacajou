package com.wacajou.module.message;

public class Sucess {
	public String lang = "fr";
	public String MESSAGE_MODULE_CREE = "";
	
	public Sucess(){
		
	}
	public Sucess(String lang){
		if(lang.equals("fr")){
			MESSAGE_MODULE_CREE = "Le module à bien été crée";
		}else if(lang.equals("en")){
			
		}
		
	}
}

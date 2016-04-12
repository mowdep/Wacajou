package com.wacajou.module.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.wacajou.XML.CreateXMLFile;
import com.wacajou.module.message.Erreur;
import com.wacajou.module.message.Sucess;
import com.wacajou.module.model.DAOModule;

public class CreateModule {

	// Message à retourner à la vue suivant le sucess ou l'echec de la creation
	// du module
	public String sucess = null;
	public String erreur = null;

	// Variable pour le stockage du fichier XML
	private String PATH = "C:\\Users\\ISEP\\Desktop\\tmp";
	private String filename;

	// Attribut du module à ajouter
	private int id;
	private int id_respo;
	private String name;
	private String desc;

	// Récupération des variables de langue
	Erreur err;
	Sucess suc;

	public CreateModule(String lang, String[] cmd) {
		err = new Erreur(lang);
		suc = new Sucess(lang);
		if (ValidationId(cmd[0]) && ValidationName(cmd[1])) {
			ValidationIdRespo(cmd[2]);
			ValidationDesc(cmd[3]);
			create();
		} else {
			erreur = err.MESSAGE_MODULE_VALIDATION;
		}
	}

	// Creation d'un module
	public void create() {

		DAOModule daoModule = new DAOModule();

		// Cherche si le module existe ou non
		try {
			if ( (!daoModule.moduleExist(id)) && (!daoModule.moduleExist(name)) ) {
				filename = id + "_" + name + "_V" + 0;

				// Creation d'un nouveau module dans la base de données
				Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
				String dateFormat = formater.format(date);
				daoModule.insert(id, name, "\\module\\" + filename, id_respo);

				// Creation d'un nouveau fichier XML de version 0
				CreateXMLFile xmlModule = new CreateXMLFile();
				try {
					xmlModule.createFile(PATH, filename);
					xmlModule.setElement("module");
					xmlModule.setAttribute("id", "" + id);
					xmlModule.setAttribute("name", name);
					xmlModule.setAttribute("creation_date", dateFormat);
					xmlModule.setAttribute("version", "" + 0);
					xmlModule.setAttribute("description", "" + desc);
					xmlModule.setAttribute("id_respo", "" + "" + id_respo);
					xmlModule.saveFile();
					
					sucess = suc.MESSAGE_MODULE_CREE;
				} catch (ParserConfigurationException | TransformerException e) {
					daoModule.delete(id);
					erreur = err.MESSAGE_MODULE_XML_CREATE;
				}
			} else {
				erreur = err.MESSAGE_MODULE_PRESENT;
			}
		} catch (SQLException e) {
			erreur = err.MESSAGE_MODULE_SQL;
		}

	}

	private boolean ValidationId(String id) {
		try {
			this.id = Integer.parseInt(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean ValidationName(String name) {
		if (!name.equals("")) {
			this.name = name;
			return true;
		}
		return false;
	}

	private boolean ValidationDesc(String desciption) {
		try {
			if (!desc.equals("")) {
				this.desc = desciption;
				return true;
			}
			this.desc = null;
			return false;
		} catch (NullPointerException e) {
			this.desc = null;
			return false;
		}
	}

	private boolean ValidationIdRespo(String id_respo) {
		try {
			this.id_respo = Integer.parseInt(id_respo);
			return true;
		} catch (Exception e) {
			this.id_respo = 0;
			return false;
		}
	}

}
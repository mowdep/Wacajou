package com.wacajou.module.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import com.wacajou.XML.CreateXMLFile;
import com.wacajou.module.message.Erreur;
import com.wacajou.module.message.Sucess;
import com.wacajou.module.model.DAOModule;

public class CreateModule {

	private int ID;
	private String NAME;
	private String PATH;
	Erreur err = new Erreur("fr");
	Sucess suc = new Sucess("fr");

	public CreateModule() {

	}

	// Creation d'un module
	public String create(String id, String name) {
		if (ValidationId(id)) {
			if (ValidationName(name)) {
				DAOModule daoModule = new DAOModule();

				// Cherche si le module existe ou non
				try {
					if (!daoModule.moduleExist(ID)) {

						// Creation d'un nouveau module dans la base de données
						Date date = new Date();
						SimpleDateFormat formater = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
						String dateFormat = formater.format(date);
						daoModule.insert(ID, NAME, PATH + ID + "_" + NAME + "_V" + 0);

						// Creation d'un nouveau fichier XML de version 0
						CreateXMLFile xmlModule = new CreateXMLFile();
						try {
							xmlModule.createFile(PATH, ID + "_" + NAME + "_V" + 0);
							xmlModule.setElement("module");
							xmlModule.setAttribute("id", "" + ID);
							xmlModule.setAttribute("name", NAME);
							xmlModule.setAttribute("creation_date", dateFormat);
							xmlModule.setAttribute("version", "" + 0);
							return suc.MESSAGE_MODULE_CREE;
						} catch (ParserConfigurationException e) {
							daoModule.delete(ID);
							return err.MESSAGE_MODULE_XML_CREATE;
						}
					} else {
						return err.MESSAGE_MODULE_PRESENT;
					}
				} catch (SQLException e) {
					return err.MESSAGE_MODULE_SQL;
				}
			} else {
				return err.MESSAGE_MODULE_VALIDATION;
			}
		} else {
			return err.MESSAGE_MODULE_VALIDATION;
		}
	}

	private boolean ValidationId(String id) {
		try {
			this.ID = Integer.parseInt(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean ValidationName(String name) {
		if (!name.equals("")) {
			this.NAME = name;
			return true;
		}
		return false;
	}
}

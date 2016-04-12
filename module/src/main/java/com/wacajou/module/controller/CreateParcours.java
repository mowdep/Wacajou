package com.wacajou.module.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.wacajou.XML.CreateXMLFile;
import com.wacajou.entity.Module;
import com.wacajou.entity.Parcours;
import com.wacajou.module.message.Erreur;
import com.wacajou.module.message.Sucess;
import com.wacajou.module.model.DAOModule;
import com.wacajou.module.model.DAOParcours;
import com.wacajou.module.model.DAOParcoursModule;

public class CreateParcours {

	// Message à retourner à la vue suivant le sucess ou l'echec de la creation
	// du module
	public String sucess = null;
	public String erreur = null;

	// Variable pour le stockage du fichier XML
	private String PATH = "B:\\tmp\\parcours";
	private String filename;

	// Attribut du module à ajouter
	private int id_respo;
	private String name;
	private String desc;

	// Récupération des variables de langue
	Erreur err;
	Sucess suc;

	// Parcours crée
	private Parcours parcours;

	// Fichier XML
	private CreateXMLFile xmlParcours;

	public CreateParcours(String lang, String[] cmd, Vector<String> module,
			Vector<String> moduleOptional) {
		err = new Erreur(lang);
		suc = new Sucess(lang);
		if (ValidationName(cmd[0])) {
			ValidationIdRespo(cmd[1]);
			ValidationDesc(cmd[2]);
			create();
			// Une fois le parcours crée, on y ajoute les différents modules si
			// ils sont sélectionnés
			if (erreur == null) {
				if ((module.size() != 0) || (moduleOptional.size() != 0)) {
					addModuleParcours(module, moduleOptional);

					try {
						xmlParcours.saveFile();
					} catch (TransformerException e) {
						erreur = err.MESSAGE_XML_CREATE;
					}
				}
			}
		} else {
			erreur = err.MESSAGE_MODULE_VALIDATION;
		}
	}

	private void addModuleParcours(Vector<String> modules,
			Vector<String> moduleOptional) {

		// Recupération des models pour accéder aux données
		DAOModule daoModule = new DAOModule();
		DAOParcoursModule daoParcoursModule = new DAOParcoursModule();

		// Création de liste de modules
		List<Module> listModule = new ArrayList<Module>();
		List<Module> listModuleOptional = new ArrayList<Module>();

		// Création de la chaîne de caractéres du XML
		String moduleAdded = "|";
		String moduleOptionalAdded = "|";

		for (int i = 0; i < modules.size(); i++) {
			try {
				listModule.add(daoModule.getModuleByName(modules.get(i)
						.toString()));
			} catch (SQLException e) {
				erreur = err.MESSAGE_PARCOURS_SQL;
			}
		}

		for (int i = 0; i < moduleOptional.size(); i++) {
			try {
				listModuleOptional.add(daoModule.getModuleByName(moduleOptional
						.get(i).toString()));
			} catch (SQLException e) {
				erreur = err.MESSAGE_PARCOURS_SQL;
			}
		}
		if (erreur == null) {

			for (int i = 0; i < listModule.size(); i++) {
				daoParcoursModule.insert(parcours.getId(), listModule.get(i)
						.getId(), false);
				moduleAdded = moduleAdded + modules.get(i) + "|";
			}
			for (int i = 0; i < listModuleOptional.size(); i++) {
				daoParcoursModule.insert(parcours.getId(), listModuleOptional
						.get(i).getId(), true);
				moduleOptionalAdded = moduleOptionalAdded
						+ moduleOptional.get(i) + "|";
			}

			xmlParcours.setAttribute("module", moduleAdded);
			xmlParcours.setAttribute("moduleOptional", moduleOptionalAdded);
		}
	}

	// Creation d'un module
	private void create() {
		DAOParcours daoParcours = new DAOParcours();

		// Cherche si le module existe ou non
		try {
			if (!daoParcours.parcoursExist(name)) {
				filename = name + "_V" + 0;

				// Creation d'un nouveau module dans la base de données
				Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat(
						"yy_MM_dd_hh_mm_ss");
				String dateFormat = formater.format(date);
				daoParcours.insert(name, "\\parcours\\" + filename, id_respo);
				parcours = daoParcours.getParcoursByName(name);

				// Creation d'un nouveau fichier XML de version 0
				xmlParcours = new CreateXMLFile();
				try {
					xmlParcours.createFile(PATH, filename);
					xmlParcours.setElement("parcours");
					xmlParcours.setAttribute("name", name);
					xmlParcours.setAttribute("creation_date", dateFormat);
					xmlParcours.setAttribute("version", "" + 0);
					xmlParcours.setAttribute("description", "" + desc);
					xmlParcours.setAttribute("id_respo", "" + "" + id_respo);

					sucess = suc.MESSAGE_PARCOURS_CREE;

				} catch (ParserConfigurationException e) {
					erreur = err.MESSAGE_XML_CREATE;
				}
			} else {
				erreur = err.MESSAGE_PARCOURS_PRESENT;
			}
		} catch (SQLException e) {
			erreur = err.MESSAGE_PARCOURS_SQL;
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
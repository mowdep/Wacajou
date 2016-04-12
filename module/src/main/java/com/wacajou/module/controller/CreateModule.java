package com.wacajou.module.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.wacajou.FileIO.MoveFile;
import com.wacajou.XML.CreateXMLFile;
import com.wacajou.module.message.Erreur;
import com.wacajou.module.message.Sucess;
import com.wacajou.module.model.DAOModule;

import static java.nio.file.StandardCopyOption.*;

public class CreateModule {

	// Message à retourner à la vue suivant le sucess ou l'echec de la creation
	// du module
	public String sucess = null;
	public String erreur = null;

	// Variable pour le stockage du fichier XML
	private String PATH = "B:\\tmp\\module";
	private String filename;

	// Attribut du module à ajouter
	private int id_respo;
	private String name;
	private String desc;
	private String target;

	// Récupération des variables de langue
	Erreur err;
	Sucess suc;

	public CreateModule(String lang, String[] cmd) {
		err = new Erreur(lang);
		suc = new Sucess(lang);
		System.out.println("Name : " + cmd[0] + ", IdRespo : " + cmd[2]
				+ ", Img path : " + cmd[3]);
		if (ValidationName(cmd[0]) && ValidationIdRespo(cmd[2])
				&& ValidationImg(cmd[3])) {
			ValidationDesc(cmd[1]);
			create();
		} else {
			erreur = err.MESSAGE_MODULE_VALIDATION;
		}
	}

	// Creation d'un module
	private void create() {

		DAOModule daoModule = new DAOModule();

		// Cherche si le module existe ou non
		try {
			if (!daoModule.moduleExist(name)) {
				filename = name + "_V" + 0;

				// Creation d'un nouveau module dans la base de données
				Date date = new Date();
				SimpleDateFormat formater = new SimpleDateFormat(
						"yy_MM_dd_hh_mm_ss");
				String dateFormat = formater.format(date);
				daoModule.insert(name, "\\module\\" + filename, id_respo);

				// Creation d'un nouveau fichier XML de version 0
				CreateXMLFile xmlModule = new CreateXMLFile();
				try {
					xmlModule.createFile(PATH, filename);
					xmlModule.setElement("module");
					xmlModule.setAttribute("name", name);
					xmlModule.setAttribute("creation_date", dateFormat);
					xmlModule.setAttribute("version", "" + 0);
					xmlModule.setAttribute("description", "" + desc);
					xmlModule.setAttribute("image", target);
					xmlModule.setAttribute("id_respo", "" + "" + id_respo);
					xmlModule.saveFile();

					sucess = suc.MESSAGE_MODULE_CREE;
				} catch (ParserConfigurationException | TransformerException e) {
					erreur = err.MESSAGE_XML_CREATE;
				}
			} else {
				erreur = err.MESSAGE_MODULE_PRESENT;
			}
		} catch (SQLException e) {
			erreur = err.MESSAGE_MODULE_SQL;
		}

	}

	private boolean ValidationName(String name) {
		if (!name.equals("")) {
			this.name = name;
			return true;
		}
		return false;
	}

	private void ValidationDesc(String description) {
		if (!description.equals("")) {
			this.desc = description;
		}
		this.desc = null;
	}

	private boolean ValidationIdRespo(String id_respo) {
		if (id_respo.equals("")) {
			this.id_respo = 0;
			return true;
		} else {
			try {
				this.id_respo = Integer.parseInt(id_respo);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	private boolean ValidationImg(String source) {
		if (!source.equals("")) {
			try {
				String extension = "";
				for (int i = 0; i < source.length(); i++) {
					if (source.charAt(i) == '.') {
						extension = source.substring(i);
					}
				}
				System.out.println(source);
				System.out.println(extension);
				target = PATH + "\\" + name + extension;
				MoveFile file = new MoveFile();
				boolean result = file.moveFile(source, target);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else
			return true;

	}
}
package com.wacajou.module.model;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.vaadin.server.ServiceException;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.repository.ModuleRepository;
import com.wacajou.core.file.io.MoveFile;
import com.wacajou.core.file.xml.Create;

@Component("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	Environment env;

	private Create XMLFile;
	private String error = null;

	private final ModuleRepository moduleRepository;

	@Autowired
	public ModuleServiceImpl(ModuleRepository moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	@Override
	public void Create(String name, String identifiant, String image, String description, String domain)
			throws ServiceException {
		// TODO Auto-generated method stub

		// Vérifiaction valeur non null

		Assert.notNull(name);
		Assert.notNull(identifiant);
		
		if (description == null)
			description = "";
		if (image == null)
			image = "";
		if (domain == null)
			domain = "";
		String moduleName = identifiant + "-" + name;

		String path = System.getProperty("application.PATH") + "Wacajou\\module";
		System.out.println("Path : " + path + "  moduleName : " + moduleName);
		try {
			XMLFile = new Create();
			XMLFile.createFile(path, moduleName + "_V0");
			XMLFile.setElement("module");
			XMLFile.setAttribute("name", name);
			XMLFile.setAttribute("id", identifiant);
			XMLFile.setAttribute("description", description);
			XMLFile.setAttribute("domaine", domain);

			try {
				String extension = "";
				for (int i = 0; i < image.length(); i++) {
					if (image.charAt(i) == '.') {
						extension = image.substring(i);
					}
				}
				System.out.println(image);
				System.out.println(extension);
				MoveFile file = new MoveFile();
				boolean result = file.moveFile(image , path + moduleName + extension);
				image = path + moduleName + extension;
				XMLFile.setAttribute("image", image);
				XMLFile.saveFile();
			}catch (TransformerException e) {
				e.printStackTrace();
				error = "Cannot create XML File on path " + path;
				throw new ServiceException(error);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			error = "Cannot create XML File on path " + path;
			throw new ServiceException(error);
		} 

		Module module = new Module(moduleName, path + moduleName + "_V0");
		try {
			moduleRepository.save(module);
		} catch (Exception e) {
			e.printStackTrace();
			error = "Duplicate module";
			throw new ServiceException("Duplicate module");
		}

	}

	public String getError() {
		return error;
	}
}

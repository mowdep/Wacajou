package com.wacajou.module.model;

import com.vaadin.server.ServiceException;

public interface ModuleService {
	void Create(String name, String identifiant, String image, String description, String domain ) throws ServiceException;
}

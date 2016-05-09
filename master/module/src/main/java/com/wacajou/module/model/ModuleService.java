package com.wacajou.module.model;

import com.vaadin.server.ServiceException;
import com.wacajou.data.jpa.domain.Module;

public interface ModuleService {
	void Create(String name, String domain) throws ServiceException;
	Module Consult(String moduleName) throws ServiceException;
	void Update(Module module) throws ServiceException;
}

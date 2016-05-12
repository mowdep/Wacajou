package com.wacajou.data.jpa.service;

import java.util.List;

import com.vaadin.server.ServiceException;
import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;

public interface ParcoursService {
	Parcours Consult(String parcoursName) throws ServiceException;
	void Update(Parcours parcours) throws ServiceException;
	Parcours Create(String name) throws ServiceException;
	void setParcoursModule(Parcours parcours, Module module, boolean optional) throws ServiceException;
}
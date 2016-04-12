package com.wacajou.module.model;

import java.util.List;

import javax.persistence.EntityManager;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.wacajou.entity.Parcours;
import com.wacajou.entity.ParcoursModule;

public class DAOParcoursModule {

	private EntityManager em;

	public DAOParcoursModule() {
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit("isep");
	}
	
	
	public List<Parcours> getByModuleId(int id) {
		// TODO Auto-generated method stub
		em.createNamedQuery("ByModuleId", ParcoursModule.class);
		
		return null;
	}

}

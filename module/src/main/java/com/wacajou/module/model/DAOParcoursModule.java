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


	public void insert(int id_parcours, int id_module, boolean option) {
		ParcoursModule parcoursmodule = new ParcoursModule();
		parcoursmodule.setId_parcours(id_parcours);
		parcoursmodule.setId_module(id_module);
		parcoursmodule.setOptional(option);
		em.getTransaction().begin();
		em.persist(parcoursmodule);
		em.getTransaction().commit();
	}

}

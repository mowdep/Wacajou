package com.wacajou.module.model;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.wacajou.entity.Module;

public class DAOModule {
	
	private EntityManager em;
	public JPAContainer<Module> JPAModule;
	
	public DAOModule() {
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit("isep");
		CachingMutableLocalEntityProvider<Module> entityProvider = new CachingMutableLocalEntityProvider<Module>(
				Module.class, em);
		JPAModule = new JPAContainer<Module>(Module.class);
		JPAModule.setEntityProvider(entityProvider);
	}

	public Module getModuleById(int id) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE ID = " + id, Module.class);
		Module result = (Module) query.getSingleResult();
		return result;
	}

	public Module getModuleByName(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE MODULE_NAME = " + name, Module.class);
		Module result = (Module) query.getSingleResult();
		return result;
	}

	public boolean moduleExist(int id) throws SQLException {
		Query query = em.createNativeQuery("SELECT id FROM MODULE WHERE ID = " + id, Module.class);

		if (query.getMaxResults() != 0)
			return false;
		return true;
	}

	public boolean moduleExist(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT id FROM MODULE WHERE MODULE_NAME = " + name, Module.class);

		if (query.getMaxResults() != 0)
			return false;
		return true;
	}

	public void insert(Module nwModule) throws SQLException {
		em.getTransaction().begin();
		em.persist(nwModule);
		em.getTransaction().commit();
		JPAModule.refresh();
	}

	public void delete(int id) throws SQLException {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM MODULE WHERE ID = " + id, Module.class);
		em.getTransaction().commit();
		JPAModule.refresh();
	}

	public void insert(int id, String name, String path) {
		Module nwModule = new Module();
		nwModule.create(id, name, path);
		em.getTransaction().begin();
		em.persist(nwModule);
		em.getTransaction().commit();
		JPAModule.refresh();		
	}

}

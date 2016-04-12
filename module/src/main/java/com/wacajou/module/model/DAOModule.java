package com.wacajou.module.model;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE IDMODULE = " + id, Module.class);
		Module result = (Module) query.getSingleResult();
		return result;
	}

	public Module getModuleByName(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE MODULENAME LIKE \'" + name + "\'", Module.class);
		Module result = (Module) query.getSingleResult();
		return result;
	}

	public boolean moduleExist(int id) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE EXISTS ( SELECT IDMODULE FROM MODULE WHERE IDMODULE = " + id + ")", Module.class);
		try {
			if (query.getResultList().get(0) != null)
				return true;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean moduleExist(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM MODULE WHERE EXISTS ( SELECT MODULENAME FROM MODULE WHERE MODULENAME LIKE \'" + name + "\')", Module.class);
		try {
			if (query.getResultList().get(0) != null)
				return true;
		} catch (Exception e) {
			return false;
		}
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
		em.createNativeQuery("DELETE FROM MODULE WHERE IDMODULE = " + id, Module.class);
		em.getTransaction().commit();
		JPAModule.refresh();
	}

	public void insert(String name, String path, int id_respo) {
		Module nwModule = new Module();
		nwModule.create(name, path);
		if (id_respo != 0)
			nwModule.setId_respo(id_respo);
		em.getTransaction().begin();
		em.persist(nwModule);
		em.getTransaction().commit();
		JPAModule.refresh();		
	}

}

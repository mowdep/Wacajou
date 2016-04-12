package com.wacajou.module.model;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;
import com.wacajou.entity.Parcours;

public class DAOParcours {
	private EntityManager em;
	
	public JPAContainer<Parcours> JPAPARCOURS;

	public DAOParcours() {
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit("isep");
		CachingMutableLocalEntityProvider<Parcours> entityProvider = new CachingMutableLocalEntityProvider<Parcours>(
				Parcours.class, em);
		JPAPARCOURS = new JPAContainer<Parcours>(Parcours.class);
		JPAPARCOURS.setEntityProvider(entityProvider);
	}

	public Parcours getParcoursById(int id) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM PARCOURS WHERE IDPARCOURS = " + id, Parcours.class);
		Parcours result = (Parcours) query.getSingleResult();
		return result;
	}

	public Parcours getParcoursByName(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM PARCOURS WHERE PARCOURSNAME LIKE \'" + name + "\'", Parcours.class);
		Parcours result = (Parcours) query.getSingleResult();
		return result;
	}

	public boolean parcoursExist(int id) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM PARCOURS WHERE EXISTS ( SELECT IDPARCOURS FROM PARCOURS WHERE IDPARCOURS = " + id + ")", Parcours.class);
		try {
			if (query.getResultList().get(0) != null)
				return true;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean parcoursExist(String name) throws SQLException {
		Query query = em.createNativeQuery("SELECT * FROM PARCOURS WHERE EXISTS ( SELECT PARCOURSNAME FROM PARCOURS WHERE PARCOURSNAME LIKE \'" + name + "\')", Parcours.class);
		try {
			if (query.getResultList().get(0) != null)
				return true;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void insert(Parcours nwParcours) throws SQLException {
		em.getTransaction().begin();
		em.persist(nwParcours);
		em.getTransaction().commit();
		JPAPARCOURS.refresh();
	}

	public void delete(int id) throws SQLException {
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM PARCOURS WHERE IDPARCOURS = " + id, Parcours.class);
		em.getTransaction().commit();
		JPAPARCOURS.refresh();
	}

	public void insert(String name, String path, int id_respo) {
		Parcours nwParcours = new Parcours();
		nwParcours.create(name, path);
		if (id_respo != 0)
			nwParcours.setIdRespo(id_respo);
		em.getTransaction().begin();
		em.persist(nwParcours);
		em.getTransaction().commit();
		JPAPARCOURS.refresh();		
	}

}

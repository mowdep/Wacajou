package com.wacajou.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
//@NamedQuery(name = "ByModuleId", query = "SELECT pm FROM ParcoursModule WHERE c.idModule = :idModule ")

public class ParcoursModule {

	@Id
	@GeneratedValue
	private Long id;
	private int idParcours;
	private int idModule;
	private boolean optional;

	public int getId_parcours() {
		return idParcours;
	}

	public void setId_parcours(int id_parcours) {
		this.idParcours = id_parcours;
	}

	public int getId_module() {
		return idModule;
	}

	public void setId_module(int id_module) {
		this.idModule = id_module;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

}

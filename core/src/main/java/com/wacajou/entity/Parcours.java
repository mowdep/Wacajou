package com.wacajou.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parcours {

	@Id
	@GeneratedValue
	private int idParcours;
	private String parcoursName;
	private String path;
	private int idRespo;
	
	public void create(String name, String path) {
		this.parcoursName = name;
		this.path = path;
	}
	
	public int getId() {
		return idParcours;
	}

	public void setId(int idParcours) {
		this.idParcours = idParcours;
	}

	public String getName() {
		return parcoursName;
	}

	public void setName(String name) {
		this.parcoursName = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getIdRespo() {
		return idRespo;
	}

	public void setIdRespo(int idRespo) {
		this.idRespo = idRespo;
	}

}

package com.wacajou.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parcours {

	@Id
	private int idParcours;
	private String name;
	private String path;
	private int idRespo;

	public int getId() {
		return idParcours;
	}

	public void setId(int idParcours) {
		this.idParcours = idParcours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

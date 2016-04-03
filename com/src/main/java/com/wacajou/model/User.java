package com.wacajou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String surname;
	private String mail;
	private String infos;
	private int id_parcours;
	
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getInfos() {
		return infos;
	}
	public void setInfos(String infos) {
		this.infos = infos;
	}
	public int getId_parcours() {
		return id_parcours;
	}
	public void setId_parcours(int id_parcours) {
		this.id_parcours = id_parcours;
	}
	public void inscrire(String surname, String name, String mail) {
		this.surname = surname;
		this.name = name;
		this.mail = mail;		
	}
}

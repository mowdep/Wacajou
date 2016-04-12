package com.wacajou.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;

@SpringComponent
@VaadinSessionScope
@Entity
public class Module {
	@Id
	@GeneratedValue
	private int idModule;

	private int idRespo;

	private String path;

	private String moduleName;

	public Module() {
	}

	public Module(String module_name, String path) {
		this.path = path;
		this.moduleName = module_name;
	}

	public void create(String name, String path) {
		this.moduleName = name;
		this.path = path;
	}

	public int getId() {
		return idModule;
	}

	public void setId(int id) {
		this.idModule = id;
	}

	public int getId_respo() {
		return idRespo;
	}

	public void setId_respo(int id_respo) {
		this.idRespo = id_respo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getModule_name() {
		return moduleName;
	}

	public void setModule_name(String module_name) {
		this.moduleName = module_name;
	}

	public String toString() {
		return String.format("Module[id=%d, moduleName='%s', path='%s']",
				idModule, moduleName, path);
	}

}

package com.wacajou.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Module {
	@Id
	@GeneratedValue
	private Long id;
	
	private int idRespo;
	
	private String path;
	
	private String moduleName;

	public Module() {
	}
	
	public Module(String module_name, String path) {
		this.path = path;
		this.moduleName = module_name;
	}

	public void create(long id, String module_name, String path) {
		this.id = id;
		this.moduleName = module_name;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return String.format("Module[id=%d, moduleName='%s', path='%s']", id,
				moduleName, path);
	}
}

package com.wacajou.data.jpa.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<ParcoursModule> parcoursModule;

	@Column(name = "moduleName")
	private String name;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = true)
	private User respo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<UserModule> userModule;
	
	private String path;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<Comments> comments;

	protected Module() {

	}

	public Module(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public int getId() {
		return this.id;
	}

	public String getPath() {
		return this.path;
	}
	
	public String getName(){
		return this.name;
	}
	
	public User getRespo() {
		return this.respo;
	}
	public Set<Comments> getComms(){
		return this.comments;
	}
}

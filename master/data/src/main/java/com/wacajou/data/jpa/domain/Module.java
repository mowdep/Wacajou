package com.wacajou.data.jpa.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

@Entity
public class Module extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<ParcoursModule> parcoursModule;

	@Column(name = "moduleName", unique = true)
	private String name;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = true)
	private User respo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<UserModule> userModule;
	
	@Column(name = "path", unique = true)
	private String path;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	private Set<Comments> comments;

	@Column(name = "domain")
	private String domain;
	
	protected Module() {

	}

	public Module(String name, String path, String domain) {
		this.name = name;
		this.path = path;
		this.domain = domain;
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
	
	public String getDomain() {
		return this.domain;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

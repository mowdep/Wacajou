package com.wacajou.core.data.jpa.domain.appli;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Parcours implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcours")
	private Set<ParcoursModule> parcoursModule;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcours")
	private Set<UserParcours> userParcours;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = true)
	private User respo;
	
	private String name;
	
	private String path;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcours")
	private Set<Comments> comments;

	protected Parcours(){
		
	}
	
	public Parcours(String name, String path){
		this.name = name;
		this.path = path;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public User getRespo(){
		return this.respo;
	}
	
	public Set<Comments> getComms(){
		return this.comments;
	}
}

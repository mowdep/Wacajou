package com.wacajou.core.data.jpa.domain.appli;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@Column(nullable = true)
	private String fname;
	@Column(nullable = true)
	private String lname;
	@Column(nullable = true)
	private String promo;
	@Column(nullable = false)
	private Statut statut;
	@Column(nullable = true)
	private String mail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserModule> userModule;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private UserParcours userParcours;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Comments> comments;
	
	protected User(){
		
	}
	
	public User(int id, Statut statut){
		this.id = id;
		this.statut = statut;
	}
	
	public void Create(int id, Statut statut){
		this.id = id;
		this.statut = statut;
	}
	
	public void Create(int id, String promo, Statut statut){
		this.id = id;
		this.statut = statut;
		this.promo = promo;
	}
	
	public void Complete(String fname, String lname, String mail){
		this.fname = fname;
		this.lname = lname;
		this.mail = mail;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getFname(){
		return this.fname;
	}
	
	public String getLname(){
		return this.lname;
	}
	
	public Statut getStatut(){
		return this.statut;
	}
	
	public String getMail(){
		return this.mail;
	}
	
	public Set<Comments> getComms(){
		return this.comments;
	}
	
	@Override
	public String toString(){
		return getId() + ","  + getFname() + "," + getLname() + "," + getMail() + "," + getStatut();
	}
}

package com.wacajou.core.data.jpa.domain.appli;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserModule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User user;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Module.class)
	private Module module;
	
	protected UserModule(){
		
	}
	
	public UserModule(User user, Module module){
		this.user = user;
		this.module = module;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public Module getModule(){
		return this.module;
	}

}

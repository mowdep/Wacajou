package com.wacajou.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParcoursModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Module.class)
	private Module module;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Parcours.class)
	private Parcours parcours;

	@Column(nullable = true)
	private boolean optional;

	protected ParcoursModule(){
		
	}
	
	public ParcoursModule(Parcours parcours, Module module, boolean optional){
		this.parcours = parcours;
		this.module = module;
		this.optional = optional;
	}
	
	public Long getId() {
		return this.id;
	}

	public Module getModule() {
		return this.module;
	}

	public Parcours getParcours() {
		return this.parcours;
	}

	public boolean getOption() {
		return this.optional;
	}
}

/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wacajou.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;

@Entity
public class Comments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(optional = true, fetch = FetchType.LAZY, targetEntity = Module.class)
	private Module module;

	@OneToOne(optional = true, fetch = FetchType.LAZY, targetEntity = Parcours.class)
	private Parcours parcours;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private User user;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Rating rating;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true, length = 5000)
	private String details;

	protected Comments() {
	}

	public Comments(Module module, User user, String title, Rating rating, String message) {
		Assert.notNull(module, "Hotel must not be null");
		Assert.notNull(details, "Details must not be null");
		this.module = module;
		this.rating = rating;
		this.title = title;
		this.details = message;
		this.user = user;
	}
	
	public Comments(Parcours parcours, User user, String title, Rating rating, String message) {
		Assert.notNull(module, "Hotel must not be null");
		Assert.notNull(details, "Details must not be null");
		this.parcours = parcours;
		this.rating = rating;
		this.title = title;
		this.details = message;
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}
	
	public Module getModule() {
		return this.module;
	}

	public Parcours getParcours() {
		return this.parcours;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

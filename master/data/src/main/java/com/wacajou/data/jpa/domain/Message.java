package com.wacajou.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = true)
	private User userReciver;
	@OneToOne(fetch = FetchType.LAZY, targetEntity = User.class, optional = true)
	private User userSender;
	
	private String message;
	private String title;
	private String subject;
	private String logdate;
	
	protected Message(){
		
	}
	
	public Message(User sender, User reciver, String title, String subject, String message){
		this.userSender = sender;
		this.userReciver = reciver;
		this.title = title;
		this.subject = subject;
		this.message = message;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String getLogdate(){
		return this.logdate;
	}
	
	@Override
	public String toString(){
		return getTitle() + "," + getSubject() + "," + getMessage();
	}
}

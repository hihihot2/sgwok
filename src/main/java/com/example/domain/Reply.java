package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reply {
	@Id @GeneratedValue
	@Column(nullable = false, unique = true)
	private long id;
	@ManyToOne
	private Question question;
	@ManyToOne
	private User user;
	@Column(length=200)
	private String repcontents;
	
	public Reply(){super();	}

	public Reply(long id, Question question, User user, String repcontents) {
		super();
		this.id = id;
		this.question = question;
		this.user = user;
		this.repcontents = repcontents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRep_contents() {
		return repcontents;
	}

	public void setRep_contents(String rep_contents) {
		this.repcontents = rep_contents;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", question=" + question + ", user=" + user + ", rep_contents=" + repcontents + "]";
	}
}

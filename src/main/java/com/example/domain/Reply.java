package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.utils.DateTimeUtils;

@Entity
public class Reply {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_question"))
	private Question question;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_reply_writer"))
	private User user;
	
	@Lob
	private String repcontents;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	public Reply(){super();	}

	public Reply(User user, Question question, String repcontents) {
		super();
		this.question = question;
		this.user = user;
		this.repcontents = repcontents;
		this.createDate = new Date();
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
	
	public String getFormattedCreateDate() {
		return DateTimeUtils.format(createDate, "yyyy.MM.dd HH:mm:ss");
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", question=" + question + ", user=" + user + ", rep_contents=" + repcontents + "]";
	}
}

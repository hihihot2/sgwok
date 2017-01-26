package com.example.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {
	@Id @GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Lob
	private String contents;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Reply> answers;
	
	public Question() {super();}
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@JsonIgnore
	public List<Reply> getAnswers() {
		return answers;
	}

	public String getFormattedCreateDate() {
		return DateTimeUtils.format(createDate, "yyyy.MM.dd HH:mm:ss");
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
}

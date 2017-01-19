package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id @GeneratedValue
	@Column(nullable = false, unique = true)
	private long id;
	@Column(length = 15, nullable = false)
	private String writer;
	@Column(length = 100, nullable = false)
	private String title;
	@Column(length = 300)
	private String contents;
	
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(String writer, String title, String contents){
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	public Question(long id, String writer, String title, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
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
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
}
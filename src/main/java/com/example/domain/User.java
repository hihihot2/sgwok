package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
//	JPA에서 인식하고 DB에서 자동으로 인식하게 도와줌
	@Id @GeneratedValue
	private long id;
	
	@Column(length = 15, nullable = false, unique = true)
	private String userId;
	@Column(length = 15, nullable = false)
	private String password;
	@Column(length = 10, nullable = false)
	private String name;
	@Column(length = 20, nullable = false)
	private String email;
	
	public User(){	}
	public User(long id, String userId, String password, String name, String email) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean matchPassword(String password){
		if(this.password.equals(password)){
			return true;
		}else
			return false;
	}
	public boolean matchId(long id) {
		if(this.id == id)
			return true;
		else
			return false;
	}
	
	public void update(User user){
		if(matchPassword(user.password)){
			this.name = user.name;
			this.email = user.email;
		}
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}
}

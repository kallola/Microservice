package com.bean.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_TBL")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "userName")
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public User(String userName) {
		this.userName = userName;
	}*/

}

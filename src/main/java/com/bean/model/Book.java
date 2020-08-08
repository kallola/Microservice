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
@Table(name = "Book_TBL")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
public class Book {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "bookname")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public Book(String name) {
		this.name = name;
	}*/

}

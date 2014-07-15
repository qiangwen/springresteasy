package com.jrj.entity;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class FormEntity {

	@QueryParam("age")
	private int age;
	
	@QueryParam("name")
	private String name;
	
	
	private int id;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	@PathParam("id")
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FormEntity [age=" + age + ", name=" + name + ", id=" + id + "]";
	}
	
	
}

package com.loyd.entity;

import java.util.Set;

/**
 * 
 * @author chenlide
 *
 */
public class StudentSet {

	private int id;
	private String name;
	private int age;
	private Set<String> hobby;
	
	public StudentSet(){
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	public Set<String> getHobby() {
		return hobby;
	}

	public void setHobby(Set<String> hobby) {
		this.hobby = hobby;
	}


	
}

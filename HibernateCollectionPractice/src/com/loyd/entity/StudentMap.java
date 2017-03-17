 package com.loyd.entity;

import java.util.Map;

/**
 * 
 * @author chenlide
 *
 */
public class StudentMap {

	private int id;
	private String name;
	private int age;
	private Map<String,String> hobby;
	
	public StudentMap(){
		
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

	public Map<String,String> getHobby() {
		return hobby;
	}

	public void setHobby(Map<String,String> hobby) {
		this.hobby = hobby;
	}
}

/**
 * 
 */
package com.loyd.entity;

/**
 * @author chenlide
 *
 */
public class Student {

	private int id;
	private String stuName;
	private int age;
	private String clazz;
	
	public Student(){
		
	}
	
	public Student(String stuName){
		this.stuName=stuName;
	}
	
	public Student(String stuName,int age){
		this.stuName=stuName;
		this.age=age;
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
	 * @return the stuName
	 */
	public String getStuName() {
		return stuName;
	}

	/**
	 * @param stuName the stuName to set
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [age=" + age + ", id=" + id + ", stuName=" + stuName + "]";
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}


	public String getClazz() {
		return clazz;
	}
	
}

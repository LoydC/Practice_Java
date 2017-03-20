/**  

* <p>Title: Citizen.java</p>  
* <p>Description:</p> 
* <p>Copyright: Copyright (c) 2015</p> 
* <p>Company:</p> 
* @author 孙连伟 
* @date 2015年6月
* @version V1.0
*/ 
package tech.loyd.entity2;

import java.util.Set;

/**
 * @author slw
 * 双向的多对多
 */
public class Student02 {

	private int id;
	private String name;
	private String gender;
	
	//对课程集合的引用
	private Set<StuCourse02> courseList;
	
	
	public Student02(){
		
	
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<StuCourse02> getCourseList() {
		return courseList;
	}

	public void setCourseList(Set<StuCourse02> courseList) {
		this.courseList = courseList;
	}

	
	
	
}

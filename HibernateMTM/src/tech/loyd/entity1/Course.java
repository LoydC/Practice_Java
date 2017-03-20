package tech.loyd.entity1;

import java.util.Set;

/**
 * @author chenlide
 * 双向的多对多
 */
public class Course {

	private int id;
	private String name;
	
	//对学生集合的引用
	private Set<Student> stuList;	
	
	public Course(){
		
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
	 * @return the stuList
	 */
	public Set<Student> getStuList() {
		return stuList;
	}

	/**
	 * @param stuList the stuList to set
	 */
	public void setStuList(Set<Student> stuList) {
		this.stuList = stuList;
	}

	
	
}

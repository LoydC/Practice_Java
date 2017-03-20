package tech.loyd.entity;

import java.util.Set;

/**
 * @author chenlide
 * 双向一对多或多对一 自身关联
 * 
 */
public class Category {

	private int id;
	private String name;
	
	//如果把Category看成是多的一端
	private Category parent;
	
	//如果把Category看成是少的一端,则需要对多的一端进行对象集合的引用
	private Set<Category> clist;
	
	public Category(){
		
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
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return the clist
	 */
	public Set<Category> getClist() {
		return clist;
	}

	/**
	 * @param clist the clist to set
	 */
	public void setClist(Set<Category> clist) {
		this.clist = clist;
	}
	
}

package tech.loyd.entity;

import java.util.Set;

/**
 * @author chenlide
 * 单向的一对多
 * 少的一端,需要对多的一端进行集合的引用
 */
public class Account {

	private int id;
	private String accName;
	
	//对多端对象集合的引用
	private Set<Orders> setOrders;
	
	public Account(){
		
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
	 * @return the accName
	 */
	public String getAccName() {
		return accName;
	}

	/**
	 * @param accName the accName to set
	 */
	public void setAccName(String accName) {
		this.accName = accName;
	}

	/**
	 * @return the setOrders
	 */
	public Set<Orders> getSetOrders() {
		return setOrders;
	}

	/**
	 * @param setOrders the setOrders to set
	 */
	public void setSetOrders(Set<Orders> setOrders) {
		this.setOrders = setOrders;
	}
	
	
}

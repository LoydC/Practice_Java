package com.loyd.entity;

import java.util.Date;

/**
 * 
 * @author chenlide
 *
 */
public class Account {

	private int id;
	private String loginName;
	private String password;
	private Date regeDate;
	
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
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", loginName=" + loginName + ", password="
				+ password + "]";
	}

	public void setRegeDate(Date regeDate) {
		this.regeDate = regeDate;
	}

	public Date getRegeDate() {
		return regeDate;
	}
	
	
}

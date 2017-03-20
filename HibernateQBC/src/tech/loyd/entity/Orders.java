package tech.loyd.entity;

import java.util.Date;

/**
 * @author chenlide
 * 双向的一对多，多端
 */
public class Orders {

	private int id;
	private String order_no;
	
	private Date order_time;
	
	private Account acc;
	
	public Orders(){
		
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
	 * @return the order_no
	 */
	public String getOrder_no() {
		return order_no;
	}

	/**
	 * @param order_no the order_no to set
	 */
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	/**
	 * @return the order_time
	 */
	public Date getOrder_time() {
		return order_time;
	}

	/**
	 * @param order_time the order_time to set
	 */
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	/**
	 * @return the acc
	 */
	public Account getAcc() {
		return acc;
	}

	/**
	 * @param acc the acc to set
	 */
	public void setAcc(Account acc) {
		this.acc = acc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Orders [id=" + id + ", order_no=" + order_no + ", order_time="
				+ order_time + "]";
	}

}

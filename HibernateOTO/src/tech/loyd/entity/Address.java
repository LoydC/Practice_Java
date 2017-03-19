package tech.loyd.entity;

/**
 * @author chenlide
 * 单向一对一  被控端
 */
public class Address {

	private int id;
	private String address;
	
	public Address(){
		
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Address [address=" + address + ", id=" + id + "]";
	}
	
	
	
}

/**
 * 
 */
package tech.loyd.entity;


/**
 * @author chelnide
 * ˫��һ��һ ���ض� 
 */
public class Account {

	private Integer id;
	private String name;
	
	//���ض����ñ��ض˵Ķ���
	
	private Address address_id;
	
	public Account(){
		
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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

//	/**
//	 * @return the address
//	 */
//	public Address getAddress() {
//		return address;
//	}
//
//	/**
//	 * @param address the address to set
//	 */
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name
				+ "]";
	}

	/**
	 * @return the address_id
	 */
	public Address getAddress_id() {
		return address_id;
	}

	/**
	 * @param addressId the address_id to set
	 */
	public void setAddress_id(Address addressId) {
		address_id = addressId;
	}
	
	
	
}

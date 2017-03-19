/**
 * 
 */
package tech.loyd.entity;


/**
 * @author chenlide
 * 双向的基于主键的一对一  被控端 公民
 */
public class Citizen {

	private int id;
	private String name;
	
	private IDCard idCard;

	
	public Citizen(){
		
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




	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}




	public IDCard getIdCard() {
		return idCard;
	}

	
	
	
}

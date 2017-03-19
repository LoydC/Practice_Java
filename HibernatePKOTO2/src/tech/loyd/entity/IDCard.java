/**
 * 
 */
package tech.loyd.entity;


/**
 * @author chenlide
 * 双向基于主键的一对一  主控端  对象模型中的身份证
 */
public class IDCard {

	private Integer id;
	private String no;
	
	//主控端引用被控端的对象
	
	private Citizen citizen;
	
	public IDCard(){
		
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
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the citizen
	 */
	public Citizen getCitizen() {
		return citizen;
	}

	/**
	 * @param citizen the citizen to set
	 */
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	
}

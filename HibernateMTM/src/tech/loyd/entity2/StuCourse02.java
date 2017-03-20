/**  

* <p>Title: StuCourse.java</p>  
* <p>Description:</p> 
* <p>Copyright: Copyright (c) 2015</p> 
* <p>Company:</p> 
* @author 孙连伟 
* @date 2015年6月
* @version V1.0
*/ 
package tech.loyd.entity2;

/**
 * @author slw
 * 学生和课程的关系实体
 */
public class StuCourse02 {

	private int id;
	private double score;
	
	private Student02 stu;
	private Course02 course;
	
	public StuCourse02(){
		
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
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the stu
	 */
	public Student02 getStu() {
		return stu;
	}

	/**
	 * @param stu the stu to set
	 */
	public void setStu(Student02 stu) {
		this.stu = stu;
	}

	/**
	 * @return the course
	 */
	public Course02 getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course02 course) {
		this.course = course;
	}
	
}

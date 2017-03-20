/**
 * 
 */
package tech.loyd.test;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import tech.loyd.entity1.Course;
import tech.loyd.entity1.Student;
import tech.loyd.entity2.Course02;
import tech.loyd.entity2.StuCourse02;
import tech.loyd.entity2.Student02;
import tech.loyd.util.HibernateUtils;

/**
 * @author chenlide
 *
 */
public class MTMTest {

	
	/**
	 * 生成DDL语句
	 */
	@Test
	public void sechmDDL(){
		Configuration config=new Configuration().configure();
		SchemaExport schema=new SchemaExport(config);
		schema.setFormat(true).create(true, true);
	}
	
	//@Test
	public void add(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			
			Course c1=new Course();
			c1.setName("java");
			session.save(c1);
			
			Course c2=new Course();
			c2.setName("android");
			session.save(c2);
			
			Student stu=new Student();
			stu.setName("zhangsan");
			Set<Course> set=new HashSet<Course>();
			set.add(c1);
			set.add(c2);
			stu.setCourseList(set);
			
			session.save(stu);
		
			tx.commit();
		}catch(HibernateException he){
			if(tx!=null){
				tx.rollback();
			}
			he.printStackTrace();
		}finally{
			HibernateUtils.closeSession(session);
		}
	}
	
	//@Test
	public void add2(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			Course02 c1=new Course02();
			c1.setName("javase");
			session.save(c1);
			
			Course02 c2=new Course02();
			c2.setName("javaee");
			session.save(c2);
			
			Student02 stu1=new Student02();
			stu1.setName("zhangsan");
			session.save(stu1);
			
			Student02 stu2=new Student02();
			stu2.setName("lisi");
			session.save(stu2);
			
			StuCourse02 stuc1=new StuCourse02();
			stuc1.setStu(stu1);
			stuc1.setCourse(c1);
			session.save(stuc1);
			
			StuCourse02 stuc2=new StuCourse02();
			stuc2.setStu(stu1);
			stuc2.setCourse(c2);
			session.save(stuc2);
			
			StuCourse02 stuc3=new StuCourse02();
			stuc3.setStu(stu2);
			stuc3.setCourse(c1);
			session.save(stuc3);
			
			StuCourse02 stuc4=new StuCourse02();
			stuc4.setStu(stu2);
			stuc4.setCourse(c2);
			session.save(stuc4);
			
			
			tx.commit();
		}catch(HibernateException he){
			if(tx!=null){
				tx.rollback();
			}
			he.printStackTrace();
		}finally{
			HibernateUtils.closeSession(session);
		}
	}
	


}

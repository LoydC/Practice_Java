/**
 * 
 */
package tech.loyd.test;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import tech.loyd.entity.Dept;
import tech.loyd.entity.Employee;
import tech.loyd.util.HibernateUtils;

/**
 * @author chenlide
 *
 */
public class MTOTest {

	
	/**
	 * 生成DDL语句
	 */
	@Test
	public  void sechmDDL(){
		Configuration config=new Configuration().configure();
		SchemaExport schema=new SchemaExport(config);
		schema.setFormat(true).create(true, true);
		
	}
	
	@Test
	public void add(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			//在单向的多对一中，要先添加的少的一端
			Dept dept=new Dept();
			dept.setDeptName("Dep");
			session.save(dept);
			
			Employee emp=new Employee();
			emp.setEmpName("zhangsan");
			emp.setHiredate(new Date());
			emp.setDept(dept);
			
			session.save(emp);
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
	
	@Test
	public void get(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			Employee emp=(Employee)session.get(Employee.class, 1);
			System.out.println(emp.getEmpName()+emp.getHiredate());
			Hibernate.initialize(emp.getDept());
			//System.out.println(emp.getDept().getDeptName());
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

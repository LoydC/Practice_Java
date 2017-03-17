package com.loyd.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.loyd.entity.StudentSet;
import com.loyd.util.HibernateUtils;

/**
 * 
 * @author chenlide
 *
 */
public class StudentSetTest {
	@Test
	public void createTable(){
		Configuration cfg=new Configuration().configure();
		SchemaExport se=new SchemaExport(cfg);
		se.create(true, true);
	}
	
	@Test
	public void save(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			StudentSet student=new StudentSet();
			student.setName("zhangsan");
			student.setAge(20);
			
			Set<String> set=new HashSet();
			set.add("abc");
			set.add("def");
			
			student.setHobby(set);
			
			session.save(student);
			
			
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
	public void findAll(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			
			StudentSet stu=(StudentSet)session.get(StudentSet.class, 1);
			System.out.println(stu.getId()+stu.getName()+stu.getAge());
			
			Set<String> hobby=stu.getHobby();
			for(String str:hobby){
				System.out.println(str);
			}
			
			
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

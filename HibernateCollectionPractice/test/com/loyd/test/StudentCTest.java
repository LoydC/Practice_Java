package com.loyd.test;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.loyd.entity.StudentC;
import com.loyd.util.HibernateUtils;

/**
 * 
 * @author chenlide
 *
 */
public class StudentCTest {
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
			
			StudentC stu=new StudentC();
			
			stu.setName("wangwu");
			stu.setAge(20);
			
			Collection<String> hobby =new ArrayList();
			hobby.add("a");
			hobby.add("b");
			
			stu.setHobby(hobby);
			
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
	
	@Test
	public void findAll(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			StudentC stu=(StudentC)session.get(StudentC.class, 1);
			System.out.println(stu.getName()+stu.getAge()+stu.getId());
			
			Collection<String> c=stu.getHobby();
			c.remove("b");
			
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

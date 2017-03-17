package com.loyd.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.loyd.entity.StudentList;
import com.loyd.util.HibernateUtils;

/**
 * 
 * @author chenlide
 *
 */
public class StudentListTest {
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
			
			StudentList student=new StudentList();
			student.setName("lisi");
			student.setAge(20);
			
			List<String> hobbyList=new ArrayList();
			hobbyList.add("a");
			hobbyList.add("b");
			
			student.setHobby(hobbyList);
			
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
			
			StudentList stu=(StudentList)session.get(StudentList.class, 1);
			System.out.println(stu.getId()+stu.getName()+stu.getAge());
			
			List<String> list=stu.getHobby();
			for(String str:list){
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

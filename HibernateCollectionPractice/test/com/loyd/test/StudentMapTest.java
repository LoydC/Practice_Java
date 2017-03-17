package com.loyd.test;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.loyd.entity.StudentMap;
import com.loyd.util.HibernateUtils;

/**
 * 
 * @author chenlide
 *
 */
public class StudentMapTest {
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
			
			StudentMap stu=new StudentMap();
			stu.setName("wangwu");
			stu.setAge(20);
			
			Map<String,String> map=new HashMap();
			map.put("a", "run");
			map.put("b", "eat");
			
			stu.setHobby(map);
			
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
			
			StudentMap stu=(StudentMap)session.get(StudentMap.class, 1);
			System.out.println(stu.getId()+stu.getName()+stu.getAge());
			
			Map<String,String > map=stu.getHobby();		
			System.out.println(map);
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

package tech.loyd.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import tech.loyd.entity.Account;
import tech.loyd.entity.Address;
import tech.loyd.util.HibernateUtils;

/**
 * @author chenlide
 *
 */
public class OTO2Test {

	
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
			//
			Address address=new Address();
			address.setAddress("001");
			session.save(address);
			//用户
			Account acc=new Account();
			acc.setName("zhangsan");
			acc.setAddress_id(address);
			
			
			session.save(acc);
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
	public void add2(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			//
			Address address=new Address();
			address.setAddress("001");
			session.save(address);
			//用户
			Account acc=new Account();
			acc.setName("zhangsan");
			acc.setAddress_id(address);
			
			//一对一关系中不能多个用户使用同一个地址ID
			Account acc2=new Account();
			acc2.setName("王五");
			acc2.setAddress_id(address);
			
			session.save(acc);
			session.save(acc2);
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

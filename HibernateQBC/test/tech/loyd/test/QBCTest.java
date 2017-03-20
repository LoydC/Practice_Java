/**
 * 
 */
package tech.loyd.test;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Assert;
import org.junit.Test;

import tech.loyd.entity.Account;
import tech.loyd.entity.Orders;
import tech.loyd.util.HibernateUtils;


/**
 * @author chenlide
 *
 */
public class QBCTest {

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
	public void testInitData(){
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtils.getSession();
			tx = session.beginTransaction(); //开启事务
			
			Random random = new Random();
			for(int i = 0; i < 21; i++){
				Account acc = new Account();
				if(i % 3 == 0){
					acc.setName("zhang" + i);
					acc.setGender("man");
				}else if(i % 3 == 1){
					acc.setName("li" + i);
					acc.setGender("women");
				}else{
					acc.setName("wang" + i);
					acc.setGender("man");
				}
				
				acc.setAge(18 + random.nextInt(42));
				session.save(acc);
				
				if(i > 0){
					int size = random.nextInt(10);
					for(int j = 0; j < size; j++){
						Orders order = new Orders();
						order.setAcc(acc);
						order.setOrder_no("jike-" + i + "-" + j);
						order.setOrder_time(new Date());
						
						session.save(order);
					}
				}
			}
				
			tx.commit(); //提交事务
		}catch(HibernateException he){
			if(tx!=null){
				tx.rollback();
			}
			he.printStackTrace();
		}finally{
			HibernateUtils.closeSession(session);
		}
	}
	
	/**
	 * Criteria 查询示例
	 */
	@Test
	public void qbcSelect1(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			Criteria cr=session.createCriteria(Account.class);
			Criterion c1=Restrictions.like("name", "zhang%");
			//cr.add(c1);
			Criterion c2=Restrictions.eq("age", 35);
			//cr.add(c2);
			Criterion c3=Restrictions.and(c1, c2);
			//cr.add(c3);
			
			cr.addOrder(Order.desc("id"));
			
			List<Account> list=cr.list();
			for(Account a:list){
				System.out.println(a);
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
	
	/**
	 * 投影查询
	 */
	@Test
	public void qbcSelect2(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			Criteria cr=session.createCriteria(Account.class);
			cr.setProjection(Projections.max("age"));
			
			List<Integer> list=(List<Integer>)cr.list();
			for(Integer a:list){
				System.out.println(a);
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
	
	
	
	/**
	 * 离线查询测试
	 */
	@Test
	public void qbcSelect3(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			DetachedCriteria dc=DetachedCriteria.forClass(Account.class);
			
			Criteria cr=dc.getExecutableCriteria(session);
			
			List<Account> list=(List<Account>)cr.list();
			for(Account a:list){
				System.out.println(a);
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

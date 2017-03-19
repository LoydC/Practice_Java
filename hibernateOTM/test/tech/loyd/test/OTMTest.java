/**
 * 
 */
package tech.loyd.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import tech.loyd.entity.Account;
import tech.loyd.entity.Orders;
import tech.loyd.util.HibernateUtils;

/**
 * @author chenlide
 *
 */
public class OTMTest {

	
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
			
			Orders order1=new Orders();
			order1.setOrderNum("1001");
			order1.setOrderTime(new Date());
			session.save(order1);
			
			Orders order2=new Orders();
			order2.setOrderNum("1002");
			order2.setOrderTime(new Date());
			session.save(order2);
			
			Account account=new Account();
			account.setAccName("zhangsan");
			Set<Orders> setOrders=new HashSet<Orders>();
			setOrders.add(order1);
			setOrders.add(order2);
			account.setSetOrders(setOrders);
			
			session.save(account);
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
			
			Account acc=(Account)session.get(Account.class,1);
			System.out.println(acc.getId()+acc.getAccName());
			
			//Hibernate.initialize(acc.getSetOrders());//强制立马加载
			//获取信息时加载
			for(Orders o:acc.getSetOrders()){
				System.out.println(o.getOrderNum());
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

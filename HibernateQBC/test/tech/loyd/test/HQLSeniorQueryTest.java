/**
 * 
 */
package tech.loyd.test;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class HQLSeniorQueryTest {

	/**
	 * 子查询示例
	 */
	@Test
	public void qbcSelect1(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="from Account a where (select count(o) from a.orderList o)=0";
			List<Account> list=session.createQuery(hql).list();
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
	 * HQL的 内连接
	 */
	@Test
	public void qbcSelect2(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select distinct a from Account a inner join a.orderList o with o.order_time>'2016-12-10' where a.id=2";
			List<Account> list=session.createQuery(hql).list();
			for(Account a:list){
				System.out.println(a);
				Set<Orders> list2=a.getOrderList();
				for(Orders or:list2){
					System.out.println(or);
				}
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
	 * 左外连接查询
	 */
	@Test
	public void qbcSelect3(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select distinct a from Account a left join a.orderList o ";
			List<Account> list=session.createQuery(hql).list();
			for(Account a:list){
				System.out.println(a);
				Set<Orders> list2=a.getOrderList();
				for(Orders or:list2){
					System.out.println(or);
				}
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
	 * 右外连接查询
	 */
	@Test
	public void qbcSelect4(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select distinct a from Account a right join a.orderList o with o.order_time>'2015-06-10' where a.id=2";
			List<Account> list=session.createQuery(hql).list();
			for(Account a:list){
				System.out.println(a);
				Set<Orders> list2=a.getOrderList();
				for(Orders or:list2){
					System.out.println(or);
				}
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
	 * HQL的 内连接 抓取连接查询
	 */
	@Test
	public void qbcSelect5(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select distinct a from Account a right join fetch a.orderList o  where a.id=2";
			List<Account> list=session.createQuery(hql).list();
			for(Account a:list){
				System.out.println(a);
				Set<Orders> list2=a.getOrderList();
				for(Orders or:list2){
					System.out.println(or);
				}
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

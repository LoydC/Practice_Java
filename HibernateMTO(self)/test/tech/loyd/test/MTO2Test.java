/**
 * 
 */
package tech.loyd.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import tech.loyd.entity.Category;
import tech.loyd.util.HibernateUtils;

/**
 * @author chenlide
 *
 */
public class MTO2Test {

	
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
			//添加一级分类
			Category c1=new Category();
			c1.setName("computer");
			
			session.save(c1);
			
			//添加二级分类
			Category c2=new Category();
			c2.setName("java");
			c2.setParent(c1);
			session.save(c2);
			//添加二级分类
			Category c3=new Category();
			c3.setName(".net");
			c3.setParent(c1);
			session.save(c3);
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

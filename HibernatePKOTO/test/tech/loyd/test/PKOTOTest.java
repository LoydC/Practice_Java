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

import tech.loyd.entity.Citizen;
import tech.loyd.entity.IDCard;
import tech.loyd.util.HibernateUtils;

/**
 * @author Sun Lianwei
 *
 */
public class PKOTOTest {

	
	/**
	 * 生成DDL语句
	 */
	@Test
	public  void sechmDDL(){
		Configuration config=new Configuration().configure();
		SchemaExport schema=new SchemaExport(config);
		schema.setFormat(true).create(true, true);
		
	}
	
	//@Test
	public void add(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
		
			Citizen c=new Citizen();
			c.setName("wangwu");
			session.save(c);
			
			IDCard idc=new IDCard();
			idc.setNo("1102456468");
			//建立关系
			idc.setCitizen(c);
			
			session.save(idc);
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

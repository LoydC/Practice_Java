/**
 * 
 */
package tech.loyd.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author chenlide
 * ������
 */
public class HibernateUtils {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	static{
		Configuration cfg=new Configuration().configure();
		
		StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		ServiceRegistry service=ssrb.build();
		sessionFactory=cfg.buildSessionFactory(service);
	
	}
	
	private HibernateUtils(){
		
	}
	
//	public static SessionFactory getSessionFactory(){
//		return sessionFactory;
//	}
	/**
	 * ��ȡsession����
	 */
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	/**
	 * �ر�session����
	 */
	public static void closeSession(){
		if(session!=null&&session.isOpen()){
			session.close();
		}
	}
	
	public static void closeSession(Session session){
		if(session!=null&&session.isOpen()){
			session.close();
		}
	}
}
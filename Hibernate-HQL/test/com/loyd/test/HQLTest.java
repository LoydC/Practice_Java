package com.loyd.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.loyd.entity.Account;
import com.loyd.entity.Student;
import com.loyd.util.HibernateUtils;

/**
 * @author chenlide
 */
public class HQLTest {

	@Test
	public void createTable() {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

	/**
	 * ��ѯ���е�
	 */
	@Test
	public void select1() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select stu from Student as stu order by stu.id asc";

			Query query = session.createQuery(hql);
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * ��ѯһ��ʵ������
	 */
	@Test
	public void select2() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "from Student";
			Student stu = (Student) session.createQuery(hql).setMaxResults(1).uniqueResult();
			System.out.println(stu);
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL��ͶӰ��ѯ
	 */
	@Test
	public void select3() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select a.stuName,a.age from Student a";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			for (Object objs[] : list) {
				for (Object obj : objs) {
					System.out.println(obj);
				}
				System.out.println("*/****����ѧԺ*******");
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * ʵ����ͶӰ��ѯ���
	 *
	 */
	@Test
	public void select4() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select new Student(a.stuName,a.age) from Student a";
			Query query = session.createQuery(hql);
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * where�Ӿ� ��where�Ӿ��п���ָ�� .�� �Ƚ�������� =��>��>=��<��<=��<> ��is null ��is not null
	 * ��Χ������� in (ֵ1, ֵ2 ��) �� �����б��е�ĳһ��ֵ not in(ֵ1, ֵ2 ��) �� �������б��е�����һ��ֵ between
	 * ֵ1 and ֵ2 �� ��ֵ1��ֵ2�ķ�Χ��(����ֵ1��ֵ2) not between ֵ1 and ֵ2 �� ����ֵ1��ֵ2�ķ�Χ��
	 * �ַ���ģʽƥ�䣺 like '�ַ���ģʽ' �ַ���ģʽ�п��á�%���������ⳤ�ȵ��ַ�������_���������ⵥ���ַ��� �߼����㣺 and (��)��
	 * or (��)��not (��) ���ڼ��ϵ��������is empty��is not empty
	 */
	@Test
	public void select5() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// ����1��������5
			String hql = "from Student a where a.id not between 1 and 5";
			Query query = session.createQuery(hql);
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL�������� 1.�ַ������ upper(s) ��lower(s) �� concat(s1, s2)
	 * ��substring(s,offset,length) �� length(s)�� trim([[both|leading|trailing]
	 * char [from]] s)��locate(search, s, offset) 2.���� abs(n)
	 * ��sqrt(n)��mod(dividend, divisor) 3.���� size(c) ���ؼ�����Ԫ�صĸ��� 4.����ʱ��
	 * current_date()��current_time()��current_timestamp() �������ݿ�ϵͳ�����ڡ�ʱ�䡢ʱ���
	 * year(d)��month(d)��day(d)��hour(d)��minute(d)��second(d) ��ָ���Ĳ�������ȡ��Ӧ��ֵ
	 */
	@Test
	public void select6() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select new Student(upper(s.stuName))from Student s";
			Query query = session.createQuery(hql);
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL ������?�ŵ�ʹ��
	 */
	@Test
	public void select7() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "from Student a where a.id=? and a.stuName=?";

			Query query = session.createQuery(hql);
			query.setInteger(0, 3);
			query.setString(1, "wangwu");
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL �������ư�
	 */
	@Test
	public void select8() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// ��ѯ����������Ҫ��ȡһ��Query �ӿ�ʵ������
			String hql = "from Student a where a.id=:id and a.stuName=:name";

			Query query = session.createQuery(hql);
			query.setInteger("id", 3);
			query.setString("name", "wangwu");
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL ȥ���ظ���¼
	 */
	@Test
	public void select9() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select distinct s.age,s.stuName from Student s";

			Query query = session.createQuery(hql);

			List<Object[]> list = query.list();
			for (Object objs[] : list) {
				System.out.println(objs[0]);
				System.out.println(objs[1]);
				System.out.println("*/******����ѧԺ*****");
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL �ۺϺ�����ʹ��
	 */
	@Test
	public void select10() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select min(s.age) from Student s";
			Query query = session.createQuery(hql);

			List list = query.list();
			System.out.println(list.get(0));

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL ���麯��
	 */
	@Test
	public void select11() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "select count(s.id),s.clazz from Student s group by s.clazz having avg(s.age)>20";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			for (Object[] objs : list) {
				for (Object obj : objs) {
					System.out.println(obj);
				}
			}

			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	@Test
	public void testPager() {
		select12(1, 2);
	}

	/**
	 * ��ҳ��ѯ
	 */
	public void select12(int pageNo, int pageSize) {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "from Student";
			Query query = session.createQuery(hql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
			List<Student> list = (List<Student>) query.list();
			for (Student stu : list) {
				System.out.println(stu);
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL ���»���ɾ��
	 */
	@Test
	public void updateOrDelete() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "update Account set password='123456' where id=:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", 1);
			int i = query.executeUpdate();
			System.out.println(i);
			if (i > 0) {
				System.out.println("�ɹ�");
			} else {
				System.out.println("ʧ��");
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

	/**
	 * HQL��������ѯ��ʽ
	 */
	@Test
	public void select13() {
		Transaction tx = null;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Query query = session.getNamedQuery("accountHql");
			List<Account> list = (List<Account>) query.list();
			for (Account ac : list) {
				System.out.println(ac);
			}
			tx.commit();
		} catch (HibernateException he) {
			if (tx != null) {
				tx.rollback();
			}
			he.printStackTrace();
		} finally {
			HibernateUtils.closeSession(session);
		}

	}

}

# Hibernate基本查询

Query接口

Query是Hibernate专门用来执行HQL语句的查询接口

```java
Query query = session.createQuery("HQL语句");
query.setParameter(...);
List resultList = query.list();
```

HQL基本查询

Hibernate Query Language（HQL）：官方推荐的查询语言，具有以下功能

- 条件查询
- 支持投影查询
- 分页查询
- 连接查询
- 分组查询
- 子查询
- 内置了一些聚集函数
- 支持动态绑定参数

## 查询实体类的所有实例对象

```java
	/**
	 * 查询所有的
	 */
	@Test
	public  void select1(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
          	//默认为升序，desc为倒叙
			String hql="select stu from Student as stu order by stu.id asc";
			
			Query query=session.createQuery(hql);
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## 查询唯一的一条记录

```java
	/**
	 *查询一个实例对象
	 */
	@Test
	public  void select2(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="from Student";
			Student stu=(Student)session.createQuery(hql).setMaxResults(1).uniqueResult();
			System.out.println(stu);
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
```

## HQL的投影查询

投影查询：即查询类的某几个属性，通过用select语句只选择类的部分属性来实现的。

```java
	/**
	 *HQL的投影查询
	 */
	@Test
	public  void select3(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select a.stuName,a.age from Student a";
			Query query=session.createQuery(hql);
			List<Object[]> list=query.list();
			for(Object objs[]:list){
				for(Object obj:objs){
					System.out.println(obj);
				}
				System.out.println("*/****极客学院*******");
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
```

## 实例化投影查询，需要类的对应的有参构造函数

```java
	public Student(String stuName,int age){
		this.stuName=stuName;
		this.age=age;
	}
```

```java
	/**
	 *实例化投影查询结果
	 *
	 */
	@Test
	public  void select4(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select new Student(a.stuName,a.age) from Student a";
			Query query=session.createQuery(hql);
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## where子句

```java
	/**
	 *where子句
	 *在where子句中可以指定
		.号
		比较运算符：
			=、>、>=、<、<=、<> 、is null 、is not null 
		范围运算符：
			in (值1, 值2 …)  ： 等于列表中的某一个值
			not in(值1, 值2 …)  ： 不等于列表中的任意一个值
			between 值1 and 值2  ： 在值1到值2的范围内(包括值1和值2)
			not between 值1 and 值2 ： 不在值1到值2的范围内
		字符串模式匹配： like '字符串模式'
			字符串模式中可用“%”代表任意长度的字符串，“_”代表任意单个字符。
		逻辑运算： and (与)、 or (或)、not (非)
		用于集合的运算符：is empty、is not empty
	 */
	@Test
	public  void select5(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="from Student a where a.id not between 1 and 5";
			Query query=session.createQuery(hql);
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## HQL函数

```java
	public Student(String stuName){
		this.stuName=stuName;
	}
```

```java
	/**
	 * HQL常见函数
		1.字符串相关
			upper(s) 、lower(s) 、
			concat(s1, s2) 、substring(s,offset,length) 、 length(s)、
			trim([[both|leading|trailing] char [from]] s)、locate(search, s, offset)
		2.数字 
			abs(n) 、sqrt(n)、mod(dividend, divisor)
		3.集合
			size(c)  返回集合中元素的个数
		4.日期时间
			current_date()、current_time()、current_timestamp() 
			返回数据库系统的日期、时间、时间戳
			year(d)、month(d)、day(d)、hour(d)、minute(d)、second(d)
			从指定的参数中提取相应的值
	 */
	@Test
	public  void select6(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select new Student(upper(s.stuName))from Student s";
			Query query=session.createQuery(hql);
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## 参数绑定?号的使用

```java
	/**
	 * HQL 参数绑定?号的使用
	 */
	@Test
	public  void select7(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="from Student a where a.id=? and a.stuName=?";
			
			Query query=session.createQuery(hql);
			query.setInteger(0, 3);
			query.setString(1, "wangwu");
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## HQL 参数名称绑定

```java
	/**
	 * HQL 参数名称绑定
	 */
	@Test
	public  void select8(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			//查询操作，首先要获取一个Query 接口实例对象
			String hql="from Student a where a.id=:id and a.stuName=:name";
			
			Query query=session.createQuery(hql);
			query.setInteger("id", 3);
			query.setString("name", "wangwu");
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## HQL 去掉重复记录

```java
	/**
	 * HQL 去掉重复记录
	 */
	@Test
	public  void select9(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select distinct s.age,s.stuName from Student s";
			
			Query query=session.createQuery(hql);
		
			List<Object[]> list=query.list();
			for(Object objs[]:list){
					System.out.println(objs[0]);
					System.out.println(objs[1]);
				System.out.println("*/******极客学院*****");
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
```

## HQL聚合函数

- count() 统计符合条件的记录条数
- avg() 求平均值
- sum() 求和
- max() 求最大值
- min() 求最小值

```java
	/**
	 * HQL 聚合函数的使用
	 */
	@Test
	public  void select10(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="select min(s.age) from Student s";
			Query query=session.createQuery(hql);
		
			List list=query.list();
			System.out.println(list.get(0));
			
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
```

## 分组函数

```java
	/**
	 * HQL 分组函数
	 */
	@Test
	public  void select11(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
          	//group分组 having筛选
			String hql="select count(s.id),s.clazz from Student s group by s.clazz having avg(s.age)>20";
			Query query=session.createQuery(hql);
			List<Object[]> list=query.list();
			for(Object[]objs:list){
				for(Object obj:objs){
					System.out.println(obj);
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
```

## 分页查询

- setFisrtResult(int firstResult)
- setMaxResult(int maxResults)

```java
	@Test
	public  void testPager(){
      	//第一页，每页有两个
		select12(1, 2);
	}
	
	/**
	 * 分页查询
	 */
	public  void select12(int pageNo,int pageSize){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="from Student";
			Query query=session.createQuery(hql).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize);
			List<Student> list=(List<Student>)query.list();
			for(Student stu:list){
				System.out.println(stu);
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
```

## 批量更新和删除

```java
	/**
	 * HQL 更新或者删除
	 */
	//@Test
	public  void updateOrDelete(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			String hql="update Account set password='123456' where id=:id";
			Query query=session.createQuery(hql);
			query.setInteger("id", 1);
			int i=query.executeUpdate();
			System.out.println(i);
			if(i>0){
				System.out.println("成功");
			}else{
				System.out.println("失败");
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
```

## Hibernate命名查询

Account.hbm.xml

```xml
	<!-- 命名查询 -->
	<query name="accountHql">
		<![CDATA[from Account]]>
	</query>
```

```java
	/**
	 * HQL的命名查询方式
	 */
	@Test
	public  void select13(){
		Transaction tx=null;
		Session session=null;
		try{
			session=HibernateUtils.getSession();
			tx=session.beginTransaction();
			Query query=session.getNamedQuery("accountHql");
			List<Account> list=(List<Account>)query.list();
			for(Account ac:list){
				System.out.println(ac);
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
```

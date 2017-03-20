# Hibernate中标准化对象查询

> 通过面向对象化的设计，将查询条件封装为一个对象。它支持在运行时动态生成查询语句。

主要由四部分组成：

- Criteria接口
- Critertion接口
- Order类
- Projection接口

## Criteria接口

> 是一个存放查询条件的容器，创建Criteria接口对象：
>
> ```java
> Criteria criteria = session.createCriteria(Class persistentClass)
> ```

## Critertion接口

> 代表一个查询条件，可以通过它的实现类Restrictions类来产生查询条件，并且还需要通过Criteria的add方法添加到Criteria实例中。

| HQL                   | QBC                                      |
| --------------------- | ---------------------------------------- |
| >=                    | Restrictions.ge()                        |
| >                     | Restrictions.gt()                        |
| <=                    | Restrictions.le()                        |
| <                     | Restrictions.lt()                        |
| =                     | Restrictions.eq()                        |
| != (<>)               | Restrictions.ne()                        |
| is null               | Restrictions.isNull()                    |
| is not null           | Restrictions.isNotNull()                 |
| between v1 and v2     | Restrictions.between()                   |
| not between v1 and v2 | Restrictions.not(Restrictions.between()) |
| in (v1,v2 ...)        | Restrictions.in()                        |
| not in (v1,v2 ...)    | Restrictions.not(Restrictions.in())      |
| and                   | Restrictions.and()                       |
| or                    | Restrictions.or()                        |
| not                   | Restrictions.not()                       |
| like                  | Restrictions.like()，Restrictions.ilike()不区分大小写 |

## Order类

> 对查询结果进行排序，通过Criteria的addOrder()方法添加到Criteria实例中

排序方式有：

- Order.desc(String propertyName)
- Order.esc(String propertyName)

## Projection接口

> 代表投影查询，它的Projections类提供了一系列产生具体Projection实例的静态方法。通过Criteria的setProjection()方法添加到Criteria实例中。

Projections类中的聚合函数有：

- avg(String propertyName)
- count(String propertyName)
- sum(String propertyName)
- max(String propertyName)
- min(String propertyName)

## 离线查询

> DetachedCriteria类可以在session范围之外创建一个查询，并且可以附加到任意Session上来执行查询。

------

# Hibernate中HQL的高级查询

## 子查询

> 内嵌在另一个查询语句中的查询，称为子查询

HQL中的子查询只可以在select或者where子句中出现

查找订单数量为零的账户

```java
String hql="from Account a where (select count(o) from a.orderList o)=0";
```

对应的SQL语句为

```sql
	select
        account0_.id as id1_0_,
        account0_.name as name2_0_,
        account0_.gender as gender3_0_,
        account0_.age as age4_0_ 
    from
        acc_tab account0_ 
    where
        (
            select
                count(orderlist1_.id) 
            from
                order_tab orderlist1_ 
            where
                account0_.id=orderlist1_.acc_id
        )=0	
```

## 连接查询

>  Hibernate中的连接查询只能在建立了关联映射的实体类之间进行，可以通过HQL的with关键字，来提供额外的join条件

### 内连接 ([inner] join)

> 内联接使用比较运算符根据每个表共有的列的值匹配两个表中的行。

找没有重复的账户，账户的id为2，订单的时间要大于2015-06-10

```java
String hql="select distinct a from Account a inner join a.orderList o with o.order_time>'2016-12-10' where a.id=2";
```

```sql
    select
        distinct account0_.id as id1_0_,
        account0_.name as name2_0_,
        account0_.gender as gender3_0_,
        account0_.age as age4_0_ 
    from
        acc_tab account0_ 
    inner join
        order_tab orderlist1_ 
            on account0_.id=orderlist1_.acc_id 
            and (
                orderlist1_.order_time>'2015-06-10'
            ) 
    where
        account0_.id=2

	select
        orderlist0_.acc_id as acc_id4_0_0_,
        orderlist0_.id as id1_1_0_,
        orderlist0_.id as id1_1_1_,
        orderlist0_.order_no as order_no2_1_1_,
        orderlist0_.order_time as order_ti3_1_1_,
        orderlist0_.acc_id as acc_id4_1_1_ 
    from
        order_tab orderlist0_ 
    where
        orderlist0_.acc_id=?
```

### 左外连接(left [outer] join)

> 左向外联接的结果集包括  LEFT OUTER子句中指定的左表的所有行，而不仅仅是联接列所匹配的行。如果左表的某行在右表中没有匹配行，则在相关联的结果集行中右表的所有选择列表列均为空值。       

```java
String hql="select distinct a from Account a left join a.orderList o ";
```

对应的sql语句

```sql
    select
        distinct account0_.id as id1_0_,
        account0_.name as name2_0_,
        account0_.gender as gender3_0_,
        account0_.age as age4_0_ 
    from
        acc_tab account0_ 
    left outer join
        order_tab orderlist1_ 
            on account0_.id=orderlist1_.acc_id

	select
        orderlist0_.acc_id as acc_id4_0_0_,
        orderlist0_.id as id1_1_0_,
        orderlist0_.id as id1_1_1_,
        orderlist0_.order_no as order_no2_1_1_,
        orderlist0_.order_time as order_ti3_1_1_,
        orderlist0_.acc_id as acc_id4_1_1_ 
    from
        order_tab orderlist0_ 
    where
        orderlist0_.acc_id=?
```

### 右外连接(right [outer] join)

> 右向外联接是左向外联接的反向联接。将返回右表的所有行。如果右表的某行在左表中没有匹配行，则将为左表返回空值。 

```java
String hql="select distinct a from Account a right join a.orderList o with o.order_time>'2015-06-10' where a.id=2";
```

对应的sql语句

```sql
    select
        distinct account0_.id as id1_0_,
        account0_.name as name2_0_,
        account0_.gender as gender3_0_,
        account0_.age as age4_0_ 
    from
        acc_tab account0_ 
    right outer join
        order_tab orderlist1_ 
            on account0_.id=orderlist1_.acc_id 
            and (
                orderlist1_.order_time>'2015-06-10'
            ) 
    where
        account0_.id=2

    select
        orderlist0_.acc_id as acc_id4_0_0_,
        orderlist0_.id as id1_1_0_,
        orderlist0_.id as id1_1_1_,
        orderlist0_.order_no as order_no2_1_1_,
        orderlist0_.order_time as order_ti3_1_1_,
        orderlist0_.acc_id as acc_id4_1_1_ 
    from
        order_tab orderlist0_ 
    where
        orderlist0_.acc_id=?
```

### 全连接(full join)

> 完整外部联接返回左表和右表中的所有行。当某行在另一个表中没有匹配行时，则另一个表的选择列表列包含空值。如果表之间有匹配行，则整个结果集行包含基表的数据值。   

很少用，而且mysql不支持

### 内外连接的区别

inner和outer连接。两种类型的主要区别在于

- 即使是在连接条件不满足的情况下，外部连接也会在结果集内返回行，而内部连接不会在结果集类返回行 
- 当外部连接不满足连接条件时，通常返回一个表中的列，但是第二个表中没有返回值--为null。

## 抓去连接查询

> fetch连接，只使用一个查询语句就将相关联的对象或一组值的集合随着它们的父对象的初始化而被初始化

```java
String hql="select distinct a from Account a right join fetch a.orderList o  where a.id=2";
```

对应的sql语句，只有一条select

```sql
    select
        distinct account0_.id as id1_0_0_,
        orderlist1_.id as id1_1_1_,
        account0_.name as name2_0_0_,
        account0_.gender as gender3_0_0_,
        account0_.age as age4_0_0_,
        orderlist1_.order_no as order_no2_1_1_,
        orderlist1_.order_time as order_ti3_1_1_,
        orderlist1_.acc_id as acc_id4_1_1_,
        orderlist1_.acc_id as acc_id4_0_0__,
        orderlist1_.id as id1_1_0__ 
    from
        acc_tab account0_ 
    right outer join
        order_tab orderlist1_ 
            on account0_.id=orderlist1_.acc_id 
    where
        account0_.id=2
```

------

# Hibernate中Native SQL查询

> 直接使用数据库SQL语句进行查询
>
> 通过Session.createSQLQuery()来获取SQLQuery接口实例

- 原生SQL语句进行多表查询，可以用addEntity指定返回的数据类型

```sql
String sql="select {a.*},{o.*} from acc_tab a join order_tab o on a.id=o.acc_id";
SQLQuery query = session.createSQLQuery(sql).addEntity("a",Account.class).addEntity("o", Orders.class);
List list=query.list();
//下标为0是账户信息，下标为1是订单信息
for(int i=0;i<list.size();i++){
	Object[] obj=(Object[])list.get(i);
	Account acc=(Account)obj[0];
	System.out.println(acc);
	Orders order=(Orders)obj[1];
	System.out.println(order);
}
```

------

# Hibernate调用存储过程

存储过程/函数必须返回一个结果集，作为Hibernate能够使用的第一个外部参数。在程序中调用的代码

```java
session.createSQLQuery("{存储过程}");
```

```java
QLQuery query=session.createSQLQuery("{call findAccounts(:ids)}").addEntity(Account.class);
query.setInteger("ids", 1);
List<Account> list=query.list();
for(Account acc:list){
	System.out.println(acc);
}
```

------


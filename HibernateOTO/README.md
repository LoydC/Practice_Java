### 基于外键的单向一对一关联映射

> 基于外键的单向一对一实际上是多对一关联映射的特例

有两个实体类，Account和Address，每一个Address对应一个Account，Account为主控端，Address为被控端，步骤如下：

- 在主控段引用被控端的对象，并加入被控端的set/get方法

- 在主控端的hbm.xml文件中加入<many-to-one>标签，并指定多的一端的unique=true，这样就限制了多的一段多重性为一

  ```xml
  <many-to-one name="address" column="address_id" unique="true"></many-to-one>
  ```

- 在hibernate.cfg.xml中mapping这两个对象

- 建表时的sql语句

  - 生成两张表
  - acc_table01表中的address_id增加了唯一的约束
  - 创建两张表的主外键关系

  ```sql
      create table acc_table01 (
          id integer not null auto_increment,
          name varchar(255),
          address_id integer,
          primary key (id)
      )

      create table address_table01 (
          id integer not null auto_increment,
          address varchar(255),
          primary key (id)
      )

      alter table acc_table01 
          add constraint UK_d87hbvswjuy4mu25pj96usgaw  unique (address_id)

      alter table acc_table01 
          add constraint FK_d87hbvswjuy4mu25pj96usgaw 
          foreign key (address_id) 
          references address_table01 (id)
  ```

- 当插入数据时，将会有两条插入语句

- 若插入数据时两个Account共用一个Address对象，程序将会报错
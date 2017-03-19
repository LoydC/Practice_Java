### 基于主键的单向一对一关联映射

有两个实体类，Citizen和IDCard，每一个Citizen对应一个IDCard，步骤如下：

- 在主控端需要加入被控端对象的引用，并且加入set/get方法

- 在主控端的hbm.xml文件中，generator的class不再是native而是foreign，使用<one-to-one>标签

  ```xml
  <id name="id">
  	<generator class="foreign">
  		<param name="property">citizen</param>
  	</generator>
  </id>
  <one-to-one name="citizen" constrained="true"></one-to-one>
  ```

- 在hibernate.cfg.xml中mapping这两个对象

- 建表时的sql语句

  - 生成两张表
  - 两个id为相同的值

  ```sql
  	create table citizen_tab1 (
          id integer not null auto_increment,
          name varchar(255),
          primary key (id)
      )

      create table idcard_tab1 (
          id integer not null,
          no varchar(255),
          primary key (id)
      )

      alter table idcard_tab1 
          add constraint FK_2bxrettaaux1a562y39a3jhaj 
          foreign key (id) 
          references citizen_tab1 (id)
  ```

- 当插入数据时，将会有两条插入语句
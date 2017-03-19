### 基于主键的双向一对一关联映射

还是两个实体类，Citizen和IDCard，每一个Citizen对应一个IDCard，步骤如下：

- 在主控段引用被控端的对象，并加入被控端的set/get方法

- 在被控端引用主控端的对象，并加入主控端的set/get方法

- 在主控端的hbm.xml文件中，generator的class不再是native而是foreign，使用<one-to-one>标签

  ```xml
  <id name="id">
  	<generator class="foreign">
   		<param name="property">citizen</param>
  	</generator>
  </id>
  <one-to-one name="citizen" constrained="true"></one-to-one>
  ```

- 在被控端的hbm.xml文件中加入<one-to-one>标签

  ```xml
  <one-to-one name="idCard" />
  ```

- 在hibernate.cfg.xml中mapping这两个对象

- 建表时的sql语句

  - 生成两张表
  - 两个id为相同的值

  ```sql
      create table citizen_tab2 (
          id integer not null auto_increment,
          name varchar(255),
          primary key (id)
      )

      create table idcard_tab2 (
          id integer not null,
          no varchar(255),
          primary key (id)
      )

      alter table idcard_tab2 
          add constraint FK_clvskb79psq8bihium1cxia0y 
          foreign key (id) 
          references citizen_tab2 (id)
  ```

- 当插入数据时，将会有两条插入语句

- 若插入数据时两个Account共用一个Address对象，程序将会报错
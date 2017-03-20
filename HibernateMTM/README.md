## 多对多关联映射

> 在关系模型中，无法直接表达两个表之间的多对多关系。需要创建一个连接表，它同时参照两个表。

例如，学生和课程两个实体类，一个学生可以选择多个课程，一个课程也可以被多个学生选择，

- 在Course类中添加对Student集合的引用，并加入set/get方法

- 在Student类中添加对Course集合的引用，并加入set/get方法

- Course.hbm.xml中

  ```xml
  <set name="stuList" table="stu_course01">
  	<key column="course_id"></key>
      <many-to-many column="student_id" class="com.jikexueyuan.entity1.Student"></many-to-many>
  </set>
  ```

- Student.hbm.xml中

  ```xml
  <set name="courseList" table="stu_course01">
  	<key column="student_id"></key>	
      <many-to-many column="course_id" class="com.jikexueyuan.entity1.Course"></many-to-many>
  </set>
  ```

- 在SQL语句中，不但有course_tab01表和stu_tab01表，还会有一个stu_course01表

  相当于把两个多对多的关联映射分解成两个一对多关联映射

  ```sql
      create table course_tab01 (
          id integer not null auto_increment,
          name varchar(255),
          primary key (id)
      )

      create table stu_course01 (
          course_id integer not null,
          student_id integer not null,
          primary key (student_id, course_id)
      )

      create table stu_tab01 (
          id integer not null auto_increment,
          name varchar(255),
          gender varchar(255),
          primary key (id)
      )

      alter table stu_course01 
          add constraint FK_19y3ja4j3qell9u0r2xg8dseq 
          foreign key (student_id) 
          references stu_tab01 (id)

      alter table stu_course01 
          add constraint FK_1m6b55xhhjylvvsev04wi216 
          foreign key (course_id) 
          references course_tab01 (id)
  ```

第二种情况，有三个类，除了学生和课程两个实体类之外，还有一个学生和课程的关系实体类

- 在Course类中添加对Student集合的引用，并加入set/get方法

- 在Student类中添加对Course集合的引用，并加入set/get方法

- 在关系实体类中，添加课程和学生类的引用

- 课程类对应的xml

  ```xml
  <set name="stuList">
  	<key column="course_id"></key>
  	<one-to-many class="tech.loyd.entity2.StuCourse02"/>
  </set>
  ```

- 学生类对应的xml

  ```xml
  <set name="courseList">
  	<key column="student_id"></key>
  	<one-to-many class="tech.loyd.entity2.StuCourse02"/>
  </set>
  ```

- 关系实体类中的xml

  ```xml
  <many-to-one  name="stu"  column="student_id"/>
  <many-to-one name="course"  column="course_id"/>
  ```

  ​
# Hibernate对集合属性的操作

> Hibernate 框架中提供了很丰富的数据类型，来简化开发人员的困扰。比如在 JavaSE 中提供了Collection、Set、List、Map 等集合，Hibernate 为了也能让这些集合能够通过 ORM 技术和关系型数据库进行关联，Hibernate 中也提供了针对集合属性的关联映射。 

------

## Set集合操作

- 在对应的实体中加入Set集合的成员变量,并加入set/get方法

  ```java
  private Set<String> hobby;

  public Set<String> getHobby() {
  	return hobby;
  }

  public void setHobby(Set<String> hobby) {
  	this.hobby = hobby;
  }
  ```

- 配置相应的hbm.xml文件

  - 在<set></set>中设置
  - name属性：持久化对象中的set属性的属性名对应
  - table属性：新建保存该set集合数据的数据表名
  - key:子元素：在数据库中保存set数据的key/id
  - element子元素：保存set属性的数据标签，通常都要设置type类型

  ```xml
  <set name="hobby" table="hobby_tab">
      <key column="student_id"></key>
      <element type="string" column="hobby"></element>
  </set>
  ```

------

## List集合操作

- 在对应的实体中加入List集合的成员变量,并加入set/get方法

  ```java
  private List<String> hobby;

  public List<String> getHobby() {
  	return hobby;
  }

  public void setHobby(List<String> hobby) {
  	this.hobby = hobby;
  }
  ```

- 配置相应的hbm.xml文件

  - 在<list></list>标签中设置
  - name属性：持久化对象中的List属性的属性名对应
  - table属性：新建保存该集合数据的数据表名
  - key:子元素：在数据库总保存list数据的key/id
  - element子元素：保存list属性的数据标签，通常都要设置type类型
  - list-index:子元素：保存在list的属性保存数据的下标索引

  ```xml
  <list name="hobby" table="hobby_tab_list">
      <key column="student_id"></key>
     	<list-index column="position"></list-index>
      <element type="string" column="hobby"></element>
  </list>
  ```

------

## Collection集合操作

- 在对应的实体中加入Collection集合的成员变量,并加入set/get方法

  ```java
  private Collection<String> hobby;

  public Collection<String> getHobby() {
  	return hobby;
  }

  public void setHobby(Collection<String> hobby) {
  	this.hobby = hobby;
  }
  ```

- 配置相应的hbm.xml文件

  - 在<bag></bag>标签中配置
  - name属性：持久化对象中的set属性的属性名对应
  - table属性：新建保存该set集合数据的数据表名
  - key:子元素：在数据库总保存set数据的key/id
  - element子元素：保存set属性的数据标签，通常都要设置type类型
  - 使用<idbag></idbag>标签，还需要设置Collection_id子元素，添加Collection属性表的id

  ```xml
  <!--若删除Collection中的一个元素，用bag标签，会先将表中的所有Collection
  		删除，再添加存在的元素-->
  <!--<bag name="hobby" table="hobby_tab_c">
  	<key column="student_id"></key>
      <element type="string" column="hobby"></element>
  </bag>-->
  		     
  <idbag name="hobby" table="hobby_tab_c">
      <collection-id type="string" column="hobby_id">
      	<generator class="uuid"></generator>
      </collection-id>
      <key column="student_id"></key>
      <element type="string" column="hobby"></element>
  </idbag>
  ```

------

## Map集合操作

- 在对应的实体中加入Map集合的成员变量,并加入set/get方法

  ```java
  private Map<String,String> hobby;

  public Map<String,String> getHobby() {
  	return hobby;
  }

  public void setHobby(Map<String,String> hobby) {
  	this.hobby = hobby;
  }
  ```

- 配置相应的hbm.xml文件

  - 在<map></map>标签中配置
  - name属性：持久化对象中的set属性的属性名对应
  - table属性：新建保存该set集合数据的数据表名
  - key:子元素：在数据库总保存set数据的key/id
  - element子元素：保存set属性的数据标签，通常都要设置type类型
  - map-key子元素：map中保存数据的key

  ```xml
  <map name="hobby" table="stu_map_hobby">	
      <key column="student_id"></key>
     	<map-key column="keycolumn" type="string"></map-key>
     	<element type="string" column="hobby"></element>
  </map>
  ```


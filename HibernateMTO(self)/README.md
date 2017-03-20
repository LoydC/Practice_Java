### 双向一对多自身关联

以类别为例，类别可能有父类别和子类别，其中子类别可能就不止一个，是一个类别的集合

- Category类相当于把上面的两个类的结合

- 在Category类中的引用如下：

  ```java
  	//如果把Category看成是多的一端
  	private Category parent;
  	
  	//如果把Category看成是少的一端,则需要对多的一端进行对象集合的引用
  	private Set<Category> clist;
  ```

- 在对应的xml文件中

  ```xml
         <set name="clist" inverse="true">
         		<key column="parent_id"></key>
         		<!-- 配置一对多的关联映射 -->
         		<one-to-many class="com.jikexueyuan.entity.Category"/>
         </set>
         <many-to-one name="parent" column="parent_id"></many-to-one>
  ```

  ​
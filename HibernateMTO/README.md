### 单向一对多关联映射

> 单向一对多关联映射，在对象关系映射文件中使用<one-to-many>标签映射，开发中不常见

两个实体类，Account和Order，一个账户可以对应多个订单，

- 在Account对象中加入对Order集合的引用，并加入set/get方法

  ```java
  private Set<Orders> setOrders;
  ```

- 在对应的xml文件中添加对集合的操作，并在其中加入<one-to-many>标签

  ```java
  <set name="setOrders">
      <key column="acc_id"></key> 		
      <one-to-many class="tech.loyd.entity.Orders"/>
  </set>
  ```

- 在hibernate.cfg.xml中mapping这两个对象
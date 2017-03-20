### 双向多对一（一对多）关联

>  双向多对一（一对多）关联是最常见的关联关系

两个实体类，Account和Order，一个账户可以对应多个订单，需要双向关联

- 两个实体类都需要互相引用

- 少的一端需要对多的一端进行集合的引用，这里是Account中加入Set<Order>集合

- 在Account.hbm.xml中，casecade选项为级联，inverse为反转关联关系的维护权，一般应交给多端来维护，两个结合使用insert和update会级联，但是关联关系不会反转

  ```xml
  <set name="setOrders" cascade="all" inverse="true">
  	<key column="acc_id"></key>
      <one-to-many class="tech.loyd.entity.Orders"/>
  </set>
  ```

- 在Order.hbm.xml中

  ```xml
  <many-to-one name="account" column="acc_id"></many-to-one>
  ```

- 在hibernate.cfg.xml中mapping这两个对象
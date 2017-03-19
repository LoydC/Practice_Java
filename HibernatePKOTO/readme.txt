1.基于主键的单向一对一关联映射
	对象模型
		主控端:添加对被控端对象的引用
		被控端:没有变化
	
	关系模型:
		主控端:主控端的主键和被控端的主键进行关联
		被控端:没有变化
	
2.对象关系映射文件配置	
	主控端的hbm.xml文件中，需要指定当前的主键所引用的外键值
	<!-- 生成对象唯一的OID标示符 -->
		<id name="id">
			<!--表示当前主键引用另一个外键作为OID值-->
			<generator class="foreign">
				<param name="property">citizen</param>
			</generator>
		</id>
	<!-- 
			constrained 告诉当前主键，你的值时采用另个表中的主键的值
			当前主键对于有关系的另一个表来说就是外键。
		 -->
		<one-to-one name="citizen" constrained="true"></one-to-one>
		
	被控端:hbm.xml文件没有发生变化
		
	
	双向对象模型
	主控端:添加对被控端对象的引用
	被控端:添加主控端对象的引用
	
	关系模型:
	主控端:主控端的主键和被控端的主键进行关联
	被控端:没有变化
#### 第十三周作业
---

##### 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
[地址为:](mysqlTest/src/main/java/matt/mysql/JDBCApplication.java)


#### 测试表格
| 测试方式 | 用时 |
|------ | ------|
|spriing jDBC LinkedList| 290575ms|
|spriing jDBC ArrrayList| 310003ms| 

Summary 
同等插入方式下,LinkedList 比ArrayList快(废话)


#### 第十四周作业
-----
##### 2、(必做)读写分离-动态切换数据源版本1.0 3、(必做)读写分离-数据库框架版本2.0

动态切换包装了一个spring boot start:[地址:](mysqlTest/src/main/java/matt/mysql/JDBCApplication.java)， 通过注解 [SwitchDs](switchDataSource/src/main/java/matt/dataSource/annotation/SwitchDs.java) 来控制数据源的切换，
和是否进行从库的负载均衡，详见[MyDataSourceAspect.java](switchDataSource/src/main/java/matt/dataSource/aspect/MyDataSourceAspect.java)

修改[application.yml](mysqlTest/src/main/resources/application.yml),加载[application-datasource.yml](mysqlTest/src/main/resources/application-datasource.yml)文件， 

3.（必做）读写分离 - 数据库框架版本 2.0
修改[application.yml](mysqlTest/src/main/resources/application.yml),加载[aoolication-sharding.yml](mysqlTest/src/main/resources/aoolication-sharding.yml)文件即可 测试看到效果
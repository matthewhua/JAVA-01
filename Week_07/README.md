第十三周作业
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
读写分离多数据源

自定义注解实现数据源切换,自定义注解[SwitchDs](switchDataSource/src/main/java/matt/dataSource/annotation/SwitchDs.java)
,修改[application-datasource.yml](mysqlTest/src/main/resources/application-datasource.yml)

读写分离使用shardingsphere-jdbc,修改[aoolication-sharding.yml](mysqlTest/src/main/resources/aoolication-sharding.yml)




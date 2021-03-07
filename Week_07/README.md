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


### Java Lambda

  ------
> 只有一个非默认方法的接口可以转换成 lambda 的写法，如:
> Runnable run()
>    Callable T call()
>   Java 的类库提供了接口满足日常开发
>   Consumer -> void accept(T t)
>
>   Supplier -> T get()
   
>   Function -> R apply(T t )
   
>   Predicate -> boolean test(T t)
   
>   最终目的是为了简化开发,写更少的代码做更多的事

### SPI

-----
Service Provider Interface
     
     在resources目录下创建 META-INF/services
     在services 目录下创建 接口的全类名的文件, 在文件中写入 接口的实现类
     ServiceLoader.load(XXXInterface.class) 就能加载到


#### 作业
##### 必做作业:简单商城Sql [地址](商城sql.sql)

#### Guava正在学习中
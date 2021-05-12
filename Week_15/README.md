## 课程总结
### 1. JVM

### 1.1 JAVA与 JVM 的关系

> 从Java 语言来说, Java 是一种面向对象、静态类型、编译执行，
> 有 VM/GC 和运行时、跨平台的高级语言。,所以运行就必须要有JVM来进行运行,以下是java源代码怎样在JVM上运行的
> ![img.png](img.png)
### 1.2 JMM(Java Memory Model)
>
> JMM是JVM里非常重要的点,只有搞明白才能掌握JVM.
>
> 以下是JMM的重点:

- CPU 乱序执行 
- volatile 关键字
-  原子性操作 
- 内存屏障
  程序计数器、本地方法栈、线程栈、堆、非堆（元空间、方法区）
  本地方法栈：加载的native修饰的方法
  线程栈：每个线程都会分配一块线程栈空间，一个方法会分配一个栈帧，栈帧包含了局部变量表、操作数栈、动态链接和方法出口
  非堆：存class字节码，常量，静态变量，类型信息
  堆：存object对象，分年轻代（1/3）老年代（2/3）;年轻代分eden、survive（from，to）（8：1：1）
>
> 栈和堆：区别在于栈在编译器就确定了，连续的空间，存放的数据不一样。
> 
>内存分配方式：指针碰撞或空闲列表，收集器带有压缩整理用指针碰撞
> 
>对象的定位方式：直接指针或句柄（指向指针）
> 
> 引用类型：强，软（内存溢出回收），弱（下次GC回收），虚
> 
> 如何判断回收与否：引用计数器法，可达性分析算法（GCROOT往下搜索）
> 
> 垃圾回收算法：标记清除，复制算法，标记整理，分代算法（新生代使用复制清除-快，老年代使用标记整理-stw）
> 
> 类装载方式：隐式（new），显示（class.forname）;
> 
> 类装载过程：加载，验证，准备，解析，初始化；
> 
> 双亲委派：一直往上回溯到父类加载器，直到找不到父类






### 1.3  JVM检测工具

>在开发或线上运行时,我们要熟悉性能检测工具,常用的检测工具如下
>
>jstat*, jmap, jstack,jcmd
>
>另外还有图形化工具
>
>jconsole, jvisualvm, jmc. .其中jmc还有飞行记录器功能.
>
>jvisualvm 的插件VisualGC,可以去插件中心下载

#### 1.3.1 内存分析工具

- Eclipse MAT
- jhat



### 1.4 JVM的GC

#### 1.4.1 GC的背景与一般原理
![让人](image-20210509205026222.png)


#### 1.4.2 Java 目前支持的所有 GC 算法

1. 串行 GC（Serial GC）: 单线程执行，应用需要暂停； 
2. 并行 GC（ParNew、Parallel Scavenge、Parallel Old）: 多线程并行地执行垃圾回收， 关注与高吞吐；
3. CMS（Concurrent Mark-Sweep）: 多线程并发标记和清除，关注与降低延迟；

4. G1（G First）: 通过划分多个内存区域做增量整理和回收，进一步降低延迟； 
5. ZGC（Z Garbage Collector）: 通过着色指针和读屏障，实现几乎全部的并发执行，几 毫秒级别的延迟，线性可扩展；
6. Epsilon: 实验性的 GC，供性能分析使用；
7.  Shenandoah: G1 的改进版本，跟 ZGC 类似



## 2. NIO

#### 2.1 IO模型


![](image-20210509210626864.png)
NIO 可以是 new IO 或者NON BLOCKING IO。 而在java 中目前最火和性能最好IO 框架就是 Netty.而netty 目前的使用最经典的就是I/O 复用模型，所以下面是经典reactor 模型，接收和发送信号的流程图


![ss](image-20210509211954230.png)
> ### 大话解释
>
> • 同步阻塞 直接排队，别的啥也干不成，直到 轮到你使用打印机了，自己打印文件 
>
> • Reactor 拿个号码，回去该干嘛干嘛，等轮 到你使用打印机了，店主通知你来用 打印机，打印文件
>
>  • Proactor 拿个号码，回去该干嘛干嘛，等轮 到你使用打印机了，店主直接给你打 印好文件，通知
>
> ​	你来拿。

#### 2.2 Netty 特性

高性能的协议服务器:

 • 高吞吐 • 低延迟 • 低开销 • 零拷贝 • 可扩容 

• 松耦合: 网络和业务逻辑分离 

• 使用方便、可维护性好



- 特点：封住了很多nio的细节，channelHandler强大的定制能力，预置了很多编解码功能

- 为啥高性能：nio，零拷贝，直接内存重复使用，串行化读写，支持高性能序列化协议

- netty的线程模型：Reactor线程模型，boss线程池处理accept事件，封装到nioSocketChannel，交给work线程池去负责请求的读写事件，hander实现具体的逻辑

- 拆包、粘包解决方法：

- - 消息定长：FixedLengthFrameDecoder类，
  - 行分隔符类：LineBasedFrameDecoder
  - 自定义分隔符类 ：DelimiterBasedFrameDecoder

- 零拷贝：

- - 直接采用directbuffers，直接在堆外进行socket读写，不需要从堆内存读到直接内存。少了用户态和内核态的转换
  - 直接聚合多个bytebuffer而不用通过内存拷贝的方式
  - 文件传输用到了transferTo直接将文件从缓冲区发送到channel

- 重要组件：

- - bootstrap、serverBootstrap
  - channel：通信组件
  - eventloop：事件处理线程
  - channelPipeline：
  - channelFuture：获取执行结果
  - channelHandler：入站出站的逻辑

- 发送消息方式：channelFuture.channel().writeAndFlush(request)或在handler的ChannelHandlerContext中

目前我所在的游戏服务器就使用了Netty 作为网络通信工具
![学习](image-20210509213103378.png)


### 3.并发编程



[并发编程思维导图](Java%20Concurrency.xmind) **这个更详细哦**

- 并发编程三要素

可见性、原子性、有序性，

缓存导致的可见性问题，volatile禁用缓存

线程切换带来的原子性问题，锁（管程的具体实现）

编译优化带来的有序性问题，new 对象时很有可能是先给地址，在实例化对象

其实缓存、线程、编译优化的目的和我们写并发程序的目的是相同的，都是提高程序性能。

但是技术在解决一个问题的同时，必然会带来另外一个问题，所以在采用一项技术的同时，一定要清楚它带来的问题是什么，以及如何规避。

- 多线程优劣势

优势：提高CPU的利用率

劣势：线程是要占用额外空间的，协调更麻烦，资源共享问题

- 进程和线程的区别

一个服务是一个进程，服务包含很多线程，进程之间的资源是相互独立的

- 上下文切换

不同的线程获取CPU的时间片，先移除上个线程的资源缓存，再加载这次线程的资源缓存。

- 用户线程和守护线程
- 死锁：互斥，资源无法自己释放，也不能别其他线程释放，循环等待
- 创建线程

继承thread，实现runnable（传入new thread），实现callable（传入futureTask，传入thread，future.get同步获取结果），使用exectors。

- 线程的生命周期

new runnable running block（等待，同步加锁，其他） dead

- 线程调度模型：分时调度模型和抢占调度模型
- sleep（不释放锁），wait（释放锁），notify，notifyAll
- 中断线程：interrupted，切换线程为中断状态，不会终止，需要在外监听。
- 如何保证多线程安全：原子类，加锁
- object有个finalize方法，垃圾回收器只显示会先调用该方法；在调用了native方法后需要在finalization里去释放内存
- 有时候为了提高性能CPU或者编译器会指令重排，对于多线程来说有时候必须通过内存屏障保证强一致性；引入***\*as-if-serial语义和happens-before规则\****
- synchronized修饰静态方法锁class，修饰实例方法锁实例对象
- 加了synchronized修饰的方法，反汇编出来后可以看到指令被monitorenter和2个monitorexit包裹，最后一个保证一定能退出
- 锁的升级：先获取偏向锁，第一次比较threadid，如果不一致升级为轻量级锁，自旋一定次数来获取锁，如果还没有获取到锁，就升级为重量锁，等待。这个是继java6之后的重大优化，提升了synchronized的效率。
- synchronized和lock的区别：加锁范围不一样，释放锁的方式
- volatile保证可见性和禁止指令重排，提供happens-before保证
- 乐观锁和悲观锁
- CAS自旋：多见于原子类，容易产生ABA的问题（通过加版本号解决），高并发的时候CPU消耗比较大，只能保证一个变量的原子性；
- 死锁与活锁：活锁是一直无法满足条件，一直等待
- AQS：并发工具类的鼻祖，构建锁和同步器的框架，使用了模板方法模式；
- AQS资源共享方式：独占reentrantLock，共享semaphere，countDownLatch，cyclicBarrier，readWriteLock
- ConcurrentHashMap：1.6 segment分段锁给hash槽加锁，1.8开始使用CAS+synchronized
- CopyOnWriteArrayList：通过副本的方式类实现线程安全，适合读多写少的场景，毕竟复制的代价还是很高的
- ThreadLocal：线程本地变量，在线程内部共享，内部维护了一个ThreadLocalMap，为了防止内存泄漏，建议set，get使用完，最后remove。
- ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
- LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
- PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
- DelayQueue：一个使用优先级队列实现的无界阻塞队列。
- SynchronousQueue：一个不存储元素的阻塞队列。
- LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
- LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
- ConcurrentLinkedQueue：单端非阻塞
- ConcurrentLinkedDeque：双端非阻塞
- 线程池创建方式

newSingleThreadExecutor：创建一个单线程的线程池

newFixedThreadPool：创建固定大小的线程池

newCachedThreadPool：创建一个可缓存的线程池

newScheduledThreadPool：创建一个大小无限的线程池

- 线程池优点：降低资源消耗，提高响应速度，统一管理
- 线程池的状态：running shutdown stop tidying terminated
- 线程池submit和execute区别：submit可以执行runnable和callable，可以拿到future
- 阿里推荐使用ThreadPoolExecutor，因为上面几种executor容易导致OOM
- ThreadPoolExecutor核心参数：核心数，最大线程数，工作队列，线程时间，时间单位，new ThreadFactory，队列放满拒绝策略handler
- 拒绝策略：直接拒绝抛异常、当前线程执行任务、不处理丢弃掉、丢弃最早未处理的任务，建议自己来维护策略


### 4. Spring 和 ORM 框架
 Java 程序猿一定绕不开的框架就是 Spring了吧,无论是工作中, 还是在学习中, 或多多或少都接触过Spring,因为Spring 发展出了强大的生态家族,不管是之前的单机
小应用,到后来的SOA, 后面的微服务, 再到现在及未来的趋势的 CNCF (Cloud Native),Spring都有对其的支持,可见Spring这个庞然大物是多么可怕.

#### 4.1 Spring Framework
以下是Spring 官网什么是Spring Framework
>  Spring makes it easy to create Java enterprise applications.
It provides everything you need to embrace the Java language
in an enterprise environment, with support for Groovy and
Kotlin as alternative languages on the JVM, and with the
flexibility to create many kinds of architectures depending on
an application’s needs.
>
**Spring最重要组成模块：**

spring-core、spring-beans、spring-context、spring-jdbc、spring-aop、spring-web、spring-test

**Spring用常用的设计模式**

工厂模式、单例模式、代理模式、模板方法、观察者模式

**Spring IOC容器**

把本该有程序员自己操控对象的事情交给容器来完成，
IOC容器负责创建对象，管理对象（依赖注入DI），装配对象，配置对象，以及整个对象的生命周期；
IOC实现机制：工厂模式+反射

**beanFactory和ApplicationContext的区别**
>ApplicationContext是beanFactory的子接口，在beanFactory的基础上还提供更多的功能；
> 
> 加载方式：beanFactory是懒加载，ApplicationContext是在容器启动时就加载所有的bean；
> 
> 创建方式：beanFactory通常用编程的方式被创建，ApplicationContext还可以用声明的方式创建；
>
> 注册方式：都支持beanPostProcessor的使用，beanFactory需要手动注册，ApplicationContext自动注册。
> 
> 依赖注入：在没有ioc来管理这些的时候，bean的注入将是一个特别繁琐的事情，现在全权由IOC容器来处理bean之间的依赖关系
> 
>依赖注入的方式：接口注入（废弃），构造器注入，setter注入



**spring支持的几种bean作用域：**
> singleton（单例），prototype（多例），request，session，global-session


单例bean是线程安全的吗：不是，这里要排除无状态的bean，有状态的bean就不是的了

bean的生命周期：实例化-属性填充-BeanNameAware.setBeanName-BeanFactoryAware.setBeanFactory-ApplicationContextAware.setApplicationContext-BeanPostProcessor前置方法-initializingBean-BeanPostProcessor后置方法-开始使用bean-DisposableBean.destroy
**Bean的自动装配方式：** 
byName，byType，构造函数（参数byType），自动探测
@Autowired：AutowiredAnnotationBeanPostProcessor后置处理器会扫描这一类的注解
**事务传播行为：**
>有就加入，没有就创建（最常用的）
有就加入，没有就没有
有就加入，没有抛异常
总是用新事务开启
不用事务，有事务就挂起
不用事务，有事务就抛异常
有事务嵌套执行，没有就创建

> 
事务的隔离级别：未提交读，已提交读，可重复读，序列化，都喜欢折中一点的
### 4.1.1 AOP
AOP：在不影响业务的情况下，进行横向拓展，实现方式就通过代理的方式
静态代理和动态代理：生成代理类的时机，静态是在编译器就生成了，动态是在使用的时候动态的织入
JDK动态代理：只提供接口级别的代理，proxy类，反射的方式获取目标方法，然后编织，invocationHandler最后生成代理对象
CGlib动态代理：通过继承的方式来生成一个子类，并覆盖需要增强的代码。
通知类型：前置，后置，返回，异常，环绕
切面：通知和切点，横切的逻辑定义，也包含连接点的定义

### 4.2 Spring Boot
**Spring boot 是Spring Framework的一个小进阶版**
- 特点：提供各种starter，开箱即用，简化了参数配置提供默认配置，再也不用担心各种版本间的搭配问题
- 核心注解：@SpringBootApplication往下debug可以看到@SpringBootConfiguration实现配置文件的功能、@EnableAutoConfiguration开启自动配置、@ComponentScan组件扫描
    - 如何写一个Starter：
    - 依赖spring-boot-starter
    - 编写属性类
    - 编写配置类@Configuration，@ConditionalOnClass，@EnableConfigurationProperties（导入属性类）
    - META-INF/spring.factories 里定义的自动配置类
    - Maven打包
  
- 配置文件加载顺序：properties-yaml-系统环境变量-命令行参数，依次加载后面覆盖前面。
- 多个配置文件：启动项里加spring.profiles.active，来激活对于版本的配置
- Security和Shiro：shiro小而精属于轻量级，security功能强大天然集成Srping
- 跨域问题：实现WebMvcConfigurer，重写addCorsMappings
- springboot打的jar包和普通jar有什么区别：可执行但是不可以引用，原因是路径多了BOOT-INF/classes。可以在pom文件里面添加spring-boot-maven-plugin插件

### 4.3 Spring Cloud
**Spring Cloud 是开源的微服务一站式 解决方案的.**
以下介绍一下现在比较热门的组件

- Config：集中配置管理工具，分布式系统中统一的外部配置管理，默认使用Git来存储配置，可以支持客户端配置的刷新及加密、解密操作。
- Netflix：Netflix OSS 开源组件集成，包括Eureka、Hystrix、Ribbon、Feign、Zuul等核心组件。
  - Eureka：服务治理组件，包括服务端的注册中心和客户端的服务发现机制；
  - Ribbon：负载均衡的服务调用组件，具有多种负载均衡调用策略；
  - Hystrix：服务容错组件，实现了断路器模式，为依赖服务的出错和延迟提供了容错能力；
  - Feign：基于Ribbon和Hystrix的声明式服务调用组件；
  - Zuul：API网关组件，对请求提供路由及过滤功能。
-  Bus：用于传播集群状态变化的消息总线，使用轻量级消息代理链接分布式系统中的节点，可以用来动态刷新集群中的服务配置。
-  Consul：基于Hashicorp Consul的服务治理组件。
-  Security：安全工具包，对Zuul代理中的负载均衡OAuth2客户端及登录认证进行支持。
-  Sleuth：Spring Cloud应用程序的分布式请求链路跟踪，支持使用Zipkin、HTrace和基于日志（例如ELK）的跟踪。
-  Stream：轻量级事件驱动微服务框架，可以使用简单的声明式模型来发送及接收消息，主要实现为Apache Kafka及RabbitMQ。
-  Task：用于快速构建短暂、有限数据处理任务的微服务框架，用于向应用中添加功能性和非功能性的特性。
-  zookeeper：基于Apache Zookeeper的服务治理组件。
-  Gateway：API网关组件，对请求提供路由及过滤功能。
-  OpenFeign：基于Ribbon和Hystrix的声明式服务调用组件，可以动态创建基于Spring MVC注解的接口实现用于服务调用，在Spring Cloud 2.0中已经取代Feign成为了一等公民。
-  和dubbo的区别：服务调用的方式dubbo是RPC，spring cloud是RestAPI，默认注册中心dubbo是zk，springcloud是eureka

### 4.4 ORM框架
####  Mybatis

-  什么是Mybatis
-  持久层框架，半ORM框架，数据库和对象的映射
-  优点：xml适合统一管理，国内使用多，相比hibernate分库分表的事务管理要方便
-  hibernate可以说是对象就是表结构，直接对对象进行操作适合简单的crud
-  过程：
-  创建SqlSessionFactory
-  通过SqlSessionFactory创建SqlSession
-  通过sqlsession执行数据库操作
-  调用session.commit()提交事务
-  调用session.close()关闭会话
-  预编译：预编译后的sql大部分情况下是可以直接执行的，可以合并多次操作，可以重复利用，防止sql注入
-  有哪些executor：
-  simpleExecutor：每次执行都用新的statement
-  reuseExecutor：复用statement
-  batchExecutor：缓存多个statement
-  如何配置executor：mybatis.configuration.default-executor-type=simple
-  支持延迟加载：也是配置打开就好，原理：使用A是需要调用B，发现B还没有，则把B先查出来再执行前面的操作
-  "#和$的区别："
    -  "#{}是占位符，预编译处理；${}是拼接符，字符串替换，没有预编译处理。"
    -  Mybatis在处理#{}时，#{}传入参数是以字符串传入，会将SQL中的#{}替换为?号，调用PreparedStatement的set方法来赋值。
    
    -  Mybatis在处理时 ， 是 原 值 传 入 ， 就 是 把 {}时，是原值传入，就是把时，是原值传入，就是把{}替换成变量的值，相当于JDBC中的Statement编译
    
    -  变量替换后，#{} 对应的变量自动加上单引号 ‘’；变量替换后，${} 对应的变量不会加上单引号 ‘’
    
    -  "#{} 可以有效的防止SQL注入，提高系统安全性；${} 不能防止SQL 注入"
    
    -  "#{} 的变量替换是在DBMS 中；${} 的变量替换是在 DBMS 外"
  
-  like语句：外面加双引号，或CONCAT函数，或用bind标签
  
-  mapper中传参方法：顺序传#{0},#{1}，@Param(“aaa”)传#{aaa}，Map传，实例对象传
  
-  批量操作：foreach
  
-  自动生成的mapper目录，建议继承接口去自定义方法，那样不用对象被覆盖的问题
  
-  一对一，一对多：通过在resultMap里面的association，collection
  
-  动态标签：trim|where|set|foreach|if|choose|when|otherwise|bind
  
-  分页：第一种sql分页（limit offset），第二种内存分页（rowbound）
  
-  缓存：一级缓存默认开启，作用域session；二级缓存<cache/>，对象需要实现序列化，作用域namespace


### Sql 与 mysql
- 结构

  - 连接器：管理连接
  - 查询缓存：只要有表更新，就会清空该表的查询缓存，极不稳定
  - 分析器：词法分析，语法分析
  - 优化器：执行计划生成，索引选择
  - 执行器：操作引擎，返回结果
  - 存储引擎：innoDB（默认），MyISAM，Memory

- 日志

  - redolog（重做日志）：innoDB日志，循环写
  - binlog（归档日志）：MySQLserver层日志，持久化到磁盘，可用做数据恢复
  - 两阶段提交：过程是存储引擎先写入redolog记为prepare状态，再在执行器生成binlog，最终落盘，redolog状态改为commit

- 事务隔离级别

  - 读未提交
  - 读提交
  - 可重复读：innoDB默认的隔离事务，开始前会对数据打一个快照
  - 串行化

- 索引

  - 哈希索引：key-value，联想hashmap的数据结构，适合等值
  - 有序数组索引：适合等值和范围查找，更新比较慢，适合不用更改的历史数据
  - 二叉树:
  - N叉树：
  - B树：和B+的区别就在于，B树每个节点都存有数据，导致每节点页的K-V不多，而B+只有叶子节点页存数据，那么一个节点页就能达到1170个k-v
  - B+树：建议树高不要超过3层，一个节点页16k，一个bigint的主键，长度8字节+指针6字节，16kb/14b=1170，一个叶子节点可以存放1170个K-V，树高3层时，假设一条数据1kb，叶子节点页可以存放16条数据，1170*1170*16=21902400（2千万条）
  - 叶子节点页分裂：自增主键顺序写很好的避免了这个问题，非自增的索引就得导致B+树叶子节点页分裂
  - 聚簇索引和非聚簇索引
    - 聚簇：叶子节点保存的是整行数据（主键索引）
    - 非聚簇：叶子节点保存的是主键
  - 回表：某个索引是非聚簇索引，它的叶子节点存的主键，再根据主键去主键索引查找数据
  - 覆盖索引：select字段和where字段全部包含在某个联合索引中，那么避免了回表
  - 最左匹配原则：（a,b,c）联合索引，可以加速 a    a,b    a,b,c包括字段的最左原则，解释了like ‘aa%’可以被加速，同时可以把较大字段放在前面，节约去单独创建的空间
  - 索引下推：基于联合索引，如果where中的字段存在于索引字段中，那么可以直接判断条件是否满足，不用再回表进行查询比较
  - 普通索引：在等值查找时，会比唯一索引多一次查找；对于更新操作，如果数据页在内存中就直接更新，如果没有，写到change_buffer里面，等下次查询到该数据页时，把数据页读到内存，同时执行change_buffer有关数据页的操作，这个过程叫merge，系统也会定时merge。类似于批处理
  - 唯一索引：在等值查找时，找到结果直接返回；对于更新，由于需要判断唯一性，一定要取一次数据

- 锁

  - 全局锁：flush tables with read lock ，整库备份使用，在连接断开后会自动释放，（innoDB备份时可以开启single-transaction快照功能，可以不用FTWRL）
  - 表锁：
    - 表锁：lock tabes T1 read/write; unlock T1
    - 元数据锁：表数据增删改查是默认加上该读锁，表结构变更时加写锁，读读不互斥，读写、写写互斥；有个场景就是在数据库数据有个长事务的时候，更改表结构是会阻塞后续的数据库增删改查也会被阻塞，这个时候可以再alter table的时候加个等待时间，及时释放锁。
  - 行锁：执行update的时候就会加上行锁，直到commit后才会释放

- change_buffer和redolog

  - change_buffer：主要节省随机读的IO消耗
  - redolog：主要解决随机写的IO消耗

- 为什么表数据删掉之后，表文件大小不变

  - delete 只是把数据槽清空，然后标记为复用，空间并未重排，需要alter table T engine = innoDB，5.6版本支持online模式不会暂停增删改

- count(字段)<count(id)<count(1)≈count(*)

- order by 原理

  - 单一索引，先查出来到临时表，然后排序，输出到结果集
  - rowid排序会导致回表
  - 联合索引，实现覆盖索引，最快但是维护麻烦，显示很难有这种理想状态

- 不走索引的例子

  - where 函数运算
  - like %aaa%，违反最左原则
  - 隐式类型转换
  - 两个表字符集不同，join时候隐式转换字符集
  - 诡异的例子，优化器记录的行数，官方误差有40%-50%，有时候优化器误判认为走主键扫描会比索引更快

- 查询一条慢
  - 等表锁
  - 等行锁
  - 事务开始还未查询，其他事务更新这条记录产生了很多版本日志，当前事务查询的时候要每个版本回滚回去。

- 间隙锁和next-key lock

  - 间隙锁：在可重复读下才有间隙锁
  - next-key lock：前开后闭，遇到等值优化的时候，右开区间，可以认为就是行锁加间隙锁


### 6. 分库分表

随着数据量的增大，读写并发的增加，系统可用性要求的提升，单机 MySQL 面临：

1、容量有限，难以扩容

2、读写压力，QPS 过大，特别是分析类需求会影响到业务事务

3、可用性不足，宕机问题



- 日志写入过程

  - binlog：binlog cache(每个事务都有)->写到文件系统的page cache->落盘，可以设置0~N积累多少
  - binlogcache再落盘
  - redolog：redolog buffer（全局）->page cache->落盘，0每次事务只放到buffer，1每次事务都落盘，2每次事务放到page cache，innoDB会每隔1秒把buffer到落盘，buffer超过一半是会强制落盘，还有一种是做顺风车捎带一起落盘

- 主从复制

  - 5.6开始就是多线程，conditionor在分发策略中同一行的事务会给同一个worker，一个事务必须始终由一个worker完成

分库分表

​	要结合业务立方体进行 扩展立方体

- X 轴：通过 clone 整个系统复制，集群 
- Y 轴：通过解耦不同功能复制，业务拆分 
- Z 轴：通过拆分不同数据扩展，数据分片![img_1.png](img_1.png)

**具体分库分表的框架和中间件**
Java 框架层面：
- TDDL
- Apache ShardingSphere-JDBC

中间件层面：
- Apache ShardingSphere-Proxy
- MyCat/DBLE


### 7. RPC和微服务
#### 7.1 RPC
微服务的两大基石就是 RPC 和MQ

- RPC是远程过程调用（Remote Procedure Call）的缩写形式
- RPC的概念与技术早在1981年由Nelson提出
  - 参数的编码/译码及网
  络通讯是由stub和RPC runtime完成的

· RPC原理
核心是代理机制。
> 1.本地代理存根: Stub
>
> 2.本地序列化反序列化
> 
> 3.网络通信
> 
> 4.远程序列化反序列化
> 
> 5.远程服务存根: Skeleton
> 
> 6.调用实际业务服务
> 
> 7.原路返回服务结果
> 
> 8.返回给本地调用方
![img_2.png](img_2.png)



RPC是基于接口的远程服务调用
Java下，代理可以选择动态代理，或者AOP实现
- C#直接有远程代理

rpc 走向服务化 -> 微服务架构
其中需要考虑的问题有
1、多个相同服务如何管理？
2、服务的注册发现机制？
3、如何负载均衡，路由等集群功能？
4、熔断，限流等治理能力。
5、重试等策略
6、高可用、监控、性能等等。
![img_3.png](img_3.png)

### 7.2 微服务
- 分布式服务化
  1、有状态的部分，放到xx中心
  2、无状态的部分，放到应用侧（具体来说是框架和配置部分，尽量不影响业务代码）
- 配置、注册、元数据
  - 配置中心（ConfigCenter）：管理系统需要的配置参数信息
  - 注册中心（RegistryCenter）：管理系统的服务注册、提供发现和协调能力
  - 元数据中心（MetadataCenter）：管理各个节点使用的元数据信息
    相同点：都需要保存和读取数据/状态，变更通知
    不同点：配置是全局非业务参数，注册中心是运行期临时状态，元数据是业务模型
- 服务集群    
  - 对于完全相同能力的多个服务，我们希望他们能一切协同工作，分摊处理流量
    - 路由
     -  负载均衡
        - 服务负载均衡（Service LoadBalance）
          - 多个不同策略，原理不同，目的基本一致（尽量均匀）：
            - Random（带权重）
            - RoundRobin（轮询）
            - LeastActive（快的多给）
            - ConsistentHashLoadBalance（同样参数请求到一个提供者
- 服务流控（Flow Control）
> 系统的容量有限。 保持部分服务能力是最佳选择，然后在问题解决后恢复正常状态
> 
> 响应式编程里，这就是所谓的回弹性（Resilient）
> 
> 需要流控的本质原因是，输入请求大于处理能力

  - 限流（内部线程数，外部调用数或数据量）
  - 服务降级（去掉不必要的业务逻辑，只保留核心逻辑）
  - 过载保护（系统短时间不提供新的业务处理服务，积压处理完后再恢复输入请求）


- 微服务发展历程
  - 1.响应式微服务
  - 2.服务网格与云原生 
  - 3.数据库网格
  - 4.单元化架构

### 8. 分布式缓存 
#### 8.1 缓存是什么
> 缓存的本质：
> 
> 系统各级处理速度不匹配，导致利用空间换时间
> 
> 缓存是提升系统性能的一个简单有效的办法
> 
> 对于 数据一致性，性能，成本 的综合衡量，是引入缓存的必须指标。
>所以说缓存在我们生活中无处不在
>

- 使用缓存要注意的点 
  - 容量
    - 缓存数据容量是必须要考虑的问题
    - 思考系统的设计容量、使用容量、峰值，应该是我们做架构设计的一个常识
    
  - 过期策略 
    - 按FIFO或LRU或LFU
    - 按固定时间过期
    - 按业务时间加权：例如3+5x
- 缓存会碰到的问题
   - 缓存穿透
  >为什么？  大量并发查询不存在的KEY，导致都直接将压力透传到数据库
  > 
  >分析：为什么会多次透传呢？不存在一直为空。
  需要注意让缓存能够区分KEY不存在和查询到一个空值。
  > 
  >解决办法：
  1、缓存空值的KEY，这样第一次不存在也会被加载会记录，下次拿到有这个KEY。
  2、Bloom过滤或RoaringBitmap 判断KEY是否存在。
  3、完全以缓存为准，使用 延迟异步加载 的策略2，这样就不会触发更新。 
  - 缓存击穿
  > 问题：某个KEY失效的时候，正好有大量并发请求访问这个KEY
  > 
  > 分析：跟前面一个其实很像，属于比较偶然的。
  > 
  > 解决办法：
1、KEY的更新操作添加全局互斥锁。
2、完全以缓存为准，使用 延迟异步加载 的策略2，这样就不会触发更新

- 缓存雪崩
  > 问题：当某一时刻发生大规模的缓存失效的情况，会有大量的请求进来直接打到数据库，导致数据
  库压力过大升值宕机
  >
  > 分析：一般来说，由于更新策略、或者数据热点、缓存服务宕机等原因，可能会导致缓存数据同一
  个时间点大规模不可用，或者都更新。所以，需要我们的更新策略要在时间上合适，数据要均匀分
  散，缓存服务器要多台高可用 
  > 解决办法：
  > 
  > 1、更新策略在时间上做到比较均匀。
  > 
  >  2、使用的热数据尽量分散到不同的机器上。
  >
  >  3、多台机器做主从复制或者多副本，实现高可用。
  > 
  > 4、实现熔断限流机制，对系统进行负载能力控制。

#### 8.2分布式缓存的产品
 目前开源界比较有名的是 Redis 和Memcached
  不过，我们以redis 为例

- redis特点：读写性能优异，能达到10万QPS，支持持久化，单个操作原子性，命令的消费单线程，主从，集群
- 为什么要用redis：提高性能：减少不必要的IO时间消耗；高并发：减小下游的并发压力
- 为什么不使用map、guava：本地缓存，很难实现分布式缓存
- 为什么redis快：基于内存，数据结构简单，单线程，IO多路复用
- 数据类型：string，list，set，zset，hash
- 使用场景：计数器，缓存热点数据，缓存token，分布式锁，求交集并集
- RDB：默认持久方式，存到dump.rdb中，配置save参数来调配存储频率（多少秒，完成多少次就进行同步），子进程来完成同步
- AOF：存的是命令日志，文件大，支持落盘频率，适合容灾恢复
- 过期键的删除策略：立即删除，惰性删除，定期删除，redis使用惰性+定期
- 淘汰策略：当redis内存满了，key淘汰机制，lru移除最近最少使用的key或随机移除某个key
- IO多路复用，核心组件：
  - 多路复用程序：监听不同的嵌套字队列，单线程的分发给事件处理器，6.0之后专门为嵌套字的读写开启了异步IO，事件处理还是单线程。
   - 套接字队列
  - 事件分派器
  - 事件处理器 
- 事务的特点：不支持回滚，某个命令出错所有命令都不会执行，通过MULTI、EXEC、DISCARD和WATCH 四个原语实现，一般保证原子性可以使用lua脚本
- 哨兵+主从：自动故障转移保证高可用
- 集群部署：16384个槽位，节点通信通过gossip协议
- 主从复制：初次会先把RDB快照复制过来，进行恢复，在把后续命令给slave
- 分布式锁：加锁和解锁，加锁（key，谁，不存在才加锁，超期时间），解锁通过lua脚本
- 客户端：jedis，lettuce，Redission

### 分布式消息队列
#### 9.1 MQ 基础
> 其他系统通信方式的缺点
> 
> - 文件: 明显不方便，不及时 
> - Socket：使用麻烦，多数情况下不如RPC 
> - 数据库：不实时，但是经常有人拿数据库来模拟消息队列 
> - RPC：调用关系复杂，同步处理，压力大的时候无法缓冲
> 这时MQ 应运而生
> 可以实现异步的消息通信 
> - 可以简化参与各方的复杂依赖关系 
> - 可以在请求量很大的时候，缓冲一下
> 类比线程池里的Queue - 某些情况下能保障消息的可靠性，甚至顺序
> 
> MQ 的优势在于
> 异步通信：异步通信，减少线程等待，特别是处理批量等大事务、耗时操作。   
> 系统解耦：系统不直接调用，降低依赖，特别是不在线也能保持通信最终完成。
> 削峰平谷：压力大的时候，缓冲部分请求消息，类似于背压处理。
> 可靠通信：提供多种消息模式、服务质量、顺序保障等。
![img_4.png](img_4.png)
>

消息处理的保障
三种QoS（注意：这是消息语义的，不是业务语义的）：
- At most once，至多一次，消息可能丢失但是不会重复发送； 
- At least once，至少一次，消息不会丢失，但是可能会重复； 
- Exactly once，精确一次，每条消息肯定会被传输一次且仅一次。
  

消息有序性
- 同一个Topic或Queue的消息，保障按顺序投递
- 不过, 如果做了消息分区，或者批量预取之类的操作，可能就没有顺序了。

消息协议有:
- STOMP
- JMS
- AMQP
- MQTT 小型通讯协议,常用于LOT
- XMPP
- OPEN Messaging

消息队列的通用结构
![img_5.png](img_5.png)
![img_6.png](img_6.png)
- 压缩算法(时间换空间的游戏)

推荐压缩算法有很多推荐使用zstd或者lz4，lz4吞吐量高，zstd压缩比例高，工具场景来选择

目前开源的消息队列

三代：
1、ActiveMQ/RabbitMQ 

2、Kafka/RocketMQ

3、Apache Pulsar

#### 9.2 kafka

- 无消息丢失

生产者与kafka通信之间

producer.send回调函数

ack=all所有在同步副本确认提交才提交成功

retry>0保证出错后重试，幂等由broker端自己保证

禁止同步落后的的副本竞选leader

设置副本数>=3

最少的在同步副本必须大于1

消费端开启手动提交位移

_ 拦截器

应用于包括客户端监控、端到端系统性能检测、消息审计等多种功能在内的场景

实例化时，配置文件里加入拦截器的全类名

生产者拦截器：继承ProducerInterceptor，实现onsend和onAcknowledgement

消费者拦截器：继承ConsumerInterceptor，实现onConsume和onCommit

- tcp的创建时机
生产者端：创建实例的时候，先建立与配置项里面的broker.server的TCP,获取到broker的元数据后建立所有的TCP，元数据同步是定时的，如果在刷新之前请求新的broker则立即建立新TCP，关闭连接可以手动close，也可以配置最大连接时长
  

消费者端：在调用poll方法是才开启TCP连接，第一次连接寻找协调者，第二次连接连接到协调者获取数据brocker，第三次连接到消费brocker

- 消息一致性
最多一次，至少一次，精确一次
  

kafka可以做到精确一次，通过producer端设置幂等参数为true（只保证单个partition幂等），结合事务开启（读已提交）initTransaction、beginTransaction、commitTransaction 和 abortTransaction

消费端同时需要开启read_committed，开启手动提交位移

- Rebalance
导致重平衡是有由于订阅的topic数量变化、topic的分区发生变化或者消费者数量发生变化
  

90%的场景是消费者的数量变化导致的，手动添加了消费者，消费者以外被踢出（消费时间长导致被误判，网络波动导致心跳未发送到），频繁的fullGC。解决方法：适当增加消费时长，心跳时长和心跳次数。

- 多线程消费

同一个消费者组，多个消费者，分别获取消息，分别执行

同一个消费者组，一个消费者获取消息，多个线程处理业务逻辑

- 高水位

高水位之前都是已提交的数据，数据消费的延迟指标，当前下标到高水位的距离，和当前下标到起始位置的距离。由于是定时删除历史数据，所以到起始位置越来越近是非常严重的一个问题，很有可能导致丢数据。
#### Pulsar
基于topic，支持namespace和多租户

![img_7.png](img_7.png)
![img_8.png](img_8.png)
四种消费模式

![img_9.png](img_9.png)

在集群模式下，为了避免单点故障导致丢消息，Broker 在保存消息的时候，必须也把消息
复制到其他的 Broker 上。当某个 Broker 节点故障的时候，并不是集群中任意一个节点都
能替代这个故障的节点，只有那些“和这个故障节点拥有相同数据的节点”才能替代这个故
障的节点。原因就是，每一个 Broker 存储的消息数据是不一样的，或者说，每个节点上都
存储了状态（数据）。这种节点称为“有状态的节点（Stateful Node）”。

Pulsar 与其他消息队列在架构上，最大的不同在于，它的 Broker 是无状态的
（Stateless）。也就是说，在 Pulsar 的 Broker 中既不保存元数据，也不存储消息。那
Pulsar 的消息存储在哪儿呢？我们来看一下 Pulsar 的架构是什么样的。
![img_10.png](img_10.png)
BookKeeper 有点儿类似 HDFS，是一个分布式的存储
集群，只不过它的存储单元和 HDFS 不一样，在 HDFS 中存储单元就是文件，这个很好理
解。

计算存储分离，高可用集群
![img_11.png](img_11.png)
什么是存储计算分离呢？顾名思义，就是将系统的存储职责和计算职责分离开，存储节点只
负责数据存储，而计算节点只负责计算，也就是执行业务逻辑。这样一种设计，称为存储计
算分离。
package io.matthew.homework;

import io.matthew.entity.UserHolder;
import io.matthew.repository.UserRepository;
import org.omg.CORBA.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Matthew
 * @date 2021-02-20 22:27
 */
public class MainDemo {

    //对查找bean 进行总结
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/application.xml");

        //bean 来源一: 自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        //bean 来源二: 依赖注入(内建依赖)
        System.out.println(userRepository.getBeanFactory());


        //bean 来源三：容器內建 Bean
        Environment environmentBean = applicationContext.getBean(Environment.class);

    }
}

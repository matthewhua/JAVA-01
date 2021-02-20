package io.matthew.homework.dependencyInjection.annotaion;

import io.matthew.entity.User;
import io.matthew.enums.City;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * 通过注解的方式自动注入
 *
 *
 * @author Matthew
 * @date 2021-02-20 23:17
 */
public class DependencyInjectionAnnotationOrdinary {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(DependencyInjectionAnnotationOrdinary.class);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        //关闭应用上下文
        applicationContext.close();
    }


    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("帅气杰克");
        user.setCity(City.BEIJING);
        return user;
    }
}

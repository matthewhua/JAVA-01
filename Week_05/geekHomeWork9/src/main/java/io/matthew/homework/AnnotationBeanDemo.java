package io.matthew.homework;

import io.matthew.entity.User;
import io.matthew.enums.City;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matthew
 * @date 2021-02-20 19:44
 */
@Configuration
public class AnnotationBeanDemo {
    /**
     * 通过 Java 注解的方式，定义了一个 Bean
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("帅气杰克");
        user.setCity(City.BEIJING);
        return user;
    }
}

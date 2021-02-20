package io.matthew.homework;

import io.matthew.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Matthew
 * @date 2021-02-20 20:09
 */
public class XmlBeanDemo {

    public static void main(String[] args) {

        //获取xml配置的Bean
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META_INF/application.xml");
        User user = (User)context.getBean("user");

        System.out.println(user.toString());
    }
}

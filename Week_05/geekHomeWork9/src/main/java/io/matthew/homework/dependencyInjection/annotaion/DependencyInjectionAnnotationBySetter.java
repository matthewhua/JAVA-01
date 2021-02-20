package io.matthew.homework.dependencyInjection.annotaion;

import io.matthew.entity.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过注解的方式 Setter方法自动注入
 *
 * @author Matthew
 * @date 2021-02-20 23:21
 */
public class DependencyInjectionAnnotationBySetter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类
        applicationContext.register(DependencyInjectionAnnotationBySetter.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResoucePath = "classpath:/META_INF/application.xml";

        //加载XML资源，解析并生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResoucePath);

        //启动应用上下文
        applicationContext.refresh();
        //依赖查找并创建Bean
//        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        User userHolder = applicationContext.getBean(User.class);
        System.out.println(userHolder);
        //关闭应用上下文
        applicationContext.close();
    }


}

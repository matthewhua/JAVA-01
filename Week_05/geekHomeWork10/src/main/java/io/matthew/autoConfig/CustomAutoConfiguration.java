package io.matthew.autoConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Matthew
 * @date 2021-02-21 0:53
 */
@Configuration
@ConditionalOnProperty(prefix = "matt.demo", name = "enabled", havingValue = "true")
public class CustomAutoConfiguration {
    @Bean(name = "matthew")
    public Student getStudent()
    {
        return new Student(199, "matthew", null, null);
    }

    @Bean("Kalss")
    public Klass getKlass()
    {
        return new Klass(Arrays.asList(getStudent()));

    }

    @Bean
    public ISchool genSchool() {
        return new School(getKlass(), getStudent());
    }
}

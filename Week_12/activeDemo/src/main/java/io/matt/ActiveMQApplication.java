package io.matt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms //ActiveMQ
public class ActiveMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class, args);
    }
}

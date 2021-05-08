package io.matt.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class ActiveConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerURl;

    public static final String QUEUE_TEST = "test.queue";
    public static final String TOPIC_TEST = "test.topic";

    @Bean
    public Queue testQueue() {
        return new ActiveMQQueue(QUEUE_TEST);
    }

    @Bean
    public Topic testTopic() {
        return new ActiveMQTopic(TOPIC_TEST);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(brokerURl);
    }

    //Queue 模式连接注入
    @Bean
    public JmsListenerContainerFactory<?> queueListener(ActiveMQConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    //Topic模式连接注入
    @Bean
    public JmsListenerContainerFactory<?> topicListener(ActiveMQConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}

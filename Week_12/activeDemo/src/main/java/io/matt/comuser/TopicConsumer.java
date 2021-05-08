package io.matt.comuser;


import io.matt.config.ActiveConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicConsumer {

    @JmsListener(destination = ActiveConfig.TOPIC_TEST, containerFactory = "topicListener")
    public void consumeTopic(String topicVo)
    {
        log.info("接收到topic={}的消息,msg={}", ActiveConfig.TOPIC_TEST, topicVo);
    }
}

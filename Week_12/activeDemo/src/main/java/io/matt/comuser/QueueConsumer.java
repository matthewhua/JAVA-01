package io.matt.comuser;


import io.matt.config.ActiveConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConsumer {

    @JmsListener(destination = ActiveConfig.QUEUE_TEST, containerFactory = "queueListener")
    public void consumerMsg(String queueVo)
    {
        log.info("接收到queue={}的消息, msg={}", ActiveConfig.QUEUE_TEST, queueVo);
    }
}

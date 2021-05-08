package io.matt.producer;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.matt.vo.QueueVO;
import io.matt.vo.TopicVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
@Slf4j
public class MessageProducer {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    public void sendMsgToQueue(QueueVO queueVO)
    {
        try {
            log.info("向queue中发送消息--{}", queueVO);
            String s = mapper.writeValueAsString(queueVO);
            jms.convertAndSend(queue, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToTopic(TopicVO queueVO)
    {
        try {
            log.info("向topic中发送消息--{}", queueVO);
            String s = mapper.writeValueAsString(queueVO);
            jms.convertAndSend(topic, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

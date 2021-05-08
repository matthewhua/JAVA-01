package io.matt.init;

import io.matt.producer.MessageProducer;
import io.matt.vo.QueueVO;
import io.matt.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class InitRun implements CommandLineRunner {
    @Autowired
    private MessageProducer messageProducer;

    @Override
    public void run(String... args) throws Exception {
        for(int i=0;i<5;i++){
            QueueVO vo = new QueueVO();
            vo.setCode("code"+(i+1));
            vo.setCnt(i);
            vo.setLocalDateTime(LocalDateTime.now());
            messageProducer.sendMsgToQueue(vo);
        }

        for(int i=0;i<10;i++){
            TopicVO vo = new TopicVO();
            vo.setChannel("ch"+(i+1));
            vo.setRate(new BigDecimal(i));
            vo.setCreateTime(LocalDateTime.now());
            messageProducer.sendMsgToTopic(vo);
        }
    }
}

package handsome.matt.kafka.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Matthew
 * @date 2020/12/6 15:12
 */
@Component
public class MtKafkaConsumer {

    @KafkaListener(topics = {"test","first_topic"})
    public void listener(ConsumerRecord record)
    {
        Optional<Object> msg = Optional.ofNullable(record.value());
        msg.ifPresent(System.out::println);
    }
}

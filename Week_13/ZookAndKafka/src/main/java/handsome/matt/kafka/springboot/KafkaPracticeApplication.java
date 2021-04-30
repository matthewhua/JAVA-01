package handsome.matt.kafka.springboot;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.TimeUnit;

/**
 * @author Matthew
 * @date 2020/12/6 15:07
 */
@SpringBootApplication
public class KafkaPracticeApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaPracticeApplication.class, args);
        MtKafkaProducer bean = context.getBean(MtKafkaProducer.class);
        for (int i = 0; i < 10; i++) {
            bean.send();
            TimeUnit.SECONDS.sleep(2);
        }
       // SpringApplication.run(KafkaPracticeApplication.class, args).close();
    }

    //RequestReplyFuture<K, V, R> sendAndReceive(ProducerRecord<K, V> record);

    @Bean
    public ApplicationRunner runner(ReplyingKafkaTemplate<String, String, String> template)
    {
        return args -> {
            ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", "foo");
            RequestReplyFuture<String, String, String> reply = template.sendAndReceive(record);
            SendResult<String, String> sendResult = reply.getSendFuture().get();
            System.out.println("Sent ok:" + sendResult.getRecordMetadata());
            ConsumerRecord<String, String> consumerRecord = reply.get();
            System.out.println("Return value: " + consumerRecord.value());
        };
    }


    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> repliesContainer){
        return new ReplyingKafkaTemplate<String, String, String>(pf, repliesContainer);
    }


    @Bean
    public ConcurrentMessageListenerContainer<String, String> repliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {

        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer("replies");
        repliesContainer.getContainerProperties().setGroupId("repliesGroup");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }



    @Bean
    public NewTopic kRequests() {
        return new NewTopic("kRequests", 10, (short) 2);
    }

    @Bean
    public NewTopic kReplies() {
        return new NewTopic("kReplies", 10, (short) 2);
    }



}

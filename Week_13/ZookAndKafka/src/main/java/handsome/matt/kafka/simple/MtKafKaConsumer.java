package handsome.matt.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Matthew
 * @date 2020/12/5 20:59
 */
public class MtKafKaConsumer  extends Thread{
    KafkaConsumer<Integer,String> consumer;
    String topic;

    public MtKafKaConsumer(String topic) {
        Properties properties=new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.31.133:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"matt-consumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "matt-hid1");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"30000");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //一个新的group的消费者去消费一个topic
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest"); //这个属性. 它能够消费昨天发布的数据
        consumer=new KafkaConsumer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        consumer.subscribe(Collections.singleton(this.topic));
        while (true){
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach(record -> {
                System.out.println(record.key() + "->" + record.value() + "->" + record.offset());
            });
        }
    }

    public static void main(String[] args) {
        new MtKafKaConsumer("test_partition").start();
    }
}
